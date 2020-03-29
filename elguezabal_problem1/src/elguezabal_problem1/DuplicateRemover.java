package elguezabal_problem1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import java.util.Set;

public class DuplicateRemover 
{
	public Set<String> uniqueWords = new HashSet<>();
	
	public DuplicateRemover() {}
	
	@SuppressWarnings("unchecked")
	public <E extends Comparable<E>> Set<E> remove(String dataFile)
	{
		Path path = Paths.get(dataFile);
		File file = new File(dataFile);
		
		if(Files.exists(path))
		{
			try (BufferedReader reader = new BufferedReader(new FileReader(file));) 
			{
				String line;
				String[] elements;
				Set<E> uniqueWords = new HashSet<>();
				
				while((line = reader.readLine()) != null)
				{
					elements = line.split(" ");
					Set<E> oldWords = new HashSet<E>((Collection<? extends E>) Arrays.asList(elements));
					uniqueWords.addAll(oldWords);	                                                                                      
				}
				
				this.uniqueWords = (Set<String>) uniqueWords;
				return uniqueWords;
				
				
			} 
			catch (IOException e) 
			{
				System.out.println("Im sorry, there was an issue reading your file, the program will now exit. (IOException)");
				System.exit(0);
			}	
		}
		else
		{
			System.out.println("No file exists.");
		}
		return null;
	}
	
	public void write(String outputFile)
	{
		Path path = Paths.get(outputFile);
		File file = new File(outputFile);
		
		try 
		{
			Files.deleteIfExists(path);
		} 
		catch (IOException e) 
		{
			System.out.println("Im sorry, there was an issue creating your file, the program will now exit. (IOException)");
			System.exit(0);
		}
		
		try (FileWriter writer = new FileWriter(file, true);)
		{
			file.createNewFile();
			
			writer.write(this.uniqueWords.toString());
		} 
		catch (IOException e) 
		{
			System.out.println("Im sorry, there was an issue creating your file, the program will now exit. (IOException)");
			System.exit(0);
		}
		
	}
}
