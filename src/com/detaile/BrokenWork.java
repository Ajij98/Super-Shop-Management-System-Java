package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class BrokenWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelWest=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();
	
	SuggestText cmbSearch=new SuggestText();
	JLabel lblNorthIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	JLabel lblWastageNo=new JLabel("Wastage No");
	JTextField textWastageNo=new JTextField(20);
	JLabel lblProductId=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategoryId=new JLabel("Category ID");
	JComboBox cmbCategoryId=new JComboBox();
	JLabel lblSubCategoryId=new JLabel("Subcategory ID");
	JComboBox cmbSubcategoryId=new JComboBox();
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JComboBox cmbSupplierName=new JComboBox();
	JLabel lblProductType=new JLabel("Product Type");
	JComboBox cmbProductType=new JComboBox();
	JLabel lblUnit=new JLabel("Unit");
	JTextField textUnit=new JTextField();
	JLabel lblDate=new JLabel("Date");
	JDateChooser dateDate=new JDateChooser();
	JLabel lblWastage=new JLabel("Wastage/Broken Quantity");
	JTextField textWastage=new JTextField();
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JTextField textDealerPrice=new JTextField();
	JLabel lblAmount=new JLabel("Amount");
	JTextField textAmount=new JTextField();
	JLabel lblReason=new JLabel("Reason");
	JTextArea textReason=new JTextArea(3,20);
	JScrollPane scrollReason=new JScrollPane(textReason,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JLabel lblUserName=new JLabel("user Name");
	JTextField textUserName=new JTextField();
	JLabel lblExecutive=new JLabel("Executive");
	
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
	
	String col[]={"Wastage No","Product Name","Wastage Quantity","Date"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col);
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	SessionBean sessionBean=new SessionBean();
	boolean isUpdate=false;
	DecimalFormat df=new DecimalFormat("#0.00");
	
	public BrokenWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		textUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void setOpaqueTrueFalse(boolean b){
		panelCenter.setOpaque(b);
		panelWest.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestSouth.setOpaque(b);
	}
	private void cmp() {
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelWest,BorderLayout.WEST);
		panelWest();
	}
	public void btnIni(boolean b){
		btnEdit.setEnabled(!b);
		btnAdd.setEnabled(b);
	}
	public void editable(boolean b){
		cmbProductId.cmbSuggest.setEnabled(b);
		cmbProductType.setEnabled(b);
		textUnit.setEditable(b);
		dateDate.setEnabled(b);
		textWastage.setEditable(b);
		textDealerPrice.setEditable(b);
		textAmount.setEditable(b);
		textReason.setEditable(b);
	}
	public void txtClear(){
		textWastageNo.setText("");
		cmbProductId.txtSuggest.setText("");
		cmbCategoryId.removeAllItems();
		cmbSubcategoryId.removeAllItems();
		cmbSupplierName.removeAllItems();
		cmbProductType.setSelectedIndex(0);
		textUnit.setText("");
		dateDate.setDate(new Date());
		textWastage.setText("");
		textDealerPrice.setText("");
		textAmount.setText("");
		textReason.setText("");
		textUserName.setText(sessionBean.getUserName());
		cmbSearch.txtSuggest.setText("");
	}
	public void refreshWork(){
		txtClear();
		autoId();
		tableDataLoad();
		cmbSearchDataLoad();
		btnIni(true);
		editable(true);
		cmbCategoryId.setEnabled(true);
		cmbSubcategoryId.setEnabled(true);
		cmbSupplierName.setEnabled(true);
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					if(isUpdate){
						if(checkConfirmation("do you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data updated successfull.");
									refreshWork();
								}
							}
						}
					}
					else{
						if(checkConfirmation("sure to save?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "Data Save successfully.");
								refreshWork();
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIni(true);
				editable(true);
				isUpdate=true;
				cmbCategoryId.setEnabled(false);
				cmbSubcategoryId.setEnabled(false);
				cmbSupplierName.setEnabled(false);
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
					if(checkConfirmation("sure to Delete?")){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "Data deleted successfully.");
							refreshWork();
						}
					}
				}
			}
		});
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbProductId.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
					String productid=token.nextToken();
					allDataLoadUnderProductid(productid);
					cmbCategoryId.setEnabled(false);
					cmbSubcategoryId.setEnabled(false);
					cmbSupplierName.setEnabled(false);
				}
				else{
					refreshWork();
					cmbCategoryId.setEnabled(true);
					cmbSubcategoryId.setEnabled(true);
					cmbSupplierName.setEnabled(true);
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {		
			}
			public void mousePressed(MouseEvent e) {				
			}
			public void mouseExited(MouseEvent e) {				
			}
			public void mouseEntered(MouseEvent e) {				
			}
			public void mouseClicked(MouseEvent e) {
				String wastageno=model.getValueAt(table.getSelectedRow(),0).toString();
				searchDataLoad(wastageno);
				btnIni(false);
				editable(false);
				cmbCategoryId.setEnabled(false);
				cmbSubcategoryId.setEnabled(false);
				cmbSupplierName.setEnabled(false);
			}
		});
		table.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {	
			}
			public void keyReleased(KeyEvent e) {
				String wastageno=model.getValueAt(table.getSelectedRow(),0).toString();
				searchDataLoad(wastageno);
				btnIni(false);
				editable(false);
				cmbCategoryId.setEnabled(false);
				cmbSubcategoryId.setEnabled(false);
				cmbSupplierName.setEnabled(false);
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		cmbSearch.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbSearch.txtSuggest.getText().trim().toString().isEmpty()){
					String wastageno=cmbSearch.txtSuggest.getText().trim();
					searchDataLoad(wastageno);
					btnIni(false);
					editable(false);
					cmbCategoryId.setEnabled(false);
					cmbSubcategoryId.setEnabled(false);
					cmbSupplierName.setEnabled(false);
				}
				else{
					refreshWork();
				}
			}
		});
		textWastage.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				amountCalculation();
			}
		});
		textDealerPrice.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				amountCalculation();
			}
		});
	}
	public void amountCalculation(){
		double wastageorbroken,dealerprice,amount;
		wastageorbroken=Double.parseDouble(textWastage.getText().trim().isEmpty()?"0":
			textWastage.getText().trim());
		dealerprice=Double.parseDouble(textDealerPrice.getText().trim().isEmpty()?"0":
			textDealerPrice.getText().trim());
		amount=wastageorbroken*dealerprice;
		textAmount.setText(df.format(amount));
	}
	public void cmbProductIdDataLoad(){
		try {
			cmbProductId.v.clear();
			cmbProductId.v.add("");
			String sql="select productId,productName from tbproductinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			
			while(rs.next()){
				cmbProductId.v.add(rs.getString("productId")+"#"+rs.getString("productName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbProductIdDataLoad()");
		}
	}
	public void allDataLoadUnderProductid(String productid){
		try {
			cmbCategoryId.removeAllItems();
			cmbSubcategoryId.removeAllItems();
			cmbSupplierName.removeAllItems();
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,supplierId,supplierName,"
					+ "unit,dealerPrice from tbproductinfo where productId='"+productid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategoryId.addItem(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
	
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().isEmpty()){
					cmbSubcategoryId.addItem(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				}
				else{
					cmbSubcategoryId.addItem("");
				}
	
				cmbSupplierName.addItem(rs.getString("supplierId")+"#"+rs.getString("supplierName"));
				textUnit.setText(rs.getString("unit"));
				textDealerPrice.setText(rs.getString("dealerPrice"));
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"allDataLoadUnderProductid()");
		}
	}
	public void autoId(){
		try {
			String sql=" select ifnull(max(cast(substring(wastageNo,locate('-',wastageNo)+1,"
					+ "length(wastageNo)-locate('-',wastageNo)) as UNSIGNED)),0)+1 as id from tbwastage";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				textWastageNo.setText("WastageNo-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoid()");
		}
	}
	public boolean checkValidation(){
		if(!textWastageNo.getText().trim().toString().isEmpty()){
			if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
				if(cmbProductType.getSelectedItem()!=null&&cmbProductType.getSelectedIndex()!=0){
					if(!textWastage.getText().trim().toString().isEmpty()){
						if(!textAmount.getText().trim().toString().isEmpty()){
							if(!textReason.getText().trim().toString().isEmpty()){
								if(!textUserName.getText().trim().toString().isEmpty()){
									return true;
								}
								else{
									JOptionPane.showMessageDialog(null, "insert user name please.");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "insert reason please.");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "insert amount please.");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "insert wastage/broken qty please.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "select product type please.");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "select productid please.");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "insert wastageNo please.");
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
	public boolean insertData(){
		String subcatid="";
		String subcatName="";
		try {
			StringTokenizer token1=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
			String productid=token1.nextToken();
			String productName=token1.nextToken();
			StringTokenizer token2=new StringTokenizer(cmbCategoryId.getSelectedItem().toString(),"#");
			String categoryid=token2.nextToken();
			String categoryName=token2.nextToken();
			StringTokenizer token3=new StringTokenizer(cmbSupplierName.getSelectedItem().toString(),"#");
			String supplierid=token3.nextToken();
			String supplierName=token3.nextToken();
			if(!cmbSubcategoryId.getSelectedItem().toString().trim().isEmpty()){
				StringTokenizer token=new StringTokenizer(cmbSubcategoryId.getSelectedItem().toString(),"#");
				subcatid=token.nextToken();
				subcatName=token.nextToken();
			}
			String date=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String sql="insert into tbwastage(wastageNo,productid,productName,catid,catName,subCatid,subCatName,"
					+ "supplierid,supplierName,productType,unit,dateDate,WastageorBroken,dealerPrice,amount,reason,"
					+ "userName,userip,entryTime)"
					+ "values('"+textWastageNo.getText().trim().toString()+"',"
					+ "'"+productid+"',"
					+ "'"+productName+"',"
					+ "'"+categoryid+"',"
					+ "'"+categoryName+"',"
					+ "'"+subcatid+"',"
					+ "'"+subcatName+"',"
					+ "'"+supplierid+"',"
					+ "'"+supplierName+"',"
					+ "'"+cmbProductType.getSelectedItem().toString()+"',"
					+ "'"+textUnit.getText().trim().toString()+"',"
					+ "'"+date+"',"
					+ "'"+textWastage.getText().trim().toString()+"',"
					+ "'"+textDealerPrice.getText().trim().toString()+"',"
					+ "'"+textAmount.getText().trim().toString()+"',"
					+ "'"+textReason.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"','',now())"; 
			dbConneciton.connection();
			dbConneciton.sta.execute(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insertData()");
		}
		return false;
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1; i>=0; i--){
				model.removeRow(i);
			}
			String sql="select wastageNo,productName,WastageorBroken,dateDate from tbwastage";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("wastageNo"),rs.getString("productName"),
						rs.getString("WastageorBroken"),rs.getDate("dateDate")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableDataLoad()");
		}
	}
	public void cmbSearchDataLoad(){
		try {
			cmbSearch.v.clear();
			cmbSearch.v.add("");
			String sql="select wastageNo from tbwastage";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearch.v.add(rs.getString("wastageNo"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearchDataLoad()");
		}
	}
	public void  searchDataLoad(String wastageNo){
		try {
			cmbCategoryId.removeAllItems();
			cmbSubcategoryId.removeAllItems();
			cmbSupplierName.removeAllItems();
			String sql="select wastageNo,productid,productName,catid,catName,subCatid,subCatName,"
					+ "supplierid,supplierName,productType,unit,dateDate,WastageorBroken,dealerPrice,"
					+ "amount,reason,userName from tbwastage where wastageNo='"+wastageNo+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				textWastageNo.setText(rs.getString("wastageNo"));
				cmbProductId.txtSuggest.setText(rs.getString("productid")+"#"+rs.getString("productName"));
				cmbCategoryId.addItem(rs.getString("catid")+"#"+rs.getString("catName"));
				if(!(rs.getString("subCatid")+rs.getString("subCatName")).trim().isEmpty()){
					cmbSubcategoryId.addItem(rs.getString("subCatid")+"#"+rs.getString("subCatName"));
				}
				else{
					cmbSubcategoryId.addItem("");
				}
				cmbSupplierName.addItem(rs.getString("supplierid")+"#"+rs.getString("supplierName"));
				cmbProductType.setSelectedItem(rs.getString("productType"));
				textUnit.setText(rs.getString("unit"));
				dateDate.setDate(rs.getDate("dateDate"));
				textWastage.setText(rs.getString("WastageorBroken"));
				textDealerPrice.setText(rs.getString("dealerPrice"));
				textAmount.setText(rs.getString("amount"));
				textReason.setText(rs.getString("reason"));
				textUserName.setText(rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchDataLoad()");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbwastage where wastageNo='"+textWastageNo.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"deleteData()");
		}
		return false;
	}
	private void panelCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenter.add(scrollTable,cn);
		scrollTable.setPreferredSize(new Dimension(555, 655));
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(570, 725));
		//panelWest.setBackground(Color.BLACK);
		panelWest.setLayout(new BorderLayout());
		
		panelWest.add(panelWestNorth, BorderLayout.NORTH);
		panelWestNorth();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenter();
		panelWest.add(panelWestSouth, BorderLayout.SOUTH);
		panelWestSouth();
	}

	private void panelWestNorth() {
		panelWestNorth.setPreferredSize(new Dimension(0, 100));
		//panelWestNorth.setBackground(Color.BLACK);
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestNorth.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(15, 5, 5, 5);
		panelWestNorth.add(cmbSearch.cmbSuggest, cn);
		cmbSearch.cmbSuggest.setPreferredSize(new Dimension(350, 35) );
		
		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(15, 5, 5, 5);
		panelWestNorth.add(lblNorthIcon, cn);
	}

	private void panelWestCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenter.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(2, 5, 2, 5);
		panelWestCenter.add(lblWastageNo,cn);
		lblWastageNo.setFont(font);
		
		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textWastageNo,cn);
		textWastageNo.setEditable(false);
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblProductId,cn);
		lblProductId.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbProductId.cmbSuggest,cn);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblCategoryId,cn);
		lblCategoryId.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbCategoryId,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSubCategoryId,cn);
		lblSubCategoryId.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSubcategoryId,cn);
		
		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSupplierName,cn);
		
		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblProductType,cn);
		lblProductType.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbProductType,cn);
		cmbProductType.addItem("");
		cmbProductType.addItem("Wastage");
		cmbProductType.addItem("Broken");
		
		cn.gridx=0;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUnit,cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textUnit,cn);
		
		cn.gridx=0;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblDate,cn);
		lblDate.setFont(font);

		cn.gridx=1;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(dateDate,cn);
		dateDate.setDate(new Date());
		dateDate.setDateFormatString("dd-MM-yyyy");
		
		cn.gridx=0;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblWastage,cn);
		lblWastage.setFont(font);

		cn.gridx=1;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textWastage,cn);
		
		cn.gridx=0;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblDealerPrice,cn);
		lblDealerPrice.setFont(font);

		cn.gridx=1;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textDealerPrice,cn);
		
		cn.gridx=0;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblAmount,cn);
		lblAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textAmount,cn);
		//textAmount.setEditable(false);
		
		cn.gridx=0;
		cn.gridy=11;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblReason,cn);
		lblReason.setFont(font);
		
		cn.gridx=1;
		cn.gridy=11;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(scrollReason,cn);
		
		cn.gridx=0;
		cn.gridy=12;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUserName,cn);
		lblUserName.setFont(font);
		
		cn.gridx=1;
		cn.gridy=12;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(textUserName,cn);
		textUserName.setEditable(false);
		
		cn.gridx=2;
		cn.gridy=12;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblExecutive,cn);

	}

	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 80));
		//panelWestSouth.setBackground(Color.blue);
		
		FlowLayout flow=new FlowLayout();
		panelWestSouth.setLayout(flow);
		flow.setVgap(20);
		flow.setHgap(10);
		
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
		
		TitledBorder titleBroken=BorderFactory.createTitledBorder("Wastage/Broken Info");
		setBorder(titleBroken);
		titleBroken.setTitleJustification(TitledBorder.CENTER);
		titleBroken.setTitleColor(Color.decode("#8B0000"));
		titleBroken.setTitleFont(new Font("clibari", Font.BOLD, 22));
		
		BorderLayout border=new BorderLayout();
		setLayout(border);
		
	}


}
