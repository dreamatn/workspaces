package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP09 {
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB0009_AWA_0009 AIB0009_AWA_0009;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP09 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0009_AWA_0009>");
        AIB0009_AWA_0009 = (AIB0009_AWA_0009) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0009_AWA_0009.DELDATE);
        CEP.TRC(SCCGWA, AIB0009_AWA_0009.PUGFUNC);
        CEP.TRC(SCCGWA, AIB0009_AWA_0009.COAFLAG);
        CEP.TRC(SCCGWA, AIB0009_AWA_0009.COANO);
        if (AIB0009_AWA_0009.DELDATE_NO != 0 
            && AIB0009_AWA_0009.DELDATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = AIB0009_AWA_0009.DELDATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
                WS_FLD_NO = AIB0009_AWA_0009.DELDATE_NO;
                S00_ERR_MSG_PROC();
            }
            if (AIB0009_AWA_0009.DELDATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DELDT_MUST_GE_ACDT;
                WS_FLD_NO = AIB0009_AWA_0009.DELDATE_NO;
                S00_ERR_MSG_PROC();
            }
        }
        CEP.ERR(SCCGWA);
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
