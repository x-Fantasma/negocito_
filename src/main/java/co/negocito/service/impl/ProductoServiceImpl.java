package co.negocito.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import antlr.collections.List;
import co.negocito.exceptions.ExceptionsMessages;
import co.negocito.exceptions.config.MalformedHeaderException;
import co.negocito.exceptions.impl.ProductoExceptions;
import co.negocito.model.Imagen;
import co.negocito.model.Producto;
import co.negocito.repository.ProductoRepository;
import co.negocito.service.ImagenService;
import co.negocito.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ImagenService imagenService;
	
	private Map<String, String> valuesMap = new HashMap<String, String>();
	private Cloudinary cloudinary;
	
	public ProductoServiceImpl() {
		configCloudinary();
	}

	@Override
	public void save(Producto producto) throws FileAlreadyExistsException {}
	
	@Override
	public Producto saveProducto(Producto producto) throws FileAlreadyExistsException {
				
		Producto p = null;
		if(getById(producto.getId()) == null) {
			try {
				producto.setImagen(saveImagen(producto.getFile()));
				p = productoRepository.save(producto);
			} catch (Exception e) {
				if(e instanceof IOException) {
					throw new ProductoExceptions(ExceptionsMessages.MENSAJE_ARCHIVO_INVALIDO);
				}
			}			
		}else {
			throw new FileAlreadyExistsException(ExceptionsMessages.MENSAJE_PRODUCTO_EXISTENTE);
		}		
		return p;
	}

	@Override
	public void update(Producto producto) throws Exception {
		
		if(getById(producto.getId()) != null) {
			productoRepository.save(producto);
		}else {
			throw new FileAlreadyExistsException(ExceptionsMessages.MENSAJE_PRODUCTO_INEXISTENTE);
		}			
	}

	@Override
	public void deleteById(Long id) {		
		productoRepository.deleteById(id);		
	}

	@Override
	public Producto getById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public List getAll() {
		return (List)productoRepository.findAll();
	}
	
	private Imagen saveImagen(MultipartFile multipartfile) throws Exception {
		
		Imagen img = null;
		BufferedImage bufferedImage = ImageIO.read(multipartfile.getInputStream());
		if(bufferedImage == null) {
			throw new MalformedHeaderException(ExceptionsMessages.MENSAJE_ARCHIVO_INVALIDO);
		}else {
			File file = convertMultipartfile(multipartfile);
			@SuppressWarnings("rawtypes")
			Map results = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			Imagen imagen = 
					new Imagen((String) results.get("original_filename"),
					(String) results.get("url"), 
					(String) results.get("public_id"));
			img = imagenService.saveImagen(imagen);
			file.delete();
		}
		return img;
	}
	
	private File convertMultipartfile(MultipartFile multipartfile) throws IOException {
		
		File file = new File(multipartfile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartfile.getBytes());
		fo.close();
		return file;
	}
	
	private void configCloudinary() {
		
		valuesMap.put("cloud_name", "fanti");
		valuesMap.put("api_key", "652757146893581");
		valuesMap.put("api_secret", "_geMvcmk-wQP7t5QKFmjoDNCsO4");
		cloudinary = new Cloudinary(valuesMap);
	}
		
}
