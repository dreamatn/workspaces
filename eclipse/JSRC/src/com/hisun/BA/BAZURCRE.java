package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZURCRE {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "FANCILITY CONTRACT STATUS CHANGE";
    String K_EVENT_CODE_CKIN = "CLCTRT";
    String WS_ERR_MSG = " ";
    long WS_SEQ_NO = 0;
    char WS_SPPM_FLG = ' ';
    char WS_PAY_MTH = ' ';
    char WS_WO_PAY_FLG = ' ';
    int WS_WO_PAY_RGT_DT = 0;
    String WS_JDGM_NO = " ";
    int WS_CLCT_BR = 0;
    int WS_C_REC_DT = 0;
    int WS_C_PYE_DT = 0;
    String WS_C_PYE_AC = " ";
    String WS_C_PYE_NM = " ";
    String WS_C_PYE_BR = " ";
    String WS_C_PYE_BN = " ";
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    String WS_CONTRACT_TYPE = " ";
    String WS_EVENT_CODE = " ";
    String WS_CTA_NO = " ";
    BAZURCRE_WS_PRM_KEY WS_PRM_KEY = new BAZURCRE_WS_PRM_KEY();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BARCLCT BARCLCT = new BARCLCT();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BACUPROD BACUPROD = new BACUPROD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BACURCRE BACURCRE;
    public void MP(SCCGWA SCCGWA, BACURCRE BACURCRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACURCRE = BACURCRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZURCRE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_GET_INFORMATION();
        if (pgmRtn) return;
        B020_TRANCHE_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_GET_INFORMATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BACURCRE.DATA.ID_NO;
        CEP.TRC(SCCGWA, "AAAAAA");
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        WS_CTA_NO = BARMST1.CTA_NO;
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BACFCLCT.FUNC = 'L';
        S000_CALL_BAZFCLCT_1();
        if (pgmRtn) return;
        WS_SPPM_FLG = BARCLCT.SPPM_FLG;
        WS_PAY_MTH = BARCLCT.PAY_MTH;
        WS_WO_PAY_FLG = BARCLCT.WO_PAY_FLG;
        WS_WO_PAY_RGT_DT = BARCLCT.WO_PAY_RGT_DT;
        WS_JDGM_NO = BARCLCT.JDGM_NO;
        WS_CLCT_BR = BARCLCT.CLCT_BR;
        WS_C_REC_DT = BARCLCT.CLCT_REC_DT;
        WS_C_PYE_DT = BARCLCT.CLCT_PYE_DT;
        WS_C_PYE_AC = BARCLCT.CLCT_PYE_AC;
        WS_C_PYE_NM = BARCLCT.CLCT_PYE_NM;
        WS_C_PYE_BR = BARCLCT.CLCT_PYE_BR;
        WS_C_PYE_BN = BARCLCT.CLCT_PYE_BN;
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        B021_GET_PRODMO_PROC();
        if (pgmRtn) return;
        if (BARMST1.BILL_STS == '3') {
            B022_ADD_ACCOUTING();
            if (pgmRtn) return;
        }
        if (BARMST1.BILL_STS == '3') {
            B024_REWRITE_BATMST1();
            if (pgmRtn) return;
        }
        B025_WRITE_BATCLCT();
        if (pgmRtn) return;
        B026_WRITE_BATTXDL();
        if (pgmRtn) return;
    }
    public void B021_GET_PRODMO_PROC() throws IOException,SQLException,Exception {
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
    public void B022_ADD_ACCOUTING() throws IOException,SQLException,Exception {
        WS_CONTRACT_TYPE = "CLBA";
        WS_EVENT_CODE = "CLCTRT";
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
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BARMST1.BILL_AMT;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B024_REWRITE_BATMST1() throws IOException,SQLException,Exception {
        BACFMST1.FUNC = 'U';
        BARMST1.BILL_STS = '1';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B025_WRITE_BATCLCT() throws IOException,SQLException,Exception {
        B27_GET_SEQ_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        BACFCLCT.FUNC = 'A';
        BARCLCT.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARCLCT.SEQ = (short) WS_SEQ_NO;
        BARCLCT.BILL_NO = BACURCRE.DATA.ID_NO;
        BARCLCT.ACPT_BR = BARMST1.ACPT_BR;
        BARCLCT.CLCT_STS = '0';
        BARCLCT.BILL_STS = BARMST1.BILL_STS;
        BARCLCT.SPPM_FLG = WS_SPPM_FLG;
        BARCLCT.PAY_MTH = WS_PAY_MTH;
        BARCLCT.PAY_CHNL = BARMST1.PAY_CHNL;
        BARCLCT.WO_PAY_FLG = WS_WO_PAY_FLG;
        BARCLCT.WO_PAY_RGT_DT = WS_WO_PAY_RGT_DT;
        BARCLCT.JDGM_NO = WS_JDGM_NO;
        BARCLCT.CLCT_BR = WS_CLCT_BR;
        BARCLCT.CLCT_REC_DT = WS_C_REC_DT;
        BARCLCT.CLCT_PYE_DT = WS_C_PYE_DT;
        BARCLCT.CLCT_PYE_AC = WS_C_PYE_AC;
        BARCLCT.CLCT_PYE_NM = WS_C_PYE_NM;
        BARCLCT.CLCT_PYE_BR = WS_C_PYE_BR;
        BARCLCT.CLCT_PYE_BN = WS_C_PYE_BN;
        BARCLCT.RTN_RSN = BACURCRE.DATA.RTN_RSN;
        BARCLCT.REMK = BACURCRE.DATA.REMK;
        CEP.TRC(SCCGWA, WS_SEQ_NO);
        CEP.TRC(SCCGWA, "000000000");
        S000_CALL_BAZFCLCT_1();
        if (pgmRtn) return;
        BACURCRE.DATA.CLCT_STS = BARCLCT.CLCT_STS;
    }
    public void B026_WRITE_BATTXDL() throws IOException,SQLException,Exception {
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
        BARTXDL.BILL_NO = BACURCRE.DATA.ID_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        BARTXDL.REMK = BACURCRE.DATA.REMK;
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void B27_GET_SEQ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        WS_SEQ_NO = 0;
        BACFCLCT.FUNC = 'L';
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        S000_CALL_BAZFCLCT_2();
        if (pgmRtn) return;
        WS_SEQ_NO = BARCLCT.SEQ + 1;
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
            BACURCRE.RC.RC_MMO = BPCSCGWY.RC.RC_MMO;
            BACURCRE.RC.RC_CODE = BPCSCGWY.RC.RC_CODE;
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
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
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
        CEP.TRC(SCCGWA, BACFCLCT.RETURN_INFO);
        CEP.TRC(SCCGWA, "CLCT1");
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
