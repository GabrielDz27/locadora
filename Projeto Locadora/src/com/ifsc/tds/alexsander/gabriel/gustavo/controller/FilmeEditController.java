package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.sql.Date;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Filme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FilmeEditController {

    @FXML
    private AnchorPane pnlPrincipal;

    @FXML
    private GridPane pnlDados;

    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblAno;

    @FXML
    private DatePicker cbxAno;

    @FXML
    private HBox pnlBotoes;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancela;
    
    private Stage janelaFilmeEdit;
    
    private Filme filme;
    
    private boolean okClick = false;
    
    @FXML
    void onClickBtnCancela(ActionEvent event) {
    	this.getJanelaFilmeEdit().close();
	
    }
	public Stage getJanelaFilmeEdit() {
		return janelaFilmeEdit;
	}

	public void setJanelaFilmeEdit(Stage janelaFilmeEdit) {
		this.janelaFilmeEdit = janelaFilmeEdit;
	}
    @FXML
    void onClickBtnOK(ActionEvent event) {
    	if (validarCampos()) {
			this.filme.setNome(this.txtNome.getText());
			this.filme.setAno(Date.valueOf(this.cbxAno.getValue()));
		
			this.okClick = true;
			this.getJanelaFilmeEdit().close();
		}
    }
    	public void populaTela(Filme filme) {
    		this.filme = filme;

    		this.txtNome.setText(this.filme.getNome());

    		if (this.filme.getAno() != null) {
    			this.cbxAno.setValue(this.filme.getAno().toLocalDate());
    		}
    	}
    	public boolean isOkClick() {
    		return okClick;
    	}
    	private boolean validarCampos() {
    		String mensagemErros = new String();

    		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
    			mensagemErros += "Informe o nome!\n";
    		}
    		
    		if (mensagemErros.length() == 0) {
    			return true;
    		} else {
    			Alert alerta = new Alert(Alert.AlertType.ERROR);
    			alerta.initOwner(this.janelaFilmeEdit);
    			alerta.setTitle("Dados inválidos!");
    			alerta.setHeaderText("Favor corrigir as seguintes informações:");
    			alerta.setContentText(mensagemErros);
    			alerta.showAndWait();

    			return false;
    		}
    	}
}
