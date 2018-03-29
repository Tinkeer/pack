package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

class stock {
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost/stu?useSSL=false";
	public static String user="root";
	public static String password="";
	public static Statement stmt=null;
	public static ResultSet rs=null;
	public static void panduan() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String selectsql="SELECT * FROM users WHERE type=5";
		stmt=(Statement) conn.createStatement();
		rs=stmt.executeQuery(selectsql);
		while(rs.next())
		{
			String bname=rs.getString("username");
			if(library.user.getSname().equals("admin"))
			{
				System.out.println("您是最高权限，可随意进入任何模块");break;
			}
			if(bname.equals(library.user.getSname()))
			{
				System.out.println("恭喜您，此身份权限可以进入此模块");break;
			}
			else
			{
				System.out.println("抱歉，您的权限无法进入此模块");
				System.exit(0);
			}
		}
	}
	public static void kucun() throws ClassNotFoundException, SQLException
	{
		System.out.println("欢迎来到库存模块，你可以对库存明细进行查看，也可以查找具体图书的库存值。");
		Scanner a=new Scanner(System.in);
		System.out.println("1:查看库存明细");
		System.out.println("2:查找某本图书的库存值");
		int b=a.nextInt();
		switch(b)
		{
		case 1:mingxi();break;
		case 2:System.out.println("请输入你要查找的图书id");int bid=a.nextInt();sfind(bid);break;
		}
	}
	public static void mingxi() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String selectsql="SELECT * FROM stock";
		stmt=(Statement) conn.createStatement();
		rs=stmt.executeQuery(selectsql);
		while(rs.next())
		{
			String id=rs.getString("id");
			int bookid=rs.getInt("bookid");
			int sumnum=rs.getInt("sumnums");
			System.out.println(id+"  "+bookid+" "+sumnum);
			
		}
	}
	public static void sfind(int bid) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String findsql="SELECT * FROM stock WHERE bookid=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(findsql);
		ptmt.setInt(1, bid);
		rs=ptmt.executeQuery();
		while(rs.next())
		{
			String iid=rs.getString("id");
			int sum=rs.getInt("sumnums");
			System.out.println(iid+"  "+sum);
			
		}
	}

}
