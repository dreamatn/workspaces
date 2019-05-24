package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5812 {
    String CPN_S_CVBR_MAIN = "AI-S-MAIN-CVBR";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSCVBR AICSCVBR = new AICSCVBR();
    SCCGWA SCCGWA;
    SCCAWAC SCCAWAC;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5812_AWA_5812 AIB5812_AWA_5812;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5812 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5812_AWA_5812>");
        AIB5812_AWA_5812 = (AIB5812_AWA_5812) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_UPD_REC_PROC();
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCVBR);
        AICSCVBR.FUNC = 'A';
        AICSCVBR.OAC_NO = AIB5812_AWA_5812.OAC_NO;
        AICSCVBR.OBR = AIB5812_AWA_5812.OBR;
        AICSCVBR.OCCY = AIB5812_AWA_5812.OCCY;
        AICSCVBR.OITM = AIB5812_AWA_5812.OITM;
        AICSCVBR.OSEQ = AIB5812_AWA_5812.OSEQ;
        AICSCVBR.AC_NO = AIB5812_AWA_5812.AC_NO;
        AICSCVBR.BR = AIB5812_AWA_5812.BR;
        AICSCVBR.CCY = AIB5812_AWA_5812.CCY;
        AICSCVBR.ITM = AIB5812_AWA_5812.ITM;
        AICSCVBR.SEQ = AIB5812_AWA_5812.SEQ;
        S000_CALL_AIZSCVBR();
    }
    public void S000_CALL_AIZSCVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CVBR_MAIN, AICSCVBR);
        if (AICSCVBR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSCVBR.RC);
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
