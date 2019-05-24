package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMGRL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_GRPL = "BP-R-MGM-GRPL       ";
    String CPN_R_GPLB = "BP-R-MGM-GPLB       ";
    String CPN_R_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_CHNL = "BP-R-MGM-CHNL       ";
    String CPN_R_GRP = "BP-R-MGM-GRP        ";
    String CPN_F_ROL_CHK = "BP-F-OTS-ROL-CHK    ";
    String CPN_R_QBNK = "BP-P-QUERY-BANK     ";
    String CPN_F_SUBS = "SC-SET-SUBS-TRANS   ";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP505";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 11;
    int K_MAX_DATE = 99991231;
    String K_HIS_REMARK_O = "SET OPERATION ROLE INF MAINTAIN";
    String K_HIS_REMARK_A = "SET AUTHORITY ROLE INF MAINTAIN";
    String K_HIS_COPYBOOK = "BPRGRPL ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    int WS_ASS_BR = 0;
    int WS_ASS_UP_BR = 0;
    String WS_CHNL = " ";
    String WS_ERR_MSG = " ";
    short WS_BR_LENG = 0;
    short WS_TLR_LENG = 0;
    short WS_CHNL_LENG = 0;
    short WS_I = 0;
    char WS_TBL_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPRGRPL BPROGRPL = new BPRGRPL();
    BPRGRP BPRGRP = new BPRGRP();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    BPCRGPLB BPCRGPLB = new BPCRGPLB();
    BPCOGPL1 BPCOGPL1 = new BPCOGPL1();
    BPCOGPLL BPCOGPLL = new BPCOGPLL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRGRP BPCRGRP = new BPCRGRP();
    BPCFOTRO BPCFOTRO = new BPCFOTRO();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSMGRL BPCSMGRL;
    public void MP(SCCGWA SCCGWA, BPCSMGRL BPCSMGRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMGRL = BPCSMGRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMGRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPROGRPL);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPCRGPLB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMGRL);
        CEP.TRC(SCCGWA, BPCSMGRL.BRW_TYPE);
        WS_BR_LENG = 6;
        WS_TLR_LENG = 8;
        WS_CHNL_LENG = 5;
        if (BPCSMGRL.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRL.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRL.FUNC == 'U') {
            if (BPCSMGRL.MODIFY_FUNC == 'D') {
                B060_CHECK_FOR_DELETE();
                if (pgmRtn) return;
            } else {
                B001_CHECK_INPUT();
                if (pgmRtn) return;
            }
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMGRL.FUNC == '1'
            || BPCSMGRL.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMGRL.FUNC == 'D') {
            B060_CHECK_FOR_DELETE();
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
        if (BPCSMGRL.ASS_TYP == 'O') {
            if (BPCSMGRL.ASS_ID == null) BPCSMGRL.ASS_ID = "";
            JIBS_tmp_int = BPCSMGRL.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMGRL.ASS_ID += " ";
            if (!IBS.isNumeric(BPCSMGRL.ASS_ID.substring(0, WS_BR_LENG))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            if (BPCSMGRL.ASS_ID == null) BPCSMGRL.ASS_ID = "";
            JIBS_tmp_int = BPCSMGRL.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMGRL.ASS_ID += " ";
            if (BPCSMGRL.ASS_ID.substring(0, WS_BR_LENG).trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(BPCSMGRL.ASS_ID.substring(0, WS_BR_LENG));
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_ASS_BR = BPCPQORG.BR;
            WS_ASS_UP_BR = BPCPQORG.SUPR_BR;
        }
        if (BPCSMGRL.ASS_TYP == 'T') {
            IBS.init(SCCGWA, BPCFTLRQ);
            if (BPCSMGRL.ASS_ID == null) BPCSMGRL.ASS_ID = "";
            JIBS_tmp_int = BPCSMGRL.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMGRL.ASS_ID += " ";
            BPCFTLRQ.INFO.TLR = BPCSMGRL.ASS_ID.substring(0, WS_TLR_LENG);
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_STS != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_ASS_BR = BPCFTLRQ.INFO.TLR_BR;
        }
        if (BPCSMGRL.ASS_TYP == 'C') {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRCHNTB);
            BPRCHNTB.KEY.TYP = "CHNTB";
            if (BPCSMGRL.ASS_ID == null) BPCSMGRL.ASS_ID = "";
            JIBS_tmp_int = BPCSMGRL.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMGRL.ASS_ID += " ";
            BPRCHNTB.KEY.CD = BPCSMGRL.ASS_ID.substring(0, WS_CHNL_LENG);
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
        if (BPCSMGRL.FUNC == 'A') {
            IBS.init(SCCGWA, BPCPQBNK);
            BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            S000_CALL_BPZPQBNK();
            if (pgmRtn) return;
            if (BPCPQBNK.DATA_INFO.TLR_COND == '2') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_COND_SVR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRGRP);
        IBS.init(SCCGWA, BPCRGRP);
        BPRGRP.KEY.ROLE_CD = BPCSMGRL.ROLE_CD;
        BPCRGRP.INFO.FUNC = 'Q';
        S000_CALL_BPZRGRP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSMGRL.ROLE_CD);
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
        if (BPCSMGRL.ASS_TYP == 'T' 
            && BPCSMGRL.MOV_FLG == 'Y' 
            && BPRGRP.ATH_MOV_FLG != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRP_NOT_MOV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMGRL.EFF_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCSMGRL.EFF_DATE == 0) {
            BPCSMGRL.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if ((BPCSMGRL.FUNC == 'A' 
            || (BPCSMGRL.FUNC == 'U' 
            && BPCSMGRL.MODIFY_FUNC == 'A')) 
            && BPCSMGRL.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMGRL.EXP_DATE == 0) {
            BPCSMGRL.EXP_DATE = K_MAX_DATE;
        }
        if (BPCSMGRL.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMGRL.EXP_DATE < BPCSMGRL.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXP_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMGRL.FUNC == 'A' 
            || BPCSMGRL.MODIFY_FUNC == 'A' 
            || BPCSMGRL.MODIFY_FUNC == 'M') {
            if (BPCSMGRL.FUNC == 'A') {
                IBS.init(SCCGWA, BPRGRPL);
                BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
                BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
                BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
                BPCRGRPL.INFO.FUNC = 'Q';
                S000_CALL_BPZRGRPL();
                if (pgmRtn) return;
                if (BPCRGRPL.RETURN_INFO == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMGRL.ASS_TYP == 'T') {
                IBS.init(SCCGWA, BPCFOTRO);
                BPCFOTRO.ASSTYP = 'O';
                BPCFOTRO.ASS_ID = "" + WS_ASS_BR;
                JIBS_tmp_int = BPCFOTRO.ASS_ID.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTRO.ASS_ID = "0" + BPCFOTRO.ASS_ID;
                BPCFOTRO.ATH_TYP = BPCSMGRL.AUTH_TYP;
                BPCFOTRO.ROLE_CD = BPCSMGRL.ROLE_CD;
                S000_CALL_BPZFOTRO();
                if (pgmRtn) return;
                if (BPCFOTRO.PRIV_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_TLR_ORG_NO_PRIV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMGRL.ASS_TYP == 'O' 
                && WS_ASS_UP_BR != WS_ASS_BR) {
                IBS.init(SCCGWA, BPCFOTRO);
                BPCFOTRO.ASSTYP = 'O';
                BPCFOTRO.ASS_ID = "" + WS_ASS_UP_BR;
                JIBS_tmp_int = BPCFOTRO.ASS_ID.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTRO.ASS_ID = "0" + BPCFOTRO.ASS_ID;
                BPCFOTRO.ATH_TYP = BPCSMGRL.AUTH_TYP;
                BPCFOTRO.ROLE_CD = BPCSMGRL.ROLE_CD;
                S000_CALL_BPZFOTRO();
                if (pgmRtn) return;
                if (BPCFOTRO.PRIV_FLG != 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_UP_ORG_NO_PRIV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
        BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
        BPCRGRPL.INFO.FUNC = 'Q';
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "RECORD NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TBL_GRPL_FLAG = 'N';
        for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT 
            && WS_TBL_GRPL_FLAG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD);
            if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD.equalsIgnoreCase(BPCSMGRL.ROLE_CD)) {
                WS_TBL_GRPL_FLAG = 'Y';
            }
        }
        if (WS_TBL_GRPL_FLAG == 'N') {
            CEP.TRC(SCCGWA, "ROLE NO NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRGRPL.INFO.FUNC = 'A';
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSMGRL.ROLE_NUM);
        CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
        CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_TYP);
        CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_ID);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].ROLE_CD);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_FLG);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_TLR);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EXP_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_TM);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_TM);
        if (BPCRGRPL.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'R';
        BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
        BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TBL_GRPL_FLAG = 'N';
        for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT 
            && WS_TBL_GRPL_FLAG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD);
            if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD.equalsIgnoreCase(BPCSMGRL.ROLE_CD)) {
                WS_TBL_GRPL_FLAG = 'Y';
            }
        }
        if (BPCSMGRL.MODIFY_FUNC == 'M' 
            || BPCSMGRL.MODIFY_FUNC == 'D') {
            if (WS_TBL_GRPL_FLAG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMGRL.MODIFY_FUNC == 'M') {
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT != BPCSMGRL.EFF_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EFF_DATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS == '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TRANS_REC_CANNOT_MOD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMGRL.MODIFY_FUNC == 'D') {
                IBS.init(SCCGWA, BPCOGPL1);
                R010_TRANS_DATA_OUPUT();
                if (pgmRtn) return;
            }
        }
        if (BPCSMGRL.MODIFY_FUNC == 'A') {
            if (BPRGRPL.ROLE_CNT >= 120) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_MAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_TBL_GRPL_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCSMGRL.ROLE_NUM = (short) (BPRGRPL.ROLE_CNT + 1);
        }
        IBS.CLONE(SCCGWA, BPRGRPL, BPROGRPL);
        if (BPCSMGRL.MODIFY_FUNC == 'D') {
            B031_FWD_PROC();
            if (pgmRtn) return;
        } else {
            R000_TRANS_DATA();
            if (pgmRtn) return;
        }
        BPCRGRPL.INFO.FUNC = 'U';
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
    }
    public void B031_FWD_PROC() throws IOException,SQLException,Exception {
        BPRGRPL.ROLE_CNT += -1;
        for (WS_I = BPCSMGRL.ROLE_NUM; WS_I <= BPRGRPL.ROLE_CNT; WS_I += 1) {
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].ROLE_CD;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_FLG = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_FLG;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_STS;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_EFF_DT;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_EXP_DT;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_EFF_TM;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_EXP_TM;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].EFF_DT;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].EXP_DT;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_TLR = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I + 1-1].MOV_TLR;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
        }
        IBS.init(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1]);
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMGRL.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSMGRL.FUNC == '2') {
            B040_02_BRW_MAINTAIN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        if (BPCSMGRL.ASS_TYP == ' ') {
            BPRGRPL.KEY.ASS_TYP = ALL.charAt(0);
        } else {
            BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
        }
        if (BPCSMGRL.ASS_ID.trim().length() == 0) {
            BPRGRPL.KEY.ASS_ID = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        } else {
            BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
        }
        if (BPCSMGRL.AUTH_TYP == ' ') {
            BPRGRPL.KEY.ATH_TYP = ALL.charAt(0);
        } else {
            BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
        }
        BPCRGPLB.INFO.FUNC = '1';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
        BPCRGPLB.INFO.FUNC = 'N';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        B040_01_02_OUT_NORMAL();
        if (pgmRtn) return;
        while (BPCRGPLB.RETURN_INFO != 'N') {
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT 
                && SCCMPAG.FUNC != 'E'; WS_I += 1) {
                if (BPCSMGRL.ROLE_CD.trim().length() == 0 
                    || (BPCSMGRL.ROLE_CD.trim().length() > 0 
                    && BPCSMGRL.ROLE_CD.equalsIgnoreCase(BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD))) {
                    if (!BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD.equalsIgnoreCase(BPCOGPLL.TSQ_LIST.ROLE_CD)) {
                        IBS.init(SCCGWA, BPRGRP);
                        IBS.init(SCCGWA, BPCRGRP);
                        BPRGRP.KEY.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
                        BPCRGRP.INFO.FUNC = 'Q';
                        S000_CALL_BPZRGRP();
                        if (pgmRtn) return;
                    }
                    B040_01_03_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
            }
            BPCRGPLB.INFO.FUNC = 'N';
            S000_CALL_BPZRGPLB();
            if (pgmRtn) return;
        }
        BPCRGPLB.INFO.FUNC = 'E';
        S000_CALL_BPZRGPLB();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 143;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_NORMAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOGPLL);
        BPCOGPLL.NORMAL.FILLER = 0X03;
        BPCOGPLL.NORMAL.FLAG = BPCSMGRL.BRW_TYPE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOGPLL.NORMAL);
        SCCMPAG.DATA_LEN = 2;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOGPLL);
        BPCOGPLL.TSQ_LIST.REC_NO = WS_I;
        BPCOGPLL.TSQ_LIST.ASS_TYP = BPRGRPL.KEY.ASS_TYP;
        BPCOGPLL.TSQ_LIST.ASS_ID = BPRGRPL.KEY.ASS_ID;
        BPCOGPLL.TSQ_LIST.AUTH_TYP = BPRGRPL.KEY.ATH_TYP;
        BPCOGPLL.TSQ_LIST.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
        BPCOGPLL.TSQ_LIST.ROLE_CNMS = BPRGRP.ROLE_CNMS;
        CEP.TRC(SCCGWA, "GGGX");
        BPCOGPLL.TSQ_LIST.MOV_FLG = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_FLG;
        BPCOGPLL.TSQ_LIST.MOV_STS = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS;
        BPCOGPLL.TSQ_LIST.MOV_EFF_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT;
        BPCOGPLL.TSQ_LIST.MOV_EXP_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT;
        BPCOGPLL.TSQ_LIST.MOV_EFF_TIME = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM;
        BPCOGPLL.TSQ_LIST.MOV_EXP_TIME = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM;
        BPCOGPLL.TSQ_LIST.EFF_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT;
        BPCOGPLL.TSQ_LIST.EXP_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT;
        CEP.TRC(SCCGWA, "III");
        BPCOGPLL.TSQ_LIST.MOV_TLR = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_TLR;
        BPCOGPLL.TSQ_LIST.UPD_DATE = BPRGRPL.UPD_DT;
        BPCOGPLL.TSQ_LIST.UPD_TLR = BPRGRPL.UPD_TLR;
        CEP.TRC(SCCGWA, "JJJ");
        CEP.TRC(SCCGWA, "BEFOR-SCCMPAG");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOGPLL.TSQ_LIST);
        SCCMPAG.DATA_LEN = 141;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER-MPAG");
    }
    public void B040_02_BRW_MAINTAIN() throws IOException,SQLException,Exception {
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'R';
        BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
        BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "RECORD NOTFND");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCOGPL1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        BPCRGRPL.INFO.FUNC = 'D';
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GRPL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCSMGRL.AUTH_TYP == '0') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_O;
        }
        if (BPCSMGRL.AUTH_TYP == '1') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK_A;
        }
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSMGRL.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMGRL.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROGRPL;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRGRPL;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSMGRL.MODIFY_FUNC != 'D' 
            || BPCSMGRL.FUNC == 'D') {
            IBS.init(SCCGWA, BPCOGPL1);
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOGPL1.ROLE_NUM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSMGRL.FUNC == 'A' 
            || BPCSMGRL.FUNC == 'U' 
            || BPCSMGRL.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        if (BPCSMGRL.FUNC == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            if (BPCSMGRL.BRW_TYPE == '2') {
                SCCSUBS.TR_CODE = 4818;
            } else {
                if (BPCSMGRL.AUTH_TYP == '0') {
                    SCCSUBS.TR_CODE = 4842;
                }
                if (BPCSMGRL.AUTH_TYP == '1') {
                    SCCSUBS.TR_CODE = 4845;
                }
            }
            S000_CALL_SCZSUBS();
            if (pgmRtn) return;
        }
        SCCFMT.DATA_PTR = BPCOGPL1;
        SCCFMT.DATA_LEN = 109;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRGRPL.KEY.ASS_TYP = BPCSMGRL.ASS_TYP;
        BPRGRPL.KEY.ASS_ID = BPCSMGRL.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCSMGRL.AUTH_TYP;
        BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].ROLE_CD = BPCSMGRL.ROLE_CD;
        BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
        BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_FLG = BPCSMGRL.MOV_FLG;
        BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
        if (BPCSMGRL.FUNC == 'A' 
            || BPCSMGRL.MODIFY_FUNC == 'A') {
            if (BPCSMGRL.MOV_STS == ' ') {
                BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS = '0';
            } else {
                BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS = BPCSMGRL.MOV_STS;
                BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            }
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_DT = BPCSMGRL.MOV_EFF_DATE;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_DT = BPCSMGRL.MOV_EXP_DATE;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_TM = BPCSMGRL.MOV_EFF_TIME;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_TM = BPCSMGRL.MOV_EXP_TIME;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_TLR = BPCSMGRL.MOV_TLR;
            BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
            BPRGRPL.ROLE_CNT += 1;
        }
        BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT = BPCSMGRL.EFF_DATE;
        BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
        BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EXP_DT = BPCSMGRL.EXP_DATE;
        BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
        BPRGRPL.UPD_DT = BPCSMGRL.UPD_DATE;
        BPRGRPL.UPD_TLR = BPCSMGRL.UPD_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOGPL1.ASS_TYP = BPRGRPL.KEY.ASS_TYP;
        BPCOGPL1.ASS_ID = BPRGRPL.KEY.ASS_ID;
        BPCOGPL1.ROLE_NUM = BPCSMGRL.ROLE_NUM;
        BPCOGPL1.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].ROLE_CD;
        BPCOGPL1.MOV_FLG = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_FLG;
        BPCOGPL1.MOV_STS = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS;
        BPCOGPL1.MOV_EFF_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_DT;
        BPCOGPL1.MOV_EXP_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_DT;
        BPCOGPL1.MOV_EFF_TIME = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_TM;
        BPCOGPL1.MOV_EXP_TIME = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_TM;
        CEP.TRC(SCCGWA, "III");
        BPCOGPL1.EFF_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT;
        BPCOGPL1.EXP_DATE = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EXP_DT;
        CEP.TRC(SCCGWA, "III");
        BPCOGPL1.MOV_TLR = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_TLR;
        BPCOGPL1.UPD_DATE = BPRGRPL.UPD_DT;
        BPCOGPL1.UPD_TLR = BPRGRPL.UPD_TLR;
        CEP.TRC(SCCGWA, BPCSMGRL.ROLE_NUM);
        CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
        CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_TYP);
        CEP.TRC(SCCGWA, BPRGRPL.KEY.ASS_ID);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].ROLE_CD);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_FLG);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_STS);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_TLR);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EFF_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].EXP_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_DT);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EFF_TM);
        CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[BPCSMGRL.ROLE_NUM-1].MOV_EXP_TM);
    }
    public void B060_CHECK_FOR_DELETE() throws IOException,SQLException,Exception {
        if (BPCSMGRL.ASS_TYP == 'O') {
            IBS.init(SCCGWA, BPRTLT);
            IBS.init(SCCGWA, BPCRTLTB);
            BPCRTLTB.INFO.FUNC = 'B';
            if (BPCSMGRL.ASS_ID == null) BPCSMGRL.ASS_ID = "";
            JIBS_tmp_int = BPCSMGRL.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCSMGRL.ASS_ID += " ";
            if (BPCSMGRL.ASS_ID.substring(0, WS_BR_LENG).trim().length() == 0) BPRTLT.TLR_BR = 0;
            else BPRTLT.TLR_BR = Integer.parseInt(BPCSMGRL.ASS_ID.substring(0, WS_BR_LENG));
            S000_CALL_BPZRTLTB();
            if (pgmRtn) return;
            BPCRTLTB.INFO.FUNC = 'N';
            S000_CALL_BPZRTLTB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
            while (BPCRTLTB.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
                IBS.init(SCCGWA, BPCFOTRO);
                BPCFOTRO.ASSTYP = 'T';
                BPCFOTRO.ASS_ID = BPRTLT.KEY.TLR;
                BPCFOTRO.ATH_TYP = BPCSMGRL.AUTH_TYP;
                BPCFOTRO.ROLE_CD = BPCSMGRL.ROLE_CD;
                S000_CALL_BPZFOTRO();
                if (pgmRtn) return;
                if (BPCFOTRO.PRIV_FLG == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_TLR_HAS_ROLE;
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
    public void S000_CALL_BPZFOTRO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-OTS-ROL-CHK    ", BPCFOTRO);
        if (BPCFOTRO.RC.RC_CODE != 0) {
            if (BPCSMGRL.MODIFY_FUNC != 'D' 
                && BPCSMGRL.FUNC == 'D') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFOTRO.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTRO.RC);
                if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_GRP_NOTFND) 
                    && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_GRP_INV)) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFOTRO.RC);
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
    public void S000_CALL_BPZRGRP() throws IOException,SQLException,Exception {
        BPCRGRP.INFO.POINTER = BPRGRP;
        BPCRGRP.INFO.LEN = 292;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRP        ", BPCRGRP);
        if (BPCRGRP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGRP.RC);
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
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS   ", SCCSUBS);
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        CEP.TRC(SCCGWA, BPRGRPL.BLOB_ROLE_TXT.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
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
