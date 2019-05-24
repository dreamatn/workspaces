package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMGRP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_GRP = "BP-R-MGM-GRP        ";
    String CPN_R_GRPB = "BP-R-MGM-GRPB       ";
    String CPN_R_QBNK = "BP-P-QUERY-BANK     ";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP502";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 5;
    int K_MAX_DATE = 99991231;
    String K_HIS_REMARK = "ROLE INFOMATION MAINTAIN";
    String K_HIS_COPYBOOK = "BPRGRP  ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    char WS_EFF_TROL_FLAG = ' ';
    char WS_EFF_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRGRP BPRGRP = new BPRGRP();
    BPRGRP BPROGRP = new BPRGRP();
    BPCRGRP BPCRGRP = new BPCRGRP();
    BPCRGRPB BPCRGRPB = new BPCRGRPB();
    BPCOGRP1 BPCOGRP1 = new BPCOGRP1();
    BPCOGRPL BPCOGRPL = new BPCOGRPL();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRTROL BPRTROL = new BPRTROL();
    BPCRTROB BPCRTROB = new BPCRTROB();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPCRGPLB BPCRGPLB = new BPCRGPLB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSMGRP BPCSMGRP;
    public void MP(SCCGWA SCCGWA, BPCSMGRP BPCSMGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMGRP = BPCSMGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMGRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRGRP);
        IBS.init(SCCGWA, BPROGRP);
        IBS.init(SCCGWA, BPCRGRP);
        IBS.init(SCCGWA, BPCRGRPB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMGRP.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRP.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRP.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRP.FUNC == '1'
            || BPCSMGRP.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMGRP.FUNC == 'D') {
            B002_CHECK_FOR_DELETE();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMGRP.EFF_DATE == 0) {
            BPCSMGRP.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSMGRP.FUNC == 'A' 
            && BPCSMGRP.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMGRP.EXP_DATE == 0) {
            BPCSMGRP.EXP_DATE = K_MAX_DATE;
        }
        if (BPCSMGRP.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMGRP.EXP_DATE < BPCSMGRP.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_CHECK_FOR_DELETE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMGRP.ROLE_CD);
        WS_EFF_TROL_FLAG = 'N';
        IBS.init(SCCGWA, BPRTROL);
        IBS.init(SCCGWA, BPCRTROB);
        BPRTROL.KEY.IN_FLG = ALL.charAt(0);
        BPRTROL.KEY.SYS_MMO = "%%%%%";
        BPRTROL.KEY.TX_CD = "%%%%%%%";
        BPRTROL.KEY.ATH_TYP = ALL.charAt(0);
        BPRTROL.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        BPCRTROB.INFO.FUNC = '1';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        BPCRTROB.INFO.FUNC = 'N';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRTROB.RETURN_INFO != 'N' 
            && WS_EFF_TROL_FLAG != 'Y'; WS_CNT += 1) {
            if (BPCSMGRP.ROLE_CD.equalsIgnoreCase(BPRTROL.KEY.ROLE_CD) 
                && BPRTROL.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                && BPRTROL.EXP_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, BPRTROL.KEY.IN_FLG);
                CEP.TRC(SCCGWA, BPRTROL.KEY.SYS_MMO);
                CEP.TRC(SCCGWA, BPRTROL.KEY.TX_CD);
                CEP.TRC(SCCGWA, "BPTTXIF-HAVE-ROLE");
                WS_EFF_TROL_FLAG = 'Y';
            }
            BPCRTROB.INFO.FUNC = 'N';
            S000_CALL_BPZRTROB();
            if (pgmRtn) return;
        }
        BPCRTROB.INFO.FUNC = 'E';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        WS_EFF_GRPL_FLAG = 'N';
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPCRGPLB);
        BPRGRPL.KEY.ASS_TYP = ALL.charAt(0);
        BPRGRPL.KEY.ASS_ID = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        BPRGRPL.KEY.ATH_TYP = ALL.charAt(0);
        BPCRGPLB.INFO.FUNC = '1';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
        BPCRGPLB.INFO.FUNC = 'N';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRGPLB.RETURN_INFO != 'N' 
            && WS_EFF_GRPL_FLAG != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                if (BPCSMGRP.ROLE_CD.equalsIgnoreCase(BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD) 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                    CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_TYP);
                    CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_ID);
                    CEP.TRC(SCCGWA, BPRGRPL.KEY.ATH_TYP);
                    CEP.TRC(SCCGWA, "BPTGRPL-HAVE-ROLE");
                    WS_EFF_GRPL_FLAG = 'Y';
                }
            }
            BPCRGPLB.INFO.FUNC = 'N';
            S000_CALL_BPZRGPLB();
            if (pgmRtn) return;
        }
        BPCRGPLB.INFO.FUNC = 'E';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
        if (WS_EFF_TROL_FLAG == 'Y' 
            || WS_EFF_GRPL_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ROLE_CAN_NOT_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRP);
        BPRGRP.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        BPCRGRP.INFO.FUNC = 'Q';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        if (BPCRGRP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRP);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRGRP.INFO.FUNC = 'A';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        if (BPCRGRP.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRP);
        BPCRGRP.INFO.FUNC = 'R';
        BPRGRP.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        if (BPCRGRP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRGRP.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
            && BPRGRP.EFF_DT != BPCSMGRP.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRP, BPROGRP);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRGRP.INFO.FUNC = 'U';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMGRP.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSMGRP.FUNC == '2') {
            B040_02_BRW_OTHER();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRP);
        if (BPCSMGRP.ROLE_CD.trim().length() == 0) {
            BPRGRP.KEY.ROLE_CD = "%%%%";
        } else {
            BPRGRP.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        }
        BPCRGRPB.INFO.FUNC = '1';
        S000_CALL_BPZRGRPB();
        if (pgmRtn) return;
        BPCRGRPB.INFO.FUNC = 'N';
        S000_CALL_BPZRGRPB();
        if (pgmRtn) return;
        if (BPCRGRPB.RETURN_INFO == 'F') {
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRGRPB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRGRPB.INFO.FUNC = 'N';
            S000_CALL_BPZRGRPB();
            if (pgmRtn) return;
        }
        BPCRGRPB.INFO.FUNC = 'E';
        S000_CALL_BPZRGRPB();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 86;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOGRPL);
        BPCOGRPL.ROLE_CD = BPRGRP.KEY.ROLE_CD;
        BPCOGRPL.ABBR_NAME_CHN = BPRGRP.ROLE_CNMS;
        BPCOGRPL.FILLER_CN_01 = 0X02;
        BPCOGRPL.ABBR_NAME_ENG = BPRGRP.ROLE_ENMS;
        BPCOGRPL.EFF_DATE = BPRGRP.EFF_DT;
        BPCOGRPL.EXP_DATE = BPRGRP.EXP_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOGRPL);
        SCCMPAG.DATA_LEN = 86;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_BRW_OTHER() throws IOException,SQLException,Exception {
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRP);
        BPCRGRP.INFO.FUNC = 'R';
        BPRGRP.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        if (BPCRGRP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOGRP1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        BPCRGRP.INFO.FUNC = 'D';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSMGRP.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMGRP.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROGRP;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRGRP;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSMGRP.FUNC != 'D') {
            IBS.init(SCCGWA, BPCOGRP1);
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSMGRP.FUNC == 'A' 
            || BPCSMGRP.FUNC == 'U' 
            || BPCSMGRP.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = BPCOGRP1;
        SCCFMT.DATA_LEN = 294;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRGRP.KEY.ROLE_CD = BPCSMGRP.ROLE_CD;
        BPRGRP.ROLE_CNNM = BPCSMGRP.NAME_CHN;
        BPRGRP.ROLE_ENNM = BPCSMGRP.NAME_ENG;
        BPRGRP.ROLE_CNMS = BPCSMGRP.ABBR_NAME_CHN;
        BPRGRP.ROLE_ENMS = BPCSMGRP.ABBR_NAME_ENG;
        BPRGRP.ATH_MOV_FLG = BPCSMGRP.AUTH_MOVE_FLG;
        BPRGRP.EFF_DT = BPCSMGRP.EFF_DATE;
        BPRGRP.EXP_DT = BPCSMGRP.EXP_DATE;
        BPRGRP.UPD_DT = BPCSMGRP.UPD_DATE;
        BPRGRP.UPD_TLR = BPCSMGRP.UPD_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOGRP1.ROLE_CD = BPRGRP.KEY.ROLE_CD;
        BPCOGRP1.NAME_CHN = BPRGRP.ROLE_CNNM;
        BPCOGRP1.FILLER_CN_01 = 0X02;
        BPCOGRP1.NAME_ENG = BPRGRP.ROLE_ENNM;
        BPCOGRP1.ABBR_NAME_CHN = BPRGRP.ROLE_CNMS;
        BPCOGRP1.FILLER_CN_02 = 0X02;
        BPCOGRP1.ABBR_NAME_ENG = BPRGRP.ROLE_ENMS;
        BPCOGRP1.AUTH_MOVE_FLG = BPRGRP.ATH_MOV_FLG;
        BPCOGRP1.EFF_DATE = BPRGRP.EFF_DT;
        BPCOGRP1.EXP_DATE = BPRGRP.EXP_DT;
        BPCOGRP1.UPD_DATE = BPRGRP.UPD_DT;
        BPCOGRP1.UPD_TLR = BPRGRP.UPD_TLR;
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_QBNK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRP() throws IOException,SQLException,Exception {
        BPCRGRP.INFO.POINTER = BPRGRP;
        BPCRGRP.INFO.LEN = 292;
        IBS.CALLCPN(SCCGWA, CPN_R_GRP, BPCRGRP);
        if (BPCRGRP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGRP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRPB() throws IOException,SQLException,Exception {
        BPCRGRPB.INFO.POINTER = BPRGRP;
        BPCRGRPB.INFO.LEN = 292;
        IBS.CALLCPN(SCCGWA, CPN_R_GRPB, BPCRGRPB);
        if (BPCRGRPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGRPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTROB() throws IOException,SQLException,Exception {
        BPCRTROB.INFO.POINTER = BPRTROL;
        BPCRTROB.INFO.LEN = 50;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TROB       ", BPCRTROB);
        if (BPCRTROB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTROB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGPLB() throws IOException,SQLException,Exception {
        BPCRGPLB.INFO.POINTER = BPRGRPL;
        BPCRGPLB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GPLB       ", BPCRGPLB);
        if (BPCRGPLB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGPLB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
