package com.hisun.LN;

public class LNCIAMT {
    public LNCIAMT_RC RC = new LNCIAMT_RC();
    public String CTA_NO = " ";
    public short AMT_TYP = 0;
    public int START_DT = 0;
    public int END_DT = 0;
    public String CCY = " ";
    public LNCIAMT_OUTPUT[] OUTPUT = new LNCIAMT_OUTPUT[1000];
    public LNCIAMT() {
        for (int i=0;i<1000;i++) OUTPUT[i] = new LNCIAMT_OUTPUT();
    }
}
