package iut.gon.sae201.g12.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Team {
    BLACK("\\e[47m"),
    WHITE("\\e[0;37m");

    private String color;

    Team(String color) {
        this.color = color;
    }

    Team() {
    }

    public Team other(){
        return (this == WHITE ? BLACK : WHITE);
    }
}
