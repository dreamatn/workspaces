package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSTBL {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_LN_TABLE = 0;
    String WS_LN_CODE = " ";
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CICCUST CICCUST = new CICCUST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    LNCSSTBL LNCSSTBL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSSTBL LNCSSTBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSTBL = LNCSSTBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSTBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCFCSTS);
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNRICTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_LN_TABLE);
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSSTBL.S_CODE.trim().length() > 0) {
            B300_GET_CIMM_STSW();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSSTBL.CON_NO);
        if ((LNCSSTBL.CON_NO.trim().length() > 0 
            || LNCSSTBL.CMM_NO.trim().length() > 0) 
            && LNCSSTBL.TR_CODE.trim().length() > 0) {
            if (LNCSSTBL.TR_CODE.trim().length() == 0) WS_LN_TABLE = 0;
            else WS_LN_TABLE = Short.parseShort(LNCSSTBL.TR_CODE);
            B400_GET_LNNO_STSW();
            if (pgmRtn) return;
        }
    }
    public void B300_GET_CIMM_STSW() throws IOException,SQLException,Exception {
        if (LNCSSTBL.S_CODE.trim().length() == 0) {
            if ("1111".trim().length() == 0) WS_LN_TABLE = 0;
            else WS_LN_TABLE = Short.parseShort("1111");
        } else {
            if (LNCSSTBL.S_CODE.trim().length() == 0) WS_LN_TABLE = 0;
            else WS_LN_TABLE = Short.parseShort(LNCSSTBL.S_CODE);
        }
        IBS.init(SCCGWA, BPCFCSTS);
        R000_GET_CUST_STSW();
        if (pgmRtn) return;
        R000_GET_MMST_STSW();
        if (pgmRtn) return;
        R000_GET_ACTY_STSW();
        if (pgmRtn) return;
        R000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B400_GET_LNNO_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (LNCSSTBL.CON_NO.trim().length() > 0) {
            LNRCONT.KEY.CONTRACT_NO = LNCSSTBL.CON_NO;
            T000_READ_LNTCONT();
            if (pgmRtn) return;
            if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
                LNRICTL.KEY.CONTRACT_NO = LNCSSTBL.CON_NO;
                T000_READ_LNTICTL();
                if (pgmRtn) return;
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 100));
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                BPCFCSTS.STATUS_WORD = LNRICTL.CTL_STSW + BPCFCSTS.STATUS_WORD.substring(100);
            }
        }
        R000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void R000_GET_CUST_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, LNCSSTBL.CI_NO);
        if (LNCSSTBL.CI_NO.trim().length() > 0) {
            CICCUST.DATA.CI_NO = LNCSSTBL.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
            JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
            BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW + BPCFCSTS.STATUS_WORD.substring(80);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 80));
        }
    }
    public void R000_GET_MMST_STSW() throws IOException,SQLException,Exception {
        if (LNCSSTBL.DRAW_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSSTBL.DRAW_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDCIMMST);
                if (LNCSSTBL.DRAW_AC.trim().length() > 0) {
                    DDCIMMST.DATA.KEY.AC_NO = LNCSSTBL.DRAW_AC;
                    DDCIMMST.TX_TYPE = 'I';
                    S000_CALL_DDZIMMST();
                    if (pgmRtn) return;
                    if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                    JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                    for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                    if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                    JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                    BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 81 - 1) + DDCIMMST.DATA.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(81 + 180 - 1);
                    CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD);
                    if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                    JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                    for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                    CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(81 - 1, 81 + 100 - 1));
                }
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = LNCSSTBL.DRAW_AC;
                S000_INQ_CARD();
                if (pgmRtn) return;
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 81 - 1) + DCCUCINF.CARD_STSW + BPCFCSTS.STATUS_WORD.substring(81 + 180 - 1);
            }
        }
    }
    public void R000_GET_ACTY_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        if (LNCSSTBL.DRAW_AC.trim().length() > 0) {
            DDCIQBAL.DATA.AC = LNCSSTBL.DRAW_AC;
            DDCIQBAL.DATA.CCY = LNCSSTBL.CCY;
            DDCIQBAL.DATA.CCY_TYPE = LNCSSTBL.CCY_TYP;
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        CEP.TRC(SCCGWA, WS_LN_TABLE);
        BPCFCSTS.AP_MMO = "LN";
        BPCFCSTS.TBL_NO = "" + WS_LN_TABLE;
        JIBS_tmp_int = BPCFCSTS.TBL_NO.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCFCSTS.TBL_NO = "0" + BPCFCSTS.TBL_NO;
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 250));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 100));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(37 - 1, 37 + 1 - 1));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(4 - 1, 4 + 1 - 1));
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, "6666666666666");
        CEP.TRC(SCCGWA, LNRCONT.KEY.IBS_HASH);
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.CONT_NFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCSSTBL.RC);
            Z_RET();
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
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_INQ_CARD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCSTS);
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC.RC_CODE);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSSTBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCSSTBL=");
            CEP.TRC(SCCGWA, LNCSSTBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
