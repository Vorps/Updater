package net.vorps.updater.utils;

import com.jcraft.jsch.*;

/**
 * Project Updater Created by Vorps on 04/09/2016 at 12:09.
 */
public class Download {

    private Session session;
    private ChannelSftp channelSftp;

    public Download(String host, int port, String user, String pass) throws Exception{
        this.session = new JSch().getSession(user, host, port);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.setPassword(pass);
        this.session.connect();
        Channel channel = this.session.openChannel("sftp");
        channel.connect();
        this.channelSftp = (ChannelSftp) channel;
    }

    public void downLoadFile(String pathSrc, String pathDest) throws Exception{
        this.channelSftp.get(pathSrc, pathDest);
    }

    public void close(){
        this.channelSftp.exit();
        this.session.disconnect();
    }
}
