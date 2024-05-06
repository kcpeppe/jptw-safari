package com.kodewerk.profile;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GenerateData {

    public static void main(String[] args) throws IOException {
	int numberOfDataPoints;
	if ( args.length == 0)
	    numberOfDataPoints = 10000000;
	else 
            numberOfDataPoints = Integer.parseInt( args[ 0]);
	System.out.println( numberOfDataPoints + " data points generated.");
        generateDataset1( numberOfDataPoints);
        generateDataset2( numberOfDataPoints);
        generateDataset3( numberOfDataPoints);
    }

    //dataset 1, all true, integers 2 to 5 characters long starting with '3'
    public static void generateDataset1( int numberOfDataPoints)
            throws IOException {
        DataOutputStream wrtr = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("dataset1.dat")));
        Random rand = new Random(12346);
        for (int i = 0; i < numberOfDataPoints; i++) {
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
        }
        wrtr.close();
    }

    //dataset 2, about half true
    public static void generateDataset2( int numberOfDataPoints)            throws IOException {
        DataOutputStream wrtr = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("dataset2.dat")));
        Random rand = new Random(12347);
        for (int j = 0; j < numberOfDataPoints / 10; j++) {
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("-" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            int i = Math.abs(rand.nextInt(100000));
            while (Integer.toString(i).charAt(0) == '3') {
                i = Math.abs(rand.nextInt(100000));
            }
            wrtr.writeUTF("" + i);
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("-" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            i = Math.abs(rand.nextInt(100000));
            while (Integer.toString(i).charAt(0) == '3') {
                i = Math.abs(rand.nextInt(100000));
            }
            wrtr.writeUTF("" + i);
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("-" + Math.abs(rand.nextInt(10000)));
        }
        wrtr.close();
    }

    //dataset 2, about 1/3 true, 1/3 false, over 1/3 not a number
    public static void generateDataset3( int numberOfDataPoints)
            throws IOException {
        DataOutputStream wrtr = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("dataset3.dat")));
        Random rand = new Random(12348);
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < numberOfDataPoints / 10; j++) {
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("-" + Math.abs(rand.nextInt(10000)));
            sb.setLength(0);
            sb.append(rand.nextInt(1000));
            sb.insert(0, 'w');
            wrtr.writeUTF(sb.toString());
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            int i = Math.abs(rand.nextInt(100000));
            while (Integer.toString(i).charAt(0) == '3') {
                i = Math.abs(rand.nextInt(100000));
            }
            wrtr.writeUTF("" + i);
            sb.setLength(0);
            sb.append(rand.nextInt(1000));
            sb.insert(1, 'q');
            wrtr.writeUTF(sb.toString());
            wrtr.writeUTF("3" + Math.abs(rand.nextInt(10000)));
            wrtr.writeUTF("-" + Math.abs(rand.nextInt(10000)));
            sb.setLength(0);
            sb.append(rand.nextInt(1000));
            sb.append('s');
            wrtr.writeUTF(sb.toString());
            wrtr.writeUTF("NOTNUM");
        }
        wrtr.close();
    }
}
