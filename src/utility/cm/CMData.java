package utility.cm;

import javax.swing.*;

public class CMData<T extends CMStore<?>, O> {
    private JComponent component;
    private T store;
    private O otherData;

    CMData(JComponent cComponent, T cStore) {
        component = cComponent;
        store = cStore;
    }

    CMData(JComponent cComponent, T cStore, O cOtherData) {
        component = cComponent;
        store = cStore;
        otherData = cOtherData;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent cComponent) {
        component = cComponent;
    }

    public boolean is(JComponent cComponent) {
        return component == cComponent;
    }

    public T getStore() {
        return store;
    }

    public void setStore(T cStore) {
        store = cStore;
    }

    public O getOtherData() {
        return otherData;
    }

    public void setOtherData(O cOtherData) {
        otherData = cOtherData;
    }
}
