package com.example.learnenglishapp.model;

public class Cau {
    private int id;
    private String sentenceSort;
    private String Part1;
    private String Part2;
    private String Part3;
    private String Part4;

    public Cau(int id, String sentenceSort, String part1, String part2, String part3, String part4) {
        this.id = id;
        this.sentenceSort = sentenceSort;
        Part1 = part1;
        Part2 = part2;
        Part3 = part3;
        Part4 = part4;
    }

    public int getId() {
        return id;
    }

    public String getSentenceSort() {
        return sentenceSort;
    }

    public String getPart1() {
        return Part1;
    }

    public String getPart2() {
        return Part2;
    }

    public String getPart3() {
        return Part3;
    }

    public String getPart4() {
        return Part4;
    }
}
