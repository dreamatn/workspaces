package com.hisun.TD;

import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPROD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm TDTOTHE_RD;
    brParm TDTOTHE_BR = new brParm();
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    short K_CCY_CNT = 12;
    String WS_MSGID = " ";
    TDZPROD_CP_PROD_CD CP_PROD_CD = new TDZPROD_CP_PROD_CD();
    short WS_I = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    TDZPROD_WS_CCY_INF[] WS_CCY_INF = new TDZPROD_WS_CCY_INF[32];
    char WS_READ_FLG = ' ';
    String WS_TERM = " ";
    BPRPARM BPRPARM = new BPRPARM();
    TDROTHE TDROTHE = new TDROTHE();
    TDCLML TDCLML = new TDCLML();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCQPRD TDCQPRD = new TDCQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCPRDP TDCPRDPO = new TDCPRDP();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCIRULP TDCPIOD;
    public TDZPROD() {
        for (int i=0;i<32;i++) WS_CCY_INF[i] = new TDZPROD_WS_CCY_INF();
    }
    public void MP(SCCGWA SCCGWA, TDCIRULP TDCPIOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPIOD = TDCPIOD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPROD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDROTHE);
        IBS.init(SCCGWA, TDCLML);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INPUT_CHECK();
        B110_GET_PARMDAT();
        if (TDCPIOD.BAL_ACTI_FLG != 'N') {
            B300_GET_ACTIDAT();
        }
    }
    public void B010_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPIOD.PROD_CD);
        CEP.TRC(SCCGWA, TDCPIOD);
        if (TDCPIOD.INTERM.trim().length() == 0) {
            TDCPIOD.GET_FLG = 'N';
        }
        if (TDCPIOD.GET_FLG != 'N' 
            && (TDCPIOD.ACTI_NO.trim().length() == 0 
            && (TDCPIOD.BR == 0 
            || TDCPIOD.CCY.trim().length() == 0 
            || TDCPIOD.INTERM.trim().length() == 0))) {
            CEP.TRC(SCCGWA, "CHECK ERROR");
            CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
            CEP.TRC(SCCGWA, TDCPIOD.BR);
            CEP.TRC(SCCGWA, TDCPIOD.CCY);
            CEP.TRC(SCCGWA, TDCPIOD.INTERM);
            if (TDCPIOD.CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR, SCCBINF);
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_I_ERR, SCCBINF);
            }
        }
        if (TDCPIOD.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR, SCCBINF);
        }
    }
    public void B110_GET_PARMDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPROD);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = TDCPIOD.PROD_CD;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPIOD.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPIOD.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPIOD.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPIOD.OTH_PRM);
        if (TDCPRDP.EXP_PRM.ONTM_FLG == '1' 
            && TDCPIOD.ACTI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ACTI-NO ERROR");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR, SCCBINF);
        }
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1]);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[2-1].MIN_CCYC);
    }
    public void B200_GET_ACIT() throws IOException,SQLException,Exception {
    }
    public void B300_GET_ACTIDAT() throws IOException,SQLException,Exception {
        if (TDCPIOD.ACTI_NO.trim().length() > 0) {
            TDROTHE.KEY.ACTI_NO = TDCPIOD.ACTI_NO;
            TDROTHE.SUC_FLG = '1';
            T00_READ_TDTOTHE();
            if (WS_READ_FLG == 'Y') {
                IBS.init(SCCGWA, TDCLML);
                if (TDROTHE.ACTI_FLG == '0' 
                    && TDCPIOD.ACCRU_FLG == 'Y') {
                    IBS.init(SCCGWA, TDCLML);
                    TDCLML.FUNC = 'J';
                    TDCLML.ACTI_NO = TDROTHE.KEY.ACTI_NO;
                    TDCLML.PROD_CD = TDROTHE.PROD_CD;
                    TDCLML.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                        TDCLML.CHNL_NO = SCCGWA.COMM_AREA.BSP_FLG;
                    } else {
                        TDCLML.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
                    }
                    CEP.TRC(SCCGWA, TDCLML.CHNL_NO);
                    TDCLML.BAL = TDCPIOD.BAL;
                    S000_CALL_TDZLML();
                }
            }
        } else {
            if (TDCPIOD.GET_FLG != 'N') {
                TDROTHE.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDROTHE.PROD_CD = TDCPIOD.C_PROD_CD;
                TDROTHE.BR = TDCPIOD.BR;
                if (TDCPIOD.BR == 0) {
                    TDROTHE.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                }
                TDROTHE.TERM = TDCPIOD.INTERM;
                TDROTHE.CCY = TDCPIOD.CCY;
                TDROTHE.SUC_FLG = '1';
                T000_STARTBR_TDTOTHE();
                T000_READNEXT_TDTOTHE();
                while (WS_READ_FLG != 'N' 
                    && (TDCLML.RC.RC_CODE != 0 
                    || !TDCLML.SMK.equalsIgnoreCase("1"))) {
                    IBS.init(SCCGWA, TDCLML);
                    if (TDROTHE.ACTI_FLG == '0') {
                        TDCLML.FUNC = 'J';
                        TDCLML.ACTI_NO = TDROTHE.KEY.ACTI_NO;
                        TDCLML.PROD_CD = TDROTHE.PROD_CD;
                        TDCLML.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                            TDCLML.CHNL_NO = SCCGWA.COMM_AREA.BSP_FLG;
                        } else {
                            TDCLML.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
                        }
                        TDCLML.BAL = TDCPIOD.BAL;
                        S000_CALL_TDZLML();
                    }
                    if (!TDCLML.SMK.equalsIgnoreCase("1")) {
                        T000_READNEXT_TDTOTHE();
                    }
                }
                T000_ENDBR_TDTOTHE();
            }
        }
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDCLML.SMK);
        if ((WS_READ_FLG == 'Y' 
            && TDCLML.RC.RC_CODE == 0) 
            || TDCLML.SMK.equalsIgnoreCase("1")) {
            TDCPIOD.ACTI_NO = TDROTHE.KEY.ACTI_NO;
            CEP.TRC(SCCGWA, TDROTHE.CCY);
            if (TDROTHE.CCY.trim().length() > 0 
                && TDROTHE.CCY.charAt(0) != 0X00) {
                WS_CNT = 1;
                while (WS_CNT <= 24 
                    && !TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDROTHE.CCY)) {
                    WS_CNT += 1;
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC);
                }
                CEP.TRC(SCCGWA, "a1");
                if (WS_CNT > 24) {
                    CEP.TRC(SCCGWA, "CCY CAN NOT FOUND");
                    CEP.TRC(SCCGWA, TDROTHE.CCY);
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR, SCCBINF);
                }
                if (TDROTHE.TERM.trim().length() > 0 
                    && TDROTHE.TERM.charAt(0) != 0X00) {
                    CEP.TRC(SCCGWA, "a2");
                    WS_TERM = TDROTHE.TERM;
                    if ((!WS_TERM.equalsIgnoreCase("D001") 
                        && !WS_TERM.equalsIgnoreCase("D007") 
                        && !WS_TERM.equalsIgnoreCase("M001") 
                        && !WS_TERM.equalsIgnoreCase("M003") 
                        && !WS_TERM.equalsIgnoreCase("M006") 
                        && !WS_TERM.equalsIgnoreCase("Y001") 
                        && !WS_TERM.equalsIgnoreCase("Y002") 
                        && !WS_TERM.equalsIgnoreCase("Y003") 
                        && !WS_TERM.equalsIgnoreCase("Y005") 
                        && !WS_TERM.equalsIgnoreCase("Y006"))) {
                        CEP.TRC(SCCGWA, "a3");
                        TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                        if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                        TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 11 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(11 + 1 - 1);
                        TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM1 = TDROTHE.TERM;
                        if (TDROTHE.TERM == null) TDROTHE.TERM = "";
                        JIBS_tmp_int = TDROTHE.TERM.length();
                        for (int i=0;i<4-JIBS_tmp_int;i++) TDROTHE.TERM += " ";
                        TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM_T1 = TDROTHE.TERM.substring(0, 1).charAt(0);
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM1);
                        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM_T1);
                    } else {
                        CEP.TRC(SCCGWA, "a4");
                        CEP.TRC(SCCGWA, TDROTHE.TERM);
                        TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = " ";
                        if (TDROTHE.TERM.equalsIgnoreCase("D001")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("D007")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 2 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(2 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("M001")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 3 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(3 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("M003")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 4 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(4 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("M006")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 5 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(5 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("Y001")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 6 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(6 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("Y002")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 7 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(7 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("Y003")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 8 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(8 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("Y005")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 9 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(9 + 1 - 1);
                        }
                        if (TDROTHE.TERM.equalsIgnoreCase("Y006")) {
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "Y";
                            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = "";
                            JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM += " ";
                            TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(0, 10 - 1) + "Y" + TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].TERM.substring(10 + 1 - 1);
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, "222");
            if (WS_CNT == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CCY_ERR);
            }
            if (TDROTHE.MIN_BAL != 0 
                && TDROTHE.MIN_BAL != 0X00) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC = TDROTHE.MIN_BAL;
            }
            if (TDROTHE.MAX_BAL != 0 
                && TDROTHE.MAX_BAL != 0X00) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT = TDROTHE.MAX_BAL;
            }
            if (TDROTHE.ADD_BAL != 0) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC = TDROTHE.ADD_BAL;
            }
            if (TDROTHE.HER_BAL != 0) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT = TDROTHE.HER_BAL;
            }
            if (TDROTHE.RAT_TYP != ' ' 
                && TDROTHE.RAT_TYP != 0X00) {
                TDCPIOD.OTH_PRM.CPRA_TYP = TDROTHE.RAT_TYP;
            }
            if (TDROTHE.PCT_S != ' ' 
                && TDROTHE.PCT_S != 0X00) {
                TDCPIOD.ACTI.PCT_S = TDROTHE.PCT_S;
            }
            if (TDROTHE.FLT_RAT != ' ' 
                && TDROTHE.FLT_RAT != 0X00) {
                TDCPIOD.ACTI.FD_RAT = TDROTHE.FLT_RAT;
            }
            if (TDROTHE.RUL_CD.trim().length() > 0 
                && TDROTHE.RUL_CD.charAt(0) != 0X00) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD.RUL_CD1 = TDROTHE.RUL_CD;
            }
            if (TDROTHE.CONT_RAT != ' ' 
                && TDROTHE.CONT_RAT != 0X00) {
                TDCPIOD.ACTI.HY_RAT = TDROTHE.CONT_RAT;
            }
            if (TDROTHE.INT_PERD != ' ' 
                && TDROTHE.INT_PERD != 0X00) {
                if (TDROTHE.INT_PERD == '1') {
                    TDCPIOD.OTH_PRM.INT_PRD2 = TDROTHE.INT_PERD;
                }
                TDCPIOD.OTH_PRM.INT_PRD1 = TDROTHE.INT_PERD;
            }
            if (TDROTHE.CD_PERD.trim().length() > 0 
                && TDROTHE.CD_PERD.charAt(0) != 0X00) {
                TDCPIOD.OTH_PRM.INT_PERD = TDROTHE.CD_PERD;
            }
            if (TDROTHE.TRAN_TYP != ' ' 
                && TDROTHE.TRAN_TYP != 0X00) {
                TDCPIOD.ACTI.ZR_TYP = TDROTHE.TRAN_TYP;
            }
            if (TDROTHE.REDE_TYP != ' ' 
                && TDROTHE.REDE_TYP != 0X00) {
                TDCPIOD.ACTI.SH_TYP = TDROTHE.REDE_TYP;
            }
            if (TDROTHE.PLED_TYP != ' ' 
                && TDROTHE.PLED_TYP != 0X00) {
                TDCPIOD.ACTI.ZY_TYP = TDROTHE.PLED_TYP;
            }
            if (TDROTHE.EARLY_TYP != ' ' 
                && TDROTHE.EARLY_TYP != 0X00) {
                TDCPIOD.EXP_PRM.ERLY_TYP = TDROTHE.EARLY_TYP;
            }
            if (TDROTHE.PRV_RAT != ' ' 
                && TDROTHE.PRV_RAT != 0X00) {
                TDCPIOD.ACTI.TQ_RAT = TDROTHE.PRV_RAT;
            }
            if (TDROTHE.LATE_TYP != ' ' 
                && TDROTHE.LATE_TYP != 0X00) {
                TDCPIOD.EXP_PRM.LATE_TYP = TDROTHE.LATE_TYP;
            }
            if (TDROTHE.OVE_RAT != ' ' 
                && TDROTHE.OVE_RAT != 0X00) {
                TDCPIOD.ACTI.YQ_RAT = TDROTHE.OVE_RAT;
            }
            if (TDROTHE.RES_TYP != ' ' 
                && TDROTHE.RES_TYP != 0X00) {
                TDCPIOD.EXP_PRM.RES_TYP = TDROTHE.RES_TYP;
            }
            if (TDROTHE.DUE_RAT != ' ' 
                && TDROTHE.DUE_RAT != 0X00) {
                TDCPIOD.ACTI.SY_RAT = TDROTHE.DUE_RAT;
            }
            if (TDROTHE.CHNL_NO.trim().length() > 0 
                && TDROTHE.CHNL_NO.charAt(0) != 0X00) {
                TDCPIOD.ACTI.CHNL_NO = TDROTHE.CHNL_NO;
            }
            if (TDROTHE.LST_TYP != ' ' 
                && TDROTHE.LST_TYP != 0X00) {
                TDCPIOD.ACTI.MD_TYP = TDROTHE.LST_TYP;
            }
            if (TDROTHE.LSTDT_TYP != ' ' 
                && TDROTHE.LSTDT_TYP != 0X00) {
                TDCPIOD.ACTI.MDQX_TYP = TDROTHE.LSTDT_TYP;
            }
            CEP.TRC(SCCGWA, TDROTHE.PART_NUM);
            if (TDROTHE.PART_NUM != 0) {
                TDCPIOD.TXN_PRM.PART_NUM = TDROTHE.PART_NUM;
            }
            if (TDROTHE.DOCU_NO.trim().length() > 0 
                && TDROTHE.DOCU_NO.charAt(0) != 0X00) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].DOCU_NO = TDROTHE.DOCU_NO;
            }
            if (TDROTHE.ACTI_FLG == '1') {
                TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
            }
        }
    }
    public void S000_CALL_TDZLML() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-Z-LML", TDCLML);
        CEP.TRC(SCCGWA, TDCLML.ACTI_FLG);
        CEP.TRC(SCCGWA, TDCLML.RC.RC_CODE);
        if (TDCLML.RC.RC_CODE != 0 
            && TDCLML.ACTI_FLG != '2') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCLML.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        } else {
            if (TDCLML.RC.RC_CODE == 0) {
                TDCLML.SMK = "1";
            }
        }
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T00_READ_TDTOTHE() throws IOException,SQLException,Exception {
        WS_READ_FLG = ' ';
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.SUC_FLG);
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "ACTI_NO = :TDROTHE.KEY.ACTI_NO "
            + "AND SUC_FLG < > :TDROTHE.SUC_FLG";
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WS-FOUND");
            WS_READ_FLG = 'Y';
        }
    }
    public void T00_READ_TDTOTHE_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
        CEP.TRC(SCCGWA, TDROTHE.BR);
        CEP.TRC(SCCGWA, TDROTHE.TERM);
        CEP.TRC(SCCGWA, TDROTHE.CCY);
        WS_READ_FLG = ' ';
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND BR = :TDROTHE.BR "
            + "AND TERM = :TDROTHE.TERM "
            + "AND CCY = :TDROTHE.CCY "
            + "AND SUC_FLG < > :TDROTHE.SUC_FLG "
            + "AND STR_DATE <= :TDROTHE.STR_DATE "
            + "AND END_DATE > :TDROTHE.STR_DATE";
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_FLG = 'Y';
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            TDCPIOD.ACTI_NO = TDROTHE.KEY.ACTI_NO;
        } else {
            CEP.TRC(SCCGWA, "READ TDTOTHE NOTFOUND OR SOMETHING ERR,CAT Q3/Q4");
            WS_READ_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTOTHE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
        CEP.TRC(SCCGWA, TDROTHE.BR);
        CEP.TRC(SCCGWA, TDROTHE.TERM);
        CEP.TRC(SCCGWA, TDROTHE.CCY);
        WS_READ_FLG = ' ';
        TDTOTHE_BR.rp = new DBParm();
        TDTOTHE_BR.rp.TableName = "TDTOTHE";
        TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND ( TERM = :TDROTHE.TERM "
            + "OR TERM = 'A999' ) "
            + "AND CCY = :TDROTHE.CCY "
            + "AND SUC_FLG < > :TDROTHE.SUC_FLG "
            + "AND STR_DATE <= :TDROTHE.STR_DATE "
            + "AND END_DATE > :TDROTHE.STR_DATE";
        TDTOTHE_BR.rp.order = "ACTI_NO";
        IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_FLG = 'Y';
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
            TDCPIOD.ACTI_NO = TDROTHE.KEY.ACTI_NO;
        } else {
            CEP.TRC(SCCGWA, "READ TDTOTHE NOTFOUND OR SOMETHING ERR,CAT Q3/Q4");
            WS_READ_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTOTHE() throws IOException,SQLException,Exception {
        WS_READ_FLG = ' ';
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_FLG = 'Y';
        } else {
            WS_READ_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTOTHE() throws IOException,SQLException,Exception {
        WS_READ_FLG = ' ';
        IBS.ENDBR(SCCGWA, TDTOTHE_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.MID_FLG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
