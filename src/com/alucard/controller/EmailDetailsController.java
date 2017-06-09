package com.alucard.controller;

import com.alucard.model.EmailMessageBean;
import com.alucard.model.ModelAccess;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

/**
 * Created by Alucard on 06-Jun-17.
 */
public class EmailDetailsController extends AbstractController implements Initializable {

  private EmailMessageBean emailMessageBean;

  @FXML
  private WebView webView;

  @FXML
  private Label subjectLabel;

  @FXML
  private Label senderLabel;

  public EmailDetailsController(ModelAccess modelAccess) {
    super(modelAccess);
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("EmailDetailsController initialized");
    emailMessageBean = getModelAccess().getSelectedMessage();
    subjectLabel.setText("Subject: " + emailMessageBean.getSubject());
    senderLabel.setText("Sender: " + emailMessageBean.getSender());
    webView.getEngine().loadContent(emailMessageBean.getContent());
  }
}