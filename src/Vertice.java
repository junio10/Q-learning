
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author junio
 */
public class Vertice {
    private char Agente;
    private int reforco;
    private double dircima;
    private double diresq;
    private double dirdireita;
    private double dirbaixo;
    private List<Vertice> adjacentes;
    public char getAgente() {
        return Agente;
    }

    public void setAgente(char Agente) {
        this.Agente = Agente;
    }

    public List<Vertice> getAdjacentes() {
        return adjacentes;
    }

    public void setAdjacentes(List<Vertice> adjacentes) {
        this.adjacentes = adjacentes;
    }
   

  

    public int getReforco() {
        return reforco;
    }

    public void setReforco(int reforco) {
        this.reforco = reforco;
    }

    public double getDircima() {
        return dircima;
    }

    public void setDircima(double dircima) {
        this.dircima = dircima;
    }

    public double getDiresq() {
        return diresq;
    }

    public void setDiresq(double diresq) {
        this.diresq = diresq;
    }

    public double getDirdireita() {
        return dirdireita;
    }

    public void setDirdireita(double dirdireita) {
        this.dirdireita = dirdireita;
    }

    public double getDirbaixo() {
        return dirbaixo;
    }

    public void setDirbaixo(double dirbaixo) {
        this.dirbaixo = dirbaixo;
    }
    
    
}
