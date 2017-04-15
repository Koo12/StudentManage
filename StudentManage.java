import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class StudentManage extends JFrame implements ActionListener {
	JPanel mb1,mb2;
	JLabel bq1,bq2,bq3,bq4;
	JTextField wbk1,wbk2;
	JButton an1,an2,an3,an4,an5;
	JComboBox xlk;
	JTable bg;
	JScrollPane gd;

	Vector Title,xiang;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;

	public static void main(String[] args){
		StudentManage xx=new StudentManage();
	}
	StudentManage()
	{
		bq1=new JLabel("查询");
		bq2=new JLabel("学号");
		bq3=new JLabel("排榜方式");
		bq4=new JLabel("人数");
		an1=new JButton("查一下");
		an1.addActionListener(this);
		an1.setActionCommand("select");
		an2=new JButton("添加");
		an2.addActionListener(this);
		an2.setActionCommand("insert");
		an3=new JButton("修改");
		an3.addActionListener(this);
		an3.setActionCommand("update");
		an4=new JButton("删除");
		an4.addActionListener(this);
		an4.setActionCommand("delete");
		an5=new JButton("请看");
		an5.addActionListener(this);
		an5.setActionCommand("look");
		String[] means={"高数","java","英语","体育"};
		wbk1=new JTextField(10);
		wbk2=new JTextField(5);
		xlk=new JComboBox(means);

		mb1=new JPanel();
		mb1.setLayout(new FlowLayout(FlowLayout.LEFT));
		mb1.add(bq1); mb1.add(bq2); mb1.add(wbk1); mb1.add(an1);

		Title=new Vector();
		Title.add("学号");
		Title.add("姓名");
		Title.add("性别");
		Title.add("数学成绩");
		Title.add("java成绩");
		Title.add("英语成绩");
		Title.add("体育成绩");

		xiang=new Vector();

		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=transcript","sa","gzx254052834843");
			ps=ct.prepareStatement("select * from data");
			rs=ps.executeQuery();

			while(rs.next())
			{
				Vector hang=new Vector();
				hang.add(rs.getInt(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getDouble(4));
				hang.add(rs.getDouble(5));
				hang.add(rs.getDouble(6));
				hang.add(rs.getDouble(7));
				xiang.add(hang);
			}

			ps=ct.prepareStatement("select count(*) from data");
			rs=ps.executeQuery();
			rs.next();
			String renshu;
			renshu=rs.getString(1);
			wbk2.setText(renshu);
		}catch(Exception e){}
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
			}
			catch(Exception e){}
		}
		
		mb2=new JPanel();
		mb2.setLayout(new GridLayout(8,1,2,20));
		mb2.add(bq3); mb2.add(an5); mb2.add(xlk); mb2.add(an2); mb2.add(an3); mb2.add(an4);
		mb2.add(bq4); mb2.add(wbk2);

		bg=new JTable(xiang,Title);
		gd=new JScrollPane(bg);
		
		this.add(mb1,"North");
		this.add(mb2,"East");
		this.add(gd);

		this.setTitle("学生信息管理系统");
		this.setSize(800,600);
		this.setLocation(500,200);

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("select"))
		{
			String number=wbk1.getText().trim();
			if(number.equals(""))
			{
				Tablemodel xx=new Tablemodel(wbk2);
				bg.setModel(xx);
			}
			else
			{
				String sql="select * from data where number='"+number+"'";
				Tablemodel xx=new Tablemodel(wbk2,sql);
				bg.setModel(xx);
			}
		}
		if(e.getActionCommand().equals("insert"))
		{
			System.out.println("222");
		}
		if(e.getActionCommand().equals("update"))
		{
			System.out.println("333");
		}
		if(e.getActionCommand().equals("delete"))
		{
			System.out.println("444");
		}
		if(e.getActionCommand().equals("look"))
		{
			Object ll=xlk.getSelectedItem();//获取下拉框中的所选项
			if(ll.equals("高数"))
			{
				String sql="select * from data order by math desc";
				Tablemodel xx=new Tablemodel(wbk2,sql);
				bg.setModel(xx);
			}
			if(ll.equals("java"))
			{
				String sql="select * from data order by java desc";
				Tablemodel xx=new Tablemodel(wbk2,sql);
				bg.setModel(xx);
			}
			if(ll.equals("英语"))
			{
				String sql="select * from data order by english desc";
				Tablemodel xx=new Tablemodel(wbk2,sql);
				bg.setModel(xx);
			}
			if(ll.equals("体育"))
			{
				String sql="select * from data order by PE desc";
				Tablemodel xx=new Tablemodel(wbk2,sql);
				bg.setModel(xx);
			}
		}
	}
}
