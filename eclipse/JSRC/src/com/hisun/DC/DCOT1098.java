package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1098 {
    DBParm DCTCDDAT_RD;
    DBParm DCTCRDLT_RD;
    brParm DCTCRDLT_BR = new brParm();
    int WS_CNT = 0;
    String WS_CARD_NO = " ";
    int WS_RCD_SEQ = 0;
    String K_OUTPUT_FMT = "DC061";
    int K_SCR_ROW_NO = 5;
    String WS_MSGID = " ";
    short WS_NUM = 0;
    short WS_A = 0;
    short WS_PAGE_ROW_NO = 0;
    DCOT1098_WS_FMT WS_FMT = new DCOT1098_WS_FMT();
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    DCB1098_AWA_1098 DCB1098_AWA_1098;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT1098 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1098_AWA_1098>");
        DCB1098_AWA_1098 = (DCB1098_AWA_1098) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, DCRCRDLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CHECK_CARD_NO();
        B030_GET_LIST();
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB1098_AWA_1098.FUNC);
        CEP.TRC(SCCGWA, DCB1098_AWA_1098.CARD_NO);
        CEP.TRC(SCCGWA, DCB1098_AWA_1098.PAGE_NUM);
        CEP.TRC(SCCGWA, DCB1098_AWA_1098.PAGE_ROW);
        if (DCB1098_AWA_1098.CARD_NO.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1098_AWA_1098.PAGE_ROW == 0) {
            WS_PAGE_ROW_NO = (short) K_SCR_ROW_NO;
        } else {
            WS_PAGE_ROW_NO = (short) DCB1098_AWA_1098.PAGE_ROW;
        }
        if (DCB1098_AWA_1098.PAGE_ROW > K_SCR_ROW_NO) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_ERR_PAGE_ROW;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_CARD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        T000_READ_DCTCDDAT();
        WS_CARD_NO = DCB1098_AWA_1098.CARD_NO;
        WS_FMT.WS_PROD_NO = DCRCDDAT.PROD_CD;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        T000_COUNT_DCTCRDLT_BY_CARD_NO();
        T000_STARTBR_DCTCRDLT_BY_CARDNO();
        if (DCB1098_AWA_1098.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( DCB1098_AWA_1098.PAGE_NUM - 1 ) * WS_PAGE_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_DCTCRDLT_FIRST();
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_NUM < WS_PAGE_ROW_NO 
            && WS_TBL_FLAG != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            CEP.TRC(SCCGWA, "**********************");
            CEP.TRC(SCCGWA, WS_NUM);
            WS_FMT.WS_DATA[WS_NUM-1].WS_REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
            WS_FMT.WS_DATA[WS_NUM-1].WS_MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_STA_DT = DCRCRDLT.STA_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_END_DT = DCRCRDLT.END_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_DT = DCRCRDLT.UPDTBL_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_UPD_TLR = DCRCRDLT.UPDTBL_TLR;
            T000_READNEXT_DCTCRDLT();
        }
        T000_ENDBR_DCTCRDLT();
        if (DCB1098_AWA_1098.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = DCB1098_AWA_1098.PAGE_NUM;
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
        SCCFMT.DATA_LEN = 807;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCRCDDAT.KEY.CARD_NO = DCB1098_AWA_1098.CARD_NO;
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_COUNT_DCTCRDLT_BY_CARD_NO() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.set = "WS-CNT=COUNT(*)";
        DCTCRDLT_RD.where = "CARD_NO = :WS_CARD_NO";
        IBS.GROUP(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
        WS_FMT.WS_TOTAL_NUM = WS_CNT;
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        WS_A = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW_NO);
        WS_FMT.WS_TOTAL_PAGE = (int) ((WS_FMT.WS_TOTAL_NUM - WS_A) / WS_PAGE_ROW_NO);
        if (WS_A > 0) {
            WS_FMT.WS_TOTAL_PAGE = WS_FMT.WS_TOTAL_PAGE + 1;
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void T000_STARTBR_DCTCRDLT_BY_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCTCRDLT_BR.rp = new DBParm();
        DCTCRDLT_BR.rp.TableName = "DCTCRDLT";
        DCTCRDLT_BR.rp.where = "CARD_NO = :WS_CARD_NO";
        IBS.STARTBR(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_DCTCRDLT_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCRDLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCRDLT_BR);
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
