package com.example.Admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.detaile.BrokenWork;
import com.detaile.CetagoryWork;
import com.detaile.ChangePassWork;
import com.detaile.ExitWork;
import com.detaile.ItemReceiptWork;
import com.detaile.LogOffWork;
import com.detaile.NewUserWork;
import com.detaile.OpeningStockWork;
import com.detaile.PartyInfoWork;
import com.detaile.ProductWork;
import com.detaile.ReceiptReturnWork;
import com.detaile.SalesReturnWork;
import com.detaile.SalesWork;
import com.detaile.SetupReportWork;
import com.detaile.SupplierWork;
import com.detaile.TaskReportWork;
import com.example.Main.MainClass;
public class WorkingPanel extends JPanel{

	JPanel panelWest=new JPanel();
	JPanel panelCenter=new JPanel();
	//JLabel lblBackgroundImage=new JLabel(new ImageIcon("Image/backgroundImage3.jpg"));
	JLabel lblBackgroundImage=new JLabel(new ImageIcon(""));
	JPanel panelBackGroundImage=new JPanel();
	ImageIcon imageIcon1=new ImageIcon("Image/backgroundImage4.jpg");
	JButton btnImage1=new JButton("");
	ImageIcon imageIcon2=new ImageIcon("Image/back5.jpg");
	JButton btnImage2=new JButton(imageIcon2);
	ImageIcon imageIcon3=new ImageIcon("Image/back6.jpg");
	JButton btnImage3=new JButton(imageIcon3);
	//ImageIcon imageIcon4=new ImageIcon("Image/backgroundImage4.jpg");
	JButton btnImage4=new JButton();
	//ImageIcon imageIcon5=new ImageIcon("Image/backgroundImage4.jpg");
	JButton btnImage5=new JButton();
	JButton btnImage6=new JButton();
	JButton btnImage7=new JButton();
	JButton btnImage8=new JButton();
	
	JPanel mainPanel=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();

	JPanel panelCenterSetup=new JPanel();
	JPanel panelCenterSecurity=new JPanel();
	JPanel panelCenterTask=new JPanel();
	JPanel panelCenterReport=new JPanel();
	JPanel panelCenterOther=new JPanel();
	
	ImageIcon iconCetagory=new ImageIcon("Image/cetagoryIcon.png");
	JButton btnCetagory=new JButton(iconCetagory);
	JLabel lblCetagory=new JLabel("Category");
	
	ImageIcon iconSupplier=new ImageIcon("Image/supplierIcon2.png");
	JButton btnSupplier=new JButton(iconSupplier);
	JLabel lblSupplier=new JLabel("Supplier");

	ImageIcon iconParty=new ImageIcon("Image/partyImage.png");
	JButton btnParty=new JButton(iconParty);
	JLabel lblParty=new JLabel("ClientInfo");

	ImageIcon iconProdInfo=new ImageIcon("Image/productInfoImage.png");
	JButton btnProdInfo=new JButton(iconProdInfo);
	JLabel lblProdInfo=new JLabel("Product Info");
	
	ImageIcon iconNewUser=new ImageIcon("Images/New user.png");
	JButton btnNewUser=new JButton(iconNewUser);
	JLabel lblNewUser=new JLabel("New User");
	
	ImageIcon iconChangePass=new ImageIcon("Images/change password.png");
	JButton btnChangePass=new JButton(iconChangePass);
	JLabel lblChangePass=new JLabel("Change password");

	ImageIcon iconOpeningStock=new ImageIcon("Images/openingStockImage.png");
	JButton btnOpeningStock=new JButton(iconOpeningStock);
	JLabel lblOpeningStock=new JLabel("Opening Stock");

	ImageIcon iconItemReceipt=new ImageIcon("Images/ItemReceiptImage.png");
	JButton btnItemReceipt=new JButton(iconItemReceipt);
	JLabel lblItemReceipt=new JLabel("Item Receipt");

	ImageIcon iconReceiptReturn=new ImageIcon("Images/ReceiptReturnIcon.png");
	JButton btnReceiptReturn=new JButton(iconReceiptReturn);
	JLabel lblReceiptReturn=new JLabel("Receipt Return");
	
	ImageIcon iconSales=new ImageIcon("Images/sales.png");
	JButton btnSales=new JButton(iconSales);
	JLabel lblSales=new JLabel("Sales");
	
