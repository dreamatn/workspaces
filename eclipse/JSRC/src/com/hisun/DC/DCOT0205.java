package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0205 {
    String K_PROD_MDEL = "IRLN";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 1;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIALP DCCSIALP = new DCCSIALP();
    SCCGWA SCCGWA;
    DCB0205_AWA_0205 DCB0205_AWA_0205;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0205 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0205_AWA_0205>");
        DCB0205_AWA_0205 = (DCB0205_AWA_0205) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_SUB();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.FUNC);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.PROD_CD);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.PROD_NM);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.LN_AC);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.DD_AC);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.CCY);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.CCY_TYP);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.LN_TYP);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.ADP_TYP);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.DED_MTH);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.D_MN_AMT);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.D_MX_AMT);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.DD_D_PER);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.LN_D_PER);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.C_ED_PER);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.T_ED_PER);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.STRAMT);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.STRDT);
        CEP.TRC(SCCGWA, DCB0205_AWA_0205.EXPDT);
    }
    public void B020_SET_SUB() throws IOException,SQLException,Exception {
        DCCSIALP.KEY.CON_MDEL = K_PROD_MDEL;
        DCCSIALP.FUNC = DCB0205_AWA_0205.FUNC;
        DCCSIALP.KEY.PROD_CD = DCB0205_AWA_0205.PROD_CD;
        DCCSIALP.PROD_NM = DCB0205_AWA_0205.PROD_NM;
        DCCSIALP.LN_AC = DCB0205_AWA_0205.LN_AC;
        DCCSIALP.DD_AC = DCB0205_AWA_0205.DD_AC;
        DCCSIALP.CCY = DCB0205_AWA_0205.CCY;
        DCCSIALP.CCY_TYP = DCB0205_AWA_0205.CCY_TYP;
        DCCSIALP.LN_TYP = DCB0205_AWA_0205.LN_TYP;
        DCCSIALP.ADP_TYP = DCB0205_AWA_0205.ADP_TYP;
        DCCSIALP.DED_MTH = DCB0205_AWA_0205.DED_MTH;
        DCCSIALP.D_MN_AMT = DCB0205_AWA_0205.D_MN_AMT;
        DCCSIALP.D_MX_AMT = DCB0205_AWA_0205.D_MX_AMT;
        DCCSIALP.DD_D_PER = DCB0205_AWA_0205.DD_D_PER;
        DCCSIALP.LN_D_PER = DCB0205_AWA_0205.LN_D_PER;
        DCCSIALP.C_ED_PER = DCB0205_AWA_0205.C_ED_PER;
        DCCSIALP.T_ED_PER = DCB0205_AWA_0205.T_ED_PER;
        DCCSIALP.STRAMT = DCB0205_AWA_0205.STRAMT;
        DCCSIALP.STRDT = DCB0205_AWA_0205.STRDT;
        DCCSIALP.EXPDT = DCB0205_AWA_0205.EXPDT;
        CEP.TRC(SCCGWA, DCCSIALP);
        if (DCB0205_AWA_0205.FUNC == 'Q') {
            DCCSIALP.FUNC = 'Q';
        } else if (DCB0205_AWA_0205.FUNC == 'A') {
            DCCSIALP.FUNC = 'A';
        } else if (DCB0205_AWA_0205.FUNC == 'M') {
            DCCSIALP.FUNC = 'M';
        } else if (DCB0205_AWA_0205.FUNC == 'D') {
            DCCSIALP.FUNC = 'D';
        } else {
        }
        S000_CALL_DCZSIALP();
    }
    public void S000_CALL_DCZSIALP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-AUTO-LN-PLAN", DCCSIALP);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
