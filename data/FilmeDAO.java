/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cenaflix.data;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmeDAO {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix", "root", "root");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public int salvar(Filme filme) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO filmes (nome, datalancamento, categoria) VALUES(?,?,?)");
            st.setString(1, filme.getNome());
            st.setString(2, filme.getDataLancamento());
            st.setString(3, filme.getCategoria());
            status = st.executeUpdate();
            return status; // retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public Filme consultar(String nome) {

        try {
            Filme filme = new Filme();
            st = conn.prepareStatement("SELECT * from filmes WHERE nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();
            // verificar se a consulta encontrou o filme com o nome informado
            if (rs.next()) { // se encontrou o filme, vamos carregar os dados
                filme.setNome(rs.getString("nome"));
                filme.setDataLancamento(rs.getString("datalancamento"));
                filme.setCategoria(rs.getString("categoria"));
                return filme;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }

    public boolean excluir(String nome) {
        try {
            st = conn.prepareStatement("DELETE FROM filmes WHERE nome = ?");
            st.setString(1, nome);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public int atualizar(Filme filme) {
        int status;
        try {
            st = conn.prepareStatement("UPDATE filmes SET dataLancamento = ?, categoria = ? where nome = ?");
            st.setString(1, filme.getDataLancamento());
            st.setString(2, filme.getCategoria());
            st.setString(3, filme.getNome());
            status = st.executeUpdate();
            return status; // retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            // pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
}
