package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9007 {
    DBParm CITACR_RD;
    boolean pgmRtn = false;
    String K_PROD_CD = "1202010201";
    String K_OUTPUT_FMT = "CIA07";
    String WS_MSGID = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CIRACR CIRACR = new CIRACR();
    CICQACAC CICQACAC = new CICQACAC();
    TDCACE TDCACE = new TDCACE();
    SCCFMT SCCFMT = new SCCFMT();
    CICFA07 CICFA07 = new CICFA07();
    SCCGWA SCCGWA;
    CIB9007_AWA_9007 CIB9007_AWA_9007;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9007 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9007_AWA_9007>");
        CIB9007_AWA_9007 = (CIB9007_AWA_9007) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, CIB9007_AWA_9007.CI_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9007_AWA_9007.CI_NO);
        if (CIB9007_AWA_9007.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIB9007_AWA_9007.CI_NO;
        CIRACR.PROD_CD = K_PROD_CD;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AGR_NO is:");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = CIRACR.KEY.AGR_NO;
        TDCACE.PAGE_INF.I_AC_SEQ = 1;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        CICFA07.CI_NO = CIB9007_AWA_9007.CI_NO;
        CICFA07.GROWN_AC = CIRACR.KEY.AGR_NO;
        CICFA07.PRIN_AMT = TDCACE.DATA[1-1].BAL;
        CICFA07.NUM_AMT = TDCACE.DATA[1-1].ACCR_INT;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA07;
        SCCFMT.DATA_LEN = 76;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "CI_NO = :CIRACR.CI_NO "
            + "AND PROD_CD = :CIRACR.PROD_CD";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
