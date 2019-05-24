package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCHCD {
    DBParm DCTNOCRD_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_CARD_NO = " ";
    String WS_OLD_CARD_NO = " ";
    String WS_NEW_CARD_NO = " ";
    char WS_NEW_CARD_ACT_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    char WS_TBL_NOCRD_FLG = ' ';
    char WS_SAME_CARD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DCRNOCRD DCRNOCRD = new DCRNOCRD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCCUCHCD DCCUCHCD;
    public void MP(SCCGWA SCCGWA, DCCUCHCD DCCUCHCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCHCD = DCCUCHCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCHCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCUCHCD.FUNC_CODE == 'I') {
            B010_SEARCH_NEW_CARD();
            if (pgmRtn) return;
        } else if (DCCUCHCD.FUNC_CODE == 'Q') {
            B020_SEARCH_OLD_CARD();
            if (pgmRtn) return;
        } else if (DCCUCHCD.FUNC_CODE == 'A') {
            B030_ADD_CARD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, DCCUCHCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCHCD.INPUT_DATA.CARD_NO);
        if (DCCUCHCD.FUNC_CODE != 'I' 
            && DCCUCHCD.FUNC_CODE != 'Q' 
            && DCCUCHCD.FUNC_CODE != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUCHCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCHCD.INPUT_DATA.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCUCHCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_SEARCH_NEW_CARD() throws IOException,SQLException,Exception {
        WS_SAME_CARD_FLG = ' ';
        WS_OLD_CARD_NO = DCCUCHCD.INPUT_DATA.CARD_NO;
        DCCUCHCD.OUTPUT_DATA.O_CARD_NO = DCCUCHCD.INPUT_DATA.CARD_NO;
        CEP.TRC(SCCGWA, DCCUCHCD.INPUT_DATA.CARD_NO);
        while (WS_TBL_FLAG != 'N' 
            && WS_SAME_CARD_FLG != 'Y') {
            IBS.init(SCCGWA, DCRNOCRD);
            DCRNOCRD.OLD_CARD_NO = WS_OLD_CARD_NO;
            T000_READ_DCTNOCRD1();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                if (DCRNOCRD.OLD_CARD_NO.equalsIgnoreCase(DCRNOCRD.KEY.NEW_CARD_NO)) {
                    WS_SAME_CARD_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, DCRNOCRD.KEY.NEW_CARD_NO);
                WS_OLD_CARD_NO = DCRNOCRD.KEY.NEW_CARD_NO;
                DCCUCHCD.OUTPUT_DATA.O_CARD_NO = DCRNOCRD.KEY.NEW_CARD_NO;
                DCCUCHCD.OUTPUT_DATA.O_NEW_CARD_ACT_FG = DCRNOCRD.NEW_CARD_ACT_FG;
                CEP.TRC(SCCGWA, DCCUCHCD.OUTPUT_DATA.O_CARD_NO);
                CEP.TRC(SCCGWA, DCCUCHCD.OUTPUT_DATA.O_NEW_CARD_ACT_FG);
            }
        }
    }
    public void B020_SEARCH_OLD_CARD() throws IOException,SQLException,Exception {
        WS_SAME_CARD_FLG = ' ';
        WS_NEW_CARD_NO = DCCUCHCD.INPUT_DATA.CARD_NO;
        DCCUCHCD.OUTPUT_DATA.O_CARD_NO = DCCUCHCD.INPUT_DATA.CARD_NO;
        CEP.TRC(SCCGWA, DCCUCHCD.INPUT_DATA.CARD_NO);
        while (WS_TBL_FLAG != 'N' 
            && WS_SAME_CARD_FLG != 'Y') {
            IBS.init(SCCGWA, DCRNOCRD);
            DCRNOCRD.KEY.NEW_CARD_NO = WS_NEW_CARD_NO;
            T000_READ_DCTNOCRD2();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                if (DCRNOCRD.KEY.NEW_CARD_NO.equalsIgnoreCase(DCRNOCRD.OLD_CARD_NO)) {
                    WS_SAME_CARD_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, DCRNOCRD.OLD_CARD_NO);
                WS_NEW_CARD_NO = DCRNOCRD.OLD_CARD_NO;
                DCCUCHCD.OUTPUT_DATA.O_CARD_NO = DCRNOCRD.OLD_CARD_NO;
                CEP.TRC(SCCGWA, DCCUCHCD.OUTPUT_DATA.O_CARD_NO);
            }
        }
    }
    public void B030_ADD_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRNOCRD);
        DCRNOCRD.KEY.NEW_CARD_NO = DCCUCHCD.INPUT_DATA.CARD_NO;
        DCRNOCRD.OLD_CARD_NO = DCCUCHCD.INPUT_DATA.OLD_CARD_NO;
        DCRNOCRD.NEW_CARD_MEDI = DCCUCHCD.INPUT_DATA.NEW_CARD_MEDI;
        DCRNOCRD.OLD_CARD_MEDI = DCCUCHCD.INPUT_DATA.OLD_CARD_MEDI;
        DCRNOCRD.NEW_CARD_ACT_FG = DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_FG;
        DCRNOCRD.NEW_CARD_ACT_DT = DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_DT;
        DCRNOCRD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRNOCRD.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRNOCRD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRNOCRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRNOCRD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRNOCRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_ADD_DCTNOCRD();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTNOCRD1() throws IOException,SQLException,Exception {
        DCTNOCRD_RD = new DBParm();
        DCTNOCRD_RD.TableName = "DCTNOCRD";
        DCTNOCRD_RD.where = "OLD_CARD_NO = :DCRNOCRD.OLD_CARD_NO";
        DCTNOCRD_RD.fst = true;
        DCTNOCRD_RD.order = "CRT_DATE DESC";
        IBS.READ(SCCGWA, DCRNOCRD, this, DCTNOCRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTNOCRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTNOCRD2() throws IOException,SQLException,Exception {
        DCTNOCRD_RD = new DBParm();
        DCTNOCRD_RD.TableName = "DCTNOCRD";
        DCTNOCRD_RD.where = "NEW_CARD_NO = :DCRNOCRD.KEY.NEW_CARD_NO";
        IBS.READ(SCCGWA, DCRNOCRD, this, DCTNOCRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTNOCRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ADD_DCTNOCRD() throws IOException,SQLException,Exception {
        DCTNOCRD_RD = new DBParm();
        DCTNOCRD_RD.TableName = "DCTNOCRD";
        IBS.WRITE(SCCGWA, DCRNOCRD, DCTNOCRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTNOCRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUCHCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUCHCD=");
            CEP.TRC(SCCGWA, DCCUCHCD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
