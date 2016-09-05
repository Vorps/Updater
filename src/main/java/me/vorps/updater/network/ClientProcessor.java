package me.vorps.updater.network;

import me.vorps.updater.Updater;
import me.vorps.updater.action.ActionStop;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Project Updater Created by Vorps on 04/09/2016 at 17:28.
 */
public class ClientProcessor implements Runnable{

    private Socket sock;

    public ClientProcessor(Socket Sock){
        this.sock = Sock;
    }

    public void run(){
        boolean closeConnexion = false;
        while(!this.sock.isClosed()){
            try {
                PrintWriter writer = new PrintWriter(this.sock.getOutputStream());
                String toSend = "";
                switch(me.vorps.updater.utils.NetWork.read(new BufferedInputStream(this.sock.getInputStream()))){
                    case "CLOSE":
                        toSend = "ACK";
                        closeConnexion = true;
                        break;
                    case "STOP":
                        toSend = "ACK";
                        new ActionStop().run(Updater.getInstance().getPlugin());
                        break;
                    default :
                        break;
                }
                writer.write(toSend);
                writer.flush();
                if(closeConnexion) this.sock.close();
            } catch (IOException e) {
                Updater.getInstance().error();
            }
        }
    }

}