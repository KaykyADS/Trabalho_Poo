package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Arma;
import model.Armadura;
import model.Classe;
import model.RPGException;
import model.persistence.Conexao;

public class ClasseDAOImpl implements ClasseDAO {
    Connection con;

    public ClasseDAOImpl() {
        try {
            Conexao conexao = new Conexao();
            con = conexao.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Classe c) {
    	System.out.println("NOME ==> " + c.getNome());
        String SQL = """
                INSERT INTO classes (nome, habilidade, level, forca, fe, destreza, inteligencia, sorte, vitalidade, resistencia, vigor, conhecimento, idArma, idArmadura) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, c.getNome());
            pst.setString(2, c.getHabilidade());
            pst.setInt(3, c.getLevel());
            pst.setInt(4, c.getForca());
            pst.setInt(5, c.getFe());
            pst.setInt(6, c.getDestreza());
            pst.setInt(7, c.getInteligencia());
            pst.setInt(8, c.getSorte());
            pst.setInt(9, c.getVitalidade());
            pst.setInt(10, c.getResistencia());
            pst.setInt(11, c.getVigor());
            pst.setInt(12, c.getConhecimento());
            pst.setInt(13, c.getIdArma());
            pst.setInt(14, c.getIdArmadura());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Classe c) {
        String SQL = """
                UPDATE classes 
                SET habilidade=?, level=?, forca=?, fe=?, destreza=?, inteligencia=?, sorte=?, vitalidade=?, resistencia=?, vigor=?, conhecimento=?, idArma=?, idArmadura=? 
                WHERE nome=?
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, c.getHabilidade());
            pst.setInt(2, c.getLevel());
            pst.setInt(3, c.getForca());
            pst.setInt(4, c.getFe());
            pst.setInt(5, c.getDestreza());
            pst.setInt(6, c.getInteligencia());
            pst.setInt(7, c.getSorte());
            pst.setInt(8, c.getVitalidade());
            pst.setInt(9, c.getResistencia());
            pst.setInt(10, c.getVigor());
            pst.setInt(11, c.getConhecimento());
            pst.setInt(12, c.getIdArma());
            pst.setInt(13, c.getIdArmadura());
            pst.setString(14, c.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(Classe c) {
        String SQL = """
                DELETE FROM classes WHERE nome=?
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, c.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Classe> pesquisarPorNome(String nome) {
        List<Classe> lista = new ArrayList<>();
        String SQL = """
                SELECT * FROM classes WHERE nome LIKE ?
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	Classe c = new Classe();
            	c.setNome(rs.getString("nome"));
            	c.setNome(rs.getString("nome"));
                c.setHabilidade(rs.getString("habilidade"));
                c.setLevel(rs.getInt("level"));
                c.setForca(rs.getInt("forca"));
                c.setFe(rs.getInt("fe"));
                c.setDestreza(rs.getInt("destreza"));
                c.setInteligencia(rs.getInt("inteligencia"));
                c.setSorte(rs.getInt("sorte"));
                c.setVitalidade(rs.getInt("vitalidade"));
                c.setResistencia(rs.getInt("resistencia"));
                c.setVigor(rs.getInt("vigor"));
                c.setConhecimento(rs.getInt("conhecimento"));
                c.setIdArma(rs.getInt("idArma"));
                c.setIdArmadura(rs.getInt("idArmadura"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Classe> pesquisarTodos() {
        List<Classe> lista = new ArrayList<>();
        String SQL = """
                SELECT * FROM classes
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	Classe c = new Classe();
            	c.setNome(rs.getString("nome"));
            	c.setNome(rs.getString("nome"));
                c.setHabilidade(rs.getString("habilidade"));
                c.setLevel(rs.getInt("level"));
                c.setForca(rs.getInt("forca"));
                c.setFe(rs.getInt("fe"));
                c.setDestreza(rs.getInt("destreza"));
                c.setInteligencia(rs.getInt("inteligencia"));
                c.setSorte(rs.getInt("sorte"));
                c.setVitalidade(rs.getInt("vitalidade"));
                c.setResistencia(rs.getInt("resistencia"));
                c.setVigor(rs.getInt("vigor"));
                c.setConhecimento(rs.getInt("conhecimento"));
                c.setIdArma(rs.getInt("idArma"));
                c.setIdArmadura(rs.getInt("idArmadura"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

	@Override
	public List<Arma> pesquisarArma() {
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

	@Override
	public List<Armadura> pesquisarArmadura() {
		List<Armadura> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM armadura
					""";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<String> pegarNomes() {
		List<String> lista = new ArrayList<>();
		try {
			String SQL = """
					SELECT * FROM classes
					""";
			PreparedStatement stm = con.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Classe c = new Classe();
				c.setNome(rs.getString("nome"));
				lista.add(c.getNome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Classe pegarClasse(String nome) {
        Classe c = new Classe();
		String SQL = """
                SELECT * FROM classes WHERE nome LIKE ?
                """;
        try (PreparedStatement pst = con.prepareStatement(SQL)) {
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
	        	c.setNome(rs.getString("nome"));
	        	c.setNome(rs.getString("nome"));
	            c.setHabilidade(rs.getString("habilidade"));
	            c.setLevel(rs.getInt("level"));
	            c.setForca(rs.getInt("forca"));
	            c.setFe(rs.getInt("fe"));
	            c.setDestreza(rs.getInt("destreza"));
	            c.setInteligencia(rs.getInt("inteligencia"));
	            c.setSorte(rs.getInt("sorte"));
	            c.setVitalidade(rs.getInt("vitalidade"));
	            c.setResistencia(rs.getInt("resistencia"));
	            c.setVigor(rs.getInt("vigor"));
	            c.setConhecimento(rs.getInt("conhecimento"));
	            c.setIdArma(rs.getInt("idArma"));
	            c.setIdArmadura(rs.getInt("idArmadura"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return c;
	}

	@Override
	public Armadura verificarArmadura(int idArma) {
		Armadura arm = new Armadura();
		try {
			String SQL = """
					SELECT * FROM armadura WHERE id = ?
					""";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setInt(1, idArma);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arm;
	}

	@Override
	public Arma verificarArma(int idArma) {
		Arma a = new Arma();
		try {
			String SQL = """
					SELECT * FROM arma
					""";
			PreparedStatement pst = con.prepareStatement(SQL);
			pst.setInt(1, idArma);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
}
