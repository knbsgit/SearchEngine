package searchEngine;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import searchEngine.constants.TaskConstants;
import searchEngine.log.Logger;
import searchEngine.searchMenu.SearchMenu;
import searchEngine.util.UtillClass;

public class StartApp
{
	public static void main(String[] args) throws ParseException
	{
		String Path = "C:\\Users\\knbha\\OneDrive\\Desktop\\IO Programs";
		File file = new File(Path);
		SearchMenu.IndexFolder(file);
		UtillClass.setDefaultConfig();
		StartApp.displayMenu();
	}
	public static void displayMenu()
	{
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		Scanner sc4 = new Scanner(System.in);
		Scanner sc5 = new Scanner(System.in);
		Scanner sc6 = new Scanner(System.in);
		
		List<TaskBean> TaskSet = new ArrayList<TaskBean>();
		
		int choice1=0;
		
		Logger LogObject = Logger.getInstance();
		
		while(choice1!=4)
		{
			System.out.println("--------MAIN MENU--------");
			System.out.println("1.Manage Task.\n"
					+ "2.Search Content.\n"
					+ "3.Configure.\n"
					+ "4.Exit");
			System.out.println("Check to configuration before adding the task");
			System.out.println("Enter you choice:");
			choice1 = sc1.nextInt();
			
			switch(choice1)
			{
				case 1:
					int choice2=0;
					while(choice2!=6)
					{
						System.out.println("-----MANAGE TASK-----");
						System.out.println("1.Add task.\n"
								+ "2.Edit Task.\n"
								+ "3.List Tasks.\n"
								+ "4.Delete Task.\n"
								+ "5.Search Task.\n"
								+ "6.Back to main Menu.");
						System.out.println("Enter your choice:");
						choice2 = sc4.nextInt();
						
						switch(choice2)
						{
						case 1:
							System.out.println("Enter the Task Title:");
							String TaskName = sc2.nextLine();
							System.out.println("Enter Task Descpription:");
							String TaskDescription = sc3.nextLine();
							if(UtillClass.stringValidation(TaskName) && UtillClass.stringValidation(TaskDescription))
							{
								TaskBean task = new TaskBean(TaskName,TaskDescription);
								if(TaskService.AddTask(task))
									System.out.println("Task Created Successfully!");
								else
									System.out.println("Sorry for Inconvinence");
							}
							else
								throw new IllegalArgumentException("Task Name and Task Desc cannot be null");
							break;
							
						case 2:
							System.out.println("Enter Task Name:");
							String name = sc5.nextLine();
							System.out.println("Enter the Descprition:");
							String Desc = sc5.nextLine();
							TaskBean t = new TaskBean(name,Desc);
							System.out.println(TaskService.updateTask(name,Desc));
							break;
							
						case 3:
							int choice3=0;
							ArrayList<TaskBean> AlphaOrder = new ArrayList<>(TaskService.listTaskBasedOn());
							ArrayList<TaskBean> TimeOrder = new ArrayList<>(TaskService.listTaskBasedOn());
							try 
							{
								TaskService.listTask();
							} 
							catch (ParseException e1) 
							{
								e1.printStackTrace();
							}
							while(choice3!=3)
							{
								System.out.println("----List Task based on:----");
								System.out.println("1.Alphabetical wise.\n"
										+ "2.Created Date Wise.\n"
										+ "3.Back to Manage Task");
								System.out.println("Enter your choice:");
								choice3 = sc6.nextInt();
								
								switch(choice3)
								{
								case 1:
									AlphaOrder.sort(new AlphabeticComparator());
									for(TaskBean t1:AlphaOrder)
									{
										System.out.println(t1);
									}
									break;
								case 2:
									TimeOrder.sort(new TimeBasedComparator());
									for(TaskBean t1:TimeOrder)
									{
										System.out.println(t1);
									}
									break;
								}
							}
							break;
							
						case 4:
							System.out.println("Enter the Task Name:");
							String deletedName = sc5.nextLine();
								TaskService.deleteTask(deletedName);
							break;
							
						case 5:
							System.out.println("Enter task name to be searched:");
							String searchName = sc5.next();
							try 
							{
								if(TaskService.searchTask(searchName)!=null)
								{
									System.out.println(TaskService.searchTask(searchName));
								}
								else
								{
									System.out.println("No such tasks are there");
								}
							} 
							catch (ParseException e) 
							{
								// TODO Auto-generated catch block
								LogObject.log(e.getMessage());
							}
							
						case 6:
							break;
						}
					}
					break;
					
				case 2:
					System.out.println("Search Option");
					System.out.println("Enter the task name to be searched:");
					String key = sc2.nextLine();
					TreeMap<String,TreeSet> KeyIndex =  SearchMenu.index;
					System.out.println("Searching....");
					TreeSet<String> value = KeyIndex.get(key);
					for(String str: value)
					{
						System.out.println(str);
					}
					break;
					
				case 3:
					System.out.println("Configure File and Task");
					String defaultPath=(UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_PATH_NAME_KEY));
					String defaultFileName = (UtillClass.getConfiguration().getProperty(TaskConstants.BASE_FILE_NAME_KEY));
					String defaultCount = (UtillClass.getConfiguration().getProperty(TaskConstants.COUNT_PER_FILE_NAME_KEY));
					
					System.out.println("The Deafult configurations are:\n"
							+ "Path Name:"+defaultPath+"\n"
									+ "File Name:"+defaultFileName+"\n"
											+ "Count Per File:"+defaultCount+"\n");
					System.out.println();
					
					System.out.println("1.TO CHANGE THE CHANGE CONFIGURATIONS");
					System.out.println("2.CONTINUE");
					System.out.println("Enter you choice");
					int choice6;
					Scanner sc7 = new Scanner(System.in);
					Scanner sc8 = new Scanner(System.in);
					choice6 = sc7.nextInt();
					switch(choice6)
					{
					case 1:
						int choice7=0;
						while(choice7!=5)
						{
							System.out.println("1.To change File Path.");
							System.out.println("2.To change File Name.");
							System.out.println("3.To change Count Per File.");
							System.out.println("4.To change the whole Config.");
							System.out.println("<---Back");
							System.out.println("Enter your choice:");
							choice7=sc7.nextInt();
							switch(choice7)
							{
							case 1:
								System.out.println("Enter Path Name:");
								String userPath = sc8.nextLine();
								UtillClass.setUserConfigPathName(userPath);
								break;
							case 2:
								System.out.println("Enter File Name:");
								String userFileName = sc8.nextLine();
								UtillClass.setUserConfigFileName(userFileName);
								break;
							case 3:
								System.out.println("Enter count per File:");
								String userCOunt = sc8.nextLine();
								UtillClass.setUserConfigCount(userCOunt);
								break;
							case 4:
								System.out.println("Enter Path Name:");
								String Path = sc8.nextLine();
								System.out.println("Enter File Name:");
								String FileName = sc8.nextLine();
								System.out.println("Enter count per File:");
								String Count = sc8.nextLine();
								UtillClass.setUserConig(Path, FileName, Count);
							}	
						}
					case 2:
						break;
					}
					
					
				case 4:
					break;
			}
				
		}
	}
}
