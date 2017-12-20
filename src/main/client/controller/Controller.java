package client.controller;
import client.model.Model;
import client.view.FirstWindow;
import client.view.View;
import client.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class Controller {
    private View theView;
    private Model theModel;
    private String infoMessage = "This is Chinese Checkers Game\n" +
            "Add some instructions";
    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addListener(new MenuListener(), new SelectionListener(), new MouseListener());
    }

    class MenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String nick = "";
            FirstWindow first = theView.getFirstWindow();
            MainWindow menu = theView.getMainWindow();
            GameWindow game = theView.getGameWindow();

            if (e.getSource().equals(first.getOK())) {
                nick = first.getNickName();
                System.out.println("\n"+nick);
                theView.hideShow1();
            }
            theModel.addPlayer(nick);
//                theView.setCalcSolution(theModel.getCalculationValue());
            if (e.getSource().equals(menu.getRightPanel().getOK())){            //instead of using switch-case construction here, I will create Object of Factory, which would use correct class to handle properties of specific game
                int number=2;
                if (menu.getRightPanel().getPlayer2().isSelected()) number=2;
                if (menu.getRightPanel().getPlayer3().isSelected()) number=3;
                if (menu.getRightPanel().getPlayer4().isSelected()) number=4;
                if (menu.getRightPanel().getPlayer6().isSelected()) number=6;
                theModel.setNumberOfPlayers(number);
                System.out.println(number + "PLAYERS");

                theView.hideShow2();
            }

            if (e.getSource().equals(game.getMenuInfo())) {
                displayInfo();
            }
        }
    }

    class SelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int first = e.getFirstIndex();
            int last = e.getLastIndex();

//            System.out.println(e.getSource());
            System.out.println(first + " " + last);
//            System.out.println(theView.getMainWindow().getData()[last]);
        }
    }

    class MouseListener implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    private void displayInfo(){
        JOptionPane.showMessageDialog(theView.getGameWindow(), infoMessage);
    }
}
