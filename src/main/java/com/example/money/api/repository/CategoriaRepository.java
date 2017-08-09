package com.example.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.money.api.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
