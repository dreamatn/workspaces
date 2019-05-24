package com.hisun.DD;

public class DDCOQBLL {
    public int NUMBER = 0;
    public DDCOQBLL_OUT_DATA[] OUT_DATA = new DDCOQBLL_OUT_DATA[5];
    public DDCOQBLL() {
        for (int i=0;i<5;i++) OUT_DATA[i] = new DDCOQBLL_OUT_DATA();
    }
}
