package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5433 {
    String K_STS_TABLE_APP = "CI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CIOT5433_WS_T_CTL WS_T_CTL = new CIOT5433_WS_T_CTL();
    int WS_T_ID = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMCNT CICMCNT = new CICMCNT();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICSSEAW CICSSEAW = new CICSSEAW();
    CICMSTS CICMSTS = new CICMSTS();
    CIRCNT CIRCNT = new CIRCNT();
    SCCGWA SCCGWA;
    BPRTRT BPRTRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5430_AWA_5430 CIB5430_AWA_5430;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5433 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5430_AWA_5430>");
        CIB5430_AWA_5430 = (CIB5430_AWA_5430) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTRT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        IBS.CPY2CLS(SCCGWA, BPRTRT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMCNT);
        CICMCNT.CI_NO = CIB5430_AWA_5430.CI_NO;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_TYPE = CIB5430_AWA_5430.CNT_TYP;
        CICMCNT.DATA.SINGLE_DATA.S_SRC_NO = CIB5430_AWA_5430.SRC_NO;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_CNTY = CIB5430_AWA_5430.CNT_CNTY;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_ZONE = CIB5430_AWA_5430.CNT_ZONE;
        CICMCNT.DATA.SINGLE_DATA.S_TEL_NO = CIB5430_AWA_5430.TEL_NO;
        CICMCNT.DATA.SINGLE_DATA.S_TEL_NO1 = CIB5430_AWA_5430.TEL_NO1;
        CICMCNT.DATA.SINGLE_DATA.S_CNT_INFO = CIB5430_AWA_5430.CNT_INFO;
        CICMCNT.DATA.SINGLE_DATA.S_REMARK = CIB5430_AWA_5430.REMARK;
        CICMCNT.FUNC = 'M';
        S000_LINK_CIZMCNT();
        if (CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("13") 
            || CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("15") 
            || CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("16")) {
            IBS.init(SCCGWA, CICMSTS);
            CICMSTS.FUNC = 'C';
            CICMSTS.DATA.CI_NO = CIB5430_AWA_5430.CI_NO;
            S000_LINK_CIZMSTS();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5430_AWA_5430.CNT_TYP.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_CNT_TYPE_IS_EMPTY;
            WS_FLD_NO = CIB5430_AWA_5430.CNT_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("19") 
            && CIB5430_AWA_5430.REMARK.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5430_AWA_5430.REMARK_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5430_AWA_5430.CNT_INFO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_CNT_INFO_IS_EMPTY;
            WS_FLD_NO = CIB5430_AWA_5430.CNT_INFO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("11") 
            || CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("12") 
            || CIB5430_AWA_5430.CNT_TYP.equalsIgnoreCase("14")) 
            && (CIB5430_AWA_5430.TEL_NO.trim().length() == 0 
            || CIB5430_AWA_5430.CNT_ZONE.trim().length() == 0)) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            if (CIB5430_AWA_5430.TEL_NO.trim().length() == 0) {
                WS_FLD_NO = CIB5430_AWA_5430.TEL_NO_NO;
            } else {
                WS_FLD_NO = CIB5430_AWA_5430.CNT_ZONE_NO;
            }
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS, true);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
    }
    public void S000_LINK_CIZMCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CNT ", CICMCNT);
        if (CICMCNT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMCNT.RC);
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
