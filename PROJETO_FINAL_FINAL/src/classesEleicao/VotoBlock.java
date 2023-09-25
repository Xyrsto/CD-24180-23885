/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;
import classesEleicao.Hash;
import classesEleicao.Candidato;
import classesEleicao.Elector;
/**
 *
 * @author rodri
 */
public class VotoBlock {
    String previousHash; //link to previous hash
    int nonce; //discovered number (proof of work)
    String currentHash; //hash of the current block
    Candidato candidato;
    Elector eleitor;
    /**
    * 
    * @param previousHash
    * @param nonce
    * @param candidato
    * @param eleitor
    * Class constructor
    */   
    public VotoBlock(String previousHash,int nonce, Candidato candidato, Elector eleitor){
        this.previousHash = previousHash;
        this.nonce = nonce;
        this.currentHash = calculateHash();
        this.candidato = candidato;
        this.eleitor = eleitor;
    }
    
    /**
     * @returns the hashed value of the current block hash(nonce + previousHash + data) 
     */
    public String calculateHash(){
        return Hash.getHash(nonce + previousHash + candidato + eleitor);
    }
    
    public String toString() {
        String data = eleitor.getNome() + ", " + candidato.getNome();
        return // (isValid() ? "OK\t" : "ERROR\t")+
                 String.format("[ %8s", previousHash) + " <- " + 
                   String.format("%-10s", data) +  String.format(" %7d ] = ", nonce) + 
                String.format("%8s",currentHash);
    }
    
    public boolean isValid(){
        return currentHash.equals(calculateHash());
    }
}
