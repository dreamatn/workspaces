package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQITM;
import com.hisun.BP.BPCIFHIS;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRCHNTB;
import com.hisun.BP.BPRFHIST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class SLOT3002 {
    boolean pgmRtn = false;
    String K_ITM_BOOK_FLG = "BK002";
    int WS_MAX_ROWS = 500;
    int K_MAX_ROW = 25;
    int K_MAX_COL = 350;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_ROWS_CNT = 0;
    SLOT3002_WS_ACNO WS_ACNO = new SLOT3002_WS_ACNO();
    char WS_AC_FLG = ' ';
    char WS_FHIS_FLG = ' ';
    char WS_NULL_FLG = ' ';
    char WS_PROP_TYP = ' ';
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SLCOTX SLCOTX = new SLCOTX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    AICPQITM AICPQITM = new AICPQITM();
    SLRAC SLRAC = new SLRAC();
    BPRFHIST BPRFHIST = new BPRFHIST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLB0001_AWA_0001 SLB0001_AWA_0001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLOT3002 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SLB0001_AWA_0001>");
        SLB0001_AWA_0001 = (SLB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLRAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.BR == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_BR_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.CCY.trim().length() == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CCY_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.ITM.trim().length() == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_ITM_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.ITM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = K_ITM_BOOK_FLG;
            AICPQITM.INPUT_DATA.NO = SLB0001_AWA_0001.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            WS_PROP_TYP = AICPQITM.OUTPUT_DATA.SL_FLG;
            if ((WS_PROP_TYP != '1' 
                && WS_PROP_TYP != 'THRU' 
                && WS_PROP_TYP != '4')) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INVALID_PTY;
                WS_FLD_NO = SLB0001_AWA_0001.ITM_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (WS_PROP_TYP == '2') {
                if (SLB0001_AWA_0001.CI_NO.trim().length() == 0) {
                    WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CINO_MUST_INPUT;
                    WS_FLD_NO = SLB0001_AWA_0001.CI_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else {
                if (SLB0001_AWA_0001.PROP_CD == 0) {
                    WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_PCD_MUST_INPUT;
                    WS_FLD_NO = SLB0001_AWA_0001.PROP_CD_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        if (SLB0001_AWA_0001.STR_DT == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_DT_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.END_DT == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_DT_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.MIN_AMT == 0) {
            WS_NULL_FLG = '1';
        }
        if (SLB0001_AWA_0001.MAX_AMT == 0) {
            WS_NULL_FLG = '2';
        }
        if (SLB0001_AWA_0001.MIN_AMT == 0 
            && SLB0001_AWA_0001.MAX_AMT == 0) {
            WS_NULL_FLG = '3';
        }
        if (SLB0001_AWA_0001.STR_DT == SLB0001_AWA_0001.END_DT) {
            if (WS_NULL_FLG == '1') {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_NULL_MIN_AMT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (WS_NULL_FLG == '2') {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_NULL_MAX_AMT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        } else {
            if (SLB0001_AWA_0001.MIN_AMT != 0 
                || SLB0001_AWA_0001.MAX_AMT != 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CANNOT_INQ_PERIOD;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        SLRAC.KEY.BR = SLB0001_AWA_0001.BR;
        SLRAC.KEY.CCY = SLB0001_AWA_0001.CCY;
        SLRAC.KEY.ITM = SLB0001_AWA_0001.ITM;
        if (WS_PROP_TYP == '2') {
            SLRAC.CI_NO = SLB0001_AWA_0001.CI_NO;
        } else {
            SLRAC.KEY.PROP_CD = SLB0001_AWA_0001.PROP_CD;
        }
        T000_READ_SLTAC();
        if (pgmRtn) return;
        if (WS_AC_FLG == 'Y') {
            WS_ACNO.WS_ACNO_BR = SLRAC.KEY.BR;
            WS_ACNO.WS_ACNO_CCY = SLRAC.KEY.CCY;
            WS_ACNO.WS_ACNO_ITM = SLRAC.KEY.ITM;
            WS_ACNO.WS_ACNO_PRP_CD = SLRAC.KEY.PROP_CD;
            CEP.TRC(SCCGWA, WS_ACNO);
        }
        R000_STARTBR_BPTFHIST();
        if (pgmRtn) return;
        R000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        if (WS_FHIS_FLG == 'Y') {
            B021_01_OUT_TITLE();
            if (pgmRtn) return;
            if (BPRFHIST.KEY.AC_DT > SLB0001_AWA_0001.END_DT) {
                WS_FHIS_FLG = 'N';
            }
        }
        while (WS_FHIS_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (WS_NULL_FLG == '3' 
                || (SLB0001_AWA_0001.MIN_AMT <= BPRFHIST.TX_AMT 
                && BPRFHIST.TX_AMT <= SLB0001_AWA_0001.MAX_AMT)) {
                B022_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            R000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        R000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B021_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCOTX);
        SLCOTX.DATA.ACNO = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        SLCOTX.DATA.AC_NAME = SLRAC.AC_NAME;
        SLCOTX.DATA.AC_DT = BPRFHIST.KEY.AC_DT;
        SLCOTX.DATA.DRCRFLG = BPRFHIST.DRCRFLG;
        SLCOTX.DATA.CCY = BPRFHIST.TX_CCY;
        SLCOTX.DATA.AMT = BPRFHIST.TX_AMT;
        CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
