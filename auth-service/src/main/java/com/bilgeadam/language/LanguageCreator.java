package com.bilgeadam.language;

public class LanguageCreator {

    public static Language language;


    public static ALanguage getLanguage(){
        switch (language){
            case TR: return new Tr();
            case EN: return new En();
            case FR: return new Fr();
            case DE: return new De();
            default: return new En();

        }
    }
}
