package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT4300 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS430";
    String CPN_U_PSZSQRTD = "PS-P-QRT-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_EXG_AREA_NO = " ";
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCSQRTD PSCSQRTD = new PSCSQRTD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB4300_AWA_4300 PSB4300_AWA_4300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSOT4300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB4300_AWA_4300>");
        PSB4300_AWA_4300 = (PSB4300_AWA_4300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.EXG_B_DT);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.EXG_E_DT);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.EXG_TMS);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.REPT_DT);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.REPT_TMS);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.EXG_CCY);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.DC_FLG);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.OUR_NO);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.PAGE_ROW);
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.PAGE_NUM);
        IBS.init(SCCGWA, PSCSQRTD);
        IBS.init(SCCGWA, BPCPQORG);
        PSCSQRTD.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_EXG_AREA_NO = "0" + WS_EXG_AREA_NO;
        PSCSQRTD.EXG_AREA_NO = WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSCSQRTD.EXG_B_DT = PSB4300_AWA_4300.EXG_B_DT;
        PSCSQRTD.EXG_E_DT = PSB4300_AWA_4300.EXG_E_DT;
        PSCSQRTD.EXG_TMS = PSB4300_AWA_4300.EXG_TMS;
        PSCSQRTD.EXG_REPT_DT = PSB4300_AWA_4300.REPT_DT;
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.REPT_DT);
        PSCSQRTD.EXG_REPT_TMS = PSB4300_AWA_4300.REPT_TMS;
        CEP.TRC(SCCGWA, PSB4300_AWA_4300.REPT_TMS);
        PSCSQRTD.EXG_CCY = PSB4300_AWA_4300.EXG_CCY;
        PSCSQRTD.EXG_DC_FLG = PSB4300_AWA_4300.DC_FLG;
        PSCSQRTD.OUR_EXG_NO = PSB4300_AWA_4300.OUR_NO;
        PSCSQRTD.PAGE_ROW = PSB4300_AWA_4300.PAGE_ROW;
        PSCSQRTD.PAGE_NUM = PSB4300_AWA_4300.PAGE_NUM;
        S000_CALL_PSZSQRTD();
    }
    public void S000_CALL_PSZSQRTD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZSQRTD, PSCSQRTD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
