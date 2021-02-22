package com.company;

import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args)throws FileNotFoundException, NullPointerException {

        Scanner scan = new Scanner(System.in);
        LinkedList<Child> childs = readChildFromFile();
        ArrayList<Child> waitlist = new ArrayList<>();
        ArrayList<Employee> employees = readEmployeeFromFile();
        LinkedList<Parent> parents = readParentFromFile(childs);

        mainMenu(scan, childs, waitlist, employees, parents);


    }

    // Metode der indeholder vores main menu
    public static void mainMenu(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist, ArrayList<Employee> employees, LinkedList<Parent> parents) throws FileNotFoundException {
        int answer = 1;

        while (answer != 0) {
            System.out.println("Welcome to Roskilde frie børnehave administrative system\n" +
                    "\nType 1 for child menu." +
                    "\nType 2 for parent menu" +
                    "\nType 3 for coworker menu." +
                    "\nType 4 to registre a child to the waiting list." +
                    "\nType 5 to print lists." +
                    "\nType 0 to terminate program.");
            answer = scan.nextInt();

            switch (answer) {
                case 1:
                    childrensMenu();
                    int canswer = scan.nextInt();

                    while(canswer != 0){
                        if(canswer==1){
                            System.out.println("You have chosen to register a child");
                            System.out.println("-----------------------------------");
                            RegisterChild(scan, childs);


                        }if(canswer==2){
                            Child c1 = childs.get(0);
                            System.out.println("You have chosen to edit a child");
                            System.out.println("-------------------------------");
                            findChildToEdit(scan, childs);


                        }if(canswer==3) {
                            System.out.println("You have chose to remove a child from the list");
                            System.out.println("-------------------------------------");
                            deleteChild(scan, childs);


                        } else{
                            //skal der være noget her?
                        }
                        childrensMenu();
                        canswer = scan.nextInt();
                    }
                    break;
                case 2:
                    System.out.println("****** Parent Menu ******");
                    System.out.println();
                    System.out.println("Type 1 to register parent.");
                    System.out.println("Type 2 to edit a parents information.");
                    System.out.println("Type 0 to return to ");
                    int panswer = scan.nextInt();

                    while(panswer != 0) {
                        if(panswer==1){
                            System.out.println("You have chosen to register parent.");
                            System.out.println("-----------------------------------");
                            //parents.add(RegisterParent(scan, childs));
                            parents.add(RegisterParent(scan, childs));

                        }if(panswer==2) {
                            System.out.println("You have chosen to edit a parent.");
                            System.out.println("---------------------------------");
                            editParent(scan, parents, childs);

                        /*}else{
                            System.out.println("You typed something wrong - try again.");
                            panswer = scan.nextInt();
                        }*/
                        }
                        System.out.println("***** Parent Menu *****");
                        System.out.println();
                        System.out.println("Type 1 to register parent.");
                        System.out.println("Type 2 to edit a parents information.");
                        System.out.println("Type 0 to return to ");
                        panswer = scan.nextInt();
                    }
                    break;

                case 3:
                    System.out.println("******* Employee Menu *******");
                    System.out.println();
                    System.out.println("Type 1 to add a new employee");
                    System.out.println("Type 2 to edit an employee");
                    System.out.println("Type 3 to delete an employee");
                    System.out.println("Type 0 to return to main menu.");
                    int eanswer = scan.nextInt();

                    switch (eanswer) {
                        case 1:
                            System.out.println("You have chosen to add a new employee");
                            System.out.println("-------------------------------------");
                            RegisterEmployee(scan, employees);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit an employee");
                            System.out.println("-----------------------------------");
                            Employee e1 = employees.get(0);
                            editEmployee(scan, employees);
                            break;
                        case 3:
                            System.out.println("You have chosen to remove an employee");
                            System.out.println("-------------------------------------");
                            deleteEmployee(scan, employees);
                        case 0:
                            break;
                        default:
                            System.out.println("Error, returning to main menu");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("You have chosen to add a child to the waiting list." +
                            "\n----------------------------------------------------\n");
                    waitlist.add(RegisterChild(scan, childs));
                    break;
                case 5:
                    System.out.println("You have chosen to print lists");
                    System.out.println("--------------------------------");
                    printList(scan, childs, waitlist, employees, parents);
                case 0:
                    writeToFileChild(childs);
                    writeToFileEmployee(employees);
                    writeToFileParent(parents);
                    writeToFileWaitList(waitlist);
                    break;
                default:
                    System.out.println("Fejlmeddelelse 244 - Error - returning to main menu");
                    break;
            }
        }


    }

    // Metoder der registrere et nyt Child object, og gemmer det på vores linkedList
    public static Child RegisterChild(Scanner scan, LinkedList<Child> childs) {
        System.out.println("Enter first name of the child");
        String fname = scan.next();
        System.out.println("Enter the last name of the child");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter age of child");
        int age = scan.nextInt();
        System.out.println("Enter personal number - xxxxxx-xxxx:");
        String personalnumber = scan.next();

        Child kid = new Child(name, age, personalnumber);
        childs.add(kid);

        System.out.println(kid + "\nIs now registred");
        return kid;
    }

    // Metode der registrere et nyt Parent object, og gemmer det på vores LinkedList sammen med childs
    public static Parent RegisterParent(Scanner scan, LinkedList<Child> childs) {

        System.out.println("Enter the first name of the parent: ");
        String fname = scan.next();
        System.out.println("Enter the last name of the parent: ");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter phone number: ");
        int phoNumber = scan.nextInt();
        System.out.println("Enter personal number - xxxxxx-xxxx: ");
        String personalnumber = scan.next();
        System.out.println("Enter the child personal number with xxxxxx-xxxx: ");
        String childPNumber = scan.next();
        //Hvis det her ikke virker er det muligvis pga getName er både fornavn og efternavn
        Parent pa = new Parent (name, phoNumber, personalnumber);
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).getCPR();
            if (childs.get(i).getCPR().equals(childPNumber)) {
                pa.setKid(childs.get(i));
                System.out.println(pa + "was created");
                return pa;
            }
        }
        if(pa.getKid() == null){
            System.out.println("There wasn't found a child with that personal number.\nTry again.");
        }
        System.out.println(pa + "was created");
        return pa;
    }

    // Metode der registrere et nyt Employee object, og gemmer det på vores Employee ArrayList
    public static void RegisterEmployee(Scanner scan, ArrayList<Employee> employees) {
        System.out.println("Enter the first name of employee");
        String fname = scan.next();
        System.out.println("Enter the last name of employee");
        String lname = scan.next();
        String name = fname + " " + lname;
        System.out.println("Enter personal number - xxxxxx-xxxx: ");
        String personalnumber = scan.next();
        System.out.println("Enter phone number: ");
        int number = scan.nextInt();

        Employee e = new Employee(name, number, personalnumber);

        employees.add(e);
    }

    //public static createWorkShifts (Scanner scan, ArrayList<Employee> employees) {

    // }

    // Metode der skriver vores Child objecter ud i en txt fil
    public static void writeToFileChild(LinkedList<Child> childs) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("ChildsList.txt"));

        for (int i = 0; i < childs.size(); i++) {
            write.println(childs.get(i).toStringToFile());
        }

        write.close();

    }

    //Metode der gemmer vores parentsliste som et txt dokument
    public static void writeToFileParent(LinkedList<Parent> parents) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("ParentList.txt"));

        for (int i = 0; i < parents.size(); i++) {
            write.println(parents.get(i).toStringToFile());
        }
        write.close();

    }

    // Metode der skriver vores venteliste ud i en txt fil
    public static void writeToFileWaitList(ArrayList<Child> waitlist) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("WaitList.txt"));

        for (int i = 0; i < waitlist.size(); i++) {
            write.println(waitlist.get(i).toStringToFile());
        }

        write.close();

    }

    // Metode der skriver vores Employee objecter ud i en txt fil
    public static void writeToFileEmployee(ArrayList<Employee> employees) throws FileNotFoundException {
        PrintStream write = new PrintStream(new File("EmployeeList.txt"));

        for (int i = 0; i < employees.size(); i++) {
            write.println(employees.get(i).toStringToFile());
        }

        write.close();

    }

    //Metode der læser vores Child objecter fra en fil, og tilføjer objecterne til vores LinkedList
    public static LinkedList<Child> readChildFromFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("ChildsList.txt"));
        LinkedList<Child> childs = new LinkedList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);
            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int age = load.nextInt();
            String CPR = load.next();

            Child c = new Child(name, age, CPR);

            childs.add(c);

        }
        return childs;
    }


    public static LinkedList<Parent> readParentFromFile(LinkedList<Child> childs) throws FileNotFoundException {
        LinkedList<Parent> parents = new LinkedList<>();
        Scanner scan = new Scanner(new File("ParentList.txt"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            String CPR = load.next();
            int number = load.nextInt();
            String CPRkid = load.next();

            for(int i = 0; i < childs.size(); i++){
                childs.get(i).getCPR();
                if(CPRkid.equals(childs.get(i).getCPR())){
                    Parent p = new Parent(name, number, CPR);
                    p.setKid(childs.get(i));
                    parents.add(p);
                    break;
                }if(!CPRkid.equals(childs.get(i).getCPR())){
                    Parent p = new Parent(name, number, CPR);
                    p.setKid(null);
                    parents.add(p);
                    break;
                }
            }

        }
        return parents;
    }

    /* public static void readWaitListFromFile(Scanner scan, ArrayList<Child> waitlist) throws FileNotFoundException {
        Scanner load = new Scanner(new File("WaitList.txt"));
        while (scan.hasNextLine()) {

            String name = load.next();
            int age = load.nextInt();
            String CPR = load.next();

            Child c = new Child(name, age, CPR);

            childs.add(c);

        }
    }*/

    public static ArrayList<Employee> readEmployeeFromFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("EmployeeList.txt"));
        ArrayList<Employee> employees = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner load = new Scanner(line);

            String fname = load.next();
            String lname = load.next();
            String name = fname + " " + lname;
            int number = load.nextInt();
            String CPR = load.next();


            Employee e = new Employee(name, number, CPR);

            employees.add(e);
        }
        return employees;
    }

    //Metode der udskriver vores Child list
    public static void printList(Scanner scan, LinkedList<Child> childs, ArrayList<Child> waitlist,
                                 ArrayList<Employee> employees, LinkedList<Parent> parent) {
        printlistmenu();
        String answer = scan.next();
        while (!answer.equals("0")) {
            if (answer.equals("1")) {
                for (int i = 0; i < childs.size(); i++) {
                    Collections.sort(childs);
                    System.out.println(childs.get(i));
                }
            }if (answer.equals("2")) {
                for (int i = 0; i < waitlist.size(); i++) {
                    System.out.println(waitlist.get(i));
                }
            }if (answer.equals("3")) {
                TreeMap<String, Integer> phoneList = new TreeMap<>();
                for(Parent par: parent){
                    phoneList.put(par.getName(), par.getNumber());
                }
                for(String par: phoneList.keySet()){
                    System.out.println("Parent: " + par + "\nPhone number: " + phoneList.get(par) + "\n");
                }
            }if (answer.equals("4")) {
                for(int i = 0; i < parent.size(); i++){
                    System.out.println(parent.get(i));
                }
            }
            if(answer.equals("5")){
                for (int i = 0; i < employees.size(); i++) {
                    Collections.sort(employees);
                    System.out.println(employees.get(i));
                }
            }
            printlistmenu();
            answer = scan.next();
        }
    }



    // Metode der udskriver vores Venteliste
   /* public static void printWaitList(ArrayList<String> waitlist) {
        for (int i = 0; i < waitlist.size(); i++) {
            System.out.println(waitlist.get(i));
        }
    }*/

    // Metode der udskriver vores Employee liste
   /* public static void printEmployeeList(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }
    }*/

    // Metode der finder et Child object for at redigere
    //skal flettes sammen med editChild
    public static void findChildToEdit(Scanner scan, LinkedList<Child> childs) {
        System.out.print("Enter the first name of the child you wish to edit: ");
        String name = scan.next();

        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).getName().equals(name)) {
                System.out.println("\n" + childs.get(i) + "\n");
                editChild(scan, childs.get(i));
            }
        }
    }

    // Metode der redigere et child object
    public static void editChild(Scanner scan, Child c1) {
        int svar = 1;
        while (svar != 0) {
            System.out.println("Type 1 to edit name.\nType 2 to edit age.\nType 3 to edit CPR\nType 0 to return\n");
            svar = scan.nextInt();

            switch (svar) {
                case 1:
                    System.out.println("You have chosen to edit name\n----------------------------\nEnter new name: ");
                    String newName = scan.next();
                    c1.setName(newName);
                    break;
                case 2:
                    System.out.println("You have chosen to edit age\n----------------------------\nEnter new age: ");
                    int newAge = scan.nextInt();
                    c1.setAge(newAge);
                    break;
                case 3:
                    System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                    String newCPR = scan.next();
                    c1.setCPR(newCPR);
                    break;
                case 0:
                    System.out.println("Returning to main menu.");
                    break;
            }
        }
    }

    //metode til at fjerne et barn
    public static void deleteChild(Scanner scan, LinkedList<Child> childs){
        for(int i = 0; i < childs.size(); i++){
            System.out.println(childs.get(i));
        }
        System.out.println("Enter personal number of the child you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < childs.size(); i++){
            if(childs.get(i).getCPR() == personalNumber){
                childs.remove(i);
                foundCPR = true;
                break;
            }
        }
        if(!foundCPR){
            System.out.println("This personal number does not exists");
        }
        System.out.println(childs);
    }

    //metode til at fjerne en ansat
    public static void deleteEmployee(Scanner scan,ArrayList<Employee> employees){
        for(int i = 0; i < employees.size(); i++){
            System.out.println(employees.get(i));
        }
        System.out.println("Enter personal number of the employee you want to remove from the list");
        boolean foundCPR = false;
        String personalNumber = scan.next();
        for (int i = 0; i < employees.size(); i++){
            if(employees.get(i).getCPR() == personalNumber){
                employees.remove(i);
                foundCPR = true;
                break;
            }
        }
    }
    public static void printPhoneList(LinkedList<Parent> parent){
        TreeMap<String, Integer> phoneList = new TreeMap<>();
        for(Parent par: parent){
            phoneList.put(par.getName(), par.getNumber());
        }
        System.out.println(phoneList);
    }

    public static void childrensMenu(){
        System.out.printf("%21s %n", "** Child Menu **");
        System.out.println("\nType 1 to register a new child.\n" +
                "Type 2 to edit a childs information.\nType 3 to remove child" +
                "\nType 0 to return to main menu.");
    }

    public static void printlistmenu(){
        System.out.println("Which list do you want see?\n---------------------------\n" +
                "1. Childrens list.\n2. The waiting list.\n3. The parent phone list.\n4. The parent list." +
                "\n5. The employee list\n0. Exit to main menu.");
    }

   /* public static void findParentToEdit(Scanner scan, LinkedList<Parent> parents, LinkedList<Child> childs) {
        System.out.print("Enter the personal number of the parent you wish to edit: ");
        String CPR = scan.next();

        for (int i = 0; i < parents.size(); i++) {
            if (parents.get(i).getCPR().equals(CPR)) {
                System.out.println("\n" + parents.get(i) + "\n");
                editParent(scan, parents.get(i), childs);
            }
        }
    }*/

    public static void editParent(Scanner scan, LinkedList<Parent> parent, LinkedList<Child> childs) {
        System.out.print("Enter the personal number of the parent you wish to edit: ");
        String CPR = scan.next();

        for (int i = 0; i < parent.size(); i++) {
            if (parent.get(i).getCPR().equals(CPR)) {
                System.out.println("\n" + parent.get(i) + "\n");

                int svar = 1;
                while (svar != 0) {
                    System.out.println("Type 1 to edit name.\nType 2 to edit age.\nType 3 to edit CPR\nType 4 to edit child" +
                            "\nType 0 to return");
                    svar = scan.nextInt();

                    switch (svar) {
                        case 1:
                            System.out.println("You have chosen to edit name\n----------------------------\nEnter new first name: ");
                            String newFName = scan.next();
                            System.out.println("Enter new last name: ");
                            String newLName = scan.next();
                            String newName = newFName + " " + newLName;
                            parent.get(i).setName(newName);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit phone number\n----------------------------\nEnter new phone number: ");
                            int newNumber = scan.nextInt();
                            parent.get(i).setNumber(newNumber);
                            break;
                        case 3:
                            System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                            String newCPR = scan.next();
                            parent.get(i).setCPR(newCPR);
                            break;
                        case 4:
                            System.out.println("You have chosen edit child CPR\n---------------------------\nEnter new CPR: ");
                            CPR = scan.next();
                            for (int j = 0; j < childs.size(); j++) {
                                if (CPR.equals(childs.get(j).getCPR())) {
                                    parent.get(j).setKid(childs.get(j));
                                }
                            }
                            break;
                        case 0:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
            }
        }
    }
     /*public static void findEmployeeToEdit(Scanner scan, ArrayList<Employee> employees) {
        System.out.print("Enter the first name of the child you wish to edit: ");
        String name = scan.next();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                System.out.println("\n" + employees.get(i) + "\n");
                editEmployee(scan, employees.get(i));
            }
        }
    }*/

    public static void editEmployee(Scanner scan, ArrayList<Employee> employees) {
        System.out.print("Enter the first name of the employee you wish to edit: ");
        String fname = scan.next();
        System.out.print("Enter the last name of the employee you wish to edit: ");
        String lname = scan.next();
        String name = fname + " " + lname;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(name)) {
                System.out.println("\n" + employees.get(i) + "\n");
                int svar = 1;
                while (svar != 0) {
                    System.out.println("Type 1 to edit name.\nType 2 to edit CPR\nType 3 to edit phone number\nType 0 to return\n");
                    svar = scan.nextInt();

                    switch (svar) {
                        case 1:
                            System.out.println("You have chosen to edit name\n----------------------------\nEnter new name: ");
                            String newName = scan.next();
                            employees.get(i).setName(newName);
                            break;
                        case 2:
                            System.out.println("You have chosen to edit CPR\n---------------------------\nEnter new CPR: ");
                            String newCPR = scan.next();
                            employees.get(i).setCPR(newCPR);
                            break;
                        case 3:
                            System.out.println("You have chosen to edit phone number\n---------------------------\nEnter new phone number: ");
                            int newNumber = scan.nextInt();
                            employees.get(i).setNumber(newNumber);
                            break;
                        case 0:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
            }
        }
    }
}