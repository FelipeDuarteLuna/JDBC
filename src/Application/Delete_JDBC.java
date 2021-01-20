package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Dao.Db;
import Exception.DbIntegrityException;

public class Delete_JDBC {
	public static void main(String[]args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Db.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM department "
					+"WHERE Id = ? ");
			
			st.setInt(1, 2);
			int rowsAffected = st.executeUpdate();
			System.out.println("Done ! Foi excluídos um total de linhas " + rowsAffected);
		}catch (DbIntegrityException | SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}
	}
}
