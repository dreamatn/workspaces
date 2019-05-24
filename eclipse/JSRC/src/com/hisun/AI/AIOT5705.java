package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5705 {
    DBParm AITCMIB_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AIOT5705_WS_OUTPUT WS_OUTPUT = new AIOT5705_WS_OUTPUT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICSCMIB AICSCMIB = new AICSCMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5700_AWA_5700 AIB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5705 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5700_AWA_5700>");
        AIB5700_AWA_5700 = (AIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_READ_REC_NAME();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_READ_REC_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICRCMIB);
        AIRCMIB.KEY.ITM = AIB5700_AWA_5700.ITM;
        AIRCMIB.KEY.SEQ = AIB5700_AWA_5700.SEQ;
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.ITM);
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.SEQ);
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        T000_STARTBR_AITCMIB();
        R000_DATA_OUTPUT();
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OUT_NAME = AIRCMIB.CHS_NM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI705";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 161;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_AITCMIB() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.where = "ITM = :AIRCMIB.KEY.ITM "
            + "AND SEQ = :AIRCMIB.KEY.SEQ";
        AITCMIB_RD.fst = true;
        AITCMIB_RD.order = "BR";
        IBS.READ(SCCGWA, AIRCMIB, this, AITCMIB_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
