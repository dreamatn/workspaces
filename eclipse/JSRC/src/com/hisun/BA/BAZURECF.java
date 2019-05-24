package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZURECF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "FANCILITY CONTRACT STATUS CHANGE";
    char K_ZERO = '0';
    char K_ONE = '1';
    String K_EVENT_CODE_CKIN = "RTCLCT";
    String WS_ERR_MSG = " ";
    long WS_SEQ_NO = 0;
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    BAZURECF_WS_PRM_KEY WS_PRM_KEY = new BAZURECF_WS_PRM_KEY();
    BAZURECF_WS_OUTPUT WS_OUTPUT = new BAZURECF_WS_OUTPUT();
    String WS_CONTRACT_TYPE = " ";
    String WS_EVENT_CODE = " ";
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BARMST1 BARMST1 = new BARMST1();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARCLCT BARCLCT = new BARCLCT();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BARTXDL BARTXDL = new BARTXDL();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACUPROD BACUPROD = new BACUPROD();
    SCCGWA SCCGWA;
    BACURECF BACURECF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BACURECF BACURECF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACURECF = BACURECF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BACURECF.RC.RC_APP = "BA";
        BACURECF.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_INFORMATION();
        if (pgmRtn) return;
        B020_TRANCHE_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_GET_INFORMATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BACURECF.DATA.ID_NO;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BARCLCT);
        IBS.init(SCCGWA, BACFCLCT);
        BACFCLCT.FUNC = 'L';
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        S000_CALL_BAZFCLCT_1();
        if (pgmRtn) return;
        WS_SEQ_NO = BARCLCT.SEQ + 1;
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        B021_GET_PRODMO_PROC();
        if (pgmRtn) return;
        B022_ADD_ACCOUTING();
        if (pgmRtn) return;
        B025_WRITE_BATCLCT_2();
        if (pgmRtn) return;
        B026_REWRITE_BATMST1();
        if (pgmRtn) return;
        B027_WRITE_BATTXDL();
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
        WS_EVENT_CODE = "RTCLCT";
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[1-1] = WS_CONTRACT_TYPE;
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = WS_CONTRACT_TYPE;
        BPCPOEWA.DATA.PROD_CODE = BARMST1.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = WS_EVENT_CODE;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        BPCPOEWA.DATA.BR_OLD = BARMST1.ACPT_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = BARMST1.BILL_AMT;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B025_WRITE_BATCLCT_2() throws IOException,SQLException,Exception {
        BARCLCT.SEQ = (short) WS_SEQ_NO;
        WS_OUTPUT.WS_CLCT_STS = '0';
        R02_WRITE_BATCLCT_COMMON();
        if (pgmRtn) return;
        BACURECF.DATA.CLCT_STS = WS_OUTPUT.WS_CLCT_STS;
    }
    public void B026_REWRITE_BATMST1() throws IOException,SQLException,Exception {
        BACFMST1.FUNC = 'U';
        BARMST1.PAY_MTH = BACURECF.DATA.PAY_MTH;
        BARMST1.PAY_CHNL = BACURECF.DATA.PAY_CHNL;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B027_WRITE_BATTXDL() throws IOException,SQLException,Exception {
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
        BARTXDL.BILL_NO = BACURECF.DATA.ID_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        BARTXDL.REMK = BACURECF.DATA.REMK;
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void R02_WRITE_BATCLCT_COMMON() throws IOException,SQLException,Exception {
        BACFCLCT.FUNC = 'A';
        BARCLCT.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARCLCT.SEQ = (short) WS_SEQ_NO;
        BARCLCT.BILL_NO = BACURECF.DATA.ID_NO;
        BARCLCT.ACPT_BR = BARMST1.ACPT_BR;
        BARCLCT.CLCT_STS = WS_OUTPUT.WS_CLCT_STS;
        BARCLCT.BILL_STS = BARMST1.BILL_STS;
        BARCLCT.SPPM_FLG = 'N';
        BARCLCT.PAY_MTH = BACURECF.DATA.PAY_MTH;
        BARCLCT.PAY_CHNL = BARMST1.PAY_CHNL;
        BARCLCT.WO_PAY_FLG = 'N';
        BARCLCT.CLCT_BR = BACURECF.DATA.C_BR;
        BARCLCT.CLCT_REC_DT = BACURECF.DATA.C_REC_DT;
        BARCLCT.CLCT_PYE_DT = BACURECF.DATA.C_PYE_DT;
        BARCLCT.CLCT_PYE_AC = BACURECF.DATA.C_PYE_AC;
        BARCLCT.CLCT_PYE_NM = BACURECF.DATA.C_PYE_NM;
        BARCLCT.CLCT_PYE_BR = BACURECF.DATA.C_PYE_BR;
        BARCLCT.CLCT_PYE_BN = BACURECF.DATA.C_PYE_BN;
        BARCLCT.RTN_RSN = BACURECF.DATA.RTN_RSN;
        BARCLCT.REMK = BACURECF.DATA.REMK;
        S000_CALL_BAZFCLCT_2();
        if (pgmRtn) return;
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
            BACURECF.RC.RC_APP = BPCSCGWY.RC.RC_MMO;
            BACURECF.RC.RC_CODE = BPCSCGWY.RC.RC_CODE;
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
    public void S000_CALL_BAZFCLCT_2() throws IOException,SQLException,Exception {
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
    public void S000_CALL_BAZFCLCT_1() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
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
