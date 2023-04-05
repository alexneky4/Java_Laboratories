package uaic.info.frames;

import uaic.info.components.ConfigPanel;
import uaic.info.components.ControlPanel;
import uaic.info.components.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    public MainFrame()
    {
        super("My Drawing Application");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas);
        pack();
        setLocationRelativeTo(null);
    }

    public void setCanvas(DrawingPanel canvas) {
        this.canvas = canvas;
    }
}
