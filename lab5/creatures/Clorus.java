package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import org.knowm.xchart.internal.chartpart.Axis;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r = 34;
    private int g = 0;
    private int b = 231;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay(){
        energy -= 0.01;
    }

    public Clorus replicate(){
        Clorus newClorus = new Clorus(energy() / 2);
        energy /= 2;
        return newClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<Direction>();
        Deque<Direction> tastyNeighbors = new ArrayDeque<Direction>();

        for (Map.Entry<Direction, Occupant> o : neighbors.entrySet()) {
            if (o.getValue().name().equals("plip")) {
                tastyNeighbors.add(o.getKey());
            } else if(o.getValue().name().equals("empty")){
                emptyNeighbors.add(o.getKey());
            }
        }


        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2
        if (!tastyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(tastyNeighbors));
        }

        //Rule 3
        if(energy >= 1.0){
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        //Rule 4
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
    }
}
