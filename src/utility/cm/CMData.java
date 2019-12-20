package utility.cm;

import javax.swing.*;

public class CMData<T> {
    private JComponent component;
    private CMStore<T> store;

    CMData(JComponent cComponent, CMStore<T> cStore) {
        component = cComponent;
        store = cStore;
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

    public CMStore<T> getStore() {
        return store;
    }

    public void setStore(CMStore<T> cStore) {
        store = cStore;
    }
}
