package com.hisun.BP;

public class BPB1115_AWA_1115 {
    public String DER_CODE = " ";
    public short DER_CODE_NO = 0;
    public BPB1115_FEE_INFO[] FEE_INFO = new BPB1115_FEE_INFO[20];
    public BPB1115_AWA_1115() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPB1115_FEE_INFO();
    }
}
