package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.AI.*;
import com.hisun.TD.*;
import com.hisun.LN.*;
import com.hisun.PN.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPRD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm BPTPDTP_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPZ11";
    String K_T_MAINT_PRDT_MENU = "BP-T-MAINT-PRDT-MENU";
    String K_T_MAINT_PRDT_INFO = "BP-T-MAINT-PRDT-INFO";
    String K_P_INQ_PRD_TYPE = "BP-P-INQ-PRD-TYPE";
    String K_P_INQ_PRD_MODEL = "BP-P-INQ-PRD-MODEL";
    String K_P_INQ_ORG = "BP-P-INQ-ORG";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    String K_P_INQ_RGNC = "BP-P-INQ-RGNC";
    String K_PRDPR_TYPE = "PRDPR";
    String K_CODE_TYPE_CP = "CONTG";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "DEVIATION MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHPRD";
    String K_R_DRW_CGWY = "BP-R-DRW-CGWY";
    BPZSMPRD_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMPRD_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    int WS_I = 0;
    BPZSMPRD_WS_PRDT_DATA[] WS_PRDT_DATA = new BPZSMPRD_WS_PRDT_DATA[50];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCTPDCD BPCTPDCD = new BPCTPDCD();
    BPCTPDME BPCTPDME = new BPCTPDME();
    BPCMPRDO BPCMPRDO = new BPCMPRDO();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    BPCRCGWY BPCRCGWY = new BPCRCGWY();
    BPRCGWY BPRCGWY = new BPRCGWY();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    CICMCGRP CICMCGRP = new CICMCGRP();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPDME BPRPDME = new BPRPDME();
    BPRPDCD BPRPDCD = new BPRPDCD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHPRD BPCOHPRD = new BPCHPRD();
    BPCHPRD BPCNHPRD = new BPCHPRD();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPCGCNGL BPCGCNGL = new BPCGCNGL();
    AICGBB01 AICGBB01 = new AICGBB01();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    TDCMPRD TDCMPRD = new TDCMPRD();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    PNCSMPRD PNCPMPRD = new PNCSMPRD();
    DCCUMPRM DCCUMPRM = new DCCUMPRM();
    DCRIRPRD DCRIRPRD = new DCRIRPRD();
    DCCSIRLN DCCSIRLN = new DCCSIRLN();
    DDCUPARM DDCUPARM = new DDCUPARM();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    BPCSMPRD BPCSMPRD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSMPRD() {
        for (int i=0;i<50;i++) WS_PRDT_DATA[i] = new BPZSMPRD_WS_PRDT_DATA();
    }
    public void MP(SCCGWA SCCGWA, BPCSMPRD BPCSMPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPRD = BPCSMPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZSMPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSMPRD.RC);
        IBS.init(SCCGWA, BPRPDCD);
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCRCGWY);
        BPCSMPRD.RC.RC_AP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 START ");
        B200_MOVE_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 END");
        if (BPCSMPRD.INFO.FUNC == 'Q'
            || BPCSMPRD.INFO.FUNC == 'A'
            || BPCSMPRD.INFO.FUNC == 'U'
            || BPCSMPRD.INFO.FUNC == 'C'
            || BPCSMPRD.INFO.FUNC == 'D') {
            CEP.TRC(SCCGWA, BPCSMPRD.INFO.FUNC);
            CEP.TRC(SCCGWA, "TTTTTTTTTTTTTTTT");
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPRD.INFO.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "PPPPPPPPPPPPPPP");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B300_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMPRD.INFO.FUNC == 'Q' 
            || BPCSMPRD.INFO.FUNC == 'D') {
            if (BPCSMPRD.PRDT_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUST_INPUT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "11111");
            CEP.TRC(SCCGWA, BPCSMPRD.CHNL1);
            CEP.TRC(SCCGWA, BPCSMPRD.CHNL2);
            if (BPCSMPRD.CHNL1.trim().length() > 0 
                && (BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL2) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL3) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL4) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL5) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL6) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL1.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, "11111");
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL1);
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL2);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL2.trim().length() > 0 
                && (BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL3) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL4) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL5) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL6) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL2.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL3);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL3.trim().length() > 0 
                && (BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL4) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL5) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL6) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL3.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL4);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL4.trim().length() > 0 
                && (BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL5) 
                || BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL6) 
                || BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL4.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL5);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL5.trim().length() > 0 
                && (BPCSMPRD.CHNL5.equalsIgnoreCase(BPCSMPRD.CHNL6) 
                || BPCSMPRD.CHNL5.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL5.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL5.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL5.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL6);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL6.trim().length() > 0 
                && (BPCSMPRD.CHNL6.equalsIgnoreCase(BPCSMPRD.CHNL7) 
                || BPCSMPRD.CHNL6.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL6.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL6.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL7);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL7.trim().length() > 0 
                && (BPCSMPRD.CHNL7.equalsIgnoreCase(BPCSMPRD.CHNL8) 
                || BPCSMPRD.CHNL7.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL7.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL8);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL8.trim().length() > 0 
                && (BPCSMPRD.CHNL8.equalsIgnoreCase(BPCSMPRD.CHNL9) 
                || BPCSMPRD.CHNL8.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL9);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CHNL9.trim().length() > 0 
                && (BPCSMPRD.CHNL9.equalsIgnoreCase(BPCSMPRD.CHNL10))) {
                CEP.TRC(SCCGWA, BPCSMPRD.CHNL10);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHNL_MUST_DIFFERENT, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "  SMPRD-ADD   SMPRD-UPDATE ");
            if (BPCSMPRD.INFO.FUNC == 'A' 
                || BPCSMPRD.INFO.FUNC == 'U') {
                CEP.TRC(SCCGWA, BPCSMPRD.PRDT_CODE);
                CEP.TRC(SCCGWA, BPCSMPRD.PRDT_MODEL);
                CEP.TRC(SCCGWA, BPCSMPRD.COMB_PRDT_IND);
                CEP.TRC(SCCGWA, BPCSMPRD.PRDT_NAME);
                CEP.TRC(SCCGWA, BPCSMPRD.ASS_BR);
                CEP.TRC(SCCGWA, BPCSMPRD.OLD_CODE);
                CEP.TRC(SCCGWA, BPCSMPRD.EFF_DATE);
                CEP.TRC(SCCGWA, BPCSMPRD.EXP_DATE);
                CEP.TRC(SCCGWA, BPCSMPRD.EFF_DATE);
                CEP.TRC(SCCGWA, BPCSMPRD.CUS_PER_FLG);
                CEP.TRC(SCCGWA, BPCSMPRD.CUS_COM_FLG);
                CEP.TRC(SCCGWA, BPCSMPRD.CUS_FIN_FLG);
                CEP.TRC(SCCGWA, BPCSMPRD.STOP_SOLD_DATE);
                CEP.TRC(SCCGWA, "===================");
                CEP.TRC(SCCGWA, BPCSMPRD.STOP_SOLD_DATE);
                if (BPCSMPRD.PRDT_CODE.trim().length() == 0 
                    || BPCSMPRD.PRDT_MODEL.trim().length() == 0 
                    || BPCSMPRD.COMB_PRDT_IND == ' ' 
                    || BPCSMPRD.PRDT_NAME.trim().length() == 0 
                    || BPCSMPRD.EFF_DATE == 0 
                    || BPCSMPRD.EXP_DATE == 0 
                    || BPCSMPRD.CUS_PER_FLG == ' ' 
                    || BPCSMPRD.CUS_COM_FLG == ' ' 
                    || BPCSMPRD.CUS_FIN_FLG == ' ' 
                    || BPCSMPRD.STOP_SOLD_DATE == 0) {
                    CEP.TRC(SCCGWA, "  MUST  INF PRD ");
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_INF_MUST_INPUT, BPCSMPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "EFF-DATE  !!!!!!!");
                if (BPCSMPRD.EFF_DATE > BPCSMPRD.STOP_SOLD_DATE 
                    || BPCSMPRD.EXP_DATE < BPCSMPRD.STOP_SOLD_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STOP_DATE_ERROR, BPCSMPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPCSMPRD.INFO.FUNC == 'A' 
                    && BPCSMPRD.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CANT_CREAT_BACKDATE, BPCSMPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "!!!!!!!!! SMPRD-EXP-DATE");
                if (BPCSMPRD.EFF_DATE > BPCSMPRD.EXP_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMPRD.BUSI_TYPE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPDT);
                BPCPQPDT.PRDT_TYPE = BPCSMPRD.BUSI_TYPE;
                S000_CALL_BPZPQPDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "PQPDT-DESC");
                CEP.TRC(SCCGWA, BPCPQPDT.DESC);
            }
            CEP.TRC(SCCGWA, "DEVJIJHERE");
            CEP.TRC(SCCGWA, BPCSMPRD.PARM_CODE);
            CEP.TRC(SCCGWA, BPCSMPRD.PRDT_MODEL);
            if (BPCSMPRD.PARM_CODE.trim().length() > 0 
                && BPCSMPRD.PRDT_MODEL.trim().length() > 0) {
                if (BPCSMPRD.PARM_CODE == null) BPCSMPRD.PARM_CODE = "";
                JIBS_tmp_int = BPCSMPRD.PARM_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMPRD.PARM_CODE += " ";
                if (BPCSMPRD.PARM_CODE.substring(0, 5).equalsIgnoreCase("MMDP9")) {
                    IBS.init(SCCGWA, TDCMPRD);
                    TDCMPRD.PROD_CD_M = BPCSMPRD.PRDT_MODEL;
                    TDCMPRD.PROD_CD = BPCSMPRD.PARM_CODE;
                    TDCMPRD.FUNC = 'I';
                    S000_CALL_TDZMPRD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCSMPRD.PARM_CODE);
                    CEP.TRC(SCCGWA, TDCMPRD.EFF_DT);
                    CEP.TRC(SCCGWA, TDCMPRD.EXP_DT);
                    CEP.TRC(SCCGWA, BPCSMPRD.EFF_DATE);
                    CEP.TRC(SCCGWA, BPCSMPRD.EXP_DATE);
                    if (BPCSMPRD.EFF_DATE < TDCMPRD.EFF_DT 
                        || BPCSMPRD.EXP_DATE > TDCMPRD.EXP_DT) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("MMDP")) {
                        IBS.init(SCCGWA, TDCQPMP);
                        IBS.init(SCCGWA, TDCPROD);
                        TDCQPMP.FUNC = 'I';
                        TDCQPMP.PROD_CD = BPCSMPRD.PARM_CODE;
                        TDCQPMP.DAT_PTR = TDCPROD;
                        S000_CALL_TDZQPMP();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, BPCSMPRD.PARM_CODE);
                        CEP.TRC(SCCGWA, TDCPROD.EFF_DT);
                        CEP.TRC(SCCGWA, TDCPROD.EXP_DT);
                        CEP.TRC(SCCGWA, BPCSMPRD.EFF_DATE);
                        CEP.TRC(SCCGWA, BPCSMPRD.EXP_DATE);
                        if (BPCSMPRD.EFF_DATE < TDCPROD.EFF_DT 
                            || BPCSMPRD.EXP_DATE > TDCPROD.EXP_DT) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("CLDD")) {
                            IBS.init(SCCGWA, LNCCTLPM);
                            LNCCTLPM.FUNC = 'I';
                            LNCCTLPM.KEY.CD = BPCSMPRD.PARM_CODE;
                            S000_CALL_LNZCTLPM();
                            if (pgmRtn) return;
                            if (LNCCTLPM.RC.RC_RTNCODE == 0) {
                                CEP.TRC(SCCGWA, LNCCTLPM.EFF_DATE);
                                CEP.TRC(SCCGWA, LNCCTLPM.EXP_DATE);
                                if (BPCSMPRD.EFF_DATE < LNCCTLPM.EFF_DATE 
                                    || BPCSMPRD.EXP_DATE > LNCCTLPM.EXP_DATE) {
                                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                                    S000_ERR_MSG_PROC();
                                    if (pgmRtn) return;
                                }
                            } else {
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCTLPM.RC);
                                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else if ((BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("CACH") 
                                || BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("BADR"))) {
                            IBS.init(SCCGWA, PNCPMPRD);
                            PNCPMPRD.FUNC = 'I';
                            PNCPMPRD.PROD_CD = BPCSMPRD.PARM_CODE;
                            S000_CALL_PNZSMPRD();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, PNCPMPRD.EFF_DATE);
                            CEP.TRC(SCCGWA, PNCPMPRD.EXP_DATE);
                            if (BPCSMPRD.EFF_DATE < PNCPMPRD.EFF_DATE 
                                || BPCSMPRD.EXP_DATE > PNCPMPRD.EXP_DATE) {
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("IRDD")) {
                            IBS.init(SCCGWA, DCCUMPRM);
                            IBS.init(SCCGWA, DCRIRPRD);
                            DCCUMPRM.FUNC = 'I';
                            DCCUMPRM.DATA.KEY.PROD_CODE = BPCSMPRD.PARM_CODE;
                            S000_CALL_DCZUMPRM();
                            if (pgmRtn) return;
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.DATA);
                            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRIRPRD);
                            CEP.TRC(SCCGWA, DCRIRPRD.EFFDAT);
                            CEP.TRC(SCCGWA, DCRIRPRD.EXPDAT);
                            if (BPCSMPRD.EFF_DATE < DCRIRPRD.EFFDAT 
                                || BPCSMPRD.EXP_DATE > DCRIRPRD.EXPDAT) {
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("IRLN")) {
                            IBS.init(SCCGWA, DCCSIRLN);
                            DCCSIRLN.FUNC = 'Q';
                            DCCSIRLN.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                            DCCSIRLN.DATA.KEY.PRDMO_CD = "IRLN";
                            DCCSIRLN.DATA.KEY.PROD_CODE = BPCSMPRD.PARM_CODE;
                            S000_CALL_DCZSIRLN();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, DCCSIRLN.DATA.STRDT);
                            CEP.TRC(SCCGWA, DCCSIRLN.DATA.EXPDT);
                            if (BPCSMPRD.EFF_DATE < DCCSIRLN.DATA.STRDT 
                                || BPCSMPRD.EXP_DATE > DCCSIRLN.DATA.EXPDT) {
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("CAAC")) {
                            IBS.init(SCCGWA, DDCUPARM);
                            DDCUPARM.TX_TYPE = 'I';
                            DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
                            DDCUPARM.DATA.KEY.PARM_CODE = BPCSMPRD.PARM_CODE;
                            DDCUPARM.DATE.EFF_DATE = BPCSMPRD.EFF_DATE;
                            S000_CALL_DDZUPARM();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, DDCUPARM.DATE.EFF_DATE);
                            CEP.TRC(SCCGWA, DDCUPARM.DATE.EXP_DATE);
                            CEP.TRC(SCCGWA, DDCUPARM.RC.RC_CODE);
                            if (DDCUPARM.RC.RC_CODE != 0) {
                                CEP.TRC(SCCGWA, "XGQ2");
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_RECORD_NO_EXIST, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else if (BPCSMPRD.PRDT_MODEL.equalsIgnoreCase("CARD")) {
                            IBS.init(SCCGWA, DCRPRDPR);
                            IBS.init(SCCGWA, DCCUPRCD);
                            DCCUPRCD.TX_TYPE = 'I';
                            DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                            DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
                            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCSMPRD.PARM_CODE;
                            S000_CALL_DCZUPRCD();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, DCCUPRCD.DATE.EFF_DATE);
                            CEP.TRC(SCCGWA, DCCUPRCD.DATE.EXP_DATE);
                            if (BPCSMPRD.EFF_DATE < DCCUPRCD.DATE.EFF_DATE 
                                || BPCSMPRD.EXP_DATE > DCCUPRCD.DATE.EXP_DATE) {
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_OR_EXP_DATE_ERR, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        } else {
                            CEP.TRC(SCCGWA, "XGQ");
                            IBS.init(SCCGWA, BPRPRMT);
                            IBS.init(SCCGWA, BPCPRMM);
                            BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
                            if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                            BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
                            if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                            if (BPCSMPRD.PARM_CODE == null) BPCSMPRD.PARM_CODE = "";
                            JIBS_tmp_int = BPCSMPRD.PARM_CODE.length();
                            for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMPRD.PARM_CODE += " ";
                            BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 10 - 1) + BPCSMPRD.PARM_CODE + BPRPRMT.KEY.CD.substring(10 + BPCSMPRD.PARM_CODE.length() - 1);
                            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
                            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                            BPCPRMM.FUNC = '3';
                            S000_CALL_BPZPRMM();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
                            CEP.TRC(SCCGWA, BPCSMPRD.PRDT_MODEL);
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCSMPRD.PRDT_MODEL)) {
                                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_MOD_DIF_PARMCD, BPCSMPRD.RC);
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
            if (BPCSMPRD.ASS_BR != 0) {
                CEP.TRC(SCCGWA, BPCSMPRD.ASS_BR);
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPCSMPRD.ASS_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.SOLD_RGN.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQRGC);
                IBS.CPY2CLS(SCCGWA, BPCSMPRD.SOLD_RGN, BPCPQRGC.RGN_NO);
                BPCPQRGC.BNK = SCCGWA.COMM_AREA.TR_BANK;
                CEP.TRC(SCCGWA, BPCPQRGC.BNK);
                S000_CALL_BPZPQRGC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP1.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP1;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP2.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP2;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP3.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP3;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP4.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP4;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP5.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP5;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP6.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP6;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP7.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP7;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP8.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP8;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP9.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP9;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.CUS_GROUP10.trim().length() > 0) {
                IBS.init(SCCGWA, CICMCGRP);
                CICMCGRP.FUNC = 'I';
                CICMCGRP.INPUT_DATA.GRPS_NO = BPCSMPRD.CUS_GROUP10;
                S000_LINK_CIZMCGRP();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MOVE_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCTPDCD);
        BPRPDME.KEY.PRDT_CODE = BPCSMPRD.PRDT_CODE;
        BPRPDME.PRDT_NAME = BPCSMPRD.PRDT_NAME;
        BPRPDME.BUSI_TYPE = BPCSMPRD.BUSI_TYPE;
        BPRPDME.PRDT_MODEL = BPCSMPRD.PRDT_MODEL;
        BPRPDME.PRDT_MODEL1 = BPCSMPRD.PRDT_MODEL1;
        BPRPDME.PRDT_MODEL2 = BPCSMPRD.PRDT_MODEL2;
        BPRPDME.PRDT_MODEL3 = BPCSMPRD.PRDT_MODEL3;
        BPRPDME.PRDT_MODEL4 = BPCSMPRD.PRDT_MODEL4;
        BPRPDME.PRDT_MODEL5 = BPCSMPRD.PRDT_MODEL5;
        if (BPCSMPRD.INFO.FUNC == 'U' 
            || BPCSMPRD.INFO.FUNC == 'A') {
            if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EFF_DATE) {
                BPCSMPRD.STS = '1';
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.STOP_SOLD_DATE) {
                    BPCSMPRD.STS = '2';
                } else {
                    if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EXP_DATE) {
                        BPCSMPRD.STS = '3';
                    } else {
                        BPCSMPRD.STS = '4';
                    }
                }
            }
        }
        BPRPDME.STS = BPCSMPRD.STS;
        BPRPDME.CTRACT_GROUP = BPCSMPRD.CTRACT_GROUP;
        BPRPDME.ACCR_TYPE = BPCSMPRD.ACCR_TYPE;
        BPRPDME.PRDT_IND = BPCSMPRD.COMB_PRDT_IND;
        BPRPDME.DESC = BPCSMPRD.DESC;
        BPRPDME.EFF_DATE = BPCSMPRD.EFF_DATE;
        BPRPDME.EXP_DATE = BPCSMPRD.EXP_DATE;
        BPRPDME.STOP_SOLD_DATE = BPCSMPRD.STOP_SOLD_DATE;
        BPRPDME.PARM_CODE = BPCSMPRD.PARM_CODE;
        BPRPDME.AC_RULE = BPCSMPRD.AC_TYPE;
        BPRPDME.PRDT_MANEGER = BPCSMPRD.PRDT_MANEGER;
        BPRPDME.ASS_BR = BPCSMPRD.ASS_BR;
        BPRPDME.OLD_CODE = BPCSMPRD.OLD_CODE;
        CEP.TRC(SCCGWA, BPCSMPRD.OLD_CODE);
        CEP.TRC(SCCGWA, BPRPDME.OLD_CODE);
        if (BPCSMPRD.INFO.FUNC == 'U') {
            BPRPDME.LAST_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRPDME.LAST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        if (BPCSMPRD.INFO.FUNC == 'A') {
            BPRPDME.OPEN_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRPDME.LAST_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRPDME.LAST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        IBS.init(SCCGWA, BPRPDCD);
        BPRPDCD.KEY.PRDT_CODE = BPCSMPRD.PRDT_CODE;
        CEP.TRC(SCCGWA, BPRPDCD.KEY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSMPRD.CUS_PER_FLG);
        BPRPDCD.CUS_PER_FLG = BPCSMPRD.CUS_PER_FLG;
        CEP.TRC(SCCGWA, BPRPDCD.CUS_PER_FLG);
        BPRPDCD.CUS_COM_FLG = BPCSMPRD.CUS_COM_FLG;
        BPRPDCD.CUS_FIN_FLG = BPCSMPRD.CUS_FIN_FLG;
        BPRPDCD.CUS_COM_SCOPE = BPCSMPRD.CUS_COM_SCOPE;
        BPRPDCD.CUS_COM_TYP1 = BPCSMPRD.CUS_COM_TYP1;
        BPRPDCD.CUS_COM_TYP2 = BPCSMPRD.CUS_COM_TYP2;
        BPRPDCD.CUS_COM_TYP3 = BPCSMPRD.CUS_COM_TYP3;
        BPRPDCD.CUS_COM_TYP4 = BPCSMPRD.CUS_COM_TYP4;
        BPRPDCD.CUS_COM_TYP5 = BPCSMPRD.CUS_COM_TYP5;
        BPRPDCD.CUS_COM_TYP6 = BPCSMPRD.CUS_COM_TYP6;
        BPRPDCD.CUS_COM_TYP7 = BPCSMPRD.CUS_COM_TYP7;
        BPRPDCD.CUS_COM_TYP8 = BPCSMPRD.CUS_COM_TYP8;
        BPRPDCD.CUS_COM_TYP9 = BPCSMPRD.CUS_COM_TYP9;
        BPRPDCD.CUS_COM_TYP10 = BPCSMPRD.CUS_COM_TYP10;
        BPRPDCD.CUS_FIN_SCOPE = BPCSMPRD.CUS_FIN_SCOPE;
        BPRPDCD.CUS_FIN_TYP1 = BPCSMPRD.CUS_FIN_TYP1;
        BPRPDCD.CUS_FIN_TYP2 = BPCSMPRD.CUS_FIN_TYP2;
        BPRPDCD.CUS_FIN_TYP3 = BPCSMPRD.CUS_FIN_TYP3;
        BPRPDCD.CUS_FIN_TYP4 = BPCSMPRD.CUS_FIN_TYP4;
        BPRPDCD.CUS_FIN_TYP5 = BPCSMPRD.CUS_FIN_TYP5;
        BPRPDCD.CUS_FIN_TYP6 = BPCSMPRD.CUS_FIN_TYP6;
        BPRPDCD.CUS_FIN_TYP7 = BPCSMPRD.CUS_FIN_TYP7;
        BPRPDCD.CUS_FIN_TYP8 = BPCSMPRD.CUS_FIN_TYP8;
        BPRPDCD.CUS_FIN_TYP9 = BPCSMPRD.CUS_FIN_TYP9;
        BPRPDCD.CUS_FIN_TYP10 = BPCSMPRD.CUS_FIN_TYP10;
        BPRPDCD.CUS_GROUP_SCOPE = BPCSMPRD.CUS_GROUP_SCOPE;
        BPRPDCD.CUS_GROUP1 = BPCSMPRD.CUS_GROUP1;
        BPRPDCD.CUS_GROUP2 = BPCSMPRD.CUS_GROUP2;
        BPRPDCD.CUS_GROUP3 = BPCSMPRD.CUS_GROUP3;
        BPRPDCD.CUS_GROUP4 = BPCSMPRD.CUS_GROUP4;
        BPRPDCD.CUS_GROUP5 = BPCSMPRD.CUS_GROUP5;
        BPRPDCD.CUS_GROUP6 = BPCSMPRD.CUS_GROUP6;
        BPRPDCD.CUS_GROUP7 = BPCSMPRD.CUS_GROUP7;
        BPRPDCD.CUS_GROUP8 = BPCSMPRD.CUS_GROUP8;
        BPRPDCD.CUS_GROUP9 = BPCSMPRD.CUS_GROUP9;
        BPRPDCD.CUS_GROUP10 = BPCSMPRD.CUS_GROUP10;
        BPRPDCD.PARTI_METH = BPCSMPRD.PARTI_METH;
        BPRPDCD.PARTI_TYPE = BPCSMPRD.PARTI_TYPE;
        BPRPDCD.PARTI_STRU = BPCSMPRD.PARTI_STRU;
        BPRPDCD.SOLD_RGN = BPCSMPRD.SOLD_RGN;
        BPRPDCD.PRDT_CUST_INFO = BPCSMPRD.PRDT_CUST_INFO;
        BPRPDCD.PRDT_MARKT_GOAL = BPCSMPRD.PRDT_MARKT_GOAL;
        BPRPDCD.COMP_PRDT_DESC = BPCSMPRD.COMP_PRDT_DESC;
        BPRPDCD.PRDT_FAV_INFO = BPCSMPRD.PRDT_FAV_INFO;
        BPRPDCD.PRDT_ADV_DESC = BPCSMPRD.PRDT_ADV_DESC;
        BPRPDCD.CHNL_SCOPE = BPCSMPRD.CHNL_SCOPE;
        BPRPDCD.CHNL1 = BPCSMPRD.CHNL1;
        BPRPDCD.CHNL2 = BPCSMPRD.CHNL2;
        BPRPDCD.CHNL3 = BPCSMPRD.CHNL3;
        BPRPDCD.CHNL4 = BPCSMPRD.CHNL4;
        BPRPDCD.CHNL5 = BPCSMPRD.CHNL5;
        BPRPDCD.CHNL6 = BPCSMPRD.CHNL6;
        BPRPDCD.CHNL7 = BPCSMPRD.CHNL7;
        BPRPDCD.CHNL8 = BPCSMPRD.CHNL8;
        BPRPDCD.CHNL9 = BPCSMPRD.CHNL9;
        BPRPDCD.CHNL10 = BPCSMPRD.CHNL10;
        CEP.TRC(SCCGWA, "666");
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_COUNTER);
        CEP.TRC(SCCGWA, "AAA");
        BPRPDCD.PRDT_COUNTER = BPCSMPRD.PRDT_COUNTER;
        BPRPDCD.SOLD_NUM = BPCSMPRD.SOLD_NUM;
        BPRPDCD.SOLD_AMT = BPCSMPRD.SOLD_AMT;
        BPRPDCD.BEL_BR = BPCSMPRD.BEL_BR;
        BPRPDCD.LEVEL1 = BPCSMPRD.LEVEL1;
        BPRPDCD.LEVEL2 = BPCSMPRD.LEVEL2;
        BPRPDCD.LEVEL3 = BPCSMPRD.LEVEL3;
        BPRPDCD.LEVEL4 = BPCSMPRD.LEVEL4;
        BPRPDCD.LEVEL5 = BPCSMPRD.LEVEL5;
        BPRPDCD.LEVEL6 = BPCSMPRD.LEVEL6;
        BPRPDCD.LEVEL7 = BPCSMPRD.LEVEL7;
        BPRPDCD.LEVEL8 = BPCSMPRD.LEVEL8;
        BPRPDCD.LEVEL9 = BPCSMPRD.LEVEL9;
        CEP.TRC(SCCGWA, "777");
        CEP.TRC(SCCGWA, BPRPDCD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, BPRPDCD);
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTPDME);
        IBS.init(SCCGWA, BPCTPDCD);
        if (BPCSMPRD.INFO.FUNC == 'Q'
            || BPCSMPRD.INFO.FUNC == 'C') {
            BPCTPDME.INFO.FUNC = 'I';
            BPCTPDCD.INFO.FUNC = 'I';
        } else if (BPCSMPRD.INFO.FUNC == 'A') {
            BPCTPDME.INFO.FUNC = 'A';
            BPCTPDCD.INFO.FUNC = 'A';
        } else if (BPCSMPRD.INFO.FUNC == 'U'
            || BPCSMPRD.INFO.FUNC == 'D') {
            BPCTPDME.INFO.FUNC = 'R';
            BPCTPDCD.INFO.FUNC = 'R';
        }
        S000_CALL_BPZTPDCD();
        if (pgmRtn) return;
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSMPRD.INFO.FUNC == 'D' 
            || BPCSMPRD.INFO.FUNC == 'A' 
            || BPCSMPRD.INFO.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMPRD.INFO.FUNC == 'U' 
            || BPCSMPRD.INFO.FUNC == 'D') {
            if (BPCSMPRD.INFO.FUNC == 'D') {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
                if (BPRPDME.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRPDME.EXP_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CANT_DELETE, BPCSMPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, AICGBB01);
                AICGBB01.PRDT_CODE = BPCSMPRD.PRDT_CODE;
                AICGBB01.FUNC = 'I';
                S000_CALL_AIZGBB01();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICGBB01.FOUND_FLG);
                if (AICGBB01.FOUND_FLG == 'Y') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CANT_DELETE2);
                }
                BPCTPDME.INFO.FUNC = 'D';
                S000_CALL_BPZTPDME();
                if (pgmRtn) return;
                BPCTPDCD.INFO.FUNC = 'D';
                S000_CALL_BPZTPDCD();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.INFO.FUNC == 'U') {
                CEP.TRC(SCCGWA, BPCSMPRD.CTRGRO);
                BPRPDME.CTRACT_GROUP = BPCSMPRD.CTRGRO;
                B200_MOVE_INPUT_DATA();
                if (pgmRtn) return;
                if (BPRPDME.STS != '2' 
                    && BPCSMPRD.CTRGRO.equalsIgnoreCase("1") 
                    && SCCGWA.COMM_AREA.AC_DATE == BPCSMPRD.EXP_DATE) {
                    BPCSMPRD.STS = '3';
                }
                BPCTPDME.INFO.FUNC = 'U';
                S000_CALL_BPZTPDME();
                if (pgmRtn) return;
                BPCTPDCD.INFO.FUNC = 'U';
                S000_CALL_BPZTPDCD();
                if (pgmRtn) return;
            }
        }
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSMPRD.PARM_CODE);
        if (BPCSMPRD.PRDT_CODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "B230 START ");
            B230_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            if (BPCSMPRD.LEVEL1.trim().length() > 0) {
                B231_BROWSE_PROCESS();
                if (pgmRtn) return;
            } else {
                if (BPCSMPRD.LEVEL2.trim().length() > 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LEVEL1_MUST_INPUT);
                } else {
                    B233_BROWSE_PROCESS();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSMPRD.PRDT_CODE.trim().length() == 0 
            && BPCSMPRD.LEVEL1.trim().length() > 0 
            && BPCSMPRD.LEVEL2.trim().length() > 0) {
            B232_BROWSE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B230_QUERY_PROCESS() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'I';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        if (BPCTPDME.RETURN_INFO == 'F') {
            BPCTPDCD.INFO.FUNC = 'I';
            S000_CALL_BPZTPDCD();
            if (pgmRtn) return;
            if ((BPCSMPRD.BUSI_TYPE.trim().length() > 0 
                && !BPRPDME.BUSI_TYPE.equalsIgnoreCase(BPCSMPRD.BUSI_TYPE)) 
                || (BPCSMPRD.PRDT_MODEL.trim().length() > 0 
                && !BPCSMPRD.PRDT_MODEL.equalsIgnoreCase(BPRPDME.PRDT_MODEL)) 
                || (BPCSMPRD.STS != ' ' 
                && (BPCSMPRD.STS == '0' 
                && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EFF_DATE 
                || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.STOP_SOLD_DATE)) 
                || (BPCSMPRD.STS == '1' 
                && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.STOP_SOLD_DATE 
                || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EXP_DATE)) 
                || (BPCSMPRD.STS == '2' 
                && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EXP_DATE 
                && SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EFF_DATE))) 
                || (BPCSMPRD.COMB_PRDT_IND != ' ' 
                && BPCSMPRD.COMB_PRDT_IND != BPRPDME.PRDT_IND)) {
                BPCSMPRD.OUTPUT_FLG = 'N';
            } else {
                BPCSMPRD.OUTPUT_FLG = 'Y';
            }
        }
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
            WS_OUTPUT_FLG = 'D';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B232_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B232 START ");
        IBS.init(SCCGWA, BPCTPDCD);
        IBS.init(SCCGWA, BPCTPDME);
        BPRPDCD.LEVEL1 = BPCSMPRD.LEVEL1;
        BPRPDCD.LEVEL2 = BPCSMPRD.LEVEL2;
        BPCTPDCD.INFO.FUNC = 'B';
        BPCTPDCD.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'Y';
        BPCTPDCD.INFO.INDEX_FLG = '2';
        S000_CALL_BPZTPDCD();
        if (pgmRtn) return;
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRPDCD.LEVEL1);
            CEP.TRC(SCCGWA, BPRPDCD.LEVEL2);
            CEP.TRC(SCCGWA, "KKKKKKKKKKKK");
            IBS.init(SCCGWA, BPCTPDCD);
            BPCTPDCD.INFO.FUNC = 'B';
            BPCTPDCD.INFO.OPT = 'N';
            S000_CALL_BPZTPDCD();
            if (pgmRtn) return;
            if (BPCTPDCD.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                BPCTPDME.INFO.FUNC = 'I';
                BPRPDME.KEY.PRDT_CODE = BPRPDCD.KEY.PRDT_CODE;
                S000_CALL_BPZTPDME();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSMPRD.OUTPUT_FLG);
                if (BPCSMPRD.OUTPUT_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "B221   START    DETAIL");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTPDCD);
        BPCTPDCD.INFO.FUNC = 'B';
        BPCTPDCD.INFO.OPT = 'E';
        S000_CALL_BPZTPDCD();
        if (pgmRtn) return;
    }
    public void B231_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B231 START ");
        IBS.init(SCCGWA, BPCTPDCD);
        IBS.init(SCCGWA, BPCTPDME);
        BPRPDCD.LEVEL1 = BPCSMPRD.LEVEL1;
        BPCTPDCD.INFO.FUNC = 'B';
        BPCTPDCD.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'Y';
        BPCTPDCD.INFO.INDEX_FLG = '1';
        S000_CALL_BPZTPDCD();
        if (pgmRtn) return;
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRPDCD.LEVEL1);
            CEP.TRC(SCCGWA, "KKKKKKKKKKKK");
            IBS.init(SCCGWA, BPCTPDCD);
            BPCTPDCD.INFO.FUNC = 'B';
            BPCTPDCD.INFO.OPT = 'N';
            S000_CALL_BPZTPDCD();
            if (pgmRtn) return;
            if (BPCTPDCD.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                BPCTPDME.INFO.FUNC = 'I';
                BPRPDME.KEY.PRDT_CODE = BPRPDCD.KEY.PRDT_CODE;
                S000_CALL_BPZTPDME();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSMPRD.OUTPUT_FLG);
                if (BPCSMPRD.OUTPUT_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "B221   START    DETAIL");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTPDCD);
        BPCTPDCD.INFO.FUNC = 'B';
        BPCTPDCD.INFO.OPT = 'E';
        S000_CALL_BPZTPDCD();
        if (pgmRtn) return;
    }
    public void B233_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B232 START ");
        IBS.init(SCCGWA, BPCTPDCD);
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'Y';
        BPCTPDME.INFO.INDEX_FLG = '1';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "KKKKKKKKKKKK");
            IBS.init(SCCGWA, BPCTPDME);
            BPRPDME.KEY.PRDT_CODE = BPRPDCD.KEY.PRDT_CODE;
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'N';
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                BPCTPDCD.INFO.FUNC = 'I';
                S000_CALL_BPZTPDCD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSMPRD.OUTPUT_FLG);
                if (BPCSMPRD.OUTPUT_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "B221   START    DETAIL");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B240_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B240 START ");
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'Y';
        CEP.TRC(SCCGWA, BPCSMPRD.BUSI_TYPE);
        CEP.TRC(SCCGWA, BPCSMPRD.PRDT_MODEL);
        if (BPCSMPRD.BUSI_TYPE.trim().length() == 0 
            && BPCSMPRD.PRDT_MODEL.trim().length() == 0) {
            BPCTPDME.INFO.INDEX_FLG = '1';
        }
        if (BPCSMPRD.BUSI_TYPE.trim().length() == 0 
            && BPCSMPRD.PRDT_MODEL.trim().length() > 0) {
            BPCTPDME.INFO.INDEX_FLG = '3';
        }
        if (BPCSMPRD.BUSI_TYPE.trim().length() > 0 
            && BPCSMPRD.PRDT_MODEL.trim().length() == 0) {
            BPCTPDME.INFO.INDEX_FLG = '2';
        }
        if (BPCSMPRD.BUSI_TYPE.trim().length() > 0 
            && BPCSMPRD.PRDT_MODEL.trim().length() > 0) {
            BPCTPDME.INFO.INDEX_FLG = '2';
        }
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCTPDME);
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'N';
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPRPDME.KEY.PRDT_CODE == null) BPRPDME.KEY.PRDT_CODE = "";
                JIBS_tmp_int = BPRPDME.KEY.PRDT_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPRPDME.KEY.PRDT_CODE += " ";
                if (BPRPDME.KEY.PRDT_CODE == null) BPRPDME.KEY.PRDT_CODE = "";
                JIBS_tmp_int = BPRPDME.KEY.PRDT_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPRPDME.KEY.PRDT_CODE += " ";
                if ((BPCSMPRD.BUSI_TYPE.trim().length() > 0 
                    && !BPRPDME.BUSI_TYPE.equalsIgnoreCase(BPCSMPRD.BUSI_TYPE)) 
                    || (BPCSMPRD.PRDT_MODEL.trim().length() > 0 
                    && !BPRPDME.PRDT_MODEL.equalsIgnoreCase(BPCSMPRD.PRDT_MODEL)) 
                    || (BPCSMPRD.STS != ' ' 
                    && (BPCSMPRD.STS == '0' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EFF_DATE 
                    || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.STOP_SOLD_DATE)) 
                    || (BPCSMPRD.STS == '1' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.STOP_SOLD_DATE 
                    || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EXP_DATE)) 
                    || (BPCSMPRD.STS == '2' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EXP_DATE 
                    && SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EFF_DATE))) 
                    || (BPCSMPRD.COMB_PRDT_IND != ' ' 
                    && BPRPDME.PRDT_IND != BPCSMPRD.COMB_PRDT_IND) 
                    || (BPCSMPRD.CUS_COM_FLG == 'Y' 
                    && BPRPDME.KEY.PRDT_CODE.substring(0, 4).equalsIgnoreCase("CLDP")) 
                    || (BPCSMPRD.CUS_PER_FLG == 'Y' 
                    && BPRPDME.KEY.PRDT_CODE.substring(0, 4).equalsIgnoreCase("CLDC"))) {
                    BPCSMPRD.OUTPUT_FLG = 'N';
                } else {
                    BPCSMPRD.OUTPUT_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPCSMPRD.OUTPUT_FLG);
                if (BPCSMPRD.OUTPUT_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "B221   START    DETAIL");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B241_BROWSE_PROCESS_A() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B241 START ");
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'Y';
        BPCTPDME.INFO.INDEX_FLG = '2';
        CEP.TRC(SCCGWA, BPRPDME.BUSI_TYPE);
        CEP.TRC(SCCGWA, "MMMMMMMMM");
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        if (BPCSMPRD.OUTPUT_FLG == 'Y') {
            if (WS_I == 1) {
                CEP.TRC(SCCGWA, "IIIIIII");
                WS_OUTPUT_FLG = 'T';
                B221_FMT_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRPDME.BUSI_TYPE);
            CEP.TRC(SCCGWA, "KKKKKKKKKKKK");
            IBS.init(SCCGWA, BPCTPDME);
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'N';
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "5555");
                if (BPRPDME.KEY.PRDT_CODE == null) BPRPDME.KEY.PRDT_CODE = "";
                JIBS_tmp_int = BPRPDME.KEY.PRDT_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPRPDME.KEY.PRDT_CODE += " ";
                if (BPRPDME.KEY.PRDT_CODE == null) BPRPDME.KEY.PRDT_CODE = "";
                JIBS_tmp_int = BPRPDME.KEY.PRDT_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPRPDME.KEY.PRDT_CODE += " ";
                if ((WS_PRDT_DATA[WS_I-1].WS_PRDT_TYPE.trim().length() > 0 
                    && !BPRPDME.BUSI_TYPE.equalsIgnoreCase(WS_PRDT_DATA[WS_I-1].WS_PRDT_TYPE)) 
                    || (BPCSMPRD.PRDT_MODEL.trim().length() > 0 
                    && !BPRPDME.PRDT_MODEL.equalsIgnoreCase(BPCSMPRD.PRDT_MODEL)) 
                    || (BPCSMPRD.STS != ' ' 
                    && (BPCSMPRD.STS == '0' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EFF_DATE 
                    || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.STOP_SOLD_DATE)) 
                    || (BPCSMPRD.STS == '1' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.STOP_SOLD_DATE 
                    || SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EXP_DATE)) 
                    || (BPCSMPRD.STS == '2' 
                    && (SCCGWA.COMM_AREA.AC_DATE < BPRPDME.EXP_DATE 
                    && SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EFF_DATE))) 
                    || (BPCSMPRD.COMB_PRDT_IND != ' ' 
                    && BPRPDME.PRDT_IND != BPCSMPRD.COMB_PRDT_IND) 
                    || (BPCSMPRD.CUS_COM_FLG == 'Y' 
                    && BPRPDME.KEY.PRDT_CODE.substring(0, 4).equalsIgnoreCase("CLDP")) 
                    || (BPCSMPRD.CUS_PER_FLG == 'Y' 
                    && BPRPDME.KEY.PRDT_CODE.substring(0, 4).equalsIgnoreCase("CLDC"))) {
                    BPCSMPRD.OUTPUT_FLG = 'N';
                } else {
                    BPCSMPRD.OUTPUT_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPCSMPRD.OUTPUT_FLG);
                if (BPCSMPRD.OUTPUT_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "B221   START    DETAIL");
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B260_BROWSE_FOR_PARMCODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B260 START");
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCSMPRD.OUTPUT_FLG = 'N';
        BPCTPDME.INFO.INDEX_FLG = '4';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'N';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
        if (BPCTPDME.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARMCD_HAVE_PRD, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 207;
            SCCMPAG.SCR_ROW_CNT = 50;
            SCCMPAG.SCR_COL_CNT = 18;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_PRDT_CODE = BPRPDME.KEY.PRDT_CODE;
            WS_OUTPUT_DATA.WS_PRDT_NAME = BPRPDME.PRDT_NAME;
            WS_OUTPUT_DATA.WS_OLD_CODE = BPRPDME.OLD_CODE;
            CEP.TRC(SCCGWA, BPRPDME.OLD_CODE);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OLD_CODE);
            if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EFF_DATE) {
                WS_OUTPUT_DATA.WS_STS = '1';
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.STOP_SOLD_DATE) {
                    WS_OUTPUT_DATA.WS_STS = '2';
                } else {
                    if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EXP_DATE) {
                        WS_OUTPUT_DATA.WS_STS = '3';
                    } else {
                        WS_OUTPUT_DATA.WS_STS = '4';
                    }
                }
            }
            WS_OUTPUT_DATA.WS_LINE = BPRPDCD.LEVEL1;
            WS_OUTPUT_DATA.WS_PRO_TYP = BPRPDCD.LEVEL2;
            WS_OUTPUT_DATA.WS_PRDT_IND = BPRPDME.PRDT_IND;
            WS_OUTPUT_DATA.WS_PARM_CODE = BPRPDME.PARM_CODE;
            WS_OUTPUT_DATA.WS_EFF_DATE = BPRPDME.EFF_DATE;
            WS_OUTPUT_DATA.WS_EXP_DATE = BPRPDME.EXP_DATE;
            WS_OUTPUT_DATA.WS_STOP_SOLD_DATE = BPRPDME.STOP_SOLD_DATE;
            WS_OUTPUT_DATA.WS_PRDT_MODEL = BPRPDME.PRDT_MODEL;
            WS_OUTPUT_DATA.WS_PRDT_MODEL1 = BPRPDME.PRDT_MODEL1;
            WS_OUTPUT_DATA.WS_PRDT_MODEL2 = BPRPDME.PRDT_MODEL2;
            WS_OUTPUT_DATA.WS_PRDT_MODEL3 = BPRPDME.PRDT_MODEL3;
            WS_OUTPUT_DATA.WS_PRDT_MODEL4 = BPRPDME.PRDT_MODEL4;
            WS_OUTPUT_DATA.WS_PRDT_MODEL5 = BPRPDME.PRDT_MODEL5;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 207;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPRD);
        BPCSMPRD.PRDT_CODE = BPRPDME.KEY.PRDT_CODE;
        BPCSMPRD.PRDT_NAME = BPRPDME.PRDT_NAME;
        BPCSMPRD.BUSI_TYPE = BPRPDME.BUSI_TYPE;
        BPCSMPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL;
        BPCSMPRD.PRDT_MODEL1 = BPRPDME.PRDT_MODEL1;
        BPCSMPRD.PRDT_MODEL2 = BPRPDME.PRDT_MODEL2;
        BPCSMPRD.PRDT_MODEL3 = BPRPDME.PRDT_MODEL3;
        BPCSMPRD.PRDT_MODEL4 = BPRPDME.PRDT_MODEL4;
        BPCSMPRD.PRDT_MODEL5 = BPRPDME.PRDT_MODEL5;
        BPCSMPRD.COMB_PRDT_IND = BPRPDME.PRDT_IND;
        BPCSMPRD.DESC = BPRPDME.DESC;
        BPCSMPRD.EFF_DATE = BPRPDME.EFF_DATE;
        BPCSMPRD.EXP_DATE = BPRPDME.EXP_DATE;
        BPCSMPRD.STOP_SOLD_DATE = BPRPDME.STOP_SOLD_DATE;
        BPCSMPRD.PARM_CODE = BPRPDME.PARM_CODE;
        BPCSMPRD.AC_TYPE = BPRPDME.AC_RULE;
        BPCSMPRD.ACCR_TYPE = BPRPDME.ACCR_TYPE;
        BPCSMPRD.PRDT_MANEGER = BPRPDME.PRDT_MANEGER;
        BPCSMPRD.ASS_BR = BPRPDME.ASS_BR;
        BPCSMPRD.OLD_CODE = BPRPDME.OLD_CODE;
        CEP.TRC(SCCGWA, BPRPDME.OLD_CODE);
        CEP.TRC(SCCGWA, BPCSMPRD.OLD_CODE);
        BPCSMPRD.CUS_PER_FLG = BPRPDCD.CUS_PER_FLG;
        BPCSMPRD.CUS_COM_FLG = BPRPDCD.CUS_COM_FLG;
        BPCSMPRD.CUS_FIN_FLG = BPRPDCD.CUS_FIN_FLG;
        BPCSMPRD.CUS_COM_SCOPE = BPRPDCD.CUS_COM_SCOPE;
        BPCSMPRD.CUS_COM_TYP1 = BPRPDCD.CUS_COM_TYP1;
        BPCSMPRD.CUS_COM_TYP2 = BPRPDCD.CUS_COM_TYP2;
        BPCSMPRD.CUS_COM_TYP3 = BPRPDCD.CUS_COM_TYP3;
        BPCSMPRD.CUS_COM_TYP4 = BPRPDCD.CUS_COM_TYP4;
        BPCSMPRD.CUS_COM_TYP5 = BPRPDCD.CUS_COM_TYP5;
        BPCSMPRD.CUS_COM_TYP6 = BPRPDCD.CUS_COM_TYP6;
        BPCSMPRD.CUS_COM_TYP7 = BPRPDCD.CUS_COM_TYP7;
        BPCSMPRD.CUS_COM_TYP8 = BPRPDCD.CUS_COM_TYP8;
        BPCSMPRD.CUS_COM_TYP9 = BPRPDCD.CUS_COM_TYP9;
        BPCSMPRD.CUS_COM_TYP10 = BPRPDCD.CUS_COM_TYP10;
        BPCSMPRD.CUS_FIN_SCOPE = BPRPDCD.CUS_FIN_SCOPE;
        BPCSMPRD.CUS_FIN_TYP1 = BPRPDCD.CUS_FIN_TYP1;
        BPCSMPRD.CUS_FIN_TYP2 = BPRPDCD.CUS_FIN_TYP2;
        BPCSMPRD.CUS_FIN_TYP3 = BPRPDCD.CUS_FIN_TYP3;
        BPCSMPRD.CUS_FIN_TYP4 = BPRPDCD.CUS_FIN_TYP4;
        BPCSMPRD.CUS_FIN_TYP5 = BPRPDCD.CUS_FIN_TYP5;
        BPCSMPRD.CUS_FIN_TYP6 = BPRPDCD.CUS_FIN_TYP6;
        BPCSMPRD.CUS_FIN_TYP7 = BPRPDCD.CUS_FIN_TYP7;
        BPCSMPRD.CUS_FIN_TYP8 = BPRPDCD.CUS_FIN_TYP8;
        BPCSMPRD.CUS_FIN_TYP9 = BPRPDCD.CUS_FIN_TYP9;
        BPCSMPRD.CUS_FIN_TYP10 = BPRPDCD.CUS_FIN_TYP10;
        BPCSMPRD.CUS_GROUP_SCOPE = BPRPDCD.CUS_GROUP_SCOPE;
        BPCSMPRD.CUS_GROUP1 = BPRPDCD.CUS_GROUP1;
        BPCSMPRD.CUS_GROUP2 = BPRPDCD.CUS_GROUP2;
        BPCSMPRD.CUS_GROUP3 = BPRPDCD.CUS_GROUP3;
        BPCSMPRD.CUS_GROUP4 = BPRPDCD.CUS_GROUP4;
        BPCSMPRD.CUS_GROUP5 = BPRPDCD.CUS_GROUP5;
        BPCSMPRD.CUS_GROUP6 = BPRPDCD.CUS_GROUP6;
        BPCSMPRD.CUS_GROUP7 = BPRPDCD.CUS_GROUP7;
        BPCSMPRD.CUS_GROUP8 = BPRPDCD.CUS_GROUP8;
        BPCSMPRD.CUS_GROUP9 = BPRPDCD.CUS_GROUP9;
        BPCSMPRD.CUS_GROUP10 = BPRPDCD.CUS_GROUP10;
        BPCSMPRD.PARTI_METH = BPRPDCD.PARTI_METH;
        BPCSMPRD.PARTI_TYPE = BPRPDCD.PARTI_TYPE;
        BPCSMPRD.PARTI_STRU = BPRPDCD.PARTI_STRU;
        BPCSMPRD.SOLD_RGN = BPRPDCD.SOLD_RGN;
        BPCSMPRD.PRDT_CUST_INFO = BPRPDCD.PRDT_CUST_INFO;
        BPCSMPRD.PRDT_MARKT_GOAL = BPRPDCD.PRDT_MARKT_GOAL;
        BPCSMPRD.COMP_PRDT_DESC = BPRPDCD.COMP_PRDT_DESC;
        BPCSMPRD.PRDT_ADV_DESC = BPRPDCD.PRDT_ADV_DESC;
        BPCSMPRD.PRDT_FAV_INFO = BPRPDCD.PRDT_FAV_INFO;
        BPCSMPRD.CHNL_SCOPE = BPRPDCD.CHNL_SCOPE;
        BPCSMPRD.CHNL1 = BPRPDCD.CHNL1;
        BPCSMPRD.CHNL2 = BPRPDCD.CHNL2;
        BPCSMPRD.CHNL3 = BPRPDCD.CHNL3;
        BPCSMPRD.CHNL4 = BPRPDCD.CHNL4;
        BPCSMPRD.CHNL5 = BPRPDCD.CHNL5;
        BPCSMPRD.CHNL6 = BPRPDCD.CHNL6;
        BPCSMPRD.CHNL7 = BPRPDCD.CHNL7;
        BPCSMPRD.CHNL8 = BPRPDCD.CHNL8;
        BPCSMPRD.CHNL9 = BPRPDCD.CHNL9;
        BPCSMPRD.CHNL10 = BPRPDCD.CHNL10;
        BPCSMPRD.PRDT_COUNTER = BPRPDCD.PRDT_COUNTER;
        BPCSMPRD.SOLD_NUM = BPRPDCD.SOLD_NUM;
        BPCSMPRD.SOLD_AMT = BPRPDCD.SOLD_AMT;
        BPCSMPRD.BEL_BR = BPRPDCD.BEL_BR;
        BPCSMPRD.LEVEL1 = BPRPDCD.LEVEL1;
        BPCSMPRD.LEVEL2 = BPRPDCD.LEVEL2;
        BPCSMPRD.LEVEL3 = BPRPDCD.LEVEL3;
        BPCSMPRD.LEVEL4 = BPRPDCD.LEVEL4;
        BPCSMPRD.LEVEL5 = BPRPDCD.LEVEL5;
        BPCSMPRD.LEVEL6 = BPRPDCD.LEVEL6;
        BPCSMPRD.LEVEL7 = BPRPDCD.LEVEL7;
        BPCSMPRD.LEVEL8 = BPRPDCD.LEVEL8;
        BPCSMPRD.LEVEL9 = BPRPDCD.LEVEL9;
        CEP.TRC(SCCGWA, BPCSMPRD);
        CEP.TRC(SCCGWA, "OOOOOOOOOOOOOOOOOOOOOOOOO");
    }
    public void B101_CHECK_BUSI_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        BPRPDTP.SUPR_TYPE = BPCSMPRD.BUSI_TYPE;
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "SUPR_TYPE = :BPRPDTP.SUPR_TYPE";
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && WS_I < 50; WS_I += 1) {
            WS_PRDT_DATA[WS_I-1].WS_PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
            WS_PRDT_DATA[WS_I-1].WS_BOTTOM_IND = BPRPDTP.BOTTOM_IND;
            CEP.TRC(SCCGWA, WS_PRDT_DATA[WS_I-1].WS_PRDT_TYPE);
            CEP.TRC(SCCGWA, WS_PRDT_DATA[WS_I-1].WS_BOTTOM_IND);
            CEP.TRC(SCCGWA, WS_I);
            if (WS_PRDT_DATA[WS_I-1].WS_BOTTOM_IND != 'B') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOT_BOTTOM, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        }
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPDME.BUSI_TYPE);
        CEP.TRC(SCCGWA, "TTTTTTTTTTTTT");
        CEP.TRC(SCCGWA, BPCMPRDO);
        CEP.TRC(SCCGWA, BPCMPRDO.PRDT_CUST_INFO);
        CEP.TRC(SCCGWA, BPCMPRDO.DESC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCMPRDO;
        SCCFMT.DATA_LEN = 2918;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMPRDO);
        BPCMPRDO.PRDT_CODE = BPRPDME.KEY.PRDT_CODE;
        BPCMPRDO.PRDT_NAME = BPRPDME.PRDT_NAME;
        BPCMPRDO.PRDT_MODEL = BPRPDME.PRDT_MODEL;
        BPCMPRDO.PRDT_MODEL1 = BPRPDME.PRDT_MODEL1;
        BPCMPRDO.PRDT_MODEL2 = BPRPDME.PRDT_MODEL2;
        BPCMPRDO.PRDT_MODEL3 = BPRPDME.PRDT_MODEL3;
        BPCMPRDO.PRDT_MODEL4 = BPRPDME.PRDT_MODEL4;
        BPCMPRDO.PRDT_MODEL5 = BPRPDME.PRDT_MODEL5;
        BPCMPRDO.LINE = BPRPDCD.LEVEL1;
        BPCMPRDO.PRO_TYP = BPRPDCD.LEVEL2;
        IBS.init(SCCGWA, BPCPQPDM);
        BPCPQPDM.PRDT_MODEL = BPRPDME.PRDT_MODEL;
        S000_CALL_BPZPQPDM();
        if (pgmRtn) return;
        BPCMPRDO.COMB_PRDT_IND = BPRPDME.PRDT_IND;
        BPCMPRDO.DESC = BPRPDME.DESC;
        BPCMPRDO.EFF_DATE = BPRPDME.EFF_DATE;
        BPCMPRDO.EXP_DATE = BPRPDME.EXP_DATE;
        BPCMPRDO.STOP_SOLD_DATE = BPRPDME.STOP_SOLD_DATE;
        BPCMPRDO.PARM_CODE = BPRPDME.PARM_CODE;
        BPCMPRDO.AC_TYPE = BPRPDME.AC_RULE;
        BPCMPRDO.PRDT_MANEGER = BPRPDME.PRDT_MANEGER;
        BPCMPRDO.ASS_BR = BPRPDME.ASS_BR;
        BPCMPRDO.OLD_CODE = BPRPDME.OLD_CODE;
        CEP.TRC(SCCGWA, BPRPDME.OLD_CODE);
        CEP.TRC(SCCGWA, BPCMPRDO.OLD_CODE);
        BPCMPRDO.CUS_PER_FLG = BPRPDCD.CUS_PER_FLG;
        BPCMPRDO.CUS_COM_FLG = BPRPDCD.CUS_COM_FLG;
        BPCMPRDO.CUS_FIN_FLG = BPRPDCD.CUS_FIN_FLG;
        BPCMPRDO.CUS_COM_SCOPE = BPRPDCD.CUS_COM_SCOPE;
        BPCMPRDO.CUS_COM_TYP1 = BPRPDCD.CUS_COM_TYP1;
        BPCMPRDO.CUS_COM_TYP2 = BPRPDCD.CUS_COM_TYP2;
        BPCMPRDO.CUS_COM_TYP3 = BPRPDCD.CUS_COM_TYP3;
        BPCMPRDO.CUS_COM_TYP4 = BPRPDCD.CUS_COM_TYP4;
        BPCMPRDO.CUS_COM_TYP5 = BPRPDCD.CUS_COM_TYP5;
        BPCMPRDO.CUS_COM_TYP6 = BPRPDCD.CUS_COM_TYP6;
        BPCMPRDO.CUS_COM_TYP7 = BPRPDCD.CUS_COM_TYP7;
        BPCMPRDO.CUS_COM_TYP8 = BPRPDCD.CUS_COM_TYP8;
        BPCMPRDO.CUS_COM_TYP9 = BPRPDCD.CUS_COM_TYP9;
        BPCMPRDO.CUS_COM_TYP10 = BPRPDCD.CUS_COM_TYP10;
        BPCMPRDO.CUS_FIN_SCOPE = BPRPDCD.CUS_FIN_SCOPE;
        BPCMPRDO.CUS_FIN_TYP1 = BPRPDCD.CUS_FIN_TYP1;
        BPCMPRDO.CUS_FIN_TYP2 = BPRPDCD.CUS_FIN_TYP2;
        BPCMPRDO.CUS_FIN_TYP3 = BPRPDCD.CUS_FIN_TYP3;
        BPCMPRDO.CUS_FIN_TYP4 = BPRPDCD.CUS_FIN_TYP4;
        BPCMPRDO.CUS_FIN_TYP5 = BPRPDCD.CUS_FIN_TYP5;
        BPCMPRDO.CUS_FIN_TYP6 = BPRPDCD.CUS_FIN_TYP6;
        BPCMPRDO.CUS_FIN_TYP7 = BPRPDCD.CUS_FIN_TYP7;
        BPCMPRDO.CUS_FIN_TYP8 = BPRPDCD.CUS_FIN_TYP8;
        BPCMPRDO.CUS_FIN_TYP9 = BPRPDCD.CUS_FIN_TYP9;
        BPCMPRDO.CUS_FIN_TYP10 = BPRPDCD.CUS_FIN_TYP10;
        BPCMPRDO.CUS_GROUP_SCOPE = BPRPDCD.CUS_GROUP_SCOPE;
        BPCMPRDO.CUS_GROUP1 = BPRPDCD.CUS_GROUP1;
        BPCMPRDO.CUS_GROUP2 = BPRPDCD.CUS_GROUP2;
        BPCMPRDO.CUS_GROUP3 = BPRPDCD.CUS_GROUP3;
        BPCMPRDO.CUS_GROUP4 = BPRPDCD.CUS_GROUP4;
        BPCMPRDO.CUS_GROUP5 = BPRPDCD.CUS_GROUP5;
        BPCMPRDO.CUS_GROUP6 = BPRPDCD.CUS_GROUP6;
        BPCMPRDO.CUS_GROUP7 = BPRPDCD.CUS_GROUP7;
        BPCMPRDO.CUS_GROUP8 = BPRPDCD.CUS_GROUP8;
        BPCMPRDO.CUS_GROUP9 = BPRPDCD.CUS_GROUP9;
        BPCMPRDO.CUS_GROUP10 = BPRPDCD.CUS_GROUP10;
        BPCMPRDO.PARTI_METH = BPRPDCD.PARTI_METH;
        BPCMPRDO.PARTI_TYPE = BPRPDCD.PARTI_TYPE;
        BPCMPRDO.PARTI_STRU = BPRPDCD.PARTI_STRU;
        BPCMPRDO.SOLD_RGN = BPRPDCD.SOLD_RGN;
        CEP.TRC(SCCGWA, BPCMPRDO.SOLD_RGN);
        BPCMPRDO.PRDT_CUST_INFO = BPRPDCD.PRDT_CUST_INFO;
        BPCMPRDO.PRDT_MARKT_GOAL = BPRPDCD.PRDT_MARKT_GOAL;
        BPCMPRDO.COMP_PRDT_DESC = BPRPDCD.COMP_PRDT_DESC;
        BPCMPRDO.PRDT_ADV_DESC = BPRPDCD.PRDT_ADV_DESC;
        BPCMPRDO.PRDT_FAV_INFO = BPRPDCD.PRDT_FAV_INFO;
        BPCMPRDO.CHNL_SCOPE = BPRPDCD.CHNL_SCOPE;
        CEP.TRC(SCCGWA, BPCMPRDO.CHNL_SCOPE);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL1);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL2);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL3);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL4);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL5);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL6);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL7);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL8);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL9);
        CEP.TRC(SCCGWA, BPRPDCD.CHNL10);
        BPCMPRDO.CHNL1 = BPRPDCD.CHNL1;
        BPCMPRDO.CHNL2 = BPRPDCD.CHNL2;
        BPCMPRDO.CHNL3 = BPRPDCD.CHNL3;
        BPCMPRDO.CHNL4 = BPRPDCD.CHNL4;
        BPCMPRDO.CHNL5 = BPRPDCD.CHNL5;
        BPCMPRDO.CHNL6 = BPRPDCD.CHNL6;
        BPCMPRDO.CHNL7 = BPRPDCD.CHNL7;
        BPCMPRDO.CHNL8 = BPRPDCD.CHNL8;
        BPCMPRDO.CHNL9 = BPRPDCD.CHNL9;
        BPCMPRDO.CHNL10 = BPRPDCD.CHNL10;
        BPCMPRDO.SOLD_NUM = BPRPDCD.SOLD_NUM;
        BPCMPRDO.SOLD_AMT = BPRPDCD.SOLD_AMT;
        CEP.TRC(SCCGWA, BPCMPRDO.BEL_BR);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL1);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL2);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL3);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL4);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL5);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL6);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL7);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL8);
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL9);
        CEP.TRC(SCCGWA, BPRPDCD.BEL_BR);
        BPCMPRDO.BEL_BR = BPRPDCD.BEL_BR;
        CEP.TRC(SCCGWA, BPRPDCD.BEL_BR);
        BPCMPRDO.LEVEL1 = BPRPDCD.LEVEL1;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL1);
        BPCMPRDO.LEVEL2 = BPRPDCD.LEVEL2;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL2);
        BPCMPRDO.LEVEL3 = BPRPDCD.LEVEL3;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL3);
        BPCMPRDO.LEVEL4 = BPRPDCD.LEVEL4;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL4);
        BPCMPRDO.LEVEL5 = BPRPDCD.LEVEL5;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL5);
        BPCMPRDO.LEVEL6 = BPRPDCD.LEVEL6;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL6);
        BPCMPRDO.LEVEL7 = BPRPDCD.LEVEL7;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL7);
        BPCMPRDO.LEVEL8 = BPRPDCD.LEVEL8;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL8);
        BPCMPRDO.LEVEL9 = BPRPDCD.LEVEL9;
        CEP.TRC(SCCGWA, BPRPDCD.LEVEL9);
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTPDCD.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTPDME.RETURN_INFO);
        if (BPCTPDME.RETURN_INFO == 'N' 
            || BPCTPDCD.RETURN_INFO == 'N') {
            if (BPCSMPRD.INFO.FUNC == 'Q' 
                || BPCSMPRD.INFO.FUNC == 'U' 
                || BPCSMPRD.INFO.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PDME_RECORD_NFOND, BPCSMPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMPRD.INFO.FUNC == 'C') {
                BPCSMPRD.EXIST_FLG = 'N';
            }
        }
        if (BPCTPDME.RETURN_INFO == 'F' 
            || BPCTPDCD.RETURN_INFO == 'F') {
            if (BPCSMPRD.INFO.FUNC == 'C') {
                BPCSMPRD.EXIST_FLG = 'Y';
            }
        }
        if (BPCTPDME.RETURN_INFO == 'D' 
            || BPCTPDCD.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPRD.INFO.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHPRD);
            BPCOHPRD.PRDT_CODE = BPRPDME.KEY.PRDT_CODE;
            BPCOHPRD.PRDT_NAME = BPRPDME.PRDT_NAME;
            BPCOHPRD.BUSI_TYPE = BPRPDME.BUSI_TYPE;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL1;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL2;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL3;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL4;
            BPCOHPRD.PRDT_MODEL = BPRPDME.PRDT_MODEL5;
            if (BPCSMPRD.INFO.FUNC == 'U' 
                || BPCSMPRD.INFO.FUNC == 'A') {
                if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EFF_DATE) {
                    BPCSMPRD.STS = '1';
                } else {
                    if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.STOP_SOLD_DATE) {
                        BPCSMPRD.STS = '2';
                    } else {
                        if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EXP_DATE) {
                            BPCSMPRD.STS = '3';
                        } else {
                            BPCSMPRD.STS = '4';
                        }
                    }
                }
            }
            BPCOHPRD.COMB_PRDT_IND = BPRPDME.PRDT_IND;
            BPCOHPRD.DESC = BPRPDME.DESC;
            BPCOHPRD.EFF_DATE = BPRPDME.EFF_DATE;
            BPCOHPRD.EXP_DATE = BPRPDME.EXP_DATE;
            BPCOHPRD.STOP_SOLD_DATE = BPRPDME.STOP_SOLD_DATE;
            BPCOHPRD.PARM_CODE = BPRPDME.PARM_CODE;
            BPCOHPRD.AC_TYPE = BPRPDME.AC_RULE;
            BPCOHPRD.PRDT_MANEGER = BPRPDME.PRDT_MANEGER;
            BPCOHPRD.ASS_BR = BPRPDME.ASS_BR;
            BPCOHPRD.OLD_CODE = BPRPDME.OLD_CODE;
            CEP.TRC(SCCGWA, BPRPDME.OLD_CODE);
            CEP.TRC(SCCGWA, BPCOHPRD.OLD_CODE);
            BPCOHPRD.CUS_PER_FLG = BPRPDCD.CUS_PER_FLG;
            BPCOHPRD.CUS_COM_FLG = BPRPDCD.CUS_COM_FLG;
            BPCOHPRD.CUS_FIN_FLG = BPRPDCD.CUS_FIN_FLG;
            BPCOHPRD.CUS_COM_SCOPE = BPRPDCD.CUS_COM_SCOPE;
            BPCOHPRD.CUS_COM_TYP1 = BPRPDCD.CUS_COM_TYP1;
            BPCOHPRD.CUS_COM_TYP2 = BPRPDCD.CUS_COM_TYP2;
            BPCOHPRD.CUS_COM_TYP3 = BPRPDCD.CUS_COM_TYP3;
            BPCOHPRD.CUS_COM_TYP4 = BPRPDCD.CUS_COM_TYP4;
            BPCOHPRD.CUS_COM_TYP5 = BPRPDCD.CUS_COM_TYP5;
            BPCOHPRD.CUS_COM_TYP6 = BPRPDCD.CUS_COM_TYP6;
            BPCOHPRD.CUS_COM_TYP7 = BPRPDCD.CUS_COM_TYP7;
            BPCOHPRD.CUS_COM_TYP8 = BPRPDCD.CUS_COM_TYP8;
            BPCOHPRD.CUS_COM_TYP9 = BPRPDCD.CUS_COM_TYP9;
            BPCOHPRD.CUS_COM_TYP10 = BPRPDCD.CUS_COM_TYP10;
            BPCOHPRD.CUS_FIN_SCOPE = BPRPDCD.CUS_FIN_SCOPE;
            BPCOHPRD.CUS_FIN_TYP1 = BPRPDCD.CUS_FIN_TYP1;
            BPCOHPRD.CUS_FIN_TYP2 = BPRPDCD.CUS_FIN_TYP2;
            BPCOHPRD.CUS_FIN_TYP3 = BPRPDCD.CUS_FIN_TYP3;
            BPCOHPRD.CUS_FIN_TYP4 = BPRPDCD.CUS_FIN_TYP4;
            BPCOHPRD.CUS_FIN_TYP5 = BPRPDCD.CUS_FIN_TYP5;
            BPCOHPRD.CUS_FIN_TYP6 = BPRPDCD.CUS_FIN_TYP6;
            BPCOHPRD.CUS_FIN_TYP7 = BPRPDCD.CUS_FIN_TYP7;
            BPCOHPRD.CUS_FIN_TYP8 = BPRPDCD.CUS_FIN_TYP8;
            BPCOHPRD.CUS_FIN_TYP9 = BPRPDCD.CUS_FIN_TYP9;
            BPCOHPRD.CUS_FIN_TYP10 = BPRPDCD.CUS_FIN_TYP10;
            BPCOHPRD.CUS_GROUP_SCOPE = BPRPDCD.CUS_GROUP_SCOPE;
            BPCOHPRD.CUS_GROUP1 = BPRPDCD.CUS_GROUP1;
            BPCOHPRD.CUS_GROUP2 = BPRPDCD.CUS_GROUP2;
            BPCOHPRD.CUS_GROUP3 = BPRPDCD.CUS_GROUP3;
            BPCOHPRD.CUS_GROUP4 = BPRPDCD.CUS_GROUP4;
            BPCOHPRD.CUS_GROUP5 = BPRPDCD.CUS_GROUP5;
            BPCOHPRD.CUS_GROUP6 = BPRPDCD.CUS_GROUP6;
            BPCOHPRD.CUS_GROUP7 = BPRPDCD.CUS_GROUP7;
            BPCOHPRD.CUS_GROUP8 = BPRPDCD.CUS_GROUP8;
            BPCOHPRD.CUS_GROUP9 = BPRPDCD.CUS_GROUP9;
            BPCOHPRD.CUS_GROUP10 = BPRPDCD.CUS_GROUP10;
            BPCOHPRD.PARTI_METH = BPRPDCD.PARTI_METH;
            BPCOHPRD.PARTI_TYPE = BPRPDCD.PARTI_TYPE;
            BPCOHPRD.PARTI_STRU = BPRPDCD.PARTI_STRU;
            BPCOHPRD.SOLD_RGN = BPRPDCD.SOLD_RGN;
            BPCOHPRD.CHNL_SCOPE = BPRPDCD.CHNL_SCOPE;
            BPCOHPRD.CHNL1 = BPRPDCD.CHNL1;
            BPCOHPRD.CHNL2 = BPRPDCD.CHNL2;
            BPCOHPRD.CHNL3 = BPRPDCD.CHNL3;
            BPCOHPRD.CHNL4 = BPRPDCD.CHNL4;
            BPCOHPRD.CHNL5 = BPRPDCD.CHNL5;
            BPCOHPRD.CHNL6 = BPRPDCD.CHNL6;
            BPCOHPRD.CHNL7 = BPRPDCD.CHNL7;
            BPCOHPRD.CHNL8 = BPRPDCD.CHNL8;
            BPCOHPRD.CHNL9 = BPRPDCD.CHNL9;
            BPCOHPRD.CHNL10 = BPRPDCD.CHNL10;
            BPCOHPRD.PRDT_CUST_INFO = BPRPDCD.PRDT_CUST_INFO;
            BPCOHPRD.PRDT_MARKT_GOAL = BPRPDCD.PRDT_MARKT_GOAL;
            BPCOHPRD.COMP_PRDT_DESC = BPRPDCD.COMP_PRDT_DESC;
            BPCOHPRD.PRDT_ADV_DESC = BPRPDCD.PRDT_ADV_DESC;
            BPCOHPRD.PRDT_FAV_INFO = BPRPDCD.PRDT_FAV_INFO;
            BPCOHPRD.PRDT_COUNTER = BPRPDCD.PRDT_COUNTER;
            BPCOHPRD.SOLD_NUM = BPRPDCD.SOLD_NUM;
            BPCOHPRD.SOLD_AMT = BPRPDCD.SOLD_AMT;
            BPCOHPRD.BEL_BR = BPRPDCD.BEL_BR;
            BPCOHPRD.LEVEL1 = BPRPDCD.LEVEL1;
            BPCOHPRD.LEVEL2 = BPRPDCD.LEVEL2;
            BPCOHPRD.LEVEL3 = BPRPDCD.LEVEL3;
            BPCOHPRD.LEVEL4 = BPRPDCD.LEVEL4;
            BPCOHPRD.LEVEL5 = BPRPDCD.LEVEL5;
            BPCOHPRD.LEVEL6 = BPRPDCD.LEVEL6;
            BPCOHPRD.LEVEL7 = BPRPDCD.LEVEL7;
            BPCOHPRD.LEVEL8 = BPRPDCD.LEVEL8;
            BPCOHPRD.LEVEL9 = BPRPDCD.LEVEL9;
            CEP.TRC(SCCGWA, BPCOHPRD.PRDT_COUNTER);
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSMPRD.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMPRD.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSMPRD.INFO.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.REF_NO = BPCSMPRD.PRDT_CODE;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        if (BPCSMPRD.INFO.FUNC == 'U') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHPRD;
            IBS.init(SCCGWA, BPCNHPRD);
            BPCNHPRD.PRDT_CODE = BPCSMPRD.PRDT_CODE;
            BPCNHPRD.PRDT_NAME = BPCSMPRD.PRDT_NAME;
            BPCNHPRD.BUSI_TYPE = BPCSMPRD.BUSI_TYPE;
            BPCNHPRD.PRDT_MODEL = BPCSMPRD.PRDT_MODEL;
            BPCNHPRD.PRDT_MODEL1 = BPCSMPRD.PRDT_MODEL1;
            BPCNHPRD.PRDT_MODEL2 = BPCSMPRD.PRDT_MODEL2;
            BPCNHPRD.PRDT_MODEL3 = BPCSMPRD.PRDT_MODEL3;
            BPCNHPRD.PRDT_MODEL4 = BPCSMPRD.PRDT_MODEL4;
            BPCNHPRD.PRDT_MODEL5 = BPCSMPRD.PRDT_MODEL5;
            if (BPCSMPRD.INFO.FUNC == 'U' 
                || BPCSMPRD.INFO.FUNC == 'A') {
                if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EFF_DATE) {
                    BPCNHPRD.STS = '1';
                } else {
                    if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.STOP_SOLD_DATE) {
                        BPCNHPRD.STS = '2';
                    } else {
                        if (SCCGWA.COMM_AREA.AC_DATE < BPCSMPRD.EXP_DATE) {
                            BPCNHPRD.STS = '3';
                        } else {
                            BPCNHPRD.STS = '4';
                        }
                    }
                }
            }
            BPCNHPRD.COMB_PRDT_IND = BPCSMPRD.COMB_PRDT_IND;
            BPCNHPRD.DESC = BPCSMPRD.DESC;
            BPCNHPRD.EFF_DATE = BPCSMPRD.EFF_DATE;
            BPCNHPRD.EXP_DATE = BPCSMPRD.EXP_DATE;
            BPCNHPRD.STOP_SOLD_DATE = BPCSMPRD.STOP_SOLD_DATE;
            BPCNHPRD.PARM_CODE = BPCSMPRD.PARM_CODE;
            BPCNHPRD.AC_TYPE = BPCSMPRD.AC_TYPE;
            BPCNHPRD.PRDT_MANEGER = BPCSMPRD.PRDT_MANEGER;
            BPCNHPRD.ASS_BR = BPCSMPRD.ASS_BR;
            BPCNHPRD.OLD_CODE = BPCSMPRD.OLD_CODE;
            CEP.TRC(SCCGWA, BPCSMPRD.OLD_CODE);
            CEP.TRC(SCCGWA, BPCNHPRD.OLD_CODE);
            BPCNHPRD.CUS_PER_FLG = BPCSMPRD.CUS_PER_FLG;
            BPCNHPRD.CUS_COM_FLG = BPCSMPRD.CUS_COM_FLG;
            BPCNHPRD.CUS_FIN_FLG = BPCSMPRD.CUS_FIN_FLG;
            BPCNHPRD.CUS_COM_SCOPE = BPCSMPRD.CUS_COM_SCOPE;
            BPCNHPRD.CUS_COM_TYP1 = BPCSMPRD.CUS_COM_TYP1;
            BPCNHPRD.CUS_COM_TYP2 = BPCSMPRD.CUS_COM_TYP2;
            BPCNHPRD.CUS_COM_TYP3 = BPCSMPRD.CUS_COM_TYP3;
            BPCNHPRD.CUS_COM_TYP4 = BPCSMPRD.CUS_COM_TYP4;
            BPCNHPRD.CUS_COM_TYP5 = BPCSMPRD.CUS_COM_TYP5;
            BPCNHPRD.CUS_COM_TYP6 = BPCSMPRD.CUS_COM_TYP6;
            BPCNHPRD.CUS_COM_TYP7 = BPCSMPRD.CUS_COM_TYP7;
            BPCNHPRD.CUS_COM_TYP8 = BPCSMPRD.CUS_COM_TYP8;
            BPCNHPRD.CUS_COM_TYP9 = BPCSMPRD.CUS_COM_TYP9;
            BPCNHPRD.CUS_COM_TYP10 = BPCSMPRD.CUS_COM_TYP10;
            BPCNHPRD.CUS_FIN_SCOPE = BPCSMPRD.CUS_FIN_SCOPE;
            BPCNHPRD.CUS_FIN_TYP1 = BPCSMPRD.CUS_FIN_TYP1;
            BPCNHPRD.CUS_FIN_TYP2 = BPCSMPRD.CUS_FIN_TYP2;
            BPCNHPRD.CUS_FIN_TYP3 = BPCSMPRD.CUS_FIN_TYP3;
            BPCNHPRD.CUS_FIN_TYP4 = BPCSMPRD.CUS_FIN_TYP4;
            BPCNHPRD.CUS_FIN_TYP5 = BPCSMPRD.CUS_FIN_TYP5;
            BPCNHPRD.CUS_FIN_TYP6 = BPCSMPRD.CUS_FIN_TYP6;
            BPCNHPRD.CUS_FIN_TYP7 = BPCSMPRD.CUS_FIN_TYP7;
            BPCNHPRD.CUS_FIN_TYP8 = BPCSMPRD.CUS_FIN_TYP8;
            BPCNHPRD.CUS_FIN_TYP9 = BPCSMPRD.CUS_FIN_TYP9;
            BPCNHPRD.CUS_FIN_TYP10 = BPCSMPRD.CUS_FIN_TYP10;
            BPRPDCD.CUS_GROUP_SCOPE = BPCSMPRD.CUS_GROUP_SCOPE;
            BPCNHPRD.CUS_GROUP1 = BPCSMPRD.CUS_GROUP1;
            BPCNHPRD.CUS_GROUP2 = BPCSMPRD.CUS_GROUP2;
            BPCNHPRD.CUS_GROUP3 = BPCSMPRD.CUS_GROUP3;
            BPCNHPRD.CUS_GROUP4 = BPCSMPRD.CUS_GROUP4;
            BPCNHPRD.CUS_GROUP5 = BPCSMPRD.CUS_GROUP5;
            BPCNHPRD.CUS_GROUP6 = BPCSMPRD.CUS_GROUP6;
            BPCNHPRD.CUS_GROUP7 = BPCSMPRD.CUS_GROUP7;
            BPCNHPRD.CUS_GROUP8 = BPCSMPRD.CUS_GROUP8;
            BPCNHPRD.CUS_GROUP9 = BPCSMPRD.CUS_GROUP9;
            BPCNHPRD.CUS_GROUP10 = BPCSMPRD.CUS_GROUP10;
            BPCNHPRD.PARTI_METH = BPCSMPRD.PARTI_METH;
            BPCNHPRD.PARTI_TYPE = BPCSMPRD.PARTI_TYPE;
            BPCNHPRD.PARTI_STRU = BPCSMPRD.PARTI_STRU;
            BPCNHPRD.SOLD_RGN = BPCSMPRD.SOLD_RGN;
            BPCNHPRD.SOLD_BR = BPCSMPRD.SOLD_BR;
            BPCNHPRD.PRDT_CUST_INFO = BPCSMPRD.PRDT_CUST_INFO;
            BPCNHPRD.PRDT_MARKT_GOAL = BPCSMPRD.PRDT_MARKT_GOAL;
            BPCNHPRD.COMP_PRDT_DESC = BPCSMPRD.COMP_PRDT_DESC;
            BPCNHPRD.PRDT_ADV_DESC = BPCSMPRD.PRDT_ADV_DESC;
            BPCNHPRD.PRDT_FAV_INFO = BPCSMPRD.PRDT_FAV_INFO;
            BPCNHPRD.CHNL_SCOPE = BPCSMPRD.CHNL_SCOPE;
            BPCNHPRD.CHNL1 = BPCSMPRD.CHNL1;
            BPCNHPRD.CHNL2 = BPCSMPRD.CHNL2;
            BPCNHPRD.CHNL3 = BPCSMPRD.CHNL3;
            BPCNHPRD.CHNL4 = BPCSMPRD.CHNL4;
            BPCNHPRD.CHNL5 = BPCSMPRD.CHNL5;
            BPCNHPRD.CHNL6 = BPCSMPRD.CHNL6;
            BPCNHPRD.CHNL7 = BPCSMPRD.CHNL7;
            BPCNHPRD.CHNL8 = BPCSMPRD.CHNL8;
            BPCNHPRD.CHNL9 = BPCSMPRD.CHNL9;
            BPCNHPRD.CHNL10 = BPCSMPRD.CHNL10;
            BPCNHPRD.PRDT_COUNTER = BPCSMPRD.PRDT_COUNTER;
            BPCNHPRD.SOLD_NUM = BPCSMPRD.SOLD_NUM;
            BPCNHPRD.SOLD_AMT = BPCSMPRD.SOLD_AMT;
            BPCNHPRD.BEL_BR = BPCSMPRD.BEL_BR;
            BPCNHPRD.LEVEL1 = BPCSMPRD.LEVEL1;
            BPCNHPRD.LEVEL2 = BPCSMPRD.LEVEL2;
            BPCNHPRD.LEVEL3 = BPCSMPRD.LEVEL3;
            BPCNHPRD.LEVEL4 = BPCSMPRD.LEVEL4;
            BPCNHPRD.LEVEL5 = BPCSMPRD.LEVEL5;
            BPCNHPRD.LEVEL6 = BPCSMPRD.LEVEL6;
            BPCNHPRD.LEVEL7 = BPCSMPRD.LEVEL7;
            BPCNHPRD.LEVEL8 = BPCSMPRD.LEVEL8;
            BPCNHPRD.LEVEL9 = BPCSMPRD.LEVEL9;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHPRD;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZUMPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-IRDD-PARM", DCCUMPRM);
        CEP.TRC(SCCGWA, DCCUMPRM.RC);
        if (DCCUMPRM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUMPRM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-MPRD", DDCUPARM);
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCCTLPM.RC.RC_RTNCODE+"", BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPDME() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.POINTER = BPRPDME;
        BPCTPDME.LEN = 516;
        IBS.CALLCPN(SCCGWA, K_T_MAINT_PRDT_MENU, BPCTPDME);
        if (BPCTPDME.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTPDME.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPDCD() throws IOException,SQLException,Exception {
        BPCTPDCD.INFO.POINTER = BPRPDCD;
        BPCTPDCD.LEN = 430;
        IBS.CALLCPN(SCCGWA, K_T_MAINT_PRDT_INFO, BPCTPDCD);
        if (BPCTPDCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTPDCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_PRD_TYPE, BPCPQPDT);
        if (BPCPQPDT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZGCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-PROD", BPCGCNGL);
    }
    public void S000_CALL_AIZGBB01() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHEK-PROD", AICGBB01);
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_PRD_MODEL, BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_QPCD_MAIN, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_ERROR, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_RGNC, BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_RECORD_NO_EXIST, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCGWY() throws IOException,SQLException,Exception {
        BPCRCGWY.POINTER = BPRCGWY;
        BPCRCGWY.LEN = 50;
        IBS.CALLCPN(SCCGWA, K_R_DRW_CGWY, BPCRCGWY);
    }
    public void S000_LINK_CIZMCGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMCGRP", CICMCGRP);
        if (CICMCGRP.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMCGRP.RC);
        }
    }
    public void S000_CALL_DCZSIRLN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SIR-LN", DCCSIRLN);
        if (DCCSIRLN.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, DCCSIRLN.RC.RC_CODE+"", BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_PNZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-PNZSMPRD", PNCPMPRD);
    }
    public void S000_CALL_TDZMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MPRD-MAINT", TDCMPRD);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_RECORD_NO_EXIST, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
