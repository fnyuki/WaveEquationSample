package waveequation;

public class WaveEquation2D {

	private int N = 51;			// n
	private double dd = 1.0;	// delta d for delta x, y
	private int t = 2;			// counter
	private int tmax = 2000;		// counter maximum
	private double c;			// velocity
	private double dt;			// delata t
	public double z[][][];
	public boolean isEnd = false;

	public WaveEquation2D(double c, double dt, int x, int y) {
		this.c = c;
		this.dt = dt;
		z = new double[3][N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i > y/2 - 2 && i < y/2 + 2 && j > x/2 - 2 && j < x/2 + 2) {
					z[0][i][j] = 200.0;
				} else {
					z[0][i][j] = 0.0;
				}
			}
		}
		for(int i = 1; i < N-1; i++) {
			for(int j = 1; j < N-1; j++) {
				z[1][i][j] = z[0][i][j] + c*c/2.0*dt*dt/(dd*dd)*(z[0][i+1][j]+z[0][i-1][j]+z[0][i][j+1]+z[0][i][j-1]-4.0*z[0][i][j]);
			}
		}
		for(int i = 0; i < N; i++) {
			z[1][i][0] = 0.0;
			z[1][i][N-1] = 0.0;
			z[1][0][i] = 0.0;
			z[1][N-1][i] = 0.0;
		}
	}

	public void simulate() {
		if (t < tmax) {
			for(int i = 1; i < N-1; i++) {
				for(int j = 1; j < N-1; j++) {
					z[2][i][j] = 2.0*z[1][i][j]-z[0][i][j]+c*c*dt*dt/(dd*dd)*(z[1][i+1][j]+z[1][i-1][j]+z[1][i][j+1]+z[1][i][j-1]-4.0*z[1][i][j]);
				}
			}
			for(int i = 0; i < N; i++) {
				z[2][i][0] = 0.0;
				z[2][i][N-1] = 0.0;
				z[2][0][i] = 0.0;
				z[2][N-1][i] = 0.0;
			}
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					z[0][i][j] = z[1][i][j];
					z[1][i][j] = z[2][i][j];
				}
			}
			t++;
		} else {
			isEnd = true;
		}
	}

}
