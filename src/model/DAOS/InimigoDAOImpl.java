package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Inimigo;
import model.RPGException;
import model.persistence.Conexao;

public class InimigoDAOImpl implements InimigoDAO {

	private Conexao conexao = new Conexao();

	@Override
	public void inserir(Inimigo i) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					INSERT INTO inimigo (nome, dano_causa, vida, fraqueza, descricao)
					VALUES (?, ?, ?, ?, ?)
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, i.getNome());
			stm.setInt(2, i.getDanoCausa());
			stm.setInt(3, i.getVida());
			stm.setString(4, i.getFraqueza());
			stm.setString(5, i.getDescricao());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public void excluir(Inimigo i) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					DELETE FROM Inimigo WHERE id = ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, i.getId());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public void atualizar(Inimigo i) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					UPDATE Inimigo SET nome=?, dano_causa=?, vida=?, fraqueza=?, descricao=?
					WHERE id = ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, i.getNome());
			stm.setInt(2, i.getDanoCausa());
			stm.setInt(3, i.getVida());
			stm.setString(4, i.getFraqueza());
			stm.setString(5, i.getDescricao());
			stm.setInt(6, i.getId());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public List<Inimigo> pesquisarTodos() throws RPGException, ClassNotFoundException {
		List<Inimigo> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM Inimigo
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Inimigo i = new Inimigo();
				i.setId(rs.getInt("id"));
				i.setNome(rs.getString("nome"));
				i.setDanoCausa(rs.getInt("dano_causa"));
				i.setVida(rs.getInt("vida"));
				i.setFraqueza(rs.getString("fraqueza"));
				i.setDescricao(rs.getString("descricao"));
				lista.add(i);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
		return lista;
	}

	@Override
	public List<Inimigo> pesquisarPorNome(String nome) throws RPGException, ClassNotFoundException {
		List<Inimigo> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM Inimigo WHERE nome LIKE ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Inimigo i = new Inimigo();
				i.setId(rs.getInt("id"));
				i.setNome(rs.getString("nome"));
				i.setDanoCausa(rs.getInt("dano_causa"));
				i.setVida(rs.getInt("vida"));
				i.setFraqueza(rs.getString("fraqueza"));
				i.setDescricao(rs.getString("descricao"));
				lista.add(i);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
		return lista;
	}

}
