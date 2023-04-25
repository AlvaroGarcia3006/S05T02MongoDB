package AlvaroGarcia.S05T02MongoDB.model;

import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Player {
    @BsonId
    private ObjectId id;
    @BsonProperty("name")
    private String name;
    @BsonProperty("registration_date")
    private LocalDateTime registrationDate;

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
