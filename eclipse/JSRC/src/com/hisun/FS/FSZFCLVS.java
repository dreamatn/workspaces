package com.hisun.FS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFCLVS {
    int JIBS_tmp_int;
    DBParm FSTVMST_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "FS510";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    String WS_VS_AC = " ";
    String WS_CI_NO = " ";
    String WS_ACO_AC = " ";
    char WS_CCY_STS = ' ';
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCIPACI DCCIPACI = new DCCIPACI();
    CICACCU CICACCU = new CICACCU();
    CICSACRL CICSACRL = new CICSACRL();
    CICSACR CICSACR = new CICSACR();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    FSCOOPVS FSCOOPVS = new FSCOOPVS();
    CICSACAC CICSACAC = new CICSACAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    FSRVMST FSRVMST = new FSRVMST();
    DDRMST DDRMST = new DDRMST();
    DCRSPAC DCRSPAC = new DCRSPAC();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    FSCFCLVS FSCFCLVS;
    public void MP(SCCGWA SCCGWA, FSCFCLVS FSCFCLVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFCLVS = FSCFCLVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZFCLVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        B020_UPD_REF();
        B030_DELETE_FSTVMST_PROC();
        B035_UPD_BP_PROC();
        B040_BP_NFHIS();
        B050_OUTPUT_PROC();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.KEY.ACC_NO = FSCFCLVS.ACC_NO;
        T000_READ_FSTVMST();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = FSCFCLVS.ACC_NO;
        CICQACRL.DATA.AC_REL = "01";
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        T000_READ_DDTMST();
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = FSCFCLVS.ACC_NO;
        DDRCCY.CCY = FSCFCLVS.CCY;
        DDRCCY.CCY_TYPE = FSCFCLVS.CCY_TYP;
        T000_READ_DDTCCY_AC();
        WS_CCY_STS = DDRCCY.STS;
        CEP.TRC(SCCGWA, WS_CCY_STS);
        WS_ACO_AC = DDRCCY.KEY.AC;
        DDRCCY.STS = 'C';
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        T000_REWRITE_DDTCCY();
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZACCU();
        WS_CI_NO = CICACCU.DATA.CI_NO;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCZM_CI_FBID;
            S000_ERR_MSG_PROC();
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = CICMSG_ERROR_MSG.CI_IS_CLOSE_STS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_UPD_REF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'D';
        CICSACRL.DATA.AC_NO = FSCFCLVS.ACC_NO;
        CICSACRL.DATA.AC_REL = "01";
        CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZSACRL();
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CICSACAC.DATA.ACAC_NO = WS_ACO_AC;
        S000_CALL_CIZSACAC();
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = FSCFCLVS.ACC_NO;
        CICSACR.DATA.ENTY_TYP = '4';
        CICSACR.DATA.CI_NO = WS_CI_NO;
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.CCY = FSCFCLVS.CCY;
        S000_CALL_CIZSACR();
    }
    public void B030_DELETE_FSTVMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.KEY.ACC_NO = FSCFCLVS.ACC_NO;
        T000_DELETE_FSTVMST();
    }
    public void B035_UPD_BP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = FSCFCLVS.ACC_NO;
        BPCSOCAC.ACO_AC = DDRCCY.KEY.AC;
        BPCSOCAC.CLOSE_AC_STS = WS_CCY_STS;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZSOCAC();
    }
    public void B040_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = FSCFCLVS.ACC_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = FSCFCLVS.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = FSCFCLVS;
        BPCPNHIS.INFO.FMT_ID = "FSZFCLVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 708;
        S000_CALL_BPZPNHIS();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCOOPVS);
        FSCOOPVS.FLOW_NO = FSCFCLVS.FLOW_NO;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = FSCOOPVS;
        SCCFMT.DATA_LEN = 14;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_FSTVMST() throws IOException,SQLException,Exception {
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.upd = true;
        IBS.READ(SCCGWA, FSRVMST, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_DELETE_FSTVMST() throws IOException,SQLException,Exception {
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        IBS.DELETE(SCCGWA, FSRVMST, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DELETE TABLE FSTVMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY_AC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
