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
import java.nio.BufferOverflowException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.sun.org.apache.xerces.internal.impl.xs.models.CMBuilder;
import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XmlVisitor.TextPredictor;
import com.toedter.calendar.JDateChooser;

public class ItemReceiptWork extends JPanel{

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

	JLabel lblInvoiceNo=new JLabel("Invoice No");
	JTextField textInvoiceNo=new JTextField(10);
	JLabel lblUserName=new JLabel("User Name");
	JTextField textUserName=new JTextField(10);
	JLabel lblExecutive=new JLabel("Executive");

	JLabel lblDate=new JLabel("Date");
	JDateChooser dateDate=new JDateChooser();
	JLabel lblInvoiceDate=new JLabel("Invoice Date");
	JDateChooser dateInvoiceDate=new JDateChooser();

	JLabel lblProductId=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategory=new JLabel("Category");
	JComboBox cmbCategory=new JComboBox();
	JLabel lblSubcategory=new JLabel("Subcategory");;
	JComboBox cmbSubcategory=new JComboBox();
	JLabel lblUnit=new JLabel("Unit");
	JTextField textUnit=new JTextField(15);
	JLabel lblStock=new JLabel("Stock");
	JTextField textStock=new JTextField();
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JTextField textDealerPrice=new JTextField();

	JLabel lblInvoiceQuantity=new JLabel("Invoice Quantity");
	JTextField textInvoiceQuantity=new JTextField(15);
	JLabel lblReceiveQuantity=new JLabel("Receive Quantity");
	JTextField textReceiveQuantity=new JTextField();
	JLabel lblAmount=new JLabel("Amount");
	JTextField textAmount=new JTextField(15);
	JLabel lblShortOverQuantity=new JLabel("S/O qty");
	JTextField textShortOverQuantity=new JTextField();
	JLabel lblPresentStock=new JLabel("Present Stock");
	JTextField textPresentStock=new JTextField();
	JLabel lblSupplierName=new JLabel("Supplier Name");
	JComboBox cmbSupplierName=new JComboBox();
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

