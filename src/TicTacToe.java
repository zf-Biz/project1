import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TicTacToe extends JPanel {
    private static final int GAP = 4;
    private static final int WIDTH = 80;
    private JButton[][] buttons = new JButton[3][3];

    public TicTacToe() {


        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        setLayout(new GridLayout(1, 1));
        JPanel[][] panel = new JPanel[1][1];
        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel[i].length; j++) {
                panel[i][j] = new JPanel(new GridLayout(3, 1));
                mainPanel.add(panel[i][j]);
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            int panelI = i / 3;
            for (int j = 0; j < buttons[i].length; j++) {
                int panelJ = j / 3;
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(WIDTH, WIDTH));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttons[i][j].addActionListener(e -> {
                    JButton clicked = (JButton) e.getSource();
                    playerDrawO(clicked);
                });
                panel[panelI][panelJ].add(buttons[i][j]);
            }
        }
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        JButton restart = new JButton("Iš naujo!");
        restart.addActionListener(e -> {
            createAndShowGui();
        });
        add(restart, BorderLayout.PAGE_END);
    }

    public void playerDrawO(JButton button) {
        if (button.getIcon() == null) {
            BufferedImage circleImg = new BufferedImage(WIDTH, WIDTH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.RED);
            int x = GAP;
            int y = x;
            int width = WIDTH - 2 * x;
            int height = WIDTH - 2 * y;
            g2.drawOval(x, y, width, height);
            g2.dispose();
//                    clicked.setEnabled(false);
            button.setIcon(new ImageIcon(circleImg, "O"));
            pcDrawX();
        } else {
            JOptionPane.showMessageDialog(this.getComponent(0), "error");
        }
    }


    public void pcDrawX() {
        if (buttonCheck()) {
            int i = ThreadLocalRandom.current().nextInt(0, 3);
            int j = ThreadLocalRandom.current().nextInt(0, 3);
            if (buttons[i][j].getIcon() == null) {
                BufferedImage xImg = new BufferedImage(WIDTH, WIDTH, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = xImg.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLUE);
                g2.drawLine(5, 5, WIDTH - 5, WIDTH - 5);
                g2.drawLine(5, WIDTH - 5, WIDTH - 5, 5);
                ImageIcon xIcon = new ImageIcon(xImg, "X");
//                g2.dispose();
                buttons[i][j].setIcon(xIcon);
                iconCheck();
            } else {
                pcDrawX();
            }
        } else {
            iconCheck();
        }
    }

    public boolean buttonCheck() {
        ArrayList<Icon> temp = new ArrayList<>();
        for (int q = 0; q < buttons.length; q++) {
            for (JButton b : buttons[q]) {
                temp.add(b.getIcon());
            }
        }
        if (temp.contains(null)) {
            return true;
        } else {
            return false;
        }
    }

    public void iconCheck() {
        ArrayList<Icon> temp = new ArrayList<>();
        for (JButton[] n : buttons) {
            for (JButton b : n) {
                if (b.getIcon() == null) {
                    BufferedImage emptyImg = new BufferedImage(WIDTH, WIDTH, BufferedImage.TYPE_INT_ARGB);
                    temp.add(new ImageIcon(emptyImg, "empty"));
                } else {
                    temp.add(b.getIcon());
                }
            }
        }
        if (temp.get(0).toString().equals("O") && temp.get(1).toString().equals("O") && temp.get(2).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(3).toString().equals("O") && temp.get(4).toString().equals("O") && temp.get(5).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(6).toString().equals("O") && temp.get(7).toString().equals("O") && temp.get(8).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(0).toString().equals("O") && temp.get(3).toString().equals("O") && temp.get(6).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(1).toString().equals("O") && temp.get(4).toString().equals("O") && temp.get(7).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(2).toString().equals("O") && temp.get(5).toString().equals("O") && temp.get(8).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(0).toString().equals("O") && temp.get(4).toString().equals("O") && temp.get(8).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");
        } else if (temp.get(6).toString().equals("O") && temp.get(4).toString().equals("O") && temp.get(2).toString().equals("O")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs laimėjote!");//
        } else if (temp.get(0).toString().equals("X") && temp.get(1).toString().equals("X") && temp.get(2).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(3).toString().equals("X") && temp.get(4).toString().equals("X") && temp.get(5).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(6).toString().equals("X") && temp.get(7).toString().equals("X") && temp.get(8).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(0).toString().equals("X") && temp.get(3).toString().equals("X") && temp.get(6).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(1).toString().equals("X") && temp.get(4).toString().equals("X") && temp.get(7).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(2).toString().equals("X") && temp.get(5).toString().equals("X") && temp.get(8).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        } else if (temp.get(0).toString().equals("X") && temp.get(4).toString().equals("X") && temp.get(8).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs lpraaimėjote!");
        } else if (temp.get(6).toString().equals("X") && temp.get(4).toString().equals("X") && temp.get(2).toString().equals("X")) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Jūs pralaimėjote!");
        }
    }


    private static void createAndShowGui() {
        TicTacToe mainPanel = new TicTacToe();

        JFrame frame = new JFrame("Kryžiukai/Nuliukai");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }
}
