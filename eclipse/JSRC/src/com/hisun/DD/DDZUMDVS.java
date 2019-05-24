package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUMDVS {
    int JIBS_tmp_int;
    DBParm DDTVSABI_RD;
    DBParm DDTMST_RD;
    String WS_VS_AC = " ";
    String K_OUTPUT_FMT = "DD870";
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    CICQACRL CICQACRL = new CICQACRL();
    DDCOMDVS DDCOMDVS = new DDCOMDVS();
    CICSACR CICSACR = new CICSACR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMST DDRMST = new DDRMST();
    DDRVSABI DDRVSABI = new DDRVSABI();
    SCCGWA SCCGWA;
    DDCUMDVS DDCUMDVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUMDVS DDCUMDVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUMDVS = DDCUMDVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZUMDVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DDCUMDVS.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_AC_INFO();
        B020_CHECK_INFO();
        B030_MAINTAIN_VS_INFO();
        B040_BP_NFHIS();
        B050_SET_RES();
    }
    public void B010_GET_AC_INFO() throws IOException,SQLException,Exception {
        DDRVSABI.KEY.VS_AC = DDCUMDVS.IO_AREA.VS_AC;
        DDRVSABI.KEY.CCY = DDCUMDVS.IO_AREA.CCY;
        DDRVSABI.KEY.CCY_TYP = DDCUMDVS.IO_AREA.CCY_TYP;
        T000_READ_DDTVSABI();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_VSAC_NOT_FND;
            S000_ERR_MSG_PROC();
        }
        if (DDRVSABI.AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUMDVS.IO_AREA.VS_AC);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDCUMDVS.IO_AREA.VS_AC;
        CICQACRL.DATA.AC_REL = "01";
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        T000_READ_DDTMST();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_FOUND;
            S000_ERR_MSG_PROC();
        }
        C000_CHECK_ACCOUNT();
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CUSTOM_FORBID;
            S000_ERR_MSG_PROC();
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CUSTOM_CLOSE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_MAINTAIN_VS_INFO() throws IOException,SQLException,Exception {
        DDRVSABI.VS_AC_NAME = DDCUMDVS.IO_AREA.VS_AC_NM;
        DDRVSABI.VS_CON_NAME = DDCUMDVS.IO_AREA.VS_CN_NM;
        DDRVSABI.VS_CON_TEL = DDCUMDVS.IO_AREA.VS_CN_TL;
        DDRVSABI.VS_CON_ADDR = DDCUMDVS.IO_AREA.VS_CN_AR;
        DDRVSABI.REMARK = DDCUMDVS.IO_AREA.REMARK;
        DDRVSABI.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRVSABI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVSABI.VS_IDTYP = DDCUMDVS.IO_AREA.VS_IDTYP;
        DDRVSABI.VS_IDNO = DDCUMDVS.IO_AREA.VS_IDNO;
        DDRVSABI.VS_MMO = DDCUMDVS.IO_AREA.VS_MMO;
        DDRVSABI.VS_SYSNO = DDCUMDVS.IO_AREA.VS_SYSNO;
        DDRVSABI.VS_FLG = DDCUMDVS.IO_AREA.VS_FLG;
        DDRVSABI.VS_CHNLNO = DDCUMDVS.IO_AREA.CHNLNO;
        DDRVSABI.VS_INTAC = DDCUMDVS.IO_AREA.VS_INTAC;
        CEP.TRC(SCCGWA, DDCUMDVS.IO_AREA.VS_MMO);
        CEP.TRC(SCCGWA, DDRVSABI.VS_AC_NAME);
        T000_REWRITE_DDTVSABI();
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CEP.TRC(SCCGWA, DDCUMDVS.IO_AREA.VS_AC);
        CICSACR.DATA.AGR_NO = DDCUMDVS.IO_AREA.VS_AC;
        CICSACR.DATA.AC_CNM = DDCUMDVS.IO_AREA.VS_AC_NM;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        S000_CALL_CIZSACR();
        CEP.TRC(SCCGWA, DDCUMDVS.IO_AREA.VS_AC_NM);
        CEP.TRC(SCCGWA, CICSACR.DATA.AC_CNM);
    }
    public void B040_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = DDCUMDVS.IO_AREA.VS_AC;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = DDCUMDVS.IO_AREA.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCUMDVS;
        BPCPNHIS.INFO.FMT_ID = "DDZUMDVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 1267;
        S000_CALL_BPZPNHIS();
    }
    public void B050_SET_RES() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVSABI.KEY.VS_AC);
        CEP.TRC(SCCGWA, DDRVSABI.VS_AC_NAME);
        IBS.init(SCCGWA, DDCOMDVS);
        DDCUMDVS.O_AREA.PARE_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            DDCUMDVS.O_AREA.PARE_AC_NM = CICACCU.DATA.AC_ENM;
        } else {
            DDCUMDVS.O_AREA.PARE_AC_NM = CICACCU.DATA.AC_CNM;
        }
        DDCUMDVS.O_AREA.OPE_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCUMDVS.O_AREA.OPE_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCUMDVS.O_AREA.AUT_TLR = " ";
        DDCOMDVS.VS_AC = DDRVSABI.KEY.VS_AC;
        DDCOMDVS.MAC = CICQACRL.O_DATA.O_REL_AC_NO;
        DDCOMDVS.CCY = DDCUMDVS.IO_AREA.CCY;
        DDCOMDVS.CCY_TYP = DDCUMDVS.IO_AREA.CCY_TYP;
        DDCOMDVS.VS_AC_NM = DDRVSABI.VS_AC_NAME;
        DDCOMDVS.VS_CN_NM = DDCUMDVS.IO_AREA.VS_CN_NM;
        DDCOMDVS.VS_CN_TL = DDCUMDVS.IO_AREA.VS_CN_TL;
        DDCOMDVS.VS_CN_AR = DDCUMDVS.IO_AREA.VS_CN_AR;
        DDCOMDVS.REMARK = DDCUMDVS.IO_AREA.REMARK;
        DDCOMDVS.UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        DDCOMDVS.ID_TYPE = DDCUMDVS.IO_AREA.VS_IDTYP;
        DDCOMDVS.ID_NO = DDCUMDVS.IO_AREA.VS_IDNO;
        DDCOMDVS.OPE_TLR = DDRVSABI.CRT_TLR;
        DDCOMDVS.AUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCOMDVS.MMO = DDCUMDVS.IO_AREA.VS_MMO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOMDVS;
        SCCFMT.DATA_LEN = 1298;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.upd = true;
        IBS.READ(SCCGWA, DDRVSABI, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void C000_CHECK_ACCOUNT() throws IOException,SQLException,Exception {
        WS_AC_STS = DDRMST.AC_STS;
        WS_AC_STS_WORD = DDRMST.AC_STS_WORD;
        CEP.TRC(SCCGWA, WS_AC_STS);
        CEP.TRC(SCCGWA, WS_AC_STS_WORD);
        CEP.TRC(SCCGWA, "AA");
        if (WS_AC_STS_WORD.equalsIgnoreCase("11")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FORBID;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "BB");
        if (WS_AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "CC");
        if (WS_AC_STS == 'D') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "DD");
        if (WS_AC_STS == 'M') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLO_INT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "EE");
        if (WS_AC_STS == 'V') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_APRV;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "FF");
        if (WS_AC_STS == 'O') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NO_ACTV;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "GG");
        CEP.TRC(SCCGWA, "HH");
    }
    public void T000_REWRITE_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        IBS.REWRITE(SCCGWA, DDRVSABI, DDTVSABI_RD);
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICACCU.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICSACR.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICQACRL.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        DDCUMDVS.O_AREA.RC_CODE = 08;
        DDCUMDVS.O_AREA.MSG_ID = WS_MSG_ID;
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUMDVS.O_AREA.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUMDVS=");
            CEP.TRC(SCCGWA, DDCUMDVS);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
