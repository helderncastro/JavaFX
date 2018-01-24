package br.edu.unitri.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import br.edu.unitri.MainApp;
import br.edu.unitri.model.Contato;

public class ContatoViewController {

	@FXML
	private TableView<Contato> contatoTable;
	@FXML
	private TableColumn<Contato, String> nomeTableColumn;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label telefoneLabel;

	private MainApp mainApp;

	public ContatoViewController() {
	}

	@FXML
	private void initialize() {

		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

		exibirDetalhesContato(null);

		contatoTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> exibirDetalhesContato(newValue));
	}

	public void setMainApp(MainApp mainApp) {

		this.mainApp = mainApp;
		contatoTable.setItems(mainApp.getListaContatos());
	}

	private void exibirDetalhesContato(Contato contato) {

		if (contato != null) {

			nomeLabel.setText(contato.getNome());
			emailLabel.setText(contato.getEmail());
			telefoneLabel.setText(contato.getTelefone());

		} else {

			nomeLabel.setText("");
			emailLabel.setText("");
			telefoneLabel.setText("");
		}
	}

	@FXML
	private void removerContato() {

		int selectedIndex = contatoTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			contatoTable.getItems().remove(selectedIndex);
		} else {
			Dialogs.create()
			.title("Alerta")
			.masthead("Nenhum contato selecionado")
			.message("Não existe nenhum contato a ser removido!")
			.showWarning();
		}
	}  

	@FXML
	private void abrirNovoContato() {
		Contato tempContato = new Contato();
		boolean okClicked = mainApp.exibirContatoDialog(tempContato);
		if (okClicked) {
			mainApp.getListaContatos().add(tempContato);
		}
	}

	@FXML
	private void editarContato() {

		Contato contatoSelecionado = contatoTable.getSelectionModel().getSelectedItem();
		if (contatoSelecionado != null) {
			boolean okClicked = mainApp.exibirContatoDialog(contatoSelecionado);
			if (okClicked) {
				exibirDetalhesContato(contatoSelecionado);
			}

		} else {
			Dialogs.create()
			.title("Alerta")
			.masthead("Nenhum contato selecionado")
			.message("Não existe nenhum contato a ser removido!")
			.showWarning();
		}
	}
}