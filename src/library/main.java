package library;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.UpdatableResultSet;

public class main {



	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Scanner a=new Scanner(System.in);
		System.out.println("1��ע��      2����¼");
		int ad=a.nextInt();
		switch(ad) {
		    case 1:library.user.zhuce();library.user.denglu();break;
			case 2:library.user.denglu();break;
			default :
				System.exit(0);
			}
		System.out.println("��������޸������û���Ϣ��������update�������˲�������skip");
		String aa=a.next();
		switch(aa)
		{
		case "update":library.user.update();break;
		case "skip":break;
		}
		System.out.println("ͨ�������ţ�ѡ����������ģ�顣");
		System.out.println("1:ͼ��ģ��");
		System.out.println("2:����ģ��");
		System.out.println("3:����ģ��");
		System.out.println("4:���ģ��");
		int nu=a.nextInt();
		switch (nu) {
		case 1:library.book.panduan();library.book.bookupdate();break;
		case 2:library.in.panduan();library.in.jinhuodan();break;
		case 3:library.outs.panduan();library.outs.xiaoshoudan();break;
		case 4:library.stock.panduan();library.stock.kucun();break;
		
	    

		default:
			break;
		}
		
					
				


	}

}
