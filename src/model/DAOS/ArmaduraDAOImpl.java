package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Armadura;
import model.RPGException;
import model.persistence.Conexao;

public class ArmaduraDAOImpl implements ArmaduraDAO {
	
	private Conexao conexao = new Conexao();

	@Override
	public void inserir(Armadura arm) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					INSERT INTO armadura (nome, peso, descricao, tipo_armadura,
					veneno, fisica, magica, fogo, eletrica, hemorragica) VALUES (
					?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, arm.getNome());
			stm.setDouble(2, arm.getPeso());
			stm.setString(3, arm.getDescricao());
			stm.setString(4, arm.getTipoArmadura());
			stm.setInt(5, arm.getVeneno());
			stm.setInt(6, arm.getFisica());
			stm.setInt(7, arm.getMagica());
			stm.setInt(8, arm.getFogo());
			stm.setInt(9, arm.getEletrica());
			stm.setInt(10, arm.getHemorragica());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public void excluir(Armadura arm) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					DELETE FROM armadura WHERE id = ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, arm.getId());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public void atualizar(Armadura arm) throws RPGException, ClassNotFoundException {
		try {
			String SQL = """
					UPDATE armadura SET nome=?, peso=?, descricao=?, tipo_armadura=?,
					veneno=?, fisica=?, magica=?, fogo=?, eletrica=?, hemorragica=?
					WHERE id = ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, arm.getNome());
			stm.setDouble(2, arm.getPeso());
			stm.setString(3, arm.getDescricao());
			stm.setString(4, arm.getTipoArmadura());
			stm.setInt(5, arm.getVeneno());
			stm.setInt(6, arm.getFisica());
			stm.setInt(7, arm.getMagica());
			stm.setInt(8, arm.getFogo());
			stm.setInt(9, arm.getEletrica());
			stm.setInt(10, arm.getHemorragica());
			stm.setInt(11, arm.getId());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
	}

	@Override
	public List<Armadura> pesquisarTodos() throws RPGException, ClassNotFoundException {
		List<Armadura> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM armadura
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Armadura arm = new Armadura();
				arm.setId(rs.getInt("id"));
				arm.setNome(rs.getString("nome"));
				arm.setPeso(rs.getDouble("peso"));
				arm.setDescricao(rs.getString("descricao"));
				arm.setTipoArmadura(rs.getString("tipo_armadura"));
				arm.setVeneno(rs.getInt("veneno"));
				arm.setFisica(rs.getInt("fisica"));
				arm.setMagica(rs.getInt("magica"));
				arm.setFogo(rs.getInt("fogo"));
				arm.setEletrica(rs.getInt("eletrica"));
				arm.setHemorragica(rs.getInt("hemorragica"));
				lista.add(arm);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
		return lista;
	}

	@Override
	public List<Armadura> pesquisarPorNome(String nome) throws RPGException, ClassNotFoundException {
		List<Armadura> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM armadura WHERE nome LIKE ?
					""";
			Connection con = conexao.getConnection();
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Armadura arm = new Armadura();
				arm.setId(rs.getInt("id"));
				arm.setNome(rs.getString("nome"));
				arm.setPeso(rs.getDouble("peso"));
				arm.setDescricao(rs.getString("descricao"));
				arm.setTipoArmadura(rs.getString("tipo_armadura"));
				arm.setVeneno(rs.getInt("veneno"));
				arm.setFisica(rs.getInt("fisica"));
				arm.setMagica(rs.getInt("magica"));
				arm.setFogo(rs.getInt("fogo"));
				arm.setEletrica(rs.getInt("eletrica"));
				arm.setHemorragica(rs.getInt("hemorragica"));
				lista.add(arm);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RPGException(e);
		}
		return lista;
	}

}