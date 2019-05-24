package com.hisun.BA;

public class BACUUUSE_COMM_DATA {
    public String ID_NO = " ";
    public String ENCR = " ";
    public String RSN = " ";
    public short FEE_CNT = 0;
    public double KHZ_AMT = 0;
    public BACUUUSE_FEE_ARRAY[] FEE_ARRAY = new BACUUUSE_FEE_ARRAY[5];
    public BACUUUSE_COMM_DATA() {
        for (int i=0;i<5;i++) FEE_ARRAY[i] = new BACUUUSE_FEE_ARRAY();
    }
}
