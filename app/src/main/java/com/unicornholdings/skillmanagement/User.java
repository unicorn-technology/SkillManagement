package com.unicornholdings.skillmanagement;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private int matrixScore ;

    public ArrayList<Courses> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Courses> courses) {
        this.courses = courses;
    }

    private ArrayList<Courses> courses;
    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    private String Course = "JAVA";
    public User(String name, int matrixScore) {
        this.name = name;
        this.matrixScore = matrixScore;
        courses = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatrixScore() {
        return matrixScore;
    }

    public void setMatrixScore(int matrixScore) {
        this.matrixScore = matrixScore;
    }


    public void addCourses(Courses course) {
        this.courses.add(course);
    }
}
