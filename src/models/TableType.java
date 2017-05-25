package models;

import javax.swing.JTable;

import lab5.FEdit;

public abstract class TableType 
{	
	abstract public void fillJTableFromTable(JTable jTable);
	
	abstract public void fillTableFromDB();

	abstract public void addRowToDB(FEdit fedit);
	
	abstract public void updateRowInDB(FEdit fedit, int id);
	
	abstract public void fillPanelFields(FEdit fedit, int id);
	
	abstract public void chngePanel(FEdit fedit);
	
	abstract public String toString();
	
}