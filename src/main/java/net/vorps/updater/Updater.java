package net.vorps.updater;

import lombok.Getter;
import me.vorps.updater.action.Action;
import me.vorps.updater.action.ActionError;
import me.vorps.updater.action.Execute;
import me.vorps.updater.action.ExecuteUUID;
import me.vorps.updater.utils.Download;
import me.vorps.updater.utils.Serialize;
import net.vorps.updater.action.ActionError;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.UUID;

/**
 * Project Updater Created by Vorps on 01/09/2016 at 23:03.
 */
public class Updater{

    private UpdateFile updateFile;
    private @Getter Plugin plugin;
    //private @Getter NetWork netWork;
    //private NetWorkListener netWorkListener;
    private Download download;
    private boolean update;

    /**
     * Construtor create onEnable plugin
     * @param plugin Plugin
     */
    public Updater(Plugin plugin) {
        Updater.instance = this;
        this.update = false;
        this.plugin = plugin;
        try {
            this.updateFile = this.getUpdateFile("updateFile.ser");
            this.download = new Download(this.updateFile.getHost(), this.updateFile.getPortSftp(), this.updateFile.getUserSftp(), this.updateFile.getPassSftp());
            //this.netWork = new NetWork(this.updateFile.getHost(), this.updateFile.getPort());
            //this.netWork.sendData(DataType.START);
            //if(this.updateFile.isListener()){
            //    this.netWorkListener = new NetWorkListener(this.updateFile.getHost(), this.updateFile.getPort());
            //    this.netWorkListener.open();
            //}
            this.enable();
        } catch (Exception e){
            e.printStackTrace();
            this.error();
        }
        if(this.update) Bukkit.reload();
    }

    public void error(){
        //if(this.netWork != null) this.netWork.sendData(DataType.ERROR);
        new ActionError().run(Updater.getInstance().getPlugin());
    }
    /**
     * Update plugin
     * @return boolean
     */
    public boolean enable() throws Exception{
        boolean state = false;
        if (this.isUpdate()) {
            new File(Updater.path + "updateFile.ser").delete();
            new File(Updater.path + "updateFileTmp.ser").renameTo(new File(Updater.path + "updateFile.ser"));
            //this.netWork.sendData(DataType.UPDATE);
        } else{
            new File(Updater.path + "updateFileTmp.ser").delete();
        }
        this.executeAction();
        return state;
    }

    /**
     * Methode onDisable plugin
     */
    public void disable() {
        //this.netWork.sendData(DataType.STOP);
        //if(this.updateFile.isListener()) this.netWorkListener.close();
        this.download.close();
       // try {
        //    this.netWork.getConnexion().close();
       // } catch (Exception e){
            //
        //}
    }


    private UpdateFile getUpdateFile(String name) throws Exception{
        return (UpdateFile) Serialize.deSerialisation(Updater.path + "/" + name);
    }

    private boolean isUpdate() throws Exception{
        this.download.downLoadFile(this.updateFile.getPath()+"updateFile.ser", Updater.path + "updateFileTmp.ser");
        UpdateFile updateFile = (UpdateFile) Serialize.deSerialisation(Updater.path + "updateFileTmp.ser");
        if (!updateFile.getVersion().equals(this.updateFile.getVersion())) this.update();
        boolean state = !this.updateFile.equals(updateFile);
        if (state) this.updateFile = updateFile;
        return state;
    }

    private boolean executeAction() throws Exception {
        Object object = Class.forName("me.vorps.updater.action.Action" + this.updateFile.getAction().toString().substring(0, 1)+this.updateFile.getAction().toString().substring(1).toLowerCase()).getConstructor().newInstance();
        return object instanceof Execute ? ((Execute) object).run(this.plugin) : ((ExecuteUUID) object).run(this.plugin, this.updateFile.getUuid());
    }

    private void update() throws Exception{
        Bukkit.getPluginManager().disablePlugin(this.plugin);
        new File(Updater.path+"plugins"+Updater.DS+plugin.getName()+".jar").delete();
        this.download.downLoadFile(this.updateFile.getPath()+plugin.getName()+".jar", Updater.path+"plugins"+Updater.DS+plugin.getName()+".jar");
        this.update = true;
    }

    private static @Getter String path;
    private static @Getter String DS;
    private static @Getter Updater instance;

    static {
        DS = System.getProperty("file.separator");
        path = System.getProperty("user.dir")+Updater.DS;
    }

    public static void main(String[] args) {
        try {
            Serialize.serializable( new UpdateFile("update.vorps.net", 80, "1.0", Action.NULL, UUID.fromString("b7135829-65dc-3790-a22b-068fa16f0610"), false, 3589, "update", "i9?fS[+F=3Ry68Cc_E3e", "/home/update/RushVolcano/"), path+"updateFile.ser");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
