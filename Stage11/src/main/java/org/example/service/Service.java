package org.example.service;

import org.example.model.Monkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Service {

    public static List<List<String>> worriedMonkey(List<String> list) {
        List<List<String>> finalList = new ArrayList<>();
        List<String> s = new ArrayList<>();

        list.forEach(l -> {
                    if (l != null) {
                        s.add(l);
                    } else {
                        finalList.add(new ArrayList<>(s));
                        s.clear();
                    }
                });

        finalList.add(new ArrayList<>(s));
        return finalList;
    }
    private static Monkey createMonkeyFromInput(List<String> list){
        Pattern monkeyPattern = Pattern.compile("Monkey (\\d):");
        Pattern multiplyPattern = Pattern.compile("Operation: new = old \\* (\\d+)");
        Pattern additionPattern = Pattern.compile("Operation: new = old \\+ (\\d+)");
        Pattern testPattern = Pattern.compile("Test: divisible by (\\d+)");
        Pattern truePattern = Pattern.compile("If true: throw to monkey (\\d+)");
        Pattern falsePattern = Pattern.compile("If false: throw to monkey (\\d+)");
        Map<Boolean,Integer> tests = new HashMap<>();
        List<Integer> startingItem = new ArrayList<>();
        int id=-1;
        int multi=-1;
        int addition=-1;
        int operation=-1;

        for (String l : list){
            l= l.trim();
            Matcher monkeyMatch = monkeyPattern.matcher(l);
            if (monkeyMatch.find()) {
                id = Integer.parseInt(monkeyMatch.group(1));
            }
            Matcher multiplyMatch = multiplyPattern.matcher(l);
            if (multiplyMatch.find()) {
                multi = Integer.parseInt(multiplyMatch.group(1));
            }

            Matcher additionMatch = additionPattern.matcher(l);
            if (additionMatch.find()) {
                addition = Integer.parseInt(additionMatch.group(1));
            }

            Matcher testMatch = testPattern.matcher(l);
            if (testMatch.find()) {
                operation = Integer.parseInt(testMatch.group(1));
            }

            Matcher trueMatch = truePattern.matcher(l);
            if (trueMatch.find()) {
                tests.put(TRUE,Integer.parseInt(trueMatch.group(1)));
            }

            Matcher falseMatch = falsePattern.matcher(l);
            if (falseMatch.find()) {
                tests.put(FALSE,Integer.parseInt(falseMatch.group(1)));
            }

            if (l.startsWith("Starting items: ")) {
                String[] items = l.trim().substring("Starting items: ".length()).split(", ");
                for (String item : items) {
                    startingItem.add(Integer.parseInt(item));
                }
            }
        }
            return Monkey.builder().id(id).multi(multi).addition(addition)
                    .test(tests).operation(operation).startingItem(startingItem).build();
    }


    public static List<Monkey> monkeys (List<List<String>> lists){

        return lists.stream().map(Service::createMonkeyFromInput).collect(Collectors.toList());

    }
}
