package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class GuestsList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestsList frame = new GuestsList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public GuestsList(String[][] columns) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
		String []rows= {"c","c","c","c","c","c","c"};
		//String[][] columns= {};
		DefaultTableModel dtm= new DefaultTableModel(columns, rows);
		//columns=dataBaseData.toArray(columns);
		/*
		for (ArrayList<String> i:dataBaseData) {
			String[] arr=new String[i.size()];
			arr=i.toArray(arr);
			dtm.addRow(arr);
			
		}*/
		/*
		for (int i = 0; i < dataBaseData.size(); i++) {
			for (int j = 0; j < dataBaseData.get(i).size(); j++) {
				columns[i][j]=(dataBaseData.get(i).get(j));
			}
		}*/
		
		table.setModel(dtm);
		JScrollPane sp= new JScrollPane();
		sp.setViewportView(table);
		getContentPane().add(sp);
	}

}
