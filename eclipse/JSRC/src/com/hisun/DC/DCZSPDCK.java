package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPDCK {
    int JIBS_tmp_int;
    DBParm DCTDCPSW_RD;
    SCCSTAR SCCSTAR = new SCCSTAR();
    String WS_ERR_MSG = " ";
    DCZSPDCK_WS_WPSTR WS_WPSTR = new DCZSPDCK_WS_WPSTR();
    char WS_EASY_PSW_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCTSSC SCCTSSC = new SCCTSSC();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    CICCUST CICCUST = new CICCUST();
    DCCF992 DCCF992 = new DCCF992();
    DCRDCPSW DCRDCPSW = new DCRDCPSW();
    SCCGWA SCCGWA;
    DCCS9992 DCCS9992;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCS9992 DCCS9992) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9992 = DCCS9992;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        C000_OUTPUT_PROCESS();
        CEP.TRC(SCCGWA, "DCZSPDCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9992.AGR_NO);
        CEP.TRC(SCCGWA, DCCS9992.ID_TYPE);
        CEP.TRC(SCCGWA, DCCS9992.ID_NO);
        CEP.TRC(SCCGWA, DCCS9992.BIR_DATE);
        CEP.TRC(SCCGWA, DCCS9992.PSWD_NO);
        CEP.TRC(SCCGWA, DCCS9992.TEL_NO);
        if (DCCS9992.ID_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9992.ID_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9992.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9992.PSWD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9992.TEL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERROR);
        }
        if (DCCS9992.AGR_NO == null) DCCS9992.AGR_NO = "";
        JIBS_tmp_int = DCCS9992.AGR_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCS9992.AGR_NO += " ";
        WS_WPSTR.WS_PSW1 = DCCS9992.AGR_NO.substring(14 - 1, 14 + 6 - 1);
        if (WS_WPSTR.WS_PSW1.trim().length() == 0) {
            WS_WPSTR.WS_PSW1 = "000000";
        }
        if (DCCS9992.TEL_NO == null) DCCS9992.TEL_NO = "";
        JIBS_tmp_int = DCCS9992.TEL_NO.length();
        for (int i=0;i<18-JIBS_tmp_int;i++) DCCS9992.TEL_NO += " ";
        WS_WPSTR.WS_PSW4 = DCCS9992.TEL_NO.substring(6 - 1, 6 + 6 - 1);
        if (DCCS9992.ID_TYPE.equalsIgnoreCase("10100")) {
            if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
            JIBS_tmp_int = DCCS9992.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
            if (DCCS9992.ID_NO.substring(16 - 1, 16 + 1 - 1).trim().length() == 0) {
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW5 = DCCS9992.ID_NO.substring(10 - 1, 10 + 6 - 1);
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW2 = "19" + DCCS9992.ID_NO;
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW3 = DCCS9992.ID_NO.substring(7 - 1, 7 + 6 - 1);
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW6 = "9" + DCCS9992.ID_NO;
            } else {
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW5 = DCCS9992.ID_NO.substring(13 - 1, 13 + 6 - 1);
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW2 = DCCS9992.ID_NO.substring(7 - 1, 7 + 6 - 1);
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW3 = DCCS9992.ID_NO.substring(9 - 1, 9 + 6 - 1);
                if (DCCS9992.ID_NO == null) DCCS9992.ID_NO = "";
                JIBS_tmp_int = DCCS9992.ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) DCCS9992.ID_NO += " ";
                WS_WPSTR.WS_PSW6 = DCCS9992.ID_NO.substring(8 - 1, 8 + 6 - 1);
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
        SCCTSSC.COMM_AREA_A.A_SRC_ACCNO = DCCS9992.AGR_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_DS_ACCNO = DCCS9992.AGR_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_SRC_PINBLOCK = DCCS9992.PSWD_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_WPNUM = 6;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_WPLEN = 6;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        SCCTSSC.COMM_AREA_A.A_WPSTR = IBS.CLS2CPY(SCCGWA, WS_WPSTR);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_A);
        S000_CALL_SCZTSSC();
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_LOWPIN_FLG);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_PINOFFSET);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_A.A_O_HOSTPINOFFSET);
        if (SCCTSSC.COMM_AREA_A.A_O_LOWPIN_FLG == 'Y') {
            WS_EASY_PSW_FLG = 'Y';
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_SO_EASY);
        } else {
            IBS.init(SCCGWA, DCRDCPSW);
            DCRDCPSW.PSW_E = SCCTSSC.COMM_AREA_A.A_O_PINOFFSET;
            T000_READ_DCTDCPSW_FIRST();
        }
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF992);
        DCCF992.LOWPIN_FLG = WS_EASY_PSW_FLG;
        CEP.TRC(SCCGWA, DCCF992);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DC992";
        SCCFMT.DATA_PTR = DCCF992;
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
            WS_EASY_PSW_FLG = 'Y';
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
