import java.sql.*;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.*;
public class Tablemodel extends AbstractTableModel{
	public int getRowCount()
	{
		return 1;
	}
	public int getColumnCount()
	{
		return 1;
	}
	public Object getValueAt(int row,int column)
	{
		return this;
	}
	
	Vector Title,xiang;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	Tablemodel(JTextField wbk2)
	{
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
			}catch(Exception e){}
		}
	}
}
