package com.hisun.DD;

public class DDCOINFC_TIR_INF {
    public DDCOINFC_TIR_DATE[] TIR_DATE = new DDCOINFC_TIR_DATE[5];
    public DDCOINFC_TIR_INF() {
        for (int i=0;i<5;i++) TIR_DATE[i] = new DDCOINFC_TIR_DATE();
    }
}
