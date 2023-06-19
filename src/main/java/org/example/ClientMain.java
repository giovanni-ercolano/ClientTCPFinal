package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 1234;
        Socket echoSocket = null;
        try {
            echoSocket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("cannot reach server "+e);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("cannot allocate bufferedreader");
        }
        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String userInput = "";
        while (true) {
            try {
                if (!((userInput = stdIn.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println(userInput);
            out.flush();
            try {
                System.out.println("echo: " + in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}