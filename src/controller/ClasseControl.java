package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Arma;
import model.Armadura;
import model.Classe;
import model.DAOS.ClasseDAO;
import model.DAOS.ClasseDAOImpl;

public class ClasseControl {
	private ObservableList<Classe> lista = FXCollections.observableArrayList();
	private ObservableList<Arma> listaArma = FXCollections.observableArrayList();
	private ObservableList<Armadura> listaArmadura = FXCollections.observableArrayList();

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty habilidade = new SimpleStringProperty("");
	private IntegerProperty level = new SimpleIntegerProperty(0);
	private IntegerProperty forca = new SimpleIntegerProperty(0);
	private IntegerProperty fe = new SimpleIntegerProperty(0);
	private IntegerProperty destreza = new SimpleIntegerProperty(0);
	private IntegerProperty inteligencia = new SimpleIntegerProperty(0);
	private IntegerProperty sorte = new SimpleIntegerProperty(0);
	private IntegerProperty vitalidade = new SimpleIntegerProperty(0);
	private IntegerProperty resistencia = new SimpleIntegerProperty(0);
	private IntegerProperty vigor = new SimpleIntegerProperty(0);
	private IntegerProperty conhecimento = new SimpleIntegerProperty(0);
	private IntegerProperty idArma = new SimpleIntegerProperty(0);
	private IntegerProperty idArmadura = new SimpleIntegerProperty(0);

	private ClasseDAO classeDAO;

	public ClasseControl() {
		classeDAO = new ClasseDAOImpl();
	}

	public void entidadeParaTela(Classe c) {
		if (c != null) {
			this.nome.set(c.getNome());
			this.habilidade.set(c.getHabilidade());
			this.level.set(c.getLevel());
			this.forca.set(c.getForca());
			this.fe.set(c.getFe());
			this.destreza.set(c.getDestreza());
			this.inteligencia.set(c.getInteligencia());
			this.sorte.set(c.getSorte());
			this.vitalidade.set(c.getVitalidade());
			this.resistencia.set(c.getResistencia());
			this.vigor.set(c.getVigor());
			this.conhecimento.set(c.getConhecimento());
			this.idArma.set(c.getIdArma());
			this.idArmadura.set(c.getIdArmadura());
		}
	}

	public void armaParaClasse(Arma a) {
		if (a != null) {
			this.idArma.set(a.getId());
		}
	}
	
	public boolean verificarArma(int idArma, int idArmadura) {
		Arma a = classeDAO.verificarArma(idArma);
		Armadura ar = classeDAO.verificarArmadura(idArmadura);
		System.out.println("Arma: " + a + " armadaura: " + ar);
		if (a == null || ar == null) return false;
		return true;
	}

	public void armaduraParaClasse(Armadura novo) {
		if (novo != null) {
			this.idArmadura.set(novo.getId());
		}
	}

	public Classe telaParaEntidade() {
		Classe c = new Classe();
		c.setNome(this.nome.get());
		c.setHabilidade(this.habilidade.get());
		c.setLevel(this.level.get());
		c.setForca(this.forca.get());
		c.setFe(this.fe.get());
		c.setDestreza(this.destreza.get());
		c.setInteligencia(this.inteligencia.get());
		c.setSorte(this.sorte.get());
		c.setVitalidade(this.vitalidade.get());
		c.setResistencia(this.resistencia.get());
		c.setVigor(this.vigor.get());
		c.setConhecimento(this.conhecimento.get());
		c.setIdArma(this.idArma.get());
		c.setIdArmadura(this.idArmadura.get());
		return c;
	}

	public void excluir(Classe c) {
		System.out.println("Excluindo a classe: " + c.getNome());
		classeDAO.remover(c);
		pesquisarTodos();
	}

	public void pesquisarPorNome() {
		lista.clear();
		lista.addAll(classeDAO.pesquisarPorNome(nome.get()));
	}

	public void gravar() {
		Classe c = telaParaEntidade();
		System.out.println(classeDAO.pesquisarPorNome(nome.get()));
		if (classeDAO.pesquisarPorNome(nome.get()).isEmpty()) {
			classeDAO.inserir(c);
		} else {
			classeDAO.atualizar(c);
		}
		pesquisarTodos();
		limparTudo();
	}

	public void limparTudo() {
		nome.set("");
		habilidade.set("");
		level.set(0);
		forca.set(0);
		fe.set(0);
		destreza.set(0);
		inteligencia.set(0);
		sorte.set(0);
		vitalidade.set(0);
		resistencia.set(0);
		vigor.set(0);
		conhecimento.set(0);
		idArma.set(0);
		idArmadura.set(0);
	}

	public List<String> pegarNomes() {
		List<String> lista = new ArrayList<>();
		lista = classeDAO.pegarNomes();
		return lista;
	}

	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(classeDAO.pesquisarTodos());
	}

	public void pesquisarPorArma() {
		listaArma.addAll(classeDAO.pesquisarArma());
	}

	public void pesquisarPorArmadura() {
		listaArmadura.addAll(classeDAO.pesquisarArmadura());
	}

	public ObservableList<Classe> getLista() {
		return this.lista;
	}

	public ObservableList<Armadura> getListaArmadura() {
		return this.listaArmadura;
	}

	public ObservableList<Arma> getListaArma() {
		return this.listaArma;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public StringProperty habilidadeProperty() {
		return this.habilidade;
	}

	public IntegerProperty levelProperty() {
		return this.level;
	}

	public IntegerProperty forcaProperty() {
		return this.forca;
	}

	public IntegerProperty feProperty() {
		return this.fe;
	}

	public IntegerProperty destrezaProperty() {
		return this.destreza;
	}

	public IntegerProperty inteligenciaProperty() {
		return this.inteligencia;
	}

	public IntegerProperty sorteProperty() {
		return this.sorte;
	}

	public IntegerProperty vitalidadeProperty() {
		return this.vitalidade;
	}

	public IntegerProperty resistenciaProperty() {
		return this.resistencia;
	}

	public IntegerProperty vigorProperty() {
		return this.vigor;
	}

	public IntegerProperty conhecimentoProperty() {
		return this.conhecimento;
	}

	public IntegerProperty idArmaProperty() {
		return this.idArma;
	}

	public IntegerProperty idArmaduraProperty() {
		return this.idArmadura;
	}
}
