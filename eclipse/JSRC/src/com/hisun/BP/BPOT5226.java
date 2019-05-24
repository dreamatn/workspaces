package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5226 {
    String K_MMO = "BP";
    String K_CALL_NAME = "BP-U-S-UOD";
    String K_OUTPUT_FMT = "BPX01";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOUOD BPCOUOD = new BPCOUOD();
    BPCOSUOD BPCOSUOD = new BPCOSUOD();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB5220_AWA_5220 BPB5220_AWA_5220;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5226 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5220_AWA_5220>");
        BPB5220_AWA_5220 = (BPB5220_AWA_5220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOUOD);
        IBS.init(SCCGWA, BPCOSUOD);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_SET_SUBSEQUENCE();
        B20_INQ_UOD_RECORD();
    }
    public void B10_SET_SUBSEQUENCE() throws IOException,SQLException,Exception {
        BPCOUOD.FUNC = BPB5220_AWA_5220.FUNC;
        CEP.TRC(SCCGWA, BPCOUOD.FUNC);
        if (BPCOUOD.FUNC == 'I') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5222;
            S00_SET_SUBS_TRN();
        } else if (BPCOUOD.FUNC == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5224;
            S00_SET_SUBS_TRN();
        } else if (BPCOUOD.FUNC == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5225;
            S00_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S00_ERR_MSG_PROC();
        }
    }
    public void B20_INQ_UOD_RECORD() throws IOException,SQLException,Exception {
        R00_TRANS_DATA_PARAMETER();
        S00_CALL_BPZSUOD();
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOSUOD.KEY.UOD_BR = BPB5220_AWA_5220.UOD_BR;
        BPCOUOD.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPB5220_AWA_5220.UOD_BR);
    }
    public void S00_CALL_BPZSUOD() throws IOException,SQLException,Exception {
        BPCOUOD.FUNC = 'I';
        BPCOUOD.POINTER = BPCOSUOD;
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCOUOD);
        if (BPCOUOD.FOUND_FLG == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            S00_ERR_MSG_PROC();
        }
    }
    public void S00_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB5220_AWA_5220.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
