package model.DAOS;

import java.util.List;

import model.Personagem;

public interface PersonagemDAO {

	public void inserir(Personagem p);
	public void excluir(Personagem p);
	public void atualizar(Personagem p);
	public List<Personagem> pesquisarTodos();
	public List<Personagem> pesquisarPorNome(String nome);
}