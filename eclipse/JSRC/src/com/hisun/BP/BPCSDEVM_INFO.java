package com.hisun.BP;

public class BPCSDEVM_INFO {
    public char FUNC = ' ';
    public Object POINTER;
    public char TYPE = ' ';
    public int BR = 0;
    public String CCY = " ";
    public String BASE_TYP = " ";
    public String TENOR = " ";
    public char FMT = ' ';
    public BPCSDEVM_DATA[] DATA = new BPCSDEVM_DATA[10];
    public BPCSDEVM_INFO() {
        for (int i=0;i<10;i++) DATA[i] = new BPCSDEVM_DATA();
    }
}
