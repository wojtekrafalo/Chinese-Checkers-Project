package application.view;

import javax.swing.*;

public class EditEmployeeWindow extends HumanWindow {
    private JButton peopleButton = new JButton("PEOPLE");
    private JButton CatsButton = new JButton("CATS");
    private JButton stayButton = new JButton("STAY");

    private JPanel tabPanel;
    private JPanel dataPanel;

    public EditEmployeeWindow() {

        super("EditEmployeeWindow");
    }


    public JButton getLogOUT() {                          //send selected field of List
        return null;
    }

    public void setTable() {

    }
}
