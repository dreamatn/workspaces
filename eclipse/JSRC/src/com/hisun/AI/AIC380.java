package com.hisun.AI;

public class AIC380 {
    public int TOD_REC_NUM = 0;
    public String END_POS = " ";
    public char END_FLG = ' ';
    public AIC380_DATA[] DATA = new AIC380_DATA[30];
    public AIC380() {
        for (int i=0;i<30;i++) DATA[i] = new AIC380_DATA();
    }
}
