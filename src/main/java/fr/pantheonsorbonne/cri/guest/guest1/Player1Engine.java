package fr.pantheonsorbonne.cri.guest.guest1;

import fr.pantheonsorbonne.cri.game.Player;

import java.util.ArrayList;

public abstract class Player1Engine extends Player {

    protected int ascendingEnemyStack;
    protected int downEnemyStack;

    public Player1Engine(String name) {
        this.ascendingStack = Player.VALUE_ASCENDING_STACK;
        this.downStack = VALUE_DOWN_STACK;
        this.ascendingEnemyStack = VALUE_ASCENDING_STACK;
        this.downEnemyStack = VALUE_DOWN_STACK;
        this.cardInHand = new ArrayList<>(CARD_IN_HAND);
        this.name = name;
    }

    public abstract String bestMoov();

    public int getAscendingStackEnemy() {
        return ascendingEnemyStack;
    }

    public int getDownStackEnemy() {
        return downEnemyStack;
    }

    protected String setCardForFunction(int cardvalue, int whichset){
        String cardtoset;
        switch(whichset){
            case 0:
                cardtoset = cardvalue + "vA";
                break;
            case 1:
                cardtoset = cardvalue + "^A";
                break;
            case 2:
                cardtoset = cardvalue + "vE";
                break;
            default:
                cardtoset = cardvalue + "^E";
                break;
        }
        return cardtoset;
    }

    public void updateStack (int ascendingStack,int downStack,int ascendingEnemyStack, int downEnemyStack){
        this.ascendingStack = ascendingStack;
        this.downStack = downStack ;
        this.ascendingEnemyStack = ascendingEnemyStack;
        this.downEnemyStack = downEnemyStack;
    }

    public void getCardsFrom(ArrayList<Integer> cardFrom){
        cardInHand.addAll(cardFrom);
    }

}
