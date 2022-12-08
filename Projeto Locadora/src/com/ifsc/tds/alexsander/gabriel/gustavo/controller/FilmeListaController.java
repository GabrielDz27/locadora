 package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.net.URL;


import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.alexsander.gabriel.gustavo.dao.FilmeDAO;
import com.ifsc.tds.alexsander.gabriel.gustavo.entity.Filme;

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

public class FilmeListaController implements Initializable {

    @FXML
    private AnchorPane pnlPrincipal;

    @FXML
    private SplitPane pnlDivisao;

    @FXML
    private AnchorPane pnlCadastro;

    @FXML
    private TableView<Filme> tbvCadastro;

    @FXML
    private TableColumn<Filme, Long> tbcCodigo;

    @FXML
    private TableColumn<Filme, String> tbcNome;

    @FXML
    private AnchorPane pnlLista;

    @FXML
    private Label lblDetalhes;

    @FXML
    private GridPane pnlDetalhesContato;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblAno;

    @FXML
    private Label lblNomeValor;

    @FXML
    private Label lblAnoValor;

    @FXML
    private ButtonBar barBotoes;

    @FXML
    private Button btnIncluir;

    @FXML
    private Tooltip tlpIncluir;

    @FXML
    private Button btnEditar;

    @FXML
    private Tooltip tlpEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Tooltip tlpExcluir;
    private List<Filme> listaFilme;
	private ObservableList<Filme> observableListaFlme = FXCollections.observableArrayList();
	private FilmeDAO filmeDAO;

	public static final String FILME_EDITAR = " - Editar";
	public static final String FILME_INCLUIR = " - Incluir";


    @FXML
    void onClickBtnEditar(ActionEvent event) {
    	Filme filme = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (filme != null) {
			boolean btnConfirmarClic = this.onShowTelaFilmeEditar(filme, FilmeListaController.FILME_EDITAR);

			if (btnConfirmarClic) {
				this.getFilmeDAO().update(filme, null);
				this.carregarTableViewFilme();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha um filme na tabela!");
			alerta.show();
		}
    }

    @FXML
    void onClickBtnExcluir(ActionEvent event) {
    	Filme filme = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (filme != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do filme?\n" + filme.getNome());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getFilmeDAO().delete(filme);
				this.carregarTableViewFilme();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma filme na tabela!");
			alerta.show();
		}
    }

    @FXML
    void onClickBtnIncluir(ActionEvent event) {
    	Filme filme = new Filme();

		boolean btnConfirmarClic = this.onShowTelaFilmeEditar(filme, FilmeListaController.FILME_INCLUIR);

		if (btnConfirmarClic) {
			this.getFilmeDAO().save(filme);
			this.carregarTableViewFilme();
		}
    }
    public void initialize(URL arg0, ResourceBundle arg1) {
		this.setFilmeDAO(new FilmeDAO());
		this.carregarTableViewFilme();
		this.selecionarItemTableViewFilme(null);

		this.tbvCadastro.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewFilme(newValue));
	}
    public FilmeDAO getFilmeDAO() {
		return filmeDAO;
	}

	public void setFilmeDAO(FilmeDAO FilmeDAO) {
		this.filmeDAO = FilmeDAO;
	}

	public List<Filme> getListaFilmes() {
		return listaFilme;
	}

	public void setListaFilme(List<Filme> listaFilme) {
		this.listaFilme = listaFilme;
	}

	public ObservableList<Filme> getObservableListaFilme() {
		return observableListaFlme;
	}

	public void setObservableListaFilme(ObservableList<Filme> observableListaFilme) {
		this.observableListaFlme = observableListaFilme;
	}
	
	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de filmes?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}
	
	public void carregarTableViewFilme() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaFilme(this.getFilmeDAO().getAll());
		this.setObservableListaFilme(FXCollections.observableArrayList(this.getListaFilmes()));
		this.tbvCadastro.setItems(this.getObservableListaFilme());
	}

	public void selecionarItemTableViewFilme(Filme filme) {
		if ( filme != null) {
			this.lblNomeValor.setText( filme.getNome());
			this.lblAnoValor.setText(filme.getAnoFormatado());
			
		} else {
			this.lblNomeValor.setText("");
			this.lblAnoValor.setText("");
			
		}
	}

	public boolean onShowTelaFilmeEditar(Filme filme, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/FilmeEdit.fxml"));
			Parent filmeEditXML = loader.load();

			// criando uma janela nova
			Stage janelFilmeEditar = new Stage();
			janelFilmeEditar.setTitle("Cadastro de filme" + operacao);
			janelFilmeEditar.initModality(Modality.APPLICATION_MODAL);
			janelFilmeEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene filmeEditLayout = new Scene(filmeEditXML);
			janelFilmeEditar.setScene(filmeEditLayout);

			// carregando o controller e a scene
			FilmeEditController filmeEditController = loader.getController();
			filmeEditController.setJanelaFilmeEdit(janelFilmeEditar);
			filmeEditController.populaTela(filme);

			// mostrando a tela
			janelFilmeEditar.showAndWait();

			return filmeEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Filme> retornaListagemFilme() {
		if (this.getFilmeDAO() == null) {
			this.setFilmeDAO(new FilmeDAO());
		}
		return this.getFilmeDAO().getAll();
	}

}

