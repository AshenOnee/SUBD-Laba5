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

public class Pupil extends TableType
{
	private ArrayList<PupilRow> table = new ArrayList<>();
	private DBcontroller dbController;
	
	public ArrayList<PupilRow> getTablePupil() {
		return table;
	}

	public void setTablePupil(ArrayList<PupilRow> tablePupil) {
		this.table = tablePupil;
	}

	public Pupil(DBcontroller dbController)
	{
		this.dbController = dbController;
	}

	@SuppressWarnings("serial")
	@Override
	public void fillJTableFromTable(JTable jTable) {
		fillTableFromDB();
    	
    	jTable.setModel(new DefaultTableModel( new String[]{"id", "class", "surname", "name", "patronymic", 
    			"date of birth", "place of residence", "phone", "bloodtype"}, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    	
    	int index = 0;
    	for(PupilRow row:table)
    	{
    		Object[] mass = new Object[9];
    		mass[0] = row.getId();
    		mass[1] = row.getClassName();
    		mass[2] = row.getSurname();
    		mass[3] = row.getName();
    		mass[4] = row.getPatronymic();
    		mass[5] = row.getDateOfBirth();
    		mass[6] = row.getPlaceOfResidence();
    		mass[7] = row.getPhone();
    		mass[8] = row.getBloodType();

            ((DefaultTableModel)jTable.getModel()).insertRow(index, mass);
            index++;
    	}
	}

	@Override
	public void fillTableFromDB() {
		try 
		{
			table.clear();
	   	 	ResultSet rs = dbController.getRows("select pupil.id, class.name, pupil.surname, pupil.name, pupil.patronymic, "
	   	 			+ "pupil.date_of_birth, pupil.place_of_residence, pupil.phone, pupil.bloodtype "
	   	 			+ "from pupil, class "
	   	 			+ "where pupil.id_class = class.id "
	   	 			+ "order by pupil.id;");

	        while(rs.next())
	        {  
	        	PupilRow row = new PupilRow();
	        	row.setId(rs.getObject(1).toString());
	        	row.setClassName(rs.getObject(2).toString());
	        	row.setSurname(rs.getObject(3).toString());
	        	row.setName(rs.getObject(4).toString());
	        	row.setPatronymic(rs.getObject(5).toString());
	        	row.setDateOfBirth(rs.getObject(6).toString());
	        	row.setPlaceOfResidence(rs.getObject(7).toString());
	        	row.setPhone(rs.getObject(8).toString());
	        	row.setBloodType(rs.getObject(9).toString());

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
		dbController.edit("insert into pupil (id_class, surname, name, patronymic, date_of_birth, place_of_residence, phone, bloodtype) "
				+ "values("+ ((ClassRow)fedit.getCbClass().getSelectedItem()).getId() + ", '" + fedit.getTfSurnamePupil().getText() + "', '" + fedit.getTfNamePupil().getText() + "', "
						+ "'" + fedit.getTfPatronymic().getText() + "', '" + fedit.getTfDateOfBirth().getText() + "', '" + fedit.getTfPlaceOfResidence().getText() 
						+ "', " + fedit.getTfPhone().getText() + ", '" + fedit.getTfBloodType().getText() + "');");
	}

	@Override
	public void updateRowInDB(FEdit fedit, int id) {
		dbController.edit("update pupil set "
				+ "id_class = " + ((ClassRow)fedit.getCbClass().getSelectedItem()).getId() + ", surname = '" 
				+ fedit.getTfSurnamePupil().getText() + "', name = '" + fedit.getTfNamePupil().getText() + "', "
				+ "patronymic = '" + fedit.getTfPatronymic().getText() + "', date_of_birth = '" + fedit.getTfDateOfBirth().getText() 
				+ "', place_of_residence = '" + fedit.getTfPlaceOfResidence().getText() + "', phone = " 
				+ fedit.getTfPhone().getText() + ", bloodtype = '" + fedit.getTfBloodType().getText() + "' where id = " + id + ";");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillPanelFields(FEdit fedit, int id) {
		try
		{
			ResultSet rs = dbController.getRow("pupil", id);
			rs.next();
			
			dbController.updateTables();
			fedit.getCbClass().setModel(new DefaultComboBoxModel<>(dbController.getClassTable().getTableClass().toArray()));
			
			for(ClassRow row:dbController.getClassTable().getTableClass())
				if(row.getId().equals(rs.getObject(1).toString()))
					fedit.getCbClass().setSelectedItem(row);

			fedit.getTfSurnamePupil().setText(rs.getObject(2).toString());
			fedit.getTfNamePupil().setText(rs.getObject(3).toString());
			fedit.getTfPatronymic().setText(rs.getObject(4).toString());
			fedit.getTfDateOfBirth().setText(rs.getObject(5).toString());
			fedit.getTfPlaceOfResidence().setText(rs.getObject(6).toString());
			fedit.getTfPhone().setText(rs.getObject(7).toString());
			fedit.getTfBloodType().setText(rs.getObject(8).toString());
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
		
		fedit.getCbClass().setModel(new DefaultComboBoxModel<>(dbController.getClassTable().getTableClass().toArray()));
		
		fedit.getpMain().removeAll();
		fedit.getpMain().add(fedit.getpPupil(), BorderLayout.CENTER);
	}


	@Override
	public String toString()
	{
		return "pupil";
	}
}
