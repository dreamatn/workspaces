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

public class VTOT2022 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    VTOT2022_WS_OUT_DATA WS_OUT_DATA = new VTOT2022_WS_OUT_DATA();
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
    VTCI2022 VTCI2022;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTOT2022 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        VTCI2022 = new VTCI2022();
        IBS.init(SCCGWA, VTCI2022);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, VTCI2022);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MODIFY_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MODIFY_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCI2022.JM_CODE);
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTCSJMDR);
        VTCSJMDR.PROD_CD = VTCI2022.PROD_CD;
        VTCSJMDR.BR = VTCI2022.BR;
        VTCSJMDR.CCY = VTCI2022.CCY;
        IBS.CPY2CLS(SCCGWA, VTCI2022.OTH, VTCSJMDR.OTH);
        VTCSJMDR.EFF_DATE = VTCI2022.EFF_DATE;
        VTCSJMDR.EXP_DATE = VTCI2022.EXP_DATE;
        VTCSJMDR.JM_CODE = VTCI2022.JM_CODE;
        VTCSJMDR.BILL_FLG = VTCI2022.BILL_FLG;
        VTCSJMDR.TAX_FLG = VTCI2022.TAX_FLG;
        VTCSJMDR.TAX_TYPE = VTCI2022.TAX_TYPE;
        VTCSJMDR.FREE_TYPE = VTCI2022.FREE_TYP;
        VTCSJMDR.BILL_LIM = VTCI2022.BILL_LIM;
        VTCSJMDR.DESC = VTCI2022.RMK;
        VTCSJMDR.FUNC = 'M';
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
