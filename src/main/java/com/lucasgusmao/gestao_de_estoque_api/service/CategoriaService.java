package com.lucasgusmao.gestao_de_estoque_api.service;

import com.lucasgusmao.gestao_de_estoque_api.execption.ResourceNotFoundException;
import com.lucasgusmao.gestao_de_estoque_api.model.Categoria;
import com.lucasgusmao.gestao_de_estoque_api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}
