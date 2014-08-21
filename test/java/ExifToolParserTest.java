/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import core.ExifToolParser;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author shobana sasi
 */
public class ExifToolParserTest {
    public static void main(String[] args1) throws IOException {
        ExifToolParser parser = new ExifToolParser();
        String lastModifiedDate = parser.parseLastModifiedDate(new File("D:\\My Documents\\My Pictures\\Shruti\\VID_20130217_173657.mp4"));
        System.out.println(lastModifiedDate);
    }
}
