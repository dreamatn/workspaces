package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2131 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP127";
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPOT2131_WS_APP_INFO WS_APP_INFO = new BPOT2131_WS_APP_INFO();
    int WS_APP_NO = 0;
    int WS_CNT1 = 0;
    int WS_CNT2 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    SCCGWA SCCGWA;
    BPB2131_AWA_2131 BPB2131_AWA_2131;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        A001_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2131 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2131_AWA_2131>");
        BPB2131_AWA_2131 = (BPB2131_AWA_2131) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPB2131_AWA_2131.APP_G, WS_APP_INFO);
    }
    public void A001_MAIN_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        for (WS_CNT1 = 1; WS_CNT1 <= 2 
            && JIBS_tmp_str[0].trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT1-1]);
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        if (WS_CNT1 == 1) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR142);
        }
        if (WS_CNT1 < 3) {
            WS_APP_NO = WS_APP_INFO.WS_APP_G[1-1].WS_APP_N;
            B000_MAIN_PROC();
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT2-1]);
            for (WS_CNT2 = 1; WS_CNT2 <= 200 
                && JIBS_tmp_str[0].trim().length() != 0; WS_CNT2 += 1) {
                CEP.TRC(SCCGWA, WS_CNT2);
                CEP.TRC(SCCGWA, WS_APP_INFO.WS_APP_G[WS_CNT2-1].WS_APP_N);
                WS_APP_NO = WS_APP_INFO.WS_APP_G[WS_CNT2-1].WS_APP_N;
                B000_MAIN_PROC();
                IBS.CPY2CLS(SCCGWA, BPB2131_AWA_2131.APP_G, WS_APP_INFO);
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_UPD_CASH_APP();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR35);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_APP_NO);
        CEP.TRC(SCCGWA, BPB2131_AWA_2131.OUT_AMT);
        if (WS_CNT1 == 2) {
            if (BPB2131_AWA_2131.OUT_AMT == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR37);
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
        }
    }
    public void B020_UPD_CASH_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'M';
        BPCOLIBB.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOLIBB.APP_NO = WS_APP_NO;
        BPCOLIBB.UP_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOLIBB.UP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOLIBB.MODIFY_STS = 'Q';
        CEP.TRC(SCCGWA, WS_CNT1);
        BPCOLIBB.CNT = WS_CNT1;
        CEP.TRC(SCCGWA, BPCOLIBB.CNT);
        BPCOLIBB.OUT_AMT = BPB2131_AWA_2131.OUT_AMT;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCOLIBB.PVAL_INFO[WS_CNT-1].PVAL = BPB2131_AWA_2131.PVAL_INF[WS_CNT-1].PVAL;
            BPCOLIBB.PVAL_INFO[WS_CNT-1].NUM = BPB2131_AWA_2131.PVAL_INF[WS_CNT-1].NUM;
        }
        S000_CALL_BPZSLIBB();
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSHAPP_MAINTAIN, BPCOLIBB);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
