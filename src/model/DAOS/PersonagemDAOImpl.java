package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Personagem;
import model.persistence.Conexao;

public class PersonagemDAOImpl implements PersonagemDAO {

    private Connection con;

    public PersonagemDAOImpl() {
        try {
            Conexao conexao = new Conexao();
            con = conexao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserir(Personagem p) {
        String SQL = """
                INSERT INTO personagem (nome, classe, item, genero, idade, descricao) 
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, p.getNome());
            pst.setString(2, p.getClasse());
            pst.setString(3, p.getItem());
            pst.setString(4, p.getGenero());
            pst.setInt(5, p.getIdade());
            pst.setString(6, p.getDescricao());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Personagem p) {
        String SQL = """
                UPDATE personagem 
                SET nome = ?, classe = ?, item = ?, genero = ?, idade = ?, descricao = ? 
                WHERE id = ?
                """;

        try (PreparedStatement pst = con.prepareStatement(SQL)) {
        	pst.setString(1, p.getNome());
            pst.setString(2, p.getClasse());
            pst.setString(3, p.getItem());
            pst.setString(4, p.getGenero());
            pst.setInt(5, p.getIdade());
            pst.setString(6, p.getDescricao());
            pst.setInt(7, p.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Personagem p) {
        String SQL = """
                DELETE FROM personagem WHERE id = 
                """;

        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setInt(1, p.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personagem> pesquisarPorNome(String nome) {
        List<Personagem> lista = new ArrayList<>();
        String SQL = """
                SELECT * FROM personagem WHERE nome LIKE ?
                """;

        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, "%" + nome + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Personagem p = new Personagem();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setClasse(rs.getString("classe"));
                p.setItem(rs.getString("item"));
                p.setGenero(rs.getString("genero"));
                p.setIdade(rs.getInt("idade"));
                p.setDescricao(rs.getString("descricao"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Personagem> pesquisarTodos() {
        List<Personagem> lista = new ArrayList<>();
        String SQL = """
                SELECT * FROM personagem
                """;

        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Personagem p = new Personagem();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setClasse(rs.getString("classe"));
                p.setItem(rs.getString("item"));
                p.setGenero(rs.getString("genero"));
                p.setIdade(rs.getInt("idade"));
                p.setDescricao(rs.getString("descricao"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
