package Application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Dao.Db;
import Exception.DbException;

public class Program_Insert {
	public static void main(String[]args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Db.getConnection(); // Inciando uma conexão com o banco de dados
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "ze Ferreira");
			st.setString(2, "ze_ferreira@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));	
			st.setDouble(4, 3500.77);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			System.out.println("Done! Rows affected: " + rowsAffected);
					
			
		}catch(SQLException | ParseException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}
	}
}
