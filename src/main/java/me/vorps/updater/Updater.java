package me.vorps.updater;

import lombok.Getter;
import me.vorps.updater.action.ActionError;
import me.vorps.updater.action.Execute;
import me.vorps.updater.action.ExecuteUUID;
import me.vorps.updater.network.NetWork;
import me.vorps.updater.network.NetWorkListener;
import me.vorps.updater.utils.DataType;
import me.vorps.updater.utils.Download;
import me.vorps.updater.utils.Serialize;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.nio.file.Paths;

/**
 * Project Updater Created by Vorps on 01/09/2016 at 23:03.
 */
public class Updater {

    private UpdateFile updateFile;
    private @Getter Plugin plugin;
    private @Getter NetWork netWork;
    private NetWorkListener netWorkListener;
    private Download download;

    /**
     * Construtor create onEnable plugin
     * @param plugin Plugin
     */
    public Updater(Plugin plugin) {
        Updater.instance = this;
        this.plugin = plugin;
        try {
            this.updateFile = this.getUpdateFile("updateFile.ser");
            this.download = new Download(this.updateFile.getHost(), this.updateFile.getPortSftp(), this.updateFile.getUserSftp(), this.updateFile.getPassSftp());
            this.netWork = new NetWork(this.updateFile.getHost(), this.updateFile.getPort());
            this.netWork.sendData(DataType.START);
            this.enable();
            if(this.updateFile.isListener()){
                this.netWorkListener = new NetWorkListener(this.updateFile.getHost(), this.updateFile.getPort());
                this.netWorkListener.open();
            }
        } catch (Exception e){
            this.error();
        }
    }

    public void error(){
        if(this.netWork != null) Updater.getInstance().getNetWork().sendData(DataType.ERROR);
        new ActionError().run(Updater.getInstance().getPlugin());
    }
    /**
     * Update plugin
     * @return boolean
     */
    public boolean enable() throws Exception{
        boolean state = false;
        if (this.isUpdate()) {
            state = new File(Updater.path + "/updateFile.ser").delete() ? new File(Updater.path + "/updateFileTmp.ser").renameTo(new File(Updater.path + "/updateFile.ser")) : state;
            this.netWork.sendData(DataType.UPDATE);
        }
        this.executeAction();
        return state;
    }

    /**
     * Methode onDisable plugin
     */
    public void disable() {
        this.netWork.sendData(DataType.STOP);
        if(this.updateFile.isListener()) this.netWorkListener.close();
    }


    private UpdateFile getUpdateFile(String name) throws Exception{
        return (UpdateFile) Serialize.deSerialisation(Updater.path + "/" + name);
    }

    private boolean isUpdate() throws Exception{
        this.download.downLoadFile(this.updateFile.getPath()+"updateFile.ser", Updater.path + "/updateFileTmp.ser");
        UpdateFile updateFile = (UpdateFile) Serialize.deSerialisation(Updater.path + "/updateFileTmp.ser");
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
        this.download.downLoadFile(this.updateFile.getPath()+plugin.getName()+".jar", Updater.path + "/"+plugin.getName()+".jar"); // TODO: 05/09/2016 Change
        Bukkit.getPluginManager().enablePlugin(this.plugin);
    }

    private static @Getter String path;
    private static @Getter Updater instance;

    static {
        path = Paths.get(System.getProperty("user.dir")).toString();
    }
}
