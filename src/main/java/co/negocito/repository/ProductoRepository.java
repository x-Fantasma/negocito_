package co.negocito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.negocito.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
