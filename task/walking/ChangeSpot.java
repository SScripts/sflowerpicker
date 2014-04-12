package sflowerpicker.task.walking;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import sflowerpicker.SFlowerPicker;
import sflowerpicker.task.Task;

public class ChangeSpot extends Task {

	public ChangeSpot(ClientContext ctx) {
		super(ctx);
		
	}
	
    Tile SpotOne = new Tile(3170, 3480, 0);
    Tile SpotTwo = new Tile (3171, 3476, 0);

	@Override
	public boolean activate() {
		
		return SFlowerPicker.isFail;
	}

	@Override
	public void execute() {
		switch (Random.nextInt(0, 2)) {
		default:
			ctx.movement.step(SpotOne);
	        SFlowerPicker.Status = "Moving to an other Spot";
	        wait(2500);
			SFlowerPicker.isFail = false;
			break;

		case 2:
			ctx.movement.step(SpotTwo);
	        SFlowerPicker.Status = "Moving to an other Spot";
	        wait(2500);
			SFlowerPicker.isFail = false;
			break;
		}
		
	}
	 public void wait(int ms) {
	        try {
	            Thread.sleep(Math.max(5, (int) (ms * Random.nextDouble(0.76, 1.45))));
	        } catch (InterruptedException ignored) {
	        }
	    }

}