package common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import account.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SignUpForm extends JFrame {

	private JPanel contentPane;
	private static JTextField txtUserName;
	private static JPasswordField txtPassword;
	private static JPasswordField txtConfirm;
	public static SignUpForm frame = new SignUpForm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public SignUpForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 120, 671, 419);
		setResizable(false);
		setTitle("Sign Up");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 38));
		lblNewLabel.setBounds(255, 11, 144, 57);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(48, 87, 153, 37);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(48, 148, 153, 37);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(48, 212, 153, 37);
		contentPane.add(lblNewLabel_1_2);

		txtUserName = new JTextField();
		txtUserName.setBounds(228, 87, 340, 37);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(228, 148, 340, 37);
		contentPane.add(txtPassword);

		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(228, 212, 340, 37);
		contentPane.add(txtConfirm);

		JButton btnSubmit = new JButton("Sign Up");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtUserName.getText();
				String password = new String(txtPassword.getPassword());
				String confirm = new String(txtConfirm.getPassword());

				char[] chars = { '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '{',
						'}', '[', ']', ';', ':', '"', '<', '>', '?', '/', '\\', '|', '\'', ' ' };

				StringBuilder sb = new StringBuilder();

				if (userName.equals("")) {
					sb.append("UserName is empty\n");
				}
				if (password.equals("")) {
					sb.append("Password is empty\n");
				}
				if (confirm.equals("")) {
					sb.append("Confim Password is empty\n");
				} else if (confirm.compareTo(password) != 0) {
					sb.append("Confirm Password is not same as Password\n");
				}
				boolean userNameValid = false;
				boolean passwordValid = false;
				for(char ch : chars) {
					if(userName.indexOf(ch) != -1 && !userNameValid) {
						userNameValid = true;
						sb.append("User name just contains a-z, A-z and 0-9\n");
					}
					if(password.indexOf(ch) != -1 && !passwordValid) {
						passwordValid = true;
						sb.append("Password just contains a-z, A-z and 0-9\n");
					}
				}

				try {
					LoginForm.accs = FileUtil.binaryInputFileAccount("account.bin",
							FileUtil.countObject("account.bin"));
					for(Account acc : LoginForm.accs) {
						if(acc.getUserName().compareTo(userName) == 0) {
							sb.append("Username already exists");
							break;
						}
					}
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(contentPane, sb.toString(), "Invalidation",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Account newAccount = new Account(userName, password);
				LoginForm.accs.add(newAccount);
				try {
					FileUtil.binaryOutputFile("account.bin", null, null, null, LoginForm.accs);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "Sign Up successfully", "Successful", JOptionPane.PLAIN_MESSAGE);
				frame.setVisible(false);
				LoginForm.frame.setVisible(true);
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSubmit.setBounds(156, 279, 153, 44);
		contentPane.add(btnSubmit);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm.resetField();
				frame.setVisible(false);
				LoginForm.frame.setVisible(true);
			}
		});
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLogin.setBounds(332, 279, 153, 44);
		contentPane.add(btnLogin);
	}
	
	public static void resetField() {
		txtUserName.setText("");
		txtPassword.setText("");
		txtConfirm.setText("");
	}
}
