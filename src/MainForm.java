import astar.Grid;
import astar.PathFinder;
import astar.SquareGridNode;
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

    private Grid grid;

    public MainForm() {
        grid = new Grid();

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findPath();
            }
        });
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.reset();
                drawGrid();
            }
        });
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handleClick(e);
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

    private void findPath() {
        List<SquareGridNode> path = PathFinder.findPath(grid.startNode, grid.endNode);
        if (path != null) {
            drawGrid(path);
        }
    }

    private void drawGrid(List<SquareGridNode> path) {
        Thread thread = new Thread(new GridDrawer(panel1.getGraphics(), grid, path));
        thread.start();
    }

    private void drawGrid() {
        Thread thread = new Thread(new GridDrawer(panel1.getGraphics(), grid));
        thread.start();
    }

    private void handleClick(MouseEvent e) {
        int x = e.getX() / 50;
        int y = e.getY() / 50;
        grid.switchBlocked(x,y);
        drawGrid();
    }

}
