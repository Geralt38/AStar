package astar;


import java.util.List;

public class SquareGridNode extends Node {

    public int x;
    public int y;
    public boolean blocked = false;
    private Grid grid;

    public SquareGridNode(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    @Override
    public double getCost(Node node) {
        return 1;
    }

    @Override
    public double getEstimatedCost(Node node) {
        SquareGridNode squareGridNode = (SquareGridNode) node;
        return Math.sqrt(Math.pow((double)x - (double)squareGridNode.x, 2D) + Math.pow((double)y - (double)squareGridNode.y, 2D));
    }

    @Override
    public List getNeighbors() {
        return grid.getNeighbours(this);
    }

    public void consoleWrite() {
        System.out.println(x + " " + y);
    }
}
