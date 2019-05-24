package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUGMC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_I = 0;
    double WS_EWA_AMT = 0;
    String WS_MSG_ERR = " ";
    String WS_COPYBOOK = " ";
    int WS_FMD_BR = 0;
    int WS_PROD_LEN = 0;
    String WS_PROD_NEW = " ";
    String WS_PROD_OLD = " ";
    String WS_MOD_NEW = " ";
    String WS_MOD_OLD = " ";
    BPZUGMC_WS_GLMST[] WS_GLMST = new BPZUGMC_WS_GLMST[10];
    char WS_FND_FLG = ' ';
    char WS_AMT_FLG = ' ';
    char WS_SAME_FLG = ' ';
    char WS_GLM_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPITM BPCPITM = new BPCPITM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCSIC BPCSIC = new BPCSIC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPRGFHIS BPRGFHIS = new BPRGFHIS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    SCCGWA SCCGWA;
    BPCUGMC BPCUGMC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_REC = " ";
    public BPZUGMC() {
        for (int i=0;i<10;i++) WS_GLMST[i] = new BPZUGMC_WS_GLMST();
    }
    public void MP(SCCGWA SCCGWA, BPCUGMC BPCUGMC, String LK_REC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUGMC = BPCUGMC;
        this.LK_REC = LK_REC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUGMC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCUGMC.RC.RC_MMO = "BP";
        BPCUGMC.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUGMC.INFO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_FETCH_EVENT_DATA();
        if (pgmRtn) return;
        if (BPCUGMC.INFO.CHG_FLG == 'B' 
            || BPCUGMC.INFO.CHG_FLG == '1') {
            B030_EVENT_BC();
            if (pgmRtn) return;
        }
        B040_EVENT_GLM();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUGMC.INFO.CHG_FLG);
        if (BPCUGMC.INFO.CHG_FLG == 'G' 
            || BPCUGMC.INFO.CHG_FLG == 'B' 
            || BPCUGMC.INFO.CHG_FLG == '1' 
            || BPCUGMC.INFO.CHG_FLG == '2') {
        } else {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUGMC.INFO.CHG_FLG == ' ') {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_CHANGE_FLG_NULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUGMC.INFO.CHG_FLG == '1' 
            || BPCUGMC.INFO.CHG_FLG == '2') {
            WS_GLM_FLG = 'Y';
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                if (BPCUGMC.INFO.GLMS[WS_I-1].GLM1 != 0) {
                    WS_GLM_FLG = 'N';
                }
            }
            if (WS_GLM_FLG == 'Y') {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if ((BPCUGMC.INFO.CHG_FLG == 'B' 
            || BPCUGMC.INFO.CHG_FLG == '1') 
            && BPCUGMC.INFO.BR_OLD == BPCUGMC.INFO.BR_NEW) {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_OLD_NEW_BR_THE_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPDM);
        BPCPQPDM.PRDT_MODEL = BPCUGMC.INFO.CNTR_TYPE;
        BPCPQPDM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_FND_FLG = 'Y';
        S000_CALL_BPZPQPDM();
        if (pgmRtn) return;
        if (WS_FND_FLG == 'N') {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCSIC);
        BPCSIC.FUNC = "03";
        BPCSIC.CCY = BPCUGMC.INFO.CCY_INFO[1-1].CCY;
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
        SCCCALL.COMMAREA_PTR = BPCSIC;
        SCCCALL.ERR_FLDNO = 16;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        for (WS_I = 2; WS_I <= 5; WS_I += 1) {
            if (BPCUGMC.INFO.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCSIC);
                BPCSIC.FUNC = "03";
                BPCSIC.CCY = BPCUGMC.INFO.CCY_INFO[WS_I-1].CCY;
                IBS.init(SCCGWA, SCCCALL);
                SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
                SCCCALL.COMMAREA_PTR = BPCSIC;
                SCCCALL.ERR_FLDNO = 16;
                SCCCALL.CONTINUE_FLG = 'Y';
                IBS.CALL(SCCGWA, SCCCALL);
            }
        }
        IBS.init(SCCGWA, BPCSIC);
        BPCSIC.FUNC = "10";
        BPCSIC.BR = BPCUGMC.INFO.BR_OLD;
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
        SCCCALL.COMMAREA_PTR = BPCSIC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCUGMC.INFO.BR_NEW != 0 
            && BPCUGMC.INFO.BR_NEW != BPCUGMC.INFO.BR_OLD) {
            IBS.init(SCCGWA, BPCSIC);
            BPCSIC.FUNC = "10";
            BPCSIC.BR = BPCUGMC.INFO.BR_NEW;
            IBS.init(SCCGWA, SCCCALL);
            SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
            SCCCALL.COMMAREA_PTR = BPCSIC;
            SCCCALL.CONTINUE_FLG = 'Y';
            IBS.CALL(SCCGWA, SCCCALL);
        }
        if ((BPCUGMC.INFO.CHG_FLG == 'G' 
            || BPCUGMC.INFO.CHG_FLG == '2') 
            && BPCUGMC.INFO.BR_NEW == 0) {
            BPCUGMC.INFO.BR_NEW = BPCUGMC.INFO.BR_OLD;
        }
    }
    public void B040_EVENT_GLM() throws IOException,SQLException,Exception {
        WS_SAME_FLG = 'N';
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (BPCUGMC.INFO.CHG_FLG == 'B' 
                || BPCUGMC.INFO.CHG_FLG == 'G') {
                WS_GLMST[WS_I-1].WS_GLMST_TMP = BPCUCNGM.DATA[WS_I-1].GLMST;
            } else {
                WS_GLMST[WS_I-1].WS_GLMST_TMP = BPCUGMC.INFO.GLMS[WS_I-1].GLM1;
            }
            CEP.TRC(SCCGWA, WS_GLMST[WS_I-1].WS_GLMST_TMP);
            CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_I-1].MSTNO);
            if (WS_GLMST[WS_I-1].WS_GLMST_TMP != BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_I-1].MSTNO) {
                WS_SAME_FLG = 'Y';
            }
        }
        if (!WS_PROD_OLD.equalsIgnoreCase(WS_PROD_NEW)) {
            WS_SAME_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, BPCUGMC.INFO.CHANGE_FLG);
        if (WS_SAME_FLG == 'Y') {
            if (GWA_BP_AREA.COM_AREA.DUMMY_VCH_FLG != 'Y' 
                && BPCUGMC.INFO.CHANGE_FLG == 'Y') {
                for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                    BPCUCNGM.DATA[WS_I-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_I-1].MSTNO;
                }
                BPCUCNGM.FUNC = 'U';
                BPCUCNGM.EC_LMT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCUCNGM.EC_LMT_TIME = SCCGWA.COMM_AREA.TR_TIME;
                S000_CALL_BPZUCNGM();
                if (pgmRtn) return;
            }
            WS_AMT_FLG = 'N';
            for (WS_I = 1; WS_I <= 76; WS_I += 1) {
                WS_EWA_AMT = BPCUGMC.INFO.AMTS[WS_I-1].AMT;
                if (WS_EWA_AMT != 0) {
                    WS_AMT_FLG = 'Y';
                }
            }
            if (WS_AMT_FLG == 'Y') {
                R000_WRITE_EVENT_GLM();
                if (pgmRtn) return;
            }
        } else {
            if ((BPCUGMC.INFO.CHG_FLG == 'B' 
                || BPCUGMC.INFO.CHG_FLG == '1') 
                && GWA_BP_AREA.COM_AREA.DUMMY_VCH_FLG != 'Y') {
                BPCUCNGM.FUNC = 'U';
                BPCUCNGM.EC_LMT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCUCNGM.EC_LMT_TIME = SCCGWA.COMM_AREA.TR_TIME;
                S000_CALL_BPZUCNGM();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_WRITE_EVENT_GLM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.MOD_NO = WS_MOD_OLD;
        BPCPOEWA.DATA.CNTR_TYPE = BPCUGMC.INFO.CNTR_TYPE;
        BPCPOEWA.DATA.PROD_CODE = WS_PROD_OLD;
        BPCPOEWA.DATA.EVENT_CODE = "GO";
        BPCPOEWA.DATA.BR_OLD = BPCUGMC.INFO.BR_NEW;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUGMC.INFO.CCY_INFO[1-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[2-1].CCY = BPCUGMC.INFO.CCY_INFO[2-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[3-1].CCY = BPCUGMC.INFO.CCY_INFO[3-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[4-1].CCY = BPCUGMC.INFO.CCY_INFO[4-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[5-1].CCY = BPCUGMC.INFO.CCY_INFO[5-1].CCY;
        BPCPOEWA.DATA.CI_NO = BPCUGMC.INFO.CI_NO;
        BPCPOEWA.DATA.AC_NO = BPCUGMC.INFO.AC_NO;
        BPCPOEWA.DATA.REF_NO = BPCUGMC.INFO.REL_SEQ;
        BPCPOEWA.DATA.EFF_DAYS = BPCUGMC.INFO.EFF_DAYS;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM1 = WS_GLMST[WS_I-1].WS_GLMST_TMP;
        }
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = BPCUGMC.INFO.AMTS[WS_I-1].AMT;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.MOD_NO = WS_MOD_OLD;
        BPCPOEWA.DATA.CNTR_TYPE = BPCUGMC.INFO.CNTR_TYPE;
        if (BPCUGMC.INFO.CHG_FLG == 'G' 
            || BPCUGMC.INFO.CHG_FLG == '2') {
            BPCPOEWA.DATA.PROD_CODE = WS_PROD_NEW;
        } else {
            BPCPOEWA.DATA.PROD_CODE = WS_PROD_OLD;
        }
        BPCPOEWA.DATA.EVENT_CODE = "GI";
        BPCPOEWA.DATA.BR_OLD = BPCUGMC.INFO.BR_NEW;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUGMC.INFO.CCY_INFO[1-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[2-1].CCY = BPCUGMC.INFO.CCY_INFO[2-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[3-1].CCY = BPCUGMC.INFO.CCY_INFO[3-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[4-1].CCY = BPCUGMC.INFO.CCY_INFO[4-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[5-1].CCY = BPCUGMC.INFO.CCY_INFO[5-1].CCY;
        BPCPOEWA.DATA.CI_NO = BPCUGMC.INFO.CI_NO;
        BPCPOEWA.DATA.AC_NO = BPCUGMC.INFO.AC_NO;
        BPCPOEWA.DATA.REF_NO = BPCUGMC.INFO.REL_SEQ;
        BPCPOEWA.DATA.EFF_DAYS = BPCUGMC.INFO.EFF_DAYS;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM1 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[WS_I-1].MSTNO;
        }
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = BPCUGMC.INFO.AMTS[WS_I-1].AMT;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B020_FETCH_EVENT_DATA() throws IOException,SQLException,Exception {
        WS_PROD_LEN = 10;
        LK_REC = IBS.CLS2CPY(SCCGWA, BPCUGMC.INFO.OTH_PTR_OLD);
        WS_COPYBOOK = " ";
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) LK_REC += " ";
        WS_COPYBOOK = LK_REC.substring(0, BPCUGMC.INFO.OTH_PTR_LEN);
        if (WS_COPYBOOK == null) WS_COPYBOOK = "";
        JIBS_tmp_int = WS_COPYBOOK.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
        WS_PROD_OLD = WS_COPYBOOK.substring(0, WS_PROD_LEN);
        CEP.TRC(SCCGWA, WS_PROD_OLD);
        if (BPCUGMC.INFO.CHG_FLG == 'G' 
            || BPCUGMC.INFO.CHG_FLG == '2') {
            LK_REC = IBS.CLS2CPY(SCCGWA, BPCUGMC.INFO.OTH_PTR_NEW);
            WS_COPYBOOK = " ";
            if (LK_REC == null) LK_REC = "";
            JIBS_tmp_int = LK_REC.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) LK_REC += " ";
            WS_COPYBOOK = LK_REC.substring(0, BPCUGMC.INFO.OTH_PTR_LEN);
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_PROD_NEW = WS_COPYBOOK.substring(0, WS_PROD_LEN);
            CEP.TRC(SCCGWA, WS_PROD_NEW);
        }
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.CNTR_TYPE = BPCUGMC.INFO.CNTR_TYPE;
        BPCUCNGM.KEY.AC = BPCUGMC.INFO.AC_NO;
        BPCUCNGM.KEY.REL_SEQ = BPCUGMC.INFO.REL_SEQ;
        BPCUCNGM.FUNC = 'Q';
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        WS_MOD_OLD = BPCUCNGM.MOD_NO;
        CEP.TRC(SCCGWA, BPCUCNGM.MOD_NO);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[1-1].GLMST);
        IBS.init(SCCGWA, BPCQCNGL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCUGMC.INFO.CNTR_TYPE;
        BPCQCNGL.DAT.INPUT.BR = BPCUGMC.INFO.BR_NEW;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = BPCUGMC.INFO.OTH_PTR_LEN;
        if (BPCUGMC.INFO.CHG_FLG == 'B' 
            || BPCUGMC.INFO.CHG_FLG == '1') {
            LK_REC = IBS.CLS2CPY(SCCGWA, BPCUGMC.INFO.OTH_PTR_OLD);
        } else {
            LK_REC = IBS.CLS2CPY(SCCGWA, BPCUGMC.INFO.OTH_PTR_NEW);
        }
        WS_COPYBOOK = " ";
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) LK_REC += " ";
        WS_COPYBOOK = LK_REC.substring(0, BPCUGMC.INFO.OTH_PTR_LEN);
        CEP.TRC(SCCGWA, "OTH-PTR-OLD");
        CEP.TRC(SCCGWA, WS_COPYBOOK);
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
        WS_MOD_NEW = BPCQCNGL.DAT.OUTPUT.MOD_NO;
        CEP.TRC(SCCGWA, WS_MOD_NEW);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO);
    }
    public void B030_EVENT_BC() throws IOException,SQLException,Exception {
        WS_AMT_FLG = 'N';
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            WS_EWA_AMT = BPCUGMC.INFO.AMTS[WS_I-1].AMT;
            if (WS_EWA_AMT != 0) {
                WS_AMT_FLG = 'Y';
            }
        }
        if (WS_AMT_FLG == 'Y') {
            R000_WRITE_EVENT_BC();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_EVENT_BC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.MOD_NO = WS_MOD_OLD;
        BPCPOEWA.DATA.CNTR_TYPE = BPCUGMC.INFO.CNTR_TYPE;
        BPCPOEWA.DATA.PROD_CODE = WS_PROD_OLD;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        BPCPOEWA.DATA.BR_NEW = BPCUGMC.INFO.BR_NEW;
        BPCPOEWA.DATA.BR_OLD = BPCUGMC.INFO.BR_OLD;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUGMC.INFO.CCY_INFO[1-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[2-1].CCY = BPCUGMC.INFO.CCY_INFO[2-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[3-1].CCY = BPCUGMC.INFO.CCY_INFO[3-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[4-1].CCY = BPCUGMC.INFO.CCY_INFO[4-1].CCY;
        BPCPOEWA.DATA.CCY_INFO[5-1].CCY = BPCUGMC.INFO.CCY_INFO[5-1].CCY;
        BPCPOEWA.DATA.CI_NO = BPCUGMC.INFO.CI_NO;
        BPCPOEWA.DATA.AC_NO = BPCUGMC.INFO.AC_NO;
        BPCPOEWA.DATA.REF_NO = BPCUGMC.INFO.REL_SEQ;
        BPCPOEWA.DATA.EFF_DAYS = BPCUGMC.INFO.EFF_DAYS;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (BPCUGMC.INFO.CHG_FLG == 'B') {
                BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM1 = BPCUCNGM.DATA[WS_I-1].GLMST;
            } else {
                BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM1 = BPCUGMC.INFO.GLMS[WS_I-1].GLM1;
            }
        }
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = BPCUGMC.INFO.AMTS[WS_I-1].AMT;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_FETCH_FMDBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI1);
        AIRPAI1.KEY.TYP = "PAI01";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRPAI1.KEY.CD = "FMD";
        IBS.CPY2CLS(SCCGWA, AIRPAI1.KEY.CD, AIRPAI1.KEY.REDEFINES6);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = AIRPAI1;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        WS_FMD_BR = AIRPAI1.DATA_TXT.DATA_INF.BR;
        CEP.TRC(SCCGWA, WS_FMD_BR);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_FND_FLG = 'N';
            } else {
                WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR);
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
        CEP.TRC(SCCGWA, BPCUGMC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUGMC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUGMC = ");
            CEP.TRC(SCCGWA, BPCUGMC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
