package com.david.design_patterns.specification;

import java.util.Arrays;
import java.util.List;

public class WordBannedSpecification implements Specification<String> {
    private final List<String> bannedWords = List.of(
            "Frontend", "Backend", "Fullstack"
    );

    @Override
    public boolean isSatisfiedBy(String candidate) {
        return Arrays.stream(candidate.split(" "))
                .anyMatch(bannedWords::contains);
    }
}
