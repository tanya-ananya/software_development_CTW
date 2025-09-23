public abstract class Student
{
    private int ID;
    private String lastName;
    protected double tuition;
	protected String classification;
	
    public Student(int pID, String pName)
    {
		ID = pID;
        lastName = pName;
    }
    public void setId(int idNum)
    {
        ID = idNum;
    }
    public void setLastName(String pName)
    {
        lastName = pName;
    }
    public int getId()
    {
        return ID;
    }
    public String getLastName()
    {
        return lastName;
    }
    public double getTuition()
    {
        return tuition;
    }
    public String getClassification()
    {
        return classification;
    }
	// no concrete code for abstract methods
    public abstract void setTuition();
	public abstract void setClassification();
}