package iut.gon.sae201.g12.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Team {
    BLACK("\\e[47m"),
    WHITE("\\e[0;37m");

    private String color;



    public String getColor() {
        return color;
    }

    public Team other(){
        return (this == WHITE ? BLACK : WHITE);
    }
}
