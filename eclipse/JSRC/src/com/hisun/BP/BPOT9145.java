package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9145 {
    short K_AP_CODE = 999;
    short K_NEXT_TXN_ADD = 9142;
    short K_NEXT_TXN_UPD = 9143;
    short K_NEXT_TXN_DEL = 9144;
    short K_NEXT_TXN_SETT = 9156;
    short K_NEXT_TXN_REVE = 9999;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSSCHD BPCSSCHD = new BPCSSCHD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9140_AWA_9140 BPB9140_AWA_9140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9145 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9140_AWA_9140>");
        BPB9140_AWA_9140 = (BPB9140_AWA_9140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NEXT_TXN();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_SET_NEXT_TXN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = K_AP_CODE;
        CEP.TRC(SCCGWA, BPB9140_AWA_9140.FUNC);
        WS_FUNC_FLG = BPB9140_AWA_9140.FUNC;
        if (WS_FUNC_FLG == 'A') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_ADD;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'M') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_UPD;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_DEL;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'S') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_SETT;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'R') {
            SCCSUBS.TR_CODE = K_NEXT_TXN_REVE;
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
        IBS.init(SCCGWA, BPCSSCHD);
        BPCSSCHD.KEY.CTRT_NO = BPB9140_AWA_9140.CTRT_NO;
        BPCSSCHD.KEY.SETTLE_DATE = BPB9140_AWA_9140.SETT_DT;
        BPCSSCHD.FUNC = 'Q';
        if (BPB9140_AWA_9140.FUNC == 'I') {
            BPCSSCHD.FUNC_OPT = 'I';
        }
        if (BPB9140_AWA_9140.FUNC == 'A') {
            BPCSSCHD.FUNC_OPT = 'A';
        }
        if (BPB9140_AWA_9140.FUNC == 'S') {
            BPCSSCHD.FUNC_OPT = 'S';
        }
        if (BPB9140_AWA_9140.FUNC == 'R') {
            BPCSSCHD.FUNC_OPT = 'R';
        }
        S00_CALL_BPZSSCHD();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S00_CALL_BPZSSCHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-FEESCHD", BPCSSCHD);
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
