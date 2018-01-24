package br.edu.unitri.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import br.edu.unitri.model.Contato;

public class ContatoDialogController {

	@FXML
	private TextField nomeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefoneTextField;

	private Stage dialogStage;
	private Contato contato;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void atualizarContato(Contato contato) {

		this.contato = contato;
		nomeTextField.setText(contato.getNome());
		emailTextField.setText(contato.getEmail());
		telefoneTextField.setText(contato.getTelefone());
	}

	private boolean validarCampos() {

		String errorMessage = "";
		if (nomeTextField.getText() == null || nomeTextField.getText().length() == 0) {
			errorMessage += "Nome inválido!\n"; 
		}
		if (emailTextField.getText() == null || emailTextField.getText().length() == 0) {
			errorMessage += "Email inválido!\n"; 
		}
		if (telefoneTextField.getText() == null || telefoneTextField.getText().length() == 0) {
			errorMessage += "Telefone inválido!\n"; 
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Dialogs.create()
			.title("Alerta")
			.masthead("Existem campos inválidos no formulário")
			.message(errorMessage)
			.showError();
			return false;
		}
	}

	@FXML
	private void cancelar() {
		
		dialogStage.close();
	}

	@FXML
	private void salvarContato() {

		if (validarCampos()) {

			contato.setNome(nomeTextField.getText());
			contato.setEmail(emailTextField.getText());
			contato.setTelefone(telefoneTextField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}
}
