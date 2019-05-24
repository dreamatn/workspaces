package com.hisun.BP;

public class BPB3940_AWA_3940 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String BV_CODE = " ";
    public short BV_CODE_NO = 0;
    public BPB3940_BR_INFO[] BR_INFO = new BPB3940_BR_INFO[30];
    public BPB3940_AWA_3940() {
        for (int i=0;i<30;i++) BR_INFO[i] = new BPB3940_BR_INFO();
    }
}
