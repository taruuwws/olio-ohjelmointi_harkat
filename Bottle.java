package com.example.h8;

public class Bottle {

        private String name;
        private String manufacturer;
        private double total_energy;
        private double size;
        private double price;
        private Integer count;


        public Bottle(String n, double s, double p, Integer i) {
            name = n;
            size = (double) s;
            price = (double) p;
            count = (Integer) i;
        }

        public String getName() {
            return name;
        }

        public double getSize() {
            return size;
        }

        public double getPrice() {
            return price;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String toString() {
            String price2 = String.format("%.2f", price);
            String choice = name + " " + size + " l";
            return choice;
        }

}
