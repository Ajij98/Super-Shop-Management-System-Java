package com.example.Main;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.omg.CORBA.IMP_LIMIT;
import com.admin.dbConneciton;
import com.example.Admin.Login;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
public class MainClass {
	public static void main(String args[]){
		try 
		{
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("fully qualified name of look and feel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
			dbConneciton.connection();
			dbConneciton.con.close();
			System.out.println("connected");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,e+"(UImanager)","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		//ImageIcon imageIcon=new ImageIcon("Image/Ok-icon.png");
		Login lg=new Login();
		//lg.setIconImage(imageIcon.getImage());*/
	}
}
