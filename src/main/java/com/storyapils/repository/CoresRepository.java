package com.storyapils.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Cores;
import com.storyapils.repository.cores.CoresRepositoryQuery;

@Repository
public interface CoresRepository extends JpaRepository<Cores, Long>, CoresRepositoryQuery {

	public Optional<Cores> findByDescricao(String descricao);
}
