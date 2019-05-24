package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5420 {
    String CPN_U_DCZULCCX = "DC-U-INF-LCCX";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCULCCX DCCULCCX = new DCCULCCX();
    SCCGWA SCCGWA;
    DCB5420_AWA_5420 DCB5420_AWA_5420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5420 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5420_AWA_5420>");
        DCB5420_AWA_5420 = (DCB5420_AWA_5420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR);
        }
    }
    public void B200_INF_NOTE_PROC() throws IOException,SQLException,Exception {
        B220_EQUIRE_LCXY();
    }
    public void B220_EQUIRE_LCXY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCULCCX);
        DCCULCCX.INP_DATA.DR_CARD = DCB5420_AWA_5420.DR_CARD;
        DCCULCCX.INP_DATA.AC = DCB5420_AWA_5420.AC;
        DCCULCCX.INP_DATA.PAGE_ROW = DCB5420_AWA_5420.PAGE_ROW;
        DCCULCCX.INP_DATA.PAGE_NUM = DCB5420_AWA_5420.PAGE_NUM;
        S000_CALL_DCZULCCX();
    }
    public void S000_CALL_DCZULCCX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DCZULCCX, DCCULCCX);
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
