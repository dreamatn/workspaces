package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4532 {
    String CPN_S_RPNG_MAINT = "AI-S-RPNG-MAINT ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSRPNG AICSRPNG = new AICSRPNG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4530_AWA_4530 AIB4530_AWA_4530;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4532 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4530_AWA_4530>");
        AIB4530_AWA_4530 = (AIB4530_AWA_4530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_UPD_REC_PROC();
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSRPNG);
        AICSRPNG.I_FUNC = 'P';
        AICSRPNG.INFO.RPT_ID = AIB4530_AWA_4530.RPT_ID;
        AICSRPNG.INFO.RPT_NAME = AIB4530_AWA_4530.RPT_NAME;
        AICSRPNG.INFO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S00_CALL_AIZSRPNG();
    }
    public void S00_CALL_AIZSRPNG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_RPNG_MAINT, AICSRPNG);
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
