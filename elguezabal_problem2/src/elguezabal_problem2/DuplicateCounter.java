package elguezabal_problem2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateCounter 
{
	Map<String, Integer> wordCounter = new HashMap<>();
	
	public DuplicateCounter() {}
	
	@SuppressWarnings("unchecked")
	public <E extends Comparable<E>> Map<E, Integer> count(String dataFile)
	{
		Path path = Paths.get(dataFile);
		File file = new File(dataFile);
		
		if(Files.exists(path))
		{
			try (BufferedReader reader = new BufferedReader(new FileReader(file));) 
			{
				int counter = 0;
				String line;
				String[] elements;
				List<E> allElements = new ArrayList<>();
				List<E> allElements2 = new ArrayList<>();
				boolean found = false;
				Map<E, Integer> wordCounter = new HashMap<>();
				

				while((line = reader.readLine()) != null)
				{
					elements = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
					
					for(int i = 0; i < elements.length; i++)
					{
						
						allElements.add((E) elements[i]);
						
					}
				}
				allElements2 = allElements;
					
				for(int i = 0; i < allElements.size(); i++)
				{	
					for(int j = 0; j < allElements2.size(); j++)
					{
						if(allElements.get(i).compareTo(allElements2.get(j)) == 0)
						{
							counter++;
							found = true;
							
						}
					}
					wordCounter.put(allElements.get(i), counter);
					if (found != true)
					{
						wordCounter.put(allElements.get(i), 1);
					}
					found = false;
					counter = 0;
				}
				this.wordCounter = (Map<String, Integer>) wordCounter;
				return wordCounter;	
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
			
			writer.write(this.wordCounter.toString());
		} 
		catch (IOException e) 
		{
			System.out.println("Im sorry, there was an issue creating your file, the program will now exit. (IOException)");
			System.exit(0);
		}
	}
}
