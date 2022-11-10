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
import java.nio.channels.ShutdownChannelGroupException;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
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

public class SupplierWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelWest=new JPanel();

	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();

	JPanel panelWestCenterEast=new JPanel();
	JPanel panelWestCenterCenter=new JPanel();

	JLabel lblSupplierId=new JLabel("Supplier ID");
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JLabel lblMailAddress=new JLabel("Mail Address");
	JLabel lblMblNumber=new JLabel("Moblile Number");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblExecutive=new JLabel("Executive");

	JTextField txtSupplierId=new JTextField(18);
	JTextField txtSupplierName=new JTextField();
	JTextField txtMailAddress=new JTextField();
	JTextField txtMobileNumber=new JTextField();
	JTextArea txtAddress=new JTextArea(3,18);
	JTextField txtUserName=new JTextField();
	Font font=new Font("clibari", Font.BOLD, 12);
	JScrollPane scroll=new JScrollPane(txtAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SuggestText cmbSearchSupplier=new SuggestText();
	JLabel lblSearchIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	JLabel lblUpload=new JLabel();
	ImageIcon uploadIcon=new ImageIcon("Images/upload22.png");
	JButton btnUpload=new JButton("Upload",uploadIcon);

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

	String col[]={"Supplier ID","Supplier Name","Mobile Number"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col){
		public boolean isCellEditable(int row, int col){
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SessionBean sessionBean=new SessionBean();	
	boolean isUpdate=false;

	JFileChooser FChooser;
	File Imagefile=null;
	String imageStorFolder="D:/SoftwareImage/SupplierImage";
	BufferedImage buffer=null;
	boolean isUpload=false;

	public SupplierWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		txtUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void setOpaqueTrueFalse(boolean b){
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
		txtSupplierName.setEditable(b);
		txtMailAddress.setEditable(b);
		txtMobileNumber.setEditable(b);
		txtAddress.setEditable(b);
	}
	public void txtClear(){
		txtSupplierName.setText("");
		txtMailAddress.setText("");
		txtMobileNumber.setText("+88");
		txtAddress.setText("");
		cmbSearchSupplier.txtSuggest.setText("");
		txtUserName.setText(sessionBean.getUserName());
		lblUpload.setIcon(new ImageIcon(""));
	}
	public void refreshWork(){
		autoId();
		tableDataLoad();
		cmbSearchDataLoadSupplier();
		btnIni(true);
		editable(true);
		txtClear();
		isUpdate=false;
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isUpdate){
						if(checkConfirmation("do you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "Data Updated sucessfully");
									refreshWork();
								}
							}
						}
					}
					else{
						if(checkConfirmation("do you want to insert?")){
							JOptionPane.showMessageDialog(null, "Data inserted successfully");
							insertData();
							refreshWork();
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIni(true);
				editable(true);
				isUpdate=true;
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWork();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					if(checkConfirmation("do you want to delete?")){
						JOptionPane.showMessageDialog(null, "data Deleted successfully");
						deleteData();
						refreshWork();
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
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				txtUserName.setText("Abdul Ajij");
				btnIni(false);
				editable(false);		
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		cmbSearchSupplier.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbSearchSupplier.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbSearchSupplier.txtSuggest.getText().trim(),"#");
					String id=token.nextToken();
					searchDataLoad(id);
					txtUserName.setText("Abdul Ajij");
					btnIni(false);
					editable(false);
				}
				else{
					refreshWork();
				}
			}
		});
		txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				if(txtMobileNumber.getText().toString().trim().length()<3){
					txtMobileNumber.setText("+88");
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upLoadAction();
			}
		});
		/*txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				try {
					int i=Integer.parseInt(txtMobileNumber.getText());

				} 
				catch (NumberFormatException e1) {
					show
				}
			}
		});*/
		txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(SupplierID,LOCATE('-',SupplierID)+1,"
					+ "LENGTH(SupplierID)-LOCATE('-',SupplierID)) as UNSIGNED)),0)+1 as id from tbsupplierinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtSupplierId.setText("supplierid-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId from supplier");
		}
	}
	public boolean checkValidation(){
		if(!txtSupplierName.getText().trim().isEmpty()){
			if(!txtMailAddress.getText().trim().isEmpty()){
				if(!txtAddress.getText().trim().isEmpty()){
					if(emailFormatValidation()){
						if(mobileNumberValidation()){
							return true;
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "please insert Address");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "please insert Mail Address");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "please insert Supplier Name");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public void upLoadAction(){
		FChooser=new JFileChooser();
		FChooser.setDialogTitle("Image Selection");
		FileNameExtensionFilter filter=new FileNameExtensionFilter("image","jpg","PNG","gif","jpeg");
		FChooser.setFileFilter(filter);
		FChooser.showOpenDialog(this);
		Imagefile=FChooser.getSelectedFile();
		if(Imagefile!=null){
			Image image=Toolkit.getDefaultToolkit().getImage(Imagefile.getPath());
			Image resize=image.getScaledInstance(lblUpload.getWidth(),lblUpload.getHeight(),
					Image.SCALE_DEFAULT);
			ImageIcon imageIcon=new ImageIcon(resize);
			lblUpload.setIcon(imageIcon);
			isUpload=true;	
		}
	}
	public boolean insertData(){
		try {
			if(isUpload){
				File StoreFile=new File(imageStorFolder);
				if(!StoreFile.isDirectory()){
					StoreFile.mkdirs();
				}
				buffer=ImageIO.read(Imagefile);
				File ImageNameFile=new File(imageStorFolder+"/"+txtSupplierId.getText()+".jpg");
				if(ImageNameFile.exists()){
					ImageNameFile.delete();
				}
				ImageIO.write(buffer,"jpg",ImageNameFile);
			}
			String dbPicture=isUpload?txtSupplierId.getText().trim()+".jpg":"";
			
			String sql="insert into tbsupplierinfo(SupplierID,SupplierName,MailAddress,MobileNumber,Address,"
					+ "UserName,Image,userip,entryTime)"
					+ "values('"+txtSupplierId.getText().trim().toString()+"',"
					+ "'"+txtSupplierName.getText().trim().toString()+"',"
					+ "'"+txtMailAddress.getText().trim().toString()+"',"
					+ "'"+txtMobileNumber.getText().trim().toString()+"',"
					+ "'"+txtAddress.getText().trim().toString()+"',"
					+ "'"+txtUserName.getText().trim().toString()+"','"+dbPicture+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert Data from supplier");
		}
		return false;
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1;i>=0;i--){
				model.removeRow(i);
			}
			String sql="select SupplierID,SupplierName,MobileNumber from tbsupplierinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("SupplierID"),rs.getString("SupplierName"),
						rs.getString("MobileNumber")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"table data load from supplier");
		}
	}
	public void cmbSearchDataLoadSupplier(){
		try {
			cmbSearchSupplier.v.clear();
			cmbSearchSupplier.v.add("");
			String sql="select SupplierID,SupplierName from tbsupplierinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearchSupplier.v.add(rs.getString("SupplierID")+"#"+rs.getString("SupplierName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch data load from supplier");
		}
	}
	public void searchDataLoad(String supplierId){
		try {
			String sql="select SupplierID,SupplierName,MailAddress,MobileNumber,Address,"
					+ "UserName,image from tbsupplierinfo where supplierID like '"+supplierId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtSupplierId.setText(rs.getString("SupplierID"));
				txtSupplierName.setText(rs.getString("SupplierName"));
				txtMailAddress.setText(rs.getString("MailAddress"));
				txtMobileNumber.setText(rs.getString("MobileNumber"));
				txtAddress.setText(rs.getString("Address"));
				txtUserName.setText(rs.getString("UserName"));
			}
			if(!rs.getString("image").trim().isEmpty()){
				Imagefile=new File(imageStorFolder+"/"+rs.getString("image"));
				Image image=Toolkit.getDefaultToolkit().getImage(Imagefile.getPath());
				Image resize=image.getScaledInstance(lblUpload.getWidth(),lblUpload.getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon imageIcon=new ImageIcon(resize);
				lblUpload.setIcon(imageIcon);
				isUpload=true;	
			}
			else{
				lblUpload.setIcon(new ImageIcon(""));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchData Load from supplier");
		}
	}
	public boolean deleteData(){
		try {
			String imagepath=imageStorFolder+"/"+txtSupplierId.getText().trim()+".jpg";
			File imageDeleteFile=new File(imagepath);
			imageDeleteFile.delete();
			String sql="delete from tbsupplierinfo where supplierID like "
					+ "'"+txtSupplierId.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete Data from supplier","Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public boolean emailFormatValidation(){
		// email_pattren="^[a-zA-Z0-9]{1-20}@[a-zA-Z0-9]{1-20}.[a-zA-Z]{2-3}$";
		String email_pattren="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern emailPattren=Pattern.compile(email_pattren);
		Matcher emailMatch=emailPattren.matcher(txtMailAddress.getText().trim());

		if(!emailMatch.matches()){
			JOptionPane.showMessageDialog(null, "Email Format is not correct."
					+ "please write Mail Address like this format (abcde12@gmail.com)","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean mobileNumberValidation(){
		if(txtMobileNumber.getText().trim().toString().length()<14){
			JOptionPane.showMessageDialog(null, "Mobile number less than 11 digit(Type 11 digit only)","Error",
					JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		else if(txtMobileNumber.getText().trim().toString().length()>14){
			JOptionPane.showMessageDialog(null, "mobile Number greater than 11 digit(Type 11 digit only)",
					"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
		/*try {
			String sql="select substring(MobileNumber,locate('0',MobileNumber),"
					+ "Length(MobileNumber)-locate('0',MobileNumber)) as mobile from tbsupplierinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(txtMobileNumber.getText().trim().toString().length()<(rs.getString("mobile")));
				return  true;
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Mobile number is not valid");
		}
		return false;*/
	}
	private void cmp() {
		setLayout(new BorderLayout());
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelWest,BorderLayout.WEST);
		panelWest();
	}
	private void panelCenter() {
		FlowLayout flow=new FlowLayout();
		panelCenter.setLayout(flow);
		flow.setVgap(0);
		panelCenter.add(scrollTable);
		scrollTable.setPreferredSize(new Dimension(485, 660));
		table.getTableHeader().setReorderingAllowed(false);
	}
	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(630, 725));		
		panelWest.setLayout(new BorderLayout());		
		panelWest.add(panelWestNorth,BorderLayout.NORTH);
		panelWestNorth();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenter();
		panelWest.add(panelWestSouth, BorderLayout.SOUTH);
		panelWestSouth();
	}
	private void panelWestNorth() {
		panelWestNorth.setPreferredSize(new Dimension(0, 100));		
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestNorth.add(cmbSearchSupplier.cmbSuggest, cn);
		cmbSearchSupplier.cmbSuggest.setPreferredSize(new Dimension(350, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
	}
	private void panelWestCenter() {
		panelWestCenter.setLayout(new BorderLayout());

		panelWestCenter.add(panelWestCenterEast,BorderLayout.EAST);
		panelWestCenterEast();
		panelWestCenter.add(panelWestCenterCenter, BorderLayout.CENTER);
		panelWestCenterCenter();
	}

	private void panelWestCenterEast() {
		panelWestCenterEast.setPreferredSize(new Dimension(210, 0));
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
		cn.insets=new Insets(5, 20, 40, 20);
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
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblSupplierId,cn);
		lblSupplierId.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(txtSupplierId,cn);
		txtSupplierId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(font);


		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(txtSupplierName,cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblMailAddress,cn);
		lblMailAddress.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(txtMailAddress,cn);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblMblNumber,cn);
		lblMblNumber.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(txtMobileNumber,cn);
		txtMobileNumber.setText("+88");

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblAddress,cn);
		lblAddress.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(scroll,cn);

		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblUserName,cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(txtUserName,cn);
		txtUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterCenter.add(lblExecutive,cn);
	}

	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 200));
		//panelWestSouth.setBackground(Color.BLACK);

		FlowLayout flow=new FlowLayout();
		panelWestSouth.setLayout(flow);
		flow.setHgap(20);

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

	private void init() {
		setPreferredSize(new Dimension(1158,714));

		TitledBorder titleSupplier=BorderFactory.createTitledBorder("Supplier Info");
		setBorder(titleSupplier);
		titleSupplier.setTitleJustification(TitledBorder.CENTER);
		titleSupplier.setTitleColor(Color.decode("#8B0000"));
		titleSupplier.setTitleFont(new Font("clibari", Font.BOLD, 22));

		BorderLayout border=new BorderLayout();
		border.setHgap(10);
		setLayout(border);

	}

}
