package com.travellingsalesmangame.Models.Game;

public class Examples {
    //Örnekler, her level bir core listesi, her listeye eklenen eleman o levelin sorusu(state-durum değeri) oluyor.

    private static Core[][] cores;

    static {

        cores = new Core[][]{

                new Core[]{ //1. level // 3 tepeli
                        new Core(new int[]{6, 14, 25},new int[][]{{6,14,29},{6,25,19},{14,25,34}},82),
                },

                new Core[]{ //2. level // 4 tepeli
                        new Core(new int[]{2, 17, 25, 29},new int[][]{{2,17,25},{2,25,10},{2,29,15},{17,25,25},{17,29,30},{25,29,35}},80),
                        new Core(new int[]{0, 14, 15, 27},new int[][]{{0,14,3},{0,27,5},{0,15,2},{15,27,1},{14,15,10},{14,27,9}},15),
                },

                new Core[]{ //3.level // 5 tepeli
                        new Core(new int[]{1, 3, 15, 19, 27},new int[][]{{1,3,7},{1,15,9},{1,19,36},{1,27,11},{3,15,23},{3,19,13},{3,27,19},{15,19,42},{15,27,10},{19,27,8}},47),
                        new Core(new int[]{3,5, 14, 25, 33},new int[][]{{3,5,8},{3,14,12},{3,25,11},{3,33,10},{5,14,8},{5,33,6},{5,25,7},{14,25,9},{14,33,5},{25,33,15}},21),
                        new Core(new int[]{3,5, 19, 21, 33},new int[][]{{5,21,2},{5,3,5},{5,19,12},{3,19,10},{21,33,4},{19,33,3},{19,21,8},{3,33,30}},21),
                        new Core(new int[]{0,3, 14, 16, 30},new int[][]{{0,3,3},{0,14,4},{0,16,2},{0,30,7},{3,14,4},{3,16,6},{3,30,3},{14,16,5},{14,30,8},{16,30,6}},19),

                },

                new Core[]{ // 4. Level //6 tepeli
                        new Core(new int[]{1, 3, 10, 14, 31, 34},new int[][]{{1,3,35},{1,10,31},{1,14,31},{1,31,37},{1,34,35},{3,10,31},{3,14,21},{3,31,25},{3,34,40},{10,14,25},{10,31,31},{10,34,34},{14,31,35},{14,34,31},{31,34,25}},162),
                        new Core(new int[]{2,10, 14, 20, 24,33},new int[][]{{10,2,45},{10,14,92},{10,20,40},{10,24,71},{10,33,67},{2,20,20},{2,14,50},{2,24,42},{2,33,54},{14,20,54},{14,24,36},{14,33,58},{20,24,32},{24,33,22},{20,33,36}},229),
                        new Core(new int[]{1,3, 15, 19, 22,31},new int[][]{{1,15,14},{1,3,14},{1,19,24},{1,22,26},{3,15,31},{3,22,23},{15,19,29},{15,22,13},{15,31,48},{19,22,13},{19,31,18},{22,31,15}},129),

                },

                new Core[]{ // 5. level // 7 tepeli
                        new Core(new int[]{4, 1, 13, 15, 25, 29, 33},new int[][]{{4,1,35},{4,13,30},{4,15,30},{4,25,37},{4,29,35},{4,33,20},{1,13,25},{1,15,25},{1,25,40},{1,29,25},{1,33,30},{13,15,29},{13,25,35},{13,29,20},{13,33,25},{15,25,25},{15,29,25},{15,33,25},{25,29,25},{25,33,25}},135),
                        new Core(new int[]{2, 5, 9, 20, 24, 31, 33},new int[][]{{9,2,11},{9,5,16},{9,20,44},{9,31,20},{9,33,21},{9,24,23},{2,5,12},{2,20,18},{2,31,10},{2,33,30},{2,24,44},{5,20,43},{5,31,35},{5,33,32},{5,24,44},{20,31,41},{20,33,30},{20,24,36},{31,33,31},{31,24,18},{24,33,31}},135),
                        new Core(new int[]{1, 9, 10, 20, 18, 34, 32},new int[][]{{1,9,7},{1,18,9},{1,34,32},{1,32,11},{1,20,20},{1,10,14},{9,18,23},{9,34,13},{9,32,19},{9,20,34},{9,10,40},{18,34,42},{18,32,10},{18,20,31},{18,10,27},{34,32,8},{34,20,20},{34,10,27},{32,20,14},{32,10,10},{20,10,29}},100),
                },
                new Core[]{ // 6. Level
                        new Core(new int[]{0, 3, 6, 8, 14, 17, 20, 24, 31, 34},new int[][]{{0,3,15},{0,6,23},{0,8,6},{0,14,20},{0,17,45},{0,20,96},{0,24,56},{0,31,22},{0,34,141}
                                ,{3,6,73},{3,8,98},{3,14,65},{3,17,70},{3,20,24},{3,24,54},{3,31,58},{3,34,44}
                                ,{6,8,33},{6,14,166},{6,17,23},{6,20,123},{6,24,36},{6,31,64},{6,34,25}
                                ,{8,14,12},{8,17,83},{8,20,24},{8,24,64},{8,31,211},{8,34,23}
                                ,{14,17,93},{14,20,36},{14,24,28},{14,31,28},{14,34,76}
                                ,{17,20,34},{17,24,8},{17,31,42},{17,34,123}
                                ,{20,24,22},{20,31,46},{20,34,332}
                                ,{24,31,32},{24,34,22}
                                ,{31,34,38}}
                                ,135),
                },

        };
    }

    //örnekleri çekme
    public static Core[][] getCores() {
        return cores;
    }

    //Her sorunun maliyetlere eişiyorum.
    public static int PathCosts(int [][] costs,int start,int finish){
        for (int i = 0; i < costs.length; i++) {
            if ((costs[i][0]==start && costs[i][1]==finish) || (costs[i][1]==start && costs[i][0]==finish))
                return costs[i][2];
        }
        return 0;
    }
}