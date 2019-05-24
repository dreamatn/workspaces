package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICMSG_ERROR_MSG;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class BPZSPVBR {
    boolean pgmRtn = false;
    String K_CMP_CALL_SCZKDGAC = "SC-AC-DIGIT    ";
    String K_OUTPUT_FMT = "BPA04";
    short K_PAGE_ROWS = 15;
    String K_AC_TIT = "95508";
    String CPN_CI_CIZACCU_CN = "CI-INQ-ACCU";
    String CPN_CI_CIZCUST_CN = "CI-INQ-CUST";
    String WS_ERR_MSG = " ";
    short WS_INP_NUM = 0;
    BPZSPVBR_WS_AC_NO_ALL WS_AC_NO_ALL = new BPZSPVBR_WS_AC_NO_ALL();
    String WS_SEQ_TYPE = "ACSEQ";
    String WS_SEQ_NAME = " ";
    char WS_BROWSE_COND_FLG = ' ';
    char WS_BROWSE_CONTINUE = ' ';
    BPZSPVBR_WS_DATA WS_DATA = new BPZSPVBR_WS_DATA();
    char WS_FRZ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRACOBL BPRACOBL = new BPRACOBL();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPROBL BPROBL = new BPROBL();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCRAOBL BPCRAOBL = new BPCRAOBL();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    char WS_AC_FLG = ' ';
    char WS_TX_TYPE = ' ';
    String WS_AC_NO = " ";
    String WS_CI_NO = " ";
    long WS_ACNO_SEQ = 0;
    char WS_USED_FLG = ' ';
    int WS_MAKE_BR = 0;
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    String WS_REMARK = " ";
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    String WS_ACO_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCRCWAT SCRCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSPVBR BPCSPVBR;
    public void MP(SCCGWA SCCGWA, BPCSPVBR BPCSPVBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPVBR = BPCSPVBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPVBR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPRFHIS1);
        IBS.init(SCCGWA, BPRFHIS2);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSPVBR.RC);
        CEP.TRC(SCCGWA, BPCSPVBR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_BROWSE_OBL_AC();
        if (pgmRtn) return;
    }
    public void S000_CHECK_RETURN_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPVBR.RC);
        if (BPCSPVBR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSPVBR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSPVBR.RC);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_BROWSE_OBL_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPVBR);
        R000_WRITE_PAGE_TITLE();
        if (pgmRtn) return;
        WS_TX_TYPE = BPCSPVBR.DATA2.TX_TYPE.charAt(0);
        CEP.TRC(SCCGWA, WS_TX_TYPE);
        WS_STR_DT = BPCSPVBR.DATA1.STR_DT;
        WS_END_DT = BPCSPVBR.DATA1.END_DT;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = BPCSPVBR.DATA2.D_AC;
        CEP.TRC(SCCGWA, BPCSPVBR.DATA2.D_AC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, WS_ACO_AC);
        if ((WS_STR_DT == SCCGWA.COMM_AREA.AC_DATE) 
            && (WS_END_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "READ DAY TBALE ONLY");
            B040_INTR_INFO1();
            if (pgmRtn) return;
        } else {
            if ((WS_END_DT == SCCGWA.COMM_AREA.AC_DATE) 
                && (WS_STR_DT < SCCGWA.COMM_AREA.AC_DATE)) {
                CEP.TRC(SCCGWA, "READ TWO TABLES");
                B040_INTR_INFO2();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "READ HIST TABLE ONLY");
                B040_INTR_INFO3();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_WRITE_PAGE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 371;
        SCCMPAG.SCR_ROW_CNT = K_PAGE_ROWS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        T000_STARTBR_ACOAC();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (SCRCWA.JRN_IN_USE == '1') {
                B060_01_DATA_TRANS_TO_FMT1();
                if (pgmRtn) return;
            } else {
                B060_01_DATA_TRANS_TO_FMT2();
                if (pgmRtn) return;
            }
            R000_WRITE_PAGE_RECORD();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        T000_STARTBR_ACOAC3();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            R000_WRITE_PAGE_RECORD();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        T000_STARTBR_ACOAC3();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            R000_WRITE_PAGE_RECORD();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
        T000_STARTBR_ACOAC();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (SCRCWA.JRN_IN_USE == '1') {
                B060_01_DATA_TRANS_TO_FMT1();
                if (pgmRtn) return;
            } else {
                B060_01_DATA_TRANS_TO_FMT2();
                if (pgmRtn) return;
            }
            R000_WRITE_PAGE_RECORD();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT1() throws IOException,SQLException,Exception {
        BPCSPVBR.DATA2.TX_DT = BPRFHIS1.KEY.AC_DT;
        CEP.TRC(SCCGWA, BPRFHIS1.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPCSPVBR.DATA2.TX_DT);
        BPCSPVBR.DATA2.JRNNO = BPRFHIS1.KEY.JRNNO;
        CEP.TRC(SCCGWA, BPRFHIS1.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPCSPVBR.DATA2.JRNNO);
        BPCSPVBR.DATA2.PVE_TYPE = BPCSPVBR.DATA2.PVE_TYPE;
        BPCSPVBR.DATA2.TX_TYPE = "" + BPRFHIS1.PV_T_TYP;
        JIBS_tmp_int = BPCSPVBR.DATA2.TX_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCSPVBR.DATA2.TX_TYPE = "0" + BPCSPVBR.DATA2.TX_TYPE;
        BPCSPVBR.DATA2.D_AC = BPRFHIS1.KEY.AC;
        BPCSPVBR.DATA2.C_AC = BPRFHIS1.RLT_AC;
