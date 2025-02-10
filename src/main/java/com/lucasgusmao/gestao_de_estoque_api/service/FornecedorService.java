package com.lucasgusmao.gestao_de_estoque_api.service;

import com.lucasgusmao.gestao_de_estoque_api.execption.ResourceNotFoundException;
import com.lucasgusmao.gestao_de_estoque_api.model.Fornecedor;
import com.lucasgusmao.gestao_de_estoque_api.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Long id) {
        Fornecedor fornecedor = buscarPorId(id);
        fornecedorRepository.delete(fornecedor);
    }
}
