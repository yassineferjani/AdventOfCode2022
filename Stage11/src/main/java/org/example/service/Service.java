package org.example.service;

import org.example.model.Monkey;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Service {

    private static List<List<String>> worriedMonkey(List<String> list) {
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
        Deque<Long> startingItem = new ArrayDeque<>();
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
            if (l.trim().equals("Operation: new = old * old")) {
                multi = -2;
            } else if (multiplyMatch.find()) {
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
                    startingItem.add(Long.valueOf(Integer.parseInt(item)));
                }
            }
        }
            return Monkey.builder().id(id).multi(multi).addition(addition)
                    .test(tests).operation(operation).startingItem(startingItem).inspections(0).build();
    }


      private static List<Monkey> monkeysList(List<List<String>> lists) {
        return lists.stream()
                .map(Service::createMonkeyFromInput)
                .collect(Collectors.toList());
    }
    private static List<Monkey> simulateMonkeyGame(List<String> input, int rounds, boolean test) {
        List<List<String>> splitInput = worriedMonkey(input);
        List<Monkey> monkeysList = monkeysList(splitInput);
        long ppm = monkeysList.stream().mapToLong(Monkey::getOperation).reduce((left, right) -> left*right).getAsLong();
        for (int i =0;i<rounds;i++){
            for (Monkey monkey : monkeysList){
                if (test)
                    monkey.round(monkeysList,ppm);
                else
                    monkey.round(monkeysList,null);
            }
        }
        return monkeysList;
    }
    private static List<Integer> inspectionsMonkeys(List<String> input, int rounds, boolean ppm){
        List<Monkey> monkeys = simulateMonkeyGame(input,rounds,ppm);
        monkeys.forEach(System.out::println);
       return monkeys.stream().map(Monkey::getInspections).collect(Collectors.toList());
    }
    public static long result1(List<String> input, int rounds, boolean ppm){
        List<Integer> inspections = inspectionsMonkeys(input,rounds,ppm);
        inspections.sort((o1, o2) -> o2-o1);
        return (long) inspections.get(0) *inspections.get(1);
    }


}
