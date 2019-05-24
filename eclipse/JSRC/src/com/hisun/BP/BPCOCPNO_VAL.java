package com.hisun.BP;

public class BPCOCPNO_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPCOCPNO_FEE_PARM[] FEE_PARM = new BPCOCPNO_FEE_PARM[20];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOCPNO_VAL() {
        for (int i=0;i<20;i++) FEE_PARM[i] = new BPCOCPNO_FEE_PARM();
    }
}
