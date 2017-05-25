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

public class Subject extends TableType
{
	private ArrayList<SubjectRow> tableSubject = new ArrayList<>();
	private DBcontroller dbController;

	public ArrayList<SubjectRow> getTableSubject() {
		return tableSubject;
	}

	public void setTableSubject(ArrayList<SubjectRow> tableSubject) {
		this.tableSubject = tableSubject;
	}

	public Subject(DBcontroller dbController)
	{
		this.dbController = dbController;
	}
	
	@SuppressWarnings("serial")
	@Override
	public void fillJTableFromTable(JTable jTable) {
		fillTableFromDB();

    	jTable.setModel(new DefaultTableModel(new String[]{"id","name"}, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    	
    	int index = 0;
    	for(SubjectRow row:tableSubject)
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
			tableSubject.clear();
	   	 	ResultSet rs = dbController.getRows("select id, name from subject order by id;");

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

	@Override
	public void addRowToDB(FEdit fedit) {
		dbController.edit("insert into subject (name) "
				+ "values('" + fedit.getTfNameSubject().getText() + "');");
	}

	@Override
	public void updateRowInDB(FEdit fedit, int id) {
		dbController.edit("update subject set "
				+ "name = '" + fedit.getTfNameSubject().getText() + "' where id = " + id + ";");
	}

	@Override
	public void fillPanelFields(FEdit fedit, int id) {
		try
		{
			ResultSet rs = dbController.getRow("subject", id);
			rs.next();
			
			fedit.getTfNameSubject().setText(rs.getObject(1).toString());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void chngePanel(FEdit fedit) {
		fedit.getpMain().removeAll();
		fedit.getpMain().add(fedit.getpSubject(), BorderLayout.CENTER);
	}

	@Override
	public String toString()
	{
		return "subject";
	}
}
