/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import core.MetaTagOrganiser;
import core.MetaTagOrganiserModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shobana sasi
 */
public class SingleFileTestWithDestinationDir {
    
    private String srcTestFileName = "file1.jpg";
    private String destFolder = TestResourceHelper.getfResourceFolder().getAbsolutePath() +
                                File.separator +
                                "output";
        
    @Before
    public void setUp() throws IOException {
//        String resDir = "." + File.separator +
//                        "res" + File.separator +
//                        "test";
//
//        testFileDirPathName = resDir + File.separator +
//                                     testFileDir;
//
//        String path = testFileDirPathName + File.separator +
//                      srcTestFileName;
//
//        srcTestFile = new File(path);
//
//        if(!srcTestFile.exists()) {
//            throw new IllegalArgumentException("Unable to locate the test file");
//        }
//
//        srcTestFilePathName = path;
//        destTestDir = resDir + File.separator + "single-file-test-w-dest";

        TestResourceHelper.setupResourceDir();
        TestResourceHelper.checkFilesExistInResourceFolder(srcTestFileName);
        TestResourceHelper.restoreBackupFiles(srcTestFileName);
    }
    
    @Test
    public void test() throws FileNotFoundException {
        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
        model.consumeArguments(new String[]{srcTestFileName, destFolder});

        new MetaTagOrganiser(model).execute();

        assertEquals(new File(destFolder).listFiles().length, 1);
        assertEquals(new File(destFolder).listFiles()[0].getName(), "2013-Sep-01 11.46.05 AM.jpg");
    }
    
    @After
    public void tearDown() throws IOException {
        File srcFile = new File(destFolder);
        FileUtils.deleteQuietly(srcFile);
    }
    
}