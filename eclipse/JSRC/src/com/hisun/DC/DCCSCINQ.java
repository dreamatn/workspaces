package com.hisun.DC;

public class DCCSCINQ {
    public String CARD_NO0 = " ";
    public String HLDR_ID_TYPE = " ";
    public String HLDR_ID_NO = " ";
    public String HLDR_NAME = " ";
    public String CARD_STS = " ";
    public DCCSCINQ_CARD_INFO[] CARD_INFO = new DCCSCINQ_CARD_INFO[1000];
    public DCCSCINQ() {
        for (int i=0;i<1000;i++) CARD_INFO[i] = new DCCSCINQ_CARD_INFO();
    }
}
