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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import org.omg.CORBA.OBJ_ADAPTER;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.sun.org.apache.bcel.internal.generic.IfInstruction;
import com.toedter.calendar.JDateChooser;

public class SalesReturnWork extends JPanel{

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

	JLabel lblSalesReturnNo=new JLabel("Sales Return No");
	JTextField textSalesReturnNo=new JTextField(14);
	JLabel lblUserName=new JLabel("User Name");
	JTextField textUserName=new JTextField(14);
	JLabel lblExecutive=new JLabel("Executive");

	JLabel lblDate=new JLabel("Date");
	JDateChooser dateDate=new JDateChooser();
	JLabel lblReturnDate=new JLabel("Return Date");
	JDateChooser dateReturnDate=new JDateChooser();

	JLabel lblSalesNo=new JLabel("Sales No");
	SuggestText cmbSalesNo=new SuggestText();
	JLabel lblProductId=new JLabel("Product ID");
	SuggestText cmbProductId=new SuggestText();
	JLabel lblCategory=new JLabel("Category");
	SuggestText cmbCategory=new SuggestText();
	JLabel lblSubcategory=new JLabel("Subcategory");
	SuggestText cmbSubcategory=new SuggestText();
	JLabel lblUnit=new JLabel("Unit");
	JTextField textUnit=new JTextField(15);

	JLabel lblSalesQty=new JLabel("Sales Qty");
	JTextField textSalesQty=new JTextField(16);
	JLabel lblAlreadyReceive=new JLabel("Already Receive");
	JTextField textAlreadyReceive=new JTextField();
	JLabel lblReceiveQty=new JLabel("Receive Qty");
	JTextField textReceiveQty=new JTextField();
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

