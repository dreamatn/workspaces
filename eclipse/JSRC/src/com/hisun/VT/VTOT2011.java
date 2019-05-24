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

public class VTOT2011 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    VTOT2011_WS_OUT_DATA WS_OUT_DATA = new VTOT2011_WS_OUT_DATA();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    VTCSJMCD VTCSJMCD = new VTCSJMCD();
    VTRJMCD VTRJMCD = new VTRJMCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCI2011 VTCI2011;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTOT2011 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        VTCI2011 = new VTCI2011();
        IBS.init(SCCGWA, VTCI2011);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, VTCI2011);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_JMCD_PROC();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_JMCD_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCI2011.JM_CODE);
        CEP.TRC(SCCGWA, VTCI2011.CHS_NM);
        CEP.TRC(SCCGWA, VTCI2011.ENG_NM);
        CEP.TRC(SCCGWA, VTCI2011.JM_TYPE);
        CEP.TRC(SCCGWA, VTCI2011.JM_AMT);
        CEP.TRC(SCCGWA, VTCI2011.JM_RAT);
        IBS.init(SCCGWA, VTRJMCD);
        IBS.init(SCCGWA, VTCSJMCD);
        VTCSJMCD.CODE = VTCI2011.JM_CODE;
        VTCSJMCD.CHS_NM = VTCI2011.CHS_NM;
        VTCSJMCD.ENG_NM = VTCI2011.ENG_NM;
        VTCSJMCD.JM_TYPE = VTCI2011.JM_TYPE;
        VTCSJMCD.JM_AMT = VTCI2011.JM_AMT;
        VTCSJMCD.JM_RAT = VTCI2011.JM_RAT;
        VTCSJMCD.RMK = VTCI2011.RMK;
        VTCSJMCD.FUNC = 'A';
        S000_CALL_VTZSJMCD();
    }
    public void S000_CALL_VTZSJMCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-S-JMCD-MAINTAIN", VTCSJMCD);
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
