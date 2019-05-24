package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUCRTC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMKS = "CREAT E-CARD";
    String WS_ERR_MSG = " ";
    long WS_SEQ_NO = 0;
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BARFEDL BARFEDL = new BARFEDL();
    BARDPAY BARDPAY = new BARDPAY();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFDPAY BACFDPAY = new BACFDPAY();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    BACUCRTC BACUCRTC;
    public void MP(SCCGWA SCCGWA, BACUCRTC BACUCRTC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUCRTC = BACUCRTC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUCRTC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BACUCRTC.RC.RC_MMO = "BA";
        BACUCRTC.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BACUCRTC.DATA.BILL_NO);
        CEP.TRC(SCCGWA, BACUCRTC.DATA.RMK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_BILL_INFO();
        if (pgmRtn) return;
        B100_UPDATE_MST1_PROC();
        if (pgmRtn) return;
        B100_ADD_TXDL_PROC();
        if (pgmRtn) return;
        B100_ADD_DPAY_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BARMST1.BILL_NO = BACUCRTC.DATA.BILL_NO;
        BACFMST1.FUNC = 'T';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "22222");
        if (BARMST1.BILL_STS != '0') {
            CEP.TRC(SCCGWA, "3333");
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_NOT_FOUND_STS, BACUCRTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BARMST1.BILL_TYP != 'P') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TYP_NOT_P, BACUCRTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_UPDATE_MST1_PROC() throws IOException,SQLException,Exception {
        BARMST1.BILL_STS = '1';
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B100_ADD_TXDL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        BACFTXDL.FUNC = 'A';
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.BILL_NO = BARMST1.BILL_NO;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void B100_ADD_DPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARDPAY);
        IBS.init(SCCGWA, BACFDPAY);
        WS_SEQ_NO = 0;
        BACFDPAY.FUNC = 'L';
        BARDPAY.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARDPAY.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        S000_CALL_BAZFDPAY();
        if (pgmRtn) return;
        WS_SEQ_NO = BARDPAY.SEQ + 1;
        IBS.init(SCCGWA, BACFDPAY);
        IBS.init(SCCGWA, BARDPAY);
        BACFDPAY.FUNC = 'A';
        BARDPAY.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARDPAY.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARDPAY.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARDPAY.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARDPAY.SEQ = (short) WS_SEQ_NO;
        BARDPAY.ACPT_BR = BARMST1.ACPT_BR;
        BARDPAY.BILL_NO = BACUCRTC.DATA.BILL_NO;
        BARDPAY.DPAY_STS = '1';
        BARDPAY.BILL_STS = '1';
        BARDPAY.AMT_STS = BARMST1.AMT_STS;
        BARDPAY.PLDG_STS = BARMST1.PLDG_STS;
        BARDPAY.BLK_STS = BARMST1.BLK_STS;
        BARDPAY.STP_STS = 'N';
        BARDPAY.WO_PAY_FLG = 'N';
        BARDPAY.CHG_DRW_FLG = 'N';
        BARDPAY.ORG_BIL_NO = BARMST1.ORG_BIL_NO;
        BARDPAY.REMK = BARMST1.REMK;
        S000_CALL_BAZFDPAY();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        CEP.TRC(SCCGWA, BACFMST1.RC.RC_CODE);
        if (BACFMST1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "11111");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUCRTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUCRTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFDPAY() throws IOException,SQLException,Exception {
        BACFDPAY.REC_PTR = BARDPAY;
        BACFDPAY.REC_LEN = 379;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFDPAY", BACFDPAY);
        if (BACFDPAY.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFDPAY.RC);
            S000_ERR_MSG_PROC();
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
