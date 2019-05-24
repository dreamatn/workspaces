package com.hisun.DD;

public class DDRARAH_TIR {
    public short TIR_TERM = 0;
    public char TIR_CYCLE = ' ';
    public DDRARAH_TIR_DATA[] TIR_DATA = new DDRARAH_TIR_DATA[5];
    public DDRARAH_TIR() {
        for (int i=0;i<5;i++) TIR_DATA[i] = new DDRARAH_TIR_DATA();
    }
}
