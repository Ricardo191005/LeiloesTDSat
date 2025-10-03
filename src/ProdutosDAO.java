/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        return listagem;
    }

    public boolean venderProduto(int idProduto) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, idProduto);
            int linhasAfetadas = prep.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setPreco(resultset.getDouble("preco"));
                p.setStatus(resultset.getString("status"));
                vendidos.add(p);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
        return vendidos;
    }
}

