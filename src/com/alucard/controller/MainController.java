package com.alucard.controller;

import com.alucard.BoldableRowFactory;
import com.alucard.model.EmailMessageBean;
import com.alucard.model.ModelAccess;
import com.alucard.model.SampleData;
import com.alucard.model.folder.EmailFolderBean;
import com.alucard.view.ViewFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController extends AbstractController implements Initializable{

  @FXML
  private TreeView<String> emailFoldersTreeView;
  private TreeItem<String> root  = new TreeItem<String>();
  private SampleData sampleData = new SampleData();
  private MenuItem showDetails = new MenuItem("show details");

  @FXML
  private TableView<EmailMessageBean> emailTableView;

  @FXML
  private TableColumn<EmailMessageBean, String> subjectCol;

  @FXML
  private TableColumn<EmailMessageBean, String> senderCol;

  @FXML
  private TableColumn<EmailMessageBean, String> sizeCol;

  @FXML
  private WebView messageRenderer;

  @FXML
  private Button Button1;

  public MainController(ModelAccess modelAccess) {
    super(modelAccess);
  }

  @FXML
  void Button1Action(ActionEvent event) {
    System.out.println("button1 clicked!!");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    emailTableView.setRowFactory(param -> new BoldableRowFactory<>());
    ViewFactory viewfactory = ViewFactory.defaultFactory;
    subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("subject"));
    senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("sender"));
    sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("size"));
    sizeCol.setComparator(new Comparator<String>() {
      Integer int1, int2;
      @Override
      public int compare(String o1, String o2) {
        int1 = EmailMessageBean.formattedValues.get(o1);
        int2 = EmailMessageBean.formattedValues.get(o2);
        return int1.compareTo(int2);
      }
    });


    EmailFolderBean<String> root = new EmailFolderBean<>("");
    emailFoldersTreeView.setRoot(root);
    emailFoldersTreeView.setShowRoot(false);

    EmailFolderBean<String> alucard = new EmailFolderBean<String>("Alucard@JavaFX.com");
    root.getChildren().add(alucard);
    EmailFolderBean<String> inbox = new EmailFolderBean<>("Inbox", "InboxComplete");
    EmailFolderBean<String> sent = new EmailFolderBean<>("Sent", "SentComplete");
    sent.getChildren().add(new EmailFolderBean<>("Subfolder1", "Subfolder1Complete"));
    sent.getChildren().add(new EmailFolderBean<>("Subfolder2", "Subfolder2Complete"));
    EmailFolderBean<String> spam = new EmailFolderBean<>("Spam", "SpamComplete");

    alucard.getChildren().addAll(inbox,sent,spam);

    inbox.getData().addAll(SampleData.inbox);

    emailTableView.setContextMenu(new ContextMenu(showDetails));

    emailFoldersTreeView.setOnMouseClicked(e ->{
      EmailFolderBean<String> item = (EmailFolderBean<String>) emailFoldersTreeView.getSelectionModel().getSelectedItem();
      if(item != null && !item.isTopElement()){
        emailTableView.setItems(item.getData());
        getModelAccess().setSelectedFolder(item);
        // clear the selected message when changing folders
        getModelAccess().setSelectedMessage(null);
      }
    });
    emailTableView.setOnMouseClicked(e->{
      EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
      if(message != null){
        messageRenderer.getEngine().loadContent(message.getContent());
        getModelAccess().setSelectedMessage(message);
      }
    });
    showDetails.setOnAction(e->{
      Scene scene = viewfactory.getEmailDetailsScene();
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.show();
    });


  }


}
