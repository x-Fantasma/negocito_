package co.negocito.service;

import co.negocito.model.Imagen;

public interface ImagenService extends CRUD<Imagen>{

	Imagen saveImagen(Imagen imagen) throws Exception;
}
