package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Classe;
import model.Personagem;
import model.DAOS.ClasseDAO;
import model.DAOS.ClasseDAOImpl;
import model.DAOS.PersonagemDAO;
import model.DAOS.PersonagemDAOImpl;

public class PersonagemControl {
	private ObservableList<Personagem> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty classe = new SimpleStringProperty("");
	private StringProperty item = new SimpleStringProperty("");
	private StringProperty genero = new SimpleStringProperty("");
	private IntegerProperty idade = new SimpleIntegerProperty(0);
	private StringProperty descricao = new SimpleStringProperty("");
	private IntegerProperty level = new SimpleIntegerProperty(0);
	private IntegerProperty forca = new SimpleIntegerProperty(0);
	private IntegerProperty fe = new SimpleIntegerProperty(0);
	private IntegerProperty destreza = new SimpleIntegerProperty(0);
	private IntegerProperty inte = new SimpleIntegerProperty(0);
	private IntegerProperty sorte = new SimpleIntegerProperty(0);
	private IntegerProperty vitalidade = new SimpleIntegerProperty(0);
	private IntegerProperty resistencia = new SimpleIntegerProperty(0);
	private IntegerProperty vigor = new SimpleIntegerProperty(0);
	private IntegerProperty conh = new SimpleIntegerProperty(0);
	private ClasseDAO classeDao;
	private Classe clas = new Classe();

	private PersonagemDAO personagemDAO;

	public PersonagemControl() {
		personagemDAO = new PersonagemDAOImpl();
		classeDao = new ClasseDAOImpl();
	}

	public Classe pegarClasse(String nome) {
		clas = classeDao.pegarClasse(nome);
		return clas;
	}

	public void atualizarAtributos(Classe classe) {
		if (classe != null) {
			this.level.set(classe.getLevel());
			this.forca.set(classe.getForca());
			this.fe.set(classe.getFe());
			this.destreza.set(classe.getDestreza());
			this.inte.set(classe.getInteligencia());
			this.sorte.set(classe.getSorte());
			this.vitalidade.set(classe.getVitalidade());
			this.resistencia.set(classe.getResistencia());
			this.vigor.set(classe.getVigor());
			this.conh.set(classe.getConhecimento());
		}
	}

	public void entidadeParaTela(Personagem p) {
		if (p != null) {
			this.id.set(p.getId());
			this.nome.set(p.getNome());
			this.classe.set(p.getClasse());
			this.item.set(p.getItem());
			this.genero.set(p.getGenero());
			this.idade.set(p.getIdade());
			this.descricao.set(p.getDescricao());
		}
	}

	public Personagem telaParaEntidade() {
		Personagem p = new Personagem();
		p.setId(this.id.get());
		p.setNome(this.nome.get());
		p.setClasse(this.classe.get());
		p.setItem(this.item.get());
		p.setGenero(this.genero.get());
		p.setIdade(this.idade.get());
		p.setDescricao(this.descricao.get());
		return p;
	}

	public void excluir(Personagem p) {
		System.out.println("Excluído o personagem: " + p.getNome());
		personagemDAO.excluir(p);
		pesquisarTodos();
	}

	public void gravar() {
		Personagem p = telaParaEntidade();
		System.out.println("NOME ==> " + p.getNome());

		if (id.get() == 0) {
			personagemDAO.inserir(p);
		} else {
			p.setId(id.get());
			personagemDAO.atualizar(p);
		}
		pesquisarTodos();
		limparTudo();
	}

	public void limparTudo() {
		id.set(0);
		nome.set("");
		classe.set("");
		item.set("");
		genero.set("");
		idade.set(0);
		descricao.set("");
	}

	public void pesquisarPorNome() {
		lista.clear();
		lista.addAll(personagemDAO.pesquisarPorNome(nome.get()));
	}

	public void pesquisarTodos() {
		lista.clear();
		lista.addAll(personagemDAO.pesquisarTodos());
	}

	public ObservableList<Personagem> getLista() {
		return this.lista;
	}

	public IntegerProperty idProperty() {
		return this.id;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public StringProperty classeProperty() {
		return this.classe;
	}

	public StringProperty itemProperty() {
		return this.item;
	}

	public StringProperty generoProperty() {
		return this.genero;
	}

	public IntegerProperty idadeProperty() {
		return this.idade;
	}

	public StringProperty descricaoProperty() {
		return this.descricao;
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

	public IntegerProperty inteProperty() {
		return this.inte;
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

	public IntegerProperty conhProperty() {
		return this.conh;
	}

	public ClasseDAO classeDaoProperty() {
		return this.classeDao;
	}
}
