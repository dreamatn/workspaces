package com.hisun.BP;

public class BPB4890_AWA_4890 {
    public String TSTS_APP = " ";
    public short TSTS_APP_NO = 0;
    public String TSTS_NO = " ";
    public short TSTS_NO_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public String PB_TYPE = " ";
    public short PB_TYPE_NO = 0;
    public String TABLE_NM = " ";
    public short TABLE_NM_NO = 0;
    public short FLD_CNT = 0;
    public short FLD_CNT_NO = 0;
    public BPB4890_FLD_ARY[] FLD_ARY = new BPB4890_FLD_ARY[40];
    public BPB4890_AWA_4890() {
        for (int i=0;i<40;i++) FLD_ARY[i] = new BPB4890_FLD_ARY();
    }
}
