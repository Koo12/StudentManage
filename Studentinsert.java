import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Studentinsert extends JDialog implements ActionListener {
	JPanel mb1,mb2,mb3;
	JLabel bq1,bq2,bq3,bq4,bq5,bq6,bq7,bq8;
	JTextField wbk1,wbk2,wbk3,wbk4,wbk5,wbk6,wbk7;
	JButton an1,an2;
	
	Studentinsert(Frame owner,String title,boolean modal)
	{
		super(owner,title,modal);
		
		bq1=new JLabel("              学号：");
		bq2=new JLabel("              姓名：");
		bq3=new JLabel("              性别：");
		bq4=new JLabel("              数学：");
		bq5=new JLabel("              java：");
		bq6=new JLabel("              英语：");
		bq7=new JLabel("              PE：");
		bq8=new JLabel("   ★★★★★★★");
		
		wbk1=new JTextField(5);
		wbk2=new JTextField(5);
		wbk3=new JTextField(5);
		wbk4=new JTextField(5);
		wbk5=new JTextField(5);
		wbk6=new JTextField(5);
		wbk7=new JTextField(5);
		
		an1=new JButton("添加");
		an1.addActionListener(this);
		an1.setActionCommand("insert");
		an2=new JButton("取消");
		an2.addActionListener(this);
		an2.setActionCommand("cancel");
		
		mb1=new JPanel();
		mb1.setLayout(new GridLayout(7,1));
		mb1.add(bq1); mb1.add(bq2); mb1.add(bq3);
		mb1.add(bq4); mb1.add(bq5); mb1.add(bq6);
		mb1.add(bq7);
		
		mb2=new JPanel();
		mb2.setLayout(new GridLayout(7,1,10,15));
		mb2.add(wbk1); mb2.add(wbk2); mb2.add(wbk3);
		mb2.add(wbk4); mb2.add(wbk5); mb2.add(wbk6);
		mb2.add(wbk7);
		
		mb3=new JPanel();
		mb3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mb3.add(an1); mb3.add(an2);
		
		this.setLayout(new BorderLayout());
		this.add(bq8,BorderLayout.NORTH);
		this.add(mb1,BorderLayout.WEST);
		this.add(mb2);
		this.add(mb3,BorderLayout.SOUTH);
		
		this.setSize(600,400);
		this.setLocation(600,300);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("insert"))
		{
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
			try
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=transcript","sa","gzx254052834843");
				ps=ct.prepareStatement("insert into data values(?,?,?,?,?,?,?)");
				ps.setString(1,wbk1.getText());
				ps.setString(2,wbk2.getText());
				ps.setString(3,wbk3.getText());
				ps.setString(4,wbk4.getText());
				ps.setString(5,wbk5.getText());
				ps.setString(6,wbk6.getText());
				ps.setString(7,wbk7.getText());
				rs=ps.executeQuery();
				
			}catch(Exception e2){}
			finally
			{
				try{
					if(rs!=null)
					{
						rs.close();
					}
					if(ps!=null)
					{
						ps.close();
					}
					if(ct!=null)
					{
						ct.close();
					}
				}catch(Exception e2){}
			}
			this.dispose();
		}
		if(e.getActionCommand().equals("cancel"))
		{
			this.dispose();
		}
	}
	
}
