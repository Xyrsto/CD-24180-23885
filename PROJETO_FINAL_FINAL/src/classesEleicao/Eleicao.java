/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEleicao;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import classesEleicao.Validador;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import classesEleicao.Miner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * Classe eleição
 * @author serra
 */
public class Eleicao implements Serializable
{
    //arrays de nomes criados no site https://gerador-nomes.herokuapp.com/
    final String[] nomesProprios = {"Mónica","Vladimir","Brayan","Stéphanie","Ayesha","Francesca","Damião","Duarte","Imran","Kaylane","Kimi","Felipe","Cristian","Cíntia","Kamila","Vicente","Zélia","Ranya","Ioan","Luís","Arina","Mauri","Matias","Cláudio","Eliane","Eddy","Samanta","Rúdi","Prince","Ionara","Pandora","Luan","Irene","Marisol","Marco","Raphael","Kennedy","Dália","Diamantino","Kelly","Nicollas","Joel","Briana","Angelina","Miranda","Dinarte","Eusébio","Ângela","Alexandr","Ariele","Harry","Thayla","Valentin","Marina","Leticia","Aylla","Leonel","Éric","Ian","Lúcia","Raissa","Valter","Olinda","Ivo","Oumou","Gabriela","Mel","Dario","Lurdes","Mateus","Simara","Mário","Bingchilling","Taynara","Kelson","Valentina","Iago","Henry","Pietro","Lisandro","Hawa","Theo","Igor","Davide","Kayla","Hermano","Azael","Antero","Yuri","Asael","Quélia","Rosário","Victor","Júlio","Maryam","Cheila","Martín","Youssef","Tom","Edir"};
    final String[] apelidos = {"Gama","Café","Barreira","Maranhão","Marques","Cobra","Leme","Aquino","Mota","Estrela","Valadão","Nazário","Pinhal","Barrocas","Parente","Santiago","Homem","Covilhã","Borges","Vasconcelos","Mirandela","Tinoco","Rodovalho","Belchiorinho","Borja","Cabeça de Vaca","Almeida","César","Ulhoa","Negrão","Minho","Lameira","Carvalhosa","Mafra","Rico","Pontes","Feijó","Maciel","Póvoas","Caparica","Urias","Cidreira","Laureano","Angelim","Aleixo","Ximenes","Caldas","Barrico","Álvares","Milheiriço","Carvalho","Noronha","Veiga","Fragoso","Varanda","Boga","Borba","Flores","Tuna","Castanho","Leiria","Bilhalva","Remígio","Vale","Ruela","Rios","Ilha","Orriça","Miguel","Bragança","Dutra","Guilheiro","Manso","Leite","Fortunato","Festas","Mourinho","Espargosa","Mansilha","Brás","Lira","Alpuim","Quinzeiro","Carmona","Muniz","Murtinho","Beiriz","Madureira","Teodoro","Almada","Norões","Quintanilha","Brum","Mantas","Lamego","Ponte","Hipólito","Ginjeira","Ninharelhos","Dourado"};
    final String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&/()=?@<>";

    public JFileChooser fc = new JFileChooser();
    
    public ArrayList<Elector> eleitores;
    public ArrayList<Candidato> candidatos;
    public ArrayList<VotoBlock> chain;
    

    /**
    * Construtor por defeito
    */ 
    public Eleicao()
    {
        eleitores = new ArrayList<>();
        candidatos = new ArrayList<>();
        chain = new ArrayList<>();
    }
    
    //métodos da blockChain
    
    public String getLastBlockHash() {
        //Genesis block
        if (chain.isEmpty()) {
            return String.format("%08d", 0);
        }
        //hash of the last in the list
        return chain.get(chain.size() - 1).currentHash;
    }
    
    public void add(String data, int difficulty) {
        //hash of previous block
        String prevHash = getLastBlockHash();
        //mining block
        int nonce = Miner.getNonce(prevHash + data , difficulty);
        //build new block
        VotoBlock newBlock = new VotoBlock(prevHash, data, nonce);
        //add new block to the chain
        chain.add(newBlock);
    }
    
