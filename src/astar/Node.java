package astar;

import java.util.List;


public abstract class Node implements Comparable {

    Node pathParent;
    public double costFromStart;
    public double estimatedCostToGoal;


    public double getCost() {
        return costFromStart + estimatedCostToGoal;
    }


    public int compareTo(Object other) {
        double thisValue = this.getCost();
        double otherValue = ((Node)other).getCost();

        double v = thisValue - otherValue;
        return (v>0)?1:(v<0)?-1:0;
    }



    public abstract double getCost(Node node);



    public abstract double getEstimatedCost(Node node);



    public abstract List getNeighbors();
}