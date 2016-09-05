package me.vorps.updater;

import me.vorps.updater.action.Action;
import me.vorps.updater.utils.Serialize;

import java.io.IOException;
import java.util.UUID;

/**
 * Project Updater Created by Vorps on 05/09/2016 at 12:59.
 */
public class Main {

    public static void main(String[] args) {
        try {
            //Serialize.serializable(new UpdateFile("localhost", "1.0", Action.NULL, new UUID(10L, 10L), 80, true), Updater.getPath() + "/updateFileTmp.ser");
            //Serialize.serializable(new UpdateFile("update.vorps.net", 80 ,"1.0", Action.STOP, UUID.fromString("cf7d57b6-5a8c-4895-aacd-22af7c924346"), false, 22, "update", "i9?fS[+F=3Ry68Cc_E3e", "/home/update/SnoWar/"), Updater.getPath() + "/updateFile.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Updater(null);
        try {
            //NetWork netWork = new NetWork("127.0.0.1" , 80);
            //netWork.sendData(DataType.UPDATE);
        }  catch (Exception e){
            e.printStackTrace();
        }
    }
}
