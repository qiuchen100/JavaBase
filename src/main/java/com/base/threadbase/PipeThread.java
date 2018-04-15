package com.base.threadbase;

import java.io.*;

class MyWriter extends Thread {
    private PipedOutputStream outStream;
    private String[] messages = {"abc", "def", "ghi"};

    public MyWriter(PipedOutputStream o) {
        this.outStream = o;
    }

    @Override
    public void run() {
        PrintStream p = new PrintStream(outStream);
        for (String message : messages) {
            p.println(message);
            //System.out.println(message);
            p.flush();
        }
        p.close();
        //p = null;
    }
}
class MyReader extends Thread{
    private PipedInputStream inStream;
    public MyReader(PipedInputStream i){
            this.inStream = i;
    }
    public void run() {
        String line;
        boolean reading = true;
        InputStreamReader d = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(d);
            while (reading && br != null) {
                try {
                    //System.out.println("xxx");
                    line = br.readLine();
                    if (line != null) {
                        System.out.println("Read: " + line);
                    } else {
                        reading = false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                br.close();
                d.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class PipeThread {
    public static void main(String[] args) {
        PipedInputStream in;
        PipedOutputStream out;
        try {
            in = new PipedInputStream();
            out = new PipedOutputStream(in);
            new MyWriter(out).start();
            new MyReader(in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
