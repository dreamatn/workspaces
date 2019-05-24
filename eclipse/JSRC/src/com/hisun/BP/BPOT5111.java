package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5111 {
    String K_CMP_CAL_MAINTAIN = "BP-S-IRPD-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIPDM BPCSIPDM = new BPCSIPDM();
    SCCGWA SCCGWA;
    BPB5110_AWA_5110 BPB5110_AWA_5110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5111 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5110_AWA_5110>");
        BPB5110_AWA_5110 = (BPB5110_AWA_5110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.CCY);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.B_TYPE);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.TENOR);
        IBS.init(SCCGWA, BPCSIPDM);
        BPCSIPDM.CCY = BPB5110_AWA_5110.CCY;
        BPCSIPDM.B_TYPE = BPB5110_AWA_5110.B_TYPE;
        BPCSIPDM.TENOR = BPB5110_AWA_5110.TENOR;
        BPCSIPDM.FUNC = 'B';
        BPCSIPDM.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSIPDM();
        if (BPCSIPDM.CHECK_RESULT == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
            WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSIPDM;
        SCCCALL.ERR_FLDNO = BPB5110_AWA_5110.FUNC_CD_NO;
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
