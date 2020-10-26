package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCommentDAO {

	//DBにid,name,commentを加えるメソッド
	public AddCommentDAO(Board bo) {

		if(bo.getName().isEmpty()) {
			bo.setName("名無し");
		}
		if(bo.getComment().isEmpty()) {
			bo.setComment("コメント無し");
		}

		final String jdbcdId = "root";
		final String jdbcPass = "Goripanee0321@";
		final String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";

		try {
			Connection con = DriverManager.getConnection(jdbcUrl, jdbcdId, jdbcPass);
			PreparedStatement ps = con.prepareStatement("INSERT INTO board (name, comment) VALUES (?, ?)");

            System.out.println("AddCommentDAO_Connected.");

            ps.setString(1, bo.getName());
            ps.setString(2, bo.getComment());

            // ひな形を送信
            int r = ps.executeUpdate();

            if (r != 0) {
            	System.out.println(r + "件の書き込めを追加しました");
            } else {
            	System.out.println("書き込みできませんでした");
            }

            ps.close();

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("AddCommentDAO_Connection Failed.");

		}

	}
}