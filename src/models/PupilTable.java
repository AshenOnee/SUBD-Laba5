package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import managers.DBcontroller;

public class PupilTable 
{
	private ArrayList<PupilRow> tablePupil = new ArrayList<>();
	private DBcontroller dbController;
	
	public ArrayList<PupilRow> getTablePupil() {
		return tablePupil;
	}

	public void setTablePupil(ArrayList<PupilRow> tablePupil) {
		this.tablePupil = tablePupil;
	}

	public PupilTable(DBcontroller dbController)
	{
		this.dbController = dbController;
	}
	
	
	public void fill()
    {
		try 
		{
			tablePupil.clear();
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

	        	tablePupil.add(row);
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
    	dbController.setHeaders(table, new String[]{"id", "class", "surname", "name", "patronymic", 
    			"date of birth", "place of residence", "phone", "bloodtype"});
    	int index = 0;
    	for(PupilRow row:tablePupil)
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

            ((DefaultTableModel)table.getModel()).insertRow(index, mass);
            index++;
    	}
    }

}
