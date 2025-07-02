package generator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

public class CrudScreenGenerator {
    
    public static <T> JFrame generateCrudScreen(CrudController<T> controller) {
        Class<T> entityClass = controller.getEntityClass();
        
        // Verificar se a classe tem a anotação @CrudEntity
        CrudEntity entityAnnotation = entityClass.getAnnotation(CrudEntity.class);
        String displayName = entityAnnotation != null && !entityAnnotation.displayName().isEmpty() 
            ? entityAnnotation.displayName() 
            : entityClass.getSimpleName();
        
        JFrame frame = new JFrame("CRUD - " + displayName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Painel principal dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Painel da tabela (lado esquerdo)
        JPanel tablePanel = createTablePanel(controller);
        
        // Painel do formulário (lado direito)
        JPanel formPanel = createFormPanel(controller);
        
        splitPane.setLeftComponent(new JScrollPane(tablePanel));
        splitPane.setRightComponent(formPanel);
        splitPane.setDividerLocation(500);
        
        frame.add(splitPane, BorderLayout.CENTER);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        
        return frame;
    }
    
    private static <T> JPanel createTablePanel(CrudController<T> controller) {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Criar tabela
        JTable table = new JTable();
        updateTable(table, controller);
        
        // Botões da tabela
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        
        refreshButton.addActionListener(e -> updateTable(table, controller));
        deleteButton.addActionListener(e -> deleteSelectedRecord(table, controller));
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private static <T> JPanel createFormPanel(CrudController<T> controller) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Formulário"));
        
        // Obter campos da classe
        List<Field> fields = getVisibleFields(controller.getEntityClass());
        
        // Criar componentes do formulário
        JPanel formFieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        Map<String, JComponent> fieldComponents = new HashMap<>();
        
        int row = 0;
        for (Field field : fields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            if (annotation != null && !annotation.editable()) continue;
            
            String label = annotation != null && !annotation.label().isEmpty() 
                ? annotation.label() 
                : formatFieldName(field.getName());
            
            gbc.gridx = 0;
            gbc.gridy = row;
            formFieldsPanel.add(new JLabel(label + ":"), gbc);
            
            JComponent component = createFieldComponent(field, annotation);
            fieldComponents.put(field.getName(), component);
            
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            formFieldsPanel.add(component, gbc);
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            
            row++;
        }
        
        // Botões do formulário
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Salvar");
        JButton clearButton = new JButton("Limpar");
        
        saveButton.addActionListener(e -> saveRecord(fieldComponents, controller));
        clearButton.addActionListener(e -> clearForm(fieldComponents));
        
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        
        panel.add(formFieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private static List<Field> getVisibleFields(Class<?> clazz) {
        List<Field> visibleFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        
        // Ordenar campos por ordem especificada na anotação
        Arrays.sort(fields, (f1, f2) -> {
            CrudField a1 = f1.getAnnotation(CrudField.class);
            CrudField a2 = f2.getAnnotation(CrudField.class);
            int order1 = a1 != null ? a1.order() : 0;
            int order2 = a2 != null ? a2.order() : 0;
            return Integer.compare(order1, order2);
        });
        
        for (Field field : fields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            if (annotation == null || annotation.visible()) {
                visibleFields.add(field);
            }
        }
        
        return visibleFields;
    }
    
    private static JComponent createFieldComponent(Field field, CrudField annotation) {
        String type = annotation != null ? annotation.type() : "TEXT";
        Class<?> fieldType = field.getType();
        
        switch (type.toUpperCase()) {
            case "PASSWORD":
                return new JPasswordField(20);
            case "NUMBER":
                if (fieldType == int.class || fieldType == Integer.class) {
                    return new JSpinner(new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
                } else if (fieldType == double.class || fieldType == Double.class) {
                    return new JSpinner(new SpinnerNumberModel(0.0, -Double.MAX_VALUE, Double.MAX_VALUE, 0.1));
                }
                return new JTextField(20);
            case "BOOLEAN":
                return new JCheckBox();
            case "DATE":
                return new JTextField(20); // Aqui você pode usar um JDatePicker se tiver
            default:
                return new JTextField(20);
        }
    }
    
    private static String formatFieldName(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)
                .replaceAll("([A-Z])", " $1");
    }
    
    private static <T> void updateTable(JTable table, CrudController<T> controller) {
        try {
            List<T> data = controller.findAll();
            List<Field> fields = getVisibleFields(controller.getEntityClass());
            
            // Criar modelo da tabela
            String[] columnNames = fields.stream()
                .map(f -> {
                    CrudField annotation = f.getAnnotation(CrudField.class);
                    return annotation != null && !annotation.label().isEmpty() 
                        ? annotation.label() 
                        : formatFieldName(f.getName());
                })
                .toArray(String[]::new);
            
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Adicionar dados
            for (T item : data) {
                Object[] row = new Object[fields.size()];
                for (int i = 0; i < fields.size(); i++) {
                    try {
                        fields.get(i).setAccessible(true);
                        row[i] = fields.get(i).get(item);
                    } catch (IllegalAccessException e) {
                        row[i] = "N/A";
                    }
                }
                model.addRow(row);
            }
            
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    private static <T> void saveRecord(Map<String, JComponent> fieldComponents, CrudController<T> controller) {
        try {
            T instance = controller.getEntityClass().getDeclaredConstructor().newInstance();
            
            for (Map.Entry<String, JComponent> entry : fieldComponents.entrySet()) {
                Field field = controller.getEntityClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                
                JComponent component = entry.getValue();
                Object value = getValueFromComponent(component, field.getType());
                
                if (value != null) {
                    field.set(instance, value);
                }
            }
            
            controller.create(instance);
            clearForm(fieldComponents);
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }
    
    private static Object getValueFromComponent(JComponent component, Class<?> targetType) {
        if (component instanceof JTextField) {
            String text = ((JTextField) component).getText();
            if (text.isEmpty()) return null;
            
            if (targetType == int.class || targetType == Integer.class) {
                return Integer.parseInt(text);
            } else if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(text);
            }
            return text;
        } else if (component instanceof JPasswordField) {
            return new String(((JPasswordField) component).getPassword());
        } else if (component instanceof JCheckBox) {
            return ((JCheckBox) component).isSelected();
        } else if (component instanceof JSpinner) {
            return ((JSpinner) component).getValue();
        }
        
        return null;
    }
    
    private static void clearForm(Map<String, JComponent> fieldComponents) {
        for (JComponent component : fieldComponents.values()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText("");
            } else if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            } else if (component instanceof JSpinner) {
                ((JSpinner) component).setValue(0);
            }
        }
    }
    
    private static <T> void deleteSelectedRecord(JTable table, CrudController<T> controller) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir este registro?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Assumindo que a primeira coluna é o ID
                    Object id = table.getValueAt(selectedRow, 0);
                    controller.delete(id);
                    updateTable(table, controller);
                    JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para excluir.");
        }
    }
}