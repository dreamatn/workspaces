package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.SQLException;

public class CIZCKLS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm CITILST_BR = new brParm();
    DBParm CITLSCTL_RD;
    String PGM_SCSSCLDT = "SCSSCLDT";
    char WS_CHECK_FLG = ' ';
    String WS_CHNL = " ";
    String WS_TERM = " ";
    CIZCKLS_REDEFINES8 REDEFINES8 = new CIZCKLS_REDEFINES8();
    String WS_MSGID = " ";
    CIZCKLS_WS_MMO WS_MMO = new CIZCKLS_WS_MMO();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRILST CIRILST = new CIRILST();
    CIRLSCTL CIRLSCTL = new CIRLSCTL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCKLS CICCKLS;
    public void MP(SCCGWA SCCGWA, CICCKLS CICCKLS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCKLS = CICCKLS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZCKLS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCKLS.RC);
        WS_CHNL = SCCGWA.COMM_AREA.CHNL;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CHECK_LST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICCKLS.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.CI_NM);
        CEP.TRC(SCCGWA, CICCKLS.DATA.AP_TYPE);
        CEP.TRC(SCCGWA, CICCKLS.DATA.EXP_MMO);
        if (CICCKLS.DATA.AGR_NO.trim().length() > 0) {
            WS_CHECK_FLG = 'A';
        } else if (CICCKLS.DATA.CI_NO.trim().length() > 0) {
            WS_CHECK_FLG = 'C';
        } else if (CICCKLS.DATA.ID_TYPE.trim().length() > 0 
                && CICCKLS.DATA.ID_NO.trim().length() > 0 
                && CICCKLS.DATA.CI_NM.trim().length() > 0) {
            WS_CHECK_FLG = 'I';
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CICCKLS.DATA.AP_TYPE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AP-TYPE MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CICCKLS.DATA.EXP_MMO);
        if (CICCKLS.DATA.EXP_MMO.trim().length() == 0) {
            CICCKLS.DATA.EXP_MMO = SCCGWA.COMM_AREA.TR_MMO;
        }
        CEP.TRC(SCCGWA, CICCKLS.DATA.EXP_MMO);
    }
    public void B020_CHECK_LST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_FLG);
        CEP.TRC(SCCGWA, WS_CHNL);
        if (WS_CHECK_FLG == 'A') {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.AGR_NO = CICCKLS.DATA.AGR_NO;
            T000_STARTBR_CITILST_BY_AC();
        } else if (WS_CHECK_FLG == 'C') {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.CI_NO = CICCKLS.DATA.CI_NO;
            T000_STARTBR_CITILST_BY_CI();
        } else if (WS_CHECK_FLG == 'I') {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.ID_TYPE = CICCKLS.DATA.ID_TYPE;
            CIRILST.KEY.ID_NO = CICCKLS.DATA.ID_NO;
            CIRILST.KEY.CI_NM = CICCKLS.DATA.CI_NM;
            T000_STARTBR_CITILST_BY_IDNM();
        } else {
        }
        T000_READNEXT_CITILST();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRILST.EXP_DT);
            if (CIRILST.EXP_DT >= SCCGWA.COMM_AREA.AC_DATE 
                || CIRILST.EXP_DT == 0) {
                CEP.TRC(SCCGWA, CIRILST.KEY.LST_TYPE);
                CEP.TRC(SCCGWA, CIRILST.AC_TYP);
                IBS.init(SCCGWA, CIRLSCTL);
                CIRLSCTL.KEY.LST_TYPE = CIRILST.KEY.LST_TYPE;
                CIRLSCTL.KEY.AC_TYP = CIRILST.AC_TYP;
                CIRLSCTL.KEY.AP_TYPE = CICCKLS.DATA.AP_TYPE;
                T000_READ_CITLSCTL();
                IBS.CPY2CLS(SCCGWA, CIRLSCTL.EXP_MMO, WS_MMO);
                if (CIRLSCTL.MSG_TYP == 'E' 
                    && (CICCKLS.DATA.EXP_MMO.trim().length() == 0 
                    || (!CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[1-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[2-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[3-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[4-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[5-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[6-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[7-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[8-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[9-1].WS_EXP_MMO) 
                    && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[10-1].WS_EXP_MMO)))) {
                    IBS.init(SCCGWA, SCCCLDT);
                    if (CIRLSCTL.LIMIT_TIME.trim().length() > 0) {
                        WS_TERM = CIRLSCTL.LIMIT_TIME;
                        IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES8);
                        if (REDEFINES8.WS_TERM_TYP == 'Y') {
                            SCCCLDT.MTHS = (short) (REDEFINES8.WS_TERM_NUM * 12);
                        } else if (REDEFINES8.WS_TERM_TYP == 'M') {
                            SCCCLDT.MTHS = REDEFINES8.WS_TERM_NUM;
                        } else if (REDEFINES8.WS_TERM_TYP == 'D') {
                            SCCCLDT.DAYS = REDEFINES8.WS_TERM_NUM;
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
                    }
                    if (CIRILST.EXP_DT >= SCCGWA.COMM_AREA.AC_DATE 
                        || (CIRILST.EXP_DT == 0 
                        && (SCCCLDT.DATE2 == 0 
                        || SCCCLDT.DATE2 >= SCCGWA.COMM_AREA.AC_DATE))) {
                        CEP.TRC(SCCGWA, "111");
                        CEP.TRC(SCCGWA, CIRLSCTL.CHNL_STSW);
                        CEP.TRC(SCCGWA, WS_CHNL);
                            if (CIRLSCTL.CHNL_STSW == null) CIRLSCTL.CHNL_STSW = "";
                            JIBS_tmp_int = CIRLSCTL.CHNL_STSW.length();
                            for (int i=0;i<20-JIBS_tmp_int;i++) CIRLSCTL.CHNL_STSW += " ";
                        if (CIRLSCTL.CHNL_STSW.substring(0, 1).equalsIgnoreCase("1")
                            || CIRLSCTL.CHNL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                                && (!WS_CHNL.equalsIgnoreCase("101") 
                                && !WS_CHNL.equalsIgnoreCase("10101") 
                                && !WS_CHNL.equalsIgnoreCase("10102"))) {
                            CEP.ERRC(SCCGWA, CIRLSCTL.MSG_ID);
                        } else {
                        }
                    }
                }
            }
            T000_READNEXT_CITILST();
        }
        T000_ENDBR_CITILST();
        if (WS_CHECK_FLG == 'A' 
            && CICCKLS.DATA.ACAC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRILST);
            CIRILST.KEY.AGR_NO = CICCKLS.DATA.ACAC_NO;
            T000_STARTBR_CITILST_BY_AC2();
            T000_READNEXT_CITILST();
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                CEP.TRC(SCCGWA, CIRILST.EXP_DT);
                if (CIRILST.EXP_DT >= SCCGWA.COMM_AREA.AC_DATE 
                    || CIRILST.EXP_DT == 0) {
                    CEP.TRC(SCCGWA, CIRILST.KEY.LST_TYPE);
                    CEP.TRC(SCCGWA, CIRILST.AC_TYP);
                    IBS.init(SCCGWA, CIRLSCTL);
                    CIRLSCTL.KEY.LST_TYPE = CIRILST.KEY.LST_TYPE;
                    CIRLSCTL.KEY.AC_TYP = CIRILST.AC_TYP;
                    CIRLSCTL.KEY.AP_TYPE = CICCKLS.DATA.AP_TYPE;
                    T000_READ_CITLSCTL();
                    IBS.CPY2CLS(SCCGWA, CIRLSCTL.EXP_MMO, WS_MMO);
                    if (CIRLSCTL.MSG_TYP == 'E' 
                        && (CICCKLS.DATA.EXP_MMO.trim().length() == 0 
                        || (!CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[1-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[2-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[3-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[4-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[5-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[6-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[7-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[8-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[9-1].WS_EXP_MMO) 
                        && !CICCKLS.DATA.EXP_MMO.equalsIgnoreCase(WS_MMO.WS_MMOS[10-1].WS_EXP_MMO)))) {
                        IBS.init(SCCGWA, SCCCLDT);
                        if (CIRLSCTL.LIMIT_TIME.trim().length() > 0) {
                            WS_TERM = CIRLSCTL.LIMIT_TIME;
                            IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES8);
                            if (REDEFINES8.WS_TERM_TYP == 'Y') {
                                SCCCLDT.MTHS = (short) (REDEFINES8.WS_TERM_NUM * 12);
                            } else if (REDEFINES8.WS_TERM_TYP == 'M') {
                                SCCCLDT.MTHS = REDEFINES8.WS_TERM_NUM;
                            } else if (REDEFINES8.WS_TERM_TYP == 'D') {
                                SCCCLDT.DAYS = REDEFINES8.WS_TERM_NUM;
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
                        }
                        if (CIRILST.EXP_DT >= SCCGWA.COMM_AREA.AC_DATE 
                            || (CIRILST.EXP_DT == 0 
                            && (SCCCLDT.DATE2 == 0 
                            || SCCCLDT.DATE2 >= SCCGWA.COMM_AREA.AC_DATE))) {
                                if (CIRLSCTL.CHNL_STSW == null) CIRLSCTL.CHNL_STSW = "";
                                JIBS_tmp_int = CIRLSCTL.CHNL_STSW.length();
                                for (int i=0;i<20-JIBS_tmp_int;i++) CIRLSCTL.CHNL_STSW += " ";
                            if (CIRLSCTL.CHNL_STSW.substring(0, 1).equalsIgnoreCase("1")
                                || CIRLSCTL.CHNL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                                    && (!WS_CHNL.equalsIgnoreCase("101") 
                                    && !WS_CHNL.equalsIgnoreCase("10101") 
                                    && !WS_CHNL.equalsIgnoreCase("10102"))) {
                                CEP.ERRC(SCCGWA, CIRLSCTL.MSG_ID);
                            } else {
                            }
                        }
                    }
                }
                T000_READNEXT_CITILST();
            }
            T000_ENDBR_CITILST();
            CEP.ERR(SCCGWA);
        }
    }
    public void T000_STARTBR_CITILST_BY_AC() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "AGR_NO";
        CITILST_BR.rp.where = "LST_TYPE < > '04' "
            + "AND CHK_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_AC2() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "AGR_NO";
        CITILST_BR.rp.where = "LST_TYPE = '04' "
            + "AND CHK_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_CI() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "CI_NO";
        CITILST_BR.rp.where = "AGR_NO = ' ' "
            + "AND CHK_STS = '0' "
            + "AND AC_TYP = '2'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
    }
    public void T000_STARTBR_CITILST_BY_IDNM() throws IOException,SQLException,Exception {
        CITILST_BR.rp = new DBParm();
        CITILST_BR.rp.TableName = "CITILST";
        CITILST_BR.rp.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITILST_BR.rp.where = "AGR_NO = ' ' "
            + "AND CI_NO = ' ' "
            + "AND CHK_STS = '0' "
            + "AND AC_TYP = '2'";
        IBS.STARTBR(SCCGWA, CIRILST, this, CITILST_BR);
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
