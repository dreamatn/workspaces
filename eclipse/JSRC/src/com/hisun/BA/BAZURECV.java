package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZURECV {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "FANCILITY CONTRACT STATUS CHANGE";
    String K_EVENT_CODE_CKIN = "CLCT";
    String WS_ERR_MSG = " ";
    long WS_SEQ_NO = 0;
    String WS_CTA_NO = " ";
    BAZURECV_WS_MAC_DATA WS_MAC_DATA = new BAZURECV_WS_MAC_DATA();
    String WS_CONTRACT_TYPE = " ";
    String WS_EVENT_CODE = " ";
    BAZURECV_WS_PRM_KEY WS_PRM_KEY = new BAZURECV_WS_PRM_KEY();
    long WS_CRE_JRN = 0;
    String WS_REMK = " ";
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BARCLCT BARCLCT = new BARCLCT();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BACUPROD BACUPROD = new BACUPROD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BACURECV BACURECV;
    public void MP(SCCGWA SCCGWA, BACURECV BACURECV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACURECV = BACURECV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZURECV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        B02_GET_PRODMO_PROC();
        if (pgmRtn) return;
        if (BARMST1.BILL_STS == '1') {
            B03_ADD_ACCOUTING();
            if (pgmRtn) return;
        }
        B05_UPDATE_MST1();
        if (pgmRtn) return;
        B06_WRITE_CLCT();
        if (pgmRtn) return;
        B07_ADD_TXDL();
        if (pgmRtn) return;
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        BACFMST1.FUNC = 'T';
        CEP.TRC(SCCGWA, BACURECV.DATA.ID_NO);
        BARMST1.BILL_NO = BACURECV.DATA.ID_NO;
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        CEP.TRC(SCCGWA, "00000000");
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        WS_MAC_DATA.WS_STA_DT = BARMST1.DRW_DT;
        WS_MAC_DATA.WS_DUE_DT = BARMST1.BILL_MAT_DT;
        if (BARMST1.OLD_DRWR_AC.trim().length() == 0) {
            WS_MAC_DATA.WS_MA_ACN = BARMST1.DRWR_AC;
        } else {
            WS_MAC_DATA.WS_MA_ACN = BARMST1.OLD_DRWR_AC;
        }
        WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT2 = BARMST1.BILL_AMT;
        WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT_1 = "" + WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT2;
        JIBS_tmp_int = WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT_1.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT_1 = "0" + WS_MAC_DATA.WS_MA_AMT3.WS_MA_AMT_1;
        WS_CTA_NO = BARMST1.CTA_NO;
        if (BARMST1.BILL_TYP != 'P') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILL_TYP_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BARMST1.BLK_STS == '0') {
            CEP.TRC(SCCGWA, BARMST1.BLK_STS);
            CEP.TRC(SCCGWA, "111");
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BLK_STS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACURECV.DATA.FUN_COD == '0') {
            if (BARMST1.BILL_STS != '1') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILLSTS_NOT_AVAI;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BACURECV.DATA.FUN_COD == '0') {
            IBS.init(SCCGWA, BARCLCT);
            IBS.init(SCCGWA, BACFCLCT);
            BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
            BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
            CEP.TRC(SCCGWA, BARCLCT.CNTR_NO);
            CEP.TRC(SCCGWA, BARCLCT.ACCT_CNT);
            BACFCLCT.FUNC = 'L';
            CEP.TRC(SCCGWA, "AAAAAAA");
            S000_CALL_BAZFCLCT_2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AAAAbA");
            if (BACFCLCT.RETURN_INFO == 'F' 
                && BARCLCT.CLCT_STS != '0') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BILL_UNAVI_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BACURECV.DATA.FUN_COD == '1') {
            BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
            BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
            BACFCLCT.FUNC = 'L';
            CEP.TRC(SCCGWA, "BBBBBBB");
            S000_CALL_BAZFCLCT_2();
            if (pgmRtn) return;
            if (BACFCLCT.RETURN_INFO == 'N') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_CLCT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (BARMST1.BILL_STS != '1' 
                    && BARMST1.BILL_STS != '3') {
                    WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BILLSTS_NOT_AVAI;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BACURECV.DATA.FUN_COD == '0') {
            if (BACURECV.DATA.DQ_DT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_DQ_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BACURECV.DATA.DQ_DT != BARMST1.BILL_MAT_DT) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MATU_DT_WRONG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BACURECV.DATA.CD_AMT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_BIAMT_BG_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BACURECV.DATA.CD_AMT);
            CEP.TRC(SCCGWA, BARMST1.BILL_AMT);
            if (BACURECV.DATA.CD_AMT != BARMST1.BILL_AMT) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_CD_AMT_UNMATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BACURECV.DATA.ENCR.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCHMPW);
                if (BACURECV.DATA.ID_NO == null) BACURECV.DATA.ID_NO = "";
                JIBS_tmp_int = BACURECV.DATA.ID_NO.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) BACURECV.DATA.ID_NO += " ";
                WS_MAC_DATA.WS_MA_BILL = BACURECV.DATA.ID_NO.substring(9 - 1, 9 + 8 - 1);
                SCCHMPW.INP_DATA.SERV_ID = "EEA4";
                SCCHMPW.INP_DATA.MAC_FLG = '0';
                SCCHMPW.INP_DATA.CHECK_DATE = WS_MAC_DATA.WS_STA_DT;
                SCCHMPW.INP_DATA.MAC_DA = IBS.CLS2CPY(SCCGWA, WS_MAC_DATA);
                SCCHMPW.INP_DATA.MAC_LEN = 75;
                S000_CALL_SCZHMPW();
                if (pgmRtn) return;
                if (!BACURECV.DATA.ENCR.equalsIgnoreCase(SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW)) {
                    WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B02_GET_PRODMO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCGWY);
        if (BARMST1.PROD_CD.equalsIgnoreCase("RMB00453")) {
            BPCSCGWY.CHANG_WAY = " ";
        } else {
            BPCSCGWY.CHANG_WAY = "P025";
        }
        BPCSCGWY.PRDT_CODE = BARMST1.PROD_CD;
        BPCSCGWY.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        S000_CALL_BPZSCGWY();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSCGWY.PARM_CODE, WS_PRM_KEY);
        CEP.TRC(SCCGWA, WS_PRM_KEY);
        IBS.init(SCCGWA, BACUPROD);
        BACUPROD.FUNC = 'I';
        BACUPROD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BACUPROD.KEY.TYPE = "PRDPR";
        S000_CALL_BAZUPROD();
        if (pgmRtn) return;
    }
    public void B03_ADD_ACCOUTING() throws IOException,SQLException,Exception {
        WS_CONTRACT_TYPE = "CLBA";
        WS_EVENT_CODE = "CLCT";
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[1-1] = WS_CONTRACT_TYPE;
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = WS_CONTRACT_TYPE;
        BPCPOEWA.DATA.PROD_CODE = BARMST1.PROD_CD;
        BPCPOEWA.DATA.AC_NO = WS_CTA_NO;
        BPCPOEWA.DATA.EVENT_CODE = WS_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = BARMST1.ACPT_BR;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BARMST1.BILL_AMT;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B05_UPDATE_MST1() throws IOException,SQLException,Exception {
        BACFMST1.FUNC = 'U';
        if (BACURECV.DATA.SPPM_FLG == 'Y') {
            BARMST1.PAY_MTH = BACURECV.DATA.PAY_MTH;
            if (BACURECV.DATA.PAY_CHNL != ' ') {
                BARMST1.PAY_CHNL = BACURECV.DATA.PAY_CHNL;
            }
        }
        CEP.TRC(SCCGWA, BACURECV.DATA.SPPM_FLG);
        if (BACURECV.DATA.SPPM_FLG == 'N') {
            BARMST1.PAY_MTH = BACURECV.DATA.PAY_MTH;
            if (BACURECV.DATA.PAY_CHNL != ' ') {
                BARMST1.PAY_CHNL = BACURECV.DATA.PAY_CHNL;
            }
        }
        if (BARMST1.BILL_STS != '3') {
            BARMST1.BILL_STS = '3';
        }
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B06_WRITE_CLCT() throws IOException,SQLException,Exception {
        R000_GET_SEQ_PROCESS();
        if (pgmRtn) return;
        if (BACURECV.DATA.FUN_COD == '0') {
            R100_TRANS_DATA_TO_BARCLCT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CCCCCC");
            S000_CALL_BAZFCLCT_1();
            if (pgmRtn) return;
        }
        if (BACURECV.DATA.FUN_COD == '1') {
            if (BACURECV.DATA.ID_NO.trim().length() == 0) {
                BACURECV.DATA.ID_NO = BARCLCT.BILL_NO;
            }
            if (BACURECV.DATA.C_BR == 0) {
                BACURECV.DATA.C_BR = BARCLCT.CLCT_BR;
            }
            if (BACURECV.DATA.C_REC_DT == 0) {
                BACURECV.DATA.C_REC_DT = BARCLCT.CLCT_REC_DT;
            }
            if (BACURECV.DATA.C_PYE_DT == 0) {
                BACURECV.DATA.C_PYE_DT = BARCLCT.CLCT_PYE_DT;
            }
            if (BACURECV.DATA.C_PYE_AC.trim().length() == 0) {
                BACURECV.DATA.C_PYE_AC = BARCLCT.CLCT_PYE_AC;
            }
            if (BACURECV.DATA.C_PYE_NM.trim().length() == 0) {
                BACURECV.DATA.C_PYE_NM = BARCLCT.CLCT_PYE_NM;
            }
            if (BACURECV.DATA.C_PYE_BR.trim().length() == 0) {
                BACURECV.DATA.C_PYE_BR = BARCLCT.CLCT_PYE_BR;
            }
            if (BACURECV.DATA.C_PYE_BN.trim().length() == 0) {
                BACURECV.DATA.C_PYE_BN = BARCLCT.CLCT_PYE_BN;
            }
            if (BACURECV.DATA.SPPM_FLG == ' ') {
                BACURECV.DATA.SPPM_FLG = BARCLCT.SPPM_FLG;
            }
            if (BACURECV.DATA.PAY_MTH == ' ') {
                BACURECV.DATA.PAY_MTH = BARCLCT.PAY_MTH;
            }
            if (BACURECV.DATA.REMK.trim().length() == 0) {
                BACURECV.DATA.REMK = BARCLCT.REMK;
            }
            R100_TRANS_DATA_TO_BARCLCT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DDDDDDD");
            S000_CALL_BAZFCLCT_1();
            if (pgmRtn) return;
        }
        BACURECV.DATA.CLCT_STS = BARCLCT.CLCT_STS;
    }
    public void B07_ADD_TXDL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ADD TXDLL");
        IBS.init(SCCGWA, BARTXDL);
        IBS.init(SCCGWA, BACFTXDL);
        BACFTXDL.FUNC = 'A';
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BACURECV.DATA.ID_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        BARTXDL.REMK = BACURECV.DATA.REMK;
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BARTXDL.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BARTXDL.TS = "0" + BARTXDL.TS;
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void R000_GET_SEQ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        WS_SEQ_NO = 0;
        BACFCLCT.FUNC = 'L';
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        CEP.TRC(SCCGWA, "EEEEEEE");
        S000_CALL_BAZFCLCT_2();
        if (pgmRtn) return;
        WS_SEQ_NO = BARCLCT.SEQ + 1;
    }
    public void R100_TRANS_DATA_TO_BARCLCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFCLCT);
        IBS.init(SCCGWA, BARCLCT);
        BACFCLCT.FUNC = 'A';
        BARCLCT.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARCLCT.SEQ = (short) WS_SEQ_NO;
        BARCLCT.BILL_NO = BACURECV.DATA.ID_NO;
        BARCLCT.ACPT_BR = BARMST1.ACPT_BR;
        BARCLCT.CLCT_STS = '1';
        BARCLCT.BILL_STS = BARMST1.BILL_STS;
        BARCLCT.SPPM_FLG = BACURECV.DATA.SPPM_FLG;
        BARCLCT.PAY_MTH = BACURECV.DATA.PAY_MTH;
        BARCLCT.PAY_CHNL = BARMST1.PAY_CHNL;
        BARCLCT.WO_PAY_FLG = 'N';
        BARCLCT.CLCT_BR = BACURECV.DATA.C_BR;
        BARCLCT.CLCT_REC_DT = BACURECV.DATA.C_REC_DT;
        BARCLCT.CLCT_PYE_AC = BACURECV.DATA.C_PYE_AC;
        BARCLCT.CLCT_PYE_DT = BACURECV.DATA.C_PYE_DT;
        BARCLCT.CLCT_PYE_NM = BACURECV.DATA.C_PYE_NM;
        BARCLCT.CLCT_PYE_BR = BACURECV.DATA.C_PYE_BR;
        BARCLCT.CLCT_PYE_BN = BACURECV.DATA.C_PYE_BR;
        BARCLCT.REMK = BACURECV.DATA.REMK;
    }
    public void S000_CALL_BAZUPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-PROD-MAINTAIN", BACUPROD);
        if (BACUPROD.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUPROD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSCGWY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-CGWY", BPCSCGWY);
        if (BPCSCGWY.RC.RC_CODE != 0) {
            BACURECV.RC.RC_APP = BPCSCGWY.RC.RC_MMO;
            BACURECV.RC.RC_CODE = BPCSCGWY.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        CEP.TRC(SCCGWA, BARMST1.BILL_STS);
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        CEP.TRC(SCCGWA, BACFMST1.RC);
        if (BACFMST1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "ERROR IN MST1");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT_1() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
        if (BACFCLCT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFCLCT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT_2() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        if ((SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("009040") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001024") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001014"))) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_PSW_NOT_TRUE_LEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001020")) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_IDEAL_NOT_TRUE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
                    WS_ERR_MSG = SCCHMPW.OUT_INFO.ERR_CODE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
