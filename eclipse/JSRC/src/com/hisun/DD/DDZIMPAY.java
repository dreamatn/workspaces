package com.hisun.DD;

import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWRMSG;
import com.hisun.DP.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMPAY {
    int JIBS_tmp_int;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTVCH_RD;
    DBParm DDTMST_RD;
    DBParm DDTLOSS_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_LOS_NO = " ";
    int WS_EFF_DT = 0;
    short WS_DAYS = 0;
    double WS_AMT = 0;
    double WS_PB_AMT = 0;
    char WS_RCCY_END = ' ';
    short WS_I = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDRVCH DDRVCH = new DDRVCH();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDCPWRT DDCPWRT = new DDCPWRT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCEX BPCEX = new BPCEX();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDRLOSS DDRLOSS = new DDRLOSS();
    SCCHMPW SCCHMPW = new SCCHMPW();
    CICACCU CICACCU = new CICACCU();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCIMPAY DDCIMPAY;
    public void MP(SCCGWA SCCGWA, DDCIMPAY DDCIMPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMPAY = DDCIMPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMPAY.PSWD_NEW);
        CEP.TRC(SCCGWA, DDCIMPAY.PSWD_OLD);
        CEP.TRC(SCCGWA, DDCIMPAY.CARD_NO);
        R000_CHK_INPUT_DATA();
        if (pgmRtn) return;
        T000_READ_UPD_DDTVCH();
        if (pgmRtn) return;
        if (DDCIMPAY.PSWD_NEW.trim().length() > 0) {
            IBS.init(SCCGWA, DCCUPSWM);
            DCCUPSWM.FUNC = 'R';
            S000_GET_ACNO_LAST_6();
            if (pgmRtn) return;
            R000_CALL_DC_CHECK_PWD();
            if (pgmRtn) return;
            DDCIMPAY.PSWD_NEW = DCCUPSWM.O_NEW_PSW;
            CEP.TRC(SCCGWA, DDCIMPAY.PSWD_NEW);
            DDRVCH.PSWD_FLG = '1';
        }
        if (DDCIMPAY.PSWD_OLD.trim().length() > 0) {
            IBS.init(SCCGWA, DCCUPSWM);
            if (DDCIMPAY.FUNC == 'P') {
                DCCUPSWM.FUNC = 'M';
            } else {
                DCCUPSWM.FUNC = 'C';
            }
            CEP.TRC(SCCGWA, DDRVCH.PSWD_FLG);
            if (DDRVCH.PSWD_FLG == '0') {
                DCCUPSWM.PSW_FLG = 'O';
            } else {
                DCCUPSWM.PSW_FLG = 'N';
            }
            R000_CALL_DC_CHECK_PWD();
            if (pgmRtn) return;
            if (DDRVCH.PSWD_FLG == '0') {
                DDCIMPAY.PSWD_OLD = DCCUPSWM.O_OLD_PSW;
            } else {
                DDCIMPAY.PSWD_OLD = DCCUPSWM.O_NEW_PSW;
            }
            CEP.TRC(SCCGWA, DDCIMPAY.PSWD_OLD);
        }
        if (DDCIMPAY.FUNC == 'M') {
            B010_MOD_MTH_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'P') {
            CEP.TRC(SCCGWA, "MOD PSW");
            R000_GET_PSW_PARM();
            if (pgmRtn) return;
            B020_MOD_PSW_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'R') {
            CEP.TRC(SCCGWA, "RST PSW");
            R000_GET_PSW_PARM();
            if (pgmRtn) return;
            B030_RST_PSW_PROC();
            if (pgmRtn) return;
            B160_UPD_DDTMST_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'U') {
            CEP.TRC(SCCGWA, "UNLOCK PSW");
            B070_UNLOCK_PSW_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'I') {
            B040_MOD_ID_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'S') {
            B050_MOD_SIGN_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'C') {
            R000_GET_PSW_PARM();
            if (pgmRtn) return;
            B060_CHK_MTH_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'A') {
            CEP.TRC(SCCGWA, "SET PSW");
            B080_SET_PSW_PROC();
            if (pgmRtn) return;
        } else if (DDCIMPAY.FUNC == 'K') {
            CEP.TRC(SCCGWA, "CHK PSW");
            R000_GET_PSW_PARM();
            if (pgmRtn) return;
            B090_CHK_PSW_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIMPAY.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_MOD_MTH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCIMPAY.PAY_MTH);
        DDRVCH.PAY_TYPE = DDCIMPAY.PAY_MTH;
        if (DDCIMPAY.PSWD_NEW.trim().length() > 0) {
            DDRVCH.PAY_PSWD = DDCIMPAY.PSWD_NEW;
        }
        if (DDCIMPAY.ID_TYPE.trim().length() > 0) {
            DDRVCH.PAY_IDTYPE = DDCIMPAY.ID_TYPE;
        }
        if (DDCIMPAY.ID_NO.trim().length() > 0) {
            DDRVCH.PAY_IDNO = DDCIMPAY.ID_NO;
        }
        if (DDCIMPAY.SIGN_NO.trim().length() > 0) {
            DDRVCH.PAY_SIGN_NO = DDCIMPAY.SIGN_NO;
        }
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDRVCH.PAY_PSWD);
        CEP.TRC(SCCGWA, DDRVCH.PAY_IDTYPE);
        CEP.TRC(SCCGWA, DDRVCH.PAY_IDNO);
    }
    public void B020_MOD_PSW_PROC() throws IOException,SQLException,Exception {
        R000_CHK_PASSWORD_PROC();
        if (pgmRtn) return;
        DDRVCH.PAY_PSWD = DDCIMPAY.PSWD_NEW;
        DDRVCH.PSWD_FLG = '1';
        DDRVCH.PSWD_ERR_NUM = 0;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B030_RST_PSW_PROC() throws IOException,SQLException,Exception {
        if (DDRVCH.PAY_TYPE != DDCIMPAY.PAY_MTH) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_ID_TYPE();
        if (pgmRtn) return;
        R000_GET_PB_AMT();
        if (pgmRtn) return;
        if (DDCIMPAY.PSWD_NEW.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_PSWD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDRVCH.PAY_PSWD = DDCIMPAY.PSWD_NEW;
        DDRVCH.PSWD_FLG = '1';
        DDRVCH.PWD_LOST_DATE = 0;
        DDRVCH.PWD_LOST_NO = " ";
        DDRVCH.PSWD_ERR_NUM = 0;
        DDRVCH.PSWD_ERR_DATE = 0;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B031_PSW_RESET_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCPORUP.DATA_INFO.CALD_CD;
        BPCOCLWD.DAYE_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCOCLWD.DATE1 = DDRVCH.PWD_LOST_DATE;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        BPCOCLWD.DAYS = DDCPWRT.DATA_TXT.DAYS;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AC_DATE <= BPCOCLWD.DATE2) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_RST_DT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B035_UPDATE_DDTLOSS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMPAY.LOS_NO);
        B036_GET_DDTLOSS();
        if (pgmRtn) return;
        B037_UPD_DDTLOSS_PROC();
        if (pgmRtn) return;
    }
    public void B036_GET_DDTLOSS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRLOSS);
        DDRLOSS.KEY.LOS_NO = DDCIMPAY.LOS_NO;
        T000_READ_UPDATE_DDTLOSS();
        if (pgmRtn) return;
    }
    public void B037_UPD_DDTLOSS_PROC() throws IOException,SQLException,Exception {
        DDRLOSS.STS = '7';
        DDRLOSS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRLOSS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_DDTLOSS();
        if (pgmRtn) return;
    }
    public void B040_MOD_ID_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCIMPAY.PAY_MTH);
        if (DDRVCH.PAY_TYPE != DDCIMPAY.PAY_MTH) {
            CEP.TRC(SCCGWA, "4444");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRVCH.PAY_IDTYPE);
        CEP.TRC(SCCGWA, DDCIMPAY.ID_TYPE);
        CEP.TRC(SCCGWA, DDRVCH.PAY_IDNO);
        CEP.TRC(SCCGWA, DDCIMPAY.ID_NO);
        if (DDRVCH.PAY_IDTYPE.equalsIgnoreCase(DDCIMPAY.ID_TYPE) 
            && DDRVCH.PAY_IDNO.equalsIgnoreCase(DDCIMPAY.ID_NO)) {
            CEP.TRC(SCCGWA, "5555");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDRVCH.PAY_IDTYPE = DDCIMPAY.ID_TYPE;
        DDRVCH.PAY_IDNO = DDCIMPAY.ID_NO;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B050_MOD_SIGN_PROC() throws IOException,SQLException,Exception {
        if (DDRVCH.PAY_TYPE != DDCIMPAY.PAY_MTH) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PAY_SIGN_NO.equalsIgnoreCase(DDCIMPAY.SIGN_NO)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDRVCH.PAY_SIGN_NO = DDCIMPAY.SIGN_NO;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B060_CHK_MTH_PROC() throws IOException,SQLException,Exception {
        DDCIMPAY.PAY_MTH = DDRVCH.PAY_TYPE;
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCIMPAY.PAY_MTH);
        CEP.TRC(SCCGWA, DDRVCH.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCIMPAY.PSWD_OLD);
        if (DDCIMPAY.PAY_MTH == '1' 
            || DDCIMPAY.PAY_MTH == '4') {
            R000_CHK_PASSWORD_PROC();
            if (pgmRtn) return;
        }
        if (DDCIMPAY.PAY_MTH == '3') {
            CEP.TRC(SCCGWA, DDCIMPAY.ID_TYPE);
            CEP.TRC(SCCGWA, DDRVCH.PAY_IDTYPE);
            CEP.TRC(SCCGWA, DDCIMPAY.ID_NO);
            CEP.TRC(SCCGWA, DDRVCH.PAY_IDNO);
            if (!DDCIMPAY.ID_TYPE.equalsIgnoreCase(DDRVCH.PAY_IDTYPE)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYPE_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DDCIMPAY.ID_NO.equalsIgnoreCase(DDRVCH.PAY_IDNO)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_UNLOCK_PSW_PROC() throws IOException,SQLException,Exception {
        if (!DDCIMPAY.PSWD_OLD.equalsIgnoreCase(DDRVCH.PAY_PSWD)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_PSW_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B075_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DDRVCH.PSWD_ERR_NUM = 0;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 6 - 1) + "0" + DDRMST.AC_STS_WORD.substring(6 + 1 - 1);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSWD_UNLOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_UPDATE_DDTMST();
        if (pgmRtn) return;
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B075_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMPAY.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B080_SET_PSW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.PAY_PSWD);
        if (DDRVCH.PAY_PSWD.trim().length() > 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSW_EXIST_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDRVCH.PAY_PSWD = DDCIMPAY.PSWD_NEW;
        DDRVCH.PSWD_FLG = '1';
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
    }
    public void B090_CHK_PSW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVCH.PAY_TYPE);
        if (DDRVCH.PAY_TYPE != '1' 
            && DDRVCH.PAY_TYPE != '4') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_PSW_MTH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_CHK_PASSWORD_PROC();
        if (pgmRtn) return;
    }
    public void B160_UPD_DDTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCIMPAY.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 6 - 1) + "0" + DDRMST.AC_STS_WORD.substring(6 + 1 - 1);
        }
        T000_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void R000_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIMPAY.AC.trim().length() == 0 
            && DDCIMPAY.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_PSW_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPWRT);
        DDCPWRT.KEY.TYP = "PDD05";
        DDCPWRT.KEY.CD = "PSW";
        BPCPRMR.DAT_PTR = DDCPWRT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPWRT.DATA_TXT.AMT);
        CEP.TRC(SCCGWA, DDCPWRT.DATA_TXT.DAYS);
        CEP.TRC(SCCGWA, DDCPWRT.DATA_TXT.ERR_TIMES);
    }
    public void R000_CHK_PASSWORD_PROC() throws IOException,SQLException,Exception {
        if (DDCIMPAY.PSWD_OLD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_PSW_M_INPUT);
        }
        if (DDRVCH.PSWD_ERR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            DDRVCH.PSWD_ERR_NUM = 0;
            DDRVCH.PSWD_ERR_DATE = 0;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
            B160_UPD_DDTMST_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDRVCH.KEY.CUS_AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSWD_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRVCH.PSWD_ERR_NUM);
        if (DDCIMPAY.PSWD_OLD.equalsIgnoreCase(DDRVCH.PAY_PSWD)) {
            CEP.TRC(SCCGWA, "CHK PSW");
            T000_READ_UPD_DDTVCH();
            if (pgmRtn) return;
            if (DDRVCH.PSWD_ERR_NUM != 0 
                && DDCIMPAY.FUNC != 'P') {
                DDRVCH.PSWD_ERR_NUM = 0;
                if (DDRVCH.PSWD_FLG == '0') {
                    DDRVCH.PAY_PSWD = DCCUPSWM.O_NEW_PSW;
                    DDRVCH.PSWD_FLG = '1';
                }
                T000_REWRITE_DDTVCH();
                if (pgmRtn) return;
            }
            if (DDRVCH.PSWD_FLG == '0' 
                && DDCIMPAY.FUNC != 'P') {
                DDRVCH.PAY_PSWD = DCCUPSWM.O_NEW_PSW;
                DDRVCH.PSWD_FLG = '1';
                T000_REWRITE_DDTVCH();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "SSSSSSSSSSS");
            T000_ROLLBACK_PROC();
            if (pgmRtn) return;
            T000_READ_UPD_DDTVCH();
            if (pgmRtn) return;
            DDRVCH.PSWD_ERR_NUM = (short) (DDRVCH.PSWD_ERR_NUM + 1);
            DDRVCH.PSWD_ERR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_DDTVCH();
            if (pgmRtn) return;
            T000_SQL_COMMIT();
            if (pgmRtn) return;
            if (DDRVCH.PSWD_ERR_NUM >= DDCPWRT.DATA_TXT.ERR_TIMES) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = DDRVCH.KEY.CUS_AC;
                T000_READ_UPDATE_DDTMST();
                if (pgmRtn) return;
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 6 - 1) + "1" + DDRMST.AC_STS_WORD.substring(6 + 1 - 1);
                T000_UPDATE_DDTMST();
                if (pgmRtn) return;
                T000_SQL_COMMIT();
                if (pgmRtn) return;
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSWD_LOCK;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "TTTTTTTTTTTTTTTTTTT");
            if (DDCIMPAY.FUNC == 'P') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_PSW_NOT_MATCH;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_PSW_NOT_MATCH;
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_ID_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCIMPAY.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
    }
    public void R000_GET_PB_AMT() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_RCCY_END != 'E') {
            if (DDRCCY.CURR_BAL != 0) {
                if (!DDRCCY.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
                    IBS.init(SCCGWA, BPCEX);
                    BPCEX.BUY_CCY = DDRCCY.CCY;
                    BPCEX.BUY_AMT = DDRCCY.CURR_BAL;
                    BPCEX.SELL_CCY = BPCRBANK.LOC_CCY1;
                    S000_CALL_BPZSEX();
                    if (pgmRtn) return;
                    WS_AMT = BPCEX.SELL_AMT;
                } else {
                    WS_AMT = DDRCCY.CURR_BAL;
                }
            }
            WS_PB_AMT += WS_AMT;
            CEP.TRC(SCCGWA, WS_PB_AMT);
            WS_AMT = 0;
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PB_AMT);
    }
    public void R000_CALL_DC_CHECK_PWD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMPAY.AC);
        CEP.TRC(SCCGWA, DDCIMPAY.CARD_NO);
        if (DDCIMPAY.AC.trim().length() > 0) {
            DCCUPSWM.OLD_AGR_NO = DDCIMPAY.AC;
            DCCUPSWM.AGR_NO = DDCIMPAY.AC;
        } else {
            DCCUPSWM.OLD_AGR_NO = DDCIMPAY.CARD_NO;
            DCCUPSWM.AGR_NO = DDCIMPAY.CARD_NO;
        }
        if (DDCIMPAY.NEW_CARD_NO.trim().length() > 0) {
            DCCUPSWM.OLD_AGR_NO = DDCIMPAY.NEW_CARD_NO;
        }
        DCCUPSWM.ID_TYP = DDCIMPAY.ID_TYPE;
        DCCUPSWM.ID_NO = DDCIMPAY.ID_NO;
        DCCUPSWM.CI_NM = DDCIMPAY.AC_CNAME;
        DCCUPSWM.CARD_PSW_OLD = DDCIMPAY.PSWD_OLD;
        DCCUPSWM.CARD_PSW_NEW = DDCIMPAY.PSWD_NEW;
        CEP.TRC(SCCGWA, DDCIMPAY.FUNC);
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
    }
    public void S000_GET_ACNO_LAST_6() throws IOException,SQLException,Exception {
        WS_I = 1;
        if (DDCIMPAY.AC == null) DDCIMPAY.AC = "";
        JIBS_tmp_int = DDCIMPAY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCIMPAY.AC += " ";
        while (DDCIMPAY.AC.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0 
            && WS_I <= 32) {
            WS_I += 1;
        }
        CEP.TRC(SCCGWA, WS_I);
        if (DDCIMPAY.AC == null) DDCIMPAY.AC = "";
        JIBS_tmp_int = DDCIMPAY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCIMPAY.AC += " ";
        DCCUPSWM.AGR_NO_6 = DDCIMPAY.AC.substring(( WS_I - 6 ) - 1, ( WS_I - 6 ) + 6 - 1);
        CEP.TRC(SCCGWA, DCCUPSWM.AGR_NO_6);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCIMPAY.AC;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "AC = :DDRCCY.CUS_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RCCY_END = 'E';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIMPAY.AC;
        CEP.TRC(SCCGWA, DDCIMPAY.AC);
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        DDRVCH.VCH_TYPE = '1';
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCIMPAY.AC;
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.M_SET_PSW_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
        CEP.TRC(SCCGWA, DCCUPSWM.RC.RC_CODE);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTVCH() throws IOException,SQLException,Exception {
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDRVCH.PSWD_ERR_NUM);
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.REWRITE(SCCGWA, DDRVCH, DDTVCH_RD);
        CEP.TRC(SCCGWA, DDRVCH.PSWD_ERR_NUM);
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS_WORD, LAST_TLR,LAST_DATE,UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_STS_WORD";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_UPDATE_DDTLOSS() throws IOException,SQLException,Exception {
        DDTLOSS_RD = new DBParm();
        DDTLOSS_RD.TableName = "DDTLOSS";
        DDTLOSS_RD.upd = true;
        IBS.READ(SCCGWA, DDRLOSS, DDTLOSS_RD);
    }
    public void T000_UPDATE_DDTLOSS() throws IOException,SQLException,Exception {
        DDTLOSS_RD = new DBParm();
        DDTLOSS_RD.TableName = "DDTLOSS";
        IBS.REWRITE(SCCGWA, DDRLOSS, DDTLOSS_RD);
    }
    public void T000_SQL_COMMIT() throws IOException,SQLException,Exception {
        IBS.COMMIT(SCCGWA);
    }
    public void T000_ROLLBACK_PROC() throws IOException,SQLException,Exception {
        IBS.ROLLBACK(SCCGWA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMPAY.FUNC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
