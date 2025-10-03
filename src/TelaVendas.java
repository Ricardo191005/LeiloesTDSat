/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.util.ArrayList;

public class TelaVendas extends JFrame {

    private final JTable tabela;
    private final ProdutosDAO dao = new ProdutosDAO();

    public TelaVendas() {
        setTitle("Produtos Vendidos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Busca produtos vendidos
        ArrayList<ProdutosDTO> vendidos = dao.listarProdutosVendidos();
        
        // Colunas da tabela
        String[] colunas = {"ID", "Nome", "Preço", "Status"};
        Object[][] dados = new Object[vendidos.size()][4];

        for (int i = 0; i < vendidos.size(); i++) {
            ProdutosDTO p = vendidos.get(i);
            dados[i][0] = p.getId();
            dados[i][1] = p.getNome();
            dados[i][2] = p.getPreco();
            dados[i][3] = p.getStatus();
        }

        tabela = new JTable(dados, colunas);
        add(new JScrollPane(tabela));
    }
}
