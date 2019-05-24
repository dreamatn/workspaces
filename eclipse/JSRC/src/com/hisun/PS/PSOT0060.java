package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT0060 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS060";
    String CPN_U_PSZPQRYP = "PS-P-PBK-QRY-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCPQRYP PSCPQRYP = new PSCPQRYP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    PSRPBIN PSRPBIN = new PSRPBIN();
    String WS_BK_NO = " ";
    String WS_AREA_NO = " ";
    String WS_CCY = " ";
    int WS_TX_BR = 0;
    String WS_EXG_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB0060_AWA_0060 PSB0060_AWA_0060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSOT0060 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB0060_AWA_0060>");
        PSB0060_AWA_0060 = (PSB0060_AWA_0060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCPQRYP);
        IBS.init(SCCGWA, BPCPQORG);
        PSCPQRYP.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSCPQRYP.EXG_NO = PSB0060_AWA_0060.EXG_NO;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_AREA_NO = "0" + WS_AREA_NO;
        PSCPQRYP.EXG_AREA_NO = WS_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        S000_CALL_PSZPQRYP();
    }
    public void S000_CALL_PSZPQRYP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZPQRYP, PSCPQRYP);
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
