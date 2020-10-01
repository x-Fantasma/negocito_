package co.negocito.service;

import antlr.collections.List;

public interface CRUD<T> {

	void save(T t) throws Exception;
	void update(T t) throws Exception;
	void deleteById(Long id);
	T getById(Long id);
	List getAll();
}
