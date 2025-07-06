/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.JFrame;
import javax.swing.UIManager;
import model.Fornecedor;
import model.Funcionario;
import model.Hospede;
import model.Marca;
import model.Modelo;
import model.Veiculo;
import model.ProdutoCopa;
import model.Servico;
import model.VagaEstacionamento;
import repository.FornecedorRepository;
import repository.FuncionarioRepository;
import repository.HospedeRepository;
import repository.MarcaRepository;
import repository.ModeloRepository;
import repository.VeiculoRepository;
import repository.ProdutoCopaRepository;
import repository.ServicoRepository;
import repository.VagaRepository;

public class TelaMenuPrincipal extends javax.swing.JFrame {

    public TelaMenuPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemProduto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFornecedor = new javax.swing.JMenuItem();
        jMenuItemHospede = new javax.swing.JMenuItem();
        jMenuItemFuncionario = new javax.swing.JMenuItem();
        jMenuItemServicos = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuVagaEstacionamento = new javax.swing.JMenuItem();
        jMenuMarcaVeiculo = new javax.swing.JMenuItem();
        jMenuItemModeloVeiculo = new javax.swing.JMenuItem();
        jMenuItemVeiculo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);

        jMenu2.setText("Cadastros");

        jMenuItemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Buy.png"))); // NOI18N
        jMenuItemProduto.setText("Produto Copa");
        jMenuItemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemProduto);
        jMenu2.add(jSeparator1);

        jMenuItemFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Delivery.png"))); // NOI18N
        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedorActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFornecedor);

        jMenuItemHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/People.png"))); // NOI18N
        jMenuItemHospede.setText("Hóspede");
        jMenuItemHospede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHospedeActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHospede);

        jMenuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Boss.png"))); // NOI18N
        jMenuItemFuncionario.setText("Funcionário");
        jMenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFuncionario);

        jMenuItemServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Phone.png"))); // NOI18N
        jMenuItemServicos.setText("Serviços");
        jMenuItemServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServicosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemServicos);
        jMenu2.add(jSeparator4);

        jMenuVagaEstacionamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Blue tag.png"))); // NOI18N
        jMenuVagaEstacionamento.setText("Vaga Estacionamento");
        jMenuVagaEstacionamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVagaEstacionamentoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuVagaEstacionamento);

        jMenuMarcaVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png"))); // NOI18N
        jMenuMarcaVeiculo.setText("Marca do Veiculo");
        jMenuMarcaVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMarcaVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuMarcaVeiculo);

        jMenuItemModeloVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png"))); // NOI18N
        jMenuItemModeloVeiculo.setText("Modelo do Veiculo");
        jMenuItemModeloVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModeloVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemModeloVeiculo);

        jMenuItemVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png"))); // NOI18N
        jMenuItemVeiculo.setText("Cadastro de Veiculo");
        jMenuItemVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemVeiculo);
        jMenu2.add(jSeparator2);

        jMenuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit.png"))); // NOI18N
        jMenuItemSair.setText("Sair");
        jMenuItemSair.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSair);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Movimentos");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatórios");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ajuda");

        jMenuItem2.setText("Sobre");
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItemHospedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHospedeActionPerformed
        HospedeRepository repository = new HospedeRepository();
        CrudController<Hospede> controller = new CrudController<>(repository, Hospede.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemHospedeActionPerformed

    private void jMenuItemProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutoActionPerformed
        ProdutoCopaRepository repository = new ProdutoCopaRepository();
        CrudController<ProdutoCopa> controller = new CrudController<>(repository, ProdutoCopa.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemProdutoActionPerformed

    private void jMenuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedorActionPerformed
        FornecedorRepository repository = new FornecedorRepository();
        CrudController<Fornecedor> controller = new CrudController<>(repository, Fornecedor.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemFornecedorActionPerformed

    private void jMenuItemServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServicosActionPerformed
        ServicoRepository repository = new ServicoRepository();
        CrudController<Servico> controller = new CrudController<>(repository, Servico.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemServicosActionPerformed

    private void jMenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuncionarioActionPerformed
        FuncionarioRepository repository = new FuncionarioRepository();
        CrudController<Funcionario> controller = new CrudController<>(repository, Funcionario.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemFuncionarioActionPerformed

    private void jMenuMarcaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMarcaVeiculoActionPerformed
        MarcaRepository repository = new MarcaRepository();
        CrudController<Marca> controller = new CrudController<>(repository, Marca.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuMarcaVeiculoActionPerformed

    private void jMenuVagaEstacionamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVagaEstacionamentoActionPerformed
        VagaRepository repository = new VagaRepository();
        CrudController<VagaEstacionamento> controller = new CrudController<>(repository, VagaEstacionamento.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuVagaEstacionamentoActionPerformed

    private void jMenuItemModeloVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModeloVeiculoActionPerformed
        ModeloRepository repository = new ModeloRepository();
        CrudController<Modelo> controller = new CrudController<>(repository, Modelo.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemModeloVeiculoActionPerformed

    private void jMenuItemVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVeiculoActionPerformed
        VeiculoRepository repository = new VeiculoRepository();
        CrudController<Veiculo> controller = new CrudController<>(repository, Veiculo.class);
        JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
        crudScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemVeiculoActionPerformed

    public static void main(String args[]) {
        // Traduz os botões padrão do JOptionPane para português
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar"); // <-- Já traduzi o "Cancelar" de bônus!
        UIManager.put("OptionPane.okButtonText", "OK");       // <-- E o "OK" também.
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemFornecedor;
    private javax.swing.JMenuItem jMenuItemFuncionario;
    private javax.swing.JMenuItem jMenuItemHospede;
    private javax.swing.JMenuItem jMenuItemModeloVeiculo;
    private javax.swing.JMenuItem jMenuItemProduto;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemServicos;
    private javax.swing.JMenuItem jMenuItemVeiculo;
    private javax.swing.JMenuItem jMenuMarcaVeiculo;
    private javax.swing.JMenuItem jMenuVagaEstacionamento;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
