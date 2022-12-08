/**
 * Sample Skeleton for 'Menu.fxml' Controller Class
 */

package com.ifsc.tds.alexsander.gabriel.gustavo.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML // fx:id="pnlPrincipal"
    private VBox pnlPrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="barMenu"
    private MenuBar barMenu; // Value injected by FXMLLoader

    @FXML // fx:id="mnuCadastro"
    private Menu mnuCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="mnoItem"
    private MenuItem mnoItem; // Value injected by FXMLLoader

    @FXML // fx:id="mnoFilmes"
    private MenuItem mnoFilmes; // Value injected by FXMLLoader

    @FXML // fx:id="mnoEmpre"
    private MenuItem mnoEmpre; // Value injected by FXMLLoader

    @FXML // fx:id="sepCadastro"
    private SeparatorMenuItem sepCadastro; // Value injected by FXMLLoader

    @FXML // fx:id="mnoSair"
    private MenuItem mnoSair; // Value injected by FXMLLoader

    @FXML // fx:id="mnuAjuda"
    private Menu mnuAjuda; // Value injected by FXMLLoader

    @FXML // fx:id="mnoSobre"
    private MenuItem mnoSobre; // Value injected by FXMLLoader

    @FXML // fx:id="pnlMeio"
    private AnchorPane pnlMeio; // Value injected by FXMLLoader
    
    private Stage stage;

    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
    void onClickMnoClientes(ActionEvent event) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/ClienteLista.fxml"));
			Parent clienteListaXML = loader.load();
			
			
			// carregando o controller e a scene
			ClienteListaController clienteListaController = loader.getController();
			Scene clienteListaLayout = new Scene(clienteListaXML);

			this.getStage().setScene(clienteListaLayout);
			this.getStage().setTitle("Cadastro de clientes");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (clienteListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    void onClickMnoEmprestimo(ActionEvent event) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/EmprestimoLista.fxml"));
			Parent emprestimoListaXML = loader.load();

			// carregando o controller e a scene
			EmprestimoListaController emprestimoListaController = loader.getController();
			Scene emprestimoListaLayout = new Scene(emprestimoListaXML);

			this.getStage().setScene(emprestimoListaLayout);
			this.getStage().setTitle("Cadastro de empr�stimos");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (emprestimoListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    void onClickMnoFilmes(ActionEvent event) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/alexsander/gabriel/gustavo/view/FilmesLista.fxml"));
			Parent filmeListaXML = loader.load();

			// carregando o controller e a scene
			FilmeListaController filmesListaController = loader.getController();
			Scene filmesListaLayout = new Scene(filmeListaXML);

			this.getStage().setScene(filmesListaLayout);
			this.getStage().setTitle("Cadastro de filmes");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (filmesListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    void onClickMnoSair(ActionEvent event) {
		if (this.onCloseQuery()) {
			System.exit(0);
		} else {
			event.consume();
		}
    }

    @FXML
    void onClickMnoSobre(ActionEvent event) {
    	Alert sobre = new Alert (Alert.AlertType.INFORMATION);
    	sobre.setTitle("MEMBROS DA EQUIPE");
    	sobre.setHeaderText("O presente trabalho foi desenvolvido por Alexsander H�schler, Gabriel Dziecinny Ferreira e Gustavo Stinghen");
		sobre.showAndWait();
    }

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do sistema?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		Optional<ButtonType> resultado = alerta.showAndWait();
		return resultado.get() == botaoSim ? true : false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.configuraStage();
	}
	
	// configura tela
	public void configuraStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}

}

