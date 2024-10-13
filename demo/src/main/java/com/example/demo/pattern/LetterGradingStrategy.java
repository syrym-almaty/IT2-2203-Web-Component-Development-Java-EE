package com.example.demo.pattern;

public class LetterGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Check if the score is within the valid range
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        // Convert the score to a letter grade
        if (score >= 90) {
            // A grade
            return 4.0;
        } else if (score >= 80) {
            // B grade
            return 3.0;
        } else if (score >= 70) {
            // C grade
            return 2.0;
        } else if (score >= 60) {
            // D grade
            return 1.0;
        } else {
            // F grade
            return 0.0;
        }
    }
}
