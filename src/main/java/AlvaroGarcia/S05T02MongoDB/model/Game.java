package AlvaroGarcia.S05T02MongoDB.model;

import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

public class Game {
    @BsonId
    private ObjectId id;
    @BsonProperty("player")
    private Player player;
    @BsonProperty("dice_1")
    private Integer dice1;
    @BsonProperty("dice_2")
    private Integer dice2;
    @BsonProperty("score")
    private Integer score;

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Integer getDice1() {
        return dice1;
    }
    public void setDice1(Integer dice1) {
        this.dice1 = dice1;
    }
    public Integer getDice2() {
        return dice2;
    }
    public void setDice2(Integer dice2) {
        this.dice2 = dice2;
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
}
