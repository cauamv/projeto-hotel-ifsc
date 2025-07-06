package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.Quarto;
import repository.QuartoRepository;



public class TelaCadastroQuarto{
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            QuartoRepository repository = new QuartoRepository();
            CrudController<Quarto> controller = new CrudController<>(repository, Quarto.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
