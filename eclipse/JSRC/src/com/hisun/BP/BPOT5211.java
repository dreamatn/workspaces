package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5211 {
    String CPN_C_INTR_INQ = "BP-C-INTR-INQ     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPOT5211_WS_OUT_DATA WS_OUT_DATA = new BPOT5211_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCMT BPCMT = new BPCMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5211_AWA_5211 BPB5211_AWA_5211;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5211 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5211_AWA_5211>");
        BPB5211_AWA_5211 = (BPB5211_AWA_5211) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_RATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5211_AWA_5211.IN_BR);
        if (BPB5211_AWA_5211.IN_BR == ' ' 
            || BPB5211_AWA_5211.IN_BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            WS_FLD_NO = BPB5211_AWA_5211.IN_BR_NO;
            S000_ERR_MSG_PROC();
        } else {
            R000_CHECK_BRANCH();
        }
    }
    public void B020_GET_RATE() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_X_BR = BPB5211_AWA_5211.IN_BR;
        for (WS_I = 1; WS_I <= 30; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB5211_AWA_5211.RT_INFO[WS_I-1].CCY);
            if (BPB5211_AWA_5211.RT_INFO[WS_I-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = BPB5211_AWA_5211.IN_BR;
                BPCCINTI.BASE_INFO.BASE_TYP = BPB5211_AWA_5211.RT_INFO[WS_I-1].BASE_TYP;
                BPCCINTI.BASE_INFO.TENOR = BPB5211_AWA_5211.RT_INFO[WS_I-1].TENOR;
                BPCCINTI.BASE_INFO.CCY = BPB5211_AWA_5211.RT_INFO[WS_I-1].CCY;
                S00_CALL_INQUIRE_RATE();
                WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_CCY = BPB5211_AWA_5211.RT_INFO[WS_I-1].CCY;
                WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_BASE_TYP = BPB5211_AWA_5211.RT_INFO[WS_I-1].BASE_TYP;
                WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_TENOR = BPB5211_AWA_5211.RT_INFO[WS_I-1].TENOR;
                WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_RATE = BPCCINTI.BASE_INFO.RATE;
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_CCY);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_BASE_TYP);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_TENOR);
                CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_RT_INFO[WS_I-1].WS_X_RATE);
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 696;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB5211_AWA_5211.IN_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB5211_AWA_5211.IN_BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_INQUIRE_RATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_C_INTR_INQ, BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            BPCCINTI.BASE_INFO.OWN_RATE = 0;
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
