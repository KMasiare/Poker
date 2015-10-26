package poker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//14
// zamiast ifow zrobic mape!
public class PokerTable {
	public int play() {
		int firstPlayerScore = 0;
		File file = new File("C:\\Users\\KMASIARE\\Desktop\\poker\\poker\\src\\main\\java\\poker\\poker.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
		    for(String line; (line = br.readLine()) != null; ) {
		    	String firstHand = line.substring(0, 15);
		    	String secondHand = line.substring(15);
		    	Score firstScore = Handparser.parse(firstHand);
		    	Score secondScore = Handparser.parse(secondHand);
		    	if(firstScore.ordinal() > secondScore.ordinal()) {
		    		firstPlayerScore++;
		    	} else if(firstScore.ordinal() == secondScore.ordinal()) {
		    		if(Handparser.checkTheBestCard(firstHand) > Handparser.checkTheBestCard(secondHand)) {
		    			firstPlayerScore++;
		    		}
		    	}
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstPlayerScore;
	}
// 


}
