package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSQSAC {
    DCZSQSAC_WS_OUT_INFO WS_OUT_INFO;
    DBParm DCTIAACR_RD;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAMST_RD;
    DBParm DCTSPAC_RD;
    boolean pgmRtn = false;
    String DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String OUTPUT_FMT = "DC607";
    short PAGE_ROW = 25;
    DCZSQSAC_WS_VARIABLES WS_VARIABLES = new DCZSQSAC_WS_VARIABLES();
    DCZSQSAC_WS_OUT_RECODE WS_OUT_RECODE = new DCZSQSAC_WS_OUT_RECODE();
    DCZSQSAC_WS_COND_FLAG WS_COND_FLAG = new DCZSQSAC_WS_COND_FLAG();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCPACTY_WS_DB_VARS WS_DB_VARS = new DCCPACTY_WS_DB_VARS();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCPQBNK_DATA_INFO DATA_INFO;
    DCCSQSAC DCCSQSAC;
    public void MP(SCCGWA SCCGWA, DCCSQSAC DCCSQSAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQSAC = DCCSQSAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQSAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLAG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLAG.PLDR_FLG == 'Y') {
            B025_PROC_BROWSE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_ROW);
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_NUM);
        CEP.TRC(SCCGWA, DCCSQSAC.PAGE_ROW);
        CEP.TRC(SCCGWA, DCCSQSAC.AC_NO);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCSQSAC.AC_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCSQSAC.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = DCCSQSAC.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        if (DCCSQSAC.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZSQSAC_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (DCCSQSAC.PAGE_ROW > PAGE_ROW) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.DC_ERR_PAGE_ROW, DCCSQSAC.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.PAGE_ROW = DCCSQSAC.PAGE_ROW;
                WS_OUT_INFO = new DCZSQSAC_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
        CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DCCSQSAC.AC_NO;
        T000_READ_DCTIAMST();
        if (pgmRtn) return;
        if (WS_COND_FLAG.PLDR_FLG == 'N') {
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.KEY.FREE_AC = DCCSQSAC.AC_NO;
            CEP.TRC(SCCGWA, DCRSPAC.KEY.FREE_AC);
            T000_READ_FREE_DCTSPAC();
            if (pgmRtn) return;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = 1;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 1;
            WS_OUT_INFO.O_SUB_AC = DCRSPAC.STD_AC;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(1-1).O_SUB_AC);
            B000_OUTPUT_PROC();
            if (pgmRtn) return;
        }
        if (DCRIAMST.AC_STS == 'C') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_VIA_AC_MST_ALR_CLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_PROC_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        if (WS_COND_FLAG.IAACR_FLG != 'N') {
            WS_VARIABLES.IDX = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            while (WS_VARIABLES.IDX != WS_VARIABLES.PAGE_ROW 
                && WS_COND_FLAG.IAACR_FLG != 'N') {
                B000_WRITE_DATA_PROC();
                if (pgmRtn) return;
                WS_VARIABLES.NEXT_START_NUM += 1;
                WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
                T000_READNEXT_DCTIAACR();
                if (pgmRtn) return;
            }
            if (WS_COND_FLAG.IAACR_FLG == 'N') {
                CEP.TRC(SCCGWA, "IF IF");
                WS_VARIABLES.TOTAL_PAGE = WS_VARIABLES.CURR_PAGE;
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.IDX;
                WS_VARIABLES.TOTAL_NUM = ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW + WS_VARIABLES.BAL_CNT;
                WS_VARIABLES.LAST_PAGE = 'Y';
                WS_VARIABLES.PAGE_ROW = WS_VARIABLES.BAL_CNT;
                WS_OUT_INFO = new DCZSQSAC_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            } else {
                CEP.TRC(SCCGWA, "IF ELSE");
                R000_GROUP_ALL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
                WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
                WS_VARIABLES.TOTAL_PAGE = (int) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
                if (WS_VARIABLES.BAL_CNT != 0) {
                    WS_VARIABLES.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = 0;
            WS_VARIABLES.TOTAL_PAGE = 0;
            WS_VARIABLES.TOTAL_NUM = 0;
            WS_VARIABLES.LAST_PAGE = 'Y';
            WS_VARIABLES.PAGE_ROW = 0;
            WS_OUT_INFO = new DCZSQSAC_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        B000_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B000_WRITE_DATA_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        WS_OUT_INFO.O_SUB_AC = DCRIAACR.SUB_AC;
        if (DCRIAACR.STD_AC_FLG == 'N') {
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.STD_AC = DCRIAACR.SUB_AC;
            T000_READ_DCTSPAC();
            if (pgmRtn) return;
            WS_OUT_INFO.O_SUB_AC = DCRSPAC.KEY.FREE_AC;
        }
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO.get(WS_VARIABLES.IDX-1).O_SUB_AC);
    }
    public void B000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***************FMT  OUTPUT*****************");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 822;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        T000_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_NUM);
    }
    public void T000_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = :DCRIAACR.ACCR_FLG";
        DCTIAACR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        WS_COND_FLAG.IAACR_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        DCRIAACR.KEY.VIA_AC = DCCSQSAC.AC_NO;
        DCRIAACR.ACCR_FLG = '1';
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = :DCRIAACR.ACCR_FLG";
        DCTIAACR_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLAG.IAACR_FLG = 'Y';
        } else {
            WS_COND_FLAG.IAACR_FLG = 'N';
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "SUB_AC = :DCRIAACR.SUB_AC";
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_IA_ACR_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLAG.PLDR_FLG = 'N';
        } else {
            WS_COND_FLAG.PLDR_FLG = 'Y';
        }
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.where = "STD_AC = :DCRSPAC.STD_AC";
        DCTSPAC_RD.fst = true;
        IBS.READ(SCCGWA, DCRSPAC, this, DCTSPAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FREE_AC_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FREE_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FREE_AC_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
