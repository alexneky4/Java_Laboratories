package uaic.info.components;

import uaic.info.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numOfVertices;
    private double edgeProbability;
    private boolean firstPlayer = true;
    private List<Line2D> lines = new ArrayList<>();
    private int[] x,y;

    private static final double CONSIDERED_CLICK = 5.0;
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
            @Override
            public void mouseClicked(MouseEvent e) {
                for(Line2D line : lines)
                {
                    if(line.contains(e.getX(),e.getY()))
                    {
                        if(firstPlayer == true)
                            graphics.setColor(Color.RED);
                        else
                            graphics.setColor(Color.BLUE);
                        firstPlayer = !firstPlayer;
                        graphics.draw(line);
                    }
                }
                repaint();
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

        createOffScrrenImage();
        createVertices();
        drawLines();
        drawVertices();
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

    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}