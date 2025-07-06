package view;
import javax.swing.*;
import repository.HospedeRepository;
import generator.CrudController;
import generator.CrudScreenGenerator;
import model.Hospede;

public class TelaCadastroHospede {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            HospedeRepository repository = new HospedeRepository();
            CrudController<Hospede> controller = new CrudController<>(repository, Hospede.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
}