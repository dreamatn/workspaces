package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8520 {
    DBParm CITACR_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ENTY_NO = " ";
    char WS_END_FLAG = ' ';
    char WS_ACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSHZAC CICSHZAC = new CICSHZAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CIRACR CIRACR = new CIRACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8500_AWA_8500 CIB8500_AWA_8500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8520 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8500_AWA_8500>");
        CIB8500_AWA_8500 = (CIB8500_AWA_8500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8500_AWA_8500.AC_NO);
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSHZAC);
        CEP.TRC(SCCGWA, CIB8500_AWA_8500.CI_NO);
        CEP.TRC(SCCGWA, CIB8500_AWA_8500.AC_NO);
        CEP.TRC(SCCGWA, CIB8500_AWA_8500.ACCI_FLG);
        CICSHZAC.AC_NO = WS_ENTY_NO;
        CEP.TRC(SCCGWA, WS_ENTY_NO);
        CICSHZAC.CI_NO = CIB8500_AWA_8500.CI_NO;
        CICSHZAC.ACCI_FLG = CIB8500_AWA_8500.ACCI_FLG;
        CICSHZAC.OPT = 'A';
        S000_LINK_CIZSHZAC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8500_AWA_8500.CI_NO.trim().length() == 0 
            && CIB8500_AWA_8500.ACCI_FLG == 'C') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8500_AWA_8500.AC_NO.trim().length() == 0 
            && CIB8500_AWA_8500.ACCI_FLG == 'A') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_AC_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8500_AWA_8500.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            IBS.init(SCCGWA, DCCPACTY);
            CIRACR.KEY.AGR_NO = CIB8500_AWA_8500.AC_NO;
            T000_READ_CITACR_AGR_NO();
            if (WS_ACR_FLG == 'Y') {
                WS_ENTY_NO = CIB8500_AWA_8500.AC_NO;
            } else {
                DCCPACTY.INPUT.AC = CIB8500_AWA_8500.AC_NO;
                S000_CALL_DCZPACTY();
                if (DCCPACTY.OUTPUT.STD_AC.trim().length() > 0) {
                    IBS.init(SCCGWA, CIRACR);
                    CIRACR.KEY.AGR_NO = DCCPACTY.OUTPUT.STD_AC;
                    T000_READ_CITACR_AGR_NO();
                    if (WS_ACR_FLG == 'Y') {
                        WS_ENTY_NO = CIRACR.KEY.AGR_NO;
                    } else {
                        WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                        WS_FLD_NO = CIB8500_AWA_8500.AC_NO_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                } else {
                    WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND;
                    WS_FLD_NO = CIB8500_AWA_8500.AC_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void T000_READ_CITACR_AGR_NO() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_LINK_CIZSHZAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-HZAC", CICSHZAC);
        if (CICSHZAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSHZAC.RC);
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
