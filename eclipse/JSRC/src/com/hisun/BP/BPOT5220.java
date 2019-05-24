package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5220 {
    String K_MMO = "BP";
    String K_CALL_NAME = "BP-U-S-UOD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOUOD BPCOUOD = new BPCOUOD();
    BPCOSUOD BPCOSUOD = new BPCOSUOD();
    SCCGWA SCCGWA;
    BPB5220_AWA_5220 BPB5220_AWA_5220;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5220 return!");
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
        B20_BRW_UOD_RECORD();
    }
    public void B20_BRW_UOD_RECORD() throws IOException,SQLException,Exception {
        R00_TRANS_DATA_PARAMETER();
        S00_CALL_BPZSUOD();
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOSUOD.KEY.UOD_BR = BPB5220_AWA_5220.UOD_BR;
        CEP.TRC(SCCGWA, BPB5220_AWA_5220.UOD_BR);
    }
    public void S00_CALL_BPZSUOD() throws IOException,SQLException,Exception {
        BPCOUOD.FUNC = 'B';
        BPCOUOD.POINTER = BPCOSUOD;
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCOUOD);
        if (BPCOUOD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            S00_ERR_MSG_PROC();
        }
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
