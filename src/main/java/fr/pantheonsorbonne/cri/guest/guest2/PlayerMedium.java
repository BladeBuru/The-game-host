package fr.pantheonsorbonne.cri.guest.guest2;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerMedium extends PlayerEngine {

    private class BestMoov{
        ArrayList<String> bestMoov;
        int bestScore;

        private BestMoov(){
            bestMoov = new ArrayList<String>();
            bestScore = 300000;
        }

        private void setBestMoov(ArrayList<String> Moov){
            this.bestMoov = new ArrayList<>(Moov);
        }

        private void setInt(int bestscore){
            this.bestScore = bestscore;
        }
    }

    public PlayerMedium(String name) {
        super(name);
    }


    /**
     * @param actualMoov  Coup actuel dans la fonction récursive
     * @param numberUsed   les nombre qui sont utilisé dans actualMoov
     * @param bestMoov    Meilleur coup trouvé actuellement et son score
     * @return une chaine de charactère avec les meilleurs coups à joue
     */
    private ArrayList<String> calculateBestMoov( ArrayList<String> actualMoov, ArrayList<Integer> numberUsed ,BestMoov bestMoov) {
        //si le minimumn de carte n'a pas été encore atteint continuer d'ajouter des cartes )

        //Integer cardNumberactual = cardsInHand.remove(cardsInHand.size()-1);
        for (int j = 0; j < this.cardInHand.size(); j++) {
            //uniquement si la carte n'est pas tiré
            if (!numberUsed.contains(this.cardInHand.get(j))) {
                for (int i = 0; i < 4; i++) {
                    actualMoov.add(setCardForFunction(String.format("%02d", this.cardInHand.get(j)), i));
                    numberUsed.add(this.cardInHand.get(j));
                    CardTrayCalcul cardTray = new CardTrayCalcul(this);
                    if (cardTray.poseAllCard(actualMoov)) {// essayer de poser si le coup n'est pas valid retirer la dernoier card ajouté et passer a la suivant
                        //compare uniquement si il y a au moins deux cartes
                        if (actualMoov.size() >= 2 ){
                            //une a trouver une meilleur combionaison
                            if (cardTray.getScore() < bestMoov.bestScore){
                                bestMoov.setInt(cardTray.getScore());
                                bestMoov.setBestMoov(actualMoov);
                            }
                        }
                        this.calculateBestMoov(actualMoov,numberUsed ,bestMoov);
                    } else {
                        actualMoov.remove(actualMoov.size() - 1);
                        numberUsed.remove(numberUsed.size()-1);
                    }
                }
            }
        }

        if (numberUsed.size()>0){
            numberUsed.remove(numberUsed.size()-1);
            actualMoov.remove(actualMoov.size() - 1);
        }


        return bestMoov.bestMoov;
    }


    @Override
    public String bestMoov() {
        Integer bestScore = Integer.MAX_VALUE;
        ArrayList<String> bestMoov = calculateBestMoov(new ArrayList<>(), new ArrayList<>(),new BestMoov() );
        String bestMoovString ="";
        for (int i = 0; i < bestMoov.size(); i++) {
            if (i !=0){
                bestMoovString +=",";
            }
            bestMoovString += "" +bestMoov.get(i);
        }
        return bestMoovString ;
    }



}