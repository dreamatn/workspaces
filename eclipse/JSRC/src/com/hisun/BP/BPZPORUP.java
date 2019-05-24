package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPORUP {
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    int K_MAX_CNT = 20;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CUR_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCPORUP BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCPORUP BPCPORUP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPORUP = BPCPORUP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPORUP return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        if (BPCPORUP.DATA_INFO.BNK.equalsIgnoreCase("0")) {
            BPCPORUP.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B20_GET_UPORG_INFO_CN();
        }
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPORUP.DATA_INFO.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_GET_UPORG_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPORUP.DATA_INFO.BNK;
        BPCPQORG.BR = BPCPORUP.DATA_INFO.BR;
        S00_CALL_BPZPQORG();
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCPORUP.DATA_INFO.LVL = BPCPQORG.LVL;
        BPCPORUP.DATA_INFO.ATTR = BPCPQORG.ATTR;
        BPCPORUP.DATA_INFO.TYP = BPCPQORG.TYP;
        BPCPORUP.DATA_INFO.ABBR = BPCPQORG.ABBR;
        BPCPORUP.DATA_INFO.FX_BUSI = BPCPQORG.FX_BUSI;
        BPCPORUP.DATA_INFO.CNAP_NO = BPCPQORG.CNAP_NO;
        BPCPORUP.DATA_INFO.ACCT_FLG = BPCPQORG.ACCT_FLG;
        BPCPORUP.DATA_INFO.CALD_CD = BPCPQORG.CALD_CD;
        BPCPORUP.DATA_INFO.BBR = BPCPQORG.BBR;
        WS_CNT = 0;
        while (WS_CNT < K_MAX_CNT 
            && BPCPQORG.SUPR_BR != BPCPQORG.BR) {
            WS_CUR_BR = BPCPQORG.SUPR_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPCPORUP.DATA_INFO.BNK;
            BPCPQORG.BR = WS_CUR_BR;
            S00_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                S000_ERR_MSG_PROC();
            }
            WS_CNT = WS_CNT + 1;
            BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL = BPCPQORG.LVL;
            BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_BR = BPCPQORG.BR;
        }
        BPCPORUP.DATA_INFO.CNT = WS_CNT;
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
