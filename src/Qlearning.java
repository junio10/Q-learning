
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.random;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author junio
 */
public class Qlearning {
    public void lerAmbiente(Vertice ambiente[][]) throws FileNotFoundException, IOException{
        FileReader arq = new FileReader("ambiente.txt");
        BufferedReader ler = new BufferedReader(arq);
        String linha = "";
      
        int i = 0;
        linha = ler.readLine();
        while(linha != null){
            
            String conteudo[] = linha.split(";");
            ambiente[i][0].setAgente(conteudo[0]);
            ambiente[i][1].setAgente(conteudo[1]);
            ambiente[i][2].setAgente(conteudo[2]);
            ambiente[i][3].setAgente(conteudo[3]);
            ambiente[i][4].setAgente(conteudo[4]);
            ambiente[i][5].setAgente(conteudo[5]);
            
            linha = ler.readLine();
            i++;
            
        }
        arq.close();
    }
    public void inicializarMatriz(Vertice ambiente[][]){
            for(int i1= 0; i1 <= 5; i1++){
                for(int j= 0; j<= 5; j++){
                ambiente[i1][j] = new Vertice();
            }
        }
    }
     
    
    public void CriarArquivo(Vertice ambiente[][], String caminho) throws IOException{
        File arquivo = new File("estado.txt");
        arquivo.createNewFile();
        FileWriter fileWriter = new FileWriter(arquivo);
        BufferedWriter escrever = new BufferedWriter(fileWriter);
        
        for(int i1= 0; i1 <= 5; i1++){
            for(int j= 0; j<= 5; j++){
            
                
                String linha = String.valueOf(i1) + ";" + String.valueOf(j) + ";" +String.valueOf(ambiente[i1][j].getDirbaixo()) + ";"+ String.valueOf(ambiente[i1][j].getDircima())
                        +";"+String.valueOf(ambiente[i1][j].getDirdireita())+";"+String.valueOf(ambiente[i1][j].getDiresq())+";" + String.valueOf(ambiente[i1][j].getReforco()) +"\n";
                escrever.write(linha);
                
               
            }
        }
        
        escrever.close();
        fileWriter.close();
        
    }
    public void Training(Vertice ambiente[][], char boneco) throws IOException{
        int i = 0, linhaant = 0, colant = 0;
        Random random = new Random();
        int linha = 0, coluna = 0;
        String parede = "1";
        Vertice estadoAnterior, estadoAtual;
        double resultLearning = 0.0f;
        //inicializando a matriz
        inicializarMatriz(ambiente);
        //lendo arquivo txt, e pondo os valores na matriz
        lerAmbiente(ambiente);
       

        ambiente[5][5].setReforco(10);
        int numrandom;
         while(i < 10000){
            numrandom = (int) (Math.random()*4)+1;
             //subir
            if(numrandom == 1){
                   if(linha > 0){ 
                    if(!ambiente[linha-1][coluna].getAgente().equals(parede)){
                    linhaant = linha;
                    colant = coluna;
                    linha = linha -1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDircima(resultLearning);
                       }  
                   }
             //descer
            }else if(numrandom == 2){
                if(linha < 5){
                   if(!ambiente[linha+1][coluna].getAgente().equals(parede)){
                   linhaant = linha;
                   colant = coluna;
                   linha = linha + 1;
                   //ambiente[linha][coluna].setAgente(boneco);
                   estadoAtual = ambiente[linha][coluna];
                   resultLearning = Qlearning(estadoAtual);
                   ambiente[linhaant][colant].setDirbaixo(resultLearning);
                   }
                }
                //direita
            }else if(numrandom == 3){
                 if(coluna < 5){
                    if(!ambiente[linha][coluna+1].getAgente().equals(parede)){
                    linhaant = linha;
                    colant = coluna;
                    coluna = coluna + 1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDirdireita(resultLearning);
                    }
                 }
                 //esquerda
            }else{
                 if(coluna > 0){
                    if(!ambiente[linha][coluna-1].getAgente().equals(parede)){
                    linhaant = linha;
                    colant = coluna;
                    coluna = coluna - 1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDiresq(resultLearning);
                    }
                 }
            }
             i++;
         }
        
    }
    public double maxSetas(Vertice v){
        double maior = 0.0d;
        if(v.getDirbaixo() > maior)
           maior = v.getDirbaixo();
        if(v.getDirdireita() > maior)
            maior = v.getDirdireita();
        if(v.getDiresq() > maior)
            maior = v.getDiresq();
        if(v.getDircima() > maior)
            maior = v.getDircima();
        
        return maior;
    }
    
