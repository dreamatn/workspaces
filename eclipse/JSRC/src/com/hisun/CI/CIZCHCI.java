package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCTPCL;
import com.hisun.DC.*;
import com.hisun.EQ.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZCHCI {
    int JIBS_tmp_int;
    int ACR_AC_CNM_LEN;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACR_RD;
    DBParm CITBAS_RD;
    brParm CITACAC_BR = new brParm();
    DBParm CITACAC_RD;
    brParm CITAGT_BR = new brParm();
    DBParm CITAGT_RD;
    boolean pgmRtn = false;
    char WS_CI_TYP = ' ';
    String WS_CI_NM_NEW = " ";
    String WS_FRM_APP = " ";
    char WS_CI_COR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACAC CIRACACO = new CIRACAC();
    CIRACAC CIRACACN = new CIRACAC();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    DCCUCINO DCCUCINO = new DCCUCINO();
    EQCSACT EQCSACT = new EQCSACT();
    BPCFXCUS BPCFXCUS = new BPCFXCUS();
    BPCSFEEG BPCSFEEG = new BPCSFEEG();
    CICCKOC CICCKOC = new CICCKOC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDCUFEES DDCUFEES = new DDCUFEES();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCHCI CICCHCI;
    public void MP(SCCGWA SCCGWA, CICCHCI CICCHCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCHCI = CICCHCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCHCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCHCI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_CHANGE_AC_CI();
        if (pgmRtn) return;
        B040_CHANGE_ACAC_CI();
        if (pgmRtn) return;
        B050_CHANGE_AGT_CI();
        if (pgmRtn) return;
        B060_CHANGE_DC();
        if (pgmRtn) return;
        B070_CHANGE_EQ();
        if (pgmRtn) return;
        B080_CHANGE_FX();
        if (pgmRtn) return;
        B090_CHANGE_FEE();
        if (pgmRtn) return;
        if (WS_CI_COR_FLG == 'Y' 
            && WS_FRM_APP.equalsIgnoreCase("DD")) {
            B100_COR_AC_WAIVE_FEE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCHCI.INPUT.AC_NO);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCHCI.INPUT.AC_NO;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        WS_FRM_APP = CIRACR.FRM_APP;
        CEP.TRC(SCCGWA, CICCHCI.INPUT.OLD_CINO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCHCI.INPUT.OLD_CINO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '2' 
            || CIRBAS.CI_TYP == '3') {
            WS_CI_COR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, CICCHCI.INPUT.NEW_CINO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCHCI.INPUT.NEW_CINO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        WS_CI_TYP = CIRBAS.CI_TYP;
        WS_CI_NM_NEW = CIRBAS.CI_NM;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (!CIRBAS.STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "CI-STS-ABNORMAL");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_STS_ABNORMAL, CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CIRACR.ENTY_TYP == '2' 
            && !CIRACR.PROD_CD.equalsIgnoreCase("1203010101") 
            && !CIRACR.PROD_CD.equalsIgnoreCase("1203021401")) {
            B020_CHECK_CARD_NUM();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_CARD_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKOC);
        CICCKOC.DATA.CI_NO = CICCHCI.INPUT.NEW_CINO;
        CICCKOC.DATA.OPEN_BR = CIRACR.OPN_BR;
        CICCKOC.DATA.AGENT_FLG = 'N';
        S000_CALL_CIZCKOC();
        if (pgmRtn) return;
    }
    public void B030_CHANGE_AC_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        if (!CIRACR.CI_NO.equalsIgnoreCase(CICCHCI.INPUT.OLD_CINO)) {
            CEP.TRC(SCCGWA, "OLD CI-NO INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_OLD_CINO_INPUT_ERR, CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.CI_NO = CICCHCI.INPUT.NEW_CINO;
        if (WS_CI_TYP == '1') {
            CIRACR.AC_CNM = WS_CI_NM_NEW;
            ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        }
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC_ACR();
        if (pgmRtn) return;
    }
    public void B040_CHANGE_ACAC_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        T000_STARTBR_CITACAC_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRACAC.FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = CIRACAC.AGR_NO;
                DDCIQBAL.DATA.CCY = CIRACAC.CCY;
                DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
                JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
                if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACCT_EXT_HLD, CICCHCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STSW);
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || TDCACE.DATA[1-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACCT_EXT_HLD, CICCHCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                || CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, DCCIQHLD);
                DCCIQHLD.INP_DATA.AC = CIRACAC.KEY.ACAC_NO;
                S000_CALL_DCZIQHLD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCIQHLD.OUT_DATA.LAW_AMT);
                if (DCCIQHLD.OUT_DATA.LAW_AMT == 'Y') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACCT_EXT_HLD, CICCHCI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, CIRACACO);
            IBS.init(SCCGWA, CIRACACN);
            IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
            CIRACAC.CI_NO = CICCHCI.INPUT.NEW_CINO;
            CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACAC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B050_CHANGE_AGT_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = CIRACR.KEY.AGR_NO;
        T000_STARTBR_CITAGT_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRAGTO);
            IBS.init(SCCGWA, CIRAGTN);
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
            CIRAGT.CI_NO = CICCHCI.INPUT.NEW_CINO;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B060_CHANGE_DC() throws IOException,SQLException,Exception {
        if (WS_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINO);
            DCCUCINO.FUNC = '2';
            DCCUCINO.CARD_NO = CICCHCI.INPUT.AC_NO;
            DCCUCINO.CI_TYP = 'P';
            DCCUCINO.NEW_CI_NO = CICCHCI.INPUT.NEW_CINO;
            S000_CALL_DCZUCINO();
            if (pgmRtn) return;
        }
    }
    public void B070_CHANGE_EQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSACT);
        EQCSACT.FUNC = 'C';
        EQCSACT.DATA.EQ_BKID = "01";
        EQCSACT.DATA.CI_NO = CICCHCI.INPUT.OLD_CINO;
        EQCSACT.DATA.OCI_NO = CICCHCI.INPUT.NEW_CINO;
        S000_CALL_EQZSACT();
        if (pgmRtn) return;
    }
    public void B080_CHANGE_FX() throws IOException,SQLException,Exception {
        if (WS_FRM_APP.equalsIgnoreCase("DC") 
            || WS_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, BPCFXCUS);
            BPCFXCUS.FUNC = '2';
            BPCFXCUS.AC_NO = CICCHCI.INPUT.AC_NO;
            BPCFXCUS.NEW_CI_NO = CICCHCI.INPUT.NEW_CINO;
            BPCFXCUS.OLD_CI_NO = CICCHCI.INPUT.OLD_CINO;
            S000_CALL_BPZFXCUS();
            if (pgmRtn) return;
        }
    }
    public void B090_CHANGE_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFEEG);
        BPCSFEEG.CI_NO_NEW = CICCHCI.INPUT.NEW_CINO;
        BPCSFEEG.CI_NO_OLD = CICCHCI.INPUT.OLD_CINO;
        BPCSFEEG.AC = CICCHCI.INPUT.AC_NO;
        S000_CALL_BPZSFEEG();
        if (pgmRtn) return;
    }
    public void B100_COR_AC_WAIVE_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUFEES);
        DDCUFEES.FUNC = '3';
        DDCUFEES.AC_NO = CICCHCI.INPUT.AC_NO;
        DDCUFEES.CI_NO = CICCHCI.INPUT.OLD_CINO;
        S000_CALL_DDZUFEES();
        if (pgmRtn) return;
        if (DDCUFEES.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUFEES.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCUFEES);
        DDCUFEES.FUNC = '4';
        DDCUFEES.AC_NO = CICCHCI.INPUT.AC_NO;
        DDCUFEES.CI_NO = CICCHCI.INPUT.NEW_CINO;
        S000_CALL_DDZUFEES();
        if (pgmRtn) return;
        if (DDCUFEES.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUFEES.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_WRT_HIS_PROC_ACR() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.CI_NO = CICCHCI.INPUT.OLD_CINO;
        BPCPNHIS.INFO.AC = CICCHCI.INPUT.AC_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_ACAC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACAC";
        BPCPNHIS.INFO.FMT_ID_LEN = 242;
        BPCPNHIS.INFO.CI_NO = CICCHCI.INPUT.NEW_CINO;
        BPCPNHIS.INFO.AC = CICCHCI.INPUT.AC_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACACO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACACN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_AGT() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRAGT";
        BPCPNHIS.INFO.FMT_ID_LEN = 664;
        BPCPNHIS.INFO.CI_NO = CICCHCI.INPUT.NEW_CINO;
        BPCPNHIS.INFO.AC = CICCHCI.INPUT.AC_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRAGTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRAGTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZCKOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-OPEN-CARD", CICCKOC);
        if (CICCKOC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKOC.RC);
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_DCZUCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-UPDATE-CI-NO", DCCUCINO);
        if (DCCUCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINO.RC);
        }
    }
    public void S000_CALL_EQZSACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-SACT", EQCSACT);
        if (EQCSACT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, EQCSACT.RC);
        }
    }
    public void S000_CALL_BPZFXCUS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX-UPD-CUS", BPCFXCUS);
        if (BPCFXCUS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFXCUS.RC);
        }
    }
    public void S000_CALL_BPZSFEEG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FEE-CHANGE", BPCSFEEG);
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.upd = true;
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICCHCI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.REWRITE(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_STARTBR_CITACAC_BY_AC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_REWRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.REWRITE(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_STARTBR_CITAGT_BY_AC() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "ENTY_NO";
        CITAGT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_REWRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.REWRITE(SCCGWA, CIRAGT, CITAGT_RD);
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD, true);
    }
    public void S000_CALL_DDZUFEES() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-WAVE-FEES", DDCUFEES, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
