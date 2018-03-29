package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;

class user {
	private static String sname=null;
	public static String getSname() {
		return sname;
	}
	public static void setSname(String shname) {
		sname = shname;
	}
	private static int id=0;
	
	public static int getId() {
		return id;
	}
	public static void setId(int iid) {
		id = iid;
	}
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost/stu?useSSL=false";
	public static String user="root";
	public static String password="";
	public static Statement stmt=null;
	public static ResultSet rs=null;
	public static void zhuce() throws SQLException, ClassNotFoundException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		Scanner a=new Scanner(System.in);
		System.out.println("请输入你要设置的用户名");
		String name=a.next();
		System.out.println("请输入你要设置的密码");
		String pwd=a.next();
		System.out.println("请输入你获得的权限编号  2：图书模块 3：进货模块 4：销售模块 5：库存模块");
		int t=a.nextInt();
		String insertsql="insert into users(username,password,type) values (?,?,?)";
		java.sql.PreparedStatement ptmt=conn.prepareStatement(insertsql);
		ptmt.setString(1, name);
		ptmt.setString(2, pwd);
		ptmt.setInt(3, t);
		ptmt.execute();
		System.out.println("已经添加了您的信息");
	}
	public static void denglu() throws SQLException, ClassNotFoundException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		Scanner a=new Scanner(System.in);
		System.out.println("请输入你的账号");
		String name=a.next();
		System.out.println("请输入你的密码");
		String pwd=a.next();
		setSname(name);
		String selectsql="SELECT username,password FROM users WHERE username=? AND password=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(selectsql);
		ptmt.setString(1, name);
		ptmt.setString(2, pwd);
		rs=ptmt.executeQuery();
		if(rs.next()){
			  System.out.println("登录成功！");
			  String findsql="SELECT * FROM users WHERE username=?";
			  PreparedStatement ntmt=(PreparedStatement) conn.prepareStatement(findsql);
			  ntmt.setString(1, name);
			  rs=ntmt.executeQuery();
			  while(rs.next())
				{
					int aid=rs.getInt("id");
					setId(aid);
				}
			  
			  setSname(name);
			 }else{
			  System.out.println("姓名或密码错误！\n请重新登录：");denglu();}
	}
	public static void update() throws ClassNotFoundException, SQLException
	{
		
		Scanner a=new Scanner(System.in);
		System.out.println("请根据编号选择你要进行的功能");
		System.out.println("1:删除");
		System.out.println("2:查找");
		System.out.println("3:修改");
		int aa=a.nextInt();
		switch(aa)
		{
		case 1:System.out.println("请输入要删除的用户名");String dname=a.next();delete(dname);break;
		case 2:System.out.println("请输入要查找的用户名");String aname=a.next();find(aname);break;
		case 3:System.out.println("请输入要修改的用户名");String pname=a.next();System.out.println("请输入要修改的权限");int nn=a.nextInt();upup(pname, nn);break;
		}
				
	}
	public static void delete(String uname) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String deletesql="DELETE FROM users WHERE username=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(deletesql);
		ptmt.setString(1, uname);
		ptmt.execute();
		System.out.println("删除成功！");
		
	}
	public static void find(String uname) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String findsql="SELECT * FROM users WHERE username=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(findsql);
		ptmt.setString(1, uname);
		rs=ptmt.executeQuery();
		while(rs.next())
		{
			String aname=rs.getString("username");
			int atype=rs.getInt("type");
			System.out.println(aname+"  "+atype);
			
		}
	}
	public static void upup(String uname,int i) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String upsql="UPDATE users SET type=? WHERE username=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(upsql);
		ptmt.setInt(1,i);
		ptmt.setString(2,uname);
		ptmt.execute();
		System.out.println("修改成功");
		

	}
	

}
