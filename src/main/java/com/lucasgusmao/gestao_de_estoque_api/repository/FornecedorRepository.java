package com.lucasgusmao.gestao_de_estoque_api.repository;

import com.lucasgusmao.gestao_de_estoque_api.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
