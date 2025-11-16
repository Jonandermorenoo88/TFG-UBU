package es.ubu.gii.ISOAssetManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}