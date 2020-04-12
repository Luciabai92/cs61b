public class TestBody{
	public static void main (String[] args){
		TestBody();
	}

	private static void TestBody(){
		Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");

        double b1Tob2 = b1.calcForceExertedBy(b2);
        double b2Tob1 = b2.calcForceExertedBy(b1);

        System.out.println("Body 1 to 2 " + b1Tob2);
        System.out.println("Body 2 to 1 " + b2Tob1);

	}
}