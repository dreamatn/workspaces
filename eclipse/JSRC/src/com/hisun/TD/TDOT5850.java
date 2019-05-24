package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5850 {
    DBParm TDTINST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTPWH_RD;
    brParm TDTPWH_BR = new brParm();
    int WS_CNT = 0;
    String WS_ACO_NO = " ";
    String WS_AC_NO = " ";
    int WS_RCD_SEQ = 0;
    int WS_CRT_DATE = 0;
    int WS_UPD_DATE = 0;
    String K_OUTPUT_FMT = "TD585";
    int K_SCR_ROW_NO = 15;
    String WS_MSGID = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    short WS_PAGE_ROW_NO = 0;
    char WS_TBL_FLAG = ' ';
    char WS_CALL_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    TDRSMST TDRSMST = new TDRSMST();
    TDRPWH TDRPWH = new TDRPWH();
    TDRINST TDRINST = new TDRINST();
    TDCF585 TDCF585 = new TDCF585();
    SCCGWA SCCGWA;
    TDB5850_AWA_5850 TDB5850_AWA_5850;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5850 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5850_AWA_5850>");
        TDB5850_AWA_5850 = (TDB5850_AWA_5850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_ACO_NO();
        B030_GET_LIST();
        CEP.TRC(SCCGWA, TDCF585.FMT.ACO_AC);
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.AC_NO);
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.BV_TYP);
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.AC_SEQ);
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.BV_NO);
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.PAGE_ROW);
        CEP.TRC(SCCGWA, TDB5850_AWA_5850.PAGE_NUM);
        if (TDB5850_AWA_5850.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5850_AWA_5850.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5850_AWA_5850.PAGE_ROW == 0) {
            WS_PAGE_ROW_NO = (short) K_SCR_ROW_NO;
        } else {
            if (TDB5850_AWA_5850.PAGE_ROW <= K_SCR_ROW_NO) {
                WS_PAGE_ROW_NO = (short) TDB5850_AWA_5850.PAGE_ROW;
            }
        }
        if (TDB5850_AWA_5850.START_DT > TDB5850_AWA_5850.END_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDB5850_AWA_5850.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDB5850_AWA_5850.AC_SEQ;
        CICQACAC.DATA.BV_NO = TDB5850_AWA_5850.BV_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (!CICQACAC.DATA.BV_NO.equalsIgnoreCase(CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO) 
            && CICQACAC.DATA.BV_NO.trim().length() > 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_ACO_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        TDCF585.FMT.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_ACO_NO;
        T000_READ_TDTSMST_BY_ACO_NO();
        TDCF585.FMT.AC_NO = TDRSMST.AC_NO;
        TDCF585.FMT.LMT_BAL = TDRSMST.BAL;
        TDCF585.FMT.OPEN_BAL = TDRSMST.FBAL;
        TDCF585.FMT.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCF585.FMT.MAIN_AC_O = TDRSMST.OPEN_DR_AC;
        TDCF585.FMT.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_TDTINST_BY_ACO_NO();
        if (WS_CALL_FLG == 'N') {
            if (TDB5850_AWA_5850.BV_TYP == '7' 
                || TDB5850_AWA_5850.BV_TYP == '8') {
                CICQACRL.FUNC = '4';
                CICQACRL.DATA.AC_NO = WS_AC_NO;
                CICQACRL.DATA.AC_REL = "07";
                S000_CALL_CIZQACRL();
                if (CICQACRL.RC.RC_CODE != 0) {
                } else {
                    TDCF585.FMT.AC_SEQ_O = CICQACRL.O_DATA.O_REL_AC_NO;
                }
            } else {
                TDCF585.FMT.AC_SEQ_O = " ";
            }
        } else {
            TDCF585.FMT.AC_SEQ_O = TDRINST.STL_AC;
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO);
        WS_CRT_DATE = TDB5850_AWA_5850.START_DT;
        WS_UPD_DATE = TDB5850_AWA_5850.END_DT;
        T000_COUNT_TDTPWH_BY_ACO_NO();
        T000_STARTBR_TDTPWH_BY_ACO_NO();
        if (TDB5850_AWA_5850.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( TDB5850_AWA_5850.PAGE_NUM - 1 ) * WS_PAGE_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_TDTPWH_FIRST();
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_ROW_NO);
        while (WS_NUM < WS_PAGE_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            CEP.TRC(SCCGWA, "**********************");
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, TDRPWH.TX_TYPE);
            TDCF585.FMT.DATA[WS_NUM-1].PART_SEQ_NO = TDRPWH.KEY.PART_SEQ_NO;
            TDCF585.FMT.DATA[WS_NUM-1].TRADE_DATE = TDRPWH.TRADE_DATE;
            TDCF585.FMT.DATA[WS_NUM-1].JRNNO = TDRPWH.JRNNO;
            TDCF585.FMT.DATA[WS_NUM-1].PRIN_AMT = TDRPWH.PRIN_AMT;
            TDCF585.FMT.DATA[WS_NUM-1].INT_SEL = TDRPWH.INT_SEL;
            TDCF585.FMT.DATA[WS_NUM-1].RATE = TDRPWH.RATE;
            TDCF585.FMT.DATA[WS_NUM-1].INT_AMT = TDRPWH.INT_AMT;
            TDCF585.FMT.DATA[WS_NUM-1].INT_AMT_PY = TDRPWH.INT_AMT_PY;
            TDCF585.FMT.DATA[WS_NUM-1].INT_AMT_TX = TDRPWH.INT_AMT_TX;
            TDCF585.FMT.DATA[WS_NUM-1].FEE_CCY = TDRPWH.FEE_CCY;
            TDCF585.FMT.DATA[WS_NUM-1].BEF_AMT = TDRPWH.BEF_AMT;
            TDCF585.FMT.DATA[WS_NUM-1].INOUT = TDRPWH.INOUT;
            TDCF585.FMT.DATA[WS_NUM-1].STL_MTH = TDRPWH.STL_MTH;
            TDCF585.FMT.DATA[WS_NUM-1].STL_AC = TDRPWH.STL_AC;
            TDCF585.FMT.DATA[WS_NUM-1].REMMIT_BK = TDRPWH.REMMIT_BK;
            TDCF585.FMT.DATA[WS_NUM-1].REMMIT_NM = TDRPWH.REMMIT_NM;
            TDCF585.FMT.DATA[WS_NUM-1].INT_INOUT = TDRPWH.INT_INOUT;
            TDCF585.FMT.DATA[WS_NUM-1].STL_INT_MTH = TDRPWH.STL_INT_MTH;
            TDCF585.FMT.DATA[WS_NUM-1].STL_INT_AC = TDRPWH.STL_INT_AC;
            TDCF585.FMT.DATA[WS_NUM-1].INT_REMMIT_BK = TDRPWH.INT_REMMIT_BK;
            TDCF585.FMT.DATA[WS_NUM-1].INT_REMMIT_NM = TDRPWH.INT_REMMIT_NM;
            TDCF585.FMT.DATA[WS_NUM-1].CRT_TLR = TDRPWH.CRT_TLR;
            TDCF585.FMT.DATA[WS_NUM-1].CRT_DATE = TDRPWH.CRT_DATE;
            TDCF585.FMT.DATA[WS_NUM-1].TX_TYPE = TDRPWH.TX_TYPE;
            CEP.TRC(SCCGWA, TDCF585.FMT.DATA[WS_NUM-1].TX_TYPE);
            T000_READNEXT_TDTPWH();
        }
        T000_ENDBR_TDTPWH();
        if (TDB5850_AWA_5850.PAGE_NUM == 0) {
            TDCF585.FMT.CURR_PAGE = 1;
        } else {
            TDCF585.FMT.CURR_PAGE = (short) TDB5850_AWA_5850.PAGE_NUM;
        }
        TDCF585.FMT.LAST_PAGE = 'N';
        if (WS_NUM <= WS_PAGE_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            TDCF585.FMT.LAST_PAGE = 'Y';
        }
        TDCF585.FMT.PAGE_ROW = WS_NUM;
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCF585;
        SCCFMT.DATA_LEN = 7640;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_TDTINST_BY_ACO_NO() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        TDTINST_RD.where = "ACO_AC = :WS_ACO_NO";
        IBS.READ(SCCGWA, TDRINST, this, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CALL_FLG = 'Y';
        } else {
            WS_CALL_FLG = 'N';
        }
    }
    public void T000_READ_TDTSMST_BY_ACO_NO() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_COUNT_TDTPWH_BY_ACO_NO() throws IOException,SQLException,Exception {
        TDTPWH_RD = new DBParm();
        TDTPWH_RD.TableName = "TDTPWH";
        TDTPWH_RD.set = "WS-CNT=COUNT(*)";
        TDTPWH_RD.where = "ACO_AC = :WS_ACO_NO "
            + "AND CRT_DATE >= :WS_CRT_DATE "
            + "AND UPD_DATE <= :WS_UPD_DATE";
        IBS.GROUP(SCCGWA, TDRPWH, this, TDTPWH_RD);
        TDCF585.FMT.TOTAL_NUM = (short) WS_CNT;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, TDCF585.FMT.TOTAL_NUM);
        WS_A = (short) (TDCF585.FMT.TOTAL_NUM % WS_PAGE_ROW_NO);
        TDCF585.FMT.TOTAL_PAGE = (short) ((TDCF585.FMT.TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            TDCF585.FMT.TOTAL_PAGE = (short) (TDCF585.FMT.TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, TDCF585.FMT.TOTAL_PAGE);
    }
    public void T000_STARTBR_TDTPWH_BY_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, TDRPWH);
        CEP.TRC(SCCGWA, "22222222");
        TDTPWH_BR.rp = new DBParm();
        TDTPWH_BR.rp.TableName = "TDTPWH";
        TDTPWH_BR.rp.where = "ACO_AC = :WS_ACO_NO "
            + "AND CRT_DATE >= :WS_CRT_DATE "
            + "AND UPD_DATE <= :WS_UPD_DATE";
        IBS.STARTBR(SCCGWA, TDRPWH, this, TDTPWH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPWH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTPWH_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPWH, this, TDTPWH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "TDTPWH FND");
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "TDTPWH NOT FND");
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPWH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTPWH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPWH, this, TDTPWH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTPWH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TDTPWH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPWH_BR);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
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
