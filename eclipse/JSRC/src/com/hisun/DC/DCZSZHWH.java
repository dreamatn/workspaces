package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.EA.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSZHWH {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZUPSWC = "DC-U-PSW-CHK";
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "DC901";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_CARD_SWAL_STSW = ' ';
    char WS_I_CARD_STSW = ' ';
    DCZSZHWH_WS_OUTPUT WS_OUTPUT = new DCZSZHWH_WS_OUTPUT();
    char WS_ACNO_TYPE = ' ';
    char WS_CARD_MEDI = ' ';
    String WS_PROD_CD = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDDAT DCRCDCAT = new DCRCDDAT();
    DDRCCY DDRCCY = new DDRCCY();
    EACSBIND EACSBIND = new EACSBIND();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCSZHWH DCCNZHWH = new DCCSZHWH();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICACCU CICACCU = new CICACCU();
    CICSACAC CICSACAC = new CICSACAC();
    CICSFAC CICSFAC = new CICSFAC();
    EACSBINQ EACSBINQ = new EACSBINQ();
    CICREGLC CICREGLC = new CICREGLC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSZHWH DCCSZHWH;
    public void MP(SCCGWA SCCGWA, DCCSZHWH DCCSZHWH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSZHWH = DCCSZHWH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSZHWH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCSZHWH.FUNC == 'A') {
            B020_UP_ACII_PROC();
            if (pgmRtn) return;
        } else if (DCCSZHWH.FUNC == 'D') {
            B030_DOWN_ACI_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSZHWH.FUNC);
        CEP.TRC(SCCGWA, DCCSZHWH.CARD_NO);
        CEP.TRC(SCCGWA, DCCSZHWH.AC_TYP);
        CEP.TRC(SCCGWA, DCCSZHWH.AC_NO);
        if (DCCSZHWH.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHWH.FUNC != 'A' 
            && DCCSZHWH.FUNC != 'D') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHWH.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHWH.FUNC == 'D' 
            && DCCSZHWH.AC_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DCCSZHWH.CARD_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSZHWH.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDDAT.ACNO_TYPE);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_MEDI);
        CEP.TRC(SCCGWA, DCCSZHWH.FUNC);
        WS_ACNO_TYPE = DCRCDDAT.ACNO_TYPE;
        WS_CARD_MEDI = DCRCDDAT.CARD_MEDI;
        WS_PROD_CD = DCRCDDAT.PROD_CD;
        if (DCCSZHWH.FUNC == 'D' 
            && DCCSZHWH.AC_TYP <= WS_ACNO_TYPE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DOWN_AC_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACNO_TYPE == '2' 
            && WS_CARD_MEDI != '4' 
            && DCCSZHWH.FUNC == 'D') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_II_MEDI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACNO_TYPE == '3' 
            && WS_CARD_MEDI != '4' 
            && DCCSZHWH.FUNC == 'A') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_III_MEDI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHWH.FUNC == 'A' 
            && WS_ACNO_TYPE == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_I_CANOT_UP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSZHWH.FUNC == 'D' 
            && WS_ACNO_TYPE == '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_III_CANOT_DOWN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACNO_TYPE == '1' 
            && DCCSZHWH.AC_TYP == '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_I_CANOT_DOWN_III;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DCCSZHWH.CARD_NO;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
    }
    public void B020_UP_ACII_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSZHWH.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((DCCUCINF.CARD_MEDI == '4' 
            || DCCUCINF.CARD_MEDI == ' ') 
            && WS_ACNO_TYPE == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MEDI_IS_NULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACNO_TYPE == '2') {
            B020_CI_ACI_CHECK();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSZHWH.AC_NO);
        if (DCCSZHWH.AC_NO.trim().length() > 0) {
            B020_BANG_ACI_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("1")) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 18 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(18 + 1 - 1);
        }
        if (WS_ACNO_TYPE == '2') {
            DCRCDDAT.ACNO_TYPE = '1';
        } else {
            DCRCDDAT.ACNO_TYPE = '2';
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_ACNO_TYPE == '2') {
            DDRCCY.AC_TYPE = '2';
        } else {
            if (DDRCCY.AC_TYPE == '5') {
                DDRCCY.AC_TYPE = '4';
            } else {
                DDRCCY.AC_TYPE = 'C';
            }
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = DDRCCY.KEY.AC;
        if (WS_ACNO_TYPE == '2') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        } else {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "2" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B020_CI_ACI_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSFAC);
        CICSFAC.DATA.CI_NO = CICACCU.DATA.CI_NO;
        S000_CALL_CIZSFAC();
        if (pgmRtn) return;
        if (CICSFAC.DATA.FIRST_NUM != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACI_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BANG_ACI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACSBIND);
        if (DCCSZHWH.FUNC == 'A') {
            EACSBIND.FUNC = 'C';
        }
        if (DCCSZHWH.FUNC == 'D') {
            EACSBIND.FUNC = 'B';
        }
        EACSBIND.CARD_NO = DCCSZHWH.CARD_NO;
        EACSBIND.IO_FLG = 'I';
        EACSBIND.CON_AC = DCCSZHWH.AC_NO;
        EACSBIND.AC_NM = CICACCU.DATA.AC_CNM;
        EACSBIND.ID_NO = CICACCU.DATA.ID_NO;
        EACSBIND.ID_TYPE = CICACCU.DATA.ID_TYPE;
        EACSBIND.PSW = DCCSZHWH.PSW;
        S000_CALL_EAZSBIND();
        if (pgmRtn) return;
    }
    public void B030_DOWN_ACI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSZHWH.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_CITZEN_CRD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_PROD_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_YXK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.ADSC_TYPE == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_COMP_CARD_NOT_BAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACNO_TYPE == '1') {
            IBS.init(SCCGWA, DCRCDCAT);
            IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDCAT);
            DCRCDDAT.PRIM_CARD_NO = DCCSZHWH.CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_I_CARD_STSW == 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_I_CARD_NOT_DOWN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRCDDAT);
            IBS.CLONE(SCCGWA, DCRCDCAT, DCRCDDAT);
        }
        CEP.TRC(SCCGWA, WS_ACNO_TYPE);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, CICREGLC);
        CICREGLC.FUNC = '2';
        if (WS_ACNO_TYPE == '1') {
            CICREGLC.AC_TYP = '2';
        } else {
            if (WS_ACNO_TYPE == '2') {
                CICREGLC.AC_TYP = '3';
            }
        }
        CICREGLC.PROD_CD = WS_PROD_CD;
        CICREGLC.CCY = "156";
        CICREGLC.DC_FLG = 'C';
        CICREGLC.CUS_AC = DCCSZHWH.CARD_NO;
        S000_CALL_CIZREGLC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICREGLC.BAL_LMT_AMT);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        if (DDRCCY.CURR_BAL > CICREGLC.BAL_LMT_AMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CURR_BAL_MORE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EACSBINQ);
        EACSBINQ.I_AC = DCCSZHWH.CARD_NO;
        S000_CALL_EAZSBINQ();
        if (pgmRtn) return;
        if (EACSBINQ.END_SEQ > 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EALINK_EXIST);
        }
        if (WS_ACNO_TYPE == '1') {
            DCRCDDAT.ACNO_TYPE = '2';
        } else {
            DCRCDDAT.ACNO_TYPE = '3';
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_ACNO_TYPE == '1') {
            DDRCCY.AC_TYPE = 'A';
        } else {
            if (DDRCCY.AC_TYPE == '4') {
                DDRCCY.AC_TYPE = '5';
            } else {
                DDRCCY.AC_TYPE = 'B';
            }
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = DDRCCY.KEY.AC;
        if (WS_ACNO_TYPE == '1') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "2" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        } else {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "3" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSZHWH.AC_NO);
        if (DCCSZHWH.AC_NO.trim().length() > 0) {
            B020_BANG_ACI_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_FUNC = DCCSZHWH.FUNC;
        WS_OUTPUT.WS_CARD_NO = DCCSZHWH.CARD_NO;
        WS_OUTPUT.WS_AC_TYP = DCCSZHWH.AC_TYP;
        WS_OUTPUT.WS_AC_NO = DCCSZHWH.AC_NO;
        WS_OUTPUT.WS_ID_NO = CICACCU.DATA.ID_NO;
        WS_OUTPUT.WS_ID_TYPE = CICACCU.DATA.ID_TYPE;
        WS_OUTPUT.WS_AC_NAME = CICACCU.DATA.AC_CNM;
        WS_OUTPUT.WS_CI_NAME = CICACCU.DATA.CI_CNM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 635;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCNZHWH);
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "UPGRATE AC II,DOWN AC I";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSZHWH";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSZHWH.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.AC = DCCSZHWH.CARD_NO;
        BPCPNHIS.INFO.CCY = DDRCCY.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        BPCPNHIS.INFO.FMT_ID_LEN = 85;
        if (DCCSZHWH.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP_CD = "PE06";
        }
        if (DCCSZHWH.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP_CD = "PE04";
        }
        BPCPNHIS.INFO.NEW_DAT_PT = DCCSZHWH;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_EAZSBINQ() throws IOException,SQLException,Exception {
        EAZSBINQ EAZSBINQ = new EAZSBINQ();
        EAZSBINQ.MP(SCCGWA, EACSBINQ);
    }
    public void S000_CALL_CIZSFAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-FIRST-AC", CICSFAC);
        if (CICSFAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFAC.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EAZSBIND() throws IOException,SQLException,Exception {
        EAZSBIND EAZSBIND = new EAZSBIND();
        EAZSBIND.MP(SCCGWA, EACSBIND);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZREGLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-REG-LC-CTL", CICREGLC);
        if (CICREGLC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICREGLC.RC);
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO";
        DCTCDDAT_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_I_CARD_STSW = 'Y';
        }
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = '156' "
            + "AND CCY_TYPE = '1'";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
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
