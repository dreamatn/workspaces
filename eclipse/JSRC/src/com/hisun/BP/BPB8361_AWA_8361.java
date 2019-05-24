package com.hisun.BP;

public class BPB8361_AWA_8361 {
    public short ALLNUM = 0;
    public short ALLNUM_NO = 0;
    public short CURRNUM = 0;
    public short CURRNUM_NO = 0;
    public BPB8361_CCY_INFO[] CCY_INFO = new BPB8361_CCY_INFO[20];
    public BPB8361_AWA_8361() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPB8361_CCY_INFO();
    }
}
