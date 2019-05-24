package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUHOLD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTHLD_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    DBParm DDTHLDR_RD;
    brParm DDTHLDR_BR = new brParm();
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "DEPOSIT HOLD TRX";
    String K_HIS_REMARKS1 = "SET AC STOP STATUS";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_HLD_NO = " ";
    String WS_RECORD_HLD_NO = " ";
    String WS_UP_HLD_NO = " ";
    String WS_DOWN_HLD_NO = " ";
    String WS_HLD_NO_SZ = " ";
    DCZUHOLD_WS_HLD_NO_TLR WS_HLD_NO_TLR = new DCZUHOLD_WS_HLD_NO_TLR();
    double WS_ACC_HLD_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_AVL_HLD_AMT = 0;
    double WS_AMT_HLD_AMT = 0;
    double WS_BANK_ACC_AMT = 0;
    double WS_BANK_ACC_AMT_Z = 0;
    double WS_HLD_AMT = 0;
    double WS_BANK_HLD_AMT = 0;
    String WS_TR_AC = " ";
    String WS_AC_TYPE = " ";
    DCZUHOLD_CP_PROD_CD CP_PROD_CD = new DCZUHOLD_CP_PROD_CD();
    char WS_HLD_STS = ' ';
    char WS_HLD_FLG = ' ';
    char WS_HLD_TYP = ' ';
    char WS_STS_LAW_HLD_FLG = ' ';
    char WS_STS_LAW_FBID_FLG = ' ';
    char WS_STS_BANK_HLD_FLG = ' ';
    char WS_STS_BANK_FBID_FLG = ' ';
    char WS_HLD_UPDATE_FLG = ' ';
    char WS_CCY_UPDATE_FLG = ' ';
    char WS_SMST_UPDATE_FLG = ' ';
    char WS_HLD_WAIT_FLG = ' ';
    char WS_CIRCLE_FLG = ' ';
    char WS_WAIT_UPDATE_FLG = ' ';
    char WS_BANK_HOLD_FLG = ' ';
    char WS_OLD_HOLD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICCKLS CICCKLS = new CICCKLS();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDCUPARM DDCUPARM = new DDCUPARM();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCUHOLD DCCUHOLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCUHOLD DCCUHOLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUHOLD = DCCUHOLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUHOLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_CI_INF_PROC();
        if (pgmRtn) return;
        if (DCCUHOLD.DATA.SPR_TYP == '2') {
            B015_CHECK_CI_LIST();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_CHK_CCY_STS_PROC();
            if (pgmRtn) return;
            B030_UPD_STS_PROC();
            if (pgmRtn) return;
            B050_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
            B070_TRANS_DATA_BACK();
            if (pgmRtn) return;
        } else {
            B080_RHLD_PROC();
            if (pgmRtn) return;
            B050_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        WS_STS_LAW_HLD_FLG = 'N';
        WS_STS_LAW_FBID_FLG = 'N';
        WS_STS_BANK_HLD_FLG = 'N';
        WS_STS_BANK_FBID_FLG = 'N';
        WS_HLD_WAIT_FLG = 'N';
        WS_OLD_HOLD_FLG = 'N';
        if (DCCUHOLD.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.HLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.HLD_TYP != '1' 
            && DCCUHOLD.DATA.HLD_TYP != '2' 
            && DCCUHOLD.DATA.HLD_TYP != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP != '1' 
            && DCCUHOLD.DATA.SPR_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCUHOLD.DATA.HLD_TYP == '2' 
            || DCCUHOLD.DATA.HLD_TYP == 'A') 
            && DCCUHOLD.DATA.AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP == '1' 
            && DCCUHOLD.DATA.SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP == '1' 
            && DCCUHOLD.DATA.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP == '1' 
            && DCCUHOLD.DATA.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.SPR_TYP == '1' 
            && DCCUHOLD.DATA.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.EFF_DT == 0) {
            DCCUHOLD.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DCCUHOLD.DATA.EXP_DT == 0) {
            DCCUHOLD.DATA.EXP_DT = 99991231;
        }
        if (DCCUHOLD.DATA.HLD_BR == 0) {
            DCCUHOLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, DCCUHOLD.DATA.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DCCUHOLD.DATA.EFF_DT != SCCGWA.COMM_AREA.AC_DATE 
            && DCCUHOLD.DATA.HLD_TYP != 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.EXP_DT != 0 
            && DCCUHOLD.DATA.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.EXP_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCCUHOLD.DATA.EXP_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_EXP_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCUHOLD.DATA.HLD_TYP == 'A' 
            && DCCUHOLD.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.HLD_TYP == 'A') {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = DCCUHOLD.DATA.HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCUHOLD.DATA.HLD_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.HLD_FLG != '1' 
            && DCCUHOLD.DATA.HLD_FLG != '2') {
            CEP.TRC(SCCGWA, "1111");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_FLG_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUHOLD.DATA.HLD_TYP != '1' 
            && DCCUHOLD.DATA.HLD_FLG == '2') {
            CEP.TRC(SCCGWA, "2222");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_FLG_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = DCCUHOLD.DATA.AC;
        CICCKLS.DATA.AP_TYPE = "P129";
        CICCKLS.DATA.EXP_MMO = "P129";
        CICCKLS.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B020_INQ_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCUHOLD.DATA.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB") 
            || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("EQ")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_NOT_ALLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DCCUHOLD.DATA.CHK_OPT != ' ' 
            && (DCCUHOLD.DATA.PSWD.trim().length() > 0 
            || DCCUHOLD.DATA.TRK_DAT2.trim().length() > 0 
            || DCCUHOLD.DATA.TRK_DAT3.trim().length() > 0)) {
            IBS.init(SCCGWA, DCCPCDCK);
            if (DCCUHOLD.DATA.CHK_OPT == '1') {
                DCCPCDCK.FUNC_CODE = 'P';
            } else if (DCCUHOLD.DATA.CHK_OPT == '2') {
                DCCPCDCK.FUNC_CODE = 'T';
            } else if (DCCUHOLD.DATA.CHK_OPT == '3') {
                DCCPCDCK.FUNC_CODE = 'B';
            } else if (DCCUHOLD.DATA.CHK_OPT == '4') {
                DCCPCDCK.FUNC_CODE = 'N';
            } else {
            }
            DCCPCDCK.CARD_NO = DCCUHOLD.DATA.AC;
            DCCPCDCK.CARD_PSW = DCCUHOLD.DATA.PSWD;
            DCCPCDCK.TRK2_DAT = DCCUHOLD.DATA.TRK_DAT2;
            DCCPCDCK.TRK3_DAT = DCCUHOLD.DATA.TRK_DAT3;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCUHOLD.DATA.AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.ADSC_TYPE == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CARD_TYPE_C;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DCCUHOLD.DATA.CHK_OPT == '1' 
            && DCCUHOLD.DATA.PSWD.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.PSWD_OLD = DCCUHOLD.DATA.PSWD;
            DDCIMPAY.AC = DCCUHOLD.DATA.AC;
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PAY_MTH = '1';
            S000_CALL_DDZIMPAY();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            if ((DCCUHOLD.DATA.HLD_TYP == '2' 
                || DCCUHOLD.DATA.HLD_TYP == 'A') 
                && DCCUHOLD.DATA.CCY.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((DCCUHOLD.DATA.HLD_TYP == '2' 
                || DCCUHOLD.DATA.HLD_TYP == 'A') 
                && DCCUHOLD.DATA.CCY_TYP == ' ') {
                DCCUHOLD.DATA.CCY_TYP = '1';
            }
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DCCUHOLD.DATA.AC;
        CICQACAC.DATA.AGR_SEQ = DCCUHOLD.DATA.SEQ;
        CICQACAC.DATA.CCY_ACAC = DCCUHOLD.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DCCUHOLD.DATA.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.equalsIgnoreCase("01")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NOMTCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACAC_NO_NOT_EXIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
            DCCUHOLD.DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
            DCCUHOLD.DATA.CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B020_CHK_CCY_STS_PROC() throws IOException,SQLException,Exception {
        R000_GEN_HLD_NO();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DCCUHOLD.DATA.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS == 'V') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_COM_AC_UNAUDIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            WS_CCY_UPDATE_FLG = 'N';
            if (DDRCCY.AC_TYPE == '3' 
                && DDRCCY.MARGIN_BAL != 0) {
                if (DCCUHOLD.DATA.HLD_TYP == '1') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MARGIN_AMT_NOT_ACHLD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_HLD_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_FBID_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_HLD_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_FBID_FLG = 'Y';
            }
            R_GET_DD_PROD_INF();
            if (pgmRtn) return;
            WS_TR_AC = DDRCCY.KEY.AC;
            if (WS_STS_BANK_HLD_FLG == 'Y' 
                && DCCUHOLD.DATA.SPR_TYP == '2' 
                && DCCUHOLD.DATA.HLD_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_STS_BANK_FBID_FLG == 'Y' 
                && DCCUHOLD.DATA.SPR_TYP == '2' 
                && DCCUHOLD.DATA.HLD_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUHOLD.DATA.SPR_TYP == '2' 
                && (DCCUHOLD.DATA.HLD_TYP == '2' 
                || DCCUHOLD.DATA.HLD_TYP == 'A') 
                && DCCUHOLD.DATA.HLD_CLS != ' ') {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DCCUHOLD.DATA.HLD_TYP != '1') {
                if (WS_STS_LAW_HLD_FLG == 'Y' 
                    || WS_STS_LAW_FBID_FLG == 'Y') {
                    WS_AVL_HLD_AMT = 0;
                    WS_ACC_HLD_AMT = 0;
                    WS_OLD_HOLD_FLG = 'Y';
                    if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                        R000_UPDATE_WAIT_HOLD_RECORD();
                        if (pgmRtn) return;
                    }
                } else {
                    CEP.TRC(SCCGWA, WS_STS_BANK_HLD_FLG);
                    CEP.TRC(SCCGWA, WS_STS_BANK_FBID_FLG);
                    if ((WS_STS_BANK_HLD_FLG == 'Y' 
                        || WS_STS_BANK_FBID_FLG == 'Y') 
                        && DCCUHOLD.DATA.SPR_TYP == '2') {
                        WS_AVL_HLD_AMT = 0;
                        WS_ACC_HLD_AMT = 0;
                        WS_OLD_HOLD_FLG = 'Y';
                        if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (DCCUHOLD.DATA.SPR_TYP == '2') {
                            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
                            if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                                R000_UPDATE_WAIT_HOLD_RECORD();
                                if (pgmRtn) return;
                            }
                            CEP.TRC(SCCGWA, "AAAAAA");
                            CEP.TRC(SCCGWA, WS_AVL_HLD_AMT);
                        } else {
                            CEP.TRC(SCCGWA, "BBBBBB");
                            R000_GET_HLD_BANK_AMT();
                            if (pgmRtn) return;
                            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL + WS_BANK_HLD_AMT;
                            if (WS_BANK_HOLD_FLG == 'Y') {
                                WS_AVL_HLD_AMT = 0;
                                WS_ACC_HLD_AMT = 0;
                                WS_OLD_HOLD_FLG = 'Y';
                            }
                            if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                                R000_UPDATE_WAIT_HOLD_RECORD();
                                if (pgmRtn) return;
                            }
                            CEP.TRC(SCCGWA, WS_AVL_HLD_AMT);
                            if (WS_BANK_HLD_AMT > 0 
                                || (WS_BANK_HLD_AMT == 0 
                                && (WS_STS_BANK_HLD_FLG == 'Y' 
                                || WS_STS_BANK_FBID_FLG == 'Y') 
                                && WS_BANK_HOLD_FLG != 'Y')) {
                                R000_HLD_BANK_PROC();
                                if (pgmRtn) return;
                            }
                        }
                        if (WS_AVL_HLD_AMT <= 0) {
                            WS_AVL_HLD_AMT = 0;
                        }
                        if (WS_AVL_HLD_AMT < DCCUHOLD.DATA.AMT) {
                            WS_ACC_HLD_AMT = WS_AVL_HLD_AMT;
                        } else {
                            WS_ACC_HLD_AMT = DCCUHOLD.DATA.AMT;
                        }
                    }
                }
                CEP.TRC(SCCGWA, WS_AVL_HLD_AMT);
                CEP.TRC(SCCGWA, DCCUHOLD.DATA.AMT);
                if ((WS_AVL_HLD_AMT < DCCUHOLD.DATA.AMT) 
                    && (DCCUHOLD.DATA.SPR_TYP == '2' 
                    || DCCUHOLD.DATA.HLD_TYP == 'A' 
                    || (DDRCCY.AC_TYPE == '3' 
                    && DCCUHOLD.DATA.SPR_TYP == '1'))) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
                if (WS_ACC_HLD_AMT == 0) {
                    WS_HLD_WAIT_FLG = 'Y';
                }
                if (WS_WAIT_UPDATE_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "CCCCCC");
                    DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - WS_AMT_HLD_AMT;
                    WS_CCY_UPDATE_FLG = 'Y';
                }
                if (!(WS_HLD_WAIT_FLG == 'Y' 
                    && WS_OLD_HOLD_FLG == 'Y')) {
                    CEP.TRC(SCCGWA, "DDDDDD");
                    DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL + DCCUHOLD.DATA.AMT;
                    WS_CCY_UPDATE_FLG = 'Y';
                }
            }
            if (DCCUHOLD.DATA.HLD_TYP == '1') {
                if (DCCUHOLD.DATA.SPR_TYP == '1') {
                    if (WS_STS_LAW_HLD_FLG == 'N' 
                        && DCCUHOLD.DATA.HLD_FLG == '1') {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "1" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
                        WS_CCY_UPDATE_FLG = 'Y';
                    }
                    if (WS_STS_LAW_FBID_FLG == 'N' 
                        && DCCUHOLD.DATA.HLD_FLG == '2') {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "1" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                        WS_CCY_UPDATE_FLG = 'Y';
                    }
                }
                if (DCCUHOLD.DATA.SPR_TYP == '2') {
                    if (DCCUHOLD.DATA.HLD_FLG == '1') {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "1" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                        WS_CCY_UPDATE_FLG = 'Y';
                    }
                    if (DCCUHOLD.DATA.HLD_FLG == '2') {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "1" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                        WS_CCY_UPDATE_FLG = 'Y';
                    }
                }
                if (WS_STS_LAW_HLD_FLG == 'Y' 
                    || WS_STS_LAW_FBID_FLG == 'Y') {
                    WS_AVL_HLD_AMT = 0;
                    WS_ACC_HLD_AMT = 0;
                    WS_OLD_HOLD_FLG = 'Y';
                    R000_UPDATE_WAIT_HOLD_RECORD();
                    if (pgmRtn) return;
                } else {
                    if ((WS_STS_BANK_HLD_FLG == 'Y' 
                        || WS_STS_BANK_FBID_FLG == 'Y') 
                        && DCCUHOLD.DATA.SPR_TYP == '2') {
                        WS_AVL_HLD_AMT = 0;
                        WS_ACC_HLD_AMT = 0;
                        WS_OLD_HOLD_FLG = 'Y';
                        R000_UPDATE_WAIT_HOLD_RECORD();
                        if (pgmRtn) return;
                    } else {
                        if (DCCUHOLD.DATA.SPR_TYP == '2') {
                            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                        } else {
                            R000_GET_HLD_BANK_AMT();
                            if (pgmRtn) return;
                            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL + WS_BANK_HLD_AMT;
                            if (WS_BANK_HOLD_FLG == 'Y') {
                                WS_AVL_HLD_AMT = 0;
                                WS_ACC_HLD_AMT = 0;
                                WS_OLD_HOLD_FLG = 'Y';
                            }
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                            if (WS_BANK_HLD_AMT > 0 
                                || (WS_BANK_HLD_AMT == 0 
                                && (WS_STS_BANK_HLD_FLG == 'Y' 
                                || WS_STS_BANK_FBID_FLG == 'Y') 
                                && WS_BANK_HOLD_FLG != 'Y')) {
                                R000_HLD_BANK_PROC();
                                if (pgmRtn) return;
                            }
                        }
                        if (WS_AVL_HLD_AMT <= 0) {
                            WS_AVL_HLD_AMT = 0;
                        }
                        WS_ACC_HLD_AMT = WS_AVL_HLD_AMT;
                    }
                }
                if (WS_WAIT_UPDATE_FLG == 'Y') {
                    DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - WS_AMT_HLD_AMT;
                    WS_CCY_UPDATE_FLG = 'Y';
                }
                if (WS_ACC_HLD_AMT == 0) {
                    WS_HLD_WAIT_FLG = 'Y';
                }
            }
            if (WS_CCY_UPDATE_FLG == 'Y') {
                DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_UPDATE_DDTCCY();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            WS_SMST_UPDATE_FLG = 'N';
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
                && TDRSMST.GUAR_BAL != 0) {
                if (DCCUHOLD.DATA.HLD_TYP == '1') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MARGIN_AMT_NOT_ACHLD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_HLD_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_FBID_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_HLD_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_FBID_FLG = 'Y';
            }
            R_GET_TD_PROD_INF();
            if (pgmRtn) return;
            WS_TR_AC = TDRSMST.KEY.ACO_AC;
            WS_AC_TYPE = TDRSMST.PRDAC_CD;
            if (WS_STS_BANK_HLD_FLG == 'Y' 
                && DCCUHOLD.DATA.SPR_TYP == '2' 
                && DCCUHOLD.DATA.HLD_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_STS_BANK_FBID_FLG == 'Y' 
                && DCCUHOLD.DATA.SPR_TYP == '2' 
                && DCCUHOLD.DATA.HLD_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUHOLD.DATA.SPR_TYP == '2' 
                && (DCCUHOLD.DATA.HLD_TYP == '2' 
                || DCCUHOLD.DATA.HLD_TYP == 'A') 
                && DCCUHOLD.DATA.HLD_CLS != ' ') {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BNK_STOP_STS_E;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCCUHOLD.DATA.SPR_TYP == '2') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_REP_HAS_IMP_BNK;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_REP_HAS_IMP_LAW;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DCCUHOLD.DATA.HLD_TYP != '1') {
                if (DCCUHOLD.DATA.CCY.trim().length() > 0) {
                    if (!DCCUHOLD.DATA.CCY.equalsIgnoreCase(TDRSMST.CCY)) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_CCY_NOT_MATCH;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (DCCUHOLD.DATA.CCY_TYP != ' ') {
                    if (DCCUHOLD.DATA.CCY_TYP != TDRSMST.CCY_TYP) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_CCY_TYP_N_MCH;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (WS_STS_LAW_HLD_FLG == 'Y' 
                    || WS_STS_LAW_FBID_FLG == 'Y') {
                    WS_AVL_HLD_AMT = 0;
                    WS_ACC_HLD_AMT = 0;
                    WS_OLD_HOLD_FLG = 'Y';
                    if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                        R000_UPDATE_WAIT_HOLD_RECORD();
                        if (pgmRtn) return;
                    }
                } else {
                    if ((WS_STS_BANK_HLD_FLG == 'Y' 
                        || WS_STS_BANK_FBID_FLG == 'Y') 
                        && DCCUHOLD.DATA.SPR_TYP == '2') {
                        WS_AVL_HLD_AMT = 0;
                        WS_ACC_HLD_AMT = 0;
                        WS_OLD_HOLD_FLG = 'Y';
                        if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (DCCUHOLD.DATA.SPR_TYP == '2') {
                            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
                            if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                                R000_UPDATE_WAIT_HOLD_RECORD();
                                if (pgmRtn) return;
                            }
                        } else {
                            R000_GET_HLD_BANK_AMT();
                            if (pgmRtn) return;
                            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL + WS_BANK_HLD_AMT;
                            if (WS_BANK_HOLD_FLG == 'Y') {
                                WS_AVL_HLD_AMT = 0;
                                WS_ACC_HLD_AMT = 0;
                                WS_OLD_HOLD_FLG = 'Y';
                            }
                            if (DCCUHOLD.DATA.HLD_TYP != 'A') {
                                R000_UPDATE_WAIT_HOLD_RECORD();
                                if (pgmRtn) return;
                            }
                            if (WS_BANK_HLD_AMT > 0 
                                || (WS_BANK_HLD_AMT == 0 
                                && (WS_STS_BANK_HLD_FLG == 'Y' 
                                || WS_STS_BANK_FBID_FLG == 'Y') 
                                && WS_BANK_HOLD_FLG != 'Y')) {
                                R000_HLD_BANK_PROC();
                                if (pgmRtn) return;
                            }
                        }
                        if (WS_AVL_HLD_AMT <= 0) {
                            WS_AVL_HLD_AMT = 0;
                        }
                        if (WS_AVL_HLD_AMT < DCCUHOLD.DATA.AMT) {
                            WS_ACC_HLD_AMT = WS_AVL_HLD_AMT;
                        } else {
                            WS_ACC_HLD_AMT = DCCUHOLD.DATA.AMT;
                        }
                    }
                }
                if ((WS_AVL_HLD_AMT < DCCUHOLD.DATA.AMT) 
                    && (DCCUHOLD.DATA.HLD_TYP == 'A' 
                    || DCCUHOLD.DATA.SPR_TYP == '2' 
                    || (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
                    && DCCUHOLD.DATA.SPR_TYP == '1'))) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_ACC_HLD_AMT == 0) {
                    WS_HLD_WAIT_FLG = 'Y';
                }
                if (WS_WAIT_UPDATE_FLG == 'Y') {
                    TDRSMST.HBAL = TDRSMST.HBAL - WS_AMT_HLD_AMT;
                    WS_SMST_UPDATE_FLG = 'Y';
                }
                if (!(WS_HLD_WAIT_FLG == 'Y' 
                    && WS_OLD_HOLD_FLG == 'Y')) {
                    TDRSMST.HBAL = TDRSMST.HBAL + DCCUHOLD.DATA.AMT;
                    WS_SMST_UPDATE_FLG = 'Y';
                }
            }
            if (DCCUHOLD.DATA.HLD_TYP == '1') {
                if (DCCUHOLD.DATA.SPR_TYP == '1') {
                    if (WS_STS_LAW_HLD_FLG == 'N' 
                        && DCCUHOLD.DATA.HLD_FLG == '1') {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "1" + TDRSMST.STSW.substring(2 + 1 - 1);
                        WS_SMST_UPDATE_FLG = 'Y';
                    }
                    if (WS_STS_LAW_FBID_FLG == 'N' 
                        && DCCUHOLD.DATA.HLD_FLG == '2') {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "1" + TDRSMST.STSW.substring(8 + 1 - 1);
                        WS_SMST_UPDATE_FLG = 'Y';
                    }
                }
                if (DCCUHOLD.DATA.SPR_TYP == '2') {
                    if (DCCUHOLD.DATA.HLD_FLG == '1') {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "1" + TDRSMST.STSW.substring(3 + 1 - 1);
                        WS_SMST_UPDATE_FLG = 'Y';
                    }
                    if (DCCUHOLD.DATA.HLD_FLG == '2') {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "1" + TDRSMST.STSW.substring(7 + 1 - 1);
                        WS_SMST_UPDATE_FLG = 'Y';
                    }
                }
                if (WS_STS_LAW_HLD_FLG == 'Y' 
                    || WS_STS_LAW_FBID_FLG == 'Y') {
                    WS_AVL_HLD_AMT = 0;
                    WS_ACC_HLD_AMT = 0;
                    WS_OLD_HOLD_FLG = 'Y';
                    R000_UPDATE_WAIT_HOLD_RECORD();
                    if (pgmRtn) return;
                } else {
                    if ((WS_STS_BANK_HLD_FLG == 'Y' 
                        || WS_STS_BANK_FBID_FLG == 'Y') 
                        && DCCUHOLD.DATA.SPR_TYP == '2') {
                        WS_AVL_HLD_AMT = 0;
                        WS_ACC_HLD_AMT = 0;
                        WS_OLD_HOLD_FLG = 'Y';
                        R000_UPDATE_WAIT_HOLD_RECORD();
                        if (pgmRtn) return;
                    } else {
                        if (DCCUHOLD.DATA.SPR_TYP == '2') {
                            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                        } else {
                            R000_GET_HLD_BANK_AMT();
                            if (pgmRtn) return;
                            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL + WS_BANK_HLD_AMT;
                            if (WS_BANK_HOLD_FLG == 'Y') {
                                WS_AVL_HLD_AMT = 0;
                                WS_ACC_HLD_AMT = 0;
                                WS_OLD_HOLD_FLG = 'Y';
                            }
                            R000_UPDATE_WAIT_HOLD_RECORD();
                            if (pgmRtn) return;
                            if (WS_BANK_HLD_AMT > 0 
                                || (WS_BANK_HLD_AMT == 0 
                                && (WS_STS_BANK_HLD_FLG == 'Y' 
                                || WS_STS_BANK_FBID_FLG == 'Y') 
                                && WS_BANK_HOLD_FLG != 'Y')) {
                                R000_HLD_BANK_PROC();
                                if (pgmRtn) return;
                            }
                        }
                        if (WS_AVL_HLD_AMT <= 0) {
                            WS_AVL_HLD_AMT = 0;
                        }
                        WS_ACC_HLD_AMT = WS_AVL_HLD_AMT;
                    }
                }
                if (WS_WAIT_UPDATE_FLG == 'Y') {
                    TDRSMST.HBAL = TDRSMST.HBAL - WS_AMT_HLD_AMT;
                    WS_SMST_UPDATE_FLG = 'Y';
                }
                if (WS_ACC_HLD_AMT == 0) {
                    WS_HLD_WAIT_FLG = 'Y';
                }
            }
            if (WS_SMST_UPDATE_FLG == 'Y') {
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_UPDATE_TDTSMST();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_UPD_STS_PROC() throws IOException,SQLException,Exception {
        if (DCCUHOLD.DATA.HLD_TYP == 'A') {
            R000_UPD_DDTHLD();
            if (pgmRtn) return;
        } else {
            R000_WRITE_DDTHLD();
            if (pgmRtn) return;
        }
        R000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B050_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DCCUHOLD.DATA.AC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (DCCUHOLD.DATA.HLD_FLG == '2') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS1;
        }
        BPCPNHIS.INFO.REF_NO = DDRHLDR.KEY.HLD_NO;
        BPCPNHIS.INFO.TX_TYP = 'A';
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            if (TDRSMST.BV_TYP == '1' 
                || TDRSMST.BV_TYP == '4') {
                BPCPNHIS.INFO.TX_TOOL = DCCUHOLD.DATA.AC;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (DDRCCY.AC_TYPE == '2') {
                BPCPNHIS.INFO.TX_TOOL = DCCUHOLD.DATA.AC;
            }
        }
        if (DCCUHOLD.DATA.TX_TYP_CD.trim().length() > 0) {
            BPCPNHIS.INFO.TX_TYP_CD = DCCUHOLD.DATA.TX_TYP_CD;
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P129";
        }
        if (DCCUHOLD.DATA.HLD_FLG == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P145";
        }
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B070_TRANS_DATA_BACK() throws IOException,SQLException,Exception {
        DCCUHOLD.DATA.HLD_NO = DDRHLDR.KEY.HLD_NO;
        DCCUHOLD.DATA.AC_TYPE = WS_AC_TYPE;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                DCCUHOLD.DATA.UAMT = 0;
                DCCUHOLD.DATA.CURR_BAL = DDRCCY.CURR_BAL;
            } else {
                DCCUHOLD.DATA.CURR_BAL = DDRCCY.CURR_BAL;
                DCCUHOLD.DATA.HAMT = DDRCCY.HOLD_BAL;
                DCCUHOLD.DATA.UAMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
                if (DCCUHOLD.DATA.UAMT < 0) {
                    DCCUHOLD.DATA.UAMT = 0;
                }
            }
            DCCUHOLD.DATA.AAMT = WS_ACC_HLD_AMT;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                DCCUHOLD.DATA.UAMT = 0;
                DCCUHOLD.DATA.CURR_BAL = TDRSMST.BAL;
            } else {
                DCCUHOLD.DATA.CURR_BAL = TDRSMST.BAL;
                DCCUHOLD.DATA.HAMT = TDRSMST.HBAL;
                DCCUHOLD.DATA.UAMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
                if (DCCUHOLD.DATA.UAMT < 0) {
                    DCCUHOLD.DATA.UAMT = 0;
                }
            }
            DCCUHOLD.DATA.AAMT = WS_ACC_HLD_AMT;
        }
    }
    public void B080_RHLD_PROC() throws IOException,SQLException,Exception {
        R000_UPD_DDTHLD_CANCEL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = WS_HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        WS_HLD_STS = DDRHLD.HLD_STS;
        WS_HLD_FLG = DDRHLD.HLD_FLG;
        WS_HLD_TYP = DDRHLD.HLD_TYP;
        WS_DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        WS_UP_HLD_NO = DDRHLD.UP_HLD_NO;
        if (DDRHLD.SPR_BR_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAW_HLD_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.HLD_TYP == '2') {
            DDRHLD.HLD_AMT = DDRHLD.HLD_AMT - DCCUHOLD.DATA.AMT;
            if (DDRHLD.HLD_AMT == 0) {
                DDRHLD.HLD_STS = 'C';
            }
        } else {
            DDRHLD.HLD_STS = 'C';
        }
        DDRHLD.REL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_HLD_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_FBID_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_HLD_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_FBID_FLG = 'Y';
            }
            if (DDRHLD.HLD_TYP == '1') {
                if (DDRHLD.HLD_FLG == '1') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "0" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                } else {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                }
            } else {
                DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - DCCUHOLD.DATA.AMT;
            }
            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DDTCCY();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_HLD_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_LAW_FBID_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_HLD_FLG = 'Y';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_STS_BANK_FBID_FLG = 'Y';
            }
            if (DDRHLD.HLD_TYP == '1') {
                if (DDRHLD.HLD_FLG == '1') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "0" + TDRSMST.STSW.substring(3 + 1 - 1);
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "0" + TDRSMST.STSW.substring(7 + 1 - 1);
                }
            } else {
                TDRSMST.HBAL = TDRSMST.HBAL - DCCUHOLD.DATA.AMT;
            }
            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.GUAR_BAL;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_UPDATE_TDTSMST();
            if (pgmRtn) return;
        }
        if (WS_HLD_STS == 'N') {
            if (WS_DOWN_HLD_NO.trim().length() == 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.DOWN_HLD_NO = " ";
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDRHLD);
                WS_CIRCLE_FLG = ' ';
                DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READ_FIRST_DDTHLD();
                if (pgmRtn) return;
                while (WS_CIRCLE_FLG != 'Y') {
                    WS_HLD_AMT = WS_HLD_AMT + DDRHLD.HLD_AMT;
                    if (DDRHLD.DOWN_HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                        WS_CIRCLE_FLG = 'Y';
                    } else {
                        DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                        T000_READ_DDTHLD_UP();
                        if (pgmRtn) return;
                    }
                }
                DDRHLD.DOWN_HLD_NO = WS_DOWN_HLD_NO;
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
                WS_AVL_HLD_AMT = WS_AVL_HLD_AMT - WS_HLD_AMT;
                if (WS_AVL_HLD_AMT > 0) {
                    IBS.init(SCCGWA, DDRHLD);
                    WS_CIRCLE_FLG = ' ';
                    DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
                    T000_READ_DDTHLD_UP();
                    if (pgmRtn) return;
                    DDRHLD.UP_HLD_NO = WS_UP_HLD_NO;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRTIE_DDTHLD();
                    if (pgmRtn) return;
                    T000_READ_DDTHLD_UP();
                    if (pgmRtn) return;
                    while (WS_CIRCLE_FLG != 'Y') {
                        if (DDRHLD.HLD_TYP == '1') {
                            WS_CIRCLE_FLG = 'Y';
                            if (DDRHLD.HLD_STS == 'W') {
                                DDRHLD.HLD_STS = 'N';
                                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                                T000_REWRITE_DDTHLD();
                                if (pgmRtn) return;
                            }
                        } else {
                            WS_AVL_HLD_AMT = WS_AVL_HLD_AMT - DDRHLD.HLD_AMT;
                            if (WS_AVL_HLD_AMT >= 0) {
                                if (DDRHLD.HLD_STS == 'W') {
                                    DDRHLD.HLD_STS = 'N';
                                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                }
                            } else {
                                if (WS_CIRCLE_FLG != 'Y') {
                                    if (DDRHLD.HLD_STS == 'W') {
                                        DDRHLD.HLD_STS = 'N';
                                        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                                        T000_REWRITE_DDTHLD();
                                        if (pgmRtn) return;
                                    }
                                }
                                WS_CIRCLE_FLG = 'Y';
                            }
                        }
                        if (DDRHLD.DOWN_HLD_NO.trim().length() == 0) {
                            WS_CIRCLE_FLG = 'Y';
                        } else {
                            DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                            T000_READ_DDTHLD_UP();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                                WS_CIRCLE_FLG = 'Y';
                            }
                        }
                    }
                }
            }
        } else {
            if (WS_HLD_STS == 'W') {
                if (WS_DOWN_HLD_NO.trim().length() == 0) {
                    IBS.init(SCCGWA, DDRHLD);
                    DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    DDRHLD.DOWN_HLD_NO = " ";
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, DDRHLD);
                    DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    DDRHLD.DOWN_HLD_NO = WS_DOWN_HLD_NO;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, DDRHLD);
                    DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    DDRHLD.UP_HLD_NO = WS_UP_HLD_NO;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R_GET_TD_PROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPROD);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        CEP.TRC(SCCGWA, TDCPRDP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP);
        if (TDCPRDP.OTH_PRM.FRZ_FLG == '1' 
            || TDCPRDP.OTH_PRM.FRZ_FLG == 'N') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_FRZ_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R_GET_DD_PROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DDRCCY.PROD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCUPARM);
        IBS.init(SCCGWA, DDVMPRD);
        DDCUPARM.TX_TYPE = 'I';
        DDCUPARM.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        if (DDCUPARM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUPARM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD);
        }
        if (DCCUHOLD.DATA.SPR_TYP == '1') {
            if (DDVMPRD.VAL.AUFR_FLG == '1' 
                || DDVMPRD.VAL.AUFR_FLG == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FRZ_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GEN_HLD_NO() throws IOException,SQLException,Exception {
        if (DCCUHOLD.DATA.HLD_NO.trim().length() == 0) {
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = "SEQ";
            BPCSGSEQ.CODE = "HOLD";
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1).trim().length() == 0) WS_HLD_NO_TLR.WS_HLD_DATE = 0;
            else WS_HLD_NO_TLR.WS_HLD_DATE = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1));
            WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
            WS_HLD_NO_TLR.WS_HLD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
            WS_HLD_NO_TLR.WS_HLD_SYSNO = 0;
            WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
            WS_HLD_NO_TLR.WS_HLD_SEQ = (int) BPCSGSEQ.SEQ;
            WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
        } else {
            WS_HLD_NO_SZ = DCCUHOLD.DATA.HLD_NO;
            IBS.CPY2CLS(SCCGWA, WS_HLD_NO_SZ, WS_HLD_NO_TLR);
        }
    }
    public void R000_UPD_DDTHLD() throws IOException,SQLException,Exception {
        DDRHLD.HLD_AMT = DDRHLD.HLD_AMT + DCCUHOLD.DATA.AMT;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
    }
    public void R000_WRITE_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = WS_HLD_NO_SZ;
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DCCUHOLD.DATA.AC;
        DDRHLD.HLD_TYP = DCCUHOLD.DATA.HLD_TYP;
        DDRHLD.SPR_BR_TYP = DCCUHOLD.DATA.SPR_TYP;
        DDRHLD.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDRHLD.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        if (DCCUHOLD.DATA.HLD_TYP != '1') {
            DDRHLD.HLD_AMT = DCCUHOLD.DATA.AMT;
        } else {
            DDRHLD.HLD_AMT = 0;
        }
        DDRHLD.EFF_DATE = DCCUHOLD.DATA.EFF_DT;
        DDRHLD.EXP_DATE = DCCUHOLD.DATA.EXP_DT;
        if (WS_HLD_WAIT_FLG == 'Y' 
            && WS_OLD_HOLD_FLG == 'Y') {
            DDRHLD.HLD_STS = 'W';
        } else {
            DDRHLD.HLD_STS = 'N';
        }
        DDRHLD.HLD_WRIT_NO = DCCUHOLD.DATA.SPR_NO;
        DDRHLD.HLD_BR_NM = DCCUHOLD.DATA.SPR_NM;
        DDRHLD.HLD_RSN = DCCUHOLD.DATA.RSN;
        DDRHLD.REMARK = DCCUHOLD.DATA.RMK;
        DDRHLD.LAW_OFF_NM1 = DCCUHOLD.DATA.LAW_NM1;
        DDRHLD.LAW_ID_NO1 = DCCUHOLD.DATA.LAW_NO1;
        DDRHLD.LAW_OFF_NM2 = DCCUHOLD.DATA.LAW_NM2;
        DDRHLD.LAW_ID_NO2 = DCCUHOLD.DATA.LAW_NO2;
        DDRHLD.SPR_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLD.SPR_BR = DCCUHOLD.DATA.HLD_BR;
        DDRHLD.DOWN_HLD_NO = WS_DOWN_HLD_NO;
        DDRHLD.UP_HLD_NO = WS_UP_HLD_NO;
        DDRHLD.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLD.HLD_CLS = DCCUHOLD.DATA.HLD_CLS;
        DDRHLD.HLD_FLG = DCCUHOLD.DATA.HLD_FLG;
        T000_WRITE_DDTHLD();
        if (pgmRtn) return;
    }
    public void R000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        if (DCCUHOLD.DATA.HLD_TYP == 'A') {
            DDRHLDR.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        } else {
            DDRHLDR.KEY.HLD_NO = WS_HLD_NO_SZ;
        }
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.HLD_TYP = DCCUHOLD.DATA.HLD_TYP;
        if (DCCUHOLD.DATA.HLD_TYP == 'A') {
            DDRHLDR.HLD_TYP = '2';
        }
        if (DCCUHOLD.DATA.HLD_TYP == '1') {
            if (DCCUHOLD.DATA.HLD_FLG == '2') {
                DDRHLDR.HLD_TYP = 'A';
            }
        }
        DDRHLDR.AC = WS_TR_AC;
        if (DCCUHOLD.DATA.HLD_TYP != '1') {
            DDRHLDR.TR_AMT = DCCUHOLD.DATA.AMT;
            DDRHLDR.BEF_TR_AMT = WS_ACC_HLD_AMT;
        } else {
            DDRHLDR.TR_AMT = 0;
            DDRHLDR.BEF_TR_AMT = WS_ACC_HLD_AMT;
        }
        DDRHLDR.SPR_BR_TYP = DCCUHOLD.DATA.SPR_TYP;
        DDRHLDR.SPR_BR_NM = DCCUHOLD.DATA.SPR_NM;
        DDRHLDR.HLD_FLG = DCCUHOLD.DATA.HLD_FLG;
        DDRHLDR.CHG_WRIT_NO = DCCUHOLD.DATA.SPR_NO;
        DDRHLDR.CHG_RSN = DCCUHOLD.DATA.RSN;
        DDRHLDR.LAW_OFF_NM1 = DCCUHOLD.DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCUHOLD.DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCUHOLD.DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCUHOLD.DATA.LAW_NO2;
        DDRHLDR.DOWN_HLD_NO = WS_DOWN_HLD_NO;
        DDRHLDR.UP_HLD_NO = WS_UP_HLD_NO;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void R000_UPD_DDTHLD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        T000_STARTBR_DDTHLDR();
        if (pgmRtn) return;
        T000_READNEXT_DDTHLDR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLDR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                DDRHLDR.REVERSE_FLG = 'Y';
                DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLDR();
                if (pgmRtn) return;
                WS_HLD_NO = DDRHLDR.KEY.HLD_NO;
                T000_READNEXT_DDTHLDR();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_DDTHLDR();
        if (pgmRtn) return;
    }
    public void R000_GET_HLD_BANK_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        T000_READ_LAST_DDTHLD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRHLD.SPR_BR_TYP == '1') {
                WS_BANK_HLD_AMT = 0;
                if (WS_STS_BANK_HLD_FLG == 'Y' 
                    || WS_STS_BANK_FBID_FLG == 'Y') {
                    WS_BANK_HOLD_FLG = 'Y';
                }
            } else {
                if (DDRHLD.HLD_STS == 'N') {
                    WS_BANK_HLD_AMT = WS_BANK_HLD_AMT + DDRHLD.HLD_AMT;
                }
                WS_RECORD_HLD_NO = " ";
                WS_CIRCLE_FLG = ' ';
                WS_RECORD_HLD_NO = DDRHLD.UP_HLD_NO;
                while (WS_CIRCLE_FLG != 'Y') {
                    if (WS_RECORD_HLD_NO.trim().length() == 0) {
                        WS_CIRCLE_FLG = 'Y';
                    } else {
                        IBS.init(SCCGWA, DDRHLD);
                        DDRHLD.KEY.HLD_NO = WS_RECORD_HLD_NO;
                        T000_READ_DDTHLD_UP();
                        if (pgmRtn) return;
                        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                            WS_CIRCLE_FLG = 'Y';
                        } else {
                            if (DDRHLD.SPR_BR_TYP == '1') {
                                WS_CIRCLE_FLG = 'Y';
                                if (WS_STS_BANK_HLD_FLG == 'Y' 
                                    || WS_STS_BANK_FBID_FLG == 'Y') {
                                    WS_BANK_HOLD_FLG = 'Y';
                                }
                            } else {
                                if (DDRHLD.HLD_STS == 'N') {
                                    WS_BANK_HLD_AMT = WS_BANK_HLD_AMT + DDRHLD.HLD_AMT;
                                }
                                WS_RECORD_HLD_NO = DDRHLD.UP_HLD_NO;
                            }
                        }
                    }
                }
            }
        }
    }
    public void R000_UPDATE_WAIT_HOLD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        T000_READ_LAST_DDTHLD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DCCUHOLD.DATA.SPR_TYP == '1') {
                R001_UPDATE_WAIT_LAW_HOLD_REC();
                if (pgmRtn) return;
            } else {
                R002_UPDATE_WAIT_BANK_HOLD_REC();
                if (pgmRtn) return;
            }
        }
    }
    public void R001_UPDATE_WAIT_LAW_HOLD_REC() throws IOException,SQLException,Exception {
        if (DDRHLD.SPR_BR_TYP == '1') {
            DDRHLD.DOWN_HLD_NO = WS_HLD_NO_SZ;
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
            WS_UP_HLD_NO = DDRHLD.KEY.HLD_NO;
            WS_DOWN_HLD_NO = " ";
        } else {
            WS_RECORD_HLD_NO = " ";
            WS_CIRCLE_FLG = ' ';
            WS_RECORD_HLD_NO = DDRHLD.UP_HLD_NO;
            while (WS_CIRCLE_FLG != 'Y') {
                if (WS_RECORD_HLD_NO.trim().length() == 0) {
                    WS_CIRCLE_FLG = 'Y';
                    WS_DOWN_HLD_NO = DDRHLD.KEY.HLD_NO;
                    WS_UP_HLD_NO = " ";
                    DDRHLD.UP_HLD_NO = WS_HLD_NO_SZ;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, DDRHLD.UP_HLD_NO);
                    DDRHLD.KEY.HLD_NO = DDRHLD.UP_HLD_NO;
                    T000_READ_DDTHLD_UP();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        WS_CIRCLE_FLG = 'Y';
                        WS_DOWN_HLD_NO = DDRHLD.KEY.HLD_NO;
                        WS_UP_HLD_NO = " ";
                    } else {
                        if (DDRHLD.SPR_BR_TYP == '1') {
                            WS_RECORD_HLD_NO = DDRHLD.DOWN_HLD_NO;
                            DDRHLD.DOWN_HLD_NO = WS_HLD_NO_SZ;
                            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                            T000_REWRITE_DDTHLD();
                            if (pgmRtn) return;
                            WS_UP_HLD_NO = DDRHLD.KEY.HLD_NO;
                            WS_DOWN_HLD_NO = WS_RECORD_HLD_NO;
                            DDRHLD.KEY.HLD_NO = WS_RECORD_HLD_NO;
                            T000_READ_DDTHLD_UP();
                            if (pgmRtn) return;
                            DDRHLD.UP_HLD_NO = WS_HLD_NO_SZ;
                            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                            T000_REWRITE_DDTHLD();
                            if (pgmRtn) return;
                            WS_CIRCLE_FLG = 'Y';
                        } else {
                            WS_RECORD_HLD_NO = " ";
                            WS_RECORD_HLD_NO = DDRHLD.UP_HLD_NO;
                        }
                    }
                }
            }
        }
    }
    public void R002_UPDATE_WAIT_BANK_HOLD_REC() throws IOException,SQLException,Exception {
        DDRHLD.DOWN_HLD_NO = WS_HLD_NO_SZ;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
        WS_UP_HLD_NO = DDRHLD.KEY.HLD_NO;
        WS_DOWN_HLD_NO = " ";
    }
    public void R000_HLD_BANK_PROC() throws IOException,SQLException,Exception {
        WS_HLD_UPDATE_FLG = 'N';
        WS_BANK_ACC_AMT = WS_AVL_HLD_AMT - DCCUHOLD.DATA.AMT;
        IBS.init(SCCGWA, DDRHLD);
        WS_CIRCLE_FLG = ' ';
        DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
        while (WS_CIRCLE_FLG != 'Y') {
            if (DDRHLD.KEY.HLD_NO.trim().length() == 0) {
                WS_CIRCLE_FLG = 'Y';
            } else {
                T000_READ_DDTHLD_UP();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_CIRCLE_FLG = 'Y';
                } else {
                    if (DDRHLD.HLD_STS == 'W') {
                        WS_CIRCLE_FLG = 'Y';
                    } else {
                        if (DDRHLD.HLD_TYP == '2') {
                            WS_BANK_ACC_AMT = WS_BANK_ACC_AMT - DDRHLD.HLD_AMT;
                            if (WS_BANK_ACC_AMT < 0) {
                                WS_HLD_UPDATE_FLG = 'Y';
                                WS_BANK_ACC_AMT_Z = ( -1 ) * WS_BANK_ACC_AMT;
                            }
                            if (WS_HLD_UPDATE_FLG == 'Y') {
                                if (WS_BANK_ACC_AMT_Z >= DDRHLD.HLD_AMT) {
                                    if (WS_BANK_HOLD_FLG == 'Y') {
                                        DDRHLD.HLD_STS = 'W';
                                        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                                        T000_REWRITE_DDTHLD();
                                        if (pgmRtn) return;
                                    }
                                    WS_AMT_HLD_AMT = WS_AMT_HLD_AMT + DDRHLD.HLD_AMT;
                                    WS_WAIT_UPDATE_FLG = 'Y';
                                }
                            }
                        } else {
                            WS_CIRCLE_FLG = 'Y';
                            if (WS_BANK_ACC_AMT <= 0) {
                                if (WS_BANK_HOLD_FLG == 'Y') {
                                    DDRHLD.HLD_STS = 'W';
                                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                    }
                }
                WS_RECORD_HLD_NO = DDRHLD.DOWN_HLD_NO;
                DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
            }
        }
    }
    public void T000_READ_FIRST_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = 'N' "
            + "AND HLD_UP_HLD_NO = SPACES";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
    }
    public void T000_READ_LAST_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' ) "
            + "AND DOWN_HLD_NO = ' '";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_STS_WORD,AC_TYPE,AC_PURP, LAST_DATE,LAST_TLR,UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void T000_WRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_BR.rp = new DBParm();
        DDTHLDR_BR.rp.TableName = "DDTHLDR";
        DDTHLDR_BR.rp.where = "TR_DATE = :DDRHLDR.KEY.TR_DATE "
            + "AND TR_JRNNO = :DDRHLDR.KEY.TR_JRNNO";
        DDTHLDR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRHLDR, this, DDTHLDR_BR);
    }
    public void T000_READNEXT_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLDR, this, DDTHLDR_BR);
    }
    public void T000_ENDBR_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLDR_BR);
    }
    public void T000_REWRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.REWRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
    }
    public void T000_REWRTIE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLD_UP() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void T000_READ_DDTHLD_BANK() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_TYP = :DDRHLD.HLD_TYP "
            + "AND HLD_FLG = :DDRHLD.HLD_FLG "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND ( HLD_STS = 'N' "
            + "OR 'W' )";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCKLS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-MPRD", DDCUPARM);
        CEP.TRC(SCCGWA, DDCUPARM.RC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
