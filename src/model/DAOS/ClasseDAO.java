package model.DAOS;

import java.util.List;

import model.Arma;
import model.Armadura;
import model.Classe;

public interface ClasseDAO {
	void inserir(Classe a);
	void atualizar(Classe a);
	void remover(Classe a);
	Classe pegarClasse(String nome);
	Arma verificarArma(int idArma);
	Armadura verificarArmadura(int idArmadura);
	List<String> pegarNomes();
	List<Classe> pesquisarPorNome(String nome);
	List<Classe> pesquisarTodos();
	List<Arma> pesquisarArma();
	List<Armadura> pesquisarArmadura();
}
