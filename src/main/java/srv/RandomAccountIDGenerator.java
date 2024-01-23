package srv;

import java.util.Random;

public class RandomAccountIDGenerator {

	 private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final int ACCOUNT_ID_LENGTH = 6;

	    public static String generateRandomAccountID() {
	        StringBuilder accountId = new StringBuilder();
	        Random random = new Random();

	        for (int i = 0; i < ACCOUNT_ID_LENGTH; i++) {
	            int randomIndex = random.nextInt(CHARACTERS.length());
	            accountId.append(CHARACTERS.charAt(randomIndex));
	        }

	        return accountId.toString();
	    }

	   
}
