package com.company;

public class Parent{
    private String name;
    private int number;
    private String CPR;
    Child kid;


    public Parent(){

    }
    public Parent(String name, int number, String CPR) throws NullPointerException {
        this.name = name;
        this.number = number;
        this.CPR = CPR;
        this.kid = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public Child getKid()throws NullPointerException {
        return kid;
    }

    public void setKid(Child kid) {
        this.kid = kid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Parents name: " + name +"\nPhone number: " + number +
                "\nPersonal number: " + CPR + "\n" + kid +"\n";
    }
    public String toStringToFile()throws NullPointerException{
        try {
            return name + " " + CPR + " " + number + " " + kid.getCPR();
        }catch (NullPointerException e){

        }
        return name + " " + CPR + " " + number + " " + null;
    }
}