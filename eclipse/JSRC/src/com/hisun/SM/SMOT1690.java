package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1690 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMOT1690_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1690_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    SMOT1690_WS_TS_CNTL WS_TS_CNTL = new SMOT1690_WS_TS_CNTL();
    SMOT1690_WS_SPE_OUT WS_SPE_OUT = new SMOT1690_WS_SPE_OUT();
    SMOT1690_WS_REC_TXT_OUT WS_REC_TXT_OUT = new SMOT1690_WS_REC_TXT_OUT();
    BPCXP30 BPCXP30 = new BPCXP30();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRCWA BPRCWA = new BPRCWA();
    SCCGWA SCCGWA;
    SMB1690_AWA_1690 SMB1690_AWA_1690;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS_TSQOUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1690 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1690_AWA_1690>");
        SMB1690_AWA_1690 = (SMB1690_AWA_1690) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRCWA);
        BPCPRMM.RC.RC_APP = "SM";
        BPCPRMM.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS_TSQOUT() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, SMB1690_AWA_1690.TXT, BPRCWA.DATA_TXT.TXT);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 999; WS_TEMP_VARIABLE.WS_I += 1) {
            if (WS_TEMP_VARIABLE.WS_I <= 120) {
                if (BPRCWA.DATA_TXT.TXT.AP_TXT.AP[WS_TEMP_VARIABLE.WS_I-1].AP_MMO.trim().length() > 0 
                    || BPRCWA.DATA_TXT.TXT.FLOW[WS_TEMP_VARIABLE.WS_I-1].FLOW_IND != ' ') {
                    B025_OUT_TXT_RECORD();
                    if (pgmRtn) return;
                }
            } else {
                if (BPRCWA.DATA_TXT.TXT.AP_TXT.AP[WS_TEMP_VARIABLE.WS_I-1].AP_MMO.trim().length() > 0) {
                    B025_OUT_TXT_RECORD();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B005_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = WS_TS_CNTL.WS_TS_MAIN_TIT;
        SCCMPAG.MAX_COL_NO = WS_TS_CNTL.WS_TS_MAX_RECLEN;
        SCCMPAG.SUBT_ROW_CNT = WS_TS_CNTL.WS_TS_TIT_NUM;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
        WS_SPE_OUT.WS_OUT_FUNC = SMB1690_AWA_1690.FUNC;
        WS_SPE_OUT.WS_OUT_PTYP = SMB1690_AWA_1690.PTYP;
        WS_SPE_OUT.WS_OUT_CODE = SMB1690_AWA_1690.CODE;
        WS_SPE_OUT.WS_OUT_DESC = SMB1690_AWA_1690.DESC;
        WS_SPE_OUT.WS_OUT_CDESC = SMB1690_AWA_1690.CDESC;
        WS_SPE_OUT.WS_CDESC_SIGNID = 0X02;
        WS_SPE_OUT.WS_OUT_EFFDATE = "" + SMB1690_AWA_1690.EFFDATE;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_EFFDATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_EFFDATE = "0" + WS_SPE_OUT.WS_OUT_EFFDATE;
        WS_SPE_OUT.WS_OUT_EXPDATE = "" + SMB1690_AWA_1690.EXPDATE;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_EXPDATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_EXPDATE = "0" + WS_SPE_OUT.WS_OUT_EXPDATE;
        WS_SPE_OUT.WS_OUT_LEN = "" + SMB1690_AWA_1690.LEN;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_LEN.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_LEN = "0" + WS_SPE_OUT.WS_OUT_LEN;
        WS_SPE_OUT.WS_OUT_VERSION_NO = "" + SMB1690_AWA_1690.VERSION;
        JIBS_tmp_int = WS_SPE_OUT.WS_OUT_VERSION_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_SPE_OUT.WS_OUT_VERSION_NO = "0" + WS_SPE_OUT.WS_OUT_VERSION_NO;
        WS_SPE_OUT.WS_OUT_SYS_ID = SMB1690_AWA_1690.SYS_ID;
        WS_SPE_OUT.WS_OUT_SYS_DEST = SMB1690_AWA_1690.SYS_DEST;
        WS_SPE_OUT.WS_OUT_SYS_STUS = SMB1690_AWA_1690.SYS_STUS;
        WS_SPE_OUT.WS_OUT_SYS_MODE = SMB1690_AWA_1690.SYS_MODE;
        WS_SPE_OUT.WS_OUT_SYS_MODE_ID = SMB1690_AWA_1690.SYS_MODI;
        WS_SPE_OUT.WS_OUT_REEN_MODE = SMB1690_AWA_1690.REEN_MOD;
        WS_SPE_OUT.WS_OUT_ACT_TR_LMT = SMB1690_AWA_1690.ACT_TR;
        WS_SPE_OUT.WS_OUT_ACT_LTR_LMT = SMB1690_AWA_1690.ACT_LTR;
        WS_SPE_OUT.WS_OUT_WARN_JRN_NO = (int) SMB1690_AWA_1690.WARN_JRN;
        WS_SPE_OUT.WS_OUT_NEXT_JRN_NO = (int) SMB1690_AWA_1690.NEXT_JRN;
        WS_SPE_OUT.WS_OUT_JRN_IN_USE = SMB1690_AWA_1690.JRN_IN_U;
        WS_SPE_OUT.WS_OUT_MST_IN_USE = SMB1690_AWA_1690.MST_IN_U;
        WS_SPE_OUT.WS_OUT_PARM_IN_USE = SMB1690_AWA_1690.PARM_IN;
        WS_SPE_OUT.WS_OUT_AC_DATE_MD = SMB1690_AWA_1690.AC_DATE;
        WS_SPE_OUT.WS_OUT_LAST_AC_DATE = SMB1690_AWA_1690.LAST_AC;
        WS_SPE_OUT.WS_OUT_FBAL_E_WORD = SMB1690_AWA_1690.FBAL_E_W;
        WS_SPE_OUT.WS_OUT_FBAL_C_WORD = SMB1690_AWA_1690.FBAL_C_W;
        WS_SPE_OUT.WS_OUT_ET_FIN_DATE = SMB1690_AWA_1690.ET_FIN_D;
        WS_SPE_OUT.WS_OUT_BTR1_TR_LMT = SMB1690_AWA_1690.BTR1_TR;
        WS_SPE_OUT.WS_OUT_BTR2_TR_LMT = SMB1690_AWA_1690.BTR2_TR;
        WS_SPE_OUT.WS_OUT_BTP_JOB_LMT = SMB1690_AWA_1690.BTP_JOB;
        WS_SPE_OUT.WS_OUT_MBT_STATUS = SMB1690_AWA_1690.MBT_STAT;
        WS_SPE_OUT.WS_OUT_TPH_IND = SMB1690_AWA_1690.TPH_IND;
        WS_SPE_OUT.WS_OUT_LOG_IND = SMB1690_AWA_1690.LOG_IND;
        WS_SPE_OUT.WS_OUT_AI_SETTLE_IND = SMB1690_AWA_1690.AI_SETTL;
        WS_SPE_OUT.WS_OUT_ECGO_IND = SMB1690_AWA_1690.ECGO_IND;
        if (SMB1690_AWA_1690.HQ_BK.trim().length() == 0) WS_SPE_OUT.WS_OUT_HQ_BK = 0;
        else WS_SPE_OUT.WS_OUT_HQ_BK = Short.parseShort(SMB1690_AWA_1690.HQ_BK);
        WS_SPE_OUT.WS_OUT_LOCAL_CCY = SMB1690_AWA_1690.LOCAL_CC;
        WS_SPE_OUT.WS_OUT_APVL_SEQ = SMB1690_AWA_1690.APVL_SEQ;
        WS_SPE_OUT.WS_OUT_ABROAD_OPER = SMB1690_AWA_1690.ABROAD_O;
        WS_SPE_OUT.WS_OUT_EX_POS_SEQ = SMB1690_AWA_1690.EX_POS_S;
        if (SMB1690_AWA_1690.CODE.trim().length() == 0) {
            WS_SPE_OUT.WS_OUT_TIME_DIF = 0;
        } else {
            WS_SPE_OUT.WS_OUT_TIME_DIF = SMB1690_AWA_1690.TIME_DIF;
        }
        WS_SPE_OUT.WS_SIGNID = 0X01;
        if (SMB1690_AWA_1690.LOCAL_BK.trim().length() == 0) WS_SPE_OUT.WS_OUT_LOCAL_BK_NO = 0;
        else WS_SPE_OUT.WS_OUT_LOCAL_BK_NO = Short.parseShort(SMB1690_AWA_1690.LOCAL_BK);
        WS_SPE_OUT.WS_OUT_BR_NO = SMB1690_AWA_1690.BR_NO;
        WS_SPE_OUT.WS_OUT_HOLD_CCY = SMB1690_AWA_1690.HOLD_CCY;
        WS_SPE_OUT.WS_OUT_REGION_CODE = SMB1690_AWA_1690.REGION_C;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SPE_OUT);
        WS_TEMP_VARIABLE.WS_LEN = 290;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B025_OUT_TXT_RECORD() throws IOException,SQLException,Exception {
        WS_REC_TXT_OUT.WS_REC_AP_NO = WS_TEMP_VARIABLE.WS_I;
        WS_REC_TXT_OUT.WS_REC_AP_DATA = IBS.CLS2CPY(SCCGWA, BPRCWA.DATA_TXT.TXT.AP_TXT.AP[WS_TEMP_VARIABLE.WS_I-1]);
        if (WS_TEMP_VARIABLE.WS_I <= 120) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCWA.DATA_TXT.TXT.FLOW[WS_TEMP_VARIABLE.WS_I-1]);
            WS_REC_TXT_OUT.WS_REC_FLOW_DATA = JIBS_tmp_str[0].charAt(0);
        } else {
            WS_REC_TXT_OUT.WS_REC_FLOW_DATA = ' ';
        }
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_TXT_OUT);
        WS_TEMP_VARIABLE.WS_LEN = 300;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TSQ_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
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
