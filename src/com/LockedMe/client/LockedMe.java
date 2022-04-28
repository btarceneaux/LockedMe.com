package com.LockedMe.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class LockedMe
{

    public static void main(String[] args) throws IOException
    {
        // Welcome screen and developer details
        System.out.println("*********************************************\n");
        System.out.println("Welcome to the LockedMe.com application.\n");
        System.out.println("Developer Name  : Burton Arceneaux\n");
        System.out.println("Developer Title : Java Full Stack Developer\n");
        System.out.println("*********************************************\n");
        
        // Set up a directory in which every file will be added to.
        createUserDirectory();
        
        int userInput = 0;
        do
        {
            displayUserOptions();
            
            do 
            {
                try(Scanner keyboard = new Scanner(System.in)) 
                {
                    userInput = keyboard.nextInt();   
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Your input is invalid! Please enter an integer between 1 and 3." + e);
                }
            }while (userInput != 1 && userInput != 2 && userInput != 3);
            
            
            if (userInput == 1)
            {
                
            }
             
                           
        }while (userInput != 3);
        
    }
    
    
    public static void displayUserOptions()
    {
        System.out.println("Please enter '1' to return the current file names in ascending order. : \n");
        System.out.println("Please enter '2' to see all of the file and directory options. : \n");
        System.out.print("Please enter '3' to quit the application : ");
    }
    
    public static void createUserDirectory()
    {
        // Create the directory where the files will exist.
        File myDirectory = new File("LockedMe_documents");
        
        if (myDirectory.mkdir() ) 
        {
            System.out.println("The directory is created");
        }
        else 
        {
            System.out.println("Document working directory already exists.\n");
        }
    }

}
