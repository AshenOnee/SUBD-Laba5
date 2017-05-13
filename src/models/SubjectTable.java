package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import managers.DBcontroller;

public class SubjectTable 
{
	private ArrayList<SubjectRow> tableSubject = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<SubjectRow> getTableSubject() {
		return tableSubject;
	}

	public void setTableSubject(ArrayList<SubjectRow> tableSubject) {
		this.tableSubject = tableSubject;
	}

	public SubjectTable(DBcontroller dbController)
	{
		this.dbController = dbController;
	}

	public void fill()
    {
		try 
		{
			tableSubject.clear();
	   	 	ResultSet rs = dbController.getRows("select * from subject order by id;");

	        while(rs.next())
	        {  
	        	SubjectRow row = new SubjectRow();
	        	row.setId(rs.getObject(1).toString());
	        	row.setName(rs.getObject(2).toString());
	        	tableSubject.add(row);
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
    	for(SubjectRow row:tableSubject)
    	{
    		Object[] mass = new Object[2];
    		mass[0] = row.getId();
    		mass[1] = row.getName();

            ((DefaultTableModel)table.getModel()).insertRow(index, mass);
            index++;
    	}
    }

}
