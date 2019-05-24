package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSOPC {
    String JIBS_tmp_str[] = new String[10];
    int BAS_CI_NM_LEN;
    DBParm BPTORGM_RD;
    int JIBS_tmp_int;
    int BAS_TAX_BANK_LEN;
    int PDM_CORP_NM_LEN;
    int PDM_REMARK_LEN;
    int NAM_CI_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITPDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITADR_RD;
    DBParm CITCNT_RD;
    DBParm CITRELN_RD;
    DBParm CITRISK_RD;
    DBParm CITCRS_RD;
    brParm CITCRS_BR = new brParm();
    brParm CITRELT_BR = new brParm();
    brParm CITRREL_BR = new brParm();
    DBParm CITRELT_RD;
    brParm CITGRLST_BR = new brParm();
    DBParm CITGRPM_RD;
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_J = 0;
    short WS_REP_NUM = 0;
    String WS_CI_NO = " ";
    String WS_RCI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_NM_TP = " ";
    String WS_CI_NM = " ";
    int WS_ID_EXP_DT = 0;
    String WS_CRS_ADR = " ";
    String WS_MSG_INFO = " ";
    char WS_CRS_FLG = ' ';
    char WS_FATCA_FLG = ' ';
    char WS_EADR_FLG = ' ';
    char WS_BADR_FLG = ' ';
    char WS_RADR_FLG = ' ';
    char WS_BAS_FOUND_FLG = ' ';
    char WS_OPEN_ID_CHK_FLG = ' ';
    char WS_ID_EXP_FLG = ' ';
    char WS_INFO_LACK_FLG = ' ';
    int WS_OPEN_BR = 0;
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRRELN CIRRELN = new CIRRELN();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    CICCINO CICCINO = new CICCINO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CIRGRLST CIRGRLST = new CIRGRLST();
    CIRGRPM CIRGRPM = new CIRGRPM();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSOPC CICSOPC;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, CICSOPC CICSOPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSOPC = CICSOPC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSOPC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        IBS.init(SCCGWA, CICSOPC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B040_WRITE_ID_INFO();
        if (pgmRtn) return;
        B050_WRITE_NAM_INFO();
        if (pgmRtn) return;
        B060_WRITE_ADR_INFO();
        if (pgmRtn) return;
        B070_WRITE_CNT_INFO();
        if (pgmRtn) return;
        B080_WRITE_REL_INFO();
        if (pgmRtn) return;
        B090_WRITE_RISK_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
        B110_WRITE_AGENT_INFO();
        if (pgmRtn) return;
        B120_OUTPUT_CI_NO();
        if (pgmRtn) return;
        B130_GRLST_IN_GRPM();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOPC.DATA.CI_TYP);
        if (CICSOPC.DATA.CI_TYP != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.CI_ATTR);
        if (CICSOPC.DATA.CI_ATTR != '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSOPC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CI_NM);
        if (CICSOPC.DATA.ID_TYPE.trim().length() == 0 
            || CICSOPC.DATA.ID_NO.trim().length() == 0 
            || CICSOPC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.RESIDENT);
        CEP.TRC(SCCGWA, CICSOPC.DATA.SEX);
        CEP.TRC(SCCGWA, CICSOPC.DATA.BIRTH_DT);
        CEP.TRC(SCCGWA, CICSOPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CICSOPC.DATA.NATION);
        CEP.TRC(SCCGWA, CICSOPC.DATA.VER_FLG);
        CEP.TRC(SCCGWA, CICSOPC.DATA.SID_FLG);
        CEP.TRC(SCCGWA, CICSOPC.DATA.PER_INC);
        CEP.TRC(SCCGWA, CICSOPC.DATA.OCCUP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.SUB_TYP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0029150")) {
            if (CICSOPC.DATA.RESIDENT == ' ' 
                || CICSOPC.DATA.SEX == ' ' 
                || CICSOPC.DATA.BIRTH_DT == 0 
                || CICSOPC.DATA.REG_CNTY.trim().length() == 0 
                || CICSOPC.DATA.NATION.trim().length() == 0 
                || CICSOPC.DATA.VER_FLG == ' ' 
                || CICSOPC.DATA.SID_FLG == ' ' 
                || CICSOPC.DATA.SUB_TYP.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
        } else {
            if (CICSOPC.DATA.RESIDENT == ' ' 
                || CICSOPC.DATA.SEX == ' ' 
                || CICSOPC.DATA.BIRTH_DT == 0 
                || CICSOPC.DATA.REG_CNTY.trim().length() == 0 
                || CICSOPC.DATA.NATION.trim().length() == 0 
                || CICSOPC.DATA.VER_FLG == ' ' 
                || CICSOPC.DATA.SID_FLG == ' ' 
                || CICSOPC.DATA.OCCUP.trim().length() == 0 
                || CICSOPC.DATA.SUB_TYP.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
        }
        WS_RADR_FLG = 'N';
        for (WS_I = 1; WS_I <= 4 
            && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_OPT != ' '; WS_I += 1) {
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("111")) {
                WS_RADR_FLG = 'Y';
            }
        }
        if (WS_RADR_FLG == 'N') {
            CEP.TRC(SCCGWA, "MUST INPUT ADR TYPE 111");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "个人联系地址必输");
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.ORGIN_TP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.ORGIN1);
        if (CICSOPC.DATA.ORGIN_TP == '8' 
            && CICSOPC.DATA.ORGIN1.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSOPC.DATA.ORGIN1;
            T000_READ_CITBAS_BY_CINO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSOPC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOPC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSOPC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FOUND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_TRT_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_TRT_VIL);
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_OPN_VIL);
            if (!WS_TRT_VIL.equalsIgnoreCase(WS_OPN_VIL)) {
                WS_BAS_FOUND_FLG = 'N';
            } else {
                if (CIRBAS.CI_ATTR == '1' 
                    || CIRBAS.CI_ATTR == '3' 
                    || CIRBAS.CI_ATTR == '5') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_EXIST);
                }
                WS_BAS_FOUND_FLG = 'Y';
                WS_CI_NO = CIRBAS.KEY.CI_NO;
            }
        }
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDTYP);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDNO);
            if (CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDTYP.equalsIgnoreCase(CICSOPC.DATA.ID_TYPE) 
                && CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDNO.equalsIgnoreCase(CICSOPC.DATA.ID_NO)) {
                WS_OPEN_ID_CHK_FLG = 'Y';
                CICSOPC.DATA.ID_AREA[WS_I-1].ID_OPEN = 'Y';
                CEP.TRC(SCCGWA, CICSOPC.DATA.ID_AREA[WS_I-1].ID_EXPDT);
                WS_ID_EXP_DT = CICSOPC.DATA.ID_AREA[WS_I-1].ID_EXPDT;
                if (CICSOPC.DATA.ID_AREA[WS_I-1].ID_EXPDT < SCCGWA.COMM_AREA.AC_DATE 
                    && CICSOPC.DATA.ID_AREA[WS_I-1].ID_EXPDT != 0) {
                    CEP.TRC(SCCGWA, "ID EXP DATE < AC DATE");
                    WS_ID_EXP_FLG = 'Y';
                }
            } else {
                CICSOPC.DATA.ID_AREA[WS_I-1].ID_OPEN = 'N';
            }
        }
        CEP.TRC(SCCGWA, WS_OPEN_ID_CHK_FLG);
        if (WS_OPEN_ID_CHK_FLG != 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "�?户证件详细信息未输入");
        }
        CEP.TRC(SCCGWA, WS_ID_EXP_FLG);
        if (WS_ID_EXP_FLG == 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_IS_EXP);
        }
        CEP.TRC(SCCGWA, WS_ID_EXP_DT);
        CEP.TRC(SCCGWA, CICSOPC.DATA.SEX);
        CEP.TRC(SCCGWA, CICSOPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CICSOPC.DATA.OCCUP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[1-1].ADR_TYPE);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CNT_AREA[1-1].CNT_TYPE);
        if (WS_ID_EXP_DT == 0 
            || CICSOPC.DATA.SEX == ' ' 
            || CICSOPC.DATA.REG_CNTY.trim().length() == 0 
            || CICSOPC.DATA.OCCUP.trim().length() == 0 
            || CICSOPC.DATA.ADR_AREA[1-1].ADR_TYPE.trim().length() == 0 
            || CICSOPC.DATA.CNT_AREA[1-1].CNT_TYPE.trim().length() == 0) {
            WS_INFO_LACK_FLG = 'Y';
        }
        if (CICSOPC.DATA.OPEN_BR == 0) {
            WS_OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            WS_OPEN_BR = CICSOPC.DATA.OPEN_BR;
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CICSOPC.DATA.BIRTH_RG);
        if (CICSOPC.DATA.REG_CNTY.equalsIgnoreCase("USA")) {
            WS_FATCA_FLG = 'Y';
        } else {
            WS_FATCA_FLG = 'N';
        }
        if (WS_FATCA_FLG == 'N') {
            for (WS_I = 1; WS_I <= 4 
                && WS_FATCA_FLG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE);
                CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY);
                CEP.TRC(SCCGWA, CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_CNTY);
                CEP.TRC(SCCGWA, CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TECN);
                if (((CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("111") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("114")) 
                    && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("USA")) 
                    || CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TECN.equalsIgnoreCase("USA") 
                    || (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("119") 
                    && (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("USA") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("ASM") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("GUM") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("MNP") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("PRI") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("VIR")))) {
                    WS_FATCA_FLG = 'Y';
                } else {
                    WS_FATCA_FLG = 'N';
                }
            }
        }
        B011_FATCA_CHK_INFO();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("600500")) {
            B012_CRS_CHECK_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSOPC.DATA.RESIDENT);
            if (CICSOPC.DATA.RESIDENT != '1') {
                B013_MOD_CHECK_INFO();
                if (pgmRtn) return;
            }
        }
        B014_MOD_CHECK_INFO();
        if (pgmRtn) return;
        if (CICSOPC.DATA.ID_TYPE.trim().length() > 0) {
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
            if (CICSOPC.DATA.ID_TYPE == null) CICSOPC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSOPC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSOPC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSOPC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B011_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOPC.DATA.FATCA_TP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.TIN_NO);
        CEP.TRC(SCCGWA, CICSOPC.DATA.REG_CNTY);
        CEP.TRC(SCCGWA, CICSOPC.DATA.BIRTH_RG);
        CEP.TRC(SCCGWA, CICSOPC.DATA.W8W9_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (WS_FATCA_FLG == 'N') {
            if (CICSOPC.DATA.FATCA_TP.trim().length() == 0 
                && CICSOPC.DATA.TIN_NO.trim().length() == 0) {
                CICSOPC.DATA.FATCA_TP = "NA00";
            }
            if (CICSOPC.DATA.FATCA_TP.trim().length() == 0 
                && CICSOPC.DATA.TIN_NO.trim().length() > 0) {
                CICSOPC.DATA.FATCA_TP = "W900";
            }
        }
        if (WS_FATCA_FLG == 'Y') {
            if (CICSOPC.DATA.FATCA_TP.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_USA_REP_EST_RFIL);
            }
            if (CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W801") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W802") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W803") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W804") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W805") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W806") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W807") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W808") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W809") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W810") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W910") 
                || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("AD00")) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "个人客户FATCA状�?�不可输�?" + CICSOPC.DATA.FATCA_TP;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, WS_MSG_INFO);
            }
            if (CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("NA00")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_TP_INP_ERR, "有FATCA表征，不可输入NA00");
            }
        }
        if (CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W800")) {
            if (CICSOPC.DATA.REG_CNTY.equalsIgnoreCase("USA")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_USA_FATCA_W9_RFIL);
            }
            for (WS_I = 1; WS_I <= 4; WS_I += 1) {
                if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("119") 
                    && (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("USA") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("ASM") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("GUM") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("MNP") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("PRI") 
                    || CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.equalsIgnoreCase("VIR"))) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_USA_NAT_LOS_CTF);
                }
            }
        }
        if (CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("AD00") 
            || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("RC00") 
            || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("RC01") 
            || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("PD00")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FATCA_ARPD);
        }
        if (!CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W800") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W802") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W803") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W804") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W805") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W806") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W807") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W808") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W809") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W810") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W900") 
            && !CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W901")) {
            if (CICSOPC.DATA.W8W9_DT != 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SIGND_MUST_NOTIN);
            }
        } else {
            if (CICSOPC.DATA.W8W9_DT != 0) {
                if (CICSOPC.DATA.W8W9_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SIGND_NOGRT_ACD);
                }
            } else {
                CICSOPC.DATA.W8W9_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        if (CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W900") 
            || CICSOPC.DATA.FATCA_TP.equalsIgnoreCase("W901")) {
            if (CICSOPC.DATA.TIN_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_W9TIN_MUST_INPUT);
            }
        } else {
            if (CICSOPC.DATA.TIN_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_W9TIN_MUST_NOTIN);
            }
        }
    }
    public void B012_CRS_CHECK_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 7 
            && WS_CRS_FLG != 'N'; WS_I += 1) {
            WS_CRS_ADR = " ";
            if (WS_I == 1) {
                WS_CRS_ADR = CICSOPC.DATA.REG_CNTY;
            } else if (WS_I == 2) {
                for (WS_J = 1; WS_J <= 4; WS_J += 1) {
                    if (CICSOPC.DATA.ADR_AREA[WS_J-1].ADR_TYPE.equalsIgnoreCase("111")) {
                        WS_CRS_ADR = CICSOPC.DATA.ADR_AREA[WS_J-1].ADR_CNTY;
                    }
                }
            } else if (WS_I == 3) {
                for (WS_J = 1; WS_J <= 4; WS_J += 1) {
                    if (CICSOPC.DATA.ADR_AREA[WS_J-1].ADR_TYPE.equalsIgnoreCase("130")) {
                        WS_CRS_ADR = CICSOPC.DATA.ADR_AREA[WS_J-1].ADR_CNTY;
                    }
                }
            } else if (WS_I == 4) {
                if (!CICSOPC.DATA.CNT_AREA[1-1].CNT_TECN.equalsIgnoreCase("CHN")) {
                    WS_CRS_ADR = CICSOPC.DATA.CNT_AREA[1-1].CNT_TECN;
                }
            } else if (WS_I == 5) {
                if (!CICSOPC.DATA.CNT_AREA[2-1].CNT_TECN.equalsIgnoreCase("CHN")) {
                    WS_CRS_ADR = CICSOPC.DATA.CNT_AREA[2-1].CNT_TECN;
                }
            } else if (WS_I == 6) {
                if (!CICSOPC.DATA.CNT_AREA[3-1].CNT_TECN.equalsIgnoreCase("CHN")) {
                    WS_CRS_ADR = CICSOPC.DATA.CNT_AREA[3-1].CNT_TECN;
                }
            } else {
                if (!CICSOPC.DATA.CNT_AREA[4-1].CNT_TECN.equalsIgnoreCase("CHN")) {
                    WS_CRS_ADR = CICSOPC.DATA.CNT_AREA[4-1].CNT_TECN;
                }
            }
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_CRS_ADR);
            if (WS_CRS_ADR.trim().length() > 0) {
                WS_CRS_FLG = 'N';
                for (WS_J = 1; WS_J <= 25 
                    && WS_CRS_FLG != 'Y'; WS_J += 1) {
                    CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[WS_J-1].CRS_ADR);
                    if (CICSOPC.DATA.CRS_AREA[WS_J-1].CRS_ADR.equalsIgnoreCase(WS_CRS_ADR)) {
                        WS_CRS_FLG = 'Y';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_TYPE);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[1-1].CRS_ADR);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_DESC);
        CEP.TRC(SCCGWA, CICSOPC.DATA.PROOF_DT);
        CEP.TRC(SCCGWA, CICSOPC.DATA.PROOF_CH);
        if (CICSOPC.DATA.CRS_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "CRS状�?�为必输�?");
        }
        if (WS_CRS_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_EMPW, "税务居住地没有CRS表征地址");
        }
        if (!CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("P1") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("EX") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("UD") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("N2") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("F1") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("F2") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("F3") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("LC") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("GV") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("IO") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("AN") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("PN") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("ID")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_CRS_TP);
        }
        if (CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            || CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            || CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("EX")) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "CRS状�?�为" + CICSOPC.DATA.CRS_TYPE;
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_TP_EMPW, WS_MSG_INFO);
        }
        if (CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("RT")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_TP_RT_PROOF, "请在备注中输入拒绝理�?");
        }
        if (!CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("P0") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("RT") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("N2") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R0") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("R1") 
            && !CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("EX")) {
            if (CICSOPC.DATA.CRS_AREA[1-1].CRS_ADR.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_ADR_ONE_INPUT);
            }
        }
        if ((CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("NA") 
            || CICSOPC.DATA.CRS_TYPE.equalsIgnoreCase("RT")) 
            && CICSOPC.DATA.CRS_DESC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_INPUT, "CRS状�?�为NA/RT");
        }
        if (CICSOPC.DATA.CRS_DESC.trim().length() > 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DESC_EMPW, "CRS状�?�原因备注不为空");
        }
        if (CICSOPC.DATA.PROOF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_PROOFD_NOGRT_ACD);
        }
        if (CICSOPC.DATA.PROOF_CH != 'B' 
            && CICSOPC.DATA.PROOF_CH != 'E' 
            && CICSOPC.DATA.PROOF_CH != 'P' 
            && CICSOPC.DATA.PROOF_CH != ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_PROOF_CH);
        }
        for (WS_I = 1; WS_I <= 25 
            && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR);
            CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO);
            CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD);
            CEP.TRC(SCCGWA, CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE);
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() > 0 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() > 0 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != ' ') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DSNO_NDCD_ONE);
            }
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() == 0 
                && !CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR.equalsIgnoreCase("CHN")) {
                WS_MSG_INFO = " ";
                WS_MSG_INFO = "税务居住地为" + CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR;
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_DENO_IMCPL, WS_MSG_INFO);
            }
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO.trim().length() == 0 
                && (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'A' 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'B' 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != 'C' 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD != ' ')) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INVAD_CRS_NDCD);
            }
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD == 'B' 
                && CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_NDRE_NEED_INPUT);
            }
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE.trim().length() > 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CRS_NDRE_EMPW);
            }
        }
    }
    public void B013_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOPC.DATA.CI_FNM);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CI_SNM);
        WS_BADR_FLG = 'N';
        WS_EADR_FLG = 'N';
        for (WS_I = 1; WS_I <= 4 
            && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_OPT != ' '; WS_I += 1) {
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L1);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L2);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L3);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L4);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L5);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L6);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L7);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_E2);
            CEP.TRC(SCCGWA, CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_NM);
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "地址类型必须输入");
            }
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "国家代码必须输入");
            }
            if ((CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L1.trim().length() > 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L2.trim().length() > 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_E2.trim().length() == 0) 
                || (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L1.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L2.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_E2.trim().length() > 0)) {
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BHA_PAC_ONEIN);
            }
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L3.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L4.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L5.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L6.trim().length() == 0 
                && CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L7.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CLFD_CNT_SPACE);
            }
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_NM.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("112")) {
                WS_EADR_FLG = 'Y';
            }
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE.equalsIgnoreCase("119")) {
                WS_BADR_FLG = 'Y';
            }
        }
        if (WS_EADR_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_EADR_MUST_INPUT);
        }
        if (WS_BADR_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BADR_MUST_INPUT);
        }
        if (CICSOPC.DATA.CI_FNM.trim().length() == 0 
            || CICSOPC.DATA.CI_SNM.trim().length() == 0) {
            WS_MSG_INFO = " ";
            WS_MSG_INFO = "非居民客户，" + "英文名和英文姓必�?";
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, WS_MSG_INFO);
        }
    }
    public void B014_MOD_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOPC.DATA.HKC_TYP);
        if (CICSOPC.DATA.HKC_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港客户分类必须输入");
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        if (WS_BAS_FOUND_FLG == 'N') {
            CEP.TRC(SCCGWA, "CALL CIZCINO");
            IBS.init(SCCGWA, CICCINO);
            S000_CALL_CIZCINO();
            if (pgmRtn) return;
            WS_CI_NO = CICCINO.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B030_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = CICSOPC.DATA.CI_TYP;
        CIRBAS.CI_ATTR = CICSOPC.DATA.CI_ATTR;
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        if (WS_ID_EXP_FLG == 'Y') {
            CEP.TRC(SCCGWA, "ID EXP");
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 22 - 1) + "1" + CIRBAS.STSW.substring(22 + 1 - 1);
        }
        if (WS_INFO_LACK_FLG == 'Y') {
            CEP.TRC(SCCGWA, "INFO LACK");
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        }
        CIRBAS.VER_STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CICSOPC.DATA.VER_FLG == 'T') {
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            CIRBAS.VER_STSW = CIRBAS.VER_STSW.substring(0, 2 - 1) + "1" + CIRBAS.VER_STSW.substring(2 + 1 - 1);
        } else {
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            CIRBAS.VER_STSW = "1" + CIRBAS.VER_STSW.substring(1);
        }
        CIRBAS.IDE_STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        CEP.TRC(SCCGWA, CICSOPC.DATA.PL_FLG);
        CEP.TRC(SCCGWA, CICSOPC.DATA.PEA_FLG);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CD_EMP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.BK_EMP);
        CEP.TRC(SCCGWA, CICSOPC.DATA.CDG_EMP);
        if (CICSOPC.DATA.BK_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = "1" + CIRBAS.IDE_STSW.substring(1);
        }
        if (CICSOPC.DATA.CDG_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 2 - 1) + "1" + CIRBAS.IDE_STSW.substring(2 + 1 - 1);
        }
        if (CICSOPC.DATA.PL_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 3 - 1) + "1" + CIRBAS.IDE_STSW.substring(3 + 1 - 1);
        }
        if (CICSOPC.DATA.PEA_FLG == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 4 - 1) + "1" + CIRBAS.IDE_STSW.substring(4 + 1 - 1);
        }
        if (CICSOPC.DATA.CD_EMP == 'Y') {
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, 6 - 1) + "1" + CIRBAS.IDE_STSW.substring(6 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_ID_TYPE = CICSOPC.DATA.ID_TYPE;
        CIRRELT.RELT_ID_NO = CICSOPC.DATA.ID_NO;
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
                CEP.TRC(SCCGWA, CIRRELT.EMP_NO);
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
        CIRRREL.REL_ID_TYPE = CICSOPC.DATA.ID_TYPE;
        CIRRREL.REL_ID_NO = CICSOPC.DATA.ID_NO;
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
        CIRBAS.OPN_BR = WS_OPEN_BR;
        CIRBAS.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.ORGIN_TP = CICSOPC.DATA.ORGIN_TP;
        CIRBAS.ORIGIN = CICSOPC.DATA.ORGIN1;
        CIRBAS.ORIGIN2 = CICSOPC.DATA.ORGIN2;
        CIRBAS.CI_NM = CICSOPC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSOPC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOPC.DATA.ID_NO;
        CIRBAS.TAX_BANK = CICSOPC.DATA.TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICSOPC.DATA.TAX_ACNO;
        CIRBAS.TAX_TYPE = CICSOPC.DATA.TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICSOPC.DATA.TAX_DSNO;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CIRBAS.OWNER_BK = BPCPQORG.BRANCH_BR;
        CIRBAS.OWNER_BR = WS_OPEN_BR;
        CIRBAS.OIC_NO = CICSOPC.DATA.OIC_NO;
        CIRBAS.OPEN_CHNL = SCCGWA.COMM_AREA.CHNL;
        CIRBAS.NRA_TAX_TYP = CICSOPC.DATA.NRA_TAX;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.SEARCH_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = WS_OPEN_BR;
        CIRBAS.UPD_BR = WS_OPEN_BR;
        if (WS_BAS_FOUND_FLG == 'N') {
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, CIRPDM);
        CIRPDM.KEY.CI_NO = WS_CI_NO;
        CIRPDM.RESIDENT = CICSOPC.DATA.RESIDENT;
        CIRPDM.CI_SUB_TYP = CICSOPC.DATA.SUB_TYP;
        CIRPDM.SEX = CICSOPC.DATA.SEX;
        CIRPDM.MARI = CICSOPC.DATA.MARI;
        CIRPDM.BIRTH_DT = CICSOPC.DATA.BIRTH_DT;
        CIRPDM.NATION = CICSOPC.DATA.NATION;
        CIRPDM.REG_CNTY = CICSOPC.DATA.REG_CNTY;
        CIRPDM.OCCUP = CICSOPC.DATA.OCCUP;
        CIRPDM.SID_FLG = CICSOPC.DATA.SID_FLG;
        CIRPDM.BIRTH_RGN = CICSOPC.DATA.BIRTH_RG;
        CIRPDM.EDU_LVL = CICSOPC.DATA.EDU_LVL;
        CIRPDM.WK_TITL = CICSOPC.DATA.WK_TITL;
        CIRPDM.PER_INC = CICSOPC.DATA.PER_INC;
        CIRPDM.FAM_INC = CICSOPC.DATA.FAM_INC;
        CIRPDM.CORP_INDUS = CICSOPC.DATA.CORP_IND;
        CIRPDM.CORP_TYP = CICSOPC.DATA.CORP_TYP;
        CIRPDM.CORP_NM = CICSOPC.DATA.CORP_NM;
        PDM_CORP_NM_LEN = CIRPDM.CORP_NM.length();
        CIRPDM.REMARK = CICSOPC.DATA.REMARK;
        PDM_REMARK_LEN = CIRPDM.REMARK.length();
        CIRPDM.HK_CI_TYP = CICSOPC.DATA.HKC_TYP;
        CIRPDM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRPDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRPDM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRPDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRPDM.CRT_BR = WS_OPEN_BR;
        CIRPDM.UPD_BR = WS_OPEN_BR;
        T000_WRITE_CITPDM();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_WRITE_ID_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            if (CICSOPC.DATA.ID_AREA[WS_I-1].ID_OPT == 'A') {
                IBS.init(SCCGWA, CIRID);
                CIRID.KEY.CI_NO = WS_CI_NO;
                CIRID.KEY.ID_TYPE = CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDTYP;
                CIRID.ID_NO = CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDNO;
                CIRID.EFF_DT = CICSOPC.DATA.ID_AREA[WS_I-1].ID_EFFDT;
                CIRID.EXP_DT = CICSOPC.DATA.ID_AREA[WS_I-1].ID_EXPDT;
                CIRID.ID_RGN = CICSOPC.DATA.ID_AREA[WS_I-1].ID_GRN;
                CIRID.OPEN = CICSOPC.DATA.ID_AREA[WS_I-1].ID_OPEN;
                CIRID.REMARK = CICSOPC.DATA.ID_AREA[WS_I-1].ID_IDRMK;
                CIRID.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRID.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRID.CRT_BR = WS_OPEN_BR;
                CIRID.UPD_BR = WS_OPEN_BR;
                T000_WRITE_CITID();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_WRITE_NAM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = WS_CI_NO;
        CIRNAM.KEY.NM_TYPE = "01";
        CIRNAM.CI_NM = CICSOPC.DATA.CI_NM;
        NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
        CIRNAM.OPEN = 'Y';
        CIRNAM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRNAM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRNAM.CRT_BR = WS_OPEN_BR;
        CIRNAM.UPD_BR = WS_OPEN_BR;
        T000_WRITE_CITNAM();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            WS_CI_NM = " ";
            WS_NM_TP = " ";
            if (WS_I == 1) {
                if (CICSOPC.DATA.CI_ENM.trim().length() > 0) {
                    WS_NM_TP = "03";
                    WS_CI_NM = CICSOPC.DATA.CI_ENM;
                }
            } else if (WS_I == 2) {
                if (CICSOPC.DATA.CI_FNM.trim().length() > 0) {
                    WS_NM_TP = "05";
                    WS_CI_NM = CICSOPC.DATA.CI_FNM;
                }
            } else if (WS_I == 3) {
                if (CICSOPC.DATA.CI_SNM.trim().length() > 0) {
                    WS_NM_TP = "06";
                    WS_CI_NM = CICSOPC.DATA.CI_SNM;
                }
            } else if (WS_I == 4) {
                if (CICSOPC.DATA.CI_OFNM.trim().length() > 0) {
                    WS_NM_TP = "07";
                    WS_CI_NM = CICSOPC.DATA.CI_OFNM;
                }
            } else if (WS_I == 5) {
                if (CICSOPC.DATA.CI_OSNM.trim().length() > 0) {
                    WS_NM_TP = "08";
                    WS_CI_NM = CICSOPC.DATA.CI_OSNM;
                }
            } else {
                if (CICSOPC.DATA.CI_ONM.trim().length() > 0) {
                    WS_NM_TP = "09";
                    WS_CI_NM = CICSOPC.DATA.CI_ONM;
                }
            }
            if (WS_CI_NM.trim().length() > 0) {
                IBS.init(SCCGWA, CIRNAM);
                CIRNAM.KEY.CI_NO = WS_CI_NO;
                CIRNAM.KEY.NM_TYPE = WS_NM_TP;
                CIRNAM.CI_NM = WS_CI_NM;
                NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                CIRNAM.OPEN = 'N';
                CIRNAM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.CRT_BR = WS_OPEN_BR;
                CIRNAM.UPD_BR = WS_OPEN_BR;
                T000_WRITE_CITNAM();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_WRITE_ADR_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            if (CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_OPT == 'A') {
                IBS.init(SCCGWA, CIRADR);
                CIRADR.KEY.CI_NO = WS_CI_NO;
                CIRADR.KEY.ADR_TYPE = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_TYPE;
                CIRADR.CNTY_CD = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_CNTY;
                CIRADR.LADDR_L1 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L1;
                CIRADR.LADDR_L2 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L2;
                CIRADR.LADDR_L3 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L3;
                CIRADR.LADDR_L4 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L4;
                CIRADR.LADDR_L5 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L5;
                CIRADR.LADDR_L6 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L6;
                CIRADR.LADDR_L7 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_L7;
                CIRADR.LADDR_E2 = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_E2;
                CIRADR.POST_CD = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_POST;
                CIRADR.ADR_NM = CICSOPC.DATA.ADR_AREA[WS_I-1].ADR_NM;
                CIRADR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRADR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRADR.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRADR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRADR.CRT_BR = WS_OPEN_BR;
                CIRADR.UPD_BR = WS_OPEN_BR;
                T000_WRITE_CITADR();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_WRITE_CNT_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            if (CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_OPT == 'A') {
                CEP.TRC(SCCGWA, CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TYPE);
                if (CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TYPE.equalsIgnoreCase("21")) {
                    IBS.init(SCCGWA, CIRCNT);
                    CIRCNT.KEY.CNT_TYPE = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TYPE;
                    CIRCNT.TEL_NO = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TEL;
                    T000_READ_CITCNT_CHK_TEL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    CEP.TRC(SCCGWA, CIRCNT.KEY.CI_NO);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                        && !CIRCNT.KEY.CI_NO.equalsIgnoreCase(WS_CI_NO)) {
                        CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TEL_NO_EXIST);
                    }
                }
                IBS.init(SCCGWA, CIRCNT);
                CIRCNT.KEY.CI_NO = WS_CI_NO;
                CIRCNT.KEY.CNT_TYPE = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TYPE;
                CIRCNT.CNT_CNTY = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_CNTY;
                CIRCNT.TEL_CNTY = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TECN;
                CIRCNT.CNT_ZONE = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_ZONE;
                CIRCNT.TEL_NO = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TEL;
                CIRCNT.TEL_NO1 = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_TEL2;
                CIRCNT.CNT_INFO = CICSOPC.DATA.CNT_AREA[WS_I-1].CNT_INFO;
                CIRCNT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCNT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCNT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCNT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCNT.CRT_BR = WS_OPEN_BR;
                CIRCNT.UPD_BR = WS_OPEN_BR;
                T000_WRITE_CITCNT();
                if (pgmRtn) return;
            }
        }
    }
    public void B080_WRITE_REL_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            if (CICSOPC.DATA.REL_AREA[WS_I-1].REL_OPT == 'A') {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.ID_TYPE = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP;
                CIRBAS.ID_NO = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDNO;
                CIRBAS.CI_NM = CICSOPC.DATA.REL_AREA[WS_I-1].REL_NAME;
                BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
                T000_READ_CITBAS_BY_IDNM();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    IBS.init(SCCGWA, CICCINO);
                    S000_CALL_CIZCINO();
                    if (pgmRtn) return;
                    WS_RCI_NO = CICCINO.DATA.CI_NO;
                    IBS.init(SCCGWA, CIRBAS);
                    CIRBAS.KEY.CI_NO = WS_RCI_NO;
                    if (CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP == null) CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP = "";
                    JIBS_tmp_int = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP.length();
                    for (int i=0;i<5-JIBS_tmp_int;i++) CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP += " ";
                    if (CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP.substring(0, 1).equalsIgnoreCase("1")) {
                        CIRBAS.CI_TYP = '1';
                    } else {
                        CIRBAS.CI_TYP = '2';
                    }
                    CIRBAS.CI_ATTR = '2';
                    CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
                    if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                    JIBS_tmp_int = CIRBAS.STSW.length();
                    for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                    CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
                    if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                    JIBS_tmp_int = CIRBAS.STSW.length();
                    for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                    CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
                    CIRBAS.CI_NM = CICSOPC.DATA.REL_AREA[WS_I-1].REL_NAME;
                    BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
                    CIRBAS.ID_TYPE = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP;
                    CIRBAS.ID_NO = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDNO;
                    CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRBAS.SEARCH_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRBAS.CRT_BR = WS_OPEN_BR;
                    CIRBAS.UPD_BR = WS_OPEN_BR;
                    T000_WRITE_CITBAS();
                    if (pgmRtn) return;
                } else {
                    WS_RCI_NO = CIRBAS.KEY.CI_NO;
                }
                IBS.init(SCCGWA, CIRRELN);
                CIRRELN.KEY.CI_NO = WS_CI_NO;
                CIRRELN.KEY.CIREL_CD = CICSOPC.DATA.REL_AREA[WS_I-1].REL_RECD;
                CIRRELN.KEY.RCI_IDTYP = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDTP;
                CIRRELN.KEY.RCI_IDNO = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDNO;
                CIRRELN.KEY.RCI_NAME = CICSOPC.DATA.REL_AREA[WS_I-1].REL_NAME;
                CIRRELN.RCI_NO = WS_RCI_NO;
                CIRRELN.RCI_STS = '0';
                CIRRELN.RCI_ID_EXP = CICSOPC.DATA.REL_AREA[WS_I-1].REL_IDEX;
                CIRRELN.RCI_MOB_NO = CICSOPC.DATA.REL_AREA[WS_I-1].REL_MOB;
                CIRRELN.RCI_TEL_NO = CICSOPC.DATA.REL_AREA[WS_I-1].REL_TEL;
                CIRRELN.RCI_SEX = CICSOPC.DATA.REL_AREA[WS_I-1].REL_SEX;
                CIRRELN.RCI_REG_CNTY = CICSOPC.DATA.REL_AREA[WS_I-1].REL_CNTY;
                CIRRELN.RCI_CNTY_CD = CICSOPC.DATA.REL_AREA[WS_I-1].REL_ADCN;
                CIRRELN.RCI_ADDR = CICSOPC.DATA.REL_AREA[WS_I-1].REL_ADDR;
                CIRRELN.RCI_CORP_NM = CICSOPC.DATA.REL_AREA[WS_I-1].REL_OCNM;
                CIRRELN.RCI_BIRTH = CICSOPC.DATA.REL_AREA[WS_I-1].REL_BIRT;
                CIRRELN.RCI_OCCP = CICSOPC.DATA.REL_AREA[WS_I-1].REL_OCCU;
                CIRRELN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRRELN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRRELN.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRRELN.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRRELN.CRT_BR = WS_OPEN_BR;
                CIRRELN.UPD_BR = WS_OPEN_BR;
                T000_WRITE_CITRELN();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_WRITE_RISK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITRISK_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CIRRISK.FATCA_TP = CICSOPC.DATA.FATCA_TP;
            CIRRISK.W8W9_DT = CICSOPC.DATA.W8W9_DT;
            CIRRISK.TIN_NO = CICSOPC.DATA.TIN_NO;
            CIRRISK.GIIN_CODE = CICSOPC.DATA.GIIN_CD;
            CIRRISK.CRS_TYPE = CICSOPC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSOPC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSOPC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSOPC.DATA.PROOF_CH;
            CIRRISK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.CRT_BR = WS_OPEN_BR;
            CIRRISK.UPD_BR = WS_OPEN_BR;
            T000_WRITE_CITRISK();
            if (pgmRtn) return;
        } else {
            CIRRISK.FATCA_TP = CICSOPC.DATA.FATCA_TP;
            CIRRISK.W8W9_DT = CICSOPC.DATA.W8W9_DT;
            CIRRISK.TIN_NO = CICSOPC.DATA.TIN_NO;
            CIRRISK.GIIN_CODE = CICSOPC.DATA.GIIN_CD;
            CIRRISK.CRS_TYPE = CICSOPC.DATA.CRS_TYPE;
            CIRRISK.CRS_DESC = CICSOPC.DATA.CRS_DESC;
            CIRRISK.PROOF_DT = CICSOPC.DATA.PROOF_DT;
            CIRRISK.PROOF_CHNL = CICSOPC.DATA.PROOF_CH;
            CIRRISK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRRISK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRRISK.UPD_BR = WS_OPEN_BR;
            T000_REWRITE_CITRISK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRCRS);
        CIRCRS.KEY.CI_NO = WS_CI_NO;
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
            if (CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR.trim().length() > 0) {
                IBS.init(SCCGWA, CIRCRS);
                CIRCRS.KEY.CI_NO = WS_CI_NO;
                CIRCRS.KEY.INFO_TYP = '1';
                CIRCRS.KEY.TAX_ADR = CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_ADR;
                CIRCRS.TAX_DIST_NO = CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_DSNO;
                CIRCRS.NO_DIST_CODE = CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDCD;
                CIRCRS.NO_DIST_REASON = CICSOPC.DATA.CRS_AREA[WS_I-1].CRS_NDRE;
                CIRCRS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRCRS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRCRS.CRT_BR = WS_OPEN_BR;
                CIRCRS.UPD_BR = WS_OPEN_BR;
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
        BPCPNHIS.INFO.CI_NO = WS_CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
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
            CICSAGEN.BR = WS_OPEN_BR;
            CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
            CICSAGEN.CI_NO = WS_CI_NO;
            CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICSAGEN.AGENT_TP = "02";
            CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
            CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
            S000_CALL_CIZSAGEN();
            if (pgmRtn) return;
        }
    }
    public void B120_OUTPUT_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOCINO);
        IBS.init(SCCGWA, SCCFMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0029150")) {
            SCCFMT.FMTID = "CIB50";
        } else {
            SCCFMT.FMTID = "CIO01";
        }
        SCCFMT.DATA_PTR = CICOCINO;
        SCCFMT.DATA_LEN = 125;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B130_GRLST_IN_GRPM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRLST);
        CIRGRLST.KEY.ID_TYP = CICSOPC.DATA.ID_TYPE;
        CIRGRLST.KEY.ID_NO = CICSOPC.DATA.ID_NO;
        CIRGRLST.KEY.CI_NM = CICSOPC.DATA.CI_NM;
        CIRGRLST.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_CITGRLST_BY_IDNM();
        if (pgmRtn) return;
        T000_READNEXT_CITGRLST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.init(SCCGWA, CIRGRPM);
                CIRGRPM.KEY.ENTY_TYP = '0';
                CIRGRPM.KEY.ENTY_NO = WS_CI_NO;
                CIRGRPM.KEY.GRPS_NO = CIRGRLST.KEY.GRPS_NO;
                CIRGRPM.KEY.EFF_DT = CIRGRLST.KEY.EFF_DT;
                CIRGRPM.KEY.EXP_DT = CIRGRLST.KEY.EXP_DT;
                CIRGRPM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRGRPM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRGRPM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRGRPM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRGRPM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRGRPM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                T000_WRITE_CITGRPM();
                if (pgmRtn) return;
                T000_READNEXT_CITGRLST();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_CITGRLST();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCINO);
        CICOCINO.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOCINO.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOCINO.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOCINO.DATA.CI_STSW = CIRBAS.STSW;
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOCINO.DATA.VER_FLG = 'T';
        } else {
            CICOCINO.DATA.VER_FLG = 'F';
        }
        CICOCINO.DATA.IDE_STSW = CIRBAS.IDE_STSW;
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
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
    public void T000_READ_CITBAS_BY_IDNM_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
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
    public void T000_WRITE_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.WRITE(SCCGWA, CIRPDM, CITPDM_RD);
    }
    public void T000_WRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRID, CITID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "证件类型录入重复");
        }
    }
    public void T000_WRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.WRITE(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_WRITE_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRADR, CITADR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "地址类型录入重复");
        }
    }
    public void T000_WRITE_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRCNT, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "联系类型录入重复");
        }
    }
    public void T000_WRITE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRRELN, CITRELN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "关系人信息录入重�?");
        }
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
    public void T000_READ_CITCNT_CHK_TEL() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CNT_TYPE,TEL_NO";
        CITCNT_RD.fst = true;
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
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
    public void T000_READ_CITRELT() throws IOException,SQLException,Exception {
        CITRELT_RD = new DBParm();
        CITRELT_RD.TableName = "CITRELT";
        IBS.READ(SCCGWA, CIRRELT, CITRELT_RD);
    }
    public void T000_STARTBR_CITGRLST_BY_IDNM() throws IOException,SQLException,Exception {
        CITGRLST_BR.rp = new DBParm();
        CITGRLST_BR.rp.TableName = "CITGRLST";
        CITGRLST_BR.rp.eqWhere = "ID_TYP , ID_NO , CI_NM";
        CITGRLST_BR.rp.where = "EXP_DT = 0 "
            + "OR EXP_DT > :CIRGRLST.KEY.EXP_DT";
        IBS.STARTBR(SCCGWA, CIRGRLST, this, CITGRLST_BR);
    }
    public void T000_READNEXT_CITGRLST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRGRLST, this, CITGRLST_BR);
    }
    public void T000_ENDBR_CITGRLST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITGRLST_BR);
    }
    public void T000_WRITE_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        IBS.WRITE(SCCGWA, CIRGRPM, CITGRPM_RD);
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
