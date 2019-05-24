package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSGRS {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITGRPM_RD;
    DBParm CITCGRP_RD;
    brParm CITGRPM_BR = new brParm();
    brParm CITCGRP_BR = new brParm();
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    char WS_CI_TYP = ' ';
    String WS_SVR_LVL = " ";
    char WS_SEX = ' ';
    String WS_OCCUP = " ";
    char WS_EP_FLG = ' ';
    char WS_CD_EMP = ' ';
    String WS_INDUS1 = " ";
    char WS_SIZE = ' ';
    CIZSGRS_WS_MATCH[] WS_MATCH = new CIZSGRS_WS_MATCH[5];
    int WS_AC_DATE = 0;
    char WS_GRS_NO_FLG = ' ';
    char WS_DYN_GRS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCGRP CIRCGRP = new CIRCGRP();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSGRS CICSGRS;
    public CIZSGRS() {
        for (int i=0;i<5;i++) WS_MATCH[i] = new CIZSGRS_WS_MATCH();
    }
    public void MP(SCCGWA SCCGWA, CICSGRS CICSGRS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSGRS = CICSGRS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSGRS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (WS_GRS_NO_FLG == 'I') {
            B020_INQUIRE_SOME_GRS_INF();
            if (pgmRtn) return;
        } else {
            B030_BROWSE_ALL_GRS_INF();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSGRS.DATA.CI_NO);
        if (CICSGRS.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICSGRS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSGRS.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICSGRS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CI_TYP = CIRBAS.CI_TYP;
        WS_SVR_LVL = CIRBAS.SVR_LVL;
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        if (CIRBAS.IDE_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_EP_FLG = '0';
        } else {
            WS_EP_FLG = '1';
        }
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        if (CIRBAS.IDE_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_CD_EMP = 'Y';
        } else {
            WS_CD_EMP = 'N';
        }
        CEP.TRC(SCCGWA, WS_EP_FLG);
        CEP.TRC(SCCGWA, WS_CD_EMP);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, WS_SVR_LVL);
        if (WS_CI_TYP == '1') {
            IBS.init(SCCGWA, CIRPDM);
            CIRPDM.KEY.CI_NO = CICSGRS.DATA.CI_NO;
            T000_READ_CITPDM();
            if (pgmRtn) return;
            WS_SEX = CIRPDM.SEX;
            WS_OCCUP = CIRPDM.OCCUP;
            CEP.TRC(SCCGWA, WS_SEX);
            CEP.TRC(SCCGWA, WS_OCCUP);
            CEP.TRC(SCCGWA, WS_EP_FLG);
        } else if (WS_CI_TYP == '2') {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICSGRS.DATA.CI_NO;
            T000_READ_CITCDM();
            if (pgmRtn) return;
            WS_INDUS1 = CIRCDM.INDUS1;
            WS_SIZE = CIRCDM.COMP_SIZE;
            CEP.TRC(SCCGWA, WS_INDUS1);
            CEP.TRC(SCCGWA, WS_SIZE);
        } else if (WS_CI_TYP == '3') {
            IBS.init(SCCGWA, CIRFDM);
            CIRFDM.KEY.CI_NO = CICSGRS.DATA.CI_NO;
            T000_READ_CITFDM();
            if (pgmRtn) return;
            WS_INDUS1 = CIRFDM.INDUS1;
            WS_SIZE = CIRFDM.COMP_SIZE;
            CEP.TRC(SCCGWA, WS_INDUS1);
            CEP.TRC(SCCGWA, WS_SIZE);
        } else {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
        }
        CEP.TRC(SCCGWA, CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO);
        if (CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO.trim().length() > 0) {
            WS_GRS_NO_FLG = 'I';
        } else {
            WS_GRS_NO_FLG = 'N';
        }
    }
    public void B020_INQUIRE_SOME_GRS_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO.trim().length() != 0 
            && WS_I <= 99; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO);
            IBS.init(SCCGWA, CIRCGRP);
            CIRCGRP.KEY.GRPS_NO = CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO;
            T000_READ_CITCGRP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (CIRCGRP.FLG == '0') {
                    IBS.init(SCCGWA, CIRGRPM);
                    CIRGRPM.KEY.ENTY_NO = CICSGRS.DATA.CI_NO;
                    CIRGRPM.KEY.ENTY_TYP = '0';
                    CIRGRPM.KEY.GRPS_NO = CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO;
                    T000_READ_CITGRPM();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_NO = CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO;
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_NM = CIRCGRP.NAME;
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_TYPE = CIRCGRP.TYPE;
                        CICSGRS.OUT_DATA[WS_I-1].BBR = CIRCGRP.BR;
                        CICSGRS.OUT_DATA[WS_I-1].EFF_DT = CIRGRPM.KEY.EFF_DT;
                        CICSGRS.OUT_DATA[WS_I-1].EXP_DT = CIRGRPM.KEY.EXP_DT;
                        CEP.TRC(SCCGWA, CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO);
                    }
                } else {
                    CEP.TRC(SCCGWA, "GROUP IS DYNAMIC");
                    R000_TRANS_MATCH_DATA();
                    if (pgmRtn) return;
                    R000_CHECK_DYN_GROUP();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_DYN_GRS_FLG);
                    if (WS_DYN_GRS_FLG == 'Y') {
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_NO = CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO;
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_NM = CIRCGRP.NAME;
                        CICSGRS.OUT_DATA[WS_I-1].GRPS_TYPE = CIRCGRP.TYPE;
                        CICSGRS.OUT_DATA[WS_I-1].BBR = CIRCGRP.BR;
                        CICSGRS.OUT_DATA[WS_I-1].EFF_DT = CIRCGRP.EFF_DATE;
                        CICSGRS.OUT_DATA[WS_I-1].EXP_DT = CIRCGRP.EXP_DATE;
                        CEP.TRC(SCCGWA, CICSGRS.DATA.MULTI_DATA[WS_I-1].GRS_NO);
                    }
                }
            }
        }
    }
    public void B030_BROWSE_ALL_GRS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        CIRGRPM.KEY.ENTY_NO = CICSGRS.DATA.CI_NO;
        CIRGRPM.KEY.ENTY_TYP = '0';
        T000_STARTBR_CITGRPM_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITGRPM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GRPM INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_NOTFND, CICSGRS.RC);
        }
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 99; WS_I += 1) {
            IBS.init(SCCGWA, CIRCGRP);
            CIRCGRP.KEY.GRPS_NO = CIRGRPM.KEY.GRPS_NO;
            T000_READ_CITCGRP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
            CICSGRS.OUT_DATA[WS_I-1].GRPS_NO = CIRCGRP.KEY.GRPS_NO;
            CICSGRS.OUT_DATA[WS_I-1].GRPS_NM = CIRCGRP.NAME;
            CICSGRS.OUT_DATA[WS_I-1].GRPS_TYPE = CIRCGRP.TYPE;
            CICSGRS.OUT_DATA[WS_I-1].BBR = CIRCGRP.BR;
            CICSGRS.OUT_DATA[WS_I-1].EFF_DT = CIRGRPM.KEY.EFF_DT;
            CICSGRS.OUT_DATA[WS_I-1].EXP_DT = CIRGRPM.KEY.EXP_DT;
            CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[WS_I-1].GRPS_NO);
            CEP.TRC(SCCGWA, WS_I);
            T000_READNEXT_CITGRPM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_III = WS_I;
            }
        }
        T000_ENDBR_CITGRPM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCGRP);
        T000_STARTBR_CITCGRP_DYN();
        if (pgmRtn) return;
        T000_READNEXT_CITCGRP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_III < 99) {
            CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
            R000_TRANS_MATCH_DATA();
            if (pgmRtn) return;
            R000_CHECK_DYN_GROUP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DYN_GRS_FLG);
            if (WS_DYN_GRS_FLG == 'Y') {
                WS_III = (short) (WS_III + 1);
                CEP.TRC(SCCGWA, WS_III);
                CICSGRS.OUT_DATA[WS_III-1].GRPS_NO = CIRCGRP.KEY.GRPS_NO;
                CICSGRS.OUT_DATA[WS_III-1].GRPS_NM = CIRCGRP.NAME;
                CICSGRS.OUT_DATA[WS_III-1].GRPS_TYPE = CIRCGRP.TYPE;
                CICSGRS.OUT_DATA[WS_III-1].BBR = CIRCGRP.BR;
                CICSGRS.OUT_DATA[WS_III-1].EFF_DT = CIRCGRP.EFF_DATE;
                CICSGRS.OUT_DATA[WS_III-1].EXP_DT = CIRCGRP.EXP_DATE;
                CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[WS_III-1].GRPS_NO);
                CEP.TRC(SCCGWA, WS_III);
                IBS.init(SCCGWA, CICSGRS.RC);
            }
            T000_READNEXT_CITCGRP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCGRP();
        if (pgmRtn) return;
    }
    public void R000_CHECK_DYN_GROUP() throws IOException,SQLException,Exception {
        WS_DYN_GRS_FLG = 'Y';
        for (WS_II = 1; WS_II <= 5; WS_II += 1) {
            CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_TYPE);
            if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("01")) {
                CEP.TRC(SCCGWA, WS_SVR_LVL);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (!WS_SVR_LVL.equalsIgnoreCase(WS_MATCH[WS_II-1].WS_MATCH_DATA)) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("02")) {
                CEP.TRC(SCCGWA, WS_SEX);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (WS_SEX != WS_MATCH[WS_II-1].WS_MATCH_DATA) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("03")) {
                CEP.TRC(SCCGWA, WS_OCCUP);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (!WS_OCCUP.equalsIgnoreCase(WS_MATCH[WS_II-1].WS_MATCH_DATA)) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("04")) {
                CEP.TRC(SCCGWA, WS_INDUS1);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (!WS_INDUS1.equalsIgnoreCase(WS_MATCH[WS_II-1].WS_MATCH_DATA)) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("05")) {
                CEP.TRC(SCCGWA, WS_SIZE);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (WS_SIZE != WS_MATCH[WS_II-1].WS_MATCH_DATA) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("06")) {
                CEP.TRC(SCCGWA, WS_EP_FLG);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (WS_EP_FLG != WS_MATCH[WS_II-1].WS_MATCH_DATA) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else if (WS_MATCH[WS_II-1].WS_MATCH_TYPE.equalsIgnoreCase("07")) {
                CEP.TRC(SCCGWA, WS_CD_EMP);
                CEP.TRC(SCCGWA, WS_MATCH[WS_II-1].WS_MATCH_DATA);
                if (WS_CD_EMP != WS_MATCH[WS_II-1].WS_MATCH_DATA) {
                    CEP.TRC(SCCGWA, "NOT MATCH");
                    WS_DYN_GRS_FLG = 'N';
                }
            } else {
            }
        }
    }
    public void R000_TRANS_MATCH_DATA() throws IOException,SQLException,Exception {
        WS_MATCH[1-1].WS_MATCH_TYPE = CIRCGRP.MACTH_TYPE1;
        WS_MATCH[2-1].WS_MATCH_TYPE = CIRCGRP.MACTH_TYPE2;
        WS_MATCH[3-1].WS_MATCH_TYPE = CIRCGRP.MACTH_TYPE3;
        WS_MATCH[4-1].WS_MATCH_TYPE = CIRCGRP.MACTH_TYPE4;
        WS_MATCH[5-1].WS_MATCH_TYPE = CIRCGRP.MACTH_TYPE5;
        WS_MATCH[1-1].WS_MATCH_KEY = CIRCGRP.MACTH_KEY1;
        WS_MATCH[2-1].WS_MATCH_KEY = CIRCGRP.MACTH_KEY2;
        WS_MATCH[3-1].WS_MATCH_KEY = CIRCGRP.MACTH_KEY3;
        WS_MATCH[4-1].WS_MATCH_KEY = CIRCGRP.MACTH_KEY4;
        WS_MATCH[5-1].WS_MATCH_KEY = CIRCGRP.MACTH_KEY5;
        WS_MATCH[1-1].WS_MATCH_DATA = CIRCGRP.MACTH_DATA1;
        WS_MATCH[2-1].WS_MATCH_DATA = CIRCGRP.MACTH_DATA2;
        WS_MATCH[3-1].WS_MATCH_DATA = CIRCGRP.MACTH_DATA3;
        WS_MATCH[4-1].WS_MATCH_DATA = CIRCGRP.MACTH_DATA4;
        WS_MATCH[5-1].WS_MATCH_DATA = CIRCGRP.MACTH_DATA5;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.eqWhere = "ENTY_TYP , ENTY_NO ,GRPS_NO";
        CITGRPM_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.READ(SCCGWA, CIRGRPM, this, CITGRPM_RD);
    }
    public void T000_READ_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
    }
    public void T000_STARTBR_CITGRPM_CI() throws IOException,SQLException,Exception {
        CITGRPM_BR.rp = new DBParm();
        CITGRPM_BR.rp.TableName = "CITGRPM";
        CITGRPM_BR.rp.eqWhere = "ENTY_TYP , ENTY_NO";
        CITGRPM_BR.rp.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        IBS.STARTBR(SCCGWA, CIRGRPM, this, CITGRPM_BR);
    }
    public void T000_READNEXT_CITGRPM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRGRPM, this, CITGRPM_BR);
    }
    public void T000_ENDBR_CITGRPM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITGRPM_BR);
    }
    public void T000_STARTBR_CITCGRP_DYN() throws IOException,SQLException,Exception {
        CITCGRP_BR.rp = new DBParm();
        CITCGRP_BR.rp.TableName = "CITCGRP";
        CITCGRP_BR.rp.where = "( EXP_DATE = 0 "
            + "OR EXP_DATE > :WS_AC_DATE ) "
            + "AND FLG = '1'";
        IBS.STARTBR(SCCGWA, CIRCGRP, this, CITCGRP_BR);
    }
    public void T000_READNEXT_CITCGRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCGRP, this, CITCGRP_BR);
    }
    public void T000_ENDBR_CITCGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCGRP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
