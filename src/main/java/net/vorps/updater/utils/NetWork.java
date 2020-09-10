package net.vorps.updater.utils;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Project Updater Created by Vorps on 04/09/2016 at 23:53.
 */
public class NetWork {

    public static String read(BufferedInputStream reader) throws IOException {
        byte[] b = new byte[4096];
        return new String(b, 0, reader.read(b));
    }
}
