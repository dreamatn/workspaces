package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1186 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_SEXP_MAINTAIN = "BP-F-S-MAINTAIN-FEXP";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_NEXT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFEXP BPCOFEXP = new BPCOFEXP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB1180_AWA_1180 BPB1180_AWA_1180;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1186 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1180_AWA_1180>");
        BPB1180_AWA_1180 = (BPB1180_AWA_1180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCSUBS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_SET_RETURN_INFO();
        B020_CREATE_EXP_RATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPB1180_AWA_1180.DER_CODE.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DER_CODE_ERR;
            WS_FLD_NO = BPB1180_AWA_1180.DER_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_EXP_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFEXP);
        BPCOFEXP.FUNC = 'I';
        BPCOFEXP.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSEXP();
    }
    public void B030_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB1180_AWA_1180.FUNC;
        if (WS_FUNC_FLG == 'M') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1182;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1183;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'I') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1184;
            S000_SET_SUBS_TRN();
        } else {
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB1180_AWA_1180.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFEXP.KEY.DER_CODE = BPB1180_AWA_1180.DER_CODE;
        BPCOFEXP.VAL.EFF_DATE = BPB1180_AWA_1180.EFF_DATE;
        BPCOFEXP.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFEXP.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCOFEXP.VAL.EFF_DATE);
    }
    public void S000_CALL_BPZFSEXP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SEXP_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFEXP;
        SCCCALL.ERR_FLDNO = BPB1180_AWA_1180.DER_CODE;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
