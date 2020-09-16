package com.forca.jogo_da_forca.models;

import com.forca.jogo_da_forca.interfaces.GameInterface;
import com.forca.jogo_da_forca.object.CharObject;
import com.forca.jogo_da_forca.object.HitObject;
import com.forca.jogo_da_forca.object.StringObject;
import org.apache.logging.log4j.util.Chars;

import java.util.*;
import java.util.stream.Collectors;

public class Game implements GameInterface {

    private static Game single_instance = null;

    private String palavraSrt;
    private Map<Integer, Character> palavra = new HashMap<Integer, Character>();
    private Integer tentativas = 6;
    private List<Character> wrongChar = new ArrayList<Character>();
    private List<String> wrongWord = new ArrayList<String>();

    public List<String> getWrongWord() {
        return wrongWord;
    }

    public void setWrongWord(List<String> wrongWord) {
        this.wrongWord = wrongWord;
    }


    public Map<Integer, Character> getPalavra() {
        return palavra;
    }

    public String getPalavraSrt() {
        return palavraSrt;
    }

    public void setPalavraSrt(String palavraSrt) {
        this.palavraSrt = palavraSrt;
    }


    public void setPalavra(Map<Integer, Character> palavra) {
        this.palavra = palavra;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public List<Character> getWrongChar() {
        return wrongChar;
    }

    public void setWrongChar(List<Character> wrongChar) {
        this.wrongChar = wrongChar;
    }

    public Game() {

    }


    //GAME IS A SINGLETON --------------------------------------------------------
    public static Game getInstance() {
        if (single_instance == null)
            single_instance = new Game();
        return single_instance;
    }

    @Override //START GAME PLACE WORD AND RETURNS THE LENGTH OF WORD CHOSEN ----------------------
    public Integer startGame(Palavra palavra) {
        this.palavra = new HashMap<Integer, Character>();
        this.tentativas = 6;
        this.wrongChar = new ArrayList<Character>();
        this.wrongWord = new ArrayList<String>();
        for (Integer i = 0; i < palavra.getPalavra_col().length(); i++) {
            this.palavra.put(i, palavra.getPalavra_col().toLowerCase().charAt(i));
        }
        this.palavraSrt = palavra.getPalavra_col().toLowerCase();
        return this.palavra.size();
    }

    //TRY FOR A SINGLE CHARACTER, RETURNS A HITOBJECT WHICH IS A GAME STATE UPDATOR----------------------------------------------
    @Override
    public HitObject tryHit(CharObject c) {
        Character ca = c.getCar().toLowerCase().charAt(0);
        if (wrongChar != null && wrongChar.contains(ca))
            return new HitObject(false, getWrongChar(), getWrongWord(), false, false, getTentativas());
        else if (palavra.containsValue(ca)) {
            List<Integer> index = makeIndex(ca);
            return new HitObject(true, getWrongChar(), getWrongWord(), index, palavra.isEmpty(), false, getTentativas());
        }
        wrongChar.add(ca);
        return new HitObject(false, getWrongChar(), getWrongWord(), false, checkLost(), getTentativas());
    }

    //TRY FOR THE WORD, RETURNS HIT OBJECT -----------------------------------------------------------------------
    @Override
    public HitObject tryWord(StringObject palavra) {
        if (wrongWord != null && wrongWord.contains(palavra.getPalavra().toLowerCase()))
            return new HitObject(false, getWrongChar(), getWrongWord(), false, false, getTentativas());
        if (!palavra.getPalavra().toLowerCase().equals(this.palavraSrt)) {
            wrongWord.add(palavra.getPalavra().toLowerCase());
            return new HitObject(false, getWrongChar(), getWrongWord(), false, checkLost(), getTentativas());
        }
        return new HitObject(false, getWrongChar(), getWrongWord(), true, false, getTentativas());
    }


    //CHECKS THE TRIES AND DICREMENTS, RETURN BOOLEAN IF THE GAME IS LOST ----------------------------------------
    public Boolean checkLost() {
        if ((this.tentativas - 1) > 0) {
            this.tentativas -= 1;
            return false;
        }
        return true;
    }

    //ADDS TO A LIST THE THE CHARACTHERS THAT ARE RIGHT -----------------------------------------
    public List<Integer> makeIndex(Character c) {
        List<Integer> list = new ArrayList<Integer>();
        while (palavra.containsValue(c)) {
            list.add(getKey(palavra, c));
            palavra.remove(getKey(palavra, c));
        }
        return list;
    }


    //GETS KEY FROM A MAP VALUE --------------------------------------
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}

