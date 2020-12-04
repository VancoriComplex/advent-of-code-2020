package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {

    public static class Part1 {
        private static final Set<String> passportFields =
                new HashSet<>
                        (Arrays.asList(
                                "byr", "iyr", "eyr",
                                "hgt", "hcl", "ecl",
                                "pid"));

        public static int getValidPassportsCount(List<String> passports) {
            int validPassportsCount = 0;
            Set<String> passportFieldsCache = new HashSet<>();

            for (int lineIndex = 0; lineIndex < passports.size(); lineIndex++) {
                if (passports.get(lineIndex).isEmpty()) {
                    if (passportFieldsCache.containsAll(passportFields))
                        validPassportsCount++;
                    passportFieldsCache.clear();
                    continue;
                }
                String[] passportFields = getPassportFields(passports, lineIndex);
                for (String field : passportFields) {
                    String code = getFieldCode(field);
                    passportFieldsCache.add(code);
                }
            }
            return validPassportsCount;
        }

        private static String[] getPassportFields(List<String> passports, int lineIndex) {
            return passports.get(lineIndex).split(" ");
        }

        private static String getFieldCode(String field) {
            return field.substring(0, field.indexOf(":"));
        }
    }

    public static class Part2 {

        private static final Set<String> validPassportFields =
                new HashSet<>
                        (Arrays.asList(
                                "byr", "iyr", "eyr",
                                "hgt", "hcl", "ecl",
                                "pid"
                        ));
        private static final Set<String> validEyeColors =
                new HashSet<>
                        (Arrays.asList(
                                "amb", "blu", "brn",
                                "gry", "grn", "hzl",
                                "oth"
                        ));

        public static int getValidPassportsCount(List<String> passports) {
            int validPassportsCount = 0;
            Set<String> passportFieldsCache = new HashSet<>();

            for (int lineIndex = 0; lineIndex < passports.size(); lineIndex++) {
                if (passports.get(lineIndex).isEmpty()) {
                    if (passportFieldsCache.containsAll(validPassportFields))
                        validPassportsCount++;
                    passportFieldsCache.clear();
                    continue;
                }
                String[] passportFields = getPassportFields(passports, lineIndex);
                for (String field : passportFields) {
                    String code = getFieldCode(field);
                    String value = getFieldValue(field);
                    if (isValidField(code, value))
                        passportFieldsCache.add(code);
                }
            }
            return validPassportsCount;
        }

        private static String[] getPassportFields(List<String> passports, int lineIndex) {
            return passports.get(lineIndex).split(" ");
        }

        private static String getFieldCode(String field) {
            return field.substring(0, field.indexOf(":"));
        }

        private static String getFieldValue(String field) {
            return field.substring(field.indexOf(":") + 1);
        }

        private static boolean isValidField(String code, String value) {
            switch (code) {
                case "byr":
                    return isValidYear(value, 1920, 2002);
                case "iyr":
                    return isValidYear(value, 2010, 2020);
                case "eyr":
                    return isValidYear(value, 2020, 2030);
                case "hgt":
                    return isValidHeight(value);
                case "hcl":
                    return isValidHairColor(value);
                case "ecl":
                    return isValidEyeColor(value);
                case "pid":
                    return isValidPassportId(value);
            }
            return false;
        }

        private static boolean isValidYear(String value, int atLeast, int atMost) {
            int birthYear = getYear(value);
            return isNumberBetween(birthYear, atLeast, atMost);
        }

        private static boolean isValidHeight(String value) {
            String endsWith = getHeightUnitsOfMeasure(value);
            switch (endsWith) {
                case "cm":
                    int heightCm = getHeightValue(value);
                    return (isNumberBetween(heightCm, 150, 193));
                case "in":
                    int heightIn = getHeightValue(value);
                    return (isNumberBetween(heightIn, 59, 76));
            }
            return false;
        }

        private static boolean isValidHairColor(String hairColor) {
            return hairColor.matches("#[a-f0-9]{6}");
        }

        private static boolean isValidEyeColor(String eyeColor) {
            return validEyeColors.contains(eyeColor);
        }

        private static boolean isValidPassportId(String passportId) {
            return passportId.matches("\\d{9}");
        }

        private static int getYear(String value) {
            return Integer.parseInt(value);
        }

        private static boolean isNumberBetween(int number, int atLeast, int atMost) {
            return (atLeast <= number && number <= atMost);
        }

        private static String getHeightUnitsOfMeasure(String value) {
            if (value.length() > 2)
                return value.substring(value.length() - 2);
            return "";
        }

        private static int getHeightValue(String value) {
            return Integer.parseInt(value.substring(0, value.length() - 2));
        }
    }
}
