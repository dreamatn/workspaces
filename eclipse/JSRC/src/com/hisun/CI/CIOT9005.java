package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9005 {
    DBParm CITACR_RD;
    DBParm CITACCK_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIA05";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CICFA05 CICFA05 = new CICFA05();
    CIRACCK CIRACCK = new CIRACCK();
    SCCGWA SCCGWA;
    CIB9005_AWA_9005 CIB9005_AWA_9005;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9005 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9005_AWA_9005>");
        CIB9005_AWA_9005 = (CIB9005_AWA_9005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, CICFA05);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_READ_AC_CHK_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9005_AWA_9005.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT ONE ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_READ_AC_CHK_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9005_AWA_9005.AGR_NO);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIB9005_AWA_9005.AGR_NO;
        T000_READ_CITACR_AGR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACCK);
        CIRACCK.KEY.AC_NO = CIB9005_AWA_9005.AGR_NO;
        T020_READ_CITACCK_AC();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
        B030_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACR_AGR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
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
    public void T020_READ_CITACCK_AC() throws IOException,SQLException,Exception {
        CITACCK_RD = new DBParm();
        CITACCK_RD.TableName = "CITACCK";
        IBS.READ(SCCGWA, CIRACCK, CITACCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACCK INF NOT FOUND");
            CIRACCK.CHK_FLG = '1';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACCK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CICFA05.FMT.AGR_NO = CIRACCK.KEY.AC_NO;
        CICFA05.FMT.CHK_FLG = CIRACCK.CHK_FLG;
    }
    public void B030_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFA05.FMT.AGR_NO);
        CEP.TRC(SCCGWA, CICFA05.FMT.CHK_FLG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA05;
        SCCFMT.DATA_LEN = 33;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
