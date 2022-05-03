package com.LockedMe.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.ListIterator;

public class User
{

    private int userInput;
    
    ArrayList<String> files = new ArrayList<String>();
    
    public User()
    {
        userInput = 0;
        
        // Set up working directory
        File workingDirectory = new File("LockedMe_documents");
        createUserDirectory(workingDirectory);
    }
    
    
    private String[] getFilesInDirectory(File parmFileDirecotry)
    {
        String directoryList = parmFileDirecotry.getParent();
        
        File f = new File(directoryList);
        String[] tempFiles = f.list();
        
        return tempFiles;
    }
    
    
    public void displayFilesInDirectory(File parmFile)
    {
        String[] tempFiles = parmFile.list(null);
        
        // Add each file name to the array list
        if (files.size() == 0)
        {
            for (String file : tempFiles)
            {
                files.add(file);
            }
        }
        
        
        files.sort(null);
        System.out.println("---------------------------------------------------");
        System.out.println("Now displaying the sorted files in ascending order.");
        System.out.println("---------------------------------------------------");
        
        // Create an iterator to iterate through the items of the string collection.
        ListIterator<String> iterator = files.listIterator();
        
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        
        System.out.println("");
    }
    
    
    // The "contextType" parameter is being used to limit the valid values between the 
    // main menu and the sub menu.
    public int getUserInput(char parmContextType) throws NumberFormatException, IOException
    {   
        boolean invalid = true;
        
        do 
        {       
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                userInput = Integer.parseInt(br.readLine());
                
                // 1, 2 and 3 are valid for the main menu, while 1, 2, 3 and 4 are valid for the sub menu.
                if ( (((userInput == 1) || (userInput == 2) || userInput == 3) && (parmContextType == 'M')) 
                        || ((userInput == 1) || (userInput == 2) || (userInput == 3) || (userInput == 4)) 
                        && parmContextType == 'S')
                {
                    invalid = false;
                }
                else
                {
                    System.out.println("You have entered an invalid value. Please try again\n");
                    displayUserOptions();
                }
            }
            catch(NumberFormatException | IOException e)
            {
                System.out.println("\nYour input is invalid! \nPlease enter an integer between 1 and 3.\n" + e + "\n");
                displayUserOptions();
            }
        }while (invalid);
        
        return userInput;
    }
    
    
    public void displayUserOptions()
    {
        System.out.println("------------------------ Menu Options --------------------------");
        System.out.println("| Enter '1' to return the current file names in ascending order.|");
        System.out.println("| Enter '2' to see all of the file and directory options.       |");
        System.out.println("| Enter '3' to quit the application                             |");
        System.out.println("----------------------------------------------------------------");
        System.out.print("Please enter your choice : ");
    }
    
    
    private void createUserDirectory(File parmWorkingDirectory)
    {
     // Set up a directory in which every file will be added to.
        
        if (parmWorkingDirectory.mkdir() ) 
        {
            System.out.println("The directory is created");
        }
        else 
        {
            System.out.println("Document working directory already exists.\n");
        }
    }
    
    
    public void displayUserInteractionInformation()
    {
        System.out.println("    ------------------------ User Options --------------------------");
        System.out.println("    | Enter '1' to add a file to the existing directory list.       |");
        System.out.println("    | Enter '2' to delete a file from the existing directory.       |");
        System.out.println("    | Enter '3' to search for a file from the main directory.       |");
        System.out.println("    | Enter '4' to navigate back to the main menu.                  |");
        System.out.println("    ----------------------------------------------------------------");
        System.out.print("    Please enter your choice : ");
    }
    
    
    // This helper method will return the file name without the directory information.
    private String getFileNameFromFullPath(String parmFullFilePath)
    {
        File f = new File(parmFullFilePath);
        
        return f.getName();
    }
    
    
    // This helper method will check to see if the file does not exist.
    private boolean checkIfFileExists(String parmDirectoryToFile)
    {
        File f = new File(parmDirectoryToFile);
        return f.exists();
    }
    
    
    // Copy file to the directory with new Name
    public void addFileToExistingDirectory(String parmFullPathToFile, String parmDirectoryLocation)
    {
        String fullDirectoryLocation = (parmDirectoryLocation + "/");
        String fileName = getFileNameFromFullPath(parmFullPathToFile);
        boolean fileExistsOnFileSystem = false;
        
        //If the file entered is not the correct case, correct it for them. 
        if(checkIfFileExists(parmFullPathToFile) == false)
        {
            File f = new File(parmFullPathToFile);
            String tempName = f.getName();
            String[] fileDirectory = getFilesInDirectory(f);
            
            //loop through the directory list of files
            for(int i = 0; i < fileDirectory.length; i++)
            {
                if(tempName.equalsIgnoreCase(fileDirectory[i]))
                {
                    fileName = fileDirectory[i];
                    fileExistsOnFileSystem = true;
                }
            }
        }     
        if(checkIfFileExists(parmFullPathToFile))
        {
            fileExistsOnFileSystem = true;
        }
        
        //Check to see if the user type
        String newFilePath = (fullDirectoryLocation + fileName);
        
        // We only want to add the file if it is not already in the directory.
        if(!checkIfFileExists(newFilePath) && fileExistsOnFileSystem == true)
        {
            try (BufferedReader bufInput = new BufferedReader(new FileReader(parmFullPathToFile));
                    BufferedWriter bufOutput = new BufferedWriter(new FileWriter(newFilePath)))
               {
                   String line = "";
                   
                   while ((line = bufInput.readLine()) != null ) 
                   {
                       bufOutput.write(line);
                       bufOutput.newLine();
                   } 
               }
               catch (FileNotFoundException f)
               {
                   System.out.println("File not fount" + f);
               }
               catch (IOException e)
               {
                   System.out.println("IOException: " + e);
               }
        }
        else if (fileExistsOnFileSystem == false)
        {
            System.out.println("The file does not exist on the file system!");
        }
        else
        {
            System.out.println("The file is already in the directory.");
        }
    }
    
    public void deleteUserSpecifiedFile()
    {
        String inputString = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try
        {
            inputString = br.readLine();
        } 
        catch (IOException e)
        {
            System.out.println("An exception has occured. : " + e);
        }
        
        if(inputString.length() != 0)
        {
            String fullPath = ("LockedMe_documents" + "/" + inputString);
            File f = new File(fullPath);
            
            // Check to see if the file exists before deleting it.
            if(f.exists())
            {
                String fileName = f.getName();
                if(f.delete())
                {
                    System.out.println("File " + fileName + " has successfully been deleted.");
                }
            }
            else
            {
                System.out.println("File not found");
            }
        }   
    }
    
    // This method will get the input for the file name
    public String getFullPathOfFile() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
                
        return inputString;
    }
}
