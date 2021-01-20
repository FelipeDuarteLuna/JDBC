package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.Db;
import Exception.DbException;

public class Program {
	public static void main(String[]args) {
		
		//Connection conn = Db.getConnection();
		Connection conn  = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			conn = Db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + " ," +rs.getString("Name"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}finally {
			Db.closeResult(rs);
			Db.closeStatement(st);
			Db.closeConnection();
		}
		
		
	}

}
