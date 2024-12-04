package model.DAOS;

import java.util.List;

import model.Armadura;
import model.RPGException;

public interface ArmaduraDAO {

	public void inserir(Armadura arm) throws RPGException, ClassNotFoundException;
	public void excluir(Armadura arm) throws RPGException, ClassNotFoundException;
	public void atualizar(Armadura arm) throws RPGException, ClassNotFoundException;
	public List<Armadura> pesquisarTodos() throws RPGException, ClassNotFoundException;
	public List<Armadura> pesquisarPorNome(String nome) throws RPGException, ClassNotFoundException;
}