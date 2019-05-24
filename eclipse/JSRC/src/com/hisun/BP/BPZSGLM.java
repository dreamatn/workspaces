package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSGLM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "AMGLM";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP210";
    int WS_ITM_CNT = 0;
    String WS_ITM_NO_TMP = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    BPZSGLM_WS_KEY WS_KEY = new BPZSGLM_WS_KEY();
    BPZSGLM_WS_VAL WS_VAL = new BPZSGLM_WS_VAL();
    char WS_ITM = ' ';
    char WS_FND_FLG = ' ';
    char WS_EMPTY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCUGLM BPCUGLM = new BPCUGLM();
    AICPQITM AICQITM = new AICPQITM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCOGLMB BPCOGLMB = new BPCOGLMB();
    BPCOGLMM BPCOGLMM = new BPCOGLMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOACM BPCOACM = new BPCOACM();
    SCCGWA SCCGWA;
    BPCSGLM BPCSGLM;
    public void MP(SCCGWA SCCGWA, BPCSGLM BPCSGLM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSGLM = BPCSGLM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSGLM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUGLM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSGLM.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA_MAINTAIN();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSGLM.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA_MAINTAIN();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSGLM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA_MAINTAIN();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSGLM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA_MAINTAIN();
            if (pgmRtn) return;
            S000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSGLM.INFO.FUNC == 'B') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.EXP_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.P_EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.P_EXP_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_TIME);
        if (BPCSGLM.INFO.FUNC == 'A' 
            || BPCSGLM.INFO.FUNC == 'U' 
            || BPCSGLM.INFO.FUNC == 'D' 
            || BPCSGLM.INFO.FUNC == 'I') {
            if (BPCSGLM.DATA.KEY.REDEFINES15.MSTNO == ' ') {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSGLM.INFO.FUNC == 'A' 
            || BPCSGLM.INFO.FUNC == 'U') {
            if (BPCSGLM.DATA.DATA_TXT.LNAME.trim().length() == 0) {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_GLMST_NM_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSGLM.DATA.DATA_TXT.CNTY1.trim().length() == 0) {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ITM = 'Y';
            for (WS_I = 1; WS_I <= 60; WS_I += 1) {
                CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO);
                CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_SEQ);
                if (BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO.trim().length() > 0) {
                    WS_ITM = 'N';
                    WS_K = WS_I + 1;
                    for (WS_J = WS_K; WS_J <= 60; WS_J += 1) {
                        if (BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO.equalsIgnoreCase(BPCSGLM.DATA.DATA_TXT.REL_ITMS[WS_J-1].ITM_NO)) {
                            CEP.TRC(SCCGWA, "DAYSUNRI");
                            CEP.TRC(SCCGWA, "RIHOUZAISHUO");
                        }
                    }
                }
            }
            if (WS_ITM == 'Y') {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_ITM_NO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUGLM);
        BPCUGLM.INFO.FUNC = 'I';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUGLM.DATA.KEY);
        BPCUGLM.DATA.P_EFF_DATE = BPCSGLM.DATA.P_EFF_DATE;
        S000_CALL_BPZUGLM();
        if (pgmRtn) return;
    }
    public void B021_MOVE_DATA_MAINTAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOGLMM);
        CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.COA_FLG);
        CEP.TRC(SCCGWA, BPCUGLM.DATA.KEY.REDEFINES16.MSTNO);
        BPCOGLMM.MSTNO = BPCUGLM.DATA.KEY.REDEFINES16.MSTNO;
        BPCOGLMM.COA_FLG = BPCUGLM.DATA.DATA_TXT.COA_FLG;
        BPCOGLMM.SNAME = BPCUGLM.DATA.DATA_TXT.SNAME;
        BPCOGLMM.LNAME = BPCUGLM.DATA.DATA_TXT.LNAME;
        BPCOGLMM.EFF_DATE = BPCUGLM.DATA.DATA_TXT.EFF_DATE;
        BPCOGLMM.EXP_DATE = BPCUGLM.DATA.DATA_TXT.EXP_DATE;
        BPCOGLMM.CNTY1 = BPCUGLM.DATA.DATA_TXT.CNTY1;
        BPCOGLMM.CNTY2 = BPCUGLM.DATA.DATA_TXT.CNTY2;
        BPCOGLMM.CNTY3 = BPCUGLM.DATA.DATA_TXT.CNTY3;
        BPCOGLMM.CKFLG = BPCUGLM.DATA.DATA_TXT.CKFLG;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            if (!BPCUGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO.equalsIgnoreCase("0")) {
                BPCOGLMM.REL_ITMS[WS_I-1].ITM_NO = BPCUGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO;
                BPCOGLMM.REL_ITMS[WS_I-1].ITM_SEQ = BPCUGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_SEQ;
                IBS.init(SCCGWA, AICQITM);
                AICQITM.INPUT_DATA.COA_FLG = BPCUGLM.DATA.DATA_TXT.COA_FLG;
                AICQITM.INPUT_DATA.NO = BPCUGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO;
                CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.COA_FLG);
                CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.REL_ITMS[WS_I-1].ITM_NO);
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
                BPCOGLMM.REL_ITMS[WS_I-1].ITM_CNM = AICQITM.OUTPUT_DATA.CHS_NM;
            }
        }
    }
    public void B022_MOVE_DATA_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOGLMB);
        BPCOGLMB.MSTNO = WS_KEY.WS_MSTNO;
        BPCOGLMB.LNAME = WS_VAL.WS_LNAME;
        BPCOGLMB.EFF_DATE = WS_VAL.WS_EFF_DATE;
        BPCOGLMB.EXP_DATE = WS_VAL.WS_EXP_DATE;
        BPCOGLMB.P_EFF_DATE = BPRPARM.EFF_DATE;
        BPCOGLMB.P_EXP_DATE = BPRPARM.EXP_DATE;
        BPCOGLMB.CNTY1 = WS_VAL.WS_CNTY1;
        BPCOGLMB.CNTY2 = WS_VAL.WS_CNTY2;
        BPCOGLMB.CNTY3 = WS_VAL.WS_CNTY3;
        BPCOGLMB.CKFLG = WS_VAL.WS_CKFLG;
        BPCOGLMB.REAL_GL = WS_VAL.WS_REAL_GL;
        BPCOGLMB.MEMO_GL = WS_VAL.WS_MEMO_GL;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUGLM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUGLM.DATA);
        BPCUGLM.DATA.DATA_LEN = 4955;
        BPCUGLM.INFO.FUNC = 'A';
        S000_CALL_BPZUGLM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUGLM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUGLM.DATA);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_TIME);
        BPCUGLM.INFO.FUNC = 'U';
        S000_CALL_BPZUGLM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUGLM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUGLM.DATA.KEY);
        BPCUGLM.DATA.DATA_TXT.UPD_DATE = BPCSGLM.DATA.DATA_TXT.UPD_DATE;
        BPCUGLM.DATA.DATA_TXT.UPD_TIME = BPCSGLM.DATA.DATA_TXT.UPD_TIME;
        BPCUGLM.DATA.DATA_TXT.EFF_DATE = BPCSGLM.DATA.DATA_TXT.EFF_DATE;
        BPCUGLM.DATA.DATA_TXT.EFF_DATE = BPCSGLM.DATA.DATA_TXT.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSGLM.DATA.P_EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.P_EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_DATE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.UPD_TIME);
        CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.UPD_DATE);
        CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.UPD_TIME);
        BPCUGLM.INFO.FUNC = 'D';
        S000_CALL_BPZUGLM();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
        S000_INITIALIZE_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        CEP.TRC(SCCGWA, K_PARM_TYPE);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.KEY);
        CEP.TRC(SCCGWA, BPCSGLM.DATA.KEY.CD);
        WS_EMPTY_FLG = 'Y';
        BPRPARM.KEY.TYPE = K_PARM_TYPE;
        BPRPARM.KEY.CODE = BPCSGLM.DATA.KEY.CD;
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'N' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_VAL);
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_KEY);
            CEP.TRC(SCCGWA, BPCSGLM.DATA.KEY.REDEFINES15.MSTNO);
            CEP.TRC(SCCGWA, WS_KEY.WS_MSTNO);
            CEP.TRC(SCCGWA, WS_VAL.WS_OPT_FLG);
            CEP.TRC(SCCGWA, BPCSGLM.DATA.DATA_TXT.OPT_FLG);
            if (WS_KEY.WS_MSTNO >= BPCSGLM.DATA.KEY.REDEFINES15.MSTNO 
                && WS_VAL.WS_OPT_FLG == BPCSGLM.DATA.DATA_TXT.OPT_FLG) {
                WS_CNT1 = WS_CNT1 + 1;
                CEP.TRC(SCCGWA, WS_CNT1);
                CEP.TRC(SCCGWA, WS_VAL.WS_CNTY1);
                CEP.TRC(SCCGWA, WS_VAL.WS_CNTY2);
                CEP.TRC(SCCGWA, WS_VAL.WS_CNTY3);
                CEP.TRC(SCCGWA, BPCSGLM.CN_TYPE);
                if (BPCSGLM.CN_TYPE.trim().length() == 0) {
                    WS_EMPTY_FLG = 'N';
                    B022_MOVE_DATA_BROWSE();
                    if (pgmRtn) return;
                    S000_WRITE_TS();
                    if (pgmRtn) return;
                } else {
                    if (BPCSGLM.CN_TYPE.equalsIgnoreCase(WS_VAL.WS_CNTY1) 
                        || BPCSGLM.CN_TYPE.equalsIgnoreCase(WS_VAL.WS_CNTY2) 
                        || BPCSGLM.CN_TYPE.equalsIgnoreCase(WS_VAL.WS_CNTY3)) {
                        WS_EMPTY_FLG = 'N';
                        B022_MOVE_DATA_BROWSE();
                        if (pgmRtn) return;
                        S000_WRITE_TS();
                        if (pgmRtn) return;
                    }
                }
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT1);
        if (WS_CNT1 == 0 
            || WS_EMPTY_FLG == 'Y') {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC.RC_CODE);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
    }
    public void S000_CALL_BPZUGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-GLM", BPCUGLM);
        if (BPCUGLM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_FND_FLG = 'N';
            } else {
                WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSGLM.INFO.FUNC == 'I') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = BPCOGLMM;
        SCCFMT.DATA_LEN = 8445;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_INITIALIZE_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 194;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, AICQITM.RC);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, AICQITM.RC);
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOGLMB);
        SCCMPAG.DATA_LEN = 194;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_INFO, WS_FLD_NO);
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
