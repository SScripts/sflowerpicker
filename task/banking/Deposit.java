package sflowerpicker.task.banking;

import org.powerbot.script.Condition;


import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

import java.util.concurrent.Callable;


public class Deposit extends Task {
    public Deposit(ClientContext ctx) {
        super(ctx);
    }

    private final int SeedID = 299;


    @Override
    public boolean activate() {
        return ctx.bank.opened();
    }

    @Override
    public void execute() {
        if (ctx.backpack.select().count() == 28){
            ctx.bank.depositInventory();
            SFlowerPicker.Status = "Depositing Inventory";
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.backpack.isEmpty();
                }
            }, 500, 2);


        }
        if (ctx.backpack.isEmpty()){
        	SFlowerPicker.Status = "withdrawing Seeds";
        	ctx.bank.withdraw(SeedID, 50);
       	
        }
        if (ctx.backpack.select().count() == 1) {
        	ctx.bank.close();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.bank.opened();
                }
            }, 500, 2);
        }

    }
}
