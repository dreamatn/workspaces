package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1290 {
    brParm DCTPSWMT_BR = new brParm();
    DBParm DCTPSWMT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC290";
    int K_MAX_ROW = 8;
    int K_MAX_COL = 8;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_TBL_PSWMT = "DCTPSWMT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_BAT_NO_TMP = 0;
    DCOT1290_WS_OUTPUT WS_OUTPUT = new DCOT1290_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPSWMT DCRPSWMT = new DCRPSWMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    int WS_DB_START_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_INCD_TYPE = " ";
    int WS_CNT_SCAD = 0;
    int WS_CNT_SCAD1 = 0;
    int WS_CLDT_DATE2 = 0;
    char WS_TBL_FLAG = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1290_AWA_1290 DCB1290_AWA_1290;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1290 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1290_AWA_1290>");
        DCB1290_AWA_1290 = (DCB1290_AWA_1290) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        BO11_DELETE_DATA();
        if (pgmRtn) return;
        B020_GET_PROD_PWSMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB1290_AWA_1290.CRT_DT);
        CEP.TRC(SCCGWA, DCB1290_AWA_1290.PROD_NO);
        if (DCB1290_AWA_1290.PROD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_PROD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB1290_AWA_1290.CRT_DT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CRT_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void BO11_DELETE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.MTHS = -3;
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_CLDT_DATE2 = SCCCLDT.DATE2;
        IBS.init(SCCGWA, DCRPSWMT);
        DCRPSWMT.KEY.CRT_DT = DCB1290_AWA_1290.CRT_DT;
        DCRPSWMT.PROD_CD = DCB1290_AWA_1290.PROD_NO;
        DCRPSWMT.APP_BR_C = DCB1290_AWA_1290.APP_BR;
        T000_DELETE_DCTPSWMT();
        if (pgmRtn) return;
    }
    public void B020_GET_PROD_PWSMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPSWMT);
        DCRPSWMT.KEY.CRT_DT = DCB1290_AWA_1290.CRT_DT;
        DCRPSWMT.PROD_CD = DCB1290_AWA_1290.PROD_NO;
        DCRPSWMT.APP_BR_C = DCB1290_AWA_1290.APP_BR;
        DCRPSWMT.APP_BAT_NO = DCB1290_AWA_1290.APP_NO;
        CEP.TRC(SCCGWA, "BROWSE START");
        CEP.TRC(SCCGWA, DCRPSWMT.KEY.CRT_DT);
        CEP.TRC(SCCGWA, DCRPSWMT.PROD_CD);
        CEP.TRC(SCCGWA, DCRPSWMT.APP_BR_C);
        CEP.TRC(SCCGWA, DCRPSWMT.APP_BAT_NO);
        CEP.TRC(SCCGWA, DCB1290_AWA_1290.PRIT_FLG);
        if (DCB1290_AWA_1290.PRIT_FLG != ' ') {
            DCRPSWMT.PRINT_FLG = DCB1290_AWA_1290.PRIT_FLG;
            T000_STARTBR_DCTPSWMT_1();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_DCTPSWMT_2();
            if (pgmRtn) return;
        }
        T000_READNEXT_DCTPSWMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRPSWMT.APP_BAT_NO);
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PRINT RECORD");
        CEP.TRC(SCCGWA, WS_CNT);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B022_OUTPUT_DETAIL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            T000_READNEXT_DCTPSWMT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTPSWMT();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START SCCMPAG");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 163;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        CEP.TRC(SCCGWA, SCCMPAG);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        CEP.TRC(SCCGWA, WS_BAT_NO_TMP);
        CEP.TRC(SCCGWA, DCRPSWMT.APP_BAT_NO);
        if (WS_BAT_NO_TMP != DCRPSWMT.APP_BAT_NO) {
            WS_CNT = WS_CNT + 1;
            WS_CNT_SCAD1 = 0;
            if (DCB1290_AWA_1290.PRIT_FLG == ' ') {
                T000_GROUP_DCTPSWMT1();
                if (pgmRtn) return;
            } else {
                T000_GROUP_DCTPSWMT2();
                if (pgmRtn) return;
            }
            WS_OUTPUT.WS_PROD_NO = DCRPSWMT.PROD_CD;
            WS_OUTPUT.WS_PROD_NAME = DCRPSWMT.PROD_NAME;
            WS_OUTPUT.WS_CRT_DT = DCRPSWMT.KEY.CRT_DT;
            WS_OUTPUT.WS_BAT_NO = DCRPSWMT.APP_BAT_NO;
            WS_BAT_NO_TMP = WS_OUTPUT.WS_BAT_NO;
            WS_OUTPUT.WS_APP_BR_C = DCRPSWMT.APP_BR_C;
            if (WS_CNT_SCAD1 > 0) {
                WS_OUTPUT.WS_PRINT_FLG = 'N';
            } else {
                WS_OUTPUT.WS_PRINT_FLG = DCRPSWMT.PRINT_FLG;
            }
            if (DCB1290_AWA_1290.PRIT_FLG != ' ') {
                WS_OUTPUT.WS_PRINT_FLG = DCB1290_AWA_1290.PRIT_FLG;
            }
            WS_OUTPUT.WS_COUNT = WS_CNT_SCAD1;
            CEP.TRC(SCCGWA, WS_OUTPUT);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
            SCCMPAG.DATA_LEN = 163;
            B_MPAG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCMPAG);
        }
    }
    public void T000_STARTBR_DCTPSWMT_1() throws IOException,SQLException,Exception {
        DCTPSWMT_BR.rp = new DBParm();
        DCTPSWMT_BR.rp.TableName = "DCTPSWMT";
        DCTPSWMT_BR.rp.col = "PROD_CD, PROD_NAME, APP_BR_C, APP_BR_C, APP_BAT_NO, PRINT_FLG";
        DCTPSWMT_BR.rp.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C "
            + "AND APP_BAT_NO >= :DCRPSWMT.APP_BAT_NO "
            + "AND PRINT_FLG = :DCRPSWMT.PRINT_FLG";
        DCTPSWMT_BR.rp.order = "APP_BR_C,PROD_CD,CRT_DT, APP_BAT_NO,CRT_SEQ";
        IBS.STARTBR(SCCGWA, DCRPSWMT, this, DCTPSWMT_BR);
    }
    public void T000_STARTBR_DCTPSWMT_2() throws IOException,SQLException,Exception {
        DCTPSWMT_BR.rp = new DBParm();
        DCTPSWMT_BR.rp.TableName = "DCTPSWMT";
        DCTPSWMT_BR.rp.col = "PROD_CD, PROD_NAME, APP_BR_C, APP_BR_C, APP_BAT_NO, PRINT_FLG";
        DCTPSWMT_BR.rp.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C "
            + "AND APP_BAT_NO >= :DCRPSWMT.APP_BAT_NO";
        DCTPSWMT_BR.rp.order = "APP_BR_C,PROD_CD,CRT_DT, APP_BAT_NO,CRT_SEQ";
        IBS.STARTBR(SCCGWA, DCRPSWMT, this, DCTPSWMT_BR);
    }
    public void T000_READNEXT_DCTPSWMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPSWMT, this, DCTPSWMT_BR);
        CEP.TRC(SCCGWA, DCRPSWMT);
    }
    public void T000_ENDBR_DCTPSWMT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPSWMT_BR);
    }
    public void T000_DELETE_DCTPSWMT() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.where = "CRT_DT <= :WS_CLDT_DATE2 "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD";
        IBS.DELETE(SCCGWA, DCRPSWMT, this, DCTPSWMT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PSWMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPSWMT_UPD() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.upd = true;
        IBS.READ(SCCGWA, DCRPSWMT, DCTPSWMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PSWMT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B010_10_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 163;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_GROUP_DCTPSWMT() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.set = "WS-CNT-SCAD=COUNT(*)";
        DCTPSWMT_RD.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C";
        IBS.GROUP(SCCGWA, DCRPSWMT, this, DCTPSWMT_RD);
    }
    public void T000_GROUP_DCTPSWMT1() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.set = "WS-CNT-SCAD1=COUNT(*)";
        DCTPSWMT_RD.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C "
            + "AND APP_BAT_NO = :DCRPSWMT.APP_BAT_NO "
            + "AND PRINT_FLG = 'N'";
        IBS.GROUP(SCCGWA, DCRPSWMT, this, DCTPSWMT_RD);
    }
    public void T000_GROUP_DCTPSWMT2() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.set = "WS-CNT-SCAD1=COUNT(*)";
        DCTPSWMT_RD.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT "
            + "AND PROD_CD = :DCRPSWMT.PROD_CD "
            + "AND APP_BR_C = :DCRPSWMT.APP_BR_C "
            + "AND APP_BAT_NO = :DCRPSWMT.APP_BAT_NO "
            + "AND PRINT_FLG = :DCRPSWMT.PRINT_FLG";
        IBS.GROUP(SCCGWA, DCRPSWMT, this, DCTPSWMT_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
