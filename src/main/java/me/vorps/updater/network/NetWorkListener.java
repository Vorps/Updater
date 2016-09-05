package me.vorps.updater.network;

import org.xbill.DNS.Address;
import org.xbill.DNS.DNSSEC;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Project Updater Created by Vorps on 04/09/2016 at 12:29.
 */

public class NetWorkListener {

    private ServerSocket server;
    private boolean isRunning;

    public NetWorkListener(String host, int port) throws IOException{
        this.isRunning = true;
        this.server = new ServerSocket(port, 100, Address.getByName(host));
    }

    public void open(){
        new Thread(new Runnable(){
            public void run(){
                try {
                    while(isRunning) new Thread(new ClientProcessor(server.accept())).start();
                    server.close();
                } catch (IOException e) {
                    //
                }
            }
        }).start();
    }

    public void close(){
        this.isRunning = false;
    }
}