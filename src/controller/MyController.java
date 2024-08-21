package controller;

import handlers.CSVFileHandler;
import handlers.JsonFileHandler;
import handlers.MyCollection;
import handlers.XMLFileHandler;

public class MyController {
    public MyController(){
        //These are runnable for reading from file and writing in array
        MyCollection myCollection = new MyCollection();
        Runnable r1 = ()->{
            CSVFileHandler csvFileHandler = new CSVFileHandler();
            csvFileHandler.read(myCollection);
        };
        Runnable r2 = ()->{
            JsonFileHandler jsonFileHandler = new JsonFileHandler();
            jsonFileHandler.read(myCollection);
        };
        Runnable r3 = ()->{
            XMLFileHandler xmlFileHandler = new XMLFileHandler();
            xmlFileHandler.read(myCollection);
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        try{
            t1.join();
        } catch (InterruptedException e){System.out.println(e.getMessage());}

        t2.start();
        try{
            t2.join();
        } catch (InterruptedException e){System.out.println(e.getMessage());}

        t3.start();
        try{
            t3.join();
        } catch (InterruptedException e){System.out.println(e.getMessage());}

        ////These are runnable for writing in file and reading from array
        Runnable r4 = ()->{
            CSVFileHandler csvFileHandler = new CSVFileHandler();
            csvFileHandler.write(myCollection);
        };
        Runnable r5 = ()->{};
        Runnable r6 = ()->{};

        Thread t4 = new Thread(r4);

        t4.start();
        try{
            t4.join();
        } catch (InterruptedException e){System.out.println(e.getMessage());}

        int writeCounter = myCollection.writeCounter;
        System.out.println("write code:"+writeCounter);
        int read = myCollection.readCounter;
        System.out.println("write code:"+read);
    }
}