    public double Qlearning(Vertice v){
        double maxEstado = 0.0d, gama = 0.9, result;
        float reforco = v.getReforco();
        maxEstado = maxSetas(v);
        
        result =  reforco + (gama * maxEstado);
        return result;
    }
    
    public void pecorrerMatriz(Vertice ambiente[][], String boneco, int origemlinha, int origemcoluna){
        double caminhomaior = 0.0f;
        //Random random = new Random();
        int linha = origemlinha, coluna = origemcoluna, aleatorio = 0;
        ambiente[origemlinha][origemcoluna].setAgente(boneco);
        ambiente[5][5].setAgente(" ");
        while(!ambiente[5][5].getAgente().equals(boneco)){
            if(ambiente[linha][coluna].getDircima() >= ambiente[linha][coluna].getDirbaixo() && ambiente[linha][coluna].getDircima() >= ambiente[linha][coluna].getDirdireita()
               && ambiente[linha][coluna].getDircima() >= ambiente[linha][coluna].getDiresq() && ambiente[5][5].getAgente() != boneco){
                linha = linha -1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("Subiu; " + "linha: " + linha + " coluna: " + coluna);
            }
            if(ambiente[linha][coluna].getDirbaixo() >= ambiente[linha][coluna].getDircima() && ambiente[linha][coluna].getDirbaixo() >= ambiente[linha][coluna].getDirdireita()
               && ambiente[linha][coluna].getDirbaixo() >= ambiente[linha][coluna].getDiresq() && ambiente[5][5].getAgente() != boneco){
                linha = linha + 1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("Desceu; " + "linha: " + linha + " coluna: " + coluna);
            }
            if(ambiente[linha][coluna].getDiresq() > ambiente[linha][coluna].getDirbaixo() && ambiente[linha][coluna].getDiresq() > ambiente[linha][coluna].getDirdireita()
               && ambiente[linha][coluna].getDiresq() > ambiente[linha][coluna].getDircima() && ambiente[5][5].getAgente() != boneco){
                coluna = coluna - 1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("Esquerda; " + "linha: " + linha + " coluna: " + coluna);
            }
            if(ambiente[linha][coluna].getDirdireita() >= ambiente[linha][coluna].getDirbaixo() && ambiente[linha][coluna].getDirdireita() >= ambiente[linha][coluna].getDircima()
               && ambiente[linha][coluna].getDirdireita() >= ambiente[linha][coluna].getDiresq() && ambiente[5][5].getAgente() != boneco){
                coluna = coluna + 1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("Direita; " + "linha: " + linha + " coluna: " + coluna);
            }
        }
        //para mostrar o caminho pecorrido
        System.out.println("Caminho pecorrido na matriz:");
         for(int i1= 0; i1 <= 5; i1++){
               System.out.println(" ");
            for(int j= 0; j<= 5; j++){
               System.out.print(ambiente[i1][j].getAgente());
            }
        }
         for(int i= 0; i <= 5; i++){    
            for(int j1= 0; j1<= 5; j1++){
                if(ambiente[i][j1].getAgente().equals(boneco)){
                    ambiente[i][j1].setAgente("0");
                }
            }
        }
         
        
        
    }
    
}
