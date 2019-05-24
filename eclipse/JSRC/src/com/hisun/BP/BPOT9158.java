package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9158 {
    String CPN_S_BPZFCPHM = "BP-S-MGM-CAL-PARM";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    short K_AP_CODE = 999;
    short K_NEXT_TXN_ADD = 9152;
    short K_NEXT_TXN_UPD = 9153;
    short K_NEXT_TXN_DEL = 9154;
    short K_NEXT_TXN_INQ = 9155;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFCPHM BPCFCPHM = new BPCFCPHM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9152_AWA_9152 BPB9152_AWA_9152;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9158 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9152_AWA_9152>");
        BPB9152_AWA_9152 = (BPB9152_AWA_9152) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B015_SET_NEXT_TXN();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB9152_AWA_9152.FUNC;
        CEP.TRC(SCCGWA, BPB9152_AWA_9152.FUNC);
        CEP.TRC(SCCGWA, BPB9152_AWA_9152.CTRT_NO);
        CEP.TRC(SCCGWA, BPB9152_AWA_9152.VAL_DT);
        if (BPB9152_AWA_9152.CTRT_NO.trim().length() == 0) {
            WS_ERR_MSG = "CONTRACT NO IS EMPTY.";
            WS_FLD_NO = BPB9152_AWA_9152.CTRT_NO_NO;
            S000_ERR_MSG_PROC();
        }
        if ((WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') 
            && BPB9152_AWA_9152.VAL_DT <= 0) {
            WS_ERR_MSG = "EFFECTIVE DATE IS EMPTY.";
            WS_FLD_NO = BPB9152_AWA_9152.VAL_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_SET_NEXT_TXN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = K_AP_CODE;
        CEP.TRC(SCCGWA, BPB9152_AWA_9152.FUNC);
        WS_FUNC_FLG = BPB9152_AWA_9152.FUNC;
        if (WS_FUNC_FLG == 'A') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_ADD;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_UPD;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_DEL;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'I') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNCTION CODE(" + WS_FUNC_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCPHM);
        BPCFCPHM.CTRT_NO = BPB9152_AWA_9152.CTRT_NO;
        BPCFCPHM.VALUE_DATE = BPB9152_AWA_9152.VAL_DT;
        CEP.TRC(SCCGWA, BPCFCPHM.FUNC);
        if (WS_FUNC_FLG == 'A') {
            BPCFCPHM.FUNCI = 'R';
        } else {
            BPCFCPHM.FUNCI = 'Q';
        }
        BPCFCPHM.FUNC = BPB9152_AWA_9152.FUNC;
        S00_CALL_BPZFCPHM();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB9152_AWA_9152.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S00_CALL_BPZFCPHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFCPHM, BPCFCPHM);
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
