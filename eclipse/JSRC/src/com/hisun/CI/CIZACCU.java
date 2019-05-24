package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZACCU {
    int JIBS_tmp_int;
    int ACR_AC_CNM_LEN;
    int ACR_AC_ENM_LEN;
    int BAS_CI_NM_LEN;
    int BAS_TAX_BANK_LEN;
    DBParm CITACR_RD;
    DBParm CITBAS_RD;
    DBParm CITNAM_RD;
    DBParm CITPDM_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITACRL_RD;
    boolean pgmRtn = false;
    short K_MAX_BASC_COUNT = 2;
    short K_MAX_ACRC_COUNT = 2;
    short WS_BASC_COUNT = 0;
    short WS_BASC_I = 0;
    char WS_BASC_FLG = ' ';
    short WS_ACRC_COUNT = 0;
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    String WS_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICACCU CICACCU;
    CICBASC CICBASC;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICACCU CICACCU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICACCU = CICACCU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZACCU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICBASC = (CICBASC) SCCGWA.COMM_AREA.CITBAS_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICACCU.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        if (CICACCU.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT, CICACCU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_CI_NO() throws IOException,SQLException,Exception {
        B030_INQ_ACR_INF();
        if (pgmRtn) return;
        B040_INQ_BAS_INF();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_ATTR == '1') {
                B050_INQ_PDM_INF();
                if (pgmRtn) return;
            }
        } else if (CIRBAS.CI_TYP == '2') {
            if (CIRBAS.CI_ATTR == '1') {
                B060_INQ_CDM_INF();
                if (pgmRtn) return;
            }
        } else if (CIRBAS.CI_TYP == '3') {
            if (CIRBAS.CI_ATTR == '1' 
                || CIRBAS.CI_ATTR == '6') {
                B070_INQ_FDM_INF();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "CI-TYP ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DATA_ERROR, CICACCU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICACCU.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        WS_CI_NO = CIRACR.CI_NO;
        CICACCU.DATA.CI_NO = CIRACR.CI_NO;
        CICACCU.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICACCU.DATA.STS = CIRACR.STS;
        CICACCU.DATA.STSW = CIRACR.STSW;
        CICACCU.DATA.PROD_CD = CIRACR.PROD_CD;
        CICACCU.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACCU.DATA.FRM_APP = CIRACR.FRM_APP;
        CICACCU.DATA.CCY = CIRACR.CCY;
        CICACCU.DATA.SHOW_FLG = CIRACR.SHOW_FLG;
        CICACCU.DATA.SMS_FLG = CIRACR.SMS_FLG;
        CICACCU.DATA.OPN_BR = CIRACR.OPN_BR;
        CICACCU.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICACCU.DATA.CLOSE_DT = CIRACR.CLOSE_DT;
    }
    public void B040_INQ_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICACCU.DATA.CI_CNM = CIRBAS.CI_NM;
        CICACCU.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICACCU.DATA.ID_NO = CIRBAS.ID_NO;
        CICACCU.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICACCU.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICACCU.DATA.SVRLVL = CIRBAS.SVR_LVL;
        CICACCU.DATA.CI_STSW = CIRBAS.STSW;
        CICACCU.DATA.VER_STSW = CIRBAS.VER_STSW;
        CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
        if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
        JIBS_tmp_int = CIRBAS.IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
        if (CIRBAS.IDE_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CICACCU.DATA.EP_FLG = 'Y';
        } else {
            CICACCU.DATA.EP_FLG = 'N';
        }
        CICACCU.DATA.CI_OIC = CIRBAS.OIC_NO;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = WS_CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        CICACCU.DATA.CI_ENM = CIRNAM.CI_NM;
        if (CIRBAS.CI_TYP == '1') {
            CICACCU.DATA.AC_CNM = CIRBAS.CI_NM;
            CICACCU.DATA.AC_ENM = CICACCU.DATA.CI_ENM;
        } else if (CIRBAS.CI_TYP == '2' 
                && CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_ACNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICACCU.DATA.AC_CNM = CIRACR.AC_CNM;
            CICACCU.DATA.AC_ENM = CIRACR.AC_ENM;
        } else {
            CICACCU.DATA.AC_CNM = CIRACR.AC_CNM;
            CICACCU.DATA.AC_ENM = CIRACR.AC_ENM;
        }
    }
    public void B050_INQ_PDM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRPDM);
        CIRPDM.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITPDM();
        if (pgmRtn) return;
        CICACCU.DATA.SID_FLG = CIRPDM.SID_FLG;
        CICACCU.DATA.RESIDENT = CIRPDM.RESIDENT;
    }
    public void B060_INQ_CDM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITCDM();
        if (pgmRtn) return;
        CICACCU.DATA.SID_FLG = CIRCDM.SID_FLG;
        CICACCU.DATA.RESIDENT = CIRCDM.RESIDENT;
        CICACCU.DATA.ORG_TYPE = CIRCDM.ORG_TYPE;
        CICACCU.DATA.INDUS1 = CIRCDM.INDUS1;
    }
    public void B070_INQ_FDM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITFDM();
        if (pgmRtn) return;
        CICACCU.DATA.SID_FLG = CIRFDM.SID_FLG;
        CICACCU.DATA.RESIDENT = CIRFDM.RESIDENT;
        CICACCU.DATA.ORG_TYPE = CIRFDM.ORG_TYPE;
        CICACCU.DATA.FIN_TYPE = CIRFDM.FIN_TYPE;
        CICACCU.DATA.INDUS1 = CIRFDM.INDUS1;
    }
    public void R000_TRANS_ACRC_TO_ACR() throws IOException,SQLException,Exception {
        CIRACR.KEY.AGR_NO = CICACRC.DATA[WS_ACRC_I-1].AGR_NO;
        CIRACR.ENTY_TYP = CICACRC.DATA[WS_ACRC_I-1].ENTY_TYP;
        CIRACR.CI_NO = CICACRC.DATA[WS_ACRC_I-1].CI_NO;
        CIRACR.STS = CICACRC.DATA[WS_ACRC_I-1].STS;
        CIRACR.STSW = CICACRC.DATA[WS_ACRC_I-1].STSW;
        CIRACR.PROD_CD = CICACRC.DATA[WS_ACRC_I-1].PROD_CD;
        CIRACR.CNTRCT_TYP = CICACRC.DATA[WS_ACRC_I-1].CNTRCT_TYP;
        CIRACR.FRM_APP = CICACRC.DATA[WS_ACRC_I-1].FRM_APP;
        CIRACR.CCY = CICACRC.DATA[WS_ACRC_I-1].CCY;
        CIRACR.SHOW_FLG = CICACRC.DATA[WS_ACRC_I-1].SHOW_FLG;
        CIRACR.SMS_FLG = CICACRC.DATA[WS_ACRC_I-1].SMS_FLG;
        CIRACR.AC_CNM = CICACRC.DATA[WS_ACRC_I-1].AC_CNM;
        CIRACR.AC_ENM = CICACRC.DATA[WS_ACRC_I-1].AC_ENM;
        ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
        ACR_AC_ENM_LEN = CIRACR.AC_ENM.length();
        CIRACR.AC_SEQ = CICACRC.DATA[WS_ACRC_I-1].AC_SEQ;
        CIRACR.OPN_BR = CICACRC.DATA[WS_ACRC_I-1].OPN_BR;
        CIRACR.OPEN_DT = CICACRC.DATA[WS_ACRC_I-1].OPEN_DT;
        CIRACR.CLOSE_DT = CICACRC.DATA[WS_ACRC_I-1].CLOSE_DT;
        CIRACR.OWNER_BK = CICACRC.DATA[WS_ACRC_I-1].OWNER_BK;
        CIRACR.CRT_TLR = CICACRC.DATA[WS_ACRC_I-1].CRT_TLR;
        CIRACR.CRT_BR = CICACRC.DATA[WS_ACRC_I-1].CRT_BR;
        CIRACR.CRT_DT = CICACRC.DATA[WS_ACRC_I-1].CRT_DT;
        CIRACR.UPD_TLR = CICACRC.DATA[WS_ACRC_I-1].UPD_TLR;
        CIRACR.UPD_BR = CICACRC.DATA[WS_ACRC_I-1].UPD_BR;
        CIRACR.UPD_DT = CICACRC.DATA[WS_ACRC_I-1].UPD_DT;
        CIRACR.TS = CICACRC.DATA[WS_ACRC_I-1].TS;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CEP.TRC(SCCGWA, WS_ACRC_I);
    }
    public void R000_WRITE_ACRC() throws IOException,SQLException,Exception {
        CICACRC.COUNT = (short) (CICACRC.COUNT + 1);
        CICACRC.DATA[CICACRC.COUNT-1].AGR_NO = CIRACR.KEY.AGR_NO;
        CICACRC.DATA[CICACRC.COUNT-1].ENTY_TYP = CIRACR.ENTY_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].CI_NO = CIRACR.CI_NO;
        CICACRC.DATA[CICACRC.COUNT-1].STS = CIRACR.STS;
        CICACRC.DATA[CICACRC.COUNT-1].STSW = CIRACR.STSW;
        CICACRC.DATA[CICACRC.COUNT-1].PROD_CD = CIRACR.PROD_CD;
        CICACRC.DATA[CICACRC.COUNT-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACRC.DATA[CICACRC.COUNT-1].FRM_APP = CIRACR.FRM_APP;
        CICACRC.DATA[CICACRC.COUNT-1].CCY = CIRACR.CCY;
        CICACRC.DATA[CICACRC.COUNT-1].SHOW_FLG = CIRACR.SHOW_FLG;
        CICACRC.DATA[CICACRC.COUNT-1].SMS_FLG = CIRACR.SMS_FLG;
        CICACRC.DATA[CICACRC.COUNT-1].AC_CNM = CIRACR.AC_CNM;
        CICACRC.DATA[CICACRC.COUNT-1].AC_ENM = CIRACR.AC_ENM;
        CICACRC.DATA[CICACRC.COUNT-1].AC_SEQ = CIRACR.AC_SEQ;
        CICACRC.DATA[CICACRC.COUNT-1].OPN_BR = CIRACR.OPN_BR;
        CICACRC.DATA[CICACRC.COUNT-1].OPEN_DT = CIRACR.OPEN_DT;
        CICACRC.DATA[CICACRC.COUNT-1].CLOSE_DT = CIRACR.CLOSE_DT;
        CICACRC.DATA[CICACRC.COUNT-1].OWNER_BK = CIRACR.OWNER_BK;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_TLR = CIRACR.CRT_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_BR = CIRACR.CRT_BR;
        CICACRC.DATA[CICACRC.COUNT-1].CRT_DT = CIRACR.CRT_DT;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_TLR = CIRACR.UPD_TLR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_BR = CIRACR.UPD_BR;
        CICACRC.DATA[CICACRC.COUNT-1].UPD_DT = CIRACR.UPD_DT;
        CICACRC.DATA[CICACRC.COUNT-1].TS = CIRACR.TS;
        CEP.TRC(SCCGWA, CICACRC.COUNT);
        CEP.TRC(SCCGWA, CICACRC.DATA[CICACRC.COUNT-1].AGR_NO);
    }
    public void R000_TRANS_BASC_TO_BAS() throws IOException,SQLException,Exception {
        CIRBAS.KEY.CI_NO = CICBASC.DATA[WS_BASC_I-1].CI_NO;
        CIRBAS.CI_TYP = CICBASC.DATA[WS_BASC_I-1].CI_TYP;
        CIRBAS.CI_ATTR = CICBASC.DATA[WS_BASC_I-1].CI_ATTR;
        CIRBAS.SVR_LVL = CICBASC.DATA[WS_BASC_I-1].SVR_LVL;
        CIRBAS.SVR_LVL1 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL1;
        CIRBAS.SVR_LVL2 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL2;
        CIRBAS.SVR_LVL3 = CICBASC.DATA[WS_BASC_I-1].SVR_LVL3;
        CIRBAS.STSW = CICBASC.DATA[WS_BASC_I-1].STSW;
        CIRBAS.VER_STSW = CICBASC.DATA[WS_BASC_I-1].VER_STSW;
        CIRBAS.IDE_STSW = CICBASC.DATA[WS_BASC_I-1].IDE_STSW;
        CIRBAS.OPN_BR = CICBASC.DATA[WS_BASC_I-1].OPN_BR;
        CIRBAS.OPEN_DT = CICBASC.DATA[WS_BASC_I-1].OPEN_DT;
        CIRBAS.CLOSE_DT = CICBASC.DATA[WS_BASC_I-1].CLOSE_DT;
        CIRBAS.ORGIN_TP = CICBASC.DATA[WS_BASC_I-1].ORGIN_TP;
        CIRBAS.ORIGIN = CICBASC.DATA[WS_BASC_I-1].ORIGIN;
        CIRBAS.ORIGIN2 = CICBASC.DATA[WS_BASC_I-1].ORIGIN2;
        CIRBAS.CI_NM = CICBASC.DATA[WS_BASC_I-1].CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICBASC.DATA[WS_BASC_I-1].ID_TYPE;
        CIRBAS.ID_NO = CICBASC.DATA[WS_BASC_I-1].ID_NO;
        CIRBAS.ID_RGN = CICBASC.DATA[WS_BASC_I-1].ID_RGN;
        CIRBAS.TAX_BANK = CICBASC.DATA[WS_BASC_I-1].TAX_BANK;
        BAS_TAX_BANK_LEN = CIRBAS.TAX_BANK.length();
        CIRBAS.TAX_AC_NO = CICBASC.DATA[WS_BASC_I-1].TAX_AC_NO;
        CIRBAS.TAX_TYPE = CICBASC.DATA[WS_BASC_I-1].TAX_TYPE;
        CIRBAS.TAX_DIST_NO = CICBASC.DATA[WS_BASC_I-1].TAX_DIST_NO;
        CIRBAS.FTA_FLG = CICBASC.DATA[WS_BASC_I-1].FTA_FLG;
        CIRBAS.OWNER_BK = CICBASC.DATA[WS_BASC_I-1].OWNER_BK;
        CIRBAS.OWNER_BR = CICBASC.DATA[WS_BASC_I-1].OWNER_BR;
        CIRBAS.OIC_NO = CICBASC.DATA[WS_BASC_I-1].OIC_NO;
        CIRBAS.RESP_CD = CICBASC.DATA[WS_BASC_I-1].RESP_CD;
        CIRBAS.SUB_DP = CICBASC.DATA[WS_BASC_I-1].SUB_DP;
        CIRBAS.OPEN_CHNL = CICBASC.DATA[WS_BASC_I-1].OPEN_CHNL;
        CIRBAS.NRA_TAX_TYP = CICBASC.DATA[WS_BASC_I-1].NRA_TAX_TYP;
        CIRBAS.CRT_TLR = CICBASC.DATA[WS_BASC_I-1].CRT_TLR;
        CIRBAS.CRT_DT = CICBASC.DATA[WS_BASC_I-1].CRT_DT;
        CIRBAS.CRT_BR = CICBASC.DATA[WS_BASC_I-1].CRT_BR;
        CIRBAS.UPD_TLR = CICBASC.DATA[WS_BASC_I-1].UPD_TLR;
        CIRBAS.UPD_BR = CICBASC.DATA[WS_BASC_I-1].UPD_BR;
        CIRBAS.UPD_DT = CICBASC.DATA[WS_BASC_I-1].UPD_DT;
        CIRBAS.TS = CICBASC.DATA[WS_BASC_I-1].TS;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        CEP.TRC(SCCGWA, WS_BASC_I);
    }
    public void R000_WRITE_BASC() throws IOException,SQLException,Exception {
        CICBASC.COUNT = (short) (CICBASC.COUNT + 1);
        CICBASC.DATA[CICBASC.COUNT-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICBASC.DATA[CICBASC.COUNT-1].CI_TYP = CIRBAS.CI_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CI_ATTR = CIRBAS.CI_ATTR;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL = CIRBAS.SVR_LVL;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL1 = CIRBAS.SVR_LVL1;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL2 = CIRBAS.SVR_LVL2;
        CICBASC.DATA[CICBASC.COUNT-1].SVR_LVL3 = CIRBAS.SVR_LVL3;
        CICBASC.DATA[CICBASC.COUNT-1].STSW = CIRBAS.STSW;
        CICBASC.DATA[CICBASC.COUNT-1].VER_STSW = CIRBAS.VER_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].IDE_STSW = CIRBAS.IDE_STSW;
        CICBASC.DATA[CICBASC.COUNT-1].OPN_BR = CIRBAS.OPN_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_DT = CIRBAS.OPEN_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CLOSE_DT = CIRBAS.CLOSE_DT;
        CICBASC.DATA[CICBASC.COUNT-1].ORGIN_TP = CIRBAS.ORGIN_TP;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN = CIRBAS.ORIGIN;
        CICBASC.DATA[CICBASC.COUNT-1].ORIGIN2 = CIRBAS.ORIGIN2;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICBASC.DATA[CICBASC.COUNT-1].CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].ID_NO = CIRBAS.ID_NO;
        CICBASC.DATA[CICBASC.COUNT-1].ID_RGN = CIRBAS.ID_RGN;
        if (CIRBAS.TAX_BANK == null) CIRBAS.TAX_BANK = "";
        JIBS_tmp_int = CIRBAS.TAX_BANK.length();
        for (int i=0;i<140-JIBS_tmp_int;i++) CIRBAS.TAX_BANK += " ";
        CICBASC.DATA[CICBASC.COUNT-1].TAX_BANK = CIRBAS.TAX_BANK.substring(0, BAS_TAX_BANK_LEN);
        CICBASC.DATA[CICBASC.COUNT-1].TAX_AC_NO = CIRBAS.TAX_AC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_TYPE = CIRBAS.TAX_TYPE;
        CICBASC.DATA[CICBASC.COUNT-1].TAX_DIST_NO = CIRBAS.TAX_DIST_NO;
        CICBASC.DATA[CICBASC.COUNT-1].FTA_FLG = CIRBAS.FTA_FLG;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BK = CIRBAS.OWNER_BK;
        CICBASC.DATA[CICBASC.COUNT-1].OWNER_BR = CIRBAS.OWNER_BR;
        CICBASC.DATA[CICBASC.COUNT-1].OIC_NO = CIRBAS.OIC_NO;
        CICBASC.DATA[CICBASC.COUNT-1].RESP_CD = CIRBAS.RESP_CD;
        CICBASC.DATA[CICBASC.COUNT-1].SUB_DP = CIRBAS.SUB_DP;
        CICBASC.DATA[CICBASC.COUNT-1].OPEN_CHNL = CIRBAS.OPEN_CHNL;
        CICBASC.DATA[CICBASC.COUNT-1].NRA_TAX_TYP = CIRBAS.NRA_TAX_TYP;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_TLR = CIRBAS.CRT_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_DT = CIRBAS.CRT_DT;
        CICBASC.DATA[CICBASC.COUNT-1].CRT_BR = CIRBAS.CRT_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_TLR = CIRBAS.UPD_TLR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_BR = CIRBAS.UPD_BR;
        CICBASC.DATA[CICBASC.COUNT-1].UPD_DT = CIRBAS.UPD_DT;
        CICBASC.DATA[CICBASC.COUNT-1].TS = CIRBAS.TS;
        CEP.TRC(SCCGWA, CICBASC.COUNT);
        CEP.TRC(SCCGWA, CICBASC.DATA[CICBASC.COUNT-1].CI_NO);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        WS_ACRC_I = 0;
        WS_ACRC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_ACRC_I < CICACRC.COUNT 
            && WS_ACRC_FLG != 'Y') {
            WS_ACRC_I = (short) (WS_ACRC_I + 1);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            if (CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICACRC.DATA[WS_ACRC_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "READ ACR CASHE");
                R000_TRANS_ACRC_TO_ACR();
                if (pgmRtn) return;
                WS_ACRC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
        if (WS_ACRC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ ACR TABLE");
            CITACR_RD = new DBParm();
            CITACR_RD.TableName = "CITACR";
            IBS.READ(SCCGWA, CIRACR, CITACR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "ACR INFO NOT FOUND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICACCU.RC);
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
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICACRC.COUNT);
                if (CICACRC.COUNT < K_MAX_ACRC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE ACR CASHE");
                    R000_WRITE_ACRC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        WS_BASC_I = 0;
        WS_BASC_FLG = ' ';
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_BASC_I < CICBASC.COUNT 
            && WS_BASC_FLG != 'Y') {
            WS_BASC_I = (short) (WS_BASC_I + 1);
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            if (CIRBAS.KEY.CI_NO.equalsIgnoreCase(CICBASC.DATA[WS_BASC_I-1].CI_NO)) {
                CEP.TRC(SCCGWA, "READ BAS CAHSE");
                R000_TRANS_BASC_TO_BAS();
                if (pgmRtn) return;
                WS_BASC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
        if (WS_BASC_FLG != 'Y') {
            CEP.TRC(SCCGWA, "READ BAS TABLE");
            CITBAS_RD = new DBParm();
            CITBAS_RD.TableName = "CITBAS";
            IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "BAS INFO NOT FOUND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, CICACCU.RC);
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
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CICBASC.COUNT);
                if (CICBASC.COUNT < K_MAX_BASC_COUNT) {
                    CEP.TRC(SCCGWA, "WRITE BAS CASHE");
                    R000_WRITE_BASC();
                    if (pgmRtn) return;
                }
            }
    } //FROM #ENDIF
        }
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITPDM() throws IOException,SQLException,Exception {
        CITPDM_RD = new DBParm();
        CITPDM_RD.TableName = "CITPDM";
        IBS.READ(SCCGWA, CIRPDM, CITPDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "PDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PDM_NOTFND, CICACCU.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITPDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CDM_NOTFND, CICACCU.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FDM INFO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FDM_NOTFND, CICACCU.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITFDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_ACNO() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
