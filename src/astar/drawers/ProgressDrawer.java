package astar.drawers;

import astar.Grid;
import astar.SquareGridNode;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ProgressDrawer extends GridDrawer{

    private List<SquareGridNode> openNodes;
    private List<SquareGridNode> closedNodes;

    public ProgressDrawer(Graphics graphics, Grid grid, List<SquareGridNode> path, List<SquareGridNode> openNodes, List<SquareGridNode> closedNodes) {
        super(graphics, grid, path);
        this.openNodes = openNodes;
        this.closedNodes = closedNodes;
    }

    protected void drawNumbers() {
        for (int i = 0; i < openNodes.size(); i++){
            graphics.drawString(String.format( "%.2f", openNodes.get(i).costFromStart ), openNodes.get(i).x * 50, openNodes.get(i).y * 50 + 25);
        }
    }

    @Override
    protected String[][] formOutputMatrix() {
        String[][] outputMatrix = super.formOutputMatrix();
        for (SquareGridNode node: openNodes) {
            if (outputMatrix[node.x][node.y] == "0") {
                outputMatrix[node.x][node.y] = "O";
            }
        }
        for (SquareGridNode node: closedNodes) {
            if (outputMatrix[node.x][node.y] == "0") {
                outputMatrix[node.x][node.y] = "C";
            }
        }
        return outputMatrix;
    }
}
