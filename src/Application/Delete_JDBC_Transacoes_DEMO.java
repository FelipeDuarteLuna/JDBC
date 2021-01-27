package Application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.Db;
import Exception.DbException;

public class Delete_JDBC_Transacoes_DEMO {
	public static void main(String[]args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = Db.getConnection();
			
			conn.setAutoCommit(false);// N�o � para confirma as opera��es automaticamentes. As opera��es ficara pendente, pela confirma��o do programador.
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 where DepartmentId = 1");
			
			/*int x = 1;
			if (x <2) {
				throw new SQLException("Fake Error");
			}*/
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 where DepartmentId = 2");
			
			conn.commit();// Confirma��o que a Transa��o confirmo e pode executar os comandos no banco.
			
			System.out.println("Rows1 " + rows1);
			System.out.println("Rows1 " + rows2);
		}catch(SQLException e) {
			try {
				conn.rollback(); // Voltar ao estado inicial do banco e n�o permite a execu��o das opera��es pendentes.
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new DbException("Error tryin to rollback! Caused by:" + e1.getMessage()); 
			}
		}finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}
	}
}
