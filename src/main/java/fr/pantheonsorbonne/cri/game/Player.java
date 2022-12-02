package fr.pantheonsorbonne.cri.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    protected static int VALUE_ASCENDING_STACK = 1;
    protected static int VALUE_DOWN_STACK = 60;
    public static int CARD_IN_HAND = 6;

    protected String name;
    protected int ascendingStack;
    protected int downStack;
    protected List<Integer> cardInHand;
    private List<Integer> pile;


    public Player(int numberEnd) {
        this.ascendingStack = VALUE_ASCENDING_STACK;
        this.downStack = VALUE_DOWN_STACK;
        this.pile = new ArrayList<>(VALUE_DOWN_STACK - VALUE_ASCENDING_STACK - 1);
        for (int i = VALUE_ASCENDING_STACK + 1; i < numberEnd; i++) {
            this.pile.add(i);
        }
        Collections.shuffle(pile);
        this.cardInHand = new ArrayList<>(CARD_IN_HAND);
        getDrawCards(CARD_IN_HAND);
    }

    public Player() {
        new Player(VALUE_DOWN_STACK);
    }

    public boolean cardIsInHand(int number) {

        for (Integer cardHand :
                this.cardInHand) {
            if (cardHand == number) return true;
        }
        return false;
    }


    // Met dans une array list les nombres des coups joués
    static final public ArrayList<Integer> splitTheNumber(ArrayList<String> cardsPlay) {
        ArrayList<Integer> cards = new ArrayList<>();

        for (String card : cardsPlay) {
            cards.add(Integer.parseInt(card.substring(0, 2)));
        }
        return cards;
    }

    public void removeCards(ArrayList<Integer> cards) {
        for (Integer card : cards) {
            this.cardInHand.remove(this.cardInHand.indexOf(card));
        }


    }

    public String getDrawCards(int numberOfCardsToDraw) {
        StringBuilder stringHand = new StringBuilder();
        for (int l = 0; l < numberOfCardsToDraw && this.cardInHand.size() <= CARD_IN_HAND && this.pile.size() > 0; l++) {
            this.cardInHand.add(this.pile.get(this.pile.size() - 1));
            if (l>0)stringHand.append(',');
            stringHand.append(this.pile.size() - 1);

            this.pile.remove(this.pile.size() - 1);
        }
        Collections.sort(this.cardInHand);
        return stringHand.toString();
    }

    public List<Integer> getCardInHand() {
        return cardInHand;
    }


    public List<Integer> getPile() {
        return pile;
    }

    public int getAscendingStack() {
        return ascendingStack;
    }

    public int getDownStack() {
        return downStack;
    }

    public void setAscendingStack(int ascendingStack) {
        this.ascendingStack = ascendingStack;
    }

    public void setDownStack(int downStack) {
        this.downStack = downStack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

