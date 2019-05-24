package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.VT.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACMT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTSCASH_RD;
    brParm IBTSCASH_BR = new brParm();
    DBParm IBTBALF_RD;
    DBParm IBTINRH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_IB_PROD_MODEL = "IBDD";
    String K_OUTPUT_FMT_O = "IBA13";
    String K_OUTPUT_FMT_M = "IBA12";
    char K_CLOSE = 'C';
    char K_BROWSE = 'B';
    char K_INQUIRE = 'I';
    char K_MODIFY = 'M';
    char K_OPEN = 'O';
    String K_STATUS = "00000000000000000000000000000000";
    String K_STATUS_NORMAL = "10000000000000000000000000000000";
    String K_STSW = "10000000";
    String K_STSW1 = "01000000000000000000";
    String K_IBTMST = "IBTMST  ";
    String K_IBTBALF = "IBTBALF ";
    String K_IBTINRH = "IBTINRH ";
    String K_IBTSCASH = "IBTSCASH";
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    String WS_AC_NO = " ";
    String WS_AC_NO1 = " ";
    short WS_I = 0;
    IBZACMT_WS_AC_STATUS WS_AC_STATUS = new IBZACMT_WS_AC_STATUS();
    String WS_L_AC_CORP = " ";
    String WS_OD_PAY_CORP = " ";
    String WS_OD_PAY_CORP_TRI = " ";
    String WS_CORP_ID = " ";
    String WS_CORP_TRI_ID = " ";
    char WS_CORP_ATTR = ' ';
    short WS_MTH = 0;
    char WS_TABLE_REC = ' ';
    char WS_CHANGE_FLAG = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICSACAC CICSACAC = new CICSACAC();
    CICSACR CICSACR = new CICSACR();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    IBCNFHIS IBCNFHIO = new IBCNFHIS();
    IBCNFHIS IBCNFHIN = new IBCNFHIS();
    IBCNMINT IBCMINTO = new IBCNMINT();
    IBCNMINT IBCMINTN = new IBCNMINT();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQIDNM IBCQIDNM = new IBCQIDNM();
    IBCCNGL IBCCNGL = new IBCCNGL();
    IBCOACOP IBCOACOP = new IBCOACOP();
    IBCOACMD IBCOACMD = new IBCOACMD();
    IBCPMORG IBCPMORG = new IBCPMORG();
    AICOCKOP AICOCKOP = new AICOCKOP();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    VTCUJMDR VTCUJMDR = new VTCUJMDR();
    IBRMST IBRMST = new IBRMST();
    IBRBALF IBRBALF = new IBRBALF();
    IBRINRH IBRINRH = new IBRINRH();
    IBRSCASH IBRSCASH = new IBRSCASH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCACMT IBCACMT;
    public void MP(SCCGWA SCCGWA, IBCACMT IBCACMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACMT = IBCACMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCACMT.RC.RC_MMO = " ";
        IBCACMT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMT.FUNC);
        if (IBCACMT.FUNC == K_OPEN) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B030_AC_OPEN_PROC();
            if (pgmRtn) return;
        } else if (IBCACMT.FUNC == K_MODIFY) {
            B020_CHECK_INPUT();
            if (pgmRtn) return;
            B040_AC_MODIFY_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCACMT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMT.CIFNO);
        CEP.TRC(SCCGWA, IBCACMT.AC_NO);
        CEP.TRC(SCCGWA, IBCACMT.NOS_CD);
        CEP.TRC(SCCGWA, IBCACMT.CCY);
        CEP.TRC(SCCGWA, IBCACMT.POST_CTR);
        CEP.TRC(SCCGWA, IBCACMT.PROD_CD);
        CEP.TRC(SCCGWA, IBCACMT.AC_ATTR);
        CEP.TRC(SCCGWA, IBCACMT.FUND_ATTR);
        CEP.TRC(SCCGWA, IBCACMT.CORRAC_NO);
        CEP.TRC(SCCGWA, IBCACMT.CORRAC_BK);
        CEP.TRC(SCCGWA, IBCACMT.EFFDATE);
        CEP.TRC(SCCGWA, IBCACMT.PRV_FLAG);
        CEP.TRC(SCCGWA, IBCACMT.RATE_FLAG);
        CEP.TRC(SCCGWA, IBCACMT.RATE_MTH);
        CEP.TRC(SCCGWA, IBCACMT.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCACMT.RATE_TERM);
        CEP.TRC(SCCGWA, IBCACMT.RATE_SPREAD);
        CEP.TRC(SCCGWA, IBCACMT.RATE);
        CEP.TRC(SCCGWA, IBCACMT.CALR_STD);
        CEP.TRC(SCCGWA, IBCACMT.INTS_CYC);
        CEP.TRC(SCCGWA, IBCACMT.INTS_DT_MM);
        CEP.TRC(SCCGWA, IBCACMT.INTS_DT_DD);
        CEP.TRC(SCCGWA, IBCACMT.OD_PAY_AC);
        CEP.TRC(SCCGWA, IBCACMT.OD_RATE);
        CEP.TRC(SCCGWA, IBCACMT.AC_NATR);
        if (IBCACMT.CIFNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.NOS_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOS_CD_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CCY_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.POST_CTR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.PROD_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.PROD_CD_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.CORRAC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.EFFDATE == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFFDATE_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.AC_ATTR == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_TYPE, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.PRV_FLAG == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.PRV_FLAG_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.RATE_FLAG == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_FLAG_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.RATE_FLAG == 'Y') {
            if (IBCACMT.RATE_MTH == '1') {
            } else if (IBCACMT.RATE_MTH == '2') {
                if (IBCACMT.RATE_TYPE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_TYPE_M, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (IBCACMT.RATE_TERM.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_TERM_M, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_MTH_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (IBCACMT.CALR_STD == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (IBCACMT.INTS_CYC == ' ') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_CYC_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        B010_01_CHECK_BR();
        if (pgmRtn) return;
        B010_02_CHECK_CIFNO();
        if (pgmRtn) return;
        B010_03_CHECK_NOSCD_CCY();
        if (pgmRtn) return;
        B010_04_CHECK_PROD_CD();
        if (pgmRtn) return;
        B010_05_CHECK_CORRAC();
        if (pgmRtn) return;
        B010_06_CHECK_EFFDATE();
        if (pgmRtn) return;
        B010_07_CHECK_INTS_DT();
        if (pgmRtn) return;
        B010_08_CHECK_TYPE_TERM();
        if (pgmRtn) return;
        B010_09_CHECK_L_AC();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR == '3') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_OPN_BR, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_02_CHECK_CIFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = IBCACMT.CIFNO;
        CICCUST.FUNC = 'C';
        S000_LINK_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE == 3011) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_NOEXIST, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '3') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIF_NOFI, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.FUNC == K_OPEN) {
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            if (CICCUST.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CLIENT_CLOSED, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_03_CHECK_NOSCD_CCY() throws IOException,SQLException,Exception {
        if (IBCACMT.FUNC == K_OPEN) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = IBCACMT.CCY;
            S000_LINK_SCSOCCY();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, IBRMST);
        IBRMST.NOSTRO_CODE = IBCACMT.NOS_CD;
        IBRMST.CCY = IBCACMT.CCY;
        T000_STARTBR_IBTMST_FIRST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_04_CHECK_PROD_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = IBCACMT.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(K_IB_PROD_MODEL)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_PROD_TYP, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_05_CHECK_CORRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.CORRAC_NO = IBCACMT.CORRAC_NO;
        T000_STARTBR_IBTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = IBRMST.KEY.AC_NO;
            S000_LINK_CIZACCU();
            if (pgmRtn) return;
            if (CICACCU.DATA.CI_NO.equalsIgnoreCase(IBCACMT.CIFNO)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_EXIST_S);
            } else {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_EXIST_D);
            }
        }
    }
    public void B010_06_CHECK_EFFDATE() throws IOException,SQLException,Exception {
        if (IBCACMT.EFFDATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.BACK_VAL, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (IBCACMT.EFFDATE > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.init(SCCGWA, AICOCKOP);
                AICOCKOP.VAL_DATE = IBCACMT.EFFDATE;
                S000_CALL_AIZCKOP();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_07_CHECK_INTS_DT() throws IOException,SQLException,Exception {
        if (IBCACMT.INTS_CYC == '1' 
            || IBCACMT.INTS_CYC == '2' 
            || IBCACMT.INTS_CYC == '3') {
            if (IBCACMT.INTS_DT_MM == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_MM_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                if ((IBCACMT.INTS_DT_MM < 1 
                    || IBCACMT.INTS_DT_MM > 12) 
                    && IBCACMT.INTS_CYC == '1') {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_MM, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if ((IBCACMT.INTS_DT_MM < 1 
                    || IBCACMT.INTS_DT_MM > 6) 
                    && IBCACMT.INTS_CYC == '2') {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_MM, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if ((IBCACMT.INTS_DT_MM < 1 
                    || IBCACMT.INTS_DT_MM > 3) 
                    && IBCACMT.INTS_CYC == '3') {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_MM, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (IBCACMT.INTS_DT_DD == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_DD_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_MTH = IBCACMT.INTS_DT_MM;
                if ((WS_MTH == 2 
                    && (IBCACMT.INTS_DT_DD == 29 
                    || IBCACMT.INTS_DT_DD == 30)) 
                    || (IBCACMT.INTS_CYC == '1' 
                    && (((WS_MTH == 4 
                    || WS_MTH == 6 
                    || WS_MTH == 9 
                    || WS_MTH == 11) 
                    && IBCACMT.INTS_DT_DD == 31) 
                    || ((WS_MTH == 1 
                    || WS_MTH == 3 
                    || WS_MTH == 5 
                    || WS_MTH == 7 
                    || WS_MTH == 8 
                    || WS_MTH == 10 
                    || WS_MTH == 12) 
                    && IBCACMT.INTS_DT_DD == 30)))) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_DD, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (IBCACMT.INTS_CYC == '4' 
            && IBCACMT.INTS_DT_DD == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INTS_DT_DD_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_08_CHECK_TYPE_TERM() throws IOException,SQLException,Exception {
        if (IBCACMT.RATE_MTH == '2' 
            && IBCACMT.RATE_FLAG == 'Y') {
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.FUNC = 'I';
            BPCCINTI.BASE_INFO.BR = IBCACMT.POST_CTR;
            BPCCINTI.BASE_INFO.CCY = IBCACMT.CCY;
            BPCCINTI.BASE_INFO.BASE_TYP = IBCACMT.RATE_TYPE;
            BPCCINTI.BASE_INFO.TENOR = IBCACMT.RATE_TERM;
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
            S00_CALL_BPZCINTI();
            if (pgmRtn) return;
        }
    }
    public void B010_09_CHECK_L_AC() throws IOException,SQLException,Exception {
        if (IBCACMT.AC_ATTR == 'L') {
            B010_09_01_CORRAC();
            if (pgmRtn) return;
            B010_09_02_CORRBK();
            if (pgmRtn) return;
            if (IBCACMT.OD_PAY_AC.trim().length() > 0) {
                B010_09_03_OD_PAY_AC();
                if (pgmRtn) return;
            }
            B010_09_04_OD_CORP();
            if (pgmRtn) return;
            B010_09_05_DD_BAL();
            if (pgmRtn) return;
        }
    }
    public void B010_09_01_CORRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = IBCACMT.CORRAC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
    }
    public void B010_09_02_CORRBK() throws IOException,SQLException,Exception {
        if (IBCACMT.CORRAC_BK.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase("043") 
                && BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NON_VIL_TRA, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_L_AC_CORP = IBCACMT.CORRAC_BK;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            CEP.TRC(SCCGWA, WS_L_AC_CORP);
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            if ((!BPCPQORG.VIL_TYP.equalsIgnoreCase("043") 
                && !WS_L_AC_CORP.equalsIgnoreCase(BPCPQORG.VIL_TYP)) 
                && (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
                && !WS_L_AC_CORP.equalsIgnoreCase(BPCPQORG.TRA_TYP))) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INVALID_CORP, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_09_03_OD_PAY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBCACMT.OD_PAY_AC;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_CLOSE, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!IBCQINF.INPUT_DATA.CCY.equalsIgnoreCase(IBCACMT.CCY)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.OD_PAY_CCY, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_09_04_OD_CORP() throws IOException,SQLException,Exception {
        if (IBCACMT.OD_PAY_AC.trim().length() > 0) {
            IBS.init(SCCGWA, IBCQIDNM);
            IBCQIDNM.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            S000_CALL_IBZQIDNM();
            if (pgmRtn) return;
            WS_OD_PAY_CORP = IBCQIDNM.CORP_ID;
            WS_OD_PAY_CORP_TRI = IBCQIDNM.CORP_TRI_ID;
        }
        IBS.init(SCCGWA, IBCQIDNM);
        IBCQIDNM.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_IBZQIDNM();
        if (pgmRtn) return;
        WS_CORP_ID = IBCQIDNM.CORP_ID;
        WS_CORP_TRI_ID = IBCQIDNM.CORP_TRI_ID;
        WS_CORP_ATTR = IBCQIDNM.CORP_ATTR;
        CEP.TRC(SCCGWA, WS_CORP_ID);
        CEP.TRC(SCCGWA, WS_CORP_TRI_ID);
        CEP.TRC(SCCGWA, WS_CORP_ATTR);
        if ((!WS_OD_PAY_CORP.equalsIgnoreCase(WS_CORP_ID) 
            || !WS_OD_PAY_CORP_TRI.equalsIgnoreCase(WS_CORP_TRI_ID)) 
            && IBCACMT.OD_PAY_AC.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.IB_ERR_BK_NOT_SAME, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_09_05_DD_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = IBCACMT.CORRAC_NO;
        DDCIQBAL.DATA.CCY = IBCACMT.CCY;
        if (IBCACMT.CCY.equalsIgnoreCase("156")) {
            DDCIQBAL.DATA.CCY_TYPE = '1';
        } else {
            DDCIQBAL.DATA.CCY_TYPE = '2';
        }
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        if (DDCIQBAL.DATA.CURR_BAL_TOT != 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.L_NOT_ZERO, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMT.CIFNO);
        CEP.TRC(SCCGWA, IBCACMT.AC_NO);
        CEP.TRC(SCCGWA, IBCACMT.NOS_CD);
        CEP.TRC(SCCGWA, IBCACMT.CCY);
        CEP.TRC(SCCGWA, IBCACMT.CORRAC_NO);
        CEP.TRC(SCCGWA, IBCACMT.CORRAC_BK);
        CEP.TRC(SCCGWA, IBCACMT.OD_PAY_AC);
        if (IBCACMT.CIFNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            B010_02_CHECK_CIFNO();
            if (pgmRtn) return;
        }
        if ((IBCACMT.NOS_CD.trim().length() == 0 
            || IBCACMT.CCY.trim().length() == 0) 
            && IBCACMT.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACMT.CORRAC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_M, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_01_GET_AC_INFO();
        if (pgmRtn) return;
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCACMT.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCACMT.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCACMT.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCACMT.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_ATTR);
    }
    public void B020_02_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG != 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B030_AC_OPEN_PROC() throws IOException,SQLException,Exception {
        B030_01_GEN_AC_NO();
        if (pgmRtn) return;
        B030_02_WEITE_BALF();
        if (pgmRtn) return;
        B030_03_WRITE_INRH();
        if (pgmRtn) return;
        B030_04_WRITE_NHIS();
        if (pgmRtn) return;
        B030_05_INQ_GL();
        if (pgmRtn) return;
        B030_06_WRITE_GL();
        if (pgmRtn) return;
        B030_07_SET_DDAC_STS();
        if (pgmRtn) return;
        B030_08_WRITE_SCASH();
        if (pgmRtn) return;
        B030_09_WRITE_MST();
        if (pgmRtn) return;
        B030_10_SEND_CI_OPEN();
        if (pgmRtn) return;
        B030_11_WRITE_BPTOCAC_PROC();
        if (pgmRtn) return;
        B030_12_OPEN_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_AC_MODIFY_PROC() throws IOException,SQLException,Exception {
        B040_01_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B040_02_WRITE_NHIS();
        if (pgmRtn) return;
        B040_03_REWRITE_SCASH();
        if (pgmRtn) return;
        B040_04_REWRITE_TMST();
        if (pgmRtn) return;
        B040_05_SEND_CI_MOD();
        if (pgmRtn) return;
        B040_06_MOD_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_01_GEN_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '1';
        BPCCGAC.DATA.CI_AC_FLG = '7';
        BPCCGAC.DATA.CI_AC_TYPE = '1';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        WS_AC_NO = BPCCGAC.DATA.CI_AC;
        CEP.TRC(SCCGWA, WS_AC_NO);
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = WS_AC_NO;
        T000_READ_IBTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_EXIST, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBCACMT.AC_NO = WS_AC_NO;
        }
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_MMO = "16";
        BPCCGAC.DATA.ACO_AC_DEF = 100;
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        WS_AC_NO1 = BPCCGAC.DATA.ACO_AC;
        CEP.TRC(SCCGWA, WS_AC_NO1);
        IBS.init(SCCGWA, IBRMST);
        IBRMST.ACO_AC = WS_AC_NO1;
        T000_READ_IBTMST_FIRST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_ACO);
        } else {
            IBCACMT.ACO_AC = WS_AC_NO1;
        }
    }
    public void B030_02_WEITE_BALF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRBALF);
        IBRBALF.KEY.AC_NO = IBCACMT.AC_NO;
        IBRBALF.KEY.BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRBALF.BAL = 0;
        IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_IBTBALF();
        if (pgmRtn) return;
    }
    public void B030_03_WRITE_INRH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCACMT.AC_NO;
        IBRINRH.PRV_FLAG = IBCACMT.PRV_FLAG;
        IBRINRH.RATE_FLAG = IBCACMT.RATE_FLAG;
        IBRINRH.KEY.VALUE_DATE = IBCACMT.EFFDATE;
        IBRINRH.RATE_MTH = IBCACMT.RATE_MTH;
        IBRINRH.RATE_TYPE = IBCACMT.RATE_TYPE;
        IBRINRH.RATE_TERM = IBCACMT.RATE_TERM;
        IBRINRH.RATE_PCT = IBCACMT.RATE_PCT;
        IBRINRH.RATE_SPREAD = IBCACMT.RATE_SPREAD;
        if (IBCACMT.RATE_FLAG == 'Y') {
            if (IBCACMT.RATE_MTH == '1') {
                IBRINRH.INT_RATE = IBCACMT.RATE;
            } else {
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
                if (IBRINRH.RATE_SPREAD != 0) {
                    IBRINRH.INT_RATE = BPCCINTI.BASE_INFO.RATE + IBRINRH.RATE_SPREAD;
                } else {
                    IBRINRH.INT_RATE = BPCCINTI.BASE_INFO.RATE * ( 1 + IBRINRH.RATE_PCT / 100 );
                }
            }
        }
        IBRINRH.OD_RATE = IBCACMT.OD_RATE;
        IBRINRH.CALR_STD = IBCACMT.CALR_STD;
        IBRINRH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINRH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRINRH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINRH.UPD_TIME = SCCGWA.COMM_AREA.AC_DATE;
        IBRINRH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_IBTINRH();
        if (pgmRtn) return;
    }
    public void B030_04_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCMINTO);
        IBS.init(SCCGWA, IBCMINTN);
        IBCMINTN.NOST_CODE = IBCACMT.NOS_CD;
        IBCMINTN.CCY = IBCACMT.CCY;
        IBCMINTN.AC_NO = IBCACMT.AC_NO;
        IBCMINTN.PRV_FLAG = IBCACMT.PRV_FLAG;
        IBCMINTN.RATE_FLAG = IBCACMT.RATE_FLAG;
        IBCMINTN.RATE_MTH = IBCACMT.RATE_MTH;
        IBCMINTN.RATE_TYPE = IBCACMT.RATE_TYPE;
        IBCMINTN.RATE_TERM = IBCACMT.RATE_TERM;
        IBCMINTN.RATE_PCT = IBCACMT.RATE_PCT;
        IBCMINTN.RATE_SPREAD = IBCACMT.RATE_SPREAD;
        IBCMINTN.CALR_STD = IBCACMT.CALR_STD;
        IBCMINTN.RATE = IBCACMT.RATE;
        IBCMINTN.OD_RATE = IBCACMT.OD_RATE;
        IBCMINTN.EFFDATE = IBCACMT.EFFDATE;
        IBCMINTN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCMINTN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, IBCNFHIO);
        IBS.init(SCCGWA, IBCNFHIN);
        IBCNFHIN.NOSTRO_CD = IBCACMT.NOS_CD;
        IBCNFHIN.CCY = IBCACMT.CCY;
        IBCNFHIN.AC_NO = IBCACMT.AC_NO;
        IBCNFHIN.NOSTRO_NM = IBCACMT.CUSTNME;
        IBCNFHIN.STATUS = K_STATUS_NORMAL;
        IBCNFHIN.POST_ACT_CTR = IBCACMT.POST_CTR;
        IBCNFHIN.PROD_CD = IBCACMT.PROD_CD;
        IBCNFHIN.OIC_NO = IBCACMT.OIC_NO;
        IBCNFHIN.RESP_CD = IBCACMT.RESP_CD;
        IBCNFHIN.SUB_DP = IBCACMT.SUB_DP;
        IBCNFHIN.CORRAC_NO = IBCACMT.CORRAC_NO;
        IBCNFHIN.CORRAC_BK = IBCACMT.CORRAC_BK;
        IBCNFHIN.FUND_ATTR = IBCACMT.FUND_ATTR;
        IBCNFHIN.OD_PAY_AC = IBCACMT.OD_PAY_AC;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCACMT.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCACMT.CIFNO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = "0165013";
        BPCPNHIS.INFO.TX_TYP_CD = "P700";
        BPCPNHIS.INFO.CCY = IBCACMT.CCY;
        if (IBCACMT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.CI_NO = IBCACMT.CIFNO;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCNFHIS";
        BPCPNHIS.INFO.FMT_ID_LEN = 468;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNFHIO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNFHIN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_05_INQ_GL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, IBCCNGL);
        if (BPCPQPRD.STOP_SOLD_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.PRO_STOP_SOLD, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        IBCCNGL.PROD_TYPE = IBCACMT.PROD_CD;
        IBCCNGL.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        IBCCNGL.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        if (IBCACMT.FUND_ATTR != '0') {
            IBCCNGL.PROP_TYP = IBCACMT.FUND_ATTR;
        }
        BPCQCNGL.DAT.INPUT.OTH_PTR = IBCCNGL;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B030_06_WRITE_GL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = IBRMST.ACO_AC;
        BPCUCNGM.PROD_TYPE = IBCACMT.PROD_CD;
        BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTCUJMDR);
        VTCUJMDR.INPUT_DATA.AC = IBRMST.ACO_AC;
        VTCUJMDR.INPUT_DATA.PROD_CD = IBCACMT.PROD_CD;
        VTCUJMDR.INPUT_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCUJMDR.INPUT_DATA.CCY = IBCACMT.CCY;
        VTCUJMDR.INPUT_DATA.OTH.OVER_FLG = CICCUST.O_DATA.O_SID_FLG;
        if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
        if (CICCUST.O_DATA.O_IDE_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            VTCUJMDR.INPUT_DATA.OTH.NCB_FLG = 'Y';
        }
        VTCUJMDR.INPUT_DATA.FUNC = 'A';
        CEP.TRC(SCCGWA, VTCUJMDR.INPUT_DATA.OTH.OVER_FLG);
        CEP.TRC(SCCGWA, VTCUJMDR.INPUT_DATA.OTH.NCB_FLG);
        if (VTCUJMDR.INPUT_DATA.OTH.OVER_FLG != ' ' 
            || VTCUJMDR.INPUT_DATA.OTH.NCB_FLG != ' ') {
            S000_CALL_VTZUJMDR();
            if (pgmRtn) return;
        }
    }
    public void B030_07_SET_DDAC_STS() throws IOException,SQLException,Exception {
        if (IBCACMT.AC_ATTR == 'L') {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = IBCACMT.CORRAC_NO;
            DDCIMMST.DATA.STS_CD = "61";
            DDCIMMST.TX_TYPE = 'S';
            DDCIMMST.DATA.SET_FLG = 'O';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
        }
    }
    public void B030_08_WRITE_SCASH() throws IOException,SQLException,Exception {
        if (IBCACMT.AC_ATTR == 'L') {
            CEP.TRC(SCCGWA, WS_CORP_ID);
            CEP.TRC(SCCGWA, WS_CORP_TRI_ID);
            CEP.TRC(SCCGWA, WS_CORP_ATTR);
            IBS.init(SCCGWA, IBRSCASH);
            IBRSCASH.KEY.CORP_ID = WS_CORP_ID;
            IBRSCASH.KEY.CORP_TRI_ID = WS_CORP_TRI_ID;
            IBRSCASH.KEY.CCY = IBCACMT.CCY;
            T000_READ_IBTSCASH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                if (IBRSCASH.AC_NO.equalsIgnoreCase(IBCACMT.AC_NO)) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.BANK_EXIST_S, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.BANK_EXIST_D, IBCACMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBRSCASH.CORP_ATTR = WS_CORP_ATTR;
                IBRSCASH.AC_NO = IBCACMT.AC_NO;
                IBRSCASH.VOSTRO_AC = IBCACMT.CORRAC_NO;
                IBRSCASH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRSCASH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRSCASH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRSCASH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                IBRSCASH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_IBTSCASH();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_09_WRITE_MST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCACMT.AC_NO;
        IBRMST.ACO_AC = IBCACMT.ACO_AC;
        IBRMST.NOSTRO_CODE = IBCACMT.NOS_CD;
        IBRMST.CCY = IBCACMT.CCY;
        IBRMST.PROD_CD = IBCACMT.PROD_CD;
        IBRMST.POST_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBRMST.OIC_NO = IBCACMT.OIC_NO;
        IBRMST.RESP_CD = IBCACMT.RESP_CD;
        IBRMST.SUB_DP = IBCACMT.SUB_DP;
        IBRMST.AC_STS = 'N';
        IBRMST.AC_STS_WORD = K_STATUS_NORMAL;
        IBRMST.AC_ATTR = IBCACMT.AC_ATTR;
        IBRMST.FUND_ATTR = IBCACMT.FUND_ATTR;
        IBRMST.AC_NATR = IBCACMT.AC_NATR;
        IBRMST.OD_PAY_AC = IBCACMT.OD_PAY_AC;
        IBRMST.CORRAC_NO = IBCACMT.CORRAC_NO;
        IBRMST.CORRAC_BK = IBCACMT.CORRAC_BK;
        IBRMST.PRV_FLAG = IBCACMT.PRV_FLAG;
        IBRMST.RATE_FLAG = IBCACMT.RATE_FLAG;
        if (IBCACMT.AC_ATTR == 'N') {
            IBRMST.RATE_MTH = IBCACMT.RATE_MTH;
            IBRMST.RATE_TYPE = IBCACMT.RATE_TYPE;
            IBRMST.RATE_TERM = IBCACMT.RATE_TERM;
            IBRMST.RATE_PCT = IBCACMT.RATE_PCT;
            IBRMST.RATE_SPREAD = IBCACMT.RATE_SPREAD;
            IBRMST.RATE = IBCACMT.RATE;
            IBRMST.CALR_STD = IBCACMT.CALR_STD;
            IBRMST.INTS_CYC = IBCACMT.INTS_CYC;
            IBRMST.INTS_DT_MM = IBCACMT.INTS_DT_MM;
            IBRMST.INTS_DT_DD = IBCACMT.INTS_DT_DD;
        }
        IBRMST.OD_FLAG = IBCACMT.OD_FLAG;
        IBRMST.OD_RATE_FLAG = IBCACMT.OD_RATE_FLAG;
        IBRMST.EFF_DATE = IBCACMT.EFFDATE;
        IBRMST.SWR_BR = IBCACMT.SWR_BR;
        IBRMST.AC_USE = IBCACMT.AC_USE;
        IBRMST.RMK = IBCACMT.RMK;
        IBRMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBRMST.OWNER_BK = SCCGWA.COMM_AREA.TR_BANK;
        IBRMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMST.CLOS_DATE = 99991231;
        IBRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        IBRMST.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, IBRMST.AUTH_TLR);
        T000_WRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B030_10_SEND_CI_OPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.CI_NO = IBCACMT.CIFNO;
        CICSACR.DATA.STSW = K_STSW;
        CEP.TRC(SCCGWA, CICSACR.DATA.STSW);
        CICSACR.DATA.AC_CNM = IBCACMT.CUSTNME;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) CICSACR.DATA.OWNER_BK = 0;
        else CICSACR.DATA.OWNER_BK = Integer.parseInt(SCCGWA.COMM_AREA.TR_BANK);
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.AGR_NO = IBCACMT.AC_NO;
        CICSACR.DATA.FRM_APP = "IB";
        CICSACR.DATA.PROD_CD = IBCACMT.PROD_CD;
        CICSACR.DATA.CNTRCT_TYP = "IBDD";
        CICSACR.DATA.CCY = IBCACMT.CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_LINK_CIZSACR();
        if (pgmRtn) return;
        B030_10_01_SEND_CI_OPEN();
        if (pgmRtn) return;
    }
    public void B030_10_01_SEND_CI_OPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.ACAC_NO = IBCACMT.ACO_AC;
        CICSACAC.DATA.AGR_NO = IBCACMT.AC_NO;
        CICSACAC.DATA.CCY = IBCACMT.CCY;
        CICSACAC.DATA.CR_FLG = '1';
        CICSACAC.DATA.PROD_CD = IBCACMT.PROD_CD;
        CICSACAC.DATA.ACAC_CTL = K_STSW1;
        CICSACAC.DATA.FRM_APP = "IB";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) CICSACAC.DATA.OWNER_BK = 0;
        else CICSACAC.DATA.OWNER_BK = Integer.parseInt(SCCGWA.COMM_AREA.TR_BANK);
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACAC.DATA.NOSEQ_FLG = 'Y';
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B030_11_WRITE_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = IBRMST.KEY.AC_NO;
        BPCSOCAC.ACO_AC = IBRMST.ACO_AC;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "24";
        BPCSOCAC.CI_TYPE = '3';
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.CCY = IBRMST.CCY;
        if (IBRMST.CCY.equalsIgnoreCase("156")) {
            BPCSOCAC.CCY_TYPE = '1';
        } else {
            BPCSOCAC.CCY_TYPE = '2';
        }
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.PROD_CD = IBRMST.PROD_CD;
        BPCSOCAC.OPEN_RAT = IBRINRH.INT_RATE;
        BPCSOCAC.AC_CNM = IBCACMT.CUSTNME;
        BPCSOCAC.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, BPCSOCAC.AUT_TLR);
        BPCSOCAC.BR = IBRMST.OPEN_BR;
        CEP.TRC(SCCGWA, IBRMST.OPEN_BR);
        CEP.TRC(SCCGWA, BPCSOCAC.BR);
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B030_12_OPEN_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOACOP);
        IBCOACOP.CI_NO = IBCACMT.CIFNO;
        IBCOACOP.AC_NO = IBRMST.KEY.AC_NO;
        IBCOACOP.NOSTR_CD = IBRMST.NOSTRO_CODE;
        IBCOACOP.CCY = IBRMST.CCY;
        IBCOACOP.CUSTNME = IBCACMT.CUSTNME;
        IBCOACOP.PROD_CD = IBRMST.PROD_CD;
        IBCOACOP.POST_CTR = IBRMST.POST_CTR;
        IBCOACOP.AC_STS = IBRMST.AC_STS;
        IBCOACOP.AC_ATTR = IBRMST.AC_ATTR;
        IBCOACOP.FUND_ATTR = IBRMST.FUND_ATTR;
        IBCOACOP.AC_NATR = IBRMST.AC_NATR;
        IBCOACOP.CORRAC_NO = IBRMST.CORRAC_NO;
        IBCOACOP.CORRAC_BK = IBRMST.CORRAC_BK;
        IBCOACOP.PRV_FLAG = IBRMST.PRV_FLAG;
        IBCOACOP.RATE_FLAG = IBRMST.RATE_FLAG;
        IBCOACOP.RATE_MTH = IBRMST.RATE_MTH;
        IBCOACOP.RATE_TYPE = IBRMST.RATE_TYPE;
        IBCOACOP.RATE_TERM = IBRMST.RATE_TERM;
        IBCOACOP.RATE_PCT = IBRMST.RATE_PCT;
        IBCOACOP.RATE_SPREAD = IBRMST.RATE_SPREAD;
        IBCOACOP.RATE = IBRMST.RATE;
        if (IBRMST.CALR_STD == K_360_DAYS) {
            IBCOACOP.CALR_STD = "01";
        } else if (IBRMST.CALR_STD == K_365_DAYS) {
            IBCOACOP.CALR_STD = "02";
        } else if (IBRMST.CALR_STD == K_366_DAYS) {
            IBCOACOP.CALR_STD = "03";
        }
        IBCOACOP.INTS_CYC = IBRMST.INTS_CYC;
        IBCOACOP.INTS_DT_MM = IBRMST.INTS_DT_MM;
        IBCOACOP.INTS_DT_DD = IBRMST.INTS_DT_DD;
        IBCOACOP.OD_PAY_AC = IBRMST.OD_PAY_AC;
        IBCOACOP.OD_FLAG = IBRMST.OD_FLAG;
        IBCOACOP.OD_RATE_FLAG = IBRMST.OD_RATE_FLAG;
        IBCOACOP.OD_RATE = IBRMST.OD_RATE;
        IBCOACOP.OD_INTS_CYC = IBRMST.OD_INTS_CYC;
        IBCOACOP.EFF_DATE = IBRMST.EFF_DATE;
        IBCOACOP.OPEN_DATE = IBRMST.OPEN_DATE;
        IBCOACOP.OPEN_BR = IBRMST.OPEN_BR;
        IBCOACOP.OWNER_BK = IBRMST.OWNER_BK;
        IBCOACOP.OPEN_TLR = IBRMST.OPEN_TLR;
        IBCOACOP.OIC_NO = IBRMST.OIC_NO;
        IBCOACOP.RESP_CD = IBRMST.RESP_CD;
        IBCOACOP.SUB_DP = IBRMST.SUB_DP;
        IBCOACOP.AUTH_TLR = IBRMST.AUTH_TLR;
        IBCOACOP.SWR_BR_FLG = IBRMST.SWR_BR;
        IBCOACOP.AC_USE = IBRMST.AC_USE;
        IBCOACOP.RMK = IBRMST.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_O;
        SCCFMT.DATA_PTR = IBCOACOP;
        SCCFMT.DATA_LEN = 789;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_01_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCACMT.AC_NO;
        T000_READ_IBTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_02_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCNFHIO);
        IBCNFHIO.NOSTRO_CD = IBRMST.NOSTRO_CODE;
        IBCNFHIO.CCY = IBRMST.CCY;
        IBCNFHIO.AC_NO = IBRMST.KEY.AC_NO;
        IBCNFHIO.NOSTRO_NM = IBCACMT.CUSTNME;
        IBCNFHIO.OIC_NO = IBRMST.OIC_NO;
        IBCNFHIO.RESP_CD = IBRMST.RESP_CD;
        IBCNFHIO.SUB_DP = IBRMST.SUB_DP;
        IBCNFHIO.CORRAC_NO = IBRMST.CORRAC_NO;
        IBCNFHIO.CORRAC_BK = IBRMST.CORRAC_BK;
        IBCNFHIO.OD_PAY_AC = IBRMST.OD_PAY_AC;
        IBS.init(SCCGWA, IBCNFHIN);
        IBCNFHIN.NOSTRO_CD = IBCACMT.NOS_CD;
        IBCNFHIN.CCY = IBCACMT.CCY;
        IBCNFHIN.AC_NO = IBCACMT.AC_NO;
        IBCNFHIN.NOSTRO_NM = IBCACMT.CUSTNME;
        IBCNFHIN.OIC_NO = IBCACMT.OIC_NO;
        IBCNFHIN.RESP_CD = IBCACMT.RESP_CD;
        IBCNFHIN.SUB_DP = IBCACMT.SUB_DP;
        IBCNFHIN.CORRAC_NO = IBCACMT.CORRAC_NO;
        IBCNFHIN.CORRAC_BK = IBCACMT.CORRAC_BK;
        IBCNFHIN.OD_PAY_AC = IBCACMT.OD_PAY_AC;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBRMST.KEY.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "P701";
        BPCPNHIS.INFO.TX_CD = "0165012";
        BPCPNHIS.INFO.CCY = IBCACMT.CCY;
        if (IBCACMT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCNFHIS";
        BPCPNHIS.INFO.FMT_ID_LEN = 468;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNFHIO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNFHIN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_03_REWRITE_SCASH() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L' 
            && !IBCACMT.CORRAC_NO.equalsIgnoreCase(IBCQINF.OUTPUT_DATA.CORRAC_NO)) {
            IBS.init(SCCGWA, IBRSCASH);
            IBRSCASH.VOSTRO_AC = IBCACMT.CORRAC_NO;
            T000_READ_SCASH_FIRST();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.VOSTRO_AC_M, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = IBCACMT.CORRAC_NO;
            DDCIQBAL.DATA.CCY = IBRMST.CCY;
            if (IBRMST.CCY.equalsIgnoreCase("156")) {
                DDCIQBAL.DATA.CCY_TYPE = '1';
            } else {
                DDCIQBAL.DATA.CCY_TYPE = '2';
            }
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL_TOT);
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            if (DDCIQBAL.DATA.CURR_BAL_TOT != 0 
                || IBRMST.VALUE_BAL != 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_INUSE, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, IBRSCASH);
            IBRSCASH.AC_NO = IBRMST.KEY.AC_NO;
            CEP.TRC(SCCGWA, IBRSCASH.AC_NO);
            T000_STARTBR_UPD_SCASH();
            if (pgmRtn) return;
            T000_READNEXT_IBTSCASH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_SCASH, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            if (IBRSCASH.TXN_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_INUSE, IBCACMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBRSCASH.VOSTRO_AC = IBCACMT.CORRAC_NO;
            T000_REWRITE_IBTSCASH();
            if (pgmRtn) return;
        }
    }
    public void B040_04_REWRITE_TMST() throws IOException,SQLException,Exception {
        IBRMST.NOSTRO_CODE = IBCACMT.NOS_CD;
        IBRMST.AC_NATR = IBCACMT.AC_NATR;
        IBRMST.CORRAC_NO = IBCACMT.CORRAC_NO;
        IBRMST.CORRAC_BK = IBCACMT.CORRAC_BK;
        IBRMST.OD_PAY_AC = IBCACMT.OD_PAY_AC;
        IBRMST.OIC_NO = IBCACMT.OIC_NO;
        IBRMST.SWR_BR = IBCACMT.SWR_BR;
        IBRMST.RESP_CD = IBCACMT.RESP_CD;
        CEP.TRC(SCCGWA, IBCACMT.SUB_DP);
        IBRMST.SUB_DP = IBCACMT.SUB_DP;
        CEP.TRC(SCCGWA, IBRMST.SUB_DP);
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B040_05_SEND_CI_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.AGR_NO = IBCACMT.AC_NO;
        CICSACR.DATA.CI_NO = IBCACMT.CIFNO;
        CICSACR.DATA.AC_CNM = IBCACMT.CUSTNME;
        CICSACR.DATA.FRM_APP = "IB";
        S000_LINK_CIZSACR();
        if (pgmRtn) return;
    }
    public void B040_06_MOD_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOACMD);
        IBCOACMD.CI_NO = IBCACMT.CIFNO;
        IBCOACMD.AC_NO = IBRMST.KEY.AC_NO;
        IBCOACMD.NOSTR_CD = IBRMST.NOSTRO_CODE;
        IBCOACMD.CCY = IBRMST.CCY;
        IBCOACMD.CUSTNME = IBCACMT.CUSTNME;
        IBCOACMD.PROD_CD = IBRMST.PROD_CD;
        IBCOACMD.POST_CTR = IBRMST.POST_CTR;
        IBCOACMD.AC_ATTR = IBRMST.AC_ATTR;
        IBCOACMD.FUND_ATTR = IBRMST.FUND_ATTR;
        IBCOACMD.AC_NATR = IBRMST.AC_NATR;
        IBCOACMD.CORRAC_NO = IBRMST.CORRAC_NO;
        IBCOACMD.CORRAC_BK = IBRMST.CORRAC_BK;
        IBCOACMD.OD_PAY_AC = IBRMST.OD_PAY_AC;
        IBCOACMD.EFF_DATE = IBRMST.EFF_DATE;
        IBCOACMD.OPEN_DATE = IBRMST.OPEN_DATE;
        IBCOACMD.LASTDATE = IBRMST.LAST_FI_DATE;
        IBCOACMD.UPD_DATE = IBRMST.UPD_DATE;
        IBCOACMD.OIC_NO = IBRMST.OIC_NO;
        IBCOACMD.SWR_BR_FLG = IBRMST.SWR_BR;
        IBCOACMD.RESP_CD = IBRMST.RESP_CD;
        IBCOACMD.SUB_DP = IBRMST.SUB_DP;
        IBCOACMD.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_M;
        SCCFMT.DATA_PTR = IBCOACMD;
        SCCFMT.DATA_LEN = 484;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_LINK_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0 
            && CICCUST.RC.RC_CODE != 3011) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_SCSOCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZUJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-JMAC-REGISTER", VTCUJMDR);
        if (VTCUJMDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCUJMDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQIDNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-ID-INQ", IBCQIDNM);
        if (IBCQIDNM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQIDNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "CORRAC_NO = :IBRMST.CORRAC_NO";
        IBTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTMST_FIRST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND CCY = :IBRMST.CCY";
        IBTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_FIRST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "ACO_AC = :IBRMST.ACO_AC";
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_UPD() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.WRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBS.READ(SCCGWA, IBRSCASH, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBS.WRITE(SCCGWA, IBRSCASH, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SCASH_FIRST() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "VOSTRO_AC = :IBRSCASH.VOSTRO_AC";
        IBTSCASH_RD.fst = true;
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_UPD_SCASH() throws IOException,SQLException,Exception {
        IBTSCASH_BR.rp = new DBParm();
        IBTSCASH_BR.rp.TableName = "IBTSCASH";
        IBTSCASH_BR.rp.where = "AC_NO = :IBRSCASH.AC_NO";
        IBTSCASH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
    }
    public void T000_READNEXT_IBTSCASH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBS.REWRITE(SCCGWA, IBRSCASH, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.WRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBS.WRITE(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY, IBCACMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
