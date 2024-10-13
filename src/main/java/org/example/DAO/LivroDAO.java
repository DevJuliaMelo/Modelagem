package org.example.DAO;

import org.example.conect.ConexaoBD;
import org.example.entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO LIVRO (TITULO, ANO_PUBLICACAO, ID_AUTOR) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getAnoPublicacao());
            ps.setInt(3, livro.getIdAutor());
            ps.executeUpdate();
            System.out.println("Livro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE LIVRO SET TITULO = ?, ANO_PUBLICACAO = ?, ID_AUTOR = ? WHERE ID_LIVRO = ?";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getAnoPublicacao());
            ps.setInt(3, livro.getIdAutor());
            ps.setInt(4, livro.getIdLivro());
            ps.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(int idLivro) {
        String sql = "DELETE FROM LIVRO WHERE ID_LIVRO = ?";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLivro);
            ps.executeUpdate();
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM LIVRO";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("ID_LIVRO"));
                livro.setTitulo(rs.getString("TITULO"));
                livro.setAnoPublicacao(rs.getInt("ANO_PUBLICACAO"));
                livro.setIdAutor(rs.getInt("ID_AUTOR"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM LIVRO WHERE ID_AUTOR = ?";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAutor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro();
                    livro.setIdLivro(rs.getInt("ID_LIVRO"));
                    livro.setTitulo(rs.getString("TITULO"));
                    livro.setAnoPublicacao(rs.getInt("ANO_PUBLICACAO"));
                    livro.setIdAutor(rs.getInt("ID_AUTOR"));
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}
