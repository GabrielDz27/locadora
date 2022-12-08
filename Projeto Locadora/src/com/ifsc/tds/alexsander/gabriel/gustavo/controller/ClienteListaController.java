/**
 * Sample Skeleton for 'ClienteLista.fxml' Controller Class

 */

package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.alexsander.gabriel.gustavo.dao.ClienteDAO;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteListaController implements Initializable {

    @FXML // fx:id="pnlPrincipal"
    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDivisao"
    private SplitPane pnlDivisao; // Value injected by FXMLLoader

    @FXML // fx:id="pnlCadastro"
    private AnchorPane pnlCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="tbvCadastro"
    private TableView<Cliente> tbvCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="tbcCodigo"
    private TableColumn<Cliente, Long> tbcCodigo; // Value injected by FXMLLoader

    @FXML // fx:id="tbcNome"
    private TableColumn<Cliente, String> tbcNome; // Value injected by FXMLLoader

    @FXML // fx:id="pnlLista"
    private AnchorPane pnlLista; // Value injected by FXMLLoader

    @FXML // fx:id="lblDetalhes"
    private Label lblDetalhes; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDetalhesContato"
    private GridPane pnlDetalhesContato; // Value injected by FXMLLoader

    @FXML // fx:id="lblNome"
    private Label lblNome; // Value injected by FXMLLoader

    @FXML // fx:id="lblTeleone"
    private Label lblTeleone; // Value injected by FXMLLoader

    @FXML // fx:id="lblNomeValor"
    private Label lblNomeValor; // Value injected by FXMLLoader

    @FXML // fx:id="lblTelefoneValor"
    private Label lblTelefoneValor; // Value injected by FXMLLoader

    @FXML // fx:id="barBotoes"
    private ButtonBar barBotoes; // Value injected by FXMLLoader

    @FXML // fx:id="btnIncluir"
    private Button btnIncluir; // Value injected by FXMLLoader

    @FXML // fx:id="tlpIncluir"
    private Tooltip tlpIncluir; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditar"
    private Button btnEditar; // Value injected by FXMLLoader

    @FXML // fx:id="tlpEditar"
    private Tooltip tlpEditar; // Value injected by FXMLLoader

    @FXML // fx:id="btnExcluir"
    private Button btnExcluir; // Value injected by FXMLLoader

    @FXML // fx:id="tlpExcluir"
    private Tooltip tlpExcluir; // Value injected by FXMLLoader
    
	private List<Cliente> listaClientes;
	private ObservableList<Cliente> observableListaClientes = FXCollections.observableArrayList();
	private ClienteDAO clienteDAO;
	
	public static final String CLIENTE_EDITAR = " - Editar";
	public static final String CLIENTE_INCLUIR = " - Incluir";

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ObservableList<Cliente> getObservableListaClientes() {
		return observableListaClientes;
	}

	public void setObservableListaClientes(ObservableList<Cliente> observableListaClientes) {
		this.observableListaClientes = observableListaClientes;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void initialize(URL location, ResourceBundle resources) {
		this.setClienteDAO(new ClienteDAO());
		this.carregarTableViewClientes();
		this.selecionarItemTableViewClientes(null);

		this.tbvCadastro.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
	}

	public void carregarTableViewClientes() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));

		this.setListaClientes(this.getClienteDAO().getAll());
		this.setObservableListaClientes(FXCollections.observableArrayList(this.getListaClientes()));
		this.tbvCadastro.setItems(this.getObservableListaClientes());
	}

	public void selecionarItemTableViewClientes(Cliente cliente) {
		if (cliente != null) {
			this.lblNomeValor.setText(cliente.getNome());
			this.lblTelefoneValor.setText(cliente.getTelefone());
		} else {
			this.lblNomeValor.setText("");
			this.lblTelefoneValor.setText("");
		}
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de clientes?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public boolean onShowTelaClienteEditar(Cliente cliente, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/ClienteEdit.fxml"));
			Parent clienteEditXML = loader.load();

			// criando uma janela nova
			Stage janelaClienteEditar = new Stage();
			janelaClienteEditar.setTitle("Cadastro de cliente" + operacao);
			janelaClienteEditar.initModality(Modality.APPLICATION_MODAL);
			janelaClienteEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene clienteEditLayout = new Scene(clienteEditXML);
			janelaClienteEditar.setScene(clienteEditLayout);

			// carregando o controller e a scene
			ClienteEditController clienteEditController = loader.getController();
			clienteEditController.setJanelaClienteEdit(janelaClienteEditar);
			clienteEditController.populaTela(cliente);

			// mostrando a tela
			janelaClienteEditar.showAndWait();

			return clienteEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Cliente cliente = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (cliente != null) {
			boolean btnConfirmarClic = this.onShowTelaClienteEditar(cliente, ClienteListaController.CLIENTE_EDITAR);

			if (btnConfirmarClic) {
				this.getClienteDAO().update(cliente, null);
				this.carregarTableViewClientes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha um cliente na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Cliente cliente = this.tbvCadastro.getSelectionModel().getSelectedItem();
		if (cliente != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do cliente?\n" + cliente.getNome());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getClienteDAO().delete(cliente);
				this.carregarTableViewClientes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha um cliente na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Cliente cliente = new Cliente();

		boolean btnConfirmarClic = this.onShowTelaClienteEditar(cliente, ClienteListaController.CLIENTE_INCLUIR);

		if (btnConfirmarClic) {
			this.getClienteDAO().save(cliente);
			this.carregarTableViewClientes();
		}
	}

	public List<Cliente> retornaListagemCliente() {
		if (this.getClienteDAO() == null) {
			this.setClienteDAO(new ClienteDAO());
		}
		return this.getClienteDAO().getAll();
	}
}
