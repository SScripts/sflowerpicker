package sflowerpicker.task.planting;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.*;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

import java.util.concurrent.Callable;


public class Planting extends Task {
    public Planting(ClientContext ctx) {
        super(ctx);
    }

    private final int SeedID = 299;

    private final Area plantArea = new Area(new Tile (3175, 3475, 0),
            new Tile (3135, 3475, 0),
            new Tile (3135, 3483, 0),
            new Tile (3175, 3483, 0));


    private final Component pick = ctx.widgets.widget(1188).component(12);

    @Override
    public boolean activate() {
        return !ctx.bank.opened() && plantArea.contains(ctx.players.local().tile()) && ctx.backpack.select().count() !=28;
    }

    @Override
    public void execute() {
        if (!pick.visible() && ctx.players.local().animation() == -1){
            Item i = ctx.backpack.select().id(SeedID).poll();
            if (i.interact("Plant")){
            	SFlowerPicker.picked ++;
            	SFlowerPicker.Status = "Plating Flower";
            	Condition.wait(new Callable<Boolean>() {
            		@Override
            		public Boolean call() throws Exception {
            			return pick.visible();
            		}
            	}, 1500, 2);
            }
        }


        if (pick.valid()){
            ctx.keyboard.send("1");
            SFlowerPicker.Status = "Picking up Flower";
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !pick.visible();
                }
            }, 500, 2);
        }
        
        
    }
    

    
}
