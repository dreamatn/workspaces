package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUAFEE {
    String CPN_F_MAINTAIN_FPEN = "BP-F-Z-Q-B-FPEN";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFPEN BPVFPEN = new BPVFPEN();
    BPCFACCR BPCFACCR = new BPCFACCR();
    SCCGWA SCCGWA;
    BPCZFPEN BPCZFPEN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCZFPEN BPCZFPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCZFPEN = BPCZFPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUAFEE return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCZFPEN);
        IBS.init(SCCGWA, BPVFPEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROGRAM();
        B020_WRITE_RECORD_PROGRAM();
    }
    public void B010_CHECK_PROGRAM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFACCR.FEE_CODE);
        CEP.TRC(SCCGWA, BPCFACCR.REF);
        if (BPCFACCR.FEE_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT, BPCFACCR.RC);
        }
        if (BPCFACCR.REF.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REF_NO_MUST_INPUT, BPCFACCR.RC);
        }
        if (BPCFACCR.AMT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_AMT, BPCFACCR.RC);
        }
    }
    public void B020_WRITE_RECORD_PROGRAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFPEN);
        IBS.init(SCCGWA, BPCZFPEN);
        BPVFPEN.KEY.FEE_CODE = BPCFACCR.FEE_CODE;
        BPVFPEN.KEY.REF_NO = BPCFACCR.REF;
        BPVFPEN.KEY.PEN_TYPE = '2';
        BPVFPEN.KEY.PEN_CCY = BPCFACCR.CCY;
        CEP.TRC(SCCGWA, BPVFPEN);
        BPCZFPEN.INFO.POINTER = BPVFPEN;
        BPCZFPEN.INFO.REC_LEN = 265;
        BPCZFPEN.INFO.FUNC = '3';
        S000_CALL_BPZTFPEN();
        CEP.TRC(SCCGWA, BPCZFPEN.RETURN_INFO);
        if (BPCZFPEN.RETURN_INFO == 'N') {
            B030_ADD_RECORD_PROGRAM();
        } else {
            B040_UPDATE_RECORD_PROGRAM();
        }
    }
    public void B030_ADD_RECORD_PROGRAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFPEN);
        IBS.init(SCCGWA, BPCZFPEN);
        BPVFPEN.KEY.FEE_CODE = BPCFACCR.FEE_CODE;
        BPVFPEN.KEY.REF_NO = BPCFACCR.REF;
        BPVFPEN.KEY.PEN_TYPE = '2';
        BPVFPEN.KEY.PEN_CCY = BPCFACCR.CCY;
        BPVFPEN.PEN_AMT = BPCFACCR.AMT;
        CEP.TRC(SCCGWA, BPVFPEN);
        CEP.TRC(SCCGWA, BPVFPEN.PEN_AMT);
        BPCZFPEN.INFO.POINTER = BPVFPEN;
        BPCZFPEN.INFO.REC_LEN = 265;
        BPCZFPEN.INFO.FUNC = '0';
        S000_CALL_BPZTFPEN();
        if (BPCZFPEN.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCFACCR.RC);
        }
    }
    public void B040_UPDATE_RECORD_PROGRAM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "UPDATE PROCESS");
        CEP.TRC(SCCGWA, BPVFPEN);
        BPCZFPEN.INFO.FUNC = '4';
        S000_CALL_BPZTFPEN();
        BPVFPEN.PEN_AMT = BPVFPEN.PEN_AMT + BPCFACCR.AMT;
        CEP.TRC(SCCGWA, BPVFPEN);
        BPCZFPEN.INFO.FUNC = '1';
        S000_CALL_BPZTFPEN();
        if (BPCZFPEN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCFACCR.RC);
        }
    }
    public void S000_CALL_BPZTFPEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_FPEN, BPCZFPEN);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCZFPEN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCZFPEN = ");
            CEP.TRC(SCCGWA, BPCZFPEN);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
