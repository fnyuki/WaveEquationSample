package waveequation;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class WaveEquation2DComponent extends JComponent implements Runnable {

	private int N = 51;
	WaveEquation2D we;

	public void simulate(double dt, double c, int x, int y) {
		we = new WaveEquation2D(dt, c, x, y);
		Thread th = new Thread(this);
		th.start();
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		if (we != null) {
			for(int i = 0; i < N-1; i++) {
				for(int j = 0; j < N-1; j++) {
					g.drawLine(32 + j*8 + i*2, 128 + i*4 - (int)(we.z[2][i][j]*2.0), 32 + (j+1)*8 + i*2, 128 + i*4 - (int)(we.z[2][i][j+1]*2.0));
					g.drawLine(32 + j*8 + i*2, 128 + i*4 - (int)(we.z[2][i][j]*2.0), 32 + j*8 + (i+1)*2, 128 + (i+1)*4 - (int)(we.z[2][i+1][j]*2.0));
				}
			}
			g.drawLine(132, 328, 532, 328);
			g.drawLine(432, 128, 532, 328);
		}
	}

	public void run() {
		while(!we.isEnd) {
			try {
				we.simulate();
				repaint();
				Thread.sleep(10);
			}
			catch(Exception e) {
				System.out.println(e.toString());
				System.exit(1);
			}
		}
	}

}
