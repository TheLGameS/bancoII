package org.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.hibernate.control.ModelController;
import org.model.Consulta;
import org.model.Medico;
import org.model.Paciente;

public class ExemploHibernate {

	private ModelController modelCtr = new ModelController();
	private final int totalInsercao=1000000;

	public void close() {
		modelCtr.close();
	}

	public void clean() {
		modelCtr.deleteAll();
	}

	public void insertPaciente() {
		for(int i=0 ; i< 500; i++){
			int mes = new Random().nextInt(12);
			int dia = new Random().nextInt(28);

			Calendar dn1 = new GregorianCalendar(1989,mes,dia);
			dn1.add(Calendar.MONTH, -1);
			Paciente pac1 = new Paciente("Paciente "+i, "Ararangua", "SC", "rua "+i+" "+mes+" "+dia, dn1.getTime());
			modelCtr.insert(pac1);		
		}
	}

	public void insertMedico() {
		for(int i=0 ; i< 500; i++){
			int cod = i+1;
			Medico med1 = new Medico(cod, "Medico "+cod);
			modelCtr.insert(med1);
		}
	}

	public void insertConsulta() {
		Session session = modelCtr.getSession();
		Transaction tx = session.beginTransaction();
		
		for(int i=0 ; i< totalInsercao; i++){
			int cod = new Random().nextInt(499);
			Medico med = new Medico(cod == 0 ? 1 : cod, null);
			Paciente pac = new Paciente(cod == 0 ? 1 : cod,null,null,null,null,null);
			Consulta cons = new Consulta(i+1, pac, med, new Date(), new Random().nextInt(999));
			session.save(cons);
			
			if(i == 100000 || i == 200000 || i == 300000 || i == 400000 || i == 500000 ||
			   i == 600000 || i == 700000 || i == 800000 || i == 900000 ){
					tx.commit();
					session.flush();
					session.clear();
					tx = session.beginTransaction();
					System.out.println("Iteracao: "+i+" Hora: "+new Date());
			}
		}
		tx.commit();
		System.out.println("Iteracao:1000000"+" Hora: "+new Date());
	}

	public static void main(String[] args) {

		ExemploHibernate ex = new ExemploHibernate();
		ex.clean();
		
		long tempoPaciente = System.currentTimeMillis();
		ex.insertPaciente();
		tempoPaciente = System.currentTimeMillis() - tempoPaciente;
		formataMostraTempo(Paciente.class.getName(),tempoPaciente);

		long tempoMedico = System.currentTimeMillis();
		ex.insertMedico();
		tempoMedico = System.currentTimeMillis() - tempoMedico;
		formataMostraTempo(Medico.class.getName(),tempoMedico);

		System.out.println("Iteracao:      0"+"\t Hora: "+new Date());
		long tempoConsulta = System.currentTimeMillis();
		ex.insertConsulta();
		tempoConsulta = System.currentTimeMillis() - tempoConsulta;
		formataMostraTempo(Consulta.class.getName(),tempoConsulta);

		ex.close();
	}

	private static void formataMostraTempo(String text ,long tempoPaciente) {
		long hours = (int)  tempoPaciente / 3600000;
		long minutes = (int) tempoPaciente / 60000;
		long seconds = (int) tempoPaciente / 1000;
		System.out.println(String.format(text +" %02d:%02d:%02d", hours,minutes,seconds));
	}
}