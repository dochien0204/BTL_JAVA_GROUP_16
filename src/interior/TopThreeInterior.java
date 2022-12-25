package interior;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.MainView;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TopThreeInterior extends JFrame {

	public static TopThreeInterior frame = new TopThreeInterior();

	private JPanel contentPane;
	Locale locale = new Locale("en", "EN");
	DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	private JScrollPane scrollPane;
	private DecimalFormat format = new DecimalFormat("#0,000,000.00");
	private JTable table = new JTable();
	private DefaultTableModel tableModel = new DefaultTableModel();


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
	public TopThreeInterior() {
		initTable();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 76, 953, 177);
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		table.setFont(new Font("Arial", Font.PLAIN, 13));
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

		table.setRowSorter(sorter);

		table.setBackground(new Color(255, 255, 255));
		table.setModel(tableModel);
		table.setRowHeight(30);
		
		JLabel lblNewLabel = new JLabel("Top 3 đồ nội thất giá cao nhất");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(269, 21, 412, 44);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InteriorPanel.frameTop.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 18));
		btnBack.setBounds(381, 292, 188, 35);
		contentPane.add(btnBack);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
	}
	
	private void initTable() {
		String[] columns = {  "ID", "Name", "Price", "Total", "Color", "Size", "Material" };
		tableModel.setColumnIdentifiers(columns);
		table.setModel(tableModel);
	}

	public void fillTable(List<Interior> list) throws ClassNotFoundException, IOException {
		tableModel.setRowCount(0);
		for (Interior i : list) {
			tableModel.addRow(new Object[] { i.getProduct_id(), i.getProduct_name(),
					format.format(i.getProduct_price()), i.getProduct_total(),
					i.getColor(), i.getSize(), i.getMaterial() });
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
}
