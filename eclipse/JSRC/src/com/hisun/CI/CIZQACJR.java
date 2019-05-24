package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQACJR {
    DBParm CITACR_RD;
    DBParm CITACRL_RD;
    brParm CITACRL_BR = new brParm();
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_REL_AC_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQACJR CICQACJR;
    public void MP(SCCGWA SCCGWA, CICQACJR CICQACJR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQACJR = CICQACJR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQACJR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_REL_AC_NO();
        if (pgmRtn) return;
        B030_GET_JRL_AC_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACJR.DATA.AC_NO);
        if (CICQACJR.DATA.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQACJR.DATA.AC_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AC-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT, CICQACJR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_REL_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = CICQACJR.DATA.AC_NO;
        CIRACRL.KEY.AC_REL = "02";
        T000_READ_CITACRL_AC_R();
        if (pgmRtn) return;
        WS_REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
    }
    public void B030_GET_JRL_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
        CIRACRL.KEY.AC_REL = "02";
        T000_STARTBR_CITACRL();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 10; WS_I += 1) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICQACJR.JRL_DATA[WS_I-1].JRL_AC_NO = CIRACR.KEY.AGR_NO;
            CICQACJR.JRL_DATA[WS_I-1].JRL_CI_NO = CIRACR.CI_NO;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQACJR.RC);
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
    public void T000_READ_CITACRL_AC_R() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACJR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACJR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_REL , REL_AC_NO";
        CITACRL_BR.rp.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
