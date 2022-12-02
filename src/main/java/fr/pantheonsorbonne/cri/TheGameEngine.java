package fr.pantheonsorbonne.cri;

import fr.pantheonsorbonne.cri.game.CardTray;
import fr.pantheonsorbonne.cri.game.Player;


import java.util.*;

public abstract class TheGameEngine {


    protected void play() {
        Stack<Player> players = new Stack<>();
        players.push(new Player());
        players.push(new Player());

        {
            ArrayList<String> playersName = (ArrayList) getInitialPlayers();
            players.get(0).setName(playersName.get(0));
            players.get(1).setName(playersName.get(1));
        }

        for (Player player : players) {
            giveCardsPlayer(player.getName(), player.getDrawCards(Player.CARD_IN_HAND));

        }


        while (true) {
            Player firstPlayer = players.pop();
            Player secondPlayer = players.pop();
            players.push(firstPlayer);
            players.push(secondPlayer);
            //Test if a player has lost (He can't play at least two cards)
            if (!playerCanPlay(firstPlayer, secondPlayer)) {
                declareWinner(secondPlayer.getName());
                break;
            }
            if (playRound(firstPlayer, secondPlayer, getCardsPlayed(firstPlayer.getName()))) {
                break;
            }
            // Test if a player to win (He has no more cards in his hand and in the draw pile)
            if (playerWin(firstPlayer)){
                declareWinner(firstPlayer.getName());
                break;
            }


        }


    }



    protected boolean playRound(Player firstPlayer, Player secondPlayer, ArrayList<String> cardsPlay) {
        //Minimum two cards played and maximum number in hand
        if (cardsPlay.size() < 2 || cardsPlay.size() > firstPlayer.getCardInHand().size()) return false;

        ArrayList<Integer> cardsJustNumber = Player.splitTheNumber(cardsPlay);
        //All the cards played are present in his hand
        for (Integer card : cardsJustNumber) {
            if (!firstPlayer.cardIsInHand(card))
                return false; // TODO Changer la façon de récupère le int
        }

        if (cardsIsDuplicates(cardsJustNumber)) return false;

        //Copies cards on the table so as not to modify the original in case of false. CardTray has the break rules
        CardTray cardTray = new CardTray(firstPlayer, secondPlayer);

        //If all the cards have been placed it means that it went well
        for (String card : cardsPlay) {
            if (!cardTray.poseCard(card)) return false;
        }

        updateStacks(firstPlayer, secondPlayer, cardTray);

        firstPlayer.removeCards(cardsJustNumber);

        int numberCardsDrawn = calculationCardsDrawn(firstPlayer, cardTray);
        String cardsDraw = new String();
        if (numberCardsDrawn > 0) {
            cardsDraw = firstPlayer.getDrawCards(numberCardsDrawn);
        }
        giveCardsPlayer(firstPlayer.getName(), cardsDraw);

        return true;
    }


    //Update both players stacks at the end of a valid turn
    private void updateStacks(Player firstPlayer, Player secondPlayer, CardTray cardTray) {
        firstPlayer.setAscendingStack(cardTray.getAscendingAllyStack());
        firstPlayer.setDownStack(cardTray.getDownAllyStack());
        secondPlayer.setAscendingStack(cardTray.getAscendingEnemyStack());
        secondPlayer.setDownStack(cardTray.getDownEnemyStack());

        updateStacksPlayer(firstPlayer.getName(),cardTray.getAscendingAllyStack(),cardTray.getDownAllyStack(),cardTray.getAscendingEnemyStack(),cardTray.getDownEnemyStack());
        updateStacksPlayer(secondPlayer.getName(),cardTray.getAscendingEnemyStack(),cardTray.getDownEnemyStack(),cardTray.getAscendingAllyStack(),cardTray.getDownAllyStack());
    }

    private static boolean cardsIsDuplicates(ArrayList<Integer> cards) {
        Set verifCards = new HashSet<>(Arrays.asList(cards));
        return verifCards.size() != cards.size();
    }

    protected static ArrayList<String> splitString(String cardsPlay) {
        ArrayList<String> cardsPlaySplit = new ArrayList<>(Arrays.asList(cardsPlay.split(",")));
        return cardsPlaySplit;
    }

    protected static ArrayList<Integer> splitInteger(String cardsPlay) {
        ArrayList<String> cardsPlaySplit =  new ArrayList<>(Arrays.asList(cardsPlay.split(",")));
        ArrayList<Integer> newList = new ArrayList<Integer>(cardsPlaySplit.size()) ;
        for (String myInt : cardsPlaySplit)
        {
            newList.add(Integer.valueOf(myInt));
        }
        return newList;
    }



    protected static String convertToData(ArrayList<String> cards){
        StringBuilder dataMessage = new StringBuilder();
        for(String card : cards){
            dataMessage.append(card);
            if(card != cards.get(cards.size()-1)) {
                dataMessage.append(",");
            }
        }
        return dataMessage.toString();
    }



    private int calculationCardsDrawn(Player player1, CardTray cardTray) {
        if (cardTray.toPlayOnTheEnemyStack()) {
            int numberCardsDrawn = Player.CARD_IN_HAND - player1.getCardInHand().size();
            if (player1.getPile().size() < numberCardsDrawn) return player1.getPile().size();
            return numberCardsDrawn;
        } else {
            if (player1.getPile().size() < 2) return player1.getPile().size();
            return 2;
        }
    }

    protected boolean playerWin(Player p1) {
        return (p1.getCardInHand().size() == 0 && p1.getPile().size() == 0);// to reform
    }


    //méthode choix carte
    protected String setCardForFunction(String cardtoset, int whichset) {
        switch (whichset) {
            case 0:
                cardtoset = cardtoset + "vA";
                break;
            case 1:
                cardtoset = cardtoset + "^A";
                break;
            case 2:
                cardtoset = cardtoset + "vE";
                break;
            default:
                cardtoset = cardtoset + "^E";
                break;
        }
        return cardtoset;
    }

    // Nouvelle méthode player can play
    protected boolean playerCanPlay(Player p1, Player p2) {
        List<Integer> cardInHand = p1.getCardInHand();
        for (int i = 0; i < cardInHand.size(); i++) {
            String testcard1 = String.format("%02d", cardInHand.get(i)); //première carte
            int j = 0;
            while (j < 4) { //on passe chaque possibilité de construction premiere carte
                String currentCard1 = setCardForFunction(testcard1, j); //on set la possibilité todo erreur
                j++;
                for (int k = 0; k < cardInHand.size(); k++) {
                    if (k != i) {
                        String testcard2 = String.format("%02d", cardInHand.get(k)); //deuxieme carte
                        int h = 0;
                        while (h < 4) { //on passe chaque possibilité de construction 2ème carte
                            String currentCard2 = setCardForFunction(testcard2, h); //on Set la possibilité
                            h++;
                            CardTray cardTraySet = new CardTray(p1.getAscendingStack(), p1.getDownStack(), p2.getAscendingStack(), p2.getDownStack());
                            if (cardTraySet.poseCard(currentCard1) && cardTraySet.poseCard(currentCard2)) { //on test si les deux se pose
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    protected abstract List<String> getInitialPlayers();

    protected abstract void declareWinner(String winner);

    protected abstract ArrayList<String> getCardsPlayed(String player);

    protected abstract void giveCardsPlayer(String player, String cards);

    protected abstract void updateStacksPlayer(String player,int ascendingStackAlly,int downStackAlly,int ascendingStackEnemy, int downStackEnemy);

}
