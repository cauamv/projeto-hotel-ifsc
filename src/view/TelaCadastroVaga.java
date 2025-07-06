package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.VagaEstacionamento;
import repository.VagaRepository;


public class TelaCadastroVaga {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            VagaRepository repository = new VagaRepository();
            CrudController<VagaEstacionamento> controller = new CrudController<>(repository, VagaEstacionamento.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
