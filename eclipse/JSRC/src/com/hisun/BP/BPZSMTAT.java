package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMTAT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TATH = "BP-R-MGM-TATH    ";
    String CPN_R_TATB = "BP-R-MGM-TATB    ";
    String CPN_F_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_CHNL = "BP-R-MGM-CHNL       ";
    String CPN_R_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_TAT_CHK = "BP-F-OTS-TATH-CHK   ";
    String CPN_F_WORKFLOW = "SM-S-INQ-WFW        ";
    String CPN_R_QBNK = "BP-P-QUERY-BANK     ";
    String CPN_F_SUBS = "SC-SET-SUBS-TRANS   ";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP506";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 13;
    int K_MAX_DATE = 99991231;
    String K_HIS_REMARK_O = "OPERATION ROLE OF SPEC TXN INF MAINTAIN";
    String K_HIS_REMARK_A = "AUTHORITY ROLE OF SPEC TXN INF MAINTAIN";
    String K_HIS_COPYBOOK = "BPRTATH ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    int WS_ASS_BR = 0;
    int WS_ASS_UP_BR = 0;
    String WS_CHNL = " ";
    String WS_ERR_MSG = " ";
    short WS_BR_LENG = 0;
    short WS_TLR_LENG = 0;
    short WS_CHNL_LENG = 0;
    short WS_I = 0;
    char WS_TBL_TATH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTATH BPRTATH = new BPRTATH();
    BPRTATH BPROTATH = new BPRTATH();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPCRTATH BPCRTATH = new BPCRTATH();
    BPCRTATB BPCRTATB = new BPCRTATB();
    BPCOTAT1 BPCOTAT1 = new BPCOTAT1();
    BPCOTATL BPCOTATL = new BPCOTATL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SMCSQWFW SMCSQWFW = new SMCSQWFW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSMTAT BPCSMTAT;
    public void MP(SCCGWA SCCGWA, BPCSMTAT BPCSMTAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMTAT = BPCSMTAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMTAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTATH);
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPCRTATB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_BR_LENG = 6;
        WS_TLR_LENG = 8;
        WS_CHNL_LENG = 5;
        if (BPCSMTAT.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTAT.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTAT.FUNC == 'U') {
            if (BPCSMTAT.MODIFY_FUNC != 'D') {
                B001_CHECK_INPUT();
                if (pgmRtn) return;
            }
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            if (BPCSMTAT.MODIFY_FUNC == 'D') {
                B060_CHECK_FOR_DELETE();
                if (pgmRtn) return;
            }
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTAT.FUNC == '1'
            || BPCSMTAT.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMTAT.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B060_CHECK_FOR_DELETE();
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
        if (BPCSMTAT.ASS_TYP == 'O') {
            if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
            JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
            if (!IBS.isNumeric(BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
            JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
            if (BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG).trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG));
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_ASS_BR = BPCPQORG.BR;
            WS_ASS_UP_BR = BPCPQORG.SUPR_BR;
        }
        if (BPCSMTAT.ASS_TYP == 'T') {
            IBS.init(SCCGWA, BPCFTLRQ);
            if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
            JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
            BPCFTLRQ.INFO.TLR = BPCSMTAT.ASS_ID.substring(0, WS_TLR_LENG);
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_STS != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ASS_BR = BPCFTLRQ.INFO.TLR_BR;
        }
        if (BPCSMTAT.ASS_TYP == 'C') {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRCHNTB);
            BPRCHNTB.KEY.TYP = "CHNTB";
            if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
            JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
            BPRCHNTB.KEY.CD = BPCSMTAT.ASS_ID.substring(0, WS_CHNL_LENG);
            BPCPRMR.DAT_PTR = BPRCHNTB;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHNL_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMTAT.IN_FLG == 'I' 
            || BPCSMTAT.IN_FLG == 'O') {
            IBS.init(SCCGWA, BPRTXIF);
            IBS.init(SCCGWA, BPCRTXIF);
            BPRTXIF.KEY.IN_FLG = BPCSMTAT.IN_FLG;
            BPRTXIF.KEY.SYS_MMO = BPCSMTAT.SYS_MMO;
            BPRTXIF.KEY.TX_CD = BPCSMTAT.TX_CD;
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
        if (BPCSMTAT.IN_FLG == 'F') {
            IBS.init(SCCGWA, SMCSQWFW);
            if (BPCSMTAT.TX_CD == null) BPCSMTAT.TX_CD = "";
            JIBS_tmp_int = BPCSMTAT.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPCSMTAT.TX_CD += " ";
            SMCSQWFW.CODE = BPCSMTAT.TX_CD.substring(0, 5);
            if (BPCSMTAT.TX_CD == null) BPCSMTAT.TX_CD = "";
            JIBS_tmp_int = BPCSMTAT.TX_CD.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) BPCSMTAT.TX_CD += " ";
            SMCSQWFW.Q_STS = BPCSMTAT.TX_CD.substring(6 - 1, 6 + 2 - 1);
            S000_CALL_SMZSQWFW();
            if (pgmRtn) return;
        }
        if (BPCSMTAT.FUNC == 'A') {
            IBS.init(SCCGWA, BPCPQBNK);
            BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            S000_CALL_BPZPQBNK();
            if (pgmRtn) return;
            if (BPCPQBNK.DATA_INFO.TLR_COND == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_COND_ROL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSMTAT.EFF_DATE == 0) {
            BPCSMTAT.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCSMTAT.FUNC == 'A' 
            && BPCSMTAT.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTAT.EXP_DATE == 0) {
            BPCSMTAT.EXP_DATE = K_MAX_DATE;
        }
        if (BPCSMTAT.EXP_DATE < BPCSMTAT.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTAT.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTAT.FUNC == 'A' 
            || BPCSMTAT.MODIFY_FUNC == 'A' 
            || BPCSMTAT.MODIFY_FUNC == 'M') {
            if (BPCSMTAT.FUNC == 'A') {
                IBS.init(SCCGWA, BPRTATH);
                BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
                BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
                BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
                BPCRTATH.INFO.FUNC = 'Q';
                S000_CALL_BPZRTATH();
                if (pgmRtn) return;
                if (BPCRTATH.RETURN_INFO == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMTAT.ASS_TYP == 'T') {
                IBS.init(SCCGWA, BPCFOTTA);
                BPCFOTTA.ASSTYP = 'O';
                BPCFOTTA.ASS_ID = "" + WS_ASS_BR;
                JIBS_tmp_int = BPCFOTTA.ASS_ID.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTTA.ASS_ID = "0" + BPCFOTTA.ASS_ID;
                BPCFOTTA.ATH_TYP = BPCSMTAT.AUTH_TYP;
                BPCFOTTA.IN_FLG = BPCSMTAT.IN_FLG;
                BPCFOTTA.SYS_MMO = BPCSMTAT.SYS_MMO;
                BPCFOTTA.TX_CD = BPCSMTAT.TX_CD;
                S000_CALL_BPZFOTTA();
                if (pgmRtn) return;
                if (BPCFOTTA.PRIV_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_ORG_NO_PRIV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMTAT.ASS_TYP == 'O' 
                && WS_ASS_UP_BR != WS_ASS_BR) {
                IBS.init(SCCGWA, BPCFOTTA);
                BPCFOTTA.ASSTYP = 'O';
                BPCFOTTA.ASS_ID = "" + WS_ASS_UP_BR;
                JIBS_tmp_int = BPCFOTTA.ASS_ID.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTTA.ASS_ID = "0" + BPCFOTTA.ASS_ID;
                BPCFOTTA.ATH_TYP = BPCSMTAT.AUTH_TYP;
                BPCFOTTA.IN_FLG = BPCSMTAT.IN_FLG;
                BPCFOTTA.SYS_MMO = BPCSMTAT.SYS_MMO;
                BPCFOTTA.TX_CD = BPCSMTAT.TX_CD;
                S000_CALL_BPZFOTTA();
                if (pgmRtn) return;
                if (BPCFOTTA.PRIV_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_UP_ORG_NO_PRIV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
        BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
        BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
        BPCRTATH.INFO.FUNC = 'Q';
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "RECORD NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TBL_TATH_FLAG = 'N';
        for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT 
            && WS_TBL_TATH_FLAG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD);
            if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG == BPCSMTAT.IN_FLG 
                && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO.equalsIgnoreCase(BPCSMTAT.SYS_MMO) 
                && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD.equalsIgnoreCase(BPCSMTAT.TX_CD)) {
                WS_TBL_TATH_FLAG = 'Y';
            }
        }
        if (WS_TBL_TATH_FLAG == 'N') {
            CEP.TRC(SCCGWA, "TATH NO. NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRTATH.INFO.FUNC = 'A';
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        BPCRTATH.INFO.FUNC = 'R';
        BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
        BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
        BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TBL_TATH_FLAG = 'N';
        for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT 
            && WS_TBL_TATH_FLAG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, "AAAAA");
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO);
            CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD);
            if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG == BPCSMTAT.IN_FLG 
                && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO.equalsIgnoreCase(BPCSMTAT.SYS_MMO) 
                && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD.equalsIgnoreCase(BPCSMTAT.TX_CD)) {
                WS_TBL_TATH_FLAG = 'Y';
            }
        }
        if (BPCSMTAT.MODIFY_FUNC == 'M' 
            || BPCSMTAT.MODIFY_FUNC == 'D') {
            if (WS_TBL_TATH_FLAG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMTAT.MODIFY_FUNC == 'M') {
                if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EFF_DT != BPCSMTAT.EFF_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_STS == '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TRANS_REC_CANNOT_MOD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMTAT.MODIFY_FUNC == 'D') {
                IBS.init(SCCGWA, BPCOTAT1);
                R010_TRANS_DATA_OUPUT();
                if (pgmRtn) return;
            }
        }
        if (BPCSMTAT.MODIFY_FUNC == 'A') {
            if (BPRTATH.TXIF_CNT >= 120) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_MAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_TBL_TATH_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCSMTAT.TXIF_NUM = (short) (BPRTATH.TXIF_CNT + 1);
        }
        IBS.CLONE(SCCGWA, BPRTATH, BPROTATH);
        if (BPCSMTAT.MODIFY_FUNC == 'D') {
            B031_FWD_PROC();
            if (pgmRtn) return;
        } else {
            R000_TRANS_DATA();
            if (pgmRtn) return;
        }
        BPCRTATH.INFO.FUNC = 'U';
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
    }
    public void B031_FWD_PROC() throws IOException,SQLException,Exception {
        BPRTATH.TXIF_CNT += -1;
        for (WS_I = BPCSMTAT.TXIF_NUM; WS_I <= BPRTATH.TXIF_CNT; WS_I += 1) {
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].IN_FLG;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].SYS_MMO;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].TX_CD;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_FLG;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_STS;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_EFF_DT;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_EXP_DT;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_EFF_TM;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_EXP_TM;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].EFF_DT;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].EXP_DT;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_TLR = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I + 1-1].MOV_TLR;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        }
        IBS.init(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1]);
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMTAT.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSMTAT.FUNC == '2') {
            B040_02_BRW_MAINTAIN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        if (BPCSMTAT.ASS_TYP == ' ') {
            BPRTATH.KEY.ASS_TYP = ALL.charAt(0);
        } else {
            BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
        }
        if (BPCSMTAT.ASS_ID.trim().length() == 0) {
            BPRTATH.KEY.ASS_ID = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        } else {
            BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
        }
        if (BPCSMTAT.AUTH_TYP == ' ') {
            BPRTATH.KEY.ATH_TYP = ALL.charAt(0);
        } else {
            BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
        }
        BPCRTATB.INFO.FUNC = '1';
        S000_CALL_BPZRTATB();
        if (pgmRtn) return;
        BPCRTATB.INFO.FUNC = 'N';
        S000_CALL_BPZRTATB();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        while (BPCRTATB.RETURN_INFO != 'N') {
            for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT 
                && SCCMPAG.FUNC != 'E'; WS_I += 1) {
                B040_01_03_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRTATB.INFO.FUNC = 'N';
            S000_CALL_BPZRTATB();
            if (pgmRtn) return;
        }
        BPCRTATB.INFO.FUNC = 'E';
        S000_CALL_BPZRTATB();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 121;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_NORMAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTATL);
        BPCOTATL.NORMAL.FILLER = 0X03;
        BPCOTATL.NORMAL.FLAG = BPCSMTAT.BRW_TYPE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTATL.NORMAL);
        SCCMPAG.DATA_LEN = 2;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTATL);
        BPCOTATL.TSQ_LIST.REC_NO = WS_I;
        BPCOTATL.TSQ_LIST.ASS_TYP = BPRTATH.KEY.ASS_TYP;
        BPCOTATL.TSQ_LIST.ASS_TYP = BPRTATH.KEY.ASS_TYP;
        BPCOTATL.TSQ_LIST.ASS_ID = BPRTATH.KEY.ASS_ID;
        BPCOTATL.TSQ_LIST.AUTH_TYP = BPRTATH.KEY.ATH_TYP;
        BPCOTATL.TSQ_LIST.IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG;
        BPCOTATL.TSQ_LIST.SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO;
        BPCOTATL.TSQ_LIST.TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD;
        BPCOTATL.TSQ_LIST.MOV_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_FLG;
        BPCOTATL.TSQ_LIST.MOV_STS = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS;
        BPCOTATL.TSQ_LIST.MOV_EFF_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT;
        BPCOTATL.TSQ_LIST.MOV_EXP_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT;
        BPCOTATL.TSQ_LIST.MOV_EFF_TIME = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM;
        BPCOTATL.TSQ_LIST.MOV_EXP_TIME = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM;
        BPCOTATL.TSQ_LIST.EFF_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT;
        BPCOTATL.TSQ_LIST.EXP_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT;
        BPCOTATL.TSQ_LIST.MOV_TLR = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_TLR;
        BPCOTATL.TSQ_LIST.UPD_DATE = BPRTATH.UPD_DT;
        BPCOTATL.TSQ_LIST.UPD_TLR = BPRTATH.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTATL.TSQ_LIST);
        SCCMPAG.DATA_LEN = 119;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_BRW_MAINTAIN() throws IOException,SQLException,Exception {
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTATH);
        BPCRTATH.INFO.FUNC = 'R';
        BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
        BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
        BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "RECORD NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOTAT1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        BPCRTATH.INFO.FUNC = 'D';
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCSMTAT.AUTH_TYP == '0') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_O;
        }
        if (BPCSMTAT.AUTH_TYP == '1') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_A;
        }
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSMTAT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMTAT.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROTATH;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTATH;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSMTAT.MODIFY_FUNC != 'D' 
            || BPCSMTAT.FUNC == 'D') {
            IBS.init(SCCGWA, BPCOTAT1);
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSMTAT.FUNC == 'A' 
            || BPCSMTAT.FUNC == 'U' 
            || BPCSMTAT.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        if (BPCSMTAT.FUNC == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            if (BPCSMTAT.BRW_TYPE == '2') {
                SCCSUBS.TR_CODE = 4858;
            } else {
                if (BPCSMTAT.AUTH_TYP == '0') {
                    SCCSUBS.TR_CODE = 4852;
                }
                if (BPCSMTAT.AUTH_TYP == '1') {
                    SCCSUBS.TR_CODE = 4855;
                }
            }
            S000_CALL_SCZSUBS();
            if (pgmRtn) return;
        }
        SCCFMT.DATA_PTR = BPCOTAT1;
        SCCFMT.DATA_LEN = 118;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRTATH.KEY.ASS_TYP = BPCSMTAT.ASS_TYP;
        BPRTATH.KEY.ASS_ID = BPCSMTAT.ASS_ID;
        BPRTATH.KEY.ATH_TYP = BPCSMTAT.AUTH_TYP;
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].IN_FLG = BPCSMTAT.IN_FLG;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].SYS_MMO = BPCSMTAT.SYS_MMO;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].TX_CD = BPCSMTAT.TX_CD;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_FLG = BPCSMTAT.MOV_FLG;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        if (BPCSMTAT.FUNC == 'A' 
            || BPCSMTAT.MODIFY_FUNC == 'A') {
            if (BPCSMTAT.MOV_STS == ' ') {
                BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_STS = '0';
            } else {
                BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_STS = BPCSMTAT.MOV_STS;
                BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            }
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_DT = BPCSMTAT.MOV_EFF_DATE;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_DT = BPCSMTAT.MOV_EXP_DATE;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_TM = BPCSMTAT.MOV_EFF_TIME;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_TM = BPCSMTAT.MOV_EXP_TIME;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_TLR = BPCSMTAT.MOV_TLR;
            BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
            BPRTATH.TXIF_CNT += 1;
        }
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EFF_DT = BPCSMTAT.EFF_DATE;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EXP_DT = BPCSMTAT.EXP_DATE;
        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
        BPRTATH.UPD_DT = BPCSMTAT.UPD_DATE;
        BPRTATH.UPD_TLR = BPCSMTAT.UPD_TLR;
        CEP.TRC(SCCGWA, BPCSMTAT.TXIF_NUM);
        CEP.TRC(SCCGWA, BPRTATH.KEY.ASS_TYP);
        CEP.TRC(SCCGWA, BPRTATH.KEY.ASS_ID);
        CEP.TRC(SCCGWA, BPRTATH.KEY.ATH_TYP);
        CEP.TRC(SCCGWA, BPRTATH.TXIF_CNT);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].IN_FLG);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].SYS_MMO);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].TX_CD);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_FLG);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_STS);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_DT);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_DT);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_TM);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_TM);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_TLR);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EFF_DT);
        CEP.TRC(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EXP_DT);
        CEP.TRC(SCCGWA, BPRTATH.UPD_DT);
        CEP.TRC(SCCGWA, BPRTATH.UPD_TLR);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOTAT1.ASS_TYP = BPRTATH.KEY.ASS_TYP;
        BPCOTAT1.ASS_ID = BPRTATH.KEY.ASS_ID;
        BPCOTAT1.TXIF_NUM = BPCSMTAT.TXIF_NUM;
        BPCOTAT1.IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].IN_FLG;
        BPCOTAT1.SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].SYS_MMO;
        BPCOTAT1.TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].TX_CD;
        BPCOTAT1.MOV_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_FLG;
        BPCOTAT1.MOV_STS = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_STS;
        BPCOTAT1.MOV_EFF_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_DT;
        BPCOTAT1.MOV_EXP_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_DT;
        BPCOTAT1.MOV_EFF_TIME = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EFF_TM;
        BPCOTAT1.MOV_EXP_TIME = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_EXP_TM;
        BPCOTAT1.EFF_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EFF_DT;
        BPCOTAT1.EXP_DATE = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].EXP_DT;
        BPCOTAT1.MOV_TLR = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[BPCSMTAT.TXIF_NUM-1].MOV_TLR;
        BPCOTAT1.UPD_DATE = BPRTATH.UPD_DT;
        BPCOTAT1.UPD_TLR = BPRTATH.UPD_TLR;
    }
    public void B060_CHECK_FOR_DELETE() throws IOException,SQLException,Exception {
        if (BPCSMTAT.ASS_TYP == 'O') {
            IBS.init(SCCGWA, BPCFOTTA);
            BPCFOTTA.ASSTYP = 'O';
            if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
            JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
            BPCFOTTA.ASS_ID = BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG);
            BPCFOTTA.ATH_TYP = BPCSMTAT.AUTH_TYP;
            BPCFOTTA.IN_FLG = BPCSMTAT.IN_FLG;
            BPCFOTTA.SYS_MMO = BPCSMTAT.SYS_MMO;
            BPCFOTTA.TX_CD = BPCSMTAT.TX_CD;
            S000_CALL_BPZFOTTA();
            if (pgmRtn) return;
            if (BPCFOTTA.PRIV_FLG != 'Y') {
                IBS.init(SCCGWA, BPRTLT);
                IBS.init(SCCGWA, BPCRTLTB);
                BPCRTLTB.INFO.FUNC = 'B';
                if (BPCSMTAT.ASS_ID == null) BPCSMTAT.ASS_ID = "";
                JIBS_tmp_int = BPCSMTAT.ASS_ID.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMTAT.ASS_ID += " ";
                if (BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG).trim().length() == 0) BPRTLT.TLR_BR = 0;
                else BPRTLT.TLR_BR = Integer.parseInt(BPCSMTAT.ASS_ID.substring(0, WS_BR_LENG));
                S000_CALL_BPZRTLTB();
                if (pgmRtn) return;
                BPCRTLTB.INFO.FUNC = 'N';
                S000_CALL_BPZRTLTB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
                while (BPCRTLTB.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
                    IBS.init(SCCGWA, BPCFOTTA);
                    BPCFOTTA.ASSTYP = 'T';
                    BPCFOTTA.ASS_ID = BPRTLT.KEY.TLR;
                    BPCFOTTA.ATH_TYP = BPCSMTAT.AUTH_TYP;
                    BPCFOTTA.IN_FLG = BPCSMTAT.IN_FLG;
                    BPCFOTTA.SYS_MMO = BPCSMTAT.SYS_MMO;
                    BPCFOTTA.TX_CD = BPCSMTAT.TX_CD;
                    S000_CALL_BPZFOTTA();
                    if (pgmRtn) return;
                    if (BPCFOTTA.PRIV_FLG == 'Y') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_TLR_HAS_TATH;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    BPCRTLTB.INFO.FUNC = 'N';
                    S000_CALL_BPZRTLTB();
                    if (pgmRtn) return;
                }
                BPCRTLTB.INFO.FUNC = 'E';
                S000_CALL_BPZRTLTB();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY  ", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-OTS-TATH-CHK   ", BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            if (BPCSMTAT.MODIFY_FUNC != 'D' 
                && BPCSMTAT.FUNC == 'D') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
                if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TXIF_NOTFND) 
                    && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TXIF_INV)) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TXIF       ", BPCRTXIF);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK     ", BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SMZSQWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-S-INQ-WFW        ", SMCSQWFW);
        if (SMCSQWFW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCSQWFW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS   ", SCCSUBS);
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        CEP.TRC(SCCGWA, BPRTATH.BLOB_TXIF_TXT.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH    ", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTATH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATB() throws IOException,SQLException,Exception {
        BPCRTATB.INFO.POINTER = BPRTATH;
        BPCRTATB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATB    ", BPCRTATB);
        if (BPCRTATB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTATB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.POINTER = BPRTLT;
        BPCRTLTB.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, "BP-R-STARTBR-TLT    ", BPCRTLTB);
        if (BPCRTLTB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTB.RC);
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
