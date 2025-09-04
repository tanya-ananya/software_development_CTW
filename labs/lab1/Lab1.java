public class Lab1 
{
    public static void main( String[] args )
    {
        Automobile a = new Automobile( "Porsche", "911 ST", 2025, 4 );
        a.printInfo();
        
		SUV s = new SUV( "Subaru", "Trek", 2025, 4, 5, 6.7 );
        s.printInfo();
    }
}
