import java.sql.*;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.*;
public class Tablemodel extends AbstractTableModel{
	
	Vector Title,xiang;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	public int getRowCount()
	{
		return this.xiang.size();
	}
    public int getColumnCount()
    {
		return this.Title.size();
	}
	public Object getValueAt(int hang, int lie)
	{
		return ((Vector)this.xiang.get(hang)).get(lie);
	}
	
	Tablemodel(JTextField wbk2,String sql)
	{
		this.moxing(wbk2,sql);
	}
	
	Tablemodel(JTextField wbk2)
	{
		String sql="select * from data";
		this.moxing(wbk2,sql);	
	}
	public String getColumnName(int e)//让每一列的名称显示出来
	{
		return (String)this.Title.get(e);
	}
	
	public void moxing(JTextField wbk2,String sql)
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
			ps=ct.prepareStatement(sql);
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
