package controllers.Activite;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import models.Activite.Activite;

public class CustomListCell extends ListCell<Activite> {

    @Override
    protected void updateItem(Activite item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText("Name: " + item.getName() + "\nDescription: " + item.getDescription() +
                    "\nStart Time: " + item.getStart_time() + "\nEnd Time: " + item.getEnd_time());
        }
    }

    public static Callback<ListView<Activite>, ListCell<Activite>> forListView() {
        return param -> new CustomListCell();
    }
}