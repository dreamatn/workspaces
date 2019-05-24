package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8052 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    AICSGPBR AICSGPBR = new AICSGPBR();
    SCCGWA SCCGWA;
    AIB8051_AWA_8051 AIB8051_AWA_8051;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT8052 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8051_AWA_8051>");
        AIB8051_AWA_8051 = (AIB8051_AWA_8051) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GL_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB8051_AWA_8051.EXP_DATE <= AIB8051_AWA_8051.EFF_DATE) {
            WS_MSG_ERR = "BP1561";
            WS_FLD_NO = AIB8051_AWA_8051.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GL_TRANSFER_PROC() throws IOException,SQLException,Exception {
        if (AIB8051_AWA_8051.FUNC == 'M') {
            R000_TRANS_DATA_PARAM();
            S000_CALL_AIZSGPBR();
        }
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSGPBR);
        AICSGPBR.DATA.KEY.CD = AIB8051_AWA_8051.CD;
        IBS.CPY2CLS(SCCGWA, AICSGPBR.DATA.KEY.CD, AICSGPBR.DATA.KEY.REDEFINES13);
        AICSGPBR.DATA.EFF_DATE = AIB8051_AWA_8051.EFF_DATE;
        CEP.TRC(SCCGWA, AIB8051_AWA_8051.CD);
        CEP.TRC(SCCGWA, AIB8051_AWA_8051.EFF_DATE);
        AICSGPBR.INFO.FUNC = 'I';
    }
    public void S000_CALL_AIZSGPBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAINT-GPBR", AICSGPBR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
