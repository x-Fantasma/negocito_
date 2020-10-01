package co.negocito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import co.negocito.model.Producto;
import co.negocito.service.ProductoService;

@RestController
@RequestMapping("/negocito")
@CrossOrigin("localhost:4200")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("{id}/getProducto")
	public Producto getProducto(@PathVariable(name = "id", required = true) Long id) {
		return productoService.getById(id);
	}
	
	@GetMapping("/getAll()")
	public List getProdutos() {
		return productoService.getAll();
	}
	
	@PostMapping("/saveproducto")
	public Producto saveProducto(@RequestBody Producto producto) throws Exception {
		return productoService.saveProducto(producto);
	}
}
