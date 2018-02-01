package curso.mysql;


import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class Consultar2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from libros");
		
		while(rs.next()) {
		System.out.println("Título"+rs.getString("Titulo"));
		System.out.println("Precio"+rs.getFloat("Precio"));
		System.out.println("Precio"+rs.getDate("FechaPublicacion"));
		}
		
		int insertar = st.executeUpdate("insert into libros(Titulo) values ('El Quijote')");
			System.out.println("Fila insertada"+insertar);
		
		PreparedStatement pstmt = conn.prepareStatement("select * from libros where titulo = ?");
		pstmt.setString(1,"El Quijote");
		ResultSet rs1=pstmt.executeQuery();
		
		while(rs1.next()) {
		System.out.println("Título"+rs1.getString("Titulo"));
		System.out.println("Precio"+rs1.getFloat("Precio"));
		System.out.println("Precio"+rs1.getDate("FechaPublicacion"));
		}
		System.out.println("-----------------------------------------------------------");
		CallableStatement cstmt = conn.prepareCall("call listalibrosporautor(?)");
		cstmt.setString(1, "Benjamin  Aumaille");
		ResultSet rs2 = cstmt.executeQuery();
		
		while(rs2.next()) {
		System.out.println("Título"+rs2.getString("Titulo"));
		System.out.println("Precio"+rs2.getFloat("Precio"));
		System.out.println("Precio"+rs2.getDate("FechaPublicacion"));
		}
		
		
}
}