package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2202 {
    int JIBS_tmp_int;
    brParm BPTCMOV_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTCLIB_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP115";
    char K_STSW_FLG_Y = '1';
    int K_MAX_PAR_CNT = 30;
    char K_DRAWING_FLG = 'D';
    char K_DEPOSIT_FLG = 'C';
    char K_DR_FLG = 'D';
    char K_CR_FLG = 'C';
    String CPN_P_INQ_BOX = "BP-P-Q-CBOX         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_CCY = "BP-INQUIRE-CCY      ";
    String CPN_BP_S_CLOSE_BALANCE = "BP-S-CLOSE-BALANCE  ";
    String CPN_R_STARTBR_APPD = "BP-R-STARTBR-APPD";
    String CPN_P_CAL_WORK_DAY = "BP-P-CAL-WORK-DAY";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String CPN_R_ADW_BRIS = "BP-R-ADW-BRIS";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS       ";
    String CPN_R_BRW_CHIS = "BP-R-BRW-CHIS       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2202_WS_ERR_INFO WS_ERR_INFO = new BPOT2202_WS_ERR_INFO();
    double WS_TMP_AMT = 0;
    double WS_HBD_AMT = 0;
    double WS_APPD_AMT = 0;
    double WS_LIMIT_AMT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CNT = 0;
    BPOT2202_WS_C0235 WS_C0235 = new BPOT2202_WS_C0235();
    char WS_RETURN_INFO_1 = ' ';
    char WS_RETURN_INFO_2 = ' ';
    char WS_RETURN_INFO_3 = ' ';
    char WS_RETURN_INFO_4 = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    char WS_STS = ' ';
    char WS_MID_END_STS = ' ';
    char WS_INVNTY_ATTR = ' ';
    char WS_HANDLER_ATTR = ' ';
    int WS_HANDLER_BR = 0;
    int WS_INVNTY_BR = 0;
    int WS_UP_BR = 0;
    char WS_FND_PAR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSCLBL BPCSCLBL = new BPCSCLBL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPCRAPPD BPCRAPPD = new BPCRAPPD();
    BPRAPPD BPRAPPD = new BPRAPPD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPCTBRIS BPCTBRIS = new BPCTBRIS();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCTTHIB BPCTTHIB = new BPCTTHIB();
    BPCTCHIB BPCTCHIB = new BPCTCHIB();
    BPCSINVT BPCSINVT = new BPCSINVT();
    BPCTINVT BPCTINVT = new BPCTINVT();
    BPRINVT BPRINVT = new BPRINVT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2202_AWA_2202 BPB2202_AWA_2202;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2202 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2202_AWA_2202>");
        BPB2202_AWA_2202 = (BPB2202_AWA_2202) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.TLR);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.CCY);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.PLBOX_NO);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.VB_FLG);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.CHK_FLG);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.TX_CODE);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.AMT);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.BOXP_TYP);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.INVNTY);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.HANDLER);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.INTY_NM);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.HLD_NM);
        if (BPB2202_AWA_2202.BOXP_TYP == '1' 
            || BPB2202_AWA_2202.BOXP_TYP == '2' 
            || BPB2202_AWA_2202.BOXP_TYP == '5') {
            if (BPB2202_AWA_2202.VB_FLG != '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR93);
            }
        } else {
            if (BPB2202_AWA_2202.BOXP_TYP == '3' 
                || BPB2202_AWA_2202.BOXP_TYP == '4' 
                || BPB2202_AWA_2202.BOXP_TYP == '6') {
                if (BPB2202_AWA_2202.VB_FLG != '0') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR94);
                }
            }
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPB2202_AWA_2202.CHK_FLG == '0') {
                B010_CHECK_INPUT_BR();
                if (pgmRtn) return;
            }
            if (BPB2202_AWA_2202.CHK_FLG == '0' 
                || BPB2202_AWA_2202.CHK_FLG == '1') {
                WS_MID_END_STS = '1';
            } else {
                WS_MID_END_STS = '2';
            }
            if (WS_MID_END_STS == '1') {
                B010_CHECK_INPUT_FOR_CN();
                if (pgmRtn) return;
                B020_ALTERNATE_LIB_FOR_CN();
                if (pgmRtn) return;
            }
            if (WS_MID_END_STS == '2') {
                B110_BROWSE_PROCESS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CNT);
                WS_CNT = WS_CNT - 1;
                if (WS_CNT < 1) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MID_DAY_LT_TWICE, BPRINVT.CCY);
                }
                B060_CHECK_ONWAY_FOR_CN();
                if (pgmRtn) return;
                if (BPB2202_AWA_2202.BOXP_TYP == '1' 
                    || BPB2202_AWA_2202.BOXP_TYP == '2' 
                    || BPB2202_AWA_2202.BOXP_TYP == '5') {
                    B080_GET_REC_PAY_AMT_CLIB_CN();
                    if (pgmRtn) return;
                }
                if (BPB2202_AWA_2202.BOXP_TYP == '4') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR147);
                }
                if (BPB2202_AWA_2202.BOXP_TYP == '3' 
                    || BPB2202_AWA_2202.BOXP_TYP == '4') {
                    B090_GET_REC_PAY_AMT_CBOX_CN();
                    if (pgmRtn) return;
                }
                B010_CHECK_INPUT_FOR_CN();
                if (pgmRtn) return;
                B060_CHECK_BOX_LIMIT_FOR_CN();
                if (pgmRtn) return;
                B070_CHECK_BR_LIMIT_FOR_CN();
                if (pgmRtn) return;
                B030_GET_BAL_FOR_CN();
                if (pgmRtn) return;
                B020_ALTERNATE_LIB_FOR_CN();
                if (pgmRtn) return;
            }
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            if (BPB2202_AWA_2202.BOXP_TYP == '1' 
                || BPB2202_AWA_2202.BOXP_TYP == '2') {
                B010_GET_BAL();
                if (pgmRtn) return;
            }
            if (BPB2202_AWA_2202.BOXP_TYP == '3' 
                || BPB2202_AWA_2202.BOXP_TYP == '4') {
                B010_02_CHECK_BOX_TOTAL_AMT();
                if (pgmRtn) return;
                B010_GET_BAL_1();
                if (pgmRtn) return;
            }
            B020_ALTERNATE_LIB();
            if (pgmRtn) return;
        }
    }
    public void B060_CHECK_BOX_LIMIT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPRCLIB.KEY.CCY = BPB2202_AWA_2202.CCY;
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
        CEP.TRC(SCCGWA, BPRCLIB.LMT_L);
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
        if (BPRCLIB.BAL < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR119);
        }
        if (BPRCLIB.BAL < BPRCLIB.LMT_L) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR120);
        }
        if (BPRCLIB.BAL > BPRCLIB.LMT_U) {
            if (BPB2202_AWA_2202.BOXP_TYP == '1' 
                || BPB2202_AWA_2202.BOXP_TYP == '2' 
                || BPB2202_AWA_2202.BOXP_TYP == '5') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BAL_MORE_THAN_LMT);
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR121);
            }
        }
    }
    public void B110_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        WS_CNT = 0;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.TLR);
        BPRINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.TLR_D = BPB2202_AWA_2202.TLR;
        BPRINVT.CB_TYP = '0';
        BPRINVT.INVNTYP = '1';
        BPRINVT.CCY = BPB2202_AWA_2202.CCY;
        BPCTINVT.INFO.FUNC = '7';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        BPCTINVT.INFO.FUNC = '2';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTINVT.RETURN_INFO != 'N'; WS_CNT += 1) {
            BPCTINVT.INFO.FUNC = '2';
            BPCTINVT.POINTER = BPRINVT;
            BPCTINVT.LEN = 409;
            S000_CALL_BPZRINVT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        BPCTINVT.INFO.FUNC = '3';
        BPCTINVT.LEN = 409;
        BPCTINVT.POINTER = BPRINVT;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        if (BPCTINVT.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void S000_CALL_BPZRINVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-PROC-INVT", BPCTINVT);
    }
    public void B010_CHECK_INPUT_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2202_AWA_2202.HANDLER;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        WS_HANDLER_BR = 0;
        WS_HANDLER_BR = BPCFTLRQ.INFO.NEW_BR;
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2202_AWA_2202.INVNTY;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        WS_INVNTY_BR = 0;
        WS_INVNTY_BR = BPCFTLRQ.INFO.NEW_BR;
        CEP.TRC(SCCGWA, WS_HANDLER_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (WS_HANDLER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR102);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_HANDLER_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_HANDLER_ATTR = ' ';
        WS_UP_BR = 0;
        WS_HANDLER_ATTR = BPCPQORG.ATTR;
        WS_UP_BR = BPCPQORG.SUPR_BR;
        CEP.TRC(SCCGWA, WS_HANDLER_ATTR);
        CEP.TRC(SCCGWA, WS_UP_BR);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_INVNTY_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_INVNTY_ATTR = ' ';
        WS_INVNTY_ATTR = BPCPQORG.ATTR;
        CEP.TRC(SCCGWA, WS_INVNTY_ATTR);
        IBS.init(SCCGWA, BPCFTLCM);
        BPCFTLCM.TLR = BPB2202_AWA_2202.INVNTY;
        BPCFTLCM.BR = WS_HANDLER_BR;
        S000_CALL_BPZFTLCM();
        if (pgmRtn) return;
        if (BPCFTLCM.AUTH_FLG != 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR103);
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2202_AWA_2202.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN);
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCFTLRQ.INFO.NEW_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR156);
        }
        if (BPB2202_AWA_2202.CHK_FLG != '0') {
            if (!BPB2202_AWA_2202.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                CEP.TRC(SCCGWA, BPB2202_AWA_2202.BOXP_TYP);
                if (BPB2202_AWA_2202.BOXP_TYP != '4') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_EQ_A);
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_EQ_A1);
                }
            }
        } else {
            if (!BPB2202_AWA_2202.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                IBS.init(SCCGWA, BPCFTLRQ);
                BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
                S000_CALL_BPZFTLRQ();
                if (pgmRtn) return;
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || BPCFTLRQ.INFO.TX_LVL == '0') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
                }
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2202_AWA_2202.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPB2202_AWA_2202.BOXP_TYP == '3' 
            || BPB2202_AWA_2202.BOXP_TYP == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB2202_AWA_2202.BOXP_TYP == '1' 
            || BPB2202_AWA_2202.BOXP_TYP == '2' 
            || BPB2202_AWA_2202.BOXP_TYP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB2202_AWA_2202.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2202_AWA_2202.CCY;
        BPCPQBOX.DATA_INFO.OPP_TLR = BPB2202_AWA_2202.TLR;
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.OPP_TLR);
        S000_CALL_BPZPQBOX();
        if (pgmRtn) return;
        if (BPCPQBOX.DATA_INFO.STS == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(BPB2202_AWA_2202.TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLIB_MGR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BAL);
        if (BPB2202_AWA_2202.AMT != BPCPQBOX.DATA_INFO.BAL) {
            if (BPB2202_AWA_2202.CHK_FLG == '0' 
                || BPB2202_AWA_2202.CHK_FLG == '1') {
                WS_STS = '1';
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_EQ_TOT_AMT_W);
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_EQ_TOT_AMT);
            }
        } else {
            WS_STS = '0';
        }
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.GD_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.GD_AMT);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.BD_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BD_AMT);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2202_AWA_2202.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPB2202_AWA_2202.BOXP_TYP == '3' 
            || BPB2202_AWA_2202.BOXP_TYP == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB2202_AWA_2202.BOXP_TYP == '1' 
            || BPB2202_AWA_2202.BOXP_TYP == '2') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB2202_AWA_2202.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.GD_AMT);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.BD_AMT);
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.HBD_AMT);
        WS_TMP_AMT = BPB2202_AWA_2202.GD_AMT + BPB2202_AWA_2202.BD_AMT + BPB2202_AWA_2202.HBD_AMT;
        if (BPB2202_AWA_2202.BOXP_TYP == '1' 
            || BPB2202_AWA_2202.BOXP_TYP == '2') {
            IBS.init(SCCGWA, BPCPQBOX);
            BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPQBOX.DATA_INFO.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
            BPCPQBOX.DATA_INFO.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
            BPCPQBOX.DATA_INFO.CCY = BPB2202_AWA_2202.CCY;
            S000_CALL_BPZPQBOX();
            if (pgmRtn) return;
            if (BPCPQBOX.DATA_INFO.STS == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_STS_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(BPB2202_AWA_2202.TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CLIB_MGR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_TMP_AMT);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BAL);
            if (WS_TMP_AMT != BPCPQBOX.DATA_INFO.BAL) {
                CEP.TRC(SCCGWA, "M-BP-MUST-EQ-TOT-AMT");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_TOT_AMT;
                WS_FLD_NO = BPB2202_AWA_2202.GD_AMT_NO;
                WS_ERR_INFO.WS_ERR_CCY = BPB2202_AWA_2202.CCY;
                S000_ERR_MSG_PROC_INFO();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB2202_AWA_2202.GD_AMT);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.GD_AMT);
            if (BPB2202_AWA_2202.GD_AMT != BPCPQBOX.DATA_INFO.GD_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_SYS_AMT;
                WS_FLD_NO = BPB2202_AWA_2202.GD_AMT_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB2202_AWA_2202.BD_AMT);
            CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BD_AMT);
            if (BPB2202_AWA_2202.BD_AMT != BPCPQBOX.DATA_INFO.BD_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_SYS_AMT;
                WS_FLD_NO = BPB2202_AWA_2202.BD_AMT_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_02_CHECK_BOX_TOTAL_AMT() throws IOException,SQLException,Exception {
        WS_TMP_AMT = BPB2202_AWA_2202.GD_AMT + BPB2202_AWA_2202.BD_AMT + BPB2202_AWA_2202.HBD_AMT;
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2202_AWA_2202.CCY;
        S000_CALL_BPZPQBOX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.CCY);
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BAL);
        if (WS_TMP_AMT != BPCPQBOX.DATA_INFO.BAL) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_TOT_AMT;
            WS_FLD_NO = BPB2202_AWA_2202.GD_AMT_NO;
            WS_ERR_INFO.WS_ERR_CCY = BPB2202_AWA_2202.CCY;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.GD_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.GD_AMT);
        if (BPB2202_AWA_2202.GD_AMT != BPCPQBOX.DATA_INFO.GD_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_SYS_AMT;
            WS_FLD_NO = BPB2202_AWA_2202.GD_AMT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.BD_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.BD_AMT);
        if (BPB2202_AWA_2202.BD_AMT != BPCPQBOX.DATA_INFO.BD_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_SYS_AMT;
            WS_FLD_NO = BPB2202_AWA_2202.BD_AMT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB2202_AWA_2202.HBD_AMT);
        CEP.TRC(SCCGWA, BPCPQBOX.DATA_INFO.HBD_AMT);
        if (BPB2202_AWA_2202.HBD_AMT != BPCPQBOX.DATA_INFO.HBD_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_EQ_SYS_AMT;
            WS_FLD_NO = BPB2202_AWA_2202.HBD_AMT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_ALTERNATE_LIB_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCLBL);
        BPCSCLBL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSCLBL.TLR = BPB2202_AWA_2202.TLR;
        BPCSCLBL.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPCSCLBL.BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSCLBL.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPCSCLBL.CCY = BPB2202_AWA_2202.CCY;
        BPCSCLBL.VB_FLG = BPB2202_AWA_2202.VB_FLG;
        BPCSCLBL.PLBOX_TP = BPB2202_AWA_2202.BOXP_TYP;
        BPCSCLBL.INVNTYP = BPB2202_AWA_2202.CHK_FLG;
        BPCSCLBL.ACTU_AMT = BPB2202_AWA_2202.AMT;
        BPCSCLBL.AMT = BPCPQBOX.DATA_INFO.BAL;
        BPCSCLBL.TX_CODE = BPB2202_AWA_2202.TX_CODE;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            CEP.TRC(SCCGWA, WS_PAR_CNT);
            CEP.TRC(SCCGWA, BPB2202_AWA_2202.PAR_INFO[WS_PAR_CNT-1].PAR_VAL);
            CEP.TRC(SCCGWA, BPB2202_AWA_2202.PAR_INFO[WS_PAR_CNT-1].PAR_NUM);
            BPCSCLBL.PAR_INFO1[WS_PAR_CNT-1].PAR_VAL1 = BPB2202_AWA_2202.PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
            BPCSCLBL.PAR_INFO1[WS_PAR_CNT-1].PAR_NUM1 = BPB2202_AWA_2202.PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
        }
        if (WS_STS == '0') {
            BPCSCLBL.STS = '0';
        } else {
            BPCSCLBL.STS = '1';
        }
        if (BPCSCLBL.INVNTYP == '0') {
            BPCSCLBL.INVNTY = BPB2202_AWA_2202.INVNTY;
            BPCSCLBL.HANDLER = BPB2202_AWA_2202.HANDLER;
            BPCSCLBL.INTY_NM = BPB2202_AWA_2202.INTY_NM;
            BPCSCLBL.HLD_NM = BPB2202_AWA_2202.HLD_NM;
        }
        if (BPRCLIB.NEW_DT == SCCGWA.COMM_AREA.AC_DATE) {
            BPCSCLBL.L_AMT = BPRCLIB.L_TLT_AMT;
        } else {
            if (BPRCLIB.NEW_DT < SCCGWA.COMM_AREA.AC_DATE) {
                BPCSCLBL.L_AMT = BPRCLIB.BAL;
            }
        }
        BPCSCLBL.BAL = BPCPQBOX.DATA_INFO.BAL;
        BPCSCLBL.AMT_C = WS_C0235.WS_AMT_C;
        BPCSCLBL.AMT_D = WS_C0235.WS_AMT_D;
        BPCSCLBL.TX_CNT = WS_C0235.WS_TX_CNT;
        CEP.TRC(SCCGWA, BPCSCLBL.L_AMT);
        CEP.TRC(SCCGWA, BPCSCLBL.AMT_C);
        CEP.TRC(SCCGWA, BPCSCLBL.AMT_D);
        CEP.TRC(SCCGWA, BPCSCLBL.BAL);
        CEP.TRC(SCCGWA, BPCSCLBL.TX_CNT);
        BPCSCLBL.FUN = '1';
        S000_CALL_BPZSCLBL();
        if (pgmRtn) return;
    }
    public void B020_ALTERNATE_LIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCLBL);
        BPCSCLBL.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSCLBL.TLR = BPB2202_AWA_2202.TLR;
        BPCSCLBL.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPCSCLBL.BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSCLBL.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPCSCLBL.CCY = BPB2202_AWA_2202.CCY;
        BPCSCLBL.VB_FLG = BPB2202_AWA_2202.VB_FLG;
        if (BPRCLIB.NEW_DT == SCCGWA.COMM_AREA.AC_DATE) {
            BPCSCLBL.L_AMT = BPRCLIB.L_TLT_AMT;
        } else {
            if (BPRCLIB.NEW_DT < SCCGWA.COMM_AREA.AC_DATE) {
                BPCSCLBL.L_AMT = BPRCLIB.BAL;
            }
        }
        BPCSCLBL.AMT_C = WS_C0235.WS_AMT_C;
        BPCSCLBL.AMT_D = WS_C0235.WS_AMT_D;
        BPCSCLBL.BAL = BPRCLIB.BAL;
        BPCSCLBL.TX_CNT = WS_C0235.WS_TX_CNT;
        CEP.TRC(SCCGWA, BPCSCLBL.L_AMT);
        CEP.TRC(SCCGWA, BPCSCLBL.AMT_C);
        CEP.TRC(SCCGWA, BPCSCLBL.AMT_D);
        CEP.TRC(SCCGWA, BPCSCLBL.BAL);
        CEP.TRC(SCCGWA, BPCSCLBL.TX_CNT);
        CEP.TRC(SCCGWA, BPCSCLBL);
        BPCSCLBL.FUN = '1';
        S000_CALL_BPZSCLBL();
        if (pgmRtn) return;
    }
    public void B030_GET_BAL_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPRCLIB.KEY.CCY = BPB2202_AWA_2202.CCY;
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
    }
    public void B040_GET_CASH_APPD_FOR_CN() throws IOException,SQLException,Exception {
        B050_GET_LAST_AC_DT_FOR_CN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRAPPD);
        IBS.init(SCCGWA, BPCRAPPD);
        BPRAPPD.APT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRAPPD.APT_WDDT = BPCOCLWD.DATE2;
        BPRAPPD.KEY.APT_CCY = BPB2202_AWA_2202.CCY;
        BPRAPPD.STS = '0';
        BPCRAPPD.INFO.FUNC = 'S';
        BPCRAPPD.INFO.LEN = 449;
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
        BPCRAPPD.INFO.FUNC = 'N';
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        BPCRAPPD.INFO.LEN = 449;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
        while (BPCRAPPD.RETURN_INFO != 'N') {
            WS_APPD_AMT = WS_APPD_AMT + BPRAPPD.APT_AMT;
            BPCRAPPD.INFO.FUNC = 'N';
            BPCRAPPD.INFO.POINTER = BPRAPPD;
            BPCRAPPD.INFO.LEN = 449;
            S000_CALL_BPZRAPPD();
            if (pgmRtn) return;
        }
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_APPD_AMT);
        BPCRAPPD.INFO.FUNC = 'E';
        BPCRAPPD.INFO.LEN = 449;
        BPCRAPPD.INFO.POINTER = BPRAPPD;
        S000_CALL_BPZRAPPD();
        if (pgmRtn) return;
        if (BPCRAPPD.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_GET_LAST_AC_DT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.DAYS = 2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void B060_CHECK_ONWAY_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRCMOV.ONWAY_DT = 0;
        BPRCMOV.MOV_TYP = '3';
        BPRCMOV.MOV_STS = '1';
        T000_STARTBR_BPTCMOV();
        if (pgmRtn) return;
        T000_READNEXT_BPTCMOV();
        if (pgmRtn) return;
        WS_CNT = 0;
        CEP.TRC(SCCGWA, WS_CNT);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_CNT <= 1) {
            WS_CNT = 2;
            T000_READNEXT_BPTCMOV();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        T000_ENDBR_BPTCMOV();
        if (pgmRtn) return;
        if (WS_CNT > 1) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR98);
        }
    }
    public void T000_STARTBR_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "IN_TLR = :BPRCMOV.IN_TLR "
            + "AND ONWAY_DT = :BPRCMOV.ONWAY_DT "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_READNEXT_BPTCMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTCMOV  ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void B070_CHECK_BR_LIMIT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPCTBRIS);
        BPRBRIS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBRIS.KEY.LMT_CCY = BPB2202_AWA_2202.CCY;
        BPCTBRIS.INFO.FUNC = 'Q';
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        S000_CALL_BPZTBRIS();
        if (pgmRtn) return;
        if (BPCTBRIS.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPRCLIB);
            IBS.init(SCCGWA, BPCTLIBB);
            BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCLIB.KEY.CCY = BPB2202_AWA_2202.CCY;
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
            BPCTLIBB.INFO.FUNC = '5';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            WS_LIMIT_AMT = 0;
            WS_LIMIT_AMT = WS_LIMIT_AMT + BPRCLIB.BAL;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
                BPCTLIBB.INFO.FUNC = 'N';
                BPCTLIBB.POINTER = BPRCLIB;
                BPCTLIBB.LEN = 352;
                S000_CALL_BPZTLIBB();
                if (pgmRtn) return;
                WS_LIMIT_AMT = WS_LIMIT_AMT + BPRCLIB.BAL;
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_LIMIT_AMT = WS_LIMIT_AMT - WS_APPD_AMT;
            if (WS_LIMIT_AMT > BPRBRIS.LMT_U) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_BRMAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_LIMIT_AMT < BPRBRIS.LMT_L) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNDER_BRMIN_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B080_GET_REC_PAY_AMT_CLIB_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCHIS);
        IBS.init(SCCGWA, BPCTCHIB);
        BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCHIS.KEY.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPRCHIS.KEY.CCY = BPB2202_AWA_2202.CCY;
        BPRCHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCHIS.STS = '0';
        BPCTCHIB.INFO.FUNC = 'C';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        BPCTCHIB.INFO.FUNC = 'N';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCHIS.AMT);
        while (BPCTCHIB.RETURN_INFO != 'N') {
            if (BPRCHIS.IN_OUT == K_CR_FLG) {
                WS_C0235.WS_AMT_D = WS_C0235.WS_AMT_D + BPRCHIS.AMT;
            }
            if (BPRCHIS.IN_OUT == K_DR_FLG) {
                WS_C0235.WS_AMT_C = WS_C0235.WS_AMT_C + BPRCHIS.AMT;
            }
            CEP.TRC(SCCGWA, "BPTCHIS");
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_D);
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_C);
            BPCTCHIB.INFO.FUNC = 'N';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
        }
        BPCTCHIB.INFO.FUNC = 'E';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTTHIB);
        BPCTTHIB.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.CCY = BPB2202_AWA_2202.CCY;
        BPCTTHIB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCTTHIB.RCE_PBNO = BPB2202_AWA_2202.PLBOX_NO;
        BPCTTHIB.PAY_PBNO = BPB2202_AWA_2202.PLBOX_NO;
        BPCTTHIB.STS = '0';
        BPCTTHIB.FUNC = 'C';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        BPCTTHIB.FUNC = 'R';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        while (BPCTTHIB.RETURN_INFO != 'N') {
            if (BPCTTHIB.DC_FLG == K_DRAWING_FLG) {
                WS_C0235.WS_AMT_D = WS_C0235.WS_AMT_D + BPCTTHIB.AMT;
            }
            if (BPCTTHIB.DC_FLG == K_DEPOSIT_FLG) {
                WS_C0235.WS_AMT_C = WS_C0235.WS_AMT_C + BPCTTHIB.AMT;
            }
            CEP.TRC(SCCGWA, "BPTTHIS");
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_D);
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_C);
            BPCTTHIB.FUNC = 'R';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
        }
        BPCTTHIB.FUNC = 'E';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_GET_REC_PAY_AMT_CBOX_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTTHIB);
        BPCTTHIB.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.CCY = BPB2202_AWA_2202.CCY;
        BPCTTHIB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCTTHIB.RCE_PBNO = BPB2202_AWA_2202.PLBOX_NO;
        BPCTTHIB.PAY_PBNO = BPB2202_AWA_2202.PLBOX_NO;
        BPCTTHIB.STS = '0';
        BPCTTHIB.FUNC = 'C';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        BPCTTHIB.FUNC = 'R';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        while (BPCTTHIB.RETURN_INFO != 'N') {
            if (BPCTTHIB.DC_FLG == K_DRAWING_FLG) {
                WS_C0235.WS_AMT_D = WS_C0235.WS_AMT_D + BPCTTHIB.AMT;
            }
            if (BPCTTHIB.DC_FLG == K_DEPOSIT_FLG) {
                WS_C0235.WS_AMT_C = WS_C0235.WS_AMT_C + BPCTTHIB.AMT;
            }
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_D);
            CEP.TRC(SCCGWA, WS_C0235.WS_AMT_C);
            BPCTTHIB.FUNC = 'R';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
        }
        BPCTTHIB.FUNC = 'E';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPRCLIB.KEY.CCY = BPB2202_AWA_2202.CCY;
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLIB.L_TLT_AMT);
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
    }
    public void B010_GET_BAL_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPB2202_AWA_2202.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPB2202_AWA_2202.CASH_TYP;
        BPRCLIB.KEY.CCY = BPB2202_AWA_2202.CCY;
        T000_READ_BPTCLIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLIB.L_TLT_AMT);
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
    }
    public void T000_READ_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.READ(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void S000_CALL_BPZRAPPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_APPD, BPCRAPPD);
        if (BPCRAPPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRAPPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CAL_WORK_DAY, BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
        if (BPCTMOVB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTMOVB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTBRIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BRIS, BPCTBRIS);
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (BPCTLIBB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTTHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_THIS, BPCTTHIB);
        if (BPCTTHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTTHIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CHIS, BPCTCHIB);
        if (BPCTCHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCHIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            WS_FLD_NO = BPB2202_AWA_2202.CCY_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSCLBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_S_CLOSE_BALANCE, BPCSCLBL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
