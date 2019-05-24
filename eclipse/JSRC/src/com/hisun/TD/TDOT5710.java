package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5710 {
    DBParm TDTPHIS_RD;
    brParm TDTPHIS_BR = new brParm();
    int WS_CNT = 0;
    String WS_ACO_NO = " ";
    int WS_RCD_SEQ = 0;
    String K_OUTPUT_FMT = "TD571";
    int K_SCR_ROW_NO = 15;
    String WS_MSGID = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    short WS_PAGE_ROW_NO = 0;
    TDOT5710_WS_FMT WS_FMT = new TDOT5710_WS_FMT();
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    TDRPHIS TDRPHIS = new TDRPHIS();
    SCCGWA SCCGWA;
    TDB5710_AWA_5710 TDB5710_AWA_5710;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5710 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5710_AWA_5710>");
        TDB5710_AWA_5710 = (TDB5710_AWA_5710) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_ACO_NO();
        B030_GET_LIST();
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.AC_NO);
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.BV_TYP);
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.AC_SEQ);
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.BV_NO);
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.PAGE_NUM);
        CEP.TRC(SCCGWA, TDB5710_AWA_5710.PAGE_ROW);
        if (TDB5710_AWA_5710.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5710_AWA_5710.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5710_AWA_5710.PAGE_ROW == 0) {
            WS_PAGE_ROW_NO = (short) K_SCR_ROW_NO;
        } else {
            WS_PAGE_ROW_NO = (short) TDB5710_AWA_5710.PAGE_ROW;
        }
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDB5710_AWA_5710.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDB5710_AWA_5710.AC_SEQ;
        CICQACAC.DATA.BV_NO = TDB5710_AWA_5710.BV_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_ACO_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO);
        T000_COUNT_TDTPHIS_BY_ACO_NO();
        T000_STARTBR_TDTPHIS_BY_ACO_NO();
        if (TDB5710_AWA_5710.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( TDB5710_AWA_5710.PAGE_NUM - 1 ) * WS_PAGE_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_TDTPHIS_FIRST();
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_NUM < WS_PAGE_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            CEP.TRC(SCCGWA, "**********************");
            CEP.TRC(SCCGWA, WS_NUM);
            WS_FMT.WS_DATA[WS_NUM-1].WS_REMMIT_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ACO_AC = TDRPHIS.KEY.ACO_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETT_DATE = TDRPHIS.KEY.SETT_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_JRNNO = TDRPHIS.KEY.JRNNO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TXN_BR = TDRPHIS.TXN_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BOOK_BR = TDRPHIS.BOOK_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETT_CCY = TDRPHIS.SETT_CCY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETT_BAL = TDRPHIS.SETT_BAL;
            WS_FMT.WS_DATA[WS_NUM-1].WS_RAT_INT = TDRPHIS.RAT_INT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TAX_RAT = TDRPHIS.TAX_RAT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STR_DATE = TDRPHIS.STR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_END_DATE = TDRPHIS.END_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STATUS = TDRPHIS.STATUS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETT_STATE = TDRPHIS.SETT_STATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_INT_AMT = TDRPHIS.INT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TAX_AMT = TDRPHIS.TAX_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETT_AMT = TDRPHIS.SETT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DRW_BUD_INT = TDRPHIS.DRW_BUD_INT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_REC_INT_DT = TDRPHIS.REC_INT_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_COM_BAL = TDRPHIS.COM_BAL;
            WS_FMT.WS_DATA[WS_NUM-1].WS_COM_ACCU = TDRPHIS.COM_ACCU;
            WS_FMT.WS_DATA[WS_NUM-1].WS_INOUT = TDRPHIS.INOUT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STL_MTH = TDRPHIS.STL_MTH;
            CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_STL_MTH);
            WS_FMT.WS_DATA[WS_NUM-1].WS_PAY_AC = TDRPHIS.PAY_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PAY_AC_SEQ = TDRPHIS.PAY_AC_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_REMMIT_BK = TDRPHIS.REMMIT_BK;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AI_ACNO = TDRPHIS.AI_ACNO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_REV_NO = TDRPHIS.REV_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_TLR = TDRPHIS.CRT_TLR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_DATE = TDRPHIS.CRT_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OWNER_BK = TDRPHIS.OWNER_BK;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_TLT = TDRPHIS.UPD_TLT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_DATE = TDRPHIS.UPD_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_TIME = TDRPHIS.UPD_TIME;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TS = TDRPHIS.TS;
            T000_READNEXT_TDTPHIS();
        }
        T000_ENDBR_TDTPHIS();
        if (TDB5710_AWA_5710.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = (short) TDB5710_AWA_5710.PAGE_NUM;
        }
        WS_FMT.WS_LAST_PAGE = 'N';
        if (WS_NUM <= WS_PAGE_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            WS_FMT.WS_LAST_PAGE = 'Y';
        }
        WS_FMT.WS_PAGE_ROW = WS_NUM;
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 9857;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_COUNT_TDTPHIS_BY_ACO_NO() throws IOException,SQLException,Exception {
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        TDTPHIS_RD.set = "WS-CNT=COUNT(*)";
        TDTPHIS_RD.where = "ACO_AC = :WS_ACO_NO "
            + "AND INT_AMT < > 0";
        IBS.GROUP(SCCGWA, TDRPHIS, this, TDTPHIS_RD);
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_STARTBR_TDTPHIS_BY_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, TDRPHIS);
        CEP.TRC(SCCGWA, "22222222");
        TDTPHIS_BR.rp = new DBParm();
        TDTPHIS_BR.rp.TableName = "TDTPHIS";
        TDTPHIS_BR.rp.where = "ACO_AC = :WS_ACO_NO "
            + "AND INT_AMT < > 0";
        TDTPHIS_BR.rp.order = "SETT_DATE";
        IBS.STARTBR(SCCGWA, TDRPHIS, this, TDTPHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTPHIS_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPHIS, this, TDTPHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTPHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPHIS, this, TDTPHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TDTPHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPHIS_BR);
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
