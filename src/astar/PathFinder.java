package astar;


import astar.drawers.ProgressDrawer;

import java.util.*;


public class PathFinder {


    public static class PriorityList extends LinkedList {

        public void add(Comparable object) {
            for (int i=0; i<size(); i++) {
                if (object.compareTo(get(i)) <= 0) {
                    add(i, object);
                    return;
                }
            }
            addLast(object);
        }
    }


    protected static List constructPath(Node node) {
        LinkedList path = new LinkedList();
        while (node.pathParent != null) {
            path.addFirst(node);
            node = node.pathParent;
        }
        return path;
    }



    public static List findPath(Node startNode, Node goalNode, boolean showSteps, Worker worker) {

        PriorityList openList = new PriorityList();
        LinkedList closedList = new LinkedList();

        startNode.costFromStart = 0;
        startNode.estimatedCostToGoal =
                startNode.getEstimatedCost(goalNode);
        startNode.pathParent = null;
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node node = (Node)openList.removeFirst();
            if (node == goalNode) {
                return constructPath(goalNode);
            }

            List neighbors = node.getNeighbors();
            for (int i=0; i<neighbors.size(); i++) {
                Node neighborNode =
                        (Node)neighbors.get(i);
                boolean isOpen = openList.contains(neighborNode);
                boolean isClosed =
                        closedList.contains(neighborNode);
                double costFromStart = node.costFromStart +
                        node.getCost(neighborNode);

                if ((!isOpen && !isClosed) ||
                        costFromStart < neighborNode.costFromStart)
                {
                    neighborNode.pathParent = node;
                    neighborNode.costFromStart = costFromStart;
                    neighborNode.estimatedCostToGoal =
                            neighborNode.getEstimatedCost(goalNode);
                    if (isClosed) {
                        closedList.remove(neighborNode);
                    }
                    if (!isOpen) {
                        openList.add(neighborNode);
                    }
                }
            }
            if (showSteps) {
                worker.drawProgress(openList, closedList, constructPath(node));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            closedList.add(node);
        }

        return null;
    }

}

