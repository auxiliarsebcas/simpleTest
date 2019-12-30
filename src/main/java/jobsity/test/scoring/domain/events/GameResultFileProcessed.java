package jobsity.test.scoring.domain.events;

public class GameResultFileProcessed implements Event {
	//TODO it is not just a messages...
	public final String message;

	public GameResultFileProcessed(String message){
		this.message = message;
	}
}
