package org.pcsoft.framework.jfex.controls.ui.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.pcsoft.framework.jfex.controls.util.FXWindowUtils;
import org.pcsoft.framework.jfex.mvvm.FxmlView;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginDialogView extends FxmlView<LoginDialogViewModel> {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUsername.textProperty().bindBidirectional(viewModel.usernameProperty());
        txtPassword.textProperty().bindBidirectional(viewModel.passwordProperty());

        FXWindowUtils.handleOnShownWindow(txtUsername, e -> {
            if (txtUsername.getText() == null || txtUsername.getText().isEmpty()) {
                txtUsername.requestFocus();
            } else {
                txtPassword.requestFocus();
            }
        });
    }
}
