package com.kodewerk.cheapdrink;

import java.io.IOException;
import java.util.*;

public class TestHarness {

    private static final String[] FIRST_NAMES = { "Ted", "Fred", "Andy",
			"Gromot", "Wallace" };
    private static final String[] LAST_NAMES = { "Mouse", "Duck", "Pascal",
			"Kabutz", "Monster", "Dread", "Crocket" };

    private static final int NUM_PERSONS = 1000; 
    private static final int NUM_WORKERS = 10;
    private static final int NUM_ITERATIONS = 50000000;
	
    /** singleton instance */
    private static final TestHarness harness = new TestHarness();
    private final PersonStore personStore = new PersonStorage();
    private final static Object lock = new Object();

    public TestHarness() {}

    private PersonStore getPersonStore() { return personStore; }
    
    public static void main(String[] args) throws InterruptedException {
        int numWorkers = NUM_WORKERS;
        int numIterations = NUM_ITERATIONS;
        if (args.length > 0) {
            numWorkers = Integer.parseInt(args[0]);
            if (args.length > 1) {
                numIterations = Integer.parseInt(args[1]);
            }
        }
        waitForUserInput();
    
        System.out.println("Running " + numWorkers + " workers with "
				+ numIterations + " iterations.");
        List workers = new ArrayList();
        long startSetup = System.currentTimeMillis();
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(harness.getPersonStore(), numIterations);
            workers.add(worker);
        }
        long setup = (System.currentTimeMillis() - startSetup);
        System.out.println("Setup time : " + setup);
    
        long startWarmup = System.currentTimeMillis();
        Worker warmUpWorker = new Worker(harness.getPersonStore(), 100);
        warmUpWorker.go();
        warmUpWorker.waitUntilFinished();
        long warmup = (System.currentTimeMillis() - startWarmup);
        System.out.println("Warmup time : " + warmup);
        
        long startGc = System.currentTimeMillis();
        System.gc();
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
        long gc = (System.currentTimeMillis() - startGc);
        System.out.println("GC time : " + gc);

        System.out.println("processing..");
        long startProcessing = System.currentTimeMillis();
        for (Iterator it = workers.iterator(); it.hasNext();) {
            Worker worker = (Worker) it.next();
            worker.go();
        }
        for (Iterator it = workers.iterator(); it.hasNext();) {
            Worker worker = (Worker) it.next();
            worker.waitUntilFinished();
        }

        workers = new ArrayList();
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(harness.getPersonStore(), numIterations);
            workers.add(worker);
        }

        for (Iterator it = workers.iterator(); it.hasNext();) {
            Worker worker = (Worker) it.next();
            worker.go();
        }
        for (Iterator it = workers.iterator(); it.hasNext();) {
            Worker worker = (Worker) it.next();
            worker.waitUntilFinished();
        }
    
        long processing = (System.currentTimeMillis() - startProcessing);
        System.out.println("Processing time : " + processing);
        waitForUserInput();
    }

    private static void waitForUserInput() {
        // System.console().readLine("Press Enter"); // Java 6 is so great
        // next is for the poor, backward compatibility
        try {
            System.out.println("Press Enter");
            int len = 0;
            byte[] buf = new byte[4];
            while (len == 0) {
                len = System.in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class Worker implements Runnable {

        private static int count = 0;
        private final int number;
        private final int numIter;
        private final Thread thread;
        private final UniqueStrings generatedFirstNames;
        private final UniqueStrings generatedLastNames;
        private final PersonStore personStore;
        Worker(PersonStore personStore, int numIter) {
            number = count++;
            this.numIter = numIter;
            thread = new Thread((Runnable) this);
            thread.setName("Worker " + number);
            this.personStore = personStore;
            generatedFirstNames = new UniqueStrings(FIRST_NAMES, NUM_PERSONS);
            generatedLastNames = new UniqueStrings(LAST_NAMES, NUM_PERSONS);
        }

        public void run() {
            for (int i = 0; i < numIter; i++) {
		        personStore.findPersonByName(generatedFirstNames.next(),generatedLastNames.next());
            }
            System.out.println(thread.getName() + " finished");
        }

        void waitUntilFinished() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void go() {
            thread.start();
        }
    }
    
    private static class UniqueStrings {

        private int nextString = 0;
        private String[] generatedStrings;
    
        UniqueStrings(String[] baseStrings, int size) {
            generatedStrings = new String[size];
            for (int i = 0; i < size; i++) {
                generatedStrings[i] = baseStrings[i % baseStrings.length] + i;
            }
        }

        String next() {
            String result = generatedStrings[nextString++];
            nextString %= generatedStrings.length;
            return result;
        }
    }
}
