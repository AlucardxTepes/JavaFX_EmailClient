package com.alucard.model.folder;

import com.alucard.model.EmailMessageBean;
import com.alucard.view.ViewFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 * Created by Alucard on 12-Jun-17.
 */
public class EmailFolderBean<T> extends TreeItem<String> {

  private boolean topElement = false; // is root folder
  private int unreadMessageCount;
  private String name;
  private String fullName; // full folder path (ie: inbox/sent)
  private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

  /**
   * Top element constructor
   * @param value
   */
  public EmailFolderBean(String value) {
    super(value, ViewFactory.defaultFactory.resolveIcon(value));
    this.name = value;
    this.fullName = value;
    data = null;
    topElement = true;
    this.setExpanded(true);
  }

  public EmailFolderBean(String value, String fullName) {
    super(value, ViewFactory.defaultFactory.resolveIcon(value));
    this.name = value;
    this.fullName = fullName;
  }

  private void updateValue() {
    if(unreadMessageCount > 0) {
      this.setValue(name + "(" + unreadMessageCount + ")");
    } else {
      this.setValue(name);
    }
  }

  public void incrementUnreadMessagesCount(int newMessages) {
    unreadMessageCount += newMessages;
    updateValue();
  }

  public void decrementUnreadMessagesCount() {
    unreadMessageCount--;
    updateValue();
  }

  public void addEmail(EmailMessageBean message){
    data.add(message);
    if (message.isRead()) {
      incrementUnreadMessagesCount(1);
    }

  }

  public boolean isTopElement() {
    return topElement;
  }

  public ObservableList<EmailMessageBean> getData() {
    return data;
  }
}
