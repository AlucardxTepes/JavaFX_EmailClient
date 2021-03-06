package com.alucard.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Alucard on 02-Jun-17.
 */
public class SampleData {

  public static final ObservableList<EmailMessageBean> inbox = FXCollections.observableArrayList(
            new EmailMessageBean("I will wake up in year 2666", "alucard@castlevania.com",
                    23950000,
                    "<html>Please be informed, <br/> I, Alucard Tepes, will remain dormant for awhile." +
                            " If you need my assistance, kindly wait 500 years.</html>",
                    false),
            new EmailMessageBean("Package delivered", "info@fedex.com", 128000,
                    "<html>Dear user, <br/>Your package containing a LENOVO THINKPAD LAPTOP has been delivered</html>",
                    true),
            new EmailMessageBean("SORIAHH", "Roy@pharae.com", 16000,
                    "This the Marquis of Pharae, <br/> Be advised, I will be leading the remains of the Lycian Alliance Army to Ostia.",
                    false),
            new EmailMessageBean("I Fight for my Friends", "Ike@greil.com", 85000,
                    "You'll get no sympathy from me.",
                    false),
            new EmailMessageBean("Check your G-Difusser", "Fox@corneria.com", 240000,
                    "Corneria is the fourth planet of the Lylat System. The evil Andross turned this once " +
                            "thriving system into a wasteland on near-extinction.",
                    false)
    );
}
