package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5840 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    String WS_ERR_MSG = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSRLPW TDCSRLPW = new TDCSRLPW();
    TDCACM TDCACM = new TDCACM();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    TDRCMST TDRCMST = new TDRCMST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    TDB5840_AWA_5840 TDB5840_AWA_5840;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5840 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5840_AWA_5840>");
        TDB5840_AWA_5840 = (TDB5840_AWA_5840) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5840_AWA_5840.MAIN_AC);
        CEP.TRC(SCCGWA, TDB5840_AWA_5840.AC);
        CEP.TRC(SCCGWA, TDB5840_AWA_5840.AC_SEQ);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = TDB5840_AWA_5840.MAIN_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        B100_CHECK_INPUT_PROC();
        B160_CHK_CUS_PSW();
        B190_WRT_NFHIS_PROC();
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDB5840_AWA_5840.MAIN_AC.trim().length() == 0 
            && TDB5840_AWA_5840.AC.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B160_CHK_CUS_PSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5840_AWA_5840.MAIN_AC);
        CEP.TRC(SCCGWA, TDB5840_AWA_5840.DRAW_PSW);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDB5840_AWA_5840.MAIN_AC;
        T000_READ_TDTCMST();
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PSW_UNLOCKED);
        }
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "0" + TDRCMST.STSW.substring(4 + 1 - 1);
        TDRCMST.ERR_NUM = 0;
        T000_REWRITE_TDTCMST();
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = TDB5840_AWA_5840.MAIN_AC;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        IBS.init(SCCGWA, TDCACM);
        TDCACM.FUNC = 'C';
        TDCACM.AC_NO = TDB5840_AWA_5840.MAIN_AC;
        TDCACM.OLD_AC_NO = TDB5840_AWA_5840.MAIN_AC;
        TDCACM.CARD_PSW_OLD = TDB5840_AWA_5840.DRAW_PSW;
        TDCACM.CARD_PSW_NEW = TDB5840_AWA_5840.DRAW_PSW;
        TDCACM.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        TDCACM.ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCACM.CI_NM = CICCUST.O_DATA.O_CI_NM;
        S000_CALL_TDZACM();
    }
    public void B200_GET_LIST() throws IOException,SQLException,Exception {
        TDCSRLPW.MAIN_AC = TDB5840_AWA_5840.MAIN_AC;
        TDCSRLPW.AC_SEQ = TDB5840_AWA_5840.AC_SEQ;
        TDCSRLPW.AC = TDB5840_AWA_5840.AC;
        TDCSRLPW.BV_TYP = TDB5840_AWA_5840.BV_TYP;
        TDCSRLPW.DRAW_PSW = TDB5840_AWA_5840.DRAW_PSW;
        S000_CALL_TDZSRLPW();
    }
    public void B190_WRT_NFHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P506";
        BPCPNHIS.INFO.AC = TDB5840_AWA_5840.MAIN_AC;
        BPCPNHIS.INFO.CI_NO = CICQACRI.DATA.CI_NO;
        BPCPNHIS.INFO.TX_TOOL = TDB5840_AWA_5840.MAIN_AC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.AC_SEQ = TDB5840_AWA_5840.AC_SEQ;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void S000_CALL_TDZSRLPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PRO-TDZSRLPW", TDCSRLPW);
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
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
