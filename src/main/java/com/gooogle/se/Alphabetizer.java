package com.gooogle.se;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Alphabetizer {

    @PostMapping(value = "query/alphabetize")
    public AlphabetizedLines getAlphabetizedLines(@RequestBody String userInput) {
        List<Line> userInputLines = Utilities.parseInput(userInput);
        AlphabetizedLines alphabetizedLines = sort(userInputLines);
        Utilities.storeLogs("src/output/AlphabetizedOutput.txt", alphabetizedLines.getLines());
        return alphabetizedLines;
    }

    public AlphabetizedLines sort(CircularShiftResponse circularShiftResponse) {
        List<CircularShiftedLine> circularShiftedLines = circularShiftResponse.getCircularShiftedLines();
        List<Line> sentences = new ArrayList<>();
        for (CircularShiftedLine line: circularShiftedLines) {
            sentences.addAll(line.getShiftedLines());
        }
        return sort(sentences);
    }

    public AlphabetizedLines sort(List<Line> lines) {
        lines.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.getLine().toLowerCase().compareTo(o2.getLine().toLowerCase());
            }
        });
        return new AlphabetizedLines(lines);
    }
}
