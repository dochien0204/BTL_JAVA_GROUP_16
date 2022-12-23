package common;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import account.Account;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class LoginForm extends JFrame {

	public static List<Account> accs = new ArrayList<>();
	private JPanel contentPane;
	private static JTextField txtUserName;
	private static JPasswordField txtPassword;
	public static LoginForm frame = new LoginForm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					initAccount();
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
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login To System");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setBounds(212, 26, 290, 61);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(78, 105, 108, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(78, 156, 108, 38);
		contentPane.add(lblNewLabel_1_1);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtUserName.setBounds(212, 101, 353, 36);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JCheckBox checkbox = new JCheckBox("Remember Me");
		checkbox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		checkbox.setBounds(212, 218, 108, 23);
		contentPane.add(checkbox);

		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = txtUserName.getText();

				String password = new String(txtPassword.getPassword());

				StringBuilder sb = new StringBuilder();

				if (userName.equals("")) {
					sb.append("UserName is empty!\n");
				}
				if (password.equals("")) {
					sb.append("Password is empty");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(contentPane, sb.toString(), "Invalidation",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				List<Account> accounts = LoginForm.getListAccountFromFile();
				boolean isSuccess = false;
				for (Account acc : accounts) {
					if (acc.getUserName().equals(userName) && acc.getPassword().equals(password)) {
						MainView mv = new MainView();
						mv.setVisible(true);
						frame.setVisible(false);
						isSuccess = true;
						return;
					}
				}
				if(!isSuccess) {
					JOptionPane.showMessageDialog(contentPane, "Invalid UserName or Password", "Invalidation",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		btnSignIn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSignIn.setBounds(163, 283, 157, 38);
		contentPane.add(btnSignIn);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetField();
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReset.setBounds(350, 283, 157, 38);
		contentPane.add(btnReset);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPassword.setBounds(212, 157, 353, 36);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_2 = new JLabel("You don't have an account?");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(212, 258, 157, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel signUp = new JLabel("Sign Up Now !");
		signUp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR); 
				signUp.setCursor(cursor);
			}
		});
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUpForm.resetField();
				frame.setVisible(false);
				SignUpForm.frame.setVisible(true);
			}
		});
		signUp.setForeground(new Color(0, 0, 255));
		signUp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		signUp.setBounds(378, 258, 157, 14);
		contentPane.add(signUp);
	}

	public static void initAccount() {
		Account acc1 = new Account("admin", "admin");
		Account acc2 = new Account("dochien0204", "chien123");
		Account acc3 = new Account("thangnv", "thang123");
		Account acc4 = new Account("vanviet", "viet123");
		Account acc5 = new Account("lahuyhoang", "hoang123");
		accs.add(acc1);
		accs.add(acc2);
		accs.add(acc3);
		accs.add(acc4);
		accs.add(acc5);

		try {
			FileUtil.binaryOutputFile("account.bin", null, null, null, accs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Account> getListAccountFromFile() {
		List<Account> list = new ArrayList<>();
		try {
			list = FileUtil.binaryInputFileAccount("account.bin", FileUtil.countObject("account.bin"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void resetField() {
		txtUserName.setText("");
		txtPassword.setText("");
	}
}
