package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Visibility;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.example.Admin.WorkingPanel;

public class CetagoryWork extends JPanel{

	JPanel panelWest=new JPanel();
	JPanel panelCenter=new JPanel();

	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();

	JPanel panelWestCenterNorth=new JPanel();
	JPanel panelWestCenterCenter=new JPanel();

	JPanel panelCenterNorth=new JPanel();
	JPanel panelCenterCenter=new JPanel();
	JPanel panelCenterSouth=new JPanel();

	JPanel panelCenterCenterNorth=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();

	JLabel lblCategoryId=new JLabel("Category ID");
	JLabel lblCategoryName=new JLabel("Category Name");
	JLabel lbluserName=new JLabel("User Name");
	JLabel lblUserType=new JLabel("Executive");
	Font font=new Font("clibari", Font.BOLD, 12);

	JLabel lblCategoryIdC=new JLabel("Category ID");
	JLabel lblSubCategoryIdC=new JLabel("Sub Category ID");
	JLabel lblCategoryNameC=new JLabel("Sub Category Name");
	JLabel lbluserNameC=new JLabel("User Name");
	JLabel lblUserTypeC=new JLabel("Executive");
	Font fontC=new Font("clibari", Font.BOLD, 12);

	JTextField txtcategoryId=new JTextField(20);
	JTextField txtcategoryName=new JTextField();
	JTextField txtuserName=new JTextField();

	SuggestText cmbCategoryIdC=new SuggestText();
	JTextField txtsubCategoryIdC=new JTextField(20);
	JTextField txtsubCategoryNameC=new JTextField();
	JTextField  txtuserNameC=new JTextField();

