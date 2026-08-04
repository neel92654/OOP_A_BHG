import java.util.Scanner;

public class VendingMachine{
	enum Coin {
		ONE,
		TWO,
		FIVE,
		TEN,
	}
	public static void main(String[] args) {
		int sum = 0;
		Scanner n = new Scanner(System.in);
		int snack = 15;
		System.out.println("Enter the coin amounts: ");
 		while (sum < 15) {
			String input = n.next().toUpperCase();
			Coin mon = Coin.valueOf(input);
			switch (mon) {
				case ONE :
					sum += 1;
					break;
				case TWO :
					sum += 2;
					break;
				case FIVE :
					sum +=5;
					break;
				case TEN :
					sum +=10;
					break;
		
			}		
		}
		System.out.println("Paid: " + sum + "Change: " + (sum - snack));
		n.close();
	}
}