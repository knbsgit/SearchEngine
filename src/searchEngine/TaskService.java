package searchEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import searchEngine.constants.TaskConstants;
import searchEngine.log.Logger;
import searchEngine.util.UtillClass;

public class TaskService 
{
	public static boolean AddTask(TaskBean task)
	{
		String finalPath;
		if (task != null) 
		{
			BufferedWriter bw = null;
			
			String Path=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
			String FileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
			
			int lineLimit = Integer.parseInt(UtillClass.getConfiguration().getProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY));  
			
			int FileCount = UtillClass.fileCount(Path,FileName);
			
			System.out.println("number of files in the directory:"+FileCount);
			
			if(FileCount < lineLimit) 
			{
				finalPath = Path + "\\"+FileName + ".txt";
			}
			else
				finalPath = Path + "\\"+FileName+(FileCount)+".txt";
			
			int storeNumberOfLinesInAFile = UtillClass.getNumberOfLine(finalPath);
			
			System.out.println("number of lines in file in the past file:"+storeNumberOfLinesInAFile);
			
			if(storeNumberOfLinesInAFile >= lineLimit) 
			{
				finalPath = Path + "\\"+FileName+(FileCount + 1)+".txt";
			}
			
			System.out.println("**** "+finalPath);
			
			try 
			{
				bw = new BufferedWriter(new FileWriter(finalPath, true));
				bw.write(task.toString());
				bw.newLine();

			}
			catch (Exception e)
			{
				Logger logger = Logger.getInstance();
				logger.log(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				UtillClass.closeBufferedWritter(bw);
			}
			return true;
		}
		return false;

	}
	
	
	public static void listTask() throws ParseException
	{
		String path=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
		String FileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
		File f = new File(path);
		File listOfFiles[] = f.listFiles();
		for(File eachLine : listOfFiles) 
		{
			if(eachLine.getName().contains(FileName) && eachLine.getName().contains("txt") && eachLine.isFile())
			{
				String Absolute = eachLine.getAbsolutePath();
				System.out.println(eachLine.getName());
				Path fileName = Path.of(Absolute);
				try 
				{
					String str = Files.readString(fileName);
					System.out.println(str);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	public static LinkedHashSet<TaskBean> listTaskBasedOn() 
	{
		LinkedHashSet<TaskBean> taskBeanList = new LinkedHashSet<TaskBean>();
		String path=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
		String FileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
		
		int lineLimit = Integer.parseInt(UtillClass.getConfiguration().getProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY));
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (File oneFile : listOfFiles)
		{
			if (oneFile.isFile() && oneFile.getName().contains("MyTask") && oneFile.getName().contains(".txt")) {
				BufferedReader br = null;
				try 
				{
					br = new BufferedReader(new FileReader(oneFile.getAbsoluteFile()));
					String oneLine;
					TaskBean tempTaskBean;
					String[] splittedWords;
					String readTaskName, readTaskDescription;
					while ((oneLine = br.readLine()) != null)
					{
						splittedWords = oneLine.split(":");
						Date date = null;
						long time = Long.parseLong(splittedWords[2]);
						Timestamp ts = new Timestamp(time);
						date = new Date(ts.getTime());
						tempTaskBean = new TaskBean(splittedWords[0], splittedWords[1], time);
						taskBeanList.add(tempTaskBean);
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
		}
	
		return taskBeanList;
	}
	public static TaskBean searchTask(String taskName) throws ParseException
	{
		TaskBean task = null;
		if(taskName!=null)
		{
			LinkedHashSet<TaskBean> AllTasks = new LinkedHashSet<TaskBean>();
			AllTasks = listTaskBasedOn();
			for(TaskBean t:AllTasks)
			{
				if(t.getTaskName().contains(taskName))
				{
					return task = new TaskBean(t.getTaskName(),t.getTaskDesc(),t.getCreatedTime());
				}
			}
			return task;
		}
		else
			return null;
	}
	
	public static boolean updateTask(String taskName, String description)
	{
		
		String path=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
		String FileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
		
		int lineLimit = Integer.parseInt(UtillClass.getConfiguration().getProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY));
		
		LinkedHashSet<TaskBean> taskBeanList = new LinkedHashSet<TaskBean>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String pathOfFile =null;
		for (File oneFile : listOfFiles) 
		{
			if (oneFile.isFile() && oneFile.getName().contains(FileName) && oneFile.getName().contains(".txt")) 
			{
				pathOfFile = UtillClass.searchTaskInFile(oneFile.getAbsolutePath(),taskName);
				
				if(pathOfFile!=null) 
					break;
			}
		}
		if(pathOfFile!=null) 
		{
			taskBeanList = UtillClass.listTaskInFile(taskBeanList,pathOfFile);
			try 
			{
				new FileOutputStream(pathOfFile).close();
			}
			catch (FileNotFoundException e)
			{
				Logger l1 = Logger.getInstance();
				l1.log(e.getMessage());
				e.printStackTrace();

			}
			catch (IOException e)
			{
				Logger logger = Logger.getInstance();
				logger.log(e.getMessage());
			}

			for (TaskBean t1 : taskBeanList) 
			{
				if (t1.getTaskName().equals(taskName))
				{
					try
					{
						t1.setTaskDesc(description);
					} 
					catch (IllegalAccessException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				UtillClass.addParticulars(t1, pathOfFile);

			}
			return true;
		}
		else 
			return false;
		

	}
	
	public static boolean deleteTask(String TaskName)
	{
		String path=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
		String FileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
		
		LinkedHashSet<TaskBean> taskList = new LinkedHashSet<TaskBean>();
		
		File folder = new File(path);
		
		File[] FileList  = folder.listFiles();
		
		String FilePath = null;
		
		TaskBean finalTask = null;
		
		for(File file:FileList)
		{
			if(file.isFile() && file.getName().contains(TaskName) && file.getName().contains(".txt"))
			{
				finalTask = UtillClass.searchTaskInFileforDelete(file.getAbsolutePath(), TaskName);
				if(finalTask!=null)
				{
					FilePath = file.getAbsolutePath();
					System.out.println(FilePath);
					break;
				}
			}
			if(FilePath!=null)
			{
				taskList = UtillClass.listTaskInFileinDelete(taskList, FilePath);
				
				taskList.remove(finalTask);
				
				try
				{
					 new FileOutputStream(FilePath).close();
				}
				catch(IOException e)
				{
					Logger logger = Logger.getInstance();
					logger.log(e.getMessage());
				}
				
				for(TaskBean t1 : taskList)
				{
					UtillClass.addParticulars(t1, FilePath);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
		
	}
}
		