package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCKID {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WK_DATE_MIN = 18510000;
    int WK_DATE_MAX = 19010000;
    int WK_DATES = 500000;
    String WS_ERR_MSG = " ";
    short WS_II = 0;
    short WS_III = 0;
    String WS_ID_NO = " ";
    char WS_CUR_VAL = ' ';
    CIZCKID_WS_WI[] WS_WI = new CIZCKID_WS_WI[17];
    char WS_LAST_CHK = ' ';
    short WS_LAST_VAL = 0;
    short WS_CHK_VAL = 0;
    short WS_TEMP = 0;
    int WS_CUR_DATE = 0;
    int WS_CUR_DATE_INT = 0;
    CIZCKID_WS_RC_MSG WS_RC_MSG = new CIZCKID_WS_RC_MSG();
    char WS_CHK_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCKID CICCKID;
    public CIZCKID() {
        for (int i=0;i<17;i++) WS_WI[i] = new CIZCKID_WS_WI();
    }
    public void MP(SCCGWA SCCGWA, CICCKID CICCKID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCKID = CICCKID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCKID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICCKID.RC.RC_MMO = "CI";
        CICCKID.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICCKID.DATA.ID_TYPE.equalsIgnoreCase("10100")
            || CICCKID.DATA.ID_TYPE.equalsIgnoreCase("10200")
            || CICCKID.DATA.ID_TYPE.equalsIgnoreCase("10300")) {
            B020_CHECK_ID_NO_PROC();
            if (pgmRtn) return;
        } else if (CICCKID.DATA.ID_TYPE.equalsIgnoreCase("10701")) {
            B030_CHECK_PERMIT_HK_MC_PROC();
            if (pgmRtn) return;
        } else if (CICCKID.DATA.ID_TYPE.equalsIgnoreCase("10703")) {
            B040_CHECK_PERMIT_TAIWAN_PROC();
            if (pgmRtn) return;
        } else if (CICCKID.DATA.ID_TYPE.equalsIgnoreCase("20500")) {
            B050_CHECK_BUS_LC_PROC();
            if (pgmRtn) return;
        } else if (CICCKID.DATA.ID_TYPE.equalsIgnoreCase("20600")) {
            B060_CHECK_ORG_CD_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCKID.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICCKID.DATA.ID_NO);
        if (CICCKID.DATA.ID_NO.trim().length() > 0) {
            WS_ID_NO = CICCKID.DATA.ID_NO;
            if (CICCKID.DATA.ID_NO.equalsIgnoreCase("653222196507194052") 
                || CICCKID.DATA.ID_NO.equalsIgnoreCase("653101198207092013") 
                || CICCKID.DATA.ID_NO.equalsIgnoreCase("650202197201230053") 
                || CICCKID.DATA.ID_NO.equalsIgnoreCase("653201198103220514") 
                || CICCKID.DATA.ID_NO.equalsIgnoreCase("653121197012273210") 
                || CICCKID.DATA.ID_NO.equalsIgnoreCase("653125198306051496")) {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_IDNO_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHECK_ID_NO_PROC() throws IOException,SQLException,Exception {
        WS_WI[1-1].WS_W = 7;
        WS_WI[2-1].WS_W = 9;
        WS_WI[3-1].WS_W = 10;
        WS_WI[4-1].WS_W = 5;
        WS_WI[5-1].WS_W = 8;
        WS_WI[6-1].WS_W = 4;
        WS_WI[7-1].WS_W = 2;
        WS_WI[8-1].WS_W = 1;
        WS_WI[9-1].WS_W = 6;
        WS_WI[10-1].WS_W = 3;
        WS_WI[11-1].WS_W = 7;
        WS_WI[12-1].WS_W = 9;
        WS_WI[13-1].WS_W = 10;
        WS_WI[14-1].WS_W = 5;
        WS_WI[15-1].WS_W = 8;
        WS_WI[16-1].WS_W = 4;
        WS_WI[17-1].WS_W = 2;
        WS_CHK_FLG = 'Y';
        for (WS_II = 16; WS_II <= 30; WS_II += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_II - 1, WS_II + 1 - 1).charAt(0);
            if (WS_CUR_VAL == ' ') {
            } else {
                WS_CHK_FLG = 'N';
            }
        }
        if (WS_CHK_FLG == 'N') {
            R00_CHECK_IDNO_18();
            if (pgmRtn) return;
        } else if (WS_CHK_FLG == 'Y') {
            R00_CHECK_IDNO_15();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
        }
    }
    public void R00_CHECK_IDNO_18() throws IOException,SQLException,Exception {
        for (WS_III = 19; WS_III <= 30; WS_III += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_III - 1, WS_III + 1 - 1).charAt(0);
            if (WS_CUR_VAL != ' ') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
            }
        }
        WS_LAST_VAL = 0;
        for (WS_III = 1; WS_III <= 17; WS_III += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_III - 1, WS_III + 1 - 1).charAt(0);
            if (WS_CUR_VAL == '0') {
            } else if (WS_CUR_VAL == '1') {
                WS_LAST_VAL += WS_WI[WS_III-1].WS_W;
            } else if (WS_CUR_VAL == '2') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 2);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '3') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 3);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '4') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 4);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '5') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 5);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '6') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 6);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '7') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 7);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '8') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 8);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '9') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 9);
                WS_LAST_VAL += WS_TEMP;
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
            }
        }
        WS_CHK_VAL = (short) (WS_LAST_VAL % 11);
        WS_TEMP = (short) ((WS_LAST_VAL - WS_CHK_VAL) / 11);
        CEP.TRC(SCCGWA, WS_CHK_VAL);
        if (WS_CHK_VAL == 0) {
            WS_LAST_CHK = '1';
        } else if (WS_CHK_VAL == 1) {
            WS_LAST_CHK = '0';
        } else if (WS_CHK_VAL == 2) {
            WS_LAST_CHK = 'X';
        } else if (WS_CHK_VAL == 3) {
            WS_LAST_CHK = '9';
        } else if (WS_CHK_VAL == 4) {
            WS_LAST_CHK = '8';
        } else if (WS_CHK_VAL == 5) {
            WS_LAST_CHK = '7';
        } else if (WS_CHK_VAL == 6) {
            WS_LAST_CHK = '6';
        } else if (WS_CHK_VAL == 7) {
            WS_LAST_CHK = '5';
        } else if (WS_CHK_VAL == 8) {
            WS_LAST_CHK = '4';
        } else if (WS_CHK_VAL == 9) {
            WS_LAST_CHK = '3';
        } else if (WS_CHK_VAL == 10) {
            WS_LAST_CHK = '2';
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR);
        }
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        if (WS_ID_NO.substring(18 - 1, 18 + 1 - 1).trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
        }
        CEP.TRC(SCCGWA, WS_LAST_CHK);
        if (CICCKID.FUNC == 'G') {
            if (CICCKID.DATA.ID_NO == null) CICCKID.DATA.ID_NO = "";
            JIBS_tmp_int = CICCKID.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICCKID.DATA.ID_NO += " ";
            JIBS_tmp_str[0] = "" + WS_LAST_CHK;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CICCKID.DATA.ID_NO = CICCKID.DATA.ID_NO.substring(0, 18 - 1) + JIBS_tmp_str[0] + CICCKID.DATA.ID_NO.substring(18 + 1 - 1);
        } else {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_LAST_CHK != WS_ID_NO.substring(18 - 1, 18 + 1 - 1)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
            }
        }
        R00_CHECK_DATE_FORMAT();
        if (pgmRtn) return;
    }
    public void R00_CHECK_IDNO_15() throws IOException,SQLException,Exception {
        for (WS_III = 1; WS_III <= 15; WS_III += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_III - 1, WS_III + 1 - 1).charAt(0);
            if (WS_CUR_VAL != '0' 
                && WS_CUR_VAL != '1' 
                && WS_CUR_VAL != '2' 
                && WS_CUR_VAL != '3' 
                && WS_CUR_VAL != '4' 
                && WS_CUR_VAL != '5' 
                && WS_CUR_VAL != '6' 
                && WS_CUR_VAL != '7' 
                && WS_CUR_VAL != '8' 
                && WS_CUR_VAL != '9') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
            }
        }
        WS_CHK_FLG = 'Y';
        R00_CHECK_DATE_FORMAT();
        if (pgmRtn) return;
    }
    public void R00_CHECK_DATE_FORMAT() throws IOException,SQLException,Exception {
        if (WS_CHK_FLG == 'N') {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_ID_NO.substring(7 - 1, 7 + 8 - 1).trim().length() == 0) WS_CUR_DATE = 0;
            else WS_CUR_DATE = Integer.parseInt(WS_ID_NO.substring(7 - 1, 7 + 8 - 1));
        } else if (WS_CHK_FLG == 'Y') {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_CUR_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_CUR_DATE;
            JIBS_NumStr = JIBS_tmp_str[0].substring(0, 2) + JIBS_NumStr.substring(2);
            WS_CUR_DATE = Integer.parseInt(JIBS_NumStr);
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            JIBS_tmp_str[0] = "" + WS_CUR_DATE;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WS_CUR_DATE;
            JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + WS_ID_NO.substring(7 - 1, 7 + 6 - 1) + JIBS_NumStr.substring(3 + 6 - 1);
            WS_CUR_DATE = Integer.parseInt(JIBS_NumStr);
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR);
        }
        WS_CUR_DATE_INT = WS_CUR_DATE;
        if (WS_CUR_DATE_INT > WK_DATE_MIN 
            && WS_CUR_DATE_INT <= WK_DATE_MAX) {
            WS_CUR_DATE_INT += WK_DATES;
        }
        CEP.TRC(SCCGWA, WS_CUR_DATE_INT);
        SCCCKDT.DATE = WS_CUR_DATE_INT;
        S000_CALL_SCSSCKDT();
        if (pgmRtn) return;
    }
    public void B030_CHECK_PERMIT_HK_MC_PROC() throws IOException,SQLException,Exception {
    }
    public void B040_CHECK_PERMIT_TAIWAN_PROC() throws IOException,SQLException,Exception {
    }
    public void B050_CHECK_BUS_LC_PROC() throws IOException,SQLException,Exception {
    }
    public void B060_CHECK_ORG_CD_PROC() throws IOException,SQLException,Exception {
        WS_WI[1-1].WS_W = 3;
        WS_WI[2-1].WS_W = 7;
        WS_WI[3-1].WS_W = 9;
        WS_WI[4-1].WS_W = 10;
        WS_WI[5-1].WS_W = 5;
        WS_WI[6-1].WS_W = 8;
        WS_WI[7-1].WS_W = 4;
        WS_WI[8-1].WS_W = 2;
        for (WS_III = 11; WS_III <= 30; WS_III += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_III - 1, WS_III + 1 - 1).charAt(0);
            if (WS_CUR_VAL != ' ') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        WS_LAST_VAL = 0;
        for (WS_III = 1; WS_III <= 8; WS_III += 1) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            WS_CUR_VAL = WS_ID_NO.substring(WS_III - 1, WS_III + 1 - 1).charAt(0);
            if (WS_CUR_VAL == '0') {
            } else if (WS_CUR_VAL == '1') {
                WS_LAST_VAL += WS_WI[WS_III-1].WS_W;
            } else if (WS_CUR_VAL == '2') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 2);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '3') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 3);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '4') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 4);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '5') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 5);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '6') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 6);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '7') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 7);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '8') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 8);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == '9') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 9);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'A') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 10);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'B') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 11);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'C') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 12);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'D') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 13);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'E') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 14);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'F') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 15);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'G') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 16);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'H') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 17);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'I') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 18);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'J') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 19);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'K') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 20);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'L') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 21);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'M') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 22);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'N') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 23);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'O') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 24);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'P') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 25);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'Q') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 26);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'R') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 27);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'S') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 28);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'T') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 29);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'U') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 30);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'V') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 31);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'W') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 32);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'X') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 33);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'Y') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 34);
                WS_LAST_VAL += WS_TEMP;
            } else if (WS_CUR_VAL == 'Z') {
                WS_TEMP = (short) (WS_WI[WS_III-1].WS_W * 35);
                WS_LAST_VAL += WS_TEMP;
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        WS_CHK_VAL = (short) (WS_LAST_VAL % 11);
        WS_TEMP = (short) ((WS_LAST_VAL - WS_CHK_VAL) / 11);
        CEP.TRC(SCCGWA, WS_CHK_VAL);
        if (WS_CHK_VAL == 0) {
            WS_LAST_CHK = '0';
        } else if (WS_CHK_VAL == 1) {
            WS_LAST_CHK = 'X';
        } else if (WS_CHK_VAL == 2) {
            WS_LAST_CHK = '9';
        } else if (WS_CHK_VAL == 3) {
            WS_LAST_CHK = '8';
        } else if (WS_CHK_VAL == 4) {
            WS_LAST_CHK = '7';
        } else if (WS_CHK_VAL == 5) {
            WS_LAST_CHK = '6';
        } else if (WS_CHK_VAL == 6) {
            WS_LAST_CHK = '5';
        } else if (WS_CHK_VAL == 7) {
            WS_LAST_CHK = '4';
        } else if (WS_CHK_VAL == 8) {
            WS_LAST_CHK = '3';
        } else if (WS_CHK_VAL == 9) {
            WS_LAST_CHK = '2';
        } else if (WS_CHK_VAL == 10) {
            WS_LAST_CHK = '1';
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR);
        }
        if (WS_ID_NO == null) WS_ID_NO = "";
        JIBS_tmp_int = WS_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
        if (!WS_ID_NO.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("-")) {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            CEP.TRC(SCCGWA, WS_ID_NO.substring(9 - 1, 9 + 1 - 1));
            CEP.TRC(SCCGWA, WS_LAST_CHK);
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_LAST_CHK != WS_ID_NO.substring(9 - 1, 9 + 1 - 1)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            CEP.TRC(SCCGWA, WS_ID_NO.substring(10 - 1, 10 + 1 - 1));
            CEP.TRC(SCCGWA, WS_LAST_CHK);
            if (WS_ID_NO == null) WS_ID_NO = "";
            JIBS_tmp_int = WS_ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
            if (WS_LAST_CHK != WS_ID_NO.substring(10 - 1, 10 + 1 - 1)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_CARD_ILLEGAL, CICCKID.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        WS_RC_MSG.WS_RC = 0;
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        WS_RC_MSG.WS_AP_MMO = "SC";
        if (WS_RC_MSG.WS_RC != 0) {
            CEP.ERR(SCCGWA, WS_RC_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICCKID.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICCKID=");
            CEP.TRC(SCCGWA, CICCKID);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
