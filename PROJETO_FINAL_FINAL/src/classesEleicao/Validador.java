/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe validador
 * @author serra
 */

public class Validador
{

    /**
    * Valida o nome
    * @param nome
    */ 
    public boolean isNomeValid(String nome)
    {
        Pattern pattern = Pattern.compile("[^a-zA-ZÀ-ÿ ]");
        Matcher matcher = pattern.matcher(nome);
        return !matcher.find();
    }

    /**
    * Verifica se o nome do candidato é único
    * @param nome
    * @param cnd
    */ 
    public boolean isNomeUnique(String nome, ArrayList<Candidato> cnd)
    {
        for (int i = 0; i < cnd.size(); i++) {
            if(nome.trim().equals(cnd.get(i).getNome().trim())) return false;
        }
        return true;
    }

    /**
    * Verifica se a data é válida
    * @param ano
    * @param mes
    * @param dia
    */ 
    public boolean isDataValid(String ano, String mes, String dia)
    {
        try{
            // Tenta converter as strings (ano,mes,dia) para inteiro e criar uma LocalDate usando esses valores.
            int anoInt = Integer.parseInt(ano.trim());
            int mesInt = Integer.parseInt(mes.trim());
            int diaInt = Integer.parseInt(dia.trim());
            LocalDate data = LocalDate.of(anoInt, mesInt, diaInt);
            LocalDate now = LocalDate.now();
            
            // Verifica se o periodo entre a data introduzida e o presente não é superior a 150 anos, e que a data não é no futuro
            if(Period.between(data, now).getYears() < 150 && !data.isAfter(now) ) return true;
        }
        catch(Exception el)
        {
            return false;
        }
        
        return false;
    }
    
    /**
    * Verifica se o cartão do cidadão é válido
    * @param cc
    * @param els
    */ 
    public static boolean isCCValid(String cc, ArrayList<Elector> els)
    {  
        for (int i = 0; i < els.size(); i++) {
            if(cc.equals(els.get(i).getCartaoCidadao())) return false;
        }

        // Verifica se o Cartão de Cidadão tem exatamente 8 digitos, sem espaços
        return (cc.trim().length() == 8);
    }
}
