package com.hisun.BP;

public class BPCSFCLO {
    public BPCSFCLO_RC RC = new BPCSFCLO_RC();
    public char RETURN_INFO = ' ';
    public char FUNC = ' ';
    public String CMMT_NO = " ";
    public String AC = " ";
    public int PROC_DT = 0;
    public BPCSFCLO_CLT_LOOP[] CLT_LOOP = new BPCSFCLO_CLT_LOOP[5];
    public BPCSFCLO() {
        for (int i=0;i<5;i++) CLT_LOOP[i] = new BPCSFCLO_CLT_LOOP();
    }
}
