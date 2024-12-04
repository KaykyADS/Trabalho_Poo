package model.DAOS;

import java.sql.SQLException;
import java.util.List;

import model.Arma;

public interface ArmaDAO {
	void inserir(Arma a);
	void atualizar(Arma a);
	void remover(Arma a);
	List<Arma> pesquisarPorNome(String nome);
	List<Arma> pesquisarTodos();
}
