package com.hisun.DC;

public class DCCF902_OUT_HEAD {
    public short O_TOTAL_NUM = 0;
    public DCCF902_OUT_INFO[] OUT_INFO = new DCCF902_OUT_INFO[8];
    public DCCF902_OUT_HEAD() {
        for (int i=0;i<8;i++) OUT_INFO[i] = new DCCF902_OUT_INFO();
    }
}
