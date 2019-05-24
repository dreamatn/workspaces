package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2420 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_STSW_FLG_Y = '1';
    String CPN_S_EX_CASH_AMT = "BP-S-EX-CASH-AMT    ";
    String CPN_P_LOC_CCY2 = "BP-P-QUERY-BKAI   ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCSCSEX BPCSCSEX = new BPCSCSEX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    BPB2420_AWA_2420 BPB2420_AWA_2420;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2420 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2420_AWA_2420>");
        BPB2420_AWA_2420 = (BPB2420_AWA_2420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCPQBAI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2420_AWA_2420);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_CHECK_TLR_INFO_FOR_CN();
        }
        B020_EXCHANGE_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPB2420_AWA_2420.F_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BE_LOCAL_CCY;
            WS_FLD_NO = BPB2420_AWA_2420.F_CCY_NO;
            S000_ERR_MSG_PROC();
        }
        S000_GET_LOC_CCY2();
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.LOC_CCY);
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.LOC_CCY2);
        if (!BPB2420_AWA_2420.LOC_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            && !BPB2420_AWA_2420.LOC_CCY.equalsIgnoreCase(BPCPQBAI.DATA_INFO.LOC_CCY2)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_LOC;
            WS_FLD_NO = BPB2420_AWA_2420.LOC_CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_EXCHANGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCSEX);
        BPCSCSEX.F_CCY = BPB2420_AWA_2420.F_CCY;
        BPCSCSEX.F_AMT = BPB2420_AWA_2420.F_AMT;
        BPCSCSEX.L_CCY = BPB2420_AWA_2420.LOC_CCY;
        BPCSCSEX.L_AMT = BPB2420_AWA_2420.LOC_AMT;
        BPCSCSEX.PL_AMT = BPB2420_AWA_2420.PL_AMT;
        BPCSCSEX.EX_RATE = BPB2420_AWA_2420.EX_RATE;
        BPCSCSEX.EX_TIME = BPB2420_AWA_2420.EX_TIME;
        BPCSCSEX.ID_TYP = BPB2420_AWA_2420.ID_TYPE;
        BPCSCSEX.IDNO = BPB2420_AWA_2420.ID_NO;
        BPCSCSEX.RMK = BPB2420_AWA_2420.REMARK;
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.F_CCY);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.F_AMT);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.LOC_CCY);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.LOC_AMT);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.PL_AMT);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.EX_RATE);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.EX_TIME);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.ID_TYPE);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.ID_NO);
        CEP.TRC(SCCGWA, BPB2420_AWA_2420.REMARK);
        S000_CALL_BPZSCSEX();
    }
    public void B030_CHECK_TLR_INFO_FOR_CN() throws IOException,SQLException,Exception {
        if (BPB2420_AWA_2420.F_CCY.equalsIgnoreCase("840")) {
            if (BPB2420_AWA_2420.F_AMT > 100) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR64);
            }
        } else {
            BPCFX.FUNC = '3';
            BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPCFX.EXR_TYPE = "TRE";
            BPCFX.BUY_CCY = BPB2420_AWA_2420.F_CCY;
            BPCFX.BUY_AMT = BPB2420_AWA_2420.F_AMT;
            BPCFX.B_CASH_FLG = '0';
            BPCFX.SELL_CCY = "840";
            BPCFX.S_CASH_FLG = '0';
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
            if (BPCFX.SELL_AMT > 100) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR64);
            }
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSCSEX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_EX_CASH_AMT;
        SCCCALL.COMMAREA_PTR = BPCSCSEX;
        SCCCALL.ERR_FLDNO = BPB2420_AWA_2420.F_CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_GET_LOC_CCY2() throws IOException,SQLException,Exception {
        BPCPQBAI.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        IBS.CALLCPN(SCCGWA, CPN_P_LOC_CCY2, BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            S000_ERR_MSG_PROC();
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
