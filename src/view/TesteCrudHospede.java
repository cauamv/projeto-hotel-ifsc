package view;
import javax.swing.*;
import controller.HospedeRepository;
import generator.CrudController;
import generator.CrudScreenGenerator;
import model.Hospede;

public class TesteCrudHospede {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Criar repositório e controlador
            HospedeRepository repository = new HospedeRepository();
            CrudController<Hospede> controller = new CrudController<>(repository, Hospede.class);
            
            // Gerar e exibir a tela CRUD
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
}