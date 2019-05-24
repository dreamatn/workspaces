package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPRAPBL;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZUBB01 {
    boolean pgmRtn = false;
    String TBL_AITBB01 = "AITBB01";
    String WS_ERR_MSG = " ";
    int WS_PART_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    AIZUBB01_WS_BAL_INFO[] WS_BAL_INFO = new AIZUBB01_WS_BAL_INFO[20];
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPRAPBL BPRAPBL = new BPRAPBL();
    AIRBB01 AIRBB01 = new AIRBB01();
    SCCGWA SCCGWA;
    SCCBPCT SCCBPCT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICUBB01 AICUBB01;
    BPRAPBL BPRLAPBL;
    public AIZUBB01() {
        for (int i=0;i<20;i++) WS_BAL_INFO[i] = new AIZUBB01_WS_BAL_INFO();
    }
    public void MP(SCCGWA SCCGWA, AICUBB01 AICUBB01) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICUBB01 = AICUBB01;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZUBB01 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICUBB01.RC);
        BPRLAPBL = (BPRAPBL) AICUBB01.POINTER;
        IBS.init(SCCGWA, BPRAPBL);
        AICUBB01.REC_LEN = 2013;
        IBS.CLONE(SCCGWA, BPRLAPBL, BPRAPBL);
        CEP.TRC(SCCGWA, BPRAPBL);
    }
    public void B00_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_GET_PART_NO_PROC();
        if (pgmRtn) return;
        B020_GEN_AITBB01_PROC();
        if (pgmRtn) return;
    }
    public void B010_GET_PART_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRBB01);
        if (BPRAPBL.CNTR_TYPE.equalsIgnoreCase("CAAC") 
            || BPRAPBL.CNTR_TYPE.equalsIgnoreCase("CLDD") 
            || BPRAPBL.CNTR_TYPE.equalsIgnoreCase("MMDP")) {
            if (BPRAPBL.CNTR_TYPE.equalsIgnoreCase("CAAC")) {
                BPRAPBL.PART_NO = BPRAPBL.PART_NO + 1;
                AIRBB01.KEY.PART_NO = BPRAPBL.PART_NO;
            }
            if (BPRAPBL.CNTR_TYPE.equalsIgnoreCase("CLDD")) {
                BPRAPBL.PART_NO = BPRAPBL.PART_NO + 230;
                AIRBB01.KEY.PART_NO = BPRAPBL.PART_NO;
            }
            if (BPRAPBL.CNTR_TYPE.equalsIgnoreCase("MMDP")) {
                BPRAPBL.PART_NO = BPRAPBL.PART_NO + 100;
                AIRBB01.KEY.PART_NO = BPRAPBL.PART_NO;
            }
        } else {
            if (BPRAPBL.APP.equalsIgnoreCase("BP")) {
                AIRBB01.KEY.PART_NO = 85;
            }
            if (BPRAPBL.APP.equalsIgnoreCase("IB")) {
                AIRBB01.KEY.PART_NO = 90;
            }
            if (BPRAPBL.APP.equalsIgnoreCase("PN")) {
                AIRBB01.KEY.PART_NO = 95;
            }
            if (BPRAPBL.APP.equalsIgnoreCase("AI")) {
                AIRBB01.KEY.PART_NO = 100;
            }
        }
    }
    public void B020_GEN_AITBB01_PROC() throws IOException,SQLException,Exception {
        AIRBB01.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRBB01.APP = BPRAPBL.APP;
        AIRBB01.KEY.BRH_OLD = BPRAPBL.BRH_OLD;
        AIRBB01.BRH_NEW = BPRAPBL.BRH_NEW;
        AIRBB01.KEY.CNTR_TYPE = BPRAPBL.CNTR_TYPE;
        AIRBB01.PROD_TYPE = BPRAPBL.PROD_TYPE;
        AIRBB01.KEY.CCY = BPRAPBL.CCY;
