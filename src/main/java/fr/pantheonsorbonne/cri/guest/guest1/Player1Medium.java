package fr.pantheonsorbonne.cri.guest.guest1;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import fr.pantheonsorbonne.cri.game.CardTray;

public class Player1Medium extends Player1Engine {

    public Player1Medium(String name) {
        super(name);
    }

    private String lessTen(CardTray testCardTray, List<Integer> cardInHand) {
        String oneCardToPose = "";
        for (int i = 0; i < cardInHand.size(); i++) {
            if (testCardTray.getAscendingAllyStack() - 10 == cardInHand.get(i)) {
                oneCardToPose = String.format("%02d", cardInHand.get(i)) + "^A";
                testCardTray.poseCard(oneCardToPose);
                cardInHand.remove(i);
                return oneCardToPose;
            } else if (testCardTray.getDownAllyStack() + 10 == cardInHand.get(i)) {
                oneCardToPose = String.format("%02d", cardInHand.get(i)) + "vA";
                testCardTray.poseCard(oneCardToPose);
                cardInHand.remove(i);
                return oneCardToPose;
            }
        }
        return oneCardToPose;
    }

    public String firstCardToPose(CardTray cardTrayTest, List<Integer> cardInHand){
        String cardToPose = "";
        int diff=100;
        int indiceCardToDelete=-1;
        //récup carte intéréssante pour ascendingCard
        for(int i=0; i<cardInHand.size();i++){
            if(cardInHand.get(i)>cardTrayTest.getAscendingAllyStack() && Math.abs(cardInHand.get(i)-cardTrayTest.getAscendingAllyStack())<diff){
                diff=Math.abs(cardInHand.get(i)-cardTrayTest.getAscendingAllyStack());
                cardToPose=String.format("%02d", cardInHand.get(i))+"^A";
                indiceCardToDelete=i;
            }
        }
        //récup carte intéréssante pour downStack
        for(int j=cardInHand.size()-1;j>=0;j--){
            if (cardInHand.get(j)<cardTrayTest.getDownAllyStack() && Math.abs(cardInHand.get(j)-cardTrayTest.getDownAllyStack())<diff){
                diff=Math.abs(cardInHand.get(j)-cardTrayTest.getDownAllyStack());
                cardToPose=String.format("%02d", cardInHand.get(j))+"vA";
                indiceCardToDelete=j;
            }
        }
        cardTrayTest.poseCard(cardToPose);
        cardInHand.remove(indiceCardToDelete);
        return cardToPose;
    }

    private String cardPoseEnnemy(CardTray testCardTray, List<Integer> cardInHand) {
        String oneCardToPose = "";
        for (int i = 0; i < cardInHand.size(); i++) {
            if (testCardTray.getAscendingEnemyStack() - 1 == cardInHand.get(i)) {
                oneCardToPose = String.format("%02d", cardInHand.get(i)) + "^E";
                testCardTray.poseCard(oneCardToPose);
                cardInHand.remove(i);
                return oneCardToPose;
            } else if (testCardTray.getDownEnemyStack() + 1 == cardInHand.get(i)) {
                oneCardToPose = String.format("%02d", cardInHand.get(i)) + "vE";
                testCardTray.poseCard(oneCardToPose);
                cardInHand.remove(i);
                return oneCardToPose;
            }
        }
        return oneCardToPose;
    }

    public List<Integer> triCard(List<Integer> cardInHand) {
        List<Integer> cardInOrder = new ArrayList<Integer>();
        for (int i = 0; i < cardInHand.size(); i++) {
            int mintmp=cardInHand.get(i);
            int indicemin=i;
            for(int j=0; j<cardInHand.size();j++){
                if(cardInHand.get(j)<mintmp){
                    mintmp=cardInHand.get(j);
                    indicemin=j;
                }
            }
            cardInHand.remove(indicemin);
            cardInHand.add(100);
            cardInOrder.add(mintmp);
        }
        return cardInOrder;
    }

    public String bestMoov() {

        CardTray cardTraySet = new CardTray(this.ascendingStack, this.downStack, this.ascendingEnemyStack,
                this.downEnemyStack);
        List<Integer> cardInHand = triCard(this.getCardInHand());
        String cardsToPose = "";

        cardsToPose = lessTen(cardTraySet, cardInHand); // vérification 10 de moins possible
        if (!cardsToPose.equals("")) {
            String secondCard = "";

            // verification si on peut pas poser 2 tens d'un coup
            secondCard = lessTen(cardTraySet, cardInHand);
            if (!secondCard.equals("")) {
                return cardsToPose + "," + secondCard; // return si combo
            }

            secondCard = cardPoseEnnemy(cardTraySet, cardInHand);
            if (!secondCard.equals("")) {
                return cardsToPose + "," + secondCard; // return si 10 de lmoins et ennemie intéréssant
            }

            // deuxieme carte si il y a un 10 de posé
            secondCard = firstCardToPose(cardTraySet, cardInHand);
            cardsToPose = cardsToPose + "," + secondCard;

        } else {
            cardsToPose = cardPoseEnnemy(cardTraySet, cardInHand);
            if (!cardsToPose.equals("")) {
                cardsToPose = cardsToPose + "," + firstCardToPose(cardTraySet, cardInHand);
            } else {
                cardsToPose = firstCardToPose(cardTraySet,cardInHand);
                String secondCard="";
                secondCard=lessTen(cardTraySet, cardInHand);
                if(!secondCard.equals("")){
                    return cardsToPose + "," + secondCard;
                }
                cardsToPose = cardsToPose + "," + firstCardToPose(cardTraySet, cardInHand);
            }
        }
        return cardsToPose;
    }
}