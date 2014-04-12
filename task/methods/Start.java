package sflowerpicker.task.methods;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

public class Start extends Task {

	public Start(ClientContext ctx) {
		super(ctx);
		
	}

	@Override
	public boolean activate() {
		
		return ctx.backpack.isEmpty() && !ctx.bank.opened();
	}

	@Override
	public void execute() {
        SFlowerPicker.Status = "Opening Bank";
		ctx.bank.open();
	     Condition.wait(new Callable<Boolean>() {
	            @Override
	            public Boolean call() throws Exception {
	                return ctx.bank.opened();
	            }
	        }, 500, 2);
		
	}

}
