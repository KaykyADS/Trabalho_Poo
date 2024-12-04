package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Inimigo;
import model.RPGException;
import model.DAOS.InimigoDAO;
import model.DAOS.InimigoDAOImpl;

public class InimigoControl {

	private ObservableList<Inimigo> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty danoCausa = new SimpleIntegerProperty(0);
	private IntegerProperty vida = new SimpleIntegerProperty(0);
	private StringProperty fraqueza = new SimpleStringProperty("");
	private StringProperty descricao = new SimpleStringProperty("");

	private InimigoDAO inimigoDAO;

	public InimigoControl() throws RPGException {
		inimigoDAO = new InimigoDAOImpl();
	}

	public void entidadeParaTela(Inimigo i) {
		if (i != null) {
			this.id.set(i.getId());
			this.nome.set(i.getNome());
			this.danoCausa.set(i.getDanoCausa());
			this.vida.set(i.getVida());
			this.fraqueza.set(i.getFraqueza());
			this.descricao.set(i.getDescricao());
		}
	}

	public void excluir(Inimigo i) throws RPGException, ClassNotFoundException {
		inimigoDAO.excluir(i);
		pesquisarTodos();
	}

	public void gravar() throws RPGException, ClassNotFoundException {
		Inimigo i = new Inimigo();
		i.setNome(this.nome.get());
		i.setDanoCausa(this.danoCausa.get());
		i.setVida(this.vida.get());
		i.setFraqueza(this.fraqueza.get());
		i.setDescricao(this.descricao.get());

		if (id.get() == 0) {
			inimigoDAO.inserir(i);
		} else {
			i.setId(id.get());
			inimigoDAO.atualizar(i);
		}
		pesquisarTodos();
		limparTudo();
	}

	public void limparTudo() {
		id.set(0);
		nome.set("");
		danoCausa.set(0);
		vida.set(0);
		fraqueza.set("");
		descricao.set("");
	}

	public void pesquisarPorNome() throws RPGException, ClassNotFoundException {
		lista.clear();
		lista.addAll(inimigoDAO.pesquisarPorNome(nome.get()));
	}

	public void pesquisarTodos() throws RPGException, ClassNotFoundException {
		lista.clear();
		lista.addAll(inimigoDAO.pesquisarTodos());
	}

	public ObservableList<Inimigo> getLista() {
		return this.lista;
	}

	public IntegerProperty idProperty() {
		return this.id;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public IntegerProperty danoCausaProperty() {
		return this.danoCausa;
	}

	public IntegerProperty vidaProperty() {
		return this.vida;
	}

	public StringProperty fraquezaProperty() {
		return this.fraqueza;
	}

	public StringProperty descricaoProperty() {
		return this.descricao;
	}
}
