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

public class Teacher extends TableType
{
	private ArrayList<TeacherRow> tableTeacher = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<TeacherRow> getTableTeacher() {
		return tableTeacher;
	}

	public void setTableTeacher(ArrayList<TeacherRow> tableTeacher) {
		this.tableTeacher = tableTeacher;
	}

	public Teacher(DBcontroller dbController)
	{
		this.dbController = dbController;
	}

	@SuppressWarnings("serial")
	@Override
	public void fillJTableFromTable(JTable jTable) {
		fillTableFromDB();

    	jTable.setModel(new DefaultTableModel( new String[]{"id","surname", "name", "patronymic"}, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    	
    	int index = 0;
    	for(TeacherRow row:tableTeacher)
    	{
    		Object[] mass = new Object[4];
    		mass[0] = row.getId();
    		mass[1] = row.getSurname();
    		mass[2] = row.getName();
    		mass[3] = row.getPatronymic();

            ((DefaultTableModel)jTable.getModel()).insertRow(index, mass);
            index++;
    	}
	}

	@Override
	public void fillTableFromDB() {
		try 
		{
			tableTeacher.clear();
	   	 	ResultSet rs = dbController.getRows("select id, surname, name, patronymic from teacher order by id;");

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

	@Override
	public void addRowToDB(FEdit fedit) {
		dbController.edit("insert into teacher (surname, name, patronymic)  "
				+ "values('" + fedit.getTfSurnameTeacher().getText() + "', '" + fedit.getTfNameTeacher().getText() 
				+ "', '" + fedit.getTfPatronymicTeacher().getText() + "');");
	}

	@Override
	public void updateRowInDB(FEdit fedit, int id) {
		dbController.edit("update teacher set "
				+ "surname = '" + fedit.getTfSurnameTeacher().getText() + "', name = '" + fedit.getTfNameTeacher().getText() + "', patronymic = '" 
				+ fedit.getTfPatronymicTeacher().getText() + "' where id = " + id + ";");
	}

	@Override
	public void fillPanelFields(FEdit fedit, int id) {
		try
		{
			ResultSet rs = dbController.getRow("teacher", id);
			rs.next();
			
			fedit.getTfSurnameTeacher().setText(rs.getObject(1).toString());
			fedit.getTfNameTeacher().setText(rs.getObject(2).toString());
			fedit.getTfPatronymicTeacher().setText(rs.getObject(3).toString());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void chngePanel(FEdit fedit) {
		fedit.getpMain().removeAll();
		fedit.getpMain().add(fedit.getpTeacher(), BorderLayout.CENTER);
	}

	@Override
	public String toString()
	{
		return "teacher";
	}
	
}
