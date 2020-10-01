package co.negocito.service;

import co.negocito.model.Producto;

public interface ProductoService extends CRUD<Producto>{

	Producto saveProducto(Producto producto) throws Exception;
}
