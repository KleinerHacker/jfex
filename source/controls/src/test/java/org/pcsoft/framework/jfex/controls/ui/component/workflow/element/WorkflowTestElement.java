package org.pcsoft.framework.jfex.controls.ui.component.workflow.element;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.MyEnum;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.BasicWorkflowElement;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowElementInfo;
import org.pcsoft.framework.jfex.controls.ui.component.workflow.type.WorkflowProperty;


@WorkflowElementInfo(name = "Test Element", group = "Test", description = "Test Element for testing framework",
        defaultExpandedPropertyGroup = WorkflowTestElement.PROP_GROUP_PRIMITIVES,
        smallIconUrl = "/icons/ic_test16.png", bigIconUrl = "/icons/ic_test48.png")
public class WorkflowTestElement extends BasicWorkflowElement<WorkflowTestElement> {
    static final String PROP_GROUP_PRIMITIVES = "Primitives";
    private static final String PROP_GROUP_EXTENSIONS = "Extensions";

    private static final String PROP_NAME = "Name";
    private static final String PROP_INT = "Integer Number";
    private static final String PROP_DOUBLE = "Double Number";
    private static final String PROP_CHECKED = "Checked";
    private static final String PROP_CHARACTER = "Character";
    private static final String PROP_BLOB = "Binary BLOB";
    private static final String PROP_COLOR = "Color";
    private static final String PROP_ENUM = "Enumeration";

    private final StringProperty name = new SimpleStringProperty("Any Name");
    private final IntegerProperty intNumber = new SimpleIntegerProperty(100);
    private final DoubleProperty doubleNumber = new SimpleDoubleProperty(10.13d);
    private final BooleanProperty checked = new SimpleBooleanProperty(false);
    private final ObjectProperty<Character> character = new SimpleObjectProperty<>('c');

    private final ObjectProperty<byte[]> blob = new SimpleObjectProperty<>(new byte[0]);
    private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    private final ObjectProperty<MyEnum> enumeration = new SimpleObjectProperty<>(MyEnum.One);

    public WorkflowTestElement(double x, double y) {
        super(x, y);
        getElementPropertyMap().put(PROP_NAME, new WorkflowProperty<>(PROP_NAME, PROP_GROUP_PRIMITIVES, name));
        getElementPropertyMap().put(PROP_INT, new WorkflowProperty<>(PROP_INT, PROP_GROUP_PRIMITIVES, intNumber));
        getElementPropertyMap().put(PROP_DOUBLE, new WorkflowProperty<>(PROP_DOUBLE, PROP_GROUP_PRIMITIVES, doubleNumber));
        getElementPropertyMap().put(PROP_CHECKED, new WorkflowProperty<>(PROP_CHECKED, PROP_GROUP_PRIMITIVES, checked));
        getElementPropertyMap().put(PROP_CHARACTER, new WorkflowProperty<>(PROP_CHARACTER, PROP_GROUP_PRIMITIVES, character));
        getElementPropertyMap().put(PROP_BLOB, new WorkflowProperty<>(PROP_BLOB, PROP_GROUP_EXTENSIONS, blob));
        getElementPropertyMap().put(PROP_COLOR, new WorkflowProperty<>(PROP_COLOR, PROP_GROUP_EXTENSIONS, color));
        getElementPropertyMap().put(PROP_ENUM, new WorkflowProperty<>(PROP_ENUM, PROP_GROUP_EXTENSIONS, enumeration));
    }

    public WorkflowTestElement() {
    }

    private WorkflowTestElement(WorkflowTestElement element) {
        super(element);
        this.name.set(element.getName());
        this.intNumber.set(element.getIntNumber());
        this.doubleNumber.set(element.getDoubleNumber());
        this.checked.set(element.getChecked());
        this.character.set(element.getCharacter());
        this.blob.set(element.getBlob());
        this.color.set(element.getColor());
        this.enumeration.set(element.getEnumeration());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getIntNumber() {
        return intNumber.get();
    }

    public IntegerProperty intNumberProperty() {
        return intNumber;
    }

    public void setIntNumber(int intNumber) {
        this.intNumber.set(intNumber);
    }

    public double getDoubleNumber() {
        return doubleNumber.get();
    }

    public DoubleProperty doubleNumberProperty() {
        return doubleNumber;
    }

    public void setDoubleNumber(double doubleNumber) {
        this.doubleNumber.set(doubleNumber);
    }

    public boolean getChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public Character getCharacter() {
        return character.get();
    }

    public ObjectProperty<Character> characterProperty() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character.set(character);
    }

    public byte[] getBlob() {
        return blob.get();
    }

    public ObjectProperty<byte[]> blobProperty() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob.set(blob);
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

    public MyEnum getEnumeration() {
        return enumeration.get();
    }

    public ObjectProperty<MyEnum> enumerationProperty() {
        return enumeration;
    }

    public void setEnumeration(MyEnum enumeration) {
        this.enumeration.set(enumeration);
    }

    @Override
    public WorkflowTestElement copy() {
        return new WorkflowTestElement(this);
    }
}
