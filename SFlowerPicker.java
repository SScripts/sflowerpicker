package sflowerpicker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import sflowerpicker.task.Task;
import sflowerpicker.task.banking.Deposit;
import sflowerpicker.task.banking.OpenBank;
import sflowerpicker.task.methods.Start;
import sflowerpicker.task.methods.Stop;
import sflowerpicker.task.planting.Planting;
import sflowerpicker.task.walking.ChangeSpot;
import sflowerpicker.task.walking.WalkToBank;
import sflowerpicker.task.walking.WalkToSpot;

@Script.Manifest(description = "Plats Flowers and picks them up, great Money", name = "SFlowerPicker")

public class SFlowerPicker extends PollingScript<org.powerbot.script.rt6.ClientContext> implements PaintListener, MessageListener {

    public List<Task> tasks = Collections.synchronizedList(new ArrayList<Task>());

    public static String Status;
    public static int picked;
    
    @Override
    public void start() {
        tasks.add(new Deposit(ctx));
        tasks.add(new OpenBank(ctx));
        tasks.add(new Planting(ctx));
        tasks.add(new WalkToBank(ctx));
        tasks.add(new WalkToSpot(ctx));
        tasks.add(new Start(ctx));
        tasks.add(new ChangeSpot(ctx));
        tasks.add(new Stop(ctx));
    }

    public static boolean isFail;
    
    @Override
    public void messaged(MessageEvent msg) {
        if (msg.getMessage().startsWith("You can")){
            isFail = true;
        }
    }
    @Override
    public void poll() {
        synchronized(tasks) {
            if (tasks.size() == 0) {
                try {
                    tasks.wait();
                } catch (InterruptedException ignored) {}
            }
        }
        for (Task task : tasks) {
            if (task.activate()) {
                task.execute();
            }
        }
    }
    
    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }
    
    @Override
    public void repaint(Graphics g) {
 	    g.setColor(Color.WHITE);
    	g.drawString("Status: " + Status, 10, 100);
        g.drawString("Time Run: " + formatTime(getTotalRuntime()), 10, 140);
    	g.drawString("Flowers Picked: " + picked, 10, 120);
        g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 5);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y);


        }
     

}
