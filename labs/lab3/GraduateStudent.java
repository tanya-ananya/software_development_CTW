public class GraduateStudent extends Student
{
    public static final double GRAD_TUITION = 6000;
	public static final String GRAD_CLASSIFY = "Graduate";
    
    public GraduateStudent(int pID, String pName)
    {
        super(pID, pName);
        setTuition();
        setClassification();
    }
	
	// You must implemente abstract methods from Student.java component.
    public void setTuition()
    {
        tuition = GRAD_TUITION;
    }
    public void setClassification()
    {
        classification = GRAD_CLASSIFY;
    }
}