	ImageIcon iconSalesReturn=new ImageIcon("Images/refresh.png");
	JButton btnSalesReturn=new JButton(iconSalesReturn);
	JLabel lblSalesReturn=new JLabel("Sales Return");
	
	ImageIcon iconBroken=new ImageIcon("Images/recycle-bin-icon-6.png");
	JButton btnBroken=new JButton(iconBroken);
	JLabel lblBroken=new JLabel("Broken");
	
	ImageIcon iconSetupReport=new ImageIcon("Images/setupReporticon11.png");
	JButton btnSetupReport=new JButton(iconSetupReport);
	JLabel lblSetupReport=new JLabel("Setup Report");
	
	ImageIcon iconTaskReport=new ImageIcon("Images/setupReportpng123.png");
	JButton btnTaskReport=new JButton(iconTaskReport);
	JLabel lblTaskReport=new JLabel("Task Report");
	
	ImageIcon iconLogOff=new ImageIcon("Image/log.png");
	JButton btnLogOff=new JButton(iconLogOff);
	JLabel lblLogOff=new JLabel("Logout");
	
	ImageIcon iconExit=new ImageIcon("Image/EXIT.png");
	JButton btnExit=new JButton(iconExit);
	JLabel lblExit=new JLabel("Exit");

	JButton btnSetup=new JButton("Setup");
	JButton btnSecurity=new JButton("Security");
	JButton btnTask=new JButton("Task");
	JButton btnReport=new JButton("Report");
	JButton btnOthers=new JButton("Others");
	JButton lblExitIcon=new JButton(new ImageIcon("Image/button_cancel_256.png"));
	JButton lblLogoffIcon=new JButton(new ImageIcon("Image/exit.png"));
	JButton lblUpdateIcon=new JButton(new ImageIcon("Images/Packing64.png"));
	Font font=new Font("Calibri",Font.BOLD, 14);
	Font font1=new Font("Calibri",Font.BOLD, 12);
	
	CetagoryWork cetagory;
	SupplierWork supplier;
	PartyInfoWork party;
	ProductWork product;
	
	NewUserWork newUser;
	ChangePassWork changepass=new ChangePassWork();
	
	OpeningStockWork openingStock;
	ItemReceiptWork itemReceipt;
	ReceiptReturnWork receiptReturn;
	SalesWork sales;
	SalesReturnWork salesReturn;
	BrokenWork broken;
	
	SetupReportWork setupReport=new SetupReportWork();
	TaskReportWork taskReport=new TaskReportWork();
	
	LogOffWork logOff=new LogOffWork();
	ExitWork exit=new ExitWork();
	JFrame frame=new JFrame();
	
	JMenuBar mainmenu=new JMenuBar();
	JMenu menuSetup=new JMenu("Setup |");
	JMenu menuSecurity=new JMenu("Security |");
	JMenu menuTask=new JMenu("Task |");
	JMenu menuReport=new JMenu("Report |");
	JMenu menuOther=new JMenu("Other");
	
	JMenuItem itemCetagory=new JMenuItem("Cetatory Info");
	JMenuItem itemSupplier=new JMenuItem("Supplier Info");
	JMenuItem itemParty=new JMenuItem("Party Info");
	JMenuItem itemProduct=new JMenuItem("Product Info");
	
	JMenuItem itemNewUser=new JMenuItem("NewUser Info");
	JMenuItem itemPassword=new JMenuItem("Password Info");
	
	JMenuItem itemOpenning=new JMenuItem("Opening Stock Info");
	JMenuItem itemItemReceipt=new JMenuItem("Item Receipt Info");
	JMenuItem itemItemReturn=new JMenuItem("Item Return Info");
	JMenuItem itemSales=new JMenuItem("Sales Info");
	JMenuItem itemSalesReturn=new JMenuItem("Sales Return Info");
	JMenuItem itemWastage=new JMenuItem("Wastage Info");
	
	JMenuItem itemSetupReport=new JMenuItem("Setup Report");
	JMenuItem itemTaskReport=new JMenuItem("Task Report");
	
	JMenuItem itemLogoff=new JMenuItem("Logoff");
	JMenuItem itemOthers=new JMenuItem("Others");
	
	SessionBean sessionbean=new SessionBean();
	
	JButton btnYes=new JButton("Yes");
	JButton btnNo=new JButton("No");
	
