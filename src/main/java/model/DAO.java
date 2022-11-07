package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/bdlivraria?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	// Criar
	public void adicionar(JavaBeans livro) {
		String create = "insert  into tblivros(codlivro,titulo,autor,categoria,valor) values (?, ?, ?, ?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setInt(1, livro.getCodlivro());
			pst.setString(2, livro.getTitulo());
			pst.setString(3, livro.getAutor());
			pst.setString(4, livro.getCategoria());
			pst.setDouble(5, livro.getValor());
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Listar
	public ArrayList<JavaBeans> listar() {
		ArrayList<JavaBeans> livros = new ArrayList<>();

		String read = "select * from tblivros";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int codlivro = rs.getInt(1);
				String titulo = rs.getString(2);
				String autor = rs.getString(3);
				String categoria = rs.getString(4);
				double valor = rs.getDouble(5);

				livros.add(new JavaBeans(codlivro, titulo, autor, categoria, valor));
			}
			con.close();
			return livros;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Alterar
	public void selecionarLivro(JavaBeans livro) {
		String read2 = "select * from tblivros where codlivro = ? ";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, livro.getCodlivro());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				livro.setCodlivro(rs.getInt(1));
				livro.setTitulo(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setCategoria(rs.getString(4));
				livro.setValor(rs.getDouble(5));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Editar
	public void alterarLivro(JavaBeans livro) {
		String create = "update tblivros set titulo=?, autor=?, categoria=?, valor=? where codlivro=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, livro.getTitulo());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getCategoria());
			pst.setDouble(4, livro.getValor());
			pst.setInt(5, livro.getCodlivro());

			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	// Deletar
	public void deletarLivro(JavaBeans livro) {
		String delete = "delete from tblivros where codlivro=?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, livro.getCodlivro());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
