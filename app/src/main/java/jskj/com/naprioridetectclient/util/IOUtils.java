package jskj.com.naprioridetectclient.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

    public void closeIO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
                closeable = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeIOs(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            closeIO(closeable);
        }
    }
}
