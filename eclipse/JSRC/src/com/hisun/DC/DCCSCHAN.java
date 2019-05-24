package com.hisun.DC;

public class DCCSCHAN {
    public String CARD_NO = " ";
    public char CHNL_SCOPE = ' ';
    public DCCSCHAN_CHNL_INFO[] CHNL_INFO = new DCCSCHAN_CHNL_INFO[100];
    public DCCSCHAN_RC RC = new DCCSCHAN_RC();
    public DCCSCHAN() {
        for (int i=0;i<100;i++) CHNL_INFO[i] = new DCCSCHAN_CHNL_INFO();
    }
}
