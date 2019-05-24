package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4640 {
    String CPN_S_BRW_ORGS = "BP-S-BRW-ORGS       ";
    String CPN_P_QUERY_ORG = "BP-P-INQ-ORG        ";
    String CPN_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END = ' ';
    int WS_CNT = 0;
    int WS_CNT_AFTER = 0;
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBOGS BPCSBOGS = new BPCSBOGS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCGWA SCCGWA;
    BPB4640_AWA_4640 BPB4640_AWA_4640;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4640 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4640_AWA_4640>");
        BPB4640_AWA_4640 = (BPB4640_AWA_4640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4640_AWA_4640.BR);
        CEP.TRC(SCCGWA, BPB4640_AWA_4640.ORG_STS);
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXX");
        B010_CHECK_INPUT();
        B020_BROWSE_ORG_STS_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_BR = BPB4640_AWA_4640.BR;
        if (BPB4640_AWA_4640.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, BPB4640_AWA_4640.BR);
            BPCPQORG.BR = BPB4640_AWA_4640.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPB4640_AWA_4640.BR);
        } else {
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1].SUPR_LVL);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[2-1].SUPR_LVL);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[3-1].SUPR_LVL);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[4-1].SUPR_BR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[4-1].SUPR_LVL);
            if (BPCPORUP.DATA_INFO.LVL == '9') {
                IBS.init(SCCGWA, BPCPQBNK);
                BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                S000_CALL_BPZPQBNK();
                BPB4640_AWA_4640.BR = BPCPQBNK.DATA_INFO.HEAD_BR;
            }
            if (BPCPORUP.DATA_INFO.LVL == '2') {
                BPB4640_AWA_4640.BR = BPCPORUP.DATA_INFO.BR;
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            }
            if (BPCPORUP.DATA_INFO.LVL == '4' 
                || BPCPORUP.DATA_INFO.LVL == '6') {
                WS_END = 'N';
                for (WS_CNT = 1; BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_BR != 0 
                    && WS_END != 'Y'; WS_CNT += 1) {
                    CEP.TRC(SCCGWA, WS_CNT);
                    if (WS_CNT == 1) {
                        if (BPCPORUP.DATA_INFO.LVL != BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL) {
                            CEP.TRC(SCCGWA, "11");
                            BPB4640_AWA_4640.BR = BPCPORUP.DATA_INFO.BR;
                            WS_END = 'Y';
                        }
                    } else {
                        WS_CNT_AFTER = WS_CNT - 1;
                        if (BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT_AFTER-1].SUPR_LVL != BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT-1].SUPR_LVL) {
                            BPB4640_AWA_4640.BR = BPCPORUP.DATA_INFO.SUPR_GRP[WS_CNT_AFTER-1].SUPR_BR;
                            WS_END = 'Y';
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPB4640_AWA_4640.BR);
    }
    public void B020_BROWSE_ORG_STS_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBOGS);
        BPCSBOGS.BR = WS_BR;
        BPCSBOGS.ORG_STS = BPB4640_AWA_4640.ORG_STS;
        S000_CALL_BPZSBOGS();
    }
    public void S000_CALL_BPZSBOGS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRW_ORGS;
        SCCCALL.COMMAREA_PTR = BPCSBOGS;
        SCCCALL.ERR_FLDNO = BPB4640_AWA_4640.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB4640_AWA_4640.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
