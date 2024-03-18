import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelGrid extends JPanel {
    private JButton[][] buttons = new JButton[3][3];

    public JPanelGrid() {
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
                buttons[i][j].setPreferredSize(new Dimension(80, 80));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttons[i][j].addActionListener(e -> {
                    JButton clicked = (JButton) e.getSource();
                    clicked.setBackground(Color.RED);
                });
                panel[panelI][panelJ].add(buttons[i][j]);
            }
        }
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(new JButton("Iš naujo!"), BorderLayout.PAGE_END);
    }


    private static void createAndShowGui() {
        JPanelGrid mainPanel = new JPanelGrid();

        JFrame frame = new JFrame("Kryžiukai/Nuliukai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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