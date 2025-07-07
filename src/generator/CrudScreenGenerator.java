package generator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.util.Locale;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

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
        JPanel formFieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        Map<String, JComponent> fieldComponents = new HashMap<>();
        int row = 0;
        for (Field field : fields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            if (annotation != null && !annotation.visible()) {
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

            if (field.getName().equals("id") && annotation != null && !annotation.editable()) {
                try {
                    List<T> allItems = controller.findAll();
                    int nextId = allItems.size() + 1;
                    if (component instanceof JSpinner) {
                        ((JSpinner) component).setValue(nextId);
                    } else if (component instanceof JTextField) {
                        ((JTextField) component).setText(String.valueOf(nextId));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (annotation != null && !annotation.editable()) {
                component.setEnabled(false);
            }
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
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if (!field.isSynthetic()) {
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
                case "VALOR":
                    try {
                        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                        NumberFormatter formatter = new NumberFormatter(format);
                        formatter.setAllowsInvalid(false);
                        formatter.setOverwriteMode(true);
                        JFormattedTextField valueField = new JFormattedTextField(formatter);
                        valueField.setColumns(20);
                        valueField.setValue(0.00);
                        valueField.setHorizontalAlignment(JTextField.RIGHT);
                        return valueField;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new JTextField(20);
                    }
                case "METRAGEM":
                    try {
                        NumberFormat format = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
                        format.setMaximumFractionDigits(2);
                        format.setMinimumFractionDigits(2);
                        if (format instanceof java.text.DecimalFormat) {
                            ((java.text.DecimalFormat) format).setPositiveSuffix(" m²");
                            ((java.text.DecimalFormat) format).setNegativeSuffix(" m²");
                        }
                        NumberFormatter formatter = new NumberFormatter(format);
                        formatter.setAllowsInvalid(false);
                        JFormattedTextField metragemField = new JFormattedTextField(formatter);
                        metragemField.setColumns(20);
                        metragemField.setValue(0.00);
                        metragemField.setHorizontalAlignment(JTextField.RIGHT);
                        return metragemField;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new JTextField(20);
                    }
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
                    e.printStackTrace();
                }
            }
        }

        String type = annotation != null ? annotation.type() : "TEXT";
        switch (type.toUpperCase()) {
            case "PASSWORD":
                return new JPasswordField(20);
            case "COMBOBOX":
                if (annotation.items().length > 0) {
                    return new JComboBox<>(annotation.items());
                }
                try {
                    Class<?> itemsClass = annotation.itemsClass();
                    if (itemsClass != Object.class) {
                        String repositoryClassName = "repository." + itemsClass.getSimpleName() + "Repository";
                        Class<?> repositoryClass = Class.forName(repositoryClassName);
                        CrudRepository<?> itemRepository = (CrudRepository<?>) repositoryClass.getDeclaredConstructor().newInstance();
                        List<?> items = itemRepository.findAll();
                        return new JComboBox<>(items.toArray());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new JComboBox<>();
                }
                break;
            case "STATUS_CHAR":
                JComboBox<StatusOption> statusComboBox = new JComboBox<>();
                statusComboBox.addItem(new StatusOption("Ativo", 'A'));
                statusComboBox.addItem(new StatusOption("Inativo", 'I'));
                return statusComboBox;
            case "NUMBER":
                Class<?> fieldType = field.getType();
                if (fieldType == int.class || fieldType == Integer.class) {
                    // Pega o valor mínimo da anotação. O padrão na anotação é 0.
                    int minValue = (annotation != null) ? annotation.minValue() : 0;

                    // Usa o minValue para configurar o JSpinner, com o valor inicial e o mínimo sendo o mesmo.
                    return new JSpinner(new SpinnerNumberModel(minValue, minValue, Integer.MAX_VALUE, 1));

                } else if (fieldType == double.class || fieldType == Double.class) {
                    // Mantendo o mínimo 0 para double, mas pode ser aprimorado da mesma forma.
                    return new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1));
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
                JFormattedTextField dateField = new JFormattedTextField();
                try {
                    MaskFormatter MASK = new MaskFormatter("##/##/####");
                    MASK.install(dateField);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dateField.setToolTipText("Formato: dd/MM/yyyy");
                return dateField;
            default:
                JTextField textField = new JTextField(20);
                if (annotation != null && annotation.maxLength() > 0) {
                    int maxLength = annotation.maxLength();
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
        return new JTextField(20);
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
                    .filter(f -> f.isAnnotationPresent(CrudField.class) && f.getAnnotation(CrudField.class).showInTable())
                    .sorted((f1, f2) -> {
                        CrudField a1 = f1.getAnnotation(CrudField.class);
                        CrudField a2 = f2.getAnnotation(CrudField.class);
                        int order1 = a1 != null ? a1.tableOrder() : Integer.MAX_VALUE;
                        int order2 = a2 != null ? a2.tableOrder() : Integer.MAX_VALUE;
                        return Integer.compare(order1, order2);
                    })
                    .collect(Collectors.toList());

            String[] columnNames = tableFields.stream()
                    .map(f -> f.getAnnotation(CrudField.class).label())
                    .toArray(String[]::new);

            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (T item : data) {
                Object[] row = tableFields.stream().map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(item);
                    } catch (IllegalAccessException e) {
                        return "N/A";
                    }
                }).toArray();
                model.addRow(row);
            }

            table.setModel(model);

            for (int i = 0; i < tableFields.size(); i++) {
                Field field = tableFields.get(i);
                if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    table.getColumnModel().getColumn(i).setCellRenderer(new BooleanToSimNaoRenderer());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static <T> void saveRecord(JTable table, Map<String, JComponent> fieldComponents, CrudController<T> controller) {
        List<Field> allFields = getAllFieldsFromClassHierarchy(controller.getEntityClass());

        // Bloco de validação continua o mesmo...
        for (Field field : allFields) {
            CrudField annotation = field.getAnnotation(CrudField.class);
            if (annotation != null && annotation.required() && fieldComponents.containsKey(field.getName())) {
                JComponent component = fieldComponents.get(field.getName());
                boolean isEmpty = false;
                if (component instanceof JFormattedTextField) {
                    Object value = ((JFormattedTextField) component).getValue();
                    if (value instanceof Number) {
                        isEmpty = ((Number) value).doubleValue() == 0;
                    } else {
                        isEmpty = value == null || value.toString().replace("_", "").trim().isEmpty();
                    }
                } else if (component instanceof JTextField) {
                    isEmpty = ((JTextField) component).getText().trim().isEmpty();
                } else if (component instanceof JPasswordField) {
                    isEmpty = ((JPasswordField) component).getPassword().length == 0;
                } else if (component instanceof JComboBox) {
                    isEmpty = ((JComboBox<?>) component).getSelectedItem() == null;
                }

                if (isEmpty) {
                    String fieldLabel = annotation.label().isEmpty() ? formatFieldName(field.getName()) : annotation.label();
                    JOptionPane.showMessageDialog(null, "O campo '" + fieldLabel + "' é obrigatório e não foi preenchido.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
                    component.requestFocusInWindow();
                    return;
                }
            }
        }

        try {
            T instance = controller.getEntityClass().getDeclaredConstructor().newInstance();
            for (Field field : allFields) {
                if (fieldComponents.containsKey(field.getName())) {
                    field.setAccessible(true);
                    JComponent component = fieldComponents.get(field.getName());
                    Object value = getValueFromComponent(component, field.getType());
                    if (value != null) {
                        field.set(instance, value);
                    }
                }
            }

            // Lógica de data de cadastro continua a mesma...
            try {
                Field dataCadastroField = findFieldInHierarchy(controller.getEntityClass(), "dataCadastro");
                if (dataCadastroField != null) {
                    dataCadastroField.setAccessible(true);
                    Object currentValue = dataCadastroField.get(instance);
                    String currentDate = currentValue != null ? currentValue.toString() : "";
                    if (currentDate.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        dataCadastroField.set(instance, sdf.format(new Date()));
                    }
                }
            } catch (Exception e) {
                System.out.println("Aviso: Não foi possível definir data de cadastro: " + e.getMessage());
            }

            controller.create(instance);
            clearForm(fieldComponents);
            updateTable(table, controller);

            // <<<<<<<<<<<<<<< LÓGICA PARA ATUALIZAR O ID DEPOIS DE SALVAR <<<<<<<<<<<<<<<
            JComponent idComponent = fieldComponents.get("id");
            if (idComponent instanceof JSpinner) {
                int nextId = controller.findAll().size() + 1;
                ((JSpinner) idComponent).setValue(nextId);
            } else if (idComponent instanceof JTextField) {
                int nextId = controller.findAll().size() + 1;
                ((JTextField) idComponent).setText(String.valueOf(nextId));
            }
            // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
        if (component instanceof JComboBox) {
            Object selectedItem = ((JComboBox<?>) component).getSelectedItem();
            if (selectedItem instanceof StatusOption) {
                return ((StatusOption) selectedItem).getValue();
            } else {
                return selectedItem;
            }
        }

        if (component instanceof JFormattedTextField) {
            Object value = ((JFormattedTextField) component).getValue();
            if (value instanceof Number) {
                Number numberValue = (Number) value;
                if (targetType == float.class || targetType == Float.class) {
                    return numberValue.floatValue();
                }
                if (targetType == double.class || targetType == Double.class) {
                    return numberValue.doubleValue();
                }
                if (targetType == int.class || targetType == Integer.class) {
                    return numberValue.intValue();
                }
            }
            return ((JFormattedTextField) component).getText();
        }

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
        }

        if (component instanceof JPasswordField) {
            return new String(((JPasswordField) component).getPassword());
        } else if (component instanceof JCheckBox) {
            return ((JCheckBox) component).isSelected();
        } else if (component instanceof JSpinner) {
            return ((JSpinner) component).getValue();
        }
        return null;
    }

    private static void clearForm(Map<String, JComponent> fieldComponents) {
        // Agora iteramos pelo Map para ter acesso ao nome do campo
        for (Map.Entry<String, JComponent> entry : fieldComponents.entrySet()) {
            String fieldName = entry.getKey();
            JComponent component = entry.getValue();

            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText("");
            } else if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            } else if (component instanceof JSpinner) {
                // SÓ limpa o JSpinner se ele NÃO for o campo 'id'
                if (!fieldName.equals("id")) {
                    ((JSpinner) component).setValue(0);
                }
            } else if (component instanceof JComboBox) {
                if (((JComboBox<?>) component).getItemCount() > 0) {
                    ((JComboBox<?>) component).setSelectedIndex(0);
                }
            } else if (component instanceof JFormattedTextField) {
                if (((JFormattedTextField) component).getFormatter() instanceof NumberFormatter) {
                    ((JFormattedTextField) component).setValue(0.00);
                } else {
                    ((JFormattedTextField) component).setText("");
                }
            }
        }
    }

    private static <T> void deleteSelectedRecord(JTable table, CrudController<T> controller) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja excluir este registro?", "Confirmar Exclusão",
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
                    JOptionPane.showMessageDialog(null, "Funcionalidade de edição será implementada.\nRegistro ID: " + id);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar registro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para editar.");
        }
    }

    public static class BooleanToSimNaoRenderer extends javax.swing.table.DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            if (value instanceof Boolean) {
                setText(((Boolean) value) ? "Sim" : "Não");
            } else {
                super.setValue(value);
            }
        }
    }

    public static class StatusOption {

        private final String displayText;
        private final char value;

        public StatusOption(String displayText, char value) {
            this.displayText = displayText;
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        @Override
        public String toString() {
            return displayText;
        }
    }
}
