package ru.kolyanpie.cube.core.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmController {
    private final ArrayList<String> rotates;
    public static final List<String> cube2x2x2 = Arrays.asList("U", "R", "F", "U'", "R'", "F'", "D", "L", "B", "D'", "L'", "B'", "U2", "R2", "F2", "D2", "L2", "B2");

    public AlgorithmController(List<String> rotates) {
        this.rotates = new ArrayList<>(rotates);
    }

    public String simplify(String algorithm) throws IllegalArgumentException {
        if (algorithm.isEmpty()) {
            return "";
        }
        String[] strings = algorithm.split(" ");
        return simplify(strings);
    }

    public String simplify(String... algorithm) throws IllegalArgumentException {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(algorithm));
        ArrayList<String> strings1 = new ArrayList<>(Arrays.asList(algorithm));
        simplifyPare(strings1);
        while (!strings.equals(strings1)) {
            strings = new ArrayList<>(strings1);
            simplifyPare(strings1);
        }
        String result = strings.toString();
        result = result.replaceAll(",", "");
        return result.substring(1, result.length() - 1);
    }

    private void simplifyPare(ArrayList<String> algorithm) throws IllegalArgumentException {
        if (algorithm.isEmpty()) {
            return;
        }
        String s = algorithm.get(0);
        if (!rotates.contains(s)) {
            throw new IllegalArgumentException(s);
        }
        for (int i = 1; i < algorithm.size(); i++) {
            s = algorithm.get(i - 1);
            if (!rotates.contains(algorithm.get(i))) {
                throw new IllegalArgumentException(algorithm.get(i));
            }
            if (algorithm.get(i).substring(0, 1).equals(s.substring(0, 1))) {
                if (algorithm.get(i).length() == 1) {
                    if (s.length() == 1) {
                        algorithm.remove(i);
                        algorithm.remove(i - 1);
                        algorithm.add(i - 1, s + "2");
                        return;
                    }
                    switch (s.charAt(1)) {
                        case '\'':
                            algorithm.remove(i);
                            algorithm.remove(i - 1);
                            return;
                        case '2':
                            algorithm.remove(i);
                            algorithm.remove(i - 1);
                            algorithm.add(i - 1, s.charAt(0) + "'");
                            return;
                    }
                }
                if (algorithm.get(i).length() == 2) {
                    switch (algorithm.get(i).charAt(1)) {
                        case '\'':
                            if (s.length() == 1) {
                                algorithm.remove(i);
                                algorithm.remove(i - 1);
                                return;
                            }
                            switch (s.charAt(1)) {
                                case '\'':
                                    algorithm.remove(i);
                                    algorithm.remove(i - 1);
                                    algorithm.add(i - 1, s.charAt(0) + "2");
                                    return;
                                case '2':
                                    algorithm.remove(i);
                                    algorithm.remove(i - 1);
                                    algorithm.add(i - 1, s.substring(0, 1));
                                    return;
                            }
                        case '2':
                            if (s.length() == 1) {
                                algorithm.remove(i);
                                algorithm.remove(i - 1);
                                algorithm.add(i - 1, s.charAt(0) + "'");
                                return;
                            }
                            switch (s.charAt(1)) {
                                case '\'':
                                    algorithm.remove(i);
                                    algorithm.remove(i - 1);
                                    algorithm.add(i - 1, s.substring(0, 1));
                                    return;
                                case '2':
                                    algorithm.remove(i);
                                    algorithm.remove(i - 1);
                                    return;
                            }
                    }
                }
            }
        }
    }
}
