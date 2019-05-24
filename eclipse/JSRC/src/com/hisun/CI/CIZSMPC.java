package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMPC {
    int JIBS_tmp_int;
    int NAM_CI_NM_LEN;
    int BAS_CI_NM_LEN;
    int BAS_TAX_BANK_LEN;
    int PDM_CORP_NM_LEN;
    int PDM_REMARK_LEN;
    String JIBS_tmp_str[] = new String[10];
    brParm CITRELT_BR = new brParm();
    brParm CITRREL_BR = new brParm();
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITID_RD;
    DBParm DUPKEY_RD;
    DBParm CITNAM_RD;
    DBParm CITRISK_RD;
    DBParm CITRELT_RD;
    DBParm CITCRS_RD;
    brParm CITCRS_BR = new brParm();
    brParm CITRELN_BR = new brParm();
    DBParm CITRELN_RD;
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_NM_TP = " ";
    String WS_CI_NM = " ";
    String WS_MSG_INFO = " ";
    char WS_FATCA_FLG = ' ';
    char WS_EADR_FLG = ' ';
    char WS_BADR_FLG = ' ';
    String WS_DEL_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRPDM CIRPDMO = new CIRPDM();
    CIRPDM CIRPDMN = new CIRPDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRID CIRIDO = new CIRID();
    CIRID CIRIDN = new CIRID();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    CIRRELN CIRRELN = new CIRRELN();
    CICCMCK CICCMCK = new CICCMCK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICCGHIS CICCGHIS = new CICCGHIS();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMPC CICSMPC;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, CICSMPC CICSMPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMPC = CICSMPC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMPC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        IBS.init(SCCGWA, CICSMPC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B030_WRITE_RISK_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
        B110_WRITE_AGENT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMPC.DATA.CI_TYP);
        if (CICSMPC.DATA.CI_TYP != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSMPC.DATA.CI_ATTR);
        if (CICSMPC.DATA.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSMPC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSMPC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSMPC.DATA.CI_NM);
        if (CICSMPC.DATA.ID_TYPE.trim().length() == 0 
            || CICSMPC.DATA.ID_NO.trim().length() == 0 
            || CICSMPC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        R000_MOD_CHECK_INFO();
        if (pgmRtn) return;
        R000_CHECK_IDNM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMPC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSMPC.DATA.SEX);
        CEP.TRC(SCCGWA, CICSMPC.DATA.BIRTH_DT);
        CEP.TRC(SCCGWA, CICSMPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CICSMPC.DATA.NATION);
        CEP.TRC(SCCGWA, CICSMPC.DATA.VER_FLG);
        CEP.TRC(SCCGWA, CICSMPC.DATA.SID_FLG);
        CEP.TRC(SCCGWA, CICSMPC.DATA.PER_INC);
        CEP.TRC(SCCGWA, CICSMPC.DATA.OCCUP);
        if (CICSMPC.DATA.RESIDENT == ' ' 
            || CICSMPC.DATA.SEX == ' ' 
            || CICSMPC.DATA.BIRTH_DT == 0 
            || CICSMPC.DATA.REG_CNTY.trim().length() == 0 
            || CICSMPC.DATA.NATION.trim().length() == 0 
            || CICSMPC.DATA.VER_FLG == ' ' 
            || CICSMPC.DATA.SID_FLG == ' ' 
            || CICSMPC.DATA.OCCUP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        B011_MOD_CHECK_INFO();
        if (pgmRtn) return;
        B012_MOD_CHECK_INFO();
        if (pgmRtn) return;
        if (CICSMPC.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSMPC.DATA.ID_TYPE == null) CICSMPC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSMPC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSMPC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSMPC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B011_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        if (CICSMPC.DATA.RESIDENT != '1') {
            if (CICSMPC.DATA.CI_FNM.trim().length() == 0 
                || CICSMPC.DATA.CI_SNM.trim().length() == 0) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "非居民客户，" + "英文名和英文姓必�?";
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
            }
        }
    }
    public void B012_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        if (CICSMPC.DATA.HKC_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港客户分类必须输入");
        }
    }
    public void B020_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASO);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = CICSMPC.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (!CICSMPC.DATA.ID_TYPE.equalsIgnoreCase(CIRBAS.ID_TYPE) 
            || !CICSMPC.DATA.ID_NO.equalsIgnoreCase(CIRBAS.ID_NO)) {
            IBS.init(SCCGWA, CIRID);
            IBS.init(SCCGWA, CIRIDO);
            IBS.init(SCCGWA, CIRIDN);
            CIRID.KEY.CI_NO = CICSMPC.DATA.CI_NO;
            T000_READ_CITID_UPD_FOR_OPENID();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CLONE(SCCGWA, CIRID, CIRIDO);
                CIRID.KEY.ID_TYPE = CICSMPC.DATA.ID_TYPE;
                CIRID.ID_NO = CICSMPC.DATA.ID_NO;
                CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITID();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRID, CIRIDN);
                IBS.init(SCCGWA, CICCGHIS);
                CICCGHIS.DATA.TABLE_NM = "CITID";
                CICCGHIS.DATA.OLD_DATA_LEN = 374;
                CICCGHIS.DATA.NEW_DATA_LEN = 374;
                CICCGHIS.DATA.OLD_DATA_PTR = CIRIDO;
                CICCGHIS.DATA.NEW_DATA_PTR = CIRIDN;
                S000_CALL_CIZCGHIS();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "ID INF NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NOTFND);
            }
        }
        if (!CICSMPC.DATA.CI_NM.equalsIgnoreCase(CIRBAS.CI_NM)) {
            IBS.init(SCCGWA, CIRNAM);
            CIRNAM.KEY.CI_NO = CICSMPC.DATA.CI_NO;
            T000_READ_CITNAM_UPD_FOR_OPENNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CIRNAM.CI_NM = CICSMPC.DATA.CI_NM;
                NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITNAM();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "NAM INF NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NAM_NOTFND);
            }
        }
        if (CICSMPC.DATA.SEX == ' ' 
            || CICSMPC.DATA.REG_CNTY.trim().length() == 0 
            || CICSMPC.DATA.OCCUP.trim().length() == 0) {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        } else {
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(24 - 1, 24 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICCMCK);
                CICCMCK.DATA.CI_NO = CICSMPC.DATA.CI_NO;
                CICCMCK.DATA.CI_TYP = '1';
                CICCMCK.DATA.PDM_CHK = 'Y';
                CICCMCK.DATA.TABLE_FLG = 'N';
                S000_CALL_CIZCMCK();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICCMCK.DATA.COM_CHK);
                if (CICCMCK.DATA.COM_CHK == 'Y') {
                    if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                    JIBS_tmp_int = CIRBAS.STSW.length();
                    for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                    CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "0" + CIRBAS.STSW.substring(24 + 1 - 1);
                }
            }
        }
        if (CICSMPC.DATA.VER_FLG == 'T') {
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            CIRBAS.VER_STSW = "01" + CIRBAS.VER_STSW.substring(2);
        } else {
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            CIRBAS.VER_STSW = "10" + CIRBAS.VER_STSW.substring(2);
        }
        CEP.TRC(SCCGWA, CICSMPC.DATA.PL_FLG);
        CEP.TRC(SCCGWA, CICSMPC.DATA.PEA_FLG);
        CEP.TRC(SCCGWA, CICSMPC.DATA.CD_EMP);
        CEP.TRC(SCCGWA, CICSMPC.DATA.BK_EMP);
        CEP.TRC(SCCGWA, CICSMPC.DATA.CDG_EMP);
        if (CICSMPC.DATA.BK_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = "1" + CIRBAS.IDE_STSW.substring(1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = "0" + CIRBAS.IDE_STSW.substring(1);
        }
        if (CICSMPC.DATA.CDG_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "1" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "0" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        }
        if (CICSMPC.DATA.PL_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "1" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "0" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        }
        if (CICSMPC.DATA.PEA_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "1" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "0" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        }
        if (CICSMPC.DATA.CD_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 6 - 1) + "1" + CIRBAS.IDE_STSW.substring(6 + 1 - 1);
        } else {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 6 - 1) + "0" + CIRBAS.IDE_STSW.substring(6 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_ID_TYPE = CICSMPC.DATA.ID_TYPE;
        CIRRELT.RELT_ID_NO = CICSMPC.DATA.ID_NO;
        T000_STARTBR_CITRELT_BY_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITRELT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRELT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRREL);
        CIRRREL.REL_ID_TYPE = CICSMPC.DATA.ID_TYPE;
        CIRRREL.REL_ID_NO = CICSMPC.DATA.ID_NO;
        T000_STARTBR_CITRREL_BY_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRRELT);
            CIRRELT.KEY.RELT_NO = CIRRREL.RELT_NO;
            T000_READ_CITRELT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRRELT.RELT_TYPE);
            if (CIRRELT.RELT_TYPE.equalsIgnoreCase("01")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 11 - 1) + "1" + CIRBAS.IDE_STSW.substring(11 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("02")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 12 - 1) + "1" + CIRBAS.IDE_STSW.substring(12 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("03")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 13 - 1) + "1" + CIRBAS.IDE_STSW.substring(13 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("04")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 14 - 1) + "1" + CIRBAS.IDE_STSW.substring(14 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("05")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 15 - 1) + "1" + CIRBAS.IDE_STSW.substring(15 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("06")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 16 - 1) + "1" + CIRBAS.IDE_STSW.substring(16 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("07")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 17 - 1) + "1" + CIRBAS.IDE_STSW.substring(17 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("08")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 18 - 1) + "1" + CIRBAS.IDE_STSW.substring(18 + 1 - 1);
            } else if (CIRRELT.RELT_TYPE.equalsIgnoreCase("09")) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 19 - 1) + "1" + CIRBAS.IDE_STSW.substring(19 + 1 - 1);
            } else {
            }
            T000_READNEXT_CITRREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRREL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        CIRBAS.ORGIN_TP = CICSMPC.DATA.ORGIN_TP;
        CIRBAS.ORIGIN = CICSMPC.DATA.ORGIN1;
        CIRBAS.ORIGIN2 = CICSMPC.DATA.ORGIN2;
        CIRBAS.CI_NM = CICSMPC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSMPC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSMPC.DATA.ID_NO;
        CIRBAS.TAX_BANK = CICSMPC.DATA.TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICSMPC.DATA.TAX_ACNO;
        CIRBAS.TAX_TYPE = CICSMPC.DATA.TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICSMPC.DATA.TAX_DSNO;
        CIRBAS.NRA_TAX_TYP = CICSMPC.DATA.NRA_TAX;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.SEARCH_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITBAS";
        CICCGHIS.DATA.OLD_DATA_LEN = 568;
        CICCGHIS.DATA.NEW_DATA_LEN = 568;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRBASO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRBASN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRPDM);
        IBS.init(SCCGWA, CIRPDMO);
        IBS.init(SCCGWA, CIRPDMN);
        CIRPDM.KEY.CI_NO = CICSMPC.DATA.CI_NO;
        T000_READ_CITPDM_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRPDM, CIRPDMO);
        if (!CIRPDM.REG_CNTY.equalsIgnoreCase(CICSMPC.DATA.REG_CNTY) 
            && CICSMPC.DATA.REG_CNTY.equalsIgnoreCase("USA")) {
            WS_FATCA_FLG = 'Y';
        }
        B021_FATCA_CHK_INFO();
        if (pgmRtn) return;
        B022_CRS_CHK_INFO();
        if (pgmRtn) return;
        CIRPDM.RESIDENT = CICSMPC.DATA.RESIDENT;
        CIRPDM.CI_SUB_TYP = CICSMPC.DATA.SUB_TYP;
        CIRPDM.SEX = CICSMPC.DATA.SEX;
        CIRPDM.MARI = CICSMPC.DATA.MARI;
        CIRPDM.BIRTH_DT = CICSMPC.DATA.BIRTH_DT;
        CIRPDM.NATION = CICSMPC.DATA.NATION;
        CIRPDM.REG_CNTY = CICSMPC.DATA.REG_CNTY;
        CIRPDM.OCCUP = CICSMPC.DATA.OCCUP;
        CIRPDM.SID_FLG = CICSMPC.DATA.SID_FLG;
        CIRPDM.BIRTH_RGN = CICSMPC.DATA.BIRTH_RG;
        CIRPDM.EDU_LVL = CICSMPC.DATA.EDU_LVL;
        CIRPDM.WK_TITL = CICSMPC.DATA.WK_TITL;
        CIRPDM.PER_INC = CICSMPC.DATA.PER_INC;
        CIRPDM.FAM_INC = CICSMPC.DATA.FAM_INC;
        CIRPDM.CORP_INDUS = CICSMPC.DATA.CORP_IND;
        CIRPDM.CORP_TYP = CICSMPC.DATA.CORP_TYP;
        CIRPDM.CORP_NM = CICSMPC.DATA.CORP_NM;
        PDM_CORP_NM_LEN = CIRPDM.CORP_NM.length();
        CIRPDM.REMARK = CICSMPC.DATA.REMARK;
        PDM_REMARK_LEN = CIRPDM.REMARK.length();
        CIRPDM.HK_CI_TYP = CICSMPC.DATA.HKC_TYP;
        CIRPDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRPDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRPDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITPDM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRPDM, CIRPDMN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITPDM";
        CICCGHIS.DATA.OLD_DATA_LEN = 179;
        CICCGHIS.DATA.NEW_DATA_LEN = 179;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRPDMO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRPDMN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            WS_CI_NM = " ";
            WS_NM_TP = " ";
            if (WS_I == 1) {
                if (CICSMPC.DATA.CI_ENM.trim().length() > 0) {
                    WS_NM_TP = "03";
                    WS_CI_NM = CICSMPC.DATA.CI_ENM;
                }
            } else if (WS_I == 2) {
                if (CICSMPC.DATA.CI_FNM.trim().length() > 0) {
                    WS_NM_TP = "05";
                    WS_CI_NM = CICSMPC.DATA.CI_FNM;
                }
            } else if (WS_I == 3) {
                if (CICSMPC.DATA.CI_SNM.trim().length() > 0) {
                    WS_NM_TP = "06";
                    WS_CI_NM = CICSMPC.DATA.CI_SNM;
                }
            } else if (WS_I == 4) {
                if (CICSMPC.DATA.CI_OFNM.trim().length() > 0) {
                    WS_NM_TP = "07";
                    WS_CI_NM = CICSMPC.DATA.CI_OFNM;
                }
            } else if (WS_I == 5) {
                if (CICSMPC.DATA.CI_OSNM.trim().length() > 0) {
                    WS_NM_TP = "08";
                    WS_CI_NM = CICSMPC.DATA.CI_OSNM;
                }
            } else {
                if (CICSMPC.DATA.CI_ONM.trim().length() > 0) {
                    WS_NM_TP = "09";
                    WS_CI_NM = CICSMPC.DATA.CI_ONM;
                }
            }
            if (WS_CI_NM.trim().length() > 0) {
                IBS.init(SCCGWA, CIRNAM);
                CIRNAM.KEY.CI_NO = CICSMPC.DATA.CI_NO;
                CIRNAM.KEY.NM_TYPE = WS_NM_TP;
                T000_READ_CITNAM_UPD();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CIRNAM.CI_NM = WS_CI_NM;
                    NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                    CIRNAM.OPEN = 'N';
                    CIRNAM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRNAM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRNAM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    T000_WRITE_CITNAM();
                    if (pgmRtn) return;
                } else {
                    CIRNAM.CI_NM = WS_CI_NM;
                    NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                    CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    T000_REWRITE_CITNAM();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B021_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CICSMPC.DATA.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        if (WS_FATCA_FLG == 'Y') {
            if (CIRRISK.FATCA_TP.equalsIgnoreCase("NA00")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_USA_EST_RFIL);
            }
            if (CIRRISK.FATCA_TP.equalsIgnoreCase("W801") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W802") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W803") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W804") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W805") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W806") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W807") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W808") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W809") 
                || CIRRISK.FATCA_TP.equalsIgnoreCase("W810") 
                || CIRRISK.FATCA_TP.trim().length() == 0) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "FATCA状�?�为" + CIRRISK.FATCA_TP;
                CEP.TRC(SCCGWA, CIRRISK.FATCA_TP);
                CEP.TRC(SCCGWA, WS_MSG_INFO);
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_USA_FAT_INCUT, WS_MSG_INFO);
            }
            if (CIRRISK.FATCA_TP.equalsIgnoreCase("W800")) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "美国居民FATCA状�?�不可为" + CIRRISK.FATCA_TP;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, WS_MSG_INFO);
            }
        }
        CEP.TRC(SCCGWA, CIRPDM.REG_CNTY);
        CEP.TRC(SCCGWA, CICSMPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CIRRISK.FATCA_TP);
        CEP.TRC(SCCGWA, CICSMPC.DATA.FATCA_TP);
        if (CIRPDM.REG_CNTY.equalsIgnoreCase("USA") 
            && !CICSMPC.DATA.REG_CNTY.equalsIgnoreCase("USA") 
            && CIRRISK.FATCA_TP.equalsIgnoreCase("W900") 
            && CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W800")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_USA_NAT_LOS_CTF);
        }
        if (CIRPDM.REG_CNTY.equalsIgnoreCase(CICSMPC.DATA.REG_CNTY) 
            && (!CIRRISK.FATCA_TP.equalsIgnoreCase("RC00") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("RC01") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("NA00") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("PD00") 
            && CIRRISK.FATCA_TP.trim().length() > 0) 
            && (CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("RC00") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("RC01") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("NA00") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("PD00"))) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_RNP);
        }
        if (CIRPDM.REG_CNTY.equalsIgnoreCase(CICSMPC.DATA.REG_CNTY) 
            && (!CIRRISK.FATCA_TP.equalsIgnoreCase("W801") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W802") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W803") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W804") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W805") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W806") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W807") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W808") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W809") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W810") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("W901") 
            && !CIRRISK.FATCA_TP.equalsIgnoreCase("AD00")) 
            && (CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W801") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W802") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W803") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W804") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W805") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W806") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W807") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W808") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W809") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W810") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W901") 
            || CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("AD00"))) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "个人客户FATCA状�?�不可输�?" + CICSMPC.DATA.FATCA_TP;
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, WS_MSG_INFO);
        }
        if (CIRPDM.REG_CNTY.equalsIgnoreCase(CICSMPC.DATA.REG_CNTY) 
            && CIRPDM.REG_CNTY.equalsIgnoreCase("USA") 
            && (!CIRRISK.FATCA_TP.equalsIgnoreCase("W800") 
            && CICSMPC.DATA.FATCA_TP.equalsIgnoreCase("W800"))) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, "国籍为美�?, FATCA状�?�不可为W800");
        }
    }
    public void B022_CRS_CHK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMPC.DATA.CRS_TYPE);
        CEP.TRC(SCCGWA, CIRRISK.CRS_TYPE);
        CEP.TRC(SCCGWA, CICSMPC.DATA.CRS_DESC);
        CEP.TRC(SCCGWA, CIRRISK.PROOF_DT);
        CEP.TRC(SCCGWA, CICSMPC.DATA.PROOF_DT);
        CEP.TRC(SCCGWA, CIRRISK.PROOF_CHNL);
        CEP.TRC(SCCGWA, CICSMPC.DATA.PROOF_CH);
        if (CICSMPC.DATA.CRS_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "CRS状�?�必�?");
        }
        if (!CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("P1") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("EX") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("UD") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("N2") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("F1") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("F2") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("F3") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("LC") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("GV") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("IO") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("AN") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("PN") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("ID")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_CRS_TP);
        }
        if ((!CIRRISK.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CIRRISK.CRS_TYPE.equalsIgnoreCase("P1")) 
            && (CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            || CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("P1"))) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "CRS状�?�为" + CICSMPC.DATA.CRS_TYPE;
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_TP_EMPW, WS_MSG_INFO);
        }
        if (CIRRISK.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSMPC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && CICSMPC.DATA.CRS_DESC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_PLS_CRS_DESC_INPUT);
        }
        if (CIRRISK.PROOF_DT != CICSMPC.DATA.PROOF_DT) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.MOD_PROOFD_EMPW);
        }
        if (CIRRISK.PROOF_CHNL != 'B' 
            && CICSMPC.DATA.PROOF_CH == 'B') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.MOD_PROOF_CH_EMPW);
        }
    }
    public void B030_WRITE_RISK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CICSMPC.DATA.CI_NO;
        T000_READ_CITRISK_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CIRRISK.FATCA_TP = CICSMPC.DATA.FATCA_TP;
            CIRRISK.W8W9_DT = CICSMPC.DATA.W8W9_DT;
            CIRRISK.TIN_NO = CICSMPC.DATA.TIN_NO;
            CIRRISK.GIIN_CODE = CICSMPC.DATA.GIIN_CD;
            CIRRISK.CRS_TYPE = CICSMPC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSMPC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSMPC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSMPC.DATA.PROOF_CH;
            CIRRISK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRRISK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITRISK();
            if (pgmRtn) return;
        } else {
            CIRRISK.FATCA_TP = CICSMPC.DATA.FATCA_TP;
            CIRRISK.W8W9_DT = CICSMPC.DATA.W8W9_DT;
            CIRRISK.TIN_NO = CICSMPC.DATA.TIN_NO;
            CIRRISK.GIIN_CODE = CICSMPC.DATA.GIIN_CD;
            CIRRISK.CRS_TYPE = CICSMPC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSMPC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSMPC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSMPC.DATA.PROOF_CH;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITRISK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRCRS);
        CIRCRS.KEY.CI_NO = CICSMPC.DATA.CI_NO;
        T000_STARTBR_CITCRS();
        if (pgmRtn) return;
        T000_READNEXT_CITCRS();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            T000_DELETE_CITCRS();
            if (pgmRtn) return;
            T000_READNEXT_CITCRS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCRS();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            if (CICSMPC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() > 0) {
                IBS.init(SCCGWA, CIRCRS);
                CIRCRS.KEY.CI_NO = CICSMPC.DATA.CI_NO;
                CIRCRS.KEY.INFO_TYP = '1';
                CIRCRS.KEY.TAX_ADR = CICSMPC.DATA.CRS_AREA[WS_I-1].CRS_ADR;
                CIRCRS.TAX_DIST_NO = CICSMPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO;
                CIRCRS.NO_DIST_CODE = CICSMPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD;
                CIRCRS.NO_DIST_REASON = CICSMPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE;
                CIRCRS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRCRS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_WRITE_CITCRS();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CICSMPC.DATA.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B110_WRITE_AGENT_INFO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            IBS.init(SCCGWA, CICSAGEN);
            CICSAGEN.FUNC = 'A';
            CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
            CICSAGEN.CI_NO = CICSMPC.DATA.CI_NO;
            CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICSAGEN.AGENT_TP = "99";
            CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
            CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
            S000_CALL_CIZSAGEN();
            if (pgmRtn) return;
        }
    }
    public void R000_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMPC.DATA.ORGIN_TP);
        CEP.TRC(SCCGWA, CICSMPC.DATA.ORGIN1);
        if (CICSMPC.DATA.ORGIN_TP == '8' 
            && CICSMPC.DATA.ORGIN1.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSMPC.DATA.ORGIN1;
            T000_READ_CITBAS_BY_CINO();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSMPC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSMPC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSMPC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRBAS.CI_ATTR == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IDNM_EXIST_ATTR0);
            } else if ((CIRBAS.CI_ATTR == '1' 
                    || CIRBAS.CI_ATTR == '3' 
                    || CIRBAS.CI_ATTR == '5' 
                    || CIRBAS.CI_ATTR == '6')) {
                if (!CICSMPC.DATA.CI_NO.equalsIgnoreCase(CIRBAS.KEY.CI_NO)) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_EXIST);
                }
            } else if ((CIRBAS.CI_ATTR == '2' 
                    || CIRBAS.CI_ATTR == '4')) {
                WS_DEL_CI_NO = CIRBAS.KEY.CI_NO;
                R000_DELETE_CUST_ATTR2OR4();
                if (pgmRtn) return;
            } else {
            }
        }
    }
    public void R000_DELETE_CUST_ATTR2OR4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_DEL_CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "001" + CIRBAS.STSW.substring(3);
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.RCI_NO = WS_DEL_CI_NO;
        T000_STARTBR_CITRELN_BY_RCI_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITRELN();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRRELN.RCI_NO = CICSMPC.DATA.CI_NO;
            T000_REWRITE_CITRELN();
            if (pgmRtn) return;
            T000_READNEXT_CITRELN();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITRELN();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCMCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-COM-CHK", CICCMCK);
        if (CICCMCK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCMCK.RC);
        }
    }
    public void S000_CALL_CIZCGHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-HISTORY", CICCGHIS);
        if (CICCGHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCGHIS.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CFM_ID_TYPE);
        }
    }
    public void T000_STARTBR_CITRELT_BY_ID() throws IOException,SQLException,Exception {
        CITRELT_BR.rp = new DBParm();
        CITRELT_BR.rp.TableName = "CITRELT";
        CITRELT_BR.rp.eqWhere = "RELT_ID_TYPE,RELT_ID_NO";
        IBS.STARTBR(SCCGWA, CIRRELT, CITRELT_BR);
    }
    public void T000_READNEXT_CITRELT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRELT, this, CITRELT_BR);
    }
    public void T000_ENDBR_CITRELT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRELT_BR);
    }
    public void T000_STARTBR_CITRREL_BY_ID() throws IOException,SQLException,Exception {
        CITRREL_BR.rp = new DBParm();
        CITRREL_BR.rp.TableName = "CITRREL";
        CITRREL_BR.rp.eqWhere = "REL_ID_TYPE,REL_ID_NO";
        IBS.STARTBR(SCCGWA, CIRRREL, CITRREL_BR);
    }
    public void T000_READNEXT_CITRREL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRREL, this, CITRREL_BR);
    }
    public void T000_ENDBR_CITRREL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRREL_BR);
    }
    public void T000_READ_CITBAS_BY_CINO() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        CEP.TRC(SCCGWA, CIRBAS.CI_ATTR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户信息不存�?");
        }
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITPDM_UPD() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        CITPDM_RD.upd = true;
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_REWRITE_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.REWRITE(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_READ_CITID_UPD_FOR_OPENID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.eqWhere = "CI_NO";
        CITID_RD.where = "OPEN = 'Y'";
        CITID_RD.upd = true;
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
    }
    public void T000_REWRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "证件类型录入重复");
        }
    }
    public void T000_READ_CITNAM_UPD() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.upd = true;
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITNAM_UPD_FOR_OPENNM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.eqWhere = "CI_NO";
        CITNAM_RD.where = "NM_TYPE = '01'";
        CITNAM_RD.upd = true;
        IBS.READ(SCCGWA, CIRNAM, this, CITNAM_RD);
    }
    public void T000_WRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.WRITE(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_REWRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.REWRITE(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_READ_CITRELT() throws IOException,SQLException,Exception {
        CITRELT_RD = new DBParm();
        CITRELT_RD.TableName = "CITRELT";
        IBS.READ(SCCGWA, CIRRELT, CITRELT_RD);
    }
    public void T000_READ_CITRISK_UPD() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        CITRISK_RD.upd = true;
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_WRITE_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.WRITE(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_REWRITE_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.REWRITE(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_WRITE_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_RD = new DBParm();
        CITCRS_RD.TableName = "CITCRS";
        CITCRS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRCRS, CITCRS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "CRS税务居住地录入重�?");
        }
    }
    public void T000_STARTBR_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_BR.rp = new DBParm();
        CITCRS_BR.rp.TableName = "CITCRS";
        CITCRS_BR.rp.eqWhere = "CI_NO";
        CITCRS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRCRS, CITCRS_BR);
    }
    public void T000_READNEXT_CITCRS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCRS, this, CITCRS_BR);
    }
    public void T000_ENDBR_CITCRS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCRS_BR);
    }
    public void T000_DELETE_CITCRS() throws IOException,SQLException,Exception {
        CITCRS_RD = new DBParm();
        CITCRS_RD.TableName = "CITCRS";
        IBS.DELETE(SCCGWA, CIRCRS, CITCRS_RD);
    }
    public void T000_STARTBR_CITRELN_BY_RCI_UPD() throws IOException,SQLException,Exception {
        CITRELN_BR.rp = new DBParm();
        CITRELN_BR.rp.TableName = "CITRELN";
        CITRELN_BR.rp.eqWhere = "RCI_NO";
        CITRELN_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRRELN, CITRELN_BR);
    }
    public void T000_READNEXT_CITRELN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRRELN, this, CITRELN_BR);
    }
    public void T000_REWRITE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        IBS.REWRITE(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_ENDBR_CITRELN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITRELN_BR);
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
