package fr.pantheonsorbonne.cri.guest.guest1;

import java.util.List;

public class Player1Hard extends Player1Engine {

    public Player1Hard(String name) {
        super(name);
    }

    @Override
    public String bestMoov() {

        Tree tree = new Tree(0,0, null,0,this.getStacksOrdered());

        tree.createTree(this.cardInHand, false);

        StringBuilder data = new StringBuilder();

        List<Integer> results = tree.findBestCombinaison();

        for(int i : results){
            data.append(setCardForFunction(i/10,i%10));
            if(i != results.get(results.size()-1)) {
                data.append(",");
            }
        }

        return data.toString();

    }

    private int[] getStacksOrdered(){
        return new int[] {this.ascendingStack, this.downStack, getAscendingStackEnemy(), getDownStackEnemy()};
    }

}
