package view;
import javax.swing.*;
import repository.FuncionarioRepository;
import generator.CrudController;
import generator.CrudScreenGenerator;
import model.Funcionario;
import model.Hospede;

public class TelaCadastroFuncionario {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            FuncionarioRepository repository = new FuncionarioRepository();
            CrudController<Funcionario> controller = new CrudController<>(repository, Funcionario.class);
            
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
}