    public VotoBlock get(int index) {
        return chain.get(index);
    }
    
    public String chainToString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Blochain size = ").append(chain.size()).append("\n");
        for (VotoBlock block : chain) {
            txt.append(block.toString()).append("\n");
        }        
        return txt.toString();
    }
    
    public void save(String fileName) throws Exception {
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(chain);
        }
    }

    public void load(String fileName) throws Exception {
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.chain = (ArrayList<VotoBlock>) in.readObject();
        }
    }

    public boolean isValid() {
        //Validate blocks
        for (VotoBlock block : chain) {
            if (!block.isValid()) {
                return false;
            }
        }
        //validate Links
        //starts in the second block
        for (int i = 1; i < chain.size(); i++) {
            //previous hash !=  hash of previous
            if (chain.get(i).previousHash != chain.get(i - 1).currentHash) {
                return false;
            }
        }
        return true;
    }
    
    //fim dos métodos da blockChain
    
    /**
    * Método toString()
    */ 
    public String toString()
    {
        return eleitores.toString() + "\n" + candidatos.toString();
    }
    
    /**
    * Guarda a eleição num ficheiro
    * @param el
    */ 

    public void guardarEleicao(Eleicao el)
    {
        //converte ArrayList em byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(el);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Convert to Byte Array
        byte[] byteArray = baos.toByteArray();
 
        //guarda byte array no ficheiro 
        if (fc.showSaveDialog(fc) == JFileChooser.APPROVE_OPTION) {
            try {
                Path paths = Paths.get(fc.getSelectedFile().getAbsolutePath());
                Files.write(paths, byteArray);
            } catch (Exception e) {
            }
        }
    }

    /**
    * Carrega uma eleição guardada num ficheiro 
    */ 
    public void carregaEleicao()
    {
        Eleicao updatedEleicao = new Eleicao();
        //abre o ficheiro de byte array, convert para arrayList do tipo Pessoa e mostra na consola pra confirmar
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            try {
                Path paths = Paths.get(fc.getSelectedFile().getAbsolutePath());
                byte[] dados = Files.readAllBytes(paths);
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dados));
                updatedEleicao = (Eleicao)ois.readObject();
                
                //this.candidatos = (ArrayList<Candidato>) ois.readObject();
            } catch (Exception e) {
                
            }
        }
        
        this.eleitores = updatedEleicao.eleitores;
        this.candidatos = updatedEleicao.candidatos;
        
    }
    
    /**
    * Gera um eleitor aleatório
    */ 
    public void gerarElectorRand()
    {
        for (int j = 0; j < 500; j++) {
            // Gera uma variavel Random
            Random rnd = new Random();

            // Gera um indice aleatorio utilizado para obter um nome e apelido a partir dos arrays nomesProprios/apelidos
            int index = rnd.nextInt(nomesProprios.length);
            String nome = nomesProprios[index];
            String apelido = apelidos[index];

            // String género
            String gen;

            // String cc
            String cc;

            // StringBuilder para gerar passwords
            StringBuilder pswdSB = new StringBuilder();
            String password;

            //  Utiliza o rnd para gerar um booleano aleatorio e em seguida escolhe um genéro
            if(rnd.nextBoolean()){
                gen = "M" ;
            } 
            else
            {
                gen = "F";
            }

            //  Enquanto o cartão de cidadão creado com o rnd for inválido tenta outro
            do {
                cc = String.valueOf(rnd.nextInt(10000000,99999999));
            } while (!Validador.isCCValid(cc, eleitores));

            // Gera uma data aleatoria
            LocalDate start = LocalDate.of(1970, 1, 1);
            long days = ChronoUnit.DAYS.between(start, LocalDate.now());
            LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));

            // Escolhe caracteres aleatorios do array passwordChars e dá append na string builder pswdSB
            for (int i = 0; i < 10; i++) {
                pswdSB.append(passwordChars.charAt(rnd.nextInt(passwordChars.length())));
            }

            password = pswdSB.toString();
            
            Elector el = new Elector(nome + " " + apelido, gen,cc, randomDate, null, password, null);          
            eleitores.add(el);
        }

    }

       
}
