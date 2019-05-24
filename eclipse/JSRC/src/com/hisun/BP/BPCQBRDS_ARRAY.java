package com.hisun.BP;

public class BPCQBRDS_ARRAY {
    public String TYP = " ";
    public short CURSOR_NUM = 0;
    public short TOT_NUM = 0;
    public BPCQBRDS_BRW_KEY_INFO[] BRW_KEY_INFO = new BPCQBRDS_BRW_KEY_INFO[3];
    public BPCQBRDS_ARRAY() {
        for (int i=0;i<3;i++) BRW_KEY_INFO[i] = new BPCQBRDS_BRW_KEY_INFO();
    }
}
