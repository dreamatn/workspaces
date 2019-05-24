package com.hisun.BP;

public class BPZFSEXP_WS_EXPO_VAL {
    String EXPO_DER_DESC = " ";
    char FILLER12 = 0X02;
    BPZFSEXP_WS_EXPO_EXP_DATA[] WS_EXPO_EXP_DATA = new BPZFSEXP_WS_EXPO_EXP_DATA[50];
    int EXPO_EFF_DATE = 0;
    int EXPO_EXP_DATE = 0;
    public BPZFSEXP_WS_EXPO_VAL() {
        for (int i=0;i<50;i++) WS_EXPO_EXP_DATA[i] = new BPZFSEXP_WS_EXPO_EXP_DATA();
    }
}
