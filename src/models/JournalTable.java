package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import managers.DBcontroller;

public class JournalTable 
{
	private ArrayList<JournalRow> tableJournal = new ArrayList<JournalRow>();
	private DBcontroller dbController;

	public JournalTable(DBcontroller dbController)
	{
		this.dbController = dbController;
	}
	
	public void fill()
    {
		try 
		{
	    	tableJournal.clear();
	   	 	ResultSet rs = dbController.getRows("select journal.id, "
	   	 			+ "pupil.surname, pupil.name, pupil.patronymic, "
	   	 			+ "teacher.surname, teacher.name, teacher.patronymic, "
	   	 			+ "subject.name, "
	   	 			+ "journal.date, "
	   	 			+ "journal.mark "
	   	 			+ "from journal, pupil, teacher, subject "
	   	 			+ "where journal.id_pupil = pupil.id AND "
	   	 			+ "journal.id_teacher =  teacher.id AND "
	   	 			+ "journal.id_subject = subject.id "
	   	 			+ "order by journal.id;");

	        while(rs.next())
	        {  
	        	JournalRow row = new JournalRow();
	        	row.setId(rs.getObject(1).toString());
	        	row.setPupilFIO(rs.getObject(2).toString() + " " + rs.getObject(3).toString() + " " + rs.getObject(4).toString());
	        	row.setTeacherFIO(rs.getObject(5).toString() + " " + rs.getObject(6).toString() + " " + rs.getObject(7).toString());
	        	row.setSubject(rs.getObject(8).toString());
	        	row.setDate(rs.getObject(9).toString());
	        	row.setMark(rs.getObject(10).toString());
	        	tableJournal.add(row);
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
    	dbController.setHeaders(table, new String[]{"id", "pupil", "teacher", "subject", "date", "mark"});
    	
    	int index = 0;
    	for(JournalRow row:tableJournal)
    	{
    		Object[] mass = new Object[6];
    		mass[0] = row.getId();
    		mass[1] = row.getPupilFIO();
    		mass[2] = row.getTeacherFIO();
    		mass[3] = row.getSubject();
    		mass[4] = row.getDate();
    		mass[5] = row.getMark();
            ((DefaultTableModel)table.getModel()).insertRow(index, mass);
            index++;
    	}
    }

}
