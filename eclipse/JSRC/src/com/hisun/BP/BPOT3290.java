package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3290 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP181";
    int K_MAX_PAR_CNT = 99;
    char K_STSW_FLG_Y = '1';
    String CPN_S_TLR_BL_CHK = "BP-S-TLR-BL-CHK";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_TLR_BV_CHK = "BP-F-TLR-BV-CHK     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTBVC BPCFTBVC = new BPCFTBVC();
    BPCSTBLC BPCSTBLC = new BPCSTBLC();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPRTBV BPRTBV = new BPRTBV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3290_AWA_3290 BPB3290_AWA_3290;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3290 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3290_AWA_3290>");
        BPB3290_AWA_3290 = (BPB3290_AWA_3290) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCRTBV);
        IBS.init(SCCGWA, BPRTBV);
        BPRTBV.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRTBV.INFO.FUNC = 'Q';
        S000_CALL_BPZRTBV();
        if ((BPRTBV.BL_BOX_CHK == 'Y' 
            && BPB3290_AWA_3290.VB_FLG == '0') 
            || (BPRTBV.BL_VLT_CHK == 'Y' 
            && BPB3290_AWA_3290.VB_FLG == '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HAS_CLOSE_BALANC;
            WS_FLD_NO = BPB3290_AWA_3290.VB_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTBVC);
        BPCFTBVC.VB_FLG = BPB3290_AWA_3290.VB_FLG;
        BPCFTBVC.BV_FLG = '1';
        for (WS_I = 1; WS_I <= K_MAX_PAR_CNT 
            && BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            BPCFTBVC.INFO[WS_I-1].BV_CODE = BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_CODE;
            BPCFTBVC.INFO[WS_I-1].BV_VAL = BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_VALUE;
            BPCFTBVC.INFO[WS_I-1].NUM = BPB3290_AWA_3290.BV_INFO[WS_I-1].NUM;
        }
        WS_I -= 1;
        BPCFTBVC.IN_CNT = WS_I;
        S00_CALL_BPZFTBVC();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL;
            WS_FLD_NO = BPB3290_AWA_3290.BV_INFO[BPCFTBVC.RT_CNT-1].NUM_NO;
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE;
            WS_FLD_NO = BPB3290_AWA_3290.BV_INFO[BPCFTBVC.RT_CNT-1].NUM_NO;
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND;
            WS_FLD_NO = BPB3290_AWA_3290.BV_INFO[BPCFTBVC.RT_CNT-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RCD_NOT_ENOUGH)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCD_NOT_ENOUGH;
            WS_FLD_NO = BPB3290_AWA_3290.BV_INFO[BPCFTBVC.RT_CNT-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTBVC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_LINK_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTBLC);
        BPCSTBLC.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSTBLC.VB_FLG = BPB3290_AWA_3290.VB_FLG;
        for (WS_I = 1; WS_I <= K_MAX_PAR_CNT 
            && BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            BPCSTBLC.INFO[WS_I-1].BV_CODE = BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_CODE;
            BPCSTBLC.INFO[WS_I-1].BV_NAME = BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_NAME;
            BPCSTBLC.INFO[WS_I-1].BV_VALUE = BPB3290_AWA_3290.BV_INFO[WS_I-1].BV_VALUE;
            BPCSTBLC.INFO[WS_I-1].NUM = BPB3290_AWA_3290.BV_INFO[WS_I-1].NUM;
        }
        S00_CALL_BPZSTBLC();
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBV() throws IOException,SQLException,Exception {
        BPCRTBV.INFO.POINTER = BPRTBV;
        BPCRTBV.INFO.LEN = 30;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBV, BPCRTBV);
        if (BPCRTBV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSTBLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_BL_CHK, BPCSTBLC);
        if (BPCSTBLC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSTBLC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZFTBVC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_BV_CHK, BPCFTBVC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
