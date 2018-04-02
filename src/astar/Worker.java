package astar;


import astar.drawers.GridDrawer;
import astar.drawers.ProgressDrawer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private JPanel panel1;
    private JRadioButton diagonalCheck;
    private JRadioButton showSteps;
    Grid grid;

    public Worker(JPanel panel, JRadioButton diagonalCheck, JRadioButton showSteps) {
        panel1 = panel;
        this.diagonalCheck = diagonalCheck;
        this.showSteps = showSteps;
        grid = new Grid();
    }

    public void findPath() {
        grid.setDiagonalMoveAllowance(diagonalCheck.isSelected());
        List<SquareGridNode> path = PathFinder.findPath(grid.startNode, grid.endNode, showSteps.isSelected(), this);
        if (path != null) {
            drawGrid(path);
        }
    }

    public void resetGrid() {
        grid.reset();
        drawGrid();
    }

    public void drawGrid(List<SquareGridNode> path) {
        Thread thread = new Thread(new GridDrawer(panel1.getGraphics(), grid, path));
        thread.start();
    }

    public void drawGrid() {
        Thread thread = new Thread(new GridDrawer(panel1.getGraphics(), grid));
        thread.start();
    }

    public void drawProgress(List<Node> openNodes, List<Node> closedNodes, List<Node> path) {
        Thread thread = new Thread(new ProgressDrawer(panel1.getGraphics(), grid, toSquareGridNodes(path), toSquareGridNodes(openNodes), toSquareGridNodes(closedNodes)));
        thread.start();
    }

    private List<SquareGridNode> toSquareGridNodes(List<Node> nodes) {
        List<SquareGridNode> list = new ArrayList<>();
        for (Node node : nodes) {
            list.add((SquareGridNode)node);
        }
        return list;
    }

    public void handleClick(MouseEvent e) {
        int x = e.getX() / 50;
        int y = e.getY() / 50;
        grid.switchBlocked(x,y);
        drawGrid();
    }
}
