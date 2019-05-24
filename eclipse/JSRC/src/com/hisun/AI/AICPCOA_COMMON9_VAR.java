package com.hisun.AI;

public class AICPCOA_COMMON9_VAR {
    public AICPCOA_COA_DATA9 COA_DATA9 = new AICPCOA_COA_DATA9();
    public AICPCOA_BKPM_DATA9[] BKPM_DATA9 = new AICPCOA_BKPM_DATA9[10];
    public char ITM_LEN9_FLG = ' ';
    public AICPCOA_COUNTER9 COUNTER9 = new AICPCOA_COUNTER9();
    public AICPCOA_COMMON9_VAR() {
        for (int i=0;i<10;i++) BKPM_DATA9[i] = new AICPCOA_BKPM_DATA9();
    }
}
