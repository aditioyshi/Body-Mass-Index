package BMI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Splash extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel percentLBL;
	private JProgressBar pBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Splash() {
		design();
		middle();
		tm.start();
	}

	public void middle() {

		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}

	Timer tm = new Timer(47, this);
	int time = 0;
	private JLabel lblWelcome;

	public void logic() {
		time++;
		pBar.setValue(time);
		percentLBL.setText(time + "%");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (time != 100) {
			logic();
		} else {
			tm.stop();
			Login frame = new Login();
			frame.setVisible(true);
			dispose();
		}
		repaint();
	}

	private void design() {
		setResizable(false);
		setTitle("BMI Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		percentLBL = new JLabel("%");
		percentLBL.setForeground(SystemColor.control);
		percentLBL.setHorizontalAlignment(SwingConstants.CENTER);
		percentLBL.setBounds(163, 210, 119, 36);
		contentPane.add(percentLBL);

		pBar = new JProgressBar();
		pBar.setBackground(SystemColor.windowBorder);
		pBar.setForeground(SystemColor.textHighlight);
		pBar.setBounds(10, 210, 424, 36);
		contentPane.add(pBar);

		lblWelcome = new JLabel("");
		lblWelcome.setIcon(new ImageIcon(Splash.class.getResource("/Files/welcome.gif")));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(35, 24, 370, 176);
		contentPane.add(lblWelcome);
	}
}
