import java.sql.*;
import java.util.Scanner;

public class StudentRecordSystem
{
	public static void main(String[] args)
	{
		System.out.println(
			"Project Title: Student Record System\n"+
			"Name : Ajay Lingayat\n"+
			"MVCEC ID : MVCEC-CCSP19-SII/06"
		);
		Index();
	}

	static int roll_nam;
	static String name;
	static int std;
	static String address;

	static final String jdbc_class = "org.apache.derby.jdbc.ClientDriver";
	static final String driver = "jdbc:derby://localhost:1527/students";

	static Connection conn = null;

	public static void Index()
	{

		System.out.println(
			"Welcome To Student Record System!\n"+
			"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
		);
		System.out.println(
			"Choose ur action:\n"+
			"1 : View Students\n"+
			"2 : Add a Student Record\n"+
			"3 : Update Student Record\n"+
			"4 : Delete Student Record\n"+
			"0 : Exit\n"
		);

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a choice:\n");
		int task_id = sc.nextInt();

		switch(num)
		{
			case 4:
				delete_student();
			case 3:
				update_student();
			case 2:
				add_student();
			case 1:
				view_students();
			case 0:
				System.out.println(
					"Thnx for using Student Record System"
				);
				break;
		}
	}

	public static void view_students()
	{
		try{

			Class.forName("org.apache.derby.jdbc.ClientDriver");
	        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/students");

	        System.out.println(
	        	"Following are the student details:"
	        );
	        statement st = conn.createStatement();

	        String raw_query = "SELECT * FROM StudentInfo";
	        ResultSet result = st.executeQuery(raw_query);
	        ResultSetMetaDate result_md = result.getMetaData();

	        System.out.println(
	        	result_md.getColumnName(1)+
	        	"\t\t"+
	        	result_md.getColumnName(2)+
	        	"\t\t\t"+
	        	result_md.getColumnName(3)+
	        	"\t\t"+
	        	result_md.getColumnName(4)+
	        	"\n\n"
	        );

	        while(result.next())
	        {
	        	System.out.println(
	        		result.getString("ROLL_NO")+
	        		"\t\t"+
	        		result.getString("NAME")+
	        		"\t\t"+
	        		result.getString("STANDARD")+
	        		"\t\t"+
	        		result.getString("ADDRESS")
	        	);
	        }
	        System.out.println("\n");
	        conn.close();
	        Index();
		}
		catch(Exception e)
		{
			System.out.println(
				"Error:"+e.getMessage()+
				"Invalid input, please try again!"
			);
			Index();
		}
	}

	public static void add_student()
	{
		try
		{
			int roll_no;
			String name = null;
			int std;
			String address = null;

			Scanner sc1 = new Scanner(System.in);

			Class.forName("org.apache.derby.jdbc.ClientDriver");
	        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/students");
	        statement st = conn.createStatement();

	        System.out.println(
	        	"TO ADD STUDENT RECORD:\n"+
	        	"~~~~~~~~~~~~~~~~~~~~~\n"+
	       		"\nEnter Roll No.:"
	       	);
	       	roll_no = sc1.nextInt();

	       	System.out.println(
	       		"Enter Name:"
	       	);
	       	name = sc1.next();

	       	System.out.println(
	       		"Enter Standard:"
	       	);
	       	std = sc1.nextInt();

	       	System.out.println(
	       		"Enter Address:"
	       	);
	       	address = sc1.next();

	       	String raw_query = "INSERT INTO StudentInfo values("+roll_no+",'"+name+"',"+std+",'"+address+"')";
	       	
	       	st.executeUpdate(raw_query);
	       	System.out.println(
	       		"Record saved successfully1"
	       	);
	       	conn.close();
	       	Index();

		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(
				"Error:"+e.getMessage()+
				"Invalid input, please try again!"
			);
			Index();
		}
	}

	public static void update_student()
	{
		try
		{
			int roll_no;
			String name = null;
			int std;
			String address = null;
			int num;

			Class.forName("org.apache.derby.jdbc.ClientDriver");
	        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/students");
	        statement st = conn.createStatement();

	        System.out.println(
	        	"TO UPDATE STUDENT RECORD:\n"+
	        	"~~~~~~~~~~~~~~~~~~~~~~~~~\n"+
	        );

			Scanner sc1 = new Scanner(System.in);

			try
			{

				System.out.println(
					"Enter roll no to view student record which u want to update"
				);
				num = sc1.nextInt();

				String raw_query = "SELECT * FROM StudentInfo WWHERE ROLL_NO = "+num;
				st.execute(raw_query);
				ResultSet result = st.executeQuery(raw_query);
				ResultSetMetaData result_md = result.getMetaData();

				System.out.println(
					"\nShowing the student record;\n"+
					result_md.getColumnName(1)+
					"\t\t"+
					result_md.getColumnName(2)+
					"\t\t"+
					result_md.getColumnName(3)+
					"\t\t"+
					result_md.getColumnName(4)+
					"\n\n"
				);

				while(result.next())
				{
					System.out.println(
						result.getString("ROLL_NO")+
		        		"\t\t"+
		        		result.getString("NAME")+
		        		"\t\t"+
		        		result.getString("STANDARD")+
		        		"\t\t"+
		        		result.getString("ADDRESS")						
					);
				}
				System.out.println('\n');
			}
			catch(Exception e)
			{
				System.out.println(
					"Error:"+e.getMessage()+
					"Invalid input, please try again!"
				);
				Index();
			}

			System.out.println(
				"Enter new details:\n\n"+
				"Enter Roll No.:"
			);
			roll_no = sc1.nextInt();

			System.out.println(
	       		"Enter Name:"
	       	);
	       	name = sc1.next();

	       	System.out.println(
	       		"Enter Standard:"
	       	);
	       	std = sc1.nextInt();

	       	System.out.println(
	       		"Enter Address:"
	       	);
	       	address = sc1.next();

	       	String raw_query = "UPDATE StudentInfo SET NAME='"+name+"', STANDARD="+std+", ADDRESS='"+address+"' WHERE ROLL_NO="+roll_no;

	       	st.executeUpdate(raw_query);
	       	System.out.println(
	       		"Student Record Updated Successfully!"
	       	);
	       	conn.close();
	       	Index();

		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(
				"Error:"+e.getMessage()+
				"Invalid input, please try again!"
			);
			Index();	
		}
	}

	public static void delete_student()
	{
		try
		{
			int roll_no;

			Scanner sc1 = new Scanner(System.in);

			Class.forName("org.apache.derby.jdbc.ClientDriver");
	        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/students");
	        statement st = conn.createStatement();

	        System.out.println(
	        	"TO DELETE STUDENT RECORD\n"+
	        	"~~~~~~~~~~~~~~~~~~~~~~~~\n"+
	        	"\nEnter Roll No.:"
	        );
	        roll_no = sc1.nextInt();

	        String raw_query = "DELETE FROM StudentInfo WHERE ROLL_NO="+roll_no;

	        st.executeUpdate(raw_query);
	        System.out.println(
	        	"Student Record Deleted Successfully!"
	        );
	        conn.commit();
	        conn.close();
	        Index();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(
				"Error:"+e.getMessage()+
				"Invalid input, please try again!"
			);
			Index();
		}
	}

}