	boolean visible=false;
	JButton btnChoseColor=new JButton("choose color");
	JButton btnBackgroundImage=new JButton("BackgroundImage"); 
	JButton btnReset=new JButton("Reset"); 

	boolean colorChoose=false,backImage=false;
	Color color= (Color.gray);
	boolean imagepanel=false;
	public WorkingPanel(JFrame frm,SessionBean sessionbean){
		this.frame=frm;
		this.sessionbean=sessionbean;
		cetagory=new CetagoryWork(sessionbean);
		supplier=new SupplierWork(sessionbean);
		party=new PartyInfoWork(sessionbean);
		product=new ProductWork(sessionbean);
		newUser=new NewUserWork(sessionbean);
		openingStock=new OpeningStockWork(sessionbean);
		itemReceipt=new ItemReceiptWork(sessionbean);
		receiptReturn=new ReceiptReturnWork(sessionbean);
		sales=new SalesWork(sessionbean);
		salesReturn=new SalesReturnWork(sessionbean);
		broken=new BrokenWork(sessionbean);
		cmp();
		btnAction();
		setupAction();
		securityAction();
		TaskAction();
		ReportAction();
		OthersAction();
		this.frame.setJMenuBar(mainmenu);
		addmenu();
		setOpaqueTrueFalse(visible);
		checkUserType();
	}
	public void checkUserType(){
		if(!sessionbean.getUserType().equals("Admin") && !sessionbean.getUserType().equals("Super Admin")){
			btnSetup.setEnabled(false);
			btnSecurity.setEnabled(false);
			menuSetup.setEnabled(false);
			menuSecurity.setEnabled(false);
		}
		else {
			btnSetup.setEnabled(true);
			btnSecurity.setEnabled(true);
			menuSetup.setEnabled(true);
			menuSecurity.setEnabled(true);
		}
	}
	public void setOpaqueTrueFalse(boolean b){
		panelWest.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelCenterSetup.setOpaque(b);
		panelCenterSecurity.setOpaque(b);
		panelCenterTask.setOpaque(b);
		panelCenterReport.setOpaque(b);
		panelCenterOther.setOpaque(b);
		panelCenter.setOpaque(b);
		cetagory.setOpaque(b);
		supplier.setOpaque(b);
		party.setOpaque(b);
		product.setOpaque(b);
		newUser.setOpaque(b);
		changepass.setOpaque(b);
		openingStock.setOpaque(b);
		itemReceipt.setOpaque(b);
		receiptReturn.setOpaque(b);
		sales.setOpaque(b);
		salesReturn.setOpaque(b);
		broken.setOpaque(b);
		setupReport.setOpaque(b);
		taskReport.setOpaque(b);
		
		cetagory.setOpaqueTrueFalse(b);
		supplier.setOpaqueTrueFalse(b);
		party.setOpaqueTrueeFalse(b);
		product.setOpaqueTrueFlase(b);
		
		newUser.setOpaqueTrueFalse(b);
		changepass.setOpaqueTrueFalse(b);
		
		openingStock.setOpaqueTrueFalse(b);
		itemReceipt.setOpaqueTrueFalse(b);
		receiptReturn.setOpaqueTrueFalse(b);
		sales.setOpaqueTrueFalse(b);
		salesReturn.setOpaqueTrueFalse(b);
		broken.setOpaqueTrueFalse(b);
		
		setupReport.setOpaqueTrueFalse(b);
		taskReport.setOpaqueTrueFalse(b);
	}
	private void addmenu() {
		mainmenu.add(menuSetup);
		menuSetup();
		mainmenu.add(menuSecurity);
		menuSecurity();
		mainmenu.add(menuTask);
		menuTask();
		mainmenu.add(menuReport);
		menuReport();
		mainmenu.add(menuOther);
		menuOthers();
		mainmenu.add(btnChoseColor);
		btnChoseColor.setBackground(Color.decode("#CDAD00"));
		btnChoseColor.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				btnChoseColor.setBackground(Color.decode("#CDAD00"));
			}
			public void mouseEntered(MouseEvent e) {
				btnChoseColor.setBackground(Color.decode("#FFFAFA"));
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		mainmenu.add(btnBackgroundImage);
		btnBackgroundImage.setBackground(Color.decode("#00FF7F"));
		btnBackgroundImage.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				btnBackgroundImage.setBackground(Color.decode("#00FF7F"));
			}
			public void mouseEntered(MouseEvent e) {
				btnBackgroundImage.setBackground(Color.decode("#FFFAFA"));
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
		mainmenu.add(btnReset);
		btnReset.setPreferredSize(new Dimension(100, 20));
		btnReset.setBackground(color.decode("#FF6103"));
		btnReset.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				btnReset.setBackground(Color.decode("#FF6103"));
			}
			public void mouseEntered(MouseEvent e) {
				btnReset.setBackground(Color.decode("#FFFAFA"));
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	private void menuSetup() {
		menuSetup.addSeparator();
		menuSetup.add(itemCetagory);
		menuSetup.addSeparator();
		menuSetup.add(itemSupplier);
		menuSetup.addSeparator();
		menuSetup.add(itemParty);
		menuSetup.addSeparator();
		menuSetup.add(itemProduct);
	}
	private void menuSecurity() {
		menuSecurity.addSeparator();
		menuSecurity.add(itemNewUser);
		menuSecurity.addSeparator();
		menuSecurity.add(itemPassword);
	}
	private void menuTask() {
		menuTask.addSeparator();
		menuTask.add(itemOpenning);
		menuTask.addSeparator();
		menuTask.add(itemItemReceipt);
		menuTask.addSeparator();
		menuTask.add(itemItemReturn);
		menuTask.addSeparator();
		menuTask.add(itemSales);
		menuTask.addSeparator();
		menuTask.add(itemSalesReturn);
		menuTask.addSeparator();
		menuTask.add(itemWastage);
	}
	private void menuReport() {
		menuReport.addSeparator();
		menuReport.add(itemSetupReport);
		menuReport.addSeparator();
		menuReport.add(itemTaskReport);
	}
	private void menuOthers() {
		menuOther.addSeparator();
		menuOther.add(itemLogoff);
		menuOther.addSeparator();
		menuOther.add(itemOthers);
	}
	private void setupAction() {
		itemCetagory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				cetagory.setVisible(true);
			}
		});
		/*itemOthers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});*/
		itemSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				supplier.setVisible(true);
			}
		});
		itemParty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				party.setVisible(true);
			}
		});
		itemProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				product.setVisible(true);
			}
		});	
		btnCetagory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				cetagory.setVisible(true);
				cetagory.autoId();
				cetagory.autoIdSubcategory();
				cetagory.tabledataloadCategory();
				cetagory.tableDataLoadSubcategory();
				cetagory.cmbSearchDataLoadCategory();
				cetagory.cmbSearchDataLoadSubcategory();
			}
		});
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				supplier.setVisible(true);
				supplier.autoId();
				supplier.tableDataLoad();
				supplier.cmbSearchDataLoadSupplier();
			}
		});
		btnParty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				party.setVisible(true);
				party.autoId();
				party.cmbReferenceByDataLoad();
				party.tableDataLoad();
				party.cmbSearchDataLoad();
			}
		});
		btnProdInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				product.setVisible(true);
				product.autoId(); 
				product.cmbCatagoryDataLoad();
				product.cmbSupplierNameDataLoad();
				product.cmbSearchDataLoad();
				product.tableDataLoad();
			}
		});
	}
	private void securityAction() {
		btnNewUser.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				newUser.setVisible(true);
				newUser.autoId();
				newUser.tableDataLoad();
				newUser.cmbSearchDataLoad();
			}
		});
		btnChangePass.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				changepass.setVisible(true);
				changepass.cmbUserNameDataLoad();
			}
		});	
		itemNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				newUser.setVisible(true);
			}
		});
		itemPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				changepass.setVisible(true);
			}
		});
	}
	private void TaskAction() {
		btnOpeningStock.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				openingStock.setVisible(true);
				openingStock.autoId();
				openingStock.cmbProductIdDataLoad();
				openingStock.tableDataLoad();
				openingStock.cmbSearchDataLoad();
			}
		});
		btnItemReceipt.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				itemReceipt.setVisible(true);
				itemReceipt.cmbProductIdDataLoad();
				itemReceipt.autoId();
				itemReceipt.tableSearchDataLoad();
			}
		});
		btnReceiptReturn.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				receiptReturn.setVisible(true);
				receiptReturn.autoId();
				receiptReturn.tableReceiptreturninfodataLoad();
				receiptReturn.cmbInvoiceNoDataLoad();
			}
		});
		btnSales.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				sales.setVisible(true);
				sales.autoId();
				sales.cmbProductIdDataLoad();
				sales.cmbClientIdDataLoad();
				sales.tableSalesInfoDateLoad();
			}
		});
		btnSalesReturn.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				salesReturn.setVisible(true);
				salesReturn.cmbSalesNoDataLoad();
				salesReturn.autoId();
				salesReturn.tableSalesReturninfoDataLoad();
			}
		});
		btnBroken.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				broken.setVisible(true);
				broken.autoId();
				broken.cmbProductIdDataLoad();
				broken.tableDataLoad();
				broken.cmbSearchDataLoad();
			}
		});	
		itemOpenning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				openingStock.setVisible(true);
			}
		});
		itemItemReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				itemReceipt.setVisible(true);
			}
		});
		itemItemReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				receiptReturn.setVisible(true);
			}
		});
		itemSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				sales.setVisible(true);
			}
		});
		itemSalesReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				salesReturn.setVisible(true);
			}
		});
		itemWastage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				broken.setVisible(true);
			}
		});
	}
	private void ReportAction() {
		btnSetupReport.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				setupReport.setVisible(true);
			}
		});
		btnTaskReport.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				taskReport.setVisible(true);
			}
		});	
		itemSetupReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				setupReport.setVisible(true);
			}
		});
		itemTaskReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				taskReport.setVisible(true);
			}
		});
	}
	private void OthersAction() {
		btnLogOff.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.setVisible(true);
				frame.setVisible(false);			
			}
		});
		btnExit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JPanel panel=new JPanel();
				JLabel lbl=new JLabel("Do You Want to EXIT?");
				panel.add(lbl);
				panelcentertruefalse();
				ImageIcon icon=new ImageIcon("Image/EXIT.png");
				int a=JOptionPane.showConfirmDialog(null,panel,"Confirm",
						JOptionPane.YES_NO_OPTION,JOptionPane.CANCEL_OPTION,icon);
				if(a==JOptionPane.YES_OPTION)
				{
					//System.exit(0);
					frame.dispose();
				}
			}
		});		
		itemLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				logOff.setVisible(true);			
			}
		});
		itemOthers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelcentertruefalse();
				exit.setVisible(true);
			}
		});
	}
	public void btnAction(){
		btnSetup.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelwestcentertruefalse();
				panelCenterSetup.setVisible(true);
			}
		});
		btnSecurity.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelwestcentertruefalse();
				panelCenterSecurity.setVisible(true);
			}
		});
		btnTask.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				panelwestcentertruefalse();
				panelCenterTask.setVisible(true);
			}
		});
		btnReport.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				panelwestcentertruefalse();
				panelCenterReport.setVisible(true);
			}
		});
		btnOthers.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				panelwestcentertruefalse();
				panelCenterOther.setVisible(true);
			}
		});
		btnBackgroundImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panelcentertruefalse();
					panelBackGroundImage.setVisible(true);
					panelBackGroundImageWork();
					imagepanel=true;
			}
		});
		btnChoseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImage=false;
				color=JColorChooser.showDialog(null, "Choose Your Color",Color.GREEN);
				mainPanel.setBackground(color);
				if(color==null){
					backImage=false;
				}
				cmp();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBackGroundImage.setVisible(false);
				backImage=false;
				color=Color.decode("#D6D9DF");
				mainPanel.setBackground(color);
				
				cmp();
			}
		});
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageChange("Image/back5.jpg");
			}
		});
		btnImage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageChange("Image/backgroundImage4.jpg");
			}
		});
		btnImage3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageChange("Image/back6.jpg");
			}
		});
	}
	public void imageChange(String image){
		Image ima=Toolkit.getDefaultToolkit().getImage(image);
		Image resize=ima.getScaledInstance(1366,768,Image.SCALE_DEFAULT);
		lblBackgroundImage.setIcon(new ImageIcon(resize));
		backImage=true;
		cmp();
	}
	private void cmp() {
		BorderLayout bdr=new BorderLayout();
		setLayout(bdr);
		bdr.setVgap(0);
		/*if(colorChoose){
			lblBackgroundImage.setVisible(false);
			add(mainPanel);
			mainPanel.setVisible(true);
			mainPanel.setBackground(color);
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(panelWest,BorderLayout.WEST);
			panelWestWork();
			mainPanel.add(panelCenter,BorderLayout.CENTER);
			panelCenterWork();
			
		}*/
		if(backImage)
		{
			mainPanel.setVisible(false);
			lblBackgroundImage.setVisible(true);
			add(lblBackgroundImage);
			lblBackgroundImage.setLayout(new BorderLayout());
			lblBackgroundImage.add(panelWest,BorderLayout.WEST);
			panelWestWork();
			lblBackgroundImage.add(panelCenter,BorderLayout.CENTER);
			panelCenterWork();
		}
		else{
			lblBackgroundImage.setVisible(false);
			add(mainPanel);
			mainPanel.setVisible(true);
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(panelWest,BorderLayout.WEST);
			panelWestWork();
			mainPanel.add(panelCenter,BorderLayout.CENTER);
			panelCenterWork();
		}
		/*BorderLayout bdr=new BorderLayout();
		setLayout(bdr);
		bdr.setVgap(0);
		add(lblBackgroundImage);
		lblBackgroundImage.setLayout(new BorderLayout());
		lblBackgroundImage.add(panelWest,BorderLayout.WEST);
		panelWestWork();
		lblBackgroundImage.add(panelCenter,BorderLayout.CENTER);
		panelCenterWork();*/
		
	}
	private void panelCenterWork() {
		panelCenter.setBorder(BorderFactory.createLoweredBevelBorder());
		FlowLayout flow=new FlowLayout();
		flow.setVgap(0);	
		panelCenter.setLayout(flow);
		
		panelCenter.add(panelBackGroundImage);
		
		panelCenter.add(cetagory);
		panelCenter.add(supplier);
		panelCenter.add(party);
		panelCenter.add(product);
		
		panelCenter.add(newUser);
		panelCenter.add(changepass);
		
		panelCenter.add(openingStock);
		panelCenter.add(itemReceipt);
		panelCenter.add(receiptReturn);
		panelCenter.add(sales);
		panelCenter.add(salesReturn);
		panelCenter.add(broken);
		
		panelCenter.add(setupReport);
		panelCenter.add(taskReport);
		
		panelCenter.add(logOff);
		panelCenter.add(exit);	
		panelcentertruefalse();
		
	}
	public void panelBackGroundImageWork(){
		panelBackGroundImage.setPreferredSize(new Dimension(1158, 150));
		panelBackGroundImage.setBackground(color.decode("#191970"));
		FlowLayout flow=new FlowLayout();
		flow.setHgap(30);
		flow.setVgap(20);
		panelBackGroundImage.setLayout(flow);
		panelBackGroundImage.add(btnImage1);
		btnImage1.setPreferredSize(new Dimension(100,100));
		
		Image image1=Toolkit.getDefaultToolkit().getImage("Image/backgroundImage4.jpg");
		Image image1resize=image1.getScaledInstance(100,100,Image.SCALE_DEFAULT);
		btnImage1.setIcon(new ImageIcon(image1resize));
		
	
		panelBackGroundImage.add(btnImage2); 
		btnImage2.setPreferredSize(new Dimension(100,100));
		
		Image image2=Toolkit.getDefaultToolkit().getImage("Image/back5.jpg");
		Image image2resize=image2.getScaledInstance(100,100,Image.SCALE_DEFAULT);
		btnImage2.setIcon(new ImageIcon(image2resize));
		
		
		panelBackGroundImage.add(btnImage3);
		btnImage3.setPreferredSize(new Dimension(100,100));
		Image image3=Toolkit.getDefaultToolkit().getImage("Image/back6.jpg");
		Image image3resize=image3.getScaledInstance(100,100,Image.SCALE_DEFAULT);
		btnImage3.setIcon(new ImageIcon(image3resize));
		
		panelBackGroundImage.add(btnImage4);
		btnImage4.setPreferredSize(new Dimension(100,100));
		panelBackGroundImage.add(btnImage5);
		btnImage5.setPreferredSize(new Dimension(100,100));
		panelBackGroundImage.add(btnImage6);
		btnImage6.setPreferredSize(new Dimension(100,100));
		panelBackGroundImage.add(btnImage7);
		btnImage7.setPreferredSize(new Dimension(100,100));
		panelBackGroundImage.add(btnImage8);
		btnImage8.setPreferredSize(new Dimension(100,100));
	}
	private void panelcentertruefalse(){
		panelBackGroundImage.setVisible(false);
		
		cetagory.setVisible(false);
		supplier.setVisible(false);
		party.setVisible(false);
		product.setVisible(false);
		
		newUser.setVisible(false);
		changepass.setVisible(false);
		
		openingStock.setVisible(false);
		itemReceipt.setVisible(false);
		receiptReturn.setVisible(false);
		sales.setVisible(false);
		salesReturn.setVisible(false);
		broken.setVisible(false);
		
		setupReport.setVisible(false);
		taskReport.setVisible(false);
		
		logOff.setVisible(false);
		exit.setVisible(false);		
	}
	private void panelWestWork() {
		panelWest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelWest.setPreferredSize(new Dimension(200,0));
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelWestNorth,BorderLayout.NORTH);
		panelWestNorth.setOpaque(false);
		panelWest.add(panelWestCenter,BorderLayout.CENTER);
		/*panelWest.add(panelWestSouth,BorderLayout.SOUTH);
		panelWestSouth();*/
		panelWestCenter();
		panelWestNorth();
	}
	/*public void panelWestSouth(){
		panelWestSouth.setLayout(new FlowLayout());
		panelWestSouth.setPreferredSize(new Dimension(0, 40));
		panelWestSouth.setBackground(Color.BLUE);
	    panelWestSouth.add(btnChoseColor);
		panelWestSouth.add(btnBackgroundImage);
	}*/
	private void panelWestNorth() {
		panelWestNorth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		panelWestNorth.setPreferredSize(new Dimension(0, 160));
		GridLayout grid=new GridLayout(5, 1);
		panelWestNorth.setLayout(grid);
		panelWestNorth.add(btnSetup);
		panelWestNorth.add(btnSecurity);
		panelWestNorth.add(btnTask);
		panelWestNorth.add(btnReport);
		panelWestNorth.add(btnOthers);
	}
	public void panelWestCenter(){
		panelWestCenter.setLayout(new FlowLayout());
		panelWestCenter.add(panelCenterSetup);
		panelWestSetup();
		panelWestCenter.add(panelCenterSecurity);
		panelWestSecurity();
		panelWestCenter.add(panelCenterTask);
		panelWestTask();
		panelWestCenter.add(panelCenterReport);
		panelWestReport();
		panelWestCenter.add(panelCenterOther);
		panelWestOther();
		panelwestcentertruefalse();
	}
	public void panelWestSetup(){
		panelCenterSetup.setPreferredSize(new Dimension(200,588));
		panelCenterSetup.setBackground(Color.cyan);
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cn=new GridBagConstraints();
		panelCenterSetup.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnCetagory.setPreferredSize(new Dimension(iconCetagory.getIconWidth(), iconCetagory.getIconHeight()));
		panelCenterSetup.add(btnCetagory,cn);	
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 25, 5, 5);
		panelCenterSetup.add(lblCetagory,cn);
		lblCetagory.setForeground(Color.BLACK);
		lblCetagory.setFont(font);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnSupplier.setPreferredSize(new Dimension(iconSupplier.getIconWidth(), iconSupplier.getIconHeight()));
		panelCenterSetup.add(btnSupplier,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 30, 5, 5);
		panelCenterSetup.add(lblSupplier,cn);
		lblSupplier.setForeground(Color.BLACK);
		lblSupplier.setFont(font);
		
		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnParty.setPreferredSize(new Dimension(iconParty.getIconWidth(), iconParty.getIconHeight()));
		panelCenterSetup.add(btnParty,cn);
		
		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 25, 5, 5);
		panelCenterSetup.add(lblParty,cn);
		lblParty.setForeground(Color.BLACK);
		lblParty.setFont(font);
		
		cn.gridx=0;
		cn.gridy=6;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnProdInfo.setPreferredSize(new Dimension(iconProdInfo.getIconWidth(), iconProdInfo.getIconHeight()));
		panelCenterSetup.add(btnProdInfo,cn);
		
		cn.gridx=0;
		cn.gridy=7;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 15, 50, 5);
		panelCenterSetup.add(lblProdInfo,cn);
		lblProdInfo.setForeground(Color.BLACK);
		lblProdInfo.setFont(font);
	}	
	private void panelWestSecurity() {
		panelCenterSecurity.setPreferredSize(new Dimension(200,588));
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cn=new GridBagConstraints();
		panelCenterSecurity.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnNewUser.setPreferredSize(new Dimension(iconNewUser.getIconWidth(), iconNewUser.getIconHeight()));
		panelCenterSecurity.add(btnNewUser,cn);	
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 30, 5, 5);
		panelCenterSecurity.add(lblNewUser,cn);
		lblNewUser.setFont(font);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnChangePass.setPreferredSize(new Dimension(iconChangePass.getIconWidth(), iconChangePass.getIconHeight()));
		panelCenterSecurity.add(btnChangePass,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 150, 5);
		panelCenterSecurity.add(lblChangePass,cn);
		lblChangePass.setFont(font);
	}
	public void panelWestTask(){
		panelCenterTask.setPreferredSize(new Dimension(200,588));
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cn=new GridBagConstraints();
		panelCenterTask.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnOpeningStock.setPreferredSize(new Dimension(iconOpeningStock.getIconWidth(), iconOpeningStock.getIconHeight()));
		panelCenterTask.add(btnOpeningStock,cn);
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterTask.add(lblOpeningStock,cn);
		lblOpeningStock.setFont(font1);

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnItemReceipt.setPreferredSize(new Dimension(iconItemReceipt.getIconWidth(), iconItemReceipt.getIconHeight()));
		panelCenterTask.add(btnItemReceipt,cn);
		
		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 10, 5, 5);
		panelCenterTask.add(lblItemReceipt,cn);
		lblItemReceipt.setFont(font1);

		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnReceiptReturn.setPreferredSize(new Dimension(iconReceiptReturn.getIconWidth(), iconReceiptReturn.getIconHeight()));
		panelCenterTask.add(btnReceiptReturn,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenterTask.add(lblReceiptReturn,cn);
		lblReceiptReturn.setFont(font1);

		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnSales.setPreferredSize(new Dimension(iconSales.getIconWidth(), iconSales.getIconHeight()));
		panelCenterTask.add(btnSales,cn);
		
		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 30, 5, 5);
		panelCenterTask.add(lblSales,cn);
		lblSales.setFont(font1);

		cn.gridx=0;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnSalesReturn.setPreferredSize(new Dimension(iconSalesReturn.getIconWidth(), iconSalesReturn.getIconHeight()));
		panelCenterTask.add(btnSalesReturn,cn);
		
		cn.gridx=0;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 10, 70, 5);
		panelCenterTask.add(lblSalesReturn,cn);
		lblSalesReturn.setFont(font1);

		cn.gridx=1;
		cn.gridy=4;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnBroken.setPreferredSize(new Dimension(iconBroken.getIconWidth(), iconBroken.getIconHeight()));
		panelCenterTask.add(btnBroken,cn);
		
		cn.gridx=1;
		cn.gridy=5;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 25, 70, 5);
		panelCenterTask.add(lblBroken,cn);
		lblBroken.setFont(font1);
	}	
	private void panelWestReport() {
		panelCenterReport.setPreferredSize(new Dimension(200,588));
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cn=new GridBagConstraints();
		panelCenterReport.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnSetupReport.setPreferredSize(new Dimension(iconSetupReport.getIconWidth(), 
				iconSetupReport.getIconHeight()));
		panelCenterReport.add(btnSetupReport,cn);	
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 15, 5, 5);
		panelCenterReport.add(lblSetupReport,cn);
		lblSetupReport.setFont(font);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnTaskReport.setPreferredSize(new Dimension(iconTaskReport.getIconWidth(), iconTaskReport.getIconHeight()));
		panelCenterReport.add(btnTaskReport,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 22, 150, 5);
		panelCenterReport.add(lblTaskReport,cn);
		lblTaskReport.setFont(font);
	}	
	private void panelWestOther() {
		panelCenterOther.setPreferredSize(new Dimension(200,588));
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cn=new GridBagConstraints();
		panelCenterOther.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnLogOff.setPreferredSize(new Dimension(iconLogOff.getIconWidth(), iconLogOff.getIconHeight()));
		panelCenterOther.add(btnLogOff,cn);
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 35, 5, 5);
		panelCenterOther.add(lblLogOff,cn);
		lblLogOff.setFont(font);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		btnExit.setPreferredSize(new Dimension(iconExit.getIconWidth(), iconExit.getIconHeight()));
		panelCenterOther.add(btnExit,cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 45, 150, 5);
		panelCenterOther.add(lblExit,cn);
		lblExit.setFont(font);
	}
	public void panelwestcentertruefalse(){
		panelCenterSetup.setVisible(false);
		panelCenterSecurity.setVisible(false);
		panelCenterTask.setVisible(false);
		panelCenterReport.setVisible(false);
		panelCenterOther.setVisible(false);
	}
}
