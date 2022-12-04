package fr.pantheonsorbonne.cri.guest.guest1;

import fr.pantheonsorbonne.cri.guest.guest2.PlayerEngine;

import java.util.ArrayList;
import java.util.List;

public class Player1Medium extends PlayerEngine {

    public Player1Medium(String name) {
        super(name);
    }

    @Override
    public String bestMoov() {

        Tree tree = new Tree(0,0, null,0,this.getStacksOrdered());

        tree.createTree(this.cardInHand, false);

        StringBuilder data = new StringBuilder();

        List<Integer> results = tree.findBestCombinaison();
        ArrayList<Integer> toRemove = new ArrayList<>();

        for(int i : results){

            toRemove.add(i/10);

            if(i/10 < 10) {
                data.append("0" + setCardForFunction(String.valueOf((i / 10)), i % 10));
            }
            else{
                data.append(setCardForFunction(String.valueOf(i / 10), i % 10));
            }

            if(i != results.get(results.size()-1)) {
                data.append(",");
            }
        }

        this.removeCards(toRemove);

        return data.toString();

    }

    private int[] getStacksOrdered(){
        return new int[] {this.ascendingStack, this.downStack, getAscendingStackEnemy(), getDownStackEnemy()};
    }

}
