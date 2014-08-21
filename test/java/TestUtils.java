import org.apache.commons.io.FileUtils;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Created by esaskum on 8/19/14.
 */
public class TestUtils {
    private static final String SEP = File.separator;
    private static TestUtils INSTANCE;

    private File resourceFolder;

    private TestUtils() {
         String folder = "." + SEP + "test" + SEP + "res";
         resourceFolder = new File(folder);
    }

    public static TestUtils getInstance() {
        if(INSTANCE != null) {
            INSTANCE = new TestUtils();
        }

        return INSTANCE;
    }

    public File getResourceFolder() {
        return resourceFolder;
    }

    public void setResourceFolder(File resourceFolder) {
        this.resourceFolder = resourceFolder;
    }

    public void checkFilesExistInResourceFolder(String ... file) throws FileNotFoundException {
        final List<String> srcFile = Arrays.asList(file);

        File[] files = getResourceFolder().listFiles(new FilenameFilter() {
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

    public void restoreBackupFiles(String... allFileName) throws IOException {
        final List<String> fileNames = Arrays.asList(allFileName);
        String sResourceDir = getResourceFolder().getAbsolutePath() + SEP;

        for(String fileName : fileNames) {
            String destFileName = fileName.replace(".bak", "");

            File src =  new File(sResourceDir + fileName);
            File dest = new File(sResourceDir + destFileName);

            FileUtils.copyFile(src, dest);
        }
    }
}
