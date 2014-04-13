package sflowerpicker.task.walking;


import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;


public class WalkToBank extends Task {
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    private final Tile bankTile = new Tile(3148, 3475, 0);

    @Override
    public boolean activate() {
        return !ctx.bank.inViewport() 
        		&& !ctx.bank.opened()
        		&& ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
    	SFlowerPicker.Status = "Walking to Bank";
        ctx.movement.step(bankTile);

    }
}
