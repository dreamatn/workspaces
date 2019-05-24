package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6000 {
    int JIBS_tmp_int;
    DBParm LNTTRAN_RD;
    DBParm LNTCONT_RD;
    DBParm LNTSETL_RD;
    char K_FUNC_QUERY = 'I';
    char K_FUNC_ADD = 'A';
    char K_FUNC_UPD = 'M';
    char K_FUNC_DEL = 'D';
    char K_USE_CPND = 'Y';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CONT_BOOK_BR = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCSRATE LNCSRATE = new LNCSRATE();
    LNRTRAN LNRTRAN = new LNRTRAN();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    LNRCONT LNRCONT = new LNRCONT();
    LNRSETL LNRSETL = new LNRSETL();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    LNB6000_AWA_6000 LNB6000_AWA_6000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT6000 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6000_AWA_6000>");
        LNB6000_AWA_6000 = (LNB6000_AWA_6000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        LNCSRATE.RC.RC_APP = "LN";
        LNCSRATE.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        B100_FUNC_MAIN();
        if (LNB6000_AWA_6000.FUN_CD != K_FUNC_QUERY) {
            B200_WRITE_HIS();
            B300_FEE_PROC();
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB6000_AWA_6000.LOAN_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6000_AWA_6000.VAL_DT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6000_AWA_6000.VAL_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_L_GDT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6000_AWA_6000.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_H_GDT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6000_AWA_6000.FUN_CD != K_FUNC_ADD 
            && LNB6000_AWA_6000.FUN_CD != K_FUNC_UPD 
            && LNB6000_AWA_6000.FUN_CD != K_FUNC_QUERY 
            && LNB6000_AWA_6000.FUN_CD != K_FUNC_DEL) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
        }
        if (LNB6000_AWA_6000.FUN_CD != K_FUNC_QUERY 
            || LNB6000_AWA_6000.FUN_CD != K_FUNC_DEL) {
            if ((LNB6000_AWA_6000.RATE_TYP.trim().length() == 0 
                && LNB6000_AWA_6000.INT_RATE != 0) 
                || (LNB6000_AWA_6000.RATE_TYP.trim().length() > 0 
                && LNB6000_AWA_6000.INT_RATE == 0)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_N_ERR;
                S000_ERR_MSG_PROC();
            }
            if ((LNB6000_AWA_6000.PEN_RATT.trim().length() == 0 
                && LNB6000_AWA_6000.PEN_IRAT != 0) 
                || (LNB6000_AWA_6000.PEN_RATT.trim().length() > 0 
                && LNB6000_AWA_6000.PEN_IRAT == 0)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_O_ERR;
                S000_ERR_MSG_PROC();
            }
            if ((LNB6000_AWA_6000.CPNDRATT.trim().length() == 0 
                && LNB6000_AWA_6000.CPNDIRAT != 0) 
                || (LNB6000_AWA_6000.CPNDRATT.trim().length() > 0 
                && LNB6000_AWA_6000.CPNDIRAT == 0)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_L_ERR;
                S000_ERR_MSG_PROC();
            }
            if ((LNB6000_AWA_6000.ABUSRATT.trim().length() == 0 
                && LNB6000_AWA_6000.ABUSIRAT != 0) 
                || (LNB6000_AWA_6000.ABUSRATT.trim().length() > 0 
                && LNB6000_AWA_6000.ABUSIRAT == 0)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_P_ERR;
                S000_ERR_MSG_PROC();
            }
            if (LNB6000_AWA_6000.ABUSRATT.equalsIgnoreCase("0") 
                && LNB6000_AWA_6000.CPNDIRAT == 0 
                && LNB6000_AWA_6000.INT_RATE == 0 
                && LNB6000_AWA_6000.RATE_INT == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_ERR;
                S000_ERR_MSG_PROC();
            }
            if (LNB6000_AWA_6000.CPND_USE == 'Y' 
                && LNB6000_AWA_6000.PEN_RATT.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_L_ERR;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, LNB6000_AWA_6000.CPND_USE);
            CEP.TRC(SCCGWA, LNB6000_AWA_6000.CPNDRATT);
            if (LNB6000_AWA_6000.CPND_USE == 'Y' 
                && LNB6000_AWA_6000.CPNDRATT.trim().length() > 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.RATE_O_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B100_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATE);
        LNCSRATE.DATA.CONTRACT_NO = LNB6000_AWA_6000.LOAN_AC;
        LNCSRATE.DATA.SUB_CTA_NO = 0;
        LNCSRATE.DATA.RAT_CHG_DT = LNB6000_AWA_6000.VAL_DT;
        CEP.TRC(SCCGWA, LNCSRATE.DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RAT_CHG_DT);
        LNCSRATE.FUNC_TYP = LNB6000_AWA_6000.FUN_CD;
        if (LNB6000_AWA_6000.FUN_CD != K_FUNC_QUERY 
            || LNB6000_AWA_6000.FUN_CD != K_FUNC_DEL) {
            LNCSRATE.DATA.INT_RATT_N = LNB6000_AWA_6000.INT_RATT;
            LNCSRATE.DATA.FLT_MTH_N = LNB6000_AWA_6000.FLT_MTH;
            LNCSRATE.DATA.RATE_TYP_N = LNB6000_AWA_6000.RATE_TYP;
            LNCSRATE.DATA.RATEPERD_N = LNB6000_AWA_6000.RATEPERD;
            LNCSRATE.DATA.RATE_VAR_N = LNB6000_AWA_6000.RATE_VAR;
            LNCSRATE.DATA.RATE_PCT_N = LNB6000_AWA_6000.RATE_PCT;
            LNCSRATE.DATA.RATE_INT_N = LNB6000_AWA_6000.RATE_INT;
            LNCSRATE.DATA.INT_RAT_N = LNB6000_AWA_6000.INT_RATE;
            LNCSRATE.DATA.PEN_RRAT_O = LNB6000_AWA_6000.PEN_RRAT;
            LNCSRATE.DATA.PEN_TYP_O = LNB6000_AWA_6000.PEN_TYP;
            LNCSRATE.DATA.PEN_RATT_O = LNB6000_AWA_6000.PEN_RATT;
            LNCSRATE.DATA.PEN_RATC_O = LNB6000_AWA_6000.PEN_RATC;
            LNCSRATE.DATA.PEN_SPR_O = LNB6000_AWA_6000.PEN_SPR;
            LNCSRATE.DATA.PEN_PCT_O = LNB6000_AWA_6000.PEN_PCT;
            LNCSRATE.DATA.INT_RAT_O = LNB6000_AWA_6000.PEN_IRAT;
            LNCSRATE.DATA.INT_MTH = LNB6000_AWA_6000.INT_MTH;
            LNCSRATE.DATA.INT_MTH = 'Y';
            LNCSRATE.DATA.CPND_USE = LNB6000_AWA_6000.CPND_USE;
            LNCSRATE.DATA.CPND_RTY_L = LNB6000_AWA_6000.CPND_RTY;
            LNCSRATE.DATA.CPND_TYP_L = LNB6000_AWA_6000.CPND_TYP;
            LNCSRATE.DATA.CPNDRATT_L = LNB6000_AWA_6000.CPNDRATT;
            LNCSRATE.DATA.CPNDRATC_L = LNB6000_AWA_6000.CPNDRATC;
            LNCSRATE.DATA.CPND_SPR_L = LNB6000_AWA_6000.CPND_SPR;
            LNCSRATE.DATA.CPND_PCT_L = LNB6000_AWA_6000.CPND_PCT;
            LNCSRATE.DATA.INT_RAT_L = LNB6000_AWA_6000.CPNDIRAT;
            LNCSRATE.DATA.ABUS_RAT_P = LNB6000_AWA_6000.ABUS_RAT;
            LNCSRATE.DATA.ABUS_TYP_P = LNB6000_AWA_6000.ABUS_TYP;
            LNCSRATE.DATA.ABUSRATT_P = LNB6000_AWA_6000.ABUSRATT;
            LNCSRATE.DATA.ABUSRATC_P = LNB6000_AWA_6000.ABUSRATC;
            LNCSRATE.DATA.ABUSSPR_P = LNB6000_AWA_6000.ABUSSPR;
            LNCSRATE.DATA.ABUSPCT_P = LNB6000_AWA_6000.ABUSPCT;
            LNCSRATE.DATA.INT_RAT_P = LNB6000_AWA_6000.ABUSIRAT;
            if (LNB6000_AWA_6000.RATE_TYP.trim().length() > 0) {
                LNCSRATE.DATA.RATE_KIND_N = 'Y';
            }
            if (LNB6000_AWA_6000.PEN_RATT.trim().length() > 0) {
                LNCSRATE.DATA.RATE_KIND_O = 'Y';
            }
            if (LNB6000_AWA_6000.CPNDRATT.trim().length() > 0) {
                LNCSRATE.DATA.RATE_KIND_L = 'Y';
            }
            if (LNB6000_AWA_6000.ABUSRATT.trim().length() > 0) {
                LNCSRATE.DATA.RATE_KIND_P = 'Y';
            }
        }
        CEP.TRC(SCCGWA, "NS2712");
        S000_CALL_LNZSRATE();
        CEP.TRC(SCCGWA, "TRANS FINISHED");
    }
    public void B200_WRITE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNB6000_AWA_6000.LOAN_AC;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.TRAN_STS = 'N';
        LNRTRAN.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNRTRAN.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRTRAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRTRAN);
        T000_WRITE_LNTTRAN();
    }
    public void B300_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, CICQACAC);
        LNRSETL.KEY.CONTRACT_NO = LNB6000_AWA_6000.LOAN_AC;
        LNRCONT.KEY.CONTRACT_NO = LNB6000_AWA_6000.LOAN_AC;
        CICQACAC.DATA.ACAC_NO = LNB6000_AWA_6000.LOAN_AC;
        CICQACAC.FUNC = 'A';
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.SETTLE_TYPE = '2';
        S000_CALL_CIZQACAC();
        T000_READ_LNTSETL();
        T000_READ_LNTCONT();
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNRSETL.KEY.CCY;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CCY_TYPE = LNRSETL.CCY_TYP;
        BPCFFTXI.TX_DATA.SVR_CD = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPCFFTXI.TX_DATA.SVR_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCFFTXI.TX_DATA.SVR_CD = "0" + BPCFFTXI.TX_DATA.SVR_CD;
        S000_CALL_BPZFFTXI();
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        BPCTCALF.INPUT_AREA.TX_CCY = LNRSETL.KEY.CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = 0;
        BPCTCALF.INPUT_AREA.PROD_CODE = LNRCONT.PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = LNRCONT.PROD_CD;
        BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
        S000_CALL_BPZFCALF();
    }
    public void S000_CALL_LNZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SEV-RATE-MAINT", LNCSRATE);
        if (LNCSRATE.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSRATE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.WRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.fst = true;
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
