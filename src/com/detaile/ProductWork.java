package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.sql.rowset.serial.SerialStruct;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;

public class ProductWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelWest=new JPanel();

	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();

	JLabel lblProductID=new JLabel("Product ID");
	JLabel lblCategoryID=new JLabel("Category ID");
	JLabel lblSubCategoryID=new JLabel("Sub Category ID");
	JLabel lblproductName=new JLabel("Product Name");
	JLabel lblproductDescription=new JLabel("Product Description");
	JLabel lblUnit=new JLabel("Unit");
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JLabel lblTradeParice=new JLabel("Trade Price");
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JLabel lblUserName=new JLabel("User Name");
	JLabel lblExecutive=new JLabel("Exicutive");

	JTextField txtProductId=new JTextField(28);
	SuggestText cmbCategoryId=new SuggestText();
	SuggestText cmbSubCategoryId=new SuggestText();
	JTextField txtProductName=new JTextField();
	JTextField txtProductDescription=new JTextField();
	JTextField txtUnit=new JTextField(20);
	JTextField txtDealerPrice=new JTextField();
	JTextField txtTradePrice=new JTextField(20);
	SuggestText cmbSupplierName=new SuggestText();
	JTextField txtUserName=new JTextField(20);
	Font font=new Font("clibari", Font.BOLD, 12);

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

	SuggestText cmbSearch=new SuggestText();
	JLabel lblSearchIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	String col[]={"Product ID","Product Name","Dealer Price","Trade Price"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col)
	{
		public boolean isCellEditable(int row, int col)
		{
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	SessionBean sessionBean=new SessionBean();
	boolean isUpdate=false;
	
	public ProductWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		txtUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void setOpaqueTrueFlase(boolean b){
		panelWest.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestSouth.setOpaque(b);
		panelCenter.setOpaque(b);
	}
	public void btnIni(boolean b){
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void editable(boolean b){
		cmbCategoryId.txtSuggest.setEnabled(b);
		cmbSubCategoryId.txtSuggest.setEnabled(b);
		cmbSupplierName.txtSuggest.setEnabled(b);
		txtProductName.setEditable(b);
		txtProductDescription.setEditable(b);
		txtUnit.setEditable(b);
		txtDealerPrice.setEditable(b);
		txtTradePrice.setEditable(b);
		cmbSupplierName.txtSuggest.setEditable(b);
	}
	public void txtClear(){
		cmbSearch.txtSuggest.setText("");
		cmbCategoryId.txtSuggest.setText("");
		cmbSubCategoryId.txtSuggest.setText("");
		txtProductName.setText("");
		txtProductDescription.setText("");
		txtUnit.setText("");
		txtDealerPrice.setText("");
		txtTradePrice.setText("");
		cmbSupplierName.txtSuggest.setText("");
		txtUserName.setText(sessionBean.getUserName());
	}
	public void refreshWork(){
		autoId();
		tableDataLoad();
		cmbSearchDataLoad();
		txtClear();
		btnIni(true);
		editable(true);
		isUpdate=false;
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isUpdate){
						if(checkConfirmation("you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data updated successfully");
									refreshWork();
								}
							}
						}
					}
					else{
						if(checkConfirmation("you want to insert?")){
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
				isUpdate=true;
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					if(checkConfirmation("do you want to delete?")){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "Data deleted successfully");
							refreshWork();
						}
					}
				}
			}
		});
		cmbCategoryId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringTokenizer token=new StringTokenizer(cmbCategoryId.txtSuggest.getText().trim(), "#");
				String catid=token.nextToken();
				cmbSubCatDataLOad(catid);
				cmbSubCategoryId.txtSuggest.setText("");
			}
		});
		/*txtTradePrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}

			public void keyReleased(KeyEvent e) {
				ValideTreadPrice();
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});*/
		/*txtTradePrice.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				ValideTreadPrice();
			}
			public void focusGained(FocusEvent e) {
				
			}
		});*/
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				String productid=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(productid);
				btnIni(false);
				editable(false);
				isUpdate=false;
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				String productid=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(productid);
				btnIni(false);
				editable(false);
				isUpdate=false;
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		cmbSearch.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cmbSearch.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbSearch.txtSuggest.getText().trim(), "#");
					String productid=token.nextToken();
					searchDataLoad(productid);
					btnIni(false);
					editable(false);
					isUpdate=false;
				}
				else{
					refreshWork();
				}
			}
		});
		txtDealerPrice.addKeyListener(new KeyListener() {
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
		txtTradePrice.addKeyListener(new KeyListener() {
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
	public boolean ValideTreadPrice(){
		double trade,dealer;
		dealer=Double.parseDouble(txtDealerPrice.getText().toString().trim().isEmpty()?"0":txtDealerPrice.getText().toString().trim());
		trade=Double.parseDouble(txtTradePrice.getText().toString().trim().isEmpty()?"0":txtTradePrice.getText().toString().trim());
		if(trade<dealer){
			txtTradePrice.setText("");
			JOptionPane.showMessageDialog(null,"Your Trade Price not Valide");
			return false;
		}
		return true;
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(SUBSTRING(productId,LOCATE('-',productId)+1,"
					+ "LENGTH(productId)-LOCATE('-',productId))as UNSIGNED)),0)+1 as id from tbproductinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtProductId.setText("productid-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"auto id from Product");
		}
	}
	public boolean checkValidation(){
		if(!cmbCategoryId.txtSuggest.getText().trim().isEmpty()){
			if(!txtProductName.getText().trim().isEmpty()){
				if(!txtProductDescription.getText().trim().isEmpty()){
					if(!txtUnit.getText().trim().isEmpty()){
						if(!txtDealerPrice.getText().trim().isEmpty()){
							if(!txtTradePrice.getText().trim().isEmpty()){
								if(!cmbSupplierName.txtSuggest.getText().trim().isEmpty()){
									if(ValideTreadPrice()){
										return true;
									}
									
								}
								else{
									JOptionPane.showMessageDialog(null, "please select Supplier Name");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "insert trade Price");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "insert dealer price");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "insert unit");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert product Description");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert productName");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select categoryId");
		}
		return false;
	}
	public  boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public void cmbCatagoryDataLoad(){
		try {
			cmbCategoryId.v.clear();
			cmbCategoryId.v.add("");
			String sql="select categoryId,categoryName from tbcategoryinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategoryId.v.add(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+" cmbCatagoryload from product");
		}
	}
	public void cmbSubCatDataLOad(String catid){
		try {
			cmbSubCategoryId.v.clear();
			cmbSubCategoryId.v.add("");
			String sql="select subcategoryId,subcategoryName from tbsubcategoryinfo "
					+ "where categoryId like '"+catid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSubCategoryId.v.add(rs.getString("subcategoryId")+"#"+rs.getString("subcategoryName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSubcategory data Load from product");
		}
	}
	public void cmbSupplierNameDataLoad(){
		try {
			cmbSupplierName.v.clear();
			cmbSupplierName.v.add("");
			String sql="select SupplierID,SupplierName from tbsupplierinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSupplierName.v.add(rs.getString("SupplierID")+"#"+rs.getString("SupplierName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSupplier Data load from product");
		}
	}
	public boolean insertData(){
		String subCat="";
		String subCatName="";
		try {
			StringTokenizer token1=new StringTokenizer(cmbCategoryId.txtSuggest.getText().trim(), "#");
			String catId=token1.nextToken();
			String catName=token1.nextToken();
			if(!cmbSubCategoryId.txtSuggest.getText().trim().isEmpty()){
				StringTokenizer token2=new StringTokenizer(cmbSubCategoryId.txtSuggest.getText().trim(), "#");
				subCat=token2.nextToken();
				subCatName=token2.nextToken();
			}
			StringTokenizer token3=new StringTokenizer(cmbSupplierName.txtSuggest.getText().trim(), "#");
			String supplierId=token3.nextToken();
			String supplierName=token3.nextToken();
			
			String sql="insert into tbproductinfo(productId,categoryId,categoryName,subCategoryId,"
					+ "subCategoryName,productName,productDescription,unit,dealerPrice,tradePrice,"
					+ "supplierId,supplierName,userName,userip,entryTime)"
					+ "values('"+txtProductId.getText().trim().toString()+"',"
					+ "'"+catId+"',"
					+ "'"+catName+"',"
					+ "'"+subCat+"',"
					+ "'"+subCatName+"',"
					+ "'"+txtProductName.getText().trim().toString()+"',"
					+ "'"+txtProductDescription.getText().trim().toString()+"',"
					+ "'"+txtUnit.getText().trim().toString()+"',"
					+ "'"+txtDealerPrice.getText().trim().toString()+"',"
					+ "'"+txtTradePrice.getText().trim().toString()+"',"
					+ "'"+supplierId+"',"
					+ "'"+supplierName+"',"
					+ "'"+txtUserName.getText().trim().toString()+"',"
					+ "'',now())";
					
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert data from product()");
		}
		return false;
	}
	public void cmbSearchDataLoad(){
		try {
			cmbSearch.v.clear();
			cmbSearch.v.add("");
			String sql="select productId,productName from tbproductinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearch.v.add(rs.getString("productId")+"#"+rs.getString("productName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch Data load from product");
		}
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1; i>=0; i--){
				model.removeRow(i);
			}
			String sql="select productId,productName,dealerPrice,tradePrice from tbproductinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("productId"),rs.getString("productName"),
						rs.getString("dealerPrice"),rs.getString("tradePrice")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableDataLOad from product");
		}
	}
	public void searchDataLoad(String productid){
		try {
			String sql="select productId,categoryId,categoryName,subCategoryId,subCategoryName,productName,"
					+ "productDescription,unit,dealerPrice,tradePrice,supplierId,supplierName,userName "
					+ "from tbproductinfo where productId like '"+productid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().isEmpty()){
					cmbSubCategoryId.txtSuggest.setText(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				}
				else{
					cmbSubCategoryId.txtSuggest.setText("");
				}
				txtProductId.setText(rs.getString("productId"));
				cmbCategoryId.txtSuggest.setText(rs.getString("categoryId")+"#"+rs.getString("categoryName"));		
				txtProductName.setText(rs.getString("productName"));
				txtProductDescription.setText(rs.getString("productDescription"));
				txtUnit.setText(rs.getString("unit"));
				txtDealerPrice.setText(rs.getString("dealerPrice"));
				txtTradePrice.setText(rs.getString("tradePrice"));
				cmbSupplierName.txtSuggest.setText(rs.getString("supplierId")+"#"+rs.getString("supplierName"));
				txtUserName.setText(rs.getString("userName"));
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchDataLoad from product");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbproductinfo where productId like '"+txtProductId.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete data from product");
		}
		return false;
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));	
		TitledBorder titleProductInfo=BorderFactory.createTitledBorder("Product Info");
		setBorder(titleProductInfo);
		titleProductInfo.setTitleJustification(TitledBorder.CENTER);
		titleProductInfo.setTitleColor(Color.decode("#8B0000"));
		titleProductInfo.setTitleFont(new Font("clibari", Font.BOLD, 22));	
		BorderLayout border=new BorderLayout();
		setLayout(border);
	}
	private void cmp() {
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelWest,BorderLayout.WEST);
		panelWest();
	}
	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(580, 725));
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
		cmbSearch.cmbSuggest.setPreferredSize(new Dimension(350, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(15, 5, 5, 5);
		panelWestNorth.add(lblSearchIcon, cn);
	}
	private void panelWestCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblProductID,cn);
		lblProductID.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtProductId,cn);
		txtProductId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblCategoryID,cn);
		lblCategoryID.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbCategoryId.cmbSuggest,cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSubCategoryID,cn);
		lblSubCategoryID.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSubCategoryId.cmbSuggest,cn);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblproductName,cn);
		lblproductName.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtProductName,cn);

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblproductDescription,cn);
		lblproductDescription.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtProductDescription,cn);

		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUnit, cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtUnit, cn);

		cn.gridx=0;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblDealerPrice, cn);
		lblDealerPrice.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtDealerPrice, cn);

		cn.gridx=0;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblTradeParice, cn);
		lblTradeParice.setFont(font);

		cn.gridx=1;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtTradePrice, cn);

		cn.gridx=0;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblSupplierName, cn);
		lblSupplierName.setFont(font);

		cn.gridx=1;
		cn.gridy=8;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(cmbSupplierName.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblUserName, cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(txtUserName, cn);
		txtUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=9;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenter.add(lblExecutive, cn);
	}
	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 90));
		FlowLayout flow=new FlowLayout();
		panelWestSouth.setLayout(flow);
		flow.setHgap(15);
		flow.setVgap(30);

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
	private void panelCenter() {
		FlowLayout flow=new FlowLayout();
		panelCenter.setLayout(flow);
		flow.setVgap(0);
		panelCenter.add(scrollTable);
		scrollTable.setPreferredSize(new Dimension(545, 660));
		table.getTableHeader().setReorderingAllowed(false);
		table.setColumnSelectionAllowed(false);
	}
}
