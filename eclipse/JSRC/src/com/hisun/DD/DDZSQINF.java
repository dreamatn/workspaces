package com.hisun.DD;

import com.hisun.DP.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQINF {
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    DDCOINFQ_CHQ_DETL CHQ_DETL;
    DBParm DDTVCH_RD;
    DBParm DDTMSTR_RD;
    DBParm DDTADMN_RD;
    brParm DDTDDTD_BR = new brParm();
    brParm DDTCHQ_BR = new brParm();
    DBParm DCTCIDEP_RD;
    DBParm DDTINTB_RD;
    DBParm DCTIAMST_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTINF_RD;
    DBParm DDTCCY_RD;
    DBParm DCTIACCY_RD;
    DBParm DDTDREG_RD;
    boolean pgmRtn = false;
    String K_FMT_STATIC = "DD804";
    String K_FMT_DETAIL = "DD805";
    String K_FMT_INT_CON1 = "DD806";
    String K_FMT_INT_CON2 = "DD819";
    String K_FMT_STP = "DD808";
    String K_FMT_HLD = "DD809";
    String K_FMT_STMT = "DD811";
    String K_FMT_CHQ = "DD812";
    String K_FMT_P_INF = "DD815";
    String K_FMT_C_INF = "DD816";
    String K_PDRAT_TYPE = "PDRAT";
    String WS_ERR_MSG = " ";
    String WS_CCY = " ";
    int WS_DOM_BR = 0;
    String WS_PROD_CODE = " ";
    short WS_BASIC_DAYS = 0;
    short WS_I = 0;
    short WS_J = 0;
    char WS_DEP_POST_PERIOD = ' ';
    short WS_DEP_POST_DATE = 0;
    char WS_OD_POST_PERIOD = ' ';
    short WS_OD_POST_DATE = 0;
    String WS_CHQ_NO = " ";
    String WS_AC_ENM = " ";
    String WS_AC_CNM = " ";
    short WS_CHQ_CNT = 0;
    short WS_CNT = 0;
    short WS_NOR_CNT = 0;
    short WS_PAID_CNT = 0;
    short WS_STOP_CNT = 0;
    short WS_DISCARD_CNT = 0;
    String WS_CHQ_STS = " ";
    char WS_ADP_STS = ' ';
    double WS_ADP_BAL = 0;
    double WS_DT_CAMT = 0;
    double WS_ADP_RATE = 0;
    char WS_PRT_FLG = ' ';
    int WS_PRT_DATE = 0;
    char WS_C_LNK_TYP = ' ';
    DDZSQINF_WS_CCY_KEY WS_CCY_KEY = new DDZSQINF_WS_CCY_KEY();
    DDZSQINF_WS_RATE_KEY WS_RATE_KEY = new DDZSQINF_WS_RATE_KEY();
    DDZSQINF_WS_DATA WS_DATA = new DDZSQINF_WS_DATA();
    double WS_EXP_INT = 0;
    DDZSQINF_WS_DATA_TOTAL WS_DATA_TOTAL = new DDZSQINF_WS_DATA_TOTAL();
    DDZSQINF_WS_PERS_DATA WS_PERS_DATA = new DDZSQINF_WS_PERS_DATA();
    DDZSQINF_OCINF_TZN_INF OCINF_TZN_INF = new DDZSQINF_OCINF_TZN_INF();
    String WS_SQINF_AC_NO = " ";
    String WS_CARD_AC = " ";
    String WS_VS_AC = " ";
    char WS_DEP_PRD_RATE_FLAG = ' ';
    char WS_OD_PRD_RATE_FLAG = ' ';
    char WS_UOD_PRD_RATE_FLAG = ' ';
    char WS_CHQ_NO_FND_FLAG = ' ';
    char WS_LIST_STOP_FLAG = ' ';
    char WS_LIST_HLD_FLAG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_MSTR_FLG = ' ';
    char WS_CHQ_FLG = ' ';
    char WS_DTD_FLG = ' ';
    char WS_MSTR_OPEN_FLG = ' ';
    char WS_ADMN_READ_FLG = ' ';
    DDCOINFI DDCOINFI = new DDCOINFI();
    DDCOINFS DDCOINFS = new DDCOINFS();
    DDCOINFD DDCOINFD = new DDCOINFD();
    DDCOINFQ DDCOINFQ = new DDCOINFQ();
    DDCOINFP DDCOINFP = new DDCOINFP();
    DDCOINFC DDCOINFC = new DDCOINFC();
    DDCOSTMT DDCOSTMT = new DDCOSTMT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DDCOPINF DDCOPINF = new DDCOPINF();
    DDCOCINF DDCOCINF = new DDCOCINF();
    DDCSRATE DDCSRATE = new DDCSRATE();
    DDRADMN DDRADMN = new DDRADMN();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    CICQACRL CICQACRL = new CICQACRL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    CICSSTC CICSSTC = new CICSSTC();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRDDTD DDRDDTD = new DDRDDTD();
    TDRSMST TDRSMST = new TDRSMST();
    DDRMST DDRMST = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRDREG DDRDREG = new DDRDREG();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRINTB DDRINTB = new DDRINTB();
    DDCIINTB DDCIINTB = new DDCIINTB();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIBASD DDCIBASD = new DDCIBASD();
    DDCUQAC DDCUQAC = new DDCUQAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    DCRCIDEP DCRCIDEP = new DCRCIDEP();
    DCCICCYR DCCICCYR = new DCCICCYR();
    DDCUPINT DDCUPINT = new DDCUPINT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDRVCH DDRVCH = new DDRVCH();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DDCUPRAT DDCUPRAT = new DDCUPRAT();
    SCCGWA SCCGWA;
    DDCSQINF DDCSQINF;
    public void MP(SCCGWA SCCGWA, DDCSQINF DDCSQINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQINF = DDCSQINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "********** INPUT DATA:");
        CEP.TRC(SCCGWA, DDCSQINF.FUNC);
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, DDCSQINF.CARD_NO);
        CEP.TRC(SCCGWA, DDCSQINF.CCY);
        CEP.TRC(SCCGWA, DDCSQINF.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSQINF.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQINF.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQINF.STOP_TYPE);
        CEP.TRC(SCCGWA, DDCSQINF.TX_DATE);
        CEP.TRC(SCCGWA, DDCSQINF.JRN_NO);
        CEP.TRC(SCCGWA, DDCSQINF.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQINF.AMT);
        CEP.TRC(SCCGWA, DDCSQINF.FROM_DATE);
        CEP.TRC(SCCGWA, DDCSQINF.TO_DATE);
        CEP.TRC(SCCGWA, "**********************");
        if (DDCSQINF.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQINF.FROM_DATE == 0) {
            DDCSQINF.FROM_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDCSQINF.TO_DATE == 0) {
            DDCSQINF.TO_DATE = 99991231;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQINF.FUNC);
        if (DDCSQINF.FUNC == '0') {
            B019_GET_AC_INF_PROC();
            if (pgmRtn) return;
            B021_CHK_AC_STS_PROC();
            if (pgmRtn) return;
            B001_INQ_ITG_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == '1') {
            B003_INQ_STIC_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == '2') {
            B005_INQ_DET_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == '3') {
            B007_INQ_COND_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == '4') {
        } else if (DDCSQINF.FUNC == '5') {
        } else if (DDCSQINF.FUNC == '6') {
        } else if (DDCSQINF.FUNC == '7') {
        } else if (DDCSQINF.FUNC == '8') {
            B017_INQ_STMT_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == '9') {
            B018_INQ_CHQ_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == 'A') {
        } else if (DDCSQINF.FUNC == 'P') {
            B023_INQ_PERS_INF_PROC();
            if (pgmRtn) return;
        } else if (DDCSQINF.FUNC == 'C') {
            B025_INQ_CORP_INF_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSQINF.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_INQ_ITG_PROC() throws IOException,SQLException,Exception {
        R000_GET_CI_INF();
        if (pgmRtn) return;
        R000_MPAGE_OUTPUT_START();
        if (pgmRtn) return;
        B001_01_OUTPUT_AC();
        if (pgmRtn) return;
        B001_02_STARTBR_DDTCCY();
        if (pgmRtn) return;
        B001_03_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_CCY_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (DDRCCY.CCY.trim().length() > 0) {
                B001_05_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            B001_03_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        B001_06_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B001_01_GET_CCY_BAL() throws IOException,SQLException,Exception {
    }
    public void B001_02_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B001_03_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
    }
    public void B001_06_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B001_04_GET_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIINTB);
        DDCIINTB.TX_TYPE = 'I';
        DDCIINTB.DATA.KEY.AC_NO = DDCSQINF.AC_NO;
        DDCIINTB.DATA.KEY.CCY = DDRCCY.CCY;
    }
    public void B001_05_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_CURRENY = DDRCCY.CCY;
        WS_DATA.WS_CCY_STS = DDRCCY.STS;
        WS_DATA.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_DATA.WS_L_BAL = DDRCCY.CURR_BAL;
        WS_DATA.WS_H_BAL = DDRCCY.HOLD_BAL;
        WS_DATA.WS_A_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        if (WS_DATA.WS_A_BAL < 0) {
            WS_DATA.WS_A_BAL = 0;
        }
        CEP.TRC(SCCGWA, WS_DATA.WS_H_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_A_BAL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DATA);
        SCCMPAG.DATA_LEN = 133;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B001_01_OUTPUT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA_TOTAL);
        WS_DATA_TOTAL.WS_AC_NO = DDCSQINF.AC_NO;
        WS_DATA_TOTAL.WS_AC_ENMO = WS_AC_ENM;
        WS_DATA_TOTAL.WS_AC_CNMO = WS_AC_CNM;
        CEP.TRC(SCCGWA, WS_DATA_TOTAL);
        CEP.TRC(SCCGWA, WS_DATA_TOTAL.WS_AC_ENMO);
        CEP.TRC(SCCGWA, WS_DATA_TOTAL.WS_AC_CNMO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DATA_TOTAL);
        SCCMPAG.DATA_LEN = 539;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B003_INQ_STIC_PROC() throws IOException,SQLException,Exception {
        B003_01_GET_CI_INF();
        if (pgmRtn) return;
        B003_05_GET_AC_INF();
        if (pgmRtn) return;
        B003_07_GET_PRD_INF();
        if (pgmRtn) return;
        B003_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B003_01_GET_CI_INF() throws IOException,SQLException,Exception {
        R000_GET_CI_INF();
        if (pgmRtn) return;
    }
    public void B003_03_GET_CCY_BAL() throws IOException,SQLException,Exception {
    }
    public void B003_05_GET_AC_INF() throws IOException,SQLException,Exception {
        R000_GET_AC_INF();
        if (pgmRtn) return;
        R000_GET_AC_PAY_INF();
        if (pgmRtn) return;
    }
    public void B003_07_GET_PRD_INF() throws IOException,SQLException,Exception {
        R000_GET_PRD_INF();
        if (pgmRtn) return;
    }
    public void B018_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CHQ;
        SCCFMT.DATA_PTR = DDCOINFQ;
        SCCFMT.DATA_LEN = 98938;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B003_FMT_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_STIC_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_STATIC;
        SCCFMT.DATA_PTR = DDCOINFS;
        SCCFMT.DATA_LEN = 2349;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B005_INQ_DET_PROC() throws IOException,SQLException,Exception {
        B005_01_GET_CI_INF();
        if (pgmRtn) return;
        B005_03_GET_AC_INF();
        if (pgmRtn) return;
        B005_05_GET_PRD_INF();
        if (pgmRtn) return;
        B005_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B005_01_GET_CI_INF() throws IOException,SQLException,Exception {
        R000_GET_CI_INF();
        if (pgmRtn) return;
    }
    public void B005_03_GET_AC_INF() throws IOException,SQLException,Exception {
        R000_GET_AC_INF();
        if (pgmRtn) return;
    }
    public void B005_05_GET_PRD_INF() throws IOException,SQLException,Exception {
        R000_GET_PRD_INF();
        if (pgmRtn) return;
    }
    public void B005_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFD);
        IBS.init(SCCGWA, SCCFMT);
        DDCOINFD.AC_NO = DDCSQINF.AC_NO;
        DDCOINFD.ENAME = WS_AC_ENM;
        DDCOINFD.CNAME = WS_AC_CNM;
        DDCOINFD.AC_STS = DDRMST.AC_STS;
        DDCOINFD.OPEN_DATE = DDRMST.OPEN_DATE;
        DDCOINFD.PROD_CODE = WS_PROD_CODE;
        DDCOINFD.PROD_CNM = DDVMPRD.VAL.CHN_NAME;
        SCCFMT.FMTID = K_FMT_DETAIL;
        SCCFMT.DATA_PTR = DDCOINFD;
        SCCFMT.DATA_LEN = 695;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B007_INQ_COND_PROC() throws IOException,SQLException,Exception {
        B007_01_GET_AC_INF();
        if (pgmRtn) return;
        B007_03_GET_AC_RATE();
        if (pgmRtn) return;
        B007_05_GET_PROD_INF();
        if (pgmRtn) return;
        B007_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B007_01_GET_AC_INF() throws IOException,SQLException,Exception {
        R000_GET_AC_INF();
        if (pgmRtn) return;
    }
    public void B007_03_GET_AC_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDCSQINF.AC_NO;
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
    }
    public void B007_05_GET_PROD_INF() throws IOException,SQLException,Exception {
        R000_GET_PRD_INF();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'N') {
            IBS.init(SCCGWA, DDVMRAT);
            WS_RATE_KEY.WS_RATE_PRD_CD = DDVMPRD.KEY.PARM_CODE;
            WS_RATE_KEY.WS_RATE_CCY = DDCSQINF.CCY;
            WS_RATE_KEY.WS_RATE_CCY_TYPE = DDCSQINF.CCY_TYPE;
            R000_GET_PROD_RATE();
            if (pgmRtn) return;
        }
    }
    public void B007_FMT_OUTPUT() throws IOException,SQLException,Exception {
        if (WS_MSTR_FLG == 'F') {
            R000_TRANS_AC_RATE_OUTPUT();
            if (pgmRtn) return;
        } else {
            R000_TRANS_PRD_RATE_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B017_INQ_STMT_PROC() throws IOException,SQLException,Exception {
        R000_GET_CI_INF();
        if (pgmRtn) return;
        B017_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B018_INQ_CHQ_PROC() throws IOException,SQLException,Exception {
        R000_GET_AC_INF();
        if (pgmRtn) return;
        R000_GET_AC_CHQ();
        if (pgmRtn) return;
        B018_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B017_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOSTMT);
        DDCOSTMT.AC_NO = DDCSQINF.AC_NO;
        DDCOSTMT.ENAME = WS_AC_ENM;
        DDCOSTMT.CNAME = WS_AC_CNM;
        SCCFMT.FMTID = K_FMT_STMT;
        SCCFMT.DATA_PTR = DDCOSTMT;
        SCCFMT.DATA_LEN = 530;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B019_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQINF.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
    }
    public void B021_CHK_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_INQ_PERS_INF_PROC() throws IOException,SQLException,Exception {
        B023_01_CHK_AC();
        if (pgmRtn) return;
        B023_03_GET_AC_INF();
        if (pgmRtn) return;
        B023_05_GET_CI_INF();
        if (pgmRtn) return;
        B023_07_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B023_01_CHK_AC() throws IOException,SQLException,Exception {
        B019_GET_AC_INF_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE != 'A' 
            && DDRMST.AC_TYPE != 'B' 
            && DDRMST.AC_TYPE != 'G' 
            && DDRMST.AC_TYPE != 'H') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYPE_M_ST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_03_GET_AC_INF() throws IOException,SQLException,Exception {
        WS_PERS_DATA.WS_P_AC_TYPE = DDRMST.AC_TYPE;
        WS_PERS_DATA.WS_P_AC_STS = DDRMST.AC_STS;
        WS_PERS_DATA.WS_P_CI_TYPE = DDRMST.CI_TYP;
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSQINF.AC_NO;
        DDRCCY.CCY = DDCSQINF.CCY;
        DDRCCY.CCY_TYPE = DDCSQINF.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "HHHHH");
            WS_PERS_DATA.WS_P_AC_STA = 'H';
            WS_PERS_DATA.WS_P_LHLD_FLG = 'Y';
        } else {
            WS_PERS_DATA.WS_P_LHLD_FLG = 'N';
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "FFFFF");
                WS_PERS_DATA.WS_P_AC_STA = 'F';
            } else {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.TRC(SCCGWA, "DDDDD");
                    WS_PERS_DATA.WS_P_AC_STA = 'D';
                } else {
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.TRC(SCCGWA, "CCCCC");
                        WS_PERS_DATA.WS_P_AC_STA = 'C';
                    } else {
                        CEP.TRC(SCCGWA, "NNNNN");
                        WS_PERS_DATA.WS_P_AC_STA = 'N';
                    }
                }
            }
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_SLP_FLG = 'Y';
            R000_GET_AC_DREG();
            if (pgmRtn) return;
            WS_PERS_DATA.WS_P_SLP_DT = DDRDREG.D_DT;
        } else {
            WS_PERS_DATA.WS_P_SLP_FLG = 'N';
        }
        WS_PERS_DATA.WS_P_OD_FLG = 'N';
        WS_PERS_DATA.WS_P_LN_FLG = 'N';
        if (DDCSQINF.CARD_NO.trim().length() > 0) {
            R000_GET_CARD_AC();
            if (pgmRtn) return;
        }
        R000_GET_AC_PAY_INF();
        if (pgmRtn) return;
        R000_INQ_AC_CHQ();
        if (pgmRtn) return;
        WS_PERS_DATA.WS_P_PAY_MTH = DDRVCH.PAY_TYPE;
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        WS_PERS_DATA.WS_P_OPEN_DATE = DDRMST.OPEN_DATE;
        WS_PROD_CODE = DDRMST.PROD_CODE;
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_TYPE);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_CI_TYPE);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STS);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_PAY_MTH);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_OD_FLG);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_LN_FLG);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_OPEN_DATE);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_CHQ_U_IND);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_SLP_FLG);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_SLP_DT);
        CEP.TRC(SCCGWA, DDCSQINF.CARD_NO);
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, DDCOPINF.LHLD_FLG);
    }
    public void B023_05_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSQINF.AC_NO;
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (DDRMST.CI_TYP == '1') {
            if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
            JIBS_tmp_int = CICACCU.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
            if (CICACCU.DATA.STSW.substring(0, 1).equalsIgnoreCase("3")) {
                WS_PERS_DATA.WS_P_AC_CNM2 = CICACCU.DATA.CI_ENM;
                WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.CI_CNM;
            } else {
                if (CICACCU.DATA.AC_ENM.trim().length() == 0) {
                    WS_PERS_DATA.WS_P_AC_CNM2 = CICACCU.DATA.CI_ENM;
                } else {
                    WS_PERS_DATA.WS_P_AC_CNM2 = CICACCU.DATA.AC_ENM;
                }
                if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                    WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.CI_CNM;
                } else {
                    WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.AC_CNM;
                }
            }
        } else {
            WS_PERS_DATA.WS_P_AC_CNM2 = CICACCU.DATA.AC_ENM;
            WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.AC_CNM;
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_CNM);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_CNM2);
        WS_PERS_DATA.WS_P_SVR_LVL = CICACCU.DATA.SVRLVL;
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_SVR_LVL);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_CI_TYPE);
        if (WS_PERS_DATA.WS_P_CI_TYPE == ' ') {
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
            WS_PERS_DATA.WS_P_CI_TYPE = CICACCU.DATA.CI_TYP;
        }
        CEP.TRC(SCCGWA, WS_PRT_FLG);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_PRT_PERM);
        CEP.TRC(SCCGWA, WS_PRT_DATE);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_SCS_ADRS);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_ADR_NM);
    }
    public void B023_07_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPINF);
        IBS.init(SCCGWA, SCCFMT);
        DDCOPINF.CARD_NO = DDCSQINF.CARD_NO;
        DDCOPINF.C_LNK_TYP = WS_C_LNK_TYP;
        if (DDCSQINF.CARD_NO.equalsIgnoreCase(DCCPACTY.INPUT.AC)) {
            DDCOPINF.AC_NO = DDCSQINF.AC_NO;
        } else {
            DDCOPINF.AC_NO = DCCPACTY.INPUT.AC;
        }
        DDCOPINF.AC_CNM = WS_PERS_DATA.WS_P_AC_CNM;
        DDCOPINF.AC_CNM2 = WS_PERS_DATA.WS_P_AC_CNM2;
        DDCOPINF.AC_TYPE = WS_PERS_DATA.WS_P_AC_TYPE;
        DDCOPINF.CI_TYPE = WS_PERS_DATA.WS_P_CI_TYPE;
        DDCOPINF.AC_STS = WS_PERS_DATA.WS_P_AC_STS;
        DDCOPINF.AC_STA = WS_PERS_DATA.WS_P_AC_STA;
        DDCOPINF.PAY_TYP = WS_PERS_DATA.WS_P_PAY_MTH;
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_PAY_MTH);
        CEP.TRC(SCCGWA, DDCOPINF.PAY_TYP);
        DDCOPINF.PRT_PERM = WS_PERS_DATA.WS_P_PRT_PERM;
        DDCOPINF.SCS_ADRS = WS_PERS_DATA.WS_P_SCS_ADRS;
        DDCOPINF.ADR_NM = WS_PERS_DATA.WS_P_ADR_NM;
        DDCOPINF.OD_FLG = WS_PERS_DATA.WS_P_OD_FLG;
        DDCOPINF.LN_FLG = WS_PERS_DATA.WS_P_LN_FLG;
        DDCOPINF.OPEN_DATE = WS_PERS_DATA.WS_P_OPEN_DATE;
        DDCOPINF.CHQ_U_IND = WS_PERS_DATA.WS_P_CHQ_U_IND;
        DDCOPINF.SVR_LVL = WS_PERS_DATA.WS_P_SVR_LVL;
        DDCOPINF.SLP_FLG = WS_PERS_DATA.WS_P_SLP_FLG;
        DDCOPINF.SLP_DT = WS_PERS_DATA.WS_P_SLP_DT;
        DDCOPINF.LHLD_FLG = WS_PERS_DATA.WS_P_LHLD_FLG;
        SCCFMT.FMTID = K_FMT_P_INF;
        SCCFMT.DATA_PTR = DDCOPINF;
        SCCFMT.DATA_LEN = 1042;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B025_INQ_CORP_INF_PROC() throws IOException,SQLException,Exception {
        B025_01_GET_MST_INF();
        if (pgmRtn) return;
        R000_GET_CI_INF();
        if (pgmRtn) return;
        B025_03_GET_AC_CCY();
        if (pgmRtn) return;
        B025_05_GET_AC_RAT();
        if (pgmRtn) return;
        R000_GET_C_AC_STS();
        if (pgmRtn) return;
        if (DDRMST.AC_STS == 'C' 
            || DDRMST.AC_STS == 'M') {
        } else {
            B025_INQ_AC_INT_PROC();
            if (pgmRtn) return;
        }
        B025_07_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B025_01_GET_MST_INF() throws IOException,SQLException,Exception {
        B019_GET_AC_INF_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRINF);
        DDRINF.KEY.CUS_AC = DDCSQINF.AC_NO;
        T000_READ_DDTINF();
        if (pgmRtn) return;
    }
    public void B025_03_GET_AC_CCY() throws IOException,SQLException,Exception {
        B000_GET_AC_CCY();
        if (pgmRtn) return;
        R000_GET_AC_DREG();
        if (pgmRtn) return;
    }
    public void B025_05_GET_AC_RAT() throws IOException,SQLException,Exception {
        R000_GET_AC_RAT();
        if (pgmRtn) return;
    }
    public void B025_INQ_AC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        CEP.TRC(SCCGWA, DDRINTB.KEY.AC);
        CEP.TRC(SCCGWA, DDRINTB.KEY.TYPE);
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        WS_EXP_INT = DDRINTB.DEP_ACCU_INT + DDRINTB.DEP_ADJ_INT;
        CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
        CEP.TRC(SCCGWA, DDRINTB.DEP_ADJ_INT);
        CEP.TRC(SCCGWA, WS_EXP_INT);
    }
    public void B025_07_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCINF);
        IBS.init(SCCGWA, SCCFMT);
        DDCOCINF.AC_INF.AC_NO = DDCSQINF.AC_NO;
        DDCOCINF.AC_INF.AC_CNM = WS_AC_CNM;
        DDCOCINF.AC_INF.AC_ENM = WS_AC_ENM;
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
        DDCOCINF.AC_INF.LICENSE_TYPE1 = CICACCU.DATA.ID_TYPE;
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
        DDCOCINF.AC_INF.LICENSE_NO1 = CICACCU.DATA.ID_NO;
        DDCOCINF.AC_INF.CI_TYP = DDRMST.CI_TYP;
        if (DDRMST.CI_TYP == ' ') {
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
            DDCOCINF.AC_INF.CI_TYP = CICACCU.DATA.CI_TYP;
        }
        DDCOCINF.AC_INF.AC_TYPE = DDRMST.AC_TYPE;
        DDCOCINF.AC_INF.CCY = WS_DATA.WS_CURRENY;
        DDCOCINF.AC_INF.CCY_TYPE = WS_DATA.WS_CCY_TYPE;
        WS_DATA.WS_L_BAL = WS_DATA.WS_L_BAL + DDRCCY.CCAL_TOT_BAL;
        DDCOCINF.AC_INF.L_BAL = WS_DATA.WS_L_BAL;
        if ((WS_PERS_DATA.WS_P_AC_STA1 == 'F' 
            || WS_PERS_DATA.WS_P_AC_STA1 == 'H') 
            || (WS_PERS_DATA.WS_P_AC_STA2 == 'F' 
            || WS_PERS_DATA.WS_P_AC_STA2 == 'H') 
            || (WS_PERS_DATA.WS_P_AC_STA3 == 'F' 
            || WS_PERS_DATA.WS_P_AC_STA3 == 'H') 
            || (WS_PERS_DATA.WS_P_AC_STA4 == 'F' 
            || WS_PERS_DATA.WS_P_AC_STA4 == 'H')) {
            DDCOCINF.AC_INF.A_BAL = 0;
        } else {
            DDCOCINF.AC_INF.A_BAL = WS_DATA.WS_A_BAL;
        }
        if (WS_DATA.WS_A_BAL < 0) {
            DDCOCINF.AC_INF.A_BAL = WS_DATA.WS_A_BAL;
        }
        DDCOCINF.AC_INF.OD_ADP_BAL = WS_DATA.WS_OD_ADP_BAL;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOCINF.AC_INF.A_BAL = DDCOCINF.AC_INF.A_BAL + DDCOCINF.AC_INF.OD_ADP_BAL;
            DDCOCINF.AC_INF.OD_ADP_BAL = 0;
        }
        if (WS_DATA.WS_L_AMT >= 0) {
            DDCOCINF.AC_INF.A_AMT = 0;
        } else {
            WS_DATA.WS_A_AMT = -1 * WS_DATA.WS_L_AMT;
            DDCOCINF.AC_INF.A_AMT = WS_DATA.WS_A_AMT;
        }
        CEP.TRC(SCCGWA, WS_DATA.WS_OD_ADP_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_A_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_A_AMT);
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.A_BAL);
        DDCOCINF.AC_INF.H_BAL = WS_DATA.WS_H_BAL;
        DDCOCINF.AC_INF.DT_CAMT = WS_DT_CAMT;
        DDCOCINF.AC_INF.TD_BAL = WS_DATA.WS_TD_BAL;
        R000_GET_AC_PAY_INF();
        if (pgmRtn) return;
        DDCOCINF.AC_INF.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOCINF.AC_INF.OPEN_DATE = DDRMST.OPEN_DATE;
        DDCOCINF.AC_INF.CLOSE_DATE = DDRMST.CLOSE_DATE;
        DDCOCINF.AC_INF.LAST_DATE = DDRCCY.LAST_FN_DATE;
        if (DDRDREG.STS == '6' 
            || DDRDREG.STS == '7') {
            DDCOCINF.AC_INF.DREG_N_DT = DDRDREG.N_DT;
        }
        DDCOCINF.AC_INF.AC_STS = WS_PERS_DATA.WS_P_AC_STS;
        DDCOCINF.AC_INF.AC_STA1 = WS_PERS_DATA.WS_P_AC_STA1;
        DDCOCINF.AC_INF.AC_STA2 = WS_PERS_DATA.WS_P_AC_STA2;
        DDCOCINF.AC_INF.AC_STA3 = WS_PERS_DATA.WS_P_AC_STA3;
        DDCOCINF.AC_INF.AC_STA4 = WS_PERS_DATA.WS_P_AC_STA4;
        DDCOCINF.AC_INF.AC_STA5 = WS_PERS_DATA.WS_P_AC_STA5;
        DDCOCINF.AC_INF.AC_STA6 = WS_PERS_DATA.WS_P_AC_STA6;
        DDCOCINF.AC_INF.FRG_CODE = DDRMST.FRG_CODE;
        DDCOCINF.AC_INF.SPC_KIND = DDRMST.SPC_KIND.charAt(0);
        DDCOCINF.AC_INF.ADP_STS = WS_ADP_STS;
        DDCOCINF.AC_INF.ADP_BAL = WS_ADP_BAL;
        CEP.TRC(SCCGWA, "127");
        DDCOCINF.AC_INF.ADP_RATE = WS_ADP_RATE;
        CEP.TRC(SCCGWA, WS_ADP_STS);
        CEP.TRC(SCCGWA, WS_ADP_BAL);
        CEP.TRC(SCCGWA, WS_ADP_RATE);
        DDCOCINF.AC_INF.PRT_FLG = WS_PRT_FLG;
        DDCOCINF.AC_INF.CINT_FLG = DDRCCY.CINT_FLG;
        DDCOCINF.AC_INF.CON_RATE = WS_ADP_RATE;
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSQINF.AC_NO;
        BPCPFPDT.INPUT.FDT_TYP = ' ';
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDCOCINF.AC_INF.DEBT_FLG = BPCPFPDT.OUTPUT.MAIN_FLG;
            CEP.TRC(SCCGWA, DDCOCINF.AC_INF.DEBT_FLG);
        }
        DDCOCINF.AC_INF.MST_OPEN_BR = DDRMST.OPEN_DP;
        DDCOCINF.AC_INF.CCY_OPEN_BR = DDRMST.OWNER_BR;
        DDCOCINF.AC_INF.CHQ_U_IND = WS_PERS_DATA.WS_P_CHQ_U_IND;
        DDCOCINF.AC_INF.DR_FLG = DDRMST.CROS_DR_FLG;
        DDCOCINF.AC_INF.CR_FLG = DDRMST.CROS_CR_FLG;
        DDCOCINF.AC_INF.FRG_IND = DDRMST.FRG_IND;
        DDCOCINF.AC_INF.EXP_INT = WS_EXP_INT;
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.EXP_INT);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "123323");
            CEP.TRC(SCCGWA, DCCICCYR.OUT_DATA.TD_TOT_BAL);
            CEP.TRC(SCCGWA, DDCOCINF.AC_INF.TZN_PBAL);
            DDCOCINF.AC_INF.TZN_PBAL = DDRCCY.CCAL_TOT_BAL;
        }
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.TZN_PBAL);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(49 - 1, 49 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOCINF.AC_INF.GROUP_FLG = 'Y';
        } else {
            DDCOCINF.AC_INF.GROUP_FLG = 'N';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOCINF.AC_INF.TZN_DR_FLG = 'Y';
        } else {
            DDCOCINF.AC_INF.TZN_DR_FLG = 'N';
        }
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        CEP.TRC(SCCGWA, DDRMST.AC_OIC_NO);
        CEP.TRC(SCCGWA, DDRMST.AC_OIC_CODE);
        CEP.TRC(SCCGWA, DDRMST.SUB_DP);
        CEP.TRC(SCCGWA, DDRMST.CHCK_IND);
        DDCOCINF.AC_INF.CASH_FLG = DDRMST.CASH_FLG;
        DDCOCINF.AC_INF.AC_OIC_NO = DDRMST.AC_OIC_NO;
        DDCOCINF.AC_INF.AC_OIC_CODE = DDRMST.AC_OIC_CODE;
        DDCOCINF.AC_INF.SUB_DP = DDRMST.SUB_DP;
        DDCOCINF.AC_INF.CHK_IND = DDRMST.CHCK_IND;
        if (DDRMST.AC_TYPE == 'F') {
            DDCOCINF.AC_INF.EXP_DATE = DDRMST.EXP_DATE;
        } else {
            DDCOCINF.AC_INF.EXP_DATE = 0;
        }
        DDCOCINF.AC_INF.CHK_DATE = DDRMST.PBC_APPR_DATE;
        DDCOCINF.AC_INF.FR_OP_NO = DDRMST.FRG_OPEN_NO;
        DDCOCINF.AC_INF.VER_TYPE1 = DDRINF.LAMT_VER_TYPE1;
        DDCOCINF.AC_INF.VER_NAME1 = DDRINF.LAMT_VER_NAME1;
        DDCOCINF.AC_INF.VER_POS1 = DDRINF.LAMT_VER_POS1;
        DDCOCINF.AC_INF.VER_TEL1 = DDRINF.LAMT_VER_TEL1;
        DDCOCINF.AC_INF.VER_CELL1 = DDRINF.LAMT_VER_CELL1;
        DDCOCINF.AC_INF.VER_TYPE2 = DDRINF.LAMT_VER_TYPE2;
        DDCOCINF.AC_INF.VER_NAME2 = DDRINF.LAMT_VER_NAME2;
        DDCOCINF.AC_INF.VER_POS2 = DDRINF.LAMT_VER_POS2;
        DDCOCINF.AC_INF.VER_TEL2 = DDRINF.LAMT_VER_TEL2;
        DDCOCINF.AC_INF.VER_CELL2 = DDRINF.LAMT_VER_CELL2;
        DDCOCINF.AC_INF.VER_TYPE3 = DDRINF.LAMT_VER_TYPE3;
        DDCOCINF.AC_INF.VER_NAME3 = DDRINF.LAMT_VER_NAME3;
        DDCOCINF.AC_INF.VER_POS3 = DDRINF.LAMT_VER_POS3;
        DDCOCINF.AC_INF.VER_TEL3 = DDRINF.LAMT_VER_TEL3;
        DDCOCINF.AC_INF.VER_CELL3 = DDRINF.LAMT_VER_CELL3;
        DDCOCINF.AC_INF.AUTH_CNM1 = DDRINF.AUTH_CNM1;
        DDCOCINF.AC_INF.AUTH_TYP1 = DDRINF.AUTH_TYP1;
        DDCOCINF.AC_INF.AUTH_NO1 = DDRINF.AUTH_NO1;
        DDCOCINF.AC_INF.AUTH_TEL1 = DDRINF.AUTH_TEL_NO1;
        DDCOCINF.AC_INF.AUTH_CNM2 = DDRINF.AUTH_CNM2;
        DDCOCINF.AC_INF.AUTH_TYP2 = DDRINF.AUTH_TYP2;
        DDCOCINF.AC_INF.AUTH_NO2 = DDRINF.AUTH_NO2;
        DDCOCINF.AC_INF.AUTH_TEL2 = DDRINF.AUTH_TEL_NO2;
        DDCOCINF.AC_INF.AUTH_CNM3 = DDRINF.AUTH_CNM3;
        DDCOCINF.AC_INF.AUTH_TYP3 = DDRINF.AUTH_TYP3;
        DDCOCINF.AC_INF.AUTH_NO3 = DDRINF.AUTH_NO3;
        DDCOCINF.AC_INF.AUTH_TEL3 = DDRINF.AUTH_TEL_NO3;
        DDCOCINF.AC_INF.FIN_STFNAME1 = DDRINF.FIN_STFNAME1;
        DDCOCINF.AC_INF.FIN_STFTEL1 = DDRINF.FIN_STFTEL1;
        DDCOCINF.AC_INF.FIN_STFCELL1 = DDRINF.FIN_STFCELL1;
        DDCOCINF.AC_INF.FIN_STFNAME2 = DDRINF.FIN_STFNAME2;
        DDCOCINF.AC_INF.FIN_STFTEL2 = DDRINF.FIN_STFTEL2;
        DDCOCINF.AC_INF.FIN_STFCELL2 = DDRINF.FIN_STFCELL2;
        DDCOCINF.AC_INF.FIN_STFNAME3 = DDRINF.FIN_STFNAME3;
        DDCOCINF.AC_INF.FIN_STFTEL3 = DDRINF.FIN_STFTEL3;
        DDCOCINF.AC_INF.FIN_STFCELL3 = DDRINF.FIN_STFCELL3;
        DDCOCINF.AC_INF.CHK_NAME1 = DDRINF.AMT_CHK_NAME1;
        DDCOCINF.AC_INF.CHK_CELL1 = DDRINF.AMT_CHK_CELL1;
        DDCOCINF.AC_INF.CHK_NAME2 = DDRINF.AMT_CHK_NAME2;
        DDCOCINF.AC_INF.CHK_CELL2 = DDRINF.AMT_CHK_CELL2;
        DDCOCINF.AC_INF.CHK_NAME3 = DDRINF.AMT_CHK_NAME3;
        DDCOCINF.AC_INF.CHK_CELL3 = DDRINF.AMT_CHK_CELL3;
        DDCOCINF.AC_INF.CHK_NAME4 = DDRINF.AMT_CHK_NAME4;
        DDCOCINF.AC_INF.CHK_CELL4 = DDRINF.AMT_CHK_CELL4;
        if (DDRMST.CI_TYP == '3') {
            DDCOCINF.AC_INF.TXN_TYP = DDRINF.TXN_TYPE;
            DDCOCINF.AC_INF.AMT_TYP = DDRINF.AMT_TYPE;
        }
        DDCOCINF.AC_INF.BASE_NO = DDRINF.BASE_NO;
        if (WS_CARD_AC.trim().length() == 0) {
            DDCOCINF.AC_INF.LNK_CARD_FLG = '0';
        } else {
            DDCOCINF.AC_INF.LNK_CARD_FLG = '1';
        }
        if (WS_VS_AC.trim().length() == 0) {
            DDCOCINF.AC_INF.LNK_VSAC_FLG = '0';
        } else {
            DDCOCINF.AC_INF.LNK_VSAC_FLG = '1';
        }
        DDCOCINF.AC_INF.MST_PROD_CD = DDRMST.PROD_CODE;
        DDCOCINF.AC_INF.AC_STS_WORD = DDRMST.AC_STS_WORD;
        DDCOCINF.AC_INF.CCY_FLG = DDRMST.CCY_FLG;
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.CCY_FLG);
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, WS_AC_CNM);
        CEP.TRC(SCCGWA, WS_AC_ENM);
        CEP.TRC(SCCGWA, DDRINF.LICENSE_TYPE1);
        CEP.TRC(SCCGWA, DDRINF.LICENSE_NO1);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.PAY_MTH);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DATE);
        CEP.TRC(SCCGWA, DDRMST.CLOSE_DATE);
        CEP.TRC(SCCGWA, DDRMST.LAST_DATE);
        CEP.TRC(SCCGWA, DDRDREG.N_DT);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STS);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA2);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA3);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA4);
        CEP.TRC(SCCGWA, DDRMST.FRG_CODE);
        CEP.TRC(SCCGWA, DDRMST.SPC_KIND);
        CEP.TRC(SCCGWA, WS_ADP_BAL);
        CEP.TRC(SCCGWA, WS_ADP_RATE);
        CEP.TRC(SCCGWA, DDRCCY.CINT_FLG);
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.CCY_OPEN_BR);
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRCCY.LAST_FN_DATE);
        CEP.TRC(SCCGWA, DDCOCINF.AC_INF.LAST_DATE);
        CEP.TRC(SCCGWA, DDCOCINF);
        SCCFMT.FMTID = K_FMT_C_INF;
        SCCFMT.DATA_PTR = DDCOCINF;
        SCCFMT.DATA_LEN = 4971;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_C_AC_STS() throws IOException,SQLException,Exception {
        WS_PERS_DATA.WS_P_AC_STS = DDRMST.AC_STS;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'F';
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'H';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'K';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'J';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'G';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'C';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'M';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'D';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'O';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'V';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'R';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'T';
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        if (WS_PERS_DATA.WS_P_AC_STA1 != ' ') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'V';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'O';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'D';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'H';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'M';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'C';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'G';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'J';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'K';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'F';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'R';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA2 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA2 = 'T';
                if (WS_PERS_DATA.WS_P_AC_STA1 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA2 = ' ';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA2);
        if (WS_PERS_DATA.WS_P_AC_STA1 != ' ' 
            && WS_PERS_DATA.WS_P_AC_STA2 != ' ') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'F';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'K';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'J';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'G';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'C';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'M';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'H';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'D';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'O';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'V';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'R';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA3 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA3 = 'T';
                if (WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA2) {
                    WS_PERS_DATA.WS_P_AC_STA3 = ' ';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA2);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA3);
        if (WS_PERS_DATA.WS_P_AC_STA1 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA2 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA3 != ' ') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'F';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'K';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'J';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'G';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'C';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'M';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'H';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'D';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'O';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'V';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'R';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA4 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA4 = 'T';
                if (WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA4 == WS_PERS_DATA.WS_P_AC_STA3) {
                    WS_PERS_DATA.WS_P_AC_STA4 = ' ';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA2);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA3);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA4);
        if (WS_PERS_DATA.WS_P_AC_STA1 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA2 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA3 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA4 != ' ') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'F';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'K';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'J';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'G';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'C';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'M';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'H';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'D';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'O';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'V';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'R';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA5 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA5 = 'T';
                if (WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA5 == WS_PERS_DATA.WS_P_AC_STA4) {
                    WS_PERS_DATA.WS_P_AC_STA5 = ' ';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA1);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA2);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA3);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA4);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA5);
        if (WS_PERS_DATA.WS_P_AC_STA1 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA2 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA3 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA4 != ' ' 
            || WS_PERS_DATA.WS_P_AC_STA5 != ' ') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'F';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'K';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'J';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'G';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'C';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'M';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'H';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'D';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'O';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(0, 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'V';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'R';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(26 - 1, 26 + 1 - 1).equalsIgnoreCase("1") 
                && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
                WS_PERS_DATA.WS_P_AC_STA6 = 'T';
                if (WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA1 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA2 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA3 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA4 
                    || WS_PERS_DATA.WS_P_AC_STA6 == WS_PERS_DATA.WS_P_AC_STA5) {
                    WS_PERS_DATA.WS_P_AC_STA6 = ' ';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA6);
        if (WS_PERS_DATA.WS_P_AC_STA1 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA2 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA3 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA4 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA5 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA6 == ' ') {
            WS_PERS_DATA.WS_P_AC_STA1 = 'N';
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STS);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STA);
    }
    public void B000_GET_AC_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, DDCSQINF.CCY);
        CEP.TRC(SCCGWA, DDCSQINF.CCY_TYPE);
        DDRCCY.CUS_AC = DDCSQINF.AC_NO;
        DDRCCY.CCY = DDCSQINF.CCY;
        DDRCCY.CCY_TYPE = DDCSQINF.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        if (DDCSQINF.CCY.trim().length() > 0 
            && DDCSQINF.CCY_TYPE != ' ') {
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            R000_01_TRANS_CCY();
            if (pgmRtn) return;
            R000_CHK_TDAC_PROC();
            if (pgmRtn) return;
            R000_INQ_AC_CHQ();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            R000_01_TRANS_CCY();
            if (pgmRtn) return;
            R000_CHK_TDAC_PROC();
            if (pgmRtn) return;
            R000_INQ_AC_CHQ();
            if (pgmRtn) return;
            T000_ENDBR_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void R000_01_TRANS_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_CURRENY = DDRCCY.CCY;
        WS_DATA.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_DATA.WS_L_BAL = DDRCCY.CURR_BAL;
        WS_DATA.WS_H_BAL = DDRCCY.HOLD_BAL;
        WS_DATA.WS_L_AMT = DDRCCY.CURR_BAL;
        if (DDRMST.LAST_FN_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && DDRCCY.DRW_CAMT > 0) {
            WS_DT_CAMT = DDRCCY.DRW_CAMT;
        } else {
            WS_DT_CAMT = 0;
        }
        WS_DATA.WS_A_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, "123456789");
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        CEP.TRC(SCCGWA, DCRIACCY.KEY.CCY_TYPE);
        CEP.TRC(SCCGWA, WS_DATA.WS_L_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_H_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_A_BAL);
        CEP.TRC(SCCGWA, WS_DT_CAMT);
    }
    public void R000_CHK_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDDTD);
        DDRDDTD.KEY.AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, DDRDDTD.KEY.AC);
        T000_STARTBR_DDTDDTD();
        if (pgmRtn) return;
        T000_READNEXT_DDTDDTD();
        if (pgmRtn) return;
        while (WS_DTD_FLG != 'N') {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = DDRDDTD.KEY.CON_NO;
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
            if (TDRSMST.ACO_STS == '0') {
                WS_DATA.WS_TD_BAL = WS_DATA.WS_TD_BAL + TDRSMST.BAL;
                WS_DATA.WS_L_BAL = WS_DATA.WS_L_BAL + TDRSMST.BAL;
            }
            T000_READNEXT_DDTDDTD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTDDTD();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_DREG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
        T000_READ_DDTDREG();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_RAT() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ADP_STS = 'N';
            CEP.TRC(SCCGWA, "NNN");
        }
        CEP.TRC(SCCGWA, WS_ADP_STS);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ADP_STS = 'Y';
            CEP.TRC(SCCGWA, "YYY");
            CEP.TRC(SCCGWA, WS_ADP_STS);
            IBS.init(SCCGWA, DDRMSTR);
            DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
            DDRMSTR.KEY.ADP_TYPE = "2";
            DDRMSTR.ADP_STS = 'O';
            CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
            CEP.TRC(SCCGWA, DDRMSTR.KEY.ADP_TYPE);
            CEP.TRC(SCCGWA, DDRMSTR.ADP_STS);
            T000_READ_DDTMSTR_FOR_OPEN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_MSTR_OPEN_FLG);
            CEP.TRC(SCCGWA, "11111");
            if (WS_MSTR_OPEN_FLG == 'Y') {
                WS_ADP_BAL = DDRMSTR.TIR_AMT11;
                if (DDRMSTR.TIR_FIX_RATE12 != 0) {
                    WS_ADP_RATE = DDRMSTR.TIR_FIX_RATE12;
                } else {
                    IBS.init(SCCGWA, DDCSRATE);
                    DDCSRATE.RATE_TYP = DDRMSTR.TIR_RBAS12;
                    DDCSRATE.RAT_TERM = DDRMSTR.TIR_RCD12;
                    if (DDRMSTR.TIR_SPR12 != 0) {
                        DDCSRATE.FLOAT_TP = '1';
                    }
                    if (DDRMSTR.TIR_SPR_PCT12 != 0) {
                        DDCSRATE.FLOAT_TP = '2';
                    }
                    DDCSRATE.F_SPRD = DDRMSTR.TIR_SPR12;
                    DDCSRATE.F_PCNT = DDRMSTR.TIR_SPR_PCT12;
                    DDCSRATE.CCY = DDRCCY.CCY;
                    CEP.TRC(SCCGWA, DDCSRATE.RATE_TYP);
                    CEP.TRC(SCCGWA, DDCSRATE.RAT_TERM);
                    CEP.TRC(SCCGWA, DDCSRATE.FLOAT_TP);
                    CEP.TRC(SCCGWA, DDCSRATE.F_SPRD);
                    CEP.TRC(SCCGWA, DDCSRATE.F_PCNT);
                    CEP.TRC(SCCGWA, DDCSRATE.CCY);
                    S000_CALL_DDZSRATE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDCSRATE.CON_RATE);
                    WS_ADP_RATE = DDCSRATE.CON_RATE;
                }
            } else {
                WS_ADP_BAL = 0;
                WS_ADP_RATE = 0;
            }
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, DDRADMN);
            DDRADMN.KEY.AC = DDRCCY.KEY.AC;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1")) {
                if (DDRMST.CI_TYP == '2') {
                    DDRADMN.KEY.ADP_TYPE = "08";
                }
                if (DDRMST.CI_TYP == '3') {
                    DDRADMN.KEY.ADP_TYPE = "10";
                }
            } else {
                DDRADMN.KEY.ADP_TYPE = "09";
            }
            CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_TYPE);
            DDRADMN.ADP_STS = 'O';
            T000_READ_DDTADMN();
            if (pgmRtn) return;
            if (WS_ADMN_READ_FLG == 'Y') {
                WS_DATA.WS_OD_ADP_BAL = DDRADMN.OD_AMT;
                CEP.TRC(SCCGWA, WS_DATA.WS_OD_ADP_BAL);
            }
        }
    }
    public void R000_GET_CARD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSQINF.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        WS_C_LNK_TYP = DCCUCINF.CARD_LNK_TYP;
        CEP.TRC(SCCGWA, WS_C_LNK_TYP);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
    }
    public void R000_GET_CI_PRT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSSTC);
        CICSSTC.SCA_DATA.AC = DDCSQINF.AC_NO;
        CICSSTC.FUNC = 'C';
        S000_CALL_CIZSSTC();
        if (pgmRtn) return;
        WS_PRT_FLG = CICSSTC.STC_DATA.PRT_PAPER;
        WS_PERS_DATA.WS_P_PRT_PERM = CICSSTC.STC_DATA.PRT_PERM;
        WS_PRT_DATE = CICSSTC.STC_DATA.PRT_DAY;
        WS_PERS_DATA.WS_P_SCS_ADRS = CICSSTC.SCS_DATA.ADRS;
        WS_PERS_DATA.WS_P_ADR_NM = CICSSTC.SCS_DATA.ADR_NM;
        CEP.TRC(SCCGWA, WS_PRT_FLG);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_PRT_PERM);
        CEP.TRC(SCCGWA, WS_PRT_DATE);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_SCS_ADRS);
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_ADR_NM);
    }
    public void R000_GET_PROD_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDVMRAT);
        IBS.init(SCCGWA, DDCUPRAT);
        DDCUPRAT.TX_TYPE = 'I';
        DDCUPRAT.DATA.KEY.PARM_CODE = WS_RATE_KEY.WS_RATE_PRD_CD;
        DDCUPRAT.DATA.KEY.CCY = WS_RATE_KEY.WS_RATE_CCY;
        CEP.TRC(SCCGWA, DDCUPRAT.DATA.KEY.PARM_CODE);
        CEP.TRC(SCCGWA, DDCUPRAT.DATA.KEY.CCY);
        S000_CALL_DDZUPRAT();
        if (pgmRtn) return;
        if (DDCUPRAT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPRAT.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPRAT.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMRAT);
            CEP.TRC(SCCGWA, DDVMRAT);
        }
        CEP.TRC(SCCGWA, DDVMRAT.KEY);
        CEP.TRC(SCCGWA, DDVMRAT);
    }
    public void R000_GET_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DDCSQINF.AC_NO;
        CICQACRL.DATA.AC_REL = "01";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        WS_VS_AC = CICQACRL.O_DATA.O_AC_NO;
        CEP.TRC(SCCGWA, WS_VS_AC);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DDCSQINF.AC_NO;
        CICQACRL.DATA.AC_REL = "04";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        WS_CARD_AC = CICQACRL.O_DATA.O_AC_NO;
        CEP.TRC(SCCGWA, WS_CARD_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSQINF.AC_NO;
        CEP.TRC(SCCGWA, DDCSQINF.AC_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        if (DDRMST.CI_TYP == '1') {
            if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
            JIBS_tmp_int = CICACCU.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
            if (CICACCU.DATA.STSW.substring(0, 1).equalsIgnoreCase("3")) {
                WS_AC_ENM = CICACCU.DATA.CI_ENM;
                WS_AC_CNM = CICACCU.DATA.CI_CNM;
            } else {
                if (CICACCU.DATA.AC_ENM.trim().length() == 0) {
                    WS_AC_ENM = CICACCU.DATA.CI_ENM;
                } else {
                    WS_AC_ENM = CICACCU.DATA.AC_ENM;
                }
                if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                    WS_AC_CNM = CICACCU.DATA.CI_CNM;
                } else {
                    WS_AC_CNM = CICACCU.DATA.AC_CNM;
                }
            }
        } else {
            WS_AC_ENM = CICACCU.DATA.AC_ENM;
            WS_AC_CNM = CICACCU.DATA.AC_CNM;
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, WS_AC_ENM);
        CEP.TRC(SCCGWA, WS_AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.BBR);
    }
    public void R000_MPAGE_OUTPUT_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 133;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQINF.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDCSQINF.FUNC == '1') {
            IBS.init(SCCGWA, DDRINF);
            DDRINF.KEY.CUS_AC = DDCSQINF.AC_NO;
            T000_READ_DDTINF();
            if (pgmRtn) return;
        }
        WS_DOM_BR = DDRMST.OPEN_DP;
        WS_PROD_CODE = DDRMST.PROD_CODE;
    }
    public void R000_INQ_AC_CHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDRCCY.KEY.AC;
        T000_STARTBR_DDTCHQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        if (WS_CHQ_FLG == 'N') {
            WS_PERS_DATA.WS_P_CHQ_U_IND = 'N';
        } else {
            WS_PERS_DATA.WS_P_CHQ_U_IND = 'Y';
        }
    }
    public void R000_GET_AC_CHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCSQINF.AC_NO;
        T000_STARTBR_DDTCHQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCOINFQ.BASIC_INFO);
        DDCOINFQ.BASIC_INFO.AC_NO = DDCSQINF.AC_NO;
        DDCOINFQ.BASIC_INFO.CCY = DDRCHQ.CCY;
        DDCOINFQ.BASIC_INFO.CHQ_CNT = 0;
        CHQ_DETL = new DDCOINFQ_CHQ_DETL();
        DDCOINFQ.CHQ_DETL.add(CHQ_DETL);
        while (WS_CHQ_FLG != 'N') {
            DDCOINFQ.BASIC_INFO.CHQ_CNT += 1;
            CHQ_DETL = new DDCOINFQ_CHQ_DETL();
            DDCOINFQ.CHQ_DETL.add(CHQ_DETL);
            IBS.init(SCCGWA, CHQ_DETL);
            CHQ_DETL.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
            CHQ_DETL.START_NO = DDRCHQ.KEY.STR_CHQ_NO;
            CHQ_DETL.END_NO = DDRCHQ.KEY.END_CHQ_NO;
            WS_NOR_CNT = 0;
            WS_PAID_CNT = 0;
            WS_STOP_CNT = 0;
            WS_DISCARD_CNT = 0;
            WS_CHQ_STS = " ";
            for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                    WS_NOR_CNT += 1;
                } else {
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("2")) {
                        WS_PAID_CNT += 1;
                    } else {
                        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                        if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("4")) {
                            WS_STOP_CNT += 1;
                        } else {
                            WS_DISCARD_CNT += 1;
                        }
                    }
                }
            }
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (WS_CHQ_STS == null) WS_CHQ_STS = "";
            JIBS_tmp_int = WS_CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) WS_CHQ_STS += " ";
            WS_CHQ_STS = DDRCHQ.CHQ_STS.substring(0, WS_CHQ_CNT) + WS_CHQ_STS.substring(WS_CHQ_CNT);
            CHQ_DETL.NOR_NUM = WS_NOR_CNT;
            CHQ_DETL.PAID_NUM = WS_PAID_CNT;
            CHQ_DETL.STOP_NUM = WS_STOP_CNT;
            CHQ_DETL.DISCARD_NUM = WS_DISCARD_CNT;
            CHQ_DETL.CHQ_STS = WS_CHQ_STS;
            T000_READNEXT_DDTCHQ();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCHQ();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_PAY_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSQINF.AC_NO;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R000_GET_PRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = WS_PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RATE", DDCSRATE, true);
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT, true);
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DD-UNIT-POST-INT";
        SCCCALL.COMMAREA_PTR = DDCUPINT;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_DDZUPRAT() throws IOException,SQLException,Exception {
        DDZUPRAT DDZUPRAT = new DDZUPRAT();
        DDZUPRAT.MP(SCCGWA, DDCUPRAT);
        if (DDCUPRAT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPRAT.RC);
        }
    }
    public void T000_READ_DDTMSTR_FOR_OPEN() throws IOException,SQLException,Exception {
        WS_MSTR_OPEN_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        DDTMSTR_RD.fst = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXX1111");
            WS_MSTR_OPEN_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "XXXX2222");
            WS_MSTR_OPEN_FLG = 'N';
        }
    }
    public void T000_READ_DDTADMN() throws IOException,SQLException,Exception {
        WS_ADMN_READ_FLG = 'N';
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE "
            + "AND ADP_STS = :DDRADMN.ADP_STS";
        DDTADMN_RD.fst = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "XXXX1111");
            WS_ADMN_READ_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "XXXX2222");
            WS_ADMN_READ_FLG = 'N';
        }
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void R000_TRANS_STIC_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFS);
        DDCOINFS.AC_INF.AC_NO = DDCSQINF.AC_NO;
        DDCOINFS.AC_INF.CI_NO = CICACCU.DATA.CI_NO;
        DDCOINFS.AC_INF.AC_BBR = DDRMST.OPEN_DP;
        DDCOINFS.AC_INF.AC_ENG_NAME = WS_AC_ENM;
        DDCOINFS.AC_INF.AC_CHN_NAME = WS_AC_CNM;
        DDCOINFS.AC_INF.PROD_CODE = DDRMST.PROD_CODE;
        DDCOINFS.AC_INF.PROD_CNM = DDVMPRD.VAL.CHN_NAME;
        DDCOINFS.AC_INF.AC_STS = DDRMST.AC_STS;
        DDCOINFS.AC_INF.OPEN_DATE = DDRMST.OPEN_DATE;
        DDCOINFS.AC_INF.CLOSE_DATE = DDRMST.CLOSE_DATE;
        DDCOINFS.AC_INF.DEP_PERD = DDVMPRD.VAL.DEP_POST_PERIOD1;
        DDCOINFS.AC_INF.DEP_DT = DDVMPRD.VAL.DEP_POST_DATE1;
        DDCOINFS.AC_INF.DEP_PRD2 = DDVMPRD.VAL.DEP_POST_PERIOD2;
        DDCOINFS.AC_INF.DEP_DT2 = DDVMPRD.VAL.DEP_POST_DATE2;
        DDCOINFS.AC_INF.AC_NO = DDRMST.KEY.CUS_AC;
        DDCOINFS.AC_INF.AC_BBR = DDRMST.OPEN_DP;
        DDCOINFS.AC_INF.DR_FLG = DDRMST.CROS_DR_FLG;
        DDCOINFS.AC_INF.CR_FLG = DDRMST.CROS_CR_FLG;
        DDCOINFS.AC_INF.POST_AC = DDRCCY.POST_AC;
        DDCOINFS.COMP_INF.SPC_KIND = DDRMST.SPC_KIND.charAt(0);
        DDCOINFS.COMP_INF.EXP_DATE = DDRMST.EXP_DATE;
        DDCOINFS.COMP_INF.FRG_OPNO = DDRMST.FRG_OPEN_NO;
        DDCOINFS.COMP_INF.FRG_CODE = DDRMST.FRG_CODE;
        DDCOINFS.COMP_INF.FRG_TYPE = DDRMST.FRG_TYPE;
        DDCOINFS.COMP_INF.LI_TP1 = DDRINF.LICENSE_TYPE1;
        DDCOINFS.COMP_INF.LI_NO1 = DDRINF.LICENSE_NO1;
        DDCOINFS.COMP_INF.LI_DT1 = DDRINF.LICENSE_DATE1;
        DDCOINFS.COMP_INF.LI_TP2 = DDRINF.LICENSE_TYPE2;
        DDCOINFS.COMP_INF.LI_NO2 = DDRINF.LICENSE_NO2;
        DDCOINFS.COMP_INF.LI_DT2 = DDRINF.LICENSE_DATE2;
        DDCOINFS.COMP_INF.BASE_AC = DDRINF.BASE_AC;
        DDCOINFS.COMP_INF.BS_AC_AR = DDRINF.BASE_AC_AREA;
        DDCOINFS.COMP_INF.BS_AC_BK = DDRINF.BASE_AC_BKNO;
        DDCOINFS.COMP_INF.BS_AC_OD = DDRINF.BASE_AC_OPDT;
        DDCOINFS.COMP_INF.BASE_NO = DDRINF.BASE_NO;
        DDCOINFS.COMP_INF.BS_AC_BN = DDRINF.BASE_AC_BK;
        DDCOINFS.COMP_INF.CRP_NM1 = DDRINF.CORP_NAME1;
        DDCOINFS.COMP_INF.CRP_NM2 = DDRINF.CORP_NAME2;
        DDCOINFS.COMP_INF.CRP_NM3 = DDRINF.CORP_NAME3;
        DDCOINFS.COMP_INF.CRP_NM4 = DDRINF.CORP_NAME4;
        DDCOINFS.COMP_INF.CRP_NM5 = DDRINF.CORP_NAME5;
        DDCOINFS.COMP_INF.AD_ORGN = DDRINF.ADMIN_ORGNAME;
        DDCOINFS.COMP_INF.AD_NO = DDRINF.ADMIN_NO;
        DDCOINFS.COMP_INF.AD_ORGC = DDRINF.ADMIN_ORGCODE;
        DDCOINFS.COMP_INF.AD_CORPF = DDRINF.ADMIN_CORP_FLG;
        DDCOINFS.COMP_INF.AD_NAME = DDRINF.ADMIN_NAME;
        DDCOINFS.COMP_INF.AD_IDTP = DDRINF.ADMIN_IDTYPE;
        DDCOINFS.COMP_INF.AD_IDNO = DDRINF.ADMIN_IDNO;
        DDCOINFS.COMP_INF.AD_DATE = DDRINF.ADMIN_DATE;
        DDCOINFS.COMP_INF.AC_ATTR = DDRMST.AC_TYPE;
        DDCOINFS.COMP_INF.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOINFS.COMP_INF.PSBK_FLG = 'N';
        DDCOINFS.COMP_INF.PSBK_NO = "" + 0;
        JIBS_tmp_int = DDCOINFS.COMP_INF.PSBK_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) DDCOINFS.COMP_INF.PSBK_NO = "0" + DDCOINFS.COMP_INF.PSBK_NO;
        if (DDVMPRD.VAL.PRD_TOOL_PSB == 'Y') {
            DDCOINFS.COMP_INF.PSBK_FLG = 'Y';
            DDCOINFS.COMP_INF.PSBK_NO = DDRVCH.PSBK_NO;
        }
        DDCOINFS.COMP_INF.ID_TYPE = DDRVCH.PAY_IDTYPE;
        DDCOINFS.COMP_INF.ID_NO = DDRVCH.PAY_IDNO;
        DDCOINFS.COMP_INF.SIGN_NO = DDRVCH.PAY_SIGN_NO;
    }
    public void R000_TRANS_AC_RATE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFC);
        DDCOINFC.AC_NO = DDCSQINF.AC_NO;
        DDCOINFC.CCY = DDCSQINF.CCY;
        DDCOINFC.CCY_TYPE = DDCSQINF.CCY_TYPE;
        DDCOINFC.DEP_RAT_INF.ADP_NO = DDRMSTR.ADP_NO;
        DDCOINFC.DEP_RAT_INF.ADP_STRDATE = DDRMSTR.KEY.ADP_STRDATE;
        DDCOINFC.DEP_RAT_INF.TIR_TYPE = DDRMSTR.TIR_TYPE;
        DDCOINFC.DEP_RAT_INF.AGSP_FLG = DDRMSTR.AGSP_FLG;
        DDCOINFC.DEP_RAT_INF.DEP_POST_PERIOD = WS_DEP_POST_PERIOD;
        DDCOINFC.DEP_RAT_INF.DEP_POST_DATE = WS_DEP_POST_DATE;
        DDCOINFC.OD_RAT_INF.OD_POST_PERIOD = WS_OD_POST_PERIOD;
        DDCOINFC.OD_RAT_INF.OD_POST_DATE = WS_OD_POST_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_INT_CON1;
        SCCFMT.DATA_PTR = DDCOINFC;
        SCCFMT.DATA_LEN = 518;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_PRD_RATE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOINFP);
        DDCOINFP.AC_NO = DDCSQINF.AC_NO;
        DDCOINFP.CCY = DDCSQINF.CCY;
        DDCOINFP.CCY_TYPE = DDCSQINF.CCY_TYPE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMRAT.VAL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCOINFP.VAL);
        DDCOINFP.DEP_POST_PERIOD = WS_DEP_POST_PERIOD;
        DDCOINFP.DEP_POST_DATE = WS_DEP_POST_DATE;
        DDCOINFP.OD_POST_PERIOD = WS_OD_POST_PERIOD;
        DDCOINFP.OD_POST_DATE = WS_OD_POST_DATE;
        CEP.TRC(SCCGWA, DDVMRAT.VAL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_INT_CON2;
        SCCFMT.DATA_PTR = DDCOINFP;
        SCCFMT.DATA_LEN = 798;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_DDTDDTD() throws IOException,SQLException,Exception {
        WS_DTD_FLG = 'N';
        DDTDDTD_BR.rp = new DBParm();
        DDTDDTD_BR.rp.TableName = "DDTDDTD";
        DDTDDTD_BR.rp.where = "AC = :DDRDDTD.KEY.AC";
        DDTDDTD_BR.rp.order = "AC, CON_NO";
        IBS.STARTBR(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC";
        DDTCHQ_BR.rp.order = "AC, STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DTD_FLG = 'Y';
        } else {
            WS_DTD_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDDTD_BR);
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_READ_DCTCIDEP() throws IOException,SQLException,Exception {
        DCTCIDEP_RD = new DBParm();
        DCTCIDEP_RD.TableName = "DCTCIDEP";
        DCTCIDEP_RD.where = "DD_AC = :DCRCIDEP.DD_AC "
            + "AND PROC_STS = :DCRCIDEP.PROC_STS";
        IBS.READ(SCCGWA, DCRCIDEP, this, DCTCIDEP_RD);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZICCYR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-CCY-RTV-DTL", DCCICCYR);
        if (DCCICCYR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICCYR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INTB-PROC", DDCIINTB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIINTB.RC);
        if ((DDCIINTB.RC.RC_CODE != 0) 
            && (!JIBS_tmp_str[0].equalsIgnoreCase(DDCMSG_ERROR_MSG.DD_INTB_REC_NOTFND))) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIINTB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZSSTC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SERVICE-STC", CICSSTC, true);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIBASD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INT-BASE-DAY", DDCIBASD);
        if (DDCIBASD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIBASD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            if (BPCPRMM.RC.RC_RTNCODE == BPCMSG_ERROR_MSG.BP_PARM_NOTFND) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_NOTFND;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        WS_CCY_FLG = 'N';
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSQINF.AC_NO;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.order = "CCY,CCY_TYPE";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'F';
        } else {
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINF() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.READ(SCCGWA, DDRINF, DDTINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        IBS.READ(SCCGWA, DDRMSTR, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
    }
    public void T000_READ_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.col = "AC,N_DT,STS";
        DDTDREG_RD.where = "AC = :DDRDREG.KEY.AC";
        IBS.READ(SCCGWA, DDRDREG, this, DDTDREG_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S00_CALL_INQUIRE_RATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            BPCCINTI.BASE_INFO.OWN_RATE = 0;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
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
