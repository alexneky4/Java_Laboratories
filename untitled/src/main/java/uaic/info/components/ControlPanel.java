package uaic.info.components;

import uaic.info.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    private JButton loadButton;
    private JButton saveButton;

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    private JButton resetButton;
    private JButton exitButton;

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        setLayout(new GridLayout(1,4));

        add(loadButton);
        add(saveButton);
        add(resetButton);
        add(exitButton);
    }
}

