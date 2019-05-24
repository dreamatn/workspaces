package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1360 {
    DBParm TDTROLL_RD;
    brParm TDTROLL_BR = new brParm();
    int WS_CNT = 0;
    int WS_RCD_SEQ = 0;
    String K_OUTPUT_FMT = "TD136";
    int K_SCR_ROW_NO = 15;
    String WS_MSGID = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    short WS_END_SEQ = 0;
    short WS_B = 0;
    short WS_PAGE_ROW_NO = 0;
    TDOT1360_WS_FMT WS_FMT = new TDOT1360_WS_FMT();
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    TDRROLL TDRROLL = new TDRROLL();
    SCCGWA SCCGWA;
    TDB1360_AWA_1360 TDB1360_AWA_1360;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1360 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1360_AWA_1360>");
        TDB1360_AWA_1360 = (TDB1360_AWA_1360) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_ACO_NO();
        B030_GET_LIST();
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.AC_NO);
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.BV_TYP);
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.AC_SEQ);
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.BV_NO);
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.PAGE_NUM);
        CEP.TRC(SCCGWA, TDB1360_AWA_1360.PAGE_ROW);
        if (TDB1360_AWA_1360.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1360_AWA_1360.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB1360_AWA_1360.PAGE_ROW == 0) {
            WS_PAGE_ROW_NO = (short) K_SCR_ROW_NO;
        } else {
            WS_PAGE_ROW_NO = TDB1360_AWA_1360.PAGE_ROW;
        }
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDB1360_AWA_1360.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDB1360_AWA_1360.AC_SEQ;
        CICQACAC.DATA.BV_NO = TDB1360_AWA_1360.BV_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        TDRROLL.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO);
        T000_COUNT_TDRROLL_BY_ACO_NO();
        T000_STARTBR_TDRROLL_BY_ACO_NO();
        if (TDB1360_AWA_1360.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( TDB1360_AWA_1360.PAGE_NUM - 1 ) * WS_PAGE_ROW_NO + 1;
            WS_END_SEQ = (short) (TDB1360_AWA_1360.PAGE_NUM * WS_PAGE_ROW_NO);
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_TDRROLL_FIRST();
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
        WS_NUM = 0;
        WS_B = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        WS_FMT.WS_AC_NO = TDB1360_AWA_1360.AC_NO;
        CEP.TRC(SCCGWA, WS_FMT.WS_AC_NO);
        WS_FMT.WS_PAGE_NUM = TDB1360_AWA_1360.PAGE_NUM;
        WS_FMT.WS_PAGE_ROW = TDB1360_AWA_1360.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_TBL_FLAG != 'N' 
            && WS_B < WS_END_SEQ) {
            WS_B += 1;
            if (WS_B >= WS_RCD_SEQ 
                && WS_B <= WS_END_SEQ) {
                WS_NUM = (short) (WS_NUM + 1);
                CEP.TRC(SCCGWA, "**********************");
                CEP.TRC(SCCGWA, WS_NUM);
                WS_FMT.WS_DATA[WS_NUM-1].WS_SER_DATE = TDRROLL.KEY.SER_DT;
                WS_FMT.WS_DATA[WS_NUM-1].WS_BV_TYP = TDRROLL.BV_TYP;
                WS_FMT.WS_DATA[WS_NUM-1].WS_STSW = TDRROLL.STSW;
                WS_FMT.WS_DATA[WS_NUM-1].WS_CCY = TDRROLL.CCY;
                WS_FMT.WS_DATA[WS_NUM-1].WS_CCY_TYP = TDRROLL.CCY_TYP;
                WS_FMT.WS_DATA[WS_NUM-1].WS_AC_BK = TDRROLL.OWNER_BK;
                WS_FMT.WS_DATA[WS_NUM-1].WS_AC_BR = TDRROLL.AC_BK;
                WS_FMT.WS_DATA[WS_NUM-1].WS_OPEN_DATE = TDRROLL.OPEN_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_PROD_CD = TDRROLL.PROD_CD;
                WS_FMT.WS_DATA[WS_NUM-1].WS_PRDAC_CD = TDRROLL.PRDAC_CD;
                WS_FMT.WS_DATA[WS_NUM-1].WS_TERM = TDRROLL.TERM;
                WS_FMT.WS_DATA[WS_NUM-1].WS_SPE_FLG = TDRROLL.RMK_100;
                WS_FMT.WS_DATA[WS_NUM-1].WS_CALR_STD = TDRROLL.CALR_STD.charAt(0);
                WS_FMT.WS_DATA[WS_NUM-1].WS_PVAL_DATE = TDRROLL.PVAL_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_VAL_DATE = TDRROLL.VAL_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_EXP_DATE = TDRROLL.EXP_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_INSTR_MTH = TDRROLL.INSTR_MTH;
                WS_FMT.WS_DATA[WS_NUM-1].WS_BAL = TDRROLL.BAL;
                WS_FMT.WS_DATA[WS_NUM-1].WS_FBAL = TDRROLL.FBAL;
                WS_FMT.WS_DATA[WS_NUM-1].WS_PBAL = TDRROLL.PBAL;
                WS_FMT.WS_DATA[WS_NUM-1].WS_LBAL_DATE = TDRROLL.LBAL_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_PROC_NUM = TDRROLL.PROC_NUM;
                WS_FMT.WS_DATA[WS_NUM-1].WS_SER_TIME = TDRROLL.SER_TIME;
                WS_FMT.WS_DATA[WS_NUM-1].WS_EXP_INT = TDRROLL.EXP_INT;
                WS_FMT.WS_DATA[WS_NUM-1].WS_DRW_INT = TDRROLL.DRW_INT;
                WS_FMT.WS_DATA[WS_NUM-1].WS_DRW_TAX = TDRROLL.DRW_TAX;
                WS_FMT.WS_DATA[WS_NUM-1].WS_OIC_NO = TDRROLL.OIC_NO;
                WS_FMT.WS_DATA[WS_NUM-1].WS_LAST_BR = TDRROLL.UPD_BR;
                WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_TLR = TDRROLL.CRT_TLR;
                WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_DATE = TDRROLL.CRT_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_UPDTBL_TLR = TDRROLL.UPD_TLT;
                WS_FMT.WS_DATA[WS_NUM-1].WS_UPDTBL_DATE = TDRROLL.UPD_DATE;
                WS_FMT.WS_DATA[WS_NUM-1].WS_TS = TDRROLL.TS;
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SER_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_BV_TYP);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_STSW);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CCY);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CCY_TYP);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_AC_BK);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_AC_BR);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_OPEN_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PROD_CD);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PRDAC_CD);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_TERM);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SPE_FLG);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CALR_STD);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PVAL_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_VAL_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_EXP_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_INSTR_MTH);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_BAL);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_FBAL);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PBAL);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_LBAL_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PROC_NUM);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SER_TIME);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_EXP_INT);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_DRW_INT);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_DRW_TAX);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_OIC_NO);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_LAST_BR);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_TLR);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_UPDTBL_TLR);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_UPDTBL_DATE);
                CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_TS);
            } else {
            }
            T000_READNEXT_TDRROLL();
        }
        T000_ENDBR_TDRROLL();
        WS_FMT.WS_END_FLG = 'N';
        if (WS_NUM <= WS_PAGE_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            WS_FMT.WS_END_FLG = 'Y';
        }
        WS_FMT.WS_PAGE_ROW = WS_NUM;
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 6582;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_COUNT_TDRROLL_BY_ACO_NO() throws IOException,SQLException,Exception {
        TDTROLL_RD = new DBParm();
        TDTROLL_RD.TableName = "TDTROLL";
        TDTROLL_RD.set = "WS-CNT=COUNT(*)";
        TDTROLL_RD.where = "ACO_AC = :TDRROLL.KEY.ACO_AC";
        IBS.GROUP(SCCGWA, TDRROLL, this, TDTROLL_RD);
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_STARTBR_TDRROLL_BY_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, TDRROLL.KEY.ACO_AC);
        TDTROLL_BR.rp = new DBParm();
        TDTROLL_BR.rp.TableName = "TDTROLL";
        TDTROLL_BR.rp.where = "ACO_AC = :TDRROLL.KEY.ACO_AC";
        IBS.STARTBR(SCCGWA, TDRROLL, this, TDTROLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
    }
    public void T000_READNEXT_TDRROLL_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRROLL, this, TDTROLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDRROLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDRROLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRROLL, this, TDTROLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDRROLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TDRROLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTROLL_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
