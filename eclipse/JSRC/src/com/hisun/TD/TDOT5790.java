package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5790 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCIIN_RD;
    brParm TDTCIIN_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_MSGID = " ";
    char WS_CIIN_FLG = ' ';
    short WS_A_CIIN_TIME = 0;
    char WS_A_CIIN_FND_FLG = ' ';
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    short WS_ST = 0;
    short WS_EN = 0;
    short WS_TIME = 0;
    int WS_COUNT = 0;
    char WS_FUNC = ' ';
    String WS_CI_NAME = " ";
    char TDOT5790_FILLER3 = 0X02;
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    char TDOT5790_FILLER6 = 0X02;
    TDOT5790_WS_DATA[] WS_DATA = new TDOT5790_WS_DATA[10];
    String WS_SMK = " ";
    char WS_END_TYP = ' ';
    String K_OUTPUT_FMT1 = "TD579";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMACE TDCMACE = new TDCMACE();
    TDRCIIN TDRCIIN = new TDRCIIN();
    TDCPIOD TDCPIOD = new TDCPIOD();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCMPAG SCCMPAG = new SCCMPAG();
    TDCO5790 TDCO5790 = new TDCO5790();
    SCCGWA SCCGWA;
    TDB5790_AWA_5790 TDB5790_AWA_5790;
    public TDOT5790() {
        for (int i=0;i<10;i++) WS_DATA[i] = new TDOT5790_WS_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT5790 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5790_AWA_5790>");
        TDB5790_AWA_5790 = (TDB5790_AWA_5790) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B100_FUNC_CHEK();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5790_AWA_5790.FUNC);
        CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        CEP.TRC(SCCGWA, TDB5790_AWA_5790.ID_TYP);
        CEP.TRC(SCCGWA, TDB5790_AWA_5790.ID_NO);
        if (TDB5790_AWA_5790.FUNC == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DOCU_FUNC_ERR);
        }
        if (TDB5790_AWA_5790.CI_NAME.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NAME_MUST_INPUT);
        }
        if (TDB5790_AWA_5790.ID_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ID_TYP_ERR);
        }
        if (TDB5790_AWA_5790.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ID_NO_ERR);
        }
        if (TDB5790_AWA_5790.FUNC != 'I' 
            && TDB5790_AWA_5790.FUNC != 'D') {
            if (TDB5790_AWA_5790.YH_TYP == ' ') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_I_ERR);
            }
            if (TDB5790_AWA_5790.YH_TYP == '0') {
                CEP.TRC(SCCGWA, TDB5790_AWA_5790.PROD_CD);
                if (TDB5790_AWA_5790.PROD_CD.trim().length() == 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH);
                }
                IBS.init(SCCGWA, BPCPQPRD);
                CEP.TRC(SCCGWA, TDB5790_AWA_5790.PROD_CD);
                BPCPQPRD.PRDT_CODE = TDB5790_AWA_5790.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                IBS.init(SCCGWA, TDCPIOD);
                TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
                TDCPIOD.C_PROD_CD = TDB5790_AWA_5790.PROD_CD;
                TDCPIOD.GET_FLG = 'N';
                CEP.TRC(SCCGWA, TDB5790_AWA_5790.PROD_CD);
                CEP.TRC(SCCGWA, TDCPIOD.PROD_CD);
                CEP.TRC(SCCGWA, "----");
                CEP.TRC(SCCGWA, TDB5790_AWA_5790.CCY);
                S100_CALL_TDZPROD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[2-1].MIN_CCYC);
                WS_TIME += 1;
                while (WS_TIME <= 12 
                    && (WS_FUN_CCY != 'Y' 
                    || WS_FUN_TERM != 'Y')) {
                    CEP.TRC(SCCGWA, WS_TIME);
                    if (TDB5790_AWA_5790.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].MIN_CCYC)) {
                        CEP.TRC(SCCGWA, WS_TIME);
                        WS_FUN_CCY = 'Y';
                        WS_ST = 1;
                        WS_EN = 4;
                        CEP.TRC(SCCGWA, TDB5790_AWA_5790.TERM);
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("D001") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(0, 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("D007") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(2 - 1, 2 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("M001") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(3 - 1, 3 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("M003") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                            CEP.TRC(SCCGWA, TDB5790_AWA_5790.TERM);
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(4 - 1, 4 + 1 - 1));
                        CEP.TRC(SCCGWA, TDB5790_AWA_5790.TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("M006") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(5 - 1, 5 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("Y001") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(6 - 1, 6 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("Y002") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(7 - 1, 7 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("Y003") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(8 - 1, 8 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("Y005") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(9 - 1, 9 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("Y006") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            WS_FUN_TERM = 'Y';
                        }
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM);
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(10 - 1, 10 + 1 - 1));
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM += " ";
                        if (TDB5790_AWA_5790.TERM.equalsIgnoreCase("S000") 
                            && TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y") 
                            && WS_FUN_TERM != 'Y') {
                            if (TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM1) 
                                || TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM2) 
                                || TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM3) 
                                || TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM4) 
                                || TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM5) 
                                || TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME-1].TERM6)) {
                                WS_FUN_TERM = 'Y';
                            }
                        }
                        CEP.TRC(SCCGWA, WS_FUN_TERM);
                    }
                    WS_TIME += 1;
                }
                CEP.TRC(SCCGWA, WS_TIME);
                CEP.TRC(SCCGWA, WS_FUN_CCY);
                if (WS_FUN_CCY != 'Y' 
                    && TDB5790_AWA_5790.CCY.trim().length() > 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR);
                }
                if (WS_FUN_TERM != 'Y' 
                    && TDB5790_AWA_5790.TERM.trim().length() > 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_I_ERR);
                }
            }
            if (TDB5790_AWA_5790.SDT == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SDT_I_ERR);
            }
            if (TDB5790_AWA_5790.DDT == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DDT_I_ERR);
            }
            IBS.init(SCCGWA, CICCUST);
            WS_FUN_CI = ' ';
            CICCUST.FUNC = 'B';
            CICCUST.DATA.ID_TYPE = TDB5790_AWA_5790.ID_TYP;
            CICCUST.DATA.ID_NO = TDB5790_AWA_5790.ID_NO;
            CICCUST.DATA.CI_NM = TDB5790_AWA_5790.CI_NAME;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (WS_FUN_CI == 'N') {
                TDB5790_AWA_5790.CI_NO = " ";
            }
            if (WS_FUN_CI == 'Y') {
                if (CICCUST.O_DATA.O_CI_TYP != '1') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR);
                }
            }
        }
    }
    public void B100_FUNC_CHEK() throws IOException,SQLException,Exception {
        if (TDB5790_AWA_5790.FUNC == 'A') {
            B030_GET_LIST();
            if (pgmRtn) return;
            T000_READ_TDTCIIN_UPD();
            if (pgmRtn) return;
            T000_ADD_TDTCIIN();
            if (pgmRtn) return;
        } else if (TDB5790_AWA_5790.FUNC == 'D') {
            B030_GET_LIST();
            if (pgmRtn) return;
            T000_DELETE_TDTCIIN();
            if (pgmRtn) return;
        } else if (TDB5790_AWA_5790.FUNC == 'M') {
            B030_GET_LIST();
            if (pgmRtn) return;
            T000_UPDATE_TDTCIIN();
            if (pgmRtn) return;
        } else if (TDB5790_AWA_5790.FUNC == 'I') {
            B030_GET_LIST();
            if (pgmRtn) return;
            R000_PAGE_ROW();
            if (pgmRtn) return;
            B101_OUT_LIST();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCIIN);
        TDRCIIN.KEY.CI_NAME = TDB5790_AWA_5790.CI_NAME;
        TDRCIIN.KEY.ID_TYP = TDB5790_AWA_5790.ID_TYP;
        TDRCIIN.KEY.ID_NO = TDB5790_AWA_5790.ID_NO;
        TDRCIIN.KEY.PROD_CD = TDB5790_AWA_5790.PROD_CD;
        TDRCIIN.KEY.CCY = TDB5790_AWA_5790.CCY;
        TDRCIIN.KEY.TERM = TDB5790_AWA_5790.TERM;
        TDRCIIN.KEY.SDT = TDB5790_AWA_5790.SDT;
        TDRCIIN.DDT = TDB5790_AWA_5790.DDT;
        CEP.TRC(SCCGWA, TDRCIIN.KEY.CI_NAME);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_TYP);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_NO);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.CCY);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.TERM);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.SDT);
        CEP.TRC(SCCGWA, TDRCIIN.DDT);
        if (TDB5790_AWA_5790.FUNC == 'A') {
            T000_A_STARTBR_TDTCIIN();
            if (pgmRtn) return;
            T000_A_READNEXT_TDTCIIN();
            if (pgmRtn) return;
            while (WS_A_CIIN_FND_FLG != 'N') {
                CEP.TRC(SCCGWA, TDRCIIN.KEY.PROD_CD);
                CEP.TRC(SCCGWA, TDRCIIN.KEY.CCY);
                CEP.TRC(SCCGWA, TDRCIIN.KEY.TERM);
                if (TDB5790_AWA_5790.PROD_CD.trim().length() > 0) {
                    if (!TDB5790_AWA_5790.PROD_CD.equalsIgnoreCase(TDRCIIN.KEY.PROD_CD) 
                        && TDRCIIN.KEY.PROD_CD.trim().length() > 0) {
                        WS_A_CIIN_TIME += 1;
                    }
                }
                if (TDB5790_AWA_5790.CCY.trim().length() > 0) {
                    if (!TDB5790_AWA_5790.CCY.equalsIgnoreCase(TDRCIIN.KEY.CCY) 
                        && TDRCIIN.KEY.CCY.trim().length() > 0) {
                        WS_A_CIIN_TIME += 1;
                    }
                }
                if (TDB5790_AWA_5790.TERM.trim().length() > 0) {
                    if (!TDB5790_AWA_5790.TERM.equalsIgnoreCase(TDRCIIN.KEY.TERM) 
                        && TDRCIIN.KEY.TERM.trim().length() > 0) {
                        WS_A_CIIN_TIME += 1;
                    }
                }
                CEP.TRC(SCCGWA, WS_A_CIIN_TIME);
                if (WS_A_CIIN_TIME < 1) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
                }
                T000_A_READNEXT_TDTCIIN();
                if (pgmRtn) return;
            }
            T000_A_ENDBR_TDTCIIN();
            if (pgmRtn) return;
        } else {
            if (TDB5790_AWA_5790.FUNC == 'M') {
                T000_M_GROUP_TDTCIIN();
                if (pgmRtn) return;
            }
            if (TDB5790_AWA_5790.FUNC == 'D' 
                || TDB5790_AWA_5790.FUNC == 'M') {
                T000_READUP_TDTCIIN();
                if (pgmRtn) return;
            }
        }
        if (TDB5790_AWA_5790.FUNC != 'I' 
            && TDB5790_AWA_5790.FUNC != 'D') {
            TDRCIIN.CI_NO = TDB5790_AWA_5790.CI_NO;
            TDRCIIN.INT_SEL = TDB5790_AWA_5790.YH_TYP;
            TDRCIIN.KEY.PROD_CD = TDB5790_AWA_5790.PROD_CD;
            TDRCIIN.KEY.CCY = TDB5790_AWA_5790.CCY;
            TDRCIIN.KEY.TERM = TDB5790_AWA_5790.TERM;
            TDRCIIN.MIN_BAL = TDB5790_AWA_5790.BAL;
            TDRCIIN.RAT_TYP = TDB5790_AWA_5790.FLG;
            TDRCIIN.DIS_SPRD = TDB5790_AWA_5790.SPRD_PNT;
            TDRCIIN.SPRD_PCT = TDB5790_AWA_5790.SPRD_PCT;
            TDRCIIN.CON_RATE = TDB5790_AWA_5790.XS_RAT;
            TDRCIIN.HOLD_LVL = TDB5790_AWA_5790.ZC_TYP;
            TDRCIIN.KEY.SDT = TDB5790_AWA_5790.SDT;
            TDRCIIN.DDT = TDB5790_AWA_5790.DDT;
        }
    }
    public void R000_PAGE_ROW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B101_OUT_LIST() throws IOException,SQLException,Exception {
        WS_TIME = 0;
        IBS.init(SCCGWA, TDRCIIN);
        TDRCIIN.KEY.CI_NAME = TDB5790_AWA_5790.CI_NAME;
        TDRCIIN.KEY.ID_TYP = TDB5790_AWA_5790.ID_TYP;
        TDRCIIN.KEY.ID_NO = TDB5790_AWA_5790.ID_NO;
        if (TDB5790_AWA_5790.SMK.trim().length() > 0) {
            if (TDB5790_AWA_5790.SMK == null) TDB5790_AWA_5790.SMK = "";
            JIBS_tmp_int = TDB5790_AWA_5790.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) TDB5790_AWA_5790.SMK += " ";
            TDRCIIN.KEY.PROD_CD = TDB5790_AWA_5790.SMK.substring(0, 10);
            if (TDB5790_AWA_5790.SMK == null) TDB5790_AWA_5790.SMK = "";
            JIBS_tmp_int = TDB5790_AWA_5790.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) TDB5790_AWA_5790.SMK += " ";
            TDRCIIN.KEY.CCY = TDB5790_AWA_5790.SMK.substring(11 - 1, 11 + 3 - 1);
            if (TDB5790_AWA_5790.SMK == null) TDB5790_AWA_5790.SMK = "";
            JIBS_tmp_int = TDB5790_AWA_5790.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) TDB5790_AWA_5790.SMK += " ";
            TDRCIIN.KEY.TERM = TDB5790_AWA_5790.SMK.substring(14 - 1, 14 + 4 - 1);
            if (TDB5790_AWA_5790.SMK == null) TDB5790_AWA_5790.SMK = "";
            JIBS_tmp_int = TDB5790_AWA_5790.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) TDB5790_AWA_5790.SMK += " ";
            if (TDB5790_AWA_5790.SMK.substring(18 - 1, 18 + 8 - 1).trim().length() == 0) TDRCIIN.KEY.SDT = 0;
            else TDRCIIN.KEY.SDT = Integer.parseInt(TDB5790_AWA_5790.SMK.substring(18 - 1, 18 + 8 - 1));
            if (TDB5790_AWA_5790.SMK == null) TDB5790_AWA_5790.SMK = "";
            JIBS_tmp_int = TDB5790_AWA_5790.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) TDB5790_AWA_5790.SMK += " ";
            if (TDB5790_AWA_5790.SMK.substring(26 - 1, 26 + 8 - 1).trim().length() == 0) TDRCIIN.DDT = 0;
            else TDRCIIN.DDT = Integer.parseInt(TDB5790_AWA_5790.SMK.substring(26 - 1, 26 + 8 - 1));
        } else {
            TDRCIIN.KEY.PROD_CD = "" + 0X00;
            JIBS_tmp_int = TDRCIIN.KEY.PROD_CD.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRCIIN.KEY.PROD_CD = "0" + TDRCIIN.KEY.PROD_CD;
            TDRCIIN.KEY.CCY = "" + 0X00;
            JIBS_tmp_int = TDRCIIN.KEY.CCY.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRCIIN.KEY.CCY = "0" + TDRCIIN.KEY.CCY;
            TDRCIIN.KEY.TERM = "" + 0X00;
            JIBS_tmp_int = TDRCIIN.KEY.TERM.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) TDRCIIN.KEY.TERM = "0" + TDRCIIN.KEY.TERM;
            TDRCIIN.KEY.SDT = 0;
            TDRCIIN.DDT = 0;
        }
        CEP.TRC(SCCGWA, TDRCIIN.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.CCY);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.TERM);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.SDT);
        CEP.TRC(SCCGWA, TDRCIIN.DDT);
        T000_STARTE_TDTCIIN();
        if (pgmRtn) return;
        T000_RENEXT_TDTCIIN();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_TIME += 1;
            IBS.init(SCCGWA, TDCO5790);
            TDCO5790.FUNC = TDB5790_AWA_5790.FUNC;
            TDCO5790.CI_NAME = TDRCIIN.KEY.CI_NAME;
            TDCO5790.ID_TYP = TDRCIIN.KEY.ID_TYP;
            TDCO5790.ID_NO = TDRCIIN.KEY.ID_NO;
            TDCO5790.CI_NO = TDRCIIN.CI_NO;
            TDCO5790.YH_TYP = TDRCIIN.INT_SEL;
            TDCO5790.PROD_CD = TDRCIIN.KEY.PROD_CD;
            TDCO5790.CCY = TDRCIIN.KEY.CCY;
            TDCO5790.TERM = TDRCIIN.KEY.TERM;
            TDCO5790.BAL = TDRCIIN.MIN_BAL;
            TDCO5790.FLG = TDRCIIN.RAT_TYP;
            TDCO5790.SPRD_PCT = TDRCIIN.SPRD_PCT;
            TDCO5790.SPRD_PNT = TDRCIIN.DIS_SPRD;
            TDCO5790.XS_RAT = TDRCIIN.CON_RATE;
            TDCO5790.ZC_TYP = TDRCIIN.HOLD_LVL;
            TDCO5790.SDT = TDRCIIN.KEY.SDT;
            TDCO5790.DDT = TDRCIIN.DDT;
            B220_OUT_LIST();
            if (pgmRtn) return;
            T000_RENEXT_TDTCIIN();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTCIIN();
        if (pgmRtn) return;
        if (WS_TIME > 0) {
            if (WS_SMK == null) WS_SMK = "";
            JIBS_tmp_int = WS_SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_SMK += " ";
            if (TDCO5790.PROD_CD == null) TDCO5790.PROD_CD = "";
            JIBS_tmp_int = TDCO5790.PROD_CD.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) TDCO5790.PROD_CD += " ";
            WS_SMK = TDCO5790.PROD_CD + WS_SMK.substring(10);
            if (WS_SMK == null) WS_SMK = "";
            JIBS_tmp_int = WS_SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_SMK += " ";
            if (TDCO5790.CCY == null) TDCO5790.CCY = "";
            JIBS_tmp_int = TDCO5790.CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) TDCO5790.CCY += " ";
            WS_SMK = WS_SMK.substring(0, 11 - 1) + TDCO5790.CCY + WS_SMK.substring(11 + 3 - 1);
            if (WS_SMK == null) WS_SMK = "";
            JIBS_tmp_int = WS_SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_SMK += " ";
            if (TDCO5790.TERM == null) TDCO5790.TERM = "";
            JIBS_tmp_int = TDCO5790.TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCO5790.TERM += " ";
            WS_SMK = WS_SMK.substring(0, 14 - 1) + TDCO5790.TERM + WS_SMK.substring(14 + 4 - 1);
            if (WS_SMK == null) WS_SMK = "";
            JIBS_tmp_int = WS_SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_SMK += " ";
            JIBS_tmp_str[0] = "" + TDCO5790.SDT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_SMK = WS_SMK.substring(0, 18 - 1) + JIBS_tmp_str[0] + WS_SMK.substring(18 + 8 - 1);
            if (WS_SMK == null) WS_SMK = "";
            JIBS_tmp_int = WS_SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_SMK += " ";
            JIBS_tmp_str[0] = "" + TDCO5790.DDT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_SMK = WS_SMK.substring(0, 26 - 1) + JIBS_tmp_str[0] + WS_SMK.substring(26 + 8 - 1);
        }
    }
    public void B220_OUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCO5790);
        SCCMPAG.DATA_LEN = 427;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_A_READ_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.where = "CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND ( PROD_CD = :TDRCIIN.KEY.PROD_CD "
            + "OR PROD_CD = ' ' ) "
            + "AND ( CCY = :TDRCIIN.KEY.CCY "
            + "OR CCY = ' ' ) "
            + "AND ( TERM = :TDRCIIN.KEY.TERM "
            + "OR TERM = ' ' ) "
            + "AND ( ( SDT <= :TDRCIIN.KEY.SDT "
            + "AND DDT > :TDRCIIN.KEY.SDT ) "
            + "OR ( SDT < :TDRCIIN.DDT "
            + "AND DDT >= :TDRCIIN.DDT ) "
            + "OR ( SDT >= :TDRCIIN.KEY.SDT "
            + "AND DDT <= :TDRCIIN.DDT ) )";
        TDTCIIN_RD.fst = true;
        IBS.READ(SCCGWA, TDRCIIN, this, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else {
        }
    }
    public void T000_A_STARTBR_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_BR.rp = new DBParm();
        TDTCIIN_BR.rp.TableName = "TDTCIIN";
        TDTCIIN_BR.rp.where = "CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND ( ( SDT <= :TDRCIIN.KEY.SDT "
            + "AND DDT > :TDRCIIN.KEY.SDT ) "
            + "OR ( SDT < :TDRCIIN.DDT "
            + "AND DDT >= :TDRCIIN.DDT ) "
            + "OR ( SDT >= :TDRCIIN.KEY.SDT "
            + "AND DDT <= :TDRCIIN.DDT ) )";
        IBS.STARTBR(SCCGWA, TDRCIIN, this, TDTCIIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_A_CIIN_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
            WS_A_CIIN_FND_FLG = 'N';
        } else {
        }
    }
    public void T000_A_READNEXT_TDTCIIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCIIN, this, TDTCIIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRCIIN.KEY.CI_NAME);
            WS_A_CIIN_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
            WS_A_CIIN_FND_FLG = 'N';
        } else {
        }
    }
    public void T000_A_ENDBR_TDTCIIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCIIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
            WS_A_CIIN_FND_FLG = 'N';
        } else {
        }
    }
    public void T000_M_GROUP_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.set = "WS-COUNT=COUNT(*)";
        TDTCIIN_RD.where = "CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND PROD_CD = :TDRCIIN.KEY.PROD_CD "
            + "AND CCY = :TDRCIIN.KEY.CCY "
            + "AND TERM = :TDRCIIN.KEY.TERM "
            + "AND ( ( SDT <= :TDRCIIN.KEY.SDT "
            + "AND DDT > :TDRCIIN.KEY.SDT ) "
            + "OR ( SDT < :TDRCIIN.DDT "
            + "AND DDT >= :TDRCIIN.DDT ) "
            + "OR ( SDT >= :TDRCIIN.KEY.SDT "
            + "AND DDT <= :TDRCIIN.DDT ) )";
        IBS.GROUP(SCCGWA, TDRCIIN, this, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (WS_COUNT > 1) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else {
        }
    }
    public void T000_ADD_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        IBS.WRITE(SCCGWA, TDRCIIN, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else {
        }
    }
    public void T000_READ_TDTCIIN_UPD() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.upd = true;
        IBS.READ(SCCGWA, TDRCIIN, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else {
        }
    }
    public void T000_DELETE_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        IBS.DELETE(SCCGWA, TDRCIIN, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        } else {
        }
    }
    public void T000_UPDATE_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        IBS.REWRITE(SCCGWA, TDRCIIN, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        } else {
        }
    }
    public void T000_READUP_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.where = "CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND PROD_CD = :TDRCIIN.KEY.PROD_CD "
            + "AND CCY = :TDRCIIN.KEY.CCY "
            + "AND TERM = :TDRCIIN.KEY.TERM "
            + "AND SDT = :TDRCIIN.KEY.SDT";
        TDTCIIN_RD.upd = true;
        IBS.READ(SCCGWA, TDRCIIN, this, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        } else {
        }
    }
    public void T000_READ_TDTCIIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_TYP);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.ID_NO);
        CEP.TRC(SCCGWA, TDRCIIN.KEY.CI_NAME);
        TDTCIIN_RD = new DBParm();
        TDTCIIN_RD.TableName = "TDTCIIN";
        TDTCIIN_RD.where = "ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND PROD_CD = :TDRCIIN.KEY.PROD_CD "
            + "AND CCY = :TDRCIIN.KEY.CCY "
            + "AND TERM = :TDRCIIN.KEY.TERM "
            + "AND SDT = :TDRCIIN.KEY.SDT "
            + "AND DDT = :TDRCIIN.DDT";
        IBS.READ(SCCGWA, TDRCIIN, this, TDTCIIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDB5790_AWA_5790.CI_NAME);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        } else {
        }
    }
    public void T000_STARTE_TDTCIIN() throws IOException,SQLException,Exception {
        TDTCIIN_BR.rp = new DBParm();
        TDTCIIN_BR.rp.TableName = "TDTCIIN";
        TDTCIIN_BR.rp.where = "CI_NAME = :TDRCIIN.KEY.CI_NAME "
            + "AND ID_TYP = :TDRCIIN.KEY.ID_TYP "
            + "AND ID_NO = :TDRCIIN.KEY.ID_NO "
            + "AND PROD_CD > :TDRCIIN.KEY.PROD_CD "
            + "AND CCY > :TDRCIIN.KEY.CCY "
            + "AND TERM > :TDRCIIN.KEY.TERM "
            + "AND SDT > :TDRCIIN.KEY.SDT "
            + "AND DDT > :TDRCIIN.DDT";
        TDTCIIN_BR.rp.order = "PROD_CD";
        IBS.STARTBR(SCCGWA, TDRCIIN, this, TDTCIIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CIIN_FLG = 'Y';
        } else {
            WS_CIIN_FLG = 'N';
        }
    }
    public void T000_RENEXT_TDTCIIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCIIN, this, TDTCIIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CIIN_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CIIN_FLG = 'N';
            WS_END_TYP = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
    }
    public void T000_ENDBR_TDTCIIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCIIN_BR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_FUN_CI = 'N';
        } else {
            WS_FUN_CI = 'Y';
        }
    }
    public void S100_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
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
