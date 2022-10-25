package fr.pantheonsorbonne.cri.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Joueur {
static  int VALUE_ASCENDING_STACK = 1 ;
static  int VALUE_DOWN_STACK = 60;
static int CARD_IN_HAND = 6;

     private String nom;
     private int ascendingStack = VALUE_ASCENDING_STACK;
     private int downStack = VALUE_DOWN_STACK;
     private List<Integer> cardInHand ;
     private List<Integer> pick ;


     public Joueur(){
        this.pick = new ArrayList<>(VALUE_DOWN_STACK - VALUE_ASCENDING_STACK - 1);
        for (int i = VALUE_ASCENDING_STACK + 1; i < VALUE_DOWN_STACK; i++) {
            this.pick.add(i);
        }
        Collections.shuffle(pick);
        this.cardInHand = new ArrayList<>(CARD_IN_HAND);
        drawACard(CARD_IN_HAND);
     }


     public void drawACard(int numberOfCardsToDraw){

        for (int l = 0; l < numberOfCardsToDraw && this.cardInHand.size() <= CARD_IN_HAND  ; l++) {
            this.cardInHand.add(this.pick.get(this.pick.size()-1));
            this.pick.remove(this.pick.size()-1) ;
        }
        Collections.sort(this.cardInHand);
        
        }

     }

