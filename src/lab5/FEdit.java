package lab5;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import managers.DBcontroller;
import models.TableType;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class FEdit extends JFrame {
	private JPanel contentPane;
	private JPanel pMain= new JPanel();
	private JPanel pSelectTable = new JPanel();
	@SuppressWarnings({ "rawtypes" })
	private JComboBox comboBox = new JComboBox();
			
	private int id = 0;
	private TableType currentTable;
	private String mode;
	
	private ArrayList<DialogListener> listeners = new ArrayList<>();

	/**
	 * @wbp.nonvisual location=250,463
	 */
	private final JPanel pPupil = new JPanel();
	private JTextField tfSurnamePupil;
	private JTextField tfNamePupil;
	private JTextField tfPatronymic;
	private JTextField tfDateOfBirth;
	private JTextField tfPlaceOfResidence;
	private JTextField tfPhone;
	private JTextField tfBloodType;
	/**
	 * @wbp.nonvisual location=350,463
	 */
	private final JPanel pTeacher = new JPanel();
	private JTextField tfSurnameTeacher;
	private JTextField tfNameTeacher;
	private JTextField tfPatronymicTeacher;
	/**
	 * @wbp.nonvisual location=630,483
	 */
	private final JPanel pClass = new JPanel();
	private JTextField tfNameClass;
	/**
	 * @wbp.nonvisual location=420,533
	 */
	private final JPanel pSubject = new JPanel();
	private JTextField tfNameSubject;
	/**
	 * @wbp.nonvisual location=53,483
	 */
	private final Panel pJournal = new Panel();
	private JTextField tfDate;
	private JTextField tfMark;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPupil = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cbSubject = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cbTeacher = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox cbClass = new JComboBox();
	

	public JPanel getpMain() {
		return pMain;
	}

	public void setpMain(JPanel pMain) {
		this.pMain = pMain;
	}

	public JTextField getTfSurnamePupil() {
		return tfSurnamePupil;
	}

	public void setTfSurnamePupil(JTextField tfSurnamePupil) {
		this.tfSurnamePupil = tfSurnamePupil;
	}

	public JTextField getTfNamePupil() {
		return tfNamePupil;
	}

	public void setTfNamePupil(JTextField tfNamePupil) {
		this.tfNamePupil = tfNamePupil;
	}

	public JTextField getTfPatronymic() {
		return tfPatronymic;
	}

	public void setTfPatronymic(JTextField tfPatronymic) {
		this.tfPatronymic = tfPatronymic;
	}

	public JTextField getTfDateOfBirth() {
		return tfDateOfBirth;
	}

	public void setTfDateOfBirth(JTextField tfDateOfBirth) {
		this.tfDateOfBirth = tfDateOfBirth;
	}

	public JTextField getTfPlaceOfResidence() {
		return tfPlaceOfResidence;
	}

	public void setTfPlaceOfResidence(JTextField tfPlaceOfResidence) {
		this.tfPlaceOfResidence = tfPlaceOfResidence;
	}

	public JTextField getTfPhone() {
		return tfPhone;
	}

	public void setTfPhone(JTextField tfPhone) {
		this.tfPhone = tfPhone;
	}

	public JTextField getTfBloodType() {
		return tfBloodType;
	}

	public void setTfBloodType(JTextField tfBloodType) {
		this.tfBloodType = tfBloodType;
	}

	public JTextField getTfSurnameTeacher() {
		return tfSurnameTeacher;
	}

	public void setTfSurnameTeacher(JTextField tfSurnameTeacher) {
		this.tfSurnameTeacher = tfSurnameTeacher;
	}

	public JTextField getTfNameTeacher() {
		return tfNameTeacher;
	}

	public void setTfNameTeacher(JTextField tfNameTeacher) {
		this.tfNameTeacher = tfNameTeacher;
	}

	public JTextField getTfPatronymicTeacher() {
		return tfPatronymicTeacher;
	}

	public void setTfPatronymicTeacher(JTextField tfPatronymicTeacher) {
		this.tfPatronymicTeacher = tfPatronymicTeacher;
	}

	public JTextField getTfNameClass() {
		return tfNameClass;
	}

	public void setTfNameClass(JTextField tfNameClass) {
		this.tfNameClass = tfNameClass;
	}

	public JTextField getTfNameSubject() {
		return tfNameSubject;
	}

	public void setTfNameSubject(JTextField tfNameSubject) {
		this.tfNameSubject = tfNameSubject;
	}

	public JTextField getTfDate() {
		return tfDate;
	}

	public void setTfDate(JTextField tfDate) {
		this.tfDate = tfDate;
	}

	public JTextField getTfMark() {
		return tfMark;
	}

	public void setTfMark(JTextField tfMark) {
		this.tfMark = tfMark;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbPupil() {
		return cbPupil;
	}
	
	@SuppressWarnings("rawtypes")
	public void setCbPupil(JComboBox cbPupil) {
		this.cbPupil = cbPupil;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbSubject() {
		return cbSubject;
	}

	@SuppressWarnings("rawtypes")
	public void setCbSubject(JComboBox cbSubject) {
		this.cbSubject = cbSubject;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbTeacher() {
		return cbTeacher;
	}

	@SuppressWarnings("rawtypes")
	public void setCbTeacher(JComboBox cbTeacher) {
		this.cbTeacher = cbTeacher;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbClass() {
		return cbClass;
	}

	@SuppressWarnings("rawtypes")
	public void setCbClass(JComboBox cbClass) {
		this.cbClass = cbClass;
	}

	public JPanel getpPupil() {
		return pPupil;
	}

	public JPanel getpTeacher() {
		return pTeacher;
	}

	public JPanel getpClass() {
		return pClass;
	}

	public JPanel getpSubject() {
		return pSubject;
	}

	public Panel getpJournal() {
		return pJournal;
	}

	private void initialization()
	{	
		pJournal.setLayout(null);

		cbPupil.setBounds(95, 11, 309, 20);
		pJournal.add(cbPupil);	
		
		JLabel lblPupil = new JLabel("pupil");
		lblPupil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPupil.setBounds(10, 11, 75, 18);
		pJournal.add(lblPupil);
		
		JLabel lblTeacher = new JLabel("teacher");
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeacher.setBounds(10, 42, 75, 18);
		pJournal.add(lblTeacher);
		
		cbTeacher.setBounds(95, 42, 309, 20);
		pJournal.add(cbTeacher);

		JLabel lblSubject = new JLabel("subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(10, 71, 75, 18);
		pJournal.add(lblSubject);
		
		cbSubject.setBounds(95, 71, 309, 20);
		pJournal.add(cbSubject);
		
		
		JLabel label_3 = new JLabel("date");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 100, 75, 18);
		pJournal.add(label_3);
		
		tfDate = new JTextField();
		tfDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfDate.setColumns(10);
		tfDate.setBounds(95, 100, 309, 20);
		pJournal.add(tfDate);
		
		JLabel label_4 = new JLabel("mark");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 129, 75, 18);
		pJournal.add(label_4);
		
		tfMark = new JTextField();
		tfMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMark.setColumns(10);
		tfMark.setBounds(95, 129, 309, 20);
		pJournal.add(tfMark);

		pSubject.setLayout(null);
		
		JLabel label_16 = new JLabel("name");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_16.setBounds(10, 11, 49, 20);
		pSubject.add(label_16);
		
		tfNameSubject = new JTextField();
		tfNameSubject.setColumns(10);
		tfNameSubject.setBounds(69, 13, 345, 20);
		pSubject.add(tfNameSubject);
		pClass.setLayout(null);
		
		JLabel label_17 = new JLabel("name");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(10, 11, 49, 20);
		pClass.add(label_17);
		
		tfNameClass = new JTextField();
		tfNameClass.setColumns(10);
		tfNameClass.setBounds(69, 13, 345, 20);
		pClass.add(tfNameClass);
		pTeacher.setLayout(null);
		
		JLabel label_13 = new JLabel("surname");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(10, 11, 95, 20);
		pTeacher.add(label_13);
		
		tfSurnameTeacher = new JTextField();
		tfSurnameTeacher.setColumns(10);
		tfSurnameTeacher.setBounds(115, 11, 299, 20);
		pTeacher.add(tfSurnameTeacher);
		
		JLabel label_14 = new JLabel("name");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_14.setBounds(10, 42, 95, 20);
		pTeacher.add(label_14);
		
		tfNameTeacher = new JTextField();
		tfNameTeacher.setColumns(10);
		tfNameTeacher.setBounds(115, 42, 299, 20);
		pTeacher.add(tfNameTeacher);
		
		JLabel label_15 = new JLabel("patronymic");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_15.setBounds(10, 73, 95, 20);
		pTeacher.add(label_15);
		
		tfPatronymicTeacher = new JTextField();
		tfPatronymicTeacher.setColumns(10);
		tfPatronymicTeacher.setBounds(115, 73, 299, 20);
		pTeacher.add(tfPatronymicTeacher);
		pPupil.setLayout(null);
		
		JLabel lblClass = new JLabel("class");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClass.setBounds(10, 11, 143, 20);
		pPupil.add(lblClass);
		
		
		cbClass.setBounds(163, 13, 251, 20);
		pPupil.add(cbClass);
		
		tfSurnamePupil = new JTextField();
		tfSurnamePupil.setColumns(10);
		tfSurnamePupil.setBounds(163, 44, 251, 20);
		pPupil.add(tfSurnamePupil);
		
		JLabel label_6 = new JLabel("surname");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 42, 143, 20);
		pPupil.add(label_6);
		
		tfNamePupil = new JTextField();
		tfNamePupil.setColumns(10);
		tfNamePupil.setBounds(163, 77, 251, 20);
		pPupil.add(tfNamePupil);
		
		JLabel label_7 = new JLabel("name");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 75, 143, 20);
		pPupil.add(label_7);
		
		tfPatronymic = new JTextField();
		tfPatronymic.setColumns(10);
		tfPatronymic.setBounds(163, 110, 251, 20);
		pPupil.add(tfPatronymic);
		
		JLabel label_8 = new JLabel("patronymic");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(10, 108, 143, 20);
		pPupil.add(label_8);
		
		JLabel label_9 = new JLabel("date_of_birth");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(10, 141, 143, 20);
		pPupil.add(label_9);
		
		tfDateOfBirth = new JTextField();
		tfDateOfBirth.setColumns(10);
		tfDateOfBirth.setBounds(163, 143, 251, 20);
		pPupil.add(tfDateOfBirth);
		
		JLabel label_10 = new JLabel("place_of_residence");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(10, 172, 143, 20);
		pPupil.add(label_10);
		
		tfPlaceOfResidence = new JTextField();
		tfPlaceOfResidence.setColumns(10);
		tfPlaceOfResidence.setBounds(163, 174, 251, 20);
		pPupil.add(tfPlaceOfResidence);
		
		JLabel label_11 = new JLabel("phone");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(10, 203, 143, 20);
		pPupil.add(label_11);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(163, 205, 251, 20);
		pPupil.add(tfPhone);
		
		JLabel label_12 = new JLabel("blood_type");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(10, 234, 143, 20);
		pPupil.add(label_12);
		
		tfBloodType = new JTextField();
		tfBloodType.setColumns(10);
		tfBloodType.setBounds(163, 236, 251, 20);
		pPupil.add(tfBloodType);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnCancel);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addOrUpdate();
				dispose();
			}
		});
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnOk);
		
		contentPane.add(pSelectTable, BorderLayout.NORTH);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pSelectTable.add(lblTable);
		
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				currentTable = (TableType)comboBox.getSelectedItem();
				changePanel();
			}
		});
		
		pSelectTable.add(comboBox);		
		
		contentPane.add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new BorderLayout(0, 0));
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	@SuppressWarnings("unchecked")
	public FEdit(TableType table, DBcontroller dbController) 
	{
		initialization();
		mode = "add";	
		comboBox.addItem(dbController.getJournalTable());
		comboBox.addItem(dbController.getPupilTable());
		comboBox.addItem(dbController.getTeacherTable());
		comboBox.addItem(dbController.getSubjectTable());
		comboBox.addItem(dbController.getClassTable());
		currentTable = table;
		changePanel();
		comboBox.setSelectedItem(currentTable);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FEdit(TableType table, int id, DBcontroller dbController)
	{
		initialization();
		currentTable = table;
		mode = "update";
		this.id = id;
		changePanel();
		fillFields();
		contentPane.remove(pSelectTable);
	}

	private void fillFields()
	{
		currentTable.fillPanelFields(this, id);
	}
	
	private void changePanel()
	{		
		currentTable.chngePanel(this);
		pMain.updateUI();
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
    
    private void addOrUpdate()
    {
    	if(mode == "add") currentTable.addRowToDB(this);
    	else currentTable.updateRowInDB(this, id);
    }
    
    @Override
    public void dispose()
    {
		fireListenersOnClose();
		super.dispose();
    }
}
