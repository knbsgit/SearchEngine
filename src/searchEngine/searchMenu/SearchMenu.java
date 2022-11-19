package searchEngine.searchMenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import searchEngine.log.Logger;
import searchEngine.util.UtillClass;

public class SearchMenu 
{
	public static TreeMap<String,TreeSet> index = new TreeMap<String,TreeSet>();
	
	public static void IndexingFile(File f)
	{

		if(f.getName().endsWith(".txt"))
		{
				//System.out.println("2");
			String Absolute = f.getAbsolutePath();
			Path fileName = Path.of(Absolute);
			try 
			{
					//System.out.println("3");
				String str = Files.readString(fileName);
				String[] splittedWords = str.split(" ");
				for(String w:splittedWords)
				{
					int Count = lineCount(f,w);
					if(index.get(w)!=null)
					{
							//System.out.println("5");
						TreeSet<String> fileIndex = index.get(w);
						fileIndex.add(Absolute+",Line Count:"+Count);
						index.put(w,(TreeSet) fileIndex);
					}
					else
					{
						TreeSet<String> fileIndexSecond = new TreeSet<String>();
						fileIndexSecond.add(Absolute+",Line Count:"+Count);
						index.put(w,(TreeSet) fileIndexSecond);
					}
				}
					
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void IndexFolder(File f)
	{
		String fileAbsoulute = f.getAbsolutePath();
		if(f.isDirectory())
		{
			File [] files = f.listFiles();
			for(File fa:files)
			{
				String filePath = fa.getAbsolutePath();
				if(fa.isDirectory())
				{
					IndexFolder(fa);
				}
				else
				{
					IndexingFile(fa);
				}
			}
		}
		else
		{
			IndexingFile(f);
		}
	}
	public static int lineCount(File f,String s)
	{
		if(f.exists()&&f.isFile()&&f.getName().endsWith(".txt"))
		{
			int count=0;
			BufferedReader br = null;
			try
			{
				br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
				String line;
				while((line=br.readLine())!=null)
				{
					count++;
					String[] split = line.split(" ");
					for(String str:split)
					{
						if(str.equals(s))
						{
							return count;
						}
					}
				}
						
			}
			catch (IOException e) 
			{
				Logger logger = Logger.getInstance();
				logger.log(e.getMessage());
			} 
			finally 
			{
				if(br!=null)
				{
					UtillClass.closeBufferReader(br);
				}
			}
			
		}
		return 0;
	}
}
