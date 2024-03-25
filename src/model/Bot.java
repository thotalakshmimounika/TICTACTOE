package model;

import constants.BotDifficultyLevel;
import constants.PlayerType;
import service.botWinningStrategy.BotPlayingStrategy;
import service.botWinningStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    public Bot(int id, char symbol,  BotDifficultyLevel botDifficultyLevel) {
        super(id,"CHITTI" , symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    private BotDifficultyLevel botDifficultyLevel;
    public Move makeMove(Board board){

        return BotPlayingStrategyFactory.getBotPlayingStrategy().makeMove(board,this);
    }

}
