package com.example.gerenciadorpedidos.repositorio;

import com.example.gerenciadorpedidos.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    List<Produto> findByNome(String nome);

}