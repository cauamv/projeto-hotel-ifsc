package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.Servico;
import repository.ServicoRepository;



public class TelaCadastroServico {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Criar repositório e controlador
            ServicoRepository repository = new ServicoRepository();
            CrudController<Servico> controller = new CrudController<>(repository, Servico.class);
            
            // Gerar e exibir a tela CRUD
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
