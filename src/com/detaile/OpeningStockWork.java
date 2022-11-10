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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.INITIALIZE;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class OpeningStockWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelWest=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();

	SuggestText cmbSearch=new SuggestText();
	JLabel lblNorthIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	JLabel lblStockId=new JLabel("Stock ID");
	JTextField txtStockId=new JTextField(20);
	JLabel lblProductId=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategoryId=new JLabel("Category ID");
	JComboBox cmbCategoryId=new JComboBox();
	JLabel lblSubCategoryId=new JLabel("Subcategory ID");
	JComboBox cmbSubcategoryId=new JComboBox();
	JLabel lblUnit=new JLabel("Unit");
	JTextField txtUnit=new JTextField();
	JLabel lblStockQuantity=new JLabel("Stock Quantity");
	JTextField txtStockQuantity=new JTextField();
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JTextField txtDealerPrice=new JTextField();
	JLabel lblAmount=new JLabel("Amount");
	JTextField txtAmount=new JTextField();
	JLabel lblStockDate=new JLabel("Stock Date");
	JDateChooser dateStockDate=new JDateChooser();
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JComboBox cmbSupplierName=new JComboBox();
	JLabel lblUserName=new JLabel("user Name");
	JTextField txtUserName=new JTextField();
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

	String col[]={"Stock ID","Product Name","Stock Quantity","Stock Date"};
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
	
	DecimalFormat dcf=new DecimalFormat("#0.00");
	
	public OpeningStockWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		txtUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void btnIni(boolean b){
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void editable(boolean b){
		cmbProductId.txtSuggest.setEnabled(b);
		cmbCategoryId.setEnabled(b);
		cmbSubcategoryId.setEnabled(b);
		txtUnit.setEditable(b);
		txtStockQuantity.setEditable(b);
		txtDealerPrice.setEditable(b);
		txtAmount.setEditable(b);
		cmbSupplierName.setEnabled(b);
	}
	public void txtClear(){
		cmbProductId.cmbSuggest.setSelectedIndex(0);
		cmbCategoryId.setSelectedIndex(-1);
		cmbSubcategoryId.setSelectedIndex(-1);
		txtUnit.setText("");
		txtStockQuantity.setText("");
		txtDealerPrice.setText("");
		txtAmount.setText("");
		dateStockDate.setDate(new Date());
		cmbSupplierName.setSelectedIndex(-1);
		txtUserName.setText(sessionBean.getUserName());
	}
	public void refreshWork(){
		autoId();
		tableDataLoad();
		cmbSearchDataLoad();
		btnIni(true);
		editable(true);
		txtClear();
	}
	public void btnAction(){
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cmbProductId.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.cmbSuggest.getSelectedItem().
							toString(),"#");
					String id=token.nextToken();
					dataLoadUnderProductId(id);
					cmbCategoryId.setEnabled(false);
					cmbSubcategoryId.setEnabled(false);
					cmbSupplierName.setEnabled(false);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isUpdate){
						if(checkConfirmation("Do you want to Update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "Data Updated Successfully");
									refreshWork();
								}
							}
						}
					}
					else{
						if(checkConfirmation("Do you want to Save?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "Data saved successfully");
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
				cmbCategoryId.setEnabled(false);
				cmbSubcategoryId.setEnabled(false);
				cmbSupplierName.setEnabled(false);
				isUpdate=true;
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
					if(checkConfirmation("Sure to Delete?")){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "Data Deleted successfully");
							refreshWork();
						}
					}
				}
			}
		});
		txtStockQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				/*String str1=txtStockQuantity.getText();
				double db1=Double.parseDouble(str1);
				String str2=txtDealerPrice.getText();
				double db2=Double.parseDouble(str2);
				double result=0;
				result=db1*db2;
				String res=Double.toString(result);
				txtAmount.setText(res);*/
				calcAmount();
			}
			public void keyPressed(KeyEvent arg0) {

			}
		});
		txtDealerPrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}
			public void keyReleased(KeyEvent e) {
				calcAmount();
			}
			public void keyPressed(KeyEvent e) {
				
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
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				btnIni(false);
				editable(false);
				isUpdate=false;
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
				isUpdate=false;
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		cmbSearch.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbSearch.cmbSuggest.getSelectedItem()!=null&&cmbSearch.cmbSuggest.getSelectedIndex()!=0){
					String id=cmbSearch.cmbSuggest.getSelectedItem().toString();
					searchDataLoad(id);
				}
			}
		});
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(stockid,locate('-',stockid)+1,"
					+ "length(stockid)-locate('-',stockid))as UNSIGNED)),0)+1 as id from tbopeningstock";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtStockId.setText("stockId-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Auto Id from Opening Stock");
		}
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
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbProduct Id data load from OpeningStock");
		}
	}
	public void calcAmount(){
		double qty,dealerPrice,amount;
		qty=Double.parseDouble(txtStockQuantity.getText().trim().isEmpty()?"0":
			txtStockQuantity.getText().trim());
		dealerPrice=Double.parseDouble(txtDealerPrice.getText().trim().isEmpty()?"0":
			txtDealerPrice.getText().trim());
		amount=qty*dealerPrice;
		txtAmount.setText(dcf.format(amount));
	}
	public void dataLoadUnderProductId(String productId){
		try {
			cmbCategoryId.removeAllItems();
			cmbSubcategoryId.removeAllItems();
			cmbSupplierName.removeAllItems();
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,"
					+ "supplierId,supplierName,unit,dealerPrice from tbproductinfo where productId like "
					+ "'"+productId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategoryId.addItem(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				if(!(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName")).trim().isEmpty()){
					cmbSubcategoryId.addItem(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				}
				else{
					cmbSubcategoryId.addItem("");
				}
				cmbSupplierName.addItem(rs.getString("supplierId")+"#"+rs.getString("supplierName"));
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbCategory Data load from openingStock");
		}
	}
	public boolean checkValidation(){
		if(!txtStockId.getText().trim().toString().isEmpty()){
			if(cmbProductId.cmbSuggest.getSelectedItem()!=null && cmbProductId.cmbSuggest.getSelectedIndex()!=0){
				if(cmbCategoryId.getSelectedItem()!=null){
					if(cmbSubcategoryId.getSelectedItem()!=null){
						if(!txtUnit.getText().trim().toString().isEmpty()){
							if(!txtStockQuantity.getText().trim().isEmpty()){
								if(!txtDealerPrice.getText().trim().isEmpty()){
									if(!txtAmount.getText().trim().isEmpty()){
										if(cmbSupplierName.getSelectedItem()!=null){
											if(!txtUserName.getText().trim().isEmpty()){
												return true;
											}
											else{
												JOptionPane.showMessageDialog(null, "insert User Name Please");
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "select supplier Name Please");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "insert Amount Please");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "insert Dealer price please");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "insert Stock Quantity please");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "insert unit please");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "select subCategoryId please");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "select CategoryId please");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "select productId please");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select stockId please");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation...",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean insertData(){
		try {
			StringTokenizer token1=new StringTokenizer(cmbProductId.cmbSuggest.getSelectedItem().toString(),"#");
			String pid=token1.nextToken();
			String pName=token1.nextToken();
			StringTokenizer token2=new StringTokenizer(cmbCategoryId.getSelectedItem().toString(),"#");
			String catid=token2.nextToken();
			String catName=token2.nextToken();
			StringTokenizer token3=new StringTokenizer(cmbSubcategoryId.getSelectedItem().toString(),"#");
			String subcatid=token3.nextToken();
			String subcatName=token3.nextToken();
			StringTokenizer token4=new StringTokenizer(cmbSupplierName.getSelectedItem().toString(),"#");
			String supplierId=token4.nextToken();
			String supplierName=token4.nextToken();
			String dStockDate=new SimpleDateFormat("yyyy-MM-dd").format(dateStockDate.getDate());
			String sql="insert into tbopeningstock(stockid,categoryid,categoryname,subcategoryid,"
					+ "subcategoryname,productid,productname,unit,stockquantity,dealerprice,amount,"
					+ "stockDate,supplierid,suppliername,userName,userip,entryTime)"
					+ "values('"+txtStockId.getText().trim().toString()+"',"
					+ "'"+catid+"',"
					+ "'"+catName+"',"
					+ "'"+subcatid+"',"
					+ "'"+subcatName+"',"
					+ "'"+pid+"',"
					+ "'"+pName+"',"
					+ "'"+txtUnit.getText().trim().toString()+"',"
					+ "'"+txtStockQuantity.getText().trim().toString()+"',"
					+ "'"+txtDealerPrice.getText().trim().toString()+"',"
					+ "'"+txtAmount.getText().trim().toString()+"',"
					+ "'"+dStockDate+"',"
					+ "'"+supplierId+"',"
					+ "'"+supplierName+"',"
					+ "'"+txtUserName.getText().trim().toString()+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert data from openingStock");
		}
		return false;
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1; i>=0; i--){
				model.removeRow(i);
			}
			String sql="select stockid,productname,stockquantity,stockDate from tbopeningstock";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("stockid"),rs.getString("productname"),
						rs.getString("stockquantity"),rs.getString("stockDate")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Table Data load from openingStock");
		}
	}
	public void cmbSearchDataLoad(){
		try {
			cmbSearch.v.clear();
			cmbSearch.v.add("");
			String sql="select stockid from tbopeningstock";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearch.v.add(rs.getString("stockid"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch data load from openingStock");
		}
	}
	public void searchDataLoad(String stockId){
		try {
			String sql="select stockid,categoryid,categoryname,subcategoryid,subcategoryname,productid,"
					+ "productname,unit,stockquantity,dealerprice,amount,stockDate,supplierid,suppliername,"
					+ "userName from tbopeningstock where stockid like '"+stockId+"';";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				txtStockId.setText(rs.getString("stockid"));
				cmbProductId.cmbSuggest.setSelectedItem(rs.getString("productid")+"#"+rs.getString("productname"));
				cmbCategoryId.setSelectedItem(rs.getString("categoryid")+"#"+rs.getString("categoryname"));
				cmbSubcategoryId.setSelectedItem(rs.getString("subcategoryid")+"#"+rs.getString("subcategoryname"));
				txtUnit.setText(rs.getString("unit"));
				txtStockQuantity.setText(rs.getString("stockquantity"));
				txtDealerPrice.setText(rs.getString("dealerprice"));
				txtAmount.setText(rs.getString("amount"));
				dateStockDate.setDate(rs.getDate("stockDate"));
				cmbSupplierName.setSelectedItem(rs.getString("supplierid")+"#"+rs.getString("suppliername"));
				txtUserName.setText(rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Search Data Load from OpeningStocks");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbopeningstock where stockid like '"+txtStockId.getText().trim()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Delete Data from OpeningStock");
		}
		return false;
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
	private void panelCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenter.add(scrollTable,cn);
		scrollTable.setPreferredSize(new Dimension(550, 655));
		table.getTableHeader().setReorderingAllowed(false);
	}
	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(570, 725));
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
		Font font=new Font("clibari",Font.BOLD,14);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblStockId,cn);
		lblStockId.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtStockId,cn);
		txtStockId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblProductId,cn);
		lblProductId.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbProductId.cmbSuggest,cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblCategoryId,cn);
		lblCategoryId.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbCategoryId,cn);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSubCategoryId,cn);
		lblSubCategoryId.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSubcategoryId,cn);

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUnit,cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtUnit,cn);

		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblStockQuantity,cn);
		lblStockQuantity.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtStockQuantity,cn);

		cn.gridx=0;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblDealerPrice,cn);
		lblDealerPrice.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtDealerPrice,cn);

		cn.gridx=0;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblAmount,cn);
		lblAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtAmount,cn);

		cn.gridx=0;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblStockDate,cn);
		lblStockDate.setFont(font);

		cn.gridx=1;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(dateStockDate,cn);
		dateStockDate.setDate(new Date());
		dateStockDate.setDateFormatString("dd-MM-yyyy");

		cn.gridx=0;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSupplierName,cn);
		lblSupplierName.setFont(font);

		cn.gridx=1;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSupplierName,cn);

		cn.gridx=0;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUserName,cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtUserName,cn);
		txtUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=10;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblExecutive,cn);
	}
	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 80));
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

		TitledBorder titleOpeningStock=BorderFactory.createTitledBorder("Opening Stock Info");
		setBorder(titleOpeningStock);
		titleOpeningStock.setTitleJustification(TitledBorder.CENTER);
		titleOpeningStock.setTitleColor(Color.decode("#8B0000"));
		titleOpeningStock.setTitleFont(new Font("clibari", Font.BOLD, 22));

		BorderLayout border=new BorderLayout();
		setLayout(border);

	}

}
