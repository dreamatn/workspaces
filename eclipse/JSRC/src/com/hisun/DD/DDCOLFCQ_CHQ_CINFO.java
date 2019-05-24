package com.hisun.DD;

public class DDCOLFCQ_CHQ_CINFO {
    public short CHQ_CLINE = 0;
    public DDCOLFCQ_CCHQ[] CCHQ = new DDCOLFCQ_CCHQ[10];
    public DDCOLFCQ_CHQ_CINFO() {
        for (int i=0;i<10;i++) CCHQ[i] = new DDCOLFCQ_CCHQ();
    }
}
