package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import managers.DBcontroller;

public class TeacherTable 
{
	private ArrayList<TeacherRow> tableTeacher = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<TeacherRow> getTableTeacher() {
		return tableTeacher;
	}

	public void setTableTeacher(ArrayList<TeacherRow> tableTeacher) {
		this.tableTeacher = tableTeacher;
	}

	public TeacherTable(DBcontroller dbController)
	{
		this.dbController = dbController;
	}
	
	public void fill()
    {
		try 
		{
			tableTeacher.clear();
	   	 	ResultSet rs = dbController.getRows("select * from teacher order by id;");

	        while(rs.next())
	        {  
	        	TeacherRow row = new TeacherRow();
	        	row.setId(rs.getObject(1).toString());
	        	row.setSurname(rs.getObject(2).toString());
	        	row.setName(rs.getObject(3).toString());
	        	row.setPatronymic(rs.getObject(4).toString());
	        	tableTeacher.add(row);
	        }	 		   	 	
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    

	public void getTeacher(JTable table)
    {
    	fill();
    	dbController.setHeaders(table, new String[]{"id","surname", "name", "patronymic"});
    	int index = 0;
    	for(TeacherRow row:tableTeacher)
    	{
    		Object[] mass = new Object[4];
    		mass[0] = row.getId();
    		mass[1] = row.getSurname();
    		mass[2] = row.getName();
    		mass[3] = row.getPatronymic();

            ((DefaultTableModel)table.getModel()).insertRow(index, mass);
            index++;
    	}
    }
	
}
