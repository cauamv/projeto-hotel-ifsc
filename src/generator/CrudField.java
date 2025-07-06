package generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CrudField {
    String label() default "";
    boolean visible() default true;        // Se o campo aparece no formulário
    boolean showInTable() default false;   // Se o campo aparece na tabela
    boolean editable() default true;       // Se o campo pode ser editado no formulário
    boolean required() default false;
    int order() default 0;
    String type() default "TEXT";          // TEXT, NUMBER, DATE, BOOLEAN, PASSWORD, CHAR
    int maxLength() default 0;
    int tableOrder() default 0;            // Ordem específica para tabela
    boolean mask() default false;
    String typeMask() default "TELEFONE";  // CPF, CNPJ, TELEFONE, CEP, RG, DATE 
    Class<?> itemsClass() default Object.class; // Para o ComboBox, define a classe dos itens
}
