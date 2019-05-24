package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1168 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP064";
    String CPN_FCPN_MAINTAIN = "BP-F-S-MAINTAIN-FCPN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_NEXT = 0;
    char WS_INPUT_ENDED = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCPN BPCOFCPN = new BPCOFCPN();
    SCCGWA SCCGWA;
    BPC1168 BPC1168;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1168 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1168 = new BPC1168();
        IBS.init(SCCGWA, BPC1168);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1168);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_BROWSE_CPN_FEE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC1168.CPNT_ID.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTIN;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_CPN_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCPN);
        if (BPC1168.CPNT_ID.trim().length() > 0) {
            R000_TRANS_DATA_PARAMETER();
        }
        BPCOFCPN.FUNC = 'B';
        S000_CALL_BPZFSCPN();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFCPN.KEY.CPNT_ID = BPC1168.CPNT_ID;
    }
    public void S000_CALL_BPZFSCPN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "  BROWSE TEST ");
        IBS.CALLCPN(SCCGWA, CPN_FCPN_MAINTAIN, BPCOFCPN);
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