	String col[]={"ProductId","ProductName","Unit","StockQty","DealerPrice",
			"InvoiceQty","ReceiveQty","ShortQty","PresentStock","Suppliered","SupplierName","Remarks","Amount"};
	Object row[][]={};
	DefaultTableModel modelitemDetails=new DefaultTableModel(row, col){
		public boolean isCellEditable(int row, int col){
			return false;
		}
	};
	JTable tableitemDetails=new JTable(modelitemDetails);
	JScrollPane scrollTable=new JScrollPane(tableitemDetails,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField textTotalAmount=new JTextField(35);

	ImageIcon iconConfirm=new ImageIcon("Images/confirm.png");
	JButton btnConfirm=new JButton("Confirm",iconConfirm);
	ImageIcon iconDone=new ImageIcon("Images/ImageDone.png");
	JButton btnDone=new JButton("Done",iconDone);
	ImageIcon iconRefreshItemReport=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefreshItemReport=new JButton("Refresh",iconRefresh);

	JPanel panelCenterCenterNorth=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JDateChooser dateFromDatePC=new JDateChooser();
	JDateChooser dateToDatePC=new JDateChooser();
	JButton btnFind=new JButton(new ImageIcon("Images/btnSubmit.png"));

	String colPC[]={"Invoice","TotalAmount","Date"};
	Object rowPC[][]={};
	DefaultTableModel modelitemInfo=new DefaultTableModel(rowPC, colPC){
		public boolean isCellEditable(int row, int col){
			return false;
		}
	};
	JTable tableitemInfo=new JTable(modelitemInfo);
	JScrollPane scrollitemInfo=new JScrollPane(tableitemInfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	SessionBean sessionBean=new SessionBean();
	DecimalFormat dformate=new DecimalFormat("#0.00");
	SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy"); 
	boolean isEdit=true;
	boolean isAgainEdid=true;
	boolean findbyDate=false;
	boolean isupdate=false;

	public ItemReceiptWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		textUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void btnIni(boolean b){
		btnSubmit.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnRefresh.setEnabled(b);
		btnDelete.setEnabled(b);
		btnConfirm.setEnabled(!b);
	}
	public void editable(boolean b){
		cmbProductId.cmbSuggest.setEnabled(b);
		cmbCategory.setEnabled(b);
		cmbSubcategory.setEnabled(b);
		textUnit.setEditable(b);
		textStock.setEditable(b);
		textDealerPrice.setEditable(b);
		textInvoiceQuantity.setEditable(b);
		textReceiveQuantity.setEditable(b);
		textShortOverQuantity.setEditable(b);
		textAmount.setEditable(b);
		textPresentStock.setEditable(b);
		cmbSupplierName.setEnabled(b);
		textRemarks.setEditable(b);
	}
	public void txtClear1(){
		cmbProductId.txtSuggest.setText("");
		cmbCategory.removeAllItems();
		cmbSubcategory.removeAllItems();
		cmbSupplierName.removeAllItems();
		cmbCategory.setEnabled(true);
		cmbSubcategory.setEnabled(true);
		cmbSupplierName.setEnabled(true);
		textUnit.setText("");
		textDealerPrice.setText("");
		textStock.setText("");
		textInvoiceQuantity.setText("");
		textReceiveQuantity.setText("");
		textShortOverQuantity.setText("");
		textAmount.setText("");
		textPresentStock.setText("");
		textRemarks.setText("");
	}
	public void refreshWork1(){
		txtClear1();
		cmbProductIdDataLoad();
		btnIni(true);
		editable(true);
		btnConfirm.setEnabled(true);
		isEdit=true;
	}
	public void txtClear2(){
		textUserName.setText(sessionBean.getUserName());
		dateDate.setDate(new Date());
		dateInvoiceDate.setDate(new Date());
		dateFromDatePC.setDate(new Date());
		dateToDatePC.setDate(new Date());
		textTotalAmount.setText("");
		for(int i=modelitemDetails.getRowCount()-1; i>=0; i--){
			modelitemDetails.removeRow(i);
		}
	}
	public void refreshWork2(){
		refreshWork1();
		txtClear2();
		autoId();
		tableSearchDataLoad();
		cmbProductIdDataLoad();
		isupdate=false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null,caption,"confirmation", JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public void btnAction(){
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					if(doubleEntryCheck()){
						tableDetailsDataLoad();
						refreshWork1();
					}	
				}	
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationItemReport()){
					if(isupdate){
						if(checkConfirmation("do you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "Data updated successfully.");
									refreshWork2();
								}
							}
						}
					}
					else{
						if(checkConfirmation("sure to save?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "Data saved Successfully.");
								refreshWork2();
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isupdate=true;
				btnIni(true);
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWork1();
			}
		});
		btnRefreshItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWork2();
			}
		});
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbProductId.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.cmbSuggest.getSelectedItem().toString().trim(),"#");
					String productid=token.nextToken();
					allDataLoadUnderProductid(productid);
					cmbCategory.setEnabled(false);
					cmbSubcategory.setEnabled(false);
					cmbSupplierName.setEnabled(false);
					textUnit.setEditable(false);
					textStock.setText("");
					textInvoiceQuantity.setText("");
					textReceiveQuantity.setText("");
					textShortOverQuantity.setText("");
					textAmount.setText("");
					textPresentStock.setText("");
					textRemarks.setText("");
				}
				else{
					cmbCategory.setEnabled(true);
					cmbSubcategory.setEnabled(true);
					cmbSupplierName.setEnabled(true);
					cmbCategory.removeAllItems();
					cmbSubcategory.removeAllItems();
					cmbSupplierName.removeAllItems();
					textUnit.setText("");
					textDealerPrice.setText("");
					textStock.setText("");
					textInvoiceQuantity.setText("");
					textReceiveQuantity.setText("");
					textShortOverQuantity.setText("");
					textAmount.setText("");
					textPresentStock.setText("");
					textRemarks.setText("");
				}
			}
		});
		textStock.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				calcPresentStock();
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textDealerPrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				calcAmount();
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textInvoiceQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				calcShortOverQty();
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textReceiveQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				calcPresentStock();
				calcAmount();
				calcShortOverQty();
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textStock.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!((Character.isDigit(ch)) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textDealerPrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!((Character.isDigit(ch)) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textInvoiceQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!((Character.isDigit(ch)) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		textReceiveQuantity.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!((Character.isDigit(ch)) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
					e.consume();
					getToolkit().beep();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		tableitemInfo.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				String invoiceNo=modelitemInfo.getValueAt(tableitemInfo.getSelectedRow(), 0).toString();
				searchDataLoad(invoiceNo);
			}
		});
		tableitemDetails.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {			
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				searchDataLoad(tableitemDetails.getSelectedRow());
				btnIni(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editable(true);
			}
		});
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fromDate=new SimpleDateFormat("yyyy-MM-dd").format(dateFromDatePC.getDate());
				String toDate=new SimpleDateFormat("yyyy-MM-dd").format(dateToDatePC.getDate());
				findbyDate=true;
				tableSearchDataLoadByDate(fromDate, toDate);
			}
		});
	}
	public void tableSearchDataLoadByDate(String fromDate,String toDate){
		try {
			for(int i=modelitemInfo.getRowCount()-1; i>=0; i--){
				modelitemInfo.removeRow(i);
			}
			String sql="select invoiceNo,invoiceDate,totalAmount from tbitemreceiptinfo where invoiceDate "
					+ "between '"+fromDate+"' and '"+toDate+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelitemInfo.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("totalAmount"),
						rs.getString("invoiceDate")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableSearchDataLoad");
		}
	}
	public void searchDataLoad(int row){ 
		cmbProductId.txtSuggest.setText(modelitemDetails.getValueAt(row, 0).toString()+"#"+
				modelitemDetails.getValueAt(row, 1).toString());
		textUnit.setText(modelitemDetails.getValueAt(row, 2).toString());
		textStock.setText(modelitemDetails.getValueAt(row, 3).toString());
		textDealerPrice.setText(modelitemDetails.getValueAt(row, 4).toString());
		textInvoiceQuantity.setText(modelitemDetails.getValueAt(row, 5).toString());
		textReceiveQuantity.setText(modelitemDetails.getValueAt(row, 6).toString());
		textAmount.setText(modelitemDetails.getValueAt(row, 12).toString());
		textShortOverQuantity.setText(modelitemDetails.getValueAt(row, 7).toString());
		//checkShortOverQty(Double.parseDouble(modelitemDetails.getValueAt(row, 7).toString()));
		textPresentStock.setText(modelitemDetails.getValueAt(row, 8).toString());
		cmbSupplierName.setSelectedItem(modelitemDetails.getValueAt(row, 9).toString()+"#"
				+modelitemDetails.getValueAt(row, 10).toString());
		textRemarks.setText(modelitemDetails.getValueAt(row, 11).toString());
		allDataLoadUnderProductid(modelitemDetails.getValueAt(row, 0).toString());
		modelitemDetails.removeRow(tableitemDetails.getSelectedRow());
	}
	public void searchDataLoad(String invoiceNo){
		try {
			for(int i=modelitemDetails.getRowCount()-1; i>=0; i--){
				modelitemDetails.removeRow(i);
			}
			int a=0;
			String sql="select a.invoiceNo,a.currDate,a.invoiceDate,a.totalAmount,a.userName,b.invoiceNo"
					+ ",b.productid,b.productName,b.unit,b.stockQty,b.dealerPrice,b.invoiceQty,b.receiveQty,"
					+ "b.shortOverQty,b.presentStock,b.supplierid,b.supplierName,b.remarks,b.amount "
					+ "from tbitemreceiptinfo a inner join tbitemreceiptdetails b  "
					+ "on a.invoiceNo=b.invoiceNo where a.invoiceNo like '"+invoiceNo+"' ";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){  
				if(a==0){
					textInvoiceNo.setText(rs.getString("invoiceNo"));
					textUserName.setText(rs.getString("userName"));
					dateDate.setDate(rs.getDate("currdate"));
					dateInvoiceDate.setDate(rs.getDate("invoiceDate"));
					textTotalAmount.setText(rs.getString("totalAmount"));
				}
				a++;
				modelitemDetails.addRow(new Object[]{rs.getString("productid"),
						rs.getString("productName"),rs.getString("unit"),
						rs.getString("stockQty"),rs.getString("dealerPrice"),
						rs.getString("invoiceQty"),rs.getString("receiveQty"),
						rs.getString("shortOverQty"),rs.getString("presentStock"),
						rs.getString("supplierid"),rs.getString("supplierName"),rs.getString("remarks"),
						rs.getString("amount")});				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void totalAmountCalculation(){
		double sum=0,amount;
		for(int i=0; i<modelitemDetails.getRowCount();i++){
			String am=modelitemDetails.getValueAt(i, 12).toString();
			amount=Double.parseDouble(am);
			sum=sum+amount;
		}
		textTotalAmount.setText(dformate.format(sum));
	}
	public boolean doubleEntryCheck(){
		StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
		String productid=token.nextToken();

		for(int i=0; i<modelitemDetails.getRowCount(); i++){
			if(modelitemDetails.getValueAt(i, 0).toString().equalsIgnoreCase(productid)){
				JOptionPane.showMessageDialog(null, "This product is already exixt!");
				return false;
			}
		}
		return true;
	}
	public void calcShortOverQty(){
		double invoice,receive,shotover=0;
		String type="";
		invoice=Double.parseDouble(textInvoiceQuantity.getText().trim().isEmpty()?"0":textInvoiceQuantity.getText().trim());
		receive=Double.parseDouble(textReceiveQuantity.getText().trim().isEmpty()?"0":textReceiveQuantity.getText().trim());
		if(invoice>receive){
			shotover=invoice-receive;
			type="Over Qty";
		}
		else if(invoice==receive){
			shotover=0;
			type="S/O qty";
		}
		else{
			shotover=receive-invoice;
			type="Short Qty";
		}
		checkShortOverQty(type);
		textShortOverQuantity.setText(dformate.format(shotover));
	}
	private void checkShortOverQty(String type) {
		if(!(textInvoiceQuantity.getText().trim().toString().isEmpty())&&!
				(textReceiveQuantity.getText().trim().toString().isEmpty())){ //textInvoiceQuantity te type korle jate kaj na kore
			lblShortOverQuantity.setText(type);  
		}
		else{
			lblShortOverQuantity.setText("S/O qty");
		}
	}
	public void calcAmount(){
		double dealer,receive,amount;
		dealer=Double.parseDouble(textDealerPrice.getText().trim().isEmpty()?"0":textDealerPrice.getText().trim());
		receive=Double.parseDouble(textReceiveQuantity.getText().trim().isEmpty()?"0":textReceiveQuantity.getText().trim());
		amount=dealer*receive;
		textAmount.setText(dformate.format(amount));
	}
	public void calcPresentStock()
	{
		double stock,receive,preStock;
		stock=Double.parseDouble(textStock.getText().trim().isEmpty()?"0":textStock.getText().trim());
		receive=Double.parseDouble(textReceiveQuantity.getText().trim().isEmpty()?"0":textReceiveQuantity.getText().trim());
		preStock=stock+receive;
		textPresentStock.setText(dformate.format(preStock));
	}
	public void cmbProductIdDataLoad(){
		try {
			cmbProductId.v.clear();
			cmbProductId.v.add("");
			String sql="select productid,productName from tbproductinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbProductId.v.add(rs.getString("productid")+"#"+rs.getString("productName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbproductid data load");
		}
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(invoiceNo,locate('-',invoiceNo)+1,"
					+ "length(invoiceNo)-locate('-',invoiceNo))as UNSIGNED)),0)+1 as id from tbitemreceiptinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				textInvoiceNo.setText("invoiceNo-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Autoid from tbItemReceiptinfo");
		}
	}

	public void allDataLoadUnderProductid(String pid){
		try {
			cmbCategory.removeAllItems();
			cmbSubcategory.removeAllItems();
			cmbSupplierName.removeAllItems();		
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,supplierId,supplierName,"
					+ "unit,dealerPrice from tbproductinfo where productId like '"+pid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategory.addItem(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().isEmpty()){
					cmbSubcategory.addItem(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				}
				else{
					cmbSubcategory.addItem("");
				}
				cmbSubcategory.addItem(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				cmbSupplierName.addItem(rs.getString("supplierId")+"#"+rs.getString("supplierName"));
				textUnit.setText(rs.getString("unit"));
				textDealerPrice.setText(rs.getString("dealerPrice"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"all data load from product");
		}
	}
	public boolean checkValidation(){
		if(!cmbProductId.txtSuggest.getText().toString().isEmpty()){
			if(!textStock.getText().trim().toString().isEmpty()){
				if(!textInvoiceQuantity.getText().trim().toString().isEmpty()){
					if(!textReceiveQuantity.getText().trim().toString().isEmpty()){
						if(!textRemarks.getText().trim().toString().isEmpty()){
							return true;
						}
						else{
							JOptionPane.showMessageDialog(null, "insert Remarks pleasse");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "insert Receive Quantity pleasse");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert Invoice Quantity pleasse");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert Stock pleasse");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select productId pleasse");
		}
		return false;
	}
	public boolean checkValidationItemReport(){
		if(modelitemDetails.getRowCount()>=1){
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Table is Empty");
		}	
		return false;
	}
	public void tableDetailsDataLoad(){
		try {
			StringTokenizer token1=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
			String productid=token1.nextToken();
			String productName=token1.nextToken();
			
			StringTokenizer token2=new StringTokenizer(cmbSupplierName.getSelectedItem().toString(),"#");
			String supplierid=token2.nextToken();
			String supplierName=token2.nextToken();

			modelitemDetails.addRow(new Object[]{productid,productName,textUnit.getText().trim().toString(),
					textStock.getText().trim().toString(),textDealerPrice.getText().trim().toString(),
					textInvoiceQuantity.getText().trim().toString(),textReceiveQuantity.getText().trim().toString(),
					textShortOverQuantity.getText().trim().toString(),textPresentStock.getText().trim().toString(),
					supplierid,supplierName,textRemarks.getText().trim().toString(),textAmount.getText().trim().toString()});
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		totalAmountCalculation();
	}
	public boolean insertData(){
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);

			String dDate=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String dinvoiceDate=new SimpleDateFormat("yyyy-MM-dd").format(dateInvoiceDate.getDate());

			String sqlInfo="insert into tbitemreceiptinfo(invoiceNo,currDate,invoiceDate,totalAmount,"
					+ "userName,"
					+ "userip,entryTime)"
					+ "values('"+textInvoiceNo.getText().trim()+"',"
					+ "'"+dDate+"',"
					+ "'"+dinvoiceDate+"',"
					+ "'"+textTotalAmount.getText().trim()+"',"
					+ "'"+textUserName.getText().trim()+"',"
					+ "'',now())";	
			dbConneciton.sta.executeUpdate(sqlInfo);

			for(int i=0; i<tableitemDetails.getRowCount(); i++){

				String sqlItemDetails="insert into tbitemreceiptdetails(invoiceNo,productid,productName,unit,"
						+ "stockQty,dealerPrice,invoiceQty,receiveQty,shortOverQty,presentStock,supplierid,"
						+ "supplierName,remarks,amount)"
						+ "values('"+textInvoiceNo.getText().trim().toString()+"',"
						+ "'"+modelitemDetails.getValueAt(i, 0)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 1)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 2)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 3)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 4)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 5)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 6)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 7)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 8)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 9)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 10)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 11)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 12)+"')";

				dbConneciton.sta.executeUpdate(sqlItemDetails);
			}
			dbConneciton.con.commit();
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			try {
				dbConneciton.con.rollback();
			} 
			catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2+"RollBack");
			}
			JOptionPane.showMessageDialog(null, e+"Insert Data()");
		}
		return false;
	}
	public boolean deleteData(){
		try {
			String sqlInfo="delete from tbitemreceiptinfo where invoiceNo "
					+ "like '"+textInvoiceNo.getText().trim().toString()+"'";
			String sqlDetails="delete from tbitemreceiptdetails where "
					+ "invoiceNo='"+textInvoiceNo.getText().trim().toString()+"'";
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

			String infoDelete="delete from tbitemreceiptinfo where invoiceNo "
					+ "like '"+textInvoiceNo.getText().trim().toString()+"'";
			dbConneciton.sta.executeUpdate(infoDelete);
			String detailsDetele="delete from tbitemreceiptdetails where "
					+ "invoiceNo='"+textInvoiceNo.getText().trim().toString()+"'";
			dbConneciton.sta.executeUpdate(detailsDetele);		

			String dDate=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String dinvoiceDate=new SimpleDateFormat("yyyy-MM-dd").format(dateInvoiceDate.getDate());

			String sqlInfo="insert into tbitemreceiptinfo(invoiceNo,currDate,invoiceDate,totalAmount,"
					+ "userName,"
					+ "userip,entryTime)"
					+ "values('"+textInvoiceNo.getText().trim()+"',"
					+ "'"+dDate+"',"
					+ "'"+dinvoiceDate+"',"
					+ "'"+textTotalAmount.getText().trim()+"',"
					+ "'"+textUserName.getText().trim()+"',"
					+ "'',now())";	
			dbConneciton.sta.executeUpdate(sqlInfo);

			for(int i=0; i<tableitemDetails.getRowCount(); i++){

				String sqlItemDetails="insert into tbitemreceiptdetails(invoiceNo,productid,productName,unit,"
						+ "stockQty,dealerPrice,invoiceQty,receiveQty,shortOverQty,presentStock,supplierid,"
						+ "supplierName,remarks,amount)"
						+ "values('"+textInvoiceNo.getText().trim().toString()+"',"
						+ "'"+modelitemDetails.getValueAt(i, 0)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 1)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 2)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 3)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 4)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 5)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 6)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 7)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 8)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 9)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 10)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 11)+"',"
						+ "'"+modelitemDetails.getValueAt(i, 12)+"')";

				dbConneciton.sta.executeUpdate(sqlItemDetails);
			}
			dbConneciton.con.commit();
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			try {
				dbConneciton.con.rollback();
			} 
			catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2+"RollBack");
			}
			JOptionPane.showMessageDialog(null, e+"updateWork()");
		}
		return false;
	}*/
	public void tableSearchDataLoad(){
		try {
			for(int i=modelitemInfo.getRowCount()-1; i>=0; i--){
				modelitemInfo.removeRow(i);
			}
			String sql="select invoiceNo,invoiceDate,totalAmount from tbitemreceiptinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelitemInfo.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("totalAmount"),
						rs.getString("invoiceDate")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableSearchDataLoad");
		}
	}
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
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthWest.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,13);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(lblInvoiceNo, cn);
		lblInvoiceNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWest.add(textInvoiceNo, cn);
		textInvoiceNo.setEditable(false);

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
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthCenter.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,13);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
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
		dateDate.setOpaque(false);

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(lblInvoiceDate, cn);
		lblInvoiceDate.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 90);
		panelNorthCenter.add(dateInvoiceDate, cn);
		dateInvoiceDate.setPreferredSize(new Dimension(140, 27));
		dateInvoiceDate.setDate(new Date());
		dateInvoiceDate.setDateFormatString("dd-MM-yyyy");
		dateInvoiceDate.setOpaque(false);
	}
	private void panelCenter() {
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
		TitledBorder titleExistingInvoice=BorderFactory.createTitledBorder("Existing Invoice");
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
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterNorth.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 0, 5, 0);
		panelCenterCenterNorth.add(lblFromDate, cn);
		lblFromDate.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(dateFromDatePC, cn);
		dateFromDatePC.setPreferredSize(new Dimension(150, 27));
		dateFromDatePC.setDate(new Date());
		dateFromDatePC.setDateFormatString("dd-MM-yyyy");
		dateFromDatePC.setOpaque(false);

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterNorth.add(lblToDate, cn);
		lblToDate.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 90);
		panelCenterCenterNorth.add(dateToDatePC, cn);
		dateToDatePC.setPreferredSize(new Dimension(150, 27));
		dateToDatePC.setDate(new Date());
		dateToDatePC.setDateFormatString("dd-MM-yyyy");
		dateToDatePC.setOpaque(false);

		cn.gridx=4;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 0);
		panelCenterCenterNorth.add(btnFind, cn);
		btnFind.setPreferredSize(new Dimension(50, 30));
	}
	private void panelCenterCenterCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(0, 1, 0, 1);
		panelCenterCenterCenter.add(scrollitemInfo, cn);
		scrollitemInfo.setPreferredSize(new Dimension(485, 218));
		tableitemInfo.getTableHeader().setReorderingAllowed(false);
	}
	private void panelCenterWest() {
		panelCenterWest.setPreferredSize(new Dimension(610, 0));
		panelCenterWest.setLayout(new BorderLayout());
		panelCenterWest.add(panelCenterWestNorth,BorderLayout.NORTH);
		panelCenterWestNorth();
		panelCenterWest.add(panelCenterWestCenter,BorderLayout.CENTER);
		panelCenterWestCenter();
	}
	private void panelCenterWestCenter() {
		FlowLayout flow=new FlowLayout();
		panelCenterWestCenter.setLayout(flow);
		flow.setVgap(20);
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
		panelCenterWestNorth.setLayout(new BorderLayout());
		panelCenterWestNorth.add(panelCenterWestNorthWest,BorderLayout.WEST);
		panelCenterWestNorthWest();
		panelCenterWestNorth.add(panelCenterWestNorthCenter,BorderLayout.CENTER);
		panelCenterWestNorthCenter();
	}
	private void panelCenterWestNorthWest() {
		panelCenterWestNorthWest.setPreferredSize(new Dimension(250, 0));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthWest.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthWest.add(lblProductId,cn);
		lblProductId.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthWest.add(cmbProductId.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=1;
		panelCenterWestNorthWest.add(lblCategory, cn);
		lblCategory.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthWest.add(cmbCategory, cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenterWestNorthWest.add(lblSubcategory, cn);
		lblSubcategory.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelCenterWestNorthWest.add(cmbSubcategory, cn);

		cn.gridx=0;
		cn.gridy=3;
		panelCenterWestNorthWest.add(lblUnit, cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelCenterWestNorthWest.add(textUnit, cn);
		textUnit.setEditable(false);

		cn.gridx=0;
		cn.gridy=4;
		panelCenterWestNorthWest.add(lblStock, cn);
		lblStock.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelCenterWestNorthWest.add(textStock, cn);

		cn.gridx=0;
		cn.gridy=5;
		panelCenterWestNorthWest.add(lblDealerPrice, cn);
		lblDealerPrice.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		panelCenterWestNorthWest.add(textDealerPrice, cn);
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
		panelCenterWestNorthCenter.add(lblInvoiceQuantity,cn);
		lblInvoiceQuantity.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthCenter.add(textInvoiceQuantity, cn);

		cn.gridx=0;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(lblReceiveQuantity, cn);
		lblReceiveQuantity.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(textReceiveQuantity, cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(lblAmount, cn);
		lblAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(textAmount, cn);
		textAmount.setEditable(false);

		cn.gridx=0;
		cn.gridy=3;
		panelCenterWestNorthCenter.add(lblShortOverQuantity, cn);
		lblShortOverQuantity.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelCenterWestNorthCenter.add(textShortOverQuantity, cn);
		textShortOverQuantity.setEditable(false);

		cn.gridx=0;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(lblPresentStock, cn);
		lblPresentStock.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(textPresentStock, cn);
		textPresentStock.setEditable(false);

		cn.gridx=0;
		cn.gridy=5;
		panelCenterWestNorthCenter.add(lblSupplierName, cn);
		lblSupplierName.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		panelCenterWestNorthCenter.add(cmbSupplierName, cn);

		cn.gridx=0;
		cn.gridy=6;
		panelCenterWestNorthCenter.add(lblRemarks, cn);
		lblRemarks.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		panelCenterWestNorthCenter.add(scrollRemarks, cn);
	}
	private void panelSouth() {
		panelSouth.setPreferredSize(new Dimension(0, 260));
		panelSouth.setLayout(new BorderLayout());
		panelSouth.add(panelSouthNorth, BorderLayout.NORTH);
		panelSouthNorth();
		panelSouth.add(panelSouthCenter, BorderLayout.CENTER);
		panelSouthCenter();
	}
	private void panelSouthCenter() {
		panelSouthCenter.setLayout(new BorderLayout());
		panelSouthCenter.add(panelSouthCenterWest, BorderLayout.WEST);
		panelSouthCenterWest();
		panelSouthCenter.add(panelSouthCenterCenter, BorderLayout.CENTER);
		panelSouthCenterCenter();
	}
	private void panelSouthCenterWest() {
		panelSouthCenterWest.setPreferredSize(new Dimension(370, 0));
		FlowLayout flow=new FlowLayout();
		panelSouthCenterWest.setLayout(flow);
		flow.setHgap(15);
		flow.setVgap(7);
		panelSouthCenterWest.add(btnConfirm);
		btnConfirm.setPreferredSize(new Dimension(100, 35));
		panelSouthCenterWest.add(btnDone);
		btnDone.setPreferredSize(new Dimension(100, 35));
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
		cn.insets=new Insets(5, 150, 5, 5);
		panelSouthCenterCenter.add(lblTotalAmount,cn);
		lblTotalAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterCenter.add(textTotalAmount,cn);
		textTotalAmount.setPreferredSize(new Dimension(120,35));
		textTotalAmount.setFont(new Font("calibri",Font.BOLD,16));
		textTotalAmount.setForeground(Color.RED);
	}
	private void panelSouthNorth() {
		panelSouthNorth.setPreferredSize(new Dimension(0, 210));		
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelSouthNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelSouthNorth.add(scrollTable,cn);
		scrollTable.setPreferredSize(new Dimension(1123, 200));	
		tableitemDetails.getTableHeader().setReorderingAllowed(false);
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));	
		TitledBorder titleItemReceipt=BorderFactory.createTitledBorder("Item Receipt Info");
		setBorder(titleItemReceipt);
		titleItemReceipt.setTitleJustification(TitledBorder.CENTER);
		titleItemReceipt.setTitleColor(Color.decode("#8B0000"));
		titleItemReceipt.setTitleFont(new Font("clibari", Font.BOLD, 22));	
		BorderLayout border=new BorderLayout();
		setLayout(border);	
	}
}
