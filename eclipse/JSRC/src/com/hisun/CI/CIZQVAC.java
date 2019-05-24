package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQVAC {
    DBParm CITACR_RD;
    brParm CITACRL_BR = new brParm();
    DBParm CITACRL_RD;
    boolean pgmRtn = false;
    int K_MAX_PAGE_ROW = 5;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_SEQ = 0;
    int WS_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQVAC CICQVAC;
    public void MP(SCCGWA SCCGWA, CICQVAC CICQVAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQVAC = CICQVAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQVAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQVAC.FUNC == '2') {
            B020_BROWSE_ACRL_INF_RAC();
            if (pgmRtn) return;
        } else if (CICQVAC.FUNC == '1') {
            B030_BROWSE_ACRL_INF_AC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_ACRL_INF_RAC() throws IOException,SQLException,Exception {
        if (CICQVAC.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "QVAC-AC-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQVAC.DATA.AC_NO;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = CICQVAC.DATA.AC_NO;
        if (CICQVAC.DATA.AC_REL.trim().length() > 0) {
            CIRACRL.KEY.AC_REL = CICQVAC.DATA.AC_REL;
            T000_STARTBR_CITACRL_AC_R();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACRL_AC();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (WS_I < 1000 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = WS_I + 1;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_AC_NO = CIRACRL.KEY.AC_NO;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_AC_REL = CIRACRL.KEY.AC_REL;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_REL_STS = CIRACRL.KEY.REL_STS;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_ACRL_INF_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQVAC.DATA.REL_AC_NO);
        CEP.TRC(SCCGWA, CICQVAC.DATA.AC_REL);
        if (CICQVAC.DATA.REL_AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "QVAC-REL-AC-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "QVAC-REL-AC-NO MUST INPUT");
        }
        if (CICQVAC.DATA.AC_REL.trim().length() == 0) {
            CEP.TRC(SCCGWA, "QVAC-AC-REL MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "QVAC-AC-REL MUST INPUT");
        }
        R000_COMPUTE_ROW();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_REL = CICQVAC.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICQVAC.DATA.REL_AC_NO;
        if (CICQVAC.DATA.REL_STS == ' ') {
            T000_STARTBR_CITACRL_RAC_R();
            if (pgmRtn) return;
        } else {
            CIRACRL.KEY.REL_STS = CICQVAC.DATA.REL_STS;
            T000_STARTBR_CITACRL_RAC_R2();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACRL_FIRST();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= WS_PAGE_ROW) {
            WS_I = WS_I + 1;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_AC_NO = CIRACRL.KEY.AC_NO;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_AC_REL = CIRACRL.KEY.AC_REL;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
            CICQVAC.OUT_DATA.REL_INF[WS_I-1].O_REL_STS = CIRACRL.KEY.REL_STS;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void R000_COMPUTE_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQVAC.DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, CICQVAC.DATA.PAGE_NUM);
        if (CICQVAC.DATA.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "QVAC-PAGE-ROW MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "QVAC-PAGE-ROW MUST INPUT");
        }
        if (CICQVAC.DATA.PAGE_ROW > K_MAX_PAGE_ROW) {
            WS_PAGE_ROW = K_MAX_PAGE_ROW;
        } else {
            WS_PAGE_ROW = CICQVAC.DATA.PAGE_ROW;
        }
        WS_SEQ = ( CICQVAC.DATA.PAGE_NUM - 1 ) * CICQVAC.DATA.PAGE_ROW + 1;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_REL = CICQVAC.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICQVAC.DATA.REL_AC_NO;
        if (CICQVAC.DATA.REL_STS == ' ') {
            T000_COUNT_CITACRL();
            if (pgmRtn) return;
        } else {
            CIRACRL.KEY.REL_STS = CICQVAC.DATA.REL_STS;
            T000_COUNT_CITACRL2();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CICQVAC.OUT_DATA.TOTAL_NUM = WS_CNT;
        WS_RECORD_NUM = CICQVAC.OUT_DATA.TOTAL_NUM % WS_PAGE_ROW;
        CICQVAC.OUT_DATA.TOTAL_PAGE = (int) ((CICQVAC.OUT_DATA.TOTAL_NUM - WS_RECORD_NUM) / WS_PAGE_ROW);
        if (WS_RECORD_NUM != 0) {
            CICQVAC.OUT_DATA.TOTAL_PAGE = CICQVAC.OUT_DATA.TOTAL_PAGE + 1;
        }
        CICQVAC.OUT_DATA.CURR_PAGE_ROW = WS_PAGE_ROW;
        CICQVAC.OUT_DATA.CURR_PAGE = CICQVAC.DATA.PAGE_NUM;
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.TOTAL_PAGE);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.CURR_PAGE);
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.CURR_PAGE_ROW);
        if (CICQVAC.OUT_DATA.TOTAL_PAGE == CICQVAC.OUT_DATA.CURR_PAGE) {
            CICQVAC.OUT_DATA.END_FLG = 'Y';
        } else {
            CICQVAC.OUT_DATA.END_FLG = 'N';
        }
        CEP.TRC(SCCGWA, CICQVAC.OUT_DATA.END_FLG);
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQVAC.RC);
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
    public void T000_STARTBR_CITACRL_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_RAC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_AC_R() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO, AC_REL";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_RAC_R() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO, AC_REL";
        CITACRL_BR.rp.order = "AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_RAC_R2() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO, AC_REL,REL_STS";
        CITACRL_BR.rp.order = "AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void T000_COUNT_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.set = "WS-CNT=COUNT(*)";
        CITACRL_RD.eqWhere = "AC_REL,REL_AC_NO";
        IBS.GROUP(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_COUNT_CITACRL2() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.set = "WS-CNT=COUNT(*)";
        CITACRL_RD.eqWhere = "AC_REL,REL_AC_NO,REL_STS";
        IBS.GROUP(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
