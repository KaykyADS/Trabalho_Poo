package view;

import controller.ArmaduraControl;
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
import model.Armadura;
import model.RPGException;

public class ArmaduraView implements Tela {

	private Label lblId = new Label("");
	private TextField txtNome = new TextField("");
	private TextField txtPeso = new TextField("");
	private TextField txtDescricao = new TextField("");
	private ComboBox<String> txtTipoArmadura = new ComboBox<>();
	private TextField txtVeneno = new TextField("");
	private TextField txtFisica = new TextField("");
	private TextField txtMagica = new TextField("");
	private TextField txtFogo = new TextField("");
	private TextField txtEletrica = new TextField("");
	private TextField txtHemorragica = new TextField("");

	private TableView<Armadura> tableView = new TableView<>();

	private ArmaduraControl control;

	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();

		try {
			control = new ArmaduraControl();
		} catch (RPGException e) {
			alert(AlertType.ERROR, "Ao ao inicializar o sistema");
		}

		txtTipoArmadura.getItems().addAll("Capacete", "Peitoral", "Manoplas", "Perneiras");
		GridPane paneForm = new GridPane();
		paneForm.add(new Label("Id: "), 0, 0);
		paneForm.add(lblId, 1, 0);
		paneForm.add(new Label("Nome: "), 0, 1);
		paneForm.add(txtNome, 1, 1);
		paneForm.add(new Label("Peso: "), 0, 2);
		paneForm.add(txtPeso, 1, 2);
		paneForm.add(new Label("Descrição: "), 0, 3);
		paneForm.add(txtDescricao, 1, 3);
		paneForm.add(new Label("Tipo da Armadura: "), 0, 4);
		paneForm.add(txtTipoArmadura, 1, 4);
		paneForm.add(new Label("Resistência a veneno: "), 0, 5);
		paneForm.add(txtVeneno, 1, 5);
		paneForm.add(new Label("Resistência fisica: "), 0, 6);
		paneForm.add(txtFisica, 1, 6);
		paneForm.add(new Label("Resistência magica: "), 0, 7);
		paneForm.add(txtMagica, 1, 7);
		paneForm.add(new Label("Resistência a fogo: "), 0, 8);
		paneForm.add(txtFogo, 1, 8);
		paneForm.add(new Label("Resistência eletrica: "), 0, 9);
		paneForm.add(txtEletrica, 1, 9);
		paneForm.add(new Label("Resistência a hemorragia: "), 0, 10);
		paneForm.add(txtHemorragica, 1, 10);

		Button btnGravar = new Button("Gravar");
		btnGravar.setOnAction(e -> {
			try {
				if (validarCampos()) {
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

		paneForm.add(btnGravar, 0, 11);
		paneForm.add(btnPesquisar, 1, 11);
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
			if (Double.parseDouble(txtPeso.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de peso não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em peso!");
			return false;
		}

		if (txtDescricao.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo descrição vazio!");
			return false;
		}

		if (txtTipoArmadura.getValue() == null || txtTipoArmadura.getValue().isEmpty()) {
			alert(AlertType.ERROR, "Campo tipo armadura vazio!");
			return false;
		}

		try {
			if (Integer.parseInt(txtVeneno.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. veneno não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. veneno.");
			return false;
		}

		try {
			if (Integer.parseInt(txtFisica.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. física não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. física.");
			return false;
		}

		try {
			if (Integer.parseInt(txtMagica.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. mágica não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. mágica.");
			return false;
		}

		try {
			if (Integer.parseInt(txtFogo.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. a fogo não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. a fogo.");
			return false;
		}

		try {
			if (Integer.parseInt(txtEletrica.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. elétrica não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. elétrica.");
			return false;
		}

		try {
			if (Integer.parseInt(txtHemorragica.getText()) <= 0) {
				alert(AlertType.ERROR, "Campo de res. hemorrágica não pode ser 0 ou menos!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em res. hemorrágica.");
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
		TableColumn<Armadura, Integer> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("id"));

		TableColumn<Armadura, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Armadura, String>("nome"));

		TableColumn<Armadura, Double> col3 = new TableColumn<>("Peso");
		col3.setCellValueFactory(new PropertyValueFactory<Armadura, Double>("peso"));

		TableColumn<Armadura, String> col4 = new TableColumn<>("Descrição");
		col4.setCellValueFactory(new PropertyValueFactory<Armadura, String>("descricao"));

		TableColumn<Armadura, String> col5 = new TableColumn<>("Tipo_Armadura");
		col5.setCellValueFactory(new PropertyValueFactory<Armadura, String>("tipoArmadura"));

		TableColumn<Armadura, Integer> col6 = new TableColumn<>("Res. Veneno");
		col6.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("veneno"));

		TableColumn<Armadura, Integer> col7 = new TableColumn<>("Res. Física");
		col7.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("fisica"));

		TableColumn<Armadura, Integer> col8 = new TableColumn<>("Res. Mágica");
		col8.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("magica"));

		TableColumn<Armadura, Integer> col9 = new TableColumn<>("Res. Fogo");
		col9.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("fogo"));

		TableColumn<Armadura, Integer> col10 = new TableColumn<>("Res. Elétrica");
		col10.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("eletrica"));

		TableColumn<Armadura, Integer> col11 = new TableColumn<>("Res. Hemorragica");
		col11.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("hemorragica"));

		Callback<TableColumn<Armadura, Void>, TableCell<Armadura, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Armadura, Void> call(TableColumn<Armadura, Void> param) {
				TableCell<Armadura, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Armadura c = tableView.getItems().get(getIndex());
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

		TableColumn<Armadura, Void> col12 = new TableColumn<>("Ações");
		col12.setCellFactory(callback);

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			control.entidadeParaTela(novo);
		});
	}

	public void vincularPropriedades() {
		Bindings.bindBidirectional(lblId.textProperty(), control.idProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtPeso.textProperty(), control.pesoProperty(),
				(StringConverter) new DoubleStringConverter());
		Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
		Bindings.bindBidirectional(txtTipoArmadura.valueProperty(), control.tipoArmaduraProperty());
		Bindings.bindBidirectional(txtVeneno.textProperty(), control.venenoProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtFisica.textProperty(), control.fisicaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtMagica.textProperty(), control.magicaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtFogo.textProperty(), control.fogoProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtEletrica.textProperty(), control.eletricaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtHemorragica.textProperty(), control.hemorragicaProperty(),
				(StringConverter) new IntegerStringConverter());
	}
}