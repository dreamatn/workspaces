package com.hisun.BP;

public class BPB8362_AWA_8362 {
    public String TRCODE = " ";
    public short TRCODE_NO = 0;
    public int ALLNUM = 0;
    public short ALLNUM_NO = 0;
    public int CURRNUM = 0;
    public short CURRNUM_NO = 0;
    public BPB8362_CCY_INFO[] CCY_INFO = new BPB8362_CCY_INFO[20];
    public BPB8362_AWA_8362() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPB8362_CCY_INFO();
    }
}
