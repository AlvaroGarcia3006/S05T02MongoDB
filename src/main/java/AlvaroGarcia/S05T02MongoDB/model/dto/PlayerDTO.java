package AlvaroGarcia.S05T02MongoDB.model.dto;

import java.time.LocalDateTime;

public class PlayerDTO {
    private String id;
    private String name;
    private LocalDateTime registrationDate;
    private Float winRate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public Float getWinRate() {
        return winRate;
    }
    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }
}
