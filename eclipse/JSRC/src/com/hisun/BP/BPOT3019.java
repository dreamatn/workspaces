package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3019 {
    String CPN_S_IN_V = "BP-S-BV-IN-V     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVIV BPCSBVIV = new BPCSBVIV();
    SCCGWA SCCGWA;
    BPB3010_AWA_3010 BPB3010_AWA_3010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3019 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3010_AWA_3010>");
        BPB3010_AWA_3010 = (BPB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBVIV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DRAW_CASH_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        BPCSBVIV.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        BPCSBVIV.CNM = BPB3010_AWA_3010.BV_DATA[1-1].CNM;
        BPCSBVIV.HEAD_NO = BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO;
        BPCSBVIV.BEG_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO;
        BPCSBVIV.END_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO;
        BPCSBVIV.NUM = BPB3010_AWA_3010.BV_DATA[1-1].NUM;
        BPCSBVIV.MOVE_DT = BPB3010_AWA_3010.MOV_DT;
        BPCSBVIV.CONF_NO = (int) BPB3010_AWA_3010.CONF_NO;
        S000_CALL_BPZSBVIV();
    }
    public void S000_CALL_BPZSBVIV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IN_V;
        SCCCALL.COMMAREA_PTR = BPCSBVIV;
        SCCCALL.ERR_FLDNO = BPB3010_AWA_3010.BV_DATA[1-1].CODE_NO;
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
