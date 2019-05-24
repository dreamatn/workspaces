package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5425 {
    String K_STS_TABLE_APP = "CI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CIOT5425_WS_T_CTL WS_T_CTL = new CIOT5425_WS_T_CTL();
    int WS_T_ID = 0;
    char WS_END_FLAG = ' ';
    char WS_ADR_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMADR CICMADR = new CICMADR();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICSSEAW CICSSEAW = new CICSSEAW();
    CICMSTS CICMSTS = new CICMSTS();
    CIRADR CIRADR = new CIRADR();
    SCCGWA SCCGWA;
    BPRTRT BPRTRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5420_AWA_5420 CIB5420_AWA_5420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5425 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5420_AWA_5420>");
        CIB5420_AWA_5420 = (CIB5420_AWA_5420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTRT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        IBS.CPY2CLS(SCCGWA, BPRTRT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMADR);
        B010_CHECK_INPUT_DATA();
        CICMADR.CI_NO = CIB5420_AWA_5420.CI_NO;
        CICMADR.ADR_TYPE = CIB5420_AWA_5420.ADR_TYPE;
        CEP.TRC(SCCGWA, CIB5420_AWA_5420.ADR_TYPE);
        CEP.TRC(SCCGWA, CICMADR.ADR_TYPE);
        CICMADR.DEFA_FLG = CIB5420_AWA_5420.DEFA_FLG;
        CICMADR.SRC_NO = CIB5420_AWA_5420.SRC_NO;
        CICMADR.LANG_CD = CIB5420_AWA_5420.LANG_CD;
        CICMADR.STD_FLG = CIB5420_AWA_5420.STD_FLG;
        CICMADR.LADDR_L4 = CIB5420_AWA_5420.LADDR_L4;
        CICMADR.LADDR_L3 = CIB5420_AWA_5420.LADDR_L3;
        CICMADR.LADDR_L2 = CIB5420_AWA_5420.LADDR_L2;
        CICMADR.LADDR_L1 = CIB5420_AWA_5420.LADDR_L1;
        CICMADR.ADR_NM = CIB5420_AWA_5420.ADR_NM;
        CICMADR.CNTY_CD = CIB5420_AWA_5420.CNTY_CD;
        CICMADR.POST_CD = CIB5420_AWA_5420.POST_CD;
        CICMADR.FUNC = 'D';
        S000_LINK_CIZMADR();
        IBS.init(SCCGWA, CICMSTS);
        CICMSTS.FUNC = 'C';
        CICMSTS.DATA.CI_NO = CIB5420_AWA_5420.CI_NO;
        S000_LINK_CIZMSTS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5420_AWA_5420.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5420_AWA_5420.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5420_AWA_5420.LANG_CD.equalsIgnoreCase("88") 
            && CIB5420_AWA_5420.STD_FLG == 'Y') {
            CICMADR.STD_CD = "88";
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS, true);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
    }
    public void S000_LINK_CIZMADR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ADR ", CICMADR);
        if (CICMADR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMADR.RC);
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
    }
    public void S000_CALL_CIZSSEAW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GET-STS", CICSSEAW);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
