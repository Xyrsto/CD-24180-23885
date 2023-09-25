/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
/**
 * Classe eleitor
 * @author serra
 */
public class Elector implements Serializable
{

    /**
    * Tamanho da imagem do eleitor por defeito
    */ 
    public static int DEFAULT_IMAGE_SIZE = 148;
    
    private String nome;
    private String genero; 
    private String cartaoCidadao;
    private LocalDate dataNasc;
    private String voto;
    private String password;
    private ImageIcon foto;

    
    /**
    * Construtor por defeito
    */ 
    public Elector()
    {
        this("","","",LocalDate.of(1,1,1),"","", new ImageIcon());
    }
    

    /**
    * Construtor com parâmetros
    * @param nome
    * @param genero
    * @param cartaoCidadao
    * @param dataNasc
    * @param voto
    * @param password
    * @param foto
    */ 
    public Elector(String nome, String genero, String cartaoCidadao, LocalDate dataNasc, String voto, String password, ImageIcon foto)
    {
        this.nome = nome;
        this.genero = genero;
        this.cartaoCidadao = cartaoCidadao;
        this.dataNasc = dataNasc;
        this.voto = voto;
        this.password = password;
        this.foto = foto;
    }
    

    /**
     * Construtor cópia
     * @param e 
     */
    public Elector(Elector e)
    {
        this.nome = e.nome;
        this.genero = e.genero;
        this.cartaoCidadao = e.cartaoCidadao;
        this.dataNasc = e.dataNasc;
        this.voto = e.voto;
        this.password = e.password;
        this.foto = e.foto;
    }

    /**
     * Encapsulamento
     * @return 
     */

    public String getNome() {
        return nome;
    }

    /**
     * Encapsulamento
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Encapsulamento
     * @return 
     */
    public String getGenero() {
        return genero;
    }
    
    /**
     * Encapsulamento
     * @param genero 
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Encapsulamento
     * @return cartaoCidadao
     */
    public String getCartaoCidadao() {
        return cartaoCidadao;
    }

    /**
     * Encapsulamento
     * @param cartaoCidadao 
     */
    public void setCartaoCidadao(String cartaoCidadao) {
        this.cartaoCidadao = cartaoCidadao;
    }

    /**
     * Encapsulamento
     * @return dataNasc
     */
    public LocalDate getDataNasc() {
        return dataNasc;
    }

    /**
     * Encapsulamento
     * @param dataNasc 
     */
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * Encapsulamento
     * @return voto
     */
    public String getVoto() {
        return voto;
    }

    /**
     * Encapsulamento
     * @param voto 
     */
    public void setVoto(String voto) {
        this.voto = voto;
    }

    /**
     * Encapsulamento
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Encapsulamento
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Encapsulamento
     * @return foto
     */
    public ImageIcon getFoto() {
        return foto;
    }

    /**
     * Encapsulamento
     * @param foto 
     */
    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
    
    /**
    * Calcula a idade
    * @return idade
    */
    public int calculaIdade()
    {
        LocalDate now = LocalDate.now(); //gets localDate
        Period diff = Period.between(dataNasc, now); //difference between the dates is calculated
        
        return diff.getYears();
    }
    
    /**
     * Verifica se o eleitor é maior de idade
     * @return isMaior
     */
    public boolean isMaior()
    {
        if(calculaIdade() >= 18)
        {
            return true;
        }
        return false;
    }
    
  
    /**
     * Verifica se o eleitor é válido para votar
     * @return isValid
     */
    public boolean isValid()
    {
        if(isMaior() && voto == null)
        {
           return true ;
        }
        return false;
    }
    
    /**
     * Método do String
     * @return nome, cartaoCidade, password
     */
    public String toString()
    {
        return nome + " " + cartaoCidadao + " " + password + " " + calculaIdade();
    }
}
