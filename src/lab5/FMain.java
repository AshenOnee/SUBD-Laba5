package lab5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import managers.DBcontroller;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.sql.SQLException;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;

@SuppressWarnings("serial")
public class FMain extends JFrame implements DialogListener {
	private JPanel contentPane;
	private JLabel lblStatus;

	private DBcontroller dbController = new DBcontroller();
	private FConnect fConnect;
	private FEdit fEdit;

	private String selectedTable = "journal";

	AbstractAction miTablesAction = new AbstractAction() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			selectedTable = ((JMenuItem)e.getSource()).getText();
			queryToDB(false);
		}
	};
	private JTable table;
 
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FMain frame = new FMain();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public FMain() 
	{
		initialization();
	}
	
	private void initialization()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 536);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);
		
		JMenuItem mntmConnect = new JMenuItem(new AbstractAction("Connect") {
		    public void actionPerformed(ActionEvent e) {
		    	fConnect = new FConnect(dbController);
		    	onCreateFConnect();
		    }
		});

		mnConnection.add(mntmConnect);
		
		JMenuItem mntmDisconnect = new JMenuItem(new AbstractAction("Disconnect") 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	try 
		    	{
					dbController.closeConnection();
					lblStatus.setText("Status: disconnected");
				} 
		    	catch (SQLException|NullPointerException ex) 
		    	{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
		    }
		});
		
		mnConnection.add(mntmDisconnect);
		
		JMenuItem mntmExit = new JMenuItem(new AbstractAction("Exit")
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	dispose();
		    }
		});
		
		mnConnection.add(mntmExit);
		
		JMenu mnTables = new JMenu("Tables");
		menuBar.add(mnTables);
		
		JMenuItem mntmJournal = new JMenuItem("journal");
		mntmJournal.addActionListener(miTablesAction);
		mnTables.add(mntmJournal);
		
		JMenuItem mntmPupil = new JMenuItem("pupil");
		mntmPupil.addActionListener(miTablesAction);
		mnTables.add(mntmPupil);
		
		JMenuItem mntmSubject = new JMenuItem("subject");
		mntmSubject.addActionListener(miTablesAction);
		mnTables.add(mntmSubject);
		
		JMenuItem mntmTeacher = new JMenuItem("teacher");
		mntmTeacher.addActionListener(miTablesAction);
		mnTables.add(mntmTeacher);
		
		JMenuItem mntmClass = new JMenuItem("class");
		mntmClass.addActionListener(miTablesAction);
		mnTables.add(mntmClass);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);	
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		panel.add(toolBar);
		
		lblStatus = new JLabel("Status: disconnected");
		toolBar.add(lblStatus);
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		toolBar.add(verticalStrut);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fEdit = new FEdit(selectedTable, dbController);
				onCreateFEdit();
			}
		});
		toolBar.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedTable == "custom")
				{
					JOptionPane.showMessageDialog(null, "Select a table");
					return;
				}
				if(table.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "At first you need select a row");
					return;
				}
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				dbController.edit("delete from " + selectedTable + " where id = " + id);
			}
		});
		toolBar.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedTable == "custom")
				{
					JOptionPane.showMessageDialog(null, "Select a table");
					return;
				}
				if(table.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "At first you need select a row");
					return;
				}
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				fEdit = new FEdit(selectedTable, id, dbController);
				onCreateFEdit();
			}
		});
		toolBar.add(btnUpdate);
		
		JButton btnQuere = new JButton("Query");
		btnQuere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				queryToDB(true);
			}
		});
		toolBar.add(btnQuere);
	}
	
	@Override
	public void onClose() 
	{
		this.setEnabled(true);
		if(dbController.isConnected())
		{
			queryToDB(false);
			lblStatus.setText("Status: connected");
		}
		else lblStatus.setText("Status: disconnected");
	}

	public void onCreateFConnect() 
	{
		fConnect.addListener(this);
    	fConnect.setVisible(true);
    	this.setEnabled(false);
	}
	
	public void onCreateFEdit() 
	{
		fEdit.addListener(this);
		fEdit.setVisible(true);
    	this.setEnabled(false);
	}
	
	public void queryToDB(boolean custom)
	{
		if(custom)
		{
			String s = (String)JOptionPane.showInputDialog(null, "Write query", "query",
                    JOptionPane.PLAIN_MESSAGE, null, null, null);
			if(s==null) return;
			dbController.queryToDB(table, s);
			selectedTable = "custom";
		}
		else
		{
			dbController.fillTable(table, selectedTable);
			TableColumnModel tcm = table.getColumnModel();
            tcm.removeColumn(tcm.getColumn(0));
		}
	}
}
