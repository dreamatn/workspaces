package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4520 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP406";
    char K_TOP_LVL = '9';
    String K_PARM_ORGTP = "ORGTP";
    String CPN_S_MAINT_ORG = "BP-S-MAINT-ORG      ";
    String CPN_P_INQ_BNK = "BP-P-QUERY-BANK     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String K_HEAD_BR = "01";
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_LVL = ' ';
    BPOT4520_WS_ORGM_KEY WS_ORGM_KEY = new BPOT4520_WS_ORGM_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMORG BPCSMORG = new BPCSMORG();
    BPCSORGO BPCSORGO = new BPCSORGO();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    SCCGWA SCCGWA;
    BPB4520_AWA_4520 BPB4520_AWA_4520;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4520 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4520_AWA_4520>");
        BPB4520_AWA_4520 = (BPB4520_AWA_4520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.I_FUNC);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.PARM_TP);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BNK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EFF_DT);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EXP_DT);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ORG_DESC);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ORG_CDES);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.SUPR_BR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ATTR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.LVL);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TYP);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CHN_NM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CHN_ADDR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ENG_NM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ENG_ADDR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ABBR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.LINK_MAN);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.LINK_TEL);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.POST);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TLR_MAX);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ATH_MAX);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.FX_BUSI);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CNAP_NO);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ACCT_FLG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.OPN_DT);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.RUN_HDAY);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.RUN_MODE);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CALD_CD);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.OPN_TM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CLS_TM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.HOPN_TM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.HCLS_TM);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.FAX);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TELEX);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.PRO_FLG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.COST_FLG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.GL_MST);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.DEF_PTR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.PAY_PTR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CFM_PTR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.SWF_ID);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BIC_NO);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.IBAN_NO);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CI_LEN);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.COUN_CD);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.CITY_CD);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.UNSCH);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.OPN_CHK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.VOU_CHK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TAX_FLG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ERP_BRAN);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BRCH_BR);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.TRA_FLG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.LIC_NO);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B020_CREATE_ORG_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4520_AWA_4520.BNK.equalsIgnoreCase("0") 
            || BPB4520_AWA_4520.BNK.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.BR == 0 
            || BPB4520_AWA_4520.BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.EFF_DT >= BPB4520_AWA_4520.EXP_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB4520_AWA_4520.EFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4520_AWA_4520.BNK;
        S000_CALL_BPZPQBNK();
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.SUPR_BR != BPB4520_AWA_4520.BR) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPB4520_AWA_4520.BNK;
            BPCPQORG.BR = BPB4520_AWA_4520.SUPR_BR;
            CEP.TRC(SCCGWA, BPB4520_AWA_4520.SUPR_BR);
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB4520_AWA_4520.LVL > BPCPQORG.LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_LVL12;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB4520_AWA_4520.SUPR_BR != BPCPQORG.SUPR_BR) {
                CEP.TRC(SCCGWA, "AAAAA");
                WS_LVL = BPCPQORG.LVL;
                BPCPQORG.BNK = BPB4520_AWA_4520.BNK;
                BPCPQORG.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                    WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPCPQORG.LVL == WS_LVL) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LEVEL_RESTRICTED;
                    WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB4520_AWA_4520.LVL == '9') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_TOP_LVL;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, "BBBBB");
            if (BPB4520_AWA_4520.LVL != '9') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_TOP_LVL;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            BPCRORGM.INFO.FUNC = 'S';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (BPCRORGM.RETURN_INFO == 'F') {
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'N';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'F') {
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    if (WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPB4520_AWA_4520.BNK)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TOP_ORG_EXIST;
                        WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'E';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
        WS_ORGM_KEY.WS_ORGM_BR = BPB4520_AWA_4520.BR;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        BPCRORGM.INFO.FUNC = 'R';
        BPRPORGM.EFF_DT = BPB4520_AWA_4520.EFF_DT;
        CEP.TRC(SCCGWA, BPRPORGM.EFF_DT);
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (BPCRORGM.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.SUPR_BR == BPB4520_AWA_4520.BR) {
            if (BPB4520_AWA_4520.LVL != K_TOP_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_TOP_LVL;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            BPCRORGM.INFO.FUNC = 'S';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (BPCRORGM.RETURN_INFO == 'F') {
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'N';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'F') {
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    if (WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPB4520_AWA_4520.BNK)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TOP_ORG_EXIST;
                        WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'E';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPB4520_AWA_4520.CALD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB4520_AWA_4520.CALD_CD;
            WS_FLD_NO = BPB4520_AWA_4520.CALD_CD_NO;
            S000_CALL_BPZPCCAL();
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB4520_AWA_4520.BNK.equalsIgnoreCase("0") 
            || BPB4520_AWA_4520.BNK.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.BR == 0 
            || BPB4520_AWA_4520.BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.EFF_DT > BPB4520_AWA_4520.EXP_DT) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPB4520_AWA_4520.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_ACDATE);
        }
        if (BPB4520_AWA_4520.OPN_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_ACDATE);
        }
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4520_AWA_4520.BNK;
        S000_CALL_BPZPQBNK();
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.SUPR_BR != BPB4520_AWA_4520.BR) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPB4520_AWA_4520.BNK;
            BPCPQORG.BR = BPB4520_AWA_4520.SUPR_BR;
            CEP.TRC(SCCGWA, BPB4520_AWA_4520.SUPR_BR);
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB4520_AWA_4520.LVL > BPCPQORG.LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_LVL12;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB4520_AWA_4520.SUPR_BR != BPCPQORG.SUPR_BR) {
                CEP.TRC(SCCGWA, "AAAAA");
                WS_LVL = BPCPQORG.LVL;
                BPCPQORG.BNK = BPB4520_AWA_4520.BNK;
                BPCPQORG.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                S000_CALL_BPZPQORG();
                if (BPCPQORG.RC.RC_CODE != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                    WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            CEP.TRC(SCCGWA, "BBBBB");
            if (BPB4520_AWA_4520.LVL != '9') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_TOP_LVL;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            BPCRORGM.INFO.FUNC = 'S';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (BPCRORGM.RETURN_INFO == 'F') {
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'N';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'F') {
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    if (WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPB4520_AWA_4520.BNK)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TOP_ORG_EXIST;
                        WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'E';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
        WS_ORGM_KEY.WS_ORGM_BR = BPB4520_AWA_4520.BR;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        BPCRORGM.INFO.FUNC = 'R';
        BPRPORGM.EFF_DT = BPCSMORG.EFF_DT;
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (BPCRORGM.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.SUPR_BR == BPB4520_AWA_4520.BR) {
            if (BPB4520_AWA_4520.LVL != K_TOP_LVL) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_TOP_LVL;
                WS_FLD_NO = BPB4520_AWA_4520.LVL_NO;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            BPCRORGM.INFO.FUNC = 'S';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (BPCRORGM.RETURN_INFO == 'F') {
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'N';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'F') {
                    WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
                    if (WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPB4520_AWA_4520.BNK)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TOP_ORG_EXIST;
                        WS_FLD_NO = BPB4520_AWA_4520.SUPR_BR_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'E';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
                if (BPCRORGM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPB4520_AWA_4520.CALD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB4520_AWA_4520.CALD_CD;
            WS_FLD_NO = BPB4520_AWA_4520.CALD_CD_NO;
            S000_CALL_BPZPCCAL();
        }
        if (BPB4520_AWA_4520.TYP.equalsIgnoreCase(K_HEAD_BR)) {
            IBS.init(SCCGWA, BPRPORGM);
            IBS.init(SCCGWA, BPCRORGM);
            WS_ORGM_KEY.WS_ORGM_BR = 0;
            BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            WS_ORGM_KEY.WS_ORGM_BNK = BPB4520_AWA_4520.BNK;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
            BPCRORGM.INFO.FUNC = 'S';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            while (BPCRORGM.RETURN_INFO != 'N') {
                if (BPRPORGM.TYPE.equalsIgnoreCase(K_HEAD_BR)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ONLY_ONE_HEADBR;
                    S000_ERR_MSG_PROC();
                }
                BPCRORGM.RETURN_INFO = ' ';
                BPCRORGM.INFO.FUNC = 'N';
                BPCRORGM.INFO.POINTER = BPRPORGM;
                BPCRORGM.INFO.LEN = 1252;
                S000_CALL_BPZRORGM();
            }
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'E';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
        }
        if (!BPB4520_AWA_4520.TRA_TYP.equalsIgnoreCase("00") 
            && BPB4520_AWA_4520.INT_FLG <= SPACE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_INT_FLG;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_ORG_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORG);
        BPCSMORG.FUNC = 'A';
        BPCSMORG.OUTPUT_FMT = K_OUTPUT_FMT;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATA_PARAMETER_CN();
        }
        S000_CALL_BPZSMORG();
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        BPCSMORG.BNK = BPB4520_AWA_4520.BNK;
        BPCSMORG.BR = BPB4520_AWA_4520.BR;
        BPCSMORG.ORG_DESC = BPB4520_AWA_4520.ORG_DESC;
        BPCSMORG.ORG_CDESC = BPB4520_AWA_4520.ORG_CDES;
        BPCSMORG.SUPR_BR = BPB4520_AWA_4520.SUPR_BR;
        BPCSMORG.ATTR = BPB4520_AWA_4520.ATTR;
        BPCSMORG.LVL = BPB4520_AWA_4520.LVL;
        BPCSMORG.TYP = BPB4520_AWA_4520.TYP;
        BPCSMORG.EFF_DT = BPB4520_AWA_4520.EFF_DT;
        BPCSMORG.EXP_DT = BPB4520_AWA_4520.EXP_DT;
        BPCSMORG.CHN_NM = BPB4520_AWA_4520.CHN_NM;
        BPCSMORG.CHN_ADDR = BPB4520_AWA_4520.CHN_ADDR;
        BPCSMORG.ENG_NM = BPB4520_AWA_4520.ENG_NM;
        BPCSMORG.ENG_ADDR = BPB4520_AWA_4520.ENG_ADDR;
        BPCSMORG.ABBR = BPB4520_AWA_4520.ABBR;
        BPCSMORG.LINK_MAN = BPB4520_AWA_4520.LINK_MAN;
        BPCSMORG.LINK_TEL = BPB4520_AWA_4520.LINK_TEL;
        BPCSMORG.POST = BPB4520_AWA_4520.POST;
        BPCSMORG.TLR_MAX = BPB4520_AWA_4520.TLR_MAX;
        BPCSMORG.ATH_MAX = BPB4520_AWA_4520.ATH_MAX;
        BPCSMORG.FX_BUSI = BPB4520_AWA_4520.FX_BUSI;
        BPCSMORG.CNAP_NO = BPB4520_AWA_4520.CNAP_NO;
        BPCSMORG.ACCT_FLG = BPB4520_AWA_4520.ACCT_FLG;
        BPCSMORG.OPN_DT = BPB4520_AWA_4520.OPN_DT;
        BPCSMORG.RUN_HDAY = BPB4520_AWA_4520.RUN_HDAY;
        BPCSMORG.RUN_MODE = BPB4520_AWA_4520.RUN_MODE;
        BPCSMORG.CALD_CD = BPB4520_AWA_4520.CALD_CD;
        BPCSMORG.OPN_TM = BPB4520_AWA_4520.OPN_TM;
        BPCSMORG.CLS_TM = BPB4520_AWA_4520.CLS_TM;
        BPCSMORG.HOPN_TM = BPB4520_AWA_4520.HOPN_TM;
        BPCSMORG.HCLS_TM = BPB4520_AWA_4520.HCLS_TM;
        BPCSMORG.UPT_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPCSMORG.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSMORG.FAX = BPB4520_AWA_4520.FAX;
        BPCSMORG.TELEX = BPB4520_AWA_4520.TELEX;
        BPCSMORG.PRO_FLG = BPB4520_AWA_4520.PRO_FLG;
        BPCSMORG.COST_FLG = BPB4520_AWA_4520.COST_FLG;
        BPCSMORG.GL_MST = BPB4520_AWA_4520.GL_MST;
        BPCSMORG.DEF_PTR = BPB4520_AWA_4520.DEF_PTR;
        BPCSMORG.PAY_PTR = BPB4520_AWA_4520.PAY_PTR;
        BPCSMORG.CFM_PTR = BPB4520_AWA_4520.CFM_PTR;
        BPCSMORG.SWF_ID = BPB4520_AWA_4520.SWF_ID;
        BPCSMORG.BIC_NO = BPB4520_AWA_4520.BIC_NO;
        BPCSMORG.IBAN_NO = "" + BPB4520_AWA_4520.IBAN_NO;
        JIBS_tmp_int = BPCSMORG.IBAN_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMORG.IBAN_NO = "0" + BPCSMORG.IBAN_NO;
        BPCSMORG.CI_LEN = BPB4520_AWA_4520.CI_LEN;
        BPCSMORG.COUN_CD = BPB4520_AWA_4520.COUN_CD;
        BPCSMORG.CITY_CD = BPB4520_AWA_4520.CITY_CD;
        BPCSMORG.UNSCH_HOL = BPB4520_AWA_4520.UNSCH;
        BPCSMORG.UNSCH_HOL_TM = BPB4520_AWA_4520.UNSCHT;
        BPCSMORG.OPN_CHECK_FLG = BPB4520_AWA_4520.OPN_CHK;
        BPCSMORG.VOU_CHECK_FLG = BPB4520_AWA_4520.VOU_CHK;
        BPCSMORG.INT_TAX_FLG = BPB4520_AWA_4520.TAX_FLG;
        BPCSMORG.ERP_BRAN = BPB4520_AWA_4520.ERP_BRAN;
        BPCSMORG.BRANCH_BR = BPB4520_AWA_4520.BRCH_BR;
        BPCSMORG.TRA_TYP = BPB4520_AWA_4520.TRA_TYP;
        BPCSMORG.VIL_TYP = BPB4520_AWA_4520.VIL_TYP;
        BPCSMORG.AREA_CD = BPB4520_AWA_4520.AREA_CD;
        BPCSMORG.INT_BR_FLG = BPB4520_AWA_4520.INT_FLG;
        BPCSMORG.TRA_REG_FLG = BPB4520_AWA_4520.TRA_FLG;
        BPCSMORG.FIN_LIC_NO = BPB4520_AWA_4520.LIC_NO;
    }
    public void S000_CALL_BPZSMORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MAINT_ORG;
        SCCCALL.COMMAREA_PTR = BPCSMORG;
        SCCCALL.ERR_FLDNO = BPB4520_AWA_4520.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSMORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMORG.RC);
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BNK, BPCPQBNK);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_BANK_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = (short) BPB4520_AWA_4520.BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_CAL_CODE, BPCOCCAL);
        if (BPCOCCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCCAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
        if (BPCRORGM.RC.RC_CODE != 0 
            && BPCRORGM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB4520_AWA_4520.TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
