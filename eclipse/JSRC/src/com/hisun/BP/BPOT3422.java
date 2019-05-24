package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCSUBS;
import com.hisun.SC.SCRPRMT;
import com.hisun.SO.SORSYS;

public class BPOT3422 {
    boolean pgmRtn = false;
    String WS_SMSG_MSG_TEXT = " ";
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCRBBSP SCCRBBSP = new SCCRBBSP();
    SCROBDTL SCROBDTL = new SCROBDTL();
    SCCRWBSP SCCRWBSP = new SCCRWBSP();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String K_FMT_CODE = "DD886";
    BPOT3422_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT3422_WS_TEMP_VARIABLE();
    short WS_RC_CODE = 0;
    String WS_TS_REC = "                                                                                                                                                                                                                                                                                                            ";
    short WS_TEMP_NUM = 0;
    short WS_LEN = 0;
    long WS_I = 0;
    short WS_NOT_FND_CNT = 0;
    char WS_OUTPUT_FLG = ' ';
    BPOT3422_WS_TLPM_HEAD WS_TLPM_HEAD = new BPOT3422_WS_TLPM_HEAD();
    BPOT3422_WS_TLPM_DATA WS_TLPM_DATA = new BPOT3422_WS_TLPM_DATA();
    BPOT3422_WS_OUTPUT WS_OUTPUT = new BPOT3422_WS_OUTPUT();
    BPOT3422_WS_PROC_PARM WS_PROC_PARM = new BPOT3422_WS_PROC_PARM();
    BPOT3422_WS_PARM_BY_BATCH_NO WS_PARM_BY_BATCH_NO = new BPOT3422_WS_PARM_BY_BATCH_NO();
    BPOT3422_WS_PROC_INFO WS_PROC_INFO = new BPOT3422_WS_PROC_INFO();
    BPOT3422_WS_MSTD_1 WS_MSTD_1 = new BPOT3422_WS_MSTD_1();
    BPOT3422_WS_MSTD_2 WS_MSTD_2 = new BPOT3422_WS_MSTD_2();
    BPOT3422_WS_MSTD_3 WS_MSTD_3 = new BPOT3422_WS_MSTD_3();
    BPOT3422_WS_MSTD_4 WS_MSTD_4 = new BPOT3422_WS_MSTD_4();
    BPOT3422_WS_MSTD_5 WS_MSTD_5 = new BPOT3422_WS_MSTD_5();
    BPOT3422_WS_MSTD_6 WS_MSTD_6 = new BPOT3422_WS_MSTD_6();
    BPOT3422_WS_MSTD_7 WS_MSTD_7 = new BPOT3422_WS_MSTD_7();
    BPOT3422_WS_MSTD_8 WS_MSTD_8 = new BPOT3422_WS_MSTD_8();
    BPOT3422_WS_MSTD_9 WS_MSTD_9 = new BPOT3422_WS_MSTD_9();
    BPOT3422_WS_MSTD_10 WS_MSTD_10 = new BPOT3422_WS_MSTD_10();
    BPOT3422_WS_MSTD_11 WS_MSTD_11 = new BPOT3422_WS_MSTD_11();
    BPOT3422_WS_MSTD_12 WS_MSTD_12 = new BPOT3422_WS_MSTD_12();
    BPOT3422_WS_MSTD_13 WS_MSTD_13 = new BPOT3422_WS_MSTD_13();
    BPOT3422_WS_MSTD_14 WS_MSTD_14 = new BPOT3422_WS_MSTD_14();
    BPOT3422_WS_MSTD_15 WS_MSTD_15 = new BPOT3422_WS_MSTD_15();
    BPOT3422_WS_MSTD_16 WS_MSTD_16 = new BPOT3422_WS_MSTD_16();
    BPOT3422_WS_MSTD_17 WS_MSTD_17 = new BPOT3422_WS_MSTD_17();
    BPOT3422_WS_MSTD_18 WS_MSTD_18 = new BPOT3422_WS_MSTD_18();
    BPOT3422_WS_MSTD_19 WS_MSTD_19 = new BPOT3422_WS_MSTD_19();
    BPOT3422_WS_MSTD_20 WS_MSTD_20 = new BPOT3422_WS_MSTD_20();
    BPOT3422_WS_MSTD_21 WS_MSTD_21 = new BPOT3422_WS_MSTD_21();
    BPOT3422_WS_MSTD_22 WS_MSTD_22 = new BPOT3422_WS_MSTD_22();
    BPOT3422_WS_MSTD_23 WS_MSTD_23 = new BPOT3422_WS_MSTD_23();
    BPOT3422_WS_MSTD_24 WS_MSTD_24 = new BPOT3422_WS_MSTD_24();
    BPOT3422_WS_MSTD_25 WS_MSTD_25 = new BPOT3422_WS_MSTD_25();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRMSG BPRMSG = new BPRMSG();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCBSP SCCBSP = new SCCBSP();
    SORSYS SORSYS = new SORSYS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCBATH SCCBATH;
    BPB3422_AWA_3422 BPB3422_AWA_3422;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_CHECK_PROC();
        if (pgmRtn) return;
        C000_OUTPUT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3422 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3422_AWA_3422>");
        BPB3422_AWA_3422 = (BPB3422_AWA_3422) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, SCCMSG);
        WS_TEMP_VARIABLE.WS_T_CTL = BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL;
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        if (BPB3422_AWA_3422.FLG == ' ') {
            BPB3422_AWA_3422.FLG = 'I';
        }
        CEP.TRC(SCCGWA, BPB3422_AWA_3422.FLG);
        CEP.TRC(SCCGWA, BPB3422_AWA_3422.SERVCODE);
        CEP.TRC(SCCGWA, BPB3422_AWA_3422.AP_TYPE);
        CEP.TRC(SCCGWA, BPB3422_AWA_3422.AP_BATNO);
        CEP.TRC(SCCGWA, BPB3422_AWA_3422.BATNO);
        if (BPB3422_AWA_3422.FLG == 'R' 
            || BPB3422_AWA_3422.FLG == 'D') {
            if (BPB3422_AWA_3422.BATNO == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR);
            }
        }
        T00_GET_CONTROL_INFO();
        if (pgmRtn) return;
        T00_GET_SCTBBTL_INFO();
        if (pgmRtn) return;
        if (BPB3422_AWA_3422.FLG == 'I') {
            B010_GET_SVR_INFO();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'T') {
            B020_RESET_TIME();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'R') {
            B030_RESET_REC();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'S') {
            B040_RESET_ALL_REC();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'D') {
            B050_DELETE_REC();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'L') {
            B060_DELETE_ALL_REC();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'P') {
            B070_SUBMIT_JOB();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'B') {
            B080_SUBMIT_BY_BATCH_NO();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'U') {
            B090_SUBMIT_BY_PARM();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'Q') {
            B110_INQ_SCTBBTL_DETAILS();
            if (pgmRtn) return;
        } else if (BPB3422_AWA_3422.FLG == 'N') {
            B130_INQ_BSP_REC_DETAILS();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B010_GET_SVR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRWBSP);
        IBS.init(SCCGWA, SCROBDTL);
        if (BPB3422_AWA_3422.AP_TYPE.trim().length() > 0) {
            SCROBDTL.KEY.AP_TYPE = BPB3422_AWA_3422.AP_TYPE;
        }
        if (BPB3422_AWA_3422.AP_BATNO != 0) {
            SCROBDTL.KEY.AP_BATNO = BPB3422_AWA_3422.AP_BATNO;
        }
        if (BPB3422_AWA_3422.BATNO != 0) {
            SCROBDTL.KEY.BAT_NO = BPB3422_AWA_3422.BATNO;
        } else {
            SCROBDTL.KEY.BAT_NO = 1;
        }
        SCCRWBSP.INFO.PTR = SCROBDTL;
        SCCRWBSP.INFO.LEN = 6384;
        SCCRWBSP.INFO.FUNC = 'Q';
        S000_CALL_UPD_TR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCRWBSP.RETURN_INFO);
        WS_NOT_FND_CNT = 0;
        C030_01_OUT_TITLE();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO != 'N') {
            C030_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
        while (WS_NOT_FND_CNT <= 13 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, SCCRWBSP);
            SCROBDTL.KEY.BAT_NO = SCROBDTL.KEY.BAT_NO + 1;
            SCCRWBSP.INFO.PTR = SCROBDTL;
            SCCRWBSP.INFO.LEN = 6384;
            SCCRWBSP.INFO.FUNC = 'Q';
            S000_CALL_UPD_TR();
            if (pgmRtn) return;
            if (SCCRWBSP.RETURN_INFO == 'N') {
                WS_NOT_FND_CNT = (short) (WS_NOT_FND_CNT + 1);
            }
            if (SCCRWBSP.RETURN_INFO != 'N') {
                C030_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCRWBSP.RETURN_INFO);
        }
    }
    public void C030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 90;
        SCCMPAG.SCR_ROW_CNT = 300;
        SCCMPAG.SCR_COL_CNT = 30;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_TLPM_HEAD);
        WS_TLPM_HEAD.WS_SVR_CODE = SCRBSPC.KEY.SERV_CODE;
        WS_TLPM_HEAD.WS_FIN_AC_DATE = SCRBBTL.FIN_AC_DATE;
        WS_TLPM_HEAD.WS_FIN_TIME = SCRBBTL.FIN_TIME;
        WS_TLPM_HEAD.WS_BSP_TABLE = SCRBSPC.TABLE_NAME;
        WS_TLPM_HEAD.WS_RUN_TYPE = SCROBDTL.RUN_TYPE;
        if (WS_TLPM_HEAD.WS_RUN_TYPE == ' ') {
            WS_TLPM_HEAD.WS_RUN_TYPE = SCRBSPC.RUN_TYPE;
        }
        if (WS_TLPM_HEAD.WS_RUN_CPN_NAME.trim().length() == 0) {
            WS_TLPM_HEAD.WS_RUN_CPN_NAME = SCROBDTL.RUN_CMP_NAME;
        }
        WS_TLPM_HEAD.WS_AP_TYPE = SCROBDTL.KEY.AP_TYPE;
        WS_TLPM_HEAD.WS_AP_BATNO = SCROBDTL.KEY.AP_BATNO;
        WS_TLPM_HEAD.WS_BR = SCROBDTL.BR;
        WS_TLPM_HEAD.WS_AC_DATE = SCROBDTL.AC_DATE;
        WS_TLPM_HEAD.WS_ERR_FLG = SCRBSPC.ERROR_FLG;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_TLPM_HEAD);
        SCCMPAG.DATA_LEN = 84;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C030_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        if (SCROBDTL.STATUS == 'E' 
            && SCROBDTL.RT_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, SCCSRMM);
            IBS.init(SCCGWA, SCRSRMT);
            SCRSRMT.KEY.TYP = "MSG";
            SCRSRMT.KEY.CD = SCROBDTL.RT_CODE;
            CEP.TRC(SCCGWA, SCRSRMT.KEY);
            SCCSRMM.FUNC = '3';
            SCCSRMM.DAT_PTR = SCRSRMT;
            S000_CALL_SCZPRMM();
            if (pgmRtn) return;
