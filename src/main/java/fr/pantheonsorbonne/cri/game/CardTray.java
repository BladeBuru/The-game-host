package fr.pantheonsorbonne.cri.game;

public class CardTray {
    static char CARACTERE_ASCENDING_STACK = '^' ;
    static char CARACTERE_DOWN_STACK = 'v';
    static char CARACTERE_ALLY = 'A';
    static char CARACTERE_ENEMY = 'E';

    private int ascendingStackAlly;
    private int downStackAlly;
    private int ascendingStackEnemy;
    private int downStackEnemy;


    public CardTray(int ascendingStackAlly, int downStackAlly, int ascendingStackEnemy, int downStackEnemy) {
        this.ascendingStackAlly = ascendingStackAlly;
        this.downStackAlly = downStackAlly;
        this.ascendingStackEnemy = ascendingStackEnemy;
        this.downStackEnemy = downStackEnemy;
    }

    private boolean poseAscendingStackAlly(int ascendingStackAlly) {
        this.ascendingStackAlly = ascendingStackAlly;
        //ToDo
    }

    private boolean poseDownStackAlly(int downStackAlly) {
        this.downStackAlly = downStackAlly;
        //ToDo
    }

    private boolean poseAscendingStackEnemy(int ascendingStackEnemy) {
        this.ascendingStackEnemy = ascendingStackEnemy;
        //ToDo
    }

    private boolean poseDownStackEnemy(int downStackEnemy) {
        this.downStackEnemy = downStackEnemy;
        //ToDo
    }

    public boolean poseCard(String card){

        // TODO WARNINNG truc pas beau a modifié  recuperer le nexte number et les next char

        int value = Integer.parseInt( card.substring(0,2));
        char typeStack =card.charAt(2);
        char whichSide = card.charAt(3);

        //ToDo
        //ToDo


       if(typeStack == CARACTERE_ALLY){
           if (whichSide == CARACTERE_ASCENDING_STACK){
               return  this.poseAscendingStackAlly(value);
           } else if (whichSide == CARACTERE_DOWN_STACK) {
               return this.poseDownStackAlly(value);
           }
           return  false;
       }


        if(typeStack == CARACTERE_ENEMY){
            if (whichSide == CARACTERE_ASCENDING_STACK){
                return this.poseAscendingStackEnemy(value);
            } else if (whichSide == CARACTERE_DOWN_STACK) {
               return this.poseDownStackEnemy(value);
            }
        }
        return false;
    }

    public int getAscendingStackAlly() {
        return ascendingStackAlly;
    }

    public int getDownStackAlly() {
        return downStackAlly;
    }

    public int getAscendingStackEnemy() {
        return ascendingStackEnemy;
    }

    public int getDownStackEnemy() {
        return downStackEnemy;
    }

    public boolean placeCard(String card){



        return true;
    }

}
