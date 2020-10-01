package co.negocito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.negocito.model.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long>{

}
