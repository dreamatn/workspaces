package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4877 {
    String K_OUTPUT_FMT = "BP554";
    String CPN_S_TMAP_MAINTAIN = "BP-S-TMAP-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTMAP BPCSTMAP = new BPCSTMAP();
    BPCOTMAP BPCOTMAP = new BPCOTMAP();
    SCCGWA SCCGWA;
    BPC4877 BPC4877;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4877 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4877 = new BPC4877();
        IBS.init(SCCGWA, BPC4877);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4877);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ORM_DATA_ITEM_UPD();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC4877.FROM_ARE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ORM_DATA_ITEM_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTMAP);
        R000_TRANS_DATA_PARAMETER();
        BPCSTMAP.FUNC = 'U';
        S000_CALL_BPZSTMAP();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTMAP);
        R100_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTMAP;
        SCCFMT.DATA_LEN = 199;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTMAP.FROM_AREA = BPC4877.FROM_ARE;
        BPCSTMAP.TX_CODE = BPC4877.TX_CODE;
        BPCSTMAP.DATA_NM = BPC4877.DATA_NM;
        BPCSTMAP.DATA_SEQ = BPC4877.DATA_SEQ;
        BPCSTMAP.PB_TYPE = BPC4877.PB_TYPE;
        BPCSTMAP.DATA_SDESC = BPC4877.DATA_SDE;
        BPCSTMAP.DATA_DESC = BPC4877.DATA_DES;
    }
    public void R100_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOTMAP.FROM_AREA = BPC4877.FROM_ARE;
        BPCOTMAP.TX_CODE = BPC4877.TX_CODE;
        BPCOTMAP.DATA_NM = BPC4877.DATA_NM;
        BPCOTMAP.DATA_SEQ = BPC4877.DATA_SEQ;
        BPCOTMAP.PB_TYPE = BPC4877.PB_TYPE;
        BPCOTMAP.DATA_SDESC = BPC4877.DATA_SDE;
        BPCOTMAP.DATA_DESC = BPC4877.DATA_DES;
    }
    public void S000_CALL_BPZSTMAP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TMAP_MAINTAIN, BPCSTMAP);
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