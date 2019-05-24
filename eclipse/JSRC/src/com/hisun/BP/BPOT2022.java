package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2022 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP113";
    String CPN_S_CDTL_MAINTAIN = "BP-S-CDTL-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCDTL BPCSCDTL = new BPCSCDTL();
    SCCGWA SCCGWA;
    BPB2020_AWA_2020 BPB2020_AWA_2020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2022 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2020_AWA_2020>");
        BPB2020_AWA_2020 = (BPB2020_AWA_2020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MODIFY_CDTL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2020_AWA_2020.SDT >= BPB2020_AWA_2020.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB2020_AWA_2020.SDT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_MODIFY_CDTL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCDTL);
        BPCSCDTL.FUNC = 'M';
        BPCSCDTL.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSCDTL();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.CCY);
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.M_FLG);
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.SDT);
        BPCSCDTL.FUNC_CODE = BPB2020_AWA_2020.FUNC;
        BPCSCDTL.CCY = BPB2020_AWA_2020.CCY;
        BPCSCDTL.PAR_VAL = BPB2020_AWA_2020.PAR_VAL;
        BPCSCDTL.M_FLG = BPB2020_AWA_2020.M_FLG;
        BPCSCDTL.R_FLG = BPB2020_AWA_2020.R_FLG;
        BPCSCDTL.SDT = BPB2020_AWA_2020.SDT;
        BPCSCDTL.EXP_DATE = BPB2020_AWA_2020.EXP_DATE;
        BPCSCDTL.DESC = BPB2020_AWA_2020.DESC;
        BPCSCDTL.CDSC = BPB2020_AWA_2020.CDESC;
        BPCSCDTL.UPT_DT = BPB2020_AWA_2020.UPT_DT;
        BPCSCDTL.UPT_TLR = BPB2020_AWA_2020.UPT_TLR;
    }
    public void S000_CALL_BPZSCDTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CDTL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSCDTL;
        SCCCALL.ERR_FLDNO = BPB2020_AWA_2020.CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
