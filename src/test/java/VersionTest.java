/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;

import core.MetaTagOrganiserModel;

/**
 *
 * @author shobana sasi
 */
public class VersionTest {
    
    public static void main(String[] args) throws FileNotFoundException {
        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
        model.consumeArguments(new String[]{"--version"});
        
//        new MetaTagOrganiser(model).execute();
    }
    
}
