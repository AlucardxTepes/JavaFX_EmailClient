package com.alucard.model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Alucard on 26-May-17.
 */
public class EmailMessageBean {

  public static Map<String, Integer> formattedValues = new HashMap<>();

  private SimpleStringProperty sender;
  private SimpleStringProperty subject;
  private SimpleStringProperty size;
  private String content;

  public EmailMessageBean(String subject, String sender, int size, String content) {
    this.sender = new SimpleStringProperty(sender);
    this.subject = new SimpleStringProperty(subject);
    this.size = new SimpleStringProperty(formatSize(size));
    this.content = content;
  }

  private String formatSize(int size) {
    String returnValue;
    if(size <= 0) {
      returnValue = "0";
    } else if(size < 1024) {
      returnValue = size + " B";
    } else if(size < 1048576) {
      returnValue = size/1024 + " kB";
    } else {
      returnValue = size/1048576 + " MB";
    }
    formattedValues.put(returnValue,size);
    return returnValue;
  }

  public String getSender() {
    return sender.get();
  }

  public String getSubject() {
    return subject.get();
  }

  public String getSize() {
    return size.get();
  }

  public String getContent() {
    return content;
  }
}