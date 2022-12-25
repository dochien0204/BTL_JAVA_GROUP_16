package computer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.FileUtil;
import common.MainView;

public class ComputerPanel extends JPanel {

	private ComputerManagerImpl computerManagerImpl = new ComputerManagerImpl();
	private static String idChoose;
	private DecimalFormat format = new DecimalFormat("#00,000,000.00");
	private static boolean isINC = true;

	private JPanel contentPane;
	private JTextField nameInput;
	private JLabel nameLable;
	private JTextField priceInput;
	private JLabel priceLable;
	private JTextField totalInput;
	private JLabel totalLable;
	private JTextField ramInput;
	private JLabel ramLable;
	private JTextField cpuInput;
	private JLabel cpuLable;
	private JTextField hardDriveInput;
	private JLabel hardDriveLable;
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel = new DefaultTableModel(){
	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	private JButton btnReset;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JComboBox cbSearch;
	private JTextField searchInput;
	private JButton btnSearch;
	private JComboBox cbSort;
	private JButton btnSort;
	private JTextField osInput;

	public static TopThreeComputer frameTop = new TopThreeComputer();
	
	public ComputerPanel() {
		try {
			FileUtil.countObject("computer.bin");
		} catch (Exception e) {
		}
		initTable();
		try {
			showTable(MainView.computers);
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		JLabel lblTitle = new JLabel("Computer Manager");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitle.setBounds(315, 20, 387, 41);
		add(lblTitle);

		totalLable = new JLabel("Total");
		totalLable.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLable.setFont(new Font("Arial", Font.BOLD, 16));
		totalLable.setBounds(43, 111, 90, 30);
		add(totalLable);

		nameLable = new JLabel("Name");
		nameLable.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLable.setFont(new Font("Arial", Font.BOLD, 16));
		nameLable.setBounds(43, 71, 90, 30);
		add(nameLable);

		priceLable = new JLabel("Price");
		priceLable.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLable.setFont(new Font("Arial", Font.BOLD, 16));
		priceLable.setBounds(43, 150, 90, 30);
		add(priceLable);

		ramLable = new JLabel("RAM");
		ramLable.setHorizontalAlignment(SwingConstants.RIGHT);
		ramLable.setFont(new Font("Arial", Font.BOLD, 16));
		ramLable.setBounds(43, 190, 90, 30);
		add(ramLable);

		cpuLable = new JLabel("CPU");
		cpuLable.setHorizontalAlignment(SwingConstants.RIGHT);
		cpuLable.setFont(new Font("Arial", Font.BOLD, 16));
		cpuLable.setBounds(43, 231, 90, 30);
		add(cpuLable);

		hardDriveLable = new JLabel("Hard Drive");
		hardDriveLable.setHorizontalAlignment(SwingConstants.RIGHT);
		hardDriveLable.setFont(new Font("Arial", Font.BOLD, 16));
		hardDriveLable.setBounds(43, 271, 90, 30);
		add(hardDriveLable);

		nameInput = new JTextField();
		nameInput.setFont(new Font("Arial", Font.PLAIN, 14));
		nameInput.setBounds(149, 71, 290, 30);
		nameInput.setColumns(10);
		add(nameInput);

		priceInput = new JTextField();
		priceInput.setFont(new Font("Arial", Font.PLAIN, 14));
		priceInput.setColumns(10);
		priceInput.setBounds(149, 151, 290, 30);
		add(priceInput);

		totalInput = new JTextField();
		totalInput.setFont(new Font("Arial", Font.PLAIN, 14));
		totalInput.setColumns(10);
		totalInput.setBounds(149, 111, 290, 30);
		add(totalInput);

		ramInput = new JTextField();
		ramInput.setFont(new Font("Arial", Font.PLAIN, 14));
		ramInput.setColumns(10);
		ramInput.setBounds(149, 191, 290, 30);
		add(ramInput);

		cpuInput = new JTextField();
		cpuInput.setFont(new Font("Arial", Font.PLAIN, 14));
		cpuInput.setColumns(10);
		cpuInput.setBounds(149, 231, 290, 30);
		add(cpuInput);

		hardDriveInput = new JTextField();
		hardDriveInput.setFont(new Font("Arial", Font.PLAIN, 14));
		hardDriveInput.setColumns(10);
		hardDriveInput.setBounds(149, 271, 290, 30);
		add(hardDriveInput);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addComputer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBounds(458, 71, 109, 45);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkDataInput()) {
					return;
				}
				try {
					editComputer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 16));
		btnEdit.setBounds(458, 126, 109, 45);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "You have not selected computer to delete", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				deleteComputer(row);
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
		btnDelete.setBounds(458, 181, 109, 45);
		add(btnDelete);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				nameInput.setText((String) table.getValueAt(row, 1));
				priceInput.setText((String) table.getValueAt(row, 2));
				totalInput.setText((String) table.getValueAt(row, 3));
				ramInput.setText((String) table.getValueAt(row, 5));
				cpuInput.setText((String) table.getValueAt(row, 4));
				hardDriveInput.setText((String) table.getValueAt(row, 6));
				osInput.setText(formatString((String) table.getValueAt(row, 7)));
				idChoose = (String) table.getValueAt(row, 0);
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 370, 953, 230);
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		add(scrollPane);
		scrollPane.setViewportView(table);