	SuggestText cmbSearchCetagory=new SuggestText();
	JLabel lblWestNorthIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));
	JLabel lblTableImage=new JLabel(new ImageIcon("Images/backGround.jpg"));

	SuggestText cmbSearchSubCetagory=new SuggestText();
	JLabel lblCenterNorthIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	ImageIcon iconAdd=new ImageIcon("Images/add1.png");
	JButton btnAdd=new JButton("Add",iconAdd);
	ImageIcon iconEdit=new ImageIcon("Images/btnEdit.png");
	JButton btnEdit=new JButton("Edit",iconEdit);
	ImageIcon iconRefresh=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	ImageIcon iconDelete=new ImageIcon("Images/cancel-icon.png");
	JButton btnDelete=new JButton("Delete",iconDelete);

	ImageIcon iconNew=new ImageIcon("Images/add1.png");
	JButton btnNewCenter=new JButton("New",iconAdd);
	ImageIcon iconEditCenter=new ImageIcon("Images/btnEdit.png");
	JButton btnEditCenter=new JButton("Edit",iconEdit);
	ImageIcon iconRefreshCenter=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefreshCenter=new JButton("Refresh",iconRefresh);
	ImageIcon iconDeleteCenter=new ImageIcon("Images/cancel-icon.png");
	JButton btnDeleteCenter=new JButton("Delete",iconDelete);

	String col[]={"Category ID","Category Name","User Name"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col){
		public boolean isCellEditable(int row, int col){
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String colC[]={"Category ID","Sub Category Id","Sub Category Name","User Name"};
	Object rowC[][]={};
	DefaultTableModel modelC=new DefaultTableModel(rowC, colC){
		public boolean isCellEditable(int rowC, int colC){
			return false;
		}
	};
	JTable tableC=new JTable(modelC);
	JScrollPane scrollC=new JScrollPane(tableC,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SessionBean sessionbean=new SessionBean();
	boolean isUpdate=false;


	public CetagoryWork(SessionBean sessionbean) {
		this.sessionbean=sessionbean;

		txtuserName.setText(sessionbean.getUserName());
		lblUserType.setText(sessionbean.getUserType());
		txtuserNameC.setText(sessionbean.getUserName());
		lbluserNameC.setText(sessionbean.getUserType());
		init();
		cmp();
		btnAction();
		btnini(true);
		editable(true);
		btnIniSubcategory(true);
		editableSubcategory(true);
	}
	public void setOpaqueTrueFalse(boolean b){
		panelWest.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelWestSouth.setOpaque(b);
		panelWestCenterCenter.setOpaque(b);
		panelWestCenterNorth.setOpaque(b);
		panelCenter.setOpaque(b);
		panelCenterCenter.setOpaque(b);
		panelCenterCenterCenter.setOpaque(b);
		panelCenterCenterNorth.setOpaque(b);
		panelCenterNorth.setOpaque(b);
		panelCenterSouth.setOpaque(b);
	}
	public void btnini(boolean b){
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void btnIniSubcategory(boolean b){
		btnNewCenter.setEnabled(b);
		btnEditCenter.setEnabled(!b);
	}
	public void editable(boolean b){
		txtcategoryName.setEditable(b);
	}
	public void editableSubcategory(boolean b){
		cmbCategoryIdC.txtSuggest.setEnabled(b);
		txtsubCategoryNameC.setEditable(b);
	}
	public void txtClear(){
		txtcategoryName.setText("");
		cmbSearchCetagory.txtSuggest.setText("");
		txtuserName.setText(sessionbean.getUserName());
		cmbSearchCetagory.txtSuggest.setText("");
	}
	public void txtClearSubcategory(){
		cmbCategoryIdC.txtSuggest.setText("");
		cmbSearchSubCetagory.txtSuggest.setText("");
		txtsubCategoryNameC.setText("");
		txtuserNameC.setText(sessionbean.getUserName());
	}
	public void refreshWork(){
		autoId();
		tabledataloadCategory();
		cmbSearchDataLoadCategory();
		txtClear();		
		btnini(true);
		editable(true);
		isUpdate=false;
	}
	public void refreshWorkSubcategory(){
		autoIdSubcategory();
		tableDataLoadSubcategory();
		cmbSearchDataLoadSubcategory();
		txtClearSubcategory();
		btnIniSubcategory(true);
		editableSubcategory(true);
		isUpdate=false;
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CheckValidation()){
					if(isUpdate){
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
						if(isExistCategoryName()){
							if(checkConfirmation("do you want to insert this data?")){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data inserted successfully");
									refreshWork();
								}
							}
						}
					}
				}
			}
		});
		btnNewCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidationSubcategory()){
					if(isUpdate){
						if(checkConfirmation("sure to upDate?")){
							if(deleteDataSubcategory()){
								if(insertDataSubcategory()){
									JOptionPane.showMessageDialog(null, "data updated successfully");
									refreshWorkSubcategory();
								}
							}
						}
					}
					else{
						if(isExistSubCategoryName()){
							if(checkConfirmation("do you want to insert?")){
								JOptionPane.showMessageDialog(null, "data inserted successfully");
								insertDataSubcategory();
								refreshWorkSubcategory();
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnini(true);
				editable(true);
				isUpdate=true;
			}
		});
		btnEditCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIniSubcategory(true);
				editableSubcategory(true);
				isUpdate=true;
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
		btnRefreshCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkSubcategory();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CheckValidation()){
					if(checkConfirmation("do you want to delete?")){
						JOptionPane.showMessageDialog(null, "your data deleted successfully");
						deleteData();
						refreshWork();
					}
				}
			}
		});
		btnDeleteCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationSubcategory()){
					if(checkConfirmation("Are you sure you want to delete?")){
						JOptionPane.showMessageDialog(null, "data deleted successfully");
						deleteDataSubcategory();
						refreshWorkSubcategory();
					}
				}
			}
		});
		cmbSearchCetagory.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbSearchCetagory.cmbSuggest.getSelectedItem()!=null && cmbSearchCetagory.cmbSuggest.
						getSelectedIndex()!=0){
					StringTokenizer token=new StringTokenizer(cmbSearchCetagory.txtSuggest.getText().
							trim().toString(), "#");
					String id=token.nextToken();
					searchDataLoad(id);
					btnini(false);
					editable(false);
				}
				else{
					refreshWork();
				}
			}
		});
		cmbSearchSubCetagory.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringTokenizer token=new StringTokenizer(cmbSearchSubCetagory.txtSuggest.getText().trim(),"#");
				String id=token.nextToken();
				searchDataLoadSubcategory(id);
				btnIniSubcategory(false);
				editableSubcategory(false);
			}
		});
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {
				String id=model.getValueAt(table.getSelectedRow(),0).toString();
				searchDataLoad(id);
				editable(false);
				btnini(false);
			}
		});
		tableC.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
				String id=modelC.getValueAt(tableC.getSelectedRow(), 1).toString();
				searchDataLoadSubcategory(id);
				btnIniSubcategory(false);
				editableSubcategory(false);
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
				String id=model.getValueAt(table.getSelectedRow(),0).toString();
				searchDataLoad(id);
				editable(false);
				btnini(false);
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		tableC.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				String id=modelC.getValueAt(tableC.getSelectedRow(), 1).toString();
				searchDataLoadSubcategory(id);
				btnIniSubcategory(false);
				editableSubcategory(false);
			}
			public void keyPressed(KeyEvent arg0) {

			}
		});
	}	
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(Substring(CategoryId,Locate('-',CategoryId)+1,"
					+ "LENGTH(CategoryId)-Locate('-',CategoryId))as UNSIGNED)),0)+1 as id from tbcategoryinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtcategoryId.setText("Cat-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception exp) {
			JOptionPane.showMessageDialog(null,exp+"autoId from category","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void autoIdSubcategory(){
		try {
			String sql="select ifnull(max(cast(SUBSTRING(subcategoryId,LOCATE('-',subcategoryId)+1,"
					+ "LENGTH(subcategoryId)-LOCATE('-',subcategoryId))as UNSIGNED)),0)+1 as id from tbsubcategoryinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtsubCategoryIdC.setText("subcat-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId from subcategory");
		}
	}
	public boolean CheckValidation(){
		if(!txtcategoryName.getText().trim().isEmpty()){
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null,"Please Insert CategoryName");
		}
		return false;
	}
	public boolean checkValidationSubcategory(){
		if(!cmbCategoryIdC.txtSuggest.getText().trim().isEmpty()){
			if(!txtsubCategoryNameC.getText().trim().isEmpty()){
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "insert subcategoryName");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select categroyId");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null,caption,"Confirm",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean isExistCategoryName(){
		try {
			String sql="select * from tbcategoryinfo where categoryName like "
					+ "'"+txtcategoryName.getText().trim().toString()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				JOptionPane.showMessageDialog(null, "This CategoryName Alreay Exist!");
				return false;
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"isExistCategoryName()");
		}
		return true;
	}
	public boolean isExistSubCategoryName(){
		try {
			StringTokenizer token=new StringTokenizer(cmbCategoryIdC.txtSuggest.getText().trim(), "#");
			token.nextToken();
			String catName=token.nextToken();

			String sql="select * from tbsubcategoryinfo where "
					+ "categoryName='"+catName+"' and "
					+ "subcategoryName='"+txtsubCategoryNameC.getText().toString().trim()+"' ";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				JOptionPane.showMessageDialog(null, "This SubCategoryName Alreay Exist!");
				return false;
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"isExistSubCategoryName()");
		}
		return true;
	}
	public boolean insertData(){
		try {
			String sql="insert into tbcategoryinfo (categoryId,categoryName,UserName,Userip,entrytime) "
					+ "values('"+txtcategoryId.getText().trim()+"','"+txtcategoryName.getText().trim()+"',"
					+ "'"+txtuserName.getText().trim()+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception exp) {
			JOptionPane.showMessageDialog(null,exp+"insertData from category","Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public boolean insertDataSubcategory(){
		try {
			StringTokenizer token=new StringTokenizer(cmbCategoryIdC.txtSuggest.getText().trim(), "#");
			String categoryId=token.nextToken();
			String catName=token.nextToken();
			String sql="insert into tbsubcategoryinfo(categoryId,categoryName,subcategoryId,subcategoryName,userName,userip,entryTime) "
					+ "VALUES('"+categoryId+"',"
					+ "'"+catName+"',"
					+ "'"+txtsubCategoryIdC.getText().trim().toString()+"',"
					+ "'"+txtsubCategoryNameC.getText().trim().toString()+"',"
					+ "'"+txtuserNameC.getText().trim().toString()+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert Data from subcategory");
		}
		return false;
	}
	public void tabledataloadCategory(){
		try{
			for(int i=model.getRowCount()-1;i>=0;i--){
				model.removeRow(i);
			}
			String sql="select categoryId,categoryName,UserName from tbcategoryinfo order by cast(substring"
					+ "(categoryid,locate('-',categoryid)+1,LENGTH(categoryid)-locate('-',categoryid))as "
					+ "UNSIGNED) desc";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("categoryId"),rs.getString("categoryName"),
						rs.getString("UserName")});
			}
			dbConneciton.con.close();
		}
		catch(Exception exp){
			JOptionPane.showMessageDialog(null,exp+"tabledataLoad from CategoryId","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void tableDataLoadSubcategory(){
		try {
			for(int i=modelC.getRowCount()-1; i>=0; i--){
				modelC.removeRow(i);
			}
			String sql="select categoryId,subcategoryId,subcategoryName,userName from tbsubcategoryinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelC.addRow(new Object[]{rs.getString("categoryId"),rs.getString("subcategoryId")
						,rs.getString("subcategoryName"),rs.getString("userName")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "table Data load from subcategory");
		}
	}
	public void cmbSearchDataLoadCategory(){
		try {
			cmbSearchCetagory.v.clear();
			cmbSearchCetagory.v.add("");
			cmbCategoryIdC.v.clear();
			cmbCategoryIdC.v.add("");
			String sql="select categoryid,categoryName from tbcategoryinfo order by categoryname";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearchCetagory.v.add(rs.getString("categoryid")+"#"+rs.getString("categoryName"));
				cmbCategoryIdC.v.add(rs.getString("categoryid")+"#"+rs.getString("categoryName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception exp) {
			JOptionPane.showMessageDialog(null,exp+"tabledataLoad from CategoryId","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cmbSearchDataLoadSubcategory(){
		try {
			cmbSearchSubCetagory.v.clear();
			cmbSearchSubCetagory.v.add("");
			String sql="select subcategoryId,subcategoryName from tbsubcategoryinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearchSubCetagory.v.add(rs.getString("subcategoryId")+"#"+rs.getString("subcategoryName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch Data Load from Subcategory");
		}
	}
	public void searchDataLoad(String catid){
		try {
			String sql="select categoryId,categoryName,username from tbcategoryinfo"
					+ " where categoryId like '"+catid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtcategoryId.setText(rs.getString("categoryId"));
				txtcategoryName.setText(rs.getString("categoryName"));
				txtuserName.setText(rs.getString("username"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,e+"searchDataLoad","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void searchDataLoadSubcategory(String subcatId){
		try {
			String sql="select categoryId,categoryName,subcategoryId,subcategoryName,userName from tbsubcategoryinfo "
					+ "where subcategoryId like '"+subcatId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategoryIdC.txtSuggest.setText(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				txtsubCategoryIdC.setText(rs.getString("subcategoryId"));
				txtsubCategoryNameC.setText(rs.getString("subcategoryName"));
				txtuserNameC.setText(rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"search Data Load from subcategory");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbcategoryinfo where categoryId like '"+txtcategoryId.getText().trim()
					.toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete data from categroy");
		}
		return false;
	}
	public boolean deleteDataSubcategory(){
		try {
			String sql="delete from tbsubcategoryinfo where subcategoryId like"
					+ " '"+txtsubCategoryIdC.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete data from subcategory");
		}
		return false;
	}
	public void cmp(){
		add(panelWest,BorderLayout.WEST);
		panelWest();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
	}
	public void panelWest(){
		panelWest.setPreferredSize(new Dimension(550,725));
		TitledBorder titelCategory = BorderFactory.createTitledBorder("Category");
		panelWest.setBorder(titelCategory);
		titelCategory.setTitleJustification(TitledBorder.CENTER);
		titelCategory.setTitleFont(new Font("clibari", Font.BOLD, 22));
		titelCategory.setTitleColor(Color.decode("#8B0000"));		
		panelWest.setLayout(new BorderLayout());		
		panelWest.add(panelWestNorth, BorderLayout.NORTH);
		panelWestNorth();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenter();
		panelWest.add(panelWestSouth, BorderLayout.SOUTH);
		panelWestSouth();
	}	
	private void panelWestNorth() {
		panelWestNorth.setPreferredSize(new Dimension(0, 80));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestNorth.setLayout(grid);
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(30, 5, 5, 5);
		panelWestNorth.add(cmbSearchCetagory.cmbSuggest,cn);
		cmbSearchCetagory.cmbSuggest.setPreferredSize(new Dimension(350, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(30, 5, 5, 5);
		panelWestNorth.add(lblWestNorthIcon,cn);
	}	
	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 330));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestSouth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestSouth.add(scrollTable,cn);
		scrollTable.setPreferredSize(new Dimension(510,320));
		table.getTableHeader().setReorderingAllowed(false);
	}	
	private void panelWestCenter() {
		panelWestCenter.setBackground(Color.GREEN);	
		panelWestCenter.setLayout(new BorderLayout());	
		panelWestCenter.add(panelWestCenterNorth, BorderLayout.NORTH);
		panelWestCenterNorth();
		panelWestCenter.add(panelWestCenterCenter, BorderLayout.CENTER);
		panelWestCenterCenter();
	}
	private void panelWestCenterCenter() {
		FlowLayout flow=new FlowLayout();
		panelWestCenterCenter.setLayout(flow);
		flow.setHgap(15);

		panelWestCenterCenter.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(100, 35));		
		btnEdit.setPreferredSize(new Dimension(100, 35));
		panelWestCenterCenter.add(btnEdit);		
		panelWestCenterCenter.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));		
		panelWestCenterCenter.add(btnDelete);
		btnDelete.setPreferredSize(new Dimension(100, 35));
	}
	private void panelWestCenterNorth() {
		panelWestCenterNorth.setPreferredSize(new Dimension(0, 200));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenterNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(lblCategoryId,cn);
		lblCategoryId.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(txtcategoryId,cn);
		txtcategoryId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(lblCategoryName,cn);
		lblCategoryName.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(txtcategoryName,cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(lbluserName,cn);
		lbluserName.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(txtuserName,cn);
		txtuserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterNorth.add(lblUserType,cn);
	}
	public void panelCenter(){
		TitledBorder titelCategory = BorderFactory.createTitledBorder("Sub Category");
		panelCenter.setBorder(titelCategory);
		titelCategory.setTitleJustification(TitledBorder.CENTER);
		titelCategory.setTitleFont(new Font("clibari", Font.BOLD, 22));
		titelCategory.setTitleColor(Color.decode("#8B0000"));	
		panelCenter.setLayout(new BorderLayout());	
		panelCenter.add(panelCenterNorth, BorderLayout.NORTH);
		panelCenterNorth();
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		panelCenterCenter();
		panelCenter.add(panelCenterSouth, BorderLayout.SOUTH);
		panelCenterSouth();	
	}
	private void panelCenterNorth() {
		panelCenterNorth.setPreferredSize(new Dimension(0, 80));		
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();	
		panelCenterNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(30, 5, 5, 5);
		panelCenterNorth.add(cmbSearchSubCetagory.cmbSuggest, cn);
		cmbSearchSubCetagory.cmbSuggest.setPreferredSize(new Dimension(380, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(30, 5, 5, 5);
		panelCenterNorth.add(lblCenterNorthIcon, cn);	
	}
	private void panelCenterCenter() {
		panelCenterCenter.setLayout(new BorderLayout());	
		panelCenterCenter.add(panelCenterCenterNorth,BorderLayout.NORTH);
		panelCenterCenterNorth();
		panelCenterCenter.add(panelCenterCenterCenter,BorderLayout.CENTER);
		panelCenterCenterCenter();
	}
	private void panelCenterCenterNorth() {
		panelCenterCenterNorth.setPreferredSize(new Dimension(0, 200));	
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lblCategoryIdC, cn);
		lblCategoryIdC.setFont(fontC);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(cmbCategoryIdC.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lblSubCategoryIdC, cn);
		lblSubCategoryIdC.setFont(fontC);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(txtsubCategoryIdC, cn);
		txtsubCategoryIdC.setEditable(false);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lblCategoryNameC, cn);
		lblCategoryNameC.setFont(fontC);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(txtsubCategoryNameC, cn);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lbluserNameC, cn);
		lbluserNameC.setFont(fontC);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(txtuserNameC, cn);
		txtuserNameC.setEditable(false);

		cn.gridx=2;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lblUserTypeC, cn);
	}	
	private void panelCenterCenterCenter() {
		FlowLayout flow=new FlowLayout();
		panelCenterCenterCenter.setLayout(flow);
		flow.setHgap(15);		
		btnNewCenter.setPreferredSize(new Dimension(100, 35));
		panelCenterCenterCenter.add(btnNewCenter);		
		btnEditCenter.setPreferredSize(new Dimension(100, 35));
		panelCenterCenterCenter.add(btnEditCenter);		
		panelCenterCenterCenter.add(btnRefreshCenter);
		btnRefreshCenter.setPreferredSize(new Dimension(100, 35));		
		panelCenterCenterCenter.add(btnDeleteCenter);
		btnDeleteCenter.setPreferredSize(new Dimension(100, 35));
	}	
	private void panelCenterSouth() {
		panelCenterSouth.setPreferredSize(new Dimension(0, 330));	
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterSouth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterSouth.add(scrollC, cn);
		scrollC.setPreferredSize(new Dimension(530, 320));
		tableC.getTableHeader().setReorderingAllowed(false);
	}
	public void init(){
		setPreferredSize(new Dimension(1158,714));
		BorderLayout border=new BorderLayout();
		border.setHgap(20);
		setLayout(border);
	}
}
