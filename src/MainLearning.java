
import java.io.FilePermission;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author junio
 */
public class MainLearning {
    public static void main(String[] args) throws IOException {
        Vertice[][] ambiente = new Vertice[6][6];
        Vertice[][] ambiente2 = new Vertice[6][6];
        Scanner ler = new Scanner(System.in);
        String caminho = "estado.txt";
    
      
        
        Qlearning aprendizado = new Qlearning();
        Qlearning aprendizado2 = new Qlearning();
        aprendizado2.inicializarMatriz(ambiente2);
        aprendizado2.lerAmbiente(ambiente2);
       
      
        aprendizado.Training(ambiente, 'M');
        aprendizado.CriarArquivo(ambiente, caminho);
        int controle = 1, origemlinha = 0, origemcoluna = 0;
        while(controle != 0){
          for(int i1= 0; i1 <= 5; i1++){
            System.out.println(" ");
            for(int j= 0; j<= 5; j++){
                System.out.print(ambiente2[i1][j].getAgente());
            }
          }
              System.out.println("");
              System.out.println("Digite uma linha, de 0 a 5:");
              origemlinha = ler.nextInt();
              System.out.println("Digite uma coluna, de 0 a 5:");
              origemcoluna = ler.nextInt();
              if(ambiente[origemlinha][origemcoluna].getAgente().equals("1")){
                  System.out.println("Tem uma parede onde vc quer iniciar");
              }else{
              System.out.println("Caminho pecorrido:");
              aprendizado.pecorrerMatriz(ambiente, "M",origemlinha,origemcoluna);
              }
              System.out.println("Quer tentar um novo caminho, digite 1. Digite 0 para encerrar");
              controle = ler.nextInt();
        }
        
       
    }
    
}
