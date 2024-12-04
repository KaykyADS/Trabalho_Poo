package view;

import controller.ClasseControl;
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
import model.Arma;
import model.Armadura;
import model.Classe;

public class ClasseView implements Tela {
	private TextField txtNome = new TextField("");
	private TextField txtHabilidade = new TextField("");
	private TextField txtLevel = new TextField("");
	private TextField txtForca = new TextField("");
	private TextField txtFe = new TextField("");
	private TextField txtDestreza = new TextField("");
	private TextField txtInteligencia = new TextField("");
	private TextField txtSorte = new TextField("");
	private TextField txtVitalidade = new TextField("");
	private TextField txtResistencia = new TextField("");
	private TextField txtVigor = new TextField("");
	private TextField txtConhecimento = new TextField("");
	private TextField txtIdArma = new TextField("");
	private TextField txtIdArmadura = new TextField("");

	private TableView<Classe> tableView = new TableView<>();
	private TableView<Arma> tableArma = new TableView<>();
	private TableView<Armadura> tableArmadura = new TableView<>();
	private ClasseControl control;

	@Override
	public Pane render() {
		BorderPane paneSecundario = new BorderPane();
		BorderPane panePrincipal = new BorderPane();

		try {
			control = new ClasseControl();
		} catch (Exception err) {
			alert(AlertType.ERROR, "Erro ao inicializar o sistema");
		}

		GridPane pane = new GridPane();
		pane.add(new Label("Criar Classe"), 0, 0);
		pane.add(new Label("Nome: "), 0, 1);
		pane.add(txtNome, 1, 1);
		pane.add(new Label("Habilidade: "), 0, 2);
		pane.add(txtHabilidade, 1, 2);
		pane.add(new Label("Level: "), 0, 3);
		pane.add(txtLevel, 1, 3);
		pane.add(new Label("Força: "), 0, 4);
		pane.add(txtForca, 1, 4);
		pane.add(new Label("Fé: "), 0, 5);
		pane.add(txtFe, 1, 5);
		pane.add(new Label("Destreza: "), 0, 6);
		pane.add(txtDestreza, 1, 6);
		pane.add(new Label("Inteligência: "), 0, 7);
		pane.add(txtInteligencia, 1, 7);
		pane.add(new Label("Sorte: "), 0, 8);
		pane.add(txtSorte, 1, 8);
		pane.add(new Label("Vitalidade: "), 0, 9);
		pane.add(txtVitalidade, 1, 9);
		pane.add(new Label("Resistência: "), 0, 10);
		pane.add(txtResistencia, 1, 10);
		pane.add(new Label("Vigor: "), 0, 11);
		pane.add(txtVigor, 1, 11);
		pane.add(new Label("Conhecimento: "), 0, 12);
		pane.add(txtConhecimento, 1, 12);
		pane.add(new Label("ID Arma: "), 0, 13);
		pane.add(txtIdArma, 1, 13);
		pane.add(new Label("ID Armadura: "), 0, 14);
		pane.add(txtIdArmadura, 1, 14);

		Button btnSalvar = new Button("Salvar");
		btnSalvar.setOnAction(e -> {
			try {
				if (validarCampos()) {
					int armadura = Integer.parseInt(txtIdArmadura.getText());
					int arma = Integer.parseInt(txtIdArma.getText());
					if (control.verificarArma(arma, armadura));  else alert(AlertType.ERROR, "Selecione Armaduras e Armas válidas");
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

		pane.add(btnSalvar, 0, 15);
		pane.add(btnPesquisar, 1, 15);
		pane.add(btnLimpar, 2, 15);

		generateArmadura();
		generateColumns();
		generateArma();

		vincularPropriedades();

		paneSecundario.setLeft(pane);
		paneSecundario.setCenter(tableView);
		panePrincipal.setTop(paneSecundario);
		panePrincipal.setCenter(tableArmadura);
		panePrincipal.setRight(tableArma);

		try {
			control.pesquisarTodos();
			control.pesquisarPorArma();
			control.pesquisarPorArmadura();
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
		if (txtHabilidade.getText().isEmpty()) {
			alert(AlertType.ERROR, "Campo habilidade vazio!");
			return false;
		}
		try {
			int level = Integer.parseInt(txtLevel.getText());
			if (level <= 0) {
				alert(AlertType.ERROR, "Campo level vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em level");
			return false;
		}
		try {
			int forca = Integer.parseInt(txtForca.getText());
			if (forca <= 0) {
				alert(AlertType.ERROR, "Campo força vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em força");
			return false;
		}
		try {
			int fe = Integer.parseInt(txtFe.getText());
			if (fe <= 0) {
				alert(AlertType.ERROR, "Campo fé vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em fé");
			return false;
		}
		try {
			int destreza = Integer.parseInt(txtDestreza.getText());
			if (destreza <= 0) {
				alert(AlertType.ERROR, "Campo destreza vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em destreza");
			return false;
		}
		try {
			int inte = Integer.parseInt(txtInteligencia.getText());
			if (inte <= 0) {
				alert(AlertType.ERROR, "Campo inteligência vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em inteligência");
			return false;
		}
		try {
			int sorte = Integer.parseInt(txtSorte.getText());
			if (sorte <= 0) {
				alert(AlertType.ERROR, "Campo sorte vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em sorte");
			return false;
		}
		try {
			int vit = Integer.parseInt(txtVitalidade.getText());
			if (vit <= 0) {
				alert(AlertType.ERROR, "Campo vitalidade vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em vitalidade");
			return false;
		}
		try {
			int resis = Integer.parseInt(txtResistencia.getText());
			if (resis <= 0) {
				alert(AlertType.ERROR, "Campo resistência vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em resistência");
			return false;
		}
		try {
			int vig = Integer.parseInt(txtVigor.getText());
			if (vig <= 0) {
				alert(AlertType.ERROR, "Campo vigor vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em vigor");
			return false;
		}
		try {
			int conh = Integer.parseInt(txtConhecimento.getText());
			if (conh <= 0) {
				alert(AlertType.ERROR, "Campo conhecimento vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em conhecimento");
			return false;
		}
		try {
			int arma = Integer.parseInt(txtIdArma.getText());
			if (arma <= 0) {
				alert(AlertType.ERROR, "Campo arma vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em idArma");
			return false;
		}
		try {
			int armadura = Integer.parseInt(txtIdArmadura.getText());
			if (armadura <= 0) {
				alert(AlertType.ERROR, "Campo armadura vazio!");
				return false;
			}
		} catch (Exception err) {
			alert(AlertType.ERROR, "Insira um número em idArmadura");
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

		TableColumn<Classe, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Classe, String> col2 = new TableColumn<>("Habilidade");
		col2.setCellValueFactory(new PropertyValueFactory<>("habilidade"));

		TableColumn<Classe, Integer> col3 = new TableColumn<>("Level");
		col3.setCellValueFactory(new PropertyValueFactory<>("level"));

		TableColumn<Classe, Integer> col4 = new TableColumn<>("Força");
		col4.setCellValueFactory(new PropertyValueFactory<>("forca"));

		TableColumn<Classe, Integer> col5 = new TableColumn<>("Fé");
		col5.setCellValueFactory(new PropertyValueFactory<>("fe"));

		TableColumn<Classe, Integer> col6 = new TableColumn<>("Destreza");
		col6.setCellValueFactory(new PropertyValueFactory<>("destreza"));

		TableColumn<Classe, Integer> col7 = new TableColumn<>("Inteligência");
		col7.setCellValueFactory(new PropertyValueFactory<>("inteligencia"));

		TableColumn<Classe, Integer> col8 = new TableColumn<>("Sorte");
		col8.setCellValueFactory(new PropertyValueFactory<>("sorte"));

		TableColumn<Classe, Integer> col9 = new TableColumn<>("Vitalidade");
		col9.setCellValueFactory(new PropertyValueFactory<>("vitalidade"));

		TableColumn<Classe, Integer> col10 = new TableColumn<>("Resistência");
		col10.setCellValueFactory(new PropertyValueFactory<>("resistencia"));

		TableColumn<Classe, Integer> col11 = new TableColumn<>("Vigor");
		col11.setCellValueFactory(new PropertyValueFactory<>("vigor"));

		TableColumn<Classe, Integer> col12 = new TableColumn<>("Conhecimento");
		col12.setCellValueFactory(new PropertyValueFactory<>("conhecimento"));

		TableColumn<Classe, Integer> col13 = new TableColumn<>("Arma");
		col13.setCellValueFactory(new PropertyValueFactory<>("idArma"));

		TableColumn<Classe, Integer> col14 = new TableColumn<>("Armadura");
		col14.setCellValueFactory(new PropertyValueFactory<>("idArmadura"));

		Callback<TableColumn<Classe, Void>, TableCell<Classe, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Classe, Void> call(TableColumn<Classe, Void> param) {
				TableCell<Classe, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Classe a = tableView.getItems().get(getIndex());
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

		TableColumn<Classe, Void> col15 = new TableColumn<>("Ações");
		col15.setCellFactory(callback);

		tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13,
				col14, col15);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			System.out.println("Selecionado a Classe ==> " + novo);
			control.entidadeParaTela(novo);
		});
	}

	public void generateArma() {
		TableColumn<Arma, Integer> col1 = new TableColumn("Id_Arma");
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

		tableArma.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
		tableArma.setItems(control.getListaArma());

		tableArma.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			alert(AlertType.CONFIRMATION, "Você escolheu a Arma: " + novo.getNome() + " para sua classe!");
			System.out.println("Selecionado a Arma ==> " + novo);
			control.armaParaClasse(novo);
		});
	}

	public void generateArmadura() {
		TableColumn<Armadura, Integer> col1 = new TableColumn<>("Id_Armadura");
		col1.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("id"));

		TableColumn<Armadura, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Armadura, String>("nome"));

		TableColumn<Armadura, Double> col3 = new TableColumn<>("Peso");
		col3.setCellValueFactory(new PropertyValueFactory<Armadura, Double>("peso"));

		TableColumn<Armadura, String> col4 = new TableColumn<>("Descricao");
		col4.setCellValueFactory(new PropertyValueFactory<Armadura, String>("descricao"));

		TableColumn<Armadura, String> col5 = new TableColumn<>("Tipo_Armadura");
		col5.setCellValueFactory(new PropertyValueFactory<Armadura, String>("tipoArmadura"));

		TableColumn<Armadura, Integer> col6 = new TableColumn<>("Res. Veneno");
		col6.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("veneno"));

		TableColumn<Armadura, Integer> col7 = new TableColumn<>("Res. Fisica");
		col7.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("fisica"));

		TableColumn<Armadura, Integer> col8 = new TableColumn<>("Res. Magica");
		col8.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("magica"));

		TableColumn<Armadura, Integer> col9 = new TableColumn<>("Res. Fogo");
		col9.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("fogo"));

		TableColumn<Armadura, Integer> col10 = new TableColumn<>("Res. Eletrica");
		col10.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("eletrica"));

		TableColumn<Armadura, Integer> col11 = new TableColumn<>("Res. Hemorragica");
		col11.setCellValueFactory(new PropertyValueFactory<Armadura, Integer>("hemorragica"));

		tableArmadura.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11);
		tableArmadura.setItems(control.getListaArmadura());
		
		tableArmadura.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			alert(AlertType.CONFIRMATION, "Você escolheu a Armadura: " + novo.getNome() + " para sua classe!");
			System.out.println("Selecionado a Armadura ==> " + novo);
			control.armaduraParaClasse(novo);
		});
	}

	public void vincularPropriedades() {
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtHabilidade.textProperty(), control.habilidadeProperty());
		Bindings.bindBidirectional(txtLevel.textProperty(), control.levelProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtForca.textProperty(), control.forcaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtFe.textProperty(), control.feProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtDestreza.textProperty(), control.destrezaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtInteligencia.textProperty(), control.inteligenciaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtSorte.textProperty(), control.sorteProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtVitalidade.textProperty(), control.vitalidadeProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtResistencia.textProperty(), control.resistenciaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtVigor.textProperty(), control.vigorProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtConhecimento.textProperty(), control.conhecimentoProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtIdArma.textProperty(), control.idArmaProperty(),
				(StringConverter) new IntegerStringConverter());
		Bindings.bindBidirectional(txtIdArmadura.textProperty(), control.idArmaduraProperty(),
				(StringConverter) new IntegerStringConverter());

	}
}
