package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPSWDM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    String K_PSWDM_I_FMT = "TD527";
    String K_PTPM_O_FMT = "TD525";
    String WS_ERR_MSG = " ";
    TDZPSWDM_WS_TABLES_INFO WS_TABLES_INFO = new TDZPSWDM_WS_TABLES_INFO();
    char WS_BVT_DB_FLG = ' ';
    TDZPSWDM_WS_CHECK_INFO WS_CHECK_INFO = new TDZPSWDM_WS_CHECK_INFO();
    String WS_PSW = " ";
    char WS_YBT_AC_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDRBVT TDRBVT = new TDRBVT();
    TDCOPSMI TDCOPSMI = new TDCOPSMI();
    TDCOPTPO TDCOPTPO = new TDCOPTPO();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICACCU CICACCU = new CICACCU();
    TDCACM TDCACM = new TDCACM();
    TDRCMST TDRCMST = new TDRCMST();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    TDCPSWDM TDCPSWDM;
    public void MP(SCCGWA SCCGWA, TDCPSWDM TDCPSWDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPSWDM = TDCPSWDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPSWDM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPSWDM.BV_CD);
        CEP.TRC(SCCGWA, TDCPSWDM.BV_TYP);
        CEP.TRC(SCCGWA, TDCPSWDM.BV_NO);
        CEP.TRC(SCCGWA, TDCPSWDM.AC_NO);
        CEP.TRC(SCCGWA, TDCPSWDM.AC_NM);
        CEP.TRC(SCCGWA, TDCPSWDM.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCPSWDM.PSW_O);
        CEP.TRC(SCCGWA, TDCPSWDM.PSW_N);
        CEP.TRC(SCCGWA, TDCPSWDM.PSBK_SEQ);
        B100_CHK_INPUT_PROC();
        B210_GET_AC_BV_INFO();
        B214_CHECK_AC_BV_INFO();
        B220_CHANGE_DRAW_PSWD();
        B230_WRI_NFIN_HIS_PROC();
        B300_FMT_OUTPUT_PROC();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCPSWDM.BV_TYP != '0' 
            && TDCPSWDM.BV_TYP != '1' 
            && TDCPSWDM.BV_TYP != '2' 
            && TDCPSWDM.BV_TYP != '3' 
            && TDCPSWDM.BV_TYP != '5' 
            && TDCPSWDM.BV_TYP != '6' 
            && TDCPSWDM.BV_TYP != '7') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B210_GET_AC_BV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCPSWDM.AC_NO;
        T000_READ_TDTCMST();
        WS_TABLES_INFO.WS_CMST_STS = TDRCMST.STS;
        WS_CHECK_INFO.WS_CHECK_AC_STS = TDRCMST.STS;
        WS_TABLES_INFO.WS_CMST_STSW = TDRCMST.STSW;
        WS_CHECK_INFO.WS_CHECK_AC_STSW = TDRCMST.STSW;
        WS_CHECK_INFO.WS_CHECK_AC_BR = TDRCMST.OWNER_BR;
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPSWDM.AC_NO;
        CEP.TRC(SCCGWA, TDCPSWDM.BV_TYP);
        if (TDCPSWDM.BV_TYP == '1') {
            CEP.TRC(SCCGWA, "TESTWFJ");
            if (TDCPSWDM.BV_NO.trim().length() > 0) {
                TDRBVT.BV_NO = TDCPSWDM.BV_NO;
                T000_READ_BVT_BVNO();
            } else {
                T000_READ_BVT_LAST();
            }
        } else {
            T000_READ_TDTBVT();
        }
        WS_TABLES_INFO.WS_BVT_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_CHECK_INFO.WS_CHECK_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_TABLES_INFO.WS_BVT_DRAW_INF = TDRCMST.DRAW_INF;
        WS_CHECK_INFO.WS_CHECK_DRAW_INF = TDRCMST.DRAW_INF;
        WS_TABLES_INFO.WS_BVT_STSW = TDRBVT.STSW;
        WS_CHECK_INFO.WS_CHECK_BVT_STSW = TDRBVT.STSW;
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B214_CHECK_AC_BV_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_STS);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_STSW);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_MTH);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_INF);
        if (WS_CHECK_INFO.WS_CHECK_AC_STS == 'C' 
            || WS_CHECK_INFO.WS_CHECK_AC_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_AC_STS == 'R' 
            || WS_CHECK_INFO.WS_CHECK_AC_STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if ((WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_PLEGDED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW == null) WS_CHECK_INFO.WS_CHECK_BVT_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_BVT_STSW += " ";
        if (WS_CHECK_INFO.WS_CHECK_BVT_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSW_NOT_END;
            S000_ERR_MSG_PROC();
        }
        if (TDRCMST.DRAW_MTH != '1' 
            && TDRCMST.DRAW_MTH != '5') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NON_VIA_PWD_DRAW;
            S000_ERR_MSG_PROC();
        }
        if (!TDRBVT.BV_NO.equalsIgnoreCase(TDCPSWDM.BV_NO)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "F-BUG88");
        CEP.TRC(SCCGWA, TDCPSWDM.PSW_O);
        CEP.TRC(SCCGWA, TDCPSWDM.PSW_N);
        if (TDCPSWDM.PSW_O.equalsIgnoreCase(TDCPSWDM.PSW_N)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSW_CANT_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B220_CHANGE_DRAW_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = TDCPSWDM.AC_NO;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            IBS.init(SCCGWA, TDCACM);
            TDCACM.FUNC = 'M';
            TDCACM.AC_NO = TDCPSWDM.AC_NO;
            TDCACM.OLD_AC_NO = TDCPSWDM.AC_NO;
            TDCACM.CARD_PSW_OLD = TDCPSWDM.PSW_O;
            TDCACM.CARD_PSW_NEW = TDCPSWDM.PSW_N;
            TDCACM.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDCACM.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDCACM.CI_NM = TDCPSWDM.AC_NM;
            S000_CALL_TDZACM();
        }
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P504";
        BPCPNHIS.INFO.AC = TDCPSWDM.AC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        B310_PRT_INPUT_INFO();
    }
    public void B310_PRT_INPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOPSMI);
        TDCOPSMI.BV_TYP = TDCPSWDM.BV_TYP;
        TDCOPSMI.BV_NO = TDCPSWDM.BV_NO;
        TDCOPSMI.AC_NO = TDCPSWDM.AC_NO;
        TDCOPSMI.AC_NM = TDCPSWDM.AC_NM;
        TDCOPSMI.DRAW_MTH = TDCPSWDM.DRAW_MTH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PSWDM_I_FMT;
        SCCFMT.DATA_PTR = TDCOPSMI;
        SCCFMT.DATA_LEN = 307;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E143";
        SCCHMPW.INP_DATA.AC_FLG = '0';
        SCCHMPW.INP_DATA.OLD_AC = TDCPSWDM.AC_NO;
        SCCHMPW.INP_DATA.NEW_AC = TDCPSWDM.AC_NO;
        SCCHMPW.INP_DATA.PIN_DA = WS_PSW;
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.OLD_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.PIN_DA);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        WS_PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "NO ERROR1");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "NO ERROR2");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BVT_BVNO() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTCMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READU_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
