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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.SimpleAttributeSet;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.sun.accessibility.internal.resources.accessibility;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateDV;
import com.toedter.calendar.JDateChooser;

public class ReceiptReturnWork extends JPanel{

	JPanel panelNorth=new JPanel();
	JPanel panelNorthWest=new JPanel();
	JPanel panelNorthCenter=new JPanel();
	JPanel panelCenter=new JPanel();
	JPanel panelCenterWest=new JPanel();
	JPanel panelCenterCenter=new JPanel();
	JPanel panelCenterWestNorth=new JPanel();
	JPanel panelCenterWestCenter=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelSouthNorth=new JPanel();
	JPanel panelSouthCenter=new JPanel();

	JPanel panelCenterWestNorthWest=new JPanel();
	JPanel panelCenterWestNorthCenter=new JPanel();

	JPanel panelSouthCenterWest=new JPanel();
	JPanel panelSouthCenterCenter=new JPanel();

	JLabel lblReturnNo=new JLabel("Return No");
	JTextField textReturnNo=new JTextField(15);
	JLabel lblUserName=new JLabel("User Name");
	JTextField textUserName=new JTextField(15);
	JLabel lblExecutive=new JLabel("Executive");

	JLabel lblDate=new JLabel("Date");
	JDateChooser dateDate=new JDateChooser();
	JLabel lblReturnDate=new JLabel("Return Date");
	JDateChooser dateReturnDate=new JDateChooser();

	JLabel lblInvoiceNo=new JLabel("invoice No");
	SuggestText cmbInvoiceNo=new SuggestText();
	JLabel lblUnit=new JLabel("Unit");
	JTextField textUnit=new JTextField(15);
	JLabel lblProductId=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategory=new JLabel("Category");
	SuggestText cmbCategory=new SuggestText();
	JLabel lblSubcategory=new JLabel("Subcategory");
	SuggestText cmbSubcategory=new SuggestText();
	JLabel lblSupplierName=new JLabel("Supplier Name");
	SuggestText cmbSupplierName=new SuggestText();

