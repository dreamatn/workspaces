package com.hisun.TD;

public class TDCUQINF_OUTPUT_DATA {
    public TDCUQINF_PAGE_INF PAGE_INF = new TDCUQINF_PAGE_INF();
    public TDCUQINF_INFO_DATA[] INFO_DATA = new TDCUQINF_INFO_DATA[25];
    public TDCUQINF_OUTPUT_DATA() {
        for (int i=0;i<25;i++) INFO_DATA[i] = new TDCUQINF_INFO_DATA();
    }
}
