package com.hisun.BP;

public class BPCPQPBL_TEXT {
    public short CNT = 0;
    public BPCPQPBL_DATA[] DATA = new BPCPQPBL_DATA[60];
    public BPCPQPBL_TEXT() {
        for (int i=0;i<60;i++) DATA[i] = new BPCPQPBL_DATA();
    }
}
