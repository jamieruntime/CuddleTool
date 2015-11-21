/*
 * Cuddle Tool
 * Jamie Purchase
 * 20/11/2015
 */
package app;

import files.FileSystem;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class Launch
{

    public static void main(String[] args)
    {
        // Init
        consoleInit();
        
        // Check arguments
        if(args.length == 3)
        {
            // Define arguments
            String path = args[0];
            String ext = args[1];
            boolean sub = false;
            if(args[2].equals("true")) {sub = true;}
            
            // Define errors
            ArrayList<String> errorOutput = new ArrayList();
            
            // Check directory is valid
            boolean pathValid = true;
            if(!new File(path).isDirectory())
            {
                pathValid = false;
                errorOutput.add("ERROR: invalid directory!");
                errorOutput.add("");
                errorOutput.add("You must specifiy a valid directory in which to run changes");
                errorOutput.add("");
            }
            
            // Check extension is valid
            boolean extValid = true;
            if(ext.length() < 1)
            {
                extValid = false;
                errorOutput.add("ERROR: invalid extension!");
                errorOutput.add("");
                errorOutput.add("You must specifiy a valid extension for files on which to run changes");
                errorOutput.add("");
            }
            
            // Check subfolder is valid
            boolean subValid = true;
            if(args[2].equalsIgnoreCase("true")) {sub = true;}
            else if(args[2].equalsIgnoreCase("false")) {sub = false;}
            else
            {
                subValid = false;
                errorOutput.add("ERROR: invalid subfolder boolean!");
                errorOutput.add("");
                errorOutput.add("You must specifiy 'true' or 'false' for subfolder processing");
                errorOutput.add("");
            }
            
            // Perform action
            if(pathValid && extValid && subValid) {fileCuddle(path, ext, sub);}
            
            // Report errors
            else
            {
                for(int e = 0; e < errorOutput.size(); e++) {System.out.println(errorOutput.get(e));}
            }
        }
        
        // Output Help
        else if(args[0].equalsIgnoreCase("help")) {consoleArgs();}
        
        // Arguments incorrect
        else
        {
            System.out.println("ERROR: incorrect arguments!");
            System.out.println("");
            consoleArgs();
        }
    }
    
    private static void consoleArgs()
    {
        System.out.println("The arguments required are;");
        System.out.println(" directory     (string) directory in which to run the tool");
        System.out.println(" extension     (string) extension of files to change");
        System.out.println(" subfolder     (boolean) true or false to change files in subfolders");
        System.out.println("");
    }
    
    private static void consoleInit()
    {
        System.out.println("");
        System.out.println("     +----------------+");
        System.out.println("     | Cuddle Tool    |");
        System.out.println("     | Jamie Purchase |");
        System.out.println("     | 20/11/2015     |");
        System.out.println("     +----------------+");
        System.out.println("");
    }
    
    private static void fileCuddle(String directory, String extension, boolean subfolder)
    {
        // Get all files in directory
        ArrayList<File> file = FileSystem.getFolderContent(directory, extension, true, subfolder);
        
        // Process files
        System.out.println("Processing files");
        for(int f = 0; f < file.size(); f++)
        {
            fileCuddleFile(file.get(f).getAbsolutePath());
        }
        System.out.println("");
        System.out.println("Done");
    }
    
    private static void fileCuddleFile(String filePath)
    {
        // Debug
        System.out.println(" - " + filePath);
        
        // Load file data
        ArrayList<String> fileData = FileSystem.loadFile(filePath);
        
        // Create blank array
        ArrayList<String> newData = new ArrayList();
        
        // Iterate through data
        int newLines = 0;
        boolean add;
        for(int x = 0; x < fileData.size(); x++)
        {
            add = true;
            if(x > 0 && fileData.get(x).indexOf("{") >= 0)
            {
                add = false;
                newData.set(newLines - 1, newData.get(newLines - 1) + " {");
            }
            if(add)
            {
                newData.add(fileData.get(x));
                newLines += 1;
            }
        }
        
        // Save new file
        FileSystem.saveFile(newData, filePath);
    }

}