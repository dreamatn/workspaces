package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9008 {
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_FMT_P_INF = "DD815";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_REL_AC = " ";
    DDOT9008_WS_PERS_DATA WS_PERS_DATA = new DDOT9008_WS_PERS_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    CICQACRL CICQACRL = new CICQACRL();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRVCH DDRVCH = new DDRVCH();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    DDCOPOUT DDCOPOUT = new DDCOPOUT();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    DDB9008_AWA_9008 DDB9008_AWA_9008;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT9008 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9008_AWA_9008>");
        DDB9008_AWA_9008 = (DDB9008_AWA_9008) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, DDCOPOUT);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_GET_ACTYP_DATA();
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            B300_DDTMST_PROC();
            B302_GET_ACAC_INFO();
            B305_DDTCCY_PROC();
            R000_GET_C_AC_STS();
            B310_CIZACCU_PROC();
            B400_DDTVCH_PROC();
        }
        B500_FMT_OUTPUT();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9008_AWA_9008.FUNC);
        CEP.TRC(SCCGWA, DDB9008_AWA_9008.CARD_NO);
        CEP.TRC(SCCGWA, DDB9008_AWA_9008.AC_NO);
        if (DDB9008_AWA_9008.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_GET_ACTYP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDB9008_AWA_9008.AC_NO;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            WS_PERS_DATA.WS_P_CARD_NO = DDB9008_AWA_9008.AC_NO;
        }
        WS_PERS_DATA.WS_P_AC_NO = DDB9008_AWA_9008.AC_NO;
    }
    public void B300_DDTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDB9008_AWA_9008.AC_NO;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.CI_TYP != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        WS_PERS_DATA.WS_P_AC_TYPE = DDRMST.AC_TYPE;
        WS_PERS_DATA.WS_P_CI_TYPE = DDRMST.CI_TYP;
        WS_PERS_DATA.WS_P_AC_STS = DDRMST.AC_STS;
        WS_PERS_DATA.WS_P_OPEN_DATE = DDRMST.OPEN_DATE;
        WS_PERS_DATA.WS_P_EXP_DATE = DDRMST.EXP_DATE;
        WS_PERS_DATA.WS_P_PROD_CODE = DDRMST.PROD_CODE;
        WS_PERS_DATA.WS_P_OPEN_DP = DDRMST.OPEN_DP;
        WS_PERS_DATA.WS_P_CRS_DR_FLG = DDRMST.CROS_DR_FLG;
    }
    public void B302_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDB9008_AWA_9008.AC_NO;
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09")
            || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_REL_AC = DDB9008_AWA_9008.AC_NO;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
    }
    public void B305_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_C_AC_STS() throws IOException,SQLException,Exception {
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
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
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
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
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
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
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
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
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
        }
        if (WS_PERS_DATA.WS_P_AC_STA1 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA2 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA3 == ' ' 
            && WS_PERS_DATA.WS_P_AC_STA3 == WS_PERS_DATA.WS_P_AC_STA4) {
            WS_PERS_DATA.WS_P_AC_STA1 = 'N';
        }
        CEP.TRC(SCCGWA, WS_PERS_DATA.WS_P_AC_STS);
    }
    public void B310_CIZACCU_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDB9008_AWA_9008.AC_NO;
        S000_CALL_CIZACCU();
        if (DDRMST.CI_TYP == '1') {
            if (CICACCU.DATA.STSW == null) CICACCU.DATA.STSW = "";
            JIBS_tmp_int = CICACCU.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICACCU.DATA.STSW += " ";
            if (CICACCU.DATA.STSW.substring(0, 1).equalsIgnoreCase("3")) {
                WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.CI_CNM;
            } else {
                if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                    WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.CI_CNM;
                } else {
                    WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.AC_CNM;
                }
            }
        } else {
            WS_PERS_DATA.WS_P_AC_CNM = CICACCU.DATA.AC_CNM;
        }
    }
    public void B400_DDTVCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDB9008_AWA_9008.AC_NO;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.fst = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_PERS_DATA.WS_P_PAY_MTH = DDRVCH.PAY_TYPE;
        WS_PERS_DATA.WS_P_PSBK_NO = DDRVCH.PSBK_NO;
        WS_PERS_DATA.WS_P_BV_NO = DDRVCH.PSBK_NO;
        WS_PERS_DATA.WS_P_PSBK_STS = DDRVCH.PSBK_STS;
    }
    public void B500_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_PERS_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCOPOUT);
        SCCFMT.FMTID = K_FMT_P_INF;
        SCCFMT.DATA_PTR = WS_PERS_DATA;
        SCCFMT.DATA_LEN = 382;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
