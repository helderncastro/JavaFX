package br.edu.unitri.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contato {

	private final StringProperty nome;
	private final StringProperty email;
	private final StringProperty telefone;
	
    public Contato() {
        this(null, null, null);
    }
	
	public Contato(String nome, String email, String telefone) {

		this.nome = new SimpleStringProperty(nome);
		this.email = new SimpleStringProperty(email);
		this.telefone = new SimpleStringProperty(telefone);
	}

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final java.lang.String nome) {
		this.nomeProperty().set(nome);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final java.lang.String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final java.lang.String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty telefoneProperty() {
		return this.telefone;
	}

	public final java.lang.String getTelefone() {
		return this.telefoneProperty().get();
	}

	public final void setTelefone(final java.lang.String telefone) {
		this.telefoneProperty().set(telefone);
	}	
}