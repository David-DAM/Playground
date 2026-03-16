package com.david.design_patterns.specification;

public class WordCountSpecification implements Specification<String> {
    @Override
    public boolean isSatisfiedBy(String candidate) {
        return candidate.split(" ").length > 2;
    }
}
