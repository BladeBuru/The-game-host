package fr.pantheonsorbonne.cri.guest.guest1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Creation d'un arbre de possibilités des combinaisons possibles en fonction des cartes en main
 */
public class Tree {

    int cardAndStack; //Valeur et stack de la carte
    int valueSum; //Somme des valeur de chaque noeud parents depuis le noeud actuel
    int nodeIndex; //Index du noeud
    float nodeValue; //Valeur du noeud
    int[] stacks; //Stacks updated
    Tree parent; //Node parent
    List<Tree> childs = new ArrayList<Tree>(); //Node childs

    public Tree(int cardAndStack, int valueSum, Tree nparent, int index, int[] stacks){
        this.nodeIndex = index + 1;
        this.nodeValue = valueSum / (index + 1);
        this.valueSum = valueSum;
        this.cardAndStack = cardAndStack;
        this.parent = nparent;
        this.stacks = stacks;
    }

    //Génération de chaque noeud
    public Tree createTree(List<Integer> remainingCards, boolean isEnemyCardPlaced){

        if(remainingCards.size()==0){
            return this;
        }

        //On ajoute toute les possibilités de coups pour chaque cartes restantes dans la main
        for(int i : remainingCards){

            List<Integer> stockRemainingCard = new ArrayList<>();

            for(int j : remainingCards){
                stockRemainingCard.add(j);
            }

            stockRemainingCard.remove(stockRemainingCard.indexOf(i));

            int[] nbStacks = findStacks(isEnemyCardPlaced, i);

            int card = i * 10;

            //Pour toutes les stacks sur lesquelles il est possible de jouer on ajoute un enfant
            for(int j : nbStacks){

                j = j - 1;

                int valueSum = this.valueSum + this.getValue(i, j);

                int[] stacks = new int[4];

                for(int k = 0; k<=3; k++){
                    stacks[k] = this.stacks[k];
                }

                stacks[j] = i;

                switch (j){
                    case 0:
                        this.addChild(card + 1, valueSum, stockRemainingCard, isEnemyCardPlaced, stacks);
                        break;
                    case 1:
                        this.addChild(card, valueSum, stockRemainingCard, isEnemyCardPlaced, stacks);
                        break;
                    case 2:
                        this.addChild(card + 3, valueSum, stockRemainingCard, true, stacks);
                        break;
                    case 3:
                        this.addChild(card + 2, valueSum, stockRemainingCard, true, stacks);
                        break;
                }

            }

            stockRemainingCard.add(i);

        }

        return this;

    }

    //Ajout d'un enfant
    private void addChild(int cardAndStack, int valueSum, List<Integer> remainingCards, boolean isEnemyCardPlaced, int[] stacks){
        this.childs.add(new Tree(cardAndStack, valueSum, this, this.nodeIndex, stacks).createTree(remainingCards, isEnemyCardPlaced));
    }

    //Methode de calcul de la valeur d'un noeud
    private int getValue(int cardValue, int n){

        int value = 60 - (Math.abs(this.stacks[n]-cardValue));

        if(n<2) {
            switch (n) {
                case 0:
                    if (cardValue == this.stacks[n] - 10) {
                        return 70;
                    }
                    break;
                case 1:
                    if (cardValue == this.stacks[n] + 10) {
                        return 70;
                    }
                    break;
            }
        }

        return value;
    }

    //Renvoie les stacks sur lesquelles une carte peut être joué
    private int[] findStacks(boolean enemyCardPlaced, int card){

        List<Integer> stacksUsed = new ArrayList<>();

        if(card > this.stacks[0] || card == this.stacks[0] - 10){
            stacksUsed.add(1);
        }
        if(card < this.stacks[1] || card == this.stacks[1] + 10){
            stacksUsed.add(2);
        }
        if(!enemyCardPlaced){
            if(card < this.stacks[2]){
                stacksUsed.add(3);
            }
            if(card > this.stacks[3]){
                stacksUsed.add(4);
            }
        }

        int[] stacksId = new int[stacksUsed.size()];

        int index = 0;

        for(int i : stacksUsed){
            stacksId[index] = i;
            index++;
        }

        return stacksId;
    }

    //Affiche l'arbre
    public void showTree(){

        System.out.println("   ".repeat(this.nodeIndex) + this.nodeIndex + " - " + this.cardAndStack + " - " + this.nodeValue  + " - " +
                this.stacks[0] + " " +
                this.stacks[1] + " " +
                this.stacks[2] + " " +
                this.stacks[3]);

        for(Tree i : this.childs){
            i.showTree();
        }

    }

    //Retourne la meilleur composition de coups calculé
    public List<Integer> findBestCombinaison(){
        return this.findBestValue(this).findPath(new ArrayList<>());
    }

    //Triuve le noeud avec la meilleur valeur
    private Tree findBestValue(Tree TreeBest){

        for(Tree i : this.childs){
            Tree stock = i.findBestValue(TreeBest);
            if(stock.nodeValue > TreeBest.nodeValue && stock.nodeIndex>=2){
                TreeBest = stock;
            }
        }

        if(this.nodeValue > TreeBest.nodeValue && this.nodeIndex>=2){
            TreeBest = this;
        }

        return TreeBest;

    }

    //Renvoi le chemin de parents en parents vers un noeud
    private List<Integer> findPath(List<Integer> path){

        if(this.parent != null){
            this.parent.findPath(path);
            path.add(this.cardAndStack);
        }

        return path;

    }

}
