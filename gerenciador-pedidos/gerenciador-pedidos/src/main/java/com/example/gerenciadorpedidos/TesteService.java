package com.example.gerenciadorpedidos;

import com.example.gerenciadorpedidos.modelo.Produto;
import com.example.gerenciadorpedidos.repositorio.ProdutoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TesteService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;



    public void testarQueries()  {

        produtoRepositorio.save(new Produto("Mouse", 350.00));
        System.out.println(produtoRepositorio.findByNome("Mouse"));
    }
}