package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1156 {
    int JIBS_tmp_int;
    DBParm FXTDIRFX_RD;
    brParm FXTDIRFX_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    int WK_MAX_ROW = 30;
    int WK_MONTHS = 12;
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_CNT = 0;
    short WS_PAGE_ROW = 0;
    short WS_PAGE_NUM = 0;
    short WS_TOTAL_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    char WS_END_FLG = ' ';
    int WS_DATE = 0;
    String WS_MSGID = " ";
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXCF156 FXCF156 = new FXCF156();
    int WS_COUNT = 0;
    String WS_AC_NO = " ";
    int WS_STR_DATE = 0;
    int WS_TRN_DATE = 0;
    int WS_END_DATE = 0;
    long WS_JRN_NO = 0;
    String WS_CHNL = " ";
    char WS_BS_FLG = ' ';
    int WS_RCD_SEQ = 0;
    SCCGWA SCCGWA;
    SCCGAPV SCCGAPV;
    FXB1156_AWA_1156 FXB1156_AWA_1156;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1156 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1156_AWA_1156>");
        FXB1156_AWA_1156 = (FXB1156_AWA_1156) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.AC_NO);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.STR_DATE);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.END_DATE);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.BS_FLG);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.BUS_PARM);
        if (FXB1156_AWA_1156.AC_NO.trim().length() == 0 
            || FXB1156_AWA_1156.AC_NO.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DDAC1_MUST);
        }
        if (FXB1156_AWA_1156.STR_DATE == 0 
            || FXB1156_AWA_1156.STR_DATE == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
        }
        if (FXB1156_AWA_1156.END_DATE == 0 
            || FXB1156_AWA_1156.END_DATE == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
        }
        if (FXB1156_AWA_1156.END_DATE < FXB1156_AWA_1156.STR_DATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
        }
        if (FXB1156_AWA_1156.BS_FLG != '0' 
            && FXB1156_AWA_1156.BS_FLG != '1' 
            && FXB1156_AWA_1156.BS_FLG != '2') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BSFLG_ERROR);
        }
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (!FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("0") 
            && !FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUSPARM_ERROR);
        }
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (!FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y") 
            && !FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("N")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUSPARM_ERROR);
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = FXB1156_AWA_1156.STR_DATE;
        SCCCLDT.MTHS = (short) WK_MONTHS;
        S000_CALL_SCSSCLDT();
        WS_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_DATE);
        if (WS_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_STRDT_ERR);
        }
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.PAGE_NUM);
        CEP.TRC(SCCGWA, FXB1156_AWA_1156.PAGE_ROW);
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
            if (FXB1156_AWA_1156.PAGE_NUM != 0 
                || FXB1156_AWA_1156.PAGE_ROW != 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUSPARM_ERROR);
            }
        }
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("N")) {
            if (FXB1156_AWA_1156.PAGE_NUM == 0 
                || (FXB1156_AWA_1156.JRN_NO != 0 
                && FXB1156_AWA_1156.JRN_NO != ' ')) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUSPARM_ERROR);
            }
        }
        if (FXB1156_AWA_1156.PAGE_ROW > WK_MAX_ROW 
            || FXB1156_AWA_1156.PAGE_ROW == 0) {
            WS_PAGE_ROW = (short) WK_MAX_ROW;
        } else {
            WS_PAGE_ROW = (short) FXB1156_AWA_1156.PAGE_ROW;
        }
        WS_PAGE_NUM = (short) FXB1156_AWA_1156.PAGE_NUM;
    }
    public void B010_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        IBS.init(SCCGWA, FXCF156);
        FXCF156.END_FLG = 'N';
        WS_AC_NO = FXB1156_AWA_1156.AC_NO;
        WS_STR_DATE = FXB1156_AWA_1156.STR_DATE;
        WS_END_DATE = FXB1156_AWA_1156.END_DATE;
        WS_JRN_NO = FXB1156_AWA_1156.JRN_NO;
        WS_CHNL = SCCGWA.COMM_AREA.CHNL;
        WS_BS_FLG = FXB1156_AWA_1156.BS_FLG;
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
            if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
            JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
            if (FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("0")) {
                if (FXB1156_AWA_1156.JRN_NO == ' ' 
                    || FXB1156_AWA_1156.JRN_NO == 0) {
                    B010_10_PROCESS_BY_DT();
                } else {
                    B010_15_PROCESS_BY_DT_JRN();
                }
            }
            if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
            JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
            if (FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("1")) {
                if (FXB1156_AWA_1156.JRN_NO == ' ' 
                    || FXB1156_AWA_1156.JRN_NO == 0) {
                    B010_10_PROCESS_BY_DT_1();
                } else {
                    B010_15_PROCESS_BY_DT_JRN_1();
                }
            }
        }
        if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
        JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
        if (FXB1156_AWA_1156.BUS_PARM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("N")) {
            R000_GET_TOTAL_NUM();
            if (WS_COUNT == 0) {
                FXCF156.END_FLG = 'Y';
            } else {
                WS_TOTAL_NUM = (short) WS_COUNT;
                FXCF156.TOTAL_NUM = WS_TOTAL_NUM;
                CEP.TRC(SCCGWA, WS_TOTAL_NUM);
                WS_REMAINDER = (short) (WS_TOTAL_NUM % WS_PAGE_ROW);
                WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / WS_PAGE_ROW);
                CEP.TRC(SCCGWA, WS_REMAINDER);
                if (WS_REMAINDER != 0) {
                    WS_TOTAL_PAGE += 1;
                }
                FXCF156.TOTAL_PAGE = WS_TOTAL_PAGE;
                CEP.TRC(SCCGWA, WS_TOTAL_PAGE);
                CEP.TRC(SCCGWA, WS_PAGE_NUM);
                CEP.TRC(SCCGWA, WS_PAGE_ROW);
                if (WS_PAGE_NUM == 0) {
                    WS_RCD_SEQ = 1;
                } else {
                    WS_RCD_SEQ = ( WS_PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
                }
                if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
                JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
                if (FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("0")) {
                    T000_STARTBR_FXTDIRFX();
                }
                if (FXB1156_AWA_1156.BUS_PARM == null) FXB1156_AWA_1156.BUS_PARM = "";
                JIBS_tmp_int = FXB1156_AWA_1156.BUS_PARM.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) FXB1156_AWA_1156.BUS_PARM += " ";
                if (FXB1156_AWA_1156.BUS_PARM.substring(0, 1).equalsIgnoreCase("1")) {
                    T000_STARTBR_FXTDIRFX_1();
                }
                CEP.TRC(SCCGWA, "READ FIRST");
                CEP.TRC(SCCGWA, WS_RCD_SEQ);
                IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    FXCF156.END_FLG = 'Y';
                }
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    R000_PEADNEXT_FXTDIRFX();
                }
                T000_ENDBR_FXTDIRFX();
            }
        }
        R000_FMT_OUTPUT();
    }
    public void B010_10_PROCESS_BY_DT() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
            + "AND TRN_DT <= :WS_END_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL";
        FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
            FXCF156.END_FLG = 'Y';
        }
        WS_CNT = 0;
        while (WS_END_FLG != 'Y' 
            && WS_CNT != WK_MAX_ROW) {
    }
    public void B010_15_PROCESS_BY_DT_JRN() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_END_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO = :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ENDJRN_ERR);
        }
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_END_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO < :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_STR_DATE != WS_END_DATE) {
                FXTDIRFX_RD = new DBParm();
                FXTDIRFX_RD.TableName = "FXTDIRFX";
                FXTDIRFX_RD.fst = true;
                FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
                    + "AND TRN_DT < :WS_END_DATE "
                    + "AND TRA_AC = :WS_AC_NO "
                    + "AND ST_TRCHNL = :WS_CHNL";
                FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
                IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_END_FLG = 'Y';
                    FXCF156.END_FLG = 'Y';
                }
            } else {
                WS_END_FLG = 'Y';
                FXCF156.END_FLG = 'Y';
            }
        }
        WS_CNT = 0;
        while (WS_END_FLG != 'Y' 
            && WS_CNT != WK_MAX_ROW) {
    }
    public void R000_GET_FX_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        if (FXB1156_AWA_1156.BS_FLG == '0' 
            || FXB1156_AWA_1156.BS_FLG == FXRDIRFX.B_S_FLG) {
            WS_CNT += 1;
            FXCF156.OUT_INFO[WS_CNT-1].TRN_DT = FXRDIRFX.KEY.TRN_DT;
            FXCF156.OUT_INFO[WS_CNT-1].TRN_TM = FXRDIRFX.EX_TIME;
            FXCF156.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.KEY.JRN_NO;
            FXCF156.OUT_INFO[WS_CNT-1].ST_CHNL = FXRDIRFX.ST_TRCHNL;
            FXCF156.OUT_INFO[WS_CNT-1].PROD_CD = FXRDIRFX.PROD_CD;
            FXCF156.OUT_INFO[WS_CNT-1].AC_NO = FXRDIRFX.TRA_AC;
            FXCF156.OUT_INFO[WS_CNT-1].STATUS = FXRDIRFX.STATUS;
            FXCF156.OUT_INFO[WS_CNT-1].ES_FLG = FXRDIRFX.B_S_FLG;
            FXCF156.OUT_INFO[WS_CNT-1].FX_RATE = FXRDIRFX.EX_RATE;
            FXCF156.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD1;
            if (FXRDIRFX.B_S_FLG == '1') {
                FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.SELL_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.BUY_CCY;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.BUY_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.B_CS_FLG;
            }
            if (FXRDIRFX.B_S_FLG == '2') {
                FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.BUY_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.SELL_CCY;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.SELL_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.S_CS_FLG;
            }
        }
        WS_JRN_NO = FXRDIRFX.KEY.JRN_NO;
        WS_TRN_DATE = FXRDIRFX.KEY.TRN_DT;
        CEP.TRC(SCCGWA, WS_TRN_DATE);
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_TRN_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO < :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_TRN_DATE != WS_STR_DATE) {
                FXTDIRFX_RD = new DBParm();
                FXTDIRFX_RD.TableName = "FXTDIRFX";
                FXTDIRFX_RD.fst = true;
                FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
                    + "AND TRN_DT < :WS_TRN_DATE "
                    + "AND TRA_AC = :WS_AC_NO "
                    + "AND ST_TRCHNL = :WS_CHNL";
                FXTDIRFX_RD.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
                IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_END_FLG = 'Y';
                    FXCF156.END_FLG = 'Y';
                }
            } else {
                WS_END_FLG = 'Y';
                FXCF156.END_FLG = 'Y';
            }
        }
    }
    public void B010_10_PROCESS_BY_DT_1() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
            + "AND TRN_DT <= :WS_END_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL";
        FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
            FXCF156.END_FLG = 'Y';
        }
        WS_CNT = 0;
        while (WS_END_FLG != 'Y' 
            && WS_CNT != WK_MAX_ROW) {
    }
    public void B010_15_PROCESS_BY_DT_JRN_1() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_STR_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO = :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_STRJRN_ERR);
        }
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_STR_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO > :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_STR_DATE != WS_END_DATE) {
                FXTDIRFX_RD = new DBParm();
                FXTDIRFX_RD.TableName = "FXTDIRFX";
                FXTDIRFX_RD.fst = true;
                FXTDIRFX_RD.where = "TRN_DT > :WS_STR_DATE "
                    + "AND TRN_DT <= :WS_END_DATE "
                    + "AND TRA_AC = :WS_AC_NO "
                    + "AND ST_TRCHNL = :WS_CHNL";
                FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
                IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_END_FLG = 'Y';
                    FXCF156.END_FLG = 'Y';
                }
            } else {
                WS_END_FLG = 'Y';
                FXCF156.END_FLG = 'Y';
            }
        }
        WS_CNT = 0;
        while (WS_END_FLG != 'Y' 
            && WS_CNT != WK_MAX_ROW) {
    }
    public void R000_GET_FX_REC_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        if (FXB1156_AWA_1156.BS_FLG == '0' 
            || FXB1156_AWA_1156.BS_FLG == FXRDIRFX.B_S_FLG) {
            WS_CNT += 1;
            FXCF156.OUT_INFO[WS_CNT-1].TRN_DT = FXRDIRFX.KEY.TRN_DT;
            FXCF156.OUT_INFO[WS_CNT-1].TRN_TM = FXRDIRFX.EX_TIME;
            FXCF156.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.KEY.JRN_NO;
            FXCF156.OUT_INFO[WS_CNT-1].ST_CHNL = FXRDIRFX.ST_TRCHNL;
            FXCF156.OUT_INFO[WS_CNT-1].PROD_CD = FXRDIRFX.PROD_CD;
            FXCF156.OUT_INFO[WS_CNT-1].AC_NO = FXRDIRFX.TRA_AC;
            FXCF156.OUT_INFO[WS_CNT-1].STATUS = FXRDIRFX.STATUS;
            FXCF156.OUT_INFO[WS_CNT-1].ES_FLG = FXRDIRFX.B_S_FLG;
            FXCF156.OUT_INFO[WS_CNT-1].FX_RATE = FXRDIRFX.EX_RATE;
            FXCF156.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD1;
            if (FXRDIRFX.B_S_FLG == '1') {
                FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.SELL_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.BUY_CCY;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.BUY_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.B_CS_FLG;
            }
            if (FXRDIRFX.B_S_FLG == '2') {
                FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.BUY_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.SELL_CCY;
                FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.SELL_AMT;
                FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.S_CS_FLG;
            }
        }
        WS_JRN_NO = FXRDIRFX.KEY.JRN_NO;
        WS_TRN_DATE = FXRDIRFX.KEY.TRN_DT;
        CEP.TRC(SCCGWA, WS_TRN_DATE);
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.fst = true;
        FXTDIRFX_RD.where = "TRN_DT = :WS_TRN_DATE "
            + "AND TRA_AC = :WS_AC_NO "
            + "AND ST_TRCHNL = :WS_CHNL "
            + "AND JRN_NO > :WS_JRN_NO";
        FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_TRN_DATE != WS_END_DATE) {
                FXTDIRFX_RD = new DBParm();
                FXTDIRFX_RD.TableName = "FXTDIRFX";
                FXTDIRFX_RD.fst = true;
                FXTDIRFX_RD.where = "TRN_DT > :WS_TRN_DATE "
                    + "AND TRN_DT <= :WS_END_DATE "
                    + "AND TRA_AC = :WS_AC_NO "
                    + "AND ST_TRCHNL = :WS_CHNL";
                FXTDIRFX_RD.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
                IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_END_FLG = 'Y';
                    FXCF156.END_FLG = 'Y';
                }
            } else {
                WS_END_FLG = 'Y';
                FXCF156.END_FLG = 'Y';
            }
        }
    }
    public void R000_FMT_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCF156.END_FLG);
        CEP.TRC(SCCGWA, FXCF156.TOTAL_PAGE);
        CEP.TRC(SCCGWA, FXCF156.TOTAL_NUM);
        CEP.TRC(SCCGWA, FXCF156.CURR_PAGE);
        CEP.TRC(SCCGWA, FXCF156.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, FXCF156.OUT_INFO[1-1].JRN_NO);
        if (WS_CNT > 1) {
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].JRN_NO);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].TRN_TM);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].ST_CHNL);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].PROD_CD);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].AC_NO);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].STATUS);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].ES_FLG);
            CEP.TRC(SCCGWA, FXCF156.OUT_INFO[WS_CNT-1].EXST_CD);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX156";
        SCCFMT.DATA_PTR = FXCF156;
        SCCFMT.DATA_LEN = 6025;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_TOTAL_NUM() throws IOException,SQLException,Exception {
        if (WS_BS_FLG == '0') {
            FXTDIRFX_RD = new DBParm();
            FXTDIRFX_RD.TableName = "FXTDIRFX";
            FXTDIRFX_RD.set = "WS-COUNT=COUNT(*)";
            FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND ( B_S_FLG = '1' "
                + "OR B_S_FLG = '2' ) "
                + "AND ST_TRCHNL = :WS_CHNL";
            IBS.GROUP(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        } else {
            FXTDIRFX_RD = new DBParm();
            FXTDIRFX_RD.TableName = "FXTDIRFX";
            FXTDIRFX_RD.set = "WS-COUNT=COUNT(*)";
            FXTDIRFX_RD.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND B_S_FLG = :WS_BS_FLG "
                + "AND ST_TRCHNL = :WS_CHNL";
            IBS.GROUP(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        }
    }
    public void T000_STARTBR_FXTDIRFX() throws IOException,SQLException,Exception {
        if (WS_BS_FLG == '0') {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.col = "TRN_DT, EX_TIME, JRN_NO, ST_TRCHNL, PROD_CD, TRA_AC, STATUS, BUY_CCY, BUY_AMT, B_CS_FLG, SELL_CCY, SELL_AMT, S_CS_FLG, B_S_FLG, EX_RATE, EXST_CD1";
            FXTDIRFX_BR.rp.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND ( B_S_FLG = '1' "
                + "OR B_S_FLG = '2' ) "
                + "AND ST_TRCHNL = :WS_CHNL";
            FXTDIRFX_BR.rp.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.col = "TRN_DT, EX_TIME, JRN_NO, ST_TRCHNL, PROD_CD, TRA_AC, STATUS, BUY_CCY, BUY_AMT, B_CS_FLG, SELL_CCY, SELL_AMT, S_CS_FLG, B_S_FLG, EX_RATE, EXST_CD1";
            FXTDIRFX_BR.rp.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND B_S_FLG = :WS_BS_FLG "
                + "AND ST_TRCHNL = :WS_CHNL";
            FXTDIRFX_BR.rp.order = "TRN_DT DESC,JRN_NO DESC,TRA_AC,ST_TRCHNL";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        }
    }
    public void T000_STARTBR_FXTDIRFX_1() throws IOException,SQLException,Exception {
        if (WS_BS_FLG == '0') {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.col = "TRN_DT, EX_TIME, JRN_NO, ST_TRCHNL, PROD_CD, TRA_AC, STATUS, BUY_CCY, BUY_AMT, B_CS_FLG, SELL_CCY, SELL_AMT, S_CS_FLG, B_S_FLG, EX_RATE, EXST_CD1";
            FXTDIRFX_BR.rp.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND ( B_S_FLG = '1' "
                + "OR B_S_FLG = '2' ) "
                + "AND ST_TRCHNL = :WS_CHNL";
            FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        } else {
            FXTDIRFX_BR.rp = new DBParm();
            FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
            FXTDIRFX_BR.rp.col = "TRN_DT, EX_TIME, JRN_NO, ST_TRCHNL, PROD_CD, TRA_AC, STATUS, BUY_CCY, BUY_AMT, B_CS_FLG, SELL_CCY, SELL_AMT, S_CS_FLG, B_S_FLG, EX_RATE, EXST_CD1";
            FXTDIRFX_BR.rp.where = "TRN_DT >= :WS_STR_DATE "
                + "AND TRN_DT <= :WS_END_DATE "
                + "AND TRA_AC = :WS_AC_NO "
                + "AND B_S_FLG = :WS_BS_FLG "
                + "AND ST_TRCHNL = :WS_CHNL";
            FXTDIRFX_BR.rp.order = "TRN_DT,JRN_NO,TRA_AC,ST_TRCHNL";
            IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        }
    }
    public void R000_PEADNEXT_FXTDIRFX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        for (WS_CNT = 1; WS_END_FLG != 'Y' 
            && WS_CNT <= WS_PAGE_ROW; WS_CNT += 1) {
            B020_70_OUTPUT_DETAIL();
            T000_READNEXT_FXTDIRFX();
        }
        WS_CNT -= 1;
        FXCF156.PAGE_ROW = WS_CNT;
        FXCF156.CURR_PAGE = FXB1156_AWA_1156.PAGE_NUM;
    }
    public void T000_READNEXT_FXTDIRFX() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_END_FLG = 'Y';
            FXCF156.END_FLG = 'Y';
        }
    }
    public void T000_ENDBR_FXTDIRFX() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void B020_70_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        CEP.TRC(SCCGWA, FXRDIRFX.STATUS);
        FXCF156.OUT_INFO[WS_CNT-1].TRN_DT = FXRDIRFX.KEY.TRN_DT;
        FXCF156.OUT_INFO[WS_CNT-1].TRN_TM = FXRDIRFX.EX_TIME;
        FXCF156.OUT_INFO[WS_CNT-1].JRN_NO = FXRDIRFX.KEY.JRN_NO;
        FXCF156.OUT_INFO[WS_CNT-1].ST_CHNL = FXRDIRFX.ST_TRCHNL;
        FXCF156.OUT_INFO[WS_CNT-1].PROD_CD = FXRDIRFX.PROD_CD;
        FXCF156.OUT_INFO[WS_CNT-1].AC_NO = FXRDIRFX.TRA_AC;
        FXCF156.OUT_INFO[WS_CNT-1].STATUS = FXRDIRFX.STATUS;
        FXCF156.OUT_INFO[WS_CNT-1].ES_FLG = FXRDIRFX.B_S_FLG;
        FXCF156.OUT_INFO[WS_CNT-1].FX_RATE = FXRDIRFX.EX_RATE;
        FXCF156.OUT_INFO[WS_CNT-1].EXST_CD = FXRDIRFX.EXST_CD1;
        if (FXRDIRFX.B_S_FLG == '1') {
            FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.SELL_AMT;
            FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.BUY_CCY;
            FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.BUY_AMT;
            FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.B_CS_FLG;
        }
        if (FXRDIRFX.B_S_FLG == '2') {
            FXCF156.OUT_INFO[WS_CNT-1].LCY_AMT = FXRDIRFX.BUY_AMT;
            FXCF156.OUT_INFO[WS_CNT-1].FCY_CCY = FXRDIRFX.SELL_CCY;
            FXCF156.OUT_INFO[WS_CNT-1].FCY_AMT = FXRDIRFX.SELL_AMT;
            FXCF156.OUT_INFO[WS_CNT-1].CASH_FLG = FXRDIRFX.S_CS_FLG;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
        }
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
