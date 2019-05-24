package com.hisun.VT;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class VTOT2021 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    VTOT2021_WS_OUT_DATA WS_OUT_DATA = new VTOT2021_WS_OUT_DATA();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    VTCSJMDR VTCSJMDR = new VTCSJMDR();
    VTRJMDR VTRJMDR = new VTRJMDR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCI2021 VTCI2021;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTOT2021 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        VTCI2021 = new VTCI2021();
        IBS.init(SCCGWA, VTCI2021);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, VTCI2021);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCI2021.JM_CODE);
        CEP.TRC(SCCGWA, VTCI2021.PROD_CD);
        CEP.TRC(SCCGWA, VTCI2021.BR);
        CEP.TRC(SCCGWA, VTCI2021.CCY);
        CEP.TRC(SCCGWA, VTCI2021.OTH);
        CEP.TRC(SCCGWA, VTCI2021.EFF_DATE);
        CEP.TRC(SCCGWA, VTCI2021.EXP_DATE);
        CEP.TRC(SCCGWA, VTCI2021.JM_CODE);
        CEP.TRC(SCCGWA, VTCI2021.BILL_FLG);
        CEP.TRC(SCCGWA, VTCI2021.TAX_FLG);
        CEP.TRC(SCCGWA, VTCI2021.TAX_TYPE);
        CEP.TRC(SCCGWA, VTCI2021.FREE_TYP);
        CEP.TRC(SCCGWA, VTCI2021.BILL_LIM);
        CEP.TRC(SCCGWA, VTCI2021.RMK);
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTCSJMDR);
        VTCSJMDR.PROD_CD = VTCI2021.PROD_CD;
        VTCSJMDR.BR = VTCI2021.BR;
        VTCSJMDR.CCY = VTCI2021.CCY;
        IBS.CPY2CLS(SCCGWA, VTCI2021.OTH, VTCSJMDR.OTH);
        VTCSJMDR.EFF_DATE = VTCI2021.EFF_DATE;
        VTCSJMDR.EXP_DATE = VTCI2021.EXP_DATE;
        VTCSJMDR.JM_CODE = VTCI2021.JM_CODE;
        VTCSJMDR.BILL_FLG = VTCI2021.BILL_FLG;
        VTCSJMDR.TAX_FLG = VTCI2021.TAX_FLG;
        VTCSJMDR.TAX_TYPE = VTCI2021.TAX_TYPE;
        VTCSJMDR.FREE_TYPE = VTCI2021.FREE_TYP;
        VTCSJMDR.BILL_LIM = VTCI2021.BILL_LIM;
        VTCSJMDR.DESC = VTCI2021.RMK;
        CEP.TRC(SCCGWA, VTCSJMDR.PROD_CD);
        CEP.TRC(SCCGWA, VTCSJMDR.BR);
        CEP.TRC(SCCGWA, VTCSJMDR.CCY);
        CEP.TRC(SCCGWA, VTCSJMDR.OTH);
        CEP.TRC(SCCGWA, VTCSJMDR.EFF_DATE);
        CEP.TRC(SCCGWA, VTCSJMDR.EXP_DATE);
        CEP.TRC(SCCGWA, VTCSJMDR.JM_CODE);
        CEP.TRC(SCCGWA, VTCSJMDR.BILL_FLG);
        CEP.TRC(SCCGWA, VTCSJMDR.TAX_FLG);
        CEP.TRC(SCCGWA, VTCSJMDR.TAX_TYPE);
        CEP.TRC(SCCGWA, VTCSJMDR.FREE_TYPE);
        CEP.TRC(SCCGWA, VTCSJMDR.BILL_LIM);
        CEP.TRC(SCCGWA, VTCSJMDR.DESC);
        VTCSJMDR.FUNC = 'A';
        S000_CALL_VTZSJMDR();
    }
    public void S000_CALL_VTZSJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-S-JMDR-MAINTAIN", VTCSJMDR);
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
