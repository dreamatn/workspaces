package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.DP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSTZM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCALL_RD;
    DBParm TDTPMPD_RD;
    DBParm DCTIAACR_RD;
    DBParm DCTACLNK_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    brParm TDTCALL_BR = new brParm();
    brParm DCTIAACR_BR = new brParm();
    boolean pgmRtn = false;
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_ADD_NOT_FMT = "TD509";
    String K_UPDI_NOT_FMT = "TD510";
    String K_UPDO_NOT_FMT = "TD511";
    String K_CDI_NOT_FMT = "TD512";
    String K_CDO_NOT_FMT = "TD513";
    String K_BRW_NOT_FMT = "TD514";
    String K_INQ_NOT_FMT = "TD515";
    String K_PRDP_TYP = "PRDPR";
    String K_OUTPUT_FMT = "TD103";
    String K_OUTPUT_FMT_B = "TD114";
    String K_HIS_CPB_NM = "TDCHCALL";
    String K_HIS_RMKS = "WITHDRAW NOTICE OF CALL DEPOSIT";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    short K_SCR_ROW_NO = 10;
    String K_AC_BK = "043";
    String WS_ERR_MSG = " ";
    TDZSTZM_WS_RC_MSG WS_RC_MSG = new TDZSTZM_WS_RC_MSG();
    short WS_MTHS = 0;
    short WS_DAYS = 0;
    int WS_APPO_DATE = 0;
    double WS_TOT_AMT = 0;
    double WS_LEFT_AMT = 0;
    String WS_TMP_AC = " ";
    short WS_TERM = 0;
    String WS_TERMC = " ";
    TDZSTZM_REDEFINES13 REDEFINES13 = new TDZSTZM_REDEFINES13();
    int WS_NUM = 0;
    TDZSTZM_WS_TABLES_INFO WS_TABLES_INFO = new TDZSTZM_WS_TABLES_INFO();
    String CI_NO_TD = " ";
    String CI_NO_DD = " ";
    short WS_I = 0;
    char WS_IS_HOD = ' ';
    int WS_APPO_DATE2 = 0;
    TDZSTZM_CP_PROD_CD CP_PROD_CD = new TDZSTZM_CP_PROD_CD();
    char WS_CCY_DEF_FLG = ' ';
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_NUM1 = 0;
    int WS_NUM2 = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    char WS_CALL_CLS = ' ';
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_DRAW_AMT = 0;
    double WS_MIN_LEFT_AMTC = 0;
    double WS_MAX_AMTC = 0;
    String WS_RUL_CDC = " ";
    short WS_CNT = 0;
    short WS_W = 0;
    char WS_CCY_FOUND = ' ';
    TDZSTZM_WS_FMT WS_FMT = new TDZSTZM_WS_FMT();
    double WS_MIN_DRAW_AMT_USD = 0;
    double WS_MIN_LEFT_AMTC_USD = 0;
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    int WS_TEMP_CALL_DT = 0;
    char WS_YBT_AC_FLAG = ' ';
    char WS_CALL_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_IAACR_FLG = ' ';
    char WS_BV_IN_PING = ' ';
    int WS_COUNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCOCALL TDCOCALL = new TDCOCALL();
    TDCHCALL TDCCALLO = new TDCHCALL();
    TDCHCALL TDCCALLN = new TDCHCALL();
    TDCOANOT TDCOANOT = new TDCOANOT();
    TDCOCDI TDCOCDI = new TDCOCDI();
    TDCOCDO TDCOCDO = new TDCOCDO();
    TDCOBNOT TDCOBNOT = new TDCOBNOT();
    TDCOINOT TDCOINOT = new TDCOINOT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DCCUAINQ DCCUAINQ = new DCCUAINQ();
    DDRMST DDRMST = new DDRMST();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    CICCUST CICCUST = new CICCUST();
    BPCEX BPCEX = new BPCEX();
    CICQACRI CICQACRI = new CICQACRI();
    TDRCMST TDRCMST = new TDRCMST();
    CICQACAC CICQACAC = new CICQACAC();
    TDCF515 TDCF515 = new TDCF515();
    BPCFX BPCFX = new BPCFX();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCALL TDRCALL = new TDRCALL();
    TDRPMPD TDRPMPD = new TDRPMPD();
    DCRACLNK DCRACLNK = new DCRACLNK();
    TDCBVCD TDCBVCD = new TDCBVCD();
    short WS_CCY_CNT = 0;
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCSTZM TDCSTZM;
    public void MP(SCCGWA SCCGWA, TDCSTZM TDCSTZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSTZM = TDCSTZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSTZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, TDCF515);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSTZM.FUNC);
        if (TDCSTZM.FUNC == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DOCU_FUNC_ERR);
        }
        CEP.TRC(SCCGWA, TDCSTZM.BV_NO);
        CEP.TRC(SCCGWA, TDCSTZM.AC_SEQ);
        if (TDCSTZM.BV_NO.trim().length() == 0 
            && TDCSTZM.AC_SEQ == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SEQ_BV_NEED);
        }
        CEP.TRC(SCCGWA, TDCSTZM.AC_NO);
        if (TDCSTZM.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
        CEP.TRC(SCCGWA, TDCSTZM.TZ_TYP);
        if (TDCSTZM.TZ_TYP == ' ' 
            && TDCSTZM.FUNC == '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            if (TDCSTZM.CALL_DATE != SCCGWA.COMM_AREA.AC_DATE 
                && TDCSTZM.CALL_DATE != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOTI_DATE_WARNING);
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCSTZM.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = TDCSTZM.AC_NO;
        if (CICQACRI.O_DATA.O_STSW == null) CICQACRI.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICQACRI.O_DATA.O_STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICQACRI.O_DATA.O_STSW += " ";
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1));
        if (CICQACRI.O_DATA.O_STSW == null) CICQACRI.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICQACRI.O_DATA.O_STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICQACRI.O_DATA.O_STSW += " ";
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1));
        if (TDCSTZM.AC_SEQ != 0) {
            CICQACAC.DATA.AGR_SEQ = TDCSTZM.AC_SEQ;
        }
        if (CICQACRI.O_DATA.O_ENTY_TYP != '2') {
            if (CICQACRI.O_DATA.O_STSW == null) CICQACRI.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICQACRI.O_DATA.O_STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICQACRI.O_DATA.O_STSW += " ";
            if (CICQACRI.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CICQACAC.DATA.AGR_SEQ = TDCSTZM.AC_SEQ;
            }
            if (CICQACRI.O_DATA.O_STSW == null) CICQACRI.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICQACRI.O_DATA.O_STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICQACRI.O_DATA.O_STSW += " ";
            if (CICQACRI.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CICQACAC.DATA.BV_NO = TDCSTZM.BV_NO;
            }
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCSTZM.AC_NO;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRBVT);
            if (TDRCMST.BV_TYP == '0') {
                if (TDRSMST.BV_TYP != '0' 
                    && TDRSMST.BV_TYP != '4' 
                    && TDRSMST.BV_TYP != ' ') {
                    CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
                    TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
                    T000_READ_TDTBVT();
                    if (pgmRtn) return;
                }
            } else {
                TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
                T000_READ_TDTBVT();
                if (pgmRtn) return;
            }
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if ((TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                || (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
                || (TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1"))) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
            }
        } else {
            CICQACAC.FUNC = 'S';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_FRZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = TDRSMST.AC_NO;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            if (TDCSTZM.APPO_AMT <= 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AMT_TOO_LITTLE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            B015_GET_PROD_INFO();
            if (pgmRtn) return;
            if (TDCSTZM.FUNC != '5' 
                && TDCSTZM.FUNC != '4' 
                && TDCSTZM.FUNC != '6') {
                B030_CHECK_NOTICE_INPUT();
                if (pgmRtn) return;
            }
            B010_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "F-BUG2");
            if (TDCSTZM.FUNC != '4' 
                && TDCSTZM.FUNC != '5') {
                B010_03_CHECK_AC_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.BV_TYP != '4') {
                CEP.TRC(SCCGWA, TDRBVT.BV_NO);
                CEP.TRC(SCCGWA, TDCSTZM.BV_NO);
                if (!TDCSTZM.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO) 
                    && TDCSTZM.BV_NO.trim().length() > 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
                }
            }
            B015_GET_PROD_INFO();
            if (pgmRtn) return;
            if (TDCSTZM.FUNC != '5' 
                && TDCSTZM.FUNC != '4' 
                && TDCSTZM.FUNC != '6') {
                B030_CHECK_NOTICE_INPUT();
                if (pgmRtn) return;
            }
            B010_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    if (TDCSTZM.FUNC == '0') {
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(25 - 1, 25 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_IS_NO_NOTI);
        }
    }
    public void B010_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCSTZM.FUNC == '0') {
            B100_ADD_NOT_PROC();
            if (pgmRtn) return;
        } else if (TDCSTZM.FUNC == '1') {
            B200_UPD_NOT_PROC();
            if (pgmRtn) return;
        } else if (TDCSTZM.FUNC == '2'
            || TDCSTZM.FUNC == '3') {
            B300_CAN_DEL_PROC();
            if (pgmRtn) return;
        } else if (TDCSTZM.FUNC == '4') {
            B500_INQ_NOT_PROC();
            if (pgmRtn) return;
        } else if (TDCSTZM.FUNC == '5') {
            B400_03_BRW_CHK_AC();
            if (pgmRtn) return;
            if (TDRCMST.KEY.AC_NO.trim().length() == 0 
                && (TDCSTZM.AC_SEQ == 0 
                || TDCSTZM.AC_SEQ == 0)) {
                B400_01_BRW_NOT_PROC();
                if (pgmRtn) return;
            } else {
                B400_BRW_NOT_PROC();
                if (pgmRtn) return;
            }
        } else if (TDCSTZM.FUNC == '6') {
            B700_ALL_DEL_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_03_CHECK_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IF-1-1");
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, "IF-1-2");
        CEP.TRC(SCCGWA, TDCSTZM.CALL_DATE);
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            if (TDCSTZM.CALL_DATE < TDRSMST.VAL_DATE 
                && TDCSTZM.CALL_DATE != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_CALL_DT_ERR);
            }
        }
        CEP.TRC(SCCGWA, "IF-2-1");
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("022")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH);
        }
        CEP.TRC(SCCGWA, "IF-2-2");
        CEP.TRC(SCCGWA, "IF-3-1");
        CEP.TRC(SCCGWA, TDCSTZM.APPO_AMT);
        if (TDCSTZM.APPO_AMT > TDRSMST.BAL) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_APPO_AMT_GE_BAL);
        }
        CEP.TRC(SCCGWA, "IF-3-2");
        if (TDRCMST.STS == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (TDRCMST.STS == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        CEP.TRC(SCCGWA, "IF-3-2-1");
        if (TDRSMST.ACO_STS == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (TDRSMST.ACO_STS == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && TDCSTZM.FUNC != '4' 
            && TDCSTZM.FUNC != '5') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_LEG_FROZEN);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && TDCSTZM.FUNC != '4' 
            && TDCSTZM.FUNC != '5') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_SPE_FROZEN);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && TDCSTZM.FUNC != '4' 
            && TDCSTZM.FUNC != '5') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_TEM_HELD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            && TDCSTZM.FUNC != '4' 
            && TDCSTZM.FUNC != '5') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_ORAL_REG_LOS);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1") 
            && TDCSTZM.FUNC != '4' 
            && TDCSTZM.FUNC != '5') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NORMAL_REG_LOS);
        }
        if (TDCSTZM.BV_TYP != ' ' 
            && TDRSMST.BV_TYP != TDCSTZM.BV_TYP 
            && TDRCMST.BV_TYP != TDCSTZM.BV_TYP) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_NOT_MATCH);
        }
        if (TDCSTZM.AUT_TRF_TYP == '0' 
            || TDCSTZM.AUT_TRF_TYP == '1') {
            if (TDRSMST.BV_TYP == '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NON_CRD_IN_CASH_NOT);
            }
        }
        if (TDCSTZM.AUT_TRF_TYP == '0') {
            if (TDRSMST.OPEN_PAY_MTH == '0' 
                || TDRSMST.OPEN_PAY_MTH == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NON_CRD_IN_CASH_NOT);
            }
        }
        if (TDCSTZM.AUT_TRF_TYP == '1') {
            CEP.TRC(SCCGWA, TDCSTZM.AUT_MTH);
            if (TDCSTZM.AUT_MTH != '2' 
                && TDCSTZM.AUT_MTH != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AUTO_MTH_ERROR);
            }
            TDRCALL.AUTO_MTH = TDCSTZM.AUT_MTH;
            TDRCALL.AUTO_AC = TDCSTZM.AUT_AC;
            TDRCALL.AUTO_AC_SEQ = TDCSTZM.AUT_AC_SEQ;
        }
        if (TDRSMST.BV_TYP != '4') {
            CEP.TRC(SCCGWA, TDRBVT.BV_NO);
            CEP.TRC(SCCGWA, TDCSTZM.BV_NO);
            if (!TDCSTZM.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO) 
                && TDCSTZM.BV_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
            }
        }
    }
    public void B015_GET_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCQPMP);
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
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDRSMST.CCY)) {
                WS_MIN_DRAW_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MDRW_AMT;
                WS_MIN_LEFT_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT;
                WS_MIN_CCYC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC;
                WS_CCY_FOUND = 'Y';
                WS_W = WS_CNT;
            }
        }
        for (WS_CNT = 1; WS_CNT <= 22; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCPROD.PROD_DATA.OTH_PRM.REF_CCY)) {
                WS_MIN_DRAW_AMT_USD = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MDRW_AMT;
                WS_MIN_LEFT_AMTC_USD = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MLET_AMT;
                WS_CNT = 99;
            }
        }
        R000_CHK_PRD_CCY_DEF();
        if (pgmRtn) return;
        if (WS_CCY_DEF_FLG == 'N') {
            if (WS_MIN_DRAW_AMT_USD != 0) {
                WS_BUY_AMT = WS_MIN_DRAW_AMT_USD;
                R000_AMT_EX_PROC();
                if (pgmRtn) return;
                WS_MIN_DRAW_AMT = WS_SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_MIN_DRAW_AMT);
            if (WS_MIN_LEFT_AMTC_USD != 0) {
                WS_BUY_AMT = WS_MIN_LEFT_AMTC_USD;
                R000_AMT_EX_PROC();
                if (pgmRtn) return;
                WS_MIN_LEFT_AMTC = WS_SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_MIN_LEFT_AMTC);
        }
        CEP.TRC(SCCGWA, WS_MIN_DRAW_AMT);
        CEP.TRC(SCCGWA, TDCSTZM.APPO_AMT);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            if (TDCSTZM.APPO_AMT < WS_MIN_DRAW_AMT) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOTICE_AMT_ERROR);
            }
        }
    }
    public void B030_CHECK_NOTICE_INPUT() throws IOException,SQLException,Exception {
        R000_CHK_APPO_DATE();
        if (pgmRtn) return;
        if (TDCSTZM.FUNC != '2' 
            && TDCSTZM.FUNC != '6') {
            if (TDCPRDP.OTH_PRM.CCY_INF[1-1].MIN_CCYC.equalsIgnoreCase("156")) {
                if (TDRSMST.BAL > WS_MIN_DRAW_AMT) {
                    if (TDCSTZM.APPO_AMT < WS_MIN_DRAW_AMT) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOTICE_AMT_ERROR);
                    }
                }
            }
        }
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            if (TDRSMST.INSTR_MTH == '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TZM_INSTR_MTH_ERR);
            }
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
            BPCOCLWD.CAL_CODE = "CH01";
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            if (TDRSMST.TERM.equalsIgnoreCase("D001")) {
                BPCOCLWD.DAYS = -2;
            }
            if (TDRSMST.TERM.equalsIgnoreCase("D007")) {
                BPCOCLWD.DAYS = -8;
            }
            CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.AGR_NO = TDRSMST.AC_NO;
            CICCUST.FUNC = 'A';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            WS_TEMP_CALL_DT = BPCOCLWD.DATE2;
            if (TDCSTZM.CALL_DATE < BPCOCLWD.DATE2 
                && CICCUST.O_DATA.O_CI_TYP != '1' 
                && TDCSTZM.FUNC == '0') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPUT_CALL_DT_ERR);
            }
            if (TDCSTZM.CALL_DATE > BPCOCLWD.DATE2 
                && TDCSTZM.CALL_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && CICCUST.O_DATA.O_CI_TYP != '1' 
                && TDCSTZM.FUNC == '0') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_DT_AUTH);
            }
        }
        if (TDCSTZM.FUNC == '0' 
            || TDCSTZM.FUNC == '1') {
            WS_TOT_AMT = 0;
            TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRCALL.CALL_STS = ALL.charAt(0);
            CEP.TRC(SCCGWA, WS_TOT_AMT);
            CEP.TRC(SCCGWA, TDRCALL.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRCALL.CALL_STS);
            T000_STARTBR_TDTCALL();
            if (pgmRtn) return;
            T000_READNEXT_TDTCALL();
            if (pgmRtn) return;
            while (WS_CALL_FLG != 'N') {
                if (TDCSTZM.CHNL_FLG == 'Y') {
                    CEP.TRC(SCCGWA, TDCSTZM.CHNL_FLG);
                    CEP.TRC(SCCGWA, TDRCALL.CALL_STS);
                    TDCSTZM.CALL_STS = TDRCALL.CALL_STS;
                    CEP.TRC(SCCGWA, TDCSTZM.CALL_STS);
                }
                if (TDRCALL.CALL_STS == '0' 
                    && TDRCALL.APPO_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.TRC(SCCGWA, TDCSTZM.FUNC);
                    CEP.TRC(SCCGWA, TDCSTZM.CALL_NO);
                    CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
                    if (TDCSTZM.FUNC == '1' 
                        && TDCSTZM.CALL_NO == TDRCALL.KEY.CALL_NO) {
                    } else {
                        WS_TOT_AMT = WS_TOT_AMT + TDRCALL.APPO_AMT;
                    }
                }
                T000_READNEXT_TDTCALL();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTCALL();
            if (pgmRtn) return;
            WS_TOT_AMT = WS_TOT_AMT + TDCSTZM.APPO_AMT;
            CEP.TRC(SCCGWA, WS_TOT_AMT);
            if (WS_TOT_AMT > TDRSMST.BAL) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CAL_AMT_GT_THAN_AMT);
            } else {
                WS_LEFT_AMT = TDRSMST.BAL - WS_TOT_AMT;
                if (WS_LEFT_AMT != 0) {
                    if (WS_LEFT_AMT < WS_MIN_LEFT_AMTC) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LEFT_AMT_LT_MIN_AMT);
                    }
                    if (TDRSMST.BAL < WS_MIN_LEFT_AMTC) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LEFT_AMT_LT_MIN_AMT);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, TDCSTZM.FUNC);
        CEP.TRC(SCCGWA, TDCSTZM.CALL_STS);
        if ((TDCSTZM.FUNC == '1' 
            || TDCSTZM.FUNC == '2' 
            || TDCSTZM.FUNC == '3' 
            || TDCSTZM.FUNC == '4') 
            && (TDCSTZM.CALL_NO == 0)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND);
        }
    }
    public void B100_ADD_NOT_PROC() throws IOException,SQLException,Exception {
        B100_01_WRI_TDTCALL();
        if (pgmRtn) return;
        B100_03_UPD_TDTSMST();
        if (pgmRtn) return;
        B100_05_FMT_ADD_NOT();
        if (pgmRtn) return;
        B600_HISTORY_PROC();
        if (pgmRtn) return;
        B520_FMT_INQ_NOT();
        if (pgmRtn) return;
    }
    public void B100_01_WRI_TDTCALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRCALL.CALL_STS = '0';
        TDRCALL.APPO_DATE = WS_APPO_DATE2;
        T000_READ_TDTCALL_NOTICE();
        if (pgmRtn) return;
        if (WS_CALL_FLG == 'Y') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOTICE_EXIST);
        }
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        TDTCALL_RD.set = "WS-COUNT=IFNULL(MAX(CALL_NO),0)";
        TDTCALL_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC";
        IBS.GROUP(SCCGWA, TDRCALL, this, TDTCALL_RD);
        TDRCALL.KEY.CALL_NO = (short) (WS_COUNT + 1);
        CEP.TRC(SCCGWA, TDRCALL.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        if (SCCGWA.COMM_AREA.VCH_NO.trim().length() == 0) TDRCALL.JRNNO = 0;
        else TDRCALL.JRNNO = Long.parseLong(SCCGWA.COMM_AREA.VCH_NO);
        TDRCALL.CALL_DATE = TDCSTZM.CALL_DATE;
        TDRCALL.APPO_DATE = WS_APPO_DATE2;
        TDRCALL.APPO_AMT = TDCSTZM.APPO_AMT;
        TDRCALL.CAN_DATE = TDCSTZM.CAN_DATE;
        TDRCALL.CALL_STS = '0';
        if (TDCSTZM.AUT_TRF_TYP == '0' 
            || TDCSTZM.AUT_TRF_TYP == '1') {
            TDRCALL.AUTO_FLG = 'Y';
        } else {
            TDRCALL.AUTO_FLG = 'N';
        }
        TDRCALL.AUTO_MTH = TDCSTZM.AUT_MTH;
        TDRCALL.AUTO_AC = TDCSTZM.AUT_AC;
        TDRCALL.AUTO_AC_SEQ = TDCSTZM.AUT_AC_SEQ;
        TDRCALL.TEL_NO = TDCSTZM.TEL_NO;
        TDRCALL.ID_TYP = TDCSTZM.ID_TYP;
        TDRCALL.ID_NO = TDCSTZM.ID_NO;
        TDRCALL.OWNER_BK = TDRSMST.OWNER_BK;
        TDRCALL.REMARK = TDCSTZM.REMARK;
        TDRCALL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.CALL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTCALL();
        if (pgmRtn) return;
    }
    public void B100_05_FMT_ADD_NOT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOCALL;
        SCCFMT.DATA_LEN = 529;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_03_UPD_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B200_UPD_NOT_PROC() throws IOException,SQLException,Exception {
        B200_01_UPD_TDTCALL();
        if (pgmRtn) return;
        B200_03_FMT_UPD_INPUT();
        if (pgmRtn) return;
        B600_HISTORY_PROC();
        if (pgmRtn) return;
        B520_FMT_INQ_NOT();
        if (pgmRtn) return;
    }
    public void B200_01_UPD_TDTCALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRCALL.KEY.CALL_NO = TDCSTZM.CALL_NO;
        CEP.TRC(SCCGWA, TDRCALL.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        T000_READ_UPDATE_TDTCALL();
        if (pgmRtn) return;
        if (TDCSTZM.FUNC == '1' 
            && TDRCALL.CALL_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_STS_EXCP);
        }
        if (TDCSTZM.FUNC == '3' 
            && (TDRCALL.CALL_STS != '0' 
            && TDRCALL.CALL_STS != '2')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_STS_EXCP);
        }
        R000_TRANS_DATA_OLD();
        if (pgmRtn) return;
        if (TDCSTZM.CALL_DATE != TDRCALL.CALL_DATE) {
            if (TDCSTZM.CALL_DATE < WS_TEMP_CALL_DT 
                && CICCUST.O_DATA.O_CI_TYP != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_APPO_DATE_ERR);
            }
            if (TDCSTZM.CALL_DATE > WS_TEMP_CALL_DT 
                && TDCSTZM.CALL_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && CICCUST.O_DATA.O_CI_TYP != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_DT_AUTH);
            }
            TDRCALL.CALL_STS = '0';
            TDRCALL.APPO_DATE = WS_APPO_DATE2;
            T000_READ_TDTCALL_NOTICE();
            if (pgmRtn) return;
            if (WS_CALL_FLG == 'Y') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOTICE_EXIST);
            }
        }
        CEP.TRC(SCCGWA, TDCSTZM.CALL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        TDRCALL.CALL_DATE = TDCSTZM.CALL_DATE;
        TDRCALL.APPO_AMT = TDCSTZM.APPO_AMT;
        TDRCALL.APPO_DATE = WS_APPO_DATE2;
        if (TDCSTZM.AUT_TRF_TYP == '0' 
            || TDCSTZM.AUT_TRF_TYP == '1') {
            TDRCALL.AUTO_FLG = 'Y';
        } else {
            TDRCALL.AUTO_FLG = 'N';
        }
        TDRCALL.AUTO_MTH = TDCSTZM.AUT_MTH;
        TDRCALL.AUTO_AC = TDCSTZM.AUT_AC;
        TDRCALL.AUTO_AC_SEQ = TDCSTZM.AUT_AC_SEQ;
        TDRCALL.TEL_NO = TDCSTZM.TEL_NO;
        TDRCALL.ID_TYP = TDCSTZM.ID_TYP;
        TDRCALL.ID_NO = TDCSTZM.ID_NO;
        TDRCALL.OWNER_BK = TDRSMST.OWNER_BK;
        TDRCALL.REMARK = TDCSTZM.REMARK;
        TDRCALL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.CALL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, "OUT UPDATE");
        CEP.TRC(SCCGWA, TDCSTZM.CALL_DATE);
        CEP.TRC(SCCGWA, TDRCALL.CALL_DATE);
        CEP.TRC(SCCGWA, TDRCALL.APPO_DATE);
        CEP.TRC(SCCGWA, TDRCALL.APPO_DATE);
        T000_UPDATE_TDTCALL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRCALL.CALL_DATE);
        R000_TRANS_DATA_NEW();
        if (pgmRtn) return;
    }
    public void B200_03_FMT_UPD_INPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOCALL;
        SCCFMT.DATA_LEN = 529;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_CAN_DEL_PROC() throws IOException,SQLException,Exception {
        B300_01_UPD_TDTCALL();
        if (pgmRtn) return;
        B300_03_FMT_UPD_INPUT();
        if (pgmRtn) return;
        B600_HISTORY_PROC();
        if (pgmRtn) return;
    }
    public void B300_01_UPD_TDTCALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRCALL.KEY.CALL_NO = TDCSTZM.CALL_NO;
        CEP.TRC(SCCGWA, TDRCALL.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        T000_READ_UPDATE_TDTCALL();
        if (pgmRtn) return;
        if (TDCSTZM.FUNC == '2' 
            && TDRCALL.CALL_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_STS_EXCP);
        }
        if (TDCSTZM.FUNC == '3' 
            && (TDRCALL.CALL_STS != '0' 
            && TDRCALL.CALL_STS != '2')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CALL_STS_EXCP);
        }
        R000_TRANS_DATA_OLD();
        if (pgmRtn) return;
        if (TDCSTZM.FUNC == '2') {
            TDRCALL.CALL_STS = '2';
            TDRCALL.CAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (TDCSTZM.FUNC == '3') {
            TDRCALL.CALL_STS = '4';
        }
        TDRCALL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTCALL();
        if (pgmRtn) return;
        R000_TRANS_DATA_NEW();
        if (pgmRtn) return;
    }
    public void B300_03_FMT_UPD_INPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOCALL;
        SCCFMT.DATA_LEN = 529;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B500_INQ_NOT_PROC() throws IOException,SQLException,Exception {
        B510_INQ_TDTCALL();
        if (pgmRtn) return;
        B520_FMT_INQ_NOT();
        if (pgmRtn) return;
    }
    public void B400_BRW_NOT_PROC() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        WS_CALL_CLS = 'R';
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CALL_CLS);
        CEP.TRC(SCCGWA, WS_P_ROW);
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, TDCSTZM.CALL_STS);
        if (TDCSTZM.CALL_STS == ' ') {
            TDRCALL.CALL_STS = ALL.charAt(0);
        } else {
            TDRCALL.CALL_STS = TDCSTZM.CALL_STS;
        }
        CEP.TRC(SCCGWA, TDRCALL.CALL_STS);
        CEP.TRC(SCCGWA, TDRCALL.KEY.ACO_AC);
        T000_STARTBR_TDTCALL();
        if (pgmRtn) return;
        T000_READNEXT_TDTCALL();
        if (pgmRtn) return;
        while (WS_CALL_FLG != 'N') {
            WS_L_CNT += 1;
            T000_READNEXT_TDTCALL();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTCALL();
        if (pgmRtn) return;
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            IBS.init(SCCGWA, TDCF515.PAGE_INF);
            TDCF515.PAGE_INF.TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = TDCF515.PAGE_INF.TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((TDCF515.PAGE_INF.TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                TDCF515.PAGE_INF.TOTAL_PAGE = WS_T_PAGE;
                if (WS_L_CNT != 0) {
                    WS_L_ROW = WS_P_ROW;
                }
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                TDCF515.PAGE_INF.TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (TDCSTZM.PAGE_NUM != 0) {
                if (TDCSTZM.PAGE_NUM >= TDCF515.PAGE_INF.TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    TDCF515.PAGE_INF.CURR_PAGE = TDCF515.PAGE_INF.TOTAL_PAGE;
                    TDCF515.PAGE_INF.LAST_PAGE = 'Y';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    TDCF515.PAGE_INF.CURR_PAGE = TDCSTZM.PAGE_NUM;
                    TDCF515.PAGE_INF.LAST_PAGE = 'N';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, TDCSTZM.PAGE_NUM);
            if (TDCSTZM.PAGE_NUM == 0) {
                TDCF515.PAGE_INF.CURR_PAGE = 1;
                if (TDCF515.PAGE_INF.TOTAL_PAGE == 1) {
                    TDCF515.PAGE_INF.LAST_PAGE = 'Y';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_L_ROW;
                } else {
                    TDCF515.PAGE_INF.LAST_PAGE = 'N';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.CURR_PAGE);
            WS_P_NUM = TDCF515.PAGE_INF.CURR_PAGE - 1;
            CEP.TRC(SCCGWA, WS_P_NUM);
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_STR_NUM);
            WS_END_NUM = TDCF515.PAGE_INF.CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_END_NUM);
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.TOTAL_PAGE);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.PAGE_ROW);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            IBS.init(SCCGWA, TDRCALL);
            TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            if (TDCSTZM.CALL_STS == ' ') {
                TDRCALL.CALL_STS = ALL.charAt(0);
            } else {
                TDRCALL.CALL_STS = TDCSTZM.CALL_STS;
            }
            T000_STARTBR_TDTCALL();
            if (pgmRtn) return;
            T000_READNEXT_TDTCALL();
            if (pgmRtn) return;
            for (WS_NUM1 = 1; WS_CALL_FLG != 'N'; WS_NUM1 += 1) {
                if (WS_NUM1 > WS_STR_NUM 
                    && WS_NUM1 <= WS_END_NUM) {
                    WS_NUM2 += 1;
                    B400_10_10_OUTPUT_LIST();
                    if (pgmRtn) return;
                }
                T000_READNEXT_TDTCALL();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTCALL();
            if (pgmRtn) return;
        } else {
            TDCF515.PAGE_INF.TOTAL_NUM = 0;
            TDCF515.PAGE_INF.TOTAL_PAGE = 0;
            TDCF515.PAGE_INF.CURR_PAGE = 0;
            TDCF515.PAGE_INF.LAST_PAGE = 'Y';
            TDCF515.PAGE_INF.PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_B;
        SCCFMT.DATA_PTR = TDCF515;
        SCCFMT.DATA_LEN = 7422;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_01_BRW_NOT_PROC() throws IOException,SQLException,Exception {
        WS_L_CNT = 0;
        WS_CALL_CLS = 'R';
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CALL_CLS);
        CEP.TRC(SCCGWA, WS_P_ROW);
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = TDCSTZM.AC_NO;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_READ_DCTACLNK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
        T000_START_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        while (WS_IAACR_FLG != 'N') {
            IBS.init(SCCGWA, TDRCALL);
            TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            if (TDCSTZM.CALL_STS == ' ') {
                TDRCALL.CALL_STS = ALL.charAt(0);
            } else {
                TDRCALL.CALL_STS = TDCSTZM.CALL_STS;
            }
            T000_STARTBR_TDTCALL();
            if (pgmRtn) return;
            T000_READNEXT_TDTCALL();
            if (pgmRtn) return;
            while (WS_CALL_FLG != 'N') {
                WS_L_CNT += 1;
                T000_READNEXT_TDTCALL();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTCALL();
            if (pgmRtn) return;
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            IBS.init(SCCGWA, TDCF515.PAGE_INF);
            TDCF515.PAGE_INF.TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = TDCF515.PAGE_INF.TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((TDCF515.PAGE_INF.TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                TDCF515.PAGE_INF.TOTAL_PAGE = WS_T_PAGE;
                if (WS_L_CNT != 0) {
                    WS_L_ROW = WS_P_ROW;
                }
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                TDCF515.PAGE_INF.TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (TDCSTZM.PAGE_NUM != 0) {
                if (TDCSTZM.PAGE_NUM >= TDCF515.PAGE_INF.TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    TDCF515.PAGE_INF.CURR_PAGE = TDCF515.PAGE_INF.TOTAL_PAGE;
                    TDCF515.PAGE_INF.LAST_PAGE = 'Y';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    TDCF515.PAGE_INF.CURR_PAGE = TDCSTZM.PAGE_NUM;
                    TDCF515.PAGE_INF.LAST_PAGE = 'N';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_P_ROW;
                }
            }
            if (TDCSTZM.PAGE_NUM == 0) {
                TDCF515.PAGE_INF.CURR_PAGE = 1;
                if (TDCF515.PAGE_INF.TOTAL_PAGE == 1) {
                    TDCF515.PAGE_INF.LAST_PAGE = 'Y';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_L_ROW;
                } else {
                    TDCF515.PAGE_INF.LAST_PAGE = 'N';
                    TDCF515.PAGE_INF.PAGE_ROW = WS_P_ROW;
                }
            }
            WS_P_NUM = TDCF515.PAGE_INF.CURR_PAGE - 1;
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            WS_END_NUM = TDCF515.PAGE_INF.CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.TOTAL_PAGE);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.PAGE_ROW);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCF515.PAGE_INF.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.KEY.CARD_NO = TDCSTZM.AC_NO;
            DCRACLNK.CARD_AC_STATUS = '1';
            T000_READ_DCTACLNK();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRIAACR);
            CEP.TRC(SCCGWA, "F-BUG4");
            CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
            DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
            T000_START_DCTIAACR();
            if (pgmRtn) return;
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
            while (WS_IAACR_FLG != 'N') {
                TDCSTZM.AC_NO = DCRIAACR.SUB_AC;
                IBS.init(SCCGWA, TDRCALL);
                TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                if (TDCSTZM.CALL_STS == ' ') {
                    TDRCALL.CALL_STS = ALL.charAt(0);
                } else {
                    TDRCALL.CALL_STS = TDCSTZM.CALL_STS;
                }
                T000_STARTBR_TDTCALL();
                if (pgmRtn) return;
                T000_READNEXT_TDTCALL();
                if (pgmRtn) return;
                while (WS_CALL_FLG != 'N') {
                    WS_NUM1 += 1;
                    if (WS_NUM1 > WS_STR_NUM 
                        && WS_NUM1 <= WS_END_NUM) {
                        WS_NUM2 += 1;
                        B400_10_10_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                    T000_READNEXT_TDTCALL();
                    if (pgmRtn) return;
                }
                T000_ENDBR_TDTCALL();
                if (pgmRtn) return;
                T000_READNEXT_DCTIAACR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTIAACR();
            if (pgmRtn) return;
        } else {
            TDCF515.PAGE_INF.TOTAL_NUM = 0;
            TDCF515.PAGE_INF.TOTAL_PAGE = 0;
            TDCF515.PAGE_INF.CURR_PAGE = 0;
            TDCF515.PAGE_INF.LAST_PAGE = 'Y';
            TDCF515.PAGE_INF.PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_B;
        SCCFMT.DATA_PTR = TDCF515;
        SCCFMT.DATA_LEN = 7422;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_10_10_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCF515.DATA[WS_NUM2-1]);
        TDCF515.DATA[WS_NUM2-1].AC_NO = TDCSTZM.AC_NO;
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        TDCF515.DATA[WS_NUM2-1].AC_NM = TDCSTZM.AC_NM;
        CEP.TRC(SCCGWA, TDCSTZM.AC_NM);
        if (TDCSTZM.AC_NM.trim().length() == 0) {
            WS_TMP_AC = TDCSTZM.AC_NO;
            CEP.TRC(SCCGWA, "TD AC NAME");
            R000_GET_CI_NAME();
            if (pgmRtn) return;
            TDCF515.DATA[WS_NUM2-1].AC_NM = CICACCU.DATA.CI_CNM;
        }
        TDCF515.DATA[WS_NUM2-1].BV_NO = TDRBVT.BV_NO;
        TDCF515.DATA[WS_NUM2-1].CALL_NO = TDRCALL.KEY.CALL_NO;
        TDCF515.DATA[WS_NUM2-1].APPO_AMT = TDRCALL.APPO_AMT;
        TDCF515.DATA[WS_NUM2-1].CALL_DATE = TDRCALL.CALL_DATE;
        TDCF515.DATA[WS_NUM2-1].APPO_DATE3 = TDRCALL.APPO_DATE;
        TDCF515.DATA[WS_NUM2-1].CAN_DATE = TDRCALL.CAN_DATE;
        TDCF515.DATA[WS_NUM2-1].CALL_STS = TDRCALL.CALL_STS;
        TDCF515.DATA[WS_NUM2-1].AUTO_FLG = TDRCALL.AUTO_FLG;
        TDCF515.DATA[WS_NUM2-1].AUTO_FLG = TDRCALL.AUTO_MTH;
        TDCF515.DATA[WS_NUM2-1].AUTO_AC = TDRCALL.AUTO_AC;
        TDCF515.DATA[WS_NUM2-1].AUTO_AC_SEQ = TDRCALL.AUTO_AC_SEQ;
        CEP.TRC(SCCGWA, TDRCALL.TEL_NO);
        TDCF515.DATA[WS_NUM2-1].TEL_NO = TDRCALL.TEL_NO;
        CEP.TRC(SCCGWA, TDRCALL.ID_TYP);
        TDCF515.DATA[WS_NUM2-1].ID_TYP = TDRCALL.ID_TYP;
        CEP.TRC(SCCGWA, TDRCALL.ID_NO);
        TDCF515.DATA[WS_NUM2-1].ID_NO = TDRCALL.ID_NO;
        TDCF515.DATA[WS_NUM2-1].OWNER_BK = TDRCALL.OWNER_BK;
        if (TDCSTZM.AC_SEQ != 0) {
            TDCF515.DATA[WS_NUM2-1].AC_SEQ = TDCSTZM.AC_SEQ;
        } else {
            if (TDRSMST.BV_TYP == '7') {
                TDCF515.DATA[WS_NUM2-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            }
        }
        CEP.TRC(SCCGWA, "SEQSEQ");
        CEP.TRC(SCCGWA, TDCSTZM.AC_NO);
        CEP.TRC(SCCGWA, TDCSTZM.AC_SEQ);
        if (TDCSTZM.BV_TYP == '4') {
            TDCF515.DATA[WS_NUM2-1].AC_SEQ = DCRIAACR.KEY.SEQ;
        }
        TDCF515.DATA[WS_NUM2-1].PROD_CD = TDRSMST.PROD_CD;
        TDCF515.DATA[WS_NUM2-1].TERM2 = TDRSMST.TERM;
        TDCF515.DATA[WS_NUM2-1].CCY = TDRSMST.CCY;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        TDCF515.DATA[WS_NUM2-1].PRIN_AMT = TDRSMST.BAL;
        CEP.TRC(SCCGWA, TDCF515.DATA[WS_NUM2-1].PRIN_AMT);
        TDCF515.DATA[WS_NUM2-1].REMARK = TDRCALL.REMARK;
        TDCF515.DATA[WS_NUM2-1].CHNL = TDRSMST.CHNL_NO;
        TDCF515.DATA[WS_NUM2-1].REQ_BR = TDRCALL.CALL_BR;
        if (TDRCALL.AUTO_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "GET AUTO-AC NAME");
            WS_TMP_AC = TDRCALL.AUTO_AC;
            R000_GET_CI_NAME();
            if (pgmRtn) return;
            TDCF515.DATA[WS_NUM2-1].AUTO_AC_NM = CICACCU.DATA.CI_CNM;
        } else {
            TDCF515.DATA[WS_NUM2-1].AUTO_AC_NM = " ";
            TDCF515.DATA[WS_NUM2-1].VAL_DATE = TDRSMST.VAL_DATE;
            TDCF515.DATA[WS_NUM2-1].CCY_TYP = TDRSMST.CCY_TYP;
        }
        CEP.TRC(SCCGWA, "LIST INT:");
    }
    public void B400_03_BRW_CHK_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCSTZM.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
    }
    public void B600_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = TDCSTZM.AC_NO;
        BPCPNHIS.INFO.AC = TDCSTZM.AC_NO;
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CI_NO = CI_NO_TD;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        if (TDCSTZM.FUNC == '0') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else if (TDCSTZM.FUNC == '1') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = TDCCALLO;
            BPCPNHIS.INFO.NEW_DAT_PT = TDCCALLN;
        } else if (TDCSTZM.FUNC == '3'
            || TDCSTZM.FUNC == '2'
            || TDCSTZM.FUNC == '6') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = TDCCALLO;
            BPCPNHIS.INFO.NEW_DAT_PT = TDCCALLN;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B700_ALL_DEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRCALL.CALL_STS = '0';
        T000_STARTBR_TDTCALL_01();
        if (pgmRtn) return;
        T000_READNEXT_TDTCALL();
        if (pgmRtn) return;
        if (WS_CALL_FLG == 'N') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (WS_CALL_FLG != 'N') {
            B710_DELETE_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NUM);
            T000_READNEXT_TDTCALL();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTCALL();
        if (pgmRtn) return;
    }
    public void B510_INQ_TDTCALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCALL);
        TDRCALL.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRCALL.KEY.CALL_NO = TDCSTZM.CALL_NO;
        T000_READ_TDTCALL();
        if (pgmRtn) return;
    }
    public void B520_FMT_INQ_NOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOINOT);
        TDCOINOT.AC_NO = TDCSTZM.AC_NO;
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        TDCOINOT.AC_NM = TDCSTZM.AC_NM;
        TDCOINOT.BV_NO = TDCSTZM.BV_NO;
        TDCOINOT.CALL_NO = TDRCALL.KEY.CALL_NO;
        TDCOINOT.APPO_AMT = TDRCALL.APPO_AMT;
        TDCOINOT.CALL_DATE = TDRCALL.CALL_DATE;
        TDCOINOT.APPO_DATE = TDRCALL.APPO_DATE;
        TDCOINOT.CAN_DATE = TDRCALL.CAN_DATE;
        TDCOINOT.CALL_STS = TDRCALL.CALL_STS;
        TDCOINOT.AUTO_FLG = TDRCALL.AUTO_FLG;
        TDCOINOT.AUTO_MTH = TDRCALL.AUTO_MTH;
        TDCOINOT.AUTO_AC = TDRCALL.AUTO_AC;
        TDCOINOT.AUTO_AC_SEQ = TDRCALL.AUTO_AC_SEQ;
        TDCOINOT.TEL_NO = TDRCALL.TEL_NO;
        TDCOINOT.ID_TYP = TDRCALL.ID_TYP;
        TDCOINOT.ID_NO = TDRCALL.ID_NO;
        TDCOINOT.OWNER_BK = TDRCALL.OWNER_BK;
        TDCOINOT.REMARK = TDRCALL.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_NOT_FMT;
        SCCFMT.DATA_PTR = TDCOINOT;
        SCCFMT.DATA_LEN = 611;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B410_TRANSFER_BRW_CALL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOINOT);
        TDCOINOT.AC_NO = TDCSTZM.AC_NO;
        CEP.TRC(SCCGWA, TDRCALL.KEY.CALL_NO);
        TDCOINOT.AC_NM = TDCSTZM.AC_NM;
        TDCOINOT.BV_NO = TDCSTZM.BV_NO;
        TDCOINOT.CALL_NO = TDRCALL.KEY.CALL_NO;
        TDCOINOT.APPO_AMT = TDRCALL.APPO_AMT;
        TDCOINOT.CALL_DATE = TDRCALL.CALL_DATE;
        TDCOINOT.APPO_DATE = TDRCALL.APPO_DATE;
        TDCOINOT.CAN_DATE = TDRCALL.CAN_DATE;
        TDCOINOT.CALL_STS = TDRCALL.CALL_STS;
        TDCOINOT.AUTO_FLG = TDRCALL.AUTO_FLG;
        TDCOINOT.AUTO_MTH = TDRCALL.AUTO_MTH;
        TDCOINOT.AUTO_AC = TDRCALL.AUTO_AC;
        TDCOINOT.AUTO_AC_SEQ = TDRCALL.AUTO_AC_SEQ;
        TDCOINOT.REMARK = TDRCALL.REMARK;
    }
    public void B420_OUTPUT_BRW_CALL_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCOINOT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCOINOT);
        SCCMPAG.DATA_LEN = 611;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B430_SET_SUB_TRAN() throws IOException,SQLException,Exception {
        SCCSUBS.AP_CODE = 12;
        SCCSUBS.TR_CODE = 5152;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void B710_DELETE_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OLD();
        if (pgmRtn) return;
        TDRCALL.CALL_STS = '2';
        TDRCALL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCALL.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCALL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTCALL();
        if (pgmRtn) return;
        R000_TRANS_DATA_NEW();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHK_APPO_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSTZM.FUNC);
        if (TDCSTZM.CHNL_FLG == 'Y' 
            && TDCSTZM.APPO_DATE != 0 
            && TDCSTZM.CALL_DATE == 0) {
            WS_APPO_DATE2 = TDCSTZM.APPO_DATE;
            if (WS_APPO_DATE2 < TDRSMST.VAL_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_APPO_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCSTZM.APPO_DATE;
            WS_TERMC = TDRSMST.TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERMC, REDEFINES13);
            SCCCLDT.DAYS = 0 - REDEFINES13.WS_TERM1;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            TDCSTZM.CALL_DATE = SCCCLDT.DATE2;
            if (SCCGWA.COMM_AREA.AC_DATE > TDCSTZM.CALL_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_APPO_LT_VAL_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (TDCSTZM.CALL_DATE == 0) {
                TDCSTZM.CALL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, TDCSTZM.CALL_DATE);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCSTZM.CALL_DATE;
            WS_TERMC = TDRSMST.TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERMC, REDEFINES13);
            SCCCLDT.DAYS = REDEFINES13.WS_TERM1;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_APPO_DATE = SCCCLDT.DATE2;
            WS_APPO_DATE2 = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, WS_APPO_DATE2);
        }
        WS_TERMC = TDRSMST.TERM;
        IBS.CPY2CLS(SCCGWA, WS_TERMC, REDEFINES13);
        WS_TERM = (short) (REDEFINES13.WS_TERM1 + 1);
        CEP.TRC(SCCGWA, "111111111");
        CEP.TRC(SCCGWA, WS_TERM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '1') {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
            BPCOCLWD.CAL_CODE = "CN";
            BPCOCLWD.DATE1 = WS_APPO_DATE2;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 1;
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
            CEP.TRC(SCCGWA, "222222222");
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        if (CICCUST.O_DATA.O_CI_TYP != '1' 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10202")) {
            WS_APPO_DATE2 = BPCOCLWD.DATE2;
        }
        CEP.TRC(SCCGWA, "333333333333333333");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, WS_APPO_DATE2);
        if (SCCGWA.COMM_AREA.AC_DATE > WS_APPO_DATE2) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_APPO_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCALLO);
        TDCCALLO.AC_SEQ = TDCSTZM.AC_SEQ;
        TDCCALLO.AC_NO = TDCSTZM.AC_NO;
        TDCCALLO.FUNC = TDCSTZM.FUNC;
        TDCCALLO.CALL_NO = TDCSTZM.CALL_NO;
        TDCCALLO.APPO_AMT = TDRCALL.APPO_AMT;
        TDCCALLO.APPO_DATE = TDRCALL.APPO_DATE;
        TDCCALLO.CALL_STS = TDRCALL.CALL_STS;
        TDCCALLO.AUT_TRF_TYP = TDRCALL.AUTO_FLG;
        TDCCALLO.AUT_MTH = TDRCALL.AUTO_MTH;
        TDCCALLO.AUT_AC = TDRCALL.AUTO_AC;
        TDCCALLO.AUT_AC_SEQ = TDRCALL.AUTO_AC_SEQ;
        TDCCALLO.REMARK = TDRCALL.REMARK;
    }
    public void R000_TRANS_DATA_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCALLN);
        TDCCALLO.AC_SEQ = TDCSTZM.AC_SEQ;
        TDCCALLO.AC_NO = TDCSTZM.AC_NO;
        TDCCALLO.FUNC = TDCSTZM.FUNC;
        TDCCALLO.CALL_NO = TDCSTZM.CALL_NO;
        TDCCALLO.APPO_AMT = TDCSTZM.APPO_AMT;
        TDCCALLO.APPO_DATE = WS_APPO_DATE2;
        TDCCALLO.CALL_STS = TDCSTZM.CALL_STS;
        TDCCALLO.AUT_TRF_TYP = TDCSTZM.AUT_TRF_TYP;
        TDCCALLO.AUT_MTH = TDCSTZM.AUT_MTH;
        TDCCALLO.AUT_AC = TDCSTZM.AUT_AC;
        TDCCALLO.AUT_AC_SEQ = TDCSTZM.AUT_AC_SEQ;
        TDCCALLO.REMARK = TDCSTZM.REMARK;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (TDCSTZM.AC_NM.trim().length() == 0 
            && TDCSTZM.BV_TYP == '4' 
            && TDRSMST.BV_TYP == '4') {
            WS_TMP_AC = TDCSTZM.AC_NO;
            R000_GET_CI_NAME();
            if (pgmRtn) return;
            if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                TDCSTZM.AC_NM = CICACCU.DATA.CI_CNM;
            } else {
                TDCSTZM.AC_NM = CICACCU.DATA.CI_ENM;
            }
        }
        IBS.init(SCCGWA, TDCOCALL);
        TDCOCALL.BV_CD = TDCSTZM.BV_CD;
        TDCOCALL.BV_TYP = TDCSTZM.BV_TYP;
        TDCOCALL.BV_NO = TDCSTZM.BV_NO;
        TDCOCALL.CARD_CVV = TDCSTZM.CARD_CVV;
        TDCOCALL.AC_SEQ = TDCSTZM.AC_SEQ;
        TDCOCALL.AC_NO = TDCSTZM.AC_NO;
        TDCOCALL.AC_NM = TDCSTZM.AC_NM;
        TDCOCALL.FUNC = TDCSTZM.FUNC;
        TDCOCALL.CALL_NO = TDRCALL.KEY.CALL_NO;
        TDCOCALL.APPO_AMT = TDRCALL.APPO_AMT;
        TDCOCALL.CALL_DATE = TDRCALL.CALL_DATE;
        TDCOCALL.APPO_DATE = TDRCALL.APPO_DATE;
        TDCOCALL.CAN_DATE = TDRCALL.CAN_DATE;
        TDCOCALL.CALL_STS = TDRCALL.CALL_STS;
        TDCOCALL.AUT_TRF_TYP = TDRCALL.AUTO_FLG;
        TDCOCALL.AUT_MTH = TDRCALL.AUTO_MTH;
        TDCOCALL.AUT_AC = TDRCALL.AUTO_AC;
        TDCOCALL.AUT_AC_SEQ = TDRCALL.AUTO_AC_SEQ;
        TDCOCALL.REMARK = TDCSTZM.REMARK;
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSTZM.PAGE_ROW);
        if (TDCSTZM.PAGE_ROW == 0) {
            WS_P_ROW = 8;
        } else {
            if (TDCSTZM.PAGE_ROW > 8) {
                WS_P_ROW = 8;
            } else {
                WS_P_ROW = TDCSTZM.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, TDCSTZM.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void R000_GET_CI_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_TMP_AC;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BUY_CCY = TDCPROD.PROD_DATA.OTH_PRM.REF_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_MIN_CCYC;
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCFX.SELL_AMT;
    }
    public void R000_CHK_PRD_CCY_DEF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMPD);
        TDRPMPD.KEY.IBS_AC_BK = K_AC_BK;
        TDRPMPD.KEY.PRD_CD = TDCPROD.PRD_CD;
        TDRPMPD.MIN_CCYC = TDRSMST.CCY;
        T000_GROP_TDTPMPD();
        if (pgmRtn) return;
    }
    public void T000_GROP_TDTPMPD() throws IOException,SQLException,Exception {
        WS_CCY_DEF_FLG = ' ';
        TDTPMPD_RD = new DBParm();
        TDTPMPD_RD.TableName = "TDTPMPD";
        TDTPMPD_RD.set = "WS-CCY-CNT=COUNT(*)";
        TDTPMPD_RD.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD "
            + "AND PRM_TYP = 'M' "
            + "AND MIN_CCYC = :TDRPMPD.MIN_CCYC";
        IBS.GROUP(SCCGWA, TDRPMPD, this, TDTPMPD_RD);
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        if (WS_CCY_CNT > 0) {
            WS_CCY_DEF_FLG = 'Y';
        } else {
            WS_CCY_DEF_FLG = 'N';
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC_MSG.WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC_MSG.WS_RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_RC_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY  ", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
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
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S001_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_SUB_NOT_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.where = "CARD_NO = :DCRACLNK.KEY.CARD_NO "
            + "AND CARD_AC_STATUS = :DCRACLNK.CARD_AC_STATUS";
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_SUB_NOT_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAACR_SEQ() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND SEQ = :DCRIAACR.KEY.SEQ";
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_SUB_NOT_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAACR_VCH_NO() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND VCH_NO = :DCRIAACR.VCH_NO";
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_SUB_NOT_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
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
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_TDTCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        IBS.WRITE(SCCGWA, TDRCALL, TDTCALL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_UPDATE_TDTCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        TDTCALL_RD.upd = true;
        IBS.READ(SCCGWA, TDRCALL, TDTCALL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        IBS.REWRITE(SCCGWA, TDRCALL, TDTCALL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_UPDATE_TA_ERR);
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_TDTCALL() throws IOException,SQLException,Exception {
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        IBS.READ(SCCGWA, TDRCALL, TDTCALL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CALLNO_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCALL_NOTICE() throws IOException,SQLException,Exception {
        WS_CALL_FLG = 'N';
        TDTCALL_RD = new DBParm();
        TDTCALL_RD.TableName = "TDTCALL";
        TDTCALL_RD.where = "ACO_AC = :TDRCALL.KEY.ACO_AC "
            + "AND CALL_STS = :TDRCALL.CALL_STS "
            + "AND APPO_DATE = :TDRCALL.APPO_DATE";
        IBS.READ(SCCGWA, TDRCALL, this, TDTCALL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            || SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            WS_CALL_FLG = 'Y';
        } else {
            WS_CALL_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTCALL() throws IOException,SQLException,Exception {
        WS_CALL_FLG = 'N';
        TDTCALL_BR.rp = new DBParm();
        TDTCALL_BR.rp.TableName = "TDTCALL";
        TDTCALL_BR.rp.where = "ACO_AC = :TDRCALL.KEY.ACO_AC "
            + "AND CALL_STS LIKE :TDRCALL.CALL_STS";
        IBS.STARTBR(SCCGWA, TDRCALL, this, TDTCALL_BR);
    }
    public void T000_STARTBR_TDTCALL_01() throws IOException,SQLException,Exception {
        WS_CALL_FLG = 'N';
        TDTCALL_BR.rp = new DBParm();
        TDTCALL_BR.rp.TableName = "TDTCALL";
        TDTCALL_BR.rp.where = "ACO_AC = :TDRCALL.KEY.ACO_AC "
            + "AND CALL_STS = :TDRCALL.CALL_STS";
        TDTCALL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRCALL, this, TDTCALL_BR);
    }
    public void T000_READNEXT_TDTCALL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRCALL, this, TDTCALL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CALL_FLG = 'Y';
        } else {
            WS_CALL_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTCALL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTCALL_BR);
    }
    public void T000_START_DCTIAACR() throws IOException,SQLException,Exception {
        WS_IAACR_FLG = 'N';
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND FRM_APP = 'TD'";
        DCTIAACR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IAACR_FLG = 'Y';
        } else {
            WS_IAACR_FLG = 'N';
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
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
