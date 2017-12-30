package client.controller;

import client.model.Model;
import client.view.FirstWindow;
import client.view.View;
import client.view.*;
import common.model.game.Game;
import common.model.game.Marble;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Session;

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
        this.theView.addListener(new MenuListener(), new SelectionListener());
    }
    public Controller() {
        this.theView = new View();
        this.theView.addListener(new MenuListener(), new SelectionListener());
    }

    class MenuGameWindowListener implements ActionListener {
        GameWindow gameWindow = theView.getGameWindow();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(gameWindow.getMenuInfo())) {
                gameWindow.displayInfo();
            }
        }
    }

    class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nick = "";
            FirstWindow first = theView.getFirstWindow();
            NewGameWindow newGame = theView.getNewGameWindow();
            JoinGameWindow joinGame = theView.getJoinGameWindow();

            if (e.getSource().equals(first.getOK())) {
                nick = first.getNickName();
                System.out.println("\n"+nick);
                theView.hideShow1();
                theModel = new Model(nick);                               //also adding specific player, not only sending a String nick
            }

            if (e.getSource().equals(newGame.getOK())) {                    //CREATE NEW GAME, send it to model
                int numberOfPlayers = 2, numberOfBoots = 0;
                if (newGame.getRightPanel().getPlayer2().isSelected()) numberOfPlayers = 2;
                else if (newGame.getRightPanel().getPlayer3().isSelected()) numberOfPlayers = 3;
                else if (newGame.getRightPanel().getPlayer4().isSelected()) numberOfPlayers = 4;
                else if (newGame.getRightPanel().getPlayer6().isSelected()) numberOfPlayers = 6;
                try {
                    numberOfBoots = Integer.parseInt(newGame.getRightPanel().getNumberOfBoots());
                    if (numberOfBoots <= numberOfPlayers) {
                        theModel.createNewGame(numberOfPlayers, numberOfBoots);
                        theView.initializeGameWindow(theModel.getGame());
                        theView.addGameWindowListener(new MenuGameWindowListener(), new MouseListener(), theModel.getGame());
                        theView.hideShow2();
                    }
                    else theView.getNewGameWindow().displayErrorMessage();
                } catch (NumberFormatException exe) {
                    theView.getNewGameWindow().displayErrorMessage();
                }
            }
            if (e.getSource().equals(joinGame.getOK())) {                    //JOIN TO NEW GAME, send it to model
                Session chosen = joinGame.getSelectedGame();
                theView.hideShow2();
                theModel.joinToGame(chosen);
            }

            if (e.getSource().equals(newGame.getMenuJoin()) || e.getSource().equals(joinGame.getMenuNew())) {
                theView.hideShowChange();
            }
            if (e.getSource().equals(newGame.getMenuInfo())) {
                newGame.displayInfo();
            }
            if (e.getSource().equals(joinGame.getMenuInfo())) {
                joinGame.displayInfo();
            }
        }
    }

    class SelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int first = e.getFirstIndex();
            int last = e.getLastIndex();

            System.out.println(first + " " + last);
        }
    }

    class MouseListener implements MouseInputListener{
        GamePanel gamePanel = theView.getGameWindow().getGamePanel();
        Marble firstMarble = null;
        Marble secondMarble= null;
        Game game = theModel.getGame();

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (e.getButton() == MouseEvent.BUTTON1) {              //check if you Clicked at some Shape
                if (firstMarble == null) {
                    for (int i=0; i<game.getBoard().length; i++) {
                        for (int j=0; j<game.getBoard().length; j++) {
                            Marble marble = game.getBoard()[i][j];
                            if (GamePanel.contains(marble, e.getX(), e.getY()) && marble.getColor() != common.model.game.Color.NONE) firstMarble = marble;
                        }
                    }
                } else {
                    for (int i=0; i<game.getBoard().length; i++) {
                        for (int j=0; j<game.getBoard().length; j++) {
                            Marble marble = game.getBoard()[i][j];
                            if (GamePanel.contains(marble, e.getX(), e.getY())) secondMarble = marble;
                        }
                    }
                    if (firstMarble != null && secondMarble != null) game.makeMove(firstMarble.getX(), firstMarble.getY(), secondMarble.getX(), secondMarble.getY(), firstMarble.getColor());

                    gamePanel.repaint();
                    firstMarble = null;
                    secondMarble= null;
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                firstMarble = null;
                secondMarble = null;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
