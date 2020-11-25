package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Fornecedor;
import com.storyapils.repository.fornecedor.FornecedorRepositoryQuery;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryQuery {
	
	public Optional<Fornecedor> findByRazaosocial(String razaosocial); 

}
