package com.hisun.BP;

public class BPC4892 {
    public String TSTS_APP = " ";
    public String TSTS_NO = " ";
    public String TABLE_NM = " ";
    public String PB_TYPE = " ";
    public BPC4892_FLD_ARY[] FLD_ARY = new BPC4892_FLD_ARY[33];
    public String CHNL = " ";
    public BPC4892() {
        for (int i=0;i<33;i++) FLD_ARY[i] = new BPC4892_FLD_ARY();
    }
}
