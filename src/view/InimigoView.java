package view;

import controller.InimigoControl;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.util.converter.IntegerStringConverter;
import model.Inimigo;
import model.RPGException;

public class InimigoView implements Tela {

	private Label lblId = new Label("");
	private TextField txtNome = new TextField("");
	private TextField txtDescricao = new TextField("");
	private TextField txtVida = new TextField("");
	private TextField txtFraqueza = new TextField("");
	private TextField txtDanoCausa = new TextField("");

	private TableView<Inimigo> tableView = new TableView<>();

	private InimigoControl control;

	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();

		try {
			control = new InimigoControl();
		} catch (RPGException e) {
			alert(AlertType.ERROR, "Ao inicializar o sistema");
		}

		GridPane paneForm = new GridPane();
		paneForm.add(new Label("Id: "), 0, 0);
		paneForm.add(lblId, 1, 0);
		paneForm.add(new Label("Nome: "), 0, 1);
		paneForm.add(txtNome, 1, 1);
		paneForm.add(new Label("Dano que ele causa: "), 0, 2);
		paneForm.add(txtDanoCausa, 1, 2);
		paneForm.add(new Label("Vida: "), 0, 3);
		paneForm.add(txtVida, 1, 3);
		paneForm.add(new Label("Fraqueza: "), 0, 4);
		paneForm.add(txtFraqueza, 1, 4);
		paneForm.add(new Label("Descricao: "), 0, 5);
		paneForm.add(txtDescricao, 1, 5);

		Button btnGravar = new Button("Gravar");
		btnGravar.setOnAction(e -> {
			try {
				if (this.validarCampos()) {
					control.gravar();
					tableView.refresh();
				}
			} catch (RPGException | ClassNotFoundException err) {
				alert(AlertType.ERROR, "Erro ao gravar");
			}
		});

		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> {
			try {
				control.pesquisarPorNome();
			} catch (RPGException | ClassNotFoundException err) {
				alert(AlertType.ERROR, "Erro ao pesquisar");
			}
		});

		Button btnLimpar = new Button("*");
		btnLimpar.setOnAction(e -> control.limparTudo());

		paneForm.add(btnGravar, 0, 6);
		paneForm.add(btnPesquisar, 1, 6);
		paneForm.add(btnLimpar, 2, 0);

		generateColumns();
		vincularPropriedades();

		panePrincipal.setTop(paneForm);
		panePrincipal.setCenter(tableView);

		try {
			control.pesquisarTodos();
		} catch (RPGException | ClassNotFoundException e) {
			alert(AlertType.ERROR, "Erro ao pesquisar todos");
		}

		return panePrincipal;
	}

	public boolean validarCampos() {
		if (txtNome.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo nome vazio!");
			return false;
		}

		try {
			if (Integer.parseInt(txtDanoCausa.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de dano não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em dano!");
			return false;
		}

		try {
			if (Integer.parseInt(txtVida.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de vida não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em vida.");
			return false;
		}

		if (txtVida.getText().isEmpty() || Integer.parseInt(txtVida.getText()) <= 0) {
			alert(AlertType.ERROR, "Campo de vida não pode ser preenchido assim!");
			return false;
		}

		if (txtFraqueza.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo fraqueza vazio!");
			return false;
		}

		if (txtDescricao.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo descrição vazio!");
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
		TableColumn<Inimigo, Integer> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<Inimigo, Integer>("id"));

		TableColumn<Inimigo, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Inimigo, String>("nome"));

		TableColumn<Inimigo, Double> col3 = new TableColumn<>("Dano_Causa");
		col3.setCellValueFactory(new PropertyValueFactory<Inimigo, Double>("danoCausa"));

		TableColumn<Inimigo, String> col4 = new TableColumn<>("Vida");
		col4.setCellValueFactory(new PropertyValueFactory<Inimigo, String>("vida"));

		TableColumn<Inimigo, Integer> col5 = new TableColumn<>("Fraqueza");
		col5.setCellValueFactory(new PropertyValueFactory<Inimigo, Integer>("fraqueza"));

		TableColumn<Inimigo, String> col6 = new TableColumn<>("Descricao");
		col6.setCellValueFactory(new PropertyValueFactory<Inimigo, String>("descricao"));

		Callback<TableColumn<Inimigo, Void>, TableCell<Inimigo, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Inimigo, Void> call(TableColumn<Inimigo, Void> param) {
				TableCell<Inimigo, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Inimigo c = tableView.getItems().get(getIndex());
								control.excluir(c);
							} catch (RPGException | ClassNotFoundException err) {
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

		TableColumn<Inimigo, Void> col7 = new TableColumn<>("Ações");
		col7.setCellFactory(callback);

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			control.entidadeParaTela(novo);
		});
	}

	public void vincularPropriedades() {
		Bindings.bindBidirectional(lblId.textProperty(), control.idProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtDanoCausa.textProperty(), control.danoCausaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtVida.textProperty(), control.vidaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtFraqueza.textProperty(), control.fraquezaProperty());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
	}
}