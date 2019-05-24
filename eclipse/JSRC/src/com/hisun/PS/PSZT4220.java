package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZT4220 {
    int JIBS_tmp_int;
    String EXG_BK_NO = "001";
    String OUTPUT_FMT = "PS420";
    String U_PSZRDBST = "PS-REB-STRZ-PROC";
    char ERROR = 'E';
    PSZT4220_WS_VARIABLES WS_VARIABLES = new PSZT4220_WS_VARIABLES();
    PSCMSG_ERROR_MSG ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCRDBST PSCRDBST = new PSCRDBST();
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
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    BPREWA BPREWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    PSCB4220 PSCB4220;
    public void MP(SCCGWA SCCGWA, PSCB4220 PSCB4220) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCB4220 = PSCB4220;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        CEP.TRC(SCCGWA, "PSZT4220 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        IBS.init(SCCGWA, PSCRDBST);
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, "222");
        CEP.TRC(SCCGWA, PSCB4220.U_EXG_NO);
        CEP.TRC(SCCGWA, PSCB4220.EXG_CCY);
        CEP.TRC(SCCGWA, PSCB4220.REPT_DT);
        CEP.TRC(SCCGWA, PSCB4220.REPT_TMS);
        CEP.TRC(SCCGWA, PSCB4220.RT_DT);
        CEP.TRC(SCCGWA, PSCB4220.RT_TMS);
        CEP.TRC(SCCGWA, PSCB4220.TX_JRNNO);
        CEP.TRC(SCCGWA, PSCB4220.OUR_ACNO);
        CEP.TRC(SCCGWA, PSCB4220.OUR_ACNM);
        CEP.TRC(SCCGWA, PSCB4220.CASH_ID);
        CEP.TRC(SCCGWA, PSCB4220.T_EXG_NO);
        CEP.TRC(SCCGWA, PSCB4220.EXG_DC);
        CEP.TRC(SCCGWA, PSCB4220.VOUCH_CD);
        CEP.TRC(SCCGWA, PSCB4220.CERT_NO);
        CEP.TRC(SCCGWA, PSCB4220.CERT_DT);
        CEP.TRC(SCCGWA, PSCB4220.ISS_BKNO);
        CEP.TRC(SCCGWA, PSCB4220.EXP_DT);
        CEP.TRC(SCCGWA, PSCB4220.ISS_AMT);
        CEP.TRC(SCCGWA, PSCB4220.EXG_AMT);
        CEP.TRC(SCCGWA, PSCB4220.OTH_ACNO);
        CEP.TRC(SCCGWA, PSCB4220.OTH_ACNM);
        CEP.TRC(SCCGWA, PSCB4220.TR_BR);
        PSCRDBST.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = PSCB4220.TR_BR;
        CEP.TRC(SCCGWA, PSCB4220.TR_BR);
        S000_CALL_BPZPQORG();
        WS_VARIABLES.WS_FMT.EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_VARIABLES.WS_FMT.EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_VARIABLES.WS_FMT.EXG_AREA_NO = "0" + WS_VARIABLES.WS_FMT.EXG_AREA_NO;
        PSCRDBST.EXG_AREA_NO = WS_VARIABLES.WS_FMT.EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        CEP.TRC(SCCGWA, PSCRDBST.EXG_AREA_NO);
        PSCRDBST.OUR_EXG_NO = PSCB4220.U_EXG_NO;
        PSCRDBST.EXG_CCY = PSCB4220.EXG_CCY;
        PSCRDBST.EXG_REPT_DT = PSCB4220.REPT_DT;
        PSCRDBST.EXG_REPT_TMS = PSCB4220.REPT_TMS;
        PSCRDBST.EXG_RT_DT = PSCB4220.RT_DT;
        PSCRDBST.EXG_RT_TMS = PSCB4220.RT_TMS;
        PSCRDBST.TX_JRNNO = PSCB4220.TX_JRNNO;
        CEP.TRC(SCCGWA, "333");
        PSCRDBST.OUR_ACNO = PSCB4220.OUR_ACNO;
        PSCRDBST.OUR_ACNM = PSCB4220.OUR_ACNM;
        PSCRDBST.CASH_ID = PSCB4220.CASH_ID;
        PSCRDBST.OTH_EXG_NO = PSCB4220.T_EXG_NO;
        PSCRDBST.EXG_DC = PSCB4220.EXG_DC;
        PSCRDBST.EXG_VOUCH_CD = PSCB4220.VOUCH_CD;
        PSCRDBST.EXG_CERT_NO = PSCB4220.CERT_NO;
        PSCRDBST.CERT_DT = PSCB4220.CERT_DT;
        PSCRDBST.ISS_BKNO = PSCB4220.ISS_BKNO;
        PSCRDBST.EXP_DT = PSCB4220.EXP_DT;
        PSCRDBST.ISS_AMT = PSCB4220.ISS_AMT;
        PSCRDBST.EXG_AMT = PSCB4220.EXG_AMT;
        PSCRDBST.OTH_ACNO = PSCB4220.OTH_ACNO;
        PSCRDBST.OTH_ACNM = PSCB4220.OTH_ACNM;
        PSCRDBST.TR_BR = PSCB4220.TR_BR;
        CEP.TRC(SCCGWA, PSCB4220.U_EXG_NO);
        CEP.TRC(SCCGWA, PSCB4220.EXG_CCY);
        CEP.TRC(SCCGWA, PSCB4220.REPT_DT);
        CEP.TRC(SCCGWA, PSCB4220.REPT_TMS);
        CEP.TRC(SCCGWA, PSCB4220.RT_DT);
        CEP.TRC(SCCGWA, PSCB4220.RT_TMS);
        CEP.TRC(SCCGWA, PSCB4220.TX_JRNNO);
        CEP.TRC(SCCGWA, PSCB4220.OUR_ACNO);
        CEP.TRC(SCCGWA, PSCB4220.OUR_ACNM);
        CEP.TRC(SCCGWA, PSCB4220.CASH_ID);
        CEP.TRC(SCCGWA, PSCB4220.T_EXG_NO);
        CEP.TRC(SCCGWA, PSCB4220.EXG_DC);
        CEP.TRC(SCCGWA, PSCB4220.VOUCH_CD);
        CEP.TRC(SCCGWA, PSCB4220.CERT_NO);
        CEP.TRC(SCCGWA, PSCB4220.CERT_DT);
        CEP.TRC(SCCGWA, PSCB4220.ISS_BKNO);
        CEP.TRC(SCCGWA, PSCB4220.EXP_DT);
        CEP.TRC(SCCGWA, PSCB4220.ISS_AMT);
        CEP.TRC(SCCGWA, PSCB4220.EXG_AMT);
        CEP.TRC(SCCGWA, PSCB4220.OTH_ACNO);
        CEP.TRC(SCCGWA, PSCB4220.OTH_ACNM);
        S000_CALL_PSZRDBST();
    }
    public void S000_CALL_PSZRDBST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_PSZRDBST, PSCRDBST);
        if (PSCRDBST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, PSCRDBST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}