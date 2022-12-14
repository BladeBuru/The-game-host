package fr.pantheonsorbonne.cri.game;

public class CardTray {
    static char CHARACTER_ASCENDING_STACK = '^' ;
    static char CHARACTER_DOWN_STACK = 'v';
    //Ally est le joueur qui joue ce tour
    static char CHARACTER_ALLY = 'A';
    static char CHARACTER_ENEMY = 'E';

    protected int ascendingAllyStack;
    protected int downAllyStack;
    protected int ascendingEnemyStack;
    protected int downEnemyStack;
    protected boolean playEnemyStack = false;


    public CardTray(int ascendingStackAlly, int downAllyStack, int ascendingEnemyStack, int downEnemyStack) {
        this.ascendingAllyStack = ascendingStackAlly;
        this.downAllyStack = downAllyStack;
        this.ascendingEnemyStack = ascendingEnemyStack;
        this.downEnemyStack = downEnemyStack;
    }

    public CardTray(Player allyPlayer, Player enemyPlayer) {
        this.ascendingAllyStack = allyPlayer.getAscendingStack();
        this.downAllyStack = allyPlayer.getDownStack();
        this.ascendingEnemyStack = enemyPlayer.getAscendingStack();
        this.downEnemyStack = enemyPlayer.getDownStack();
    }

    //Pose une carte sur la stack ascendante allié
    protected boolean poseAscendingStackAlly(int card) {
        if(card > this.ascendingAllyStack || card == this.ascendingAllyStack - 10){
            this.ascendingAllyStack = card;
            return true;
        }
        return false;
    }

    //Pose une carte sur la stack descendante allié
    protected boolean poseDownStackAlly(int card) {
        if(card < this.downAllyStack || card == this.downAllyStack + 10){
            this.downAllyStack = card;
            return true;
        }
        return false;
    }

    //Pose une carte sur la stack ascendante enemy
    protected boolean poseAscendingStackEnemy(int card) {
        if(card < this.ascendingEnemyStack && this.playEnemyStack == false){
            this.ascendingEnemyStack = card;
            this.playEnemyStack = true;
            return true;
        }
        return false;
    }

    //Pose une carte sur la stack descendante enemy
    protected boolean poseDownStackEnemy(int card) {
        if(card > this.downEnemyStack && this.playEnemyStack == false){
            this.downEnemyStack = card;
            this.playEnemyStack = true;
            return true;
        }
        return false;
    }

    //Pose une carte sur une stack
    public boolean poseCard(String card){

        int value = Integer.parseInt( card.substring(0,2));
        char typeStack = card.charAt(2);
        char whichSide = card.charAt(3);

        if(whichSide == CHARACTER_ALLY){
            if (typeStack == CHARACTER_ASCENDING_STACK){
                return  this.poseAscendingStackAlly(value);
            } else if (typeStack == CHARACTER_DOWN_STACK) {
                return this.poseDownStackAlly(value);
            }
            return  false;
        }


        if(whichSide == CHARACTER_ENEMY){
            if (typeStack == CHARACTER_ASCENDING_STACK){
                return this.poseAscendingStackEnemy(value);
            } else if (typeStack == CHARACTER_DOWN_STACK) {
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
