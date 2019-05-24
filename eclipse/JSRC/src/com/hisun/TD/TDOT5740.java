package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5740 {
    DBParm TDTAHIS_RD;
    brParm TDTAHIS_BR = new brParm();
    DBParm TDTIREV_RD;
    DBParm TDTOTHE_RD;
    int WS_CNT = 0;
    String WS_ACO_NO = " ";
    int WS_SDT = 0;
    int WS_DDT = 0;
    int WS_RCD_SEQ = 0;
    String K_OUTPUT_FMT = "TD574";
    int K_SCR_ROW_NO = 25;
    char WS_DATE_FUNC = ' ';
    String WS_MSGID = " ";
    int WS_SETT_DATE = 0;
    String WS_ACTI_NO = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    short WS_PAGE_ROW_NO = 0;
    TDOT5740_WS_FMT WS_FMT = new TDOT5740_WS_FMT();
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    TDRAHIS TDRAHIS = new TDRAHIS();
    TDRIREV TDRIREV = new TDRIREV();
    TDROTHE TDROTHE = new TDROTHE();
    SCCGWA SCCGWA;
    TDB5740_AWA_5740 TDB5740_AWA_5740;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5740 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5740_AWA_5740>");
        TDB5740_AWA_5740 = (TDB5740_AWA_5740) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_ACO_NO();
        B030_GET_LIST();
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.AC_NO);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.BV_TYP);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.AC_SEQ);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.BV_NO);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.SDT);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.DDT);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.PAGE_NUM);
        CEP.TRC(SCCGWA, TDB5740_AWA_5740.PAGE_ROW);
        if (TDB5740_AWA_5740.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5740_AWA_5740.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        WS_SDT = TDB5740_AWA_5740.SDT;
        WS_DDT = TDB5740_AWA_5740.DDT;
        if (WS_SDT == 0) {
            if (WS_DDT == 0) {
                WS_DATE_FUNC = 'N';
            } else {
                WS_DATE_FUNC = 'D';
            }
        } else {
            if (WS_DDT == 0) {
                WS_DATE_FUNC = 'S';
            } else {
                if (TDB5740_AWA_5740.SDT > TDB5740_AWA_5740.DDT) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_SDT_GT_DDT;
                    S000_ERR_MSG_PROC();
                }
                WS_DATE_FUNC = 'A';
            }
        }
        CEP.TRC(SCCGWA, WS_DATE_FUNC);
        if (TDB5740_AWA_5740.PAGE_ROW == 0) {
            WS_PAGE_ROW_NO = (short) K_SCR_ROW_NO;
        } else {
            WS_PAGE_ROW_NO = (short) TDB5740_AWA_5740.PAGE_ROW;
        }
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDB5740_AWA_5740.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDB5740_AWA_5740.AC_SEQ;
        CICQACAC.DATA.BV_NO = TDB5740_AWA_5740.BV_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
        WS_ACO_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO);
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTIREV();
        WS_ACTI_NO = TDRIREV.ACTI_NO;
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = WS_ACTI_NO;
        T000_READ_TDTOTHE();
        if (WS_DATE_FUNC == 'S') {
            T000_COUNT_TDTAHIS_ACONO_SDT();
            T000_STARTBR_TDTAHIS_ACONO_SDT();
        } else if (WS_DATE_FUNC == 'D') {
            T000_COUNT_TDTAHIS_ACONO_DDT();
            T000_STARTBR_TDTAHIS_ACONO_DDT();
        } else if (WS_DATE_FUNC == 'A') {
            T000_COUNT_TDTAHIS_ACONO_SDDT();
            T000_STARTBR_TDTAHIS_ACONO_SDDT();
        } else {
            T000_COUNT_TDTAHIS_ACONO();
            T000_STARTBR_TDTAHIS_ACONO();
        }
        if (TDB5740_AWA_5740.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( TDB5740_AWA_5740.PAGE_NUM - 1 ) * WS_PAGE_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_TDTAHIS_FIRST();
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_NUM < WS_PAGE_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            CEP.TRC(SCCGWA, WS_NUM);
            WS_FMT.WS_DATA[WS_NUM-1].WS_ACO_AC = TDRAHIS.KEY.ACO_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STR_DATE = TDRAHIS.KEY.STR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_END_DATE = TDRAHIS.END_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BOOK_BR = TDRAHIS.BOOK_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PRD_CODE = TDRAHIS.PRD_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CCY = TDRAHIS.CCY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BAL = TDRAHIS.BAL;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CON_RATE = TDRAHIS.CON_RATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BAS_DAYS = TDRAHIS.BAS_DAYS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BSP_INT_AMT = TDRAHIS.BSP_INT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ACCR_AMT = TDRAHIS.ACCR_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TOT_ACCR_AMT = TDRAHIS.TOT_ACCR_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OWNER_BR = TDRAHIS.OWNER_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OWNER_BRDP = TDRAHIS.OWNER_BRDP;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OWNER_BK = TDRAHIS.OWNER_BK;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_TLR = TDRAHIS.CRT_TLR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRT_DATE = TDRAHIS.CRT_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_TLT = TDRAHIS.UPD_TLT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_DATE = TDRAHIS.UPD_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_TIME = TDRAHIS.UPD_TIME;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TS = TDRAHIS.TS;
            T000_READNEXT_TDTAHIS();
        }
        T000_ENDBR_TDTAHIS();
        if (TDB5740_AWA_5740.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = (short) TDB5740_AWA_5740.PAGE_NUM;
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
        SCCFMT.DATA_LEN = 5642;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_COUNT_TDTAHIS_ACONO() throws IOException,SQLException,Exception {
        TDTAHIS_RD = new DBParm();
        TDTAHIS_RD.TableName = "TDTAHIS";
        TDTAHIS_RD.set = "WS-CNT=COUNT(*)";
        TDTAHIS_RD.where = "ACO_AC = :WS_ACO_NO";
        IBS.GROUP(SCCGWA, TDRAHIS, this, TDTAHIS_RD);
        if (WS_CNT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_COUNT_TDTAHIS_ACONO_SDT() throws IOException,SQLException,Exception {
        TDTAHIS_RD = new DBParm();
        TDTAHIS_RD.TableName = "TDTAHIS";
        TDTAHIS_RD.set = "WS-CNT=COUNT(*)";
        TDTAHIS_RD.where = "ACO_AC = :WS_ACO_NO "
            + "AND STR_DATE >= :WS_SDT";
        IBS.GROUP(SCCGWA, TDRAHIS, this, TDTAHIS_RD);
        if (WS_CNT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_COUNT_TDTAHIS_ACONO_DDT() throws IOException,SQLException,Exception {
        TDTAHIS_RD = new DBParm();
        TDTAHIS_RD.TableName = "TDTAHIS";
        TDTAHIS_RD.set = "WS-CNT=COUNT(*)";
        TDTAHIS_RD.where = "ACO_AC = :WS_ACO_NO "
            + "AND END_DATE <= :WS_DDT";
        IBS.GROUP(SCCGWA, TDRAHIS, this, TDTAHIS_RD);
        if (WS_CNT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_COUNT_TDTAHIS_ACONO_SDDT() throws IOException,SQLException,Exception {
        TDTAHIS_RD = new DBParm();
        TDTAHIS_RD.TableName = "TDTAHIS";
        TDTAHIS_RD.set = "WS-CNT=COUNT(*)";
        TDTAHIS_RD.where = "ACO_AC = :WS_ACO_NO "
            + "AND STR_DATE >= :WS_SDT "
            + "AND END_DATE <= :WS_DDT";
        IBS.GROUP(SCCGWA, TDRAHIS, this, TDTAHIS_RD);
        if (WS_CNT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_FMT.WS_TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_STARTBR_TDTAHIS_ACONO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRAHIS);
        TDRAHIS.KEY.ACO_AC = WS_ACO_NO;
        TDTAHIS_BR.rp = new DBParm();
        TDTAHIS_BR.rp.TableName = "TDTAHIS";
        TDTAHIS_BR.rp.eqWhere = "ACO_AC";
        TDTAHIS_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRAHIS, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_TDTAHIS_ACONO_SDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, TDRAHIS);
        CEP.TRC(SCCGWA, "22222222");
        TDRAHIS.KEY.ACO_AC = WS_ACO_NO;
        TDTAHIS_BR.rp = new DBParm();
        TDTAHIS_BR.rp.TableName = "TDTAHIS";
        TDTAHIS_BR.rp.eqWhere = "ACO_AC";
        TDTAHIS_BR.rp.where = "STR_DATE >= :WS_SDT";
        TDTAHIS_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRAHIS, this, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_TDTAHIS_ACONO_DDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, TDRAHIS);
        CEP.TRC(SCCGWA, "22222222");
        TDRAHIS.KEY.ACO_AC = WS_ACO_NO;
        TDTAHIS_BR.rp = new DBParm();
        TDTAHIS_BR.rp.TableName = "TDTAHIS";
        TDTAHIS_BR.rp.eqWhere = "ACO_AC";
        TDTAHIS_BR.rp.where = "END_DATE <= :WS_DDT";
        TDTAHIS_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRAHIS, this, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_TDTAHIS_ACONO_SDDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, TDRAHIS);
        CEP.TRC(SCCGWA, "22222222");
        TDRAHIS.KEY.ACO_AC = WS_ACO_NO;
        TDTAHIS_BR.rp = new DBParm();
        TDTAHIS_BR.rp.TableName = "TDTAHIS";
        TDTAHIS_BR.rp.eqWhere = "ACO_AC";
        TDTAHIS_BR.rp.where = "STR_DATE >= :WS_SDT "
            + "AND END_DATE <= :WS_DDT";
        TDTAHIS_BR.rp.order = "STR_DATE";
        IBS.STARTBR(SCCGWA, TDRAHIS, this, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTAHIS_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRAHIS, this, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTAHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRAHIS, this, TDTAHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTAHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TDTAHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTAHIS_BR);
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
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
