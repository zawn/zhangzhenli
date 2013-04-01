package com.mycompany.txthandler;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] d = new int[100];
        String[] s;
        for (int j = 1; j <= 100; j++) {
            System.out.print(1);
            d[j-1]=1 ;
        }
        System.out.println();
        for (int i = 2; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (j % i == 1) {
                    System.out.print(1);
                    d[j-1]+=1 ;
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
        for (int j = 1; j <= 100; j++) {
            System.out.print(d[j-1]);
            System.out.print(" ");
        }
         System.out.println(" ");
         int sn=0;
        for (int j = 1; j <= 100; j++) {
            System.out.print(d[j-1]%2);
            sn+=d[j-1]%2;
            System.out.print(" ");
        }
         System.out.println();
         System.out.println(sn);
    }
}
