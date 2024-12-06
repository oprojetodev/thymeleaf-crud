package com.oprojetodev.thymeleaf.repository;

import org.springframework.data.repository.CrudRepository;

import com.oprojetodev.thymeleaf.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {

}
