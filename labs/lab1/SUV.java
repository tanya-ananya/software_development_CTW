public class SUV extends Automobile 
{
	private int numPass;
	private double cargoSpc;
	
	public SUV(String mk, String md, int y, int nw, int np, double c) 
	{
		super(mk, md, y, nw);
		numPass = np;
		cargoSpc = c;
	}
	
	public void printInfo() 
	{
		super.printInfo();
		System.out.println("Number of Passengers: " + numPass);
		System.out.println("Cargo Space: " + cargoSpc);
	}
}
