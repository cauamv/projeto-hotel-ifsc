package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import repository.VeiculoRepository;
import model.Veiculo;


public class TelaCadastroVeiculo {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            VeiculoRepository repository = new VeiculoRepository();
            CrudController<Veiculo> controller = new CrudController<>(repository, Veiculo.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
