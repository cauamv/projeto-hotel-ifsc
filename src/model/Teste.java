package model;

import javax.swing.JOptionPane;

public class Teste {
    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        
        pessoa.setId(1);
        pessoa.setNome("RoberUauuuuuu");
        
        JOptionPane.showMessageDialog(null, pessoa.toString());
        //System.out.println( pessoa.toString());
    }
}