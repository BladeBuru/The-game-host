package fr.pantheonsorbonne.cri.guest.guest2;

import java.util.ArrayList;

public class PlayerHard extends PlayerMedium {
    private int bonusPerCardPlayed = 3;
    private int malusCardPlayedOnEnemy = 1;
    public PlayerHard(String name) {
        super(name);
    }
    public PlayerHard(String name,int bonusPerCardPlayed, int malusCardPlayedOnEnemy) {
        this(name);
        this.malusCardPlayedOnEnemy = malusCardPlayedOnEnemy;
        this.bonusPerCardPlayed = bonusPerCardPlayed;
    }

    @Override
    protected ArrayList<String> calculateBestMoov(ArrayList<String> actualMoov, ArrayList<Integer> numberUsed , BestMoov bestMoov) {
        //si le minimum de carte n'a pas été encore atteint continuer d'ajouter des cartes)

        //Integer cardNumberactual = cardsInHand.remove(cardsInHand.size()-1);
        for (int j = 0; j < this.cardInHand.size(); j++) {
            //uniquement si la carte n'est pas tirée
            if (!numberUsed.contains(this.cardInHand.get(j))) {
                for (int i = 0; i < 4; i++) {
                    actualMoov.add(setCardForFunction(String.format("%02d", this.cardInHand.get(j)), i));
                    numberUsed.add(this.cardInHand.get(j));
                    CardTrayCalcul cardTray = new CardTrayCalcul(this,bonusPerCardPlayed,malusCardPlayedOnEnemy);
                    //Todo CardTrayCalcul cardTray = new CardTrayCalcul(this);
                    if (cardTray.poseAllCard(actualMoov)) {// essayer de poser si le coup n'est pas valid retirer la dernoier card ajouté et passer a la suivant
                        //compare uniquement s'il y a au moins deux cartes
                        if (actualMoov.size() >= 2 ){
                            //une a trouvé une meilleure combinaison
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
}
