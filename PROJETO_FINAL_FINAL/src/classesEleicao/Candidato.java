/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Classe candidato
 * @author serra
 */
public class Candidato implements Serializable
{

    /**
     * Tamanho por defeito da imagem de um candidato
     */
    public static int DEFAULT_IMAGE_SIZE = 148;
    
    private String nome;
    private int votos;
    private ImageIcon foto;
    
    /**
     * Construtor por defeito
     */
    public Candidato()
    {
        this("", 0, new ImageIcon());
    }
    
    /**
     * Construtor com parâmetros
     * @param nome
     * @param votos
     * @param foto
     */
    public Candidato(String nome, int votos, ImageIcon foto)
    {
        this.nome = nome;
        this.votos = votos;
        this.foto = foto;
    }

    /**
     * Construtor cópia
     * @param c
     */
    public Candidato(Candidato c)
    {
        this.nome = c.nome;
        this.votos = c.votos;
        this.foto = c.foto;
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
    public int getVotos() {
        return votos;
    }

    /**
     * Encapsulamento
     * @param votos
     */
    public void setVotos(int votos) {
        this.votos = votos;
    }
    
    /**
     * Encapsulamento
     * @param foto
     */
    public void setFoto(ImageIcon foto)
    {
        this.foto = foto;
    }
    
    /**
     * Encapsulamento
     * @return foto
     */
    public ImageIcon getFoto()
    {
        return foto;
    }
    
    /**
     * Adiciona um voto
     */
    public void addVoto()
    {
        votos++;
    }
    
    /**
     * Remove um voto
     */
    public void removeVoto()
    {
        votos--;
    }
    
    /**
     * Método toString()
     * @return nome, votos
     */
    public String toString()
    {
        return nome + " " + votos;
    }
}
