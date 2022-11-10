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
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SetupReportWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();

	JLabel lblReportType=new JLabel("Report Type");
	JLabel lblReportName=new JLabel("Report Name");	
	SuggestText cmbReportType=new SuggestText();
	SuggestText cmbReportName=new SuggestText();
	JCheckBox chkAll=new JCheckBox("All");	

	ImageIcon iconPreview=new ImageIcon("Images/Reset.png");
	ImageIcon iconRefresh=new ImageIcon("Images/refresh1.png");
	JButton btnPreview=new JButton("Preview",iconPreview);
	JButton btnRefresh=new JButton("Refresh",iconRefresh);

	public SetupReportWork() {
		init();
		btnAction();
	}	
	public void setOpaqueTrueFalse(boolean b){
		panelCenter.setOpaque(b);
		panelSouth.setOpaque(false);
	}
	public void txtClear(){
		cmbReportType.txtSuggest.setText("");
		cmbReportName.v.clear();
		cmbReportName.txtSuggest.setText("");
		cmbReportName.cmbSuggest.setEnabled(true);
		chkAll.setSelected(false);
		lblReportName.setText("Report Name");
	}
	private void btnAction() {
		chkAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkAll.isSelected()){
					cmbReportName.cmbSuggest.setEnabled(false);
					cmbReportName.txtSuggest.setText("");
				}
				else{
					cmbReportName.cmbSuggest.setEnabled(true);
				}
			}
		});
		cmbReportType.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbReportType.txtSuggest.getText().trim().toString().isEmpty()){
					lblReportName.setText(cmbReportType.txtSuggest.getText()+" Name");
					cmbReportNameDataLoad();
				}
				else{
					lblReportName.setText("Report Name");
					cmbReportName.v.clear();
				}
			}
		});
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					String query=getReportQuery();
					if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Product")){
						showReport("SetupReport/ProductReport.jrxml", query);
					}
					else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Supplier")){
						showReport("SetupReport/SupplierReport.jrxml", query);
					}
					else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Client")){
						showReport("SetupReport/ClientReport.jrxml", query);
					}
					else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Opening Stock")){
						showReport("SetupReport/OpeningStockReport.jrxml", query);
					}
					else if(cmbReportType.txtSuggest.getText().trim().equalsIgnoreCase("Wastage")){
						showReport("SetupReport/WastageorBrokenReport.jrxml", query);
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtClear();
			}
		});
	}
	public void showReport(String jrxml, String query){
		try {
			dbConneciton.connection();
			JasperDesign jd=JRXmlLoader.load(jrxml);
			JRDesignQuery jrq=new JRDesignQuery();
			jrq.setText(query);
			jd.setQuery(jrq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null, dbConneciton.con);
			JasperViewer.viewReport(jp, false);
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"showReport()");
		}
	}
	public String getReportQuery(){
		String query="";
		if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Product")){
			String productid="%";
			if(!chkAll.isSelected()){
				StringTokenizer token=new StringTokenizer(cmbReportName.txtSuggest.getText().trim().toString(),"#");
				productid=token.nextToken();
			}
			query="select categoryName,subCategoryName,productName,productDescription,dealerPrice,"
					+ "tradePrice from tbproductinfo where productId like '"+productid+"' order by "
					+ "categoryName,subcategoryName,productName";
		}
		else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Supplier")){
			String supplierid="%";
			if(!chkAll.isSelected()){
				StringTokenizer token=new StringTokenizer(cmbReportName.txtSuggest.getText().trim().toString(),"#");
				supplierid=token.nextToken();
			}
			query="select SupplierName,Address,MobileNumber,MailAddress from tbsupplierinfo where "
					+ "supplierId like '"+supplierid+"' order by supplierName";
		}
		else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Client")){
			String clientid="%";
			if(!chkAll.isSelected()){
				StringTokenizer token=new StringTokenizer(cmbReportName.txtSuggest.getText().trim(),"#");
				clientid=token.nextToken();
			}
			query="select clientName,Address,Email,mobileNumber,Nationalid,Nationality,ReferenceBy "
					+ "from tbpartyinfo where clientId like '"+clientid+"' order by clientName";
		}
		else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Opening Stock")){
			String stockid="%";
			if(!chkAll.isSelected()){
				stockid=cmbReportName.txtSuggest.getText().trim().toString();
			}
			query="select categoryname,subcategoryname,productname,unit,stockquantity,dealerprice,amount"
					+ " from tbopeningstock where stockid like '"+stockid+"' order by categoryname,"
					+ "subcategoryname,productname";
		}
		else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage")){
			String wastageNo="%";
			if(!chkAll.isSelected()){
				wastageNo=cmbReportName.txtSuggest.getText().trim().toString();
			}
			query="select catName,subCatName,productName productType,WastageorBroken,dealerPrice,amount"
					+ " from tbwastage where wastageNo like '"+wastageNo+"' order by catName,subCatName,productName";
		}
		return query;
	}
	public boolean checkValidation(){
		if(!cmbReportType.txtSuggest.getText().trim().toString().isEmpty()){
			if(!cmbReportName.txtSuggest.getText().trim().toString().isEmpty()||chkAll.isSelected()){
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Select Report Name Please.");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Select Report Type Please.");
		}
		return false;
	}
	public void cmbReportNameDataLoad(){
		try {
			cmbReportName.v.clear();
			cmbReportName.v.add("");
			cmbReportName.txtSuggest.setText("");
			String sql="";
			if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Product")){
				sql="select productId as id,productName as name from tbproductinfo order by productName";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Supplier")){
				sql="select SupplierID as id ,SupplierName as name from tbsupplierinfo order by SupplierName";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Client")){
				sql="select clientId as id,clientName as name from tbpartyinfo order by clientName";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Opening Stock")){
				sql="select stockid as id from tbopeningstock";
			}
			else if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage")){
				sql="select wastageNo as id from tbwastage";
			}
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Opening Stock")||
						cmbReportType.txtSuggest.getText().trim().toString().equalsIgnoreCase("Wastage")){
					cmbReportName.v.add(rs.getString("id"));
				}
				else{
					cmbReportName.v.add(rs.getString("id")+"#"+rs.getString("name"));
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbReportNameDataLoad()");
		}
	}
	private void init() {
		TitledBorder titleBorder=BorderFactory.createTitledBorder("Setup Report");
		titleBorder.setTitleColor(Color.BLACK);
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		titleBorder.setTitleFont(new Font("Carlibri", Font.BOLD, 20));
		setPreferredSize(new Dimension(1158,714));
		setBorder(titleBorder);
		BorderLayout border=new BorderLayout();
		setLayout(border);
		border.setHgap(8);
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelSouth,BorderLayout.SOUTH);
		panelSouth();
	}
	private void panelCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;

		cn.gridx=0;
		cn.gridy=0;
		cn.insets=new Insets(150, 5, 5, 5);
		panelCenter.add(lblReportType,cn);
		lblReportType.setFont(new Font("Carlibri", Font.BOLD, 15));

		cn.gridx=1;
		cn.gridy=0;
		cn.insets=new Insets(150, 5, 5, 5);
		panelCenter.add(cmbReportType.cmbSuggest,cn);
		cmbReportType.cmbSuggest.setPreferredSize(new Dimension(250, 30));
		cmbReportType.v.add("");
		cmbReportType.v.add("Product");
		cmbReportType.v.add("Supplier");
		cmbReportType.v.add("Client");
		cmbReportType.v.add("Opening Stock");
		cmbReportType.v.add("Wastage");

		cn.gridx=0;
		cn.gridy=1;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(lblReportName,cn);
		lblReportName.setFont(new Font("Carlibri", Font.BOLD, 15));

		cn.gridx=1;
		cn.gridy=1;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(cmbReportName.cmbSuggest,cn);
		cmbReportName.cmbSuggest.setPreferredSize(new Dimension(250, 30));

		cn.gridx=2;
		cn.gridy=1;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(chkAll,cn);
		chkAll.setFont(new Font("Carlibri", Font.BOLD, 15));

	}
	public void panelSouth(){
		panelSouth.setPreferredSize(new Dimension(0, 350));
		FlowLayout flow=new FlowLayout();
		panelSouth.setLayout(flow);

		panelSouth.add(btnPreview);
		btnPreview.setFont(new Font("Carlibri", Font.PLAIN, 14));
		btnPreview.setPreferredSize(new Dimension(130, 45));
		panelSouth.add(btnRefresh);
		btnRefresh.setFont(new Font("Carlibri", Font.PLAIN, 14));
		btnRefresh.setPreferredSize(new Dimension(130,45));
	}

}





