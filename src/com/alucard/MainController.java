package com.alucard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

/**
 * Created by Alucard on 26-May-17.
 */
public class MainController implements Initializable {

  @FXML
  private TableView<EmailMessageBean> emailTableView;
  @FXML
  private TableColumn<EmailMessageBean, String> subjectCol;
  @FXML
  private TableColumn<EmailMessageBean, String> senderCol;
  @FXML
  private TableColumn<EmailMessageBean, Integer> sizeCol;
  @FXML
  private Button Button1;
  @FXML
  private WebView messageRenderer;

  @FXML
  void Button1Action(ActionEvent event) {
    System.out.println("Button1 clicked");
  }

  final ObservableList<EmailMessageBean> data = FXCollections.observableArrayList(
          new EmailMessageBean("I will wake up in year 2666", "alucard@castlevania.com", 23500),
          new EmailMessageBean("Package delivered", "info@fedex.com", 128000 ),
          new EmailMessageBean("SORIAHH", "Roy@pharae.com", 16000),
          new EmailMessageBean("I Fight for my Friends", "Ike@greil.com", 85000),
          new EmailMessageBean("Check your G-Difusser", "Fox@corneria.com", 240000)
  );

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Document loaded");
    messageRenderer.getEngine().loadContent("<html>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</html>");

    // Column factories
    subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
    senderCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
    sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

    emailTableView.setItems(data);
  }
}
