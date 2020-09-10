package net.vorps.updater.network;

import lombok.Getter;
import net.vorps.updater.utils.DataType;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Project Updater Created by Vorps on 03/09/2016 at 18:49.
 */
public class NetWork implements Runnable{

    private @Getter Socket connexion;
    private DataType dataType;
    private boolean state;

    public NetWork(String host, int port) throws IOException{
        this.connexion = new Socket(host, port);
    }

    public boolean sendData(DataType dataType){
        this.dataType = dataType;
        new Thread(this).start();
        return state;
    }

    @Override
    public void run(){
        try {
            PrintWriter writer = new PrintWriter(this.connexion.getOutputStream(), true);
            writer.write(this.dataType.toString());
            writer.flush();
            this.state = net.vorps.updater.utils.NetWork.read(new BufferedInputStream(this.connexion.getInputStream())) != null;
            writer.close();
        } catch (IOException e){
            this.state = false;
        }
    }
}