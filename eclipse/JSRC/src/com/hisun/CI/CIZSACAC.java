package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSACAC {
    int JIBS_tmp_int;
    DBParm BPTRGND_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACR_RD;
    DBParm CITACAC_RD;
    DBParm CITBAS_RD;
    DBParm CITOPAC_RD;
    brParm CITOPAC_BR = new brParm();
    boolean pgmRtn = false;
    String K_CITIZEN_CARD = "1203010101";
    short K_MAX_ACRC_COUNT = 2;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_AC_SEQ = 0;
    char WS_DEFAULT_FLG = ' ';
    String WS_AGR_NO = " ";
    int WS_I = 0;
    int WS_LIMIT_NUM = 0;
    char WS_CITIZEN_FLG = ' ';
    String WS_CI_NO = " ";
    String WS_ACR_PROD_CD = " ";
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    CIZSACAC_WS_SOLD_RGN WS_SOLD_RGN = new CIZSACAC_WS_SOLD_RGN();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACAC CIRACACO = new CIRACAC();
    CIRACAC CIRACACN = new CIRACAC();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIROPAC CIROPAC = new CIROPAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICCKLS CICCKLS = new CICCKLS();
    CICSFAC CICSFAC = new CICSFAC();
    CICPCDL CICPCDL = new CICPCDL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPRRGND BPRRGND = new BPRRGND();
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSACAC CICSACAC;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICSACAC CICSACAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSACAC = CICSACAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSACAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICSACAC.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSACAC.FUNC == 'A') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B050_CANCEL_ADD();
                if (pgmRtn) return;
            } else {
                B020_ADD_ACAC_INF();
                if (pgmRtn) return;
            }
        } else if (CICSACAC.FUNC == 'M') {
            B030_MOD_ACAC_INF();
            if (pgmRtn) return;
        } else if (CICSACAC.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                B060_CANCEL_DEL();
                if (pgmRtn) return;
            } else {
                B040_DEL_ACAC_INF();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
        if (CICSACAC.DATA.ACAC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ACAC-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_ACAC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICSACAC.DATA.PROD_CD);
        CEP.TRC(SCCGWA, CICSACAC.DATA.FRM_APP);
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPEN_DT);
        if (CICSACAC.DATA.AGR_NO.trim().length() == 0 
            || CICSACAC.DATA.PROD_CD.trim().length() == 0 
            || CICSACAC.DATA.FRM_APP.trim().length() == 0 
            || CICSACAC.DATA.OPN_BR == 0 
            || CICSACAC.DATA.OPEN_DT == 0) {
            CEP.TRC(SCCGWA, "LACK OF INFORMATION FOR ADD ACAC");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.FRM_APP);
        if (CICSACAC.DATA.FRM_APP.equalsIgnoreCase("DD")) {
            CICSACAC.DATA.NOSEQ_FLG = 'Y';
        }
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = CICSACAC.DATA.AGR_NO;
        CICCKLS.DATA.AP_TYPE = "002";
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_NO);
        if (CICSACAC.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSACAC.DATA.AGR_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
        }
        WS_ACR_PROD_CD = CIRACR.PROD_CD;
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_CI_NO = CIRACR.CI_NO;
            R000_CHECK_FIRST_AC();
            if (pgmRtn) return;
        }
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("2")) {
            WS_CI_NO = CIRACR.CI_NO;
            R000_CHECK_SECOND_AC();
            if (pgmRtn) return;
        }
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("3")) {
            WS_CI_NO = CIRACR.CI_NO;
            R000_CHECK_THIRD_AC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICSACAC.DATA.AGR_NO;
        T000_READ_CITACAC_DEFAULT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && WS_DEFAULT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "DEFAULT ACAC INF EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DEFAULT_ACAC_EXIST, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READ_CITACAC_EXIST();
        if (pgmRtn) return;
        if (CICSACAC.DATA.OWNER_BK == 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CICSACAC.DATA.OPN_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CICSACAC.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        }
        R000_DATA_TRANS_TO_TBL_ADD();
        if (pgmRtn) return;
        T000_WRITE_CITACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        CICSACAC.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_SEQ);
        if ((CICSACAC.DATA.MUL_FLG == '1' 
                || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
            R000_MUL_OPEN_ACAC();
            if (pgmRtn) return;
        } else if (CICSACAC.DATA.MUL_FLG == '2') {
            R000_MUL_OPEN_ACAC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_MOD_ACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        if (CIRACAC.ACAC_STS != '0') {
            CEP.TRC(SCCGWA, CIRACAC.ACAC_STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_STS_ERROR, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRACAC.CI_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        WS_ACR_PROD_CD = CIRACR.PROD_CD;
        if (CICSACAC.DATA.AGR_NO.trim().length() > 0) {
            WS_AGR_NO = CICSACAC.DATA.AGR_NO;
        } else {
            WS_AGR_NO = CIRACAC.AGR_NO;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        T000_READ_CITACAC_DEFAULT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACAC.FRM_APP);
        if (CIRACAC.FRM_APP.equalsIgnoreCase("DD")) {
            CICSACAC.DATA.NOSEQ_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        CEP.TRC(SCCGWA, WS_DEFAULT_FLG);
        if (CICSACAC.DATA.AGR_NO.trim().length() > 0) {
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            if (CIRACAC.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && WS_DEFAULT_FLG == 'Y' 
                && CICSACAC.DATA.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "DEFAULT ACAC INF EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DEFAULT_ACAC_EXIST, CICSACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRACAC.AGR_NO = CICSACAC.DATA.AGR_NO;
            if (CICSACAC.DATA.NOSEQ_FLG != 'Y') {
                IBS.init(SCCGWA, CIRACR);
                IBS.init(SCCGWA, CIRACRO);
                IBS.init(SCCGWA, CIRACRN);
                CIRACR.KEY.AGR_NO = CICSACAC.DATA.AGR_NO;
                T000_READ_CITACR_UPD();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
                CEP.TRC(SCCGWA, CIRACR.AC_SEQ);
                CIRACAC.AGR_SEQ = CIRACR.AC_SEQ + 1;
                CEP.TRC(SCCGWA, CIRACAC.AGR_SEQ);
                CEP.TRC(SCCGWA, CIRACR.AC_SEQ);
                CIRACR.AC_SEQ = CIRACAC.AGR_SEQ;
                CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                T000_REWRITE_CITACR();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                R000_WRT_HIS_PROC_ACR();
                if (pgmRtn) return;
            } else {
                CIRACAC.AGR_SEQ = 0;
            }
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.BV_NO);
        if (CICSACAC.DATA.BV_NO.trim().length() > 0) {
            CIRACAC.BV_NO = CICSACAC.DATA.BV_NO;
        } else {
            CEP.TRC(SCCGWA, CICSACAC.DATA.NOBV_FLG);
            if (CICSACAC.DATA.NOBV_FLG == 'Y') {
                CIRACAC.BV_NO = " ";
            }
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.PROD_CD);
        if (CICSACAC.DATA.PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = CICSACAC.DATA.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.SOLD_RGN);
            CEP.TRC(SCCGWA, BPCPQPRD.SOLD_BR);
            if (BPCPQPRD.SOLD_RGN.trim().length() > 0) {
                IBS.init(SCCGWA, BPRRGND);
                IBS.CPY2CLS(SCCGWA, BPCPQPRD.SOLD_RGN, WS_SOLD_RGN);
                BPRRGND.KEY.BNK = "043";
                BPRRGND.KEY.RGN_TYPE = WS_SOLD_RGN.WS_RGN_TYPE;
                BPRRGND.KEY.RGN_NO = WS_SOLD_RGN.WS_RGN_NO;
                BPRRGND.KEY.RGN_UNIT = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT = "0" + BPRRGND.KEY.RGN_UNIT;
                BPTRGND_RD = new DBParm();
                BPTRGND_RD.TableName = "BPTRGND";
                IBS.READ(SCCGWA, BPRRGND, BPTRGND_RD);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BR_NOT_IN_SOLD_RGN);
                }
            }
            CIRACAC.PROD_CD = CICSACAC.DATA.PROD_CD;
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) {
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 2 - 1) + "0" + CIRACAC.ACAC_CTL.substring(2 + 1 - 1);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        } else if (CICSACAC.DATA.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_DEFAULT_FLG == 'Y') {
                CEP.TRC(SCCGWA, "DEFAULT ACAC INF EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DEFAULT_ACAC_EXIST, CICSACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 2 - 1) + "1" + CIRACAC.ACAC_CTL.substring(2 + 1 - 1);
        } else {
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPN_BR);
        if (CICSACAC.DATA.OPN_BR != 0) {
            CIRACAC.OPN_BR = CICSACAC.DATA.OPN_BR;
            CEP.TRC(SCCGWA, CICSACAC.DATA.OWNER_BK);
            if (CICSACAC.DATA.OWNER_BK != 0) {
                CIRACAC.OWNER_BK = CICSACAC.DATA.OWNER_BK;
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CICSACAC.DATA.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CICSACAC.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
            }
        }
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0")) {
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 5 - 1) + "0" + CIRACAC.ACAC_CTL.substring(5 + 1 - 1);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        } else if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_CI_NO = CIRACAC.CI_NO;
            R000_CHECK_FIRST_AC();
            if (pgmRtn) return;
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 5 - 1) + "1" + CIRACAC.ACAC_CTL.substring(5 + 1 - 1);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        } else if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("2")) {
            WS_CI_NO = CIRACAC.CI_NO;
            R000_CHECK_SECOND_AC();
            if (pgmRtn) return;
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 5 - 1) + "2" + CIRACAC.ACAC_CTL.substring(5 + 1 - 1);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        } else if (CICSACAC.DATA.ACAC_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("3")) {
            WS_CI_NO = CIRACAC.CI_NO;
            R000_CHECK_THIRD_AC();
            if (pgmRtn) return;
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 5 - 1) + "3" + CIRACAC.ACAC_CTL.substring(5 + 1 - 1);
        } else {
        }
        CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0354800")) {
        }
        CICSACAC.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
    }
    public void B040_DEL_ACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        if (CIRACAC.ACAC_STS != '0') {
            CEP.TRC(SCCGWA, CIRACAC.ACAC_STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_STS_ERROR, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
        CIRACAC.ACAC_STS = '1';
        CIRACAC.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_CANCEL_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        if (CIRACAC.ACAC_STS != '0') {
            CEP.TRC(SCCGWA, CIRACAC.ACAC_STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_STS_ERROR, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
        CIRACAC.ACAC_STS = '2';
        CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        if (CICSACAC.DATA.MUL_FLG == '1') {
        } else if (CICSACAC.DATA.MUL_FLG == '2') {
        } else {
        }
    }
    public void B060_CANCEL_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        if (CIRACAC.ACAC_STS != '1') {
            CEP.TRC(SCCGWA, CIRACAC.ACAC_STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_STS_ERROR, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIRACAC.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (CIRACR.STS != '0') {
            CEP.TRC(SCCGWA, CIRACR.STS);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_IS_CLOSED, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
        CIRACAC.ACAC_STS = '0';
        CIRACAC.CLOSE_DT = 0;
        CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_TBL_ADD() throws IOException,SQLException,Exception {
        CIRACAC.KEY.ACAC_NO = CICSACAC.DATA.ACAC_NO;
        CIRACAC.AGR_NO = CICSACAC.DATA.AGR_NO;
        CIRACAC.CI_NO = CIRACR.CI_NO;
        if (CICSACAC.DATA.NOSEQ_FLG != 'Y') {
            IBS.init(SCCGWA, CIRACR);
            IBS.init(SCCGWA, CIRACRO);
            IBS.init(SCCGWA, CIRACRN);
            CIRACR.KEY.AGR_NO = CICSACAC.DATA.AGR_NO;
            T000_READ_CITACR_UPD();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
            CIRACAC.AGR_SEQ = CIRACR.AC_SEQ + 1;
            CIRACR.AC_SEQ = CIRACAC.AGR_SEQ;
            CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_CITACR();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRT_HIS_PROC_ACR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRACAC.AGR_SEQ);
        CIRACAC.BV_NO = CICSACAC.DATA.BV_NO;
        CIRACAC.CCY = CICSACAC.DATA.CCY;
        CIRACAC.CR_FLG = CICSACAC.DATA.CR_FLG;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = CICSACAC.DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.SOLD_RGN);
        CEP.TRC(SCCGWA, BPCPQPRD.SOLD_BR);
        if (BPCPQPRD.SOLD_RGN.trim().length() > 0) {
            IBS.init(SCCGWA, BPRRGND);
            IBS.CPY2CLS(SCCGWA, BPCPQPRD.SOLD_RGN, WS_SOLD_RGN);
            BPRRGND.KEY.BNK = "043";
            BPRRGND.KEY.RGN_TYPE = WS_SOLD_RGN.WS_RGN_TYPE;
            BPRRGND.KEY.RGN_NO = WS_SOLD_RGN.WS_RGN_NO;
            BPRRGND.KEY.RGN_UNIT = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT = "0" + BPRRGND.KEY.RGN_UNIT;
            BPTRGND_RD = new DBParm();
            BPTRGND_RD.TableName = "BPTRGND";
            IBS.READ(SCCGWA, BPRRGND, BPTRGND_RD);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BR_NOT_IN_SOLD_RGN);
            }
        }
        CIRACAC.PROD_CD = CICSACAC.DATA.PROD_CD;
        CIRACAC.AID = CICSACAC.DATA.AID;
        CIRACAC.ACAC_STS = '0';
        CIRACAC.ACAC_CTL = "" + 0;
        JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL = "0" + CIRACAC.ACAC_CTL;
        CIRACAC.ACAC_CTL = CICSACAC.DATA.ACAC_CTL;
        CIRACAC.FRM_APP = CICSACAC.DATA.FRM_APP;
        CIRACAC.OPN_BR = CICSACAC.DATA.OPN_BR;
        CIRACAC.OWNER_BK = CICSACAC.DATA.OWNER_BK;
        CIRACAC.OPEN_DT = CICSACAC.DATA.OPEN_DT;
        CIRACAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACAC.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRACAC.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void R000_CHECK_FIRST_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        BPRPRMT.KEY.CD = "05";
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_LIMIT_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
        if (WS_CITIZEN_FLG == 'Y' 
            || !WS_ACR_PROD_CD.equalsIgnoreCase(K_CITIZEN_CARD)) {
            IBS.init(SCCGWA, CICSFAC);
            CICSFAC.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZSFAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSFAC.DATA.FIRST_NUM);
            if (CICSFAC.DATA.FIRST_NUM >= WS_LIMIT_NUM) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FIRST_AC_OVER_LIMIT, CICSACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_SECOND_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        BPRPRMT.KEY.CD = "06";
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_LIMIT_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
        if (WS_CITIZEN_FLG == 'Y' 
            || !WS_ACR_PROD_CD.equalsIgnoreCase(K_CITIZEN_CARD)) {
            IBS.init(SCCGWA, CICSFAC);
            CICSFAC.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZSFAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSFAC.DATA.SECOND_NUM);
            if (CICSFAC.DATA.SECOND_NUM >= WS_LIMIT_NUM) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SECOND_AC_OVER_LIMIT, CICSACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_THIRD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, CICPCDL);
        BPCPRMR.FUNC = ' ';
        BPRPRMT.KEY.TYP = "CIACL";
        BPRPRMT.KEY.CD = "07";
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPCDL);
        CEP.TRC(SCCGWA, CICPCDL.NUM);
        WS_LIMIT_NUM = CICPCDL.NUM;
        WS_CITIZEN_FLG = CICPCDL.CITIZEN_FLG;
        if (WS_CITIZEN_FLG == 'Y' 
            || !WS_ACR_PROD_CD.equalsIgnoreCase(K_CITIZEN_CARD)) {
            IBS.init(SCCGWA, CICSFAC);
            CICSFAC.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZSFAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSFAC.DATA.THIRD_NUM);
            if (CICSFAC.DATA.THIRD_NUM >= WS_LIMIT_NUM) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_THIRD_AC_OVER_LIMIT, CICSACAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_MUL_OPEN_ACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIROPAC);
        CIROPAC.KEY.AC_TYP = "A2";
        CIROPAC.KEY.AC_NO = CICSACAC.DATA.ACAC_NO;
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
        CIROPAC.KEY.AC_TYP = "A2";
        CIROPAC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIROPAC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        T000_STARTBR_CITOPAC();
        if (pgmRtn) return;
        T000_READNEXT_CITOPAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = WS_I + 1;
            SCCTPCL.INP_AREA.BD_ECIF_AC0002.CI02_AC_LIST[WS_I-1].CI02_CALC_AC_NO = CIROPAC.KEY.AC_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            SCCTPCL.INP_AREA.BD_ECIF_AC0002.CI02_AC_LIST[WS_I-1].CI02_CALC_AC_NO = CICSACAC.DATA.AGR_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
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
        CIROPAC.KEY.AC_TYP = "A2";
        CIROPAC.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIROPAC.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        T000_STARTBR_CITOPAC_C();
        if (pgmRtn) return;
        T000_READNEXT_CITOPAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = WS_I + 1;
            SCCTPCL.INP_AREA.BD_ECIF_AC0002.CI02_AC_LIST[WS_I-1].CI02_CALC_AC_NO = CIROPAC.KEY.AC_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            SCCTPCL.INP_AREA.BD_ECIF_AC0002.CI02_AC_LIST[WS_I-1].CI02_CALC_AC_NO = CICSACAC.DATA.AGR_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
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
        BPCPNHIS.INFO.AC = CIRACAC.AGR_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "CIRACAC";
        BPCPNHIS.INFO.FMT_ID_LEN = 242;
        BPCPNHIS.INFO.CI_NO = CIRACAC.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACACO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACACN;
    }
    public void R000_WRT_HIS_PROC_ACR() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.AC = CIRACAC.AGR_NO;
        BPCPNHIS.INFO.CI_NO = CIRACAC.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
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
        CICACRC.DATA[WS_ACRC_I-1].AC_CNM = CIRACR.AC_CNM;
        CICACRC.DATA[WS_ACRC_I-1].AC_ENM = CIRACR.AC_ENM;
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
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_CIZSFAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-FIRST-AC", CICSFAC);
        if (CICSFAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFAC.RC);
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACR_UPD() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.upd = true;
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICSACAC.RC);
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
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICSACAC.RC);
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
    public void T000_READ_CITACAC_EXIST() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "ACAC INF EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_FND, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DEFAULT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DEFAULT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.WRITE(SCCGWA, CIRACAC, CITACAC_RD);
        CEP.TRC(SCCGWA, "WRITE CITACAC");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_CITACAC_UPD() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.upd = true;
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICSACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.REWRITE(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSACAC.RC);
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
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
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
