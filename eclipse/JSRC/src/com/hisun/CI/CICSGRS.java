package com.hisun.CI;

public class CICSGRS {
    public CICSGRS_RC RC = new CICSGRS_RC();
    public CICSGRS_DATA DATA = new CICSGRS_DATA();
    public CICSGRS_OUT_DATA[] OUT_DATA = new CICSGRS_OUT_DATA[99];
    public CICSGRS() {
        for (int i=0;i<99;i++) OUT_DATA[i] = new CICSGRS_OUT_DATA();
    }
}
