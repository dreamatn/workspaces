package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT1138 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    double K_MAX_AMT = 999999999999999.99;
    short K_MAX_CPNT = 9999;
    double K_MAX_PER = 100.00;
    String K_OUTPUT_FMT = "BP059";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_F_S_MAIN_FREB = "BP-F-S-MAINTAIN-FREB";
    String CPN_FECR_MAINTAIN = "BP-F-S-MAINTAIN-FECR";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    short WS_CNT1 = 0;
    short WS_MON = 0;
    int WS_DAY = 0;
    BPOT1138_WS_AMT WS_AMT = new BPOT1138_WS_AMT();
    BPOT1138_WS_PER WS_PER = new BPOT1138_WS_PER();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFECR BPCOFECR = new BPCOFECR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1138_AWA_1138 BPB1138_AWA_1138;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1138 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1138_AWA_1138>");
        BPB1138_AWA_1138 = (BPB1138_AWA_1138) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MODIFY_REB_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.EFF_DATE);
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_DATE1 = BPB1138_AWA_1138.EFF_DATE;
        WS_DATE2 = BPB1138_AWA_1138.EXP_DATE;
        if (BPB1138_AWA_1138.EFF_DATE != 0 
            && BPB1138_AWA_1138.EXP_DATE != 0 
            && (WS_DATE2 < WS_DATE1 
            || WS_DATE2 == WS_DATE1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1138_AWA_1138.EXP_DATE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1138_AWA_1138.EFF_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1138_AWA_1138.EFF_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1138_AWA_1138.EFF_DATE_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB1138_AWA_1138.EXP_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1138_AWA_1138.EXP_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1138_AWA_1138.EXP_DATE_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.R_CYCLE);
        if (BPB1138_AWA_1138.R_CYCLE != '1' 
            && BPB1138_AWA_1138.R_CYCLE != '2' 
            && BPB1138_AWA_1138.R_CYCLE != '3' 
            && BPB1138_AWA_1138.R_CYCLE != '4') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_CLFY_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.R_CYCLE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_TYPE);
        if (BPB1138_AWA_1138.REB_TYPE != '1' 
            && BPB1138_AWA_1138.REB_TYPE != '2' 
            && BPB1138_AWA_1138.REB_TYPE != '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_MTH_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.REB_TYPE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.AGG_TYPE);
        if (BPB1138_AWA_1138.AGG_TYPE != '1' 
            && BPB1138_AWA_1138.AGG_TYPE != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AGGR_TYP_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.AGG_TYPE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_DATE);
        JIBS_tmp_str[0] = "" + BPB1138_AWA_1138.REB_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_MON = 0;
        else WS_MON = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPB1138_AWA_1138.REB_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DAY = 0;
        else WS_DAY = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        if (WS_MON < 1 
            || WS_MON > 12) {
            CEP.TRC(SCCGWA, "WS-MON ERROR      ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_DATE_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.REB_DATE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_DAY < 1 
            || WS_DAY > 31) {
            CEP.TRC(SCCGWA, "WS-DAY ERROR      ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_DATE_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.REB_DATE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_STDT);
        JIBS_tmp_str[0] = "" + BPB1138_AWA_1138.REB_STDT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_MON = 0;
        else WS_MON = Short.parseShort(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPB1138_AWA_1138.REB_STDT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_DAY = 0;
        else WS_DAY = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        if (WS_MON < 1 
            || WS_MON > 12) {
            CEP.TRC(SCCGWA, "WS-MON ERROR      ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_DATE_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.REB_STDT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_DAY < 1 
            || WS_DAY > 31) {
            CEP.TRC(SCCGWA, "WS-DAY ERROR      ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REB_DATE_INPT_ERR;
            WS_FLD_NO = BPB1138_AWA_1138.REB_STDT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1138_AWA_1138.REB_TYPE == '1') {
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT);
            WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT;
            WS_AMT.WS_AMT2 = BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT;
            CEP.TRC(SCCGWA, WS_AMT.WS_AMT1);
            CEP.TRC(SCCGWA, WS_AMT.WS_AMT2);
            if (WS_AMT.WS_AMT1 != 0 
                && WS_AMT.WS_AMT2 == 0) {
                WS_CNT1 = 1;
            }
            if (WS_AMT.WS_AMT1 != 0 
                && WS_AMT.WS_AMT2 != 0) {
                if (WS_AMT.WS_AMT1 > WS_AMT.WS_AMT2) {
                    CEP.TRC(SCCGWA, "1");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 = 2;
                    CEP.TRC(SCCGWA, "AAA");
                    CEP.TRC(SCCGWA, WS_CNT1);
                }
            }
            IBS.init(SCCGWA, WS_AMT);
            WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT;
            WS_AMT.WS_AMT2 = BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT;
            if (WS_AMT.WS_AMT1 != 0 
                && WS_AMT.WS_AMT2 != 0) {
                if (WS_AMT.WS_AMT1 > WS_AMT.WS_AMT2) {
                    CEP.TRC(SCCGWA, "2");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                    CEP.TRC(SCCGWA, "AAB");
                    CEP.TRC(SCCGWA, WS_CNT1);
                }
            }
            IBS.init(SCCGWA, WS_AMT);
            WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT;
            WS_AMT.WS_AMT2 = BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT;
            if (WS_AMT.WS_AMT1 != 0 
                && WS_AMT.WS_AMT2 != 0) {
                if (WS_AMT.WS_AMT1 > WS_AMT.WS_AMT2) {
                    CEP.TRC(SCCGWA, "3");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                    CEP.TRC(SCCGWA, "AAC");
                    CEP.TRC(SCCGWA, WS_CNT1);
                }
            }
            IBS.init(SCCGWA, WS_AMT);
            WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT;
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT);
            WS_AMT.WS_AMT2 = BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT;
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT);
            if (WS_AMT.WS_AMT1 != 0 
                && WS_AMT.WS_AMT2 != 0) {
                CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT);
                CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT);
                CEP.TRC(SCCGWA, WS_AMT.WS_AMT1);
                CEP.TRC(SCCGWA, WS_AMT.WS_AMT2);
                if (WS_AMT.WS_AMT1 > WS_AMT.WS_AMT2) {
                    CEP.TRC(SCCGWA, "4");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                    CEP.TRC(SCCGWA, "AAD");
                    CEP.TRC(SCCGWA, WS_CNT1);
                }
            }
            CEP.TRC(SCCGWA, WS_CNT1);
            IBS.init(SCCGWA, WS_AMT);
            if (BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT != 0) {
                if (WS_CNT1 == 0) {
                    CEP.TRC(SCCGWA, "0");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT;
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_CNT1 == 1) {
                    CEP.TRC(SCCGWA, "1");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT;
                    CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT);
                    CEP.TRC(SCCGWA, WS_AMT.WS_AMT1);
                    CEP.TRC(SCCGWA, K_MAX_AMT);
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[1-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_CNT1 == 2) {
                    CEP.TRC(SCCGWA, "2");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT;
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[2-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_CNT1 == 3) {
                    CEP.TRC(SCCGWA, "3");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT;
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[3-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_CNT1 == 4) {
                    CEP.TRC(SCCGWA, "4");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT;
                    CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT);
                    CEP.TRC(SCCGWA, WS_AMT.WS_AMT1);
                    CEP.TRC(SCCGWA, K_MAX_AMT);
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        CEP.TRC(SCCGWA, "ZHENGJIE");
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[4-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_CNT1 == 5) {
                    CEP.TRC(SCCGWA, "5");
                    WS_AMT.WS_AMT1 = BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT;
                    if (WS_AMT.WS_AMT1 != K_MAX_AMT) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                        WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[5-1].UP_AMT_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    IBS.init(SCCGWA, SCCEXCP);
                    SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_CNT1 + ")";
                    CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
                }
            }
        }
        if (BPB1138_AWA_1138.REB_TYPE == '2') {
            WS_CNT1 = 0;
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[1-1].UP_CNT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT);
            CEP.TRC(SCCGWA, BPB1138_AWA_1138.REB_INFO[5-1].UP_CNT);
            if (BPB1138_AWA_1138.REB_INFO[1-1].UP_CNT != 0 
                && BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT != 0) {
                if (BPB1138_AWA_1138.REB_INFO[1-1].UP_CNT > BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[1-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 = 2;
                }
            }
            if (BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT != 0 
                && BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT != 0) {
                if (BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT > BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[2-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                }
            }
            if (BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT != 0 
                && BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT != 0) {
                if (BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT > BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[3-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                }
            }
            if (BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT != 0 
                && BPB1138_AWA_1138.REB_INFO[5-1].UP_CNT != 0) {
                if (BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT > BPB1138_AWA_1138.REB_INFO[5-1].UP_CNT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR;
                    WS_FLD_NO = BPB1138_AWA_1138.REB_INFO[4-1].UP_CNT_NO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_CNT1 += 1;
                }
            }
            CEP.TRC(SCCGWA, WS_CNT1);
            if (BPB1138_AWA_1138.REB_INFO[1-1].UP_CNT != 0) {
                if (WS_CNT1 == 0) {
                    CEP.TRC(SCCGWA, "1");
