package com.hisun.TD;

public class TDCOITP {
    public String SMK = " ";
    public char END_FLG = ' ';
    public TDCOITP_DATA[] DATA = new TDCOITP_DATA[11];
    public TDCOITP() {
        for (int i=0;i<11;i++) DATA[i] = new TDCOITP_DATA();
    }
}
