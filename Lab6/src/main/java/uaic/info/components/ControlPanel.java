package uaic.info.components;


import uaic.info.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

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
                chooser.setDialogTitle("Select where the folder with png and json has to be saved");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(e.getComponent()) == JFileChooser.APPROVE_OPTION) {
                    String selectedFolder = chooser.getSelectedFile().toString();
                    String fileName = JOptionPane.showInputDialog("Enter game name:");
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
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(e.getComponent()) == JFileChooser.APPROVE_OPTION) {
                    String selectedGame = chooser.getSelectedFile().toString();
                    String imagePath = selectedGame + "\\" + chooser.getSelectedFile().getName() + ".png";
                    String gamePath = selectedGame + "\\" + chooser.getSelectedFile().getName() + ".ser";

                    try{
                        BufferedImage image = ImageIO.read(new File(imagePath));

                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(gamePath));
                        frame.getCanvas().loadImage(image);
                        frame.setCanvas((DrawingPanel) in.readObject());

                        frame.getConfigPanel().setDotsSpinner(frame.getCanvas().getNumOfVertices());
                        frame.getConfigPanel().setLinesCombo(frame.getCanvas().getEdgeProbability());
                    }
                    catch(IOException exception)
                    {
                        int option = JOptionPane.showConfirmDialog(e.getComponent(),"" +
                                "Could not load the game","Wrong file type",JOptionPane.OK_OPTION);
                    }
                    catch (ClassNotFoundException exception)
                    {
                        int option = JOptionPane.showConfirmDialog(e.getComponent(),"" +
                                "Could not load the game stats","Could not deserialize the datase",JOptionPane.OK_OPTION);
                    }


                }

            }
        });
    }

    private void saveGame(String path, String fileName) throws IOException {
        new File(path + "/" + fileName).mkdir();
        path = path + "/" + fileName;
        File outputFile = new File(path, fileName + ".png");
        ImageIO.write(frame.getCanvas().image, "png", outputFile);
        FileOutputStream outputSerialize = new FileOutputStream(new File(path, fileName + ".ser"));
        ObjectOutputStream out = new ObjectOutputStream(outputSerialize);
        out.writeObject(frame.getCanvas());
        outputSerialize.close();
        out.close();
    }
}