package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZBSP32 {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSIACC DDCSIACC = new DDCSIACC();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    CICACCU CICACCU = new CICACCU();
    CICMACR CICMACR = new CICMACR();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    BPROCAC BPROCAC = new BPROCAC();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCBSP32 DDCBSP32;
    public void MP(SCCGWA SCCGWA, DDCBSP32 DDCBSP32) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCBSP32 = DDCBSP32;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDZBSP32 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B00_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCBSP32.AC);
        B010_GET_AC_INF();
        B020_CANCEL_CI_REL();
        B030_DSD_UNUSE_CHQB_PROC();
        B090_UPD_MST_PROC();
        B100_CRT_DD_OCAC_PROC();
    }
    public void B010_GET_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCBSP32.AC;
        T000_READUP_DDTMST();
        DDRCCY.CUS_AC = DDCBSP32.AC;
        DDRCCY.CCY = DDCBSP32.CCY_CCY;
        DDRCCY.CCY_TYPE = DDCBSP32.CCY_TYPE;
        T000_READUP_DDTCCY();
    }
    public void B020_CANCEL_CI_REL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.ACAC_NO = DDRCCY.KEY.AC;
        S000_CALL_CIZSACAC();
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCBSP32.AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = DDCBSP32.AC;
        S000_CALL_CIZSACR();
    }
    public void B030_DSD_UNUSE_CHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIACC);
        DDCSIACC.AC = DDCBSP32.AC;
        DDCSIACC.FUNC = 'D';
        S000_CALL_DDZSIACC();
    }
    public void B090_UPD_MST_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.AC_STS = 'C';
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 10 - 1) + "0" + DDRMST.AC_STS_WORD.substring(10 + 1 - 1);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 11 - 1) + "1" + DDRMST.AC_STS_WORD.substring(11 + 1 - 1);
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        DDRCCY.STS = 'C';
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
    }
    public void B100_CRT_DD_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.ACO_AC = DDRCCY.KEY.AC;
        BPCSOCAC.AC = DDCBSP32.AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.LOSS_INT = DDCBSP32.CLOSE_INT;
        BPCSOCAC.LOSS_AMT = DDCBSP32.CLOSE_AMT;
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CLOSE_AC_STS = DDRMST.CLOSE_AC_STS;
        BPCSOCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
    }
    public void B110_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCBSP32.AC;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "DDCBSP32";
        BPCPNHIS.INFO.TX_RMK = "AC BATCH CANCEL PROCESS";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = "0115845";
        BPCPNHIS.INFO.TX_TYP_CD = "C004";
        BPCPNHIS.INFO.NEW_DAT_PT = DDCBSP32;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_DDZSIACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSIACC", DDCSIACC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACR.RC);
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.fst = true;
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
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
