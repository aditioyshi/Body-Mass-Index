package BMI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Signup extends JFrame {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;

	String gender = "";

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField emailTF;
	private JLabel lblUsername;
	private JTextField usernameTF;
	private JLabel lblPassword;
	private JLabel lblConfirmPass;
	private JTextField contactTF;
	private JLabel lblContact;
	private JLabel lblAddress;
	private JTextField addressTF;
	private JLabel lblDateOfBirth;
	private JLabel lblHeight;
	private JTextField heightTF;
	private JTextField weightTF;
	private JLabel lblWeight;
	private JLabel lblGender;
	private JPasswordField passF1;
	private JPasswordField passF2;
	@SuppressWarnings("rawtypes")
	private JComboBox genderCB;
	private JDateChooser dobF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {

		design();
		middle();
		conn = SQLConnection.ConnecrDb();
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

	@SuppressWarnings("deprecation")
	private void signUP() {

		try {
			String query = "INSERT INTO user (name,email,username,password,contact,address,dob,height,weight,gender) VALUES (?,?,?,?,?,?,?,?,?,?)";

			pst = conn.prepareStatement(query);
			pst.setString(1, nameTF.getText());
			pst.setString(2, emailTF.getText());
			pst.setString(3, usernameTF.getText());
			if (passF1.getText().equals(passF2.getText())) {
				pst.setString(4, passF1.getText());
			} else {
				JOptionPane.showMessageDialog(null, "Password not Matched");
			}
			pst.setString(5, contactTF.getText());
			pst.setString(6, addressTF.getText());
			pst.setString(7, ((JTextField) dobF.getDateEditor().getUiComponent()).getText());
			pst.setString(8, heightTF.getText());
			pst.setString(9, weightTF.getText());
			pst.setString(10, genderCB.getSelectedItem().toString());

			pst.execute();
			pst.close();

			JOptionPane.showMessageDialog(null, "Registration Completed");

			Login frame = new Login();
			frame.setVisible(true);
			dispose();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Registration Failed");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private void signupChecker() {
		if (nameTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Name First");
		} else if (emailTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Email");
		} else if (usernameTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Username");
		} else if (!passF1.getText().equals((passF2.getText()))) {
			JOptionPane.showMessageDialog(null, "Password Does Not Matched.");
		} else if (contactTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Contact");
		} else if (addressTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Address");
		} else if (((JTextField) dobF.getDateEditor().getUiComponent()).getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Select Date of Birth");
		} else if (heightTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Hegiht");
		} else if (weightTF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Weight");
		} else if (genderCB.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(null, "Select Gender to Continues");
		} else {
			signUP();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void design() {
		setResizable(false);
		setTitle("Signup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameTF = new JTextField();
		nameTF.setBounds(180, 54, 191, 20);
		contentPane.add(nameTF);
		nameTF.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(78, 57, 75, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(78, 83, 75, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);

		emailTF = new JTextField();
		emailTF.setBounds(180, 80, 191, 20);
		emailTF.setColumns(10);
		contentPane.add(emailTF);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(78, 111, 75, 14);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUsername);

		usernameTF = new JTextField();
		usernameTF.setBounds(180, 108, 191, 20);
		usernameTF.setColumns(10);
		contentPane.add(usernameTF);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(78, 137, 75, 14);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPassword);

		lblConfirmPass = new JLabel("Confirm Pass");
		lblConfirmPass.setBounds(67, 167, 103, 14);
		lblConfirmPass.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblConfirmPass);

		contactTF = new JTextField();
		contactTF.setBounds(180, 190, 191, 20);
		contactTF.setColumns(10);
		contentPane.add(contactTF);

		lblContact = new JLabel("Contact");
		lblContact.setBounds(78, 193, 75, 14);
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblContact);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(78, 222, 75, 14);
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAddress);

		addressTF = new JTextField();
		addressTF.setBounds(180, 219, 191, 20);
		addressTF.setColumns(10);
		contentPane.add(addressTF);

		lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(78, 286, 75, 14);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDateOfBirth);

		lblHeight = new JLabel("Height (inch)");
		lblHeight.setBounds(78, 317, 75, 14);
		lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblHeight);

		heightTF = new JTextField();
		heightTF.setBounds(180, 314, 191, 20);
		heightTF.setHorizontalAlignment(SwingConstants.CENTER);
		heightTF.setColumns(10);
		contentPane.add(heightTF);

		weightTF = new JTextField();
		weightTF.setBounds(180, 340, 191, 20);
		weightTF.setHorizontalAlignment(SwingConstants.CENTER);
		weightTF.setColumns(10);
		contentPane.add(weightTF);

		lblWeight = new JLabel("Weight (foot)");
		lblWeight.setBounds(78, 343, 75, 14);
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWeight);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(78, 374, 75, 14);
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGender);

		genderCB = new JComboBox();
		genderCB.setBounds(180, 371, 191, 17);
		genderCB.setModel(new DefaultComboBoxModel(new String[] { "-", "Male", "Female" }));
		contentPane.add(genderCB);

		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupChecker();
			}
		});
		btnSignup.setBounds(109, 435, 89, 23);
		contentPane.add(btnSignup);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(249, 435, 89, 23);
		contentPane.add(btnBack);

		dobF = new JDateChooser();
		dobF.setDateFormatString("yyyy-MM-dd");
		dobF.setBounds(180, 280, 191, 20);
		contentPane.add(dobF);

		passF1 = new JPasswordField();
		passF1.setBounds(180, 136, 191, 20);
		contentPane.add(passF1);

		passF2 = new JPasswordField();
		passF2.setBounds(180, 164, 191, 20);
		contentPane.add(passF2);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(67, 36, 320, 218);
		horizontalBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		contentPane.add(horizontalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBounds(67, 260, 320, 159);
		horizontalBox_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		contentPane.add(horizontalBox_1);
	}
}
