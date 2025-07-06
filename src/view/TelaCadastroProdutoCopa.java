package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.ProdutoCopa;
import repository.ProdutoCopaRepository;


public class TelaCadastroProdutoCopa {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ProdutoCopaRepository repository = new ProdutoCopaRepository();
            CrudController<ProdutoCopa> controller = new CrudController<>(repository, ProdutoCopa.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
