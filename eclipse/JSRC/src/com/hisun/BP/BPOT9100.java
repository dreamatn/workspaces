package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9100 {
    String CPN_S_FEEPRD_DEF = "BP-S-MGM-FEEPRD  ";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    short K_AP_CODE = 999;
    short K_NEXT_TXN_ADD = 9101;
    short K_NEXT_TXN_UPD = 9102;
    short K_NEXT_TXN_DEL = 9103;
    short K_NEXT_TXN_INQ = 9105;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSFPDD BPCSFPDD = new BPCSFPDD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9100_AWA_9100 BPB9100_AWA_9100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9100_AWA_9100>");
        BPB9100_AWA_9100 = (BPB9100_AWA_9100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NEXT_TXN();
        if (WS_FUNC_FLG != 'A') {
            B020_INQ_REC_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPB9100_AWA_9100.FUNC != 'I' 
            && BPB9100_AWA_9100.FUNC != 'M' 
            && BPB9100_AWA_9100.FUNC != 'D' 
            && BPB9100_AWA_9100.FUNC != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB9100_AWA_9100.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9100_AWA_9100.FUNC != 'A') {
            if (BPB9100_AWA_9100.ACCT_CTR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_ACCT_MUSTINPUT;
                WS_FLD_NO = BPB9100_AWA_9100.ACCT_CTR_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB9100_AWA_9100.PRD_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
                WS_FLD_NO = BPB9100_AWA_9100.PRD_TYPE_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB9100_AWA_9100.EFF_DATE == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT;
                WS_FLD_NO = BPB9100_AWA_9100.EFF_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B015_SET_NEXT_TXN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        WS_FUNC_FLG = BPB9100_AWA_9100.FUNC;
        if (WS_FUNC_FLG == 'A') {
            if ("999".trim().length() == 0) SCCSUBS.AP_CODE = 0;
            else SCCSUBS.AP_CODE = Short.parseShort("999");
            if ("9101".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("9101");
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'M') {
            if ("999".trim().length() == 0) SCCSUBS.AP_CODE = 0;
            else SCCSUBS.AP_CODE = Short.parseShort("999");
            if ("9102".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("9102");
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            if ("999".trim().length() == 0) SCCSUBS.AP_CODE = 0;
            else SCCSUBS.AP_CODE = Short.parseShort("999");
            if ("9103".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("9103");
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'I') {
            if ("999".trim().length() == 0) SCCSUBS.AP_CODE = 0;
            else SCCSUBS.AP_CODE = Short.parseShort("999");
            if ("9105".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("9105");
            S000_SET_SUBS_TRN();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNCTION CODE(" + WS_FUNC_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFPDD);
        BPCSFPDD.FUNC = 'Q';
        BPCSFPDD.KEY.ACCT_CENTRE = BPB9100_AWA_9100.ACCT_CTR;
        BPCSFPDD.KEY.PROD_TYPE = BPB9100_AWA_9100.PRD_TYPE;
        BPCSFPDD.INF.EFF_DATE = BPB9100_AWA_9100.EFF_DATE;
        S00_CALL_BPZSFPDD();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB9100_AWA_9100.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S00_CALL_BPZSFPDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_FEEPRD_DEF, BPCSFPDD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
