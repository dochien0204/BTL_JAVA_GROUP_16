package housing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.FileUtil;
import common.MainView;

public class HousingPanel extends JPanel {

//	public static List<Housing> list = new ArrayList<>();
	private HousingManagerImpl housingManager = new HousingManagerImpl();
	Locale locale = new Locale("en", "EN");
	DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	private static String idChoose;

	public static JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textTotal;
	private JTextField textArea;
	private JTextField textLocation;
	private JTextField textOwner;
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JButton btnReset;
	private JSeparator separator_1;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JLabel lblNewLabel_8;
	private JTextField textSearch;
	private JButton btnSearch;
	private JLabel lblNewLabel_9;
	private JComboBox cbSort;
	private JButton btnSort;
	private JComboBox cbIsNC;
	private JSeparator separator_2;
	public static TopThreeHousing frameTop = new TopThreeHousing();


	public HousingPanel() {
		initTable();
		try {
			MainView.housings = FileUtil.binaryInputFileHousing("housing.bin", FileUtil.countObject("housing.bin"));
			fillTable(MainView.housings);
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
//		setResizable(false);
		contentPane = new JPanel();

//		setContentPane(contentPane);
		setLayout(null);

		JLabel lblTitle = new JLabel("Housing Manager");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitle.setBounds(320, 20, 357, 41);
		add(lblTitle);

		lblNewLabel_2 = new JLabel("Total");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(43, 111, 90, 30);
		add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(43, 71, 90, 30);
		add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Price ($)");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(43, 150, 90, 30);
		add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Area (m²)");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5.setBounds(43, 190, 90, 30);
		add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Location");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6.setBounds(43, 231, 90, 30);
		add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Owner");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_7.setBounds(43, 271, 90, 30);
		add(lblNewLabel_7);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(149, 71, 290, 30);
		add(textName);

		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(149, 151, 290, 30);
		add(textPrice);

		textTotal = new JTextField();
		textTotal.setColumns(10);
		textTotal.setBounds(149, 111, 290, 30);
		add(textTotal);

		textArea = new JTextField();
		textArea.setColumns(10);
		textArea.setBounds(149, 191, 290, 30);
		add(textArea);

		textLocation = new JTextField();
		textLocation.setColumns(10);
		textLocation.setBounds(149, 231, 290, 30);
		add(textLocation);

		textOwner = new JTextField();
		textOwner.setColumns(10);
		textOwner.setBounds(149, 271, 290, 30);
		add(textOwner);

		// event add
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkValidData()) {
					return;
				}
				Housing hs = new Housing();
				hs.setProduct_id("HS".concat(String.valueOf(MainView.housings.size() + 1)));
				hs.setProduct_name(textName.getText().trim());
				hs.setProduct_price(Double.valueOf(textPrice.getText().trim()));
				hs.setProduct_total(Integer.valueOf(textTotal.getText().trim()));
				hs.setArea(Double.valueOf(textArea.getText().trim()));
				hs.setLocation(textLocation.getText().trim());
				hs.setOwner(textOwner.getText().trim());
				for (Housing housing : MainView.housings) {
					if (hs.getProduct_name().compareTo(housing.getProduct_name()) == 0) {
						JOptionPane.showMessageDialog(contentPane, "Housing already exist", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				housingManager.addHousing(hs);
				JOptionPane.showMessageDialog(contentPane, "ADD housing successful", "Successful",
						JOptionPane.PLAIN_MESSAGE);
				try {
					MainView.housings = FileUtil.binaryInputFileHousing("housing.bin",
							FileUtil.countObject("housing.bin"));
					fillTable(MainView.housings);
					resetField();
				} catch (ClassNotFoundException | IOException e1) {
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
				if (!checkValidData()) {
					return;
				}
				int choice = JOptionPane.showConfirmDialog(contentPane, "Are you sure you're up to date?", "Hỏi",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {
					resetField();
					return;
				}
				for (Housing housing : MainView.housings) {
					if (housing.getProduct_id().compareTo(idChoose) == 0) {
						housing.setProduct_id(idChoose);
						housing.setProduct_name(textName.getText().trim());
						housing.setProduct_price(Double.valueOf(textPrice.getText().trim()));
						housing.setProduct_total(Integer.valueOf(textTotal.getText().trim()));
						housing.setArea(Double.valueOf(textArea.getText().trim()));
						housing.setLocation(textLocation.getText().trim());
						housing.setOwner(textOwner.getText().trim());
						break;
					}
				}

				try {
					writeToFile();
					MainView.housings = FileUtil.binaryInputFileHousing("housing.bin",
							FileUtil.countObject("housing.bin"));
					fillTable(MainView.housings);
					resetField();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "UPDATE housing successful", "Successful",
						JOptionPane.PLAIN_MESSAGE);
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
					JOptionPane.showMessageDialog(contentPane, "You have not selected housing to delete", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int choice = JOptionPane.showConfirmDialog(contentPane,
						"Do you want to remove housing " + (String) table.getValueAt(row, 0), "Question",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {
					return;
				}
				for (Housing hs : MainView.housings) {
					if (hs.getProduct_id().compareTo((String) table.getValueAt(row, 0)) == 0) {
						housingManager.delHousing(hs);
						break;
					}
				}
				try {
					writeToFile();
					MainView.housings = FileUtil.binaryInputFileHousing("housing.bin",
							FileUtil.countObject("housing.bin"));
					fillTable(MainView.housings);
					resetField();
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
		btnDelete.setBounds(458, 181, 109, 45);
		add(btnDelete);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 351, 1023, 2);
		add(separator);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textName.setText((String) table.getValueAt(row, 1));
				textPrice.setText(HousingPanel.formatString((String) table.getValueAt(row, 2)));
				textTotal.setText((String) table.getValueAt(row, 3));
				textArea.setText((String) table.getValueAt(row, 4));
				textLocation.setText((String) table.getValueAt(row, 5));
				textOwner.setText((String) table.getValueAt(row, 6));
				idChoose = (String) table.getValueAt(row, 0);
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 370, 953, 301);
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

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});
		btnReset.setFont(new Font("Arial", Font.BOLD, 16));
		btnReset.setBounds(458, 236, 109, 45);
		add(btnReset);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(577, 71, 24, 270);
		add(separator_1);

		lblNewLabel_1 = new JLabel("Search By");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(577, 71, 90, 30);
		add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBox.addItem(new ComboItem("Search By Name", "name"));
		comboBox.addItem(new ComboItem("Search By Price", "price"));
		comboBox.addItem(new ComboItem("Search By Area", "area"));
		comboBox.addItem(new ComboItem("Search By Location", "location"));
		comboBox.addItem(new ComboItem("Search By Owner", "owner"));
		comboBox.setBounds(677, 71, 268, 30);
		add(comboBox);

		lblNewLabel_8 = new JLabel("Search");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_8.setBounds(577, 111, 90, 30);
		add(lblNewLabel_8);

		textSearch = new JTextField();
		textSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		textSearch.setColumns(10);
		textSearch.setBounds(677, 111, 268, 30);
		add(textSearch);
		textSearch.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = comboBox.getSelectedItem();
				String value = ((ComboItem) item).getValue();
				List<Housing> listSearch = new ArrayList<>();
				if (textSearch.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(contentPane, "Please enter search information", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String textInput = textSearch.getText().trim();
				if (value.compareTo("name") == 0) {
					listSearch = housingManager.searchHousingByName(textSearch.getText());
				} else if (value.compareTo("price") == 0) {
					try {
						double price = Double.parseDouble(textInput);
						if (price <= 0) {
							JOptionPane.showMessageDialog(contentPane, "Price must be greater than 0", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						listSearch = housingManager.searchHousingByPrice(price);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(contentPane, "Price must be real number", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else if (value.compareTo("area") == 0) {
					try {
						double area = Double.parseDouble(textInput);
						if (area <= 0) {
							JOptionPane.showMessageDialog(contentPane, "Area must be greater than 0", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						listSearch = housingManager.searchHousingByArea(area);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(contentPane, "Area must be real number", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else if (value.compareTo("owner") == 0) {
					listSearch = housingManager.searchHousingByOwner(textInput);
				} else if (value.compareTo("location") == 0) {
					listSearch = housingManager.searchHousingByLocation(textInput);
				}

				try {
					fillTable(listSearch);
					textSearch.setText("");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearch.setBounds(677, 149, 109, 30);
		add(btnSearch);

		lblNewLabel_9 = new JLabel("Sort By");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(577, 202, 90, 30);
		add(lblNewLabel_9);

		cbSort = new JComboBox();
		cbSort.addItem(new ComboItem("Sort By Price", "price"));
		cbSort.addItem(new ComboItem("Sort By Area", "area"));
		cbSort.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSort.setBounds(677, 202, 268, 30);
		add(cbSort);

		btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// lấy ra table đang được hiển thị
				int totalRow = table.getRowCount();
				int totalColumn = table.getColumnCount();
				List<Housing> listSort = new ArrayList<>();
				for (int i = 0; i < totalRow; i++) {
					Housing hs = new Housing();
					hs.setProduct_id((String) table.getValueAt(i, 0));
					hs.setProduct_name((String) table.getValueAt(i, 1));
					hs.setProduct_price(Double.parseDouble(HousingPanel.formatString((String) table.getValueAt(i, 2))));
					hs.setProduct_total(Integer.parseInt((String) table.getValueAt(i, 3)));
					hs.setArea(Double.parseDouble((String) table.getValueAt(i, 4)));
					hs.setLocation((String) table.getValueAt(i, 5));
					hs.setOwner((String) table.getValueAt(i, 6));
					listSort.add(hs);
				}

				Object itemSort = cbSort.getSelectedItem();
				String valueSort = ((ComboItem) itemSort).getValue();

				Object itemIsNC = cbIsNC.getSelectedItem();
				String valueIsNC = ((ComboItem) itemIsNC).getValue();

				if (valueSort.compareTo("price") == 0) {
					if (valueIsNC.compareTo("asc") == 0) {
						Collections.sort(listSort, new sortedByPrice());
					} else {
						Collections.sort(listSort, new sortedByPrice().reversed());
					}
				} else {
					if (valueIsNC.compareTo("asc") == 0) {
						Collections.sort(listSort, new sortedByArea());
					} else {
						Collections.sort(listSort, new sortedByArea().reversed());
					}
				}

				try {
					fillTable(listSort);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSort.setFont(new Font("Arial", Font.BOLD, 16));
		btnSort.setBounds(677, 283, 109, 30);
		add(btnSort);

		cbIsNC = new JComboBox();
		cbIsNC.addItem(new ComboItem("Ascending", "asc"));
		cbIsNC.addItem(new ComboItem("Descending", "desc"));
		cbIsNC.setFont(new Font("Arial", Font.PLAIN, 14));
		cbIsNC.setBounds(677, 242, 138, 31);
		add(cbIsNC);

		separator_2 = new JSeparator();
		separator_2.setBounds(598, 190, 349, 2);
		add(separator_2);
		
		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Housing> topThree = new ArrayList<>();
				try {
					List<Housing> hs = FileUtil.binaryInputFileHousing("housing.bin", FileUtil.countObject("housing.bin"));
					Collections.sort(hs, new sortedByArea().reversed());
					for(int i = 0; i < 3; i++){
						topThree.add(hs.get(i));
					}
					
					FileUtil.binaryOutputFile("report.bin", topThree, null, null, null);
					List<Housing> test = FileUtil.binaryInputFileHousing("report.bin", FileUtil.countObject("report.bin"));
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
		String[] columns = { "ID", "Name", "Price ($)", "Total", "Area (m²)", "Location", "Owner" };
		tableModel.setColumnIdentifiers(columns);
		table.setModel(tableModel);
	}

	private void fillTable(List<Housing> list) throws ClassNotFoundException, IOException {
		tableModel.setRowCount(0);
		for (Housing hs : list) {
			tableModel.addRow(new String[] { hs.getProduct_id(), hs.getProduct_name(),
					decimalFormat.format(hs.getProduct_price()), "" + hs.getProduct_total(),
					String.format("%.2f", hs.getArea()), hs.getLocation(), hs.getOwner() });
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

	public boolean checkValidData() {
		if (!validateEmtyField()) {
			return false;
		}
		StringBuilder sb = new StringBuilder();
		try {
			double price = Double.parseDouble(textPrice.getText());
			if (price <= 0) {
				sb.append("Price must be greater than 0");
			}

		} catch (Exception ex) {
			sb.append("Price must be real number");
		}
		try {
			int total = Integer.parseInt(textTotal.getText());
			if (total <= 0) {
				sb.append("Total must be greater than 0");
			}

		} catch (Exception ex) {
			sb.append("Total must be an integer");
		}
		try {
			double area = Double.parseDouble(textArea.getText());
			if (area < 0) {
				sb.append("Area must be greater than 0");
			}

		} catch (Exception ex) {
			sb.append("Area must be real number");
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(contentPane, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validateEmtyField() {
		StringBuilder sb = new StringBuilder();
		if (textName.getText().equals("")) {
			sb.append("Name cannot be blank \n");
		}
		if (textPrice.getText().equals("")) {
			sb.append("Price cannot be blank \n");
		}
		if (textTotal.getText().equals("")) {
			sb.append("Total cannot be blankg \n");
		}
		if (textArea.getText().equals("")) {
			sb.append("Area cannot be blank \n");
		}
		if (textLocation.getText().equals("")) {
			sb.append("Location cannot be blank \n");
		}
		if (textOwner.getText().equals("")) {
			sb.append("Owner cannot be blank \n");
		}
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(contentPane, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void resetField() {
		textName.setText("");
		textPrice.setText("");
		textTotal.setText("");
		textArea.setText("");
		textLocation.setText("");
		textOwner.setText("");
		textSearch.setText("");
	}

	public void resetAll() {
		resetField();
		try {
			fillTable(MainView.housings);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeToFile() throws IOException {
		FileUtil.binaryOutputFile("housing.bin", MainView.housings, null, null, null);
	}

	public static List<Housing> generatedHousing(int n) {
		String[] housingName = { "Căn hộ chung cư", "Nhà phố", "Đất nền", "Đất nghỉ dưỡng", "Đất xây nhà xưởng",
				"Đất nghĩa trang", "Condotel", "Officetel", "Shophouse" };
		String[] location = { "Hà Nội", "Cần Thơ", "Đà Nẵng", "Hải Phòng", "Bắc Ninh", "Đồng Nai", "Buôn Ma Thuột",
				"Đà Lạt", "Lâm Đồng", "Thanh Hoá", "An Giang", "Cà Mau", "Cao Bằng", "Bình Phước", "Bình Thuận",
				"Thành phố HCM", "Hà Nội", "Khánh Hoà", "Kiên Giang", "Kon Tum", "Lạng Sơn", "Lào Cai", "Nam Định",
				"Nghệ An", "Ninh Bình", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Sơn La", "Sóc Trăng",
				"Thái Bình", "Hà Nam", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái" };
		String[] firstNames = { "Anh", "Anh Tuấn", "Tuấn Anh", "Minh Anh", "Bảo", "Vân", "Hân", "Vũ", "Hùng", "Hải Anh",
				"Châu", "Châu Anh", "Minh Châu", "Linh", "Thuý", "Hồng", "Việt", "Nam", "Khải", "Huyền Anh", "Huy Anh",
				"Hưng", "Trang", "Yến", "Yến Anh" };
		String[] lastNames = { "Hoàng", "Nguyễn", "Lê", "Phan", "Phạm", "Vũ", "Đào", "Đoàn", "Linh", "Lương", "Ngô",
				"Mạnh", "Đỗ" };
		Housing[] list = new Housing[n];
		List<Housing> hs = Arrays.asList(list);
		for (int i = 0; i < n; i++) {
			list[i] = new Housing();
			list[i].setProduct_id("HS" + String.valueOf(i + 1));
			int indexName = (int) (Math.random() * housingName.length);
			int indexLocation = (int) (Math.random() * location.length);
			list[i].setProduct_name(housingName[indexName] + " " + location[indexLocation]);
			list[i].setProduct_price((int) (50000 + (int) (Math.random() * 300000)));
			list[i].setProduct_total(1 + (int) (Math.random() * 5));
			list[i].setArea((double) 50 + (double) (Math.random() * 300));
			list[i].setLocation(location[indexLocation]);
			int indexFirstName = (int) (Math.random() * firstNames.length);
			int indexLastName = (int) (Math.random() * lastNames.length);
			list[i].setOwner(lastNames[indexLastName] + " " + firstNames[indexFirstName]);
		}
		return hs;
	}
}
