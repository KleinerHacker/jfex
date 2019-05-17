package org.pcsoft.framework.jfex.controls.ui.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.pcsoft.framework.jfex.controls.ui.component.HeaderPane;
import org.pcsoft.framework.jfex.mvvm.Fxml;

import java.util.List;
import java.util.ResourceBundle;


public class LoginDialog extends Dialog<LoginDialog.Data> {
    public static final class Data {
        private final String username, password;

        public Data(String username) {
            this.username = username;
            this.password = null;
        }

        private Data(String username, String password) {
            this.password = password;
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }
    }

    private final LoginDialogView controller;

    public LoginDialog(final List<Image> iconList, final Data data) {
        this(iconList, data, (Image)null);
    }

    public LoginDialog(final List<Image> iconList, final Data data, final Image headerImage) {
        this(iconList, data, headerImage, null);
    }

    public LoginDialog(final List<Image> iconList, final Data data, final EventHandler<ActionEvent> onHelpAction) {
        this(iconList, data, null, onHelpAction);
    }

    public LoginDialog(final List<Image> iconList, final Data data, final Image headerImage, final EventHandler<ActionEvent> onHelpAction) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("lan/text");

        setTitle(resourceBundle.getString("dlg.login.title"));
        setResizable(false);
        getDialogPane().setMinWidth(350);
        getDialogPane().setMinHeight(150);
        final HeaderPane headerPane = new HeaderPane(
                resourceBundle.getString("dlg.login.title"),
                resourceBundle.getString("dlg.login.text"),
                headerImage == null ? new Image(getClass().getResourceAsStream("/icons/ic_login48.png")) : headerImage);
        headerPane.setOnHelpAction(onHelpAction);
        getDialogPane().setHeader(headerPane);
        getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        ((Stage) getDialogPane().getScene().getWindow()).getIcons().setAll(iconList);

        final Fxml.FxmlTuple<LoginDialogView, LoginDialogViewModel> viewTuple =
                Fxml.from(LoginDialogView.class).withResources(resourceBundle).load();
        controller = viewTuple.getViewController();
        getDialogPane().setContent(viewTuple.getView());

        final Button btnOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
        btnOk.setId("btnOk");
        btnOk.setText(resourceBundle.getString("dlg.login.btn.login"));
        btnOk.disableProperty().bind(viewTuple.getViewModel().allowLoginProperty().not());
        setResultConverter(param -> {
            if (param != ButtonType.OK)
                return null;

            return new Data(viewTuple.getViewModel().getUsername(), viewTuple.getViewModel().getPassword());
        });

        viewTuple.getViewModel().setUsername(data == null ? null : data.getUsername());
    }
}
