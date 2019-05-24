package com.hisun.BP;

public class BPCOFDER_INFO {
    public String DER_CODE = " ";
    public BPCOFDER_DATA[] DATA = new BPCOFDER_DATA[20];
    public BPCOFDER_INFO() {
        for (int i=0;i<20;i++) DATA[i] = new BPCOFDER_DATA();
    }
}
