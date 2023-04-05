package uaic.info.components;


import uaic.info.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.getCanvas().createBoard();
            }
        });

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Select where the png has to be saved");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(e.getComponent()) == JFileChooser.APPROVE_OPTION) {
                    String selectedFolder = chooser.getSelectedFile().toString();
                    String fileName = JOptionPane.showInputDialog("Enter file name:");
                    if (fileName != null && !fileName.trim().isEmpty()) {
                        try {
                            saveGame(selectedFolder, fileName.trim());
                        } catch(IOException exception) {
                            exception.printStackTrace();
                        }
                        frame.getCanvas().createBoard();
                    }
                }
            }
        });

        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setDialogTitle("Select saved game");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(e.getComponent()) == JFileChooser.APPROVE_OPTION) {
                    String selectedImage = chooser.getSelectedFile().toString();
                    try{
                        BufferedImage image = ImageIO.read(new File(selectedImage));
                        frame.getCanvas().loadImage(image);
                    }
                    catch(IOException exception)
                    {
                        int option = JOptionPane.showConfirmDialog(e.getComponent(),"" +
                                "Could not load the game","Wrong file type",JOptionPane.OK_OPTION);
                    }
                }

            }
        });
    }

    private void saveGame(String path, String fileName) throws IOException {
        File outputFile = new File(path, fileName + ".png");
        ImageIO.write(frame.getCanvas().image, "png", outputFile);
    }
}

