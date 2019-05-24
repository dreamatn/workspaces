package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUDREG {
    int JIBS_tmp_int;
    brParm DDTCCY_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTDREG_RD;
    boolean pgmRtn = false;
    char K_REQ_STS_NORMAL = 'N';
    char K_REQ_STS_DELETE = 'D';
    String K_HIS_CPB_NAME = "DDCUDREG";
    String K_HIS_REMARKS = "DDTDREG RECORD MAINTAIN";
    String K_HIS_MMO = "GA9";
    String WS_ERR_MSG = " ";
    double WS_DEP_INT = 0;
    double WS_INT_TAX = 0;
    double WS_TX_AMT = 0;
    char WS_DREG_FLG = ' ';
    String WS_ACAC = " ";
    char WS_REC_CHG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRDREG DDRDREG = new DDRDREG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCUPINT DDCUPINT = new DDCUPINT();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    DDCUDREG DDCUDREG;
    public void MP(SCCGWA SCCGWA, DDCUDREG DDCUDREG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUDREG = DDCUDREG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B001_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUDREG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B001_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_DREG_FLG = DDCUDREG.DATA.DREG_FLG;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_AC_INF_PROC();
        if (pgmRtn) return;
        if (WS_DREG_FLG == '1') {
            B300_CHECK_CCY_PROC();
            if (pgmRtn) return;
            B040_CRT_DREG_INF_PROC();
            if (pgmRtn) return;
            B050_UPATE_MST_CCY_PROC();
            if (pgmRtn) return;
            B600_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
            R000_SEND_MESSAGE();
            if (pgmRtn) return;
        }
        if (WS_DREG_FLG == '2') {
            R000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            R000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
                    CEP.TRC(SCCGWA, DDRCCY.CCY);
                    CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
                    CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
                    B030_CHECK_CCY_PROC();
                    if (pgmRtn) return;
                    B050_UPDATE_CCY_PROC();
                    if (pgmRtn) return;
                }
                R000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTCCY();
            if (pgmRtn) return;
            B070_UPDATE_DDTMST();
            if (pgmRtn) return;
            B400_CRT_DREG_INF_PROC();
            if (pgmRtn) return;
            B600_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
        if (WS_DREG_FLG == '3') {
            B300_CHECK_CCY_PROC();
            if (pgmRtn) return;
            B400_CRT_DREG_INF_PROC();
            if (pgmRtn) return;
            B500_UPDATE_MST_CCY_PROC();
            if (pgmRtn) return;
            B600_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3') {
            CEP.TRC(SCCGWA, "--COMPANY AC PROC--");
            R000_STARTBR_DDTCCY();
            if (pgmRtn) return;
            R000_READNEXT_DDTCCY();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
                    CEP.TRC(SCCGWA, DDRCCY.CCY);
                    CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
                    CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
                    B030_CHECK_CCY_PROC();
                    if (pgmRtn) return;
                    B040_CRT_DREG_INF_PROC();
                    if (pgmRtn) return;
                    B050_UPDATE_CCY_PROC();
                    if (pgmRtn) return;
                    B060_NON_FIN_HIS_PROC();
                    if (pgmRtn) return;
                }
                R000_READNEXT_DDTCCY();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTCCY();
            if (pgmRtn) return;
            B070_UPDATE_DDTMST();
            if (pgmRtn) return;
        }
        if (CICACCU.DATA.CI_TYP == '1') {
            CEP.TRC(SCCGWA, "--PERSON AC PROC--");
            B300_CHECK_CCY_PROC();
            if (pgmRtn) return;
            B400_CRT_DREG_INF_PROC();
            if (pgmRtn) return;
            B500_UPDATE_MST_CCY_PROC();
            if (pgmRtn) return;
            B600_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            if (DDCMSG_ERROR_MSG.DD_CCY_CLEARED.trim().length() == 0) DDCUDREG.RC.RC_CODE = 0;
            else DDCUDREG.RC.RC_CODE = Short.parseShort(DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
            Z_RET();
            if (pgmRtn) return;
        }
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
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_HAS_HLD_REC);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (CICACCU.DATA.CI_TYP == '1' 
            && DDRCCY.STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALREADY_SLEEP);
        }
        WS_TX_AMT = DDRCCY.CURR_BAL;
    }
    public void B400_CRT_DREG_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = WS_ACAC;
        T000_READUP_DDTDREG();
        if (pgmRtn) return;
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        DDRDREG.KEY.AC = WS_ACAC;
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            DDRDREG.STS = '9';
        }
        if (WS_DREG_FLG == '1') {
            DDRDREG.STS = '1';
        }
        DDRDREG.FLG = 'B';
        DDRDREG.OPN_DT = DDRCCY.OPEN_DATE;
        DDRDREG.BR = DDRCCY.OWNER_BRDP;
        DDRDREG.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDREG.OWNER_BRDP = DDRCCY.OWNER_BRDP;
        if (WS_DREG_FLG == '1') {
            DDRDREG.W_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRDREG.W_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDREG.W_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            DDRDREG.D_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRDREG.D_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDREG.D_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        DDRDREG.NTF_FLG = 'Y';
        DDRDREG.NTF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.NTF_NUM = 0;
        DDRDREG.RCD_STS = K_REQ_STS_NORMAL;
        DDRDREG.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDREG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            T000_WRITE_DDTDREG();
            if (pgmRtn) return;
        } else {
            T000_UPDATE_DDTDREG();
            if (pgmRtn) return;
        }
    }
    public void B600_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.AC = DDCUDREG.DATA.AC_NO;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            BPCPNHIS.INFO.TX_TYP_CD = "P404";
        }
        if (WS_DREG_FLG == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P401";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B500_UPDATE_MST_CCY_PROC() throws IOException,SQLException,Exception {
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "1" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 5 - 1) + "1" + DDRMST.AC_STS_WORD.substring(5 + 1 - 1);
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.CUS_AC = DDCUDREG.DATA.AC_NO;
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void R000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void R000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void B020_CHECK_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUDREG.DATA.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCUDREG.DATA.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCUDREG.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCUDREG.DATA.CCY_TYPE;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUDREG.DATA.AC_NO;
        T000_READUP_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (DDRMST.AC_STS == 'W' 
            || DDRMST.AC_STS == 'D') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_NO_INVALID);
        }
    }
    public void B030_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            if (DDCMSG_ERROR_MSG.DD_CCY_CLEARED.trim().length() == 0) DDCUDREG.RC.RC_CODE = 0;
            else DDCUDREG.RC.RC_CODE = Short.parseShort(DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
            Z_RET();
            if (pgmRtn) return;
        }
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
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_HAS_HLD_REC);
        }
    }
    public void B040_CRT_DREG_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = WS_ACAC;
        T000_READUP_DDTDREG();
        if (pgmRtn) return;
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        DDRDREG.KEY.AC = WS_ACAC;
        DDRDREG.KEY.APP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            DDRDREG.STS = '9';
        }
        if (WS_DREG_FLG == '1') {
            DDRDREG.STS = '1';
        }
        DDRDREG.FLG = 'B';
        DDRDREG.OPN_DT = DDRCCY.OPEN_DATE;
        DDRDREG.BR = DDRCCY.OWNER_BRDP;
        DDRDREG.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDREG.OWNER_BRDP = DDRCCY.OWNER_BRDP;
        if (WS_DREG_FLG == '1') {
            DDRDREG.W_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRDREG.W_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDREG.W_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            DDRDREG.D_DT = SCCGWA.COMM_AREA.AC_DATE;
            DDRDREG.D_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRDREG.D_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        DDRDREG.NTF_FLG = 'Y';
        DDRDREG.NTF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.NTF_NUM = 0;
        DDRDREG.RCD_STS = K_REQ_STS_NORMAL;
        DDRDREG.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDREG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            T000_WRITE_DDTDREG();
            if (pgmRtn) return;
        } else {
            T000_UPDATE_DDTDREG();
            if (pgmRtn) return;
        }
    }
    public void B040_INQ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCUDREG.DATA.AC_NO;
        DDCUPINT.CCY = DDCUDREG.DATA.CCY;
        DDCUPINT.CCY_TYPE = DDCUDREG.DATA.CCY_TYPE;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.UOD_INT);
        WS_DEP_INT = DDCUPINT.DEP_INT;
        WS_INT_TAX = DDCUPINT.INT_TAX;
    }
    public void B040_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCUDREG.DATA.AC_NO;
        DDCUPINT.CCY = DDCUDREG.DATA.CCY;
        DDCUPINT.CCY_TYPE = DDCUDREG.DATA.CCY_TYPE;
        DDCUPINT.TX_MMO = K_HIS_MMO;
        DDCUPINT.REMARK = "POST ACCOUNT INT";
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
    }
    public void B050_UPATE_MST_CCY_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3') {
            DDRMST.AC_STS = 'W';
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
        if (CICACCU.DATA.CI_TYP == '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "1" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B070_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 5 - 1) + "1" + DDRMST.AC_STS_WORD.substring(5 + 1 - 1);
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void B050_UPDATE_CCY_PROC() throws IOException,SQLException,Exception {
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "1" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDREG.DATA.AC_NO);
        CEP.TRC(SCCGWA, DDCUDREG.DATA.CCY);
        CEP.TRC(SCCGWA, DDCUDREG.DATA.CCY_TYPE);
        if (DDCUDREG.DATA.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.AC = DDCUDREG.DATA.AC_NO;
        BPCPNHIS.INFO.CCY = DDRCCY.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        if (WS_DREG_FLG == '2' 
            || WS_DREG_FLG == '3') {
            BPCPNHIS.INFO.TX_TYP_CD = "P404";
        }
        if (WS_DREG_FLG == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P401";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_SEND_MESSAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, DDCUDREG.DATA.AC_NO);
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.SEQ = 1;
        SCCWRMSG.AP_CODE = "29";
        SCCWRMSG.CI_NO = CICACCU.DATA.CI_NO;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCWRMSG.AC = DDCUDREG.DATA.AC_NO;
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READUP_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.eqWhere = "AC";
        DDTDREG_RD.upd = true;
        IBS.READ(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void T000_WRITE_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRDREG, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DREG_REC_EXIST, DDCUDREG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        IBS.REWRITE(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUDREG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUDREG=");
            CEP.TRC(SCCGWA, DDCUDREG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
