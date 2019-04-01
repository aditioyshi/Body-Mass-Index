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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Login extends JFrame {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;

	protected static int serial = 1;
	protected static double height = 0.0;
	protected static double weight = 0.0;
	protected static int age = 0;
	protected static String gender = "";
	protected static String name = "Guest";

	private JPanel contentPane;
	private JTextField usernameTF;
	private JPasswordField passwordF;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton userRB;
	private JRadioButton adminRB;
	private JButton btnSignup;
	private JLabel clockLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {

		design();

		conn = SQLConnection.ConnecrDb();
		middle();
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

	/*
	 * protected static int calculateAge(age, LocalDate currentDate) { if ((age !=
	 * null) && currentDate != null) { return Period.between(age,
	 * currentDate).getYears(); } else { return 0; } }
	 */

	@SuppressWarnings("deprecation")
	private void login() {

		try {
			String query = "select * from user where username=? and password=?";

			pst = conn.prepareStatement(query);
			pst.setString(1, usernameTF.getText());
			pst.setString(2, passwordF.getText());

			rs = pst.executeQuery();

			if (rs.next()) {

				serial = rs.getInt("serial");
				name = rs.getString("name");
				height = rs.getDouble("height");
				weight = rs.getDouble("weight");
				gender = rs.getString("gender");
				// JOptionPane.showMessageDialog(null, "Logged in successfully");

				BMICalculator bc = new BMICalculator();
				bc.setVisible(true);
				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Username or Password is Wrong");
			}
			rs.close();
			pst.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);

		}

	}

	@SuppressWarnings("deprecation")
	private void loginAdmin() {

		try {
			String query = "select * from admin where username=? and password=?";

			pst = conn.prepareStatement(query);
			pst.setString(1, usernameTF.getText());
			pst.setString(2, passwordF.getText());

			rs = pst.executeQuery();

			if (rs.next()) {

				name = rs.getString("name");
				// JOptionPane.showMessageDialog(null, "Logged in successfully");

				AdminPanel frame = new AdminPanel();
				frame.setVisible(true);
				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Username or Password is Wrong");
			}
			rs.close();
			pst.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);

		}

	}

	@SuppressWarnings("deprecation")
	private void checkEmptyFields() {
		if (usernameTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Username First");
		} else if (passwordF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Password to Continue");
		} else {
			login();
		}
	}

	@SuppressWarnings("deprecation")
	private void checkEmptyFieldsforAdmin() {
		if (usernameTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Admin Username First");
		} else if (passwordF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Password to Continue");
		} else {
			loginAdmin();

		}
	}

	private void checkUser() {
		if (userRB.isSelected()) {
			checkEmptyFields();
		} else if (adminRB.isSelected()) {
			checkEmptyFieldsforAdmin();

		} else {
			JOptionPane.showMessageDialog(null, "Select an Option for Login");
		}
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
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernameTF = new JTextField();
		usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
		usernameTF.setBounds(46, 25, 172, 39);
		contentPane.add(usernameTF);
		usernameTF.setColumns(10);

		passwordF = new JPasswordField();
		passwordF.setHorizontalAlignment(SwingConstants.CENTER);
		passwordF.setBounds(46, 85, 172, 39);
		contentPane.add(passwordF);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkUser();
			}
		});
		loginBtn.setBounds(90, 193, 89, 23);
		contentPane.add(loginBtn);

		userRB = new JRadioButton("User");
		buttonGroup.add(userRB);
		userRB.setBounds(149, 148, 70, 23);
		contentPane.add(userRB);

		adminRB = new JRadioButton("Admin");
		buttonGroup.add(adminRB);
		adminRB.setBounds(46, 148, 70, 23);
		contentPane.add(adminRB);

		JButton bmiBtn = new JButton("Fast Calculate");
		bmiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BMICalculator bc = new BMICalculator();
				bc.setVisible(true);
				bc.btnSaveResult.setEnabled(false);
				bc.btnHistory.setEnabled(false);
				bc.logoutBtn.setEnabled(false);
				bc.btnBack.setVisible(true);
				dispose();
			}
		});
		bmiBtn.setBounds(130, 227, 114, 23);
		contentPane.add(bmiBtn);

		btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup frame = new Signup();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSignup.setBounds(22, 227, 89, 23);
		contentPane.add(btnSignup);

		clockLbl = new JLabel("Clock");
		clockLbl.setBounds(276, 11, 141, 23);
		contentPane.add(clockLbl);
	}
}
