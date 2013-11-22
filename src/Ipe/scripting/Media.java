package Ipe.scripting;

import javafx.stage.Stage;

import java.awt.*;

public class Media {

    /**
     * Private variables
     */
    private Stage _stage;

    /**
     *
     * @param stage
     */
    public Media(Stage stage) {
        this._stage = stage;
    }

    public void beep() {
        Toolkit.getDefaultToolkit().beep();
    }
}
