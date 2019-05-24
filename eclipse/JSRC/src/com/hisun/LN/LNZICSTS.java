package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICSTS {
    int JIBS_tmp_int;
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    LNCICSTS LNCICSTS;
    public void MP(SCCGWA SCCGWA, LNCICSTS LNCICSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICSTS = LNCICSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICSTS.RC.RC_APP = "LN";
        LNCICSTS.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CONTRACT_NO);
        if (LNCICSTS.COMM_DATA.CONTRACT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT, LNCICSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCICSTS.COMM_DATA.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        LNCICSTS.COMM_DATA.CTL_STSW = LNRICTL.CTL_STSW;
        if (LNCICSTS.COMM_DATA.CTL_STSW.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ICTL_NFND, LNCICSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(0, 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'A';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'P';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'M';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'D';
            }
        } else {
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'A';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'P';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'N';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'X';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'W';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'M';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'C';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'D';
            }
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
            if (LNCICSTS.COMM_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICSTS.COMM_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                LNCICSTS.COMM_DATA.STS = 'T';
            }
        }
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.STS);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(6 - 1, 6 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(33 - 1, 33 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(35 - 1, 35 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1));
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CTL_STSW.substring(15 - 1, 15 + 1 - 1));
        LNCICSTS.COMM_DATA.STS_WORD = "0000000000000000";
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.CTL_STSW.substring(6 - 1, 6 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 2 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(33 - 1, 33 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(2 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 3 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(35 - 1, 35 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(3 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 4 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(4 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 5 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(5 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 6 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(6 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 7 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(7 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 8 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(8 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 9 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(14 - 1, 14 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(9 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 10 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(51 - 1, 51 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(10 + 1 - 1);
        if (LNCICSTS.COMM_DATA.CTL_STSW == null) LNCICSTS.COMM_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.CTL_STSW += " ";
        if (LNCICSTS.COMM_DATA.STS_WORD == null) LNCICSTS.COMM_DATA.STS_WORD = "";
        JIBS_tmp_int = LNCICSTS.COMM_DATA.STS_WORD.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) LNCICSTS.COMM_DATA.STS_WORD += " ";
        LNCICSTS.COMM_DATA.STS_WORD = LNCICSTS.COMM_DATA.STS_WORD.substring(0, 11 - 1) + LNCICSTS.COMM_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1) + LNCICSTS.COMM_DATA.STS_WORD.substring(11 + 1 - 1);
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.STS_WORD);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICSTS.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCICSTS=");
            CEP.TRC(SCCGWA, LNCICSTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
