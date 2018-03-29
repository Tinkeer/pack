package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

class book {
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
		String selectsql="SELECT * FROM users WHERE type=2";
		stmt=(Statement) conn.createStatement();
		rs=stmt.executeQuery(selectsql);
		while(rs.next())
		{
			String bname=rs.getString("username");
			if(library.user.getSname().equals("admin"))
			{
				System.out.println("�������Ȩ�ޣ�����������κ�ģ��");break;
			}
			if(bname.equals(library.user.getSname()))
			{
				System.out.println("��ϲ���������Ȩ�޿��Խ����ģ��");break;
			}
			else
			{
				System.out.println("��Ǹ������Ȩ���޷������ģ��");
				System.exit(0);
			}
		}
	}
	public static void bookupdate() throws ClassNotFoundException, SQLException
	{
		System.out.println("��ӭ����ͼ��ģ�飬����Զ�ͼ����Ϣ������ɾ��ġ�");
		Scanner a=new Scanner(System.in);
		System.out.println("1:����");
		System.out.println("2:ɾ��");
		System.out.println("3:����");
		System.out.println("4����");
		int b=a.nextInt();
		switch(b)
		{
		case 1:System.out.println("��������Ҫ��ӵ�ͼ����");String bname=a.next();
		System.out.println("��������Ҫ��ӵĽ����۸�");double i=a.nextDouble();
		System.out.println("��������Ҫ��ӵ������۸�");double o=a.nextDouble();
		binsert(bname,i,o);break;
		case 2:System.out.println("��������Ҫɾ����ͼ����");String dname=a.next();bdelete(dname);break;
		case 3:System.out.println("��������Ҫ���ҵ�ͼ����");String fname=a.next();bfind(fname);break;
		case 4:System.out.println("��������Ҫ���ĵ�ͼ����");String uname=a.next();
		System.out.println("��������Ҫ���ĵĽ�����");double inn=a.nextDouble();
		System.out.println("��������Ҫ���ĵ�������");double sall=a.nextDouble();
		bupdate(uname, inn, sall);break;
		}
	}
	public static void binsert(String iname,double sin,double sout) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String insertsql="INSERT INTO bookuse(bookname,inprice,saleprice) values(?,?,?)";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(insertsql);
		ptmt.setString(1, iname);
		ptmt.setDouble(2, sin);
		ptmt.setDouble(3, sout);
		ptmt.execute();
		System.out.println("�Ѿ������������Ϣ");
	}
	public static void bdelete(String dname) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String deletesql="DELETE FROM bookuse WHERE bookname=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(deletesql);
		ptmt.setString(1, dname);
		ptmt.execute();
		System.out.println("ɾ���ɹ���");
	}
	public static void bfind(String fname) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String findsql="SELECT * FROM bookuse WHERE bookname=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(findsql);
		ptmt.setString(1, fname);
		rs=ptmt.executeQuery();
		while(rs.next())
		{
			String aname=rs.getString("bookname");
			double iinprice=rs.getInt("inprice");
			double isaleprice=rs.getInt("saleprice");
			System.out.println(aname+"  "+iinprice+" "+isaleprice);
			
		}
	}
	public static void bupdate(String uname,double in,double sale) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		String upsql="UPDATE bookuse SET inprice=?,saleprice=? WHERE bookname=?";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(upsql);
		ptmt.setDouble(1, in);
		ptmt.setDouble(2, sale);
		ptmt.setString(3, uname);
		ptmt.execute();
		System.out.println("�޸ĳɹ�");
	}

}
