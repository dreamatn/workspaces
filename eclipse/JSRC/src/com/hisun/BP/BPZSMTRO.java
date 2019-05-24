package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMTRO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TROL = "BP-R-MGM-TROL       ";
    String CPN_R_TROB = "BP-R-MGM-TROB       ";
    String CPN_R_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_R_GRP = "BP-R-MGM-GRP        ";
    String CPN_F_WORKFLOW = "SM-S-INQ-WFW        ";
    String CPN_F_SUBS = "SC-SET-SUBS-TRANS   ";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP504";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 9;
    int K_MAX_DATE = 99991231;
    String K_HIS_REMARK_O = "OPERATION ROLE OF TXN INF MAINTAIN";
    String K_HIS_REMARK_A = "AUTHORITY ROLE OF TXN INF MAINTAIN";
    String K_HIS_COPYBOOK = "BPRTROL ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    int K_MAX_CNT = 500;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTROL BPRTROL = new BPRTROL();
    BPRTROL BPROTROL = new BPRTROL();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPRGRP BPRGRP = new BPRGRP();
    BPCRTROL BPCRTROL = new BPCRTROL();
    BPCRTROB BPCRTROB = new BPCRTROB();
    BPCOTRL1 BPCOTRL1 = new BPCOTRL1();
    BPCOTRL2 BPCOTRL2 = new BPCOTRL2();
    BPCOTRLL BPCOTRLL = new BPCOTRLL();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCRGRP BPCRGRP = new BPCRGRP();
    SMCSQWFW SMCSQWFW = new SMCSQWFW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSMTRO BPCSMTRO;
    public void MP(SCCGWA SCCGWA, BPCSMTRO BPCSMTRO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMTRO = BPCSMTRO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMTRO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTROL);
        IBS.init(SCCGWA, BPROTROL);
        IBS.init(SCCGWA, BPCRTROL);
        IBS.init(SCCGWA, BPCRTROB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMTRO.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTRO.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTRO.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTRO.FUNC == '1'
            || BPCSMTRO.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMTRO.FUNC == 'D') {
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
        if (BPCSMTRO.IN_FLG == 'I' 
            || BPCSMTRO.IN_FLG == 'O') {
            IBS.init(SCCGWA, BPRTXIF);
            IBS.init(SCCGWA, BPCRTXIF);
            BPRTXIF.KEY.IN_FLG = BPCSMTRO.IN_FLG;
            BPRTXIF.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
            BPRTXIF.KEY.TX_CD = BPCSMTRO.TX_CD;
            BPCRTXIF.INFO.FUNC = 'Q';
            S000_CALL_BPZRTXIF();
            if (pgmRtn) return;
            if (BPCRTXIF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTXIF.STS != 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_INV;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMTRO.IN_FLG == 'F') {
            IBS.init(SCCGWA, SMCSQWFW);
            if (BPCSMTRO.TX_CD == null) BPCSMTRO.TX_CD = "";
            JIBS_tmp_int = BPCSMTRO.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPCSMTRO.TX_CD += " ";
            SMCSQWFW.CODE = BPCSMTRO.TX_CD.substring(0, 5);
            if (BPCSMTRO.TX_CD == null) BPCSMTRO.TX_CD = "";
            JIBS_tmp_int = BPCSMTRO.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPCSMTRO.TX_CD += " ";
            SMCSQWFW.Q_STS = BPCSMTRO.TX_CD.substring(6 - 1, 6 + 2 - 1);
            S000_CALL_SMZSQWFW();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRGRP);
        IBS.init(SCCGWA, BPCRGRP);
        BPRGRP.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        BPCRGRP.INFO.FUNC = 'Q';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        if (BPCRGRP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRGRP.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_GRP_NOT_EFF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRGRP.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_INV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTRO.EFF_DATE == 0) {
            BPCSMTRO.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSMTRO.FUNC == 'A' 
            && BPCSMTRO.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTRO.EXP_DATE == 0) {
            BPCSMTRO.EXP_DATE = K_MAX_DATE;
        }
        if (BPCSMTRO.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTRO.EXP_DATE < BPCSMTRO.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        BPRTROL.KEY.ATH_TYP = BPCSMTRO.AUTH_TYP;
        BPRTROL.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        BPCRTROL.INFO.FUNC = 'Q';
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
        if (BPCRTROL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TROL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRTROL.INFO.FUNC = 'A';
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
        if (BPCRTROL.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TROL_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        BPCRTROL.INFO.FUNC = 'R';
        BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        BPRTROL.KEY.ATH_TYP = BPCSMTRO.AUTH_TYP;
        BPRTROL.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
        if (BPCRTROL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TROL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTROL.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
            && BPRTROL.EFF_DT != BPCSMTRO.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTROL.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSMTRO.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTROL, BPROTROL);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRTROL.INFO.FUNC = 'U';
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMTRO.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSMTRO.FUNC == '2') {
            B040_02_BRW_MAINTAIN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        if (BPCSMTRO.IN_FLG == ' ') {
            BPRTROL.KEY.IN_FLG = ALL.charAt(0);
        } else {
            BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        }
        if (BPCSMTRO.SYS_MMO.trim().length() == 0) {
            BPRTROL.KEY.SYS_MMO = "%%%%%";
        } else {
            BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        }
        if (BPCSMTRO.TX_CD.trim().length() == 0) {
            BPRTROL.KEY.TX_CD = "%%%%%%%";
        } else {
            BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        }
        if (BPCSMTRO.AUTH_TYP == ' ') {
            BPRTROL.KEY.ATH_TYP = ALL.charAt(0);
        } else {
            BPRTROL.KEY.ATH_TYP = BPCSMTRO.AUTH_TYP;
        }
        if (BPCSMTRO.ROLE_CD.trim().length() == 0) {
            BPRTROL.KEY.ROLE_CD = "%%%%";
        } else {
            BPRTROL.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        }
        BPCRTROB.INFO.FUNC = '1';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        BPCRTROB.INFO.FUNC = 'N';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        B040_01_02_OUT_NORMAL();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRTROB.RETURN_INFO != 'N' 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            if (!BPRTROL.KEY.ROLE_CD.equalsIgnoreCase(BPCOTRLL.TSQ_LIST.ROLE_CD)) {
                IBS.init(SCCGWA, BPRGRP);
                IBS.init(SCCGWA, BPCRGRP);
                BPRGRP.KEY.ROLE_CD = BPRTROL.KEY.ROLE_CD;
                BPCRGRP.INFO.FUNC = 'Q';
                S000_CALL_BPZRGRP();
                if (pgmRtn) return;
            }
            if (BPRTROL.KEY.IN_FLG != BPCOTRLL.TSQ_LIST.IN_FLG 
                || !BPRTROL.KEY.SYS_MMO.equalsIgnoreCase(BPCOTRLL.TSQ_LIST.SYS_MMO) 
                || !BPRTROL.KEY.TX_CD.equalsIgnoreCase(BPCOTRLL.TSQ_LIST.TX_CD)) {
                IBS.init(SCCGWA, BPRTXIF);
                IBS.init(SCCGWA, BPCRTXIF);
                BPRTXIF.KEY.IN_FLG = BPRTROL.KEY.IN_FLG;
                BPRTXIF.KEY.SYS_MMO = BPRTROL.KEY.SYS_MMO;
                BPRTXIF.KEY.TX_CD = BPRTROL.KEY.TX_CD;
                BPCRTXIF.INFO.FUNC = 'Q';
                S000_CALL_BPZRTXIF();
                if (pgmRtn) return;
            }
            B040_01_03_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRTROB.INFO.FUNC = 'N';
            S000_CALL_BPZRTROB();
            if (pgmRtn) return;
        }
        BPCRTROB.INFO.FUNC = 'E';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_NORMAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTRLL);
        BPCOTRLL.NORMAL.FILLER = 0X03;
        BPCOTRLL.NORMAL.FLAG = BPCSMTRO.BRW_TYPE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTRLL.NORMAL);
        SCCMPAG.DATA_LEN = 2;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTRLL);
        BPCOTRLL.TSQ_LIST.IN_FLG = BPRTROL.KEY.IN_FLG;
        BPCOTRLL.TSQ_LIST.SYS_MMO = BPRTROL.KEY.SYS_MMO;
        BPCOTRLL.TSQ_LIST.TX_CD = BPRTROL.KEY.TX_CD;
        BPCOTRLL.TSQ_LIST.AUTH_TYP = BPRTROL.KEY.ATH_TYP;
        BPCOTRLL.TSQ_LIST.ROLE_CD = BPRTROL.KEY.ROLE_CD;
        BPCOTRLL.TSQ_LIST.ROLE_CNMS = BPRGRP.ROLE_CNMS;
        BPCOTRLL.TSQ_LIST.EFF_DATE = BPRTROL.EFF_DT;
        BPCOTRLL.TSQ_LIST.EXP_DATE = BPRTROL.EXP_DT;
        BPCOTRLL.TSQ_LIST.UPD_DATE = BPRTROL.UPD_DT;
        BPCOTRLL.TSQ_LIST.UPD_TLR = BPRTROL.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTRLL.TSQ_LIST);
        SCCMPAG.DATA_LEN = 204;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_BRW_MAINTAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        if (BPCSMTRO.IN_FLG == ' ') {
            BPRTROL.KEY.IN_FLG = ALL.charAt(0);
        } else {
            BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        }
        if (BPCSMTRO.SYS_MMO.trim().length() == 0) {
            BPRTROL.KEY.SYS_MMO = "%%%%%";
        } else {
            BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        }
        BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        BPCRTROB.INFO.FUNC = '2';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        BPCRTROB.INFO.FUNC = 'N';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
        B040_02_01_OUT_TITLE();
        if (pgmRtn) return;
        B040_02_02_OUT_INFO();
        if (pgmRtn) return;
        while (BPCRTROB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B040_02_03_OUT_LIST_DATA();
            if (pgmRtn) return;
            BPCRTROB.INFO.FUNC = 'N';
            S000_CALL_BPZRTROB();
            if (pgmRtn) return;
        }
        BPCRTROB.INFO.FUNC = 'E';
        S000_CALL_BPZRTROB();
        if (pgmRtn) return;
    }
    public void B040_02_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 51;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_02_OUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTRL2);
        BPCOTRL2.TXN_INF.FILLER = 0X03;
        BPCOTRL2.TXN_INF.IN_FLG = BPCSMTRO.IN_FLG;
        BPCOTRL2.TXN_INF.SYS_MMO = BPCSMTRO.SYS_MMO;
        BPCOTRL2.TXN_INF.TX_CD = BPCSMTRO.TX_CD;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTRL2.TXN_INF);
        SCCMPAG.DATA_LEN = 14;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_03_OUT_LIST_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTRL2);
        BPCOTRL2.LST_INF.ROLE_CD = BPRTROL.KEY.ROLE_CD;
        BPCOTRL2.LST_INF.AUTH_TYP = BPRTROL.KEY.ATH_TYP;
        BPCOTRL2.LST_INF.EFF_DATE = BPRTROL.EFF_DT;
        BPCOTRL2.LST_INF.EXP_DATE = BPRTROL.EXP_DT;
        BPCOTRL2.LST_INF.UPD_DATE = BPRTROL.UPD_DT;
        BPCOTRL2.LST_INF.UPD_TLR = BPRTROL.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTRL2.LST_INF);
        SCCMPAG.DATA_LEN = 37;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTROL);
        BPCRTROL.INFO.FUNC = 'R';
        BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        BPRTROL.KEY.ATH_TYP = BPCSMTRO.AUTH_TYP;
        BPRTROL.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        CEP.TRC(SCCGWA, BPRTROL.KEY.IN_FLG);
        CEP.TRC(SCCGWA, BPRTROL.KEY.SYS_MMO);
        CEP.TRC(SCCGWA, BPRTROL.KEY.TX_CD);
        CEP.TRC(SCCGWA, BPRTROL.KEY.ATH_TYP);
        CEP.TRC(SCCGWA, BPRTROL.KEY.ROLE_CD);
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
        if (BPCRTROL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TROL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOTRL1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        BPCRTROL.INFO.FUNC = 'D';
        S000_CALL_BPZRTROL();
        if (pgmRtn) return;
        if (BPCRTROL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TROL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCSMTRO.AUTH_TYP == '0') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_O;
        }
        if (BPCSMTRO.AUTH_TYP == '1') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_A;
        }
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSMTRO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMTRO.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROTROL;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTROL;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSMTRO.FUNC != 'D') {
            IBS.init(SCCGWA, BPCOTRL1);
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSMTRO.FUNC == 'A' 
            || BPCSMTRO.FUNC == 'U' 
            || BPCSMTRO.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        if (BPCSMTRO.FUNC == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            if (BPCSMTRO.BRW_TYPE == '2') {
                SCCSUBS.TR_CODE = 4808;
            } else {
                if (BPCSMTRO.AUTH_TYP == '0') {
                    SCCSUBS.TR_CODE = 4832;
                }
                if (BPCSMTRO.AUTH_TYP == '1') {
                    SCCSUBS.TR_CODE = 4835;
                }
            }
            S000_CALL_SCZSUBS();
            if (pgmRtn) return;
        }
        SCCFMT.DATA_PTR = BPCOTRL1;
        SCCFMT.DATA_LEN = 49;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRTROL.KEY.IN_FLG = BPCSMTRO.IN_FLG;
        BPRTROL.KEY.SYS_MMO = BPCSMTRO.SYS_MMO;
        BPRTROL.KEY.TX_CD = BPCSMTRO.TX_CD;
        BPRTROL.KEY.ATH_TYP = BPCSMTRO.AUTH_TYP;
        BPRTROL.KEY.ROLE_CD = BPCSMTRO.ROLE_CD;
        BPRTROL.EFF_DT = BPCSMTRO.EFF_DATE;
        BPRTROL.EXP_DT = BPCSMTRO.EXP_DATE;
        BPRTROL.UPD_DT = BPCSMTRO.UPD_DATE;
        BPRTROL.UPD_TLR = BPCSMTRO.UPD_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOTRL1.IN_FLG = BPRTROL.KEY.IN_FLG;
        BPCOTRL1.SYS_MMO = BPRTROL.KEY.SYS_MMO;
        BPCOTRL1.TX_CD = BPRTROL.KEY.TX_CD;
        BPCOTRL1.ROLE_CD = BPRTROL.KEY.ROLE_CD;
        BPCOTRL1.EFF_DATE = BPRTROL.EFF_DT;
        BPCOTRL1.EXP_DATE = BPRTROL.EXP_DT;
        BPCOTRL1.UPD_DATE = BPRTROL.UPD_DT;
        BPCOTRL1.UPD_TLR = BPRTROL.UPD_TLR;
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_TXIF, BPCRTXIF);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
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
    public void S000_CALL_SMZSQWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_WORKFLOW, SMCSQWFW);
        if (SMCSQWFW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCSQWFW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_SUBS, SCCSUBS);
    }
    public void S000_CALL_BPZRTROL() throws IOException,SQLException,Exception {
        BPCRTROL.INFO.POINTER = BPRTROL;
        BPCRTROL.INFO.LEN = 50;
        IBS.CALLCPN(SCCGWA, CPN_R_TROL, BPCRTROL);
        if (BPCRTROL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTROL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTROB() throws IOException,SQLException,Exception {
        BPCRTROB.INFO.POINTER = BPRTROL;
        BPCRTROB.INFO.LEN = 50;
        IBS.CALLCPN(SCCGWA, CPN_R_TROB, BPCRTROB);
        if (BPCRTROB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTROB.RC);
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
