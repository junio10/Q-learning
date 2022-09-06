/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author junio
 */
public class MainLearning {
    public static void main(String[] args) {
        Vertice[][] ambiente = new Vertice[6][6];
        Qlearning aprendizado = new Qlearning();
        //aprendizado.InicializarMatriz(ambiente);
      
        aprendizado.Training(ambiente, 'M');
        //aprendizado.pecorrerMatriz(ambiente, 'M');
         for(int i1= 0; i1 <= 5; i1++){
            for(int j= 0; j<= 5; j++){
                System.out.println("linha: " + i1 + "coluna: " + j);
                System.out.println(ambiente[i1][j].getDirbaixo());
                System.out.println(ambiente[i1][j].getDircima());
                System.out.println(ambiente[i1][j].getDiresq());
                System.out.println(ambiente[i1][j].getDirdireita());
            }
        }
    }
    
}
