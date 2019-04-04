package com.unicornholdings.skillmanagement;

class Courses {

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    private String courseName;
    private String courseDuration;
    private String URL;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoursePoints() {
        return coursePoints;
    }

    public void setCoursePoints(String coursePoints) {
        this.coursePoints = coursePoints;
    }

    public String getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(String courseRating) {
        this.courseRating = courseRating;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    private String courseId;
    private String type;
    private String coursePoints;
    private String courseRating;
    private Double cost;


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Courses(String courseName, String courseDuration, String URL) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.URL = URL;
    }
}
