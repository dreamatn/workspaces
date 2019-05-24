package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT8110 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS811";
    String CPN_U_PSZRQRYD = "PS-P-DTL-QRY-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_EXG_AREA_NO = " ";
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCRQRYD PSCRQRYD = new PSCRQRYD();
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
    PSB8110_AWA_8110 PSB8110_AWA_8110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSOT8110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB8110_AWA_8110>");
        PSB8110_AWA_8110 = (PSB8110_AWA_8110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCRQRYD);
        IBS.init(SCCGWA, BPCPQORG);
        PSCRQRYD.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_EXG_AREA_NO = "0" + WS_EXG_AREA_NO;
        PSCRQRYD.EXG_AREA_NO = WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSCRQRYD.EXG_DT = PSB8110_AWA_8110.EXG_DT;
        PSCRQRYD.EXG_TMS = PSB8110_AWA_8110.EXG_TMS;
        PSCRQRYD.EXG_JRNNO = PSB8110_AWA_8110.EG_JRNNO;
        CEP.TRC(SCCGWA, PSB8110_AWA_8110.EXG_DT);
        CEP.TRC(SCCGWA, PSB8110_AWA_8110.EXG_TMS);
        CEP.TRC(SCCGWA, PSB8110_AWA_8110.EG_JRNNO);
        S000_CALL_PSZRQRYD();
    }
    public void S000_CALL_PSZRQRYD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZRQRYD, PSCRQRYD);
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
