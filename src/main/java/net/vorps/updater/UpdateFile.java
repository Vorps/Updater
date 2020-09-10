package net.vorps.updater;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.vorps.updater.action.Action;
import net.vorps.updater.action.Action;

import java.io.Serializable;
import java.util.UUID;

/**
 * Project Updater Created by Vorps on 01/09/2016 at 23:43.
 */
@AllArgsConstructor
public class UpdateFile implements Serializable{

    private @Getter String host;
    private @Getter int port;
    private @Getter String version;
    private @Getter
    Action action;
    private @Getter UUID uuid;
    private @Getter boolean listener;
    private @Getter int portSftp;
    private @Getter String userSftp;
    private @Getter String passSftp;
    private @Getter String path;

    private static final long serialVersionUID = -4072024704477723946L;

    @Override
    public boolean equals(Object obj){
        boolean state = false;
        if(obj instanceof UpdateFile){
            UpdateFile updateFile = (UpdateFile) obj;
            state = this.host.equals(updateFile.host) &&
                    this.port == updateFile.port &&
                    this.version.equals(updateFile.version) &&
                    this.action == updateFile.action &&
                    this.uuid.equals(updateFile.uuid) &&
                    this.listener == updateFile.listener &&
                    this.portSftp == updateFile.portSftp &&
                    this.userSftp.equals(updateFile.userSftp) &&
                    this.passSftp.equals(updateFile.passSftp) &&
                    this.path.equals(updateFile.path);
        }
        return state;
    }

    /*
    public void show(){
        try {
            for (Field method: this.getClass().getDeclaredFields()) {
                System.out.println(method.getName() + " : " + method.get(this));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    */
}
