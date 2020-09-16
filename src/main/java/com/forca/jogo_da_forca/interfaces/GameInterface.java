package com.forca.jogo_da_forca.interfaces;

import com.forca.jogo_da_forca.models.Palavra;
import com.forca.jogo_da_forca.object.CharObject;
import com.forca.jogo_da_forca.object.HitObject;
import com.forca.jogo_da_forca.object.StringObject;

public interface GameInterface {
        public Integer startGame(Palavra palavra);
        public HitObject tryHit(CharObject c);
        public HitObject tryWord(StringObject palavra);
}
