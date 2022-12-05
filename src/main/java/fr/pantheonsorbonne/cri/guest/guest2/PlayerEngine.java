package fr.pantheonsorbonne.cri.guest.guest2;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerEngine extends Player {

    protected int ascendingStackEnemy;
    protected int downStackEnemy;

    public PlayerEngine(String name) {
        this.ascendingStack = Player.VALUE_ASCENDING_STACK;
        this.downStack = VALUE_DOWN_STACK;
        this.ascendingStackEnemy = VALUE_ASCENDING_STACK;
        this.downStackEnemy = VALUE_DOWN_STACK;
        this.cardInHand = new ArrayList<>(CARD_IN_HAND);
        this.name = name;
    }

    public int getAscendingStackEnemy() {
        return ascendingStackEnemy;
    }

    public int getDownStackEnemy() {
        return downStackEnemy;
    }

    //Renvoi du coup joué par le joueur
    public abstract String bestMoov();

    //Adapte un coup sous le bon format de données
    protected String setCardForFunction(String cardtoset, int whichset){
        switch(whichset){
            case 0:
                cardtoset = cardtoset + "vA";
                break;
            case 1:
                cardtoset = cardtoset + "^A";
                break;
            case 2:
                cardtoset = cardtoset + "vE";
                break;
            default:
                cardtoset = cardtoset + "^E";
                break;
        }
        return cardtoset;
    }

    public void updateStack (int ascendingStack,int downStack,int ascendingStackEnemy, int downStackEnemy){
        this.ascendingStack = ascendingStack;
        this.downStack = downStack ;
        this.ascendingStackEnemy = ascendingStackEnemy;
        this.downStackEnemy = downStackEnemy;
    }

    public void getCardsFrom(ArrayList<Integer> cardFrom){
        cardInHand.addAll(cardFrom);
    }


}
