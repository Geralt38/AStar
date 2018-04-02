package astar.drawers;


import astar.ColorDictionary;
import astar.Grid;
import astar.SquareGridNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridDrawer implements Runnable {

    protected Graphics graphics;
    protected Grid grid;
    protected List<SquareGridNode> path;

    public GridDrawer(Graphics graphics, Grid grid) {
        this.graphics = graphics;
        this.grid = grid;
        path = new ArrayList<>();
    }

    public GridDrawer(Graphics graphics, Grid grid, List<SquareGridNode> path) {
        this.graphics = graphics;
        this.grid = grid;
        this.path = path;
    }

    @Override
    public void run() {
        drawGrid();
    }

    protected void drawGrid() {
        String[][] outputMatrix = formOutputMatrix(grid, path);
        ColorDictionary dictionary = ColorDictionary.getInstance();
        for (int i = 0; i < outputMatrix.length; i++) {
            for (int j = 0; j < outputMatrix[0].length; j++) {
                graphics.setColor(Color.BLACK);
                graphics.drawRect(i * 50, j * 50, 50, 50);
                graphics.setColor(dictionary.get(outputMatrix[i][j]));
                graphics.fillRect(i * 50 + 1, j * 50 + 1, 49, 49);
            }
        }
    }

    protected static String[][] formOutputMatrix(Grid grid, List<SquareGridNode> path) {
        SquareGridNode[][] nodes = grid.getNodes();
        String[][] outputMatrix = new String[nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                outputMatrix[i][j] = nodes[i][j].blocked ? "1" : "0";
            }
        }
        for (SquareGridNode node: path) {
            outputMatrix[node.x][node.y] = "P";
        }
        outputMatrix[grid.startNode.x][grid.startNode.y] = "S";
        outputMatrix[grid.endNode.x][grid.endNode.y] = "E";
        return outputMatrix;
    }
}
