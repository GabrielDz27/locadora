package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.alexsander.gabriel.gustavo.dao.EmprestimoDAO;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Cliente;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Emprestimo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmprestimoListaController implements Initializable {
	
	@FXML // fx:id="pnlPrincipal"
    private AnchorPane pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDivisao"
    private SplitPane pnlDivisao; // Value injected by FXMLLoader

    @FXML // fx:id="pnlCadastro"
    private AnchorPane pnlCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="tbvCadastro"
    private TableView<Emprestimo> tbvCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="tbcCodigo"
    private TableColumn<Emprestimo, Long> tbcCodigo; // Value injected by FXMLLoader

    @FXML // fx:id="tbcNome"
    private TableColumn<Cliente, String> tbcNome; // Value injected by FXMLLoader

    @FXML // fx:id="pnlLista"
    private AnchorPane pnlLista; // Value injected by FXMLLoader

    @FXML // fx:id="lblDetalhes"
    private Label lblDetalhes; // Value injected by FXMLLoader

    @FXML // fx:id="pnlDetalhesContato"
    private GridPane pnlDetalhesContato; // Value injected by FXMLLoader

    @FXML // fx:id="lblValorEmprestimoValor"
    private Label lblValorEmprestimoValor; // Value injected by FXMLLoader

    @FXML // fx:id="lblCliente"
    private Label lblCliente; // Value injected by FXMLLoader

    @FXML // fx:id="lblDataEmprestimo"
    private Label lblDataEmprestimo; // Value injected by FXMLLoader

    @FXML // fx:id="lblDataEntrega"
    private Label lblDataEntrega; // Value injected by FXMLLoader

    @FXML // fx:id="lblidvalor"
    private Label lblidvalor; // Value injected by FXMLLoader

    @FXML // fx:id="lblValorEmprestimovalor"
    private Label lblValorEmprestimovalor; // Value injected by FXMLLoader

    @FXML // fx:id="lblDataEntregValor"
    private Label lblDataEntregValor; // Value injected by FXMLLoader

    @FXML // fx:id="lblDataEmprestimoValor"
    private Label lblDataEmprestimoValor; // Value injected by FXMLLoader

    @FXML // fx:id="lblObservacaoValor"
    private Label lblObservacaoValor; // Value injected by FXMLLoader

    @FXML // fx:id="lblFilme"
    private Label lblFilme; // Value injected by FXMLLoader

    @FXML // fx:id="lblFileValor"
    private Label lblFilmeValor; // Value injected by FXMLLoader

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
	
    @FXML // fx:id="lblClienteValor"
    private Label lblClienteValor; // Value injected by FXMLLoader
	
	private List<Emprestimo> listaEmprestimos;
	private ObservableList<Emprestimo> observableListaEmprestimo = FXCollections.observableArrayList();
	private EmprestimoDAO emprestimoDAO;

	public static final String EMPRESTIMO_EDITAR = " - Editar";
	public static final String EMPRESTIMO_INCLUIR = " - Incluir";
	
	
	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
		this.listaEmprestimos = listaEmprestimos;
	}

	public ObservableList<Emprestimo> getObservableListaEmprestimo() {
		return observableListaEmprestimo;
	}

	public void setObservableListaEmprestimo(ObservableList<Emprestimo> observableListaEmprestimo) {
		this.observableListaEmprestimo = observableListaEmprestimo;
	}

	public EmprestimoDAO getEmprestimoDAO() {
		return emprestimoDAO;
	}

	public void setEmprestimoDAO(EmprestimoDAO emprestimoDAO) {
		this.emprestimoDAO = emprestimoDAO;
	}

	public static String getEmprestimoEditar() {
		return EMPRESTIMO_EDITAR;
	}

	public static String getEmprestimoIncluir() {
		return EMPRESTIMO_INCLUIR;
	}

	@FXML
	void onClickBtnEditar(ActionEvent event) {
	Emprestimo emprestimo = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (emprestimo != null) {
			boolean btnConfirmarClic = this.onShowTelaEmprestimoEditar(emprestimo, EmprestimoListaController.EMPRESTIMO_EDITAR);

			if (btnConfirmarClic) {
				this.getEmprestimoDAO().update(emprestimo, null);
				this.carregarTableViewEmprestimos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha um empr�stimo na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Emprestimo emprestimo = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (emprestimo != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do emprestimo?\n\n" + "Cliente:" +  emprestimo.getCliente().getNome() + "\nFilme: " + emprestimo.getFilme().getNome() + "\nData do empréstimo: "+ emprestimo.getData_emprestimo() +"\nObservação: " + emprestimo.getObs());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getEmprestimoDAO().delete(emprestimo);
				this.carregarTableViewEmprestimos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha um empr�stimo na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Emprestimo emprestimo = new Emprestimo();
		boolean btnConfirmarClic = this.onShowTelaEmprestimoEditar(emprestimo, EmprestimoListaController.EMPRESTIMO_INCLUIR);

		if (btnConfirmarClic) {
			this.getEmprestimoDAO().save(emprestimo);
			this.carregarTableViewEmprestimos();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setEmprestimoDAO(new EmprestimoDAO());
		this.carregarTableViewEmprestimos();
		this.selecionarItemTableViewEmprestimos(null);

		this.tbvCadastro.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewEmprestimos(newValue));
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de empéstimo?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public void carregarTableViewEmprestimos() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaEmprestimos(this.getEmprestimoDAO().getAll());
		this.setObservableListaEmprestimo(FXCollections.observableArrayList(this.getListaEmprestimos()));
		this.tbvCadastro.setItems(this.getObservableListaEmprestimo());
	}

	public void selecionarItemTableViewEmprestimos(Emprestimo emprestimo) {
		if (emprestimo != null) {
			this.lblDataEmprestimoValor.setText(emprestimo.getDataEmprestimoFormatada());
			this.lblDataEntregValor.setText(emprestimo.getDataEntregaFormatada());
			this.lblObservacaoValor.setText(emprestimo.getObs());
			this.lblFilmeValor.setText(emprestimo.getFilme().getNome());
			this.lblClienteValor.setText(emprestimo.getCliente().getNome());
			this.lblValorEmprestimovalor.setText(String.valueOf(emprestimo.getValorEmprestimo()));
		} else {
			this.lblDataEmprestimoValor.setText("");
			this.lblDataEntregValor.setText("");
			this.lblFilmeValor.setText("");
			this.lblClienteValor.setText("");
			this.lblObservacaoValor.setText("");
			this.lblValorEmprestimovalor.setText("");
		}
	}

	public boolean onShowTelaEmprestimoEditar(Emprestimo emprestimo, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/EmprestimoEdit.fxml"));
			Parent emprestimoEditXML = loader.load();

			// criando uma janela nova
			Stage emprestimoRevistaEditar = new Stage();
			emprestimoRevistaEditar.setTitle("Cadastro de empréstimos" + operacao);
			emprestimoRevistaEditar.initModality(Modality.APPLICATION_MODAL);
			emprestimoRevistaEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene emprestimoEditLayout = new Scene(emprestimoEditXML);
			emprestimoRevistaEditar.setScene(emprestimoEditLayout);

			// carregando o controller e a scene
			EmprestimoEditController emprestimoEditController = loader.getController();
			emprestimoEditController.setJanelaEmprestimoEdit(emprestimoRevistaEditar);
			emprestimoEditController.populaTela(emprestimo);

			// mostrando a tela
			emprestimoRevistaEditar.showAndWait();

			return emprestimoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
