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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class SalesWork extends JPanel{

	JPanel panelNorth=new JPanel();
	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();

	JPanel panelNorthCenter=new JPanel();
	JPanel panelNorthWest=new JPanel();
	JPanel panelSouthNorth=new JPanel();
	JPanel panelSouthCenter=new JPanel();
	JPanel panelCenterWest=new JPanel();
	JPanel panelCenterCenter=new JPanel();

	JPanel panelCenterWestNorth=new JPanel();
	JPanel panelCenterWestCenter=new JPanel();

	JPanel panelNorthWestWest=new JPanel();
	JPanel panelNorthWestCenter=new JPanel();

	JPanel panelCenterCenterNorth=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();

	JPanel panelCenterWestNorthWest=new JPanel();
	JPanel panelCenterWestNorthCenter=new JPanel();

	JPanel panelSouthCenterWest=new JPanel();
	JPanel panelSouthCenterCenter=new JPanel();

	JCheckBox chkRegisterClient=new JCheckBox("Register Client");
	JLabel lblClientID=new JLabel("Client ID");
	SuggestText cmbClientId=new SuggestText();
	JCheckBox chkUnregisterClient=new JCheckBox("Unregister Client");
	JLabel lblUserName=new JLabel("User Name");
	JTextField textUserName=new JTextField(15);
	JLabel lblExecutive=new JLabel("Executive");

	JLabel lblSalesNo=new JLabel("Sales No");
	JTextField textSalesNo=new JTextField(15);
	JLabel lblDate=new JLabel("Date");
	JDateChooser dateDate=new JDateChooser();

	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JDateChooser dateFromDatePC=new JDateChooser();
	JDateChooser dateToDatePC=new JDateChooser();
	JButton btnIcon=new JButton(new ImageIcon("Images/btnSubmit.png"));

	JLabel lblPaymentProtocol=new JLabel("Payment Protocol");
	SuggestText cmbPaymentProtocol=new SuggestText();
	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField textTotalAmount=new JTextField(15);
	JLabel lblPaidAmoutn=new JLabel("Paid Amount");
	JTextField textPaidAmount=new JTextField(15);
	JLabel lblReference=new JLabel("Reference");
	JTextField textReference=new JTextField(15);
	JLabel lblDiscountAmount=new JLabel("Discount Amount");
	JTextField textDiscountAmount=new JTextField(15);
	JLabel lblDue=new JLabel("Due");
	JTextField textDue=new JTextField(15);

	ImageIcon iconConfirm=new ImageIcon("Images/confirm.png");
	JButton btnConfirm=new JButton("Confirm",iconConfirm);
	ImageIcon iconRefreshItemReport=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefreshItemReport=new JButton("Refresh",iconRefreshItemReport);

	String col[]={"Sales No","Total Amount","Due Amount","Date"};
	Object row[][]={};
	DefaultTableModel modelSalesinfo=new DefaultTableModel(row, col);
	JTable tableSalesinfo=new JTable(modelSalesinfo);
	JScrollPane scroll=new JScrollPane(tableSalesinfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String colPN[]={"ProductId","ProductName","Catid","CatName","Subcatid","SubcatName","Unit","StockQty",
			"SalesQty","DealerPrice","TradePrice","Amount","Remark"};
	Object rowPN[][]={};
	DefaultTableModel modelSalesDetails=new DefaultTableModel(rowPN, colPN){
		public boolean isCellEditable(int row,int col){
			return false;
		}
	};
	JTable tableSalesDetails=new JTable(modelSalesDetails);
	JScrollPane scrollTable=new JScrollPane(tableSalesDetails,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel lblProductID=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategory=new JLabel("Category");
	SuggestText cmbCategory=new SuggestText();	
	JLabel lblSubcategory=new JLabel("Subcategory");
	SuggestText cmbSubcategory=new SuggestText();
	JLabel lblUnit=new JLabel("Unit");
	JTextField textUnit=new JTextField(15);

	JLabel lblStockQty=new JLabel("Stock Qty");
	JTextField textStockQty=new JTextField(16);
	JLabel lblSalesQty=new JLabel("Sales Qty");
	JTextField textSalesQty=new JTextField();
	JLabel lblDealerPrice=new JLabel("Dealer Price");
	JTextField textDealerPrice=new JTextField();
	JLabel lblTradePrice=new JLabel("Trade Price");
	JTextField textTradePrice=new JTextField();
	JLabel lblAmount=new JLabel("Amount");
	JTextField textAmount=new JTextField();
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

	ButtonGroup btnGroup=new ButtonGroup();

	SessionBean sessionBean=new SessionBean();
	DecimalFormat df=new DecimalFormat("#0.00");
	SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

	boolean isUpdate=false;

	//(sales e sob kaj sequentialy kora ace kontar por konta hobe nice jebabe korci oybabei)//
	public SalesWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		textUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
	}
	public void btnIni(boolean b){
		btnSubmit.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void txtClearSalesDetails(){
		cmbProductId.txtSuggest.setText("");
		cmbCategory.txtSuggest.setText("");
		cmbSubcategory.txtSuggest.setText("");
		textUnit.setText("");
		textStockQty.setText("");
		textSalesQty.setText("");
		textDealerPrice.setText("");
		textTradePrice.setText("");
		textAmount.setText("");
		textRemarks.setText("");
	}
	public void refreshWorkSalesDetails(){
		txtClearSalesDetails();
		cmbProductIdDataLoad();
		btnIni(true);
		editable(true);
	}
	public void txtClearSalesInfo(){
		chkUnregisterClient.setSelected(true);
		cmbClientId.txtSuggest.setText("");
		cmbClientId.cmbSuggest.setEnabled(false);
		textSalesNo.setText("");
		textUserName.setText("");
		textUserName.setText(sessionBean.getUserName());
		dateDate.setDate(new Date());
		cmbPaymentProtocol.txtSuggest.setText("");
		textReference.setText("");
		textTotalAmount.setText("");
		textPaidAmount.setText("");
		textDue.setText("");
		textDiscountAmount.setText("");
		for(int i=modelSalesDetails.getRowCount()-1; i>=0; i--){
			modelSalesDetails.removeRow(i);
		}
	}
	public void refreshWorkSalesInfo(){
		refreshWorkSalesDetails();
		txtClearSalesInfo();
		autoId();
		cmbProductIdDataLoad();
		cmbClientIdDataLoad();
		tableSalesInfoDateLoad();
		isUpdate=false;
		btnIni(true);
		editable(true);
		btnConfirm.setEnabled(false);
	}
	public void editable(boolean b){
		cmbProductId.cmbSuggest.setEnabled(b);
		cmbCategory.cmbSuggest.setEnabled(b);
		cmbSubcategory.cmbSuggest.setEnabled(b);
		textUnit.setEditable(b);
		textStockQty.setEditable(b);
		textSalesQty.setEditable(b);
		textDealerPrice.setEditable(b);
		textTradePrice.setEditable(b);
		textAmount.setEditable(b);
		textRemarks.setEditable(b);
	}
	public void btnAction(){
		chkRegisterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkRegisterClient.isSelected()){
					cmbClientId.cmbSuggest.setEnabled(true);
				}
			}
		});
		chkUnregisterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkUnregisterClient.isSelected()){
					cmbClientId.cmbSuggest.setEnabled(false); 
				}
			}
		});
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(),"#");
					String pid=token.nextToken();
					allDataLoadUnderProductId(pid);
					textStockQty.setText("");
					textSalesQty.setText("");
					textAmount.setText("");
					textRemarks.setText("");
				}
				else{
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					textUnit.setText("");
					textStockQty.setText("");
					textSalesQty.setText("");
					textDealerPrice.setText("");
					textTradePrice.setText("");
					textAmount.setText("");
					textRemarks.setText("");
				}
			}
		});
		textSalesQty.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				amountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		textTradePrice.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				amountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		tableSalesDetails.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}	
			public void mousePressed(MouseEvent e) {}		
			public void mouseExited(MouseEvent e) {}		
			public void mouseEntered(MouseEvent e) {}		
			public void mouseClicked(MouseEvent e) {
				searchDataItemDetails(tableSalesDetails.getSelectedRow());
				totalAmountCalculation();
				btnIni(false);
				editable(false);
			}
		});
		textTotalAmount.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				paidAmountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		textDue.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				paidAmountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		textDiscountAmount.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				paidAmountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});

		/*textPaidAmount.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				dueAmountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});*/
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationItemDetails()){
					if(doubleEntryCheck()){
						tableItemDetailsDataLoad();
						totalAmountCalculation();
						refreshWorkSalesDetails();
						btnConfirm.setEnabled(true);
					}
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkRegisterClient.isSelected()){
					if(checkValidationInsertData1()){
						if(isUpdate){
							if(checkConfirmation("do you want to update data?")){
								if(deleteData()){
									if(insertData()){
										JOptionPane.showMessageDialog(null, "Data updated successfully.");
										refreshWorkSalesInfo();
									}
								}
							}
						}
						else{
							if(checkConfirmation("Sure to Save?")){
								if(insertData()){ 
									JOptionPane.showMessageDialog(null, "All information saved successfully.");
									refreshWorkSalesInfo();
								}
							}
						}
					}
				}		
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkUnregisterClient.isSelected()){
					if(checkValidationInsertData2()){
						if(isUpdate){
							if(checkConfirmation("do you want to update data?")){
								if(deleteData()){
									if(insertData()){
										JOptionPane.showMessageDialog(null, "Data updated successfully.");
										refreshWorkSalesInfo();
									}
								}
							}
						}
						else{
							if(checkConfirmation("Sure to Save?")){
								if(insertData()){ 
									JOptionPane.showMessageDialog(null, "All information saved successfully.");
									refreshWorkSalesInfo();
								}
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
			}
		});
		tableSalesinfo.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				String salesNo=modelSalesinfo.getValueAt(tableSalesinfo.getSelectedRow(), 0).toString();
				searchData(salesNo);
				textTotalAmount.setForeground(Color.RED);
				textTotalAmount.setFont(new Font("Carlibri", Font.BOLD, 15));
			}
		});
		textDiscountAmount.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				double discountAmount,totalAmount;
				discountAmount=Double.parseDouble(textDiscountAmount.getText().trim().toString().isEmpty()?"0":
					textDiscountAmount.getText().trim().toString());
				totalAmount=Double.parseDouble(textTotalAmount.getText().trim().toString().isEmpty()?"0":
					textTotalAmount.getText().trim().toString());
				if(discountAmount>totalAmount){
					textDiscountAmount.setText("");
					textPaidAmount.setText("");
					JOptionPane.showMessageDialog(null, "Discount amount cannot greater then Total amount.",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
		textDue.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				double dueAmount,totalAmount;
				dueAmount=Double.parseDouble(textDue.getText().trim().toString().isEmpty()?"0":
					textDue.getText().trim().toString());
				totalAmount=Double.parseDouble(textTotalAmount.getText().trim().toString().isEmpty()?"0":
					textTotalAmount.getText().trim().toString());
				if(dueAmount>totalAmount){
					textDue.setText("");
					textPaidAmount.setText("");
					JOptionPane.showMessageDialog(null, "Due amount cannot greater then Total amount.",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDataWithDate();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkSalesDetails();
			}
		});
		btnRefreshItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkSalesInfo();
			}
		});
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(salesNo,locate('-',salesNo)+1,"
					+ "length(salesNo)-locate('-',salesNo)) as UNSIGNED)),0)+1 as id from tbsalesinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				textSalesNo.setText("SalesNo-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId()");
		}
	}
	public void cmbClientIdDataLoad(){
		try {
			cmbClientId.v.clear();
			cmbClientId.v.add("");
			String sql="select clientId,clientName from tbpartyinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbClientId.v.add(rs.getString("clientid")+"#"+rs.getString("clientName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbClientIdDataLoad()");
		}
	}
	public void cmbProductIdDataLoad(){
		try {
			cmbProductId.v.clear();
			cmbProductId.v.add("");
			String sql="select productId,productName from tbProductinfo";
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
	public void allDataLoadUnderProductId(String productid){
		try {
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,unit,dealerPrice,"
					+ "tradePrice from tbProductinfo where productId='"+productid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategory.txtSuggest.setText(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().toString().isEmpty()){
					cmbSubcategory.txtSuggest.setText(rs.getString("subCategoryId")+"#"+rs.getString("subCategoryName"));
				}
				else{
					cmbSubcategory.txtSuggest.setText("");
				}
				textUnit.setText(rs.getString("unit"));
				textDealerPrice.setText(rs.getString("dealerPrice"));
				textTradePrice.setText(rs.getString("tradePrice"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"allDataLoadUnderProductId()");
		}
	}
	public void amountCalculation(){
		double salesQty,tradePrice,amount;
		salesQty=Double.parseDouble(textSalesQty.getText().trim().toString().isEmpty()?"0":
			textSalesQty.getText().trim().toString());
		tradePrice=Double.parseDouble(textTradePrice.getText().trim().toString().isEmpty()?"0":
			textTradePrice.getText().trim().toString());
		amount=salesQty*tradePrice;
		textAmount.setText(df.format(amount));
	}
	public boolean checkValidationItemDetails(){
		if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
			if(!textStockQty.getText().trim().toString().isEmpty()){
				if(!textSalesQty.getText().trim().toString().isEmpty()){
					if(!textAmount.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "insert Amount please","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert SalesQty please","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert StockQty please","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "please select productid","Error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public void tableItemDetailsDataLoad(){
		String subcatid="";
		String subcatName="";
		StringTokenizer token1=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(), "#");
		String pid=token1.nextToken();
		String pName=token1.nextToken();
		StringTokenizer token2=new StringTokenizer(cmbCategory.txtSuggest.getText().trim(), "#");
		String catid=token2.nextToken();
		String catName=token2.nextToken();
		if(!cmbSubcategory.txtSuggest.getText().trim().toString().isEmpty()){
			StringTokenizer token3=new StringTokenizer(cmbSubcategory.txtSuggest.getText().trim(), "#");
			subcatid=token3.nextToken();
			subcatName=token3.nextToken();
		}		
		modelSalesDetails.addRow(new Object[]{pid,pName,catid,catName,subcatid,subcatName,
				textUnit.getText().trim().toString(),textStockQty.getText().trim().toString(),
				textSalesQty.getText().trim().toString(),textDealerPrice.getText().trim().toString(),
				textTradePrice.getText().trim().toString(),textAmount.getText().trim().toString(),
				textRemarks.getText().trim().toString()});	
	}
	public boolean doubleEntryCheck(){
		StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim().toString(), "#");
		String pid=token.nextToken();
		for(int i=0; i<modelSalesDetails.getRowCount(); i++){
			String productid=modelSalesDetails.getValueAt(i, 0).toString();
			if(pid.equalsIgnoreCase(productid)){
				JOptionPane.showMessageDialog(null, "This product already exist!","warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	public void totalAmountCalculation(){
		double sum=0.0,amount;
		for(int i=0; i<modelSalesDetails.getRowCount(); i++){
			amount=Double.parseDouble(modelSalesDetails.getValueAt(i, 11).toString());
			sum=sum+amount;
		}
		textTotalAmount.setText(df.format(sum));
		textTotalAmount.setForeground(Color.RED);
		textTotalAmount.setFont(new Font("", Font.BOLD, 15));
	}
	public void paidAmountCalculation(){
		double totalAmount,dueAmount,discountAmount;
		totalAmount=Double.parseDouble(textTotalAmount.getText().trim().toString().isEmpty()?"0":
			textTotalAmount.getText().trim().toString());
		discountAmount=Double.parseDouble(textDiscountAmount.getText().trim().toString().isEmpty()?"0":
			textDiscountAmount.getText().trim().toString());
		dueAmount=Double.parseDouble(textDue.getText().trim().toString().isEmpty()?"0":
			textDue.getText().trim().toString());
		textPaidAmount.setText(df.format(totalAmount-dueAmount-discountAmount));
		textPaidAmount.setForeground(Color.RED);
		textPaidAmount.setFont(new Font("", Font.BOLD, 15));	
	}
	public void dueAmountCalculation(){
		double totalAmount,discountAmount,paidAmount,dueAmount;
		totalAmount=Double.parseDouble(textTotalAmount.getText().trim().toString().isEmpty()?"0":
			textTotalAmount.getText().trim().toString());
		paidAmount=Double.parseDouble(textPaidAmount.getText().trim().toString().isEmpty()?"0":
			textPaidAmount.getText().trim().toString());
		discountAmount=Double.parseDouble(textDiscountAmount.getText().trim().toString().isEmpty()?"0":
			textDiscountAmount.getText().trim().toString());
		dueAmount=totalAmount-discountAmount-paidAmount;		
		textDue.setText(df.format(paidAmount));
		textDue.setForeground(Color.RED);
		textDue.setFont(new Font("", Font.BOLD, 15));		
	}
	public void searchDataItemDetails(int rowNumber){
		cmbProductId.txtSuggest.setText(modelSalesDetails.getValueAt(rowNumber, 0).toString()+"#"+
				modelSalesDetails.getValueAt(rowNumber, 1).toString());
		cmbCategory.txtSuggest.setText(modelSalesDetails.getValueAt(rowNumber, 2).toString()+"#"+
				modelSalesDetails.getValueAt(rowNumber, 3).toString());
		if(!(modelSalesDetails.getValueAt(rowNumber, 4).toString().isEmpty()&&
				modelSalesDetails.getValueAt(rowNumber, 5).toString().isEmpty())){
			cmbSubcategory.txtSuggest.setText(modelSalesDetails.getValueAt(rowNumber, 4).toString()+"#"+
					modelSalesDetails.getValueAt(rowNumber, 5).toString());
		}
		else{
			cmbSubcategory.txtSuggest.setText("");
		}
		textUnit.setText(modelSalesDetails.getValueAt(rowNumber, 6).toString());
		textStockQty.setText(modelSalesDetails.getValueAt(rowNumber, 7).toString());
		textSalesQty.setText(modelSalesDetails.getValueAt(rowNumber, 8).toString());
		textDealerPrice.setText(modelSalesDetails.getValueAt(rowNumber, 9).toString());
		textTradePrice.setText(modelSalesDetails.getValueAt(rowNumber, 10).toString());
		textAmount.setText(modelSalesDetails.getValueAt(rowNumber, 11).toString());
		textRemarks.setText(modelSalesDetails.getValueAt(rowNumber, 12).toString());
		modelSalesDetails.removeRow(rowNumber);
	}
	public boolean checkValidationInsertData1(){
		if(!cmbClientId.txtSuggest.getText().trim().toString().isEmpty()){
			if(!textUserName.getText().trim().toString().isEmpty()){
				if(!textSalesNo.getText().trim().toString().isEmpty()){
					if(modelSalesDetails.getRowCount()!=0){
						if(!cmbPaymentProtocol.txtSuggest.getText().trim().toString().isEmpty()){
							if(!textReference.getText().trim().toString().isEmpty()){
								if(!textTotalAmount.getText().trim().toString().isEmpty()){
									if(!textPaidAmount.getText().trim().toString().isEmpty()){
										if(!textDiscountAmount.getText().trim().toString().isEmpty()){
											if(!textDue.getText().trim().toString().isEmpty()){
												return true;
											}
											else{
												JOptionPane.showMessageDialog(null, "insert Due Amount Please");
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "Insert Discount Please");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "Insert Paid Amount Please");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Insert Total Amount");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Insert Reference Please");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Select Paymentprotocal please");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Table is Empty!!!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Insert Sale No please");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Insert User Name Please");
			}

		}
		else{
			JOptionPane.showMessageDialog(null, "Select ClientId please");
		}
		return false;
	}
	public boolean checkValidationInsertData2(){
		if(!textUserName.getText().trim().toString().isEmpty()){
			if(!textSalesNo.getText().trim().toString().isEmpty()){
				if(modelSalesDetails.getRowCount()!=0){
					if(!cmbPaymentProtocol.txtSuggest.getText().trim().toString().isEmpty()){
						if(!textReference.getText().trim().toString().isEmpty()){
							if(!textTotalAmount.getText().trim().toString().isEmpty()){
								if(!textPaidAmount.getText().trim().toString().isEmpty()){
									if(!textDiscountAmount.getText().trim().toString().isEmpty()){
										if(!textDue.getText().trim().toString().isEmpty()){
											return true;
										}
										else{
											JOptionPane.showMessageDialog(null, "insert Due Amount Please");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "Insert Discount Please");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Insert Paid Amount Please");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Insert Total Amount");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Insert Reference Please");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Select Paymentprotocal please");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Table is Empty!!!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Insert Sale No please");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Insert User Name Please");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"Confiramtion",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean insertData(){
		String clientid="";
		String clientName="";
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);
			if(!cmbClientId.txtSuggest.getText().trim().toString().isEmpty()){
				StringTokenizer token=new StringTokenizer(cmbClientId.txtSuggest.getText().trim().toString(),"#");
				clientid=token.nextToken();
				clientName=token.nextToken();
			}
			String date=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());

			String sqlinfo="insert into tbsalesinfo(salesNo,clientid,clientName,dateDate,"
					+ "paymentProtocol,reference,totalAmount,paidAmount,discountAmounnt,dueAmount,"
					+ "userName,userip,entryTime)"
					+ "values('"+textSalesNo.getText().trim().toString()+"',"
					+ "'"+clientid+"',"
					+ "'"+clientName+"',"
					+ "'"+date+"',"
					+ "'"+cmbPaymentProtocol.txtSuggest.getText().trim().toString()+"',"
					+ "'"+textReference.getText().trim().toString()+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textPaidAmount.getText().trim().toString()+"',"
					+ "'"+textDiscountAmount.getText().trim().toString()+"',"
					+ "'"+textDue.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlinfo);
			for(int i=0; i<modelSalesDetails.getRowCount(); i++){
				String sqldetails="insert into tbsalesdetails(salesNo,productid,productName,catid,"
						+ "catName,subcatid,subcatName,unit,stockQty,salesQty,dealerPrice,tradePrice,"
						+ "amount,remarks,userName,userip,entryTime)"
						+ "values('"+textSalesNo.getText().trim().toString()+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 0)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 1)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 2)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 3)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 4)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 5)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 6)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 7)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 8)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 9)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 10)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 11)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 12)+"',"
						+ "'"+textUserName.getText().trim().toString()+"',"
						+ "'',now())";
				dbConneciton.sta.executeUpdate(sqldetails);
			}
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
			JOptionPane.showMessageDialog(null, e+"insertData()");
		}
		return false;
	}
	public boolean deleteData(){
		try {
			String sqlInfo="";
			String sqlDetails="";
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
		String clientid="";
		String clientName="";
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);
			
			String infoDelete="delete from tbsalesinfo where salesNo like "
					+ "'"+textSalesNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(infoDelete);
			String detailsDetele="delete from tbsalesdetails where salesNo like "
					+ "'"+textSalesNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(detailsDetele);
			
			if(!cmbClientId.txtSuggest.getText().trim().toString().isEmpty()){
				StringTokenizer token=new StringTokenizer(cmbClientId.txtSuggest.getText().trim().toString(),"#");
				clientid=token.nextToken();
				clientName=token.nextToken();
			}
			String date=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());

			String sqlinfo="insert into tbsalesinfo(salesNo,clientid,clientName,dateDate,"
					+ "paymentProtocol,reference,totalAmount,paidAmount,discountAmounnt,dueAmount,"
					+ "userName,userip,entryTime)"
					+ "values('"+textSalesNo.getText().trim().toString()+"',"
					+ "'"+clientid+"',"
					+ "'"+clientName+"',"
					+ "'"+date+"',"
					+ "'"+cmbPaymentProtocol.txtSuggest.getText().trim().toString()+"',"
					+ "'"+textReference.getText().trim().toString()+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textPaidAmount.getText().trim().toString()+"',"
					+ "'"+textDiscountAmount.getText().trim().toString()+"',"
					+ "'"+textDue.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlinfo);
			for(int i=0; i<modelSalesDetails.getRowCount(); i++){
				String sqldetails="insert into tbsalesdetails(salesNo,productid,productName,catid,"
						+ "catName,subcatid,subcatName,unit,stockQty,salesQty,dealerPrice,tradePrice,"
						+ "amount,remarks,userName,userip,entryTime)"
						+ "values('"+textSalesNo.getText().trim().toString()+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 0)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 1)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 2)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 3)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 4)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 5)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 6)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 7)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 8)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 9)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 10)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 11)+"',"
						+ "'"+modelSalesDetails.getValueAt(i, 12)+"',"
						+ "'"+textUserName.getText().trim().toString()+"',"
						+ "'',now())";
				dbConneciton.sta.executeUpdate(sqldetails);
			}
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
			JOptionPane.showMessageDialog(null, e+"insertData()");
		}
		return false;
	}*/
	public void tableSalesInfoDateLoad(){
		try {
			for(int i=modelSalesinfo.getRowCount()-1; i>=0; i--){
				modelSalesinfo.removeRow(i);
			}
			String sql="select salesNo,totalAmount,dueAmount,dateDate from tbsalesinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelSalesinfo.addRow(new Object[]{rs.getString("salesNo"),
						df.format(Double.parseDouble(rs.getString("totalAmount"))),
						df.format(Double.parseDouble(rs.getString("dueAmount"))),
						dateFormat.format(rs.getDate("dateDate"))});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableSalesInfoDateLoad()");
		}
	}
	public void searchData(String salesNo){
		try {
			for(int i=modelSalesDetails.getRowCount()-1; i>=0; i--){
				modelSalesDetails.removeRow(i);
			}
			String sql="select a.salesNo,a.clientid,a.clientName,a.dateDate,a.paymentProtocol,"
					+ "a.reference,a.totalAmount,a.paidAmount,a.discountAmounnt,a.dueAmount,"
					+ "a.userName,b.productid,b.productName,b.catid,b.catName,b.subcatid,b.subcatName,"
					+ "b.unit,b.stockQty,b.salesQty,b.dealerPrice,b.tradePrice,b.amount,b.remarks"
					+ " from tbsalesinfo a inner join tbsalesdetails b on a.salesNo=b.salesNo "
					+ "where a.salesNo='"+salesNo+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			int a=0;
			while(rs.next()){
				if(a==0){
					textSalesNo.setText(rs.getString("salesNo"));
					if(!(rs.getString("clientid")+rs.getString("clientName")).trim().toString().isEmpty()){
						cmbClientId.txtSuggest.setText(rs.getString("clientid")+"#"+rs.getString("clientName"));
						chkRegisterClient.setSelected(true);
					}
					else{
						cmbClientId.txtSuggest.setText("");
						chkUnregisterClient.setSelected(true);
					}
					dateDate.setDate(rs.getDate("dateDate"));
					cmbPaymentProtocol.txtSuggest.setText(rs.getString("paymentProtocol"));
					textReference.setText(rs.getString("reference"));
					textTotalAmount.setText(df.format(Double.parseDouble(rs.getString("totalAmount"))));
					textPaidAmount.setText(df.format(Double.parseDouble(rs.getString("paidAmount"))));
					textDiscountAmount.setText(df.format(Double.parseDouble(rs.getString("discountAmounnt"))));
					textDue.setText(df.format(Double.parseDouble(rs.getString("dueAmount"))));
					textUserName.setText(rs.getString("userName"));
				}
				a++;
				modelSalesDetails.addRow(new Object[]{rs.getString("productid"),rs.getString("productName"),
						rs.getString("catid"),rs.getString("catName"),rs.getString("subcatid"),
						rs.getString("subcatName"),rs.getString("unit"),rs.getString("stockQty"),
						rs.getString("salesQty"),rs.getString("dealerPrice"),rs.getString("tradePrice"),
						rs.getString("amount"),rs.getString("remarks")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchData()");
		}
	}
	public void findDataWithDate(){
		try {
			for(int i=modelSalesinfo.getRowCount()-1; i>=0; i--){
				modelSalesinfo.removeRow(i);
			}
			String dateFromdate=new SimpleDateFormat("yyyy-MM-dd").format(dateFromDatePC.getDate());
			String dateTodate=new SimpleDateFormat("yyyy-MM-dd").format(dateToDatePC.getDate());

			String sql="select salesNo,totalAmount,dueAmount,dateDate from tbsalesinfo "
					+ "where dateDate between '"+dateFromdate+"' and '"+dateTodate+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelSalesinfo.addRow(new Object[]{rs.getString("salesNo"),
						df.format(Double.parseDouble(rs.getString("totalAmount"))),
						df.format(Double.parseDouble(rs.getString("dueAmount"))),
						dateFormat.format(rs.getDate("dateDate"))});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"findDataWithDate()");
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
		panelNorthWestCenter.setOpaque(b);
		panelNorthWestWest.setOpaque(b);
		panelSouth.setOpaque(b);
		panelSouthCenter.setOpaque(b);
		panelSouthCenterCenter.setOpaque(b);
		panelSouthCenterWest.setOpaque(b);
		panelSouthNorth.setOpaque(b);
		dateDate.setOpaque(b);
		dateFromDatePC.setOpaque(b);
		dateToDatePC.setOpaque(b);
	}
	private void init() {
		setPreferredSize(new Dimension(1158, 714));
	}
	private void cmp() {	
		TitledBorder titleSales=BorderFactory.createTitledBorder("Sales Info");
		setBorder(titleSales);
		titleSales.setTitleJustification(TitledBorder.CENTER);
		titleSales.setTitleFont(new Font("clibari",Font.BOLD,22));
		titleSales.setTitleColor(Color.decode("#8B0000"));

		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		panelNorth();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth();		
	}
	private void panelNorth() {
		panelNorth.setPreferredSize(new Dimension( 0, 80));
		panelNorth.setBackground(Color.BLACK);
		panelNorth.setLayout(new BorderLayout());
		panelNorth.add(panelNorthWest,BorderLayout.WEST);
		panelNorthWest();
		panelNorth.add(panelNorthCenter,BorderLayout.CENTER);
		panelNorthCenter();
	}

	private void panelNorthWest() {
		panelNorthWest.setPreferredSize(new Dimension(650, 0));
		panelNorthWest.setLayout(new BorderLayout());
		panelNorthWest.add(panelNorthWestWest,BorderLayout.WEST);
		panelNorthWestWest();
		panelNorthWest.add(panelNorthWestCenter, BorderLayout.CENTER);
		panelNorthWestCenter();		
	}
	private void panelNorthWestWest() {
		panelNorthWestWest.setPreferredSize(new Dimension(280, 0));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthWestWest.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestWest.add(chkRegisterClient, cn);
		chkRegisterClient.setFont(font);
		btnGroup.add(chkRegisterClient);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestWest.add(chkUnregisterClient, cn);
		chkUnregisterClient.setFont(font);
		chkUnregisterClient.setSelected(true);
		btnGroup.add(chkUnregisterClient);
	}
	private void panelNorthWestCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthWestCenter.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestCenter.add(lblClientID, cn);
		lblClientID.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestCenter.add(cmbClientId.cmbSuggest, cn);
		cmbClientId.cmbSuggest.setEnabled(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestCenter.add(lblUserName, cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestCenter.add(textUserName, cn);
		textUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthWestCenter.add(lblExecutive, cn);

	}

	private void panelNorthCenter() {
		//panelNorthCenter.setBackground(Color.GREEN);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelNorthCenter.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(lblSalesNo, cn);
		lblSalesNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(textSalesNo, cn);
		textSalesNo.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(lblDate, cn);
		lblDate.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelNorthCenter.add(dateDate, cn);
		dateDate.setDate(new Date());
		dateDate.setDateFormatString("dd-MM-yyyy");
	}

	private void panelCenter() {
		panelCenter.setBackground(Color.BLUE);
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(panelCenterWest,BorderLayout.WEST);
		panelCenterWest();
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		panelCenterCenter();
	}

	private void panelCenterWest() {
		panelCenterWest.setPreferredSize(new Dimension(600, 0));
		//panelCenterWest.setBackground(Color.PINK);
		panelCenterWest.setLayout(new BorderLayout());
		panelCenterWest.add(panelCenterWestNorth, BorderLayout.NORTH);
		panelCenterWestNorth();
		panelCenterWest.add(panelCenterWestCenter, BorderLayout.CENTER);
		panelCenterWestCenter();
	}

	private void panelCenterWestCenter() {
		//panelCenterWestCenter.setBackground(Color.GRAY);

		FlowLayout flow=new FlowLayout();
		panelCenterWestCenter.setLayout(flow);
		flow.setVgap(0);
		flow.setHgap(15);

		panelCenterWestCenter.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnEdit);
		//btnEdit.setEnabled(false);
		btnEdit.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnDelete);
		btnDelete.setPreferredSize(new Dimension(100, 35));
		panelCenterWestCenter.add(btnReport);
		btnReport.setPreferredSize(new Dimension(100, 35));
	}

	private void panelCenterWestNorth() {
		panelCenterWestNorth.setPreferredSize(new Dimension(0, 230));
		//panelCenterWestNorth.setBackground(Color.GREEN);

		panelCenterWestNorth.setLayout(new BorderLayout());
		panelCenterWestNorth.add(panelCenterWestNorthWest,BorderLayout.WEST);
		panelCenterWestNorthWest();
		panelCenterWestNorth.add(panelCenterWestNorthCenter,BorderLayout.CENTER);
		panelCenterWestNorthCenter();
	}

	private void panelCenterWestNorthWest() {
		panelCenterWestNorthWest.setPreferredSize(new Dimension(300, 0));
		//panelCenterWestNorthWest.setBackground(Color.YELLOW);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthWest.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(2, 2, 2, 2);
		panelCenterWestNorthWest.add(lblProductID, cn);
		lblProductID.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(cmbProductId.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(lblCategory, cn);
		lblCategory.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(cmbCategory.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(lblSubcategory, cn);
		lblSubcategory.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(cmbSubcategory.cmbSuggest, cn);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(lblUnit, cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		//cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestNorthWest.add(textUnit, cn);
	}

	private void panelCenterWestNorthCenter() {
		//panelCenterWestNorthCenter.setBackground(Color.RED);
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthCenter.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthCenter.add(lblStockQty,cn);
		lblStockQty.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthCenter.add(textStockQty, cn);

		cn.gridx=0;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(lblSalesQty, cn);
		lblSalesQty.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(textSalesQty, cn);

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
		panelCenterWestNorthCenter.add(lblTradePrice, cn);
		lblTradePrice.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelCenterWestNorthCenter.add(textTradePrice, cn);
		textTradePrice.setEditable(false);

		cn.gridx=0;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(lblAmount, cn);
		lblAmount.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelCenterWestNorthCenter.add(textAmount, cn);
		textAmount.setEditable(false);

		cn.gridx=0;
		cn.gridy=5;
		panelCenterWestNorthCenter.add(lblRemarks, cn);
		lblRemarks.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		panelCenterWestNorthCenter.add(scrollRemarks, cn);
	}

	private void panelCenterCenter() {
		//panelCenterCenter.setBackground(Color.CYAN);
		TitledBorder titleSales=BorderFactory.createTitledBorder("Existing Sales Invoice");
		panelCenterCenter.setBorder(titleSales);
		titleSales.setTitleFont(new Font("clibari",Font.BOLD,12));
		titleSales.setTitleJustification(TitledBorder.CENTER);

		panelCenterCenter.setLayout(new BorderLayout());
		panelCenterCenter.add(panelCenterCenterNorth, BorderLayout.NORTH);
		panelCenterCenterNorth();
		panelCenterCenter.add(panelCenterCenterCenter, BorderLayout.CENTER);
		panelCenterCenterCenter();

	}

	private void panelCenterCenterNorth() {
		panelCenterCenterNorth.setPreferredSize(new Dimension(0, 60));
		//panelCenterCenterNorth.setBackground(Color.BLUE);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterNorth.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

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
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterCenterCenter.add(scroll);
		scroll.setPreferredSize(new Dimension(495, 170));
		tableSalesinfo.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(JLabel.RIGHT);
		tableSalesinfo.getColumnModel().getColumn(1).setCellRenderer(cellRender);
		tableSalesinfo.getColumnModel().getColumn(2).setCellRenderer(cellRender);
	}

	private void panelSouth() {
		panelSouth.setPreferredSize(new Dimension(0, 300));
		panelSouth.setBackground(Color.GREEN);
		panelSouth.setLayout(new BorderLayout());
		panelSouth.add(panelSouthNorth,BorderLayout.NORTH);
		panelSouthNorth();
		panelSouth.add(panelSouthCenter,BorderLayout.CENTER);
		panelSouthCenter();
	}

	private void panelSouthCenter() {
		//panelSouthCenter.setBackground(Color.orange);
		panelSouthCenter.setLayout(new BorderLayout());
		panelSouthCenter.add(panelSouthCenterWest, BorderLayout.WEST);
		panelSouthCenterWest();
		panelSouthCenter.add(panelSouthCenterCenter, BorderLayout.CENTER);
		panelSouthCenterCenter();
	}

	private void panelSouthCenterWest() {
		panelSouthCenterWest.setPreferredSize(new Dimension(890, 0));
		//panelSouthCenterWest.setBackground(Color.BLUE);
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelSouthCenterWest.setLayout(grid);
		Font font=new Font("clibari",Font.BOLD,12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblPaymentProtocol, cn);
		lblPaymentProtocol.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(cmbPaymentProtocol.cmbSuggest, cn);
		cmbPaymentProtocol.v.add("");
		cmbPaymentProtocol.v.add("Cash");
		cmbPaymentProtocol.v.add("Check");
		cmbPaymentProtocol.v.add("Devit Card");
		cmbPaymentProtocol.v.add("Credit Card");
		cmbPaymentProtocol.v.add("Bkash");

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblTotalAmount, cn);
		lblTotalAmount.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(textTotalAmount, cn);
		textTotalAmount.setEditable(false);

		cn.gridx=4;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblPaidAmoutn, cn);
		lblPaidAmoutn.setFont(font);

		cn.gridx=5;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(textPaidAmount, cn);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblReference, cn);
		lblReference.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(textReference, cn);

		cn.gridx=2;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblDiscountAmount, cn);
		lblDiscountAmount.setFont(font);

		cn.gridx=3;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(textDiscountAmount, cn);

		cn.gridx=4;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(lblDue, cn);
		lblDue.setFont(font);

		cn.gridx=5;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelSouthCenterWest.add(textDue, cn);
	}

	private void panelSouthCenterCenter() {

		//panelSouthCenterCenter.setBackground(Color.CYAN);
		FlowLayout flow=new FlowLayout();
		panelSouthCenterCenter.setLayout(flow);
		flow.setHgap(10);
		flow.setVgap(20);
		panelSouthCenterCenter.add(btnConfirm);
		btnConfirm.setEnabled(false);
		btnConfirm.setPreferredSize(new Dimension(100, 35));
		panelSouthCenterCenter.add(btnRefreshItemReport);
		btnRefreshItemReport.setPreferredSize(new Dimension(100, 35));
	}

	private void panelSouthNorth() {
		panelSouthNorth.setPreferredSize(new Dimension(0, 220));
		//panelSouthNorth.setBackground(Color.MAGENTA);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelSouthNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelSouthNorth.add(scrollTable,cn);
		scrollTable.setPreferredSize(new Dimension(1123, 217));
		tableSalesDetails.getTableHeader().setReorderingAllowed(false);
	}

}
