package client.controller;
import client.model.Model;
import client.view.FirstWindow;
import client.view.View;
import client.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    public AnchorPane anchorPane1;
    @FXML
    private TextField nickName;
    private View theView;
    private Model theModel;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addListener(new MenuListener());
    }

    @FXML
    public void nickNameConfirmed(ActionEvent actionEvent) {
        System.out.println(nickName.getText());
        theView.hideShow1();
    }

    class MenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String nick = "";
            FirstWindow first = theView.getFirstWindow();
            MainWindow menu = theView.getMainWindow();

            if (e.getSource().equals(first.getOK())) {
                nick = first.getNickName();
                System.out.println("\n"+nick);
                first.setVisible(false);
                menu.setVisible(true);

            }
            theModel.addPlayer(nick);
//                theView.setCalcSolution(theModel.getCalculationValue());
        }
    }
}
