package org.pcsoft.framework.jfex.dialog;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class LoginDialogViewModel implements ViewModel {
    private final StringProperty username = new SimpleStringProperty(), password = new SimpleStringProperty();
    private final BooleanBinding allowLogin;

    public LoginDialogViewModel() {
        allowLogin = username.isNotNull().and(username.isNotEmpty()).and(password.isNotNull()).and(password.isNotEmpty());
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public Boolean getAllowLogin() {
        return allowLogin.get();
    }

    public BooleanBinding allowLoginProperty() {
        return allowLogin;
    }
}
