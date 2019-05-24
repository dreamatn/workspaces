package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8528 {
    int JIBS_tmp_int;
    brParm BPTVCHT_BR = new brParm();
    brParm BPTVCHH_BR = new brParm();
    DBParm BPTVCHT_RD;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "AI250";
    String K_OUTPUT_FMT_B = "AIX01";
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 10;
    short K_SCR_COL_CNT = 7;
    short K_MAX_BUTT_CNT = 9;
    int K_MAX_ROW = 12;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    char WS_FLG = ' ';
    int WS_NUM = 0;
    int WS_A = 0;
    int WS_CX = 0;
    short WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    String WS_TBL_BPTVCH = " ";
    AIOT8528_WS_FMT WS_FMT = new AIOT8528_WS_FMT();
    char WS_TBL_FLAG = ' ';
    char WS_BROWSE_FLG = ' ';
    int WS_AC_DATE = 0;
    String WS_OTHSYS_KEY = " ";
    String WS_SET_NO = " ";
    int WS_RCD_SEQ = 0;
    int WS_COUNT_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPRVCHH BPRVCHH = new BPRVCHH();
    SCCQJRN SCCQJRN = new SCCQJRN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB8528_AWA_8528 AIB8528_AWA_8528;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT8528 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8528_AWA_8528>");
        AIB8528_AWA_8528 = (AIB8528_AWA_8528) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_TBL_BPTVCH = "BPTVCHT";
        } else {
            WS_TBL_BPTVCH = "BPTVCHH";
        }
        CEP.TRC(SCCGWA, "TEST WLL111");
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.PAGE_ROW);
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.PAGE_NUM);
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_TBL_BPTVCH);
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE_ROW);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_DFT_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB8528_AWA_8528.AC_DT == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, AIB8528_AWA_8528.AC_DT);
        }
        if (AIB8528_AWA_8528.AC_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, AIB8528_AWA_8528.AC_DT);
        }
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.OTHSYS_K);
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.SET_NO);
        if (AIB8528_AWA_8528.OTHSYS_K.trim().length() == 0 
            && AIB8528_AWA_8528.SET_NO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, AIB8528_AWA_8528.OTHSYS_K);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
    }
    public void B200_INF_DFT_PROC() throws IOException,SQLException,Exception {
        B020_BROWSE_PROCESS();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            IBS.init(SCCGWA, BPRVCHT);
        } else {
            IBS.init(SCCGWA, BPRVCHH);
        }
        BPRVCHT.KEY.AC_DATE = AIB8528_AWA_8528.AC_DT;
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.SET_NO);
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.OTHSYS_K);
        if (AIB8528_AWA_8528.SET_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "IFIFI");
            IBS.init(SCCGWA, SCCQJRN);
            SCCQJRN.INP_DATA.EX_SYS_JRN = AIB8528_AWA_8528.OTHSYS_K;
            S000_CALL_SCZQJRN();
            WS_SET_NO = "" + SCCQJRN.OUT_DATA.HOST_JRN;
            JIBS_tmp_int = WS_SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) WS_SET_NO = "0" + WS_SET_NO;
            CEP.TRC(SCCGWA, SCCQJRN.OUT_DATA.HOST_JRN);
        } else {
            CEP.TRC(SCCGWA, "ELSE");
            WS_SET_NO = AIB8528_AWA_8528.SET_NO;
        }
        T000_STARTBR_AITVCH();
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.PAGE_ROW);
        CEP.TRC(SCCGWA, K_MAX_ROW);
        if (AIB8528_AWA_8528.PAGE_ROW > K_MAX_ROW 
            || AIB8528_AWA_8528.PAGE_ROW == 0) {
            WS_PAGE_ROW = (short) K_MAX_ROW;
        } else {
            WS_PAGE_ROW = (short) AIB8528_AWA_8528.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        B020_01_OUT_PAGE_TITLE();
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.PAGE_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        if (AIB8528_AWA_8528.PAGE_NUM > 0) {
            WS_CX = ( AIB8528_AWA_8528.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        } else {
            WS_CX = 1;
        }
        WS_RCD_SEQ = WS_CX;
        CEP.TRC(SCCGWA, WS_CX);
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_AITVCH_FIRST();
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        WS_NUM = 0;
        for (WS_NUM = 1; WS_NUM <= WS_PAGE_ROW 
            && WS_FMT.WS_LAST_PAGE != 'Y'; WS_NUM += 1) {
            CEP.TRC(SCCGWA, WS_NUM);
            if (AIB8528_AWA_8528.OTHSYS_K.trim().length() > 0 
                && AIB8528_AWA_8528.SET_NO.trim().length() > 0 
                && AIB8528_AWA_8528.OTHSYS_K.equalsIgnoreCase(BPRVCHT.OTHSYS_KEY)) {
                CEP.TRC(SCCGWA, "IF11111");
                WS_FLG = 'Y';
                B030_GET_BPTVCH_DATA();
            }
            if (AIB8528_AWA_8528.SET_NO.trim().length() == 0 
                || AIB8528_AWA_8528.OTHSYS_K.trim().length() == 0) {
                CEP.TRC(SCCGWA, "IF22222");
                B030_GET_BPTVCH_DATA();
            }
            CEP.TRC(SCCGWA, "WL005");
            T000_READNEXT_AITVCH();
            CEP.TRC(SCCGWA, "HERE : ");
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, "HERE : ");
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
        }
        if (AIB8528_AWA_8528.OTHSYS_K.trim().length() > 0 
            && AIB8528_AWA_8528.SET_NO.trim().length() > 0 
            && WS_FLG != 'Y') {
            CEP.TRC(SCCGWA, "AWA-OUTSETNO NOT= QVCH-OTHSYS-KEY");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXREF_NO_WRONG;
            S000_ERR_MSG_PROC();
        }
        T000_ENDBR_AITVCH();
        if (AIB8528_AWA_8528.PAGE_NUM == 0) {
            WS_FMT.WS_CURR_PAGE = 1;
        } else {
            WS_FMT.WS_CURR_PAGE = (short) AIB8528_AWA_8528.PAGE_NUM;
        }
        WS_FMT.WS_CURR_PAGE_ROW = (short) (WS_NUM - 1);
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_FMT.WS_CURR_PAGE_ROW);
        B090_DATA_OUTPUT();
    }
    public void B020_01_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.PAGE_NUM == 0) {
            WS_COUNT_NO = 0;
            CEP.TRC(SCCGWA, "WLL001");
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            T000_COUNT_BPTVCHT();
            CEP.TRC(SCCGWA, "WLL002");
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            WS_FMT.WS_TOTAL_NUM = (short) WS_COUNT_NO;
            CEP.TRC(SCCGWA, "WLL003");
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            WS_RECORD_NUM = (short) (WS_FMT.WS_TOTAL_NUM % WS_PAGE_ROW);
            WS_FMT.WS_TOTAL_PAGE = (short) ((WS_FMT.WS_TOTAL_NUM - WS_RECORD_NUM) / WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
            if (WS_RECORD_NUM > 0) {
                WS_FMT.WS_TOTAL_PAGE = (short) (WS_FMT.WS_TOTAL_PAGE + 1);
            }
        }
        CEP.TRC(SCCGWA, "WLL004");
        CEP.TRC(SCCGWA, WS_FMT.WS_TOTAL_PAGE);
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_B;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 8753;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_AITVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB8528_AWA_8528.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_TBL_BPTVCH);
        if (BPRVCHT.KEY.SET_NO == null) BPRVCHT.KEY.SET_NO = "";
        JIBS_tmp_int = BPRVCHT.KEY.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHT.KEY.SET_NO += " ";
        if (BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHT.KEY.PART_NO = 0;
        else BPRVCHT.KEY.PART_NO = Short.parseShort(BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            CEP.TRC(SCCGWA, AIB8528_AWA_8528.AC_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, WS_TBL_BPTVCH);
            CEP.TRC(SCCGWA, WS_BROWSE_FLG);
            BPTVCHT_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
            else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
            BPTVCHT_BR.rp.where = "PART_NO = :BPRVCHT.KEY.PART_NO "
                + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :WS_SET_NO";
            BPTVCHT_BR.rp.order = "PART_NO,AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        } else {
            BPTVCHH_BR.rp = new DBParm();
            BPTVCHH_BR.rp.TableName = "BPTVCHH";
            BPTVCHH_BR.rp.where = "PART_NO = :BPRVCHT.KEY.PART_NO "
                + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
                + "AND SET_NO = :WS_SET_NO";
            BPTVCHH_BR.rp.order = "PART_NO,AC_DATE,SET_NO,SET_SEQ";
            IBS.STARTBR(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TBL_BPTVCH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
        }
    }
    public void T000_COUNT_BPTVCHT() throws IOException,SQLException,Exception {
        WS_AC_DATE = AIB8528_AWA_8528.AC_DT;
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_NO);
        CEP.TRC(SCCGWA, BPRVCHT.OTHSYS_KEY);
        BPRVCHT.KEY.AC_DATE = WS_AC_DATE;
        BPRVCHT.KEY.SET_NO = WS_SET_NO;
        if (WS_SET_NO == null) WS_SET_NO = "";
        JIBS_tmp_int = WS_SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_SET_NO += " ";
        if (WS_SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHT.KEY.PART_NO = 0;
        else BPRVCHT.KEY.PART_NO = Short.parseShort(WS_SET_NO.substring(11 - 1, 11 + 2 - 1));
        BPRVCHT.OTHSYS_KEY = WS_OTHSYS_KEY;
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_NO);
        CEP.TRC(SCCGWA, BPRVCHT.OTHSYS_KEY);
        CEP.TRC(SCCGWA, "WLL008");
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        BPTVCHT_RD.set = "WS-COUNT-NO=COUNT(*)";
        BPTVCHT_RD.where = "PART_NO = :BPRVCHT.KEY.PART_NO "
            + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCHT.KEY.SET_NO";
        IBS.GROUP(SCCGWA, BPRVCHT, this, BPTVCHT_RD);
    }
    public void T000_READNEXT_AITVCH_FIRST() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            CEP.TRC(SCCGWA, AIB8528_AWA_8528.AC_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, "WLL readnext");
            IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FMT.WS_LAST_PAGE = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FMT.WS_LAST_PAGE = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TBL_BPTVCH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_AITVCH() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRVCHH, this, BPTVCHH_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FMT.WS_LAST_PAGE = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FMT.WS_LAST_PAGE = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TBL_BPTVCH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_AITVCH() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            IBS.ENDBR(SCCGWA, BPTVCHT_BR);
        } else {
            IBS.ENDBR(SCCGWA, BPTVCHH_BR);
        }
    }
    public void B030_GET_BPTVCH_DATA() throws IOException,SQLException,Exception {
        if (AIB8528_AWA_8528.AC_DT == SCCGWA.COMM_AREA.AC_DATE 
            && WS_TBL_BPTVCH.equalsIgnoreCase("BPTVCHT")) {
            WS_FMT.WS_DATA[WS_NUM-1].WS_AC_DT = BPRVCHT.KEY.AC_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SET_NO1 = BPRVCHT.KEY.SET_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SET_SEQ = BPRVCHT.KEY.SET_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AI_SEQ = BPRVCHT.AI_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_JRN_NO = BPRVCHT.JRN_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AP_MMO = BPRVCHT.AP_MMO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_CODE = BPRVCHT.TR_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_MMO = BPRVCHT.TR_MMO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_DT = BPRVCHT.TR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TIME = BPRVCHT.TR_TIME;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_BK = BPRVCHT.TR_BK;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_BR = BPRVCHT.TR_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TLR = BPRVCHT.TR_TELLER;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TM_NO = BPRVCHT.TM_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CHNL_NO = BPRVCHT.CHNL_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EC_IND = BPRVCHT.EC_IND;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ORG_DT = BPRVCHT.ORG_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ORG_VCHNO = BPRVCHT.ORG_VCHNO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_GEN_TYPE = BPRVCHT.GEN_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TYPE = BPRVCHT.TR_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODE_FLG = BPRVCHT.ODE_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODE_GRP_NO = BPRVCHT.ODE_GRP_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTH_TR_DT = BPRVCHT.OTH_TR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_ID = BPRVCHT.OTHSYS_ID;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_K = BPRVCHT.OTHSYS_KEY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BOOK_FLG = BPRVCHT.BOOK_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BR = BPRVCHT.BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ITM = BPRVCHT.ITM;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CCY = BPRVCHT.CCY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SIGN = BPRVCHT.SIGN;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AMT = BPRVCHT.AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_VAL_DT = BPRVCHT.VAL_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CNTR_TYPE = BPRVCHT.CNTR_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PROD_CODE = BPRVCHT.PROD_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AC_NO = BPRVCHT.AC_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_MIB_NO = BPRVCHT.MIB_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EVENT_CODE = BPRVCHT.EVENT_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_GLMST = BPRVCHT.GLMST;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CI_NO = BPRVCHT.CI_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_REF_NO = BPRVCHT.REF_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_RED_FLG = BPRVCHT.RED_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_NO = BPRVCHT.CRVS_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_SEQ = BPRVCHT.CRVS_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ONL_GMIB_F = BPRVCHT.ONL_GMIB_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ONL_GVCH_F = BPRVCHT.ONL_GVCH_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PORTFO_CD = BPRVCHT.PORTFO_CD;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_GUP = BPRVCHT.TR_GUP;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CHQ_NO = BPRVCHT.CHQ_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_POST_DATE = BPRVCHT.POST_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_POST_NARR = BPRVCHT.POST_NARR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_NARR_CD = BPRVCHT.NARR_CD;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DESC = BPRVCHT.DESC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EFF_DAYS = BPRVCHT.EFF_DAYS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PRINT_FLG = BPRVCHT.PRINT_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SUSPENSE_F = BPRVCHT.SUSPENSE_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SUSPENSE_R = BPRVCHT.SUSPENSE_RSN;
            WS_FMT.WS_DATA[WS_NUM-1].WS_THEIR_AC = BPRVCHT.THEIR_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETTLE_DT = BPRVCHT.SETTLE_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TLR_ID = BPRVCHT.TLR_ID;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TLR_BR = BPRVCHT.TLR_BR;
        } else {
            WS_FMT.WS_DATA[WS_NUM-1].WS_AC_DT = BPRVCHH.KEY.AC_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SET_NO1 = BPRVCHH.KEY.SET_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SET_SEQ = BPRVCHH.KEY.SET_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AI_SEQ = BPRVCHH.AI_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_JRN_NO = BPRVCHH.JRN_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AP_MMO = BPRVCHH.AP_MMO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_CODE = BPRVCHH.TR_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_MMO = BPRVCHH.TR_MMO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_DT = BPRVCHH.TR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TIME = BPRVCHH.TR_TIME;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_BK = BPRVCHH.TR_BK;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_BR = BPRVCHH.TR_BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TLR = BPRVCHT.TR_TELLER;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TM_NO = BPRVCHH.TM_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CHNL_NO = BPRVCHH.CHNL_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EC_IND = BPRVCHH.EC_IND;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ORG_DT = BPRVCHH.ORG_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ORG_VCHNO = BPRVCHH.ORG_VCHNO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_GEN_TYPE = BPRVCHH.GEN_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_TYPE = BPRVCHH.TR_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODE_FLG = BPRVCHH.ODE_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ODE_GRP_NO = BPRVCHH.ODE_GRP_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTH_TR_DT = BPRVCHH.OTH_TR_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_ID = BPRVCHH.OTHSYS_ID;
            WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_K = BPRVCHH.OTHSYS_KEY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BOOK_FLG = BPRVCHH.BOOK_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_BR = BPRVCHH.BR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ITM = BPRVCHH.ITM;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CCY = BPRVCHH.CCY;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SIGN = BPRVCHH.SIGN;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AMT = BPRVCHH.AMT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_VAL_DT = BPRVCHH.VAL_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CNTR_TYPE = BPRVCHH.CNTR_TYPE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PROD_CODE = BPRVCHH.PROD_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_AC_NO = BPRVCHH.AC_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_MIB_NO = BPRVCHH.MIB_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EVENT_CODE = BPRVCHH.EVENT_CODE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_GLMST = BPRVCHH.GLMST;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CI_NO = BPRVCHH.CI_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_REF_NO = BPRVCHH.REF_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_RED_FLG = BPRVCHH.RED_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_NO = BPRVCHH.CRVS_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_SEQ = BPRVCHH.CRVS_SEQ;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ONL_GMIB_F = BPRVCHH.ONL_GMIB_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_ONL_GVCH_F = BPRVCHH.ONL_GVCH_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PORTFO_CD = BPRVCHH.PORTFO_CD;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TR_GUP = BPRVCHH.TR_GUP;
            WS_FMT.WS_DATA[WS_NUM-1].WS_CHQ_NO = BPRVCHH.CHQ_NO;
            WS_FMT.WS_DATA[WS_NUM-1].WS_POST_DATE = BPRVCHH.POST_DATE;
            WS_FMT.WS_DATA[WS_NUM-1].WS_POST_NARR = BPRVCHH.POST_NARR;
            WS_FMT.WS_DATA[WS_NUM-1].WS_NARR_CD = BPRVCHH.NARR_CD;
            WS_FMT.WS_DATA[WS_NUM-1].WS_DESC = BPRVCHH.DESC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_EFF_DAYS = BPRVCHH.EFF_DAYS;
            WS_FMT.WS_DATA[WS_NUM-1].WS_PRINT_FLG = BPRVCHH.PRINT_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SUSPENSE_F = BPRVCHH.SUSPENSE_FLG;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SUSPENSE_R = BPRVCHH.SUSPENSE_RSN;
            WS_FMT.WS_DATA[WS_NUM-1].WS_THEIR_AC = BPRVCHH.THEIR_AC;
            WS_FMT.WS_DATA[WS_NUM-1].WS_SETTLE_DT = BPRVCHH.SETTLE_DT;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TLR_ID = BPRVCHH.TLR_ID;
            WS_FMT.WS_DATA[WS_NUM-1].WS_TLR_BR = BPRVCHH.TLR_BR;
        }
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SET_NO1);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SET_SEQ);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_BR);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_ITM);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CCY);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SIGN);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_AMT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_VAL_DT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CNTR_TYPE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_PROD_CODE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_AC_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_EVENT_CODE);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CI_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_REF_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CHQ_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_POST_NARR);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_NARR_CD);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_DESC);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_RED_FLG);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CRVS_SEQ);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_MIB_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_THEIR_AC);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_SETTLE_DT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_CHNL_NO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_EC_IND);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_ORG_VCHNO);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_OTH_TR_DT);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_ID);
        CEP.TRC(SCCGWA, WS_FMT.WS_DATA[WS_NUM-1].WS_OTHSYS_K);
    }
    public void S000_CALL_SCZQJRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-INQ-HOSTJRN", SCCQJRN);
        CEP.TRC(SCCGWA, SCCQJRN.OUT_DATA.RTN_CODE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
