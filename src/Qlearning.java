
import java.io.BufferedWriter;
import java.io.File;
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
    public void Training(Vertice ambiente[][], char boneco){
        int i = 0, linhaant = 0, colant = 0;
        Random random = new Random();
        int linha = 0, coluna = 0;
        Vertice estadoAnterior, estadoAtual;
        double resultLearning = 0.0f;
        for(int i1= 0; i1 <= 5; i1++){
            for(int j= 0; j<= 5; j++){
                ambiente[i1][j] = new Vertice();
            }
        }

        ambiente[5][5].setReforco(10);
        int numrandom;
         while(i < 10000){
            numrandom = (int) (Math.random()*4)+1;
             //subir
            if(numrandom == 1){
                   if(linha > 0){ 
                    linhaant = linha;
                    colant = coluna;
                    linha = linha -1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDircima(resultLearning);
                    
                   }
             //descer
            }else if(numrandom == 2){
                if(linha < 5){
                   linhaant = linha;
                   colant = coluna;
                   linha = linha + 1;
                   //ambiente[linha][coluna].setAgente(boneco);
                   estadoAtual = ambiente[linha][coluna];
                   resultLearning = Qlearning(estadoAtual);
                   ambiente[linhaant][colant].setDirbaixo(resultLearning);
                }
                //direita
            }else if(numrandom == 3){
                 if(coluna < 5){
                    linhaant = linha;
                    colant = coluna;
                    coluna = coluna + 1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDirdireita(resultLearning);
                 }
                 //esquerda
            }else{
                 if(coluna > 0){
                    linhaant = linha;
                    colant = coluna;
                    coluna = coluna - 1;
                    //ambiente[linha][coluna].setAgente(boneco);
                    estadoAtual = ambiente[linha][coluna];
                    resultLearning = Qlearning(estadoAtual);
                    ambiente[linhaant][colant].setDiresq(resultLearning);
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
    
    public void pecorrerMatriz(Vertice ambiente[][], char boneco, int origemlinha, int origemcoluna){
        double caminhomaior = 0.0f;
        //Random random = new Random();
        int linha = origemlinha, coluna = origemcoluna, aleatorio = 0;
        ambiente[5][5].setAgente(' ');
        while(ambiente[5][5].getAgente() != boneco){
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
        
    }
    
}
