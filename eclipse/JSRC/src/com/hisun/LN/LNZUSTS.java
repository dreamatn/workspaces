package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUSTS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTRPOFF_RD;
    DBParm LNTRCVD_RD;
    boolean pgmRtn = false;
    char LNZUSTS_FILLER1 = ' ';
    String WS_ERR_MSG = " ";
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    int WS_AC_DATE = 0;
    String WS_CTL_STSW = " ";
    LNZUSTS_WS_BUCKET_DETAIL WS_BUCKET_DETAIL = new LNZUSTS_WS_BUCKET_DETAIL();
    LNZUSTS_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZUSTS_WS_LOAN_CONT_AREA();
    char WS_RCVD_OVER_DUE_FLG = ' ';
    char WS_RCVD_REPY_STS_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRRPOFF LNRRPOFF = new LNRRPOFF();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCICUT LNCICUT = new LNCICUT();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    CICMACR CICMACR = new CICMACR();
    SCCGWA SCCGWA;
    SCCBATH SCCBATH;
    LNCUSTS LNCUSTS;
    public void MP(SCCGWA SCCGWA, LNCUSTS LNCUSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUSTS = LNCUSTS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            WS_AC_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        LNCUSTS.RC.RC_APP = "LN";
        LNCUSTS.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUSTS.COMM_DATA.LN_AC);
        if (LNCUSTS.COMM_DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INQ_LOAN_BUCKETS();
        if (pgmRtn) return;
        if (LNCUSTS.FUNC_CODE != 'E') {
            B200_UPD_LOAN_STATUS();
            if (pgmRtn) return;
        } else {
            B300_ENQ_LOAN_STATUS();
            if (pgmRtn) return;
        }
    }
    public void B100_INQ_LOAN_BUCKETS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        IBS.init(SCCGWA, LNCBALLM);
        IBS.init(SCCGWA, WS_LOAN_CONT_AREA);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        WS_BUCKET_DETAIL.WS_INT_BAL = WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
        WS_BUCKET_DETAIL.WS_ACCR_INT = WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[15-1].WS_LOAN_BAL;
        WS_BUCKET_DETAIL.WS_UND_AMT = WS_BUCKET_DETAIL.WS_INT_BAL;
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_INT_BAL);
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_ACCR_INT);
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
        if (LNCICTLM.REC_DATA.INT_CUT_DT < WS_AC_DATE 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            IBS.init(SCCGWA, LNCICUT);
            LNCICUT.COMM_DATA.FUNC_CODE = 'I';
            LNCICUT.COMM_DATA.LN_AC = LNCUSTS.COMM_DATA.LN_AC;
            LNCICUT.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
            LNCICUT.COMM_DATA.TYPE = 'I';
            LNCICUT.COMM_DATA.AMT = WS_BUCKET_DETAIL.WS_INT_BAL;
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
            WS_BUCKET_DETAIL.WS_ACCR_INT += LNCICUT.COMM_DATA.INT_AMT;
        }
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_ACCR_INT);
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_UND_AMT);
        WS_BUCKET_DETAIL.WS_DUE_AMT = WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[5-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[20-1].WS_LOAN_BAL;
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_DUE_AMT);
        WS_BUCKET_DETAIL.WS_OVD_AMT = WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[4-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[6-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES17.WS_LOAN_CONT[22-1].WS_LOAN_BAL;
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_OVD_AMT);
        IBS.init(SCCGWA, LNRRPOFF);
        LNRRPOFF.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        if (LNCUSTS.COMM_DATA.SUF_NO.trim().length() == 0) LNRRPOFF.KEY.SUB_CTA_NO = 0;
        else LNRRPOFF.KEY.SUB_CTA_NO = Integer.parseInt(LNCUSTS.COMM_DATA.SUF_NO);
        LNRRPOFF.KEY.AMT_TYP = 'I';
        T000_READ_LNTRPOFF_PROC();
        if (pgmRtn) return;
        WS_BUCKET_DETAIL.WS_WTF_INT_AMT = LNRRPOFF.I_REC_AMT;
        CEP.TRC(SCCGWA, WS_BUCKET_DETAIL.WS_WTF_INT_AMT);
    }
    public void B200_UPD_LOAN_STATUS() throws IOException,SQLException,Exception {
        B201_GET_RCVD_TERM();
        if (pgmRtn) return;
        if (WS_P_CUR_TERM == 0) {
            WS_P_CUR_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        }
        if (WS_IC_CUR_TERM == 0) {
            WS_IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        if (LNCUSTS.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUSTS.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_RCVD_OVER_DUE_FLG);
        if (WS_RCVD_OVER_DUE_FLG != 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
            LNCUSTS.COMM_DATA.LN_STATUS = 'N';
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
            LNCUSTS.COMM_DATA.LN_STATUS = 'O';
        }
        CEP.TRC(SCCGWA, WS_RCVD_REPY_STS_FLG);
        if (WS_BUCKET_DETAIL.WS_UND_AMT == 0 
            && WS_BUCKET_DETAIL.WS_ACCR_INT <= 0 
            && WS_BUCKET_DETAIL.WS_DUE_AMT == 0 
            && WS_BUCKET_DETAIL.WS_OVD_AMT == 0 
            && WS_BUCKET_DETAIL.WS_WTF_INT_AMT == 0 
            && WS_RCVD_REPY_STS_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 2 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(2 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
            LNCUSTS.COMM_DATA.LN_STATUS = 'D';
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0136080") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0136070")) {
            } else {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
                if (WS_BUCKET_DETAIL.WS_WTF_INT_AMT != 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.WRITE_OFF_INT_EXISTS;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_P_CUR_TERM != 0) {
            LNCICTLM.REC_DATA.P_CUR_TERM = WS_P_CUR_TERM;
        }
        if (WS_IC_CUR_TERM != 0) {
            LNCICTLM.REC_DATA.IC_CUR_TERM = WS_IC_CUR_TERM;
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CUR_TERM);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CUR_TERM);
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AFTER WRITE ICTLM-CTL-STSW(26:1) = ");
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(26 - 1, 26 + 1 - 1));
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        LNCCONTM.FUNC = '2';
        if (LNCUSTS.COMM_DATA.LN_STATUS == 'D') {
            LNCCONTM.REC_DATA.CTA_STS = '1';
        } else {
            if (LNCCONTM.REC_DATA.CTA_STS == '1') {
                LNCCONTM.REC_DATA.CTA_STS = '0';
            }
        }
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        CEP.TRC(SCCGWA, WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && !LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICMACR);
                CICMACR.FUNC = '2';
                CICMACR.DATA.ENTY_TYP = '1';
                CICMACR.DATA.AGR_NO = LNCUSTS.COMM_DATA.LN_AC;
                CICMACR.DATA.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_CIZMACR();
                if (pgmRtn) return;
            }
        } else {
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (!WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICMACR);
                CICMACR.FUNC = '2';
                CICMACR.DATA.ENTY_TYP = '1';
                CICMACR.DATA.AGR_NO = LNCUSTS.COMM_DATA.LN_AC;
                CICMACR.DATA.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_CIZMACR();
                if (pgmRtn) return;
            }
        }
    }
    public void B201_GET_RCVD_TERM() throws IOException,SQLException,Exception {
        WS_RCVD_OVER_DUE_FLG = 'N';
        WS_RCVD_REPY_STS_FLG = 'Y';
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
            if (LNCUSTS.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCUSTS.COMM_DATA.SUF_NO);
            LNRRCVD.KEY.AMT_TYP = 'C';
            LNRRCVD.REPY_STS = '2';
            T000_READ_LNTRCVD();
            if (pgmRtn) return;
            WS_IC_CUR_TERM = LNRRCVD.KEY.TERM;
        } else {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
            if (LNCUSTS.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
            else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCUSTS.COMM_DATA.SUF_NO);
            LNRRCVD.KEY.AMT_TYP = 'P';
            LNRRCVD.REPY_STS = '2';
            T000_READ_LNTRCVD();
            if (pgmRtn) return;
            WS_P_CUR_TERM = LNRRCVD.KEY.TERM;
            if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
                IBS.init(SCCGWA, LNRRCVD);
                LNRRCVD.KEY.CONTRACT_NO = LNCUSTS.COMM_DATA.LN_AC;
                if (LNCUSTS.COMM_DATA.SUF_NO.trim().length() == 0) LNRRCVD.KEY.SUB_CTA_NO = 0;
                else LNRRCVD.KEY.SUB_CTA_NO = Integer.parseInt(LNCUSTS.COMM_DATA.SUF_NO);
                LNRRCVD.KEY.AMT_TYP = 'I';
                LNRRCVD.REPY_STS = '2';
                T000_READ_LNTRCVD();
                if (pgmRtn) return;
                WS_IC_CUR_TERM = LNRRCVD.KEY.TERM;
            }
        }
    }
    public void B300_ENQ_LOAN_STATUS() throws IOException,SQLException,Exception {
        if (WS_BUCKET_DETAIL.WS_OVD_AMT == 0) {
            LNCUSTS.COMM_DATA.LN_STATUS = 'N';
        } else {
            LNCUSTS.COMM_DATA.LN_STATUS = 'O';
        }
        if (WS_BUCKET_DETAIL.WS_UND_AMT == 0 
            && WS_BUCKET_DETAIL.WS_ACCR_INT == 0 
            && WS_BUCKET_DETAIL.WS_DUE_AMT == 0 
            && WS_BUCKET_DETAIL.WS_OVD_AMT == 0) {
            LNCUSTS.COMM_DATA.LN_STATUS = 'D';
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRPOFF_PROC() throws IOException,SQLException,Exception {
        LNTRPOFF_RD = new DBParm();
        LNTRPOFF_RD.TableName = "LNTRPOFF";
        LNTRPOFF_RD.where = "CONTRACT_NO = :LNRRPOFF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRRPOFF.KEY.SUB_CTA_NO "
            + "AND AMT_TYP = :LNRRPOFF.KEY.AMT_TYP "
            + "AND I_REC_AMT < > 0";
        LNTRPOFF_RD.fst = true;
        LNTRPOFF_RD.order = "I_REC_AMT DESC";
        IBS.READ(SCCGWA, LNRRPOFF, this, LNTRPOFF_RD);
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND AMT_TYP = :LNRRCVD.KEY.AMT_TYP "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RCVD_REPY_STS_FLG = 'N';
            if (LNRRCVD.TERM_STS == '1') {
                WS_RCVD_OVER_DUE_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCUSTS.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCUSTS=");
            CEP.TRC(SCCGWA, LNCUSTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
