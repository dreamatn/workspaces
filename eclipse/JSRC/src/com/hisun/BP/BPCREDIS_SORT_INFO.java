package com.hisun.BP;

public class BPCREDIS_SORT_INFO {
    public String[] BRW_FLD = new String[3];
    public BPCREDIS_BRW_SRT_INFO[] BRW_SRT_INFO = new BPCREDIS_BRW_SRT_INFO[2];
    public BPCREDIS_SORT_INFO() {
        for (int i=0;i<2;i++) BRW_SRT_INFO[i] = new BPCREDIS_BRW_SRT_INFO();
    }
}
