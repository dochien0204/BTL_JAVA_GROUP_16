package interior;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class InteriorPanel extends JPanel {

	private JTextField txtId = new JTextField();
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtTotal;
	private JTextField txtColor;
	private JTextField txtSize;
	private JTextField txtMaterial;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;

	private JFrame frame = new JFrame();
	private JTable tb;

	static DefaultTableModel dtm = new DefaultTableModel();
	final static Object[] Title = { "Id", "Name", "Price", "Total", "Color", "Size", "Material" };
	private JTextField txtSearch;
	private JTextField txtSortByPrice;
	
	public InteriorPanel() {

		InteritorManagerImpl interitorManagerImpl = new InteritorManagerImpl();
		setBounds(100, 100, 1000, 700);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtId.setEnabled(true);
			}
		});
		setBorder(new EmptyBorder(5, 5, 5, 5));

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();

		int w = screen.width;
		int h = screen.height;
		setLocation(w / 5, h / 15);
		setLayout(null);

		JLabel lblTitle = new JLabel("Interior Manager");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitle.setBounds(346, 20, 290, 41);
		add(lblTitle);

		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Arial", Font.BOLD, 16));
		lblId.setBounds(43, 71, 90, 30);
		add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("Arial", Font.PLAIN, 14));
		txtId.setBounds(149, 71, 290, 30);
		add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(149, 111, 290, 30);
		add(txtName);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Arial", Font.BOLD, 16));
		lblName.setBounds(43, 111, 90, 30);
		add(lblName);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(149, 151, 290, 30);
		add(txtPrice);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 16));
		lblPrice.setBounds(43, 150, 90, 30);
		add(lblPrice);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTotal.setColumns(10);
		txtTotal.setBounds(149, 191, 290, 30);
		add(txtTotal);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotal.setBounds(43, 190, 90, 30);
		add(lblTotal);

		txtColor = new JTextField();
		txtColor.setFont(new Font("Arial", Font.PLAIN, 14));
		txtColor.setColumns(10);
		txtColor.setBounds(149, 231, 290, 30);
		add(txtColor);

		JLabel lblColor = new JLabel("Color");
		lblColor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblColor.setFont(new Font("Arial", Font.BOLD, 16));
		lblColor.setBounds(43, 231, 90, 30);
		add(lblColor);

		txtSize = new JTextField();
		txtSize.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSize.setColumns(10);
		txtSize.setBounds(149, 271, 290, 30);
		add(txtSize);

		JLabel lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSize.setFont(new Font("Arial", Font.BOLD, 16));
		lblSize.setBounds(43, 271, 90, 30);
		add(lblSize);

		txtMaterial = new JTextField();
		txtMaterial.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMaterial.setColumns(10);
		txtMaterial.setBounds(149, 311, 290, 30);
		add(txtMaterial);

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaterial.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaterial.setBounds(43, 311, 90, 30);
		add(lblMaterial);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(577, 71, 24, 270);
		add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(598, 190, 349, 2);
		add(separator_2);
		
		separator = new JSeparator();
		separator.setBounds(20, 351, 1023, 2);
		add(separator);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtId.getText() != "" && txtName.getText() != "" && txtPrice.getText() != ""
						&& txtTotal.getText() != "" && txtColor.getText() != "" && txtSize.getText() != ""
						&& txtMaterial.getText() != "") {
					Interior interior = new Interior(txtId.getText(), txtName.getText(),
							Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtTotal.getText()),
							txtColor.getText(), txtSize.getText(), txtMaterial.getText());
					if (interitorManagerImpl.addInterior(interior)) {
						JOptionPane.showMessageDialog(frame, "Added", "Notification", JOptionPane.INFORMATION_MESSAGE);
						try {
							FileUtil.binaryOutputFile("interior.bin", null, null, MainView.interiors, null);
							dtm.addRow(new Object[] { interior.getProduct_id(), interior.getProduct_name(),
									FormatDouble(interior.getProduct_price()), interior.getProduct_total(),
									interior.getColor(), interior.getSize(), interior.getMaterial(), });
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Add Failed", "Notification", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter full information", "Notification",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBounds(458, 71, 109, 45);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if (i >= 0) {
					Interior interior = new Interior(txtId.getText(), txtName.getText(),
							Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtTotal.getText()),
							txtColor.getText(), txtSize.getText(), txtMaterial.getText());
					if (interitorManagerImpl.editInterior(interior)) {
						JOptionPane.showMessageDialog(frame, "Edited", "Notification", JOptionPane.INFORMATION_MESSAGE);
						try {
							FileUtil.binaryOutputFile("interior.bin", null, null, MainView.interiors, null);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dtm.setValueAt(txtId.getText(), i, 0);
						dtm.setValueAt(txtName.getText(), i, 1);
						dtm.setValueAt(txtPrice.getText(), i, 2);
						dtm.setValueAt(txtTotal.getText(), i, 3);
						dtm.setValueAt(txtColor.getText(), i, 4);
						dtm.setValueAt(txtSize.getText(), i, 5);
						dtm.setValueAt(txtMaterial.getText(), i, 6);
					} else {
						JOptionPane.showMessageDialog(frame, "Edit Failed", "Notification", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select Interior want edit", "Notification",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 16));
		btnEdit.setBounds(458, 126, 109, 45);
		add(btnEdit);

		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if (i >= 0) {
					Interior interior = new Interior();
					interior.setProduct_id(txtId.getText());
					interitorManagerImpl.delInterior(interior);
					try {
						FileUtil.binaryOutputFile("interior.bin", null, null, MainView.interiors, null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dtm.removeRow(i);
					JOptionPane.showMessageDialog(frame, "Deleted", "Notification", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select Interior want delete", "Notification",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDel.setFont(new Font("Arial", Font.BOLD, 16));
		btnDel.setBounds(458, 181, 109, 45);
		add(btnDel);

		String searchBy[] = { "Search By Id", "Search By Name", "Search By Price", "Search By Color",
				"Search By Material" };
		JComboBox cbSearchBy = new JComboBox(searchBy);
		cbSearchBy.setFont(new Font("Arial", Font.PLAIN, 14));
		JComboBox cb = new JComboBox(searchBy);
		cbSearchBy.setBounds(677, 71, 270, 30);
		add(cbSearchBy);

		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchBy.setFont(new Font("Arial", Font.BOLD, 16));
		lblSearchBy.setBounds(577, 71, 90, 30);
		add(lblSearchBy);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSearch.setColumns(10);
		txtSearch.setBounds(677, 111, 270, 30);
		add(txtSearch);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearch.setFont(new Font("Arial", Font.BOLD, 16));
		lblSearch.setBounds(577, 111, 90, 30);
		add(lblSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IsEmptyTxt(txtSearch.getText())) {
					String txtSearchBy = cbSearchBy.getItemAt(cbSearchBy.getSelectedIndex()).toString();
					if (txtSearchBy.compareTo("Search By Id") == 0) {
						List<Interior> list = new ArrayList<>();
						Interior interior = interitorManagerImpl.searchInteriorById(txtSearch.getText());
						if (interior != null) {
							list.add(interior);
							dtm.setRowCount(0);
							LoadData(list);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Interior with id: " + txtSearch.getText() + " does not exist.", "Notification",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (txtSearchBy.compareTo("Search By Name") == 0) {
						List<Interior> list = interitorManagerImpl.searchInteriorByName(txtSearch.getText());
						if (list.size() > 0) {
							dtm.setRowCount(0);
							LoadData(list);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Interior with name: " + txtSearch.getText() + " does not exist.", "Notification",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (txtSearchBy.compareTo("Search By Price") == 0) {
						List<Interior> list = interitorManagerImpl
								.searchInteriorByPrice(Double.parseDouble(txtSearch.getText()));
						if (list.size() > 0) {
							dtm.setRowCount(0);
							LoadData(list);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Interior with price: " + txtSearch.getText() + " does not exist.", "Notification",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (txtSearchBy.compareTo("Search By Total") == 0) {
						List<Interior> list = interitorManagerImpl.searchInteriorByColor(txtSearch.getText());
						if (list.size() > 0) {
							dtm.setRowCount(0);
							LoadData(list);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Interior with color: " + txtSearch.getText() + " does not exist.", "Notification",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (txtSearchBy.compareTo("Search By Material") == 0) {
						List<Interior> list = interitorManagerImpl.searchInteriorByMaterial(txtSearch.getText());
						if (list.size() > 0) {
							dtm.setRowCount(0);
							LoadData(list);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Interior with material: " + txtSearch.getText() + " does not exist.",
									"Notification", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"Please enter search information", "Notification",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearch.setBounds(677, 149, 109, 30);
		add(btnSearch);

		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortBy.setFont(new Font("Arial", Font.BOLD, 16));
		lblSortBy.setBounds(577, 191, 90, 30);
		add(lblSortBy);

		JLabel lblSortPrice = new JLabel("Price");
		lblSortPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortPrice.setFont(new Font("Arial", Font.BOLD, 16));
		lblSortPrice.setBounds(577, 271, 90, 30);
		add(lblSortPrice);

		txtSortByPrice = new JTextField();
		txtSortByPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSortByPrice.setColumns(10);
		txtSortByPrice.setBounds(677, 271, 270, 30);
		add(txtSortByPrice);

		String sortType[] = { "Ascending", "Descending" };
		JComboBox cbSortType = new JComboBox(sortType);
		cbSortType.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSortType.setBounds(677, 231, 109, 30);
		add(cbSortType);

		String sortByMinMax[] = { "Sort By Price Min", "Sort By Price Max" };
		JComboBox cbPriceMinMax = new JComboBox(sortByMinMax);
		cbPriceMinMax.setFont(new Font("Arial", Font.PLAIN, 14));
		cbPriceMinMax.setBounds(804, 230, 143, 30);
		add(cbPriceMinMax);
		String sortBy[] = { "Sort By Price", "Sort By Total" };

		JComboBox cbSortBy = new JComboBox(sortBy);
		cbSortBy.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cbItemSortBy = cbSortBy.getItemAt(cbSortBy.getSelectedIndex()).toString();
				if (cbItemSortBy.compareTo("Sort By Price") == 0) {
					lblSortPrice.setVisible(true);
					txtSortByPrice.setVisible(true);
					cbPriceMinMax.setVisible(true);
				} else if (cbItemSortBy.compareTo("Sort By Total") == 0) {
					lblSortPrice.setVisible(false);
					txtSortByPrice.setVisible(false);
					cbPriceMinMax.setVisible(false);
				}
			}
		});
		cbSortBy.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSortBy.setBounds(677, 191, 270, 30);
		add(cbSortBy);

		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cbItemSortBy = cbSortBy.getItemAt(cbSortBy.getSelectedIndex()).toString();
				String cbItemSortType = cbSortType.getItemAt(cbSortType.getSelectedIndex()).toString();
				String cbItemPriceMinMax = cbPriceMinMax.getItemAt(cbPriceMinMax.getSelectedIndex()).toString();

				List<Interior> interiorsFromFile = new ArrayList<>();
				try {
					interiorsFromFile = FileUtil.binaryInputFileInterior("interior.bin", MainView.interiors.size());
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<Interior> interiorsSort = new ArrayList<>();

				if (cbItemSortBy.compareTo("Sort By Price") == 0) {

					System.out.println("Price");
					if (IsEmptyTxt(txtSortByPrice.getText())) {
						if (cbItemPriceMinMax.compareTo("Sort By Price Min") == 0) {
							for (int i = 0; i < interiorsFromFile.size(); i++) {
								if (Double.parseDouble(txtSortByPrice.getText()) <= interiorsFromFile.get(i)
										.getProduct_price()) {
									interiorsSort.add(interiorsFromFile.get(i));
								}
							}
						} else if (cbItemPriceMinMax.compareTo("Sort By Price Max") == 0) {
							for (int i = 0; i < interiorsFromFile.size(); i++) {
								if (Double.parseDouble(txtSortByPrice.getText()) >= interiorsFromFile.get(i)
										.getProduct_price()) {
									interiorsSort.add(interiorsFromFile.get(i));
								}
							}
						}
					} else {
						interiorsSort.addAll(interiorsFromFile);
					}

					if (cbItemSortType.compareTo("Ascending") == 0) {
						interiorsSort.sort((i1, i2) -> (int) (i1.getProduct_price() - i2.getProduct_price()));
					} else {
						interiorsSort.sort((i1, i2) -> (int) (i2.getProduct_price() - i1.getProduct_price()));
					}

				} else if (cbItemSortBy.compareTo("Sort By Total") == 0) {
					System.out.println("Total");
					interiorsSort.addAll(interiorsFromFile);
					if (cbItemSortType.compareTo("Ascending") == 0) {
						interiorsSort.sort((i1, i2) -> (int) (i1.getProduct_total() - i2.getProduct_total()));
					} else {
						interiorsSort.sort((i1, i2) -> (int) (i2.getProduct_total() - i1.getProduct_total()));
					}
				}
				dtm.setRowCount(0);
				LoadData(interiorsSort);
			}
		});
		btnSort.setFont(new Font("Arial", Font.BOLD, 16));
		btnSort.setBounds(677, 309, 109, 30);
		add(btnSort);

		FileInputStream inFile;
		try {
			inFile = new FileInputStream("interior.bin");
			ObjectInputStream in = new ObjectInputStream(inFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dtm.setColumnIdentifiers(Title);
		dtm.setRowCount(0);
		LoadData(MainView.interiors);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 370, 953, 295);
		add(scrollPane);

		tb = new JTable();
		scrollPane.setViewportView(tb);
		tb.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tb.getSelectedRow();
				txtId.setText(dtm.getValueAt(i, 0).toString());
				txtId.setEnabled(false);
				txtName.setText(dtm.getValueAt(i, 1).toString());
				txtPrice.setText(dtm.getValueAt(i, 2).toString());
				txtTotal.setText(dtm.getValueAt(i, 3).toString());
				txtColor.setText(dtm.getValueAt(i, 4).toString());
				txtSize.setText(dtm.getValueAt(i, 5).toString());
				txtMaterial.setText(dtm.getValueAt(i, 6).toString());
			}
		});
		tb.setFont(new Font("Arial", Font.PLAIN, 13));
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);

		tb.setRowSorter(sorter);
		
		tb.setBackground(new Color(255, 255, 255));
		tb.setModel(dtm);
		tb.setRowHeight(30);
		tb.getColumnModel().getColumn(0).setPreferredWidth(15);
		tb.getColumnModel().getColumn(1).setPreferredWidth(120);
		tb.getColumnModel().getColumn(2).setPreferredWidth(90);
		tb.getColumnModel().getColumn(3).setPreferredWidth(30);
		tb.getColumnModel().getColumn(4).setPreferredWidth(170);
		tb.getColumnModel().getColumn(5).setPreferredWidth(170);
		tb.getColumnModel().getColumn(6).setPreferredWidth(150);
		


		// Can giua cac cot trong table
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int columnIndex = 0; columnIndex < dtm.getColumnCount(); columnIndex++) {
			tb.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
		}

		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				try {
					LoadData(FileUtil.binaryInputFileInterior("interior.bin", MainView.interiors.size()));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowAll.setFont(new Font("Arial", Font.BOLD, 16));
		btnShowAll.setBounds(458, 236, 109, 45);
		add(btnShowAll);
		
		JLabel lblSortType = new JLabel("Sort Type");
		lblSortType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortType.setFont(new Font("Arial", Font.BOLD, 16));
		lblSortType.setBounds(577, 231, 90, 30);
		add(lblSortType);
	}
	public static List<Interior> generateInterior(int n) {
		List<Interior> list = new ArrayList<>();

		String[] names = { "Bàn", "Bàn ăn", "Bàn học", "Bàn đảo", "Bàn trang điểm", "Ghế", "Ghế đẩu", "Ghế sofa",
				"Ghế dài", "Tủ", "Tủ chè", "Tủ quần áo", "Tủ TV", "Tủ sách", "Tủ bếp", "Giường ngủ", "Giường 2 tầng" };

		String[] colors = { "Xám", "Xanh thẫm", "Xanh nhạt", "Xanh đen", "Đỏ", "Đỏ đô", "Đỏ nhạt", "Tím", "Tím than" };

		String[] sizes = { "D2200 - R950 - C750", "D2100 - R550 - C950", "D1000 - R700 - C500", "D2500 - R850 - C650",
				"D2600 - R1050 - C950", "D1800 - R550 - C450", };

		String[] materials = { "Vải", "Gỗ lim", "Gỗ sồi", "Nhựa", "Gỗ tần bì", "Gỗ xưa" };

//		Thực hiện sinh ngẫu nhiên danh sách
		int index;

		for (int i = 0; i < n; i++) {

			Interior interior = new Interior();
			interior.setProduct_id((i + 1) + "");
			index = (int) (Math.random() * names.length);
			interior.setProduct_name(names[index]);
			index = (int) (Math.random() * 10000000);
			interior.setProduct_price(index);
			index = (int) (Math.random() * 1000);
			interior.setProduct_total(index);
			index = (int) (Math.random() * colors.length);
			interior.setColor(colors[index]);
			index = (int) (Math.random() * sizes.length);
			interior.setSize(sizes[index]);
			index = (int) (Math.random() * materials.length);
			interior.setMaterial(materials[index]);

			list.add(interior);
		}
		return list;
	}

	public static boolean IsEmptyTxt(String txt) {
		JFrame frame1 = new JFrame();
		if (txt.compareTo("") == 0) {
			return false;
		}
		return true;
	}

	public static void LoadData(List<Interior> list) {
		for (int i = 0; i < list.size(); i++) {
			dtm.addRow(new Object[] { list.get(i).getProduct_id(), list.get(i).getProduct_name(),
					FormatDouble(list.get(i).getProduct_price()), list.get(i).getProduct_total(),
					list.get(i).getColor(), list.get(i).getSize(), list.get(i).getMaterial() });
		}
	}

	public static String FormatDouble(double price) {
		if (price == (long) price)
			return String.format("%d", (long) price);
		else
			return String.format("%f", price);
	}
}
