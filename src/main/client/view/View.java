package client.view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class View {
    private FirstWindow firstWindow = new FirstWindow();
    private MainWindow mainWindow   = new MainWindow();

    public View() {
//        super("ViewWindow");
        firstWindow.setVisible(true);
    }

    public void addListener(ActionListener listener){
        firstWindow.addListener(listener);
        mainWindow.addListener(listener);
    }

    public FirstWindow getFirstWindow () {
        return firstWindow;
    }

    public MainWindow getMainWindow () {
        return mainWindow;
    }


//    public String getnickName(){
//        return firstWindow.getnickName();
//    }
}