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
	private final int totalInsercao=10000;
	
	public void close() {
		modelCtr.close();
	}

	public void clean() {
		modelCtr.deleteAll();
	}

	/*public void inserts() {
		Calendar dn1 = new GregorianCalendar(1980,3,1);
		dn1.add(Calendar.MONTH, -1);
		Paciente pac1 = new Paciente("Paciente 1", "Ararangua", "SC", "R. das Oliveiras", dn1.getTime());
		modelCtr.insert(pac1);

		Calendar dn2 = new GregorianCalendar(1978,02,27);
		dn2.add(Calendar.MONTH, -1);
		Paciente pac2 = new Paciente("Paciente 2", "Arroio do Silva", "SC", "R. das Palmeiras", dn2.getTime());
		modelCtr.insert(pac2);

		Medico med1 = new Medico(1, "Medico 1");
		modelCtr.insert(med1);

		Medico med2 = new Medico(2, "Medico 2");
		modelCtr.insert(med2);

		Medico med3 = new Medico(3, "Medico 3");
		modelCtr.insert(med3);

		Consulta cons1 = new Consulta(1, pac1, med1, new Date(), 100.0);
		modelCtr.insert(cons1);
		Consulta cons2 = new Consulta(2, pac1, med2, new Date(), 110.0);
		modelCtr.insert(cons2);
		Consulta cons3 = new Consulta(3, pac2, med2, new Date(), 110.0);
		modelCtr.insert(cons3);
		Consulta cons4 = new Consulta(4, pac1, med1, new Date(), 50.0);
		modelCtr.insert(cons4);

		System.out.println("Insercoes realizazadas com sucesso!!!");

	}*/

	public void insertPaciente() {
		for(int i=0 ; i< totalInsercao; i++){
			int mes = new Random().nextInt(12);
			int dia = new Random().nextInt(28);

			Calendar dn1 = new GregorianCalendar(1989,mes,dia);
			dn1.add(Calendar.MONTH, -1);
			Paciente pac1 = new Paciente("Paciente "+i, "Ararangua", "SC", "rua "+i+" "+mes+" "+dia, dn1.getTime());
			modelCtr.insert(pac1);		
		}
	}

	public void insertMedico() {
		for(int i=0 ; i< totalInsercao; i++){
			int cod = i+1;
			Medico med1 = new Medico(cod, "Medico "+cod);
			modelCtr.insert(med1);
		}
	}

	public void insertConsulta() {
		for(int i=0 ; i< totalInsercao; i++){
			int cod = i+1;
			Medico med = new Medico(cod, null);
			Paciente pac = new Paciente(cod,null,null,null,null,null);
			Consulta cons = new Consulta(cod, pac, med, new Date(), new Random().nextInt(999));
			modelCtr.insert(cons);

		}
	}

	/*@SuppressWarnings("unchecked")
	public void print() {
		//Alterar a chamada em função do último valor da sequence
		Paciente pac = (Paciente)modelCtr.getByCode(Paciente.class,codigoPacienteBusca);
		System.out.println("\nId: "+pac.getId()+" Nome: "+pac.getNome()+"\n");

		//Lista pacientes
		System.out.println("\n-- Relacao de Pacientes --");
		List<Paciente> listPaciente = (List<Paciente>)modelCtr.list(Paciente.class);
		for (Paciente paciente : listPaciente) {
			System.out.println("Id: "+paciente.getId()+" Nome: "+paciente.getNome());
		}

		//Lista médicos
		System.out.println("\n-- Relacao de Medicos --");
		List<Medico> listMedico = (List<Medico>)modelCtr.list(Medico.class);
		for (Medico medico : listMedico) {
			System.out.println("Id: "+medico.getId()+" Nome: "+medico.getNome());
		}

		//Lista consulta
		System.out.println("\n-- Relacao de Medicos --");
		List<Consulta> listConsulta = (List<Consulta>)modelCtr.list(Consulta.class);
		for (Consulta consulta : listConsulta) {
			System.out.println("Id: "+consulta.getId()+" Paciente: "+consulta.getPaciente().getNome()+
					" Medico: "+consulta.getMedico().getNome()+ " Valor: "+consulta.getValor());
		}


		//Lista Paciente (consutla geral)
		System.out.println("\n-- Relacao de Pacientes --");
		String sql = "select * from paciente where cod_paciente = :cod_paciente";

		Map<String, Integer> parameters = new LinkedHashMap<String, Integer>();
		parameters.put("cod_paciente", codigoPacienteBusca);

		listPaciente = (List<Paciente>)modelCtr.query(sql, parameters, Paciente.class);
		for (Paciente paciente : listPaciente) {
			System.out.println("Id: "+paciente.getId()+" Nome: "+paciente.getNome());
		}

	}*/

	public static void main(String[] args) {

		ExemploHibernate ex = new ExemploHibernate();
		ex.clean();
		//ex.inserts();

		long tempoPaciente = System.currentTimeMillis();
		ex.insertPaciente();
		tempoPaciente = System.currentTimeMillis() - tempoPaciente;
		formataMostraTempo(Paciente.class.getName(),tempoPaciente);
		//ex.print();

		long tempoMedico = System.currentTimeMillis();
		ex.insertMedico();
		tempoMedico = System.currentTimeMillis() - tempoMedico;
		formataMostraTempo(Medico.class.getName(),tempoMedico);

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