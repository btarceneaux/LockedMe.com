package com.LockedMe.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.ListIterator;

public class User
{
    
//    private String[] files;
    private int userInput;
    
    ArrayList<String> files = new ArrayList<String>();
    
    public User()
    {
        userInput = 0;
        
        // Set up working directory
        File workingDirectory = new File("LockedMe_documents");
        createUserDirectory(workingDirectory);
    }
    
    public void displayFilesInDirectory(File parmFile)
    {
        String[] tempFiles = parmFile.list(null);
        
        // Add each file name to the array list
        for (String file : tempFiles)
        {
            files.add(file);
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
        
        System.out.println("\n");
    }
    
    
    public int getUserInput() throws NumberFormatException, IOException
    {   
        boolean invalid = true;
        
        do 
        {       
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                userInput = Integer.parseInt(br.readLine());
                
                if ((userInput == 1) || (userInput == 2) || userInput == 3)
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
}