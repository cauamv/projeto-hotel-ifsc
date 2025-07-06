package view;
import javax.swing.*;
import generator.CrudController;
import generator.CrudScreenGenerator;
import model.Fornecedor;
import repository.FornecedorRepository;

public class TelaCadastroFornecedor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Criar repositório e controlador
            FornecedorRepository repository = new FornecedorRepository();
            CrudController<Fornecedor> controller = new CrudController<>(repository, Fornecedor.class);
            
            // Gerar e exibir a tela CRUD
            JFrame crudScreen = CrudScreenGenerator.generateCrudScreen(controller);
            crudScreen.setVisible(true);
        });
    }
}