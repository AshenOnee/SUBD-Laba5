package models;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lab5.FEdit;
import managers.DBcontroller;

public class Group extends TableType
{
	private ArrayList<ClassRow> tableClass = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<ClassRow> getTableClass() {
		return tableClass;
	}

	public void setTableClass(ArrayList<ClassRow> tableClass) {
		this.tableClass = tableClass;
	}

	public Group(DBcontroller dbController)
	{
		this.dbController = dbController;
	}

	@SuppressWarnings("serial")
	@Override
	public void fillJTableFromTable(JTable jTable) {
		fillTableFromDB();
    	
    	jTable.setModel(new DefaultTableModel( new String[]{"id","name"}, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    	
    	int index = 0;
    	for(ClassRow row:tableClass)
    	{
    		Object[] mass = new Object[2];
    		mass[0] = row.getId();
    		mass[1] = row.getName();

            ((DefaultTableModel)jTable.getModel()).insertRow(index, mass);
            index++;
    	}
	}

	@Override
	public void fillTableFromDB() {
		try 
		{
			tableClass.clear();
	   	 	ResultSet rs = dbController.getRows("select id, name from class order by id;");

	        while(rs.next())
	        {  
	        	ClassRow row = new ClassRow();
	        	row.setId(rs.getObject(1).toString());
	        	row.setName(rs.getObject(2).toString());
	        	tableClass.add(row);
	        }	 		   	 	
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void addRowToDB(FEdit fedit) {
		dbController.edit("insert into class (name) "
				+ "values('" + fedit.getTfNameClass().getText() + "');");
	}

	@Override
	public void updateRowInDB(FEdit fedit, int id) {
		dbController.edit("update class set "
				+ "name = '" + fedit.getTfNameClass().getText() + "' where id = " + id + ";");
	}

	@Override
	public void fillPanelFields(FEdit fedit, int id) {
		try
		{
			ResultSet rs = dbController.getRow("class", id);
			rs.next();
			
			fedit.getTfNameClass().setText(rs.getObject(1).toString());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void chngePanel(FEdit fedit) {
		fedit.getpMain().removeAll();
		fedit.getpMain().add(fedit.getpClass(), BorderLayout.CENTER);
	}
	

	@Override
	public String toString()
	{
		return "class";
	}
}
