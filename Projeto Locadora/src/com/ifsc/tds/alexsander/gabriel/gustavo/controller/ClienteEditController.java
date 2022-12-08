/**
 * Sample Skeleton for 'ClienteEdit.fxml' Controller Class
 */

package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClienteEditController {

    @FXML // fx:id="pnlPrincipal"
    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDados"
    private GridPane pnlDados; // Value injected by FXMLLoader

    @FXML // fx:id="lblNome"
    private Label lblNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="lblTelefone"
    private Label lblTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="txtTelefone"
    private TextField txtTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="pnlBotoes"
    private HBox pnlBotoes; // Value injected by FXMLLoader

    @FXML // fx:id="btnOK"
    private Button btnOK; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancela"
    private Button btnCancela; // Value injected by FXMLLoader
    
	private Stage janelaClienteEdit;

	private Cliente cliente;

	private boolean okClick = false;

    public Stage getJanelaClienteEdit() {
		return janelaClienteEdit;
	}

	public void setJanelaClienteEdit(Stage janelaClienteEdit) {
		this.janelaClienteEdit = janelaClienteEdit;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isOkClick() {
		return okClick;
	}

	public void setOkClick(boolean okClick) {
		this.okClick = okClick;
	}

	@FXML
    void onClickBtnCancela(ActionEvent event) {
		this.getJanelaClienteEdit().close();
    }

    @FXML
    void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.cliente.setNome(this.txtNome.getText());
			this.cliente.setTelefone(this.txtTelefone.getText());

			this.okClick = true;
			this.getJanelaClienteEdit().close();
		}
    }

	public void populaTela(Cliente cliente) {
		this.cliente = cliente;

		this.txtNome.setText(cliente.getNome());
		this.txtTelefone.setText(cliente.getTelefone());
	}
	private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			mensagemErros += "Informe um nome válido!\n";
		}

		if (this.txtTelefone.getText() == null || this.txtTelefone.getText().trim().length() == 0) {
			mensagemErros += "Informe o seu telefone!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaClienteEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}
}

