package jskj.com.naprioridetectclient;

import org.junit.Test;

import jskj.com.naprioridetectclient.db.NAprioriDao;
import jskj.com.naprioridetectclient.entry.AppInfo;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test_db_fun() {
        NAprioriDao instance = NAprioriDao.getInstance(NAprioriApplication.getContext());
        AppInfo info = new AppInfo();
        info.name = "test";
        info.normalApp = 1;
        info.permissionFamilly = "internet";
        info.versionName = "1.0";
        instance.save(info);
    }
}