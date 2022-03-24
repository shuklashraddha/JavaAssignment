	public class Library extends example1{
	String lname;
	public Library(String x1, String x2)
	{
	super(x1);       //passing argument to parent class constructor
	this.lname = x2;
	}
	public void display()
	{
	System.out.println(super.lname+" and "+lname);
	}
	public static void main(String[] args)
	{
	Library c = new Library("library name","library id");
	c.display();
	}
	}

