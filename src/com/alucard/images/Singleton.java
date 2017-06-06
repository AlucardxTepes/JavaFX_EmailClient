package com.alucard.images;

import com.alucard.EmailMessageBean;

/**
 * Created by Alucard on 6/6/2017.
 */
public class Singleton {

  private static Singleton instance = new Singleton();
  private EmailMessageBean emailMessageBean;

  private Singleton() {}

  public static Singleton getInstance() {
    return instance;
  }

  public EmailMessageBean getEmailMessageBean() {
    return emailMessageBean;
  }

  public void setEmailMessageBean(EmailMessageBean emailMessageBean) {
    this.emailMessageBean = emailMessageBean;
  }
}
