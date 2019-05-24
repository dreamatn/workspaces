package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMCYY {
    int JIBS_tmp_int;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm DDTVCH_RD;
    brParm DDTHLD_BR = new brParm();
    DBParm DDTHLD_RD;
    boolean pgmRtn = false;
    int WS_HLD_NO = 0;
    short WS_STS_NO = 0;
    DDZIMCYY_WS_COUNT_NUM WS_COUNT_NUM = new DDZIMCYY_WS_COUNT_NUM();
    char WS_STOP_FLG = ' ';
    short WS_TIME = 0;
    String WS_MSGID = " ";
    char WS_TABLE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRVCH DDRVCH = new DDRVCH();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCCY DDRCCYO = new DDRCCY();
    DDRINF DDRINF = new DDRINF();
    DDRHLD DDRHLD = new DDRHLD();
    SCCGWA SCCGWA;
    DDCIMCYY DDCIMCYY;
    public void MP(SCCGWA SCCGWA, DDCIMCYY DDCIMCYY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMCYY = DDCIMCYY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMCYY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIMCYY.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIMCYY.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCIMCYY.TX_TYPE == 'I') {
            B010_INQ_AC_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIMCYY.TX_TYPE == 'U') {
            B030_UPD_AC_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCIMCYY.TX_TYPE == 'S') {
            B050_SET_AC_STS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCIMCYY.TX_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_AC_INF_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.KEY.AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCIMCYY.DATA.KEY.AC;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRCCY, DDRCCYO);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDRVCH.VCH_TYPE = '1';
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B030_UPD_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCIMCYY.DATA.AC_OLD;
        T000_STARTBR_DDTCCY_UPD();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_STOP_FLG != 'N') {
            CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
            WS_TIME += 1;
            if (DDCIMCYY.IC_FLG == 'Y') {
                DDRCCY.CUS_AC = DDCIMCYY.DATA.AC_NEW;
                T000_REWRITE_DDTCCY();
                if (pgmRtn) return;
            } else {
                if (DDRCCY.AC_TYPE != '6') {
                    DDRCCY.CUS_AC = DDCIMCYY.DATA.AC_NEW;
                    T000_REWRITE_DDTCCY();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        R000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.CARD_NO = DDCIMCYY.DATA.AC_OLD;
        T000_STARTBR_DDTHLD_UPD();
        if (pgmRtn) return;
        T000_READNEXT_DDTHLD();
        if (pgmRtn) return;
        while (WS_TABLE_FLG != 'N') {
            CEP.TRC(SCCGWA, DDRHLD.CARD_NO);
            DDRHLD.CARD_NO = DDCIMCYY.DATA.AC_NEW;
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DDTHLD();
            if (pgmRtn) return;
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
        }
        R000_ENDBR_DDTHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TIME);
    }
    public void B050_SET_AC_STS_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        R000_R_UPD_CCY_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DDRCCY, DDRCCYO);
        R000_CHECK_CCY_STUS();
        if (pgmRtn) return;
        if (DDCIMCYY.DATA.STS_CD.trim().length() == 0) WS_STS_NO = 0;
        else WS_STS_NO = Short.parseShort(DDCIMCYY.DATA.STS_CD);
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.SET_FLG);
        if (DDCIMCYY.DATA.SET_FLG == 'O') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, WS_STS_NO - 1) + "1" + DDRCCY.STS_WORD.substring(WS_STS_NO + 1 - 1);
        } else {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, WS_STS_NO - 1) + "0" + DDRCCY.STS_WORD.substring(WS_STS_NO + 1 - 1);
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMCYY.DATA.KEY.AC);
        if (DDCIMCYY.DATA.KEY.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIMCYY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIMCYY.TX_TYPE == 'S') {
            if (DDCIMCYY.DATA.STS_CD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_STS_NO_M_INPUT, DDCIMCYY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMCYY.DATA.STS_CD.compareTo("00") < 0) 
                || (DDCIMCYY.DATA.STS_CD.compareTo("99") > 0)) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID, DDCIMCYY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((DDCIMCYY.DATA.SET_FLG != 'O') 
                && (DDCIMCYY.DATA.SET_FLG != 'F')) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_SET_FLG_INVALID, DDCIMCYY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_DDTCCY_UPD() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "CUS_AC";
        DDTCCY_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STOP_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_STOP_FLG = 'N';
            } else {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMCYY.RC);
            DDCIMCYY.RC.RC_INFO = DDCIMCYY.DATA.KEY.AC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void R000_R_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCIMCYY.DATA.KEY.AC;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        DDCIMCYY.DATA.OWNER_BK = DDRCCYO.OWNER_BK;
        DDCIMCYY.DATA.CUS_AC = DDRCCYO.CUS_AC;
        DDCIMCYY.DATA.CCY = DDRCCYO.CCY;
        DDCIMCYY.DATA.CCY_TYPE = DDRCCYO.CCY_TYPE;
        DDCIMCYY.DATA.AC_TYPE = DDRCCYO.AC_TYPE;
        DDCIMCYY.DATA.STS = DDRCCYO.STS;
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        DDCIMCYY.DATA.STS_WORD = DDRCCYO.STS_WORD;
        DDCIMCYY.DATA.PROD_CODE = DDRCCYO.PROD_CODE;
        DDCIMCYY.DATA.OPEN_DATE = DDRCCYO.OPEN_DATE;
        DDCIMCYY.DATA.LAST_DATE = DDRCCYO.LAST_DATE;
        DDCIMCYY.DATA.LAST_FN_DATE = DDRCCYO.LAST_FN_DATE;
        DDCIMCYY.DATA.OPEN_DP = DDRCCYO.OPEN_DP;
        DDCIMCYY.DATA.OPEN_TLR = DDRCCYO.OPEN_TLR;
        DDCIMCYY.DATA.OWNER_BR = DDRCCYO.OWNER_BR;
        DDCIMCYY.DATA.OWNER_BRDP = DDRCCYO.OWNER_BRDP;
        DDCIMCYY.DATA.CURR_BAL = DDRCCYO.CURR_BAL;
        DDCIMCYY.DATA.HOLD_BAL = DDRCCYO.HOLD_BAL;
        DDCIMCYY.DATA.NOT_INT_BAL = DDRCCYO.NOT_INT_BAL;
        DDCIMCYY.DATA.CCAL_TOT_BAL = DDRCCYO.CCAL_TOT_BAL;
        DDCIMCYY.DATA.MARGIN_BAL = DDRCCYO.MARGIN_BAL;
        DDCIMCYY.DATA.CLOSE_BAL = DDRCCYO.CLOSE_BAL;
        DDCIMCYY.DATA.LAST_BAL = DDRCCYO.LAST_BAL;
        DDCIMCYY.DATA.LAST_BAL_DATE = DDRCCYO.LAST_BAL_DATE;
        DDCIMCYY.DATA.LAST_DAYEND_BAL = DDRCCYO.LAST_DAYEND_BAL;
        DDCIMCYY.DATA.LAST_YEAR_BAL = DDRCCYO.LAST_YEAR_BAL;
        DDCIMCYY.DATA.LST_DAY_YEAR_BAL = DDRCCYO.LST_DAY_YEAR_BAL;
        DDCIMCYY.DATA.TOT_DEP_INT = DDRCCYO.TOT_DEP_INT;
        DDCIMCYY.DATA.TOT_OD_INT = DDRCCYO.TOT_OD_INT;
        DDCIMCYY.DATA.TOT_OD_TAX = DDRCCYO.TOT_OD_TAX;
        DDCIMCYY.DATA.DAY_TOT_DR_AMT = DDRCCYO.DAY_TOT_DR_AMT;
        DDCIMCYY.DATA.DAY_TOT_CR_AMT = DDRCCYO.DAY_TOT_CR_AMT;
        DDCIMCYY.DATA.LASDAY_TOT_DR_AMT = DDRCCYO.LASDAY_TOT_DR_AMT;
        DDCIMCYY.DATA.LASDAY_TOT_CR_AMT = DDRCCYO.LASDAY_TOT_CR_AMT;
        DDCIMCYY.DATA.LAST_POST_DATE = DDRCCYO.LAST_POST_DATE;
        DDCIMCYY.DATA.DEP_CAMT = DDRCCYO.DEP_CAMT;
        DDCIMCYY.DATA.DRW_CAMT = DDRCCYO.DRW_CAMT;
        DDCIMCYY.DATA.DEP_TAMT = DDRCCYO.DEP_TAMT;
        DDCIMCYY.DATA.DRW_TAMT = DDRCCYO.DRW_TAMT;
        DDCIMCYY.DATA.LAST_DEP_CAMT = DDRCCYO.LAST_DEP_CAMT;
        DDCIMCYY.DATA.LAST_DRW_CAMT = DDRCCYO.LAST_DRW_CAMT;
        DDCIMCYY.DATA.LAST_DEP_TAMT = DDRCCYO.LAST_DEP_TAMT;
        DDCIMCYY.DATA.LAST_DRW_TAMT = DDRCCYO.LAST_DRW_TAMT;
        DDCIMCYY.DATA.FRG_LMT_AMT = DDRCCYO.FRG_LMT_AMT;
        DDCIMCYY.DATA.FRG_TAMT = DDRCCYO.FRG_TAMT;
        DDCIMCYY.DATA.POST_AC = DDRCCYO.POST_AC;
        DDCIMCYY.DATA.CINT_FLG = DDRCCYO.CINT_FLG;
        DDCIMCYY.DATA.AC_OIC_NO = DDRCCYO.AC_OIC_NO;
        DDCIMCYY.DATA.AC_OIC_CODE = DDRCCYO.AC_OIC_CODE;
        DDCIMCYY.DATA.SUB_DP = DDRCCYO.SUB_DP;
        DDCIMCYY.DATA.CRT_DATE = DDRCCYO.CRT_DATE;
        DDCIMCYY.DATA.CRT_TLR = DDRCCYO.CRT_TLR;
        DDCIMCYY.DATA.UPDTBL_DATE = DDRCCYO.UPDTBL_DATE;
        DDCIMCYY.DATA.UPDTBL_TLR = DDRCCYO.UPDTBL_TLR;
        DDCIMCYY.DATA.AMT_TYPE = DDRINF.AMT_TYPE;
        DDCIMCYY.DATA.TXN_TYPE = DDRINF.TXN_TYPE;
    }
    public void R000_CHECK_CCY_STUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE, DDCIMCYY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMCYY.RC);
            DDCIMCYY.RC.RC_INFO = DDCIMCYY.DATA.KEY.AC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMCYY.RC);
            DDCIMCYY.RC.RC_INFO = DDCIMCYY.DATA.KEY.AC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTHLD_UPD() throws IOException,SQLException,Exception {
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "CARD_NO = :DDRHLD.CARD_NO "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        DDTHLD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
    }
    public void T000_READNEXT_DDTHLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else {
            WS_TABLE_FLG = 'N';
        }
    }
    public void R000_ENDBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void T000_READUPD_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "CARD_NO = :DDRHLD.CARD_NO "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
        }
    }
    public void T000_UPDATE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIMCYY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIMCYY=");
            CEP.TRC(SCCGWA, DDCIMCYY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
