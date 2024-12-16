package com.ngdat.duoihinhbatchu;


public class IDButton {
    private int idAnswer, idPick;

    public IDButton(int idPick, int idAnswer) {
        this.idAnswer = idAnswer;
        this.idPick = idPick;
    }

    public int getIdPick() {
        return idPick;
    }

    public int getIdAnswer() {
        return idAnswer;
    }
}
