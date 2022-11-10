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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.admin.dbConneciton;
import com.example.Admin.ResetPassword;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TaskReportWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelCenterWest=new JPanel();
	JPanel panelCenterWestCenter=new JPanel();
	JPanel panelCenterWestSouth=new JPanel();
	JPanel panelCenterCenter=new JPanel();
	JPanel panelCenterCenterCenter=new JPanel();
	JPanel panelCenterCenterSouth=new JPanel();

	JLabel lblReportTypewest=new JLabel("ReportType");
	JLabel lblFromDatewest=new JLabel("FromDate");
	JLabel lblToDatewest=new JLabel("ToDate");
	JLabel lblReportTypeCenter=new JLabel("ReportType");
	JLabel lblCategory=new JLabel("Category");
	JLabel lblsubcategoryName=new JLabel("Subcategory");
	JLabel lblProductid=new JLabel("Productid");
	JLabel lblFromDate=new JLabel("FromDate");
	JLabel lblToDate=new JLabel("ToDate");
	JLabel lblTransactionNo=new JLabel("TransactionNo");

	SuggestText cmbReportTypewest=new SuggestText();
	JDateChooser dateFromDatewest=new JDateChooser();
	JDateChooser dateToDatewest=new JDateChooser();
	SuggestText cmbReportType=new SuggestText();
	SuggestText cmbCategory=new SuggestText();
	JCheckBox chkCategory=new JCheckBox("All");
	SuggestText cmbSubcategory=new SuggestText();
	JCheckBox chkSubcategory=new JCheckBox("All");
	SuggestText cmbProductid=new SuggestText();
	JCheckBox chkProduct=new JCheckBox("All");
	JDateChooser dateFromDate=new JDateChooser();
	JDateChooser dateToDate=new JDateChooser();
	SuggestText cmbTransactionNo=new SuggestText();

	ImageIcon iconPreview=new ImageIcon("Images/Reset.png");
	ImageIcon iconRefresh=new ImageIcon("Images/refresh1.png");
	JButton btnPreviewWest=new JButton("Preview",iconPreview);
	JButton btnPreview=new JButton("Preview",iconPreview);
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	JButton btnRefreshWest=new JButton("Refresh",iconRefresh);

	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dDateFormate=new SimpleDateFormat("dd-MM-yyyy");

	public TaskReportWork() {
		init();
		cmp();
		btnAction();
	}	
	public void refreshWork(){
		cmbReportType.txtSuggest.setText("");
		lblTransactionNo.setVisible(false);
		lblCategory.setVisible(false);
		lblsubcategoryName.setVisible(false);
		lblProductid.setVisible(false);
		lblFromDate.setVisible(false);
		lblToDate.setVisible(false);
		cmbTransactionNo.cmbSuggest.setVisible(false);
		cmbCategory.cmbSuggest.setVisible(false);
		cmbSubcategory.cmbSuggest.setVisible(false);
		cmbProductid.cmbSuggest.setVisible(false);
		chkCategory.setVisible(false);
		chkSubcategory.setVisible(false);
		chkProduct.setVisible(false);
		dateFromDate.setVisible(false);
		dateToDate.setVisible(false);
		cmbTransactionNo.txtSuggest.setText("");
		cmbTransactionNo.v.clear();
	}
	public void refreshWorkWest(){
		dateFromDatewest.setDate(new Date());
		dateToDatewest.setDate(new Date());
		cmbReportTypewest.txtSuggest.setText("");
	}
	public void btnAction(){
		cmbReportType.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbReportType.txtSuggest.getText().trim().toString().isEmpty()){
					taskReportTrueFalse();
					if(!cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ProfitStatement")){
						cmbTransactionNoDataLoad();
					}
				}
				else{
					lblTransactionNo.setVisible(false);
					lblCategory.setVisible(false);
					lblsubcategoryName.setVisible(false);
					lblProductid.setVisible(false);
					lblFromDate.setVisible(false);
					lblToDate.setVisible(false);
					cmbTransactionNo.cmbSuggest.setVisible(false);
					cmbCategory.cmbSuggest.setVisible(false);
					cmbSubcategory.cmbSuggest.setVisible(false);
					cmbProductid.cmbSuggest.setVisible(false);
					chkCategory.setVisible(false);
					chkSubcategory.setVisible(false);
					chkProduct.setVisible(false);
					dateFromDate.setVisible(false);
					dateToDate.setVisible(false);
					cmbReportType.txtSuggest.setText("");
					cmbTransactionNo.v.clear();
				}
			}
		});
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query=getReportQuery();
				if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("ItemReceipt")){
					showReport("TaskReport/ItemReceiptReports.jrxml", query,null);
				}
				else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")){
					showReport("TaskReport/ReceiptReturnReport.jrxml", query,null);
				}
				else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")){
					showReport("TaskReport/SalesReport.jrxml", query,null);
				}
				else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")){
					showReport("TaskReport/SalesReturnReport.jrxml", query,null);
				}
				else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
					showReport("TaskReport/WastageorBrokenReport.jrxml", query,null);
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWork();
			}
		});
		btnPreviewWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbReportTypewest.txtSuggest.getText().trim().toString().isEmpty()){
					String query=getReportQueryDateBetween();
					if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ItemReceipt")){
						HashMap<String, String>hmap=new HashMap<String, String>();
						hmap.put("FromDate", dDateFormate.format(dateFromDatewest.getDate()));
						hmap.put("ToDate", dDateFormate.format(dateToDatewest.getDate()));
						showReport("DateBetweenReport/ItemReceiptReportsDateBetween.jrxml", query,hmap);
					}
					if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")){
						HashMap<String, String>hmap=new HashMap<String, String>();
						hmap.put("FromDate", dateFormat.format(dateFromDatewest.getDate()));
						hmap.put("ToDate", dateFormat.format(dateToDatewest.getDate()));

						showReport("DateBetweenReport/ReceiptReturnReportDateBetween.jrxml", query,hmap);
					}
					else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")){
						HashMap<String, String>hmap=new HashMap<String, String>();
						hmap.put("FromDate", dateFormat.format(dateFromDatewest.getDate()));
						hmap.put("ToDate", dateFormat.format(dateToDatewest.getDate()));

						showReport("DateBetweenReport/SalesReportDateBetween.jrxml", query,hmap);
					}
					else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")){
						HashMap<String, String>hmap=new HashMap<String, String>();
						hmap.put("FromDate", dateFormat.format(dateFromDatewest.getDate()));
						hmap.put("ToDate", dateFormat.format(dateToDatewest.getDate()));

						showReport("DateBetweenReport/SalesReturnReportDateBetween.jrxml", query,hmap);
					}
					else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
						HashMap<String, String>hmap=new HashMap<String, String>();
						hmap.put("FromDate", dateFormat.format(dateFromDatewest.getDate()));
						hmap.put("ToDate", dateFormat.format(dateToDatewest.getDate()));

						showReport("DateBetweenReport/WastageorBrokenDateBetween.jrxml", query,hmap);
					}
					else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ProfitStatement")){
						//showReport("DateBetweenReport/SalesReturnReportDateBetween.jrxml", query);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Select Report Type Please.");
				}
			}
		});
		btnRefreshWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWorkWest();
			}
		});
	}
	public void showReport(String jrxml, String query, HashMap<String, String>hMap){
		try {
			dbConneciton.connection();
			JasperDesign jd=JRXmlLoader.load(jrxml);
			JRDesignQuery jrq=new JRDesignQuery();
			jrq.setText(query);
			jd.setQuery(jrq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, hMap, dbConneciton.con);
			JasperViewer.viewReport(jp, false);
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"showReport()");
		}
	}
	public String getReportQueryDateBetween(){
		String query="";
		if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ItemReceipt")){
			query="select b.productName,b.unit,ifnull(sum(b.invoiceQty),0)invoiceQty,"
					+ "ifnull(sum(b.receiveQty),0)receiveQty,ifnull(sum(b.shortOverQty),0)shortOverQty,"
					+ "avg(b.dealerPrice)dealerPrice,ifnull(sum(b.Amount),0)Amount from tbItemReceiptInfo a "
					+ "inner join tbItemReceiptDetails b on a.invoiceNo=b.invoiceNo where a.invoiceDate "
					+ "between '"+dateFormat.format(dateFromDatewest.getDate())+"' "
					+ "and '"+dateFormat.format(dateToDatewest.getDate())+"' "
					+ "group by b.productName,b.unit order by productName";
		}
		if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")){
			query="select b.productName,b.unit,ifnull(sum(b.returnQty),0)returnQty,avg(b.dealerPrice)dealerPrice,"
					+ "ifnull(sum(b.amount),0)amount,b.remarks from tbreceiptreturninfo a inner join "
					+ "tbreceiptreturndetails b on a.returnNo=b.returnNo where a.returnDate "
					+ "between '"+dateFormat.format(dateFromDatewest.getDate())+"' and "
					+ "'"+dateFormat.format(dateToDatewest.getDate())+"' group by b.productName,b.unit "
					+ "order by productName";
		}
		else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")){
			query="select b.productName,b.unit,ifnull(sum(b.salesQty),0)salesQty,avg(b.dealerPrice)dealerPrice,"
					+ "avg(b.tradePrice)tradePrice,ifnull(sum(b.amount),0)Amount,b.remarks from tbsalesinfo a "
					+ "inner join tbsalesdetails b on a.salesNo=b.salesNo where a.dateDate between "
					+ "'"+dateFormat.format(dateFromDatewest.getDate())+"' and "
					+ "'"+dateFormat.format(dateToDatewest.getDate())+"' group "
					+ "by b.productName,b.unit order by productName";
		}
		else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")){
			query="select b.productName,b.unit,ifnull(sum(b.salesQty),0)salesQty,"
					+ "ifnull(sum(b.alreadyReturn),0)AlreadyReturn,ifnull(sum(b.receiveQty),0)ReturnQty,"
					+ "avg(b.tradePrice)TradePrice,ifnull(sum(b.amount),0)Amount,b.remarks from tbsalesreturninfo a "
					+ "inner join tbsalesreturndetails b on a.salesReturnNo=b.salesReturnNo where a.returnDate"
					+ " between '"+dateFormat.format(dateFromDatewest.getDate())+"' and "
					+ "'"+dateFormat.format(dateToDatewest.getDate())+"' group "
					+ "by b.productName,b.unit order by productName";
		}
		else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
			query="select catName,subCatName,productName,productType,"
					+ "ifnull(sum(WastageorBroken),0)WastageorBrokenQty,avg(dealerPrice)dealerPrice,"
					+ "ifnull(sum(amount),0)Amount from tbwastage where dateDate "
					+ "between '"+dateFormat.format(dateFromDatewest.getDate())+"' and "
					+ "'"+dateFormat.format(dateToDatewest.getDate())+"' group "
					+ "by productName order by catName,subCatName,productName";
		}
		else if(cmbReportTypewest.txtSuggest.getText().trim().toString().equalsIgnoreCase("ProfitStatement")){
			query="";
		}
		return query;
	}
	public String getReportQuery(){
		String query="";
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ItemReceipt")){
			String invoiceNo=cmbTransactionNo.txtSuggest.getText().trim().toString();
			query="select a.invoiceNo,a.userName,a.invoiceDate,a.entryTime,b.productName,b.unit,"
					+ "b.invoiceQty,b.receiveQty,b.shortOverQty,b.dealerPrice,b.amount from "
					+ "tbitemreceiptinfo as a inner join tbitemreceiptdetails as b "
					+ "on a.invoiceNo=b.invoiceNo where a.invoiceNo like '"+invoiceNo+"'";
		}
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")){
			String returnNo=cmbTransactionNo.txtSuggest.getText().trim().toString();
			query="select a.returnNo,a.returnDate,b.productName,b.supplierName,b.dealerPrice,b.returnQty,"
					+ "b.amount,b.remarks from tbreceiptreturninfo as a inner join tbreceiptreturndetails "
					+ "as b on a.returnNo=b.returnNo where a.returnNo like '"+returnNo+"'";
		}
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")){
			String salesNo=cmbTransactionNo.txtSuggest.getText().trim().toString();
			query="select a.salesNo,a.clientName,a.dateDate,a.entryTime,a.userName,b.productName,"
					+ "b.unit,b.salesQty,b.dealerPrice,b.tradePrice,b.amount,b.remarks from tbsalesinfo"
					+ " as a inner join tbsalesdetails as b on a.salesNo=b.salesNo"
					+ " where a.salesNo like '"+salesNo+"'";
		}
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")){
			String saleReturnNo=cmbTransactionNo.txtSuggest.getText().trim().toString();
			query="select a.salesReturnNo,a.returnDate,a.entryTime,a.userName,b.productName,"
					+ "b.unit,b.salesQty,b.alreadyReturn,b.receiveQty,b.tradePrice,b.amount,"
					+ "b.remarks from tbsalesreturninfo as a inner join tbsalesreturndetails "
					+ "as b on a.salesReturnNo=b.salesReturnNo "
					+ "where a.salesReturnNo='"+saleReturnNo+"'";
		}
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
			String wastageNo=cmbTransactionNo.txtSuggest.getText().trim().toString();
			query="select catName,subCatName,productName productType,WastageorBroken,dealerPrice,amount"
					+ " from tbwastage where wastageNo like '"+wastageNo+"' order by catName,subCatName,"
					+ "productName";
		}
		return query;
	}
	public void taskReportTrueFalse(){
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ItemReceipt")||
				cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")||
				cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")||
				cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")||
				cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
			trueFalse(false);
		}
		else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ProfitStatement")){
			trueFalse(true);
		}
	}
	public void trueFalse(boolean b){
		lblTransactionNo.setVisible(!b);
		lblCategory.setVisible(b);
		lblsubcategoryName.setVisible(b);
		lblProductid.setVisible(b);
		lblFromDate.setVisible(b);
		lblToDate.setVisible(b);
		cmbTransactionNo.cmbSuggest.setVisible(!b);
		cmbCategory.cmbSuggest.setVisible(b);
		cmbSubcategory.cmbSuggest.setVisible(b);
		cmbProductid.cmbSuggest.setVisible(b);
		chkCategory.setVisible(b);
		chkSubcategory.setVisible(b);
		chkProduct.setVisible(b);
		dateFromDate.setVisible(b);
		dateToDate.setVisible(b);
	}
	public void cmbTransactionNoDataLoad(){
		try {
			cmbTransactionNo.v.clear();
			cmbTransactionNo.v.add("");
			cmbTransactionNo.txtSuggest.setText("");
			String sql="";
			if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ItemReceipt")){
				sql="select invoiceNo as id from tbitemreceiptinfo order by invoiceNo";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("ReceiptReturn")){
				sql="select returnNo as id from tbreceiptreturninfo order by returnNo";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Sales")){
				sql="select salesNo as id from tbsalesinfo order by salesNo";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("SalesReturn")){
				sql="select salesReturnNo as id from tbsalesreturninfo order by salesReturnNo";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage/Broken")){
				sql="select wastageNo as id from tbwastage order by wastageNo";
			}
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbTransactionNo.v.add(rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbTransactionNoDataLoad()");
		}
	}
	public void setOpaqueTrueFalse(boolean b){
		panelCenter.setOpaque(b);
		panelSouth.setOpaque(b);
		panelCenterCenter.setOpaque(b);
		panelCenterWest.setOpaque(b);
		panelCenterCenterCenter.setOpaque(b);
		panelCenterCenterSouth.setOpaque(b);
		panelCenterWestCenter.setOpaque(false);
		panelCenterWestSouth.setOpaque(false);
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));
	}
	private void cmp() {
		TitledBorder titleBorder=BorderFactory.createTitledBorder("Task Report");
		titleBorder.setTitleColor(Color.BLACK);
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		titleBorder.setTitleFont(new Font("Carlibri", Font.BOLD, 20));
		setPreferredSize(new Dimension(1158,714));
		setBorder(titleBorder);

		BorderLayout border=new BorderLayout();
		setLayout(border);
		border.setHgap(8);
		add(panelSouth,BorderLayout.SOUTH);
		panelSouth();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();	
	}
	public void panelSouth(){
		panelSouth.setPreferredSize(new Dimension(0,300));
		panelSouth.setBackground(Color.BLACK);
	}
	public void panelCenter(){
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		panelCenterCenter();
		panelCenter.add(panelCenterWest, BorderLayout.WEST);
		panelCenterWest();
	}
	public void panelCenterCenter(){	
		panelCenterCenter.setLayout(new BorderLayout());
		panelCenterCenter.add(panelCenterCenterCenter, BorderLayout.CENTER);
		panelCenterCenterCenter();
		panelCenterCenter.add(panelCenterCenterSouth, BorderLayout.SOUTH);
		panelCenterCenterSouth();
	}
	public void panelCenterCenterCenter(){
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterCenterCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblReportTypeCenter, cn);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(cmbReportType.cmbSuggest, cn);
		cmbReportType.cmbSuggest.setPreferredSize(new Dimension(250, 32));
		cmbReportType.v.add("");
		cmbReportType.v.add("ItemReceipt");
		cmbReportType.v.add("ReceiptReturn");
		cmbReportType.v.add("Sales");
		cmbReportType.v.add("SalesReturn");
		cmbReportType.v.add("Wastage/Broken");
		cmbReportType.v.add("ProfitStatement");

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblTransactionNo, cn);
		lblTransactionNo.setVisible(false);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(cmbTransactionNo.cmbSuggest, cn);
		cmbTransactionNo.cmbSuggest.setVisible(false);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblCategory, cn);
		lblCategory.setVisible(false);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(cmbCategory.cmbSuggest, cn);
		cmbCategory.cmbSuggest.setVisible(false);

		cn.gridx=2;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(chkCategory, cn);
		chkCategory.setVisible(false);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblsubcategoryName, cn);
		lblsubcategoryName.setVisible(false);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(cmbSubcategory.cmbSuggest, cn);
		cmbSubcategory.cmbSuggest.setVisible(false);

		cn.gridx=2;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(chkSubcategory, cn);
		chkSubcategory.setVisible(false);

		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblProductid, cn);
		lblProductid.setVisible(false);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(cmbProductid.cmbSuggest, cn);
		cmbProductid.cmbSuggest.setVisible(false);

		cn.gridx=2;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(chkProduct, cn);
		chkProduct.setVisible(false);

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblFromDate, cn);
		lblFromDate.setVisible(false);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(dateFromDate, cn);
		dateFromDate.setVisible(false);

		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(lblToDate, cn);
		lblToDate.setVisible(false);

		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterCenterCenter.add(dateToDate, cn);
		dateToDate.setVisible(false);
	}
	public void panelCenterCenterSouth(){
		panelCenterCenterSouth.setBackground(Color.BLUE);
		panelCenterCenterSouth.setPreferredSize(new Dimension(0, 80));
		FlowLayout flow=new FlowLayout();
		panelCenterCenterSouth.setLayout(flow);
		panelCenterCenterSouth.add(btnPreview);
		btnPreview.setPreferredSize(new Dimension(150, 45));
		panelCenterCenterSouth.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(150, 45));
	}
	public void panelCenterWest(){
		panelCenterWest.setLayout(new BorderLayout());
		panelCenterWest.add(panelCenterWestCenter, BorderLayout.CENTER);
		panelCenterWestCenter();
		panelCenterWest.add(panelCenterWestSouth, BorderLayout.SOUTH);
		panelCenterWestSouth();
	}
	public void panelCenterWestSouth() {
		panelCenterWestSouth.setPreferredSize(new Dimension(0, 80));
		FlowLayout flow=new FlowLayout();
		panelCenterWestSouth.setLayout(flow);
		panelCenterWestSouth.add(btnPreviewWest);
		btnPreviewWest.setPreferredSize(new Dimension(150, 45));
		panelCenterWestSouth.add(btnRefreshWest);
		btnRefreshWest.setPreferredSize(new Dimension(150, 45));
	}
	public void panelCenterWestCenter() {
		panelCenterWestCenter.setPreferredSize(new Dimension(550, 0));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenterWestCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(80, 5, 5, 5);
		panelCenterWestCenter.add(lblReportTypewest, cn);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(80, 5, 5, 5);
		panelCenterWestCenter.add(cmbReportTypewest.cmbSuggest, cn);
		cmbReportTypewest.cmbSuggest.setPreferredSize(new Dimension(250, 32));
		cmbReportTypewest.v.add("");
		cmbReportTypewest.v.add("ItemReceipt");
		cmbReportTypewest.v.add("ReceiptReturn");
		cmbReportTypewest.v.add("Sales");
		cmbReportTypewest.v.add("SalesReturn");
		cmbReportTypewest.v.add("Wastage/Broken");
		cmbReportTypewest.v.add("ProfitStatement");

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(lblFromDatewest, cn);

		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(dateFromDatewest, cn);
		dateFromDatewest.setDate(new Date());
		dateFromDatewest.setDateFormatString("dd-MM-yyyy");

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(lblToDatewest, cn);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(dateToDatewest, cn);
		dateToDatewest.setDate(new Date());
		dateToDatewest.setDateFormatString("dd-MM-yyyy");
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(btnRefreshWest, cn);

		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterWestCenter.add(btnPreviewWest, cn);
	}
}









