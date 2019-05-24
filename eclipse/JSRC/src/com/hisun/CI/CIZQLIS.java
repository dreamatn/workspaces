package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQLIS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm CITILST_BR = new brParm();
    DBParm CITLSCTL_RD;
    DBParm CITACAC_RD;
    boolean pgmRtn = false;
    int K_MAX_ROW = 5;
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_EXP_DT = 0;
    String WS_TERM = " ";
    CIZQLIS_REDEFINES9 REDEFINES9 = new CIZQLIS_REDEFINES9();
    String WS_MSGID = " ";
    char WS_INQ_BY_AC = ' ';
    String WS_ACAC_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRILST CIRILST = new CIRILST();
    CIRLSCTL CIRLSCTL = new CIRLSCTL();
    CIRACAC CIRACAC = new CIRACAC();
    CICFA43 CICFA43 = new CICFA43();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQLIS CICQLIS;
    public void MP(SCCGWA SCCGWA, CICQLIS CICQLIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQLIS = CICQLIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQLIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICQLIS.RC);
        IBS.init(SCCGWA, CICFA43);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        if (CICQLIS.DATA.FUNC.trim().length() == 0) {
            B030_INQUIRE_LIST();
            if (pgmRtn) return;
        } else {
            B040_CHECK_LIST();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQLIS.DATA.PAGE_NUM);
        if (CICQLIS.DATA.PAGE_NUM == 0) {
            CEP.TRC(SCCGWA, "PAGE-NUM IS NULL!");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "当前页数必输");
        }
        CEP.TRC(SCCGWA, CICQLIS.DATA.PAGE_ROW);
        if (CICQLIS.DATA.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "PAGE-ROW IS NULL!");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "每页行数必输");
        }
        CEP.ERR(SCCGWA);
        CEP.TRC(SCCGWA, CICQLIS.DATA.PAGE_ROW);
        if (CICQLIS.DATA.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICQLIS.DATA.PAGE_ROW;
        }
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQLIS.DATA.PAGE_NUM);
        WS_MIN_ROW = ( CICQLIS.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = CICQLIS.DATA.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 1;
        WS_I = 1;
    }
    public void B030_INQUIRE_LIST() throws IOException,SQLException,Exception {
        if (CICQLIS.DATA.AGR_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICQLIS.DATA.AGR_NO);
            WS_INQ_BY_AC = 'Y';
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.AGR_NO = CICQLIS.DATA.AGR_NO;
            T000_STARTBR_CITILST_BY_AC();
            if (pgmRtn) return;
        } else if (CICQLIS.DATA.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICQLIS.DATA.CI_NO);
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.CI_NO = CICQLIS.DATA.CI_NO;
            T000_STARTBR_CITILST_BY_CI();
            if (pgmRtn) return;
        } else if (CICQLIS.DATA.ID_TYPE.trim().length() > 0 
                && CICQLIS.DATA.ID_NO.trim().length() > 0 
                && CICQLIS.DATA.CI_NM.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICQLIS.DATA.ID_TYPE);
            CEP.TRC(SCCGWA, CICQLIS.DATA.ID_NO);
            CEP.TRC(SCCGWA, CICQLIS.DATA.CI_NM);
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.ID_TYPE = CICQLIS.DATA.ID_TYPE;
            CIRILST.KEY.ID_NO = CICQLIS.DATA.ID_NO;
            CIRILST.KEY.CI_NM = CICQLIS.DATA.CI_NM;
            T000_STARTBR_CITILST_BY_IDNM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITILST();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICQLIS.DATA.LST_TYP.trim().length() == 0 
                || CICQLIS.DATA.LST_TYP.equalsIgnoreCase(CIRILST.KEY.LST_TYPE)) 
                && (CICQLIS.DATA.CHK_STS == ' ' 
                || CICQLIS.DATA.CHK_STS == CIRILST.CHK_STS)) {
                CEP.TRC(SCCGWA, WS_CURRENT_ROW);
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                    && WS_I <= WS_PAGE_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                }
                WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            }
            T000_READNEXT_CITILST();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITILST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INQ_BY_AC);
        if (WS_INQ_BY_AC == 'Y') {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CICQLIS.DATA.AGR_NO;
            T000_READ_CITACAC_DEFAULT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                IBS.init(SCCGWA, CIRILST);
                CIRILST.KEY.AGR_NO = CIRACAC.KEY.ACAC_NO;
                T000_STARTBR_CITILST_BY_AC2();
                if (pgmRtn) return;
                T000_READNEXT_CITILST();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    if ((CICQLIS.DATA.LST_TYP.trim().length() == 0 
                        || CICQLIS.DATA.LST_TYP.equalsIgnoreCase(CIRILST.KEY.LST_TYPE)) 
                        && (CICQLIS.DATA.CHK_STS == ' ' 
                        || CICQLIS.DATA.CHK_STS == CIRILST.CHK_STS)) {
                        CEP.TRC(SCCGWA, WS_CURRENT_ROW);
                        if (WS_CURRENT_ROW >= WS_MIN_ROW 
                            && WS_CURRENT_ROW <= WS_MAX_ROW 
                            && WS_I <= WS_PAGE_ROW) {
                            R000_TRANS_DATA_TO_OUTPUT();
                            if (pgmRtn) return;
                        }
                        WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                    }
                    T000_READNEXT_CITILST();
                    if (pgmRtn) return;
                }
                T000_ENDBR_CITILST();
                if (pgmRtn) return;
            }
        }
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_CHECK_LIST() throws IOException,SQLException,Exception {
        if (CICQLIS.DATA.AGR_NO.trim().length() > 0) {
            WS_INQ_BY_AC = 'Y';
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.AGR_NO = CICQLIS.DATA.AGR_NO;
            T000_STARTBR_CITILST_BY_AC();
            if (pgmRtn) return;
        } else if (CICQLIS.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.CI_NO = CICQLIS.DATA.CI_NO;
            T000_STARTBR_CITILST_BY_CI();
            if (pgmRtn) return;
        } else if (CICQLIS.DATA.ID_TYPE.trim().length() > 0 
                && CICQLIS.DATA.ID_NO.trim().length() > 0 
                && CICQLIS.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.ID_TYPE = CICQLIS.DATA.ID_TYPE;
            CIRILST.KEY.ID_NO = CICQLIS.DATA.ID_NO;
            CIRILST.KEY.CI_NM = CICQLIS.DATA.CI_NM;
            T000_STARTBR_CITILST_BY_IDNM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITILST();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICQLIS.DATA.LST_TYP.trim().length() == 0 
                || CICQLIS.DATA.LST_TYP.equalsIgnoreCase(CIRILST.KEY.LST_TYPE)) 
                && (CICQLIS.DATA.CHK_STS == ' ' 
                || CICQLIS.DATA.CHK_STS == CIRILST.CHK_STS)) {
                CEP.TRC(SCCGWA, CIRILST.KEY.LST_TYPE);
                CEP.TRC(SCCGWA, CIRILST.AC_TYP);
                IBS.init(SCCGWA, CIRLSCTL);
                CIRLSCTL.KEY.LST_TYPE = CIRILST.KEY.LST_TYPE;
                CIRLSCTL.KEY.AC_TYP = CIRILST.AC_TYP;
                CIRLSCTL.KEY.AP_TYPE = CICQLIS.DATA.FUNC;
                T000_READ_CITLSCTL();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    if (CIRILST.EXP_DT != 0) {
                        WS_EXP_DT = CIRILST.EXP_DT;
                    } else {
                        if (CIRLSCTL.LIMIT_TIME.trim().length() > 0) {
                            IBS.init(SCCGWA, SCCCLDT);
                            WS_TERM = CIRLSCTL.LIMIT_TIME;
                            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES9);
                            if (REDEFINES9.WS_TERM_TYP == 'Y') {
                                SCCCLDT.MTHS = (short) (REDEFINES9.WS_TERM_NUM * 12);
                            } else if (REDEFINES9.WS_TERM_TYP == 'M') {
                                SCCCLDT.MTHS = REDEFINES9.WS_TERM_NUM;
                            } else if (REDEFINES9.WS_TERM_TYP == 'D') {
                                SCCCLDT.DAYS = REDEFINES9.WS_TERM_NUM;
                            } else {
                            }
                            SCCCLDT.DATE1 = CIRILST.EFF_DT;
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
                                CEP.ERR(SCCGWA, WS_MSGID);
                            }
                            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                            WS_EXP_DT = SCCCLDT.DATE2;
                        } else {
                            WS_EXP_DT = 0;
                        }
                    }
                    if (CIRLSCTL.MSG_TYP == 'E' 
                        && (WS_EXP_DT == 0 
                        || WS_EXP_DT >= SCCGWA.COMM_AREA.AC_DATE)) {
                        if (WS_CURRENT_ROW >= WS_MIN_ROW 
                            && WS_CURRENT_ROW <= WS_MAX_ROW 
                            && WS_I <= WS_PAGE_ROW) {
                            R000_TRANS_DATA_TO_OUTPUT();
                            if (pgmRtn) return;
                        }
                        WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                    }
                }
            }
            T000_READNEXT_CITILST();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITILST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INQ_BY_AC);
        if (WS_INQ_BY_AC == 'Y') {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CICQLIS.DATA.AGR_NO;
            T000_READ_CITACAC_DEFAULT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                IBS.init(SCCGWA, CIRILST);
                CIRILST.KEY.AGR_NO = CIRACAC.KEY.ACAC_NO;
                T000_STARTBR_CITILST_BY_AC2();
                if (pgmRtn) return;
                T000_READNEXT_CITILST();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    if ((CICQLIS.DATA.LST_TYP.trim().length() == 0 
                        || CICQLIS.DATA.LST_TYP.equalsIgnoreCase(CIRILST.KEY.LST_TYPE)) 
                        && (CICQLIS.DATA.CHK_STS == ' ' 
                        || CICQLIS.DATA.CHK_STS == CIRILST.CHK_STS)) {
                        CEP.TRC(SCCGWA, CIRILST.KEY.LST_TYPE);
                        CEP.TRC(SCCGWA, CIRILST.AC_TYP);
                        IBS.init(SCCGWA, CIRLSCTL);
                        CIRLSCTL.KEY.LST_TYPE = CIRILST.KEY.LST_TYPE;
                        CIRLSCTL.KEY.AC_TYP = CIRILST.AC_TYP;
                        CIRLSCTL.KEY.AP_TYPE = CICQLIS.DATA.FUNC;
                        T000_READ_CITLSCTL();
                        if (pgmRtn) return;
                        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                            if (CIRILST.EXP_DT != 0) {
                                WS_EXP_DT = CIRILST.EXP_DT;
                            } else {
                                if (CIRLSCTL.LIMIT_TIME.trim().length() > 0) {
                                    IBS.init(SCCGWA, SCCCLDT);
                                    WS_TERM = CIRLSCTL.LIMIT_TIME;
                                    IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES9);
                                    if (REDEFINES9.WS_TERM_TYP == 'Y') {
                                        SCCCLDT.MTHS = (short) (REDEFINES9.WS_TERM_NUM * 12);
                                    } else if (REDEFINES9.WS_TERM_TYP == 'M') {
                                        SCCCLDT.MTHS = REDEFINES9.WS_TERM_NUM;
                                    } else if (REDEFINES9.WS_TERM_TYP == 'D') {
                                        SCCCLDT.DAYS = REDEFINES9.WS_TERM_NUM;
                                    } else {
                                    }
                                    SCCCLDT.DATE1 = CIRILST.EFF_DT;
                                    CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                                    SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
                                    SCSSCLDT2.MP(SCCGWA, SCCCLDT);
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
                                        CEP.ERR(SCCGWA, WS_MSGID);
                                    }
                                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                                    WS_EXP_DT = SCCCLDT.DATE2;
                                } else {
                                    WS_EXP_DT = 0;
                                }
                            }
                            if (CIRLSCTL.MSG_TYP == 'E' 
                                && (WS_EXP_DT == 0 
                                || WS_EXP_DT >= SCCGWA.COMM_AREA.AC_DATE)) {
                                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                                    && WS_I <= WS_PAGE_ROW) {
                                    R000_TRANS_DATA_TO_OUTPUT();
                                    if (pgmRtn) return;
                                }
                                WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                            }
                        }
                    }
                    T000_READNEXT_CITILST();
                    if (pgmRtn) return;
                }
                T000_ENDBR_CITILST();
                if (pgmRtn) return;
            }
        }
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, CIRILST.KEY.AGR_NO);
        CEP.TRC(SCCGWA, CIRILST.AC_TYP);
        CEP.TRC(SCCGWA, CIRILST.KEY.LST_TYPE);
        CEP.TRC(SCCGWA, CIRILST.KEY.CI_NO);
        CEP.TRC(SCCGWA, CIRILST.KEY.ID_TYPE);
        CEP.TRC(SCCGWA, CIRILST.KEY.ID_NO);
        CEP.TRC(SCCGWA, CIRILST.KEY.CI_NM);
        if (CIRILST.KEY.LST_TYPE.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.KEY.ACAC_NO = CIRILST.KEY.AGR_NO;
            T000_READ_CITACAC();
            if (pgmRtn) return;
            CICFA43.FMT.DATA[WS_I-1].AGR_NO = CIRACAC.AGR_NO;
        } else {
            CICFA43.FMT.DATA[WS_I-1].AGR_NO = CIRILST.KEY.AGR_NO;
        }
        CICFA43.FMT.DATA[WS_I-1].AC_TYP = CIRILST.AC_TYP;
        CICFA43.FMT.DATA[WS_I-1].LST_TYP = CIRILST.KEY.LST_TYPE;
        CICFA43.FMT.DATA[WS_I-1].CI_NO = CIRILST.KEY.CI_NO;
        CICFA43.FMT.DATA[WS_I-1].ID_TYP = CIRILST.KEY.ID_TYPE;
        CICFA43.FMT.DATA[WS_I-1].ID_NO = CIRILST.KEY.ID_NO;
        CICFA43.FMT.DATA[WS_I-1].CI_NM = CIRILST.KEY.CI_NM;
        CICFA43.FMT.DATA[WS_I-1].TEL_NO = CIRILST.TEL_NO;
        CICFA43.FMT.DATA[WS_I-1].START_DT = CIRILST.START_DT;
        CICFA43.FMT.DATA[WS_I-1].END_DT = CIRILST.END_DT;
        CICFA43.FMT.DATA[WS_I-1].EFF_DT = CIRILST.EFF_DT;
        CICFA43.FMT.DATA[WS_I-1].EXP_DT = CIRILST.EXP_DT;
        CICFA43.FMT.DATA[WS_I-1].CHK_STS = CIRILST.CHK_STS;
        CICFA43.FMT.DATA[WS_I-1].CHK_BR = CIRILST.CHK_BR;
        CICFA43.FMT.DATA[WS_I-1].CHK_TLR = CIRILST.CHK_TLR;
        CICFA43.FMT.DATA[WS_I-1].CHK_DT = CIRILST.CHK_DT;
        WS_I = WS_I + 1;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_CURRENT_ROW = WS_CURRENT_ROW - 1;
        WS_I = WS_I - 1;
        CICFA43.FMT.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        CICFA43.FMT.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CICFA43.FMT.TOTAL_PAGE = CICFA43.FMT.TOTAL_PAGE + 1;
        }
        CICFA43.FMT.CURR_PAGE = CICQLIS.DATA.PAGE_NUM;
        CICFA43.FMT.PAGE_ROW = WS_I;
        if (CICFA43.FMT.CURR_PAGE >= CICFA43.FMT.TOTAL_PAGE 
            || CICFA43.FMT.TOTAL_PAGE == 0) {
            CICFA43.FMT.LAST_PAGE = 'Y';
        } else {
            CICFA43.FMT.LAST_PAGE = 'N';
        }
        CEP.TRC(SCCGWA, CICFA43.FMT.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICFA43.FMT.CURR_PAGE);
        CEP.TRC(SCCGWA, CICFA43.FMT.PAGE_ROW);
        CEP.TRC(SCCGWA, CICFA43.FMT.LAST_PAGE);
        CEP.TRC(SCCGWA, CICFA43.FMT.DATA[1-1].CI_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIA43";
        SCCFMT.DATA_PTR = CICFA43;
        SCCFMT.DATA_LEN = 4445;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_CITILST_BY_AC() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "AGR_NO";
        CITILST_BR.rp.where = "LST_TYPE < > '04'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_AC2() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "AGR_NO";
        CITILST_BR.rp.where = "LST_TYPE = '04'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_CI() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRILST, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_IDNM() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        IBS.STARTBR(SCCGWA, CIRILST, CITILST_BR);
    }
    public void T000_READNEXT_CITILST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_ENDBR_CITILST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITILST_BR);
    }
    public void T000_READ_CITLSCTL() throws IOException,SQLException,Exception {
        CITLSCTL_RD = new DBParm();
        CITLSCTL_RD.TableName = "CITLSCTL";
        IBS.READ(SCCGWA, CIRLSCTL, CITLSCTL_RD);
    }
    public void T000_READ_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1' "
            + "AND ACAC_STS = '0'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
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
