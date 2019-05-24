package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUPWCK {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTDCPSW_RD;
    SCCSTAR SCCSTAR = new SCCSTAR();
    String K_PGM_NAME = "DCZUPWCK";
    String CPN_PARM_MT = "DC-SIMPLE-PSW-CHECK";
    String WS_ERR_MSG = " ";
    DCZUPWCK_WS_WPSTR WS_WPSTR = new DCZUPWCK_WS_WPSTR();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCTSSC SCCTSSC = new SCCTSSC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    CICCUST CICCUST = new CICCUST();
    DCCF991 DCCF991 = new DCCF991();
    DCRDCPSW DCRDCPSW = new DCRDCPSW();
    SCCGWA SCCGWA;
    DCCS9991 DCCS9991;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCS9991 DCCS9991) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9991 = DCCS9991;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        C000_OUTPUT_PROCESS();
        CEP.TRC(SCCGWA, "DCZUPWCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9991.OLD_AGR_NO);
        CEP.TRC(SCCGWA, DCCS9991.NEW_AGR_NO);
        CEP.TRC(SCCGWA, DCCS9991.AGR_NO_6);
        CEP.TRC(SCCGWA, DCCS9991.ID_TYPE);
        CEP.TRC(SCCGWA, DCCS9991.ID_NO);
        CEP.TRC(SCCGWA, DCCS9991.CI_NM);
        CEP.TRC(SCCGWA, DCCS9991.PSWD_NO);
        if (DCCS9991.AGR_NO_6.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.ID_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.OLD_AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.NEW_AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9991.PSWD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        WS_WPSTR.WS_PSW1 = DCCS9991.AGR_NO_6;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'B';
        CICCUST.DATA.ID_TYPE = DCCS9991.ID_TYPE;
        CICCUST.DATA.ID_NO = DCCS9991.ID_NO;
        CICCUST.DATA.CI_NM = DCCS9991.CI_NM;
        S000_CALL_CIZCUST();
        if (CICCUST.O_DATA.O_BIRTH_DT != ' ') {
            JIBS_tmp_str[0] = "" + CICCUST.O_DATA.O_BIRTH_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_WPSTR.WS_PSW2 = JIBS_tmp_str[0].substring(0, 6);
            JIBS_tmp_str[0] = "" + CICCUST.O_DATA.O_BIRTH_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_WPSTR.WS_PSW3 = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
            JIBS_tmp_str[0] = "" + CICCUST.O_DATA.O_BIRTH_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_WPSTR.WS_PSW6 = JIBS_tmp_str[0].substring(2 - 1, 2 + 6 - 1);
        }
        if (CICCUST.O_DATA.O_TEL_NO.trim().length() > 0) {
            if (CICCUST.O_DATA.O_TEL_NO == null) CICCUST.O_DATA.O_TEL_NO = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_TEL_NO.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CICCUST.O_DATA.O_TEL_NO += " ";
            WS_WPSTR.WS_PSW4 = CICCUST.O_DATA.O_TEL_NO.substring(6 - 1, 6 + 6 - 1);
        }
        if (DCCS9991.ID_TYPE.equalsIgnoreCase("10100")) {
            if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
            JIBS_tmp_int = DCCS9991.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
            if (DCCS9991.ID_NO.substring(16 - 1, 16 + 1 - 1).trim().length() == 0) {
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW5 = DCCS9991.ID_NO.substring(10 - 1, 10 + 6 - 1);
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW2 = "19" + DCCS9991.ID_NO;
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW3 = DCCS9991.ID_NO.substring(7 - 1, 7 + 6 - 1);
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW6 = "9" + DCCS9991.ID_NO;
            } else {
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW5 = DCCS9991.ID_NO.substring(13 - 1, 13 + 6 - 1);
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW2 = DCCS9991.ID_NO.substring(7 - 1, 7 + 6 - 1);
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW3 = DCCS9991.ID_NO.substring(9 - 1, 9 + 6 - 1);
                if (DCCS9991.ID_NO == null) DCCS9991.ID_NO = "";
                JIBS_tmp_int = DCCS9991.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9991.ID_NO += " ";
                WS_WPSTR.WS_PSW6 = DCCS9991.ID_NO.substring(8 - 1, 8 + 6 - 1);
            }
        }
        if (WS_WPSTR.WS_PSW5 == null) WS_WPSTR.WS_PSW5 = "";
        JIBS_tmp_int = WS_WPSTR.WS_PSW5.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_WPSTR.WS_PSW5 += " ";
        if (WS_WPSTR.WS_PSW5.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("X")) {
            WS_WPSTR.WS_PSW5 = " ";
        }
        if (WS_WPSTR.WS_PSW2.trim().length() == 0) {
            WS_WPSTR.WS_PSW2 = WS_WPSTR.WS_PSW1;
        }
        if (WS_WPSTR.WS_PSW3.trim().length() == 0) {
            WS_WPSTR.WS_PSW3 = WS_WPSTR.WS_PSW1;
        }
        if (WS_WPSTR.WS_PSW6.trim().length() == 0) {
            WS_WPSTR.WS_PSW6 = WS_WPSTR.WS_PSW1;
        }
        if (WS_WPSTR.WS_PSW4.trim().length() == 0) {
            WS_WPSTR.WS_PSW4 = WS_WPSTR.WS_PSW1;
        }
        if (WS_WPSTR.WS_PSW5.trim().length() == 0) {
            WS_WPSTR.WS_PSW5 = WS_WPSTR.WS_PSW1;
        }
        if (WS_WPSTR.WS_PSW4 == null) WS_WPSTR.WS_PSW4 = "";
        JIBS_tmp_int = WS_WPSTR.WS_PSW4.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_WPSTR.WS_PSW4 += " ";
        if (WS_WPSTR.WS_PSW4.substring(6 - 1, 6 + 1 - 1).trim().length() == 0) {
            WS_WPSTR.WS_PSW4 = "000000";
        }
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW1);
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW2);
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW3);
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW4);
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW5);
        CEP.TRC(SCCGWA, WS_WPSTR.WS_PSW6);
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = 'A';
        CEP.TRC(SCCGWA, DCCS9991.OLD_AGR_NO);
        CEP.TRC(SCCGWA, DCCS9991.NEW_AGR_NO);
        if (DCCS9991.OLD_AGR_NO == null) DCCS9991.OLD_AGR_NO = "";
        JIBS_tmp_int = DCCS9991.OLD_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCS9991.OLD_AGR_NO += " ";
        if (DCCS9991.OLD_AGR_NO.substring(0, 2).equalsIgnoreCase("FT")) {
            SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCS9991.OLD_AGR_NO.substring(4-1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        } else {
            SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCS9991.OLD_AGR_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        if (DCCS9991.NEW_AGR_NO == null) DCCS9991.NEW_AGR_NO = "";
        JIBS_tmp_int = DCCS9991.NEW_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCS9991.NEW_AGR_NO += " ";
        if (DCCS9991.NEW_AGR_NO.substring(0, 2).equalsIgnoreCase("FT")) {
            SCCTSSC.COMM_AREA_A.A_DS_ACCNO = DCCS9991.NEW_AGR_NO.substring(4-1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        } else {
            SCCTSSC.COMM_AREA_A.A_DS_ACCNO = DCCS9991.NEW_AGR_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        if (DCCS9991.OLD_AGR_NO == null) DCCS9991.OLD_AGR_NO = "";
        JIBS_tmp_int = DCCS9991.OLD_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCS9991.OLD_AGR_NO += " ";
        if (DCCS9991.OLD_AGR_NO.substring(0, 3).equalsIgnoreCase("NRA")) {
            SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCS9991.OLD_AGR_NO.substring(4-1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        if (DCCS9991.NEW_AGR_NO == null) DCCS9991.NEW_AGR_NO = "";
        JIBS_tmp_int = DCCS9991.NEW_AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCS9991.NEW_AGR_NO += " ";
        if (DCCS9991.NEW_AGR_NO.substring(0, 3).equalsIgnoreCase("NRA")) {
            SCCTSSC.COMM_AREA_A.A_DS_ACCNO = DCCS9991.NEW_AGR_NO.substring(4-1);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_SRC_ACCNO);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_DS_ACCNO);
        SCCTSSC.COMM_AREA_A.A_SRC_PINBLOCK = DCCS9991.PSWD_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        if (SCCTSSC.COMM_AREA_A.A_TEL_NO.trim().length() == 0) {
            SCCTSSC.COMM_AREA_A.A_TEL_NO = "11111111111";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        if (DCCS9991.ID_TYPE.equalsIgnoreCase("10100") 
            || DCCS9991.ID_TYPE.equalsIgnoreCase("10200")) {
            SCCTSSC.COMM_AREA_A.A_ID_NO = DCCS9991.ID_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        } else {
            SCCTSSC.COMM_AREA_A.A_WPSTR = DCCS9991.ID_NO;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
            SCCTSSC.COMM_AREA_A.A_WPSTR2 = "" + CICCUST.O_DATA.O_BIRTH_DT;
            JIBS_tmp_int = SCCTSSC.COMM_AREA_A.A_WPSTR2.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_A.A_WPSTR2 = "0" + SCCTSSC.COMM_AREA_A.A_WPSTR2;
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        }
        CEP.TRC(SCCGWA, "PASSWORD001");
        S000_CALL_SCZTSSC();
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_LOWPIN_FLG);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_PINOFFSET);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET);
        if (SCCTSSC.COMM_AREA_A.A_O_LOWPIN_FLG == 'Y') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_SO_EASY);
        } else {
            IBS.init(SCCGWA, DCRDCPSW);
            DCRDCPSW.PSW_E = SCCTSSC.COMM_AREA_A.A_O_PINOFFSET;
            T000_READ_DCTDCPSW_FIRST();
        }
        DCCS9991.O_PINOFFSET = SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET;
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF991);
        DCCF991.LOWPIN_FLG = SCCTSSC.COMM_AREA_A.A_O_LOWPIN_FLG;
        CEP.TRC(SCCGWA, DCCF991);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DC991";
        SCCFMT.DATA_PTR = DCCF991;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTDCPSW_FIRST() throws IOException,SQLException,Exception {
        DCTDCPSW_RD = new DBParm();
        DCTDCPSW_RD.TableName = "DCTDCPSW";
        DCTDCPSW_RD.where = "PSW_E = :DCRDCPSW.PSW_E";
        DCTDCPSW_RD.fst = true;
        IBS.READ(SCCGWA, DCRDCPSW, this, DCTDCPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_SO_EASY);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCPSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        if (SCCTSSC.RC != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ERROR_FROM_ENP);
        }
        SCCTSSC.COMM_AREA_A.A_O_PINOFFSET = "888888";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET = "888888";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
    }
    public void S000_CALL_SCZTSSC_0115240() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        if (SCCTSSC.RC != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ERROR_FROM_ENP);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
