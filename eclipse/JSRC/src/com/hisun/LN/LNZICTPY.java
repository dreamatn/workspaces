package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICTPY {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTRATN_RD;
    DBParm LNTRATL_RD;
    DBParm LNTCTPY_RD;
    DBParm LNTPAIP_RD;
    brParm LNTPAIP_BR = new brParm();
    boolean pgmRtn = false;
    String K_FMT_ID = "LN809";
    char K_CKPD_INQ = '0';
    String WS_LOAN_AC = " ";
    int WS_VAL_DATE = 0;
    LNZICTPY_WS_MSGID WS_MSGID = new LNZICTPY_WS_MSGID();
    LNZICTPY_WS_O_PHS_DATA[] WS_O_PHS_DATA = new LNZICTPY_WS_O_PHS_DATA[5];
    short WS_IDX = 0;
    char WS_CONT_EXIST_FLG = ' ';
    char WS_ICTL_EXIST_FLG = ' ';
    char WS_AGRE_EXIST_FLG = ' ';
    char WS_APRD_EXIST_FLG = ' ';
    char WS_RATN_EXIST_FLG = ' ';
    char WS_RATP_EXIST_FLG = ' ';
    char WS_RATO_EXIST_FLG = ' ';
    char WS_RATL_EXIST_FLG = ' ';
    char WS_PAIP_EXIST_FLG = ' ';
    char WS_CTPY_EXIST_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATO = new LNRRATL();
    LNRRATL LNRRATP = new LNRRATL();
    LNRRATL LNRRATL = new LNRRATL();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRCTPY LNRCTPY = new LNRCTPY();
    CICCUST CICCUST = new CICCUST();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    int WS_O_PHS_NUM = 0;
    SCCGWA SCCGWA;
    LNCICTPY LNCICTPY;
    public LNZICTPY() {
        for (int i=0;i<5;i++) WS_O_PHS_DATA[i] = new LNZICTPY_WS_O_PHS_DATA();
    }
    public void MP(SCCGWA SCCGWA, LNCICTPY LNCICTPY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICTPY = LNCICTPY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICTPY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICTPY.RC.RC_MMO = "LN";
        LNCICTPY.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, LNCICTPY.RC.RC_MMO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_FUNC_MAIN();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCICTPY.O_INPUT_INFO.LOAN_ACNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_FUNC_MAIN() throws IOException,SQLException,Exception {
        B200_GET_CONT_INF();
        if (pgmRtn) return;
        B210_GET_ICTL_INF();
        if (pgmRtn) return;
        B220_GET_AGRE_INF();
        if (pgmRtn) return;
        B230_GET_APRD_INF();
        if (pgmRtn) return;
        B240_GET_RATN_INF();
        if (pgmRtn) return;
        B250_GET_RATO_INF();
        if (pgmRtn) return;
        B260_GET_RATP_INF();
        if (pgmRtn) return;
        B270_GET_RATL_INF();
        if (pgmRtn) return;
        B280_GET_PAIP_INF();
        if (pgmRtn) return;
        B290_GET_CTPY_INF();
        if (pgmRtn) return;
        R00_COMA_RECA();
        if (pgmRtn) return;
    }
    public void B200_GET_CONT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (WS_CONT_EXIST_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.CONT_NFND, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
    }
    public void B210_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        if (WS_ICTL_EXIST_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ICTL_NFND, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_GET_AGRE_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        if (WS_AGRE_EXIST_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRAGRE.PAPER_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B230_GET_APRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        if (WS_APRD_EXIST_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B240_GET_RATN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTRATN();
        if (pgmRtn) return;
    }
    public void B250_GET_RATO_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATO);
        LNRRATO.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        LNRRATO.KEY.OVD_KND = 'O';
        T000_READ_LNTRATO();
        if (pgmRtn) return;
    }
    public void B260_GET_RATP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATP);
        LNRRATP.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        LNRRATP.KEY.OVD_KND = 'P';
        T000_READ_LNTRATP();
        if (pgmRtn) return;
    }
    public void B270_GET_RATL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        LNRRATL.KEY.OVD_KND = 'L';
        T000_READ_LNTRATL();
        if (pgmRtn) return;
    }
    public void B280_GET_PAIP_INF() throws IOException,SQLException,Exception {
        T000_CHECK_GROUP_LNTPAIP();
        if (pgmRtn) return;
        if (WS_O_PHS_NUM >= 1) {
            WS_IDX = 1;
            T000_STARTBR_LNTPAIP();
            if (pgmRtn) return;
            while (WS_IDX <= WS_O_PHS_NUM 
                && WS_PAIP_EXIST_FLG != 'N') {
                IBS.init(SCCGWA, WS_O_PHS_DATA[WS_IDX-1]);
                WS_O_PHS_DATA[WS_IDX-1].WS_O_PHS_TERM = LNRPAIP.PHS_TOT_TERM;
                WS_O_PHS_DATA[WS_IDX-1].WS_O_PHS_PERD = LNRPAIP.PERD;
                WS_O_PHS_DATA[WS_IDX-1].WS_O_PHS_PERD_UN = LNRPAIP.PERD_UNIT;
                WS_O_PHS_DATA[WS_IDX-1].WS_O_PHS_AMT = LNRPAIP.PHS_PRIN_AMT;
                WS_O_PHS_DATA[WS_IDX-1].WS_O_PHS_INST = LNRPAIP.INST_MTH;
                WS_IDX += 1;
                T000_READNEXT_LNTPAIP();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTPAIP();
            if (pgmRtn) return;
        }
    }
    public void B290_GET_CTPY_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        T000_READ_LNTCTPY();
        if (pgmRtn) return;
        if (WS_CTPY_EXIST_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.CONT_NFND, LNCICTPY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        LNCICTPY.OUTPOUT_INFO.O_LOAN_AC = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        LNCICTPY.OUTPOUT_INFO.O_DOMI_BR = LNRCONT.DOMI_BR;
        LNCICTPY.OUTPOUT_INFO.O_BOOK_BR = LNRCONT.BOOK_BR;
        LNCICTPY.OUTPOUT_INFO.O_CI_NO = CICCUST.O_DATA.O_CI_NO;
        LNCICTPY.OUTPOUT_INFO.O_CI_CNM = CICCUST.O_DATA.O_CI_NM;
        LNCICTPY.OUTPOUT_INFO.O_PROD_CD = LNRCONT.PROD_CD;
        LNCICTPY.OUTPOUT_INFO.O_CCY = LNRCONT.CCY;
        LNCICTPY.OUTPOUT_INFO.O_LOAN_BAL = LNRCONT.LN_TOT_AMT;
        LNCICTPY.OUTPOUT_INFO.O_VAL_DT = LNRCONT.START_DATE;
        LNCICTPY.OUTPOUT_INFO.O_RAT_DT = LNRCONT.START_DATE;
        LNCICTPY.OUTPOUT_INFO.O_DUE_DT = LNRCONT.MAT_DATE;
        LNCICTPY.OUTPOUT_INFO.O_CTA_NO = LNRAGRE.PAPER_NO;
        LNCICTPY.OUTPOUT_INFO.O_DRAW_NO = LNRAGRE.DRAW_NO;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'A';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'P';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'N';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'X';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'W';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'M';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'C';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTPY.OUTPOUT_INFO.O_STS = 'D';
        }
        LNCICTPY.OUTPOUT_INFO.O_POUT_TYP = LNRCTPY.KEY.TR_TYP;
        LNCICTPY.OUTPOUT_INFO.O_POUT_FRM = LNRCTPY.PRIN_MODE;
        LNCICTPY.OUTPOUT_INFO.O_POUT_DT = LNRCTPY.PACK_DT;
        LNCICTPY.OUTPOUT_INFO.O_POUT_BAL = LNRCTPY.PRIN_BAL;
        LNCICTPY.OUTPOUT_INFO.O_POUT_INT = LNRCTPY.I_REC_AMT;
        LNCICTPY.OUTPOUT_INFO.O_POUT_LC = LNRCTPY.O_REC_AMT;
        LNCICTPY.OUTPOUT_INFO.O_POUT_LI = LNRCTPY.L_REC_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PACK_NO = LNRCTPY.KEY.BTH_PK;
        LNCICTPY.OUTPOUT_INFO.O_RAT_MTH = LNRRATN.RATE_FLG;
        LNCICTPY.OUTPOUT_INFO.O_INT_FLPERD_UN = LNRRATN.FLT_PERD_UNIT;
        LNCICTPY.OUTPOUT_INFO.O_INT_FLPERD = LNRRATN.FLT_PERD;
        LNCICTPY.OUTPOUT_INFO.O_FLOAT_FLG = LNRRATN.FIRST_FLT_FLG;
        LNCICTPY.OUTPOUT_INFO.O_FLOAT_DAY = LNRRATN.FLT_DAY;
        LNCICTPY.OUTPOUT_INFO.O_FLT_FIX_DAYS = LNRRATN.FST_FLT_DT;
        LNCICTPY.OUTPOUT_INFO.O_FLOAT_MTH = LNRRATN.VARIATION_METHOD;
        LNCICTPY.OUTPOUT_INFO.O_RATE_TYPE = LNRRATN.INT_RATE_TYPE1;
        LNCICTPY.OUTPOUT_INFO.O_RATE_PERD = LNRRATN.INT_RATE_CLAS1;
        LNCICTPY.OUTPOUT_INFO.O_RATE_VAR = LNRRATN.RATE_VARIATION1;
        LNCICTPY.OUTPOUT_INFO.O_RATE_PCT = LNRRATN.RATE_PECT1;
        LNCICTPY.OUTPOUT_INFO.O_RATE_INT = LNRRATN.BASE_RATE1;
        LNCICTPY.OUTPOUT_INFO.O_INT_RATE = LNRRATN.ALL_IN_RATE;
        LNCICTPY.OUTPOUT_INFO.O_PEN_RRAT = LNRRATO.INT_CHRG_MTH;
        LNCICTPY.OUTPOUT_INFO.O_PEN_TYPE = LNRRATO.VARIATION_METHOD;
        LNCICTPY.OUTPOUT_INFO.O_PEN_RATT = LNRRATO.RATE_TYPE;
        LNCICTPY.OUTPOUT_INFO.O_PEN_RATC = LNRRATO.RATE_CLAS;
        LNCICTPY.OUTPOUT_INFO.O_PEN_SPR = LNRRATO.DIF_IRAT_PNT;
        LNCICTPY.OUTPOUT_INFO.O_PEN_PCT = LNRRATO.DIF_IRAT_PER;
        LNCICTPY.OUTPOUT_INFO.O_PEN_IRAT = LNRRATO.EFF_RAT;
        LNCICTPY.OUTPOUT_INFO.O_CPND_RTY = LNRRATL.INT_CHRG_MTH;
        LNCICTPY.OUTPOUT_INFO.O_CPND_TYPE = LNRRATL.VARIATION_METHOD;
        LNCICTPY.OUTPOUT_INFO.O_CPNDRATT = LNRRATL.RATE_TYPE;
        LNCICTPY.OUTPOUT_INFO.O_CPNDRATC = LNRRATL.RATE_CLAS;
        LNCICTPY.OUTPOUT_INFO.O_CPND_SPR = LNRRATL.DIF_IRAT_PNT;
        LNCICTPY.OUTPOUT_INFO.O_CPND_PCT = LNRRATL.DIF_IRAT_PER;
        LNCICTPY.OUTPOUT_INFO.O_CPND_IRA = LNRRATL.EFF_RAT;
        LNCICTPY.OUTPOUT_INFO.O_ABUS_RAT = LNRRATP.INT_CHRG_MTH;
        LNCICTPY.OUTPOUT_INFO.O_ABUS_TYPE = LNRRATP.VARIATION_METHOD;
        LNCICTPY.OUTPOUT_INFO.O_ABUSRATT = LNRRATP.RATE_TYPE;
        LNCICTPY.OUTPOUT_INFO.O_ABUSRATC = LNRRATP.RATE_CLAS;
        LNCICTPY.OUTPOUT_INFO.O_ABUSSPR = LNRRATP.DIF_IRAT_PNT;
        LNCICTPY.OUTPOUT_INFO.O_ABUSPCT = LNRRATP.DIF_IRAT_PER;
        LNCICTPY.OUTPOUT_INFO.O_ABUSIRAT = LNRRATP.EFF_RAT;
        LNCICTPY.OUTPOUT_INFO.O_PAY_MTH_OUT = LNRAPRD.PAY_MTH;
        LNCICTPY.OUTPOUT_INFO.O_PAY_MTH = LNRAPRD.BPAY_MTH;
        if (WS_O_PHS_NUM > 1) {
            LNCICTPY.OUTPOUT_INFO.O_PHP_FLG = 'Y';
        } else {
            LNCICTPY.OUTPOUT_INFO.O_PHP_FLG = 'N';
        }
        LNCICTPY.OUTPOUT_INFO.O_INST_MTH = LNRAPRD.INST_MTH;
        LNCICTPY.OUTPOUT_INFO.O_CAL_PERD = LNRAPRD.CAL_PERD;
        LNCICTPY.OUTPOUT_INFO.O_CAL_PERD_UNIT = LNRAPRD.CAL_PERD_UNIT;
        LNCICTPY.OUTPOUT_INFO.O_OLC_PERD = LNRAPRD.OCAL_PERD;
        LNCICTPY.OUTPOUT_INFO.O_OLC_PERD_UNIT = LNRAPRD.OCAL_PERD_UNIT;
        LNCICTPY.OUTPOUT_INFO.O_FIRST_PAY = LNRAPRD.FST_PAY_FLG;
        LNCICTPY.OUTPOUT_INFO.O_PAY_DAY_TYP = LNRAPRD.PAY_DD_TYPE;
        LNCICTPY.OUTPOUT_INFO.O_PAY_DD_LTERM = LNRAPRD.PAY_DD_LTERM;
        LNCICTPY.OUTPOUT_INFO.O_PAY_DAY = LNRAPRD.PAY_DAY;
        LNCICTPY.OUTPOUT_INFO.O_PYP_PERD = LNRAPRD.PAYP_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PYP_PERD_UNIT = LNRAPRD.PAYP_PERD_UNIT;
        LNCICTPY.OUTPOUT_INFO.O_PYP_GRA = LNRAPRD.PRIN_DOG;
        LNCICTPY.OUTPOUT_INFO.O_PHS_NUM = (short) WS_O_PHS_NUM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_TERM1 = WS_O_PHS_DATA[1-1].WS_O_PHS_TERM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD1 = WS_O_PHS_DATA[1-1].WS_O_PHS_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD_UN1 = WS_O_PHS_DATA[1-1].WS_O_PHS_PERD_UN;
        LNCICTPY.OUTPOUT_INFO.O_PHS_AMT1 = WS_O_PHS_DATA[1-1].WS_O_PHS_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PHS_INST1 = WS_O_PHS_DATA[1-1].WS_O_PHS_INST;
        LNCICTPY.OUTPOUT_INFO.O_PHS_TERM2 = WS_O_PHS_DATA[2-1].WS_O_PHS_TERM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD2 = WS_O_PHS_DATA[2-1].WS_O_PHS_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD_UN2 = WS_O_PHS_DATA[2-1].WS_O_PHS_PERD_UN;
        LNCICTPY.OUTPOUT_INFO.O_PHS_AMT2 = WS_O_PHS_DATA[2-1].WS_O_PHS_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PHS_INST2 = WS_O_PHS_DATA[2-1].WS_O_PHS_INST;
        LNCICTPY.OUTPOUT_INFO.O_PHS_TERM3 = WS_O_PHS_DATA[3-1].WS_O_PHS_TERM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD3 = WS_O_PHS_DATA[3-1].WS_O_PHS_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD_UN3 = WS_O_PHS_DATA[3-1].WS_O_PHS_PERD_UN;
        LNCICTPY.OUTPOUT_INFO.O_PHS_AMT3 = WS_O_PHS_DATA[3-1].WS_O_PHS_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PHS_INST3 = WS_O_PHS_DATA[3-1].WS_O_PHS_INST;
        LNCICTPY.OUTPOUT_INFO.O_PHS_TERM4 = WS_O_PHS_DATA[4-1].WS_O_PHS_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD4 = WS_O_PHS_DATA[4-1].WS_O_PHS_TERM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD_UN4 = WS_O_PHS_DATA[4-1].WS_O_PHS_PERD_UN;
        LNCICTPY.OUTPOUT_INFO.O_PHS_AMT4 = WS_O_PHS_DATA[4-1].WS_O_PHS_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PHS_INST4 = WS_O_PHS_DATA[4-1].WS_O_PHS_INST;
        LNCICTPY.OUTPOUT_INFO.O_PHS_TERM5 = WS_O_PHS_DATA[5-1].WS_O_PHS_TERM;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD5 = WS_O_PHS_DATA[5-1].WS_O_PHS_PERD;
        LNCICTPY.OUTPOUT_INFO.O_PHS_PERD_UN5 = WS_O_PHS_DATA[5-1].WS_O_PHS_PERD_UN;
        LNCICTPY.OUTPOUT_INFO.O_PHS_AMT5 = WS_O_PHS_DATA[5-1].WS_O_PHS_AMT;
        LNCICTPY.OUTPOUT_INFO.O_PHS_INST5 = WS_O_PHS_DATA[5-1].WS_O_PHS_INST;
        LNCICTPY.OUTPOUT_INFO.O_ACCU_TYP = LNRAPRD.ACCRUAL_TYPE;
        LNCICTPY.OUTPOUT_INFO.O_INT_BASE = LNRAPRD.INT_DBAS_STD;
        LNCICTPY.OUTPOUT_INFO.O_GRA_DAYS = LNRAPRD.PRIN_DOG;
        LNCICTPY.OUTPOUT_INFO.O_GRATDUE_FLG = LNRAPRD.DOG_PSTD_FLG;
        LNCICTPY.OUTPOUT_INFO.O_GRA_MTH = LNRAPRD.PRIN_DOG_MTH;
        LNCICTPY.OUTPOUT_INFO.O_LGRA_MTH = LNRAPRD.INT_DOG_MTH;
        LNCICTPY.OUTPOUT_INFO.O_PRE_CHRH = LNRAPRD.PREP_CHG_RATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_ID;
        SCCFMT.DATA_PTR = LNCICTPY.OUTPOUT_INFO;
        SCCFMT.DATA_LEN = 1395;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        WS_CONT_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONT_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        WS_ICTL_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ICTL_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        WS_APRD_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_APRD_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        WS_AGRE_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGRE_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        WS_RATN_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATN_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTRATO() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATO.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATO.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        WS_RATO_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATO_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        WS_RATL_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATL_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTRATP() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATP.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATP.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        WS_RATP_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RATP_EXIST_FLG = 'Y';
        }
    }
    public void T000_READ_LNTCTPY() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.fst = true;
        LNTCTPY_RD.order = "DESC";
        IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
        WS_CTPY_EXIST_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CTPY_EXIST_FLG = 'Y';
        }
    }
    public void T000_CHECK_GROUP_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCICTPY.O_INPUT_INFO.LOAN_ACNO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.set = "WS-O-PHS-NUM=COUNT(*)";
        LNTPAIP_RD.where = "CONTRACT_NO = :LNRPAIP.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPAIP.KEY.SUB_CTA_NO";
        IBS.GROUP(SCCGWA, LNRPAIP, this, LNTPAIP_RD);
    }
    public void T000_STARTBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.where = "CONTRACT_NO = :LNRPAIP.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPAIP.KEY.SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
    }
    public void T000_READNEXT_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PAIP_EXIST_FLG = 'Y';
        } else {
            WS_PAIP_EXIST_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTPAIP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
