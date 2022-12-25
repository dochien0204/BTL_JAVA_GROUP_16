package common;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import computer.Computer;
import computer.ComputerPanel;
import housing.Housing;
import housing.HousingPanel;
import interior.Interior;
import interior.InteriorPanel;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MainView extends JFrame {
	public static List<Housing> housings = HousingPanel.generatedHousing(20);
	public static List<Computer> computers = ComputerPanel.generateComputers(20);
	public static List<Interior> interiors = InteriorPanel.generateInterior(20);

	static {
		try {
			FileUtil.binaryOutputFile("housing.bin", MainView.housings, null, null, null);
			FileUtil.binaryOutputFile("computer.bin", null, MainView.computers, null, null);
			FileUtil.binaryOutputFile("interior.bin", null, null, MainView.interiors,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    JFrame frame = new JFrame();
	private JPanel contentPane;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	HousingPanel housingPanel = new HousingPanel();
	ComputerPanel computerPanel = new ComputerPanel();
	InteriorPanel interiorPanel = new InteriorPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setTitle("Manager");
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 10, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 15));
		
		tabbedPane.setBounds(47, 20, 1000, 665);
		tabbedPane.add("Housing", housingPanel);
		tabbedPane.add("Computer", computerPanel);
		tabbedPane.add("Interior", interiorPanel);
		
//		frame.getContentPane().add(tabbedPane);
//
//        frame.setVisible(true);
		contentPane.add(tabbedPane);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 16));
		btnExit.setBounds(938, 695, 109, 45);
		getContentPane().add(btnExit);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginForm.frame.setVisible(true);
			}
		});
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogOut.setBackground(new Color(62, 158, 255));
		btnLogOut.setBounds(808, 695, 109, 45);
		contentPane.add(btnLogOut);

	}
}
