package view;
import generator.CrudController;
import generator.CrudScreenGenerator;
import javax.swing.*;
import model.Hospede;
import model.Marca;
import repository.HospedeRepository;
import repository.MarcaRepository;

public class TelaCadastroMarca {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Criar repositório e controlador
            MarcaRepository repository = new MarcaRepository();
            CrudController<Marca> controller = new CrudController<>(repository, Marca.class);
            
            // Gerar e exibir a tela CRUD
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
  }
