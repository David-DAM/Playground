package com.david.design_patterns.specification;

public class SpecificationExample {
    static void main() {
        Specification<String> textSpecification = new WordLengthSpecification()
                .and(new WordCountSpecification())
                .and(new WordBannedSpecification().not())
                .and(
                        new WordLengthSpecification()
                                .or(new TextBlankSpecification().not())
                );

        boolean satisfied = textSpecification.isSatisfiedBy("Hello world I am David");
        System.out.println(satisfied);
    }
}
