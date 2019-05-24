package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3903 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP164";
    String CPN_S_MGM_TBV = "BP-S-MGM-TBV ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_BOX_VLT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMTBV BPCSMTBV = new BPCSMTBV();
    SCCGWA SCCGWA;
    BPB3900_AWA_3900 BPB3900_AWA_3900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3900_AWA_3900>");
        BPB3900_AWA_3900 = (BPB3900_AWA_3900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_TBV_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3900_AWA_3900.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
            && (BPB3900_AWA_3900.FUNC == 'A' 
            || BPB3900_AWA_3900.FUNC == 'D')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MAINT_ONESELF;
            WS_FLD_NO = BPB3900_AWA_3900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEL_TBV_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMTBV);
        BPCSMTBV.FUNC = 'D';
        BPCSMTBV.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSMTBV();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSMTBV.TLR = BPB3900_AWA_3900.TLR;
        BPCSMTBV.TLR_NAME = BPB3900_AWA_3900.TLR_NAME;
        CEP.TRC(SCCGWA, BPCSMTBV.TLR_NAME);
        BPCSMTBV.BOX_VLT_FLG = BPB3900_AWA_3900.FLG;
    }
    public void S000_CALL_BPZSMTBV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MGM_TBV;
        SCCCALL.COMMAREA_PTR = BPCSMTBV;
        SCCCALL.ERR_FLDNO = BPB3900_AWA_3900.FUNC_NO;
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
