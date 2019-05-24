package com.hisun.BP;

public class BPC4896 {
    public String TSTS_APP = " ";
    public String TSTS_NO = " ";
    public String TABLE_NM = " ";
    public String PB_TYPE = " ";
    public BPC4896_FLD_ARY[] FLD_ARY = new BPC4896_FLD_ARY[10];
    public BPC4896() {
        for (int i=0;i<10;i++) FLD_ARY[i] = new BPC4896_FLD_ARY();
    }
}
