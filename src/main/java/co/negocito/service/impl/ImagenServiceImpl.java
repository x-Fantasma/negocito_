package co.negocito.service.impl;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import co.negocito.exceptions.ExceptionsMessages;
import co.negocito.model.Imagen;
import co.negocito.repository.ImagenRepository;
import co.negocito.service.ImagenService;

@Service
public class ImagenServiceImpl implements ImagenService{

	@Autowired
	private ImagenRepository imagenRepository;
	
	@Override
	public void save(Imagen imagen) throws Exception {
		
	}

	@Override
	public void update(Imagen imagen) throws Exception {
		
		if(getById(imagen.getId()) != null) {
			imagenRepository.save(imagen);
		}
	}

	@Override
	public void deleteById(Long id) {
		
		imagenRepository.deleteById(id);
	}

	@Override
	public Imagen getById(Long id) {
		return imagenRepository.findById(id).orElse(null);
	}

	@Override
	public List getAll() {
		return (List) imagenRepository.findAll();
	}

	@Override
	public Imagen saveImagen(Imagen imagen) throws Exception {
		
		Imagen img = null;
		if(getById(imagen.getId()) == null) {
			img = imagenRepository.save(imagen);
		}else {
			throw new FileAlreadyExistsException(ExceptionsMessages.MENSAJE_ARCHIVO_INVALIDO);
		}
		return img;
	}

}
