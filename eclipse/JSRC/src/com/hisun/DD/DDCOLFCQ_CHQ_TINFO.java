package com.hisun.DD;

public class DDCOLFCQ_CHQ_TINFO {
    public short CHQ_TLINE = 0;
    public DDCOLFCQ_TCHQ[] TCHQ = new DDCOLFCQ_TCHQ[10];
    public DDCOLFCQ_CHQ_TINFO() {
        for (int i=0;i<10;i++) TCHQ[i] = new DDCOLFCQ_TCHQ();
    }
}
