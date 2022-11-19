package searchEngine.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Properties;



import searchEngine.TaskBean;
import searchEngine.constants.TaskConstants;
import searchEngine.log.Logger;


public class UtillClass 
{
	public static void closeBufferReader(BufferedReader br)
	{
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeBufferedWritter(BufferedWriter bw)
	{
		try 
		{
			bw.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean stringValidation(String s)
	{
		if (s == null && s.equals(""))
			return false;
		else
			return true;
	}
	
	public static void setDefaultConfig()
	{
		Properties config = new Properties();
		
		config.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,TaskConstants.BASE_FILE_PATH_FOR_TASK_VALUE);
		config.setProperty(TaskConstants.BASE_FILE_NAME_KEY,TaskConstants.BASE_TASK_FILE_NAME_VALUE);
		config.setProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY,TaskConstants.COUNT_PER_FILE_VALUE);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			config.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
	}
	public static void setUserConfigPathName(String pathName)
	{
		Properties userConfig = new Properties();
		
		userConfig.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,pathName);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			userConfig.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
		
	}
	public static void setUserConfigPathName(String pathName,String fileName,String count)
	{
		Properties userConfig = new Properties();
		
		userConfig.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,pathName);
		userConfig.setProperty(TaskConstants.BASE_FILE_NAME_KEY,TaskConstants.BASE_TASK_FILE_NAME_VALUE);
		userConfig.setProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY,TaskConstants.COUNT_PER_FILE_VALUE);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			userConfig.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
		
	}
	public static void setUserConfigFileName(String fileName)
	{
		Properties userConfig = new Properties();
		
		userConfig.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,TaskConstants.BASE_FILE_PATH_FOR_TASK_VALUE);
		userConfig.setProperty(TaskConstants.BASE_FILE_NAME_KEY,fileName);
		userConfig.setProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY,TaskConstants.COUNT_PER_FILE_VALUE);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			userConfig.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
		
	}
	
	public static void setUserConfigCount(String count)
	{
		Properties userConfig = new Properties();
		userConfig.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,TaskConstants.BASE_FILE_PATH_FOR_TASK_VALUE);
		userConfig.setProperty(TaskConstants.BASE_FILE_NAME_KEY,TaskConstants.BASE_TASK_FILE_NAME_VALUE);
		userConfig.setProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY,count);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			userConfig.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
		
	}
	public static void setUserConig(String pathName,String fileName,String count)
	{
		Properties userConfig = new Properties();
		userConfig.setProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY,pathName);
		userConfig.setProperty(TaskConstants.BASE_FILE_NAME_KEY,fileName);
		userConfig.setProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY,count);
		
		Logger LogObject = Logger.getInstance();
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(TaskConstants.CONFIGUREPATH));
			userConfig.store(bw,"configparams");
		} 
		catch (IOException e1) 
		{
			LogObject.log(e1.getMessage());
		}
		finally
		{
			if(bw!=null)
			{
				closeBufferedWritter(bw);
			}
		}
		
	}
	public static Properties getConfiguration()
	{
		Properties prop = new Properties();
		Logger LogObject = Logger.getInstance();
		
		try 
		{
			prop.load(new FileInputStream(TaskConstants.CONFIGUREPATH));
			
		} 
		catch (FileNotFoundException e) 
		{
			LogObject.log(e.getMessage());
		} 
		catch (IOException e)
		{
			LogObject.log(e.getMessage());
		}
		return prop;
	}
	
	public static int getNumberOfLine(String path)
	{
		BufferedReader br = null;
		Logger LogObject = Logger.getInstance();
		int count=0;
		try
		{
			br = new BufferedReader(new FileReader(path));
			while (br.readLine() != null)
			{
				count++;
			}
		}
		catch(IOException e)
		{
			LogObject.log(e.getMessage());
		}
		return count;
	}
	public static int fileCount(String path,String FileName) 
	{
		int fileCount = 0;
		try {
			
			File f = new File(path);
			if(f.exists() && f.isDirectory()) {
				File listOfFiles[] = f.listFiles();
				for(File eachLine : listOfFiles) {
					if(eachLine.getName().contains(FileName) && eachLine.getName().contains("txt") && eachLine.isFile()) 
					{
						fileCount++;
					}
				}
				
			}
			
		}
		catch(Exception e) 
		{
			Logger logger = Logger.getInstance();
			logger.log(e.getMessage());
		}
		return fileCount;
	}
	public static LinkedHashSet<TaskBean> listTaskInFile(LinkedHashSet<TaskBean> taskBeanList, String path) 
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(path));
			String oneLine;
			TaskBean tempTaskBean;
			String[] splittedWords;
			while ((oneLine = br.readLine()) != null)
			{
				splittedWords = oneLine.split(":");
				long time = Long.parseLong(splittedWords[2]);
				tempTaskBean = new TaskBean(splittedWords[0], splittedWords[1], time);
				taskBeanList.add(tempTaskBean);
			}

			return taskBeanList;
		} 
		catch (IOException e) 
		{
			Logger logger = Logger.getInstance();
			logger.log(e.getMessage());
		} 
		finally
		{
			closeBufferReader(br);
		}
		return null;
	}
	public static String searchTaskInFile(String path,String taskName) 
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(path));
			String oneLine;
			String[] splittedWords;
			while ((oneLine = br.readLine()) != null)
			{
				splittedWords = oneLine.split(":");
				if(splittedWords[0].equals(taskName))
				{
					return path;
				}
			}
			
			return null;
		} 
		catch (IOException e)
		{
			Logger logger = Logger.getInstance();
			logger.log(e.getMessage());
		}
		finally 
		{
			closeBufferReader(br);
		}
		return null;
	}
	public static boolean addParticulars(TaskBean tb,String path) {
		String finalPath;
		if (tb != null)
		{
			BufferedWriter bw = null;
			try 
			{
				bw = new BufferedWriter(new FileWriter(path, true));
				bw.write(tb.toString());
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
				closeBufferedWritter(bw);
			}
			return true;
		}
		return false;
	}
	public static TaskBean searchTaskInFileforDelete(String path,String taskName)
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			
			String oneLine;
			String[] splittedWords;
			while ((oneLine = br.readLine()) != null)
			{
				splittedWords = oneLine.split(":");
				
				if(splittedWords[0].equals(taskName))
				{
					long timeStamp = Long.parseLong(splittedWords[2]);
					
					return  new TaskBean(taskName, splittedWords[1], timeStamp);
				}
			}
			
			return null;
		} 
		catch (IOException e) 
		{
			Logger logger = Logger.getInstance();
			logger.log(e.getMessage());
		} 
		finally
		{
			closeBufferReader(br);
		}
		return null;
	}
	public static LinkedHashSet<TaskBean> listTaskInFileinDelete(LinkedHashSet<TaskBean> taskBeanList, String path)
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(path));
			String line;
			TaskBean currentTask;
			String[] split;
			while((line=br.readLine())!=null)
			{
				split = line.split(":");
				long time = Long.parseLong(split[2]);
				currentTask = new TaskBean(split[0], split[1], time);
				taskBeanList.add(currentTask);
			}
			return taskBeanList;
		}
		catch (IOException e) 
		{
			Logger logger = Logger.getInstance();
			logger.log(e.getMessage());
		} 
		finally
		{
			closeBufferReader(br);
		}
		return null;
	}
}
