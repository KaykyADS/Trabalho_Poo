package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Arma;
import model.DAOS.ArmaDAO;
import model.DAOS.ArmaDAOImpl;

public class ArmaControl {
	private ObservableList<Arma> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private DoubleProperty peso = new SimpleDoubleProperty(0.0);
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty tipo_dano = new SimpleStringProperty("");
	private IntegerProperty dano = new SimpleIntegerProperty(0);
	private StringProperty forca = new SimpleStringProperty("");
	private StringProperty fe = new SimpleStringProperty("");
	private StringProperty destreza = new SimpleStringProperty("");
	private StringProperty inteligencia = new SimpleStringProperty("");

	private ArmaDAO armaDAO;
	private int contador = 0;

	public ArmaControl() {
		armaDAO = new ArmaDAOImpl();
	}

	public void entidadeParaTela(Arma a) {
		if (a != null) {
			this.id.set(a.getId());
			this.nome.set(a.getNome());
			this.peso.set(a.getPeso());
			this.descricao.set(a.getDescricao());
			this.tipo_dano.set(a.getTipo_dano());
			this.dano.set(a.getDano());
			this.forca.set(a.getForca());
			this.fe.set(a.getFe());
			this.destreza.set(a.getDestreza());
			this.inteligencia.set(a.getInteligencia());
		}
	}

	public Arma telaParaEntidade() {
		Arma a = new Arma();
		a.setId(this.id.get());
		a.setNome(this.nome.get());
		a.setPeso(this.peso.get());
		a.setDescricao(this.descricao.get());
		a.setTipo_dano(this.tipo_dano.get());
		a.setDano(this.dano.get());
		a.setForca(this.forca.get());
		a.setFe(this.fe.get());
		a.setDestreza(this.destreza.get());
		a.setInteligencia(this.inteligencia.get());
		return a;
	}

	public void excluir(Arma a) {
		System.out.println("Excluído a arma: " + a.getNome());
		armaDAO.remover(a);
		pesquisarTodos();
	}

	public void gravar() {
		Arma a = telaParaEntidade();
		System.out.println("NOME ==> " + a.getNome());

		if (id.get() == 0) {
			a.setId(contador);
			armaDAO.inserir(a);
			contador += 1;
		} else {
			a.setId(this.id.get());
			armaDAO.atualizar(a);
		}
		pesquisarTodos();
		limparTudo();
	}

	public void limparTudo() {
		id.set(0);
		nome.set("");
		peso.set(0.0);
		descricao.set("");
		tipo_dano.set("");
		dano.set(0);
		forca.set("");
		fe.set("");
		destreza.set("");
		inteligencia.set("");
	}

	public void pesquisarPorNome() {
		lista.clear();
		lista.addAll(armaDAO.pesquisarPorNome(nome.get()));
	}

	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(armaDAO.pesquisarTodos());
	}

	public ObservableList<Arma> getLista() {
		return this.lista;
	}

	public IntegerProperty idProperty() {
		return this.id;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public DoubleProperty pesoProperty() {
		return this.peso;
	}

	public StringProperty descricaoProperty() {
		return this.descricao;
	}

	public StringProperty tipoDanoProperty() {
		return this.tipo_dano;
	}

	public IntegerProperty danoProperty() {
		return this.dano;
	}

	public StringProperty forcaProperty() {
		return this.forca;
	}

	public StringProperty feProperty() {
		return this.fe;
	}

	public StringProperty destrezaProperty() {
		return this.destreza;
	}

	public StringProperty inteligenciaProperty() {
		return this.inteligencia;
	}
}