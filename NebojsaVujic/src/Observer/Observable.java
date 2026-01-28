package Observer;

public interface Observable {
	void addObservers(Observer o);
	void removeObservers(Observer o);
	void notifyObservers();
	
}
