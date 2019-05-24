package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.GD.*;
import com.hisun.CI.*;
import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZDDALN {
    int JIBS_tmp_int;
    DBParm LNTRCVD_RD;
    DecimalFormat df;
    String JIBS_tmp_str[] = new String[10];
    brParm LNTRCVD_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_FMT_ID = "LN400";
    LNZDDALN_WS_ERR_MSG WS_ERR_MSG = new LNZDDALN_WS_ERR_MSG();
    LNZDDALN_WS_LAST_DT WS_LAST_DT = new LNZDDALN_WS_LAST_DT();
    LNZDDALN_WS_END_DT WS_END_DT = new LNZDDALN_WS_END_DT();
    LNZDDALN_WS_PERD_UNIT WS_PERD_UNIT = new LNZDDALN_WS_PERD_UNIT();
    short WS_P_PERD = 0;
    char WS_P_UNIT = ' ';
    short WS_I_PERD = 0;
    char WS_I_UNIT = ' ';
    short WS_CAL_PERD = 0;
    short WS_BEI_SHU = 0;
    short WS_YU_SHU = 0;
    int WS_P_CAL_DUE_DT = 0;
    short WS_IC_CAL_TERM = 0;
    short WS_ALRDY_CALD_TERM = 0;
    short WS_I_ALD_CAL_TERM = 0;
    char WS_REPY_TYP = ' ';
    short WS_INPUT_TERM = 0;
    short WS_P_CMP_TERM = 0;
    int WS_P_CMP_DUE_DT = 0;
    short WS_IC_CMP_TERM = 0;
    int WS_IC_CMP_VAL_DT = 0;
    int WS_IC_CMP_DUE_DT = 0;
    LNZDDALN_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZDDALN_WS_LOAN_CONT_AREA();
    int WS_VAL_DT = 0;
    short WS_PHS_NO = 0;
    double WS_N_RATE = 0;
    double WS_PHS_INST_AMT = 0;
    int WS_DATE = 0;
    int WS_DATE1 = 0;
    int WS_DAYS = 0;
    int WS_DATE2 = 0;
    int WS_MAT_DT = 0;
    int WS_GRC_DUE_DATE = 0;
    short WS_TOT_TERM = 0;
    char WS_OLD_APRD_PAY_MTH = ' ';
    char WS_OLD_APRD_BPAY_MTH = ' ';
    char WS_A_B_RP_M = ' ';
    short WS_I = 0;
    String WS_CTL_STSW = " ";
    String WS_MSG_INFO = "                                                                      ";
    String WS_MSG_INFO_CHAR = " ";
    String WS_MSG_9 = " ";
    String WS_MSG_INFO_CHAR1 = " ";
    String WS_MSG_91 = " ";
    short WS_IC_CUR_TERM = 0;
    int WS_G = 0;
    double WS_PAY_AMT = 0;
    double WS_KOU_KUAN_AMT = 0;
    double WS_KOU_GDA_AMT = 0;
    double WS_KOU_GDA_TOT_AMT = 0;
    double WS_KOU_SETL_AMT = 0;
    double WS_KOU_TOT_AMT = 0;
    short WS_SEQ1 = 0;
    short WS_SEQ2 = 0;
    int WS_IC_CAL_DUE_DT = 0;
    String WS_AC_TYP = " ";
    String WS_AC = " ";
    String WS_GDA_AC = " ";
    String WS_AC_CCY = " ";
    double WS_AC_AMT = 0;
    LNZDDALN_WS_GDA_ACAMT[] WS_GDA_ACAMT = new LNZDDALN_WS_GDA_ACAMT[10];
    double WS_EX_TOT_AMT = 0;
    LNZDDALN_WS_TRF_NARRATIVE WS_TRF_NARRATIVE = new LNZDDALN_WS_TRF_NARRATIVE();
    double WS_PQMIB_CBAL = 0;
    double WS_TOT_P_UDAMT = 0;
    char WS_RCVD_FLG = ' ';
    char WS_PPRP_FLG = ' ';
    char WS_EXTN_FLG = ' ';
    char WS_TYP_FLG = ' ';
    char WS_PLPI_END_FLG = ' ';
    char WS_PAIP_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCILCM LNCILCM = new LNCILCM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCURPN LNCURPN = new LNCURPN();
    LNCOD10 LNCOD10 = new LNCOD10();
    LNCICRCM LNCICRCM = new LNCICRCM();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNCSRPC LNCSRPC = new LNCSRPC();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRICTL LNRICTL = new LNRICTL();
    LNCICAL LNCICAL = new LNCICAL();
    LNRCTPY LNRCTPY = new LNRCTPY();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    BPCEX BPCEX = new BPCEX();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    CICQACRI CICQACRI = new CICQACRI();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    SCCTPCL SCCTPCL = new SCCTPCL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRPLDT LNRPLDT = new LNRPLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    LNCCNEX LNCCNEX = new LNCCNEX();
    AICPQIA AICPQIA = new AICPQIA();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    AICPQITM AICPQITM = new AICPQITM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCSQBAL DDCSQBAL = new DDCSQBAL();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNRRELI LNRRELI = new LNRRELI();
    LNCSSUNM LNCSSUNM = new LNCSSUNM();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRPACK LNRPACK = new LNRPACK();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCSCLR LNCSCLR = new LNCSCLR();
    IBCQINF IBCQINF = new IBCQINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCSCLRR LNCSCLRR = new LNCSCLRR();
    CICQACAC CICQACAC = new CICQACAC();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    LNCDDALN LNCDDALN;
    public LNZDDALN() {
        for (int i=0;i<10;i++) WS_GDA_ACAMT[i] = new LNZDDALN_WS_GDA_ACAMT();
    }
    public void MP(SCCGWA SCCGWA, LNCDDALN LNCDDALN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCDDALN = LNCDDALN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZDDALN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        WS_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B201_UPDATE_LNTRCVD();
        if (pgmRtn) return;
        B051_AMT_DEBIT_PROC();
        if (pgmRtn) return;
        B070_CUST_REPAY_PROC();
        if (pgmRtn) return;
        B340_UPDATE_LNTPAIP();
        if (pgmRtn) return;
        B110_BP_HISTORY_GEN();
        if (pgmRtn) return;
        B120_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B201_UPDATE_LNTRCVD() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        if (LNCCLNQ.DATA.VAL_DT == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CUR_TRM_TRDT_ERR1, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCDDALN.COMM_DATA.TR_VAL_DTE != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CUR_TRM_TRDT_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNRRCVD.DUE_DT = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS = :LNRRCVD.REPY_STS "
            + "AND DUE_DT > :LNRRCVD.DUE_DT";
        LNTRCVD_RD.fst = true;
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPET_CUR_REPAY, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNRRCVD.DUE_DT = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS "
            + "AND DUE_DT <= :LNRRCVD.DUE_DT";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        B320_GET_DANG_QI_AMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_IC_CAL_DUE_DT);
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNCDDALN.COMM_DATA.CTA_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = WS_IC_CAL_DUE_DT;
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
        if (LNCDDALN.COMM_DATA.TOT_P_AMT != 0 
            || LNCDDALN.COMM_DATA.TOT_I_AMT != 0) {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = 0;
            LNRRCVD.DUE_DT = WS_IC_CAL_DUE_DT;
            T000_STARTBR_RCVD_PROC();
            if (pgmRtn) return;
            T000_READNEXT_RCVD_PROC();
            if (pgmRtn) return;
            while (LNCRRCVD.RETURN_INFO != 'E') {
                CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                CEP.TRC(SCCGWA, LNCDDALN.COMM_DATA.TOT_P_AMT);
                CEP.TRC(SCCGWA, LNCDDALN.COMM_DATA.TOT_I_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                if (LNRRCVD.KEY.AMT_TYP == 'C') {
                    if ((LNCDDALN.COMM_DATA.TOT_P_AMT != LNRRCVD.P_REC_AMT 
                        || LNCDDALN.COMM_DATA.TOT_I_AMT != LNRRCVD.I_REC_AMT)) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CUR_TRM_REPY_AMT_ERR, WS_ERR_MSG);
                        df = new DecimalFormat("################V00");
                        WS_MSG_9 = df.format(LNRRCVD.P_REC_AMT);
                        WS_MSG_INFO_CHAR = WS_MSG_9;
                        df = new DecimalFormat("################V00");
                        WS_MSG_91 = df.format(LNRRCVD.I_REC_AMT);
                        WS_MSG_INFO_CHAR1 = WS_MSG_91;
                        WS_MSG_INFO = "P SHOULD BE " + WS_MSG_INFO_CHAR + " I SHOULD BE " + WS_MSG_INFO_CHAR1;
                    } else {
                        T000_RCVD_UPDATE_PROC();
                        if (pgmRtn) return;
                        LNRRCVD.P_REC_AMT = LNRRCVD.P_REC_AMT + LNRRCVD.I_REC_AMT - 2000;
                        LNRRCVD.I_REC_AMT = 200;
                        T000_RCVD_REWRITE_PROC();
                        if (pgmRtn) return;
                        LNCDDALN.COMM_DATA.TOT_P_AMT = LNRRCVD.P_REC_AMT;
                        LNCDDALN.COMM_DATA.TOT_I_AMT = LNRRCVD.I_REC_AMT;
                        LNCDDALN.COMM_DATA.TOT_AMT = LNCDDALN.COMM_DATA.TOT_P_AMT + LNCDDALN.COMM_DATA.TOT_I_AMT;
                    }
                } else {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_TYPE, WS_ERR_MSG);
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                if (JIBS_tmp_str[0].trim().length() > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                    CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_MSG_INFO);
                }
                T000_READNEXT_RCVD_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_RCVD_PROC();
            if (pgmRtn) return;
        }
    }
    public void B320_GET_DANG_QI_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSCHE);
        LNCSSCHE.DATA_AREA.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNCSSCHE.CUR_FLG = 'Y';
        S000_CALL_LNZSSCHE();
        if (pgmRtn) return;
        if (LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE != 0 
            && LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE < LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE) {
            WS_IC_CAL_DUE_DT = LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE;
        } else {
            WS_IC_CAL_DUE_DT = LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE;
        }
    }
    public void B051_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 5 
            && (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() != 0 
            || LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() != 0 
            || WS_I <= 1); WS_I += 1) {
            CEP.TRC(SCCGWA, "111S");
            if (WS_PAY_AMT != 0 
                || WS_EX_TOT_AMT != 0) {
                WS_KOU_KUAN_AMT = WS_PAY_AMT;
                if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 != 0 
                    && LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 < WS_KOU_KUAN_AMT) {
                    WS_KOU_KUAN_AMT = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
                }
                CEP.TRC(SCCGWA, LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2);
                if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("01) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("07) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("05) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("06) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_DD_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("02) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_GLSUS_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("03) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_NOSCD_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("04) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_GM_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("08) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_CASH_DEBIT_PROC();
                    if (pgmRtn) return;
                } else {
                }
                if (WS_KOU_KUAN_AMT <= WS_PAY_AMT) {
                    WS_PAY_AMT -= WS_KOU_KUAN_AMT;
                }
            } else {
                WS_KOU_KUAN_AMT = 0;
            }
            LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 = WS_KOU_KUAN_AMT;
            WS_KOU_SETL_AMT += WS_KOU_KUAN_AMT;
        }
    }
    public void B052_DD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.BV_TYP = '3';
        if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("05) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("06) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CARD_NO = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        } else {
            DDCUDRAC.AC = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        }
        if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2.trim().length() > 0 
            && LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("07) LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CHQ_NO = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2;
            DDCUDRAC.CHQ_TYPE = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].CHQ_TYPE2;
            DDCUDRAC.CHQ_ISS_DATE = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].ISS_DATE2;
            DDCUDRAC.PAY_PSWD = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_PSWD;
        }
        DDCUDRAC.CCY = LNCDDALN.COMM_DATA.CCY;
        DDCUDRAC.TX_AMT = WS_KOU_KUAN_AMT;
        DDCUDRAC.VAL_DATE = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCDDALN.COMM_DATA.MMO;
        DDCUDRAC.RLT_AC = LNCDDALN.COMM_DATA.CTA_NO;
        DDCUDRAC.OTHER_AMT = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
        DDCUDRAC.NARRATIVE = IBS.CLS2CPY(SCCGWA, WS_TRF_NARRATIVE);
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = DDCUDRAC.TX_AMT;
    }
    public void B052_GLSUS_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_AC = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        WS_AC_CCY = LNCDDALN.COMM_DATA.CCY;
        B042_GET_SUSP_AC_AVABAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCDDALN.COMM_DATA.CCY;
        if (AICPQMIB.OUTPUT_DATA.BAL_RFLG == 'N' 
            && WS_PQMIB_CBAL < WS_KOU_KUAN_AMT) {
            AICUUPIA.DATA.AMT = WS_PQMIB_CBAL;
        } else {
            AICUUPIA.DATA.AMT = WS_KOU_KUAN_AMT;
        }
        AICUUPIA.DATA.VALUE_DATE = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.PAY_MAN = LNCCLNQ.DATA.CI_CNAME;
        AICUUPIA.DATA.RVS_NO = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].RVS_NO2;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = AICUUPIA.DATA.AMT;
    }
    public void B052_NOSCD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        IBCPOSTA.CCY = LNCDDALN.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_KOU_KUAN_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCDDALN.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCDDALN.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCDDALN.COMM_DATA.CTA_NO;
        IBCPOSTA.NARR = IBS.CLS2CPY(SCCGWA, WS_TRF_NARRATIVE);
        IBCPOSTA.TX_MMO = LNCDDALN.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = IBCPOSTA.AMT;
    }
    public void B052_GM_DEBIT_PROC() throws IOException,SQLException,Exception {
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0) {
            LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = " ";
            for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                if (GDCIQLDR.INFO[WS_G-1].CCY.equalsIgnoreCase(LNCDDALN.COMM_DATA.CCY)) {
                    if (WS_KOU_KUAN_AMT > GDCIQLDR.INFO[WS_G-1].RAMT) {
                        WS_KOU_GDA_AMT = GDCIQLDR.INFO[WS_G-1].RAMT;
                    } else {
                        WS_KOU_GDA_AMT = WS_KOU_KUAN_AMT;
                    }
                    IBS.init(SCCGWA, GDCUMPDR);
                    GDCUMPDR.INPUT.FUNC = 'D';
                    GDCUMPDR.INPUT.RSEQ = LNCPPMQ.DATA_CONT_SPC.CONT_GDA_APREF;
                    GDCUMPDR.INPUT.AC = GDCIQLDR.INFO[WS_G-1].OUT_AC;
                    GDCUMPDR.INPUT.AMT = WS_KOU_GDA_AMT;
                    if (GDCUMPDR.INPUT.AMT != 0) {
                        WS_SEQ1 += 1;
                        GDCUMPDR.INPUT.SEQ_NO = WS_SEQ1;
                        S000_CALL_GDZUMPDR();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, WS_GDA_ACAMT[WS_SEQ1-1]);
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_AC1 = GDCIQLDR.INFO[WS_G-1].OUT_AC;
                        if (GDCIQLDR.INFO[WS_G-1].OUT_AC.trim().length() == 0) WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_AC_SEQ1 = 0;
                        else WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_AC_SEQ1 = Integer.parseInt(GDCIQLDR.INFO[WS_G-1].OUT_AC);
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_CCY1 = GDCIQLDR.INFO[WS_G-1].CCY;
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_PAY_AMT1 = GDCUMPDR.INPUT.AMT;
                    }
                    WS_KOU_GDA_TOT_AMT += GDCUMPDR.INPUT.AMT;
                    WS_KOU_KUAN_AMT -= GDCUMPDR.INPUT.AMT;
                }
            }
            WS_KOU_KUAN_AMT = WS_KOU_GDA_TOT_AMT;
        } else {
            for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].OUT_AC) 
                    && LNCDDALN.COMM_DATA.CCY.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].CCY) 
                    && GDCIQLDR.INFO[WS_G-1].RAMT != 0) {
                    if (WS_KOU_KUAN_AMT > GDCIQLDR.INFO[WS_G-1].RAMT) {
                        WS_KOU_GDA_AMT = GDCIQLDR.INFO[WS_G-1].RAMT;
                    } else {
                        WS_KOU_GDA_AMT = WS_KOU_KUAN_AMT;
                    }
                    IBS.init(SCCGWA, GDCUMPDR);
                    GDCUMPDR.INPUT.FUNC = 'D';
                    GDCUMPDR.INPUT.RSEQ = LNCPPMQ.DATA_CONT_SPC.CONT_GDA_APREF;
                    GDCUMPDR.INPUT.AC = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
                    GDCUMPDR.INPUT.AMT = WS_KOU_GDA_AMT;
                    if (GDCUMPDR.INPUT.AMT != 0) {
                        WS_SEQ1 += 1;
                        GDCUMPDR.INPUT.SEQ_NO = WS_SEQ1;
                        S000_CALL_GDZUMPDR();
                        if (pgmRtn) return;
                    }
                    WS_KOU_GDA_TOT_AMT += GDCUMPDR.INPUT.AMT;
                    WS_KOU_KUAN_AMT -= GDCUMPDR.INPUT.AMT;
                }
            }
            WS_KOU_KUAN_AMT = WS_KOU_GDA_TOT_AMT;
        }
    }
    public void B052_CASH_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = LNCDDALN.COMM_DATA.CCY;
        BPCUABOX.CCY_TYP = '0';
        BPCUABOX.AMT = WS_KOU_KUAN_AMT;
        BPCUABOX.OPP_AC = LNCDDALN.COMM_DATA.CTA_NO;
        BPCUABOX.CASH_NO = "117";
        BPCUABOX.RMK = "LOAN PRE-REPAYMENT";
        if (BPCUABOX.AMT != 0) {
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = BPCUABOX.AMT;
    }
    public void B070_CUST_REPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCURPN);
        LNCURPN.COMM_DATA.ACM_EVENT = "RLN";
        LNCURPN.COMM_DATA.LN_AC = LNCDDALN.COMM_DATA.CTA_NO;
        LNCURPN.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCURPN.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCURPN.COMM_DATA.SUF_NO = "0" + LNCURPN.COMM_DATA.SUF_NO;
        LNCURPN.COMM_DATA.SUBS_FLG = LNCDDALN.COMM_DATA.SUBS_FLG;
        LNCURPN.COMM_DATA.TR_VAL_DATE = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNCDDALN.COMM_DATA.TOT_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCDDALN.COMM_DATA.TOT_P_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = WS_TOT_P_UDAMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCDDALN.COMM_DATA.TOT_I_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCDDALN.COMM_DATA.TOT_O_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCDDALN.COMM_DATA.TOT_L_AMT;
        LNCURPN.COMM_DATA.APT_REF = LNCSRPC.COMM_DATA.APT_REF;
        LNCURPN.COMM_DATA.RDI_FLG = LNCDDALN.COMM_DATA.RDI_FLG;
        LNCURPN.COMM_DATA.RDI_AMT = LNCDDALN.COMM_DATA.RDI_AMT;
        LNCURPN.COMM_DATA.ADJ_TYP = LNCDDALN.COMM_DATA.ADJ_TYP;
        LNCURPN.COMM_DATA.ADJ_AC = LNCDDALN.COMM_DATA.ADJ_AC;
        LNCURPN.COMM_DATA.HRG_AMT = LNCDDALN.COMM_DATA.HRG_AMT;
        LNCURPN.COMM_DATA.CUR_TRM = LNCDDALN.COMM_DATA.CUR_TRM;
        LNCURPN.COMM_DATA.CLN_CUT = LNCDDALN.COMM_DATA.CLN_CUT;
        LNCURPN.COMM_DATA.MMO = LNCDDALN.COMM_DATA.MMO;
        WS_G = 1;
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            if (LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() == 0 
                && LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0) {
                WS_I = 6;
            } else {
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].REC_AC2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].AC_FLG2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].AC_FLG2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].AMT_FRM2 = LNCDDALN.COMM_DATA.ACAMT[WS_I-1].AMT_FRM2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].REC_CCY2 = LNCDDALN.COMM_DATA.CCY;
            }
        }
        S000_CALL_LNZURPN();
        if (pgmRtn) return;
    }
    public void B110_BP_HISTORY_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = LNCDDALN.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.ACO_AC = LNCDDALN.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.REF_NO = LNCDDALN.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        BPCPFHIS.DATA.TX_CCY = LNCDDALN.COMM_DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCDDALN.COMM_DATA.TOT_P_AMT + LNCDDALN.COMM_DATA.TOT_I_AMT + LNCDDALN.COMM_DATA.TOT_O_AMT + LNCDDALN.COMM_DATA.TOT_L_AMT + LNCSCLR.COMM_DATA.NOR_P + LNCSCLR.COMM_DATA.NOR_I + LNCSCLR.COMM_DATA.OVR_P + LNCSCLR.COMM_DATA.OVR_I + LNCSCLR.COMM_DATA.NOR_O + LNCSCLR.COMM_DATA.NOR_L;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        BPCPFHIS.DATA.TX_MMO = LNCDDALN.COMM_DATA.MMO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        BPCPFHIS.DATA.CI_NO = LNCDDALN.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCDDALN.COMM_DATA.PROD_CD;
        if (LNCDDALN.COMM_DATA.ACAMT[1-1].AC_FLG2 == '0') {
            BPCPFHIS.DATA.OTH_AC = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else {
            BPCPFHIS.DATA.RLT_AC = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        }
        if (LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("05) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("06) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            BPCPFHIS.DATA.TX_TOOL = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        }
        IBS.init(SCCGWA, LNCOD10.TXN_INPUT);
        LNCOD10.TXN_INPUT.CTA_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNCOD10.TXN_INPUT.BR = LNCDDALN.COMM_DATA.BR;
        LNCOD10.TXN_INPUT.CI_NO = LNCDDALN.COMM_DATA.CI_NO;
        LNCOD10.TXN_INPUT.CI_ENMS = " ";
        LNCOD10.TXN_INPUT.CITY_CD = " ";
        LNCOD10.TXN_INPUT.PROD_CD = LNCDDALN.COMM_DATA.PROD_CD;
        LNCOD10.TXN_INPUT.CCY = LNCDDALN.COMM_DATA.CCY;
        LNCOD10.TXN_INPUT.LON_PRIN = LNCDDALN.COMM_DATA.PRINCIPAL;
        LNCOD10.TXN_INPUT.LON_BAL = LNCDDALN.COMM_DATA.BALANCE - LNCDDALN.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TR_VALDT = LNCDDALN.COMM_DATA.TR_VAL_DTE;
        LNCOD10.TXN_INPUT.TOT_AMT = LNCDDALN.COMM_DATA.TOT_AMT;
        LNCOD10.TXN_INPUT.TOT_PRIN = LNCDDALN.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TOT_INT = LNCDDALN.COMM_DATA.TOT_I_AMT;
        LNCOD10.TXN_INPUT.TOT_PLC = LNCDDALN.COMM_DATA.TOT_O_AMT;
        LNCOD10.TXN_INPUT.WAV_PLC = 0;
        LNCOD10.TXN_INPUT.TOT_ILC = LNCDDALN.COMM_DATA.TOT_L_AMT;
        LNCOD10.TXN_INPUT.WAV_ILC = 0;
        LNCOD10.TXN_INPUT.HRG_AMT = LNCDDALN.COMM_DATA.HRG_AMT;
        LNCOD10.TXN_INPUT.SETL_MTH = LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2;
        if (LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("01) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.DEP_AC = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("07) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.CHQ_NO = LNCDDALN.COMM_DATA.ACAMT[1-1].CHQ_NO2;
        } else if (LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("02) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.SUSP_AC = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("03) LNCDDALN.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.NOS_CD = LNCDDALN.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else {
        }
        LNCOD10.IDX = 0;
        LNCOD10.TXN_INPUT.TAX_AMT = 0;
        BPCPFHIS.DATA.FMT_CODE = K_FMT_ID;
        BPCPFHIS.DATA.FMT_LEN = 2481;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCOD10);
        if (BPCPFHIS.DATA.TX_AMT != 0) {
            S000_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void B120_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_ID;
        SCCFMT.DATA_PTR = LNCOD10;
        SCCFMT.DATA_LEN = 2481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B340_UPDATE_LNTPAIP() throws IOException,SQLException,Exception {
        B310_DELETE_LNTPLPI();
        if (pgmRtn) return;
        R000_GET_LOAN_BAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRPAIP);
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNRPAIP.KEY.PHS_NO = 1;
        LNCRPAIP.FUNC = 'R';
        WS_PAIP_FLG = 'Y';
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
        if (WS_PAIP_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_COMP_INST_AMT();
        if (pgmRtn) return;
        LNRPAIP.PHS_INST_AMT = WS_PHS_INST_AMT;
        LNRPAIP.PHS_REM_PRIN_AMT = WS_LOAN_CONT_AREA.REDEFINES35.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
        LNRPAIP.PHS_CAL_TERM = (short) (LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
        LNRPAIP.PHS_CMP_TERM = LNRPAIP.PHS_CAL_TERM;
        LNRPAIP.CUR_INST_AMT = WS_PHS_INST_AMT;
        LNRPAIP.CUR_INST_IRAT = WS_N_RATE;
        LNCRPAIP.FUNC = 'U';
        S000_CALL_LNZPAIPL();
        if (pgmRtn) return;
    }
    public void R000_GET_LOAN_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
    }
    public void R000_COMP_INST_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '4';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCILCM);
        if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
            LNCILCM.COMM_DATA.FORM_CODE = "31";
        } else if (LNCAPRDM.REC_DATA.INST_MTH == '2') {
            LNCILCM.COMM_DATA.FORM_CODE = "34";
        }
        LNCILCM.COMM_DATA.FUNC_CODE = '1';
        LNCILCM.COMM_DATA.PRIN_AMT = WS_LOAN_CONT_AREA.REDEFINES35.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
        LNCILCM.COMM_DATA.RATE = WS_N_RATE;
        LNCILCM.COMM_DATA.BASDAYS_STD = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCILCM.COMM_DATA.PERIOD = LNCAPRDM.REC_DATA.CAL_PERD;
        LNCILCM.COMM_DATA.PERIOD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        LNCILCM.COMM_DATA.CCY = LNRCONT.CCY;
        LNRPAIP.PHS_TOT_TERM = (short) (LNRPAIP.PHS_TOT_TERM + LNCICTLM.REC_DATA.IC_CAL_TERM - 1);
        LNCILCM.COMM_DATA.TERM = LNRPAIP.PHS_TOT_TERM;
        S000_CALL_LNZILCM();
        if (pgmRtn) return;
        WS_PHS_INST_AMT = LNCILCM.COMM_DATA.INST_AMT;
    }
    public void B310_DELETE_LNTPLPI() throws IOException,SQLException,Exception {
        WS_REPY_TYP = 'C';
        WS_INPUT_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        R000_DEL_PLPI_UNTIL_NF();
        if (pgmRtn) return;
    }
    public void R000_DEL_PLPI_UNTIL_NF() throws IOException,SQLException,Exception {
        WS_PLPI_END_FLG = 'N';
        S000_CALL_LNZRPLPI_DELETE();
        if (pgmRtn) return;
        while (WS_PLPI_END_FLG != 'Y') {
            S000_CALL_LNZRPLPI_DELETE();
            if (pgmRtn) return;
        }
    }
    public void B042_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_AC;
        AICPQMIB.INPUT_DATA.CCY = WS_AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.BAL_RFLG);
        if (AICPQMIB.OUTPUT_DATA.CBAL < 0) {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL * ( -1 );
        } else {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL;
        }
    }
    public void B044_GET_GDA_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCIQLDR);
        GDCIQLDR.RSEQ = LNCPPMQ.DATA_CONT_SPC.CONT_GDA_APREF;
        S000_CALL_GDCIQLDR();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        CEP.TRC(SCCGWA, "DDZUDRAC SUCC");
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCDDALN.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCDDALN.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_GDCIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZURPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REPAY-NORMAL", LNCURPN);
        CEP.TRC(SCCGWA, LNCURPN.RC);
        if (LNCURPN.RC.RC_RTNCODE != 0) {
            LNCDDALN.RC.RC_APP = LNCURPN.RC.RC_APP;
            LNCDDALN.RC.RC_RTNCODE = LNCURPN.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRPLPI_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'R';
        LNRPLPI.KEY.CONTRACT_NO = LNCDDALN.COMM_DATA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = 0;
        LNRPLPI.KEY.REPY_MTH = WS_REPY_TYP;
        LNRPLPI.KEY.TERM = WS_INPUT_TERM;
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        if (LNCRPLPI.RC.RC_CODE == 0) {
            LNCRPLPI.FUNC = 'D';
            S000_CALL_LNZRPLPI();
            if (pgmRtn) return;
        }
        if (LNCRPLPI.RC.RC_CODE == 0) {
            WS_INPUT_TERM += 1;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCDDALN.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCDDALN.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        CEP.TRC(SCCGWA, LNCCLNQ.RC.RC_CODE);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_RCVD_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND DUE_DT = :LNRRCVD.DUE_DT "
            + "AND REPY_STS < > '2'";
        LNTRCVD_BR.rp.order = "AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND, LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRRCVD.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_RCVD_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RCVD_NOTFND, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_RCVD_REWRITE_PROC() throws IOException,SQLException,Exception {
        LNRRCVD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRCVD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void S000_CALL_LNZRPLPI() throws IOException,SQLException,Exception {
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            if (LNCRPLPI.RETURN_INFO == 'N') {
                WS_PLPI_END_FLG = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZPAIPL() throws IOException,SQLException,Exception {
        LNCRPAIP.REC_PTR = LNRPAIP;
        LNCRPAIP.REC_LEN = 200;
        IBS.CALLCPN(SCCGWA, "LN-R-PAIPL-MAIN", LNCRPAIP);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND)) {
                WS_PAIP_FLG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZILCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INST-AMT-CMP", LNCILCM);
        if (LNCILCM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCILCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCDDALN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCDDALN.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCDDALN=");
            CEP.TRC(SCCGWA, LNCDDALN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
