package day14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MaskSystem {

    long decodeValue(String value, String mask) {
        StringBuilder result = getAsBinaryStringReversed(value);
        StringBuilder binaryMask = new StringBuilder(mask);
        binaryMask.reverse();
        assert binaryMask.length() == result.length();

        for (int i = 0; i < binaryMask.length(); i++) {
            switch (binaryMask.charAt(i)) {
                case '0' -> result.replace(i, i + 1, String.valueOf(0));
                case '1' -> result.replace(i, i + 1, String.valueOf(1));
            }
        }

        result.reverse();
        return Long.parseLong(result.toString(), 2);
    }

    Set<Long> decodeMemoryAddress(String address, String mask) {
        StringBuilder addressBuilder = getAsBinaryStringReversed(address);
        StringBuilder bitMask = new StringBuilder(mask);
        bitMask.reverse();
        assert bitMask.length() == addressBuilder.length();

        List<Integer> indX = new ArrayList<>();
        for (int i = 0; i < bitMask.length(); i++) {
            switch (bitMask.charAt(i)) {
                case 'X' -> indX.add(i);
                case '1' -> addressBuilder.replace(i, i + 1, String.valueOf(1));
            }
        }

        return getAddressCombinations(addressBuilder, indX);
    }

    private StringBuilder getAsBinaryStringReversed(String value) {
        long v = Long.parseLong(String.valueOf(value));
        String vBinary = Long.toBinaryString(v);
        StringBuilder binaryBuilder = new StringBuilder(vBinary);
        binaryBuilder.reverse();
        binaryBuilder.append("0".repeat(36 - binaryBuilder.length()));
        return binaryBuilder;
    }

    private Set<Long> getAddressCombinations(StringBuilder addressBuilder, List<Integer> indX) {
        Set<Long> result = new HashSet<>();
        int[] binaryCounter = new int[indX.size()];
        int[] binary = new int[] {0, 1};
        for (int combination = 0;
             combination < Math.pow(2, indX.size());
             combination++) {
            StringBuilder nextAddress = new StringBuilder(addressBuilder);
            for (int i = 0; i < indX.size(); i++) {
                nextAddress.replace(
                        indX.get(i),
                        indX.get(i) + 1,
                        String.valueOf(binary[binaryCounter[i]]));
            }
            nextAddress.reverse();
            result.add(Long.parseLong(nextAddress.toString(), 2));
            for (int counter = binaryCounter.length - 1; counter >= 0; counter--) {
                if (binaryCounter[counter] + 1 < binary.length) {
                    binaryCounter[counter]++;
                    break;
                } else {
                    binaryCounter[counter] = 0;
                }
            }
        }
        return result;
    }
}
