package searchEngine.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import searchEngine.util.UtillClass;

public class Logger 
{
	private String path = "C:\\Users\\knbha\\OneDrive\\Desktop\\SearchEngineProject\\Logger.txt";
	
	private boolean devMode = true;
	
	public void log(String data)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(path,true));
			Date dt = new Date();
			bw.write(dt.toString()+":"+data);
			bw.newLine();
			if(devMode)
				System.out.println(data);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				UtillClass.closeBufferedWritter(bw);
			}
		}
	}
	private Logger()
	{
		
	}
	private static Logger obj = null;
	
	public static Logger getInstance()
	{
		if(obj == null)
			obj = new Logger();
		
		return obj;
	}
}
