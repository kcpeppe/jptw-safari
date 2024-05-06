package com.kodewerk.profile;

import java.util.*;
import java.io.*;

public class CheckIntegerTestHarness {
    public static void main(String[] args) throws IOException {
        testDataset("dataset1.dat");
        testDataset("dataset2.dat");
        testDataset("dataset3.dat");
    }

    public static void testDataset(String dataset) throws IOException {
        long starttime = System.currentTimeMillis();
        DataInputStream rdr = new DataInputStream(new FileInputStream(dataset));
        int truecount = 0;
        ArrayList<String> dataSet = new ArrayList<>();
        String s;
        try {
            while ((s = rdr.readUTF()) != null) {
                if (checkInteger(s))
                    truecount++;
            }
        } catch (EOFException e) {}
        rdr.close();

        System.out.println(truecount + " (count); time " + (System.currentTimeMillis() - starttime));
    }

    public static boolean checkInteger(String testInteger) {
        try {
            Integer theInteger = Integer.parseInt(testInteger);//fails if not  a number
            return
                    (theInteger.toString() != "") && //not empty
                    (theInteger.intValue() > 10) && //greater than ten
                    ((theInteger.intValue() >= 2) &&
                    (theInteger.intValue() <= 100000)) && //2>=X<=100000
                    (theInteger.toString().charAt(0) == '3'); //first digit is 3
        } catch (NumberFormatException err) {
            return false;
        }
    }

}
