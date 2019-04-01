package BMI;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BMIHistory extends JFrame {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection conn = null;

	protected static int id = 1;
	protected double BMI = 0.0;
	protected double BMIRes = 0.0;

	private JPanel contentPane;
	private JLabel lblWelcome;
	private JTable historyTable;
	private JLabel BMIResLbl;
	private JLabel BMIHintLbl;
	private JLabel ageLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMIHistory frame = new BMIHistory();
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
	public BMIHistory() {
		setResizable(false);
		setTitle("History");
		design();
		conn = SQLConnection.ConnecrDb();
		middle();
		loadData();
		loadTable();
		calcualteAverage();
		ageCalculate();

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
	private void loadData() {
		Login l = new Login();
		lblWelcome.setText("Welcome " + l.name);
		id = l.serial;
	}

	private void ageCalculate() {

		try {
			String query = "SELECT YEAR(CURRENT_TIMESTAMP) - YEAR(user.dob) - (RIGHT(CURRENT_TIMESTAMP, 5) < RIGHT(user.dob, 5)) as age FROM user WHERE serial = '"
					+ id + "'";
			pst = conn.prepareStatement(query);

			rs = pst.executeQuery();

			while (rs.next()) {
				String age = rs.getString("age");
				ageLbl.setText("You Are " + age + " Years Old");
			}

			pst.close();
			rs.close();

			BMIHint();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No History Available");
		}

	}

	private void loadTable() {

		try {
			String query = "select BMI,insertDate FROM history WHERE serial_FK = '" + id + "'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

			historyTable.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void calcualteAverage() {

		try {
			String query = "select AVG(BMI) FROM History WHERE serial_FK = '" + id + "'";
			pst = conn.prepareStatement(query);

			rs = pst.executeQuery();

			while (rs.next()) {
				String avg = rs.getString("AVG(BMI)");
				DecimalFormat df = new DecimalFormat(".#");
				BMIResLbl.setText(df.format(Double.parseDouble(avg)));
			}

			pst.close();
			rs.close();

			BMIHint();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No History Available");
		}

	}

	private void BMIHint() {
		BMIRes = Double.parseDouble(BMIResLbl.getText());
		if (BMIRes > 1 && BMIRes < 18.5) {
			BMIHintLbl.setText("Under Weight");
		} else if (BMIRes >= 18.5 && BMIRes <= 24.9) {
			BMIHintLbl.setText("Healthy Weight");
		} else if (BMIRes >= 25 && BMIRes <= 29.9) {
			BMIHintLbl.setText("Over Weight");
		} else if (BMIRes > 30) {
			BMIHintLbl.setText("Obese");
		} else {
			BMIHintLbl.setText("Calculate BMI First");
		}
	}

	private void design() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(10, 11, 196, 23);
		contentPane.add(lblWelcome);

		JScrollPane historyTableScroll = new JScrollPane();
		historyTableScroll.setBounds(10, 78, 196, 182);
		contentPane.add(historyTableScroll);

		historyTable = new JTable();
		historyTableScroll.setViewportView(historyTable);

		JLabel lblYourAverageBmi = new JLabel("Your Average BMI is");
		lblYourAverageBmi.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAverageBmi.setBounds(252, 28, 160, 33);
		contentPane.add(lblYourAverageBmi);

		BMIResLbl = new JLabel("-");
		BMIResLbl.setForeground(Color.BLUE);
		BMIResLbl.setHorizontalAlignment(SwingConstants.CENTER);
		BMIResLbl.setBounds(252, 72, 160, 33);
		contentPane.add(BMIResLbl);

		BMIHintLbl = new JLabel("-");
		BMIHintLbl.setHorizontalAlignment(SwingConstants.CENTER);
		BMIHintLbl.setForeground(Color.BLUE);
		BMIHintLbl.setBounds(252, 156, 160, 33);
		contentPane.add(BMIHintLbl);

		JLabel lblAndYouAre = new JLabel("and You are");
		lblAndYouAre.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndYouAre.setBounds(252, 112, 160, 33);
		contentPane.add(lblAndYouAre);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BMICalculator frame = new BMICalculator();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(285, 216, 89, 23);
		contentPane.add(btnBack);

		ageLbl = new JLabel("");
		ageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ageLbl.setBounds(10, 44, 196, 23);
		contentPane.add(ageLbl);
	}
}
