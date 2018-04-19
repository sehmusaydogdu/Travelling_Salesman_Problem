package com.travellingsalesman.Models;

public class Core {

    private int[] cities;   //Şehirler
    private int[][] costs;  //Maliyetler
    private int solution;   //Çözüm

    //Core Tipinde Constructor
    protected Core(Core core){
        this.cities=core.getCities();
        this.costs=core.getCosts();
        this.solution=core.getSolution();
    }

    //Her bir sorunun tanımlanacağı Constructor
    protected Core(int[] cities, int[][] costs, int solution){
        this.cities=cities;
        this.costs=costs;
        this.solution=solution;
    }

    public int[] getCities() { return cities; }

    protected int[][] getCosts() { return costs; }

    protected int getSolution() {
        return solution;
    }

}
