package view;

import controller.ClasseControl;
import controller.PersonagemControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Classe;
import model.Personagem;

public class PersonagemView implements Tela {
	private Label lbId = new Label("");
	private Label level = new Label("");
	private Label forca = new Label("");
	private Label fe = new Label();
	private Label destreza = new Label("");
	private Label inte = new Label("");
	private Label sorte = new Label("");
	private Label vitalidade = new Label("");
	private Label resistencia = new Label("");
	private Label vigor = new Label("");
	private Label conh = new Label("");
	private TextField txtNome = new TextField("");
	private ComboBox<String> txtClasse = new ComboBox<>();
	private TextField txtItem = new TextField("");
	private ComboBox<String> txtGenero = new ComboBox<>();
	private TextField txtIdade = new TextField("");
	private TextArea txtDescricao = new TextArea("");

	private TableView<Personagem> tableView = new TableView<>();
	private PersonagemControl control;
	private ClasseControl controlClasse;

	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();

		try {
			control = new PersonagemControl();
			controlClasse = new ClasseControl();
		} catch (Exception err) {
			alert(Alert.AlertType.ERROR, "Erro ao inicializar o sistema.");
		}

		txtGenero.getItems().addAll("Masculino", "Feminino", "Outro");
		txtClasse.getItems().addAll(controlClasse.pegarNomes());

		GridPane pane = new GridPane();
		pane.add(new Label("Id: "), 0, 0);
		pane.add(lbId, 1, 0);
		pane.add(new Label("Nome: "), 0, 1);
		pane.add(txtNome, 1, 1);
		pane.add(new Label("Classe: "), 0, 2);
		pane.add(txtClasse, 1, 2);
		pane.add(new Label("Item: "), 0, 3);
		pane.add(txtItem, 1, 3);
		pane.add(new Label("Gênero: "), 0, 4);
		pane.add(txtGenero, 1, 4);
		pane.add(new Label("Idade: "), 0, 5);
		pane.add(txtIdade, 1, 5);
		pane.add(new Label("Descrição: "), 0, 6);
		pane.add(txtDescricao, 1, 6);
		pane.add(new Label("Level: "), 6, 0);
		pane.add(level, 7, 0);
		pane.add(new Label("Vitalidade: "), 9, 0);
		pane.add(vitalidade, 10, 0);
		pane.add(new Label("Força: "), 6, 1);
		pane.add(forca, 7, 1);
		pane.add(new Label("Fé: "), 9, 1);
		pane.add(fe, 10, 1);
		pane.add(new Label("Destreza: "), 6, 2);
		pane.add(destreza, 7, 2);
		pane.add(new Label("Inteligência: "), 9, 2);
		pane.add(inte, 10, 2);
		pane.add(new Label("Sorte: "), 6, 3);
		pane.add(sorte, 7, 3);
		pane.add(new Label("Resistência: "), 9, 3);
		pane.add(resistencia, 10, 3);
		pane.add(new Label("Vigor: "), 6, 4);
		pane.add(vigor, 7, 4);
		pane.add(new Label("Conhecimento: "), 9, 4);
		pane.add(conh, 10, 4);

