package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Armadura;
import model.RPGException;
import model.DAOS.ArmaduraDAO;
import model.DAOS.ArmaduraDAOImpl;

public class ArmaduraControl {
	private ObservableList<Armadura> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private DoubleProperty peso = new SimpleDoubleProperty(0);
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty tipoArmadura = new SimpleStringProperty("");
	private IntegerProperty veneno = new SimpleIntegerProperty(0);
	private IntegerProperty fisica = new SimpleIntegerProperty(0);
	private IntegerProperty magica = new SimpleIntegerProperty(0);
	private IntegerProperty fogo = new SimpleIntegerProperty(0);
	private IntegerProperty eletrica = new SimpleIntegerProperty(0);
	private IntegerProperty hemorragica = new SimpleIntegerProperty(0);

	private ArmaduraDAO armaduraDAO;

	public ArmaduraControl() throws RPGException {
		armaduraDAO = new ArmaduraDAOImpl();
	}

	public void entidadeParaTela(Armadura arm) {
		if (arm != null) {
			this.id.set(arm.getId());
			this.nome.set(arm.getNome());
			this.peso.set(arm.getPeso());
			this.descricao.set(arm.getDescricao());
			this.tipoArmadura.set(arm.getTipoArmadura());
			this.veneno.set(arm.getVeneno());
			this.fisica.set(arm.getFisica());
			this.magica.set(arm.getMagica());
			this.fogo.set(arm.getFogo());
			this.eletrica.set(arm.getEletrica());
			this.hemorragica.set(arm.getHemorragica());
		}
	}

	public void excluir(Armadura arm) throws RPGException, ClassNotFoundException {
		armaduraDAO.excluir(arm);
		pesquisarTodos();
	}

	public void gravar() throws RPGException, ClassNotFoundException {
		Armadura arm = new Armadura();
		arm.setNome(this.nome.get());
		arm.setPeso(this.peso.get());
		arm.setDescricao(this.descricao.get());
		arm.setTipoArmadura(this.tipoArmadura.get());
		arm.setVeneno(this.veneno.get());
		arm.setFisica(this.fisica.get());
		arm.setMagica(this.magica.get());
		arm.setFogo(this.fogo.get());
		arm.setEletrica(this.eletrica.get());
		arm.setHemorragica(this.hemorragica.get());

		if (id.get() == 0) {
			armaduraDAO.inserir(arm);
		} else {
			arm.setId(id.get());
			armaduraDAO.atualizar(arm);
		}
		pesquisarTodos();
		limparTudo();
	}

	public void limparTudo() {
		id.set(0);
		nome.set("");
		peso.set(0);
		descricao.set("");
		tipoArmadura.set("");
		veneno.set(0);
		fisica.set(0);
		magica.set(0);
		fogo.set(0);
		eletrica.set(0);
		hemorragica.set(0);
	}

	public void pesquisarPorNome() throws RPGException, ClassNotFoundException {
		lista.clear();
		lista.addAll(armaduraDAO.pesquisarPorNome(nome.get()));
	}

	public void pesquisarTodos() throws RPGException, ClassNotFoundException {
		lista.clear();
		lista.addAll(armaduraDAO.pesquisarTodos());
	}

	public ObservableList<Armadura> getLista() {
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

	public StringProperty tipoArmaduraProperty() {
		return this.tipoArmadura;
	}

	public IntegerProperty venenoProperty() {
		return this.veneno;
	}

	public IntegerProperty fisicaProperty() {
		return this.fisica;
	}

	public IntegerProperty magicaProperty() {
		return this.magica;
	}

	public IntegerProperty fogoProperty() {
		return this.fogo;
	}

	public IntegerProperty eletricaProperty() {
		return this.eletrica;
	}

	public IntegerProperty hemorragicaProperty() {
		return this.hemorragica;
	}

}