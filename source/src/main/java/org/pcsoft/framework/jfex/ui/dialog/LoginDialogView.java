package org.pcsoft.framework.jfex.ui.dialog;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.pcsoft.framework.jfex.util.FXWindowUtils;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginDialogView implements FxmlView<LoginDialogViewModel>, Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @InjectViewModel
    private LoginDialogViewModel viewModel;

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