	String col[]={"SalesNo","ProductId","ProductName","Unit","Sales Quantity","AlreadyReturn",
			"ReceiveQuantity","TradePrice","Amount","Remark"};
	Object row[][]={};
	DefaultTableModel modelSalesReturnDetails=new DefaultTableModel(row, col);
	JTable tableSalesReturnDetails=new JTable(modelSalesReturnDetails);
	JScrollPane scrollTableSalesReturnDetails=new JScrollPane(tableSalesReturnDetails,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel lblTotalAmount=new JLabel("Total Amount");
	JTextField textTotalAmount=new JTextField(35);

	ImageIcon iconConfirm=new ImageIcon("Images/confirm.png");
	JButton btnConfirm=new JButton("Confirm",iconConfirm);
	ImageIcon iconRefreshItemReport=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefreshItemReport=new JButton("Refresh",iconRefreshItemReport);

	JPanel panelCenterCenterNorth=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();
	JLabel lblFromDate=new JLabel("From Date");
	JLabel lblToDate=new JLabel("To Date");
	JDateChooser dateFromDatePC=new JDateChooser();
	JDateChooser dateToDatePC=new JDateChooser();
	JButton btnIcon=new JButton(new ImageIcon("Images/btnSubmit.png"));

	String colPC[]={"SalesReturnNo","TotalAmount","Date"}; 
	Object rowPC[][]={};
	DefaultTableModel modelSalesReturninfo=new DefaultTableModel(rowPC, colPC);
	JTable tableSalesReturninfo=new JTable(modelSalesReturninfo);
	JScrollPane scrollPC=new JScrollPane(tableSalesReturninfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	SessionBean sessionBean=new SessionBean();
	DecimalFormat decimalformat=new DecimalFormat("#0.00");
	SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");

	boolean isUpdate=false;

	public SalesReturnWork(SessionBean sessionBean){
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
		cmbSalesNo.cmbSuggest.setEnabled(b);
		cmbProductId.cmbSuggest.setEnabled(b);
		cmbSubcategory.cmbSuggest.setEnabled(b);
		cmbCategory.cmbSuggest.setEnabled(b);
		textUnit.setEditable(b);
		textSalesQty.setEditable(b);
		textAlreadyReceive.setEditable(b);
		textReceiveQty.setEditable(b);
		textTradePrice.setEditable(b);
		textAmount.setEditable(b);
		textRemarks.setEditable(b);
	}
	public void btnIni(boolean b){
		btnSubmit.setEnabled(b);
		btnEdit.setEnabled(!b);
	}
	public void txtClearSalesReturnDetails(){
		cmbSalesNo.txtSuggest.setText("");
		cmbProductId.txtSuggest.setText("");
		cmbProductId.v.clear();
		cmbCategory.txtSuggest.setText("");
		cmbSubcategory.txtSuggest.setText("");
		textUnit.setText("");
		textSalesQty.setText("");
		textAlreadyReceive.setText("");
		textReceiveQty.setText("");
		textTradePrice.setText("");
		textAmount.setText("");
		textRemarks.setText("");
	}
	public void refreshWorkSalesReturnDetails(){
		txtClearSalesReturnDetails();
		cmbSalesNoDataLoad();
		btnIni(true);
		editable(true);
	}
	public void txtClearSalesReturnInfo(){
		cmbSalesNo.txtSuggest.setText("");
		cmbSalesNo.v.clear();
		textSalesReturnNo.setText("");
		textUserName.setText("");
		textUserName.setText(sessionBean.getUserName());
		dateDate.setDate(new Date());
		dateReturnDate.setDate(new Date());
		textTotalAmount.setText("");
		for(int i=modelSalesReturnDetails.getRowCount()-1; i>=0; i--){
			modelSalesReturnDetails.removeRow(i);
		}
	}
	public void refreshWorkSalesReturnInfo(){
		refreshWorkSalesReturnDetails();
		txtClearSalesReturnInfo();
		autoId();
		tableSalesReturninfoDataLoad();
		cmbSalesNoDataLoad();
		isUpdate=false;
		btnConfirm.setEnabled(false);
		btnIni(true);
	}
	public void btnAction(){
		cmbSalesNo.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbSalesNo.txtSuggest.getText().trim().toString().isEmpty()){
					String salesNo=cmbSalesNo.txtSuggest.getText().trim().toString();
					cmbProductId.txtSuggest.setText("");
					cmbProductId.v.clear();
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					textUnit.setText("");
					textSalesQty.setText("");
					textAlreadyReceive.setText("");
					textReceiveQty.setText("");
					textAmount.setText("");
					textTradePrice.setText("");
					cmbProductidDataLoad(salesNo);
				}
				else{
					cmbProductId.txtSuggest.setText("");
					cmbProductId.v.clear();
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					textUnit.setText("");
					textSalesQty.setText("");
					textAlreadyReceive.setText("");
					textReceiveQty.setText("");
					textAmount.setText("");
					textTradePrice.setText("");
				}
			}
		});
		cmbProductId.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
					String productid=token.nextToken();
					textSalesQty.setText("");
					textAlreadyReceive.setText("");
					textReceiveQty.setText("");
					textAmount.setText("");
					allDataLoadUnderProductid(productid);
				}
				else{
					cmbCategory.txtSuggest.setText("");
					cmbSubcategory.txtSuggest.setText("");
					textUnit.setText("");
					textSalesQty.setText("");
					textAlreadyReceive.setText("");
					textReceiveQty.setText("");
					textAmount.setText("");
					textTradePrice.setText("");
				}
			}
		});
		textReceiveQty.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				amountCalculation();
			}
			public void keyPressed(KeyEvent e) {}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationSalesReturnDetails()){
					if(doubleEntryCheck()){
						tableSalesReturnDetailsDataLoad();
						totalAmountCalculation();
						refreshWorkSalesReturnDetails();
						btnConfirm.setEnabled(true);
					}
				}
			}
		});
		tableSalesReturnDetails.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				searchDataSalesReturnDetails(tableSalesReturnDetails.getSelectedRow());
				if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
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
				if(checkValidationSalesReturnInfo()){
					if(isUpdate){
						if(checkConfirmation("do you want to update data?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "Data updated successfully.");
									refreshWorkSalesReturnInfo();
								}
							}
						}
					}
					else{
						if(checkConfirmation("Sure to save?")){
							if(insertData()){
								JOptionPane.showMessageDialog(null, "Data saved successfully.");
								refreshWorkSalesReturnInfo();
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
		tableSalesReturninfo.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				String salesReturnNo=modelSalesReturninfo.getValueAt(tableSalesReturninfo.getSelectedRow(), 0).toString();
				searchDataLoad(salesReturnNo);
				textTotalAmount.setFont(new Font("Carlibri", Font.BOLD, 18));
				textTotalAmount.setForeground(Color.RED);
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkSalesReturnDetails();
			}
		});
		btnRefreshItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkSalesReturnInfo();
			}
		});
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDataLoadwithDate();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidationSalesReturnDetails()){
					if(checkValidationSalesReturnInfo()){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "data deletede successfully.");
							refreshWorkSalesReturnInfo();
						}
					}
				}
			}
		});
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(salesReturnNo,locate('-',salesReturnNo)+1,"
					+ "length(salesReturnNo)-locate('-',salesReturnNo))as UNSIGNED)),0)+1 as id "
					+ "from tbsalesreturninfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				textSalesReturnNo.setText("SalesReturnNo-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoId()");
		}
	}
	public void cmbSalesNoDataLoad(){
		try {
			cmbSalesNo.v.clear();
			cmbSalesNo.v.add("");
			String sql="select salesNo from tbsalesinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSalesNo.v.add(rs.getString("salesNo"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSalesNoDataLoad()");
		}
	}
	public void cmbProductidDataLoad(String salesNo){
		try {
			cmbProductId.v.clear();
			cmbProductId.v.add("");
			String sql="select productid,productName from tbsalesdetails where salesNo='"+salesNo+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbProductId.v.add(rs.getString("productid")+"#"+rs.getString("productName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbProductidDataLoad()");
		}
	}
	public void allDataLoadUnderProductid(String productid){
		try {
			String sql="select categoryId,categoryName,subCategoryId,subCategoryName,unit,"
					+ "tradePrice from tbproductinfo where productId='"+productid+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbCategory.txtSuggest.setText(rs.getString("categoryId")+"#"+rs.getString("categoryName"));
				if(!(rs.getString("subCategoryId")+rs.getString("subCategoryName")).trim().toString().isEmpty()){
					cmbSubcategory.txtSuggest.setText(rs.getString("subCategoryId")+"#"+
							rs.getString("subCategoryName"));
				}
				else{
					cmbCategory.txtSuggest.setText("");
				}
				textUnit.setText(rs.getString("unit"));
				textTradePrice.setText(rs.getString("tradePrice"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"allDataLoadUnderProductid()");
		}
	}
	public void amountCalculation(){
		double receiveQty,tradePrice,amount;
		receiveQty=Double.parseDouble(textReceiveQty.getText().trim().toString().isEmpty()?"0":
			textReceiveQty.getText().trim().toString());
		tradePrice=Double.parseDouble(textTradePrice.getText().trim().toString().isEmpty()?"0":
			textTradePrice.getText().trim().toString());
		amount=receiveQty*tradePrice;
		textAmount.setText(decimalformat.format(amount));
	}
	public boolean checkValidationSalesReturnDetails(){
		if(!cmbSalesNo.txtSuggest.getText().trim().toString().isEmpty()){
			if(!cmbProductId.txtSuggest.getText().trim().toString().isEmpty()){
				if(!textSalesQty.getText().trim().toString().isEmpty()){
					if(!textAlreadyReceive.getText().trim().toString().isEmpty()){
						if(!textReceiveQty.getText().trim().toString().isEmpty()){
							return true;
						}
						else{
							JOptionPane.showMessageDialog(null, "Insert ReceiveQty Please");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Insert Already Receive Please");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Insert SalesQty");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "select ProductId Please");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select SalesNo Please");
		}
		return false;
	}
	public boolean doubleEntryCheck(){
		StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
		String productid=token.nextToken();
		String salesNo=cmbSalesNo.txtSuggest.getText().toString().trim();
		for(int i=0; i<modelSalesReturnDetails.getRowCount(); i++){
			
			if(modelSalesReturnDetails.getValueAt(i, 0).toString().equals(salesNo) && 
					modelSalesReturnDetails.getValueAt(i, 1).toString().equals(productid)){
				JOptionPane.showMessageDialog(null, "This Product Already Exist!");
				return false;
			}
		}
		return true;
	}
	public void tableSalesReturnDetailsDataLoad(){
		StringTokenizer token=new StringTokenizer(cmbProductId.txtSuggest.getText().trim(),"#");
		String productid=token.nextToken();
		String productName=token.nextToken();

		modelSalesReturnDetails.addRow(new Object[]{cmbSalesNo.txtSuggest.getText().trim().toString(),
				productid,productName,textUnit.getText().trim().toString(),
				textSalesQty.getText().trim().toString(),textAlreadyReceive.getText().trim().toString(),
				textReceiveQty.getText().trim().toString(),textTradePrice.getText().trim().toString(),
				textAmount.getText().trim().toString(),textRemarks.getText().trim().toString()});
	}
	public void totalAmountCalculation(){
		double sum=0.0,amount;
		for(int i=0; i<modelSalesReturnDetails.getRowCount(); i++){
			amount=Double.parseDouble(modelSalesReturnDetails.getValueAt(i, 7).toString());
			sum=sum+amount;
		}
		textTotalAmount.setText(decimalformat.format(sum)); 
		textTotalAmount.setFont(new Font("Carlibri", Font.BOLD, 18));
		textTotalAmount.setForeground(Color.RED);
	}
	public void searchDataSalesReturnDetails(int row){
		cmbSalesNo.txtSuggest.setText(modelSalesReturnDetails.getValueAt(row, 0).toString());
		cmbProductId.txtSuggest.setText(modelSalesReturnDetails.getValueAt(row, 1).toString()+"#"+
				modelSalesReturnDetails.getValueAt(row, 2).toString());
		textSalesQty.setText(modelSalesReturnDetails.getValueAt(row, 4).toString());
		textAlreadyReceive.setText(modelSalesReturnDetails.getValueAt(row, 5).toString());
		textReceiveQty.setText(modelSalesReturnDetails.getValueAt(row, 6).toString());
		textAmount.setText(modelSalesReturnDetails.getValueAt(row, 8).toString());
		textRemarks.setText(modelSalesReturnDetails.getValueAt(row, 9).toString());
		modelSalesReturnDetails.removeRow(row);
	}
	public boolean checkValidationSalesReturnInfo(){
		if(!textSalesReturnNo.getText().trim().toString().isEmpty()){
			if(!textUserName.getText().trim().toString().isEmpty()){
				if(modelSalesReturnDetails.getRowCount()!=0){
					if(!textTotalAmount.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "Insert Total Amount Please");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Table is Empty!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Insert User Name Please");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Insert SalesNo Please");
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
			String sqlInfo="insert into tbsalesreturninfo(salesReturnNo,curDate,returnDate,"
					+ "totalAmount,userName,userip,entryTime)"
					+ "values('"+textSalesReturnNo.getText().trim().toString()+"',"
					+ "'"+datecurDate+"',"
					+ "'"+returnDate+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlInfo);

			for(int i=0; i<modelSalesReturnDetails.getRowCount(); i++){
				String sqlDetails="insert into tbsalesreturndetails(salesReturnNo,salesNo,productid,productName,unit,"
						+ "salesQty,alreadyReturn,receiveQty,tradePrice,amount,remarks,userName,userip,entryTime)"
						+ "values('"+textSalesReturnNo.getText().trim().toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 0).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 1).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 2).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 3).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 4).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 5).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 6).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 7).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 8).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 9).toString()+"',"
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
			String sqlInfo="delete from tbsalesreturninfo where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
			String sqlDetails="delete from tbsalesreturndetails where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
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
	/*public boolean deleteData(){
		try {
			dbConneciton.connection();
			String infoDelete="delete from tbsalesreturninfo where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(infoDelete);
			String detailsDelete="delete from tbsalesreturndetails where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(detailsDelete);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"deleteData()");
		}
		return false;
	}*/
	/*public boolean updateWork(){
		try {
			dbConneciton.connection();
			dbConneciton.con.setAutoCommit(false);

			String infoDelete="delete from tbsalesreturninfo where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(infoDelete);
			String detailsDelete="delete from tbsalesreturndetails where salesReturnNo like "
					+ "'"+textSalesReturnNo.getText().toString().trim()+"'";
			dbConneciton.sta.executeUpdate(detailsDelete);
			
			String datecurDate=new SimpleDateFormat("yyyy-MM-dd").format(dateDate.getDate());
			String returnDate=new SimpleDateFormat("yyyy-MM-dd").format(dateReturnDate.getDate());
			String sqlInfo="insert into tbsalesreturninfo(salesReturnNo,curDate,returnDate,"
					+ "totalAmount,userName,userip,entryTime)"
					+ "values('"+textSalesReturnNo.getText().trim().toString()+"',"
					+ "'"+datecurDate+"',"
					+ "'"+returnDate+"',"
					+ "'"+textTotalAmount.getText().trim().toString()+"',"
					+ "'"+textUserName.getText().trim().toString()+"',"
					+ "'',now())";
			dbConneciton.sta.executeUpdate(sqlInfo);

			for(int i=0; i<modelSalesReturnDetails.getRowCount(); i++){
				String sqlDetails="insert into tbsalesreturndetails(salesReturnNo,salesNo,productid,productName,unit,"
						+ "salesQty,alreadyReturn,receiveQty,tradePrice,amount,remarks,userName,userip,entryTime)"
						+ "values('"+textSalesReturnNo.getText().trim().toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 0).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 1).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 2).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 3).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 4).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 5).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 6).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 7).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 8).toString()+"',"
						+ "'"+modelSalesReturnDetails.getValueAt(i, 9).toString()+"',"
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
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, e+"insertData()");
		}
		return false;
	}*/
	public void tableSalesReturninfoDataLoad(){
		try {
			for(int i=modelSalesReturninfo.getRowCount()-1; i>=0; i--){
				modelSalesReturninfo.removeRow(i);
			}
			String sql="select salesReturnNo,returnDate,totalAmount from tbsalesreturninfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelSalesReturninfo.addRow(new Object[]{rs.getString("salesReturnNo"),
						decimalformat.format((Double.parseDouble(rs.getString("totalAmount")))),
						dateformat.format(rs.getDate("returnDate"))});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableSalesReturninfoDataLoad()");
		}
	}
	public void searchDataLoad(String salesReturnNo){
		try {
			for(int i=modelSalesReturnDetails.getRowCount()-1; i>=0; i--){
				modelSalesReturnDetails.removeRow(i);
			}	
			String sql="select a.salesReturnNo,a.curDate,a.returnDate,a.totalAmount,"
					+ "a.userName,b.salesNo,b.productid,b.productName,b.unit,b.salesQty,b.alreadyReturn,"
					+ "b.receiveQty,b.tradePrice,b.amount,b.remarks from tbsalesreturninfo a "
					+ "inner join tbsalesreturndetails b on a.salesReturnNo=b.salesReturnNo "
					+ "where a.salesReturnNo='"+salesReturnNo+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			int a=0;
			while(rs.next()){
				if(a==0){
					textSalesReturnNo.setText(rs.getString("salesReturnNo"));
					dateDate.setDate(rs.getDate("curDate"));
					dateReturnDate.setDate(rs.getDate("returnDate"));
					textTotalAmount.setText(decimalformat.format((Double.parseDouble(rs.getString("totalAmount")))));
					textUserName.setText(rs.getString("userName"));
				}
				a++;
				modelSalesReturnDetails.addRow(new Object[]{rs.getString("salesNo"),rs.getString("productid"),rs.getString("productName"),
						rs.getString("unit"),rs.getString("salesQty"),rs.getString("alreadyReturn"),
						rs.getString("receiveQty"),rs.getString("tradePrice"),rs.getString("amount"),
						rs.getString("remarks")});
			}

		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"searchDataLoad()");
		}
	}
	public void findDataLoadwithDate(){
		try {
			for(int i=modelSalesReturninfo.getRowCount()-1; i>=0; i--){
				modelSalesReturninfo.removeRow(i);
			}
			String dateFromDate=new SimpleDateFormat("yyyy-MM-dd").format(dateFromDatePC.getDate());
			String dateToDate=new SimpleDateFormat("yyyy-MM-dd").format(dateToDatePC.getDate());

			String sql="select salesReturnNo,returnDate,totalAmount from tbsalesreturninfo "
					+ "where returnDate between '"+dateFromDate+"' and '"+dateToDate+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				modelSalesReturninfo.addRow(new Object[]{rs.getString("salesReturnNo"),
						decimalformat.format(Double.parseDouble(rs.getString("totalAmount"))),
						dateformat.format(rs.getDate("returnDate"))});
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"findDataLoadwithDate()");
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
		dateDate.setOpaque(b);
		dateReturnDate.setOpaque(b);
		dateFromDatePC.setOpaque(b);
		dateToDatePC.setOpaque(b);
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
		//panelNorth.setBackground(Color.BLACK);
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
		cn.insets=new Insets(5, 1, 5, 1);
		panelNorthWest.add(lblSalesReturnNo, cn);
		lblSalesReturnNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelNorthWest.add(textSalesReturnNo, cn);
		textSalesReturnNo.setEditable(false);

		cn.gridx=2;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelNorthWest.add(lblUserName, cn);
		lblUserName.setFont(font);

		cn.gridx=3;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 1);
		panelNorthWest.add(textUserName, cn);
		textUserName.setEditable(false);

		cn.gridx=4;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 1, 5, 10);
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
		TitledBorder titleSalesReturnInfo=BorderFactory.createTitledBorder("Existing Sales Return Invoice");
		panelCenterCenter.setBorder(titleSalesReturnInfo);
		titleSalesReturnInfo.setTitleJustification(TitledBorder.CENTER);
		titleSalesReturnInfo.setTitleFont(font);

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
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterCenterCenter.add(scrollPC, cn);
		scrollPC.setPreferredSize(new Dimension(466, 200));
		tableSalesReturninfo.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer cellRender=new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(JLabel.RIGHT);
		tableSalesReturninfo.getColumnModel().getColumn(1).setCellRenderer(cellRender);
	}
	private void panelCenterWest() {
		panelCenterWest.setPreferredSize(new Dimension(630, 0));
		panelCenterWest.setLayout(new BorderLayout());
		panelCenterWest.add(panelCenterWestNorth,BorderLayout.NORTH);
		panelCenterWestNorth();
		panelCenterWest.add(panelCenterWestCenter,BorderLayout.CENTER);
		panelCenterWestCenter();
	}
	private void panelCenterWestCenter() {
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
		panelCenterWestNorthWest.setPreferredSize(new Dimension(270, 0));
		//panelCenterWestNorthWest.setBackground(Color.DARK_GRAY);

		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestNorthWest.setLayout(grid);
		Font font=new Font("clibari", Font.BOLD, 12);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenterWestNorthWest.add(lblSalesNo,cn);
		lblSalesNo.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthWest.add(cmbSalesNo.cmbSuggest, cn);

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
		cn.insets=new Insets(5, 5, 25, 5);
		panelCenterWestNorthWest.add(lblUnit, cn);
		lblUnit.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		cn.insets=new Insets(5, 5, 25, 5);
		panelCenterWestNorthWest.add(textUnit, cn);
		textUnit.setEditable(false);

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
		panelCenterWestNorthCenter.add(lblSalesQty,cn);
		lblSalesQty.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelCenterWestNorthCenter.add(textSalesQty, cn);

		cn.gridx=0;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(lblAlreadyReceive, cn);
		lblAlreadyReceive.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelCenterWestNorthCenter.add(textAlreadyReceive, cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(lblReceiveQty, cn);
		lblReceiveQty.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelCenterWestNorthCenter.add(textReceiveQty, cn);
		//textReceiveQty.setEditable(false);

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
		btnConfirm.setEnabled(false);
		btnConfirm.setPreferredSize(new Dimension(100, 35));
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
		panelSouthNorth.add(scrollTableSalesReturnDetails,cn);
		scrollTableSalesReturnDetails.setPreferredSize(new Dimension(1124, 217));
		tableSalesReturnDetails.getTableHeader().setReorderingAllowed(false);
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));

		TitledBorder titleItemReceipt=BorderFactory.createTitledBorder("Sales Return Info");
		setBorder(titleItemReceipt);
		titleItemReceipt.setTitleJustification(TitledBorder.CENTER);
		titleItemReceipt.setTitleColor(Color.decode("#8B0000"));
		titleItemReceipt.setTitleFont(new Font("clibari", Font.BOLD, 22));

		BorderLayout border=new BorderLayout();
		setLayout(border);

	}


}
