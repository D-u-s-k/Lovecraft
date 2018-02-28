package dusk.programs.lcg.src;

import javax.swing.SwingUtilities;

import dusk.programs.lcg.dungeon.Dungeon;
import dusk.programs.lcg.graphic.GraphicsHandler;
import dusk.programs.lcg.graphic.TextureHandler;

public class Main implements Runnable {
	
	public static Main m;
	public static Thread thread;
	
	public enum Mode {
		Menu, OverWorld, Dungeon;
	}
	
	public static Mode mode = Mode.Menu;
	
	public static void main(String[] args) {
		m = new Main();
	}
	
	public Main() {
		TextureHandler.init();
		TextureHandler.handleTextures();
		GraphicsHandler.init();
		start();
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public static void stop() {
		System.exit(0);
	}

	@Override
	public void run() {
		update();
	}
	
	public static long nextTime = System.currentTimeMillis();
	public static int frames = 0;
	public static long lastTime = System.currentTimeMillis();
	
	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					lastTime = System.currentTimeMillis();
					GraphicsHandler.graphicsUpdate();
					frames++;
					if (System.currentTimeMillis() >= nextTime) {
						nextTime += 1000;
						//System.out.println(frames + " fps");
						frames = 0;
					}
					long currTime = System.currentTimeMillis();
					long elapsedTime = currTime - lastTime;
					if (elapsedTime >= 20) {
						elapsedTime = 20;// don't allow negative sleep times but aim for 50 FPS
					}
					Thread.sleep(20 - elapsedTime);
					update();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 50fps
			}
		});
	}

}
