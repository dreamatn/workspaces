package com.hisun.PN;

public class PNCUGHTZ {
    public PNCUGHTZ_RC RC = new PNCUGHTZ_RC();
    public PNCUGHTZ_DRFT_ARRAY[] DRFT_ARRAY = new PNCUGHTZ_DRFT_ARRAY[25];
    public PNCUGHTZ() {
        for (int i=0;i<25;i++) DRFT_ARRAY[i] = new PNCUGHTZ_DRFT_ARRAY();
    }
}
