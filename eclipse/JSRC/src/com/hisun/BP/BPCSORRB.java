package com.hisun.BP;

public class BPCSORRB {
    public char FUNC = ' ';
    public String BNK = " ";
    public BPCSORRB_INFO[] INFO = new BPCSORRB_INFO[50];
    public BPCSORRB() {
        for (int i=0;i<50;i++) INFO[i] = new BPCSORRB_INFO();
    }
}
