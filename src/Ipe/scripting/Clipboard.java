package Ipe.scripting;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Clipboard {

    private java.awt.datatransfer.Clipboard c;

    public Clipboard() {
        c = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void setText(String text) {
        StringSelection selection = new StringSelection(text);
        c.setContents(selection, selection);
    }

    public Object getText() {
        try {
            return c.getContents(null).getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
