package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT1210 {
    CMOT1210_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMOT1210_WS_TEMP_VARIABLE();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CMCSDJDD CMCSDJDD = new CMCSDJDD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB1210_AWA_1210 CMB1210_AWA_1210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT1210 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB1210_AWA_1210>");
        CMB1210_AWA_1210 = (CMB1210_AWA_1210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, CMB1210_AWA_1210.TX_ID);
        CEP.TRC(SCCGWA, CMB1210_AWA_1210.SYS_ID);
        CEP.TRC(SCCGWA, CMB1210_AWA_1210.TX_DATE);
        CEP.TRC(SCCGWA, CMB1210_AWA_1210.VCH_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_MAIN_PROC();
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSDJDD);
        CMCSDJDD.SYS_ID = CMB1210_AWA_1210.SYS_ID;
        CMCSDJDD.TX_DATE = CMB1210_AWA_1210.TX_DATE;
        CMCSDJDD.VCH_NO = CMB1210_AWA_1210.VCH_NO;
        S000_CALL_CMZSDJDD();
    }
    public void S000_CALL_CMZSDJDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-DJDD-POST", CMCSDJDD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
