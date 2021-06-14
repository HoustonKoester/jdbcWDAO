package jdbcwithdao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeptDAOImpl implements DeptDAO{

	@Override
	public void printMenu() {
		System.out.println("Choose an Option");
		System.out.println("1. List a deprtment");
		System.out.println("2. Add a deprtment");
		System.out.println("3. Update a deprtment");
		System.out.println("4. Modify a deprtment");
		System.out.println("5. Insert using StoredProcedure");
		System.out.println("7. Retrieve stored image");
	}

	@Override
	public void displayDept() {
		// TODO Auto-generated method stub
		String QUERY = "select deptno,dname,loc from dept";
		try(Connection con = ConnectionUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY))
		    {	
				while(rs.next()){
				int no = rs.getInt("deptno");
				String name = rs.getString("dname");
				String location = rs.getString("loc");
				System.out.println("Connection");
				System.out.println(no + ", " +name+ ", " +location );
			}//try
		} catch (SQLException e) {e.printStackTrace(); }
	}

	@Override
	public void insertDept() {
		// TODO Auto-generated method stub
		String QUERY = "insert into dept values(80, 'Marketing', 'Reston')";
		try(Connection con = ConnectionUtil.getConnection();
				Statement stmt = con.createStatement())
			{ 
			int result = stmt.executeUpdate(QUERY);
			System.out.println(" Result is :"+result);
		     if (result==1) 
		    	System.out.println(" Data Inserted");
		     else
		    	 System.out.println(" Issue in data insertion ");
		    } catch (SQLException e) {e.printStackTrace(); }
	}

	@Override
	public int deleteDept() {
		// TODO Auto-generated method stub
		int row=0;
		String QUERY = "Delete from dept where deptno = ?";
	     try (Connection conn = ConnectionUtil.getConnection();
	             java.sql.PreparedStatement preparedStatement = conn.prepareStatement(QUERY)) 
	     {		
	    	 preparedStatement.setInt(1, 80);
	        row = preparedStatement.executeUpdate();
	         // rows affected
	         System.out.println(row);
	    	 
	     }catch( SQLException e)
	     {
	    	 System.out.println(" Row cannot be deleted");
	     }
		   return row; 
	}

	@Override
	public int updateDept() {
		// TODO Auto-generated method stub
		int row=0;
		String QUERY = "UPDATE dept SET dname='IT80' WHERE deptno=?";
		 try (Connection conn = ConnectionUtil.getConnection();
	             java.sql.PreparedStatement preparedStatement = conn.prepareStatement(QUERY)) 
	     {	preparedStatement.setInt(1, 80);
	        row = preparedStatement.executeUpdate();
	         // rows affected
	         System.out.println(row);
	    	 
	     }catch( SQLException e)
	     {
	    	 System.out.println(" Row cannot be updated");
	     }
		   return row;
	}

	@Override
	public void retImage() {
		// TODO Auto-generated method stub
		 try (Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from imgtable")) 
	     {
		 
		ResultSet rs=ps.executeQuery();  
		if(rs.next()){//now on 1st row  
		              
		Blob b=rs.getBlob(2);//2 means 2nd column data  
		byte barr[]=b.getBytes(1,(int)b.length());//1 means first image  
		              
		FileOutputStream fout = null;
		fout = new FileOutputStream("../jdbcwithdao/g.jpg");

		fout.write(barr);

		fout.close();

	    	 System.out.println(" Row cannot be updated");
	     }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

	@Override
	public void setImage() {
		// TODO Auto-generated method stub
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into imgtable values(?,?)");  ){
		ps.setString(1,"sonoo");  
		FileInputStream fin = new FileInputStream("../jdbcwithdao/IMG_0732.JPG");

		ps.setBinaryStream(2,fin,fin.available());
		 ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	

