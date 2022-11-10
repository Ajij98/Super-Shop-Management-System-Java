package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.security.auth.Refreshable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class PartyInfoWork extends JPanel{

	JPanel panelWest=new JPanel();
	JPanel panelCenter=new JPanel();	
	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();	
	JPanel panelWestCenterCenter=new JPanel();
	JPanel panelWestCenterEast=new JPanel();	
	SuggestText cmbSearch=new SuggestText();
	JLabel lblSearchIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));	

	ImageIcon iconAdd=new ImageIcon("Images/add1.png");
	JButton btnAdd=new JButton("Add",iconAdd);
	ImageIcon iconEdit=new ImageIcon("Images/btnEdit.png");
	JButton btnEdit=new JButton("Edit",iconEdit);
	ImageIcon iconRefresh=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	ImageIcon iconDelete=new ImageIcon("Images/cancel-icon.png");
	JButton btnDelete=new JButton("Delete",iconDelete);	
	ImageIcon iconReport=new ImageIcon("Images/Report.png");
	JButton btnReport=new JButton("Report",iconReport);	

	JLabel lblClientId=new JLabel("Client ID");
	JLabel lblClientName=new JLabel("Client Name");
	JLabel lblGender=new JLabel("Gender");
	JLabel lblFathersName=new JLabel("Fathers Name");
	JLabel lblMothersName=new JLabel("Mothers Name");
	JLabel lblReligion=new JLabel("Religion");
	JLabel lblDateOfBirth=new JLabel("Date of Birth");
	JLabel lblDateOfJoin=new JLabel("Date of Join");
	JLabel lblMblNum=new JLabel("Mobile Number");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblEmail=new JLabel("Email");
	JLabel lblNationalid=new JLabel("National Id");
	JLabel lblNatonality=new JLabel("Natonality");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblSearch=new JLabel();
	JLabel lblReferenceBy=new JLabel("Reference By");	

	JTextField txtClientId=new JTextField(16);
	JTextField txtClientName=new JTextField();
	JComboBox cmbGender=new JComboBox();
	JTextField txtFatherName=new JTextField();
	JTextField txtMotherName=new JTextField();
	JTextField txtReligion=new JTextField();
	JDateChooser dDateofBirth=new JDateChooser();
	JDateChooser dDateofJoin=new JDateChooser();
	JTextField txtMobileNumber=new JTextField();
	JTextArea txtAddress=new JTextArea(3,20);
	JTextField txtEmail=new JTextField();
	JTextField txtNationalId=new JTextField();
	JTextField txtNationality=new JTextField();
	JTextField txtUserName=new JTextField();
	JComboBox cmbReferenceBy=new JComboBox();
	Font font=new Font("clibari", Font.BOLD, 12);

	JScrollPane scrollAddress=new JScrollPane(txtAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel lblUpload=new JLabel();
	ImageIcon iconUpload=new ImageIcon("Images/upload22.png");
	JButton btnUpload=new JButton("Upload",iconUpload);

	String col[]={"Client ID","Client Name","Mobile","Email"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col){	
		public boolean isCellEditable(int row,int col){
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SessionBean sessionBean=new SessionBean();
	boolean isUpate=false;
	
	JFileChooser fileChooser;
	File imageFile=null;
	boolean isUplooad=false;
	String imageStoreFolder="F:ProjectImage/images";
	BufferedImage buffer=null;

	public PartyInfoWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		txtUserName.setText(sessionBean.getUserName());
		lblSearch.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void setOpaqueTrueeFalse(boolean b){
		panelWest.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelWestCenterCenter.setOpaque(b);
		panelWestCenterEast.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestSouth.setOpaque(b);
		panelCenter.setOpaque(b);
	}
	public void btnIni(boolean b){
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnUpload.setEnabled(b);
	}
	public void editable(boolean b){
		txtClientName.setEditable(b);
		cmbGender.setEnabled(b);
		txtFatherName.setEditable(b);
		txtMotherName.setEditable(b);
		txtReligion.setEditable(b);
		txtMobileNumber.setEditable(b);
		txtAddress.setEditable(b);
		txtEmail.setEditable(b);
		txtNationalId.setEditable(b);
		txtNationality.setEditable(b);
		cmbReferenceBy.setEnabled(b);
	}
	public void txtClear(){
		txtClientName.setText("");
		cmbGender.setSelectedIndex(0);
		txtFatherName.setText("");
		txtMotherName.setText("");
		txtReligion.setText("");
		dDateofBirth.setDate(new Date());
		dDateofJoin.setDate(new Date());
		txtMobileNumber.setText("+88");
		txtAddress.setText("");
		txtEmail.setText("");
		txtNationalId.setText("");
		txtNationality.setText("");
		cmbReferenceBy.setSelectedIndex(0);
		txtUserName.setText(sessionBean.getUserName());
		cmbSearch.txtSuggest.setText("");
		lblUpload.setIcon(new ImageIcon(""));
	}
	public void refreshWork(){
		autoId();
		tableDataLoad();
		cmbSearchDataLoad();
		btnIni(true);
		editable(true);
		txtClear();
		isUpate=false;
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isUpate){
						if(checkConfirmation("do you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data updated successfully");
									refreshWork();
								}
							}
						}
					}
					else{
						if(checkConfirmation("do you want to insert?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "data inserted successfully");
								refreshWork();
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIni(true);
				editable(true);
				isUpate=true;
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(checkConfirmation("Are you sure you want to delete?")){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "data deleted successfully");
							refreshWork();
						}
					}
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				btnIni(false);
				editable(false);
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {			
			}
		});
		table.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				btnIni(false);
				editable(false);
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		cmbSearch.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cmbSearch.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbSearch.txtSuggest.getText().trim(), "#");
					String id=token.nextToken();
					searchDataLoad(id);
					btnIni(false);
					editable(false);
				}
				else{
					refreshWork();
				}
			}
		});
		txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				if(txtMobileNumber.getText().trim().toString().length()<3){
					txtMobileNumber.setText("+88");
				}
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!(Character.isDigit(ch) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadAction();
			}
		});
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(SUBSTRING(clientId,LOCATE('-',clientId)+1,"
					+ "LENGTH(clientId)-LOCATE('-',clientId)) as UNSIGNED)),0)+1 as id from tbpartyinfo ";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtClientId.setText("clientId-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId from party");
		}
	}
	public boolean checkValidation(){
		if(!txtClientName.getText().trim().isEmpty()){
			if(cmbGender.getSelectedItem()!=null&&cmbGender.getSelectedIndex()!=0){
				if(!txtFatherName.getText().trim().isEmpty()){
					if(!txtMotherName.getText().trim().isEmpty()){
						if(!txtReligion.getText().trim().isEmpty()){
							if(!txtMobileNumber.getText().trim().isEmpty()){
								if(!txtAddress.getText().trim().isEmpty()){
									if(!txtEmail.getText().trim().isEmpty()){
										if(!txtNationalId.getText().trim().isEmpty()){
											if(!txtNationality.getText().trim().isEmpty()){
												if(cmbReferenceBy.getSelectedItem()!=null&&
														cmbReferenceBy.getSelectedIndex()!=0){
													if(mobileNumberValidation()){
														if(emailFormatVlidation()){
															return true;
														}
													}
												}
												else{
													JOptionPane.showMessageDialog(null, "select ReferenceBy");
												}
											}
											else{
												JOptionPane.showMessageDialog(null, "insert Nationality");
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "insert NationalId");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "insert Email");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "insert Address");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "insert Mobile Number");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "insert Religion");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "insert Mother Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert Father Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "select Gender");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "insert client Name");
		}
		return false;
	}
	public boolean mobileNumberValidation(){
		/*if(txtMobileNumber.getText().trim().length()<14){
			JOptionPane.showMessageDialog(null, "Mobile Number less then 11 digit..(please type 11 digit)");
			return false;
		}
		else if(txtMobileNumber.getText().trim().length()>14){
			JOptionPane.showMessageDialog(null, "Mobile Number more then 11 digit..(please type only 11 digit)");
			return false;
		}
		return true;*/
		String number_format="^[+0-9]{14}$";
		Pattern number_pattern=Pattern.compile(number_format);
		Matcher number_matcher=number_pattern.matcher(txtMobileNumber.getText().trim());
		if(!number_matcher.matches()){
			JOptionPane.showMessageDialog(null, "Mobile Number is not valid...\n(All number have to write in "
					+ "digit format and \nnot more than 11 and \nnot less than 11 digit","Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean emailFormatVlidation(){
		String email_format="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern email_pattern=Pattern.compile(email_format);
		Matcher email_match=email_pattern.matcher(txtEmail.getText().trim().toString());
		if(!email_match.matches()){
			JOptionPane.showMessageDialog(null, "Email Format is not correct."
					+ "please write Mail Address like this format (abcde12@gmail.com)","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public void cmbReferenceByDataLoad(){
		try {
			cmbReferenceBy.removeAllItems();
			cmbReferenceBy.addItem("");
			String sql="select userId,userName from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbReferenceBy.addItem(rs.getString("userId")+"#"+rs.getString("userName"));
			}
			dbConneciton.con.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "cmbReference data load from partyInfo");
		}
	}
	public void uploadAction(){
		fileChooser=new JFileChooser();
		fileChooser.setDialogTitle("please selct an image");
		fileChooser.showOpenDialog(this);
		FileNameExtensionFilter filter=new FileNameExtensionFilter("image", "jpg","PNG","jpeg","gif");
		fileChooser.setFileFilter(filter);
		imageFile=fileChooser.getSelectedFile();
		if(imageFile!=null){
			Image image=Toolkit.getDefaultToolkit().getImage(imageFile.getPath());
			Image resize=image.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), 
					Image.SCALE_DEFAULT);
			ImageIcon icon=new ImageIcon(resize);
			lblUpload.setIcon(icon);
			isUplooad=true;
		}
	}
	public boolean insertData(){
		try {
			if(isUplooad){
				File imageStoreFile=new File(imageStoreFolder);
				if(!imageStoreFile.isDirectory()){
					imageStoreFile.mkdirs();
				}
				buffer=ImageIO.read(imageFile);
				File imageNameFile=new File(imageStoreFolder+"/"+txtClientId.getText().trim()+".jpg");
				if(imageNameFile.exists()){
					imageNameFile.delete();
				}
				ImageIO.write(buffer, "jpg", imageNameFile);
			}
			String dbImage=isUplooad?txtClientId.getText().trim()+".jpg":"";
			String dateofBirth=new SimpleDateFormat("yyyy-MM-dd").format(dDateofBirth.getDate());
			String dateofJoin=new SimpleDateFormat("yyyy-MM-dd").format(dDateofJoin.getDate());
			String sql="insert into tbpartyinfo(clientId,clientName,Gender,fathersName,mothersName,Religion,"
					+ "dateofBirth,dateofJoin,mobileNumber,Address,Email,Nationalid,Nationality,userName,"
					+ "ReferenceBy,image,userip,entryTime)"
					+ "values('"+txtClientId.getText().trim().toString()+"',"
					+ "'"+txtClientName.getText().trim().toString()+"',"
					+ "'"+cmbGender.getSelectedItem().toString()+"',"
					+ "'"+txtFatherName.getText().trim().toString()+"',"
					+ "'"+txtMotherName.getText().trim().toString()+"',"
					+ "'"+txtReligion.getText().trim().toString()+"',"
					+ "'"+dateofBirth+"',"
					+ "'"+dateofJoin+"',"
					+ "'"+txtMobileNumber.getText().trim().toString()+"',"
					+ "'"+txtAddress.getText().trim().toString()+"',"
					+ "'"+txtEmail.getText().trim().toString()+"',"
					+ "'"+txtNationalId.getText().trim().toString()+"',"
					+ "'"+txtNationality.getText().trim().toString()+"',"
					+ "'"+txtUserName.getText().trim().toString()+"',"
					+ "'"+cmbReferenceBy.getSelectedItem().toString()+"','"+dbImage+"','',now()) ";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert data from clientInfo");
		}
		return false;
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1; i>=0; i--){
				model.removeRow(i);
			}
			String sql="select clientId,clientName,mobileNumber,Email from tbpartyinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("clientId"),rs.getString("clientName"),
						rs.getString("mobileNumber"),rs.getString("Email")});
			}
			dbConneciton.con.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableData load from client");
		}
	}
	public void cmbSearchDataLoad(){
		try {
			cmbSearch.v.clear();
			cmbSearch.v.add("");
			String sql="select clientId,clientName from tbpartyinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearch.v.add(rs.getString("clientId")+"#"+rs.getString("clientName"));
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch data load from client");
		}
	}
	public void searchDataLoad(String clientId){
		try {
			String sql="select clientId,clientName,Gender,fathersName,mothersName,Religion,dateofBirth,"
					+ "dateofJoin,mobileNumber,Address,Email,Nationalid,Nationality,userName,ReferenceBy,"
					+ " image from tbpartyinfo where clientId like '"+clientId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtClientId.setText(rs.getString("clientId"));
				txtClientName.setText(rs.getString("clientName"));
				cmbGender.setSelectedItem(rs.getString("Gender"));
				txtFatherName.setText(rs.getString("fathersName"));
				txtMotherName.setText(rs.getString("mothersName"));
				txtReligion.setText(rs.getString("Religion"));
				dDateofBirth.setDate(rs.getDate("dateofBirth"));
				dDateofJoin.setDate(rs.getDate("dateofJoin"));
				txtMobileNumber.setText(rs.getString("mobileNumber"));
				txtAddress.setText(rs.getString("Address"));
				txtEmail.setText(rs.getString("Email"));
				txtNationalId.setText(rs.getString("Nationalid"));
				txtNationality.setText(rs.getString("Nationality"));
				txtUserName.setText(rs.getString("userName"));
				cmbReferenceBy.setSelectedItem(rs.getString("ReferenceBy"));
			}
			if(!rs.getString("image").trim().isEmpty()){
				imageFile=new File(imageStoreFolder+"/"+rs.getString("image"));
				Image image=Toolkit.getDefaultToolkit().getImage(imageFile.getPath());
				Image resize=image.getScaledInstance(lblUpload.getWidth(), lblUpload.getHeight(), 
						Image.SCALE_DEFAULT);
				ImageIcon icon=new ImageIcon(resize);
				lblUpload.setIcon(icon);
				isUplooad=true;
			}
			else{
				lblUpload.setIcon(new ImageIcon(""));
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"search data load from clientInfo");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbpartyinfo where clientId like '"+txtClientId.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete data from clientInfo");
		}
		return false;
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));		
		TitledBorder titlePartyInfo=BorderFactory.createTitledBorder("Party Info");
		setBorder(titlePartyInfo);
		titlePartyInfo.setTitleJustification(TitledBorder.CENTER);
		titlePartyInfo.setTitleColor(Color.decode("#8B0000"));
		titlePartyInfo.setTitleFont(new Font("clibari", Font.BOLD, 22));	
		BorderLayout border=new BorderLayout();
		setLayout(border);
		border.setHgap(10);
	}
	private void cmp() {
		setLayout(new BorderLayout());
		add(panelWest,BorderLayout.WEST);
		panelWest();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
	}
	private void panelCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenter.add(scrollTable, cn);
		scrollTable.setPreferredSize(new Dimension(525, 655));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight()+9);
	}
	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(590, 725));
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelWestNorth, BorderLayout.NORTH);
		panelWestNorth();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenter();
		panelWest.add(panelWestSouth, BorderLayout.SOUTH);
		panelWestSouth();	
	}
	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 60));		
		FlowLayout flow=new FlowLayout();
		panelWestSouth.setLayout(flow);
		flow.setHgap(15);
		flow.setVgap(15);

		panelWestSouth.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnEdit);
		btnEdit.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnDelete);
		btnDelete.setPreferredSize(new Dimension(100, 35));	
		panelWestSouth.add(btnReport);
		btnReport.setPreferredSize(new Dimension(100, 35));
	}
	private void panelWestCenter() {
		panelWestCenter.setLayout(new BorderLayout());	
		panelWestCenter.add(panelWestCenterCenter,BorderLayout.CENTER);
		panelWestCenterCenter();
		panelWestCenter.add(panelWestCenterEast, BorderLayout.EAST);
		panelWestCenterEast();
	}
	private void panelWestCenterEast() {
		panelWestCenterEast.setPreferredSize(new Dimension(160, 0));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenterEast.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterEast.add(lblUpload, cn);
		lblUpload.setPreferredSize(new Dimension(142, 165));
		lblUpload.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 20, 270, 20);
		panelWestCenterEast.add(btnUpload, cn);
		btnUpload.setPreferredSize(new Dimension(80, 35));
	}
	private void panelWestCenterCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenterCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblClientId,cn);
		lblClientId.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtClientId,cn);
		txtClientId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblClientName,cn);
		lblClientName.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtClientName,cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblGender,cn);
		lblGender.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(cmbGender,cn);
		cmbGender.addItem("");
		cmbGender.addItem("Male");
		cmbGender.addItem("Female");

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblFathersName,cn);
		lblFathersName.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtFatherName,cn);

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblMothersName,cn);
		lblMothersName.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtMotherName,cn);

		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblReligion,cn);
		lblReligion.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtReligion,cn);

		cn.gridx=0;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblDateOfBirth,cn);
		lblDateOfBirth.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(dDateofBirth,cn);
		dDateofBirth.setDateFormatString("dd-MM-yyyy");
		dDateofBirth.setDate(new Date());

		cn.gridx=0;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblDateOfJoin,cn);
		lblDateOfJoin.setFont(font);

		cn.gridx=1;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(dDateofJoin,cn);
		dDateofJoin.setDateFormatString("dd-MM-yyyy");
		dDateofJoin.setDate(new Date());

		cn.gridx=0;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblMblNum,cn);
		lblMblNum.setFont(font);

		cn.gridx=1;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtMobileNumber,cn);
		txtMobileNumber.setText("+88");

		cn.gridx=0;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblAddress,cn);
		lblAddress.setFont(font);

		cn.gridx=1;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(scrollAddress,cn);

		cn.gridx=0;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblEmail,cn);
		lblEmail.setFont(font);

		cn.gridx=1;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtEmail,cn);

		cn.gridx=0;
		cn.gridy=11;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblNationalid,cn);
		lblNationalid.setFont(font);

		cn.gridx=1;
		cn.gridy=11;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtNationalId,cn);

		cn.gridx=0;
		cn.gridy=12;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblNatonality,cn);
		lblNatonality.setFont(font);

		cn.gridx=1;
		cn.gridy=12;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtNationality,cn);

		cn.gridx=0;
		cn.gridy=13;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblUserName,cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=13;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(txtUserName,cn);
		txtUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=13;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblSearch,cn);
		lblSearch.setFont(font);

		cn.gridx=0;
		cn.gridy=14;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(lblReferenceBy,cn);
		lblReferenceBy.setFont(font);	
		cn.gridx=1;
		cn.gridy=14;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 5, 1, 5);
		panelWestCenterCenter.add(cmbReferenceBy,cn);
	}
	private void panelWestNorth() {
		panelWestNorth.setPreferredSize(new Dimension(0, 60)); 
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 15, 5);
		panelWestNorth.add(cmbSearch.cmbSuggest, cn);
		cmbSearch.cmbSuggest.setPreferredSize(new Dimension(350, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 15, 5);
		panelWestNorth.add(lblSearchIcon, cn);
	}
}
