package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCMZR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String F_MAINTAIN_CMZR = "BP-F-R-MAINTAIN-CMZR";
    String F_MAINTAIN_ESIP = "BP-F-R-MAINTAIN-ESIP";
    String OUTPUT_FMT = "BP058";
    char CMZ_FLG_BIZ = '4';
    char CMZ_FLG_CARD = '3';
    char CMZ_FLG_AC = '2';
    char CMZ_FLG_CUST = '1';
    char ENTI_FLG_ALL = '0';
    String DD_M_DDZIMMST = "DD-I-NFIN-M-MST";
    String CI_STS_TBL = "CIST";
    String REC_NHIS = "BP-REC-NHIS";
    String CALL_DCZUSPAC = "DC-INQ-STD-AC";
    String HIS_COPYBOOK = "BPRCMZR";
    BPZSCMZR_WS_VARIABLES WS_VARIABLES = new BPZSCMZR_WS_VARIABLES();
    BPZSCMZR_WS_OUTPUT WS_OUTPUT = new BPZSCMZR_WS_OUTPUT();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRCMZR BPCRCMZR = new BPCRCMZR();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPCOCMZR BPCOCMZR = new BPCOCMZR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    BPCQCMZR BPCQCMZR = new BPCQCMZR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSCMZR BPCSCMZR;
    public void MP(SCCGWA SCCGWA, BPCSCMZR BPCSCMZR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCMZR = BPCSCMZR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCMZR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPRCMZR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (BPCSCMZR.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCMZR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCMZR.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCMZR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCMZR.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (BPCSCMZR.FUNC == 'A' 
            && BPCSCMZR.CMZ_FLG == '2') {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPCSCMZR.CMZ_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            if ((DDCIMMST.DATA.AC_STS != 'N' 
                && DDCIMMST.DATA.AC_STS != 'D') 
                && DDCIMMST.RC.RC_CODE == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.AC_STATUS_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCIMMST.RC.RC_CODE != 0) {
                B013_CHECK_SPAC_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (BPCSCMZR.FUNC == 'A' 
            && BPCSCMZR.CMZ_FLG == '1') {
            if (BPCSCMZR.CI_NO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_SGN_CI_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPCSCMZR.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
            }
        }
    }
    public void B013_CHECK_SPAC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUSPAC);
        DCCUSPAC.FUNC.AC = BPCSCMZR.CMZ_AC;
        S000_CALL_DCZUSPAC();
        if (pgmRtn) return;
        if (DCCUSPAC.OUTPUT.STD_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = DCCUSPAC.OUTPUT.STD_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            if (DDCIMMST.RC.RC_CODE != 0) {
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((DDCIMMST.DATA.AC_STS != 'N' 
                && DDCIMMST.DATA.AC_STS != 'D') 
                && DDCIMMST.RC.RC_CODE == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.AC_STATUS_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B011_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        R000_TRANS_BRW_QUERY_DATA_PAR();
        if (pgmRtn) return;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        R000_BEGIN_MPAGE_OUTPUT1();
        if (pgmRtn) return;
        B070_START_BROWSE_Q();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDD");
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_BIZ);
        IBS.init(SCCGWA, BPCQCMZR);
        while (BPCRCMZR.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && (WS_VARIABLES.CNT_Q <= 19)) {
            WS_VARIABLES.CNT_Q += 1;
            CEP.TRC(SCCGWA, "AAA");
            CEP.TRC(SCCGWA, WS_VARIABLES.CNT_Q);
            R000_TRANS_DATA_OUTPUT_BRW_Q();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].FEE_CODE);
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        B090_END_BROWSE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT_Q);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_BIZ);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCQCMZR;
        SCCFMT.DATA_LEN = 1403;
        IBS.FMT(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, SCCMPAG);
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCMZR.CMZ_FLG == CMZ_FLG_CUST 
            || BPCSCMZR.CMZ_FLG == CMZ_FLG_AC 
            || BPCSCMZR.CMZ_FLG == CMZ_FLG_CARD) {
            B110_CHECK_ADD_REC_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSCMZR.CMZ_FLG == CMZ_FLG_BIZ) {
                B120_CHECK_ADD_REC_PROC_BIZ();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REL_FLG_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_TRANS_ADD_DATA_PAR();
        if (pgmRtn) return;
        BPCRCMZR.INFO.FUNC = 'C';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        CEP.TRC(SCCGWA, "START CALL");
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "END CALL");
        B023_WRITE_NHIS_PROC();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCMZR.CMZ_FLG == CMZ_FLG_CUST 
            || BPCSCMZR.CMZ_FLG == CMZ_FLG_AC 
            || BPCSCMZR.CMZ_FLG == CMZ_FLG_CARD) {
            B100_CHECK_MODIFY_REC_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSCMZR.CMZ_FLG == CMZ_FLG_BIZ) {
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REL_FLG_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B060_READUPD_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCMZR);
        IBS.CLONE(SCCGWA, BPRCMZR, BPRCMZR);
        B105_CHECK_WITH_OLD_REC_PROC();
        if (pgmRtn) return;
        R000_TRANS_MOD_DATA_PAR();
        if (pgmRtn) return;
        BPCRCMZR.INFO.FUNC = 'U';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        B023_WRITE_NHIS_PROC();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B011_CHECK_KEY_VALIDITY();
        if (pgmRtn) return;
        B060_READUPD_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCMZR);
        IBS.CLONE(SCCGWA, BPRCMZR, BPRCMZR);
        BPCRCMZR.INFO.FUNC = 'D';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        B023_WRITE_NHIS_PROC();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_BRW_QUERY_DATA_PAR();
        if (pgmRtn) return;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B070_START_BROWSE();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        while (BPCRCMZR.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_DATA_OUTPUT_BRW();
            if (pgmRtn) return;
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        B090_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B060_READUPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        IBS.init(SCCGWA, BPRCMZR);
        BPRCMZR.KEY.CMZ_FLG = BPCSCMZR.CMZ_FLG;
        BPRCMZR.KEY.CI_NO = BPCSCMZR.CI_NO;
        BPRCMZR.KEY.CMZ_AC = BPCSCMZR.CMZ_AC;
        BPRCMZR.KEY.CMZ_BIZ = BPCSCMZR.CMZ_BIZ;
        BPRCMZR.KEY.EFF_DATE = BPCSCMZR.EFF_DATE;
        BPRCMZR.KEY.FEE_CODE = BPCSCMZR.FEE_CODE;
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_FLG);
        CEP.TRC(SCCGWA, BPCSCMZR.CI_NO);
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_AC);
        CEP.TRC(SCCGWA, BPCSCMZR.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSCMZR.FEE_CODE);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_AC);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
        BPCRCMZR.INFO.FUNC = 'R';
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        BPCRCMZR.INFO.REC_LEN = 279;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        if (BPCRCMZR.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCRCMZR.RETURN_INFO == 'F') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPCSCMZR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B023_WRITE_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_RMK = BPRCMZR.CMZ_DESC;
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_DESC);
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        BPCPNHIS.INFO.AC = BPCSCMZR.CMZ_AC;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPCPNHIS.INFO.CI_NO = BPCSCMZR.CI_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CI_NO);
        if (BPCSCMZR.CMZ_FLG == '4') {
            BPCPNHIS.INFO.AC = BPCSCMZR.CMZ_BIZ;
            BPCPNHIS.INFO.TX_TOOL = BPCSCMZR.CMZ_AC;
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TOOL);
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        if (BPCSCMZR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.FMT_ID_LEN = 279;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRCMZR;
        }
        if (BPCSCMZR.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.FMT_ID_LEN = 279;
            BPCPNHIS.INFO.OLD_DAT_PT = BPRCMZR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRCMZR;
        }
        if (BPCSCMZR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.FMT_ID_LEN = 279;
            BPCPNHIS.INFO.OLD_DAT_PT = BPRCMZR;
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID_LEN);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, DD_M_DDZIMMST, DDCIMMST);
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CALL_DCZUSPAC, DCCUSPAC);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_START_BROWSE() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'S';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B070_START_BROWSE_Q() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'O';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B070_START_BROWSE_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'T';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'N';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
    }
    public void B090_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'E';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B011_CHECK_KEY_VALIDITY() throws IOException,SQLException,Exception {
        if (BPCSCMZR.CMZ_FLG == ' ') {
            CEP.TRC(SCCGWA, BPCSCMZR.CMZ_FLG);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSCMZR.CMZ_FLG == '1') {
            CEP.TRC(SCCGWA, BPCSCMZR.CI_NO);
            if (BPCSCMZR.CI_NO.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_INF_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSCMZR.CMZ_FLG == '2' 
            || BPCSCMZR.CMZ_FLG == '3') {
            CEP.TRC(SCCGWA, BPCSCMZR.CMZ_AC);
            if (BPCSCMZR.CMZ_AC.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_INF_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSCMZR.CMZ_FLG == '4') {
            CEP.TRC(SCCGWA, BPCSCMZR.CMZ_BIZ);
            if (BPCSCMZR.CMZ_BIZ.trim().length() == 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_INF_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSCMZR.ENTI_FLG == ' ') {
            CEP.TRC(SCCGWA, BPCSCMZR.ENTI_FLG);
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_MODIFY_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_CCY);
        CEP.TRC(SCCGWA, BPCSCMZR.CCY_RULE);
        if (BPCSCMZR.CMZ_CCY.trim().length() > 0 
            && BPCSCMZR.CCY_RULE == ' ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CMZ_CCY_RULE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRCMZR);
        IBS.init(SCCGWA, BPRCMZR);
        BPRCMZR.KEY.CI_NO = BPCSCMZR.CI_NO;
        B070_START_BROWSE_1();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        while (BPCRCMZR.RETURN_INFO != 'N') {
            if (BPRCMZR.KEY.EFF_DATE > BPCSCMZR.EFF_DATE) {
                if (BPCSCMZR.ENTI_FLG == ENTI_FLG_ALL 
                    && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CUST_EXP_CONFLICT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((BPRCMZR.ENTI_FLG == ENTI_FLG_ALL 
                        && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE) 
                        || (BPRCMZR.KEY.FEE_CODE.equalsIgnoreCase(BPCSCMZR.FEE_CODE) 
                        && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE)) {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CUST_EXP_CONFLICT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        B090_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B100_CHECK_MODIFY_REC_PROC_BIZ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_CCY);
        CEP.TRC(SCCGWA, BPCSCMZR.CCY_RULE);
        if (BPCSCMZR.CMZ_CCY.trim().length() > 0 
            && BPCSCMZR.CCY_RULE == ' ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CMZ_CCY_RULE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCRCMZR);
        IBS.init(SCCGWA, BPRCMZR);
        BPRCMZR.KEY.CMZ_BIZ = BPCSCMZR.CMZ_BIZ;
        B070_START_BROWSE_1();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        while (BPCRCMZR.RETURN_INFO != 'N') {
            if (BPRCMZR.KEY.EFF_DATE > BPCSCMZR.EFF_DATE) {
                if (BPCSCMZR.ENTI_FLG == ENTI_FLG_ALL 
                    && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CUST_EXP_CONFLICT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if ((BPRCMZR.ENTI_FLG == ENTI_FLG_ALL 
                        && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE) 
                        || (BPRCMZR.KEY.FEE_CODE.equalsIgnoreCase(BPCSCMZR.FEE_CODE) 
                        && BPRCMZR.KEY.EFF_DATE <= BPCSCMZR.EXP_DATE)) {
                        WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CUST_EXP_CONFLICT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        B090_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B105_CHECK_WITH_OLD_REC_PROC() throws IOException,SQLException,Exception {
        if (BPCSCMZR.EXP_DATE == BPRCMZR.EXP_DATE 
            && BPCSCMZR.CMZ_FLG1 == BPRCMZR.CMZ_FLG1 
            && BPCSCMZR.CMZ_FLG2 == BPRCMZR.CMZ_FLG2 
            && BPCSCMZR.CMZ_PCN == BPRCMZR.CMZ_PCN 
            && BPCSCMZR.CMZ_AMT == BPRCMZR.CMZ_AMT 
            && BPCSCMZR.CMZ_CCY.equalsIgnoreCase(BPRCMZR.CMZ_CCY) 
            && BPCSCMZR.CCY_RULE == BPRCMZR.CMZ_CCY_RULE 
            && BPCSCMZR.CMZ_DESC.equalsIgnoreCase(BPRCMZR.CMZ_DESC) 
            && BPCSCMZR.EFF_F_DT == BPRCMZR.EFF_F_DT 
            && BPCSCMZR.EXP_F_DT == BPRCMZR.EXP_F_DT 
            && BPCSCMZR.TRC_NUM == BPRCMZR.TRC_NUM) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_UPD_DATA_NOT_CHG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_CHECK_ADD_REC_PROC() throws IOException,SQLException,Exception {
        if (BPCSCMZR.CMZ_FLG == CMZ_FLG_CARD 
            || BPCSCMZR.CMZ_FLG == CMZ_FLG_AC) {
            B050_CHECK_CMZ_CARD_TRN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSCMZR.CCY_RULE);
        if (BPCSCMZR.CMZ_CCY.trim().length() > 0 
            && BPCSCMZR.CCY_RULE == ' ') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CMZ_CCY_RULE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B120_CHECK_ADD_REC_PROC_BIZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMZR);
        IBS.init(SCCGWA, BPCRCMZR);
        BPRCMZR.KEY.CMZ_FLG = BPCSCMZR.CMZ_FLG;
        BPRCMZR.KEY.CMZ_BIZ = BPCSCMZR.CMZ_BIZ;
        BPRCMZR.KEY.EFF_DATE = BPCSCMZR.EFF_DATE;
        BPCRCMZR.INFO.FUNC = 'Q';
        BPCRCMZR.INFO.OPT = 'B';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_BIZ);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
        if (BPCRCMZR.RC.RC_CODE == 0) {
            CEP.TRC(SCCGWA, BPCRCMZR.RC);
            CEP.TRC(SCCGWA, "QWE");
            if (BPCSCMZR.EFF_DATE > BPRCMZR.EXP_DATE) {
                CEP.TRC(SCCGWA, BPCSCMZR.EFF_DATE);
                CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BIZ_RECORD_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "EWQ");
            CEP.TRC(SCCGWA, BPCRCMZR.RC);
            if (BPCRCMZR.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "POI");
            } else {
                CEP.TRC(SCCGWA, "IOP");
                WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCMZR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_CHECK_CMZ_CARD_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = BPCSCMZR.CMZ_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCSCMZR.CI_NO);
        if (BPCSCMZR.CI_NO.trim().length() > 0) {
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(BPCSCMZR.CI_NO)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AC_CI_NOT_RELATED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_TRANS_ADD_DATA_PAR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMZR);
        IBS.init(SCCGWA, BPCRCMZR);
        BPRCMZR.KEY.CMZ_FLG = BPCSCMZR.CMZ_FLG;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        BPRCMZR.KEY.CI_NO = BPCSCMZR.CI_NO;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        BPRCMZR.KEY.CMZ_AC = BPCSCMZR.CMZ_AC;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_AC);
        BPRCMZR.KEY.CMZ_BIZ = BPCSCMZR.CMZ_BIZ;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_BIZ);
        BPRCMZR.KEY.EFF_DATE = BPCSCMZR.EFF_DATE;
        BPRCMZR.EXP_DATE = BPCSCMZR.EXP_DATE;
        BPRCMZR.ENTI_FLG = BPCSCMZR.ENTI_FLG;
        BPRCMZR.KEY.FEE_CODE = BPCSCMZR.FEE_CODE;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
        BPRCMZR.CMZ_CCY = BPCSCMZR.CMZ_CCY;
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_CCY);
        BPRCMZR.CMZ_DESC = BPCSCMZR.CMZ_DESC;
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_DESC);
        BPRCMZR.CMZ_FLG1 = BPCSCMZR.CMZ_FLG1;
        BPRCMZR.CMZ_FLG2 = BPCSCMZR.CMZ_FLG2;
        BPRCMZR.CMZ_AMT = BPCSCMZR.CMZ_AMT;
        BPRCMZR.CMZ_PCN = BPCSCMZR.CMZ_PCN;
        BPRCMZR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCMZR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCMZR.CRT_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRCMZR.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRCMZR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRCMZR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRCMZR.SUP_TEL1);
        BPRCMZR.CMZ_CCY_RULE = BPCSCMZR.CCY_RULE;
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_CCY_RULE);
        BPRCMZR.TRC_NUM = BPCSCMZR.TRC_NUM;
        BPRCMZR.EFF_F_DT = BPCSCMZR.EFF_F_DT;
        BPRCMZR.EXP_F_DT = BPCSCMZR.EXP_F_DT;
    }
    public void R000_TRANS_MOD_DATA_PAR() throws IOException,SQLException,Exception {
        BPRCMZR.EXP_DATE = BPCSCMZR.EXP_DATE;
        BPRCMZR.ENTI_FLG = BPCSCMZR.ENTI_FLG;
        BPRCMZR.CMZ_CCY = BPCSCMZR.CMZ_CCY;
        BPRCMZR.CMZ_DESC = BPCSCMZR.CMZ_DESC;
        BPRCMZR.CMZ_FLG1 = BPCSCMZR.CMZ_FLG1;
        BPRCMZR.CMZ_FLG2 = BPCSCMZR.CMZ_FLG2;
        BPRCMZR.CMZ_AMT = BPCSCMZR.CMZ_AMT;
        BPRCMZR.CMZ_PCN = BPCSCMZR.CMZ_PCN;
        BPRCMZR.TRC_NUM = BPCSCMZR.TRC_NUM;
        BPRCMZR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCMZR.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRCMZR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRCMZR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPRCMZR.CMZ_CCY_RULE = BPCSCMZR.CCY_RULE;
        BPRCMZR.EFF_F_DT = BPCSCMZR.EFF_F_DT;
        BPRCMZR.EXP_F_DT = BPCSCMZR.EXP_F_DT;
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_DESC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, BPRCMZR.SUP_TEL1);
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_CCY_RULE);
    }
    public void R000_TRANS_BRW_QUERY_DATA_PAR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMZR);
        IBS.init(SCCGWA, BPCRCMZR);
        CEP.TRC(SCCGWA, "ZHENGJIE");
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_FLG);
        CEP.TRC(SCCGWA, BPCSCMZR.CI_NO);
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_AC);
        CEP.TRC(SCCGWA, BPCSCMZR.CMZ_BIZ);
        CEP.TRC(SCCGWA, BPCSCMZR.EFF_DATE);
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("100100")) {
            if (BPCSCMZR.EFF_DATE == 0) {
                BPCSCMZR.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, BPCSCMZR.EFF_DATE);
            }
        }
        BPRCMZR.KEY.CMZ_FLG = BPCSCMZR.CMZ_FLG;
        BPRCMZR.KEY.CI_NO = BPCSCMZR.CI_NO;
        BPRCMZR.KEY.CMZ_AC = BPCSCMZR.CMZ_AC;
        BPRCMZR.KEY.CMZ_BIZ = BPCSCMZR.CMZ_BIZ;
        BPRCMZR.KEY.EFF_DATE = BPCSCMZR.EFF_DATE;
    }
    public void R000_TRANS_DATA_OUTPUT_BRW_Q() throws IOException,SQLException,Exception {
        BPCQCMZR.CMZ_FLG = BPRCMZR.KEY.CMZ_FLG;
        BPCQCMZR.CI_NO = BPRCMZR.KEY.CI_NO;
        BPCQCMZR.CMZ_AC = BPRCMZR.KEY.CMZ_AC;
        BPCQCMZR.CMZ_BIZ = BPRCMZR.KEY.CMZ_BIZ;
        BPCQCMZR.EFF_DATE = BPRCMZR.KEY.EFF_DATE;
        BPCQCMZR.EXP_DATE = BPRCMZR.EXP_DATE;
        BPCQCMZR.ENTI_FLG = BPRCMZR.ENTI_FLG;
        BPCQCMZR.CMZ_DESC = BPRCMZR.CMZ_DESC;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CCY_RULE = BPRCMZR.CMZ_CCY_RULE;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CMZ_CCY = BPRCMZR.CMZ_CCY;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].FEE_CODE = BPRCMZR.KEY.FEE_CODE;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CMZ_FLG1 = BPRCMZR.CMZ_FLG1;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CMZ_FLG2 = BPRCMZR.CMZ_FLG2;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CMZ_AMT = BPRCMZR.CMZ_AMT;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].CMZ_PCN = BPRCMZR.CMZ_PCN;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].TRC_NUM = BPRCMZR.TRC_NUM;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].EFF_F_DT = BPRCMZR.EFF_F_DT;
        BPCQCMZR.FEE_INFO[WS_VARIABLES.CNT_Q-1].EXP_F_DT = BPRCMZR.EXP_F_DT;
    }
    public void R000_TRANS_DATA_OUTPUT_BRW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.CMZ_FLG = BPRCMZR.KEY.CMZ_FLG;
        WS_OUTPUT.CI_NO = BPRCMZR.KEY.CI_NO;
        WS_OUTPUT.CMZ_AC = BPRCMZR.KEY.CMZ_AC;
        WS_OUTPUT.CMZ_BIZ = BPRCMZR.KEY.CMZ_BIZ;
        WS_OUTPUT.EFF_DATE = BPRCMZR.KEY.EFF_DATE;
        WS_OUTPUT.EXP_DATE = BPRCMZR.EXP_DATE;
        WS_OUTPUT.ENTI_FLG = BPRCMZR.ENTI_FLG;
        WS_OUTPUT.CMZ_CCY = BPRCMZR.CMZ_CCY;
        WS_OUTPUT.FEE_CODE = BPRCMZR.KEY.FEE_CODE;
        WS_OUTPUT.CMZ_DESC = BPRCMZR.CMZ_DESC;
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_DESC);
        WS_OUTPUT.CMZ_FLG1 = BPRCMZR.CMZ_FLG1;
        WS_OUTPUT.CMZ_FLG2 = BPRCMZR.CMZ_FLG2;
        WS_OUTPUT.CMZ_AMT = BPRCMZR.CMZ_AMT;
        WS_OUTPUT.CMZ_PCN = BPRCMZR.CMZ_PCN;
        WS_OUTPUT.SUP_TEL1 = BPRCMZR.SUP_TEL1;
        WS_OUTPUT.SUP_TEL2 = BPRCMZR.SUP_TEL2;
        WS_OUTPUT.CRT_DATE = BPRCMZR.CRT_DATE;
        WS_OUTPUT.UPD_DATE = BPRCMZR.UPD_DATE;
        WS_OUTPUT.CRT_TEL = BPRCMZR.CRT_TEL;
        WS_OUTPUT.UPD_TEL = BPRCMZR.UPD_TEL;
        WS_OUTPUT.CCY_RULE = BPRCMZR.CMZ_CCY_RULE;
        WS_OUTPUT.EFF_F_DT = BPRCMZR.EFF_F_DT;
        WS_OUTPUT.EXP_F_DT = BPRCMZR.EXP_F_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 250;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, BPCOCMZR);
        BPCOCMZR.CMZ_FLG = BPRCMZR.KEY.CMZ_FLG;
        BPCOCMZR.CI_NO = BPRCMZR.KEY.CI_NO;
        BPCOCMZR.CMZ_AC = BPRCMZR.KEY.CMZ_AC;
        BPCOCMZR.CMZ_BIZ = BPRCMZR.KEY.CMZ_BIZ;
        BPCOCMZR.EFF_DATE = BPRCMZR.KEY.EFF_DATE;
        BPCOCMZR.EXP_DATE = BPRCMZR.EXP_DATE;
        BPCOCMZR.ENTI_FLG = BPRCMZR.ENTI_FLG;
        BPCOCMZR.CMZ_CCY = BPRCMZR.CMZ_CCY;
        BPCOCMZR.CMZ_CCY_RULE = BPRCMZR.CMZ_CCY_RULE;
        BPCOCMZR.FEE_CODE = BPRCMZR.KEY.FEE_CODE;
        BPCOCMZR.CMZ_DESC = BPRCMZR.CMZ_DESC;
        CEP.TRC(SCCGWA, BPCOCMZR.CMZ_DESC);
        BPCOCMZR.CMZ_FLG1 = BPRCMZR.CMZ_FLG1;
        BPCOCMZR.CMZ_FLG2 = BPRCMZR.CMZ_FLG2;
        BPCOCMZR.CMZ_AMT = BPRCMZR.CMZ_AMT;
        BPCOCMZR.CMZ_PCN = BPRCMZR.CMZ_PCN;
        BPCOCMZR.SUP_TEL1 = BPRCMZR.SUP_TEL1;
        BPCOCMZR.SUP_TEL2 = BPRCMZR.SUP_TEL2;
        BPCOCMZR.CRT_DATE = BPRCMZR.CRT_DATE;
        BPCOCMZR.UPD_DATE = BPRCMZR.UPD_DATE;
        BPCOCMZR.CRT_TEL = BPRCMZR.CRT_TEL;
        BPCOCMZR.UPD_TEL = BPRCMZR.UPD_TEL;
        BPCOCMZR.CCY_RULE = BPRCMZR.CMZ_CCY_RULE;
        BPCOCMZR.TRC_NUM = BPRCMZR.TRC_NUM;
        BPCOCMZR.EFF_F_DT = BPRCMZR.EFF_F_DT;
        BPCOCMZR.EXP_F_DT = BPRCMZR.EXP_F_DT;
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCMZR;
        SCCFMT.DATA_LEN = 255;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1403;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R020_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 250;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_CALL_BPZRCMZR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_MAINTAIN_CMZR, BPCRCMZR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
