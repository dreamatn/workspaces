package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCHKDT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm IBTMST_RD;
    DBParm IBTTMST_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_OUTPUT_FMT = "IBG20   ";
    short WS_MTHS = 0;
    String WS_TABLE_NAME = " ";
    int WS_L_INTS_DT = 0;
    IBZCHKDT_WS_DATE1 WS_DATE1 = new IBZCHKDT_WS_DATE1();
    int WS_INTS_DT = 0;
    IBZCHKDT_WS_DATE2 WS_DATE2 = new IBZCHKDT_WS_DATE2();
    IBZCHKDT_FILLER15 FILLER15 = new IBZCHKDT_FILLER15();
    IBZCHKDT_FILLER17 FILLER17 = new IBZCHKDT_FILLER17();
    IBZCHKDT_FILLER19 FILLER19 = new IBZCHKDT_FILLER19();
    short WS_I = 0;
    String WS_ERR_MSG = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    IBCOCKDT IBCOCKDT = new IBCOCKDT();
    IBRMST IBRMST = new IBRMST();
    IBRTMST IBRTMST = new IBRTMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCCHKDT IBCCHKDT;
    public void MP(SCCGWA SCCGWA, IBCCHKDT IBCCHKDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCHKDT = IBCCHKDT;
        CEP.TRC(SCCGWA);
        IBS.CPY2CLS(SCCGWA, "312831303130313130313031", FILLER15);
        IBS.CPY2CLS(SCCGWA, "010702080309041005110612", FILLER17);
        IBS.CPY2CLS(SCCGWA, "010407100205081103060912", FILLER19);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZCHKDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (IBCCHKDT.FUNC == '1') {
            B020_DDAC_SETTL_DATE();
        } else if (IBCCHKDT.FUNC == '2') {
            B030_TDAC_SETTL_DATE();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCCHKDT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        R000_PROC_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCHKDT.FUNC);
        CEP.TRC(SCCGWA, IBCCHKDT.SETT_DT);
        CEP.TRC(SCCGWA, IBCCHKDT.SEQ_NO);
        CEP.TRC(SCCGWA, IBCCHKDT.AC_NO);
        if (IBCCHKDT.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AC_NO_M_INPUT);
        }
        if (IBCCHKDT.FUNC == '2') {
            if (IBCCHKDT.SEQ_NO == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
            }
        }
    }
    public void B020_DDAC_SETTL_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCCHKDT.AC_NO;
        T000_READ_IBTMST();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        if (IBRMST.L_INTS_DT != 0) {
            WS_L_INTS_DT = IBRMST.L_INTS_DT;
            IBS.CPY2CLS(SCCGWA, WS_L_INTS_DT+"", WS_DATE1);
        } else {
            WS_L_INTS_DT = IBRMST.EFF_DATE;
            IBS.CPY2CLS(SCCGWA, WS_L_INTS_DT+"", WS_DATE1);
        }
        CEP.TRC(SCCGWA, IBRMST.INTS_CYC);
        if (IBRMST.INTS_CYC == '1') {
            B020_01_CAL_INTS_DATE();
        } else if (IBRMST.INTS_CYC == '2') {
            B020_02_CAL_INTS_DATE();
        } else if (IBRMST.INTS_CYC == '3') {
            B020_03_CAL_INTS_DATE();
        } else if (IBRMST.INTS_CYC == '4') {
            B020_04_CAL_INTS_DATE();
        } else if (IBRMST.INTS_CYC == '5') {
            B020_05_CAL_INTS_DATE();
        } else {
        }
    }
    public void B020_01_CAL_INTS_DATE() throws IOException,SQLException,Exception {
        if (IBRMST.L_INTS_DT != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_L_INTS_DT;
            SCCCLDT.MTHS = 12;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
        } else {
            WS_DATE2.WS_DATE2_YYYY = WS_DATE1.WS_DATE1_YYYY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = IBRMST.INTS_DT_MM;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE1.WS_DATE1_MMDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2.WS_DATE2_MMDD);
            if (JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) >= 0) {
                WS_DATE2.WS_DATE2_YYYY = (short) (WS_DATE2.WS_DATE2_YYYY + 1);
                CEP.TRC(SCCGWA, WS_DATE2.WS_DATE2_YYYY);
            }
        }
        R000_GET_MTH_END();
    }
    public void B020_02_CAL_INTS_DATE() throws IOException,SQLException,Exception {
        if (IBRMST.L_INTS_DT != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_L_INTS_DT;
            SCCCLDT.MTHS = 6;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
        } else {
            WS_DATE2.WS_DATE2_YYYY = WS_DATE1.WS_DATE1_YYYY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_I = IBRMST.INTS_DT_MM;
            JIBS_tmp_str[0] = "" + FILLER17.WS_CYC_SEMI[WS_I-1];
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + FILLER17.WS_CYC_SEMI[WS_I-1];
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > Short.parseShort(JIBS_tmp_str[0].substring(0, 2)) 
                && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM <= Short.parseShort(JIBS_tmp_str[1].substring(3 - 1, 3 + 2 - 1))) {
                JIBS_tmp_str[0] = "" + FILLER17.WS_CYC_SEMI[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
            } else {
                JIBS_tmp_str[0] = "" + FILLER17.WS_CYC_SEMI[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
            }
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM) {
                WS_DATE2.WS_DATE2_YYYY = (short) (WS_DATE2.WS_DATE2_YYYY + 1);
                CEP.TRC(SCCGWA, WS_DATE2.WS_DATE2_YYYY);
            }
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM == WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM 
                && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_DD >= WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD) {
                R000_GET_MTH_END();
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_INTS_DT;
                SCCCLDT.MTHS = 6;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_INTS_DT = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
                WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            }
        }
        R000_GET_MTH_END();
    }
    public void B020_03_CAL_INTS_DATE() throws IOException,SQLException,Exception {
        if (IBRMST.L_INTS_DT != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_L_INTS_DT;
            SCCCLDT.MTHS = 3;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
        } else {
            WS_DATE2.WS_DATE2_YYYY = WS_DATE1.WS_DATE1_YYYY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_I = IBRMST.INTS_DT_MM;
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > Short.parseShort(JIBS_tmp_str[0].substring(0, 2)) 
                    && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM <= Short.parseShort(JIBS_tmp_str[1].substring(3 - 1, 3 + 2 - 1))) {
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            } else if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1)) 
                    && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM <= Short.parseShort(JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1))) {
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            } else if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1)) 
                    && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM <= Short.parseShort(JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1))) {
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
            } else {
                JIBS_tmp_str[0] = "" + FILLER19.WS_CYC_QUATER[WS_I-1];
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = 0;
                else WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[1]);
            }
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM > WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM) {
                WS_DATE2.WS_DATE2_YYYY = (short) (WS_DATE2.WS_DATE2_YYYY + 1);
                CEP.TRC(SCCGWA, WS_DATE2.WS_DATE2_YYYY);
            }
            if (WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM == WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM 
                && WS_DATE1.WS_DATE1_MMDD.WS_DATE1_DD >= WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD) {
                R000_GET_MTH_END();
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_INTS_DT;
                SCCCLDT.MTHS = 3;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_INTS_DT = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
                WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            }
        }
        R000_GET_MTH_END();
    }
    public void B020_04_CAL_INTS_DATE() throws IOException,SQLException,Exception {
        if (IBRMST.INTS_DT_DD >= WS_DATE1.WS_DATE1_MMDD.WS_DATE1_DD 
            && IBRMST.L_INTS_DT == 0) {
            WS_DATE2.WS_DATE2_YYYY = WS_DATE1.WS_DATE1_YYYY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM = WS_DATE1.WS_DATE1_MMDD.WS_DATE1_MM;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_L_INTS_DT;
            SCCCLDT.MTHS = 1;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = IBRMST.INTS_DT_DD;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
            WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
        }
        R000_GET_MTH_END();
    }
    public void B020_05_CAL_INTS_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_L_INTS_DT;
        SCCCLDT.DAYS = 1;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        S000_CALL_SCSSCLDT();
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_INTS_DT = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
    }
    public void B030_TDAC_SETTL_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.PRIM_AC_NO = IBCCHKDT.AC_NO;
        IBRTMST.SEQ_NO = IBCCHKDT.SEQ_NO;
        T000_READ_IBTTMST();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        CEP.TRC(SCCGWA, IBRTMST.INTS_CYC);
        if (IBRTMST.INTS_CYC == '1') {
            WS_MTHS = 12;
        } else if (IBRTMST.INTS_CYC == '2') {
            WS_MTHS = 6;
        } else if (IBRTMST.INTS_CYC == '3') {
            WS_MTHS = 3;
        } else if (IBRTMST.INTS_CYC == '4') {
            WS_MTHS = 1;
        } else {
        }
        if (IBRTMST.INTS_CYC != '5') {
            B030_01_TDAC_SETTL_DATE();
        } else {
            B030_02_TDAC_SETTL_DATE();
        }
    }
    public void B030_01_TDAC_SETTL_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "000");
        if (IBRTMST.LSET_DATE != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.MTHS = WS_MTHS;
            SCCCLDT.DATE1 = IBRTMST.LSET_DATE;
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
        } else {
            if (IBRTMST.PVAL_DATE != 0) {
                CEP.TRC(SCCGWA, IBRTMST.PVAL_DATE);
                WS_INTS_DT = IBRTMST.PVAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.MTHS = WS_MTHS;
                SCCCLDT.DATE1 = IBRTMST.VAL_DATE;
                CEP.TRC(SCCGWA, "222");
                CEP.TRC(SCCGWA, SCCCLDT.MTHS);
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_INTS_DT = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            }
        }
    }
    public void B030_02_TDAC_SETTL_DATE() throws IOException,SQLException,Exception {
        if (IBRTMST.LSET_DATE != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DAYS = 1;
            SCCCLDT.DATE1 = IBRTMST.LSET_DATE;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_INTS_DT = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
        } else {
            if (IBRTMST.PVAL_DATE != 0) {
                WS_INTS_DT = IBRTMST.PVAL_DATE;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = 1;
                SCCCLDT.DATE1 = IBRTMST.VAL_DATE;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_INTS_DT = SCCCLDT.DATE2;
                IBS.CPY2CLS(SCCGWA, WS_INTS_DT+"", WS_DATE2);
            }
        }
    }
    public void R000_GET_MTH_END() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD);
        if (WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD > FILLER15.WS_MTH_DAYS[WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM-1]) {
            if (WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM == 2) {
                IBS.init(SCCGWA, SCCCKDT);
                WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = 1;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
                SCCCKDT.DATE = WS_INTS_DT;
                S000_CALL_SCSSCKDT();
                WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = SCCCKDT.MTH_DAYS;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
                WS_DATE2.WS_DATE2_MMDD.WS_DATE2_DD = FILLER15.WS_MTH_DAYS[WS_DATE2.WS_DATE2_MMDD.WS_DATE2_MM-1];
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE2);
                WS_INTS_DT = Integer.parseInt(JIBS_tmp_str[0]);
            }
        }
    }
    public void R000_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCKDT);
        IBCOCKDT.DATE = WS_INTS_DT;
        if (WS_INTS_DT != IBCCHKDT.SETT_DT) {
            IBCOCKDT.NEED_FLAG = 'Y';
        } else {
            IBCOCKDT.NEED_FLAG = 'N';
        }
        CEP.TRC(SCCGWA, IBCOCKDT.DATE);
        CEP.TRC(SCCGWA, IBCOCKDT.NEED_FLAG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCKDT;
        SCCFMT.DATA_LEN = 9;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, SCCCLDT);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCKDT.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = :IBRTMST.SEQ_NO";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
