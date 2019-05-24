package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT0010 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS010";
    String CPN_U_PSZWMBKP = "PS-P-MBK-MAT-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    PSCWMBKP PSCWMBKP = new PSCWMBKP();
    PSRPBIN PSRPBIN = new PSRPBIN();
    String WS_BK_NO = " ";
    String WS_AREA_NO = " ";
    String WS_CCY = " ";
    int WS_TX_BR = 0;
    String WS_EXG_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB0010_AWA_0010 PSB0010_AWA_0010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSOT0010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB0010_AWA_0010>");
        PSB0010_AWA_0010 = (PSB0010_AWA_0010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCWMBKP);
        IBS.init(SCCGWA, BPCPQORG);
        PSCWMBKP.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSCWMBKP.FUNC_CD = PSB0010_AWA_0010.FUNC_CD;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_AREA_NO = "0" + WS_AREA_NO;
        PSCWMBKP.EXG_AREA_NO = WS_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSCWMBKP.EXG_CCY = PSB0010_AWA_0010.EXG_CCY;
        PSCWMBKP.EXG_TX_BR = PSB0010_AWA_0010.TX_BR;
        CEP.TRC(SCCGWA, PSB0010_AWA_0010.TX_BR);
        CEP.TRC(SCCGWA, PSCWMBKP.EXG_TX_BR);
        PSCWMBKP.EXG_INSNM = PSB0010_AWA_0010.INSNM;
        PSCWMBKP.EXG_SYS_STS = PSB0010_AWA_0010.SYS_STS;
        PSCWMBKP.EXG_NO = PSB0010_AWA_0010.EXG_NO;
        PSCWMBKP.EXG_DT = PSB0010_AWA_0010.EXG_DT;
        PSCWMBKP.EXG_TMS = PSB0010_AWA_0010.EXG_TMS;
        PSCWMBKP.EXG_PRE_DT = PSB0010_AWA_0010.PRE_DT;
        PSCWMBKP.EXG_PRE_TMS = PSB0010_AWA_0010.PRE_TMS;
        PSCWMBKP.EXG_BOOK_BR = PSB0010_AWA_0010.BOOK_BR;
        PSCWMBKP.EXG_CLR_BR = PSB0010_AWA_0010.CLR_BR;
        PSCWMBKP.EXG_JOIN_TMS = PSB0010_AWA_0010.JOIN_TMS;
        PSCWMBKP.EXG_CLR_AC = PSB0010_AWA_0010.CLR_AC;
        PSCWMBKP.EXG_YEND_TMS = PSB0010_AWA_0010.YEND_TMS;
        PSCWMBKP.RMK = PSB0010_AWA_0010.RMK;
        S000_CALL_PSZWMBKP();
    }
    public void S000_CALL_PSZWMBKP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZWMBKP, PSCWMBKP);
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
