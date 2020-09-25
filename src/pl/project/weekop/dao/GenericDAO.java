package pl.project.weekop.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {
	
	T create(T object);
	
	T read(PK primaryKey);
	
	boolean update(T object);
	
	boolean delete(PK key);
	
	List<T> getAll();

}
