package fr.pantheonsorbonne.cri.game;

public class CardTray {
    static char CARACTERE_ASCENDING_STACK = '^' ;
    static char CARACTERE_DOWN_STACK = 'v';
    static char CARACTERE_ALLY = 'A';
    static char CARACTERE_ENEMY = 'E';

    private int ascendingAllyStack;
    private int downAllyStack;
    private int ascendingEnemyStack;
    private int downEnemyStack;
    private boolean playEnemyStack = false;


    public CardTray(int ascendingStackAlly, int downAllyStack, int ascendingEnemyStack, int downEnemyStack) {
        this.ascendingAllyStack = ascendingStackAlly;
        this.downAllyStack = downAllyStack;
        this.ascendingEnemyStack = ascendingEnemyStack;
        this.downEnemyStack = downEnemyStack;
    }

    private boolean poseAscendingStackAlly(int card) {
       if(card > this.ascendingAllyStack || card == this.ascendingAllyStack - 10){
        this.ascendingAllyStack = card;
        return true;
       }
        return false;
    }

    private boolean poseDownStackAlly(int card) {
        if(card < this.ascendingAllyStack || card == this.ascendingAllyStack + 10){
            this.downAllyStack = card;
            return true;
        }
        return false;
    }

    private boolean poseAscendingStackEnemy(int card) {
        if(card < this.ascendingEnemyStack && this.playEnemyStack == false){
            this.ascendingEnemyStack = card;
            this.playEnemyStack = true;
            return true;
        }
        return false;
    }

    private boolean poseDownStackEnemy(int card) {
        if(card > this.ascendingEnemyStack && this.playEnemyStack == false){
            this.downEnemyStack = card;
            this.playEnemyStack = true;
            return true;
        }
        return false;
    }

    public boolean poseCard(String card){

        // TODO WARNINNG truc pas beau a modifié  recuperer le next number et les next char

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

    public int getAscendingAllyStack() {
        return ascendingAllyStack;
    }

    public int getDownAllyStack() {
        return downAllyStack;
    }

    public int getAscendingEnemyStack() {
        return ascendingEnemyStack;
    }

    public int getDownEnemyStack() {
        return downEnemyStack;
    }
    public boolean toPlayOnTheEnemyStack() {
        return playEnemyStack;
    }


}
