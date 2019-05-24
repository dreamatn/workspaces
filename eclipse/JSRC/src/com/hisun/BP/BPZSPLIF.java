package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPLIF {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_CASH_SUSPENSE = "BP-S-CASH-SUSPENSE  ";
    String CPN_R_MAINT_CLBI = "BP-R-ADW-CLBI";
    int K_MAX_PAR_CNT = 12;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_PAR_CNT = 0;
    double WS_SUSP_AMT = 0;
    double WS_SUSP_NO_S_AMT = 0;
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCOPLIF BPCOPLIF = new BPCOPLIF();
    BPCTLBIF BPCTLBIF = new BPCTLBIF();
    BPCSCSSP BPCSCSSP = new BPCSCSSP();
    SCCGWA SCCGWA;
    BPCSPLIF BPCSPLIF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSPLIF BPCSPLIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPLIF = BPCSPLIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSPLIF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSPLIF.IN_FLAG == 'D') {
            B010_UPDATE_IN_PVAL_INFO();
        }
        if (BPCSPLIF.OT_FLAG == 'C') {
            B020_UPDATE_OUT_PVAL_INFO();
        }
        B030_SUSPENSE_PROF();
        B040_TRANS_DATA_OUTPUT();
    }
    public void B010_UPDATE_IN_PVAL_INFO() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            if (BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_PVAL > 0 
                && BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM > 0) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                BPRCLBI.KEY.VB_BR = BPCSPLIF.BR;
                BPRCLBI.KEY.PLBOX_NO = BPCSPLIF.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSPLIF.IN_CASH_TYPE;
                BPRCLBI.KEY.CCY = BPCSPLIF.IN_CCY;
                WS_CS_KIND = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_CS_KIND;
                BPRCLBI.KEY.PAR_VAL = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_PVAL;
                BPRCLBI.KEY.M_FLG = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_M_FLG;
                BPCTLBIF.INFO.FUNC = 'R';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                    IBS.init(SCCGWA, BPCTLBIF);
                    IBS.init(SCCGWA, BPRCLBI);
                    BPRCLBI.KEY.VB_BR = BPCSPLIF.BR;
                    BPRCLBI.KEY.PLBOX_NO = BPCSPLIF.PLBOX_NO;
                    BPRCLBI.KEY.CASH_TYP = BPCSPLIF.IN_CASH_TYPE;
                    BPRCLBI.KEY.CCY = BPCSPLIF.IN_CCY;
                    BPRCLBI.KEY.PAR_VAL = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_PVAL;
                    BPRCLBI.KEY.M_FLG = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_M_FLG;
                    if (WS_CS_KIND == '0') {
                        BPRCLBI.GD_NUM = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (WS_CS_KIND == '2') {
                        BPRCLBI.BD_NUM = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (WS_CS_KIND == '3') {
                        BPRCLBI.HBD_NUM = BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else {
                    }
                    BPCTLBIF.INFO.FUNC = 'A';
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    S000_CALL_BPZTLBIF();
                } else {
                    if (WS_CS_KIND == '0') {
                        BPRCLBI.GD_NUM = BPRCLBI.GD_NUM + BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (WS_CS_KIND == '2') {
                        BPRCLBI.BD_NUM = BPRCLBI.BD_NUM + BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (WS_CS_KIND == '3') {
                        BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM + BPCSPLIF.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else {
                    }
                    BPCTLBIF.INFO.FUNC = 'U';
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    IBS.init(SCCGWA, BPCTLBIF.RC);
                    S000_CALL_BPZTLBIF();
                }
            }
        }
    }
    public void B020_UPDATE_OUT_PVAL_INFO() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            if (BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_PVAL > 0 
                && BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM > 0) {
                IBS.init(SCCGWA, BPRCLBI);
                IBS.init(SCCGWA, BPCTLBIF);
                BPRCLBI.KEY.VB_BR = BPCSPLIF.BR;
                BPRCLBI.KEY.PLBOX_NO = BPCSPLIF.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPCSPLIF.OT_CASH_TYPE;
                BPRCLBI.KEY.CCY = BPCSPLIF.OT_CCY;
                WS_CS_KIND = BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_CS_KIND;
                BPRCLBI.KEY.PAR_VAL = BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_PVAL;
                BPRCLBI.KEY.M_FLG = BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_M_FLG;
                BPCTLBIF.INFO.FUNC = 'R';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                    S000_ERR_MSG_PROC();
                } else {
                    if (WS_CS_KIND == '0' 
                        && BPRCLBI.GD_NUM < BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM 
                        || WS_CS_KIND == '2' 
                        && BPRCLBI.BD_NUM < BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM 
                        || WS_CS_KIND == '3' 
                        && BPRCLBI.HBD_NUM < BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                        S000_ERR_MSG_PROC();
                    } else {
                        if (WS_CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM - BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM;
                        } else if (WS_CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM - BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM;
                        } else if (WS_CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM - BPCSPLIF.OT_PVAL_INFO[WS_CNT-1].OT_NUM;
                        } else {
                        }
                        BPCTLBIF.INFO.FUNC = 'U';
                        BPCTLBIF.POINTER = BPRCLBI;
                        BPCTLBIF.LEN = 222;
                        IBS.init(SCCGWA, BPCTLBIF.RC);
                        S000_CALL_BPZTLBIF();
                    }
                }
            }
        }
    }
    public void B030_SUSPENSE_PROF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCSSP);
        if (BPCSPLIF.IN_CCY.equalsIgnoreCase(BPCSPLIF.OT_CCY)) {
            WS_SUSP_AMT = BPCSPLIF.IN_S_AMT - BPCSPLIF.OT_S_AMT;
            BPCSCSSP.CASH_TYP = BPCSPLIF.IN_CASH_TYPE;
            BPCSCSSP.CCY = BPCSPLIF.IN_CCY;
        } else if (BPCSPLIF.IN_FLAG == 'D' 
                && BPCSPLIF.IN_S_AMT != 0) {
            WS_SUSP_AMT = BPCSPLIF.IN_S_AMT;
            BPCSCSSP.CASH_TYP = BPCSPLIF.IN_CASH_TYPE;
            BPCSCSSP.CCY = BPCSPLIF.IN_CCY;
        } else if (BPCSPLIF.OT_FLAG == 'C' 
                && BPCSPLIF.OT_S_AMT != 0) {
            WS_SUSP_AMT = WS_SUSP_AMT - BPCSPLIF.OT_S_AMT;
            BPCSCSSP.CASH_TYP = BPCSPLIF.OT_CASH_TYPE;
            BPCSCSSP.CCY = BPCSPLIF.OT_CCY;
        }
        if (WS_SUSP_AMT != 0) {
            WS_SUSP_NO_S_AMT = WS_SUSP_AMT;
            BPCSCSSP.TOTAL_AMT = WS_SUSP_NO_S_AMT;
            BPCSCSSP.BOX_FLG = '3';
            if (WS_SUSP_AMT > 0) {
                BPCSCSSP.ML_OPT = '1';
            } else {
                BPCSCSSP.ML_OPT = '0';
            }
            BPCSCSSP.CS_KIND = '0';
            BPCSCSSP.SUSP_TYPE = '3';
            BPCSCSSP.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZSCSSP();
        }
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSPLIF.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPLIF;
        SCCFMT.DATA_LEN = 791;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPLIF);
        BPCOPLIF.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOPLIF.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOPLIF.TR_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOPLIF.PLBOX_TYPE = BPCSPLIF.PLBOX_TYPE;
        BPCOPLIF.PLBOX_NO = BPCSPLIF.PLBOX_NO;
        BPCOPLIF.IN_FLAG = BPCSPLIF.IN_FLAG;
        BPCOPLIF.IN_CCY = BPCSPLIF.IN_CCY;
        BPCOPLIF.IN_D_AMT = BPCSPLIF.IN_D_AMT;
        BPCOPLIF.IN_R_AMT = BPCSPLIF.IN_R_AMT;
        BPCOPLIF.IN_S_AMT = BPCSPLIF.IN_S_AMT;
        BPCOPLIF.IN_CASH_TYPE = BPCSPLIF.IN_CASH_TYPE;
        BPCOPLIF.OT_FLAG = BPCSPLIF.OT_FLAG;
        BPCOPLIF.OT_CCY = BPCSPLIF.OT_CCY;
        BPCOPLIF.OT_D_AMT = BPCSPLIF.OT_D_AMT;
        BPCOPLIF.OT_R_AMT = BPCSPLIF.OT_R_AMT;
        BPCOPLIF.OT_S_AMT = BPCSPLIF.OT_S_AMT;
        BPCOPLIF.OT_CASH_TYPE = BPCSPLIF.OT_CASH_TYPE;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            BPCOPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_CS_KIND = BPCSPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_CS_KIND;
            BPCOPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_PVAL = BPCSPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_PVAL;
            BPCOPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_NUM = BPCSPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_NUM;
            BPCOPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_M_FLG = BPCSPLIF.IN_PVAL_INFO[WS_PAR_CNT-1].IN_M_FLG;
            BPCOPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_CS_KIND = BPCSPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_CS_KIND;
            BPCOPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_PVAL = BPCSPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_PVAL;
            BPCOPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_NUM = BPCSPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_NUM;
            BPCOPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_M_FLG = BPCSPLIF.OT_PVAL_INFO[WS_PAR_CNT-1].OT_M_FLG;
        }
    }
    public void S000_CALL_BPZSCSSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_SUSPENSE, BPCSCSSP);
    }
    public void S000_CALL_BPZTLBIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLBI, BPCTLBIF);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
        if (BPCTLBIF.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
