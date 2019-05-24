package com.hisun.BP;

public class BPB2911_AWA_2911 {
    public int BR = 0;
    public short BR_NO = 0;
    public int APP_BR = 0;
    public short APP_BR_NO = 0;
    public int APP_DT = 0;
    public short APP_DT_NO = 0;
    public String APP_NOTE = " ";
    public short APP_NOTE_NO = 0;
    public BPB2911_BV_INFO[] BV_INFO = new BPB2911_BV_INFO[10];
    public char APP_TYPE = ' ';
    public short APP_TYPE_NO = 0;
    public BPB2911_AWA_2911() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPB2911_BV_INFO();
    }
}
