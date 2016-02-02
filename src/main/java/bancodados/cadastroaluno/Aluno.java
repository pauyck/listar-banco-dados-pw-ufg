package bancodados.cadastroaluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
	private String matricula;
	private static String url = "jdbc:derby:banco-de-teste;create=true";

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String fone;

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void incluir() {
		try {
			// Obtém a conexão.
			Connection conn = DriverManager.getConnection(url);
			// Cria a sentença SQL.
			String sql = "insert into aluno (matricula, nome, fone, cpf) values (?, ?, ?, ?)";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, matricula);
			prepareStatement.setString(2, nome);
			prepareStatement.setString(3, fone);
			prepareStatement.setString(4, cpf);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			// Fecha a sentença.
			prepareStatement.close();
			// Fecha a conexão.
			conn.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public void alterar() {
		try {
			// Obtém a conexão.
			Connection conn = DriverManager.getConnection(url);
			// Cria a sentença SQL.
			String sql = "update aluno set nome=?, fone=?, cpf=? where matricula=?";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, nome);
			prepareStatement.setString(2, fone);
			prepareStatement.setString(3, cpf);
			prepareStatement.setString(4, matricula);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			// Fecha a sentença.
			prepareStatement.close();
			// Fecha a conexão.
			conn.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public void remover() {
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql = "delete from aluno where matricula=?";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, matricula);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public static List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql;
			PreparedStatement prepareStatement;
			sql = "select * from aluno order by matricula";
			prepareStatement = conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getString(1));
				aluno.setNome(rs.getString(2));
				aluno.setFone(rs.getString(3));
				aluno.setCpf(rs.getString(4));

				alunos.add(aluno);
			}
			rs.close();
			prepareStatement.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
		return alunos;
	}
}