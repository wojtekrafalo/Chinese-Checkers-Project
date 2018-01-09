package client.controller;

import client.model.Model;
import client.model.ServerHandle;
import client.view.FirstWindow;
import client.view.View;
import client.view.*;
import common.model.connection.Command;
import common.model.connection.Instruction;
import common.model.game.Game;
import client.model.LocalSession;
import common.model.game.Marble;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private View theView;
    private Model theModel;
    private LocalSession localSession;
    private ServerHandle serverHandle;

    private String infoMessage = "This is Chinese Checkers Game\n" +
            "Add some instructions";

    public Controller() throws IOException{
        System.out.println("theController created");

        this.theView = new View();
        this.theView.addListener(new MenuListener(), new SelectionListener(), new MouseListListener());
    }


    public void start(Model model) {
        this.theModel = model;
    }

    public void addPlayer(String nick, String color, String ID) {
//        localSession.addPlayer(Converter.parseColor(color));
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

                try {
                    initializeServerHandler(nick);
                } catch (IOException ex) {}

                theModel = new Model(nick, serverHandle);
            }

            if (e.getSource().equals(newGame.getOK())) {                    //CREATE NEW GAME, send it to model
                int numberOfPlayers = 2, numberOfBoots;

                     if (newGame.getRightPanel().getPlayer2().isSelected()) numberOfPlayers = 2;
                else if (newGame.getRightPanel().getPlayer3().isSelected()) numberOfPlayers = 3;
                else if (newGame.getRightPanel().getPlayer4().isSelected()) numberOfPlayers = 4;
                else if (newGame.getRightPanel().getPlayer6().isSelected()) numberOfPlayers = 6;

                try {
                    numberOfBoots = Integer.parseInt(newGame.getRightPanel().getNumberOfBoots());
                    if (numberOfBoots <= numberOfPlayers) {
                        serverHandle.write(new Command(
                                Instruction.CREATE_GAME,
                                newGame.getRightPanel().getNameOfSession(),
                                String.valueOf(numberOfPlayers),
                                String.valueOf(numberOfBoots)
                        ));
                    }
                    else theView.getNewGameWindow().displayErrorMessage();
                } catch (NumberFormatException exe) {
                    theView.getNewGameWindow().displayErrorMessage();
                }
            }

            if (e.getSource().equals(joinGame.getOK())) {                    //JOIN TO NEW GAME, send it to model
                int selected = joinGame.getSelectedNumber();
                String[] data = joinGame.getData();
                String[] sessionData = new String[5];
                for (int i=0; i<5; i++) {
                    sessionData[i] = data[selected*5 + i];
                }
                theView.hideShow2();

                serverHandle.write(new Command(Instruction.SESSION_CHOSEN, sessionData));
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

    public void createGameView() {
        theView.initializeGameWindow(theModel.getLocalSession());
        theView.addGameWindowListener(new MenuGameWindowListener(), new MouseListener(), theModel.getGame());
        theView.hideShow2();
    }

    private void initializeServerHandler(String nick) throws IOException{
        System.out.println("iNItialization of serverHadler");
        serverHandle = new ServerHandle("0.0.0.0", 5001);
        serverHandle.setController(this);
        serverHandle.setModel(theModel);
        serverHandle.send(new Command(Instruction.NICK_INSERTED, nick));
    }

    public void setLocalSession (LocalSession localSession) {
        this.localSession = localSession;
    }

    class SelectionListener implements ListSelectionListener{                   //probably unnecessary

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int first = e.getFirstIndex();
            int last = e.getLastIndex();

            System.out.println(first + " " + last);
        }
    }

    class MouseListListener implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (theView.getJoinGameWindow().getList() != null) {
                int index = theView.getJoinGameWindow().getList().locationToIndex(e.getPoint());
                System.out.println("Clicked on Item " + index);
                theView.getJoinGameWindow().setInfoAboutSession(index);
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


    class MouseListener implements MouseInputListener{
        GamePanel gamePanel = theView.getGameWindow().getGamePanel();
        Marble firstMarble = null;
        Marble secondMarble= null;
        Game game = theModel.getGame();

        @Override
        public void mouseClicked(MouseEvent e) {
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

    public void repaint() {
        theView.getGameWindow().getGamePanel().repaint();
    }

    public void setSessions(ArrayList<String> sessions) {
        theView.getJoinGameWindow().setData(sessions);
    }
}