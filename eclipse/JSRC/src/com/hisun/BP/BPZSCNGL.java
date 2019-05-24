package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCNGL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm AITGLM_RD;
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP230";
    String K_OUTPUT_FMT_1 = "BPX01";
    String K_S_MAINT_PRDT_PARM = "BP-S-MAINT-PRDT-PARM";
    String K_S_QUERY_PRDT_PARM = "BP-P-QUERY-PRDT-PARM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_LEN = 0;
    int WS_CNT1 = 0;
    String WS_OTH = " ";
    BPZSCNGL_REDEFINES6 REDEFINES6 = new BPZSCNGL_REDEFINES6();
    BPZSCNGL_WS_KEY WS_KEY = new BPZSCNGL_WS_KEY();
    BPZSCNGL_WS_EXLGL WS_EXLGL = new BPZSCNGL_WS_EXLGL();
    BPZSCNGL_WS_CNGL_INFO WS_CNGL_INFO = new BPZSCNGL_WS_CNGL_INFO();
    int WS_J = 0;
    BPZSCNGL_WS_GLM_KEY WS_GLM_KEY = new BPZSCNGL_WS_GLM_KEY();
    short WS_GLM_LAST_BEGSEQ = 0;
    char WS_REC_INFO = ' ';
    char WS_RETURN_CNGM_INFO = ' ';
    char WS_RETURN_GLM_INFO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCUCNGL BPCUCNGL = new BPCUCNGL();
    BPCCNGLO BPCCNGLO = new BPCCNGLO();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSMPPR BPCSMPPR = new BPCSMPPR();
    BPCPQPPR BPCPQPPR = new BPCPQPPR();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AIRGLM AIRGLM = new AIRGLM();
    BPCPRMBR BPCPRMBR = new BPCPRMBR();
    SCCGWA SCCGWA;
    BPCSCNGL BPCSCNGL;
    BPB4060_AWA_4060 BPB4060_AWA_4060;
    SCCCWA SCCCWA;
    public void MP(SCCGWA SCCGWA, BPCSCNGL BPCSCNGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCNGL = BPCSCNGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCNGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4060_AWA_4060>");
        BPB4060_AWA_4060 = (BPB4060_AWA_4060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCUCNGL);
        IBS.init(SCCGWA, BPCCNGLO);
        CEP.TRC(SCCGWA, BPCSCNGL);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        R000_SET_LK_MMT();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSCNGL.INFO.FUNC == 'I'
            || BPCSCNGL.INFO.FUNC == 'R'
            || BPCSCNGL.INFO.FUNC == 'M') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSCNGL.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSCNGL.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSCNGL.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSCNGL.INFO.FUNC == 'B') {
            CEP.TRC(SCCGWA, BPB4060_AWA_4060.OTH);
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNGL.INFO.FUNC);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.OTH);
        if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
        JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
        if (BPCSCNGL.DAT.KEY.OTH.substring(0, 10).equalsIgnoreCase("*")) {
            if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
            JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
            BPCSCNGL.DAT.KEY.OTH = "          " + BPCSCNGL.DAT.KEY.OTH.substring(10);
        }
        if (BPCSCNGL.INFO.FUNC == 'A' 
            || BPCSCNGL.INFO.FUNC == 'U') {
            CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BR);
            CEP.TRC(SCCGWA, BPCSCNGL.DAT.TXT.MSTNO);
            CEP.TRC(SCCGWA, BPCSCNGL.DAT.TXT.BR_OLD);
            CEP.TRC(SCCGWA, BPCSCNGL.DAT.TXT.OTH_OLD);
            IBS.init(SCCGWA, AIRGLM);
            AIRGLM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            AIRGLM.KEY.GL_MSTNO = BPCSCNGL.DAT.TXT.MSTNO;
            T000_READ_AITGLM();
            if (pgmRtn) return;
            if (WS_RETURN_GLM_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AITGLM_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AIRGLM.CNTY1);
            CEP.TRC(SCCGWA, AIRGLM.CNTY2);
            CEP.TRC(SCCGWA, AIRGLM.CNTY3);
            CEP.TRC(SCCGWA, AIRGLM.KEY.EFF_DT);
            CEP.TRC(SCCGWA, AIRGLM.KEY.EXP_DT);
            if (SCCGWA.COMM_AREA.AC_DATE < AIRGLM.KEY.EFF_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.AM_GLMST_EFF_DT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.AC_DATE >= AIRGLM.KEY.EXP_DT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.AM_GLMST_EXP_DT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase(AIRGLM.CNTY1) 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase(AIRGLM.CNTY2) 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase(AIRGLM.CNTY3)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GLM_NOT_MATCH_CNTR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSCNGL.DAT.KEY.CNTR_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("GL") 
                || !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("FEEV")) {
                BPCPQPDM.PRDT_MODEL = BPCSCNGL.DAT.KEY.CNTR_TYPE;
                S000_CALL_BPZPQPDM();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.CNTR_TYPE);
            WS_OTH = BPCSCNGL.DAT.KEY.OTH;
            IBS.CPY2CLS(SCCGWA, WS_OTH, REDEFINES6);
            CEP.TRC(SCCGWA, REDEFINES6.WS_ACCMD_PROD_TYPE);
            if (BPCSCNGL.DAT.KEY.BOOK_FLG.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                BPCQBKPM.FUNC = 'Q';
                BPCQBKPM.KEY.BK_FLG = BPCSCNGL.DAT.KEY.BOOK_FLG;
                S000_CALL_BPZQBKPM();
                if (pgmRtn) return;
            }
            if (BPCSCNGL.DAT.KEY.BR != 0 
                && BPCSCNGL.DAT.KEY.BR != 999999) {
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = BPCSCNGL.DAT.KEY.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            if ((REDEFINES6.WS_ACCMD_PROD_TYPE.trim().length() > 0 
                && !REDEFINES6.WS_ACCMD_PROD_TYPE.equalsIgnoreCase("*") 
                && !REDEFINES6.WS_ACCMD_PROD_TYPE.equalsIgnoreCase("0") 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("CS") 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("CAS") 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("BVF") 
                && !BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("FEES"))) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = REDEFINES6.WS_ACCMD_PROD_TYPE;
                S010_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
                CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
                if (SCCGWA.COMM_AREA.AC_DATE >= BPCPQPRD.EXP_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_STS_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (SCCGWA.COMM_AREA.AC_DATE < BPCPQPRD.EFF_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AIRGLM.KEY.EXP_DT < BPCPQPRD.EXP_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PROD_NE_GLM_EXPDT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("FEES")) {
                IBS.init(SCCGWA, BPCOUBAS);
                BPCOUBAS.FUNC = 'I';
                BPCOUBAS.KEY.FEE_CODE = REDEFINES6.WS_ACCMD_PROD_TYPE;
                S000_CALL_BPZFUBAS();
                if (pgmRtn) return;
            }
            if (BPCSCNGL.DAT.KEY.CNTR_TYPE.equalsIgnoreCase("BVF")) {
                IBS.init(SCCGWA, BPCFBVQU);
                BPCFBVQU.TX_DATA.KEY.CODE = REDEFINES6.WS_ACCMD_PROD_TYPE;
                S000_CALL_BPZFBVQU();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCUCNGL.FUNC = 'I';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.DAT.KEY);
        if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
        JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.OTH.substring(0, 10));
        if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
        JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.OTH.substring(11 - 1, 11 + 10 - 1));
        if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
        JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.OTH.substring(40 - 1, 40 + 10 - 1));
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCNGL.DAT);
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPCSCNGL.INFO.FUNC == 'R') {
            BPCSCNGL.INFO.FUNC = 'D';
            SCCSUBS.TR_CODE = 4063;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        }
        if (BPCSCNGL.INFO.FUNC == 'M') {
            BPCSCNGL.INFO.FUNC = 'U';
            SCCSUBS.TR_CODE = 4062;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        }
        K_OUTPUT_FMT = K_OUTPUT_FMT_1;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCUCNGL.FUNC = 'A';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.DAT);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.TXT);
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCUCNGL.FUNC = 'U';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.DAT);
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY);
        BPCUCNGL.FUNC = 'D';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.DAT.KEY);
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCNGL.DAT);
    }
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
        WS_OTH = " ";
        BPCUCNGL.FUNC = 'S';
        WS_REC_INFO = 'Y';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.DAT.KEY);
        if (BPB4060_AWA_4060.OTH == null) BPB4060_AWA_4060.OTH = "";
        JIBS_tmp_int = BPB4060_AWA_4060.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPB4060_AWA_4060.OTH += " ";
        REDEFINES6.WS_ACCMD_PROD_TYPE = BPB4060_AWA_4060.OTH.substring(0, 10);
        WS_OTH = IBS.CLS2CPY(SCCGWA, REDEFINES6);
        BPCUCNGL.DAT.KEY.OTH = WS_OTH;
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY);
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        while (WS_REC_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCUCNGL);
            BPCUCNGL.FUNC = 'N';
            S000_CALL_BPZUCNGL();
            if (pgmRtn) return;
            if (WS_REC_INFO != 'N') {
                CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.CNTR_TYPE);
                CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY.CNTR_TYPE);
                CEP.TRC(SCCGWA, BPCUCNGL);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCNGL.DAT);
                CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BR);
                CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BR);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                if (BPCSCNGL.DAT.KEY.OTH.substring(0, 10).trim().length() > 0) {
                    if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                    JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                    WS_EXLGL.WS_CNGL_PRDT_CODE = BPCSCNGL.DAT.KEY.OTH.substring(0, 10);
                }
                WS_CNGL_INFO.WS_CNGL_CNTR_TYPE = BPCSCNGL.DAT.KEY.CNTR_TYPE;
                WS_CNGL_INFO.WS_CNGL_BOOK_FLG = BPCSCNGL.DAT.KEY.BOOK_FLG;
                WS_CNGL_INFO.WS_CNGL_BR = BPCSCNGL.DAT.KEY.BR;
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_PROD_TYPE = BPCSCNGL.DAT.KEY.OTH.substring(0, 10);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_CI_TYP = BPCSCNGL.DAT.KEY.OTH.substring(11 - 1, 11 + 1 - 1).charAt(0);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_FIN_TYP = BPCSCNGL.DAT.KEY.OTH.substring(12 - 1, 12 + 8 - 1);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_AC_TYP = BPCSCNGL.DAT.KEY.OTH.substring(20 - 1, 20 + 1 - 1).charAt(0);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_PROP_TYP = BPCSCNGL.DAT.KEY.OTH.substring(21 - 1, 21 + 1 - 1).charAt(0);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_TERM_CD = BPCSCNGL.DAT.KEY.OTH.substring(22 - 1, 22 + 4 - 1);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_LOAN_TYPE = BPCSCNGL.DAT.KEY.OTH.substring(26 - 1, 26 + 4 - 1);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_WE_FLG = BPCSCNGL.DAT.KEY.OTH.substring(30 - 1, 30 + 1 - 1).charAt(0);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_DUTY_FREE = BPCSCNGL.DAT.KEY.OTH.substring(31 - 1, 31 + 1 - 1).charAt(0);
                if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
                JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
                WS_CNGL_INFO.WS_O_BUS_MOD = BPCSCNGL.DAT.KEY.OTH.substring(32 - 1, 32 + 1 - 1).charAt(0);
                WS_CNGL_INFO.WS_CNGL_MSTNO = BPCSCNGL.DAT.TXT.MSTNO;
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_CNTR_TYPE);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_MODEL_NM);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_PRDT_NM);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_PARM_NM);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_BOOK_FLG);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_PROD_TYPE);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_CI_TYP);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_FIN_TYP);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_AC_TYP);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_PROP_TYP);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_TERM_CD);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_LOAN_TYPE);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_WE_FLG);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_DUTY_FREE);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_O_BUS_MOD);
                CEP.TRC(SCCGWA, WS_CNGL_INFO.WS_CNGL_MSTNO);
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        BPCUCNGL.FUNC = 'E';
        S000_CALL_BPZUCNGL();
        if (pgmRtn) return;
        if (WS_CNT1 == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOUBAS);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-BV-PRM-QUERY", BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP ", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGL", BPCUCNGL);
        CEP.TRC(SCCGWA, BPCUCNGL.RC);
        if (BPCUCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.RC);
            if (BPCUCNGL.FUNC == 'N' 
                && JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST)) {
                WS_REC_INFO = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCCNGLO.INFO.FUNC = BPCSCNGL.INFO.FUNC;
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BR);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.BR);
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.KEY.CNTR_TYPE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNGL.DAT.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCNGLO.DAT.KEY);
        BPCCNGLO.DAT.TXT.MSTNO = BPCSCNGL.DAT.TXT.MSTNO;
        CEP.TRC(SCCGWA, BPCCNGLO.DAT.TXT);
        if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
        JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
        if (BPCSCNGL.DAT.KEY.OTH.substring(0, 10).trim().length() == 0) {
            if (BPCCNGLO.DAT.TXT.OTH1 == null) BPCCNGLO.DAT.TXT.OTH1 = "";
            JIBS_tmp_int = BPCCNGLO.DAT.TXT.OTH1.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BPCCNGLO.DAT.TXT.OTH1 += " ";
            BPCCNGLO.DAT.TXT.OTH1 = "*" + BPCCNGLO.DAT.TXT.OTH1.substring(10);
            if (BPCSCNGL.DAT.KEY.OTH == null) BPCSCNGL.DAT.KEY.OTH = "";
            JIBS_tmp_int = BPCSCNGL.DAT.KEY.OTH.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BPCSCNGL.DAT.KEY.OTH += " ";
            if (BPCCNGLO.DAT.TXT.OTH1 == null) BPCCNGLO.DAT.TXT.OTH1 = "";
            JIBS_tmp_int = BPCCNGLO.DAT.TXT.OTH1.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BPCCNGLO.DAT.TXT.OTH1 += " ";
            BPCCNGLO.DAT.TXT.OTH1 = BPCCNGLO.DAT.TXT.OTH1.substring(0, 11 - 1) + BPCSCNGL.DAT.KEY.OTH.substring(11 - 1, 11 + 40 - 1) + BPCCNGLO.DAT.TXT.OTH1.substring(11 + 40 - 1);
        } else {
            BPCCNGLO.DAT.TXT.OTH1 = BPCSCNGL.DAT.KEY.OTH;
        }
        CEP.TRC(SCCGWA, BPCSCNGL.DAT.TXT);
        CEP.TRC(SCCGWA, BPCCNGLO.DAT.TXT.OTH1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCCNGLO;
        SCCFMT.DATA_LEN = 124;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 436;
        SCCMPAG.SCR_ROW_CNT = 10;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        WS_CNT1 = WS_CNT1 + 1;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, WS_CNGL_INFO);
        CEP.TRC(SCCGWA, WS_CNGL_INFO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CNGL_INFO);
        SCCMPAG.DATA_LEN = 436;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_AITGLM() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            WS_J = 0;
            WS_J = 1;
            SCCGWA.COMM_AREA.DBIO_FLG = '0';
            WS_RETURN_GLM_INFO = 'N';
            IBS.init(SCCGWA, BPCPRMBR.CD);
            BPCPRMBR.TYP = "AITGLM";
            BPCPRMBR.CD.KEY = IBS.CLS2CPY(SCCGWA, WS_GLM_KEY);
            BPCPRMBR.LTH = 4767;
            BPCPRMBR.CD.KEY_KEYSEQ = (short) WS_J;
            BPCPRMBR.CD.KEY_BEGSEQ = WS_GLM_LAST_BEGSEQ;
            AIRGLM = (AIRGLM) IBS.HASHLKUP(SCCGWA, "AIRGLM", BPCPRMBR.CD);
            WS_J += 1;
            WS_GLM_LAST_BEGSEQ = 0;
            WS_GLM_LAST_BEGSEQ = BPCPRMBR.CD.KEY_BEGSEQ;
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                AITGLM_RD = new DBParm();
                AITGLM_RD.TableName = "AITGLM";
                AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
                    + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO";
                IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
            }
        } else {
            AITGLM_RD = new DBParm();
            AITGLM_RD.TableName = "AITGLM";
            AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
                + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO";
            IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
        }
    } else { //FROM #ELSE
        AITGLM_RD = new DBParm();
        AITGLM_RD.TableName = "AITGLM";
        AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
            + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO";
        IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_GLM_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_GLM_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGLM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S010_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
    }
    public void S010_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
    }
    public void S000_CALL_BPZSMPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_MAINT_PRDT_PARM, BPCSMPPR);
    }
    public void S000_CALL_BPZPQPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_QUERY_PRDT_PARM, BPCPQPPR);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
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
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPCPRMBR.TYP.equalsIgnoreCase("CITDTL")
            || BPCPRMBR.TYP.equalsIgnoreCase("CITDINF")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITENTY")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITGLM")) {
            BPCPRMBR.I = 12;
        } else {
            BPCPRMBR.I = 12;
        }
        if (SCCCWA.PARM_IN_USE[BPCPRMBR.I-1] == 1) {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR1_AREA[BPCPRMBR.I-1].PARM_PTR1;
        } else {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR2_AREA[BPCPRMBR.I-1].PARM_PTR2;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
