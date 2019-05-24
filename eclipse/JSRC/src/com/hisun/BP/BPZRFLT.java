package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFLT {
    int JIBS_tmp_int;
    String K_PGM_NAME = "BPZRFLT";
    String K_PARM_FLOAT = "FLOAT";
    char K_ONLINE_RELASE = 'O';
    String CPN_CALL_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_CALL_BPZPRMB = "BP-PARM-BROWSE      ";
    String CPN_CALL_BPZUFLT = "BP-UPD-FLT-STS      ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZRFLT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZRFLT_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUFLT BPCUFLT = new BPCUFLT();
    SCCGWA SCCGWA;
    BPCRFLT BPCRFLT;
    public void MP(SCCGWA SCCGWA, BPCRFLT BPCRFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFLT = BPCRFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRFLT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFLT);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD.trim().length() != 0; WS_CNT += 1) {
            B010_TRANS_OUTPUT();
            if (BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FUNC == 'R') {
                B020_RELASE_FLOAT();
            } else if (BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FUNC == 'C') {
                B030_CANCEL_RELASE();
            } else if (BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FUNC == ' ') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RELEASE_STS_ERR;
                S000_ERR_MSG_PROC();
            }
            B040_TRANS_OUTPUT();
        }
        B050_OUTPUT();
    }
    public void B010_TRANS_OUTPUT() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_STS = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].STS;
        BPCUFLT.FLT_CODE = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD;
        BPCUFLT.CCY = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].CCY;
        BPCUFLT.FLT_ITEM = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM;
        BPCUFLT.FLT_STS = K_ONLINE_RELASE;
    }
    public void B020_RELASE_FLOAT() throws IOException,SQLException,Exception {
        BPCUFLT.FUNC = 'R';
        S000_CALL_BPZUFLT();
    }
    public void B030_CANCEL_RELASE() throws IOException,SQLException,Exception {
        BPCUFLT.FUNC = 'O';
        S000_CALL_BPZUFLT();
    }
    public void B040_TRANS_OUTPUT() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FLT_CD = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD;
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FLT_ITM = BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM;
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FUNC = "" + BPCRFLT.OUTPUT_DATA.DATA[WS_CNT-1].FUNC;
        JIBS_tmp_int = WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FUNC.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FUNC = "0" + WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_FUNC;
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_CCY = BPCUFLT.CCY;
        WS_OUTPUT_DATA.WS_DATA[WS_CNT-1].WS_NEW_STS = BPCUFLT.FLT_STS;
    }
    public void B050_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCRFLT.FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 350;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZUFLT, BPCUFLT);
        CEP.TRC(SCCGWA, BPCUFLT.RC);
        if (BPCUFLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFLT.RC);
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
