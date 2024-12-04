package model.DAOS;

import java.util.List;

import model.Inimigo;
import model.RPGException;

public interface InimigoDAO {


	public void inserir(Inimigo i) throws RPGException, ClassNotFoundException;
	public void excluir(Inimigo i) throws RPGException, ClassNotFoundException;
	public void atualizar(Inimigo i) throws RPGException, ClassNotFoundException;
	public List<Inimigo> pesquisarTodos() throws RPGException, ClassNotFoundException;
	public List<Inimigo> pesquisarPorNome(String nome) throws RPGException, ClassNotFoundException;
}
