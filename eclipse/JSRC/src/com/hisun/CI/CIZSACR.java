package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSACR {
    int JIBS_tmp_int;
    int ACR_AC_CNM_LEN;
    int ACR_AC_ENM_LEN;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITACAC_RD;
    brParm CITACRL_BR = new brParm();
    DBParm CITOPAC_RD;
    brParm CITOPAC_BR = new brParm();
    boolean pgmRtn = false;
    String K_GROWTH_ACCOUNT = "9520000001";
    short K_MAX_ACRC_COUNT = 2;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICCKLS CICCKLS = new CICCKLS();
    CICSOEC CICSOEC = new CICSOEC();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CIROPAC CIROPAC = new CIROPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSACR CICSACR;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICSACR CICSACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSACR = CICSACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICSACR.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSACR.FUNC == 'A') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B050_CANCEL_ADD();
                if (pgmRtn) return;
            } else {
                B020_ADD_ACR_INF();
                if (pgmRtn) return;
            }
        } else if (CICSACR.FUNC == 'M') {
            B030_MOD_ACR_INF();
            if (pgmRtn) return;
        } else if (CICSACR.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B060_CANCEL_DEL();
                if (pgmRtn) return;
            } else {
                B040_DEL_ACR_INF();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "FUNC CODE INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        if (CICSACR.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AC MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.CI_NO);
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        if (CICSACR.DATA.CI_NO.trim().length() > 0 
            && !CICSACR.DATA.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSACR.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            if (CIRBAS.VER_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_NOT_VERIFIED, CIRBAS.KEY.CI_NO);
            }
            CEP.TRC(SCCGWA, CIRBAS.CI_ATTR);
            if (CIRBAS.CI_ATTR != '1' 
                && CIRBAS.CI_ATTR != '3' 
                && CIRBAS.CI_ATTR != '6') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ATTR_INPUT_ERR);
            }
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.OPEN_DT);
        if (CICSACR.DATA.OPEN_DT != 0 
            && CICSACR.DATA.OPEN_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "OPEN-DT INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.ENTY_TYP);
        if (CICSACR.DATA.ENTY_TYP != ' ' 
            && CICSACR.DATA.ENTY_TYP != '1' 
            && CICSACR.DATA.ENTY_TYP != '2' 
            && CICSACR.DATA.ENTY_TYP != '3' 
            && CICSACR.DATA.ENTY_TYP != '4' 
            && CICSACR.DATA.ENTY_TYP != '5') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
    }
    public void B020_ADD_ACR_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACR.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICSACR.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSACR.DATA.FRM_APP);
        CEP.TRC(SCCGWA, CICSACR.DATA.SHOW_FLG);
        CEP.TRC(SCCGWA, CICSACR.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICSACR.DATA.OPEN_DT);
        if (CICSACR.DATA.ENTY_TYP == ' ' 
            || CICSACR.DATA.CI_NO.trim().length() == 0 
            || CICSACR.DATA.FRM_APP.trim().length() == 0 
            || CICSACR.DATA.SHOW_FLG == ' ' 
            || CICSACR.DATA.OPN_BR == 0 
            || CICSACR.DATA.OPEN_DT == 0) {
            CEP.TRC(SCCGWA, "LACK OF INFORMATION FOR ADD AC");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        if (CICSACR.DATA.CI_NO.trim().length() > 0 
            && !CICSACR.DATA.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CICCKLS);
            CICCKLS.DATA.CI_NO = CICSACR.DATA.CI_NO;
            CICCKLS.DATA.AP_TYPE = "001";
            S000_CALL_CIZCKLS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICCKLS);
            CICCKLS.DATA.ID_TYPE = CIRBAS.ID_TYPE;
            CICCKLS.DATA.ID_NO = CIRBAS.ID_NO;
            CICCKLS.DATA.CI_NM = CIRBAS.CI_NM;
            CICCKLS.DATA.AP_TYPE = "001";
            S000_CALL_CIZCKLS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.PROD_CD);
        if (CICSACR.DATA.PROD_CD.equalsIgnoreCase(K_GROWTH_ACCOUNT)) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.CI_NO = CICSACR.DATA.CI_NO;
            CIRACR.PROD_CD = K_GROWTH_ACCOUNT;
            T000_READ_CITACR_GROWTH();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AGR-NO EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_FND, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        if (CICSACR.DATA.OWNER_BK == 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CICSACR.DATA.OPN_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CICSACR.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        }
        R000_DATA_TRANS_TO_TBL_ADD();
        if (pgmRtn) return;
        T000_WRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        if ((CICSACR.CTL_FLG.MUL_FLG == '1' 
                || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
            R000_MUL_OPEN_ACR();
            if (pgmRtn) return;
        } else if (CICSACR.CTL_FLG.MUL_FLG == '2') {
            R000_MUL_OPEN_ACR();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_MOD_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        if (CIRACR.STS != '0') {
            CEP.TRC(SCCGWA, CIRACR.STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSACR.DATA.AC_CNM);
        if (CICSACR.DATA.AC_CNM.trim().length() > 0) {
            if (CIRBAS.CI_TYP == '2' 
                || CIRBAS.CI_TYP == '3') {
                CIRACR.AC_CNM = CICSACR.DATA.AC_CNM;
                ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
            } else {
                CEP.TRC(SCCGWA, "THE AC OF PERSONAL CI CANT MOD NAME");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_NAME_CANT_MOD, CICSACR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.AC_ENM);
        if (CICSACR.DATA.AC_ENM.trim().length() > 0) {
            if (CIRBAS.CI_TYP == '2' 
                || CIRBAS.CI_TYP == '3') {
                CIRACR.AC_ENM = CICSACR.DATA.AC_ENM;
                ACR_AC_ENM_LEN = CIRACR.AC_ENM.length();
            } else {
                CEP.TRC(SCCGWA, "THE AC OF PERSONAL CI CANT MOD NAME");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_NAME_CANT_MOD, CICSACR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.PROD_CD);
        if (CICSACR.DATA.PROD_CD.trim().length() > 0) {
            CIRACR.PROD_CD = CICSACR.DATA.PROD_CD;
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.CNTRCT_TYP);
        if (CICSACR.DATA.CNTRCT_TYP.trim().length() > 0) {
            CIRACR.CNTRCT_TYP = CICSACR.DATA.CNTRCT_TYP;
        }
        CEP.TRC(SCCGWA, CICSACR.CTL_FLG.RSAC_FLG);
        if (CICSACR.CTL_FLG.RSAC_FLG != ' ') {
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
            JIBS_tmp_str[0] = "" + CICSACR.CTL_FLG.RSAC_FLG;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CIRACR.STSW = CIRACR.STSW.substring(0, 6 - 1) + JIBS_tmp_str[0] + CIRACR.STSW.substring(6 + 1 - 1);
        }
        CEP.TRC(SCCGWA, CICSACR.DATA.OPN_BR);
        if (CICSACR.DATA.OPN_BR != 0) {
            CIRACR.OPN_BR = CICSACR.DATA.OPN_BR;
            CEP.TRC(SCCGWA, CICSACR.DATA.OWNER_BK);
            if (CICSACR.DATA.OWNER_BK != 0) {
                CIRACR.OWNER_BK = CICSACR.DATA.OWNER_BK;
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CICSACR.DATA.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CICSACR.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
            }
        }
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DEL_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        if (CIRACR.STS != '0') {
            CEP.TRC(SCCGWA, CIRACR.STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_CANCEL_CHECK();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.STS = '1';
        CIRACR.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_CANCEL_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        if (CIRACR.STS != '0') {
            CEP.TRC(SCCGWA, CIRACR.STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.STS = '2';
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        if (CICSACR.CTL_FLG.MUL_FLG == '1') {
        } else if (CICSACR.CTL_FLG.MUL_FLG == '2') {
        } else {
        }
    }
    public void B060_CANCEL_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        if (CIRACR.STS != '1') {
            CEP.TRC(SCCGWA, CIRACR.STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_STS_ERROR, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.STS = '0';
        CIRACR.CLOSE_DT = 0;
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_TBL_ADD() throws IOException,SQLException,Exception {
        CIRACR.KEY.AGR_NO = CICSACR.DATA.AGR_NO;
        CIRACR.ENTY_TYP = CICSACR.DATA.ENTY_TYP;
        if (CICSACR.CTL_FLG.EXP_FLG == 'Y') {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = CICSACR.DATA.CI_NO;
            S000_CALL_CIZSOEC();
            if (pgmRtn) return;
            CIRACR.CI_NO = CICSOEC.DATA.SPECIAL_CI_NO;
            CICSACR.DATA.CI_NO = CIRACR.CI_NO;
            CICSACR.DATA.AC_CNM = "南商（中国）保证�?";
        } else {
            CIRACR.CI_NO = CICSACR.DATA.CI_NO;
        }
        CIRACR.STS = '0';
        CIRACR.STSW = CICSACR.DATA.STSW;
        CIRACR.PROD_CD = CICSACR.DATA.PROD_CD;
        CIRACR.CNTRCT_TYP = CICSACR.DATA.CNTRCT_TYP;
        CIRACR.FRM_APP = CICSACR.DATA.FRM_APP;
        CIRACR.CCY = CICSACR.DATA.CCY;
        CIRACR.SHOW_FLG = CICSACR.DATA.SHOW_FLG;
        CIRACR.SMS_FLG = 'N';
        if (CIRBAS.CI_TYP != '1') {
            CIRACR.AC_CNM = CICSACR.DATA.AC_CNM;
            CIRACR.AC_ENM = CICSACR.DATA.AC_ENM;
            ACR_AC_CNM_LEN = CIRACR.AC_CNM.length();
            ACR_AC_ENM_LEN = CIRACR.AC_ENM.length();
        }
        CIRACR.OPN_BR = CICSACR.DATA.OPN_BR;
        CIRACR.OPEN_DT = CICSACR.DATA.OPEN_DT;
        CIRACR.OWNER_BK = CICSACR.DATA.OWNER_BK;
        CIRACR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_MUL_OPEN_ACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIROPAC);
        CIROPAC.KEY.AC_TYP = "A1";
        CIROPAC.KEY.AC_NO = CICSACR.DATA.AGR_NO;
        CIROPAC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIROPAC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            CIROPAC.AP_TYPE = GWA_SC_AREA.BSP_APTYPE;
            CIROPAC.AP_BATNO = GWA_SC_AREA.BSP_BATNO;
        }
        CIROPAC.DEAL_FLG = '1';
        T000_WRITE_CITOPAC();
        if (pgmRtn) return;
    }
    public void R000_MUL_TRANS_TO_ECIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIROPAC);
        IBS.init(SCCGWA, SCCTPCL);
        CIROPAC.KEY.AC_TYP = "A1";
        CIROPAC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIROPAC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        T000_STARTBR_CITOPAC();
        if (pgmRtn) return;
        T000_READNEXT_CITOPAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = WS_I + 1;
            SCCTPCL.INP_AREA.BD_ECIF_AC0001.CI01_ACCT_LIST[WS_I-1].CI01_ACCT_NO = CIROPAC.KEY.AC_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            CIROPAC.DEAL_FLG = '1';
            T000_REWRITE_CITOPAC();
            if (pgmRtn) return;
            if (WS_I == 50) {
                WS_I = 0;
                IBS.init(SCCGWA, SCCTPCL);
            }
            T000_READNEXT_CITOPAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITOPAC();
        if (pgmRtn) return;
    }
    public void R000_MUL_TRANS_TO_ECIF_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIROPAC);
        IBS.init(SCCGWA, SCCTPCL);
        CIROPAC.KEY.AC_TYP = "A1";
        CIROPAC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIROPAC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        T000_STARTBR_CITOPAC_C();
        if (pgmRtn) return;
        T000_READNEXT_CITOPAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = WS_I + 1;
            SCCTPCL.INP_AREA.BD_ECIF_AC0001.CI01_ACCT_LIST[WS_I-1].CI01_ACCT_NO = CIROPAC.KEY.AC_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            CIROPAC.DEAL_FLG = '2';
            T000_REWRITE_CITOPAC();
            if (pgmRtn) return;
            if (WS_I == 50) {
                WS_I = 0;
                IBS.init(SCCGWA, SCCTPCL);
            }
            T000_READNEXT_CITOPAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITOPAC();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = CICSACR.DATA.AGR_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.CI_NO = CICSACR.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
    }
    public void R000_CANCEL_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICSACR.DATA.AGR_NO;
        T000_READ_CITACAC_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "账号下存在未�?户记账账�?");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_NOT_CANCEL, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CICSACR.DATA.AGR_NO;
        T000_STARTBR_CITACRL();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
            if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("01")) {
                CEP.TRC(SCCGWA, "AC REL 01 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_01_EXIST);
            } else if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("04")) {
                CEP.TRC(SCCGWA, "AC REL 04 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_04_EXIST);
            } else if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("05")) {
                CEP.TRC(SCCGWA, "AC REL 05 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_05_EXIST);
            } else if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("06")) {
                CEP.TRC(SCCGWA, "AC REL 06 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_06_EXIST);
            } else if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("07")) {
                CEP.TRC(SCCGWA, "AC REL 07 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_07_EXIST);
            } else if (CIRACRL.KEY.AC_REL.equalsIgnoreCase("11")) {
                CEP.TRC(SCCGWA, "AC REL 11 EXIST");
                CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_AC_REL_11_EXIST);
            } else {
            }
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        CEP.ERR(SCCGWA);
    }
    public void R000_DATA_TRANS_TO_ECIF() throws IOException,SQLException,Exception {
    }
    public void R000_REWRITE_ACRC() throws IOException,SQLException,Exception {
        CICACRC.DATA[WS_ACRC_I-1].AGR_NO = CIRACR.KEY.AGR_NO;
        CICACRC.DATA[WS_ACRC_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
        CICACRC.DATA[WS_ACRC_I-1].CI_NO = CIRACR.CI_NO;
        CICACRC.DATA[WS_ACRC_I-1].STS = CIRACR.STS;
        CICACRC.DATA[WS_ACRC_I-1].STSW = CIRACR.STSW;
        CICACRC.DATA[WS_ACRC_I-1].PROD_CD = CIRACR.PROD_CD;
        CICACRC.DATA[WS_ACRC_I-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACRC.DATA[WS_ACRC_I-1].FRM_APP = CIRACR.FRM_APP;
        CICACRC.DATA[WS_ACRC_I-1].CCY = CIRACR.CCY;
        CICACRC.DATA[WS_ACRC_I-1].SHOW_FLG = CIRACR.SHOW_FLG;
        CICACRC.DATA[WS_ACRC_I-1].SMS_FLG = CIRACR.SMS_FLG;
        if (CIRACR.AC_CNM == null) CIRACR.AC_CNM = "";
        JIBS_tmp_int = CIRACR.AC_CNM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRACR.AC_CNM += " ";
        CICACRC.DATA[WS_ACRC_I-1].AC_CNM = CIRACR.AC_CNM.substring(0, ACR_AC_CNM_LEN);
        if (CIRACR.AC_ENM == null) CIRACR.AC_ENM = "";
        JIBS_tmp_int = CIRACR.AC_ENM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRACR.AC_ENM += " ";
        CICACRC.DATA[WS_ACRC_I-1].AC_ENM = CIRACR.AC_ENM.substring(0, ACR_AC_ENM_LEN);
        CICACRC.DATA[WS_ACRC_I-1].AC_SEQ = CIRACR.AC_SEQ;
        CICACRC.DATA[WS_ACRC_I-1].OPN_BR = CIRACR.OPN_BR;
        CICACRC.DATA[WS_ACRC_I-1].OPEN_DT = CIRACR.OPEN_DT;
        CICACRC.DATA[WS_ACRC_I-1].CLOSE_DT = CIRACR.CLOSE_DT;
        CICACRC.DATA[WS_ACRC_I-1].OWNER_BK = CIRACR.OWNER_BK;
        CICACRC.DATA[WS_ACRC_I-1].CRT_TLR = CIRACR.CRT_TLR;
        CICACRC.DATA[WS_ACRC_I-1].CRT_BR = CIRACR.CRT_BR;
        CICACRC.DATA[WS_ACRC_I-1].CRT_DT = CIRACR.CRT_DT;
        CICACRC.DATA[WS_ACRC_I-1].UPD_TLR = CIRACR.UPD_TLR;
        CICACRC.DATA[WS_ACRC_I-1].UPD_BR = CIRACR.UPD_BR;
        CICACRC.DATA[WS_ACRC_I-1].UPD_DT = CIRACR.UPD_DT;
        CICACRC.DATA[WS_ACRC_I-1].TS = CIRACR.TS;
        CEP.TRC(SCCGWA, WS_ACRC_I);
        CEP.TRC(SCCGWA, CICACRC.DATA[WS_ACRC_I-1].AGR_NO);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSACR.RC);
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
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACR_GROWTH() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.eqWhere = "CI_NO , PROD_CD";
        CITACR_RD.where = "STS = '0'";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GROWTH_FOUND, CICSACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
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
    public void T000_WRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.WRITE(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACR_UPD() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.upd = true;
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSACR.RC);
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
    public void T000_REWRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.REWRITE(SCCGWA, CIRACR, CITACR_RD);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_ACRC_I < CICACRC.COUNT 
            && WS_ACRC_FLG != 'Y') {
            WS_ACRC_I = (short) (WS_ACRC_I + 1);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            if (CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICACRC.DATA[WS_ACRC_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "REWRITE ACR CASHE");
                R000_REWRITE_ACRC();
                if (pgmRtn) return;
                WS_ACRC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
    }
    public void T000_READ_CITACAC_FIRST() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "ACAC_STS = '0' "
            + "AND AID = ' '";
        CITACAC_RD.fst = true;
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_STARTBR_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        CITACRL_BR.rp.where = "REL_STS = '0' "
            + "AND AC_REL IN ( '01' , '04' , '05' , '06' , '07' , '11' , '13' )";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void T000_WRITE_CITOPAC() throws IOException,SQLException,Exception {
        CITOPAC_RD = new DBParm();
        CITOPAC_RD.TableName = "CITOPAC";
        IBS.WRITE(SCCGWA, CIROPAC, CITOPAC_RD);
    }
    public void T000_STARTBR_CITOPAC() throws IOException,SQLException,Exception {
        CITOPAC_BR.rp = new DBParm();
        CITOPAC_BR.rp.TableName = "CITOPAC";
        CITOPAC_BR.rp.eqWhere = "AC_TYP";
        CITOPAC_BR.rp.where = "DEAL_FLG = '0' "
            + "AND AC_DATE = :WS_AC_DATE "
            + "AND JRN_NO = :WS_JRN_NO";
        CITOPAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIROPAC, this, CITOPAC_BR);
    }
    public void T000_STARTBR_CITOPAC_C() throws IOException,SQLException,Exception {
        CITOPAC_BR.rp = new DBParm();
        CITOPAC_BR.rp.TableName = "CITOPAC";
        CITOPAC_BR.rp.eqWhere = "AC_TYP";
        CITOPAC_BR.rp.where = "DEAL_FLG = '1' "
            + "AND AC_DATE = :WS_AC_DATE "
            + "AND JRN_NO = :WS_JRN_NO";
        CITOPAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIROPAC, this, CITOPAC_BR);
    }
    public void T000_REWRITE_CITOPAC() throws IOException,SQLException,Exception {
        CITOPAC_RD = new DBParm();
        CITOPAC_RD.TableName = "CITOPAC";
        IBS.REWRITE(SCCGWA, CIROPAC, CITOPAC_RD);
    }
    public void T000_READNEXT_CITOPAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIROPAC, this, CITOPAC_BR);
    }
    public void T000_ENDBR_CITOPAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITOPAC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
