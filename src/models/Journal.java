package models;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lab5.FEdit;
import managers.DBcontroller;

public class Journal extends TableType 
{
	private ArrayList<JournalRow> table = new ArrayList<JournalRow>();
	private DBcontroller dbController;

	public Journal(DBcontroller dbController)
	{
		this.dbController = dbController;
	}

	@SuppressWarnings("serial")
	@Override
	public void fillJTableFromTable(JTable jTable) 
	{
		fillTableFromDB();
    	
    	jTable.setModel(new DefaultTableModel( new String[]{"id", "pupil", "teacher", "subject", "date", "mark"}, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    	
    	int index = 0;
    	for(JournalRow row:table)
    	{
    		Object[] mass = new Object[6];
    		mass[0] = row.getId();
    		mass[1] = row.getPupilFIO();
    		mass[2] = row.getTeacherFIO();
    		mass[3] = row.getSubject();
    		mass[4] = row.getDate();
    		mass[5] = row.getMark();
            ((DefaultTableModel)jTable.getModel()).insertRow(index, mass);
            index++;
    	}
	}

	@Override
	public void fillTableFromDB() {
		try 
		{
	    	table.clear();
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
	        	table.add(row);
	        }	 		   	 	
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void addRowToDB(FEdit fedit) {
		dbController.edit("insert into journal (id_pupil, id_teacher, id_subject, date, mark) "
				+ "values(" + ((PupilRow)fedit.getCbPupil().getSelectedItem()).getId() + ", " + 
				((TeacherRow)fedit.getCbTeacher().getSelectedItem()).getId() + ", " + ((SubjectRow)fedit.getCbSubject().getSelectedItem()).getId() + ", "
						+ "'" + fedit.getTfDate().getText() + "', " + fedit.getTfMark().getText() + ");");
	}

	@Override
	public void updateRowInDB(FEdit fedit, int id) {
		dbController.edit("update journal set "
				+ "id_pupil = " + ((PupilRow)fedit.getCbPupil().getSelectedItem()).getId() + ", id_teacher =" 
				+ ((TeacherRow)fedit.getCbTeacher().getSelectedItem()).getId() + ", id_subject =" 
				+ ((SubjectRow)fedit.getCbSubject().getSelectedItem()).getId() + ", date = '" 
				+ fedit.getTfDate().getText() + "', mark = " + fedit.getTfMark().getText() + " where id = " + id + ";");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillPanelFields(FEdit fedit, int id) {
		try
		{
			ResultSet rs = dbController.getRow("journal", id);
			rs.next();
			
			dbController.updateTables();
			
			fedit.getCbPupil().setModel(new DefaultComboBoxModel<>(dbController.getPupilTable().getTablePupil().toArray()));
			
			fedit.getCbSubject().setModel(new DefaultComboBoxModel<>(dbController.getSubjectTable().getTableSubject().toArray()));
			
			fedit.getCbTeacher().setModel(new DefaultComboBoxModel<>(dbController.getTeacherTable().getTableTeacher().toArray()));
			
			for(PupilRow row:dbController.getPupilTable().getTablePupil())
				if(row.getId().equals(rs.getObject(1).toString()))
					fedit.getCbPupil().setSelectedItem(row);
			
			for(TeacherRow row:dbController.getTeacherTable().getTableTeacher())
				if(row.getId().equals(rs.getObject(2).toString()))
					fedit.getCbTeacher().setSelectedItem(row);
			
			for(SubjectRow row:dbController.getSubjectTable().getTableSubject())
				if(row.getId().equals(rs.getObject(3).toString()))
					fedit.getCbSubject().setSelectedItem(row);
			
			fedit.getTfDate().setText(rs.getObject(4).toString());
			fedit.getTfMark().setText(rs.getObject(5).toString());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void chngePanel(FEdit fedit) {
		dbController.updateTables();

		fedit.getCbPupil().setModel(new DefaultComboBoxModel<>(dbController.getPupilTable().getTablePupil().toArray()));

		fedit.getCbSubject().setModel(new DefaultComboBoxModel<>(dbController.getSubjectTable().getTableSubject().toArray()));
		
		fedit.getCbTeacher().setModel(new DefaultComboBoxModel<>(dbController.getTeacherTable().getTableTeacher().toArray()));
		
		fedit.getpMain().removeAll();
		fedit.getpMain().add(fedit.getpJournal(), BorderLayout.CENTER);
	}

	@Override
	public String toString()
	{
		return "journal";
	}
}
