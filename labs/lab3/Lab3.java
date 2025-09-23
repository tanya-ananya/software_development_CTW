public class Lab3
{
    public static void main(String[] args)
    {
        Student students[] = new Student[2];
        int i;
        students[0] = new UndergraduateStudent(111, "Lambert");
        students[1] = new UndergraduateStudent(122, "Lembeck");
		System.out.println("\n\n\n\nUndergraduate Students:");
		
        for(i = 0; i < students.length; ++i) 
		{
			System.out.println("Student ID: " +
			                    students[i].getId() + ", Name: " +
			                    students[i].getLastName() + ", Tuition: " +
			                    students[i].getTuition() + " per year, " +
			                    "Student Class is: " + students[i].getClassification());
		}
		
		System.out.println("\n\n\nGraduate Students:");

		students = new Student[2];
        students[0] = new GraduateStudent(222, "Snoopy");
        students[1] = new GraduateStudent(333, "Shaggy");
		
        for(Student x : students) 
		{
			System.out.println("Student ID: " +
			                    x.getId() + ", Name: " +
			                    x.getLastName() + ", Tuition: " +
			                    x.getTuition() + " per year, " +
			                    "Student Class is: " + x.getClassification());
		}	
		
		System.out.println("\n\n\nProgrammer is: Taaruni Ananya\n\n\n\n");
    }
}
