package co.negocito.service;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.web.multipart.MultipartFile;

import co.negocito.model.Producto;

public interface ProductoService extends CRUD<Producto>{

	Producto saveProducto(Producto producto) throws FileAlreadyExistsException;
}
