package view;

import controller.ArmaControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Arma;

public class ArmaView implements Tela {
	private Label lbId = new Label("");
	private TextField txtNome = new TextField("");
	private TextField txtDescricao = new TextField("");
	private TextField txtPeso = new TextField("");
	private ComboBox<String> txtTipo_dano = new ComboBox<>();
	private TextField txtDano = new TextField("");
	private TextField txtForca = new TextField("");
	private TextField txtFe = new TextField("");
	private TextField txtDestreza = new TextField("");
	private TextField txtInteligencia = new TextField("");

	private TableView<Arma> tableView = new TableView<>();
	private ArmaControl control;

	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();

		try {
			control = new ArmaControl();
		} catch (Exception err) {
			alert(AlertType.ERROR, "Ao ao inicializar o sistema");
		}

		txtTipo_dano.getItems().addAll("Físico", "Mágico");
		GridPane pane = new GridPane();
		pane.add(new Label("Id: "), 0, 0);
		pane.add(lbId, 1, 0);
		pane.add(new Label("Nome da arma: "), 0, 1);
		pane.add(txtNome, 1, 1);
		pane.add(new Label("Descrição: "), 0, 2);
		pane.add(txtDescricao, 1, 2);
		pane.add(new Label("Peso: "), 0, 3);
		pane.add(txtPeso, 1, 3);
		pane.add(new Label("Tipo de dano: "), 0, 4);
		pane.add(txtTipo_dano, 1, 4);
		pane.add(new Label("Dano: "), 0, 5);
		pane.add(txtDano, 1, 5);
		pane.add(new Label("Força: "), 0, 6);
		pane.add(txtForca, 1, 6);
		pane.add(new Label("Fé: "), 0, 7);
		pane.add(txtFe, 1, 7);
		pane.add(new Label("Destreza: "), 0, 8);
		pane.add(txtDestreza, 1, 8);
		pane.add(new Label("Inteligência: "), 0, 9);
		pane.add(txtInteligencia, 1, 9);

		Button btnSalvar = new Button("Salvar");
		btnSalvar.setOnAction(e -> {
			try {
				if (validarCampos()) {
					control.gravar();
					tableView.refresh();
				}
			} catch (Exception err) {
				alert(AlertType.ERROR, "Erro ao gravar");
			}
		});
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> {
			try {
				control.pesquisarPorNome();
			} catch (Exception err) {
				alert(AlertType.ERROR, "Erro ao pesquisar");
			}
		});

		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> {
			control.limparTudo();
		});

		pane.add(btnSalvar, 0, 10);
		pane.add(btnPesquisar, 1, 10);
		pane.add(btnLimpar, 2, 10);

		generateColumns();
		vincularProprieades();

		panePrincipal.setTop(pane);
		panePrincipal.setCenter(tableView);

		try {
			control.pesquisarTodos();
		} catch (Exception err) {
			alert(AlertType.ERROR, "Erro ao pesquisar todos");
		}
		return panePrincipal;
	}

	public boolean validarCampos() {
		if (txtNome.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo nome vazio!");
			return false;
		}
		if (txtDescricao.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo descrição vazio!");
			return false;
		}
		try {
			if (Double.parseDouble(txtPeso.getText()) <= 0.0) {
				alert(AlertType.ERROR, "Campo de peso não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em peso!");
			return false;
		}

		if (txtTipo_dano.getValue() == null || txtTipo_dano.getValue().isEmpty()) {
			alert(AlertType.ERROR, "Campo tipo dano vazio!");
			return false;
		}
		try {
			if (Integer.parseInt(txtDano.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo dano vazio!");
				return false;
			}
		} catch (Exception e) {
			alert(AlertType.ERROR, "Insira um número em dano!");
			return false;
		}
		if (txtForca.getText().isEmpty() || txtForca.getText().length() > 1) {
			alert(AlertType.ERROR, "Campo força vazio ou ultrapassou 1 caractere!");
			return false;
		}
		if (txtFe.getText().isEmpty() || txtFe.getText().length() > 1) {
			alert(AlertType.ERROR, "Campo fé vazio ou ultrapassou 1 caractere!");
			return false;
		}
		if (txtDestreza.getText().isEmpty() || txtDestreza.getText().length() > 1) {
			alert(AlertType.ERROR, "Campo destreza vazio ou ultrapassou 1 caractere!");
			return false;
		}
		if (txtInteligencia.getText().isEmpty() || txtInteligencia.getText().length() > 1) {
			alert(AlertType.ERROR, "Campo inteligência vazio ou ultrapassou 1 caractere!");
			return false;
		}
		return true;
	}

	public void alert(AlertType tipo, String msg) {
		Alert alertWindow = new Alert(tipo);
		alertWindow.setHeaderText("Alerta");
		alertWindow.setContentText(msg);
		alertWindow.showAndWait();
	}

	public void generateColumns() {
		TableColumn<Arma, Integer> col1 = new TableColumn("Id");
		col1.setCellValueFactory(new PropertyValueFactory<Arma, Integer>("id"));

		TableColumn<Arma, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Arma, Double> col3 = new TableColumn<>("Peso");
		col3.setCellValueFactory(new PropertyValueFactory<>("peso"));

		TableColumn<Arma, String> col4 = new TableColumn<>("Descrição");
		col4.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		TableColumn<Arma, String> col5 = new TableColumn<>("Tipo de Dano");
		col5.setCellValueFactory(new PropertyValueFactory<>("tipo_dano"));

		TableColumn<Arma, Integer> col6 = new TableColumn<>("Dano");
		col6.setCellValueFactory(new PropertyValueFactory<>("dano"));

		TableColumn<Arma, String> col7 = new TableColumn<>("Força");
		col7.setCellValueFactory(new PropertyValueFactory<>("forca"));

		TableColumn<Arma, String> col8 = new TableColumn<>("Fé");
		col8.setCellValueFactory(new PropertyValueFactory<>("fe"));

		TableColumn<Arma, String> col9 = new TableColumn<>("Destreza");
		col9.setCellValueFactory(new PropertyValueFactory<>("destreza"));

		TableColumn<Arma, String> col10 = new TableColumn<>("Inteligência");
		col10.setCellValueFactory(new PropertyValueFactory<>("inteligencia"));

		Callback<TableColumn<Arma, Void>, TableCell<Arma, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Arma, Void> call(TableColumn<Arma, Void> param) {
				TableCell<Arma, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Arma a = tableView.getItems().get(getIndex());
								control.excluir(a);
							} catch (Exception err) {
								alert(AlertType.ERROR, "Erro ao excluir");
							}
						});
					}

					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnExcluir);
						}
					}
				};
				return tc;
			}
		};

		TableColumn<Arma, Void> col11 = new TableColumn<>("Ações");
		col11.setCellFactory(callback);

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			System.out.println("Selecionado a Arma ==> " + novo);
			control.entidadeParaTela(novo);
		});
	}

	public void vincularProprieades() {
		Bindings.bindBidirectional(lbId.textProperty(), control.idProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtPeso.textProperty(), control.pesoProperty(),
				(StringConverter) new DoubleStringConverter());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
		Bindings.bindBidirectional(txtTipo_dano.valueProperty(), control.tipoDanoProperty());
		Bindings.bindBidirectional(txtDano.textProperty(), control.danoProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtForca.textProperty(), control.forcaProperty());
		Bindings.bindBidirectional(txtFe.textProperty(), control.feProperty());
		Bindings.bindBidirectional(txtDestreza.textProperty(), control.destrezaProperty());
		Bindings.bindBidirectional(txtInteligencia.textProperty(), control.inteligenciaProperty());
	}
}
