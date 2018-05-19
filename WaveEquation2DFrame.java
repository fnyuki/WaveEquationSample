package waveequation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WaveEquation2DFrame extends JFrame implements ActionListener {

	private int N = 51;
	private JTextField tField = new JTextField("0.5");	// Δt
	private JTextField cField = new JTextField("0.1");	// 波速度
	private JTextField xField = new JTextField("50");	// x位置
	private JTextField yField = new JTextField("50");	// y位置
	private JButton simBtn = new JButton("simulate");
	private WaveEquation2DComponent comp = new WaveEquation2DComponent();

	public WaveEquation2DFrame() {
		super("波動方程式2次元版");
		Container cont = getContentPane();
		cont.setLayout(null);
		comp.setLocation(0,0);
		comp.setSize(600,600);
		cont.add(comp);
		JLabel tLabel = new JLabel("delta t = ");
		tLabel.setLocation(632,32);
		tLabel.setSize(100,32);
		cont.add(tLabel);
		tField.setLocation(732,32);
		tField.setSize(100,32);
		cont.add(tField);
		JLabel cLabel = new JLabel("velocity = ");
		cLabel.setLocation(632,80);
		cLabel.setSize(100,32);
		cont.add(cLabel);
		cField.setLocation(732,80);
		cField.setSize(100,32);
		cont.add(cField);
		JLabel xLabel = new JLabel("x(%) = ");
		xLabel.setLocation(632,128);
		xLabel.setSize(100,32);
		cont.add(xLabel);
		xField.setLocation(732,128);
		xField.setSize(100,32);
		cont.add(xField);
		JLabel yLabel = new JLabel("y(%) = ");
		yLabel.setLocation(632,176);
		yLabel.setSize(100,32);
		cont.add(yLabel);
		yField.setLocation(732,176);
		yField.setSize(100,32);
		cont.add(yField);
		simBtn.setLocation(632,224);
		simBtn.setSize(200,100);
		cont.add(simBtn);
		simBtn.addActionListener(this);
		setSize(864,610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String [] args) {
		new WaveEquation2DFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == simBtn) {
			try {
				double dt = Double.parseDouble(tField.getText());
				double c = Double.parseDouble(cField.getText());
				int x = Integer.parseInt(xField.getText());
				int y = Integer.parseInt(yField.getText());
				comp.simulate(dt, c, x, y);
			}
			catch(NumberFormatException nfe) {}
		}
	}

}