package com.hisun.BP;

public class BPB5140_AWA_5140 {
    public String EXR_TYPE = " ";
    public short EXR_TYPE_NO = 0;
    public int STR_DATE = 0;
    public short STR_DATE_NO = 0;
    public int END_DATE = 0;
    public short END_DATE_NO = 0;
    public BPB5140_QTP_DATA[] QTP_DATA = new BPB5140_QTP_DATA[30];
    public BPB5140_AWA_5140() {
        for (int i=0;i<30;i++) QTP_DATA[i] = new BPB5140_QTP_DATA();
    }
}
