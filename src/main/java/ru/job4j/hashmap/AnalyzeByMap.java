package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double score = 0;
        int subjectAll = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                subjectAll++;
            }
        }
        return subjectAll == 0 ? 0D : score / subjectAll;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> result = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            int subjectAll = pupil.subjects().size();
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            result.add(new Label(pupil.name(), score / subjectAll));
        }
        return result;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> scores = new HashMap<>();
        Map<String, Integer> subjectAll = new HashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                scores.put(subject.name(), scores.getOrDefault(subject.name(), 0) + subject.score());
                subjectAll.put(subject.name(), subjectAll.getOrDefault(subject.name(), 0) + 1);
            }
        }
        List<Label> result = new ArrayList<>();
        for (String subjectName : scores.keySet()) {
            double averageScore = (double) scores.get(subjectName) / subjectAll.get(subjectName);
            result.add(new Label(subjectName, averageScore));
        }
        return result;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> studentScores = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            studentScores.add(new Label(pupil.name(), score));
        }

        studentScores.sort(Comparator.naturalOrder());
        return studentScores.isEmpty() ? null : studentScores.get(studentScores.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectScores = new HashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectScores.put(subject.name(), subjectScores.getOrDefault(subject.name(), 0) + subject.score());
            }
        }
        List<Label> subjectLabels = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : subjectScores.entrySet()) {
            subjectLabels.add(new Label(entry.getKey(), entry.getValue()));
        }
        subjectLabels.sort(Comparator.naturalOrder());
        return subjectLabels.isEmpty() ? null : subjectLabels.get(subjectLabels.size() - 1);
    }
}
