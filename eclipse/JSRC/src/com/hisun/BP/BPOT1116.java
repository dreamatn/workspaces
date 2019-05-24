package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1116 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP051";
    char K_RUN_MODE = 'D';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "BAS";
    String K_SEQ_TYPE = "SEQ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    double WS_TOT_PCNT = 0;
    double WS_PCNT1 = 0;
    double WS_PCNT2 = 0;
    double WS_PCNT3 = 0;
    short WS_MON = 0;
    int WS_DAY = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    SCCGWA SCCGWA;
    BPB1116_AWA_1116 BPB1116_AWA_1116;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1116 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1116_AWA_1116>");
        BPB1116_AWA_1116 = (BPB1116_AWA_1116) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCOFBAS);
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, BPCPQGLM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB1116_AWA_1116.FUNC;
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.FUNC);
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B010_CHECK_INPUT_CN();
            } else {
                B010_CHECK_INPUT();
            }
        }
        if (WS_FUNC_FLG == 'A') {
            B020_CREATE_BAS_INFO();
        } else if (WS_FUNC_FLG == 'U') {
            B020_MODIFY_BAS_INFO();
        } else if (WS_FUNC_FLG == 'D') {
            B020_DELETE_BAS_INFO();
        } else if (WS_FUNC_FLG == 'Q') {
            B020_QUERY_BAS_INFO();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.UPD_FLG);
        if (BPB1116_AWA_1116.UPD_FLG != '0' 
            && BPB1116_AWA_1116.UPD_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.UPD_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MSTNO1);
        if (BPB1116_AWA_1116.MSTNO1 != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB1116_AWA_1116.MSTNO1;
            S000_CALL_BPZPQGLM();
            CEP.TRC(SCCGWA, BPCPQGLM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB1116_AWA_1116.MSTNO1_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_GLMST_MUST_INPUT;
            WS_FLD_NO = BPB1116_AWA_1116.MSTNO1_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MSTNO2);
        if (BPB1116_AWA_1116.MSTNO2 != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB1116_AWA_1116.MSTNO2;
            S000_CALL_BPZPQGLM();
            CEP.TRC(SCCGWA, BPCPQGLM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB1116_AWA_1116.MSTNO2_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MSTNO3);
        if (BPB1116_AWA_1116.MSTNO3 != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB1116_AWA_1116.MSTNO3;
            S000_CALL_BPZPQGLM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB1116_AWA_1116.MSTNO3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MSTNO4);
        if (BPB1116_AWA_1116.MSTNO4 != 0) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = BPB1116_AWA_1116.MSTNO4;
            S000_CALL_BPZPQGLM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
                WS_FLD_NO = BPB1116_AWA_1116.MSTNO4_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CHG_TYPE);
        if (BPB1116_AWA_1116.CHG_TYPE != '0' 
            && BPB1116_AWA_1116.CHG_TYPE != '1') {
            WS_FLD_NO = BPB1116_AWA_1116.CHG_TYPE_NO;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.CHG_TYPE == '0') {
            CEP.TRC(SCCGWA, BPB1116_AWA_1116.CHG_MTH);
            if (BPB1116_AWA_1116.CHG_MTH != '0' 
                && BPB1116_AWA_1116.CHG_MTH != '1') {
                WS_FLD_NO = BPB1116_AWA_1116.CHG_MTH_NO;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if (BPB1116_AWA_1116.CHG_MTH == '1') {
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.CYC_MTH);
                    if (BPB1116_AWA_1116.CYC_MTH != '0' 
                        && BPB1116_AWA_1116.CYC_MTH != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.CYC_MTH_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        if (BPB1116_AWA_1116.CHG_TYPE == '1') {
            CEP.TRC(SCCGWA, BPB1116_AWA_1116.DIS_MTH);
            if (BPB1116_AWA_1116.DIS_MTH != '0' 
                && BPB1116_AWA_1116.DIS_MTH != '1' 
                && BPB1116_AWA_1116.DIS_MTH != '2') {
                WS_FLD_NO = BPB1116_AWA_1116.DIS_MTH_NO;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if (BPB1116_AWA_1116.DIS_MTH == '1' 
                    || BPB1116_AWA_1116.DIS_MTH == '2') {
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_FLG);
                    if (BPB1116_AWA_1116.AMOT_FLG != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.AMOT_FLG_NO;
                        S000_ERR_MSG_PROC();
                    }
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_CD);
                    if (BPB1116_AWA_1116.AMOT_CD.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.AMOT_CD_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_FLG);
        if (BPB1116_AWA_1116.AMOT_FLG != '0' 
            && BPB1116_AWA_1116.AMOT_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.AMOT_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_FLG);
        if (BPB1116_AWA_1116.CAL_FLG != '0' 
            && BPB1116_AWA_1116.CAL_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.CAL_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_CYC);
        if (BPB1116_AWA_1116.CAL_CYC != ' ') {
            if (BPB1116_AWA_1116.CAL_CYC != '0' 
                && BPB1116_AWA_1116.CAL_CYC != '1' 
                && BPB1116_AWA_1116.CAL_CYC != '2' 
                && BPB1116_AWA_1116.CAL_CYC != '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_CYC_INPUT_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_CYC_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.CAL_CYC == '1') {
            if (BPB1116_AWA_1116.CAL_DATE < 1 
                || BPB1116_AWA_1116.CAL_DATE > 31) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.CAL_CYC == '2') {
            if (BPB1116_AWA_1116.CAL_DATE < 1 
                || BPB1116_AWA_1116.CAL_DATE > 7) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_DATE);
        JIBS_tmp_str[0] = "" + BPB1116_AWA_1116.CAL_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_MON = 0;
        else WS_MON = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPB1116_AWA_1116.CAL_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DAY = 0;
        else WS_DAY = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        CEP.TRC(SCCGWA, WS_MON);
        CEP.TRC(SCCGWA, WS_DAY);
        if (BPB1116_AWA_1116.CAL_CYC == '0' 
            || BPB1116_AWA_1116.CAL_CYC == '3') {
            if (WS_MON < 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_MON > 12) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_DAY < 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_DAY > 31) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.DEBT_MTH);
        if (BPB1116_AWA_1116.DEBT_MTH != '0' 
            && BPB1116_AWA_1116.DEBT_MTH != '1' 
            && BPB1116_AWA_1116.DEBT_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEBT_MTH_ERROR;
            WS_FLD_NO = BPB1116_AWA_1116.DEBT_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.RECH_MTH);
        if (BPB1116_AWA_1116.RECH_MTH != '0' 
            && BPB1116_AWA_1116.RECH_MTH != '1' 
            && BPB1116_AWA_1116.RECH_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECH_MTH_ERROR;
            WS_FLD_NO = BPB1116_AWA_1116.RECH_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.STR_DATE);
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.END_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_DATE1 = BPB1116_AWA_1116.STR_DATE;
        WS_DATE2 = BPB1116_AWA_1116.END_DATE;
        if (BPB1116_AWA_1116.STR_DATE != 0 
            && WS_DATE1 < SCCGWA.COMM_AREA.AC_DATE 
            && WS_FUNC_FLG == 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1116_AWA_1116.STR_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.STR_DATE != 0 
            && BPB1116_AWA_1116.END_DATE != 0 
            && (WS_DATE2 < WS_DATE1 
            || WS_DATE2 == WS_DATE1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1116_AWA_1116.END_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.STR_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1116_AWA_1116.STR_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.STR_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1116_AWA_1116.END_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.END_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.ADV_FLG);
        if (BPB1116_AWA_1116.ADV_FLG != ' ') {
            if (BPB1116_AWA_1116.ADV_FLG != '0' 
                && BPB1116_AWA_1116.ADV_FLG != '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADV_FLG_INPUT_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.ADV_FLG_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MIN_DAMT);
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MAX_UAMT);
        if ((BPB1116_AWA_1116.MIN_DAMT != 0) 
            && (BPB1116_AWA_1116.MAX_UAMT != 0) 
            && (BPB1116_AWA_1116.MAX_UAMT < BPB1116_AWA_1116.MIN_DAMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.RAT_GRP);
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.UPD_FLG);
        if (BPB1116_AWA_1116.UPD_FLG != '0' 
            && BPB1116_AWA_1116.UPD_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.UPD_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CHG_TYPE);
        if (BPB1116_AWA_1116.CHG_TYPE != '0' 
            && BPB1116_AWA_1116.CHG_TYPE != '1') {
            WS_FLD_NO = BPB1116_AWA_1116.CHG_TYPE_NO;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.CHG_TYPE == '0') {
            CEP.TRC(SCCGWA, BPB1116_AWA_1116.CHG_MTH);
            if (BPB1116_AWA_1116.CHG_MTH != '0' 
                && BPB1116_AWA_1116.CHG_MTH != '1') {
                WS_FLD_NO = BPB1116_AWA_1116.CHG_MTH_NO;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if (BPB1116_AWA_1116.CHG_MTH == '1') {
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.CYC_MTH);
                    if (BPB1116_AWA_1116.CYC_MTH != '0' 
                        && BPB1116_AWA_1116.CYC_MTH != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.CYC_MTH_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        if (BPB1116_AWA_1116.CHG_TYPE == '1') {
            CEP.TRC(SCCGWA, BPB1116_AWA_1116.DIS_MTH);
            if (BPB1116_AWA_1116.DIS_MTH != '0' 
                && BPB1116_AWA_1116.DIS_MTH != '1' 
                && BPB1116_AWA_1116.DIS_MTH != '2') {
                WS_FLD_NO = BPB1116_AWA_1116.DIS_MTH_NO;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if (BPB1116_AWA_1116.DIS_MTH == '1' 
                    || BPB1116_AWA_1116.DIS_MTH == '2') {
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_FLG);
                    if (BPB1116_AWA_1116.AMOT_FLG != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.AMOT_FLG_NO;
                        S000_ERR_MSG_PROC();
                    }
                    CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_CD);
                    if (BPB1116_AWA_1116.AMOT_CD.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CYC_MTH_ERR;
                        WS_FLD_NO = BPB1116_AWA_1116.AMOT_CD_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.AMOT_FLG);
        if (BPB1116_AWA_1116.AMOT_FLG != '0' 
            && BPB1116_AWA_1116.AMOT_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.AMOT_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_FLG);
        if (BPB1116_AWA_1116.CAL_FLG != '0' 
            && BPB1116_AWA_1116.CAL_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1116_AWA_1116.CAL_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_CYC);
        if (BPB1116_AWA_1116.CAL_CYC != ' ') {
            if (BPB1116_AWA_1116.CAL_CYC != '0' 
                && BPB1116_AWA_1116.CAL_CYC != '1' 
                && BPB1116_AWA_1116.CAL_CYC != '2' 
                && BPB1116_AWA_1116.CAL_CYC != '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_CYC_INPUT_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_CYC_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.CAL_CYC == '1') {
            if (BPB1116_AWA_1116.CAL_DATE < 1 
                || BPB1116_AWA_1116.CAL_DATE > 31) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.CAL_CYC == '2') {
            if (BPB1116_AWA_1116.CAL_DATE < 1 
                || BPB1116_AWA_1116.CAL_DATE > 7) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.CAL_DATE);
        JIBS_tmp_str[0] = "" + BPB1116_AWA_1116.CAL_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_MON = 0;
        else WS_MON = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPB1116_AWA_1116.CAL_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DAY = 0;
        else WS_DAY = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        CEP.TRC(SCCGWA, WS_MON);
        CEP.TRC(SCCGWA, WS_DAY);
        if (BPB1116_AWA_1116.CAL_CYC == '0' 
            || BPB1116_AWA_1116.CAL_CYC == '3') {
            if (WS_MON < 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_MON > 12) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_DAY < 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_DAY > 31) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_DATE_ERROR;
                WS_FLD_NO = BPB1116_AWA_1116.CAL_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.DEBT_MTH);
        if (BPB1116_AWA_1116.DEBT_MTH != '0' 
            && BPB1116_AWA_1116.DEBT_MTH != '1' 
            && BPB1116_AWA_1116.DEBT_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEBT_MTH_ERROR;
            WS_FLD_NO = BPB1116_AWA_1116.DEBT_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.RECH_MTH);
        if (BPB1116_AWA_1116.RECH_MTH != '0' 
            && BPB1116_AWA_1116.RECH_MTH != '1' 
            && BPB1116_AWA_1116.RECH_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECH_MTH_ERROR;
            WS_FLD_NO = BPB1116_AWA_1116.RECH_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.STR_DATE);
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.END_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_DATE1 = BPB1116_AWA_1116.STR_DATE;
        WS_DATE2 = BPB1116_AWA_1116.END_DATE;
        if (BPB1116_AWA_1116.STR_DATE != 0 
            && WS_DATE1 < SCCGWA.COMM_AREA.AC_DATE 
            && WS_FUNC_FLG == 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1116_AWA_1116.STR_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.STR_DATE != 0 
            && BPB1116_AWA_1116.END_DATE != 0 
            && (WS_DATE2 < WS_DATE1 
            || WS_DATE2 == WS_DATE1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1116_AWA_1116.END_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1116_AWA_1116.STR_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1116_AWA_1116.STR_DATE;
            SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
            SCSSCKDT3.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.STR_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1116_AWA_1116.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1116_AWA_1116.END_DATE;
            SCSSCKDT SCSSCKDT4 = new SCSSCKDT();
            SCSSCKDT4.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.END_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.ADV_FLG);
        if (BPB1116_AWA_1116.ADV_FLG != ' ') {
            if (BPB1116_AWA_1116.ADV_FLG != '0' 
                && BPB1116_AWA_1116.ADV_FLG != '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADV_FLG_INPUT_ERR;
                WS_FLD_NO = BPB1116_AWA_1116.ADV_FLG_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MIN_DAMT);
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.MAX_UAMT);
        if ((BPB1116_AWA_1116.MIN_DAMT != 0) 
            && (BPB1116_AWA_1116.MAX_UAMT != 0) 
            && (BPB1116_AWA_1116.MAX_UAMT < BPB1116_AWA_1116.MIN_DAMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B020 START..");
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_BPFBAS_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = K_NUM;
        S000_CALL_BPZSGSEQ();
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'A';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOFBAS.VAL.FEE_NO = (short) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_NO);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_MODIFY_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'U';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_DELETE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'D';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_QUERY_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'I';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFBAS.KEY.FEE_CODE = BPB1116_AWA_1116.FEE_CD;
        if (BPB1116_AWA_1116.FEE_NO != 0) {
            BPCOFBAS.VAL.FEE_NO = BPB1116_AWA_1116.FEE_NO;
            CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_NO);
        }
        BPCOFBAS.VAL.FEE_DESC = BPB1116_AWA_1116.DESC;
        BPCOFBAS.VAL.FEE_TYPE = BPB1116_AWA_1116.FEE_TYP;
        BPCOFBAS.VAL.UP_AMT = BPB1116_AWA_1116.MAX_UAMT;
        BPCOFBAS.VAL.DWN_AMT = BPB1116_AWA_1116.MIN_DAMT;
        BPCOFBAS.VAL.UP_PCT = BPB1116_AWA_1116.UP_PCT;
        BPCOFBAS.VAL.DWN_PCT = BPB1116_AWA_1116.DWN_PCT;
        BPCOFBAS.VAL.AREA_TYPE = BPB1116_AWA_1116.AREA_TYP;
        BPCOFBAS.VAL.FEE_UPD_FLG = BPB1116_AWA_1116.UPD_FLG;
        BPCOFBAS.VAL.GL_MASTERNO1 = BPB1116_AWA_1116.MSTNO1;
        BPCOFBAS.VAL.GL_MASTERNO2 = BPB1116_AWA_1116.MSTNO2;
        BPCOFBAS.VAL.GL_MASTERNO3 = BPB1116_AWA_1116.MSTNO3;
        BPCOFBAS.VAL.GL_MASTERNO4 = BPB1116_AWA_1116.MSTNO4;
        if (BPB1116_AWA_1116.RAT_GRP.trim().length() == 0) {
            BPCOFBAS.VAL.RATE_GROUP = BPCPQBNK.DATA_INFO.EX_RA;
        } else {
            BPCOFBAS.VAL.RATE_GROUP = BPB1116_AWA_1116.RAT_GRP;
        }
        BPCOFBAS.VAL.FEE_EXG_TYP = BPB1116_AWA_1116.EXG_TYP;
        BPCOFBAS.VAL.FEE_OWN_FLG = BPB1116_AWA_1116.OWN_FLG;
        BPCOFBAS.VAL.TRN_BR_PCT = BPB1116_AWA_1116.TX_PCT;
        BPCOFBAS.VAL.AC_BR1_PCT = BPB1116_AWA_1116.AC1_PCT;
        BPCOFBAS.VAL.AC_BR2_PCT = BPB1116_AWA_1116.AC2_PCT;
        BPCOFBAS.VAL.FEE_TX_MMO = BPB1116_AWA_1116.TX_MMO;
        BPCOFBAS.VAL.FEE_CHG_TYPE = BPB1116_AWA_1116.CHG_TYPE;
        BPCOFBAS.VAL.FEE_GTH_METH = BPB1116_AWA_1116.CHG_MTH;
        BPCOFBAS.VAL.FEE_CYC_METH = BPB1116_AWA_1116.CYC_MTH;
        BPCOFBAS.VAL.FEE_DIS_METH = BPB1116_AWA_1116.DIS_MTH;
        BPCOFBAS.VAL.FEE_AMOT_FLG = BPB1116_AWA_1116.AMOT_FLG;
        BPCOFBAS.VAL.AMOT_CODE = BPB1116_AWA_1116.AMOT_CD;
        BPCOFBAS.VAL.FEE_REB_FLG = BPB1116_AWA_1116.REB_FLG;
        BPCOFBAS.VAL.REB_CODE = BPB1116_AWA_1116.REB_CD;
        BPCOFBAS.VAL.ACCRUAL_FLG = BPB1116_AWA_1116.CAL_FLG;
        BPCOFBAS.VAL.ACCRUAL_CYC = BPB1116_AWA_1116.CAL_CYC;
        BPCOFBAS.VAL.ACCRUAL_TIMES = BPB1116_AWA_1116.PERD_CNT;
        BPCOFBAS.VAL.ACCRUAL_DAY = BPB1116_AWA_1116.CAL_DATE;
        BPCOFBAS.VAL.DEBT_METH = BPB1116_AWA_1116.DEBT_MTH;
        BPCOFBAS.VAL.RECH_METH = BPB1116_AWA_1116.RECH_MTH;
        BPCOFBAS.VAL.MAX_RECH_CNT = BPB1116_AWA_1116.MRCH_CNT;
        BPCOFBAS.VAL.ADV_FLG = BPB1116_AWA_1116.ADV_FLG;
        if (BPB1116_AWA_1116.STR_DATE == 0) {
            BPCOFBAS.VAL.FEE_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCOFBAS.VAL.FEE_EFF_DATE = BPB1116_AWA_1116.STR_DATE;
        }
        if (BPB1116_AWA_1116.END_DATE == 0) {
            BPCOFBAS.VAL.FEE_EXP_DATE = 99991231;
        } else {
            BPCOFBAS.VAL.FEE_EXP_DATE = BPB1116_AWA_1116.END_DATE;
        }
        BPCOFBAS.LAST_TIME = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFBAS.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-S-MAINTAIN-FBAS";
        SCCCALL.COMMAREA_PTR = BPCOFBAS;
        SCCCALL.ERR_FLDNO = BPB1116_AWA_1116.FEE_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-S-GET-SEQ";
        SCCCALL.COMMAREA_PTR = BPCSGSEQ;
        SCCCALL.ERR_FLDNO = BPB1116_AWA_1116.FEE_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_FLD_NO = BPB1116_AWA_1116.RAT_GRP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
            WS_FLD_NO = BPB1116_AWA_1116.RAT_GRP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
