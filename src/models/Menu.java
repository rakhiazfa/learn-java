package models;

public class Menu {

    public interface SelectedCallback {
        void onSelected();
    }

    private final String label;

    private final SelectedCallback callback;

    public Menu(String label, SelectedCallback callback) {
        this.label = label;
        this.callback = callback;
    }

    public void select() {
        callback.onSelected();
    }

    public String getLabel() {
        return label;
    }
}
