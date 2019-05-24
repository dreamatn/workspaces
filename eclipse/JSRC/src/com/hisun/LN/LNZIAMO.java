package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIAMO {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_PRINCIPAL = 'P';
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_IDX = 0;
    int WS_AMOR_VAL_DATE = 0;
    int WS_AMOR_END_DATE = 0;
    int WS_UNAMOR_DAYS = 0;
    int WS_AMOR_DAYS = 0;
    double WS_TODAY_AMOR_INT = 0;
    double WS_UN_AMORTISE_INT = 0;
    double WS_REPAID_INT = 0;
    String WS_INPUT_CCY = " ";
    double WS_INPUT_AMT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FIRST_AMORTISE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNBU LNCCNBU = new LNCCNBU();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    LNRDISC LNRDISC = new LNRDISC();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCBATH SCCBATH;
    LNCIAMO LNCIAMO;
    public void MP(SCCGWA SCCGWA, LNCIAMO LNCIAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIAMO = LNCIAMO;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIAMO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        LNCIAMO.RC.RC_APP = "LN";
        LNCIAMO.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B100_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START.....");
        CEP.TRC(SCCGWA, LNCIAMO.DATA.CONTRACT_NO);
        if (LNCIAMO.DATA.CONTRACT_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, LNCIAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_GET_CONT_INFO();
        if (pgmRtn) return;
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            R000_GET_LOAN_INFO();
            if (pgmRtn) return;
        } else {
            R000_GET_CLDL_INFO();
            if (pgmRtn) return;
        }
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP")) {
            R000_GET_ICTL_INFO();
            if (pgmRtn) return;
        }
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP")) {
            R000_GET_PLPI_INFO();
            if (pgmRtn) return;
        } else {
            WS_AMOR_VAL_DATE = LNRCONT.START_DATE;
            WS_AMOR_END_DATE = LNRCONT.ORI_MAT_DATE;
        }
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDL") 
            && LNRDISC.INT_CAL_DT != 0) {
            WS_AMOR_END_DATE = LNRDISC.INT_CAL_DT;
        }
        CEP.TRC(SCCGWA, WS_AMOR_VAL_DATE);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        B200_INT_AMORTISE();
        if (pgmRtn) return;
        B300_RETURN_INFO();
        if (pgmRtn) return;
    }
    public void B200_INT_AMORTISE() throws IOException,SQLException,Exception {
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP")) {
            B250_GET_UNAMOR_INT();
            if (pgmRtn) return;
        } else {
            B250_GET_UNAMOR_OINT();
            if (pgmRtn) return;
        }
        B210_CAL_UNAMOR_DAYS();
        if (pgmRtn) return;
        B230_CAL_TO_AMOR_DAYS();
        if (pgmRtn) return;
        if (WS_UN_AMORTISE_INT > 0) {
            B270_CAL_TODAY_AMOR_INT();
            if (pgmRtn) return;
            if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP")) {
                B290_CNTL_PROCESS();
                if (pgmRtn) return;
            } else {
                B280_OAMO_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_CAL_UNAMOR_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCBATH.JPRM.AC_DATE;
        SCCCLDT.DATE2 = WS_AMOR_END_DATE;
        CEP.TRC(SCCGWA, WS_FIRST_AMORTISE_FLAG);
        CEP.TRC(SCCGWA, WS_AMOR_VAL_DATE);
        if (WS_FIRST_AMORTISE_FLAG == 'Y') {
            SCCCLDT.DATE1 = WS_AMOR_VAL_DATE;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_UNAMOR_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_UNAMOR_DAYS);
    }
    public void B230_CAL_TO_AMOR_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCBATH.JPRM.AC_DATE;
        SCCCLDT.DATE2 = SCCBATH.JPRM.NEXT_AC_DATB;
        if (WS_FIRST_AMORTISE_FLAG == 'Y') {
            SCCCLDT.DATE1 = WS_AMOR_VAL_DATE;
        }
        if (SCCBATH.JPRM.NEXT_AC_DATB > WS_AMOR_END_DATE) {
            SCCCLDT.DATE2 = WS_AMOR_END_DATE;
        }
        R00_CAL_DATE();
        if (pgmRtn) return;
        WS_AMOR_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_AMOR_DAYS);
    }
    public void B250_GET_UNAMOR_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQAMOI";
        LNCCNEX.COMM_DATA.LN_AC = LNCIAMO.DATA.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + LNCIAMO.DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZINEX();
        if (pgmRtn) return;
        WS_UN_AMORTISE_INT = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
        WS_REPAID_INT = LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT);
        if (LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT == LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT) {
            WS_FIRST_AMORTISE_FLAG = 'Y';
        } else {
            WS_FIRST_AMORTISE_FLAG = 'N';
        }
        CEP.TRC(SCCGWA, WS_FIRST_AMORTISE_FLAG);
    }
    public void B250_GET_UNAMOR_OINT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_UN_AMORTISE_INT);
        CEP.TRC(SCCGWA, WS_REPAID_INT);
        if (WS_UN_AMORTISE_INT == WS_REPAID_INT) {
            WS_FIRST_AMORTISE_FLAG = 'Y';
        } else {
            WS_FIRST_AMORTISE_FLAG = 'N';
        }
        CEP.TRC(SCCGWA, WS_FIRST_AMORTISE_FLAG);
    }
    public void B270_CAL_TODAY_AMOR_INT() throws IOException,SQLException,Exception {
        if (WS_AMOR_END_DATE <= SCCBATH.JPRM.NEXT_AC_DATB) {
            WS_TODAY_AMOR_INT = WS_UN_AMORTISE_INT;
        } else {
            WS_TODAY_AMOR_INT = WS_UN_AMORTISE_INT / WS_UNAMOR_DAYS * WS_AMOR_DAYS;
            bigD = new BigDecimal(WS_TODAY_AMOR_INT);
            WS_TODAY_AMOR_INT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if ((!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP") 
            && (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1"))) 
            || (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDP"))) {
            WS_TODAY_AMOR_INT = WS_UN_AMORTISE_INT;
        }
        WS_INPUT_CCY = LNRCONT.CCY;
        WS_INPUT_AMT = WS_TODAY_AMOR_INT;
        R000_ROUND_PROCESS();
        if (pgmRtn) return;
        WS_TODAY_AMOR_INT = BPCRDAMT.RESULT_AMT;
        CEP.TRC(SCCGWA, WS_TODAY_AMOR_INT);
    }
    public void R000_ROUND_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INPUT_CCY);
        CEP.TRC(SCCGWA, WS_INPUT_AMT);
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = WS_INPUT_CCY;
        BPCRDAMT.AMT = WS_INPUT_AMT;
        BPCRDAMT.RESULT_AMT = WS_INPUT_AMT;
        WS_INPUT_CCY = " ";
        WS_INPUT_AMT = 0;
    }
    public void B290_CNTL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "AMOTINT";
        LNCCNEV.COMM_DATA.ACM_EVENT = "AM";
        LNCCNEV.COMM_DATA.LN_AC = LNCIAMO.DATA.CONTRACT_NO;
        LNCCNEV.COMM_DATA.SUF_NO = "" + LNCIAMO.DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = SCCBATH.JPRM.AC_DATE;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = WS_TODAY_AMOR_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = WS_REPAID_INT - WS_UN_AMORTISE_INT + WS_TODAY_AMOR_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[3-1].INT_AMT = WS_REPAID_INT - WS_UN_AMORTISE_INT;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B280_OAMO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = LNCIAMO.DATA.CONTRACT_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCIGVCY);
        LNCIGVCY.DATA.CNTR_TYPE = LNRCONT.CONTRACT_TYPE;
        LNCIGVCY.DATA.PROD_CODE_OLD = LNRCONT.PROD_CD;
        LNCIGVCY.DATA.CTA_NO = LNCIAMO.DATA.CONTRACT_NO;
        LNCIGVCY.DATA.EVENT_CODE = "AM";
        LNCIGVCY.DATA.BR_OLD = LNRCONT.BOOK_BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNRCONT.CCY;
        LNCIGVCY.DATA.VALUE_DATE = LNRCONT.START_DATE;
        LNCIGVCY.DATA.CI_NO = CICACCU.DATA.CI_NO;
        LNCIGVCY.DATA.AMT_INFO[54-1].AMT = WS_REPAID_INT - WS_UN_AMORTISE_INT;
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void B291_REC_TODAY_AMOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQACCR";
        LNCCNEX.COMM_DATA.LN_AC = LNCIAMO.DATA.CONTRACT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = "" + LNCIAMO.DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZINEX();
        if (pgmRtn) return;
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID);
            if (LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID.CTNR_TYP != ' ') {
                IBS.init(SCCGWA, LNCCNBU);
                LNCCNBU.COMM_DATA.LN_AC = LNCIAMO.DATA.CONTRACT_NO;
                LNCCNBU.COMM_DATA.SUF_NO = "" + LNCIAMO.DATA.SUB_CTA_NO;
                JIBS_tmp_int = LNCCNBU.COMM_DATA.SUF_NO.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNBU.COMM_DATA.SUF_NO = "0" + LNCCNBU.COMM_DATA.SUF_NO;
                LNCCNBU.COMM_DATA.VAL_DATE = SCCBATH.JPRM.AC_DATE;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_IDX-1].CTNR_ID);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNBU.COMM_DATA.CNTL_ID);
                LNCCNBU.COMM_DATA.AMT = WS_TODAY_AMOR_INT;
                CEP.TRC(SCCGWA, WS_TODAY_AMOR_INT);
                LNCCNBU.COMM_DATA.AS_IND = 'I';
                LNCCNBU.COMM_DATA.RVS_IND = 'N';
                LNCCNBU.COMM_DATA.TXN_FLG = 'N';
                LNCCNBU.COMM_DATA.ACM_EVCD = "DY";
                S000_CALL_LNZCNBU();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_RETURN_INFO() throws IOException,SQLException,Exception {
        LNCIAMO.DATA.AMO_AMT = WS_TODAY_AMOR_INT;
        LNCIAMO.DATA.UNAMO_AMT = WS_UN_AMORTISE_INT - WS_TODAY_AMOR_INT;
        CEP.TRC(SCCGWA, "**************END************");
        CEP.TRC(SCCGWA, LNCIAMO.DATA.AMO_AMT);
        CEP.TRC(SCCGWA, LNCIAMO.DATA.UNAMO_AMT);
    }
    public void R000_GET_CONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCIAMO.DATA.CONTRACT_NO;
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        S000_CALL_SRC_LNZRCONT();
        if (pgmRtn) return;
    }
    public void R000_GET_LOAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRLOAN);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = LNCIAMO.DATA.CONTRACT_NO;
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        S000_CALL_SRC_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void R000_GET_CLDL_INFO() throws IOException,SQLException,Exception {
    }
    public void R000_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCIAMO.DATA.CONTRACT_NO;
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        S000_CALL_SRC_LNZRICTL();
        if (pgmRtn) return;
    }
    public void R000_GET_PLPI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRPLPI);
        IBS.init(SCCGWA, LNRPLPI);
        LNCRPLPI.FUNC = 'I';
        LNRPLPI.KEY.CONTRACT_NO = LNCIAMO.DATA.CONTRACT_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCIAMO.DATA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = K_PRINCIPAL;
        LNRPLPI.KEY.TERM = 1;
        LNCRPLPI.REC_PTR = LNRPLPI;
        LNCRPLPI.REC_LEN = 277;
        S000_CALL_SRC_LNZRPLPI();
        if (pgmRtn) return;
        WS_AMOR_VAL_DATE = LNRPLPI.VAL_DT;
        WS_AMOR_END_DATE = LNRPLPI.DUE_DT;
        WS_AMOR_END_DATE = LNRCONT.ORI_MAT_DATE;
        CEP.TRC(SCCGWA, WS_AMOR_END_DATE);
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        if (SCCCLDT.DATE2 > SCCCLDT.DATE1) {
            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC != 0) {
                LNCIAMO.RC.RC_APP = "SC";
                LNCIAMO.RC.RC_RTNCODE = SCCCLDT.RC;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SRC_LNZRCONT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCIAMO.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCIAMO.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            LNCIAMO.RC.RC_APP = LNCRLOAN.RC.RC_MMO;
            LNCIAMO.RC.RC_RTNCODE = LNCRLOAN.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRICTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            LNCIAMO.RC.RC_APP = LNCRICTL.RC.RC_MMO;
            LNCIAMO.RC.RC_RTNCODE = LNCRICTL.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SRC_LNZRPLPI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTPLPI", LNCRPLPI);
        if (LNCRPLPI.RC.RC_CODE != 0) {
            LNCIAMO.RC.RC_APP = LNCRPLPI.RC.RC_MMO;
            LNCIAMO.RC.RC_RTNCODE = LNCRPLPI.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZINEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            LNCIAMO.RC.RC_APP = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            LNCIAMO.RC.RC_RTNCODE = LNCCNEX.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCIAMO.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCIAMO.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNBU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-BAL-UPD", LNCCNBU);
        if (LNCCNBU.RC.RC_RTNCODE != 0) {
            LNCIAMO.RC.RC_APP = LNCCNBU.RC.RC_APP;
            LNCIAMO.RC.RC_RTNCODE = LNCCNBU.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            LNCIAMO.RC.RC_APP = BPCRDAMT.RC.RC_MMO;
            LNCIAMO.RC.RC_RTNCODE = BPCRDAMT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCIAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIAMO.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIAMO=");
            CEP.TRC(SCCGWA, LNCIAMO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
