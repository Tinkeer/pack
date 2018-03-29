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
		System.out.println("��������Ҫ���õ��û���");
		String name=a.next();
		System.out.println("��������Ҫ���õ�����");
		String pwd=a.next();
		System.out.println("���������õ�Ȩ�ޱ��  2��ͼ��ģ�� 3������ģ�� 4������ģ�� 5�����ģ��");
		int t=a.nextInt();
		String insertsql="insert into users(username,password,type) values (?,?,?)";
		java.sql.PreparedStatement ptmt=conn.prepareStatement(insertsql);
		ptmt.setString(1, name);
		ptmt.setString(2, pwd);
		ptmt.setInt(3, t);
		ptmt.execute();
		System.out.println("�Ѿ������������Ϣ");
	}
	public static void denglu() throws SQLException, ClassNotFoundException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		Scanner a=new Scanner(System.in);
		System.out.println("����������˺�");
		String name=a.next();
		System.out.println("�������������");
		String pwd=a.next();
		setSname(name);
		String selectsql="SELECT username,password FROM users WHERE username=? AND password=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(selectsql);
		ptmt.setString(1, name);
		ptmt.setString(2, pwd);
		rs=ptmt.executeQuery();
		if(rs.next()){
			  System.out.println("��¼�ɹ���");
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
			  System.out.println("�������������\n�����µ�¼��");denglu();}
	}
	public static void update() throws ClassNotFoundException, SQLException
	{
		
		Scanner a=new Scanner(System.in);
		System.out.println("����ݱ��ѡ����Ҫ���еĹ���");
		System.out.println("1:ɾ��");
		System.out.println("2:����");
		System.out.println("3:�޸�");
		int aa=a.nextInt();
		switch(aa)
		{
		case 1:System.out.println("������Ҫɾ�����û���");String dname=a.next();delete(dname);break;
		case 2:System.out.println("������Ҫ���ҵ��û���");String aname=a.next();find(aname);break;
		case 3:System.out.println("������Ҫ�޸ĵ��û���");String pname=a.next();System.out.println("������Ҫ�޸ĵ�Ȩ��");int nn=a.nextInt();upup(pname, nn);break;
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
		System.out.println("ɾ���ɹ���");
		
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
		System.out.println("�޸ĳɹ�");
		

	}
	

}
