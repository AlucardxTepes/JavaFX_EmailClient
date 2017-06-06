package com.alucard;

import com.alucard.images.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

/**
 * Created by Alucard on 06-Jun-17.
 */
public class EmailDetailsController implements Initializable {

  private Singleton singleton;
  private EmailMessageBean emailMessageBean;

  @FXML
  private WebView webView;

  @FXML
  private Label subjectLabel;

  @FXML
  private Label senderLabel;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("EmailDetailsController initialized");
    singleton = Singleton.getInstance();
    emailMessageBean = singleton.getEmailMessageBean();
    subjectLabel.setText("Subject: " + emailMessageBean.getSubject());
    senderLabel.setText("Sender: " + emailMessageBean.getSender());
    webView.getEngine().loadContent(emailMessageBean.getContent());
  }
}