package lab5;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import managers.DBcontroller;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FConnect extends JFrame 
{
	
	DBcontroller dbController;
	private JPanel contentPane;
	private JTextField tfIp;
	private JTextField tfName;
	private JTextField tfPassword;
	
	private ArrayList<DialogListener> listeners = new ArrayList<>();
	private JTextField tfPort;
	private JTextField tfDataBase;
	
	public FConnect(DBcontroller dbController) 
	{
		initialization();
		this.dbController = dbController;
	}
	
	private void initialization()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfIp = new JTextField();
		tfIp.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfIp.setText("localhost");
		tfIp.setBounds(101, 11, 323, 20);
		contentPane.add(tfIp);
		tfIp.setColumns(10);
		
		JLabel lblUrl = new JLabel("Ip");
		lblUrl.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblUrl.setBounds(10, 14, 46, 14);
		contentPane.add(lblUrl);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblName.setBounds(10, 101, 46, 14);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfName.setText("postgres");
		tfName.setColumns(10);
		tfName.setBounds(101, 98, 323, 20);
		contentPane.add(tfName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblPassword.setBounds(10, 129, 81, 14);
		contentPane.add(lblPassword);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfPassword.setText("1");
		tfPassword.setColumns(10);
		tfPassword.setBounds(101, 126, 323, 20);
		contentPane.add(tfPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//fireListenersOnClose();
				dispose();
			}
		});
		btnCancel.setBounds(10, 154, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				connect();
			}
		});
		btnConnect.setBounds(335, 157, 89, 23);
		contentPane.add(btnConnect);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblPort.setBounds(10, 45, 46, 14);
		contentPane.add(lblPort);
		
		tfPort = new JTextField();
		tfPort.setText("5432");
		tfPort.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfPort.setColumns(10);
		tfPort.setBounds(101, 42, 323, 20);
		contentPane.add(tfPort);
		
		JLabel lblDataBase = new JLabel("Data base");
		lblDataBase.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblDataBase.setBounds(10, 73, 81, 14);
		contentPane.add(lblDataBase);
		
		tfDataBase = new JTextField();
		tfDataBase.setText("SchoolJournal");
		tfDataBase.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfDataBase.setColumns(10);
		tfDataBase.setBounds(101, 70, 323, 20);
		contentPane.add(tfDataBase);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void connect()
	{
		dbController.setUrl("jdbc:postgresql://" + tfIp.getText() + ":" + tfPort.getText() + "/" + tfDataBase.getText());
		dbController.setName(tfName.getText());
		dbController.setPassword(tfPassword.getText());
		try 
		{
			dbController.openConnection();
			dispose();
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Неверные данные");
		}
	}
	
	public void addListener(DialogListener listener) 
	{
        listeners.add(listener);
    }

    public void removeListener(DialogListener listener) 
    {
        listeners.remove(listener);
    }

    private void fireListenersOnClose() 
    {
        for(DialogListener listener : listeners) 
            listener.onClose();
    }
    
    @Override
    public void dispose()
    {
		fireListenersOnClose();
		super.dispose();
    }
}
