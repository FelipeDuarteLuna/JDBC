package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import Dao.Db;
import Exception.DbException;

public class Update_JDBC {
	public static void main(String[]args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = Db.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller"
					+ " set BaseSalary = BaseSalary + ? "
					+ " Where (DepartmentId = ?) ");
			
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowsAffected  = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}
		
	}

}
