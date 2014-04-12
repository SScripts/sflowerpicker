package sflowerpicker.task.methods;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;


import sflowerpicker.task.Task;

public class Stop extends Task {

    public Stop(ClientContext ctx) {
        super(ctx);

    }

    private final int SeedID = 299;


    @Override
    public boolean activate() {
        final Item i = ctx.backpack.select().id(SeedID).poll();
        return !ctx.backpack.select().contains(i) && !ctx.bank.opened();
    }

    @Override
    public void execute() {
        ctx.controller.stop();
    }

}