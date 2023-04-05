package uaic.info.components;

import uaic.info.frames.MainFrame;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JLabel dotsLabel, linesLabel;
    private JSpinner dotsSpinner;
    private JComboBox linesCombo;
    private JButton createButton;

    public ConfigPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        dotsLabel = new JLabel("Number of dots");
        linesLabel = new JLabel("Line probability");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6,3,50,1));

        Double[] probabilities = {1.0, 0.75, 0.5};
        linesCombo = new JComboBox(probabilities);
        createButton = new JButton("Start Game");

        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
    }


    public JLabel getDotsLabel() {
        return dotsLabel;
    }

    public JLabel getLinesLabel() {
        return linesLabel;
    }

    public JSpinner getDotsSpinner() {
        return dotsSpinner;
    }

    public JComboBox getLinesCombo() {
        return linesCombo;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public void setDotsSpinner(int newValue)
    {
        dotsSpinner.setValue(newValue);
    }

    public void setLinesCombo(double newValue)
    {
        linesCombo.setSelectedItem(newValue);
    }
}
