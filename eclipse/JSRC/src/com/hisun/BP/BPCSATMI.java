package com.hisun.BP;

public class BPCSATMI {
    public BPCSATMI_RC RC = new BPCSATMI_RC();
    public String OUTPUT_FMT = " ";
    public char FUNC = ' ';
    public int FROM_BR = 0;
    public char FROM_TYP = ' ';
    public String FROM_TLR = " ";
    public int TO_BR = 0;
    public String TO_TLR = " ";
    public char TO_TYP = ' ';
    public char CS_KIND = ' ';
    public BPCSATMI_CCY_INFO[] CCY_INFO = new BPCSATMI_CCY_INFO[5];
    public BPCSATMI() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCSATMI_CCY_INFO();
    }
}
