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
    String data;
    /**
    * 
    * @param previousHash
    * @param nonce
    * @param candidato
    * @param eleitor
    * Class constructor
    */   
    public VotoBlock(String previousHash, String data, int nonce){
        this.previousHash = previousHash;
        this.nonce = nonce;
        this.currentHash = calculateHash();
        this.data = data;
    }
    
    /**
     * @returns the hashed value of the current block hash(nonce + previousHash + data) 
     */
    public String calculateHash(){
        return Hash.getHash(nonce + previousHash + data);
    }
    
    public String toString() {
        return // (isValid() ? "OK\t" : "ERROR\t")+
                 String.format("[ %8s", previousHash) + " <- " + 
                   String.format("%-10s", data) +  String.format(" %7d ] = ", nonce) + 
                String.format("%8s",currentHash);
    }
    
    public boolean isValid(){
        return currentHash.equals(calculateHash());
    }
}
