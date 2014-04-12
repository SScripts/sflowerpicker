package sflowerpicker.task.banking;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;


import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

import java.util.concurrent.Callable;


public class CloseBank extends Task {
    public CloseBank(ClientContext ctx) {
        super(ctx);
    }
    
 
    @Override
    public boolean activate() {
        return ctx.bank.opened() && ctx.backpack.select().count() == 1;
    }

    @Override
    public void execute() {
        ctx.bank.close();
        SFlowerPicker.Status = "Closing bank";
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.bank.opened();
            }
        }, 500, 2);
    }
}
