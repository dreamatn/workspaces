package com.hisun.BP;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7050 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSQSTM BPCSQSTM = new BPCSQSTM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB7050_AWA_7050 BPB7050_AWA_7050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT7050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7050_AWA_7050>");
        BPB7050_AWA_7050 = (BPB7050_AWA_7050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSQSTM);
        BPCSQSTM.FUNC = 'Q';
        BPCSQSTM.ACNO = BPB7050_AWA_7050.ACNO;
        BPCSQSTM.CCY = BPB7050_AWA_7050.CCY;
        BPCSQSTM.FROM_DATE = BPB7050_AWA_7050.FM_DT;
        BPCSQSTM.TO_DATE = BPB7050_AWA_7050.TO_DT;
        CEP.TRC(SCCGWA, BPB7050_AWA_7050.ACNO);
        CEP.TRC(SCCGWA, BPB7050_AWA_7050.CCY);
        CEP.TRC(SCCGWA, BPB7050_AWA_7050.FM_DT);
        CEP.TRC(SCCGWA, BPB7050_AWA_7050.TO_DT);
        S010_CALL_BPZSQSTM();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB7050_AWA_7050.FM_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FROM_DT_M_INPUT;
            WS_FLD_NO = BPB7050_AWA_7050.FM_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB7050_AWA_7050.TO_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DT_M_INPUT;
            WS_FLD_NO = BPB7050_AWA_7050.TO_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB7050_AWA_7050.TO_DT < BPB7050_AWA_7050.FM_DT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DATE_LT_FROM_DATE;
            WS_FLD_NO = BPB7050_AWA_7050.TO_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
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
    public void S010_CALL_BPZSQSTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-INQUIRE-STATEME", BPCSQSTM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
