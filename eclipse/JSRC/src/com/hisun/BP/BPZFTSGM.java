package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTSGM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFTSGM";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_R_STARTBR_TLT = "BP-R-STARTBR-TLT    ";
    String K_OUTPUT_FMT = "BP561";
    String K_HIS_REMARKS = "TELLER COMPEL SIGN OFF INFO  ";
    BPZFTSGM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFTSGM_WS_TEMP_VARIABLE();
    BPZFTSGM_WS_OUT_DATA WS_OUT_DATA = new BPZFTSGM_WS_OUT_DATA();
    char WS_TBL_FLAG = ' ';
    char WS_SIGN_STS = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPRTLT BPRTLT = new BPRTLT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCFTSGM BPCFTSGM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTSGM BPCFTSGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTSGM = BPCFTSGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTSGM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFTSGM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_DISPOSE_SIGN_ON_TLR();
        if (pgmRtn) return;
        B200_DISPOSE_TEMP_SIGN_OFF_TLR();
        if (pgmRtn) return;
        B300_OUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_DISPOSE_SIGN_ON_TLR() throws IOException,SQLException,Exception {
        WS_SIGN_STS = 'O';
        WS_TBL_FLAG = 'Y';
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        while (WS_TBL_FLAG != 'N') {
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFTSGM.TLR_BR);
            CEP.TRC(SCCGWA, WS_SIGN_STS);
            if (WS_TBL_FLAG == 'Y') {
                if ((BPRTLT.TLR_TYP == 'T' 
                    && BPRTLT.SIGN_STS == WS_SIGN_STS)) {
                    WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                    CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
                    if (WS_TEMP_VARIABLE.WS_CNT < 51) {
                        CEP.TRC(SCCGWA, "20140601");
                        WS_OUT_DATA.WS_OUT_ARRARY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_TLR = BPRTLT.KEY.TLR;
                    } else {
                    }
                    B040_REWRITE_PROCESS();
                    if (pgmRtn) return;
                    B041_HISTORY_PROCESS();
                    if (pgmRtn) return;
                }
            }
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_DISPOSE_TEMP_SIGN_OFF_TLR() throws IOException,SQLException,Exception {
        WS_SIGN_STS = 'T';
        WS_TBL_FLAG = 'Y';
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                if ((BPRTLT.TLR_TYP == 'T' 
                    && BPRTLT.SIGN_STS == WS_SIGN_STS)) {
                    WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                    CEP.TRC(SCCGWA, BPCFTSGM.TLR_BR);
                    CEP.TRC(SCCGWA, WS_SIGN_STS);
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                    CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
                    if (WS_TEMP_VARIABLE.WS_CNT < 51) {
                        CEP.TRC(SCCGWA, "20140602");
                        WS_OUT_DATA.WS_OUT_ARRARY[WS_TEMP_VARIABLE.WS_CNT-1].WS_OUT_TLR = BPRTLT.KEY.TLR;
                    } else {
                        CEP.TRC(SCCGWA, "20140603");
                    }
                    B040_REWRITE_PROCESS();
                    if (pgmRtn) return;
                    B041_HISTORY_PROCESS();
                    if (pgmRtn) return;
                }
            }
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.NEW_BR = BPCFTSGM.TLR_BR;
        BPRTLT.SIGN_STS = WS_SIGN_STS;
        BPCRTLTB.INFO.FUNC = 'U';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTB.INFO.FUNC = 'N';
        BPRTLT.TLR_BR = BPCFTSGM.TLR_BR;
        BPRTLT.SIGN_STS = WS_SIGN_STS;
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20140613");
        if (BPCRTLTB.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "Y");
            WS_TBL_FLAG = 'Y';
        } else if (BPCRTLTB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "N");
            WS_TBL_FLAG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR, BPCFTSGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "20140614");
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTB.INFO.FUNC = 'E';
        BPRTLT.TLR_BR = BPCFTSGM.TLR_BR;
        BPRTLT.SIGN_STS = WS_SIGN_STS;
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR, BPCFTSGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.FUNC = 'M';
        BPRTLT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRTLT.NEW_BR = BPCFTSGM.TLR_BR;
        BPRTLT.SIGN_STS = WS_SIGN_STS;
        BPRTLT.SIGN_STS = 'C';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRTLTB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR, BPCFTSGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B041_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.REF_NO = BPRTLT.KEY.TLR;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B300_OUT_PROCESS() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_OUT_CNT = WS_TEMP_VARIABLE.WS_CNT;
        WS_OUT_DATA.WS_OUT_BRANCH = BPCFTSGM.TLR_BR;
        WS_OUT_DATA.WS_OUT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUT_DATA.WS_OUT_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 425;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTSGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLT, BPCRTLTB);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTSGM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTSGM = ");
            CEP.TRC(SCCGWA, BPCFTSGM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
