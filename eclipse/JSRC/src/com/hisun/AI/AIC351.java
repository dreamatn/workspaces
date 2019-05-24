package com.hisun.AI;

public class AIC351 {
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public AIC351_DATA[] DATA = new AIC351_DATA[30];
    public AIC351() {
        for (int i=0;i<30;i++) DATA[i] = new AIC351_DATA();
    }
}
