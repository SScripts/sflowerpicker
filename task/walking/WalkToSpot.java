package sflowerpicker.task.walking;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;


public class WalkToSpot extends Task {
    public WalkToSpot(ClientContext ctx) {
        super(ctx);
    }

    Tile spotTile = new Tile(3173, 3478, 0);

    Area PlantArea = new Area(new Tile (3175, 3477, 0),
            new Tile (3172, 3477, 0),
            new Tile (3172, 3480, 0),
            new Tile (3175, 3480, 0));

    @Override
    public boolean activate() {
        return !ctx.bank.opened() && ctx.backpack.select().count() == 1 && !PlantArea.contains(ctx.players.local().tile());
    }

    @Override
    public void execute() {
    	SFlowerPicker.Status = "Walking to Spot";
        ctx.movement.step(spotTile);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.distance(ctx.players.local().tile(), ctx.movement.destination()) < 8;
            }
        }, 250, 20);

    }
}
