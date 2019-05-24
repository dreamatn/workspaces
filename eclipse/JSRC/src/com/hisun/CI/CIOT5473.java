package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5473 {
    DBParm CITACR_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    String WS_ENTY_NO = " ";
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSACR CICSACR = new CICSACR();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5470_AWA_5470 CIB5470_AWA_5470;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT5473 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5470_AWA_5470>");
        CIB5470_AWA_5470 = (CIB5470_AWA_5470) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_TYP);
            CEP.TRC(SCCGWA, CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_NO);
            CEP.TRC(SCCGWA, CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_CNM);
            CEP.TRC(SCCGWA, CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_ENM);
            if (CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_TYP != ' ' 
                || CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_NO.trim().length() > 0 
                || CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_CNM.trim().length() > 0 
                || CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_ENM.trim().length() > 0) {
                B010_CHECK_INPUT_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CICSACR);
                CICSACR.DATA.ENTY_TYP = CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_TYP;
                CICSACR.DATA.AGR_NO = CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_NO;
                CICSACR.DATA.AC_CNM = CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_CNM;
                CICSACR.DATA.AC_ENM = CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_ENM;
                CICSACR.FUNC = 'M';
                IBS.init(SCCGWA, CIRACR);
                IBS.init(SCCGWA, CIRACRO);
                CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                if (CIRACR.STS != '0') {
                    CEP.TRC(SCCGWA, CIRACR.STS);
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
                S000_LINK_CIZSACR();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRACR);
                IBS.init(SCCGWA, CIRACRN);
                CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
                BPCPNHIS.INFO.TX_TYP = 'A';
                R000_WRT_HIS_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_TYP == ' ') {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB5470_AWA_5470.AC_GRPS[WS_I-1].ENTY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_CNM.trim().length() == 0 
            && CIB5470_AWA_5470.AC_GRPS[WS_I-1].AC_ENM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = CICSACR.DATA.AGR_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.CI_NO = CICSACR.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_LINK_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACR.RC);
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
