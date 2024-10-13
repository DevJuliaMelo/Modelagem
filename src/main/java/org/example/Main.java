package org.example;

import org.example.DAO.AutorDAO;
import org.example.DAO.LivroDAO;
import org.example.entity.Autor;
import org.example.entity.Livro;

public class Main {
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorDAO();
        Autor autor = new Autor();

        autor.setIdAutor(1);
        autor.setNome("Júlia");
        autor.setNacionalidade("Brasileira");

        autorDAO.atualizarAutor(autor);
        autorDAO.inserirAutor(autor);
        autorDAO.excluirAutor(1);

        LivroDAO livroDAO = new LivroDAO();
        Livro livro = new Livro();

        livro.setIdLivro(1);
        livro.setTitulo("Seja foda");
        livro.setAnoPublicacao(2022);
        livro.setIdAutor(2);

        livroDAO.atualizarLivro(livro);
        livroDAO.inserirLivro(livro);
        livroDAO.excluirLivro(4);
    }
}
