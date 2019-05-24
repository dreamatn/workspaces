package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2033 {
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP135";
    String CPN_S_IBAC_MT = "BP-S-IBAC-MAINTAIN  ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2033_WS_IBAC_KEY WS_IBAC_KEY = new BPOT2033_WS_IBAC_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIBAC BPCSIBAC = new BPCSIBAC();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPB2030_AWA_2030 BPB2030_AWA_2030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2033 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2030_AWA_2030>");
        BPB2030_AWA_2030 = (BPB2030_AWA_2030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_IBAC_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ADMINI_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        BPB2030_AWA_2030.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPB2030_AWA_2030.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B020_DELETE_IBAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIBAC);
        BPCSIBAC.FUNC = 'D';
        BPCSIBAC.FUNC_CODE = BPB2030_AWA_2030.FUNC;
        BPCSIBAC.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSIBAC();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSIBAC.FUNC_CODE = BPB2030_AWA_2030.FUNC;
        BPCSIBAC.CCY = BPB2030_AWA_2030.CCY;
        BPCSIBAC.SWIFT = BPB2030_AWA_2030.SWIFT;
        BPCSIBAC.EFF_DATE = BPB2030_AWA_2030.EFF_DT;
    }
    public void S000_CALL_BPZSIBAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IBAC_MT;
        SCCCALL.COMMAREA_PTR = BPCSIBAC;
        SCCCALL.ERR_FLDNO = BPB2030_AWA_2030.CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSIBAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSIBAC.RC);
            WS_FLD_NO = BPB2030_AWA_2030.CCY_NO;
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
