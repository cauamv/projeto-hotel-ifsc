package view;

import generator.CrudController;
import generator.CrudRepository;
import generator.CrudScreenGenerator;
import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import model.*;
import repository.*;

public class TelaMenuPrincipal extends javax.swing.JFrame {

    public TelaMenuPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private <T> void openCrudScreen(Class<T> modelClass) {
        try {
            String repositoryClassName = "repository." + modelClass.getSimpleName() + "Repository";

            if (modelClass.equals(VagaEstacionamento.class)) {
                repositoryClassName = "repository.VagaEstacionamentoRepository";
            }

            Class<?> repositoryClass = Class.forName(repositoryClassName);
            CrudRepository<T> repository = (CrudRepository<T>) repositoryClass.getDeclaredConstructor().newInstance();
            CrudController<T> controller = new CrudController<>(repository, modelClass);
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao abrir a tela de cadastro para: " + modelClass.getSimpleName() + "\nVerifique se o repositório correspondente existe e segue a convenção de nomenclatura.",
                    "Erro de Configuração",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemProduto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemQuarto = new javax.swing.JMenuItem();
        jMenuItemHospede = new javax.swing.JMenuItem();
        jMenuItemServicos = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFornecedor = new javax.swing.JMenuItem();
        jMenuItemFuncionario = new javax.swing.JMenuItem();
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

        jMenu2.setText("Cadastros");

        jMenuItemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Buy.png")));
        jMenuItemProduto.setText("Produto Copa");
        jMenuItemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemProduto);
        jMenu2.add(jSeparator1);

        jMenuItemQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Key.png")));
        jMenuItemQuarto.setText("Quarto");
        jMenuItemQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuartoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemQuarto);

        jMenuItemHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/People.png")));
        jMenuItemHospede.setText("Hóspede");
        jMenuItemHospede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHospedeActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHospede);

        jMenuItemServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Phone.png")));
        jMenuItemServicos.setText("Serviços");
        jMenuItemServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServicosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemServicos);
        jMenu2.add(jSeparator5);

        jMenuItemFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Delivery.png")));
        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedorActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFornecedor);

        jMenuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Boss.png")));
        jMenuItemFuncionario.setText("Funcionário");
        jMenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemFuncionario);
        jMenu2.add(jSeparator4);

        jMenuVagaEstacionamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Blue tag.png")));
        jMenuVagaEstacionamento.setText("Vaga Estacionamento");
        jMenuVagaEstacionamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVagaEstacionamentoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuVagaEstacionamento);

        jMenuMarcaVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png")));
        jMenuMarcaVeiculo.setText("Marca do Veiculo");
        jMenuMarcaVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMarcaVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuMarcaVeiculo);

        jMenuItemModeloVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png")));
        jMenuItemModeloVeiculo.setText("Modelo do Veiculo");
        jMenuItemModeloVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModeloVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemModeloVeiculo);

        jMenuItemVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Car key.png")));
        jMenuItemVeiculo.setText("Cadastro de Veiculo");
        jMenuItemVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVeiculoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemVeiculo);
        jMenu2.add(jSeparator2);

        jMenuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit.png")));
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
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
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
    }//GEN-END:initComponents
    // </editor-fold>                        

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jMenuItemHospedeActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Hospede.class);
    }

    private void jMenuItemProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(ProdutoCopa.class);
    }

    private void jMenuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Fornecedor.class);
    }

    private void jMenuItemServicosActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Servico.class);
    }

    private void jMenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Funcionario.class);
    }

    private void jMenuMarcaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Marca.class);
    }

    private void jMenuVagaEstacionamentoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(VagaEstacionamento.class);
    }

    private void jMenuItemModeloVeiculoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Modelo.class);
    }

    private void jMenuItemVeiculoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Veiculo.class);
    }

    private void jMenuItemQuartoActionPerformed(java.awt.event.ActionEvent evt) {
        openCrudScreen(Quarto.class);
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        String appName = "Sistema de Gestão de Hotel v1.0";
        String devName = "Desenvolvido por: Arthur Souza Mendes e Cauã de Moraes Vieira";
        String linkText = "Visualizar Projeto no GitHub";
        String githubUrl = "https://github.com/cauamv/projeto-hotel-ifsc";

        JLabel appNameLabel = new JLabel(appName);
        JLabel devNameLabel = new JLabel(devName);
        appNameLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        JLabel linkLabel = new JLabel("<html><a href=''>" + linkText + "</a></html>");
        linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        linkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(githubUrl));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Object[] message = {
            appNameLabel,
            devNameLabel,
            new JSeparator(),
            linkLabel
        };

        JOptionPane.showMessageDialog(
                this,
                message,
                "Sobre o Sistema",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String args[]) {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.okButtonText", "OK");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify 
    // GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemFornecedor;
    private javax.swing.JMenuItem jMenuItemFuncionario;
    private javax.swing.JMenuItem jMenuItemHospede;
    private javax.swing.JMenuItem jMenuItemModeloVeiculo;
    private javax.swing.JMenuItem jMenuItemProduto;
    private javax.swing.JMenuItem jMenuItemQuarto;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemServicos;
    private javax.swing.JMenuItem jMenuItemVeiculo;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuMarcaVeiculo;
    private javax.swing.JMenuItem jMenuVagaEstacionamento;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    // End of variables declaration
    //GEN-END:variables
}
