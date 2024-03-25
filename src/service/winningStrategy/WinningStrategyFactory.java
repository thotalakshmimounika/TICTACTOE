package service.winningStrategy;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(WinningStrategyNames name, int dimension){
        return new OrderOneWinningStrategy(dimension);
    }
}
