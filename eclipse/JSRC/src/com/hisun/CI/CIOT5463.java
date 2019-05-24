package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5463 {
    String K_IDT_CHK = "02";
    char K_DAL_WAY = '1';
    String K_STS_TABLE_APP = "CI";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CIOT5463_WS_T_CTL WS_T_CTL = new CIOT5463_WS_T_CTL();
    int WS_T_ID = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSVER CICSVER = new CICSVER();
    CICSSEAW CICSSEAW = new CICSSEAW();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCGWA SCCGWA;
    BPRTRT BPRTRT;
    CIB5463_AWA_5463 CIB5463_AWA_5463;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5463 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5463_AWA_5463>");
        CIB5463_AWA_5463 = (CIB5463_AWA_5463) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTRT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        CEP.TRC(SCCGWA, BPRTRT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL);
        IBS.CPY2CLS(SCCGWA, BPRTRT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PGM_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CEP.TRC(SCCGWA, CIB5463_AWA_5463.IDT_CHK);
        CEP.TRC(SCCGWA, CICSVER.CI_DATE.IDT_CHK);
        CICSVER.CI_DATE.CI_NO = CIB5463_AWA_5463.CI_NO;
        CICSVER.CI_DATE.REAL_CHK = CIB5463_AWA_5463.REAL_CHK;
        CICSVER.CI_DATE.IDT_CHK = CIB5463_AWA_5463.IDT_CHK;
        CICSVER.CI_DATE.RSN = CIB5463_AWA_5463.RSN;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY1 = CIB5463_AWA_5463.DAL_WAY1;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY2 = CIB5463_AWA_5463.DAL_WAY2;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY3 = CIB5463_AWA_5463.DAL_WAY3;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY4 = CIB5463_AWA_5463.DAL_WAY4;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY5 = CIB5463_AWA_5463.DAL_WAY5;
        CICSVER.CI_DATE.DEAL_WAY.DAL_WAY6 = CIB5463_AWA_5463.DAL_WAY6;
        CICSVER.CI_DATE.DESC = CIB5463_AWA_5463.DESC;
        CICSVER.FUNC = 'M';
        B020_CALL_CIZSVER();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB5463_AWA_5463.DAL_WAY6 == K_DAL_WAY 
            && CIB5463_AWA_5463.DESC.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR;
            WS_ERR_INFO = "DESC IS SPACE";
            WS_FLD_NO = CIB5463_AWA_5463.DESC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
        IBS.init(SCCGWA, CICSSEAW);
        IBS.init(SCCGWA, BPCFCSTS);
        CICSSEAW.DATA.CI_NO = CIB5463_AWA_5463.CI_NO;
        CEP.TRC(SCCGWA, CIB5463_AWA_5463.CI_NO);
        S000_CALL_CIZSSEAW();
        CEP.TRC(SCCGWA, CICSSEAW.RC.RC_CODE);
        if (CICSSEAW.RC.RC_CODE == 0) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            BPCFCSTS.TBL_NO = WS_T_CTL.WS_T_C1;
            BPCFCSTS.STATUS_WORD = IBS.CLS2CPY(SCCGWA, CICSSEAW.DATA.STS_DATA);
            CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
            CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            S000_CALL_BPZFCSTS();
        }
    }
    public void B020_CALL_CIZSVER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-VER-CIZSVER", CICSVER);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
    }
    public void S000_CALL_CIZSSEAW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GET-STS", CICSSEAW);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
