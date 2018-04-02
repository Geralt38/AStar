import astar.*;
import astar.drawers.GridDrawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainForm {

    private static JFrame frame;
    private JPanel panel1;
    private JButton buttonFind;
    private JButton buttonReset;
    private JRadioButton diagonalCheck;
    private JRadioButton showSteps;

    Worker worker;

    public MainForm() {
        Worker worker = new Worker(panel1, diagonalCheck, showSteps);

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.findPath();
            }
        });
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.resetGrid();
            }
        });
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                worker.handleClick(e);
            }
        });
    }

    public static void main(String[] args) {

        frame = new JFrame("A*");

        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }




}
