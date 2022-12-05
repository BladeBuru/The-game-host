package fr.pantheonsorbonne.cri.guest;
import java.util.ArrayList;
import java.util.Arrays;
import fr.pantheonsorbonne.cri.guest.guest1.*;

public class PlayerEasy1Test {
    public static void main(String[] args) {
        PlayerEasy1 p1 =new PlayerEasy1("p1");
        p1.updateStack(59,05,48,14);
        p1.getCardsFrom(new ArrayList<>(Arrays.asList(13,19,23,27,46,49)));
        //p1.getCardsFrom(new ArrayList<>(Arrays.asList(54,20,13,36,10)));
        System.out.print("Result : ");
        System.out.println(p1.easyPoseCard());
    }
}
