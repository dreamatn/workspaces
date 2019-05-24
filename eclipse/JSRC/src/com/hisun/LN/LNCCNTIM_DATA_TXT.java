package com.hisun.LN;

public class LNCCNTIM_DATA_TXT {
    public LNCCNTIM_CTNR_DATA[] CTNR_DATA = new LNCCNTIM_CTNR_DATA[10];
    public LNCCNTIM_DATA_TXT() {
        for (int i=0;i<10;i++) CTNR_DATA[i] = new LNCCNTIM_CTNR_DATA();
    }
}
