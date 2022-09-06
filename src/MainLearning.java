
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
        Scanner ler = new Scanner(System.in);
        String caminho = "estado.txt";
       
        Qlearning aprendizado = new Qlearning();
        //aprendizado.InicializarMatriz(ambiente);
      
        aprendizado.Training(ambiente, 'M');
        aprendizado.CriarArquivo(ambiente, caminho);
        int controle = 1, origemlinha = 0, origemcoluna = 0;
        while(controle != 0){
              System.out.println("Digite uma linha, de 0 a 5:");
              origemlinha = ler.nextInt();
              System.out.println("Digite uma coluna, de 0 a 5:");
              origemcoluna = ler.nextInt();
              aprendizado.pecorrerMatriz(ambiente, 'M',origemlinha,origemcoluna);
              System.out.println("Quer tentar um novo caminho, digite 1. Digite 0 para encerrar");
              controle = ler.nextInt();
        }
        
       
    }
    
}
