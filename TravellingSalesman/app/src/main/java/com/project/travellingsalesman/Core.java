package com.project.travellingsalesman;

class Core {

    private int[] cities;   //Şehirler
    private int[][] costs;  //Maliyetler
    private int solution;   //Çözüm

    //Core Tipinde Constructor
    Core(Core core){
        this.cities=core.getCities();
        this.costs=core.getCosts();
        this.solution=core.getSolution();
    }

    //Her bir sorunun tanımlanacağı Constructor
    Core(int[] cities, int[][] costs, int solution){
        this.cities=cities;
        this.costs=costs;
        this.solution=solution;
    }

    int[] getCities() {
        return cities;
    }

    int[][] getCosts() {
        return costs;
    }

    int getSolution() {
        return solution;
    }

}
