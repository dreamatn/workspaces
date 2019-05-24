package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class LNZSNACR {
    boolean pgmRtn = false;
    String K_FMT_CD1 = "LNP16";
    String K_HIS_RMKS = "SET/CANCEL NON-ACCRUAL STATUS";
    String K_HIS_CPB_NM = "LNCHSARU";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNZSNACR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSNACR_WS_TEMP_VARIABLE();
    LNZSNACR_WS_MSG_INFO WS_MSG_INFO = new LNZSNACR_WS_MSG_INFO();
    int WS_SUB_CTA_NO = 0;
    short WS_I = 0;
    double WS_N_INT = 0;
    double WS_O_INT = 0;
    double WS_OLC_INT = 0;
    double WS_LLC_INT = 0;
    int WS_GWA_AC_DATE = 0;
    double WS_REPORT_AMT = 0;
    int WS_TR_VAL_DTE = 0;
    char WS_EXIST_FWDH_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    CICCUST CICCUST = new CICCUST();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBKPO SCCBKPO = new SCCBKPO();
    SCCBINF SCCBINF = new SCCBINF();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCSCQIF LNCSCQIF = new LNCSCQIF();
    LNCIHDCK LNCIHDCK = new LNCIHDCK();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRCTPY LNRCTPY = new LNRCTPY();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCIPART LNCIPART = new LNCIPART();
    LNCOSARU LNCOSARU = new LNCOSARU();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCRFWDH LNCRFWDH = new LNCRFWDH();
    LNCHSARU LNCHSARU = new LNCHSARU();
    LNCHSARU LNCSARUO = new LNCHSARU();
    LNCHSARU LNCSARUN = new LNCHSARU();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNCBALLM LNCBALLM = new LNCBALLM();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCBATH SCCBATH;
    LNCSNACR LNCSNACR;
    public void MP(SCCGWA SCCGWA, LNCSNACR LNCSNACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSNACR = LNCSNACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSNACR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSNACR.RC.RC_APP = "LN";
        LNCSNACR.RC.RC_RTNCODE = 0;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            WS_GWA_AC_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_GWA_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B100_PRE_ARUS_PROC();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void S000_GET_OIC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSNACR.DATA.ACRU_STS != 'Y' 
            && LNCSNACR.DATA.ACRU_STS != 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSNACR.DATA.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSNACR.DATA.TR_VAL_DTE == 0) {
            LNCSNACR.DATA.TR_VAL_DTE = WS_GWA_AC_DATE;
        }
        CEP.TRC(SCCGWA, LNCSNACR.DATA.TR_VAL_DTE);
        CEP.TRC(SCCGWA, WS_GWA_AC_DATE);
        if (LNCSNACR.DATA.TR_VAL_DTE > WS_GWA_AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FWTXN_REJECT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_PRE_ARUS_PROC() throws IOException,SQLException,Exception {
        B110_GET_LOAN_INF();
        if (pgmRtn) return;
        B130_GET_ICTL_INF();
        if (pgmRtn) return;
        B120_GET_APRD_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSNACR.DATA.TR_VAL_DTE);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LAST_F_VAL_DATE);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
        if (LNCCONTM.REC_DATA.LAST_F_VAL_DATE > 0 
            && LNCSNACR.DATA.TR_VAL_DTE < LNCCONTM.REC_DATA.LAST_F_VAL_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.TX_DT_EARLY_FIN_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
    }
    public void B120_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B130_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if ((LNCICTLM.REC_DATA.CTL_STSW.substring(39 - 1, 39 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("0")) 
            || (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("0"))) {
            if (LNCSNACR.DATA.ACRU_STS == 'Y') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, LNRCTPY);
                LNRCTPY.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
                LNRCTPY.KEY.TR_TYP = '1';
                T000_READ_LNTCTPY();
                if (pgmRtn) return;
                if (( LNRCTPY.I_REC_AMT + LNRCTPY.L_REC_AMT + LNRCTPY.O_REC_AMT ) == 0) {
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B150_GET_CQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCQIF);
        LNCSCQIF.CTA_NO = LNCSNACR.DATA.CTA_NO;
        S000_CALL_LNZSCQIF();
        if (pgmRtn) return;
    }
    public void B170_CHECK_FWDH() throws IOException,SQLException,Exception {
        WS_EXIST_FWDH_FLAG = 'N';
        B300_STARTBR_LNTFWDH();
        if (pgmRtn) return;
        B300_READNEXT_LNTFWDH();
        if (pgmRtn) return;
        while (LNCRFWDH.RETURN_INFO != 'E' 
            && WS_EXIST_FWDH_FLAG != 'Y') {
            if (LNRFWDH.TRAN_STS == '0' 
                && LNRFWDH.KEY.TR_VAL_DATE > WS_GWA_AC_DATE) {
                WS_EXIST_FWDH_FLAG = 'Y';
            }
            B300_READNEXT_LNTFWDH();
            if (pgmRtn) return;
        }
        B300_ENDBR_LNTFWDH();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSNACR.DATA.ACRU_STS);
        if (LNCSNACR.DATA.ACRU_STS == 'Y') {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 11 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(11 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            WS_TR_VAL_DTE = LNCSNACR.DATA.TR_VAL_DTE;
        } else {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 11 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(11 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            WS_TR_VAL_DTE = 0;
            IBS.init(SCCGWA, LNCBALLM);
            LNCBALLM.FUNC = '4';
            LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            LNCBALLM.FUNC = '2';
        }
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '4';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        LNCLOANM.REC_DATA.TRANS_NACCR_DATE = WS_TR_VAL_DTE;
        LNCLOANM.FUNC = '2';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        B210_CTNR_PROCESS();
        if (pgmRtn) return;
    }
    public void B210_CTNR_PROCESS() throws IOException,SQLException,Exception {
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNRCTPY);
            LNRCTPY.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
            LNRCTPY.KEY.TR_TYP = '1';
            T000_READ_LNTCTPY();
            if (pgmRtn) return;
            WS_N_INT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
            WS_O_INT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
            WS_OLC_INT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        } else {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("0")) {
                B210_INQ_NOR_INT();
                if (pgmRtn) return;
                WS_N_INT = LNCCNEX.COMM_DATA.INQ_AMT;
                if (WS_N_INT < 0) {
                    WS_N_INT = 0;
                }
                B210_INQ_OVD_INT();
                if (pgmRtn) return;
                WS_O_INT = LNCCNEX.COMM_DATA.INQ_AMT;
                B210_INQ_POST_P_OLC();
                if (pgmRtn) return;
                WS_OLC_INT = LNCCNEX.COMM_DATA.INQ_AMT;
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.init(SCCGWA, LNRCTPY);
                    LNRCTPY.KEY.CONTRACT_NO = LNCSNACR.DATA.CTA_NO;
                    LNRCTPY.KEY.TR_TYP = '2';
                    T000_READ_LNTCTPY();
                    if (pgmRtn) return;
                    WS_N_INT = WS_N_INT - ( LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT );
                    WS_O_INT = WS_O_INT - ( LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT );
                    WS_OLC_INT = WS_OLC_INT - ( LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT );
                }
            }
        }
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "TONOACR";
        if (LNCSNACR.DATA.ACRU_STS == 'Y') {
            LNCCNEV.COMM_DATA.ACM_EVENT = "PN";
        } else {
            LNCCNEV.COMM_DATA.ACM_EVENT = "NP";
        }
        LNCCNEV.COMM_DATA.LN_AC = LNCSNACR.DATA.CTA_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + WS_SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCSNACR.DATA.TR_VAL_DTE;
        LNCCNEV.COMM_DATA.I_AMT = WS_N_INT + WS_O_INT;
        LNCCNEV.COMM_DATA.O_AMT = WS_OLC_INT;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
