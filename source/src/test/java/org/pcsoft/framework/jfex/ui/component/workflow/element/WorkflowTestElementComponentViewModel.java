package org.pcsoft.framework.jfex.ui.component.workflow.element;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.Arrays;


public class WorkflowTestElementComponentViewModel implements ViewModel {
    private final StringProperty name = new SimpleStringProperty(), intNumber = new SimpleStringProperty(), doubleNumber = new SimpleStringProperty(),
            checked = new SimpleStringProperty(), character = new SimpleStringProperty(), blobLength = new SimpleStringProperty(),
            blobHash = new SimpleStringProperty(), enumeration = new SimpleStringProperty();
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getIntNumber() {
        return intNumber.get();
    }

    public StringProperty intNumberProperty() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber.set(intNumber);
    }

    public String getDoubleNumber() {
        return doubleNumber.get();
    }

    public StringProperty doubleNumberProperty() {
        return doubleNumber;
    }

    public void setDoubleNumber(String doubleNumber) {
        this.doubleNumber.set(doubleNumber);
    }

    public String getChecked() {
        return checked.get();
    }

    public StringProperty checkedProperty() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked.set(checked);
    }

    public String getCharacter() {
        return character.get();
    }

    public StringProperty characterProperty() {
        return character;
    }

    public void setCharacter(String character) {
        this.character.set(character);
    }

    public String getBlobLength() {
        return blobLength.get();
    }

    public StringProperty blobLengthProperty() {
        return blobLength;
    }

    public void setBlobLength(String blobLength) {
        this.blobLength.set(blobLength);
    }

    public String getBlobHash() {
        return blobHash.get();
    }

    public StringProperty blobHashProperty() {
        return blobHash;
    }

    public void setBlobHash(String blobHash) {
        this.blobHash.set(blobHash);
    }

    public String getEnumeration() {
        return enumeration.get();
    }

    public StringProperty enumerationProperty() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration.set(enumeration);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    void updateProperties(final WorkflowTestElement element) {
        name.bind(element.nameProperty());
        intNumber.bind(element.intNumberProperty().asString());
        doubleNumber.bind(element.doubleNumberProperty().asString());
        checked.bind(element.checkedProperty().asString());
        character.bind(element.characterProperty().asString());
        blobLength.bind(Bindings.createStringBinding(() -> element.getBlob() == null ? "0" : String.valueOf(element.getBlob().length), element.blobProperty()));
        blobHash.bind(Bindings.createStringBinding(() -> element.getBlob() == null ? "0" : String.valueOf(Arrays.hashCode(element.getBlob())), element.blobProperty()));
        color.bind(element.colorProperty());
        enumeration.bind(element.enumerationProperty().asString());
    }
}
