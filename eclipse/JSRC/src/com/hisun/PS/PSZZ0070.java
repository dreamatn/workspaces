package com.hisun.PS;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZZ0070 {
    String K_EXG_BK_NO = "001";
    String K_EXG_AREA_NO = "000001";
    String K_OUTPUT_FMT = "PS070";
    String CPN_U_PSZWMBKR = "PS-R-MBK-MAT-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    PSCWMBKR PSCWMBKR = new PSCWMBKR();
    PSRPBIN PSRPBIN = new PSRPBIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCEXDEL PSCEXDEL;
    public void MP(SCCGWA SCCGWA, PSCEXDEL PSCEXDEL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCEXDEL = PSCEXDEL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSZZ0070 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCWMBKR);
        PSCWMBKR.EXG_CCY = PSCEXDEL.EXG_CCY;
        PSCWMBKR.EXG_NO = PSCEXDEL.EXG_NO;
        PSCWMBKR.EFF_DT = PSCEXDEL.EXG_EF_DT;
        PSCWMBKR.EXG_NB = PSCEXDEL.EXG_NB;
        PSCWMBKR.EX_INSNM = PSCEXDEL.PBKA_EX_INSNM;
        PSCWMBKR.CLR_EXN = PSCEXDEL.PBKA_CLR_EXN;
        PSCWMBKR.CLR_EXNM = PSCEXDEL.PBKA_CLR_EXNM;
        PSCWMBKR.RMK = PSCEXDEL.RMK;
        PSCWMBKR.EXG_AREA_NO = PSCEXDEL.EXG_AREA_NO;
        CEP.TRC(SCCGWA, PSCWMBKR.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCWMBKR.EXG_NO);
        CEP.TRC(SCCGWA, PSCWMBKR.CLR_EXN);
        CEP.TRC(SCCGWA, PSCWMBKR.EFF_DT);
        S000_CALL_PSZWMBKR();
    }
    public void S000_CALL_PSZWMBKR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZWMBKR, PSCWMBKR);
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
