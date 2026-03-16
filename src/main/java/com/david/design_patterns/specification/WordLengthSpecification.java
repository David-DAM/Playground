package com.david.design_patterns.specification;

public class WordLengthSpecification implements Specification<String> {
    @Override
    public boolean isSatisfiedBy(String candidate) {
        return candidate.length() > 2;
    }
}
