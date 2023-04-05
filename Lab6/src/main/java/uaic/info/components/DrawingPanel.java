package uaic.info.components;

import uaic.info.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class DrawingPanel extends JPanel implements Serializable {

    final MainFrame frame;
    final static int W = 600, H = 400;
    private int numOfVertices;
    private double edgeProbability;
    private boolean firstPlayer = true;
    private List<Line2D> lines = new ArrayList<>();
    private Map<String,Set<Line2D>> coloredLines = new HashMap<>();
    private int[] x,y;

    private static final double CONSIDERED_CLICK = 3.0;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame)
    {
        this.frame = frame;
        createOffScrrenImage();
        initPanel();
        createBoard();
    }

    private void initPanel()
    {
        setPreferredSize(new Dimension(W,H));
        setBorder(BorderFactory.createEtchedBorder());
        frame.getConfigPanel().getCreateButton().addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createBoard();
                    }
                }
        );

        this.addMouseListener(new MouseAdapter() {
            private boolean stopListening = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(stopListening == true)
                    return;
                for(Line2D line : lines)
                {

                    if(line.ptSegDist(e.getPoint()) <= CONSIDERED_CLICK)
                    {

                        String color;
                        if(firstPlayer == true)
                        {
                            graphics.setColor(Color.RED);
                            color = "Red";
                        }
                        else
                        {
                            graphics.setColor(Color.BLUE);
                            color = "Blue";
                        }
                        lines.remove(line);
                        graphics.draw(line);
                        repaint();
                        if(winningCondition(firstPlayer,line) == true)
                        {
                            String player = firstPlayer ? "Player one" : "Player two";
                            int option = JOptionPane.showConfirmDialog(null,
                                    player + " has won the game\nStart a new game?","Game finished",
                                    JOptionPane.YES_NO_OPTION);
                            if(option == JOptionPane.YES_OPTION)
                                createBoard();
                            else
                                stopListening = true;
                            firstPlayer = true;
                            break;
                        }
                        firstPlayer = !firstPlayer;
                        coloredLines.get(color).add(line);
                        break;
                    }
                }
            }
        });
    }

    private void createOffScrrenImage()
    {
        image = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,W,H);
    }

    public void createBoard()
    {
        numOfVertices = (Integer) frame.getConfigPanel().getDotsSpinner().getValue();
        edgeProbability = (Double) frame.getConfigPanel().getLinesCombo().getSelectedItem();
        lines.clear();
        coloredLines.put("Red", new HashSet<>());
        coloredLines.put("Blue", new HashSet<>());
        createOffScrrenImage();
        createVertices();
        drawVertices();
        drawLines();
        repaint();
    }

    private void createVertices()
    {
        int x0 = W / 2; int y0 = H / 2;
        int radius = H / 2 - 10;
        double alpha = 2 * Math.PI / numOfVertices;
        x = new int[numOfVertices];
        y = new int[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void drawLines()
    {
        graphics.setColor(Color.GRAY);
        for(int i = 0; i < numOfVertices - 1; i++)
            for(int j = i + 1; j < numOfVertices; j++)
            {
                if(Math.random() < edgeProbability)
                {
                    Line2D line = new Line2D.Double(x[i],y[i],x[j],y[j]);
                    graphics.draw(line);
                    lines.add(line);
                }
            }
    }

    private void drawVertices()
    {
        graphics.setColor(Color.BLACK);
        for(int i = 0; i < numOfVertices; i++)
            graphics.fillOval(x[i],y[i], 10, 10);
    }

    private boolean winningCondition(boolean firstPlayer,Line2D line)
    {
        String color;
        if(firstPlayer == true)
        {
            color = "Red";
        }
        else color = "Blue";

        long firstCoord = coloredLines.get(color).stream().filter(
                l -> (l.getX1() == line.getX1() && l.getY1() == line.getY1())
                        || (l.getX2() == line.getX1() && l.getY2() == line.getY1())).count();
        long secondCoord = coloredLines.get(color).stream().filter(
                l -> (l.getX1() == line.getX2() && l.getY1() == line.getY2())
                        || (l.getX2() == line.getX2() && l.getY2() == line.getY2())).count();
        return firstCoord > 0  && secondCoord > 0 ;
    }
    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public void loadImage(BufferedImage image) {
        // Clear the panel
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);

        // Draw the loaded image on the panel
        graphics.drawImage(image, 0, 0, null);
        repaint();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException
    {
        outputStream.writeInt(numOfVertices);
        outputStream.writeDouble(edgeProbability);
        outputStream.writeBoolean(firstPlayer);
        outputStream.writeObject(lines);
        outputStream.writeObject(coloredLines);
        outputStream.writeObject(x);
        outputStream.writeObject(y);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException,ClassNotFoundException
    {
        numOfVertices = inputStream.readInt();
        edgeProbability = inputStream.readDouble();
        firstPlayer = inputStream.readBoolean();
        lines = (ArrayList<Line2D>)inputStream.readObject();
        coloredLines = (Map<String, Set<Line2D>>)inputStream.readObject();
        x = (int[])inputStream.readObject();
        y = (int[])inputStream.readObject();
    }

    public int getNumOfVertices()
    {
        return numOfVertices;
    }

    public double getEdgeProbability()
    {
        return edgeProbability;
    }
}