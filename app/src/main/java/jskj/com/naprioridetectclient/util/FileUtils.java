package jskj.com.naprioridetectclient.util;

import java.io.File;

/**
 * Created by cui on 17-3-26.
 */

public class FileUtils {

    public String getFileSuffix(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filepath must not be null!!!");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("the file is not exist!!!");
        }
        if (!file.getAbsolutePath().contains(".")) return null;
        return file.getAbsolutePath().substring(filePath.lastIndexOf("."));
    }
}
