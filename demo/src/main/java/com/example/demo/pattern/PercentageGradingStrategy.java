package com.example.demo.pattern;

public class PercentageGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Check if the score is within the valid range
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        // Convert the score to a percentage
        return score / 100.0;

    }
}
