package com.hisun.BP;

public class BPCDNCSO {
    public int FROM_BR = 0;
    public String FROM_TLR = " ";
    public String TO_TLR = " ";
    public int CCY_CNT = 0;
    public int DT_CNT = 0;
    public BPCDNCSO_CCY_INFO[] CCY_INFO = new BPCDNCSO_CCY_INFO[5];
    public BPCDNCSO_DT_INFO[] DT_INFO = new BPCDNCSO_DT_INFO[60];
    public BPCDNCSO() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCDNCSO_CCY_INFO();
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCDNCSO_DT_INFO();
    }
}
