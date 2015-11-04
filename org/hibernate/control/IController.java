package org.hibernate.control;

import java.util.List;
import java.util.Map;

public interface IController {

	public void insert(Object object);
	public void update(Object object);
	public void delete(Object object);
	public List<?> list(Class<?> iClass);
	public Object getByCode(Class<?> iClass, Integer id);
	public List<?> query(String sql, Map<String,Integer> parameters,  Class<?> iClass);
	
}
