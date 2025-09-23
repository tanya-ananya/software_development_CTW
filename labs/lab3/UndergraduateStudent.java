public class UndergraduateStudent extends Student
{
    public static final double UNDERGRAD_TUITION = 4000;
	public static final String UNDGRAD_CLASSIFY = "Undergraduate";
    
	public UndergraduateStudent(int pID, String pName)
    {
		super(pID, pName);
        setTuition();
        setClassification();
    }
	
	// The implemented abstract methods defined in component Student.java
    public void setTuition()
    {
        tuition = UNDERGRAD_TUITION;
    }
    public void setClassification()
    {
        classification = UNDGRAD_CLASSIFY;
    }
}
