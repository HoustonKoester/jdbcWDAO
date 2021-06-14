package jdbcwithdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import com.mysql.jdbc.PreparedStatement;

public class TestJDBCOracleOnAWS {
	public static void main(String[] args) {
		int choice=0;
		DeptDAOImpl deptimp = new DeptDAOImpl();
		
		do {
			deptimp.printMenu();
			
			Scanner sc=new Scanner(System.in);
			choice = sc.nextInt();
		switch (choice) {
		case 1:
		{  
			deptimp.displayDept();
			break;
		}
		case 2: 
		{
			deptimp.insertDept();
			break;
		}
		case 3:
		{	
			deptimp.updateDept();
			break;
		}
		case 4: 
		{
			deptimp.deleteDept();
			break;
		}
		case 5: 
		{	
			
			insertUsingProcedure();
			 System.out.println("Row inserted using procedure");
			break;
		
		}
		case 7:
		{
			deptimp.retImage();
		}
	}	
	}while(choice!=6);
	}
	
	
	private static void insertUsingProcedure() {
		 try (Connection conn = ConnectionUtil.getConnection();
		 CallableStatement stmt=conn.prepareCall("{call INSERTROWS(?,?,?)}"))
	     {	 stmt.setInt(1,78);  
			 stmt.setString(2,"dept78");  
			 stmt.setString(3,"loc78");
			 stmt.execute();
	     } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}