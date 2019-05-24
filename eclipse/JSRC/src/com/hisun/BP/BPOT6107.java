package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6107 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCSRTCO BPCSRTCO = new BPCSRTCO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6107_AWA_6107 BPB6107_AWA_6107;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6107 return!");
        Z_RET();
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6107_AWA_6107>");
        BPB6107_AWA_6107 = (BPB6107_AWA_6107) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSRTCO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        B200_ADD_PROC();
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
        CEP.TRC(SCCGWA, BPB6107_AWA_6107.RT_CCY);
        CEP.TRC(SCCGWA, BPB6107_AWA_6107.RT_RBASE);
        CEP.TRC(SCCGWA, BPB6107_AWA_6107.RT_RTENO);
        CEP.TRC(SCCGWA, BPB6107_AWA_6107.OLD_BASE);
        CEP.TRC(SCCGWA, BPB6107_AWA_6107.OLD_TYPE);
        if (BPB6107_AWA_6107.RT_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6107_AWA_6107.RT_CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6107_AWA_6107.RT_CCY);
            S000_ERR_MSG_NO_PROC();
        }
        if (BPB6107_AWA_6107.RT_RBASE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6107_AWA_6107.RT_RBASE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6107_AWA_6107.RT_RBASE);
            S000_ERR_MSG_NO_PROC();
        }
        if (BPB6107_AWA_6107.RT_RTENO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6107_AWA_6107.RT_RTENO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6107_AWA_6107.RT_RTENO);
            S000_ERR_MSG_NO_PROC();
        }
        if (BPB6107_AWA_6107.OLD_BASE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6107_AWA_6107.OLD_BASE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6107_AWA_6107.OLD_BASE);
            S000_ERR_MSG_NO_PROC();
        }
        if (BPB6107_AWA_6107.OLD_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6107_AWA_6107.OLD_TYPE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6107_AWA_6107.OLD_TYPE);
            S000_ERR_MSG_NO_PROC();
        }
    }
    public void B200_ADD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ADD");
        BPCSRTCO.FUNC = 'A';
        BPCSRTCO.INP.INP_CCY = BPB6107_AWA_6107.RT_CCY;
        BPCSRTCO.INP.INP_RBASE = BPB6107_AWA_6107.RT_RBASE;
        BPCSRTCO.INP.INP_RTENO = BPB6107_AWA_6107.RT_RTENO;
        BPCSRTCO.INP.INP_DESC = BPB6107_AWA_6107.RT_DESC;
        BPCSRTCO.INP.INP_OLD_BASE = BPB6107_AWA_6107.OLD_BASE;
        BPCSRTCO.INP.INP_OLD_TYPE = BPB6107_AWA_6107.OLD_TYPE;
        S000_CALL_BPZSRTCO();
        if (BPCSRTCO.RETURN_INFO == 'X') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST;
            S100_ERR_MSG_PROC();
        } else if (BPCSRTCO.RETURN_INFO == 'F') {
            R000_RTN_DATA_PROC();
        }
    }
    public void S000_CALL_BPZSRTCO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-OPER-RT", BPCSRTCO);
    }
    public void R000_RTN_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPZ09";
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_OLD_BASE);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_DESC);
        CEP.TRC(SCCGWA, BPCSRTCO.INP.INP_OLD_TYPE);
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_CCY = BPCSRTCO.INP.INP_CCY;
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_RBASE = BPCSRTCO.INP.INP_RBASE;
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_RTENO = BPCSRTCO.INP.INP_RTENO;
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_DESC = BPCSRTCO.INP.INP_DESC;
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE = BPCSRTCO.INP.INP_OLD_BASE;
        BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE = BPCSRTCO.INP.INP_OLD_TYPE;
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_DESC);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_OLD_BASE);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_OLD_TYPE);
        SCCFMT.DATA_PTR = BPCSRTCO.OUTPUT_INFO.OUT_DATA;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_NO_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S100_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
