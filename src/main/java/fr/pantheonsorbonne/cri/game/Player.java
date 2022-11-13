package fr.pantheonsorbonne.cri.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
static  int VALUE_ASCENDING_STACK = 1 ;
static  int VALUE_DOWN_STACK = 60;
public static int CARD_IN_HAND = 6;

     private String name;
     private int ascendingStack;
     private int downStack;
     private List<Integer> cardInHand ;
     private List<Integer> pick;


    public Player(int numberEnd){
        this.ascendingStack = VALUE_ASCENDING_STACK;
        this.downStack = VALUE_DOWN_STACK;
        this.pick = new ArrayList<>(VALUE_DOWN_STACK - VALUE_ASCENDING_STACK - 1);
        for (int i = VALUE_ASCENDING_STACK + 1; i < numberEnd; i++) {
            this.pick.add(i);
        }
        Collections.shuffle(pick);
        this.cardInHand = new ArrayList<>(CARD_IN_HAND);
        getDrawCards(CARD_IN_HAND);
    }
    public Player(){
            new Player(VALUE_DOWN_STACK);
    }

     public String getDrawCards(int numberOfCardsToDraw){
            StringBuilder stringHand  = new StringBuilder();
        for (int l = 0; l < numberOfCardsToDraw && this.cardInHand.size() <= CARD_IN_HAND && this.cardInHand.size() > 0; l++) {
            this.cardInHand.add(this.pick.get(this.pick.size()-1));
            stringHand.append(this.pick.size()-1);
            stringHand.append(' ');
            this.pick.remove(this.pick.size()-1);
        }
        Collections.sort(this.cardInHand);
         return stringHand.toString();
     }


    public List<Integer> getCardInHand() {
        return cardInHand;
    }

    public boolean cardIsInHand(int number){

        for (Integer cardHand:
             this.cardInHand) {
            if (cardHand == number)return true;
        }
        return false;
    }


    static final public ArrayList<Integer> splitTheNumber(ArrayList<String> cardsPlay){
        ArrayList<Integer> cards= new ArrayList<>();

        for (String card : cardsPlay) {
            cards.add(Integer.parseInt(card.substring(0, 2)));
        }
        return cards;
    }

    public List<Integer> getPick() {
        return pick;
    }

    public int getAscendingStack() {
        return ascendingStack;
    }

    public int getDownStack() {
        return downStack;
    }
}

