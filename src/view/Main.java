package view;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Map<String, Tela> telas = new HashMap<>();

	@Override
	public void start(Stage stage) {
		BorderPane panePrincipal = new BorderPane();
		telas.put("armadura", new ArmaduraView());
		telas.put("inimigo", new InimigoView());
		telas.put("classe", new ClasseView());
		telas.put("personagem", new PersonagemView());
		telas.put("arma", new ArmaView());

		MenuBar menuBar = new MenuBar();

		Menu mnuCadastro = new Menu("Cadastro");

		MenuItem mnuArmadura = new MenuItem("Armadura");
		MenuItem mnuInimigo = new MenuItem("Inimigo");
		MenuItem mnuArma = new MenuItem("Arma");
		MenuItem mnuClasse = new MenuItem("Classe");
		MenuItem mnuPersonagem = new MenuItem("Personagem");

		mnuCadastro.getItems().addAll(mnuInimigo, mnuArma, mnuArmadura, mnuClasse, mnuPersonagem);

		menuBar.getMenus().add(mnuCadastro);

		panePrincipal.setTop(menuBar);

		mnuArmadura.setOnAction(e -> panePrincipal.setCenter(telas.get("armadura").render()));
		mnuInimigo.setOnAction(e -> panePrincipal.setCenter(telas.get("inimigo").render()));
		mnuClasse.setOnAction(e -> panePrincipal.setCenter(telas.get("classe").render()));
		mnuPersonagem.setOnAction(e -> panePrincipal.setCenter(telas.get("personagem").render()));
		mnuArma.setOnAction(e -> panePrincipal.setCenter(telas.get("arma").render()));

		Scene scn = new Scene(panePrincipal, 1000, 600);
		stage.setScene(scn);
		stage.setTitle("Catalogo RPG");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

}
