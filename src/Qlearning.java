
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
    
     
    
    public void Training(Vertice ambiente[][], char boneco){
        int i = 0, linhaant = 0, colant = 0;
        Random random = new Random();
        int linha = 4, coluna = 4;
        Vertice estadoAnterior, estadoAtual;
        double resultLearning = 0.0f;
        for(int i1= 0; i1 <= 5; i1++){
            for(int j= 0; j<= 5; j++){
                ambiente[i1][j] = new Vertice();
            }
        }
        
        ambiente[5][5].setReforco(10);
        int numrandom;
         while(i < 1000000){
            numrandom = (int) (Math.random()*3)+1;
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
        double maxEstado = 0.0d, gama = 0.9d, result;
        float reforco = v.getReforco();
        maxEstado = maxSetas(v);
        
        result =  reforco + (gama * maxEstado);
        return result;
    }
    
    public void pecorrerMatriz(Vertice ambiente[][], char boneco){
        double caminhomaior = 0.0f;
        Random random = new Random();
        int linha = 0, coluna = 0, aleatorio = 0;
        while(ambiente[5][5].getAgente() != boneco){
            caminhomaior = maxSetas(ambiente[linha][coluna]);
            if(caminhomaior == 0){
               aleatorio = random.nextInt(3)+1;
            }
            if(caminhomaior == ambiente[linha][coluna].getDircima() || aleatorio==1){
                linha = linha-1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("cima");
            }else if(caminhomaior == ambiente[linha][coluna].getDirbaixo() || aleatorio==2){
                linha = linha+1;
                ambiente[linha][coluna].setAgente(boneco);
                System.out.println("baixo");
            }else if(caminhomaior == ambiente[linha][coluna].getDiresq() || aleatorio==3){
                 coluna = coluna - 1;
                 ambiente[linha][coluna].setAgente(boneco);
                 System.out.println("esquerda");
            }else{
                 coluna = coluna + 1;
                 ambiente[linha][coluna].setAgente(boneco);
                 System.out.println("direita");
            }
            
            aleatorio = 0;
            }
        
    }
    
}
