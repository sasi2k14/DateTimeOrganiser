import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shobana sasi on 10-08-2014.
 */
public class TestResourceHelper {

    private String sResourceDir;
    private File fResourceFolder;

    public TestResourceHelper() {
        setupResourceDir();
    }

    public  File getfResourceFolder() {
        return fResourceFolder;
    }

    public void setfResourceFolder(File fResourceFolder) {
        fResourceFolder = fResourceFolder;
    }

    /**
     * Setup Resource directory for all test cases to be run
     */
    public  void setupResourceDir() {
        sResourceDir = "." + File.separator +
                       "test" + File.separator +
                       "res" + File.separator;

        fResourceFolder = new File(sResourceDir);
        if(fResourceFolder.exists() == false) {
            throw  new RuntimeException("Resource does not exist!");
        }
    }

    public void checkFilesExistInResourceFolder(String ... file) throws FileNotFoundException{
        final List<String> srcFile = new ArrayList<>();
        srcFile.addAll(Arrays.asList(file));

        File[] files = getfResourceFolder().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (srcFile.contains(name)) {
                    srcFile.remove(name);
                    return true;
                }

                return false;
            }
        });

        if(srcFile.size() != 0) {
            String errorMsg = "Following file(s) not found in resource folder \n" + srcFile.toString();
            throw new FileNotFoundException(errorMsg);
        }
    }

    public  void restoreBackupFiles(String... allFileName) throws IOException {
        final List<String> fileNames = Arrays.asList(allFileName);

        for(String fileName : fileNames) {
            String destFileName = fileName.replace(".bak", "");

            File src = new File(sResourceDir + fileName);
            File dest = new File(sResourceDir + destFileName);

            FileUtils.copyFile(src, dest);
        }
    }
}
