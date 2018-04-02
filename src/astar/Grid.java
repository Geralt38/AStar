package astar;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private SquareGridNode[][] nodes;
    public SquareGridNode startNode;
    public SquareGridNode endNode;
    private boolean allowDiagonalMoves = false;

    public Grid() {reset();}

    public void setDiagonalMoveAllowance(boolean allowDiagonalMoves) {
        this.allowDiagonalMoves = allowDiagonalMoves;
    }

    public void reset() {
        nodes = new SquareGridNode[10][10];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                nodes[i][j] = new SquareGridNode(this, i, j);
            }
        }
        startNode = nodes[0][0];
        endNode = nodes[9][0];
    }

    public void switchBlocked(int x, int y) {
        if ((x >= 0) && (x < nodes.length) && (y >= 0) && (y < nodes[0].length) && (nodes[x][y] != startNode) && (nodes[x][y] != endNode)) {
            nodes[x][y].blocked = !nodes[x][y].blocked;
        }
    }

    public List<SquareGridNode> getNeighbours(SquareGridNode node) {
        List<SquareGridNode> neigbours = new ArrayList<SquareGridNode>();
        if (node.x > 0) {
            addNeigbour(neigbours,nodes[node.x - 1][ node.y]);
            if (allowDiagonalMoves) {
                if (node.y > 0) {
                    addNeigbour(neigbours, nodes[node.x - 1][node.y - 1]);
                }
                if (node.y < nodes.length - 1) {
                    addNeigbour(neigbours, nodes[node.x - 1][node.y + 1]);
                }
            }
        }
        if (node.x < nodes.length-1) {
            addNeigbour(neigbours,nodes[node.x + 1][ node.y]);
            if (allowDiagonalMoves) {
                if (node.y > 0) {
                    addNeigbour(neigbours, nodes[node.x + 1][node.y - 1]);
                }
                if (node.y < nodes.length - 1) {
                    addNeigbour(neigbours, nodes[node.x + 1][node.y + 1]);
                }
            }
        }
        if (node.y > 0) {addNeigbour(neigbours,nodes[node.x][ node.y - 1]);}
        if (node.y < nodes.length-1) {addNeigbour(neigbours,nodes[node.x][ node.y + 1]);}
        return neigbours;
    }

    private void addNeigbour(List nodes, SquareGridNode node) {
        if (!node.blocked) {
            nodes.add(node);
        }
    }

    public SquareGridNode[][] getNodes() {
        return nodes;
    }
}
