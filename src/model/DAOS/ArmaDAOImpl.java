package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Arma;
import model.persistence.Conexao;

public class ArmaDAOImpl implements ArmaDAO {
	Connection con;
	Arma arma = new Arma();
	public ArmaDAOImpl() {
		try {
			Conexao conexao = new Conexao();
			con = conexao.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Arma a) {
		String SQL = """
				INSERT INTO arma (nome, peso, descricao, tipo_dano, dano, forca, fe, destreza, inteligencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";
		try (PreparedStatement pst = con.prepareStatement(SQL)) { 
			System.out.println("Inserindo arma => " + a.getNome());
			pst.setString(1,  a.getNome());
			pst.setDouble(2,  a.getPeso());
			pst.setString(3,  a.getDescricao());
			pst.setString(4,  a.getTipo_dano());
			pst.setInt(5,  a.getDano());
			pst.setString(6,  a.getForca());
			pst.setString(7,  a.getFe());
			pst.setString(8,  a.getDestreza());
			pst.setString(9,  a.getInteligencia());
			int i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Arma a) {
		String SQL = """
				UPDATE arma SET nome=?, peso=?, descricao=?, tipo_dano=?, dano=?, forca=?, fe=?, destreza=?, inteligencia=?
				WHERE id=?
				""";
		try (PreparedStatement pst = con.prepareStatement(SQL)) {
			pst.setString(1,  a.getNome());
			pst.setDouble(2,  a.getPeso());
			pst.setString(3,  a.getDescricao());
			pst.setString(4,  a.getTipo_dano());
			pst.setInt(5,  a.getDano());
			pst.setString(6,  a.getForca());
			pst.setString(7,  a.getFe());
			pst.setString(8,  a.getDestreza());
			pst.setString(9,  a.getInteligencia());
			pst.setInt(10,  a.getId());
			int i = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Arma a) {
		String SQL = """
				DELETE FROM arma WHERE id=?
				""";
		try(PreparedStatement pst = con.prepareStatement(SQL)) {
			pst.setInt(1,  a.getId());
			System.out.println("Escluído arma de id: " + arma.getId());
			int i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Arma> pesquisarPorNome(String nome) {
		List<Arma> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM arma WHERE nome LIKE ?
					""";
			PreparedStatement pst = con.prepareStatement(SQL);
			pst.setString(1, "%" + nome + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Arma a = new Arma();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setPeso(rs.getDouble("peso"));
				a.setDescricao(rs.getString("descricao"));
				a.setTipo_dano(rs.getString("tipo_dano"));
				a.setDano(rs.getInt("dano"));
				a.setForca(rs.getString("forca"));
				a.setFe(rs.getString("fe"));
				a.setDestreza(rs.getString("destreza"));
				a.setInteligencia(rs.getString("inteligencia"));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Arma> pesquisarTodos() {
		List<Arma> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM arma
					""";
			PreparedStatement pst = con.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Arma a = new Arma();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				a.setPeso(rs.getDouble("peso"));
				a.setDescricao(rs.getString("descricao"));
				a.setTipo_dano(rs.getString("tipo_dano"));
				a.setDano(rs.getInt("dano"));
				a.setForca(rs.getString("forca"));
				a.setFe(rs.getString("fe"));
				a.setDestreza(rs.getString("destreza"));
				a.setInteligencia(rs.getString("inteligencia"));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
