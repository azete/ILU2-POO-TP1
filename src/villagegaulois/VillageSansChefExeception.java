package villagegaulois;

public class VillageSansChefExeception extends Exception {

	public VillageSansChefExeception() {
	}
	public VillageSansChefExeception(String message) {
		super(message);
	}
	public VillageSansChefExeception(Throwable cause) {
		super(cause);
	}
	public VillageSansChefExeception(String message, Throwable cause) {
		super(message,cause);
	}
}
