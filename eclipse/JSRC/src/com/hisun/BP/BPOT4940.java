package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4940 {
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_TLR_INF_BRW = "BP-S-TLR-INF-BRW    ";
    String CPN_TLR_STS_BRW = "BP-S-TLR-STS-QUERY  ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4940_WS_DATA WS_DATA = new BPOT4940_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSTLSQ BPCSTLSQ = new BPCSTLSQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPB4940_AWA_4940 BPB4940_AWA_4940;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4940 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4940_AWA_4940>");
        BPB4940_AWA_4940 = (BPB4940_AWA_4940) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSTLSQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BR_STS_INQ();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4940_AWA_4940.BR);
        if (BPB4940_AWA_4940.BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPB4940_AWA_4940.BR;
            S000_CALL_BPZPQORG();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        WS_DATA.WS_TLR_BR = BPCFTLRQ.INFO.NEW_BR;
        CEP.TRC(SCCGWA, WS_DATA.WS_TLR_BR);
        CEP.TRC(SCCGWA, BPB4940_AWA_4940.BR);
        if (WS_DATA.WS_TLR_BR == BPB4940_AWA_4940.BR) {
        } else {
            BPCPRGST.BR1 = BPB4940_AWA_4940.BR;
            BPCPRGST.BR2 = WS_DATA.WS_TLR_BR;
            S000_CALL_BPZPRGST();
            if (BPCPRGST.FLAG == 'Y' 
                && BPCPRGST.LVL_RELATION == 'C') {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AUTH_RGN_OUTSIDE);
            }
        }
    }
    public void B020_BR_STS_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSQ);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSQ();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSQ.TLR_BR = BPB4940_AWA_4940.BR;
        BPCSTLSQ.SIGN_STS = BPB4940_AWA_4940.STS;
        BPCSTLSQ.CHK_FLG = BPB4940_AWA_4940.CHK_FLG;
    }
    public void S000_CALL_BPZSTLSQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TLR_STS_BRW, BPCSTLSQ);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLRQ.RC);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPRGST.RC);
        }
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
