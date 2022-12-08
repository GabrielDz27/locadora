/**
 * Sample Skeleton for 'EmprestimoEdit.fxml' Controller Class
 */

package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.net.URL;

import java.sql.Date;
import java.util.ResourceBundle;

import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Cliente;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Emprestimo;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Filme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EmprestimoEditController implements Initializable {

    @FXML // fx:id="btnCancela"
    private Button btnCancela; // Value injected by FXMLLoader

    @FXML // fx:id="btnOK"
    private Button btnOK; // Value injected by FXMLLoader

    @FXML // fx:id="cbxCliente"
    private ComboBox<Cliente> cbxCliente; // Value injected by FXMLLoader

    @FXML // fx:id="cbxFilme"
    private ComboBox<Filme> cbxFilme; // Value injected by FXMLLoader

    @FXML // fx:id="dtpEmprestimo"
    private DatePicker dtpEmprestimo; // Value injected by FXMLLoader

    @FXML // fx:id="dtpEntrega"
    private DatePicker dtpEntrega; // Value injected by FXMLLoader

    @FXML // fx:id="lblEmprestimo"
    private Label lblEmprestimo; // Value injected by FXMLLoader

    @FXML // fx:id="lblEntrega"
    private Label lblEntrega; // Value injected by FXMLLoader

    @FXML // fx:id="lblFilme"
    private Label lblFilme; // Value injected by FXMLLoader

    @FXML // fx:id="lblObservacao"
    private Label lblObservacao; // Value injected by FXMLLoader

    @FXML // fx:id="lblValorEmprestimo"
    private Label lblValorEmprestimo; // Value injected by FXMLLoader

    @FXML // fx:id="lblidCliente"
    private Label lblidCliente; // Value injected by FXMLLoader

    @FXML // fx:id="pnlBotoes"
    private HBox pnlBotoes; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDados"
    private GridPane pnlDados; // Value injected by FXMLLoader

    @FXML // fx:id="pnlPrincipal"
    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmprestimo"
    private TextField txtValor; // Value injected by FXMLLoader

    @FXML // fx:id="txtObservacao"
    private TextField txtObservacao; // Value injected by FXMLLoader

    private Stage janelaEmprestimoEdit;

	private Emprestimo emprestimo;

	private boolean okClick = false;

	private ClienteListaController clienteListaController;
	private FilmeListaController filmeListaController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clienteListaController = new ClienteListaController();
		this.filmeListaController = new FilmeListaController();

		this.carregarComboBoxClientes();
		this.carregarComboBoxFilmes();
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public ClienteListaController getClienteListaController() {
		return clienteListaController;
	}

	public void setClienteListaController(ClienteListaController clienteListaController) {
		this.clienteListaController = clienteListaController;
	}

	public FilmeListaController getFilmeListaController() {
		return filmeListaController;
	}

	public void setFilmeListaController(FilmeListaController filmeListaController) {
		this.filmeListaController = filmeListaController;
	}

	public Stage getJanelaEmprestimoEdit() {
		return janelaEmprestimoEdit;
	}

	public void setOkClick(boolean okClick) {
		this.okClick = okClick;
	}

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaEmprestimoEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.emprestimo.setObs(this.txtObservacao.getText());
			this.emprestimo.setData_emprestimo((Date.valueOf(this.dtpEmprestimo.getValue())));
			this.emprestimo.setData_Entrega((Date.valueOf(this.dtpEntrega.getValue())));
			this.emprestimo.setCliente(this.cbxCliente.getSelectionModel().getSelectedItem());
			this.emprestimo.setFilme(this.cbxFilme.getSelectionModel().getSelectedItem());
			this.emprestimo.setValorEmprestimo(Integer.parseInt(txtValor.getText()));
			this.okClick = true;
			this.getJanelaEmprestimoEdit().close();
		}
	}


	private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.cbxCliente.getSelectionModel() == null) {
			mensagemErros += "Informe o cliente\n";
		}
		
		if (this.cbxFilme.getSelectionModel() == null) {
			mensagemErros += "Informe o filme\n";
		}
		
		if (this.txtObservacao.getText() == null || this.txtObservacao.getText().trim().length() == 0) {
			mensagemErros += "Insira alguma observação\n";
		}
		
		if (this.txtValor.getText() == null || this.txtValor.getText().trim().length() == 0) {
			mensagemErros += "Insira o valor do empréstimo\n";
		}
		
		if (this.dtpEmprestimo.getPromptText() == null) {
			mensagemErros += "Insira a data da realização do empréstimo\n";
		}
		
		if (this.dtpEntrega.getPromptText() == null) {
			mensagemErros += "Insira a data de entrega\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaEmprestimoEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}

	public void populaTela(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
		
		if( this.emprestimo.getValorEmprestimo() >= 0) {
			this.txtValor.setText(String.valueOf(this.emprestimo.getValorEmprestimo()));
		}
			
		if (this.emprestimo.getObs() != null) {
			this.txtObservacao.setText(this.emprestimo.getObs());
		}
	
		if (this.emprestimo.getData_Entrega() != null) {
			this.dtpEntrega.setValue(this.emprestimo.getData_Entrega().toLocalDate());
		}
		
		if (this.emprestimo.getData_emprestimo() != null) {
			this.dtpEmprestimo.setValue(this.emprestimo.getData_emprestimo().toLocalDate());
		}

		if (this.emprestimo.getCliente() != null) {
			this.cbxCliente.setValue(this.emprestimo.getCliente());
		}

		if (this.emprestimo.getFilme() != null) {
			this.cbxFilme.setValue(this.emprestimo.getFilme());
		}
	}

	public void setJanelaEmprestimoEdit(Stage janelaEmprestimoEditar) {
		this.janelaEmprestimoEdit = janelaEmprestimoEditar;
	}

	public boolean isOkClick() {
		return okClick;
	}
	
	public void carregarComboBoxClientes() {
		ObservableList<Cliente> observableListaCliente = FXCollections
				.observableArrayList(this.clienteListaController.retornaListagemCliente());
		this.cbxCliente.setItems(observableListaCliente);
	}

	public void carregarComboBoxFilmes() {
		ObservableList<Filme> observableListaTipoFilmes = FXCollections
				.observableArrayList(this.filmeListaController.retornaListagemFilme());

		this.cbxFilme.setItems(observableListaTipoFilmes);
	}
}

