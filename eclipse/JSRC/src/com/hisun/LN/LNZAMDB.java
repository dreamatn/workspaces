package com.hisun.LN;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class LNZAMDB {
    int JIBS_tmp_int;
    DBParm LNTRCVD_RD;
    LNZAMDB_WS_O_RCV_DATA WS_O_RCV_DATA;
    BigDecimal bigD;
    LNZAMDB_WS_RCV_DATA WS_RCV_DATA;
    DecimalFormat df;
    brParm LNTRCVD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_REC_NUM = 1000;
    char K_CKPD_INQ = '0';
    String WS_ERR_MSG = " ";
    String WS_MSG_INFO = "                                                                      ";
    String WS_MSG_AMT = " ";
    char LNZAMDB_FILLER4 = ' ';
    String WS_PSEQ_CD = " ";
    short WS_STS_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_G = 0;
    short WS_X = 0;
    double WS_AVA_AMT = 0;
    double WS_AVA_TYPE_AMT = 0;
    double WS_TMP_AMT = 0;
    double WS_TMP_AMT0 = 0;
    char WS_STOP_ID = ' ';
    double WS_CUR_TRM_TOT = 0;
    int WS_DUE_DT_WORK = 0;
    double WS_BAL_AMT = 0;
    double WS_DUE_PRIN_AMT = 0;
    double WS_PER_INS_AMT = 0;
    LNZAMDB_WS_DISB_SEQ WS_DISB_SEQ = new LNZAMDB_WS_DISB_SEQ();
    LNZAMDB_WS_TOT_D_AMTS WS_TOT_D_AMTS = new LNZAMDB_WS_TOT_D_AMTS();
    int WS_REC_CNT = 0;
    int WS_P_CNT = 0;
    int WS_I_CNT = 0;
    int WS_O_CNT = 0;
    int WS_L_CNT = 0;
    int WS_F_CNT = 0;
    int WS_P_MIN_CNT = 0;
    int WS_I_MIN_CNT = 0;
    int WS_O_MIN_CNT = 0;
    int WS_L_MIN_CNT = 0;
    int WS_F_MIN_CNT = 0;
    int WS_MAX_MIN_CNT = 0;
    List<LNZAMDB_WS_RCV_DATA> WS_RCV_DATA = new ArrayList<LNZAMDB_WS_RCV_DATA>();
    List<LNZAMDB_WS_O_RCV_DATA> WS_O_RCV_DATA = new ArrayList<LNZAMDB_WS_O_RCV_DATA>();
    char WS_FOUND_FLG = ' ';
    char WS_F_PAY_FLG = ' ';
    char WS_FST_TERM_FLG = ' ';
    char WS_CKPD_FUNC = ' ';
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCPAYSM LNCPAYSM = new LNCPAYSM();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCSAPT LNCSAPT = new LNCSAPT();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    LNCAMDB LNCAMDB;
    public void MP(SCCGWA SCCGWA, LNCAMDB LNCAMDB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCAMDB = LNCAMDB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZAMDB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS);
        LNCAMDB.RC.RC_APP = "LN";
        LNCAMDB.RC.RC_RTNCODE = 0;
        WS_DISB_SEQ.WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_DISB_SEQ.WS_SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCAMDB.COMM_DATA.APT_REF = IBS.CLS2CPY(SCCGWA, WS_DISB_SEQ);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.MAX_PAY_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TR_VAL_DATE);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.SUBS_FLG);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.CUR_TRM);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCAMDB.COMM_DATA.FUNC_CODE != 'I' 
            && LNCAMDB.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCAMDB.COMM_DATA.LN_AC.equalsIgnoreCase("0") 
            || LNCAMDB.COMM_DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT > 0) {
            WS_TOT_D_AMTS.WS_TOT_D_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT;
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT != WS_TOT_D_AMTS.WS_TOT_D_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH3, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        if (LNCAMDB.COMM_DATA.FUNC_CODE == 'U') {
            B300_UPDATE_DISB();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        B120_GET_ICTL_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        LNCAPRDM.FUNC = '3';
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        LNCCONTM.FUNC = '3';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_SEQ.trim().length() > 0) {
            WS_PSEQ_CD = LNCAPRDM.REC_DATA.PAY_SEQ;
        } else {
            B130_GET_PARM_INF();
            if (pgmRtn) return;
        }
        B140_GET_PAYSQ_INF();
        if (pgmRtn) return;
    }
    public void B120_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        if (LNCAMDB.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCAMDB.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NOT_SUP_DIST, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STS_NO = 1;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_STS_NO = 1;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS_NO = 2;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS_NO = 3;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_STS_NO = 7;
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW);
        CEP.TRC(SCCGWA, WS_STS_NO);
    }
    public void B130_GET_PARM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        if (WS_STS_NO == 1 
            || WS_STS_NO == 2) {
            WS_PSEQ_CD = LNCSCKPD.REPY_CT1;
        }
        if (WS_STS_NO == 3) {
            WS_PSEQ_CD = LNCSCKPD.REPY_CT2;
        }
        if (WS_STS_NO == 7) {
            WS_PSEQ_CD = LNCSCKPD.REPY_CT3;
        }
        CEP.TRC(SCCGWA, WS_PSEQ_CD);
    }
    public void B140_GET_PAYSQ_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PSEQ_CD);
        IBS.init(SCCGWA, LNCPAYSM);
        LNCPAYSM.FUNC = 'I';
        LNCPAYSM.KEY.TYP = "PAYSQ";
        LNCPAYSM.KEY.CD = WS_PSEQ_CD;
        S000_CALL_LNZPAYSM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B300_GET_RCVD_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.CUR_TRM);
        if (LNCAMDB.COMM_DATA.CUR_TRM == 'Y') {
            B320_GET_DANG_QI();
            if (pgmRtn) return;
            if (WS_CUR_TRM_TOT == 0) {
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_I == 0) {
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_REC_CNT = WS_I;
        }
        B400_PAY_AMDB_PROC();
        if (pgmRtn) return;
        B500_TOT_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B320_GET_WOF_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQUDUP";
        LNCCNEX.COMM_DATA.LN_AC = LNCAMDB.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT = LNCCNEX.COMM_DATA.INQ_AMT;
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT);
    }
    public void B320_GET_DANG_QI() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_MAT, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
            if (LNCAMDB.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCAMDB.COMM_DATA.SUF_NO);
            LNRRCVD.REPY_STS = '2';
            LNRRCVD.DUE_DT = LNCAMDB.COMM_DATA.TR_VAL_DATE;
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_STS";
            LNTRCVD_RD.where = "DUE_DT > :LNRRCVD.DUE_DT";
            LNTRCVD_RD.fst = true;
            IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPET_CUR_REPAY, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "YOU YING HUANG KUAN BU NENG HUAN DANG QI");
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNRRCVD.DUE_DT = LNCAMDB.COMM_DATA.TR_VAL_DATE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_RD.where = "REPY_STS < > :LNRRCVD.REPY_STS "
            + "AND DUE_DT <= :LNRRCVD.DUE_DT";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CUR_TRM_TOT = 0;
        IBS.init(SCCGWA, LNCSSCHE);
        LNCSSCHE.DATA_AREA.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        LNCSSCHE.CUR_FLG = 'Y';
        S000_CALL_LNZSSCHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_TYP);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_INT);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_TYP);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_PRIN);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_INT);
        if (LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE != 0) {
            if (LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE == LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE) {
                for (WS_I = 1; WS_I <= 2; WS_I += 1) {
                    WS_REC_CNT = WS_I;
                    IBS.init(SCCGWA, WS_O_RCV_DATA.set(WS_I-1, ));
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_TYPE = LNCSSCHE.OUT_INFO[WS_I-1].O_PAY_TYP;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_TERM = LNCSSCHE.OUT_INFO[WS_I-1].O_TERM;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_VAL_DT = LNCSSCHE.OUT_INFO[WS_I-1].O_VAL_DATE;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_DUE_DT = LNCSSCHE.OUT_INFO[WS_I-1].O_DUE_DATE;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_P_AMT = LNCSSCHE.OUT_INFO[WS_I-1].O_PAY_PRIN;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_I_AMT = LNCSSCHE.OUT_INFO[WS_I-1].O_PAY_INT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                        && LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
                        WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT * ( LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100 );
                        bigD = new BigDecimal(WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT);
                        WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    }
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                        WS_O_RCV_DATA.WS_O_F_AMT = WS_PER_INS_AMT;
                        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    }
                    WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                    WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                }
            } else {
                WS_I += 1;
                if (LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE < LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE) {
                    WS_REC_CNT = WS_I;
                    IBS.init(SCCGWA, WS_O_RCV_DATA.set(1-1, ));
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_TYPE = LNCSSCHE.OUT_INFO[1-1].O_PAY_TYP;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_TERM = LNCSSCHE.OUT_INFO[1-1].O_TERM;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_VAL_DT = LNCSSCHE.OUT_INFO[1-1].O_VAL_DATE;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_DUE_DT = LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_P_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_I_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_INT;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                } else {
                    if (LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE > LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE) {
                        WS_REC_CNT = WS_I;
                        IBS.init(SCCGWA, WS_O_RCV_DATA.set(1-1, ));
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_TYPE = LNCSSCHE.OUT_INFO[2-1].O_PAY_TYP;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_TERM = LNCSSCHE.OUT_INFO[2-1].O_TERM;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_VAL_DT = LNCSSCHE.OUT_INFO[2-1].O_VAL_DATE;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_DUE_DT = LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_P_AMT = LNCSSCHE.OUT_INFO[2-1].O_PAY_PRIN;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                        WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                        WS_O_RCV_DATA.WS_O_I_AMT = LNCSSCHE.OUT_INFO[2-1].O_PAY_INT;
                        WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                    }
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                    && LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
                    WS_O_RCV_DATA.get(1-1).WS_O_F_AMT = WS_O_RCV_DATA.get(1-1).WS_O_I_AMT * ( LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100 );
                    bigD = new BigDecimal(WS_O_RCV_DATA.get(1-1).WS_O_F_AMT);
                    WS_O_RCV_DATA.get(1-1).WS_O_F_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                    WS_O_RCV_DATA.WS_O_F_AMT = WS_PER_INS_AMT;
                    WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
                }
                WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(1-1).WS_O_P_AMT;
                WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(1-1).WS_O_I_AMT;
            }
        } else {
            WS_I += 1;
            WS_REC_CNT = WS_I;
            IBS.init(SCCGWA, WS_O_RCV_DATA.set(1-1, ));
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_TYPE = LNCSSCHE.OUT_INFO[1-1].O_PAY_TYP;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_TERM = LNCSSCHE.OUT_INFO[1-1].O_TERM;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_VAL_DT = LNCSSCHE.OUT_INFO[1-1].O_VAL_DATE;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_DUE_DT = LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_P_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
            WS_O_RCV_DATA.WS_O_I_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_INT;
            WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                && LNCAPRDM.REC_DATA.CHG_DB_FLG == '3') {
                WS_O_RCV_DATA.get(1-1).WS_O_F_AMT = WS_O_RCV_DATA.get(1-1).WS_O_I_AMT * ( LNCAPRDM.REC_DATA.HAND_CHG_RATE / 100 );
                bigD = new BigDecimal(WS_O_RCV_DATA.get(1-1).WS_O_F_AMT);
                WS_O_RCV_DATA.get(1-1).WS_O_F_AMT = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(1-1);
                WS_O_RCV_DATA.WS_O_F_AMT = WS_PER_INS_AMT;
                WS_O_RCV_DATA.set(1-1, WS_O_RCV_DATA);
            }
            WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(1-1).WS_O_P_AMT;
            WS_CUR_TRM_TOT += WS_O_RCV_DATA.get(1-1).WS_O_I_AMT;
            CEP.TRC(SCCGWA, WS_CUR_TRM_TOT);
        }
    }
    public void B300_GET_RCVD_INF() throws IOException,SQLException,Exception {
        WS_I = 0;
        WS_FOUND_FLG = 'N';
        T01_STARTBR_LNTRCVD();
        if (pgmRtn) return;
        T01_READNEXT_LNTRCVD();
        if (pgmRtn) return;
        while (WS_FOUND_FLG != 'N' 
            && WS_I < WS_REC_NUM) {
            if (LNRRCVD.P_REC_AMT == 0 
                && LNRRCVD.I_REC_AMT == 0 
                && LNRRCVD.O_REC_AMT == 0 
                && LNRRCVD.L_REC_AMT == 0 
                && LNRRCVD.F_REC_AMT == 0) {
            } else {
                CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.F_REC_AMT);
                WS_I += 1;
                B310_TERM_RCVD_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, LNRRCVD.O_REC_AMT);
            CEP.TRC(SCCGWA, LNRRCVD.L_REC_AMT);
            T01_READNEXT_LNTRCVD();
            if (pgmRtn) return;
        }
        T01_ENDBR_LNTRCVD();
        if (pgmRtn) return;
    }
    public void B310_TERM_RCVD_PROC() throws IOException,SQLException,Exception {
        WS_REC_CNT = WS_I;
        IBS.init(SCCGWA, WS_O_RCV_DATA.set(WS_I-1, ));
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_TYPE = LNRRCVD.KEY.AMT_TYP;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_TERM = LNRRCVD.KEY.TERM;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_SUBS_PROJ_NO = LNRRCVD.KEY.SUBS_PROJ_NO;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_VAL_DT = LNRRCVD.VAL_DT;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_DUE_DT = LNRRCVD.DUE_DT;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        if (LNRRCVD.P_REC_AMT < 0 
            && LNRRCVD.I_REC_AMT < 0 
            && LNRRCVD.O_REC_AMT < 0 
            && LNRRCVD.L_REC_AMT < 0 
            && LNRRCVD.F_REC_AMT < 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REC_AMT_LZERO, LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_P_AMT = LNRRCVD.P_REC_AMT;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT -= LNRRCVD.P_PAY_AMT;
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_I_AMT = LNRRCVD.I_REC_AMT;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT -= LNRRCVD.I_PAY_AMT;
        WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT -= LNRRCVD.I_WAV_AMT;
        if (LNRRCVD.KEY.SUBS_PROJ_NO == 0) {
            B312_LC_COMP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.O_AMT);
            CEP.TRC(SCCGWA, LNCLCCM.COMM_DATA.L_AMT);
            WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT += LNCLCCM.COMM_DATA.O_AMT;
            WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT += LNCLCCM.COMM_DATA.L_AMT;
        }
        WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
        WS_O_RCV_DATA.WS_O_F_AMT = LNRRCVD.F_REC_AMT;
        WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
        WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT -= LNRRCVD.F_PAY_AMT;
        WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT -= LNRRCVD.F_WAV_AMT;
    }
    public void B312_LC_COMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNCAMDB.COMM_DATA.LN_AC;
        LNCLCCM.COMM_DATA.SUF_NO = LNCAMDB.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TYPE = WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE;
        LNCLCCM.COMM_DATA.TERM = WS_O_RCV_DATA.get(WS_I-1).WS_O_TERM;
        LNCLCCM.COMM_DATA.END_DATE = LNCAMDB.COMM_DATA.TR_VAL_DATE;
        S000_CALL_LNZLCCM();
        if (pgmRtn) return;
    }
    public void B400_DUE_DENG_HE_PROC() throws IOException,SQLException,Exception {
        WS_J = 0;
        WS_DUE_DT_WORK = 0;
        for (WS_I = 1; WS_I <= WS_REC_CNT 
            && WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE != ' '; WS_I += 1) {
            if (WS_O_RCV_DATA.get(WS_I-1).WS_O_DUE_DT != WS_DUE_DT_WORK) {
                WS_J += 1;
                IBS.init(SCCGWA, WS_RCV_DATA.set(WS_J-1, ));
                WS_DUE_DT_WORK = WS_O_RCV_DATA.get(WS_I-1).WS_O_DUE_DT;
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_TYPE = WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_DUE_DT = WS_O_RCV_DATA.get(WS_I-1).WS_O_DUE_DT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_P_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_I_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_O_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_L_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_F_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
            } else {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_J-1);
                WS_RCV_DATA.WS_TYPE = 'Q';
                WS_RCV_DATA.set(WS_J-1, WS_RCV_DATA);
                WS_RCV_DATA.get(WS_J-1).WS_P_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                WS_RCV_DATA.get(WS_J-1).WS_I_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                WS_RCV_DATA.get(WS_J-1).WS_O_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
                WS_RCV_DATA.get(WS_J-1).WS_L_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
                WS_RCV_DATA.get(WS_J-1).WS_F_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
            }
            CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_J-1).WS_P_AMT);
            CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_J-1).WS_L_AMT);
            WS_DUE_PRIN_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
        }
    }
    public void B400_DUE_DENG_FEN_PROC() throws IOException,SQLException,Exception {
        WS_J = 0;
        WS_G = 0;
        WS_DUE_DT_WORK = 0;
        for (WS_I = 1; WS_I <= WS_REC_CNT 
            && WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE != ' '; WS_I += 1) {
            WS_J += 1;
            if (WS_RCV_DATA.get(WS_J-1).WS_TYPE == 'Q') {
                WS_G = (short) (WS_I + 1);
                if (WS_RCV_DATA.get(WS_J-1).WS_D_P_AMT > WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_P_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA.get(WS_G-1).WS_O_D_P_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_P_AMT - WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                } else {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_P_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_P_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_G-1);
                    WS_O_RCV_DATA.WS_O_D_P_AMT = 0;
                    WS_O_RCV_DATA.set(WS_G-1, WS_O_RCV_DATA);
                }
                if (WS_RCV_DATA.get(WS_J-1).WS_D_I_AMT > WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_I_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA.get(WS_G-1).WS_O_D_I_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_I_AMT - WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                } else {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_I_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_I_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_G-1);
                    WS_O_RCV_DATA.WS_O_D_I_AMT = 0;
                    WS_O_RCV_DATA.set(WS_G-1, WS_O_RCV_DATA);
                }
                if (WS_RCV_DATA.get(WS_J-1).WS_D_O_AMT > WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_O_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA.get(WS_G-1).WS_O_D_O_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_O_AMT - WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
                } else {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_O_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_O_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_G-1);
                    WS_O_RCV_DATA.WS_O_D_O_AMT = 0;
                    WS_O_RCV_DATA.set(WS_G-1, WS_O_RCV_DATA);
                }
                if (WS_RCV_DATA.get(WS_J-1).WS_D_L_AMT > WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_L_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA.get(WS_G-1).WS_O_D_L_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_L_AMT - WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
                } else {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_L_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_L_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_G-1);
                    WS_O_RCV_DATA.WS_O_D_L_AMT = 0;
                    WS_O_RCV_DATA.set(WS_G-1, WS_O_RCV_DATA);
                }
                if (WS_RCV_DATA.get(WS_J-1).WS_D_F_AMT > WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT) {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_F_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA.get(WS_G-1).WS_O_D_F_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_F_AMT - WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
                } else {
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                    WS_O_RCV_DATA.WS_O_D_F_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_F_AMT;
                    WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                    WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_G-1);
                    WS_O_RCV_DATA.WS_O_D_F_AMT = 0;
                    WS_O_RCV_DATA.set(WS_G-1, WS_O_RCV_DATA);
                }
                WS_I = WS_G;
            } else {
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_P_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_P_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_I_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_I_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_O_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_O_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_L_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_L_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_F_AMT = WS_RCV_DATA.get(WS_J-1).WS_D_F_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
            }
        }
    }
    public void B400_PAY_AMDB_PROC() throws IOException,SQLException,Exception {
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT > 0 
            || LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT > 0) {
            CEP.TRC(SCCGWA, "SEP AMTS");
            B400_DUE_DENG_HE_PROC();
            if (pgmRtn) return;
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                B400_PAY_AMDB_WOF_PROC1();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_P_CNT);
            CEP.TRC(SCCGWA, WS_I_CNT);
            CEP.TRC(SCCGWA, WS_O_CNT);
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_F_CNT);
            CEP.TRC(SCCGWA, WS_F_PAY_FLG);
            B410_PAY_AMDB_P();
            if (pgmRtn) return;
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                B401_CHECK_SUNI_AMDB();
                if (pgmRtn) return;
            }
            B400_DUE_DENG_FEN_PROC();
            if (pgmRtn) return;
        } else {
            if (LNCAMDB.COMM_DATA.MAX_PAY_AMT > 0) {
                CEP.TRC(SCCGWA, "ONLY TOT AMTS");
                B400_DUE_DENG_HE_PROC();
                if (pgmRtn) return;
                WS_AVA_AMT = LNCAMDB.COMM_DATA.MAX_PAY_AMT;
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
                    && LNCPAYSM.DATA_TXT.REPY_MTH == 'L') {
                    for (WS_I = 1; WS_I <= WS_REC_CNT 
                        && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                        && WS_AVA_AMT != 0; WS_I += 1) {
                        if (WS_RCV_DATA.get(WS_I-1).WS_F_AMT <= WS_AVA_AMT) {
                            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                            WS_RCV_DATA.WS_D_F_AMT = WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
                            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
                            WS_AVA_AMT -= WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
                        } else {
                            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                            WS_RCV_DATA.WS_D_F_AMT = WS_AVA_AMT;
                            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
                            WS_AVA_AMT = 0;
                        }
                    }
                }
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    B400_PAY_AMDB_WOF_PROC2();
                    if (pgmRtn) return;
                }
                if (LNCPAYSM.DATA_TXT.REPY_MTH == 'T') {
                    B410_PAY_AMDB_T();
                    if (pgmRtn) return;
                }
                if (LNCPAYSM.DATA_TXT.REPY_MTH == 'L') {
                    B420_PAY_AMDB_L();
                    if (pgmRtn) return;
                }
                B400_DUE_DENG_FEN_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B400_PAY_AMDB_WOF_PROC1() throws IOException,SQLException,Exception {
        WS_BAL_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT + WS_DUE_PRIN_AMT;
        if (LNCAMDB.COMM_DATA.MAX_PAY_AMT >= WS_BAL_AMT) {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT;
        } else {
            if (LNCAMDB.COMM_DATA.MAX_PAY_AMT > WS_DUE_PRIN_AMT) {
                LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = LNCAMDB.COMM_DATA.MAX_PAY_AMT - WS_DUE_PRIN_AMT;
            }
        }
    }
    public void B400_PAY_AMDB_WOF_PROC2() throws IOException,SQLException,Exception {
        WS_BAL_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT + WS_DUE_PRIN_AMT;
        if (LNCAMDB.COMM_DATA.MAX_PAY_AMT >= WS_BAL_AMT) {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT;
            WS_AVA_AMT = LNCAMDB.COMM_DATA.MAX_PAY_AMT - LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT;
        } else {
            if (LNCAMDB.COMM_DATA.MAX_PAY_AMT > WS_DUE_PRIN_AMT) {
                LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = LNCAMDB.COMM_DATA.MAX_PAY_AMT - WS_DUE_PRIN_AMT;
                WS_AVA_AMT = WS_DUE_PRIN_AMT;
            }
        }
        LNCPAYSM.DATA_TXT.REPY_MTH = 'L';
        for (WS_X = 1; WS_X <= 5; WS_X += 1) {
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].P_FLG = 0;
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].I_FLG = 0;
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].O_FLG = 0;
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].L_FLG = 0;
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].F_FLG = 0;
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].FUL_PAY_FLG = 'N';
            LNCPAYSM.DATA_TXT.DATA[WS_X-1].CRS_PAY_FLG = 'N';
        }
        LNCPAYSM.DATA_TXT.DATA[1-1].P_FLG = 1;
        LNCPAYSM.DATA_TXT.DATA[2-1].I_FLG = 1;
        LNCPAYSM.DATA_TXT.DATA[3-1].O_FLG = 1;
        LNCPAYSM.DATA_TXT.DATA[4-1].L_FLG = 1;
    }
    public void B401_CHECK_SUNI_AMDB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_P_CNT);
        CEP.TRC(SCCGWA, WS_I_CNT);
        CEP.TRC(SCCGWA, WS_O_CNT);
        CEP.TRC(SCCGWA, WS_L_CNT);
        CEP.TRC(SCCGWA, WS_F_CNT);
        CEP.TRC(SCCGWA, WS_F_PAY_FLG);
        if (WS_F_CNT > 0) {
            if (WS_F_PAY_FLG == 'Y' 
                && (WS_F_CNT < WS_P_CNT 
                || WS_F_CNT < WS_I_CNT 
                || WS_F_CNT < WS_O_CNT 
                || WS_F_CNT < WS_L_CNT)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUNI_RPY_FST, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (WS_F_PAY_FLG == 'N' 
                && (WS_F_CNT <= WS_P_CNT 
                || WS_F_CNT <= WS_I_CNT 
                || WS_F_CNT <= WS_O_CNT 
                || WS_F_CNT <= WS_L_CNT)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUNI_RPY_FST, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            WS_MAX_MIN_CNT = WS_P_MIN_CNT;
            if (WS_MAX_MIN_CNT < WS_I_MIN_CNT) {
                WS_MAX_MIN_CNT = WS_I_MIN_CNT;
            }
            if (WS_MAX_MIN_CNT < WS_O_MIN_CNT) {
                WS_MAX_MIN_CNT = WS_O_MIN_CNT;
            }
            if (WS_MAX_MIN_CNT < WS_L_MIN_CNT) {
                WS_MAX_MIN_CNT = WS_L_MIN_CNT;
            }
            for (WS_I = 1; WS_I <= WS_MAX_MIN_CNT; WS_I += 1) {
                if (WS_RCV_DATA.get(WS_I-1).WS_F_AMT > 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUNI_RPY_FST, LNCAMDB.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B410_PAY_AMDB_P() throws IOException,SQLException,Exception {
        WS_FST_TERM_FLG = 'N';
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT > 0) {
            WS_AVA_TYPE_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT;
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_TYPE_AMT != 0; WS_I += 1) {
                B4S3_PAY_WS_O_P_AMT();
                if (pgmRtn) return;
            }
            WS_P_CNT = WS_I - 1;
        }
        WS_FST_TERM_FLG = 'N';
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT > 0) {
            WS_AVA_TYPE_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT;
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_TYPE_AMT != 0; WS_I += 1) {
                B4S3_PAY_WS_O_I_AMT();
                if (pgmRtn) return;
            }
            WS_I_CNT = WS_I - 1;
        }
        WS_FST_TERM_FLG = 'N';
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT > 0) {
            WS_AVA_TYPE_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT;
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_TYPE_AMT != 0; WS_I += 1) {
                B4S3_PAY_WS_O_O_AMT();
                if (pgmRtn) return;
            }
            WS_O_CNT = WS_I - 1;
        }
        WS_FST_TERM_FLG = 'N';
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT > 0) {
            WS_AVA_TYPE_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_TYPE_AMT != 0; WS_I += 1) {
                B4S3_PAY_WS_O_L_AMT();
                if (pgmRtn) return;
            }
            WS_L_CNT = WS_I - 1;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT > 0) {
            WS_AVA_TYPE_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT;
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_TYPE_AMT != 0; WS_I += 1) {
                B4S3_PAY_WS_O_F_AMT();
                if (pgmRtn) return;
            }
            WS_F_CNT = WS_I - 1;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            && WS_RCV_DATA.get(WS_F_CNT-1).WS_F_AMT == WS_RCV_DATA.get(WS_F_CNT-1).WS_D_F_AMT) {
            WS_F_PAY_FLG = 'Y';
        } else {
            WS_F_PAY_FLG = 'N';
        }
    }
    public void B410_PAY_AMDB_T() throws IOException,SQLException,Exception {
        WS_STOP_ID = 'N';
        for (WS_I = 1; WS_I <= WS_REC_CNT 
            && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
            && WS_AVA_AMT != 0 
            && WS_STOP_ID != 'Y'; WS_I += 1) {
            for (WS_J = 1; WS_J <= 5 
                && WS_AVA_AMT != 0 
                && WS_STOP_ID != 'Y'; WS_J += 1) {
                B4S1_PAY_AMDB_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B420_PAY_AMDB_L() throws IOException,SQLException,Exception {
        WS_STOP_ID = 'N';
        for (WS_J = 1; WS_J <= 5; WS_J += 1) {
            for (WS_I = 1; WS_I <= WS_REC_CNT 
                && WS_RCV_DATA.get(WS_I-1).WS_TYPE != ' ' 
                && WS_AVA_AMT != 0 
                && WS_STOP_ID != 'Y'; WS_I += 1) {
                B4S1_PAY_AMDB_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B4S1_PAY_AMDB_PROC() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            && LNCPAYSM.DATA_TXT.REPY_MTH == 'T') {
            if (WS_AVA_AMT > WS_RCV_DATA.get(WS_I-1).WS_F_AMT) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_F_AMT = WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
                WS_AVA_AMT -= WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
            } else {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_F_AMT = WS_AVA_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
                WS_AVA_AMT = 0;
            }
        }
        WS_TMP_AMT = WS_RCV_DATA.get(WS_I-1).WS_P_AMT * LNCPAYSM.DATA_TXT.DATA[WS_J-1].P_FLG + WS_RCV_DATA.get(WS_I-1).WS_I_AMT * LNCPAYSM.DATA_TXT.DATA[WS_J-1].I_FLG + WS_RCV_DATA.get(WS_I-1).WS_O_AMT * LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG + WS_RCV_DATA.get(WS_I-1).WS_L_AMT * LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG;
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, WS_AVA_AMT);
        CEP.TRC(SCCGWA, LNCPAYSM.DATA_TXT.DATA[WS_J-1].P_FLG);
        CEP.TRC(SCCGWA, LNCPAYSM.DATA_TXT.DATA[WS_J-1].I_FLG);
        CEP.TRC(SCCGWA, LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG);
        CEP.TRC(SCCGWA, LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG);
        CEP.TRC(SCCGWA, LNCPAYSM.DATA_TXT.DATA[WS_J-1].F_FLG);
        if (WS_TMP_AMT <= WS_AVA_AMT) {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].P_FLG == 1) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_P_AMT = WS_RCV_DATA.get(WS_I-1).WS_P_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            }
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].I_FLG == 1) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_I_AMT = WS_RCV_DATA.get(WS_I-1).WS_I_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            }
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG == 1) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_O_AMT = WS_RCV_DATA.get(WS_I-1).WS_O_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            }
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG == 1) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_L_AMT = WS_RCV_DATA.get(WS_I-1).WS_L_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (!LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                if (WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT != 0) {
                    WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                    WS_RCV_DATA.WS_D_F_AMT = WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
                    WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
                }
            }
            WS_AVA_AMT -= WS_TMP_AMT;
        } else {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].FUL_PAY_FLG == 'N') {
                B4S2_PAY_AMDB_PCT();
                if (pgmRtn) return;
            } else {
                if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].CRS_PAY_FLG == 'N') {
                    WS_STOP_ID = 'Y';
                }
            }
        }
    }
    public void B4S2_PAY_AMDB_PCT() throws IOException,SQLException,Exception {
        WS_TMP_AMT0 = 0;
        if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].P_FLG == 1) {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].I_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].F_FLG == 0) {
                WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT = WS_AVA_AMT - WS_TMP_AMT0;
            } else {
                WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT = WS_AVA_AMT * ( WS_RCV_DATA.get(WS_I-1).WS_P_AMT / WS_TMP_AMT );
                bigD = new BigDecimal(WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT);
                WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            WS_TMP_AMT0 += WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT;
        }
        if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].I_FLG == 1) {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].F_FLG == 0) {
                WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT = WS_AVA_AMT - WS_TMP_AMT0;
            } else {
                WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT = WS_AVA_AMT * ( WS_RCV_DATA.get(WS_I-1).WS_I_AMT / WS_TMP_AMT );
                bigD = new BigDecimal(WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT);
                WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            WS_TMP_AMT0 += WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT;
        }
        if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].O_FLG == 1) {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG == 0 
                && LNCPAYSM.DATA_TXT.DATA[WS_J-1].F_FLG == 0) {
                WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT = WS_AVA_AMT - WS_TMP_AMT0;
            } else {
                WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT = WS_AVA_AMT * ( WS_RCV_DATA.get(WS_I-1).WS_O_AMT / WS_TMP_AMT );
                bigD = new BigDecimal(WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT);
                WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            WS_TMP_AMT0 += WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT;
        }
        if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].L_FLG == 1) {
            if (LNCPAYSM.DATA_TXT.DATA[WS_J-1].F_FLG == 0) {
                WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT = WS_AVA_AMT - WS_TMP_AMT0;
            } else {
                WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT = WS_AVA_AMT * ( WS_RCV_DATA.get(WS_I-1).WS_L_AMT / WS_TMP_AMT );
                bigD = new BigDecimal(WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT);
                WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            WS_TMP_AMT0 += WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (!LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT != 0) {
                WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
                WS_RCV_DATA.WS_D_F_AMT = WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
                WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            }
        }
        WS_AVA_AMT = 0;
        CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_I-1).WS_D_P_AMT);
        CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_I-1).WS_D_I_AMT);
        CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_I-1).WS_D_O_AMT);
        CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_I-1).WS_D_L_AMT);
        CEP.TRC(SCCGWA, WS_RCV_DATA.get(WS_I-1).WS_D_F_AMT);
    }
    public void B4S3_PAY_WS_O_P_AMT() throws IOException,SQLException,Exception {
        if (WS_RCV_DATA.get(WS_I-1).WS_P_AMT > 0 
            && WS_AVA_TYPE_AMT > 0 
            && WS_FST_TERM_FLG == 'N') {
            WS_FST_TERM_FLG = 'Y';
            WS_P_MIN_CNT = WS_I;
        }
        if (WS_RCV_DATA.get(WS_I-1).WS_P_AMT <= WS_AVA_TYPE_AMT) {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_P_AMT = WS_RCV_DATA.get(WS_I-1).WS_P_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT -= WS_RCV_DATA.get(WS_I-1).WS_P_AMT;
        } else {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_P_AMT = WS_AVA_TYPE_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT = 0;
        }
    }
    public void B4S3_PAY_WS_O_I_AMT() throws IOException,SQLException,Exception {
        if (WS_RCV_DATA.get(WS_I-1).WS_I_AMT > 0 
            && WS_AVA_TYPE_AMT > 0 
            && WS_FST_TERM_FLG == 'N') {
            WS_FST_TERM_FLG = 'Y';
            WS_I_MIN_CNT = WS_I;
        }
        if (WS_RCV_DATA.get(WS_I-1).WS_I_AMT <= WS_AVA_TYPE_AMT) {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_I_AMT = WS_RCV_DATA.get(WS_I-1).WS_I_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT -= WS_RCV_DATA.get(WS_I-1).WS_I_AMT;
        } else {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_I_AMT = WS_AVA_TYPE_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT = 0;
        }
    }
    public void B4S3_PAY_WS_O_O_AMT() throws IOException,SQLException,Exception {
        if (WS_RCV_DATA.get(WS_I-1).WS_O_AMT > 0 
            && WS_AVA_TYPE_AMT > 0 
            && WS_FST_TERM_FLG == 'N') {
            WS_FST_TERM_FLG = 'Y';
            WS_O_MIN_CNT = WS_I;
        }
        if (WS_RCV_DATA.get(WS_I-1).WS_O_AMT <= WS_AVA_TYPE_AMT) {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_O_AMT = WS_RCV_DATA.get(WS_I-1).WS_O_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT -= WS_RCV_DATA.get(WS_I-1).WS_O_AMT;
        } else {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_O_AMT = WS_AVA_TYPE_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT = 0;
        }
    }
    public void B4S3_PAY_WS_O_L_AMT() throws IOException,SQLException,Exception {
        if (WS_RCV_DATA.get(WS_I-1).WS_L_AMT > 0 
            && WS_AVA_TYPE_AMT > 0 
            && WS_FST_TERM_FLG == 'N') {
            WS_FST_TERM_FLG = 'Y';
            WS_L_MIN_CNT = WS_I;
        }
        if (WS_RCV_DATA.get(WS_I-1).WS_L_AMT <= WS_AVA_TYPE_AMT) {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_L_AMT = WS_RCV_DATA.get(WS_I-1).WS_L_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT -= WS_RCV_DATA.get(WS_I-1).WS_L_AMT;
        } else {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_L_AMT = WS_AVA_TYPE_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT = 0;
        }
    }
    public void B4S3_PAY_WS_O_F_AMT() throws IOException,SQLException,Exception {
        if (WS_RCV_DATA.get(WS_I-1).WS_F_AMT <= WS_AVA_TYPE_AMT) {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_F_AMT = WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT -= WS_RCV_DATA.get(WS_I-1).WS_F_AMT;
        } else {
            WS_RCV_DATA = WS_RCV_DATA.get(WS_I-1);
            WS_RCV_DATA.WS_D_F_AMT = WS_AVA_TYPE_AMT;
            WS_RCV_DATA.set(WS_I-1, WS_RCV_DATA);
            WS_AVA_TYPE_AMT = 0;
        }
    }
    public void B500_TOT_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TOT_D_AMTS);
        for (WS_I = 1; WS_I <= WS_REC_CNT 
            && WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE != ' '; WS_I += 1) {
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_O_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_L_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_F_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
            WS_TOT_D_AMTS.WS_TOT_D_P_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_D_P_AMT;
            WS_TOT_D_AMTS.WS_TOT_D_I_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_D_I_AMT;
            WS_TOT_D_AMTS.WS_TOT_D_O_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_D_O_AMT;
            WS_TOT_D_AMTS.WS_TOT_D_L_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_D_L_AMT;
            WS_TOT_D_AMTS.WS_TOT_D_F_AMT += WS_O_RCV_DATA.get(WS_I-1).WS_O_D_F_AMT;
        }
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_AMT);
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_P_AMT);
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_I_AMT);
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_O_AMT);
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_L_AMT);
        CEP.TRC(SCCGWA, WS_TOT_D_AMTS.WS_TOT_D_F_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT);
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT > 0) {
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT != WS_TOT_D_AMTS.WS_TOT_D_P_AMT) {
                CEP.TRC(SCCGWA, "AMDB-TOT-D-P-AMT NOT = WS-TOT-D-P-AMT");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH4, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = WS_TOT_D_AMTS.WS_TOT_D_P_AMT;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT > 0) {
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT != WS_TOT_D_AMTS.WS_TOT_D_I_AMT) {
                CEP.TRC(SCCGWA, "AMDB-TOT-D-I-AMT NOT = WS-TOT-D-I-AMT");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH5, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = WS_TOT_D_AMTS.WS_TOT_D_I_AMT;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT > 0) {
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT != WS_TOT_D_AMTS.WS_TOT_D_O_AMT) {
                CEP.TRC(SCCGWA, "AMDB-TOT-D-O-AMT NOT = WS-TOT-D-O-AMT");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH6, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = WS_TOT_D_AMTS.WS_TOT_D_O_AMT;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT > 0) {
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT != WS_TOT_D_AMTS.WS_TOT_D_L_AMT) {
                CEP.TRC(SCCGWA, "AMDB-TOT-D-L-AMT NOT = WS-TOT-D-L-AMT");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH7, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = WS_TOT_D_AMTS.WS_TOT_D_L_AMT;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT > 0) {
            if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT != WS_TOT_D_AMTS.WS_TOT_D_F_AMT) {
                CEP.TRC(SCCGWA, "AMDB-TOT-D-F-AMT NOT = WS-TOT-D-F-AMT");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCAMDB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT = WS_TOT_D_AMTS.WS_TOT_D_F_AMT;
        }
        if (LNCAMDB.COMM_DATA.MAX_PAY_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT == 0 
            && LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT == 0) {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_AMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_AMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_UDAMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_UDAMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_O_AMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_L_AMT;
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_F_AMT;
            for (WS_I = 1; WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE != ' ' 
                && WS_I <= WS_REC_CNT; WS_I += 1) {
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_P_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_I_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_O_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_L_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
                WS_O_RCV_DATA = WS_O_RCV_DATA.get(WS_I-1);
                WS_O_RCV_DATA.WS_O_D_F_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
                WS_O_RCV_DATA.set(WS_I-1, WS_O_RCV_DATA);
            }
        }
        LNCAMDB.COMM_DATA.TOT_AMTS.TOT_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_AMT + LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_AMT + LNCAMDB.COMM_DATA.TOT_AMTS.TOT_O_AMT + LNCAMDB.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCAMDB.COMM_DATA.TOT_AMTS.TOT_AMT += LNCAMDB.COMM_DATA.TOT_AMTS.TOT_F_AMT;
        }
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT + LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT += LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT;
        }
        if (LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT < LNCAMDB.COMM_DATA.MAX_PAY_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_E_EP_TOT_AMT;
            df = new DecimalFormat("0000000000000000.00");
            WS_MSG_AMT = df.format(LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT);
            WS_MSG_INFO = "" + WS_MSG_AMT;
            JIBS_tmp_int = WS_MSG_INFO.length();
            for (int i=0;i<18-JIBS_tmp_int;i++) WS_MSG_INFO = "0" + WS_MSG_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_P_UDAMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_I_UDAMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_AMTS.TOT_F_AMT);
        CEP.TRC(SCCGWA, "-");
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_UDAMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT);
        CEP.TRC(SCCGWA, LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT);
    }
    public void B300_UPDATE_DISB() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_REC_CNT 
            && WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE != ' '; WS_I += 1) {
            IBS.init(SCCGWA, LNCSAPT);
            LNCSAPT.COMM_DATA.FUNC_CODE = 'A';
            LNCSAPT.COMM_DATA.APT_REF = IBS.CLS2CPY(SCCGWA, WS_DISB_SEQ);
            LNCSAPT.COMM_DATA.TR_VAL_DTE = LNCAMDB.COMM_DATA.TR_VAL_DATE;
            LNCSAPT.COMM_DATA.CTA_NO = LNCAMDB.COMM_DATA.LN_AC;
            LNCSAPT.COMM_DATA.CTA_SEQ = LNCAMDB.COMM_DATA.SUF_NO;
            LNCSAPT.COMM_DATA.TYPE = WS_O_RCV_DATA.get(WS_I-1).WS_O_TYPE;
            LNCSAPT.COMM_DATA.TERM = WS_O_RCV_DATA.get(WS_I-1).WS_O_TERM;
            LNCSAPT.COMM_DATA.SUBS_PROJ_NO = WS_O_RCV_DATA.get(WS_I-1).WS_O_SUBS_PROJ_NO;
            LNCSAPT.COMM_DATA.VAL_DTE = WS_O_RCV_DATA.get(WS_I-1).WS_O_VAL_DT;
            LNCSAPT.COMM_DATA.DUE_DTE = WS_O_RCV_DATA.get(WS_I-1).WS_O_DUE_DT;
            LNCSAPT.COMM_DATA.TOT_AMTS.P_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_P_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.D_P_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_D_P_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.I_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_I_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.D_I_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_D_I_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.O_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_O_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.D_O_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_D_O_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.L_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_L_AMT;
            LNCSAPT.COMM_DATA.TOT_AMTS.D_L_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_D_L_AMT;
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                LNCSAPT.COMM_DATA.TOT_AMTS.BFC_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_D_F_AMT;
            } else {
                LNCSAPT.COMM_DATA.TOT_AMTS.BFC_AMT = WS_O_RCV_DATA.get(WS_I-1).WS_O_F_AMT;
            }
            S000_CALL_LNZSAPT();
            if (pgmRtn) return;
        }
    }
    public void T01_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCAMDB.COMM_DATA.LN_AC;
        if (LNCAMDB.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
        else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCAMDB.COMM_DATA.SUF_NO);
        LNRRCVD.KEY.SUBS_PROJ_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNRRCVD.DUE_DT = LNCAMDB.COMM_DATA.TR_VAL_DATE;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
        CEP.TRC(SCCGWA, LNRRCVD.REPY_STS);
        CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
        if (LNCAMDB.COMM_DATA.SUBS_FLG == 'Y') {
            CEP.TRC(SCCGWA, "1");
            LNTRCVD_BR.rp = new DBParm();
            LNTRCVD_BR.rp.TableName = "LNTRCVD";
            LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
            LNTRCVD_BR.rp.where = "SUBS_PROJ_NO > :LNRRCVD.KEY.SUBS_PROJ_NO "
                + "AND DUE_DT <= :LNRRCVD.DUE_DT "
                + "AND REPY_STS < > :LNRRCVD.REPY_STS";
            LNTRCVD_BR.rp.order = "DUE_DT";
            IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        } else {
            CEP.TRC(SCCGWA, "2");
            CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
            CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
            CEP.TRC(SCCGWA, LNRRCVD.REPY_STS);
            LNTRCVD_BR.rp = new DBParm();
            LNTRCVD_BR.rp.TableName = "LNTRCVD";
            LNTRCVD_BR.rp.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO "
                + "AND SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
                + "AND SUBS_PROJ_NO = :LNRRCVD.KEY.SUBS_PROJ_NO "
                + "AND DUE_DT <= :LNRRCVD.DUE_DT "
                + "AND REPY_STS < > :LNRRCVD.REPY_STS";
            LNTRCVD_BR.rp.order = "DUE_DT";
            IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        }
    }
    public void T01_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void T01_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPAYSM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-PAYSQ-MAINT", LNCPAYSM);
        if (LNCPAYSM.RC.RC_RTNCODE != 0) {
            LNCAMDB.RC.RC_APP = LNCPAYSM.RC.RC_APP;
            LNCAMDB.RC.RC_RTNCODE = LNCPAYSM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLCCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-LC-CMP", LNCLCCM);
        if (LNCLCCM.RC.RC_RTNCODE != 0) {
            LNCAMDB.RC.RC_APP = LNCLCCM.RC.RC_APP;
            LNCAMDB.RC.RC_RTNCODE = LNCLCCM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSAPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-AMT-APPORTION", LNCSAPT);
        if (LNCSAPT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSAPT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCAPRDM.RC.RC_RTNCODE+"", LNCAMDB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCAMDB.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCAMDB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCAMDB=");
            CEP.TRC(SCCGWA, LNCAMDB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
