package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSCI {
    int JIBS_tmp_int;
    DBParm LNTAPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMKS = " ";
    String WS_ERR_MSG = " ";
    double WS_INT_AMT = 0;
    char WS_RTN_INT_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICAL LNCICAL = new LNCICAL();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNCSINTA LNCSINTA = new LNCSINTA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCICUT LNCICUT = new LNCICUT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSSCI LNCSSCI;
    public void MP(SCCGWA SCCGWA, LNCSSCI LNCSSCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSCI = LNCSSCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B030_MAIN_PROC();
        if (pgmRtn) return;
        B040_CALL_PLAJ();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSSCI.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSCI.CTA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSSCI.CTA;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        if (LNCSSCI.SC_DT < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1677, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNCSSCI.SC_DT);
        if (LNCSSCI.SC_DT < LNRCONT.START_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SC_DT_LESS, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSSCI.CTA;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_SYR, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_D53, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_XBD, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSCI.SC_DT < LNRICTL.INT_CUT_DT 
            || LNCSSCI.SC_DT < LNRICTL.NEXT_LC_CAL_DAT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAL_DAT, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSSCI.CTA;
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
        if (LNRAPRD.PAY_MTH == '0' 
            && LNRCONT.MAT_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_PAY_MTH, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCSSCI.FUNC == 'S' 
            && LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SSCI_STS, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCSSCI.FUNC == 'C' 
            && LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SSCI_STS2, LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_CALL_PLAJ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLAJ);
        if (LNRAPRD.PAY_MTH == '4') {
            LNCPLAJ.COMM_DATA.ADJ_IND = 'S';
            LNCPLAJ.COMM_DATA.LN_AC = LNCSSCI.CTA;
            LNCPLAJ.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCPLAJ.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCPLAJ.COMM_DATA.SUF_NO = "0" + LNCPLAJ.COMM_DATA.SUF_NO;
            S000_CALL_LNZPLAJ();
            if (pgmRtn) return;
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_WTRITE_HIS_PROC();
        if (pgmRtn) return;
        if (LNCSSCI.FUNC == 'S') {
            if (LNCSSCI.SC_DT == 0) {
                LNCSSCI.SC_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
                IBS.init(SCCGWA, LNCLOANM);
                LNCLOANM.FUNC = '4';
                LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSSCI.CTA;
                S000_CALL_LNZLOANM();
                if (pgmRtn) return;
                if (LNCLOANM.REC_DATA.STOP_DUE_DT != 0 
                    && SCCGWA.COMM_AREA.AC_DATE < LNCLOANM.REC_DATA.STOP_DUE_DT) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1689, LNCSSCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                LNCLOANM.FUNC = '2';
                LNCLOANM.REC_DATA.STOP_INT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                LNCLOANM.REC_DATA.STOP_VAL_DT = LNCSSCI.SC_DT;
                if (LNCLOANM.REC_DATA.STOP_DUE_DT != 0 
                    && LNCLOANM.REC_DATA.STOP_VAL_DT > LNCLOANM.REC_DATA.STOP_DUE_DT) {
                    LNCLOANM.REC_DATA.STOP_DUE_DT = 0;
                }
                S000_CALL_LNZLOANM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_VAL_DT);
            } else {
                IBS.init(SCCGWA, LNRDISC);
                IBS.init(SCCGWA, LNCRDISC);
                LNCRDISC.FUNC = 'R';
                LNRDISC.KEY.CONTRACT_NO = LNCSSCI.CTA;
                S000_CALL_LNZRDISC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRDISC.STOP_VAL_DT);
                if (LNRDISC.STOP_VAL_DT != 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1658, LNCSSCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                LNRDISC.STOP_INT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                LNRDISC.STOP_VAL_DT = LNCSSCI.SC_DT;
                LNCRDISC.FUNC = 'U';
                CEP.TRC(SCCGWA, "ZZZZZZZZZZZZZZZ");
                S000_CALL_LNZRDISC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "YYYYYYYYYYYYYYY");
            }
            IBS.init(SCCGWA, LNCRICTL);
            IBS.init(SCCGWA, LNRICTL);
            LNCRICTL.FUNC = 'R';
            LNRICTL.KEY.CONTRACT_NO = LNCSSCI.CTA;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            LNCRICTL.FUNC = 'U';
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 6 - 1) + "1" + LNRICTL.CTL_STSW.substring(6 + 1 - 1);
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
        }
        if (LNCSSCI.FUNC == 'C') {
            CEP.TRC(SCCGWA, "333333333333");
            if (LNCSSCI.SC_DT == 0) {
                LNCSSCI.SC_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
                IBS.init(SCCGWA, LNCLOANM);
                LNCLOANM.FUNC = '4';
                LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSSCI.CTA;
                S000_CALL_LNZLOANM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCSSCI.SC_DT);
                CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_VAL_DT);
                if (LNCSSCI.SC_DT < LNCLOANM.REC_DATA.STOP_VAL_DT) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DT_LESS_STOPDT, LNCSSCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                LNCLOANM.FUNC = '2';
                LNCLOANM.REC_DATA.STOP_DUE_DT = LNCSSCI.SC_DT;
                S000_CALL_LNZLOANM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.STOP_DUE_DT);
            } else {
                IBS.init(SCCGWA, LNRDISC);
                IBS.init(SCCGWA, LNCRDISC);
                LNCRDISC.FUNC = 'R';
                LNRDISC.KEY.CONTRACT_NO = LNCSSCI.CTA;
                S000_CALL_LNZRDISC();
                if (pgmRtn) return;
                if (LNCSSCI.SC_DT < LNRDISC.STOP_VAL_DT) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DT_LESS_STOPDT, LNCSSCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                LNCRDISC.FUNC = 'U';
                LNRDISC.STOP_DUE_DT = LNCSSCI.SC_DT;
                S000_CALL_LNZRDISC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNCRICTL);
            IBS.init(SCCGWA, LNRICTL);
            LNCRICTL.FUNC = 'R';
            LNRICTL.KEY.CONTRACT_NO = LNCSSCI.CTA;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            LNCRICTL.FUNC = 'U';
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 6 - 1) + "0" + LNRICTL.CTL_STSW.substring(6 + 1 - 1);
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(6 - 1, 6 + 1 - 1));
        }
    }
    public void R000_WTRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.REF_NO = LNCSSCI.CTA;
        BPCPNHIS.INFO.AC = LNCSSCI.CTA;
        BPCPNHIS.INFO.CI_NO = LNCSSCI.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "LNCHUEXT";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (LNCSSCI.FUNC == 'S') {
            K_HIS_RMKS = "SET STOP RATN DATE";
        } else {
            K_HIS_RMKS = "CAN STOP RATN DATE";
        }
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID_LEN = 427;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSSCI;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSSCI;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        LNCLOANM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PLPI-AUTADJ", LNCPLAJ);
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLAJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRDISC() throws IOException,SQLException,Exception {
        LNRDISC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRDISC.REC_PTR = LNRDISC;
        LNCRDISC.REC_LEN = 258;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTDISC", LNCRDISC);
        CEP.TRC(SCCGWA, LNCRDISC.RC.RC_CODE);
        if (LNCRDISC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRDISC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-CUT", LNCICUT);
        if (LNCICUT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSINTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-ADJ-INTA", LNCSINTA);
        if (LNCSINTA.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSINTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        if (LNCSSCI.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSSCI=");
            CEP.TRC(SCCGWA, LNCSSCI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
