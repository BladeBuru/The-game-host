package fr.pantheonsorbonne.cri.guest.guest2;

import fr.pantheonsorbonne.cri.game.CardTray;

import java.util.List;

public class PlayerEasy extends PlayerEngine{


    public PlayerEasy(String name){
        super(name);
    }

    @Override
    public String bestMoov() {
        CardTray cardTraySet = new CardTray(this.ascendingStack, this.downStack, this.ascendingStackEnemy, this.downStackEnemy);
        List<Integer> cardInHand = this.getCardInHand();
        for (int i = 0; i < cardInHand.size(); i++) {
            String testcard1 = String.format("%02d", cardInHand.get(i)); //première carte
            int j = 0;
            while (j < 4) { //on passe chaque possibilité de construction premiere carte
                String currentCard1 = setCardForFunction(testcard1, j); //on set la possibilité
                j++;
                for (int k = 0; k < cardInHand.size(); k++) {
                    if (k != i) {
                        String testcard2 = String.format("%02d", cardInHand.get(k)); //deuxieme carte
                        int h = 0;
                        while (h < 4) { //on passe chaque possibilité de construction 2eme carte
                            String currentCard2 = setCardForFunction(testcard2, h); //on set la possibilité
                            h++;
                            CardTray cardTraySet2 = new CardTray(this.ascendingStack, this.downStack, this.ascendingStackEnemy, this.downStackEnemy);
                            if (cardTraySet2.poseCard(currentCard1) && cardTraySet2.poseCard(currentCard2)) { //on test si les deux se pose
                                return currentCard1 + "," + currentCard2;
                            }
                        }
                    }

                }
            }
        }
        return "nada";
    }




}
