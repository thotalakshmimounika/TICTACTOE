package model;

import constants.BotDifficultyLevel;
import constants.PlayerType;

public class Bot extends Player{
    public Bot(int id, String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(id,"CHITTI" , symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    private BotDifficultyLevel botDifficultyLevel;
    public Move makeMove(Board board){
        return null;
    }

}
