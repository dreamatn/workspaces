package com.hisun.BA;

public class BAZUQPAY_WS_DB_VARS {
    int START_NUM = 0;
    int CNT = 0;
    char AMT_STS = ' ';
    char BILL_STS = ' ';
    int ACPT_BR = 0;
    String DRWR_AC = " ";
    double REM_AMT = 0;
    String CNTR_NO = " ";
    BAZUQPAY_WS_BR[] WS_BR = new BAZUQPAY_WS_BR[1000];
    public BAZUQPAY_WS_DB_VARS() {
        for (int i=0;i<1000;i++) WS_BR[i] = new BAZUQPAY_WS_BR();
    }
}
