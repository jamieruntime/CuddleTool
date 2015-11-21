/*
 * Cuddle Tool
 * Jamie Purchase
 * 20/11/2015
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class FileSystem
{
    
    private static String getExtension(File file)
    {
        if(file.getName().lastIndexOf(".") != -1 && file.getName().lastIndexOf(".") != 0)
        {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return "";
    }
    
    public static ArrayList<File> getFolder(String path, boolean getFiles, boolean getDirectories, String getExt)
    {
        ArrayList<File> result = new ArrayList();
        File[] folder = new File(path).listFiles();
        for(int f = 0; f < folder.length; f++)
        {
            if(getFiles && folder[f].isFile())
            {
                if(getExt != null)
                {
                    if(getExtension(folder[f]).equals(getExt)) {result.add(folder[f]);}
                }
                else {result.add(folder[f]);}
            }
            if(getDirectories && folder[f].isDirectory()) {result.add(folder[f]);}
        }
        return result;
    }
    
    public static ArrayList<File> getFolderContent(String path, String ext, boolean print, boolean sub)
    {
        // Create an empty array of files
        ArrayList<File> result = new ArrayList();
        
        // Create an empty array of subfolders
        ArrayList<File> subfolder = new ArrayList();
        
        // Get files in folder
        ArrayList<File> folderFile = getFolder(path, true, false, ext);
        for(int f = 0; f < folderFile.size(); f++) {result.add(folderFile.get(f));}
        
        // Get files in subfolders
        if(sub)
        {
            ArrayList<File> folderSub = getFolder(path, false, true, ext);
            for(int s = 0; s < folderSub.size(); s++)
            {
                ArrayList<File> folderSubFile = getFolderContent(folderSub.get(s).getPath(), ext, false, true);
                for(int sf = 0; sf < folderSubFile.size(); sf++) {result.add(folderSubFile.get(sf));}
            }
        }
        
        // Debug
        if(print)
        {
            System.out.println("Getting all files with extension '" + ext + "' in '" + path + "'");
            for(int r = 0; r < result.size(); r++)
            {
                System.out.println(" - " + result.get(r).getPath());
            }
            System.out.println("");
        }
        
        // Return array of files
        return result;
    }

    public static ArrayList<String> loadFile(String path)
    {
        ArrayList<String> data = new ArrayList();
        boolean active = true;
        BufferedReader reader;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(path));
            try
            {
                while(active)
                {
                    line = reader.readLine();
                    if(line != null) {data.add(line);}
                    else {active = false;}
                }
                reader.close();
            }
            catch(IOException ex) {System.out.println(ex);}
        }
        catch(FileNotFoundException ex) {System.out.println(ex);}
        return data;
    }
    
    private static void saveFile(String data, String path)
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(new FileWriter(path, false));
            writer.printf("%s" + "%n", data);
            writer.close();
        }
        catch (IOException ex) {System.out.println(ex);}
    }
    
    public static void saveFile(ArrayList<String> data, String path)
    {
        String condense = "";
        for(int x = 0; x < data.size(); x++)
        {
            condense += data.get(x);
            if(x < data.size() - 1) {condense += System.getProperty("line.separator");}
        }
        saveFile(condense, path);
    }

}