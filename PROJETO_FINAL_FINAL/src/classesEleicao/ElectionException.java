/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;

import javax.swing.JOptionPane;

/**
 * Exceções para as eleições
 * @author serra
 */
public class ElectionException extends Exception 
{

    /**
     * Construtor com parâmetros
     * @param msg
     */
    public ElectionException(String msg)
    {
        super(msg);
    }
    
    public String getMessage()
    {
        return super.getMessage();
    }
    
    /**
     * Mostra uma exceção
     */
    public void show()
    {
        JOptionPane.showMessageDialog(null, getMessage());
    }
}
