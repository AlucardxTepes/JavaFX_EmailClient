package com.alucard.model;

import com.alucard.model.folder.EmailFolderBean;

/**
 * Created by Alucard on 07-Jun-17.
 */
public class ModelAccess {

  private EmailFolderBean<String> selectedFolder;

  private EmailMessageBean selectedMessage;

  public EmailMessageBean getSelectedMessage() {
    return selectedMessage;
  }

  public void setSelectedMessage(EmailMessageBean selectedMessage) {
    this.selectedMessage = selectedMessage;
  }

  public EmailFolderBean<String> getSelectedFolder() {
    return selectedFolder;
  }

  public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
    this.selectedFolder = selectedFolder;
  }
}
