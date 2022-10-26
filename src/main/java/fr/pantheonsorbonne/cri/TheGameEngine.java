package fr.pantheonsorbonne.cri;

public abstract class TheGameEngine {
    public void play(){
    //To do 
    }


    protected abstract boolean setPiles(/*...*/); 
    
    protected abstract void giveCardsToPlayer(/*...*/);

    protected abstract void playRound(/*...*/);
    
    protected boolean playerWin(Player p1){
        //To Do 
        //Verif de si il peut jouer ou si il a finit ses cartes à l'interieur de la fonction????
        return true;//to reform
    }

    protected static String getWinner(Player p1, Player p2){

        return;
    }

    protected abstract void declareWinner(String winner);

    

}
