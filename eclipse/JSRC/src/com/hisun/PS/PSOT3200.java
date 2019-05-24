package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT3200 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS320";
    String CPN_U_PSZTSWHP = "PS-P-TIM-SWH-PROC";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSOT3200_WS_FMT WS_FMT = new PSOT3200_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCTSWHP PSCTSWHP = new PSCTSWHP();
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
    PSB3200_AWA_3200 PSB3200_AWA_3200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        B300_OUTPUT_PROC();
        CEP.TRC(SCCGWA, "PSOT3200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB3200_AWA_3200>");
        PSB3200_AWA_3200 = (PSB3200_AWA_3200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCTSWHP);
        IBS.init(SCCGWA, BPCPQORG);
        PSCTSWHP.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_FMT.WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_FMT.WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_FMT.WS_EXG_AREA_NO = "0" + WS_FMT.WS_EXG_AREA_NO;
        PSCTSWHP.EXG_AREA_NO = WS_FMT.WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSCTSWHP.OUR_EXG_NO = PSB3200_AWA_3200.EXG_NO;
        PSCTSWHP.EXG_DT = PSB3200_AWA_3200.EXG_DT;
        PSCTSWHP.EXG_TMS = PSB3200_AWA_3200.EXG_TMS;
        PSCTSWHP.EXG_CCY = PSB3200_AWA_3200.EXG_CCY;
        PSCTSWHP.NEXT_EXG_DT = PSB3200_AWA_3200.N_EXG_DT;
        PSCTSWHP.NEXT_EXG_TMS = PSB3200_AWA_3200.N_EG_TMS;
        CEP.TRC(SCCGWA, PSB3200_AWA_3200.N_EG_TMS);
        CEP.TRC(SCCGWA, PSCTSWHP.NEXT_EXG_TMS);
        S000_CALL_PSZTSWHP();
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_OUR_EXG_NO = PSB3200_AWA_3200.EXG_NO;
        WS_FMT.WS_EXG_DT = PSB3200_AWA_3200.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSB3200_AWA_3200.EXG_TMS;
        WS_FMT.WS_EXG_CCY = PSB3200_AWA_3200.EXG_CCY;
        WS_FMT.WS_NEXT_EXG_DT = PSB3200_AWA_3200.N_EXG_DT;
        WS_FMT.WS_NEXT_EXG_TMS = PSB3200_AWA_3200.N_EG_TMS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 43;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_PSZTSWHP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZTSWHP, PSCTSWHP);
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
