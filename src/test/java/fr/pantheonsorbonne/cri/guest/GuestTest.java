package fr.pantheonsorbonne.cri.guest;

import fr.pantheonsorbonne.cri.game.PlayerEngine;

import fr.pantheonsorbonne.cri.guest.guest2.PlayerMedium;

import java.util.ArrayList;
import java.util.Arrays;

public class GuestTest {

    public static void main(String[] args) {
        PlayerEngine p1 = new PlayerMedium("p1");
        p1.updateStack(20,53,17,48);
        p1.getCardsFrom(new ArrayList<>(Arrays.asList(13,25,36,54)));
        //p1.getCardsFrom(new ArrayList<>(Arrays.asList(54,20,13,36,10)));
        System.out.println("Result : \n");
        System.out.println(p1.bestMoov());
    }
}
