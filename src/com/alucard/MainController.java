package com.alucard;

import com.alucard.images.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by Alucard on 26-May-17.
 */
public class MainController implements Initializable {

  @FXML private TreeView<String> emailFoldersTreeView;
  @FXML private TableView<EmailMessageBean> emailTableView;
  @FXML private TableColumn<EmailMessageBean, String> subjectCol;
  @FXML private TableColumn<EmailMessageBean, String> senderCol;
  @FXML private TableColumn<EmailMessageBean, String> sizeCol;
  @FXML private Button Button1;
  @FXML private WebView messageRenderer;
  private TreeItem<String> root = new TreeItem<>();
  private SampleData sampleData = new SampleData();
  private MenuItem showDetails = new MenuItem("Show Details");
  private Singleton singleton;

  @FXML
  void Button1Action(ActionEvent event) {
    System.out.println("Button1 clicked");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
//    messageRenderer.getEngine().loadContent("<html>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</html>");
    singleton = Singleton.getInstance();

    // Column factories
    subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
    senderCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
    sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));

    sizeCol.setComparator((o1, o2) -> {
      Integer int1 = EmailMessageBean.formattedValues.get(o1);
      Integer int2 = EmailMessageBean.formattedValues.get(o2);
      return int1.compareTo(int2);
    });

    emailFoldersTreeView.setRoot(root);
    root.setValue("Alucard@JavaFX.com");
    root.setGraphic(resolveIcon(root.getValue()));
    TreeItem<String> inbox = new TreeItem<>("Inbox", resolveIcon("Inbox"));
    TreeItem<String> subItem1 = new TreeItem<>("SubItem1", resolveIcon("Subitem1"));
    TreeItem<String> subItem2 = new TreeItem<>("SubItem2", resolveIcon("Subitem2"));
    inbox.getChildren().addAll(subItem1, subItem2);

    TreeItem<String> sent = new TreeItem<>("Sent", resolveIcon("Sent"));
    TreeItem<String> spam = new TreeItem<>("Spam", resolveIcon("Spam"));
    TreeItem<String> trash = new TreeItem<>("Trash", resolveIcon("Trash"));

    root.getChildren().addAll(inbox,sent,spam,trash);
    root.setExpanded(true);

    // Add context menu to EmailTableView (email list)
    emailTableView.setContextMenu(new ContextMenu(showDetails));
    showDetails.setOnAction(e -> {
      Pane pane = new Pane();
      try {
        pane = FXMLLoader.load(getClass().getResource("EmailDetailsLayout.fxml"));
      } catch (IOException e1) {
        e1.printStackTrace();
      }

      Scene scene = new Scene(pane);
      scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
      Stage stage = new Stage();
      stage.setScene(scene);
      stage.show();
    });


    // Folder Tree click listener
    emailFoldersTreeView.setOnMouseClicked(e -> {
      TreeItem<String> item = emailFoldersTreeView.getSelectionModel().getSelectedItem();
      if(item != null) {
        emailTableView.setItems(sampleData.emailFolders.get(item.getValue()));
      }
    });
    // EmailTableView (Email list item)click listener
    emailTableView.setOnMouseClicked(e -> {
      EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
      if(message!= null) {
        messageRenderer.getEngine().loadContent(message.getContent());
        singleton.setEmailMessageBean(message);
      }
    });
  }

  private Node resolveIcon(String treeItemValue) {
    String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
    ImageView returnIcon;

    if(lowerCaseTreeItemValue.contains("inbox")){
      returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/inbox.png")));
    } else if(lowerCaseTreeItemValue.contains("sent")){
      returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/sent2.png")));
    } else if(lowerCaseTreeItemValue.contains("spam")){
      returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/spam.png")));
    } else if(lowerCaseTreeItemValue.contains("@")){
      returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/email.png")));
    } else {
      returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/folder.png")));
    }
    returnIcon.setFitWidth(16);
    returnIcon.setFitHeight(16);
    return returnIcon;
  }
}
