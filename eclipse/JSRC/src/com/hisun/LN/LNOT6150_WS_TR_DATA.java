package com.hisun.LN;

public class LNOT6150_WS_TR_DATA {
    char WS_TR_DATA_FUNC = ' ';
    char WS_TR_DATA_FLG = ' ';
    LNOT6150_WS_TR_DATA_ARRAY[] WS_TR_DATA_ARRAY = new LNOT6150_WS_TR_DATA_ARRAY[5];
    public LNOT6150_WS_TR_DATA() {
        for (int i=0;i<5;i++) WS_TR_DATA_ARRAY[i] = new LNOT6150_WS_TR_DATA_ARRAY();
    }
}
