package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import managers.DBcontroller;

public class ClassTable 
{
	private ArrayList<ClassRow> tableClass = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<ClassRow> getTableClass() {
		return tableClass;
	}

	public void setTableClass(ArrayList<ClassRow> tableClass) {
		this.tableClass = tableClass;
	}

	public ClassTable(DBcontroller dbController)
	{
		this.dbController = dbController;
	}
	
	public void fill()
    {
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
	
	public void getTable(JTable table)
    {
    	fill();
    	dbController.setHeaders(table, new String[]{"id","name"});
    	int index = 0;
    	for(ClassRow row:tableClass)
    	{
    		Object[] mass = new Object[2];
    		mass[0] = row.getId();
    		mass[1] = row.getName();

            ((DefaultTableModel)table.getModel()).insertRow(index, mass);
            index++;
    	}
    }
	
	
}
