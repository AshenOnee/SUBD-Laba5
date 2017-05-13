package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.ClassTable;
import models.JournalTable;
import models.PupilTable;
import models.SubjectTable;
import models.TeacherTable;


public class DBcontroller 
{
	private String url = new String();
	private String name = new String();
	private String password = new String();
    private Connection connection;
    	
	private JournalTable journalTable = new JournalTable(this);
	private PupilTable pupilTable = new PupilTable(this);
	private TeacherTable teacherTable = new TeacherTable(this);
	private ClassTable classTable = new ClassTable(this);
	private SubjectTable subjectTable = new SubjectTable(this);	
    
	public JournalTable getJournalTable() {
		return journalTable;
	}

	public void setJournalTable(JournalTable journalTable) {
		this.journalTable = journalTable;
	}

	public PupilTable getPupilTable() {
		return pupilTable;
	}

	public void setPupilTable(PupilTable pupilTable) {
		this.pupilTable = pupilTable;
	}

	public TeacherTable getTeacherTable() {
		return teacherTable;
	}

	public void setTeacherTable(TeacherTable teacherTable) {
		this.teacherTable = teacherTable;
	}

	public ClassTable getClassTable() {
		return classTable;
	}

	public void setClassTable(ClassTable classTable) {
		this.classTable = classTable;
	}

	public SubjectTable getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(SubjectTable subjectTable) {
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

    @SuppressWarnings("serial")
	public void setHeaders(JTable table, String[] headers)
    {
    	table.setModel(new DefaultTableModel(headers, 0)
	    {
	  		@Override
			public boolean isCellEditable(int row, int column) 
	   		{
	   			return false;
			}
	   	});
    }

    public void fillTable(JTable table, String selectedTable)
    {
    	switch(selectedTable)
		{
			case "journal":
				journalTable.getTable(table);
				break;
			case "pupil":
				pupilTable.getTable(table);
				break;
			case "class":
				classTable.getTable(table);
				break;
			case "teacher":
				teacherTable.getTeacher(table);
				break;
			case "subject":
				subjectTable.getTable(table);
				break;
		}
    }
    
    public void updateTables()
    {
		journalTable.fill();
		pupilTable.fill();
		classTable.fill();
		subjectTable.fill();
		teacherTable.fill();
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
    
    public int maxIdTable(String table)
    {
   	 	try 
   	 	{
   	    	Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select max(id) from "+ table);
			rs.next();
			return Integer.parseInt(rs.getObject(1).toString());
		} 
   	 	catch (SQLException e) 
   	 	{
			e.printStackTrace();
			return -1;
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
