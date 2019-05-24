package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMARK {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD650";
    String K_HIS_COPYBOOK_NAME = "DDCSMARK";
    String K_HIS_REMARKS = "AC SLEEP MARK";
    String WS_ERR_MSG = " ";
    char WS_MST_AC_STS = ' ';
    DDZSMARK_WS_OUT_DATA WS_OUT_DATA = new DDZSMARK_WS_OUT_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDCRMST DDCRMST = new DDCRMST();
    SCCBINF SCCBINF = new SCCBINF();
    DDRCCY DDRCCY = new DDRCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    DDCSMARK DDCSMARK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSMARK DDCSMARK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMARK = DDCSMARK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMARK return!");
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
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B025_CHECK_CI_STS_PROC();
        if (pgmRtn) return;
        B030_READUP_DDTCCY_PROC();
        if (pgmRtn) return;
        B040_UPT_CCY_INF_PROC();
        if (pgmRtn) return;
        B090_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMARK.AC);
        CEP.TRC(SCCGWA, DDCSMARK.CCY);
        CEP.TRC(SCCGWA, DDCSMARK.CCY_TYPE);
        R000_CHECK_DD_MUST();
        if (pgmRtn) return;
        if (DDCSMARK.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMARK.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSMARK.CCY.equalsIgnoreCase("156") 
            && DDCSMARK.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSMARK.CCY.equalsIgnoreCase("156") 
            && (DDCSMARK.CCY_TYPE != '1' 
            && DDCSMARK.CCY_TYPE != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DD_MUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = DDCSMARK.AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_DD_AC);
        }
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSMARK.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSMARK.CCY;
        CICQACAC.DATA.CR_FLG = DDCSMARK.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            || CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_DD_AC);
        }
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSMARK.AC;
            DDCRMST.FUNC = 'R';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
            WS_MST_AC_STS = DDRMST.AC_STS;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DDCSMARK.AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
            if (DCCUCINF.CARD_STS != 'N') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
            }
        }
    }
    public void B025_CHECK_CI_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMARK.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_IS_CLOSE_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_READUP_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "AC,STS,STS_WORD";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_BDH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_UPT_CCY_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCSMARK.OPT_FLG == '0') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(60 - 1, 60 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ALREADY_SLEEP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAA");
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 60 - 1) + "1" + DDRCCY.STS_WORD.substring(60 + 1 - 1);
                DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                S000_REWRITE_DDTCCY();
                if (pgmRtn) return;
                B080_NFIN_TX_HIS_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSMARK.OPT_FLG == '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (!DDRCCY.STS_WORD.substring(60 - 1, 60 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NON_SLEEP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "BBBBBBBBBBBBBB");
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 60 - 1) + "0" + DDRCCY.STS_WORD.substring(60 + 1 - 1);
                DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                S000_REWRITE_DDTCCY();
                if (pgmRtn) return;
                B080_NFIN_TX_HIS_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_BR = DDCSMARK.BR;
        WS_OUT_DATA.WS_AC = DDCSMARK.AC;
        WS_OUT_DATA.WS_AC_NM = DDCSMARK.AC_NM;
        WS_OUT_DATA.WS_CCY = DDCSMARK.CCY;
        WS_OUT_DATA.WS_CCY_TYPE = DDCSMARK.CCY_TYPE;
        WS_OUT_DATA.WS_AUTO_FLG = DDCSMARK.AUTO_FLG;
        WS_OUT_DATA.WS_OPT_FLG = DDCSMARK.OPT_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 297;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
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
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSMARK.AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (DDCSMARK.OPT_FLG == '0') {
            BPCPNHIS.INFO.TX_TYP_CD = "P410";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P411";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
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
