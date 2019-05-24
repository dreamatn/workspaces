package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSHITI {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_IHIT_BRW = "BP-R-IHIT-BRW     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_JJ = 0;
    int WS_DATE = 0;
    int WS_STARTD = 0;
    int WS_ENDD = 0;
    short WS_INPUT_CNT = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    int WS_BASETYP_CNT = 0;
    BPZSHITI_WS_BASETYP_ALL[] WS_BASETYP_ALL = new BPZSHITI_WS_BASETYP_ALL[20];
    int WS_CCY_CNT = 0;
    BPZSHITI_WS_CCY_TWENTY[] WS_CCY_TWENTY = new BPZSHITI_WS_CCY_TWENTY[20];
    BPZSHITI_WS_TAB_CNT[] WS_TAB_CNT = new BPZSHITI_WS_TAB_CNT[20];
    BPZSHITI_WS_RATE_CNT[] WS_RATE_CNT = new BPZSHITI_WS_RATE_CNT[400];
    String WS_RATE_ARR = " ";
    BPZSHITI_WS_QUEUE_PROC WS_QUEUE_PROC = new BPZSHITI_WS_QUEUE_PROC();
    char WS_END_FLG = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_COMPLETE_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_CCY_INPUT_FLG = ' ';
    char WS_BASETYP_INPUT_FLG = ' ';
    char WS_TENOR_INPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRHITB BPCRHITB = new BPCRHITB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCQ12 BPCQ12 = new BPCQ12();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    BPCSHITI BPCSHITI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSHITI() {
        for (int i=0;i<20;i++) WS_BASETYP_ALL[i] = new BPZSHITI_WS_BASETYP_ALL();
        for (int i=0;i<20;i++) WS_CCY_TWENTY[i] = new BPZSHITI_WS_CCY_TWENTY();
        for (int i=0;i<20;i++) WS_TAB_CNT[i] = new BPZSHITI_WS_TAB_CNT();
        for (int i=0;i<400;i++) WS_RATE_CNT[i] = new BPZSHITI_WS_RATE_CNT();
    }
    public void MP(SCCGWA SCCGWA, BPCSHITI BPCSHITI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSHITI = BPCSHITI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSHITI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_QUERY_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSHITI.BR == ' ' 
            || BPCSHITI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCSHITI.BR != 999999) {
                WS_BR = BPCSHITI.BR;
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        WS_END_FLG = 'N';
        for (WS_I = 1; WS_I <= 10 
            && WS_END_FLG != 'Y'; WS_I += 1) {
            if (BPCSHITI.INTR_TBL[WS_I-1].CCY.trim().length() == 0) {
                WS_CCY_INPUT_FLG = 'D';
            } else {
                WS_CCY_INPUT_FLG = 'T';
                WS_CCY = BPCSHITI.INTR_TBL[WS_I-1].CCY;
                R000_CHECK_CCY();
                if (pgmRtn) return;
            }
            if (BPCSHITI.INTR_TBL[WS_I-1].BASE_TYP.trim().length() == 0) {
                WS_BASETYP_INPUT_FLG = 'D';
            } else {
                WS_BASETYP_INPUT_FLG = 'T';
                WS_BASE_TYP = BPCSHITI.INTR_TBL[WS_I-1].BASE_TYP;
                R000_CHECK_BASE_TYP();
                if (pgmRtn) return;
            }
            if (BPCSHITI.INTR_TBL[WS_I-1].TENOR.trim().length() == 0 
                || BPCSHITI.INTR_TBL[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                WS_TENOR_INPUT_FLG = 'D';
            } else {
                WS_TENOR_INPUT_FLG = 'T';
                WS_TENOR = BPCSHITI.INTR_TBL[WS_I-1].TENOR;
                R000_CHECK_TENOR();
                if (pgmRtn) return;
            }
            if (WS_CCY_INPUT_FLG == 'D' 
                && WS_BASETYP_INPUT_FLG == 'D' 
                && WS_TENOR_INPUT_FLG == 'D') {
                WS_END_FLG = 'Y';
            }
        }
        WS_INPUT_CNT = (short) (WS_I - 2);
        if (BPCSHITI.STARTD != 0) {
            WS_DATE = BPCSHITI.STARTD;
            WS_STARTD = BPCSHITI.STARTD;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            WS_STARTD = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSHITI.ENDD != 0) {
            WS_DATE = BPCSHITI.ENDD;
            WS_ENDD = BPCSHITI.ENDD;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            WS_ENDD = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (WS_STARTD > WS_ENDD) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B210_MPAG_HEAD_PROC();
        if (pgmRtn) return;
        if (WS_INPUT_CNT == 0) {
            B220_QUERY_DETAIL_PROC();
            if (pgmRtn) return;
        } else {
            for (WS_I = 1; WS_I <= WS_INPUT_CNT; WS_I += 1) {
                B220_QUERY_DETAIL_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_MPAG_HEAD_PROC() throws IOException,SQLException,Exception {
        WS_OUTPUT_FLG = 'T';
        WS_QUEUE_PROC.WS_RES_MESS = " ";
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B220_QUERY_DETAIL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITB);
        BPCRHITB.BR = BPCSHITI.BR;
        BPCRHITB.STARTD = BPCSHITI.STARTD;
        BPCRHITB.ENDD = BPCSHITI.ENDD;
        if (WS_INPUT_CNT != 0) {
            BPCRHITB.CCY = BPCSHITI.INTR_TBL[WS_I-1].CCY;
            BPCRHITB.BASE_TYP = BPCSHITI.INTR_TBL[WS_I-1].BASE_TYP;
            BPCRHITB.TENOR = BPCSHITI.INTR_TBL[WS_I-1].TENOR;
        }
        WS_CCY_INPUT_FLG = 'D';
        WS_BASETYP_INPUT_FLG = 'D';
        WS_TENOR_INPUT_FLG = 'D';
        if (BPCRHITB.CCY.trim().length() > 0) {
            WS_CCY_INPUT_FLG = 'T';
        }
        if (!BPCRHITB.BASE_TYP.equalsIgnoreCase("0") 
            && BPCRHITB.BASE_TYP.trim().length() > 0) {
            WS_BASETYP_INPUT_FLG = 'T';
        }
        if (!BPCRHITB.TENOR.equalsIgnoreCase("0") 
            && BPCRHITB.TENOR.trim().length() > 0) {
            WS_TENOR_INPUT_FLG = 'T';
        }
        if (WS_CCY_INPUT_FLG == 'D' 
                && WS_BASETYP_INPUT_FLG == 'D' 
                && WS_TENOR_INPUT_FLG == 'D') {
            BPCRHITB.COND = '1';
            CEP.TRC(SCCGWA, "AAA");
        } else if (WS_CCY_INPUT_FLG == 'T' 
                && WS_BASETYP_INPUT_FLG == 'D' 
                && WS_TENOR_INPUT_FLG == 'D') {
            BPCRHITB.COND = '2';
            CEP.TRC(SCCGWA, "BBB");
        } else if (WS_CCY_INPUT_FLG == 'D' 
                && WS_BASETYP_INPUT_FLG == 'T' 
                && WS_TENOR_INPUT_FLG == 'D') {
            BPCRHITB.COND = '3';
            CEP.TRC(SCCGWA, "CCC");
        } else if (WS_CCY_INPUT_FLG == 'D' 
                && WS_BASETYP_INPUT_FLG == 'D' 
                && WS_TENOR_INPUT_FLG == 'T') {
            BPCRHITB.COND = '4';
            CEP.TRC(SCCGWA, "DDD");
        } else if (WS_CCY_INPUT_FLG == 'T' 
                && WS_BASETYP_INPUT_FLG == 'T' 
                && WS_TENOR_INPUT_FLG == 'D') {
            BPCRHITB.COND = '5';
            CEP.TRC(SCCGWA, "EEE");
        } else if (WS_CCY_INPUT_FLG == 'T' 
                && WS_BASETYP_INPUT_FLG == 'D' 
                && WS_TENOR_INPUT_FLG == 'T') {
            BPCRHITB.COND = '6';
            CEP.TRC(SCCGWA, "FFF");
        } else if (WS_CCY_INPUT_FLG == 'D' 
                && WS_BASETYP_INPUT_FLG == 'T' 
                && WS_TENOR_INPUT_FLG == 'T') {
            BPCRHITB.COND = '7';
            CEP.TRC(SCCGWA, "GGG");
        } else if (WS_CCY_INPUT_FLG == 'T' 
                && WS_BASETYP_INPUT_FLG == 'T' 
                && WS_TENOR_INPUT_FLG == 'T') {
            BPCRHITB.COND = '8';
            CEP.TRC(SCCGWA, "HHH");
        } else {
        }
        WS_RECORD_FLG = 'Y';
        if (BPCRHITB.BR != 999999) {
            B221_READFST_BPZRHITB();
            if (pgmRtn) return;
        }
        B222_STARTBR_BPZRHITB();
        if (pgmRtn) return;
        B223_READNEXT_BPZRHITB();
        if (pgmRtn) return;
        while (WS_RECORD_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B224_SHOW_DETAIL_MESS();
            if (pgmRtn) return;
            B223_READNEXT_BPZRHITB();
            if (pgmRtn) return;
        }
        B225_ENDB_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B221_READFST_BPZRHITB() throws IOException,SQLException,Exception {
        BPCRHITB.FUNC = 'R';
        R000_CALL_BPZRHITB1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_RECORD_FLG);
        if (WS_RECORD_FLG == 'N') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCRHITB.BR;
            CEP.TRC(SCCGWA, BPCRHITB.BR);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            BPCRHITB.BR = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            CEP.TRC(SCCGWA, BPCRHITB.BR);
            BPCRHITB.FUNC = 'R';
            R000_CALL_BPZRHITB1();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_RECORD_FLG);
            if (WS_RECORD_FLG == 'N') {
                CEP.TRC(SCCGWA, "ASD");
                BPCRHITB.BR = 999999;
                CEP.TRC(SCCGWA, BPCRHITB.BR);
                BPCRHITB.FUNC = 'R';
                R000_CALL_BPZRHITB1();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_RECORD_FLG);
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void B222_STARTBR_BPZRHITB() throws IOException,SQLException,Exception {
        BPCRHITB.FUNC = 'S';
        R000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B223_READNEXT_BPZRHITB() throws IOException,SQLException,Exception {
        BPCRHITB.FUNC = 'N';
        R000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void B224_SHOW_DETAIL_MESS() throws IOException,SQLException,Exception {
        WS_OUTPUT_FLG = 'D';
        WS_QUEUE_PROC.WS_RES_MESS = " ";
        IBS.init(SCCGWA, BPCQ12.DETAIL);
        CEP.TRC(SCCGWA, BPCRHITB.FORMAT);
        BPCQ12.DETAIL.TS_DATE = BPCRHITB.DT;
        BPCQ12.DETAIL.TS_BR = BPCRHITB.BR;
        BPCQ12.DETAIL.TS_CCY = BPCRHITB.CCY;
        BPCQ12.DETAIL.TS_BASETYP = BPCRHITB.BASE_TYP;
        BPCQ12.DETAIL.TS_TENOR = BPCRHITB.TENOR;
        BPCQ12.DETAIL.TS_R_BR = BPCRHITB.REF_BR;
        BPCQ12.DETAIL.TS_R_CCY = BPCRHITB.REF_CCY;
        BPCQ12.DETAIL.TS_R_BASETYP = BPCRHITB.REF_BASE_TYP;
        BPCQ12.DETAIL.TS_R_TENOR = BPCRHITB.REF_TENOR;
        BPCQ12.DETAIL.TS_FORMAT = BPCRHITB.FORMAT;
        BPCQ12.DETAIL.TELLER = BPCRHITB.TELLER;
        BPCQ12.DETAIL.TS_OVR1 = BPCRHITB.OVR1;
        BPCQ12.DETAIL.TS_DT = BPCRHITB.TS;
        if (BPCRHITB.REF_CCY.trim().length() == 0) {
            BPCQ12.DETAIL.TS_RATE = BPCRHITB.RATE;
        } else {
            BPCQ12.DETAIL.TS_RATE = BPCRHITB.DIFF;
        }
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_DATE);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_BR);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_CCY);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_BASETYP);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_TENOR);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_R_BR);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_R_CCY);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_R_BASETYP);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_R_TENOR);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_FORMAT);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_RATE);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_OVR1);
        CEP.TRC(SCCGWA, BPCQ12.DETAIL.TS_DT);
        WS_QUEUE_PROC.WS_RES_MESS = IBS.CLS2CPY(SCCGWA, BPCQ12.DETAIL);
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B225_ENDB_BPZRHITB() throws IOException,SQLException,Exception {
        BPCRHITB.FUNC = 'E';
        R000_CALL_BPZRHITB();
        if (pgmRtn) return;
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
    }
    public void R000_CALL_BPZRHITB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIT_BRW, BPCRHITB);
        if (BPCRHITB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITB.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST) 
                && BPCRHITB.FUNC == 'N') {
                WS_RECORD_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHITB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CALL_BPZRHITB1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIT_BRW, BPCRHITB);
        if (BPCRHITB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITB.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
                WS_RECORD_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHITB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_RECORD_FLG = 'Y';
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 600;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 200;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            WS_QUEUE_PROC.WS_OUTPUT_DATA = " ";
            WS_QUEUE_PROC.WS_OUTPUT_DATA = WS_QUEUE_PROC.WS_RES_MESS;
            if (WS_QUEUE_PROC.WS_OUTPUT_DATA == null) WS_QUEUE_PROC.WS_OUTPUT_DATA = "";
            JIBS_tmp_int = WS_QUEUE_PROC.WS_OUTPUT_DATA.length();
            for (int i=0;i<600-JIBS_tmp_int;i++) WS_QUEUE_PROC.WS_OUTPUT_DATA += " ";
            CEP.TRC(SCCGWA, WS_QUEUE_PROC.WS_OUTPUT_DATA.substring(0, 100));
            if (WS_QUEUE_PROC.WS_OUTPUT_DATA == null) WS_QUEUE_PROC.WS_OUTPUT_DATA = "";
            JIBS_tmp_int = WS_QUEUE_PROC.WS_OUTPUT_DATA.length();
            for (int i=0;i<600-JIBS_tmp_int;i++) WS_QUEUE_PROC.WS_OUTPUT_DATA += " ";
            CEP.TRC(SCCGWA, WS_QUEUE_PROC.WS_OUTPUT_DATA.substring(100 - 1, 100 + 100 - 1));
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = WS_QUEUE_PROC.WS_OUTPUT_DATA;
            SCCMPAG.DATA_LEN = 600;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
