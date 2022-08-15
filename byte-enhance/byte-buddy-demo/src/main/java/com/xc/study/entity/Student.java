package com.xc.study.entity;

public class Student {

    private Person person;

    private String className;

    public Student(Person person, String className) {
        this.person = person;
        this.className = className;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String say() {
        return person.getName() + "-" + person.getAge() + "-" + className;
    }
    @Override
    public String toString() {
        return "Student{" +
                "person=" + person +
                ", className='" + className + '\'' +
                '}';
    }
}
