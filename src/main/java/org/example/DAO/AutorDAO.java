package org.example.DAO;

import org.example.conect.ConexaoBD;
import org.example.entity.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO AUTOR (NOME, NACIONALIDADE) VALUES (?, ?)";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.executeUpdate();
            System.out.println("Autor inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE AUTOR SET NOME = ?, NACIONALIDADE = ? WHERE ID_AUTOR = ?";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setInt(3, autor.getIdAutor());
            ps.executeUpdate();
            System.out.println("Autor atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirAutor(int idAutor) {
        String sql = "DELETE FROM AUTOR WHERE ID_AUTOR = ?";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAutor);
            ps.executeUpdate();
            System.out.println("Autor excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM AUTOR";
        try (PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("ID_AUTOR"));
                autor.setNome(rs.getString("NOME"));
                autor.setNacionalidade(rs.getString("NACIONALIDADE"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}
