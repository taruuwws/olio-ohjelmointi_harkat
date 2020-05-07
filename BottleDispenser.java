package com.example.h8;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class BottleDispenser {

    public static ArrayList<Bottle> bottle_list = new ArrayList<Bottle>(); {
        bottle_list.add(new Bottle("Pepsi Max", 0.5, 1.80, 5));
        bottle_list.add(new Bottle("Pepsi Max", 1.5, 2.20, 5));
        bottle_list.add(new Bottle("Coca-Cola Zero", 0.5, 2.00, 5));
        bottle_list.add(new Bottle("Coca-Cola Zero", 1.5, 2.50, 5));
        bottle_list.add(new Bottle("Fanta Zero", 0.5, 1.95, 5));
    }

    private static BottleDispenser bd = new BottleDispenser();

    private BottleDispenser() {
        bottles = 25;
        money = 0;
    }

    public static BottleDispenser getInstance() {
        return bd;
    }


    public int bottles;
    private double money;
    int bottleCount = 0;
    String lastBuy = "";
    double lastPrice = 0;



    public void addMoney(TextView eventWindow, TextView moneyWindow, double money3) {

        money += money3;
        String money2 = String.format("%.2f", money);
        if (money == 0) {
            eventWindow.setText("Add money first!");
        } else {
            eventWindow.setText("Klink! Added more money!");
            moneyWindow.setText("Current money: " + money2 + "€");
        }
    }

    public void buyBottle(TextView eventWindow, TextView moneyWindow, int loc) {
        bottleCount = bottle_list.get(loc).getCount();
        if (bottles <= 0) {
            eventWindow.setText("Bottle Dispenser is empty!");
        } else if ((money < (double)  bottle_list.get(loc).getPrice())) {
            eventWindow.setText("Add more money!");
        } else if (bottleCount <= 0) {
            eventWindow.setText("No more bottles. Choose another bottle!");
        } else {

           bottles -= 1;
           money -= bottle_list.get(loc).getPrice();
           bottle_list.get(loc).setCount(bottleCount-1);

           String money2 = String.format("%.2f", money);
           eventWindow.setText("KACHUNK! " + bottle_list.get(loc).getName() + " came out of the dispenser!");
           moneyWindow.setText("Current money: " + money2 + "€");
           lastBuy = bottle_list.get(loc).getName();
           lastPrice = bottle_list.get(loc).getPrice();

        }
    }

    public void listBottles(TextView bottleWindow) {
        bottleWindow.setText("");
        for (int i = 0; i < 5; i++) {
            bottleWindow.append((i+1) + ". Name: " + bottle_list.get(i).getName() + "\n");
            bottleWindow.append("      Size: " + bottle_list.get(i).getSize() + "      Price: " + bottle_list.get(i).getPrice()+ "\n\n");
        }
    }

    public void returnMoney(TextView eventWindow, TextView moneyWindow) {
        String money2 = String.format("%.2f", money);
        eventWindow.setText("Klink klink. Money came out! You got " + money2 + "€ back");
        moneyWindow.setText(("Current money: 0.00€"));
        money = 0;
    }

    public void writeReceipt(TextView eventWindow, Context context) {
        try {
            System.out.println(context.getFilesDir());
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("BottleDispenserReceipt.txt", Context.MODE_PRIVATE));
            String money2 = String.format("%.2f", lastPrice);
            ows.write("This is a receipt from your purchase.\n\n" + lastBuy + ", " + money2 + " €.\n");
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }

        eventWindow.setText("Receipt printed. See you soon!");
    }
}
