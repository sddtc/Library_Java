package sddtc.library.java.algorithm;

import java.util.Random;

/**
 * Created by sddtc on 16/5/16.
 */
public class ShuffleArrayFisherYates {
    private static char[] pukers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public void shuffleArrayFisherYates(char[] array, int length) {
        int i = length, j;
        char temp;
        Random random = new Random();

        if (i == 0) {
            return;
        }
        while (i > 0) {
            i--;
            j = random.nextInt(10) % (i + 1);
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        for (int k = 0; k < array.length; k++) {
            System.out.print(array[k] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        ShuffleArrayFisherYates shuffleArrayFisherYates = new ShuffleArrayFisherYates();
        for (int i = 0; i < 5; i++) {
            shuffleArrayFisherYates.shuffleArrayFisherYates(pukers, pukers.length);
        }
    }
}
