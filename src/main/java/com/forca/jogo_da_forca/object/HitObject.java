package com.forca.jogo_da_forca.object;


import com.forca.jogo_da_forca.models.Game;

import java.util.List;

public class HitObject {

    private Boolean hit = false;
    private List<Character> wrongChar;
    private List<String> wrongWord;
    private List<Integer>  index;
    private Boolean won = false;
    private Boolean lost = false;
    private Integer tentativas = 6;

    public List<String> getWrongWord() {
        return wrongWord;
    }

    public void setWrongWord(List<String> wrongWord) {
        this.wrongWord = wrongWord;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public List<Character> getWrongChar() {
        return wrongChar;
    }

    public void setWrongChar(List<Character> wrongChar) {
        this.wrongChar = wrongChar;
    }

    public List<Integer>  getIndex() {
        return index;
    }

    public void setIndex(List<Integer>  index) {
        this.index = index;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Boolean getLost() {
        return lost;
    }

    public void setLost(Boolean lost) {
        this.lost = lost;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public HitObject(Boolean hit, List<Character> wrongChar, List<String> wrongWord, List<Integer> index, Boolean won, Boolean lost, Integer tentativas) {
        this.hit = hit;
        this.wrongChar = wrongChar;
        this.wrongWord = wrongWord;
        this.index = index;
        this.won = won;
        this.lost = lost;
        this.tentativas = tentativas;
    }

    public HitObject(Boolean hit, List<Character> wrongChar, List<String> wrongWord, Boolean won, Boolean lost, Integer tentativas) {
        this.hit = hit;
        this.wrongChar = wrongChar;
        this.wrongWord = wrongWord;
        this.won = won;
        this.lost = lost;
        this.tentativas = tentativas;
    }
}
