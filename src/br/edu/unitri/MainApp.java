package br.edu.unitri;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import br.edu.unitri.model.Contato;
import br.edu.unitri.view.ContatoDialogController;
import br.edu.unitri.view.ContatoViewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Contato> listaContatos = FXCollections.observableArrayList();

	public MainApp() {

		listaContatos.add(new Contato("José da Silva", "jose@gmail.com", "(34)9999-9999"));
		listaContatos.add(new Contato("Manoel Costinha", "manoel@gmail.com", "(34)9977-7777"));
		listaContatos.add(new Contato("Maria Madalena", "maria@gmail.com", "(34)9966-6666"));
	}

	public ObservableList<Contato> getListaContatos() {
		return listaContatos;
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ContatosApp");
	    this.primaryStage.getIcons().add(new Image("file:resources/img/ContatosApp.png"));
		iniciarLayoutPrincipal();
		exibirContatoView();
	}

	public void iniciarLayoutPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LayoutPrincipal.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exibirContatoView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoView.fxml"));
			AnchorPane contatoView = (AnchorPane) loader.load();
			rootLayout.setCenter(contatoView);
			ContatoViewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean exibirContatoDialog(Contato contato) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ContatoDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.atualizarContato(contato);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}	

	public static void main(String[] args) {
		launch(args);
	}
}