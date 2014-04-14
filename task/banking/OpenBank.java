package sflowerpicker.task.banking;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

import java.util.concurrent.Callable;


public class OpenBank extends Task {

    public OpenBank(ClientContext ctx) {
        super(ctx);
    }



    @Override
    public boolean activate() {
        return !ctx.bank.opened() && ctx.backpack.select().count() == 28 && ctx.bank.inViewport();
    }

    @Override
    public void execute() {
        SFlowerPicker.Status = "Opening Bank";
        if(ctx.bank.open()){
        	Condition.wait(new Callable<Boolean>() {
            	@Override
            	public Boolean call() throws Exception {
                	return ctx.bank.opened();
            	}
        	}, 500, 2);
        }
    }
}
