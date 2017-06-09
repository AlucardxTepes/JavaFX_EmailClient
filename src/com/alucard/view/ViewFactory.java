package com.alucard.view;

import com.alucard.controller.AbstractController;
import com.alucard.controller.EmailDetailsController;
import com.alucard.controller.MainController;
import com.alucard.model.ModelAccess;

import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewFactory {

  public static ViewFactory defaultFactory = new ViewFactory();

  private static final String MAIN_LAYOUT_FXML = "MainLayout.fxml";
  private static final String EMAIL_DETAIL_FXML = "EmailDetailsLayout.fxml";
  private final String DEFAULT_CSS = "style.css";

  private ModelAccess modelAccess = new ModelAccess();
  private MainController mainController;
  private EmailDetailsController emailDetailsController;
  private boolean mainViewInitialized;

  public Scene getMainScene() throws OperationNotSupportedException {
    if(!mainViewInitialized) {
      mainViewInitialized = true;
      mainController = new MainController(modelAccess);
      return initializeScene(MAIN_LAYOUT_FXML, mainController);
    } else {
      throw new OperationNotSupportedException("Main View already initialized");
    }
  }

  public Scene getEmailDetailsScene(){
    emailDetailsController = new EmailDetailsController(modelAccess);
    return initializeScene(EMAIL_DETAIL_FXML, emailDetailsController);
  }

  public Node resolveIcon(String treeItemValue){
    String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
    ImageView returnIcon;
    try {
      if(lowerCaseTreeItemValue.contains("inbox")){
        returnIcon= new ImageView(new Image(getClass().getResourceAsStream("images/inbox.png")));
      } else if(lowerCaseTreeItemValue.contains("sent")){
        returnIcon= new ImageView(new Image(getClass().getResourceAsStream("images/sent2.png")));
      } else if(lowerCaseTreeItemValue.contains("spam")){
        returnIcon= new ImageView(new Image(getClass().getResourceAsStream("images/spam.png")));
      } else if(lowerCaseTreeItemValue.contains("@")){
        returnIcon= new ImageView(new Image(getClass().getResourceAsStream("images/email.png")));
      } else{
        returnIcon= new ImageView(new Image(getClass().getResourceAsStream("images/folder.png")));
      }
    } catch (NullPointerException e) {
      System.out.println("Invalid image location");
      e.printStackTrace();
      returnIcon = new ImageView();
    }

    returnIcon.setFitHeight(16);
    returnIcon.setFitWidth(16);

    return returnIcon;
  }

  private Scene initializeScene(String fxmlPath, AbstractController controller) {
    FXMLLoader loader;
    Parent parent = null;
    Scene scene;
    try {
      loader = new FXMLLoader(getClass().getResource(fxmlPath));
      loader.setController(controller);
      parent = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    scene = new Scene(parent);
    scene.getStylesheets().add(getClass().getResource(DEFAULT_CSS).toExternalForm());
    return scene;
  }
}
