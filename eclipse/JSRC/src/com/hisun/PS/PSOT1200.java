package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT1200 {
    int JIBS_tmp_int;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS120";
    String CPN_U_PSZOTDTP = "PS-P-OTDT-PROC";
    char K_ERROR = 'E';
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSOT1200_WS_FMT WS_FMT = new PSOT1200_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCOTDTP PSCOTDTP = new PSCOTDTP();
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
    PSB1200_AWA_1200 PSB1200_AWA_1200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B200_CLR_PNT_PROC();
        B300_OUTPUT_PROC();
        CEP.TRC(SCCGWA, "PSOT1200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB1200_AWA_1200>");
        PSB1200_AWA_1200 = (PSB1200_AWA_1200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B200_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CLR_PNT_PROC();
    }
    public void B210_CLR_PNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.AREA_NO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXG_DT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXG_TMS);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.AC_TYP);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.S_EXG_DT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.S_EXG_TS);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXG_NO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EX_INSNM);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.OTH_ACNO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.OTH_ACNM);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXG_CCY);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.CASH_ID);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXG_AMT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.VOUCH_CD);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.CERT_NO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.ISS_BKNO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.ENCRY_NO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.ISS_AMT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.M_AMT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.CERT_DT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.EXP_DT);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.OUR_ACNO);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.OUR_ACNM);
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.RMK);
        IBS.init(SCCGWA, PSCOTDTP);
        IBS.init(SCCGWA, BPCPQORG);
        PSCOTDTP.BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_FMT.WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_FMT.WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_FMT.WS_EXG_AREA_NO = "0" + WS_FMT.WS_EXG_AREA_NO;
        PSCOTDTP.EXG_AREA_NO = WS_FMT.WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        PSCOTDTP.EXG_DT = PSB1200_AWA_1200.EXG_DT;
        PSCOTDTP.EXG_TMS = PSB1200_AWA_1200.EXG_TMS;
        PSCOTDTP.AC_TYP = PSB1200_AWA_1200.AC_TYP;
        PSCOTDTP.SHL_EXG_DT = PSB1200_AWA_1200.S_EXG_DT;
        PSCOTDTP.SHL_EXG_TMS = PSB1200_AWA_1200.S_EXG_TS;
        PSCOTDTP.OTH_EXG_NO = PSB1200_AWA_1200.EXG_NO;
        PSCOTDTP.PBKA_EX_INSNM = PSB1200_AWA_1200.EX_INSNM;
        PSCOTDTP.OTH_ACNO = PSB1200_AWA_1200.OTH_ACNO;
        PSCOTDTP.OTH_ACNM = PSB1200_AWA_1200.OTH_ACNM;
        PSCOTDTP.EXG_CCY = PSB1200_AWA_1200.EXG_CCY;
        PSCOTDTP.CASH_ID = PSB1200_AWA_1200.CASH_ID;
        PSCOTDTP.EXG_AMT = PSB1200_AWA_1200.EXG_AMT;
        PSCOTDTP.EXG_VOUCH_CD = PSB1200_AWA_1200.VOUCH_CD;
        PSCOTDTP.EXG_CERT_NO = PSB1200_AWA_1200.CERT_NO;
        PSCOTDTP.ISS_BKNO = PSB1200_AWA_1200.ISS_BKNO;
        PSCOTDTP.ENCRY_NO = PSB1200_AWA_1200.ENCRY_NO;
        PSCOTDTP.ISS_AMT = PSB1200_AWA_1200.ISS_AMT;
        PSCOTDTP.M_AMT = PSB1200_AWA_1200.M_AMT;
        PSCOTDTP.CERT_DT = PSB1200_AWA_1200.CERT_DT;
        PSCOTDTP.EXP_DT = PSB1200_AWA_1200.EXP_DT;
        PSCOTDTP.OUR_ACNO = PSB1200_AWA_1200.OUR_ACNO;
        PSCOTDTP.OUR_ACNM = PSB1200_AWA_1200.OUR_ACNM;
        PSCOTDTP.RMK = PSB1200_AWA_1200.RMK;
        PSCOTDTP.DC = 'D';
        S000_CALL_PSROTDTP();
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSB1200_AWA_1200.AREA_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_EXG_AREA_NO);
        WS_FMT.WS_EXG_DT = PSB1200_AWA_1200.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSB1200_AWA_1200.EXG_TMS;
        WS_FMT.WS_SHL_EXG_DT = PSB1200_AWA_1200.S_EXG_DT;
        WS_FMT.WS_SHL_EXG_TMS = PSB1200_AWA_1200.S_EXG_TS;
        WS_FMT.WS_OTH_EXG_NO = PSB1200_AWA_1200.EXG_NO;
        WS_FMT.WS_PBK_EX_INSNM = PSB1200_AWA_1200.EX_INSNM;
        WS_FMT.WS_OTH_ACNO = PSB1200_AWA_1200.OTH_ACNO;
        WS_FMT.WS_OTH_ACNM = PSB1200_AWA_1200.OTH_ACNM;
        WS_FMT.WS_EXG_CCY = PSB1200_AWA_1200.EXG_CCY;
        WS_FMT.WS_CASH_ID = PSB1200_AWA_1200.CASH_ID;
        WS_FMT.WS_EXG_AMT = PSB1200_AWA_1200.EXG_AMT;
        WS_FMT.WS_EXG_VOUCH_CD = PSB1200_AWA_1200.VOUCH_CD;
        WS_FMT.WS_EXG_CERT_NO = PSB1200_AWA_1200.CERT_NO;
        WS_FMT.WS_ISS_BKNO = PSB1200_AWA_1200.ISS_BKNO;
        WS_FMT.WS_ENCRY_NO = PSB1200_AWA_1200.ENCRY_NO;
        WS_FMT.WS_ISS_AMT = PSB1200_AWA_1200.ISS_AMT;
        WS_FMT.WS_M_AMT = PSB1200_AWA_1200.M_AMT;
        WS_FMT.WS_CERT_DT = PSB1200_AWA_1200.CERT_DT;
        WS_FMT.WS_EXP_DT = PSB1200_AWA_1200.EXP_DT;
        WS_FMT.WS_OUR_ACNO = PSB1200_AWA_1200.OUR_ACNO;
        WS_FMT.WS_OUR_ACNM = PSB1200_AWA_1200.OUR_ACNM;
        WS_FMT.WS_RMK = PSB1200_AWA_1200.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 1125;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_PSROTDTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PSZOTDTP, PSCOTDTP);
        CEP.TRC(SCCGWA, PSCOTDTP.RC);
        if (PSCOTDTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PSCOTDTP.RC);
            S000_ERR_MSG_PROC();
        }
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