package generator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

public class CrudScreenGenerator {

    public static <T> JFrame generateCrudScreen(CrudController<T> controller) {
        Class<T> entityClass = controller.getEntityClass();

        CrudEntity entityAnnotation = entityClass.getAnnotation(CrudEntity.class);
        String displayName = entityAnnotation != null && !entityAnnotation.displayName().isEmpty()
                ? entityAnnotation.displayName()
                : entityClass.getSimpleName();

        JFrame frame = new JFrame("CRUD - " + displayName);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTable table = new JTable();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        JPanel tablePanel = createTablePanel(table, controller);
        JPanel formPanel = createFormPanel(table, controller);

        JScrollPane formScrollPane = new JScrollPane(formPanel);
        formScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        splitPane.setLeftComponent(new JScrollPane(tablePanel));
        splitPane.setRightComponent(formScrollPane);
        splitPane.setDividerLocation(600);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private static <T> JPanel createTablePanel(JTable table, CrudController<T> controller) {
        JPanel panel = new JPanel(new BorderLayout());

        updateTable(table, controller);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton editButton = new JButton("Editar");

        refreshButton.addActionListener(e -> updateTable(table, controller));
        deleteButton.addActionListener(e -> deleteSelectedRecord(table, controller));
        editButton.addActionListener(e -> editSelectedRecord(table, controller));

        buttonPanel.add(refreshButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static <T> JPanel createFormPanel(JTable table, CrudController<T> controller) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Formulário"));

        List<Field> fields = getAllFieldsFromClassHierarchy(controller.getEntityClass());

        System.out.println("=== CAMPOS ENCONTRADOS ===");
        for (Field field : fields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            String label = annotation != null && !annotation.label().isEmpty()
                    ? annotation.label()
                    : formatFieldName(field.getName());
            System.out.println("Campo: " + field.getName() + " | Label: " + label + " | Classe: " + field.getDeclaringClass().getSimpleName());
        }
        System.out.println("=========================");

        JPanel formFieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        Map<String, JComponent> fieldComponents = new HashMap<>();

        int row = 0;
        for (Field field : fields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            if (annotation != null && !annotation.editable()) {
                continue;
            }

            String label = annotation != null && !annotation.label().isEmpty()
                    ? annotation.label()
                    : formatFieldName(field.getName());

            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;

            JLabel fieldLabel = new JLabel(label + ":");
            if (annotation != null && annotation.required()) {
                fieldLabel.setText(label + " *:");
                fieldLabel.setForeground(Color.RED);
            }
            formFieldsPanel.add(fieldLabel, gbc);

            JComponent component = createFieldComponent(field, annotation);
            fieldComponents.put(field.getName(), component);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            formFieldsPanel.add(component, gbc);

            row++;
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Salvar");
        JButton clearButton = new JButton("Limpar");

        saveButton.addActionListener(e -> saveRecord(table, fieldComponents, controller));
        clearButton.addActionListener(e -> clearForm(fieldComponents));

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        panel.add(formFieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static List<Field> getAllFieldsFromClassHierarchy(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            System.out.println("Analisando classe: " + currentClass.getSimpleName());
            Field[] declaredFields = currentClass.getDeclaredFields();
            System.out.println("Campos declarados em " + currentClass.getSimpleName() + ": " + declaredFields.length);
            for (Field field : declaredFields) {
                if (!field.isSynthetic()) {
                    System.out.println("  -> Adicionando campo: " + field.getName() + " (Classe: " + currentClass.getSimpleName() + ")");
                    allFields.add(field);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        allFields.sort((f1, f2) -> {
            CrudField a1 = f1.getAnnotation(CrudField.class);
            CrudField a2 = f2.getAnnotation(CrudField.class);
            int order1 = a1 != null ? a1.order() : Integer.MAX_VALUE;
            int order2 = a2 != null ? a2.order() : Integer.MAX_VALUE;
            if (order1 != order2) {
                return Integer.compare(order1, order2);
            }
            return f1.getName().compareTo(f2.getName());
        });
        return allFields;
    }

    private static JComponent createFieldComponent(Field field, CrudField annotation) {
        if (annotation != null && annotation.mask()) {
            String maskType = annotation.typeMask();
            String maskPattern = "";

            switch (maskType.toUpperCase()) {
                case "CPF":
                    maskPattern = "###.###.###-##";
                    break;

                case "CNPJ":
                    maskPattern = "##.###.###/####-##";
                    break;

                case "TELEFONE":
                    maskPattern = "(##) #####-####";
                    break;

                case "CEP":
                    maskPattern = "#####-###";
                    break;

                case "RG":
                    maskPattern = "##.###.###-#";
                    break;

                case "DATE":
                    maskPattern = "##/##/####";
                    break;
            }

            if (!maskPattern.isEmpty()) {
                try {
                    MaskFormatter formatter = new MaskFormatter(maskPattern);
                    formatter.setPlaceholderCharacter('_');
                    return new JFormattedTextField(formatter);
                } catch (java.text.ParseException e) {
                }
            }
        }

        String type = annotation != null ? annotation.type() : "TEXT";
        Class<?> fieldType = field.getType();
        int maxLength = annotation != null ? annotation.maxLength() : 0;

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
            case "CHAR":
                JTextField charField = new JTextField(1);
                charField.setDocument(new javax.swing.text.PlainDocument() {
                    @Override
                    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                        if (str != null && (getLength() + str.length()) <= 1) {
                            super.insertString(offset, str.toUpperCase(), attr);
                        }
                    }
                });
                return charField;
            case "DATE":
                JTextField dateField = new JTextField(20);
                dateField.setToolTipText("Formato: dd/MM/yyyy");
                return dateField;
            default:
                JTextField textField = new JTextField(20);
                if (maxLength > 0) {
                    textField.setDocument(new javax.swing.text.PlainDocument() {
                        @Override
                        public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                            if (str != null && (getLength() + str.length()) <= maxLength) {
                                super.insertString(offset, str, attr);
                            }
                        }
                    });
                }
                return textField;
        }
    }

    private static String formatFieldName(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)
                .replaceAll("([A-Z])", " $1");
    }

    private static <T> void updateTable(JTable table, CrudController<T> controller) {
        try {
            List<T> data = controller.findAll();
            List<Field> fields = getAllFieldsFromClassHierarchy(controller.getEntityClass());

            List<Field> tableFields = fields.stream()
                    .filter(f -> {
                        CrudField annotation = f.getAnnotation(CrudField.class);
                        return annotation != null && annotation.showInTable();
                    })
                    .sorted((f1, f2) -> {
                        CrudField a1 = f1.getAnnotation(CrudField.class);
                        CrudField a2 = f2.getAnnotation(CrudField.class);
                        int order1 = a1 != null ? a1.tableOrder() : Integer.MAX_VALUE;
                        int order2 = a2 != null ? a2.tableOrder() : Integer.MAX_VALUE;
                        if (order1 != order2) {
                            return Integer.compare(order1, order2);
                        }
                        return f1.getName().compareTo(f2.getName());
                    })
                    .collect(Collectors.toList());

            System.out.println("=== CAMPOS NA TABELA ===");
            for (Field field : tableFields) {
                CrudField annotation = field.getAnnotation(CrudField.class);
                String label = annotation.label().isEmpty() ? formatFieldName(field.getName()) : annotation.label();
                System.out.println("Campo tabela: " + field.getName() + " | Label: " + label + " | Order: " + annotation.tableOrder());
            }
            System.out.println("========================");

            String[] columnNames = tableFields.stream()
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

            for (T item : data) {
                Object[] row = new Object[tableFields.size()];
                for (int i = 0; i < tableFields.size(); i++) {
                    try {
                        tableFields.get(i).setAccessible(true);
                        Object value = tableFields.get(i).get(item);
                        row[i] = value;
                    } catch (IllegalAccessException e) {
                        row[i] = "N/A";
                    }
                }
                model.addRow(row);
            }

            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static <T> void saveRecord(JTable table, Map<String, JComponent> fieldComponents, CrudController<T> controller) {
        try {
            T instance = controller.getEntityClass().getDeclaredConstructor().newInstance();
            List<Field> allFields = getAllFieldsFromClassHierarchy(controller.getEntityClass());
            for (Field field : allFields) {
                if (fieldComponents.containsKey(field.getName())) {
                    field.setAccessible(true);
                    JComponent component = fieldComponents.get(field.getName());
                    // Chamada para o método alterado
                    Object value = getValueFromComponent(component, field.getType());
                    if (value != null) {
                        field.set(instance, value);
                    }
                }
            }
            try {
                Field dataCadastroField = findFieldInHierarchy(controller.getEntityClass(), "dataCadastro");
                if (dataCadastroField != null) {
                    dataCadastroField.setAccessible(true);
                    String currentDate = dataCadastroField.get(instance) != null
                            ? (String) dataCadastroField.get(instance) : "";
                    if (currentDate == null || currentDate.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        dataCadastroField.set(instance, sdf.format(new java.util.Date()));
                    }
                }
            } catch (Exception e) {
                System.out.println("Aviso: Não foi possível definir data de cadastro: " + e.getMessage());
            }

            controller.create(instance);
            clearForm(fieldComponents);

            updateTable(table, controller);

            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Field findFieldInHierarchy(Class<?> clazz, String fieldName) {
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            try {
                return currentClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        return null;
    }

    private static Object getValueFromComponent(JComponent component, Class<?> targetType) {
        if (component instanceof JTextField) {
            String text = ((JTextField) component).getText().trim();
            if (text.isEmpty()) {
                if (targetType == char.class) {
                    return 'A'; 
                }           
                return null;
            }

            if (targetType == int.class || targetType == Integer.class) {
                try {
                    return Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    return 0; 
                }
            } else if (targetType == double.class || targetType == Double.class) {
                try {
                    return Double.parseDouble(text.replace(',', '.'));
                } catch (NumberFormatException e) {
                    return 0.0; 
                }
            } else if (targetType == float.class || targetType == Float.class) {
                try {
                    return Float.parseFloat(text.replace(',', '.'));
                } catch (NumberFormatException e) {
                    return 0.0f; 
                }
            } else if (targetType == char.class) {
                return text.length() > 0 ? text.charAt(0) : 'A';
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

    private static <T> void editSelectedRecord(JTable table, CrudController<T> controller) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Object id = table.getValueAt(selectedRow, 0);
                T record = controller.findById(id);
                if (record != null) {
                    JOptionPane.showMessageDialog(null,
                            "Funcionalidade de edição será implementada.\nRegistro ID: " + id);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar registro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para editar.");
        }
    }
}
