package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Group;
import models.Journal;
import models.Pupil;
import models.Subject;
import models.Teacher;


public class DBcontroller 
{
	private String url;
	private String name;
	private String password;
    private Connection connection;
    	
	private Journal journalTable = new Journal(this);
	private Pupil pupilTable = new Pupil(this);
	private Teacher teacherTable = new Teacher(this);
	private Group classTable = new Group(this);
	private Subject subjectTable = new Subject(this);	
    
	public Journal getJournalTable() {
		return journalTable;
	}

	public void setJournalTable(Journal journalTable) {
		this.journalTable = journalTable;
	}

	public Pupil getPupilTable() {
		return pupilTable;
	}

	public void setPupilTable(Pupil pupilTable) {
		this.pupilTable = pupilTable;
	}

	public Teacher getTeacherTable() {
		return teacherTable;
	}

	public void setTeacherTable(Teacher teacherTable) {
		this.teacherTable = teacherTable;
	}

	public Group getClassTable() {
		return classTable;
	}

	public void setClassTable(Group classTable) {
		this.classTable = classTable;
	}

	public Subject getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(Subject subjectTable) {
		this.subjectTable = subjectTable;
	}	
	
    public String getUrl() 
    {
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public DBcontroller()
    {
        try 
        {
			Class.forName("org.postgresql.Driver");	
		} 
        catch (ClassNotFoundException e) 
        {
        	JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    public void openConnection() throws SQLException, NullPointerException
    {	
		connection = DriverManager.getConnection(url, name, password);	
    }
    
    public void closeConnection() throws SQLException
    {
    	connection.close();
    }
    
    public boolean isConnected()
    {
    	try 
    	{
			return !connection.isClosed();
		} 
    	catch (SQLException e) 
    	{
			return false;
		}
    }
    
    public ResultSet getRows(String sql)
    {
    	try 
		{
			Statement statement = connection.createStatement();
	   	 	ResultSet rs = statement.executeQuery(sql);
	   	 	return rs;
		}
    	catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
    }
    
    public void updateTables()
    {
		journalTable.fillTableFromDB();
		pupilTable.fillTableFromDB();
		classTable.fillTableFromDB();
		subjectTable.fillTableFromDB();
		teacherTable.fillTableFromDB();
    }
    
	@SuppressWarnings("serial")
	public void queryToDB(JTable table, String query)
    {
		try
		{
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(query);
	    	 
	         int columns = rs.getMetaData().getColumnCount();

	    	 String[] columnNames = new String[columns];
	    	 for(int i = 0; i < columns; i++)
	    		 columnNames[i] = rs.getMetaData().getColumnName(i+1);
	    	 
	    	 table.setModel(new DefaultTableModel(columnNames, 0)
	    	 {
	    		 @Override
			     public boolean isCellEditable(int row, int column) 
	    		 {
	    			 return false;
			     }
	    	 });

	         while(rs.next())
	         {  
	             Object[] row = new Object[columns];
	             for (int i = 0; i < columns; i++)
	                 row[i] = rs.getObject(i+1);
	             
	             ((DefaultTableModel)table.getModel()).insertRow(rs.getRow()-1, row);
	         }
	         rs.close();
	         statement.close();  
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
    }   
   
    public ResultSet getRow(String table, int id)
    {
		try 
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select * from "+ table + " where id = " + id + ";");
			return rs;
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
    }
    
    public void edit(String sql)
    {
    	try 
    	{
			Statement statement = connection.createStatement();
			connection.setAutoCommit(false); 
			statement.execute(sql); 
			connection.commit(); 
		} 
    	catch (SQLException e) 
    	{
			JOptionPane.showMessageDialog(null, e.getMessage());
			try 
			{
				connection.rollback();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			} 
		}
    }

}