		Button btnSalvar = new Button("Salvar");
		btnSalvar.setOnAction(e -> {
			try {
				if (validarCampos()) {
					control.gravar();
					tableView.refresh();
				}
			} catch (Exception err) {
				alert(Alert.AlertType.ERROR, "Erro ao gravar.");
			}
		});

		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> {
			try {
				control.pesquisarPorNome();
			} catch (Exception err) {
				alert(Alert.AlertType.ERROR, "Erro ao pesquisar.");
			}
		});

		txtClasse.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			System.out.println("Classe selecionada ==> " + novo);
			if (novo != null) {
				// Tente obter a classe
				Classe classe = control.pegarClasse(novo);

				if (classe != null) {
					// Verifique o conteúdo da classe
					System.out.println("Classe encontrada: " + classe.getNome()); // Supondo que Classe tenha um método
																					// getNome()

					// Atualize os atributos
					control.atualizarAtributos(classe);
				} else {
					System.out.println("Classe não encontrada no banco de dados.");
				}
			} else {
				System.out.println("Nenhuma classe foi selecionada.");
			}
		});

		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> {
			control.limparTudo();
		});

		pane.add(btnSalvar, 0, 7);
		pane.add(btnPesquisar, 1, 7);
		pane.add(btnLimpar, 2, 7);

		generateColumns();
		vincularPropriedades();

		panePrincipal.setTop(pane);
		panePrincipal.setCenter(tableView);

		try {
			control.pesquisarTodos();
		} catch (Exception err) {
			alert(Alert.AlertType.ERROR, "Erro ao carregar dados.");
		}
		return panePrincipal;
	}

	public boolean validarCampos() {
		if (txtNome.getText().isEmpty()) {
			alert(Alert.AlertType.ERROR, "Campo nome vazio!");
			return false;
		}
		if (txtClasse.getValue().isEmpty()) {
			alert(Alert.AlertType.ERROR, "Campo classe vazio!");
			return false;
		}
		if (txtItem.getText().isEmpty()) {
			alert(Alert.AlertType.ERROR, "Campo item vazio!");
			return false;
		}
		if (txtGenero.getValue() == null || txtGenero.getValue().isEmpty()) {
			alert(Alert.AlertType.ERROR, "Campo gênero vazio!");
			return false;
		}
		if (txtIdade.getText().isEmpty() || !txtIdade.getText().matches("\\d+")) {
			alert(Alert.AlertType.ERROR, "Campo idade vazio ou inválido!");
			return false;
		}
		if (txtDescricao.getText().isEmpty()) {
			alert(Alert.AlertType.ERROR, "Campo descrição vazio!");
			return false;
		}
		return true;
	}

	public void alert(Alert.AlertType tipo, String msg) {
		Alert alertWindow = new Alert(tipo);
		alertWindow.setHeaderText("Alerta");
		alertWindow.setContentText(msg);
		alertWindow.showAndWait();
	}

	public void generateColumns() {
		TableColumn<Personagem, Integer> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Personagem, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Personagem, String> col3 = new TableColumn<>("Classe");
		col3.setCellValueFactory(new PropertyValueFactory<>("classe"));

		TableColumn<Personagem, String> col4 = new TableColumn<>("Item");
		col4.setCellValueFactory(new PropertyValueFactory<>("item"));

		TableColumn<Personagem, String> col5 = new TableColumn<>("Gênero");
		col5.setCellValueFactory(new PropertyValueFactory<>("genero"));

		TableColumn<Personagem, Integer> col6 = new TableColumn<>("Idade");
		col6.setCellValueFactory(new PropertyValueFactory<>("idade"));

		TableColumn<Personagem, String> col7 = new TableColumn<>("Descrição");
		col7.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			System.out.println("Personagem selecionado ==> " + novo);
			control.entidadeParaTela(novo);
		});
	}

	public void vincularPropriedades() {
		Bindings.bindBidirectional(lbId.textProperty(), control.idProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtClasse.valueProperty(), control.classeProperty());
		Bindings.bindBidirectional(txtItem.textProperty(), control.itemProperty());
		Bindings.bindBidirectional(txtGenero.valueProperty(), control.generoProperty());
		Bindings.bindBidirectional(txtIdade.textProperty(), control.idadeProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
		Bindings.bindBidirectional(level.textProperty(), control.levelProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(forca.textProperty(), control.forcaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(fe.textProperty(), control.feProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(destreza.textProperty(), control.destrezaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(inte.textProperty(), control.inteProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(sorte.textProperty(), control.sorteProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(vitalidade.textProperty(), control.vitalidadeProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(resistencia.textProperty(), control.resistenciaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(vigor.textProperty(), control.vigorProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(conh.textProperty(), control.conhProperty(),
				(StringConverter) new IntegerStringConverter());
	}

}
