package lv.javaguru.java2.bankapp.domain;

import java.util.Random;

public class Bank {
    int balance;



    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Deposit failure :(");
            return;
        }

        balance += amount;
        System.out.println("Deposit success :)");
    }


    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal failure: amount is cannot be negative :(");
            return;
        }

        if (amount > balance) {
            System.out.println("Withdrawal failure: not enough money :(");
            return;
        }

        balance -= amount; // balance = balance - amount;
        System.out.println("Withdrawal success :)");
    }


    public void printBalanceStatement() {
        System.out.println("Current Balance: " + balance);
    }

    public void BankNumber() {
        Random rand = new Random();
        String card = "BABALV";
        for (int i = 0; i < 14; i++) {
            int n = rand.nextInt(10) + 0;
            card += Integer.toString(n);
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0)
                System.out.print(" ");
            System.out.print(card.charAt(i));
        }
    }
    //String masked() {
    //        return cardNumber.substring(0, 3)
    //                + "*".repeat(8)
    //                + cardNumber.substring(cardNumber.length() - 3);
    //    }

}







