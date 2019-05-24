package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5133 {
    String K_HIS_REMARKS = "DEL SETL";
    String K_PROD_CD = "DEL SETL";
    String K_HIS_CPB_NM1 = "DEL SETL";
    String K_HIS_RMKS = "DEL SETL";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    char WS_STL_TYP = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCSREMI LNCSREMI = new LNCSREMI();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    CICSSTC CICSSTC = new CICSSTC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCSSETL LNCSSETL = new LNCSSETL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    LNB5132_AWA_5132 LNB5132_AWA_5132;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5133 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5132_AWA_5132>");
        LNB5132_AWA_5132 = (LNB5132_AWA_5132) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (LNB5132_AWA_5132.AC_NO.trim().length() > 0 
            && LNB5132_AWA_5132.AC_FLG == '0') {
            B030_GET_AC_TYPE();
        }
        B020_TRANCHE_MAIN_PROC();
        B040_NFIANCE_HIS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_STL_TYP = LNB5132_AWA_5132.STL_TYP;
        CEP.TRC(SCCGWA, LNB5132_AWA_5132);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.CONT_TYP);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.CONT_NO);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.PART_BK);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.CI_TYPE);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.CCY);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.STL_TYP);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.MWHD_FLG);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_TYP);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_NO);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.REMARK);
        if (LNB5132_AWA_5132.AC_FLG == ' ') {
            LNB5132_AWA_5132.AC_FLG = '0';
        }
        if (LNB5132_AWA_5132.CONT_NO.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            if (LNB5132_AWA_5132.CONT_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB5132_AWA_5132.CONT_NO);
            S000_ERR_MSG_PROC();
        }
        if (LNB5132_AWA_5132.CONT_TYP == ' ') {
            if (LNB5132_AWA_5132.CONT_TYP == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+LNB5132_AWA_5132.CONT_TYP);
            S000_ERR_MSG_PROC();
        }
        if (LNB5132_AWA_5132.CCY.trim().length() == 0) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY;
            if (LNB5132_AWA_5132.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB5132_AWA_5132.CCY);
            S000_ERR_MSG_PROC();
        }
        if (LNB5132_AWA_5132.STL_TYP == ' ') {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_SET_TYP_I;
            if (LNB5132_AWA_5132.STL_TYP == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+LNB5132_AWA_5132.STL_TYP);
            S000_ERR_MSG_PROC();
        }
        if ((WS_STL_TYP != '1' 
            && WS_STL_TYP != '2' 
            && WS_STL_TYP != '3' 
            && WS_STL_TYP != '4' 
            && WS_STL_TYP != '5' 
            && WS_STL_TYP != '6' 
            && WS_STL_TYP != '7' 
            && WS_STL_TYP != '8' 
            && WS_STL_TYP != '9' 
            && WS_STL_TYP != 'A' 
            && WS_STL_TYP != 'B' 
            && WS_STL_TYP != 'C' 
            && WS_STL_TYP != 'D')) {
            WS_MSGID = LNCMSG_ERROR_MSG.LN_ERR_EXCEED;
            if (LNB5132_AWA_5132.STL_TYP == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+LNB5132_AWA_5132.STL_TYP);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_AC_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = LNB5132_AWA_5132.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        R_GET_AC_TYPE();
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSETL);
        LNCSSETL.CONTRACT_ATTR = LNB5132_AWA_5132.CONT_TYP;
        LNCSSETL.CONTRACT_NO = LNB5132_AWA_5132.CONT_NO;
        LNCSSETL.PART_BK = LNB5132_AWA_5132.PART_BK;
        LNCSSETL.CI_TYPE = LNB5132_AWA_5132.CI_TYPE;
        LNCSSETL.CCY = LNB5132_AWA_5132.CCY;
        LNCSSETL.SETTLE_TYPE = LNB5132_AWA_5132.STL_TYP;
        LNCSSETL.MWHD_AC_FLG = LNB5132_AWA_5132.MWHD_FLG;
        LNCSSETL.AC_TYP = LNB5132_AWA_5132.AC_TYP;
        LNCSSETL.AC = LNB5132_AWA_5132.AC_NO;
        LNCSSETL.REMARK = LNB5132_AWA_5132.REMARK;
        LNCSSETL.AC_DATA[1-1].STL_ACTYP = LNB5132_AWA_5132.AC_DATA[1-1].S_ACTYP;
        LNCSSETL.AC_DATA[1-1].STL_AC = LNB5132_AWA_5132.AC_DATA[1-1].S_AC;
        LNCSSETL.AC_DATA[1-1].STL_AC_FLG = LNB5132_AWA_5132.AC_DATA[1-1].S_AC_FLG;
        LNCSSETL.AC_DATA[1-1].STL_AC_NM = LNB5132_AWA_5132.AC_DATA[1-1].S_AC_NM;
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_DATA[1-1].S_AC);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_DATA[1-1].S_ACTYP);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_NO);
        CEP.TRC(SCCGWA, LNB5132_AWA_5132.AC_TYP);
        LNCSSETL.AC_DATA[2-1].STL_ACTYP = LNB5132_AWA_5132.AC_DATA[2-1].S_ACTYP;
        LNCSSETL.AC_DATA[2-1].STL_AC = LNB5132_AWA_5132.AC_DATA[2-1].S_AC;
        LNCSSETL.AC_DATA[2-1].STL_AC_FLG = LNB5132_AWA_5132.AC_DATA[2-1].S_AC_FLG;
        LNCSSETL.AC_DATA[2-1].STL_AC_NM = LNB5132_AWA_5132.AC_DATA[2-1].S_AC_NM;
        LNCSSETL.AC_DATA[3-1].STL_ACTYP = LNB5132_AWA_5132.AC_DATA[3-1].S_ACTYP;
        LNCSSETL.AC_DATA[3-1].STL_AC = LNB5132_AWA_5132.AC_DATA[3-1].S_AC;
        LNCSSETL.AC_DATA[3-1].STL_AC_FLG = LNB5132_AWA_5132.AC_DATA[3-1].S_AC_FLG;
        LNCSSETL.AC_DATA[3-1].STL_AC_NM = LNB5132_AWA_5132.AC_DATA[3-1].S_AC_NM;
        LNCSSETL.AC_DATA[4-1].STL_ACTYP = LNB5132_AWA_5132.AC_DATA[4-1].S_ACTYP;
        LNCSSETL.AC_DATA[4-1].STL_AC = LNB5132_AWA_5132.AC_DATA[4-1].S_AC;
        LNCSSETL.AC_DATA[4-1].STL_AC_FLG = LNB5132_AWA_5132.AC_DATA[4-1].S_AC_FLG;
        LNCSSETL.AC_DATA[4-1].STL_AC_NM = LNB5132_AWA_5132.AC_DATA[4-1].S_AC_NM;
        S000_CALL_LNZSSETL();
    }
    public void R_GET_AC_TYPE() throws IOException,SQLException,Exception {
        if (LNB5132_AWA_5132.AC_TYP.equalsIgnoreCase("01")) {
            if (!(DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD"))) {
                CEP.TRC(SCCGWA, "111111111111111111111111");
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB5132_AWA_5132.AC_TYP.equalsIgnoreCase("02")) {
            CEP.TRC(SCCGWA, "3333333333333333333333");
            if (DCCPACTY.OUTPUT.AC_TYPE != 'G') {
                CEP.TRC(SCCGWA, "222222222222222222222");
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB5132_AWA_5132.AC_TYP.equalsIgnoreCase("03")) {
            if (!(DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("IB"))) {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        if (LNB5132_AWA_5132.AC_TYP.equalsIgnoreCase("05")) {
            if (DCCPACTY.OUTPUT.AC_TYPE != 'K') {
                WS_MSGID = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSSETL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-LNZSSETL", LNCSSETL);
    }
    public void B040_NFIANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "LNCSSETL";
        BPCPNHIS.INFO.FMT_ID_LEN = 1699;
        BPCPNHIS.INFO.REF_NO = LNB5132_AWA_5132.CONT_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSSETL;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSSETL;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