	JLabel lblStockQuantity=new JLabel("Stock Qty");
	JTextField textStockQuantity=new JTextField(16);
	JLabel lblReturnQty=new JLabel("Return Qty");
	JTextField textReturnQty=new JTextField();
	JLabel lblAmount=new JLabel("Amount");
	JTextField textAmount=new JTextField(15);
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JTextField textDealerPrice=new JTextField();
	JLabel lblRemarks=new JLabel("Remarks");
	JTextArea textRemarks=new JTextArea(2,1);
	JScrollPane scrollRemarks=new JScrollPane(textRemarks,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	ImageIcon iconSubmit=new ImageIcon("Images/btnSubmit.png");
	JButton btnSubmit=new JButton("Submit",iconSubmit);
	ImageIcon iconEdit=new ImageIcon("Images/btnEdit.png");
	JButton btnEdit=new JButton("Edit",iconEdit);
	ImageIcon iconRefresh=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	ImageIcon iconDelete=new ImageIcon("Images/cancel-icon.png");
	JButton btnDelete=new JButton("Delete",iconDelete);
	ImageIcon iconReport=new ImageIcon("Images/Report.png");
	JButton btnReport=new JButton("Report",iconReport);

	String col[]={"InvoiceNo","ProductId","ProductName","SupplierId","SupplierName","Unit",
			"DealerPrice","StockQty","ReturnQty","Amount","Remarks"};
	Object row[][]={};
	DefaultTableModel modelReceiptReturnDetails=new DefaultTableModel(row, col);
	JTable tableReceiptReturnDetails=new JTable(modelReceiptReturnDetails);
	JScrollPane scrollTableReceiptReturnDetails=new JScrollPane(tableReceiptReturnDetails,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField textTotalAmount=new JTextField(35);

	ImageIcon iconConfirm=new ImageIcon("Images/confirm.png");
	JButton btnConfirm=new JButton("Confirm",iconConfirm);
	ImageIcon iconRefreshItemReport=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefreshItemReport=new JButton("Refresh",iconRefresh);

	JPanel panelCenterCenterNorth=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JDateChooser dateFromDatePC=new JDateChooser();
	JDateChooser dateToDatePC=new JDateChooser();
	JButton btnIcon=new JButton(new ImageIcon("Images/btnSubmit.png"));

	String colPC[]={"Return No","TotalAmount","Date"};
	Object rowPC[][]={};
	DefaultTableModel modelReturnInfo=new DefaultTableModel(rowPC, colPC);
	JTable tableReturnInfo=new JTable(modelReturnInfo);
	JScrollPane scrollPC=new JScrollPane(tableReturnInfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SessionBean sessionBean=new SessionBean();
	DecimalFormat df=new DecimalFormat("#0.00");
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

	boolean isUpdate=false;

	public ReceiptReturnWork(SessionBean sessionBean){
		this.sessionBean=sessionBean;
		textUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void editable(boolean b){
		cmbInvoiceNo.cmbSuggest.setEnabled(b);
		cmbProductId.cmbSuggest.setEnabled(b);
		cmbCategory.cmbSuggest.setEnabled(b);
		cmbSubcategory.cmbSuggest.setEnabled(b);
		cmbSupplierName.cmbSuggest.setEnabled(b);
		textUnit.setEditable(b);
		textStockQuantity.setEditable(b);
		textReturnQty.setEditable(b);
		textDealerPrice.setEditable(b);
		textAmount.setEditable(b);
		textRemarks.setEditable(b);
	}
	public void btnIni(boolean b){
		btnSubmit.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void txtClearReceiptReturnDetails(){
		cmbInvoiceNo.txtSuggest.setText("");
		cmbProductId.txtSuggest.setText("");
		cmbCategory.txtSuggest.setText("");
		cmbSubcategory.txtSuggest.setText("");
		cmbSupplierName.txtSuggest.setText("");
		textUnit.setText("");
		textStockQuantity.setText("");
		textReturnQty.setText("");
		textDealerPrice.setText("");
		textAmount.setText("");
		textRemarks.setText("");
	}
	public void refreshWorkReceiptReturnDetails(){
		txtClearReceiptReturnDetails();
		cmbInvoiceNoDataLoad();
		btnIni(true);
		editable(true);
	}
	public void txtClearReceiptReturnInfo(){
		textReturnNo.setText("");
		textUserName.setText("");
		textUserName.setText(sessionBean.getUserName());
		dateDate.setDate(new Date());
		dateReturnDate.setDate(new Date());
		textTotalAmount.setText("");
		for(int i=modelReceiptReturnDetails.getRowCount()-1; i>=0; i--){
			modelReceiptReturnDetails.removeRow(i);
		}
	}
	public void refreshWorkReceiptReturnInfo(){
		refreshWorkReceiptReturnDetails();
		txtClearReceiptReturnInfo();
		autoId();
		tableReceiptreturninfodataLoad();
		isUpdate=false;
		btnIni(true);
		editable(true);
		btnConfirm.setEnabled(false);
	}
	public void btnAction(){
		cmbInvoiceNo.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbInvoiceNo.txtSuggest.getText().trim().toString().isEmpty()){
					String invoiceNo=cmbInvoiceNo.txtSuggest.getText().trim().toString();
					cmbProductId.v.clear();
					cmbProductId.txtSuggest.setText("");
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					cmbSupplierName.txtSuggest.setText("");
					textUnit.setText("");
					textStockQuantity.setText("");
					textReturnQty.setText("");
					textAmount.setText("");
					textDealerPrice.setText("");
					cmbProductIdDataLoad(invoiceNo);
				}
				else{
					cmbProductId.v.clear();
					cmbProductId.txtSuggest.setText("");
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					cmbSupplierName.txtSuggest.setText("");
					textUnit.setText("");
					textStockQuantity.setText("");
					textReturnQty.setText("");
					textAmount.setText("");
					textDealerPrice.setText("");
				}
			}
		});
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
					String productid=token.nextToken();
					textStockQuantity.setText("");
					textReturnQty.setText("");
					textAmount.setText("");
					allDataLoadUnderProductid(productid);
				}
				else{
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					cmbSupplierName.txtSuggest.setText("");
					textUnit.setText("");
					textStockQuantity.setText("");
					textReturnQty.setText("");
					textAmount.setText("");
					textDealerPrice.setText("");
				}
			}
		});
		textStockQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!(Character.isDigit(ch) || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {}		
			public void keyPressed(KeyEvent e) {}
		});
		textDealerPrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!(Character.isDigit(ch) || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {}		
			public void keyPressed(KeyEvent e) {}
		});
		textReturnQty.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!(Character.isDigit(ch) || ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {}		
			public void keyPressed(KeyEvent e) {}
		});
		textReturnQty.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				amountClculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationItemDetails()){
					if(doubleEntryCheck()){
						tableReceiptReturnDetailsDataLoad();
						totalAmountCalculation();
						refreshWorkReceiptReturnDetails();
						btnConfirm.setEnabled(true);
					}
				}
			}
		});
		tableReceiptReturnDetails.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				searchDataItemDetails(tableReceiptReturnDetails.getSelectedRow());
				if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
					String productid=token.nextToken();
					allDataLoadUnderProductid(productid);
					totalAmountCalculation();
					btnIni(false);
					editable(false);
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationReceiptinfo()){
					if(isUpdate){
						if(checkConfirmation("do you want to upDate?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data updated successfully.");
									refreshWorkReceiptReturnInfo();
								}
							}
						}
					}
					else{
						if(checkConfirmation("sure to save?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "Data saved successfully.");
								refreshWorkReceiptReturnInfo();
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isUpdate=true;
				btnIni(true);
				editable(true);
				cmbCategory.cmbSuggest.setEnabled(false);
				cmbSubcategory.cmbSuggest.setEnabled(false);
				cmbSupplierName.cmbSuggest.setEnabled(false);
			}
		});
		tableReturnInfo.addMouseListener(new MouseListener() {	
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				String returnNo=modelReturnInfo.getValueAt(tableReturnInfo.getSelectedRow(), 0).toString();
				searchDataLoad(returnNo);
				textTotalAmount.setFont(new Font("Carlibri", Font.BOLD, 18));
				textTotalAmount.setForeground(Color.RED);
			}
		});
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDataLoadWithDate();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationReceiptinfo()){
					if(checkValidationItemDetails()){
						if(checkConfirmation("sure to delete?")){
							if(deleteData()){
								JOptionPane.showMessageDialog(null, "Data deleted successfully.");
							}
						}
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkReceiptReturnDetails();
			}
		});
		btnRefreshItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkReceiptReturnInfo();
			}
		});
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(returnNo,locate('-',returnNo)+1,"
					+ "length(returnNo)-locate('-',returnNo))as UNSIGNED)),0)+1 as id from tbreceiptreturninfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				textReturnNo.setText("ReturnNo-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId()");
		}
	}
	public void cmbInvoiceNoDataLoad(){
		try {
			cmbInvoiceNo.v.clear();
			cmbInvoiceNo.v.add("");
			String sql="select invoiceNo from tbitemreceiptinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);

			while(rs.next()){
				cmbInvoiceNo.v.add(rs.getString("invoiceNo"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbInvoiceNoDataLoad()");
		}
	}
	public void cmbProductIdDataLoad(String invoiceNo){
		try {
			cmbProductId.v.clear();
			cmbProductId.v.add("");
			String sql="select productId,productName from tbitemreceiptdetails where invoiceNo "
					+ "like '"+invoiceNo+"'";
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
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,supplierId,"
					+ "supplierName,unit,dealerPrice from tbproductinfo where productId='"+productid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategory.txtSuggest.setText(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().toString().isEmpty()){
					cmbSubcategory.txtSuggest.setText(rs.getString("subCategoryId")+"#"
							+rs.getString("subCategoryName"));
				}
				else{
					cmbSubcategory.txtSuggest.setText("");
				}	
				cmbSupplierName.txtSuggest.setText(rs.getString("supplierId")+"#"+rs.getString("supplierName"));
				textUnit.setText(rs.getString("unit"));
				textDealerPrice.setText(rs.getString("dealerPrice"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"allDataLoadUnderProductid()");
		}
	}
	public void amountClculation(){
		double returnQty,dealerPrice,amount;
		returnQty=Double.parseDouble(textReturnQty.getText().trim().toString().isEmpty()?"0":
			textReturnQty.getText().trim().toString());
		dealerPrice=Double.parseDouble(textDealerPrice.getText().trim().toString().isEmpty()?"0":
			textDealerPrice.getText().trim().toString());
		amount=returnQty*dealerPrice;
		textAmount.setText(df.format(amount));
	}
	public boolean checkValidationItemDetails(){
		if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
			if(!textStockQuantity.getText().trim().toString().isEmpty()){
				if(!textReturnQty.getText().trim().toString().isEmpty()){
					if(!textAmount.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "Insert Amount please.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Insert ReturnQty please.");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Insert StockQty please.");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Select Productid please.");
		}
		return false;
	}
	public boolean doubleEntryCheck(){
		StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
		String productid=token.nextToken();
		String invoiceNo=cmbInvoiceNo.txtSuggest.getText().toString().trim();
		for(int i=0; i<modelReceiptReturnDetails.getRowCount(); i++){
			if(modelReceiptReturnDetails.getValueAt(i, 0).equals(invoiceNo) && 
					modelReceiptReturnDetails.getValueAt(i, 1).equals(productid)){
				JOptionPane.showMessageDialog(null, "This product already exist!");
				return false;
			}
		}
		return true;
	}
	public void tableReceiptReturnDetailsDataLoad(){	
		StringTokenizer token1=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
		String productid=token1.nextToken();
		String productName=token1.nextToken();
		StringTokenizer token2=new StringTokenizer(cmbSupplierName.txtSuggest.getText().trim().toString(),"#");
		String supplierid=token2.nextToken();
		String supplierName=token2.nextToken();

		modelReceiptReturnDetails.addRow(new Object[]{cmbInvoiceNo.txtSuggest.getText().toString().trim(),
				productid,productName,supplierid,supplierName,
				textUnit.getText().trim().toString(),
				textDealerPrice.getText().trim().toString(),
				textStockQuantity.getText().trim().toString(),
				textReturnQty.getText().trim().toString(),
				textAmount.getText().trim().toString(),
				textRemarks.getText().trim().toString()});
	}
	public void totalAmountCalculation(){
		double sum=0.0,amount;
		for(int i=0; i<modelReceiptReturnDetails.getRowCount(); i++){
			amount=Double.parseDouble(modelReceiptReturnDetails.getValueAt(i, 9).toString());
			sum=sum+amount;
		}
		textTotalAmount.setText(df.format(sum));
		textTotalAmount.setFont(new Font("Carlibri", Font.BOLD, 18));
		textTotalAmount.setForeground(Color.RED);
	}
	public void searchDataItemDetails(int row){
		cmbInvoiceNo.txtSuggest.setText(modelReceiptReturnDetails.getValueAt(row, 0).toString());
		cmbProductId.txtSuggest.setText(modelReceiptReturnDetails.getValueAt(row, 1).toString()+"#"+
				modelReceiptReturnDetails.getValueAt(row, 2).toString());
		textStockQuantity.setText(modelReceiptReturnDetails.getValueAt(row, 6).toString());
		textReturnQty.setText(modelReceiptReturnDetails.getValueAt(row, 7).toString());
		textAmount.setText(modelReceiptReturnDetails.getValueAt(row, 8).toString());
		textRemarks.setText(modelReceiptReturnDetails.getValueAt(row, 9).toString());
		modelReceiptReturnDetails.removeRow(row);
	}
	public boolean checkValidationReceiptinfo(){
		if(!textReturnNo.getText().trim().toString().isEmpty()){
			if(!textUserName.getText().trim().toString().isEmpty()){
				if(modelReceiptReturnDetails.getRowCount()!=0){
					if(!textTotalAmount.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "Insert Total Amount please.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Table is Empty!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Insert User Name please.");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "insert ReturnNo please.");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation,",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean insertData(){
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);

			String datecurDate=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String returnDate=new SimpleDateFormat("yyyy-MM-dd").format(dateReturnDate.getDate());
			String sqlinfo="insert into tbreceiptreturninfo(returnNo,curDate,returnDate,totalAmount,"
					+ "userName,userip,entryTime)"
					+ "values('"+textReturnNo.getText().trim().toString()+"',"
					+ "'"+datecurDate+"',"
					+ "'"+returnDate+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlinfo);

			for(int i=0; i<modelReceiptReturnDetails.getRowCount(); i++){
				String sqlDetails="insert into tbreceiptreturndetails(returnNo,invoiceNo,productid,productName,"
						+ "supplierid,supplierName,unit,dealerPrice,stockQty,returnQty,amount,remarks,"
						+ "userName,userip,entryTime)"
						+ "values('"+textReturnNo.getText().trim().toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 0).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 1).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 2).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 3).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 4).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 5).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 6).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 7).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 8).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 9).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 10).toString()+"',"
						+ "'"+textUserName.getText().trim().toString()+"',"
						+ "'',now())";
				dbConneciton.sta.executeUpdate(sqlDetails);
			}
			dbConneciton.con.commit();
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			try {
				dbConneciton.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, e+"");
		}
		return false;
	}
	public boolean deleteData(){
		try {
			String sqlInfo="delete from tbreceiptreturninfo where returnNo like "
					+ "'"+textReturnNo.getText().toString().trim()+"'";
			String sqlDetails="delete from tbreceiptreturndetails where returnNo like "
					+ "'"+textReturnNo.getText().toString().trim()+"'";
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);
			dbConneciton.sta.executeUpdate(sqlInfo);
			dbConneciton.sta.executeUpdate(sqlDetails);
			dbConneciton.con.commit();
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			try {
				dbConneciton.con.rollback();
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, e+"deleteData()");
		}
		return false;
	}
	/*public boolean updateWork(){
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);

			String infoDelete="delete from tbreceiptreturninfo where returnNo like "
					+ "'"+textReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(infoDelete);
			String detailsDetete="delete from tbreceiptreturndetails where returnNo like "
					+ "'"+textReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(detailsDetete);

			String datecurDate=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String returnDate=new SimpleDateFormat("yyyy-MM-dd").format(dateReturnDate.getDate());
			String sqlinfo="insert into tbreceiptreturninfo(returnNo,curDate,returnDate,totalAmount,"
					+ "userName,userip,entryTime)"
					+ "values('"+textReturnNo.getText().trim().toString()+"',"
					+ "'"+datecurDate+"',"
					+ "'"+returnDate+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlinfo);

			for(int i=0; i<modelReceiptReturnDetails.getRowCount(); i++){
				String sqlDetails="insert into tbreceiptreturndetails(returnNo,invoiceNo,productid,productName,"
						+ "supplierid,supplierName,unit,dealerPrice,stockQty,returnQty,amount,remarks,"
						+ "userName,userip,entryTime)"
						+ "values('"+textReturnNo.getText().trim().toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 0).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 1).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 2).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 3).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 4).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 5).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 6).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 7).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 8).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 9).toString()+"',"
						+ "'"+modelReceiptReturnDetails.getValueAt(i, 10).toString()+"',"
						+ "'"+textUserName.getText().trim().toString()+"',"
						+ "'',now())";
				dbConneciton.sta.executeUpdate(sqlDetails);
			}
			dbConneciton.con.commit();
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			try {
				dbConneciton.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, e+"");
		}
		return false;
	}*/
	public void tableReceiptreturninfodataLoad(){
		try {
			for(int i=modelReturnInfo.getRowCount()-1; i>=0; i--){
				modelReturnInfo.removeRow(i);
			}
			String sql="select returnNo,returnDate,totalAmount from tbreceiptreturninfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelReturnInfo.addRow(new Object[]{rs.getString("returnNo"),
						df.format(Double.parseDouble(rs.getString("totalAmount"))),
						dateFormat.format(rs.getDate("returnDate"))});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableReceiptreturninfodataLoad()");
		}
	}
	public void searchDataLoad(String returnNo){
		try {
			for(int i=modelReceiptReturnDetails.getRowCount()-1; i>=0; i--){
				modelReceiptReturnDetails.removeRow(i);
			}	
			String sql="select a.returnNo,a.curDate,a.returnDate,a.totalAmount,a.userName,b.invoiceNo,"
					+ "b.productid,b.productName,b.supplierid,b.supplierName,b.unit,b.dealerPrice,b.stockQty,"
					+ "b.returnQty,b.amount,b.remarks from tbreceiptreturninfo a inner join "
					+ "tbreceiptreturndetails b on a.returnNo=b.returnNo where a.returnNo='"+returnNo+"'";
			dbConneciton.connection(); 
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			int a=0;
			while(rs.next()){
				if(a==0){
					textReturnNo.setText(rs.getString("returnNo"));
					textUserName.setText(rs.getString("userName"));
					dateDate.setDate(rs.getDate("curDate"));
					dateReturnDate.setDate(rs.getDate("returnDate"));
					textTotalAmount.setText(rs.getString("totalAmount"));
				}
				a++;
				modelReceiptReturnDetails.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productid"),
						rs.getString("productName"),rs.getString("supplierid"),rs.getString("supplierName"),
						rs.getString("unit"),rs.getString("dealerPrice"),rs.getString("stockQty"),
						rs.getString("returnQty"),rs.getString("amount"),rs.getString("remarks")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchDataLoad()");
		}
	}
	public void findDataLoadWithDate(){
		try {
			for(int i=modelReturnInfo.getRowCount()-1; i>=0; i--){
				modelReturnInfo.removeRow(i);
			}
			String dFromDate=new SimpleDateFormat("yyyy-MM-dd").format(dateFromDatePC.getDate());
			String dToDate=new SimpleDateFormat("yyyy-MM-dd").format(dateToDatePC.getDate());
			String sql="select returnNo,returnDate,totalAmount from tbreceiptreturninfo where"
					+ " returnDate between '"+dFromDate+"' and '"+dToDate+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelReturnInfo.addRow(new Object[]{rs.getString("returnNo"),
						df.format(Double.parseDouble(rs.getString("totalAmount"))),
						dateFormat.format(rs.getDate("returnDate"))});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"findDataLoadWithDate()");
		}
	}
	/*public boolean deleteData(){
		try {
			StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
			String productid=token.nextToken();
			String sql="delete from tbreceiptreturndetails where returnNo='"+textReturnNo.getText().trim().
					toString()+"' and productid='"+productid+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"deleteData()");
		}
		return false;
	}*/
	public void setOpaqueTrueFalse(boolean b){
		panelCenter.setOpaque(b);
		panelCenterCenter.setOpaque(b);
		panelCenterCenterCenter.setOpaque(b);
		panelCenterCenterNorth.setOpaque(b);
		panelCenterWest.setOpaque(b);
		panelCenterWestCenter.setOpaque(b);
		panelCenterWestNorth.setOpaque(b);
		panelCenterWestNorthCenter.setOpaque(b);
		panelCenterWestNorthWest.setOpaque(b);
		panelNorth.setOpaque(b);
		panelNorthCenter.setOpaque(b);
		panelNorthWest.setOpaque(b);
		panelSouth.setOpaque(b);
		panelSouthCenter.setOpaque(b);
		panelSouthCenterCenter.setOpaque(b);
		panelSouthCenterWest.setOpaque(b);
		panelSouthNorth.setOpaque(b);
		dateFromDatePC.setOpaque(false);
		dateToDatePC.setOpaque(false);
		dateDate.setOpaque(false);
		dateReturnDate.setOpaque(false);
	}
	private void cmp() {
		add(panelNorth,BorderLayout.NORTH);
		panelNorth();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelSouth,BorderLayout.SOUTH);
		panelSouth();

	}
	private void panelNorth() {
		panelNorth.setPreferredSize(new Dimension(0, 80));
		panelNorth.setLayout(new BorderLayout());
		panelNorth.add(panelNorthWest,BorderLayout.WEST);
		panelNorthWest();
		panelNorth.add(panelNorthCenter,BorderLayout.CENTER);
		panelNorthCenter();
	}

	private void panelNorthWest() {
		panelNorthWest.setPreferredSize(new Dimension(610, 0));
		//panelNorthWest.setBackground(Color.CYAN);
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthWest.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(lblReturnNo, cn);
		lblReturnNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(textReturnNo, cn);
		textReturnNo.setEditable(false);

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(lblUserName, cn);
		lblUserName.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(textUserName, cn);
		textUserName.setEditable(false);

		cn.gridx=4;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(lblExecutive, cn);
		lblExecutive.setFont(font);
	}
	private void panelNorthCenter() {
		//panelNorthCenter.setBackground(Color.GRAY);
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthCenter.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 20, 5, 5);
		panelNorthCenter.add(lblDate, cn);
		lblDate.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(dateDate, cn);
		dateDate.setPreferredSize(new Dimension(140, 27));
		dateDate.setDate(new Date());
		dateDate.setDateFormatString("dd-MM-yyyy");

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(lblReturnDate, cn);
		lblReturnDate.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 90);
		panelNorthCenter.add(dateReturnDate, cn);
		dateReturnDate.setPreferredSize(new Dimension(140, 27));
		dateReturnDate.setDate(new Date());
		dateReturnDate.setDateFormatString("dd-MM-yyyy");

	}

	private void panelCenter() {
		//panelCenter.setPreferredSize(new Dimension(0, 400));
		panelCenter.setBackground(Color.CYAN);
		BorderLayout border=new BorderLayout();
		panelCenter.setLayout(border);

		panelCenter.add(panelCenterWest,BorderLayout.WEST);
		panelCenterWest();
		panelCenter.add(panelCenterCenter,BorderLayout.CENTER);
		panelCenterCenter();

	}
	private void panelCenterCenter() {
		Font font=new Font("clibari",Font.BOLD,12);
		TitledBorder titleExistingInvoice=BorderFactory.createTitledBorder("Existing Return to Supplier Invoice");
		panelCenterCenter.setBorder(titleExistingInvoice);
		titleExistingInvoice.setTitleJustification(TitledBorder.CENTER);
		titleExistingInvoice.setTitleFont(font);

		panelCenterCenter.setLayout(new BorderLayout());
		panelCenterCenter.add(panelCenterCenterNorth, BorderLayout.NORTH);
		panelCenterCenterNorth();
		panelCenterCenter.add(panelCenterCenterCenter, BorderLayout.CENTER);
		panelCenterCenterCenter();
	}
	private void panelCenterCenterNorth() {
		panelCenterCenterNorth.setPreferredSize(new Dimension(0, 50));
		//panelCenterCenterNorth.setBackground(Color.BLACK);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterNorth.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,11);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelCenterCenterNorth.add(lblFromDate, cn);
		lblFromDate.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelCenterCenterNorth.add(dateFromDatePC, cn);
		dateFromDatePC.setPreferredSize(new Dimension(150, 27));
		dateFromDatePC.setDate(new Date());
		dateFromDatePC.setDateFormatString("dd-MM-yyyy");

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelCenterCenterNorth.add(lblToDate, cn);
		lblToDate.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelCenterCenterNorth.add(dateToDatePC, cn);
		dateToDatePC.setPreferredSize(new Dimension(150, 27));
		dateToDatePC.setDate(new Date());
		dateToDatePC.setDateFormatString("dd-MM-yyyy");

		cn.gridx=4;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 0);
		panelCenterCenterNorth.add(btnIcon, cn);
		btnIcon.setPreferredSize(new Dimension(50, 30));
	}
	private void panelCenterCenterCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(0, 0, 0, 0);
		panelCenterCenterCenter.add(scrollPC, cn);
		scrollPC.setPreferredSize(new Dimension(468, 200));
		tableReturnInfo.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(JLabel.RIGHT);
		tableReturnInfo.getColumnModel().getColumn(1).setCellRenderer(cellRender);

		TableColumn col2=tableReturnInfo.getColumnModel().getColumn(1);
		col2.setPreferredWidth(40);
	}
	private void panelCenterWest() {
		panelCenterWest.setPreferredSize(new Dimension(630, 0));
		//panelCenterWest.setBackground(Color.LIGHT_GRAY);

		panelCenterWest.setLayout(new BorderLayout());

		panelCenterWest.add(panelCenterWestNorth,BorderLayout.NORTH);
		panelCenterWestNorth();
		panelCenterWest.add(panelCenterWestCenter,BorderLayout.CENTER);
		panelCenterWestCenter();
	}
	private void panelCenterWestCenter() {
		//panelCenterWestCenter.setBackground(Color.blue);

		FlowLayout flow=new FlowLayout();
		panelCenterWestCenter.setLayout(flow);
		flow.setVgap(0);
		flow.setHgap(15);

		panelCenterWestCenter.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnEdit);
		btnEdit.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnDelete);
		btnDelete.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnReport);
		btnReport.setPreferredSize(new Dimension(100, 35));
	}
	private void panelCenterWestNorth() {
		panelCenterWestNorth.setPreferredSize(new Dimension(0, 250));
		//panelCenterWestNorth.setBackground(Color.PINK);
		panelCenterWestNorth.setLayout(new BorderLayout());

		panelCenterWestNorth.add(panelCenterWestNorthWest,BorderLayout.WEST);
		panelCenterWestNorthWest();
		panelCenterWestNorth.add(panelCenterWestNorthCenter,BorderLayout.CENTER);
		panelCenterWestNorthCenter();
	}
	private void panelCenterWestNorthWest() {
		panelCenterWestNorthWest.setPreferredSize(new Dimension(290, 0));
		//panelCenterWestNorthWest.setBackground(Color.DARK_GRAY);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthWest.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthWest.add(lblInvoiceNo,cn);
		lblInvoiceNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthWest.add(cmbInvoiceNo.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthWest.add(lblProductId,cn);
		lblProductId.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthWest.add(cmbProductId.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenterWestNorthWest.add(lblCategory, cn);
		lblCategory.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelCenterWestNorthWest.add(cmbCategory.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=3;
		panelCenterWestNorthWest.add(lblSubcategory, cn);
		lblSubcategory.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelCenterWestNorthWest.add(cmbSubcategory.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=4;
		panelCenterWestNorthWest.add(lblSupplierName, cn);
		lblSupplierName.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelCenterWestNorthWest.add(cmbSupplierName.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=5;
		cn.insets=new Insets(5, 5, 25, 5);
		panelCenterWestNorthWest.add(lblUnit, cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		cn.insets=new Insets(5, 5, 25, 5);
		panelCenterWestNorthWest.add(textUnit, cn);

	}
	private void panelCenterWestNorthCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthCenter.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthCenter.add(lblStockQuantity,cn);
		lblStockQuantity.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthCenter.add(textStockQuantity, cn);

		cn.gridx=0;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(lblReturnQty, cn);
		lblReturnQty.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(textReturnQty, cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(lblDealerPrice, cn);
		lblDealerPrice.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(textDealerPrice, cn);
		textDealerPrice.setEditable(false);

		cn.gridx=0;
		cn.gridy=3;
		panelCenterWestNorthCenter.add(lblAmount, cn);
		lblAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelCenterWestNorthCenter.add(textAmount, cn);

		cn.gridx=0;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(lblRemarks, cn);
		lblRemarks.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(scrollRemarks, cn);

	}
	private void panelSouth() {
		panelSouth.setPreferredSize(new Dimension(0, 280));
		//panelSouth.setBackground(Color.GREEN);

		panelSouth.setLayout(new BorderLayout());

		panelSouth.add(panelSouthNorth, BorderLayout.NORTH);
		panelSouthNorth();
		panelSouth.add(panelSouthCenter, BorderLayout.CENTER);
		panelSouthCenter();
	}
	private void panelSouthCenter() {
		panelSouthCenter.setBackground(Color.ORANGE);
		panelSouthCenter.setLayout(new BorderLayout());

		panelSouthCenter.add(panelSouthCenterWest, BorderLayout.WEST);
		panelSouthCenterWest();
		panelSouthCenter.add(panelSouthCenterCenter, BorderLayout.CENTER);
		panelSouthCenterCenter();
	}
	private void panelSouthCenterWest() {
		panelSouthCenterWest.setPreferredSize(new Dimension(250, 0));
		//panelSouthCenterWest.setBackground(Color.BLACK);
		FlowLayout flow=new FlowLayout();
		panelSouthCenterWest.setLayout(flow);
		flow.setHgap(20);
		flow.setVgap(15);
		panelSouthCenterWest.add(btnConfirm);
		btnConfirm.setPreferredSize(new Dimension(100, 35));
		btnConfirm.setEnabled(false);
		panelSouthCenterWest.add(btnRefreshItemReport);
		btnRefreshItemReport.setPreferredSize(new Dimension(100, 35));

	}
	private void panelSouthCenterCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelSouthCenterCenter.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 380, 5, 5);
		panelSouthCenterCenter.add(lblTotalAmount,cn);
		lblTotalAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterCenter.add(textTotalAmount,cn);
		textTotalAmount.setPreferredSize(new Dimension(150, 35));
	}
	private void panelSouthNorth() {
		//panelSouthNorth.setBackground(Color.MAGENTA);
		panelSouthNorth.setPreferredSize(new Dimension(0, 220));

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelSouthNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelSouthNorth.add(scrollTableReceiptReturnDetails,cn);
		scrollTableReceiptReturnDetails.setPreferredSize(new Dimension(1123, 217));
		tableReceiptReturnDetails.getTableHeader().setReorderingAllowed(false);
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));

		TitledBorder titleItemReceipt=BorderFactory.createTitledBorder("Receipt Return Info");
		setBorder(titleItemReceipt);
		titleItemReceipt.setTitleJustification(TitledBorder.CENTER);
		titleItemReceipt.setTitleColor(Color.decode("#8B0000"));
		titleItemReceipt.setTitleFont(new Font("clibari", Font.BOLD, 22));

		BorderLayout border=new BorderLayout();
		setLayout(border);

	}

}
