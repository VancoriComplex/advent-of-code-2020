package day25;

import java.util.List;

public class Day25 {

    public static long part1(List<String> input) {
        int cardPublicKey = Integer.parseInt(input.get(0));
        int doorPublicKey = Integer.parseInt(input.get(1));
        int divider = 20201227;
        int subject = 7;

        int cardLoopSize = getLoopSize(subject, divider, cardPublicKey);
        int doorLoopSize = getLoopSize(subject, divider, doorPublicKey);

        long cardEncryptionKey = getEncryptionKey(cardPublicKey, divider, doorLoopSize);
        long doorEncryptionKey = getEncryptionKey(doorPublicKey, divider, cardLoopSize);

        assert cardEncryptionKey == doorEncryptionKey;

        return cardEncryptionKey;
    }

    private static int getLoopSize(int subject, int divider, int publicKey) {
        long key = 1;
        int loopSize = 0;
        while (key != publicKey) {
            loopSize++;
            key = transform(key, subject, divider);
        }
        return loopSize;
    }

    private static long getEncryptionKey(int subject, int divider, int loopSize) {
        long encryptionKey = 1;
        for (int i = 0; i < loopSize; i++) {
            encryptionKey = transform(encryptionKey, subject, divider);
        }
        return encryptionKey;
    }

    private static long transform(long key, int subject, int divider) {
        return (key * subject)%divider;
    }
}
