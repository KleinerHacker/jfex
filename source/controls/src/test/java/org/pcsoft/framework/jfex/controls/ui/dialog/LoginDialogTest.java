package org.pcsoft.framework.jfex.controls.ui.dialog;

import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.pcsoft.framework.jftex.ExtendedApplicationTest;

import java.util.ArrayList;


public class LoginDialogTest extends ExtendedApplicationTest {

    private LoginDialog loginDialog;

    @Override
    public void start(Stage stage) throws Exception {
        loginDialog = new LoginDialog(new ArrayList<>(), null);
        loginDialog.setOnShowing(e -> ((Dialog)e.getSource()).getDialogPane().getScene().getWindow().setOpacity(0));
        loginDialog.show();
    }

    @Test
    public void test() {
        typeFX("#txtUsername", "user");
        typeFX("#txtPassword", "12345");
        clickOnFX("#btnOk");

        Assert.assertNotNull(loginDialog.getResult());
        Assert.assertEquals("user", loginDialog.getResult().getUsername());
        Assert.assertEquals("12345", loginDialog.getResult().getPassword());
    }
}