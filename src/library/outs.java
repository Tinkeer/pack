package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

class outs {
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost/stu?useSSL=false";
	public static String user="root";
	public static String password="";
	public static Statement stmt=null;
	public static ResultSet rs=null;
	private static int outoutid;
	public static int getOutoutid() {
		return outoutid;
	}
	public static void setOutoutid(int outid) {
		outoutid = outid;
	}
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
	public static void xiaoshoudan() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,password);
		System.out.println("���Ѿ���������ģ��");
		System.out.println("����д���۵�");
		System.out.println("����д����ʱ��");
		Scanner a=new Scanner(System.in);
		double outtime=a.nextDouble();
		String oinsertsql="INSERT INTO outmain(outdate,outuserid) values(?,?)";
		PreparedStatement ptmt=(PreparedStatement) conn.prepareStatement(oinsertsql);
		ptmt.setDouble(1, outtime);
		ptmt.setInt(2, library.user.getId());
		ptmt.execute();
		System.out.println("���۹������ɳɹ�");
		String iifindsql="SELECT * FROM outmain WHERE outdate=?";
		PreparedStatement ztmt=(PreparedStatement) conn.prepareStatement(iifindsql);
		ztmt.setDouble(1, outtime);
		rs=ztmt.executeQuery();
		while(rs.next())
		{
			int oooid=rs.getInt("id");
			setOutoutid(oooid);

			
		}
		
		System.out.println("��������д������ϸ��ϸ");
		System.out.println("����д����ͼ����");
		int nbookid=a.nextInt();
		System.out.println("����д��������");
		int bnum=a.nextInt();
		String minsertsql="INSERT INTO outdetail(outid,bookid,sumnum,summoney) values(?,?,?,?)";
		String findsql="SELECT * FROM bookuse WHERE id=?";
		PreparedStatement atmt=(PreparedStatement) conn.prepareStatement(minsertsql);
		PreparedStatement btmt=(PreparedStatement) conn.prepareStatement(findsql);
		btmt.setInt(1, nbookid);
		rs=btmt.executeQuery();
		double nnp=0;
		double nnpsum=0;
		while(rs.next())
		{
			double ininprice=rs.getDouble("saleprice");
			nnp=ininprice;
		}
		nnpsum=nnp*bnum;
		atmt.setInt(1, getOutoutid());
		atmt.setInt(2, nbookid);
		atmt.setInt(3, bnum);
		atmt.setDouble(4,nnpsum );
		atmt.execute();
		System.out.println("����ͬ�����");
		String sinsertsql="UPDATE stock SET sumnums=sumnums-? WHERE bookid=?";
		PreparedStatement ctmt=(PreparedStatement) conn.prepareStatement(sinsertsql);
		ctmt.setInt(1, bnum);
		ctmt.setInt(2, nbookid);
		ctmt.execute();
		System.out.println("�����ɹ�");
	}

}
