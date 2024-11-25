package gameLogic.boardGames;

public interface BoardGameObserver<T> {
    void update(T element);
}
