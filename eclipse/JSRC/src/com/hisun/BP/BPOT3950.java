package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3950 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP187";
    String CPN_S_BVLT_MAINTAIN = "BP-S-BVLT-MAINTAIN ";
    String CPN_P_INQ_BR = "BP-P-INQ-BR         ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_HEAD_NO = " ";
    String WS_BV_NO = " ";
    int WS_LENGTH = 0;
    int WS_BR = 0;
    int WS_BR3 = 0;
    int WS_BR4 = 0;
    int WS_J = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSBVLT BPCSBVLT = new BPCSBVLT();
    BPCOBVLT BPCOBVLT = new BPCOBVLT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB3950_AWA_3950 BPB3950_AWA_3950;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3950 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3950_AWA_3950>");
        BPB3950_AWA_3950 = (BPB3950_AWA_3950) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBVLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_CDTL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3950_AWA_3950.FUNC);
        WS_FUNC_FLG = BPB3950_AWA_3950.FUNC;
        if (WS_FUNC_FLG != 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB3950_AWA_3950.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB3950_AWA_3950.BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB3950_AWA_3950.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_RL == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_NOT_ZERO;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_RL;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_PL == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_NOT_ZERO;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_PL;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_BL == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_NOT_ZERO;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_BL;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_RL <= BPB3950_AWA_3950.LMT_L_RL) {
            CEP.TRC(SCCGWA, "LMT-U-RL <= LMT-L-RL");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_GREAT_LILMT;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_RL;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_PL <= BPB3950_AWA_3950.LMT_L_PL) {
            CEP.TRC(SCCGWA, "LMT-U-PL <= LMT-L-PL");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_GREAT_LILMT;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_PL;
            S000_ERR_MSG_PROC();
        }
        if (BPB3950_AWA_3950.LMT_U_BL <= BPB3950_AWA_3950.LMT_L_BL) {
            CEP.TRC(SCCGWA, "LMT-U-BL <= LMT-L-BL");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLMT_GREAT_LILMT;
            WS_FLD_NO = (short) BPB3950_AWA_3950.LMT_U_BL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_CDTL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVLT);
        BPCSBVLT.FUNC = 'A';
        BPCSBVLT.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBVLT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBVLT.FUNC = BPB3950_AWA_3950.FUNC;
        BPCSBVLT.BV_CODE = BPB3950_AWA_3950.BV_CODE;
        BPCSBVLT.BR = BPB3950_AWA_3950.BR;
        BPCSBVLT.LMT_U_RL = BPB3950_AWA_3950.LMT_U_RL;
        BPCSBVLT.LMT_L_RL = BPB3950_AWA_3950.LMT_L_RL;
        BPCSBVLT.LMT_U_PL = BPB3950_AWA_3950.LMT_U_PL;
        BPCSBVLT.LMT_L_PL = BPB3950_AWA_3950.LMT_L_PL;
        BPCSBVLT.LMT_U_BL = BPB3950_AWA_3950.LMT_U_BL;
        BPCSBVLT.LMT_L_BL = BPB3950_AWA_3950.LMT_L_BL;
    }
    public void S000_CALL_BPZSBVLT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-CALL-BPZSBVLT");
        IBS.CALLCPN(SCCGWA, CPN_S_BVLT_MAINTAIN, BPCSBVLT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BR, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_INFOR() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_LENGTH, WS_FLD_NO);
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
