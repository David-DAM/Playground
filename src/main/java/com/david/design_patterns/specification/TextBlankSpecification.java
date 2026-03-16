package com.david.design_patterns.specification;

public class TextBlankSpecification implements Specification<String> {
    @Override
    public boolean isSatisfiedBy(String candidate) {
        return candidate.isBlank();
    }
}
