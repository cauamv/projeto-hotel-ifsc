package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.Modelo;
import repository.ModeloRepository;

public class TelaCadastroModelo{
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ModeloRepository repository = new ModeloRepository();
            CrudController<Modelo> controller = new CrudController<>(repository, Modelo.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