		table.setFont(new Font("Arial", Font.PLAIN, 13));
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

		table.setRowSorter(sorter);

		table.setBackground(new Color(255, 255, 255));
		table.setModel(tableModel);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(577, 71, 24, 270);
		add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBounds(598, 190, 349, 2);
		add(separator_2);

		separator = new JSeparator();
		separator.setBounds(20, 351, 953, 2);
		add(separator);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resetAll();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReset.setFont(new Font("Arial", Font.BOLD, 16));
		btnReset.setBounds(458, 236, 109, 45);
		add(btnReset);

		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchBy.setFont(new Font("Arial", Font.BOLD, 16));
		lblSearchBy.setBounds(577, 71, 90, 30);
		add(lblSearchBy);

		cbSearch = new JComboBox();
		cbSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSearch.addItem(new ComboItem("Search By Name", "name"));
		cbSearch.addItem(new ComboItem("Search By CPU", "cpu"));
		cbSearch.addItem(new ComboItem("Search By RAM", "ram"));
		cbSearch.setBounds(677, 71, 221, 30);
		add(cbSearch);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearch.setFont(new Font("Arial", Font.BOLD, 16));
		lblSearch.setBounds(577, 111, 90, 30);
		add(lblSearch);

		searchInput = new JTextField();
		searchInput.setFont(new Font("Arial", Font.PLAIN, 14));
		searchInput.setColumns(10);
		searchInput.setBounds(677, 111, 221, 30);
		add(searchInput);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchComputer();
			}
		});
		btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearch.setBounds(677, 149, 109, 30);
		add(btnSearch);
		
		JLabel lblSortBy = new JLabel("Sort");
		lblSortBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortBy.setFont(new Font("Arial", Font.BOLD, 16));
		lblSortBy.setBounds(588, 207, 75, 30);
		add(lblSortBy);
		
		cbSort = new JComboBox();
		cbSort.setMaximumRowCount(9);
		cbSort.addItem(new ComboItem("Sort By Price >= 10 Million", "price_10"));
		cbSort.addItem(new ComboItem("Sort By Price >= 20 Million", "price_20"));
		cbSort.addItem(new ComboItem("Sort By Price >= 30 Million", "price_30"));
		cbSort.addItem(new ComboItem("Sort By Name", "name"));
		cbSort.addItem(new ComboItem("Sort By Total", "total"));
		cbSort.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSort.setBounds(669, 207, 236, 31);
		add(cbSort);

		btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortComputer();
				isINC = !isINC;
			}
		});
		btnSort.setFont(new Font("Arial", Font.BOLD, 16));
		btnSort.setBounds(677, 248, 109, 30);
		add(btnSort);

		JLabel osLable = new JLabel("OS");
		osLable.setHorizontalAlignment(SwingConstants.RIGHT);
		osLable.setFont(new Font("Arial", Font.BOLD, 16));
		osLable.setBounds(0, 311, 133, 30);
		add(osLable);

		osInput = new JTextField();
		osInput.setFont(new Font("Arial", Font.PLAIN, 14));
		osInput.setColumns(10);
		osInput.setBounds(149, 311, 290, 30);
		add(osInput);
		
		JButton btnThongKe = new JButton("Report");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Computer> topThree = new ArrayList<>();
				try {
					List<Computer> c = FileUtil.binaryInputFileComputer("computer.bin", FileUtil.countObject("computer.bin"));
					Collections.sort(c, new sortByPrice().reversed());
					for(int i = 0; i < 3; i++){
						topThree.add(c.get(i));
					}
					
					FileUtil.binaryOutputFile("report.bin", null, topThree, null, null);
					List<Computer> test = FileUtil.binaryInputFileComputer("report.bin", FileUtil.countObject("report.bin"));
					frameTop.fillTable(topThree);
					frameTop.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnThongKe.setFont(new Font("Arial", Font.PLAIN, 14));
		btnThongKe.setBounds(834, 25, 120, 30);
		add(btnThongKe);
	}

	private void initTable() {
		String[] columnNames = { "ID", "Name", "Price", "Total", "CPU", "Ram", "Hard Drive", "OS" };
		tableModel.setColumnIdentifiers(columnNames);
		table.setModel(tableModel);
	}

	private void showTable(List<Computer> list) throws ClassNotFoundException, IOException {
		tableModel.setRowCount(0);
		for (Computer c : list) {
			tableModel.addRow(new String[] { c.getProduct_id(), c.getProduct_name(),
					format.format(c.getProduct_price()), String.valueOf(c.getProduct_total()), c.getTypeOfChip(),
					String.valueOf(c.getRam()), c.getHardDrive(), c.getOperatingSystem() });
		}
		table.setRowHeight(30);

		// Can giua cac cot trong table
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
			table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
		}
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		table.setAutoCreateRowSorter(true);
		tableModel.fireTableDataChanged();
	}

	public static String formatString(String s) {
		String str[] = s.split("");
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			if (str[i].compareTo(",") != 0) {
				newString.append(str[i]);
			}
		}
		return newString.toString();
	}

	public boolean checkDataInput() {
		if (!validation()) {
			return false;
		}
		StringBuilder sb = new StringBuilder();
		try {
			double price = Double.parseDouble(priceInput.getText());
			if (price < 10_000_000.0) {
				sb.append("Price must be more than 10 million VND");
			}
		} catch (Exception ex) {
			sb.append("Price must be real number");
		}

		try {
			int total = Integer.parseInt(totalInput.getText());
			if (total < 100) {
				sb.append("Số lượng >= 100");
			}
		} catch (Exception ex) {
			sb.append("Total must be an integer");
		}

		try {
			int ram = Integer.parseInt(ramInput.getText());
			if (ram < 0) {
				sb.append("RAM must be greater than 0");
			}
			if (ram % 4 != 0) {
				sb.append("RAM must be divisible by 4");
			}
		} catch (Exception ex) {
			sb.append("RAM must be an integer");
		}

		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(contentPane, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validation() {
		StringBuilder sb = new StringBuilder();
		if (nameInput.getText().equals("")) {
			sb.append("Name cannot be blank\n");
		}
		if (priceInput.getText().equals("")) {
			sb.append("Price cannot be blank\n");
		}
		if (totalInput.getText().equals("")) {
			sb.append("Total cannot be blank\n");
		}
		if (ramInput.getText().equals("")) {
			sb.append("RAM cannot be blank\n");
		}
		if (cpuInput.getText().equals("")) {
			sb.append("CPU cannot be blank\n");
		}
		if (hardDriveInput.getText().equals("")) {
			sb.append("Hard drive cannot be blank\n");
		}
		if (osInput.getText().equals("")) {
			sb.append("Operation system cannot be blank\n");
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(contentPane, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void resetField() {
		nameInput.setText("");
		priceInput.setText("");
		totalInput.setText("");
		ramInput.setText("");
		cpuInput.setText("");
		hardDriveInput.setText("");
		osInput.setText("");
		searchInput.setText("");
	}

	public static void writeToFile() throws IOException {
		FileUtil.binaryOutputFile("computer.bin", null, MainView.computers, null, null);
	}

	public void addComputer() throws IOException {
		if (!checkDataInput()) {
			return;
		}
		Computer computer = new Computer();
		computer.setProduct_id("C" + (MainView.computers.size() + 1));
		computer.setProduct_name(nameInput.getText().trim());
		computer.setProduct_price(Double.parseDouble(priceInput.getText().trim()));
		computer.setProduct_total(Integer.parseInt(totalInput.getText().trim()));
		computer.setRam(Integer.parseInt(ramInput.getText().trim()));
		computer.setTypeOfChip(cpuInput.getText().trim());
		computer.setHardDrive(hardDriveInput.getText().trim());
		computer.setOperatingSystem(osInput.getText().trim());
		for (Computer c : MainView.computers) {
			if (c.getProduct_name().equals(computer.getProduct_name())) {
				JOptionPane.showMessageDialog(contentPane, "Computer already exist", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		MainView.computers.add(computer);

		if (computerManagerImpl.addComputer(computer)) {
			JOptionPane.showMessageDialog(contentPane, "ADD computer successful", "Successful",
					JOptionPane.PLAIN_MESSAGE);
		}
		FileUtil.binaryOutputFile("computer.bin", null, MainView.computers, null, null);

		try {
			MainView.computers = FileUtil.binaryInputFileComputer("computer.bin", FileUtil.countObject("computer.bin"));
			showTable(MainView.computers);
			resetField();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public void editComputer() throws IOException {
		int choice = JOptionPane.showConfirmDialog(contentPane, "Are you sure you're up to date?", "Hỏi",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {
			resetField();
			return;
		}
		for (Computer computer : MainView.computers) {
			if (computer.getProduct_id().equals(idChoose)) {
				computer.setProduct_id(idChoose);
				computer.setProduct_name(nameInput.getText().trim());
				computer.setProduct_price(Double.parseDouble(priceInput.getText().trim()));
				computer.setProduct_total(Integer.parseInt(totalInput.getText().trim()));
				computer.setRam(Integer.parseInt(ramInput.getText().trim()));
				computer.setTypeOfChip(cpuInput.getText().trim());
				computer.setHardDrive(hardDriveInput.getText().trim());
				computer.setOperatingSystem(osInput.getText().trim());
				break;
			}
		}

		try {
			writeToFile();
			MainView.computers = FileUtil.binaryInputFileComputer("computer.bin", FileUtil.countObject("computer.bin"));
			showTable(MainView.computers);
			resetField();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(contentPane, "UPDATE computer successful", "Successful",
				JOptionPane.PLAIN_MESSAGE);
	}

	public void deleteComputer(int row) {
		int choice = JOptionPane.showConfirmDialog(contentPane,
				"Do you want to remove computer " + (String) table.getValueAt(row, 0), "Question",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {
			return;
		}
		for (Computer c : MainView.computers) {
			if (c.getProduct_id().equals((String) table.getValueAt(row, 0))) {
				if (computerManagerImpl.delComputer(c)) {
					MainView.computers.remove(c);
					JOptionPane.showMessageDialog(contentPane, "DELETE computer successful", "Successful",
							JOptionPane.PLAIN_MESSAGE);
					break;
				}
			}
		}
		try {
			writeToFile();
			MainView.computers = FileUtil.binaryInputFileComputer("computer.bin", FileUtil.countObject("computer.bin"));
			showTable(MainView.computers);
			resetField();
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void searchComputer() {
		Object item = cbSearch.getSelectedItem();
		String value = ((ComboItem) item).getValue();

		List<Computer> listSearch = new ArrayList<>();
		if (searchInput.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(contentPane, "Please enter search information", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String textInput = searchInput.getText().trim();
		if (value.compareTo("name") == 0) {
			listSearch = computerManagerImpl.searchComputer(searchInput.getText());
		} else if (value.compareTo("cpu") == 0) {
			listSearch = computerManagerImpl.searchComputerByCPU(searchInput.getText());
		} else if (value.compareTo("ram") == 0) {
			try {
				int ram = Integer.parseInt(textInput);
				if (ram <= 0) {
					JOptionPane.showMessageDialog(contentPane, "RAM must be greater than 0 and divisible by 4", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				listSearch = computerManagerImpl.searchComputerByRam(ram);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(contentPane, "Price must be an integer", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		try {
			showTable(listSearch);
			searchInput.setText("");
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void sortComputer() {
		int totalRow = table.getRowCount();
		List<Computer> listSort = new ArrayList<>();
		for (int i = 0; i < totalRow; i++) {
			Computer c = new Computer();
			nameInput.setText((String) table.getValueAt(i, 1));
			totalInput.setText((String) table.getValueAt(i, 2));
			cpuInput.setText((String) table.getValueAt(i, 3));
			ramInput.setText((String) table.getValueAt(i, 4));
			hardDriveInput.setText((String) table.getValueAt(i, 5));
			osInput.setText((String) table.getValueAt(i, 6));
			priceInput.setText(formatString((String) table.getValueAt(i, 7)));
			listSort.add(c);
		}

		Object itemSort = cbSort.getSelectedItem();
		String valueSort = ((ComboItem) itemSort).getValue();

		if (valueSort.equals("price_10")) {
			listSort = computerManagerImpl.sortedComputer(10_000_000.00);
		} else if (valueSort.equals("price_20")) {
			listSort = computerManagerImpl.sortedComputer(20_000_000.00);
		} else if (valueSort.equals("price_30")) {
			listSort = computerManagerImpl.sortedComputer(30_000_000.00);
		} else if (valueSort.equals("name")) {
			listSort = computerManagerImpl.sortedComputerByName(isINC);
		} else {
			listSort = computerManagerImpl.sortedComputerByTotalQuantity(isINC);
		}

		try {
			showTable(listSort);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void resetAll() throws ClassNotFoundException, IOException {
		resetField();
		showTable(MainView.computers);
	}

	public static List<Computer> generateComputers(int n) {
		List<Computer> list = new ArrayList<>();

		// Danh sach ten
		String[] names = { "Asus TUF Gaming", "Lenovo Gaming IdeaPad", "Asus Vivobook", "HP Pavilion",
				"Lenovo IdeaPad 5", "Dell Gaming G3" };
		// Danh sach cpu
		String[] cpu = { "Intel i5", "Intel i7" };

		// Danh sach o cung
		String[] hardDrives = { "256GB SSD", "256GB HDD", "512GB SSD", "512GB HDD", "1TB HDD" };

		// Danh sach he dieu hanh
		String[] os = { "Windows 10", "Windows 11", "Linux" };

		int index;
		for (int i = 0; i < n; i++) {
			Computer computer = new Computer();
			computer.setProduct_id("C" + (i + 1));

			// Sinh ten
			index = (int) (Math.random() * names.length);
			computer.setProduct_name(names[index]);

			// Sinh so luong
			index = 100 * (int) (Math.random() * 6) + 100;
			computer.setProduct_total(index);

			// Sinh gia
			double price = 10_000_000.0 * (int) ((Math.random() * 3) + 1) + 100_000.0 * (int) (Math.random() * 10)
					+ 10_000.0 * (int) (Math.random() * 10);
			computer.setProduct_price(price);

			// Sinh ram
			index = 4 * (int) (Math.random() * 6) + 4;
			computer.setRam(index);

			// Sinh cpu
			index = (int) (Math.random() * cpu.length);
			computer.setTypeOfChip(cpu[index]);

			// Sinh hard drive
			index = (int) (Math.random() * hardDrives.length);
			computer.setHardDrive(hardDrives[index]);

			// Sinh ten
			index = (int) (Math.random() * os.length);
			computer.setOperatingSystem(os[index]);

			list.add(computer);
		}
//		FileUtil.binaryOutputFile("computer.bin", null, MainView.computers, null);
		return list;
	}

}
