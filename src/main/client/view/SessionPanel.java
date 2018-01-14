package client.view;

import client.model.LocalSession;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SessionPanel extends JPanel{
    private LocalSession localSession;

    private JLabel[][] playersLabel;

    /**
     * @param localSession Session, which state would be painted at Panel
     */
    SessionPanel (LocalSession localSession){
        this.localSession = localSession;

        setBounds(0, 0, 60, 240);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        drawLabels();
    }

    public void drawLabels() {
        List<Pair<Pair<Integer, String>, common.model.game.Color>> players = localSession.getPlayers();
        int nrPlayers = localSession.getNrPlayers();
        int labelWidth = 60, labelHeight = 40;
        playersLabel = new JLabel[localSession.getNrPlayers()][3];

        for (int i=0; i<localSession.getNrPlayers(); i++) {
            Pair <Pair<Integer, String>, common.model.game.Color> pair = players.get(i);

            playersLabel[i][0] = new JLabel("ACTIVE");
            playersLabel[i][1] = new JLabel(pair.getKey().getValue());
            if (localSession.getTurn() == pair.getValue())
                playersLabel[i][2] = new JLabel("MOVE");
            else playersLabel[i][2] = new JLabel("WAIT");

            for (int j=0; j<3; j++) {
                playersLabel[i][j].setBackground(GamePanel.switchColor(pair.getValue()));
                playersLabel[i][j].setBounds(j*labelWidth, i*labelHeight, labelWidth, labelHeight);
                playersLabel[i][j].setOpaque(true);
                this.add(playersLabel[i][j]);
            }
        }
    }
}