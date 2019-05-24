package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2041 {
    String K_OUTPUT_FMT = "BP282";
    String CPN_S_BRIL_MT = "BP-S-BRIL-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2041_WS_IBAC_KEY WS_IBAC_KEY = new BPOT2041_WS_IBAC_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSBRIL BPCSBRIL = new BPCSBRIL();
    SCCGWA SCCGWA;
    BPB2041_AWA_2041 BPB2041_AWA_2041;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2041 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2041_AWA_2041>");
        BPB2041_AWA_2041 = (BPB2041_AWA_2041) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.FUNC);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.INSR_CCY);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.BOIS_AMT);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.PBIS_AMT);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.LMT_CCY);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.MAX_LMT);
        CEP.TRC(SCCGWA, BPB2041_AWA_2041.MIN_LMT);
        B010_CHECK_INPUT();
        B020_CREATE_IBAC_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2041_AWA_2041.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2041_AWA_2041.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2041_AWA_2041.BOIS_AMT > BPB2041_AWA_2041.PBIS_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BOIS_IS_WRONG;
            WS_FLD_NO = BPB2041_AWA_2041.BOIS_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2041_AWA_2041.MIN_LMT > BPB2041_AWA_2041.MAX_LMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MINLMT_IS_WRONG;
            WS_FLD_NO = BPB2041_AWA_2041.BOIS_AMT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_IBAC_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBRIL);
        BPCSBRIL.FUNC = 'A';
        BPCSBRIL.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBRIL();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBRIL.FUNC = BPB2041_AWA_2041.FUNC;
        BPCSBRIL.BR = BPB2041_AWA_2041.BR;
        BPCSBRIL.INSR_CCY = BPB2041_AWA_2041.INSR_CCY;
        BPCSBRIL.BOIS_AMT = BPB2041_AWA_2041.BOIS_AMT;
        BPCSBRIL.PBIS_AMT = BPB2041_AWA_2041.PBIS_AMT;
        BPCSBRIL.LMT_CCY = BPB2041_AWA_2041.LMT_CCY;
        BPCSBRIL.MAX_LMT = BPB2041_AWA_2041.MAX_LMT;
        BPCSBRIL.MIN_LMT = BPB2041_AWA_2041.MIN_LMT;
        BPCSBRIL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSBRIL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZSBRIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BRIL_MT;
        SCCCALL.COMMAREA_PTR = BPCSBRIL;
        SCCCALL.ERR_FLDNO = BPB2041_AWA_2041.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
