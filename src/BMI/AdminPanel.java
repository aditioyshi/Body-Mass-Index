package BMI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AdminPanel extends JFrame {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;

	private JPanel contentPane;
	private JLabel welcomeLbl;
	private JLabel clockLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {

		design();
		middle();
		conn = SQLConnection.ConnecrDb();
		loadValues();
		clock();

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

	@SuppressWarnings("static-access")
	private void loadValues() {

		Login l = new Login();
		welcomeLbl.setText("Welcome  " + l.name);

		// System.out.println(id);

	}

	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm a");
						Date date = cal.getTime();
						String timeString = formatter.format(date);
						clockLbl.setText(timeString);
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

	private void design() {
		setResizable(false);
		setTitle("AdminPanel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		welcomeLbl = new JLabel("");
		welcomeLbl.setBounds(10, 11, 169, 27);
		contentPane.add(welcomeLbl);

		clockLbl = new JLabel("Clock");
		clockLbl.setBounds(265, 17, 169, 19);
		contentPane.add(clockLbl);
	}
}
