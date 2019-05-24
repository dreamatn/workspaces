package com.hisun.CI;

public class CICO3080 {
    public CICO3080_DTL[] DTL = new CICO3080_DTL[4];
    public CICO3080_VAL_INFO[] VAL_INFO = new CICO3080_VAL_INFO[50];
    public char END_FLG = ' ';
    public CICO3080() {
        for (int i=0;i<4;i++) DTL[i] = new CICO3080_DTL();
        for (int i=0;i<50;i++) VAL_INFO[i] = new CICO3080_VAL_INFO();
    }
}
