package com.hisun.CI;

import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3070 {
    String WS_MSGID = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICPDTL CICPDTL = new CICPDTL();
    SCCGWA SCCGWA;
    CIB3070_AWA_3070 CIB3070_AWA_3070;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3070 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3070_AWA_3070>");
        CIB3070_AWA_3070 = (CIB3070_AWA_3070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = CIB3070_AWA_3070.FUNC;
        CICPDTL.TYPE = CIB3070_AWA_3070.TYPE;
        CICPDTL.OBJ_TYP = CIB3070_AWA_3070.OBJ_TYP;
        CICPDTL.LC_OBJ = CIB3070_AWA_3070.LC_OBJ;
        CICPDTL.CUS_AC = CIB3070_AWA_3070.CUS_AC;
        CICPDTL.OBJ_TYP1 = CIB3070_AWA_3070.OOBJ_TYP;
        CICPDTL.LC_OBJ1 = CIB3070_AWA_3070.OLC_OBJ;
        CICPDTL.TX_TYPE = CIB3070_AWA_3070.TX_TYPE;
        CICPDTL.LC_NO = CIB3070_AWA_3070.LC_NO;
        CICPDTL.SEQ = CIB3070_AWA_3070.SEQ;
        CICPDTL.CON_TYP = CIB3070_AWA_3070.CON_TYP;
        CICPDTL.LVL_TYP = CIB3070_AWA_3070.LVL_TYP;
        CICPDTL.LVL = CIB3070_AWA_3070.LVL;
        CICPDTL.LC_CCY = CIB3070_AWA_3070.LC_CCY;
        CICPDTL.DAY_STA = CIB3070_AWA_3070.DAY_STA;
        CICPDTL.DAY_END = CIB3070_AWA_3070.DAY_END;
        CICPDTL.TXN_AMT = CIB3070_AWA_3070.TXN_AMT;
        CICPDTL.DLY_AMT = CIB3070_AWA_3070.DLY_AMT;
        CICPDTL.DLY_VOL = CIB3070_AWA_3070.DLY_VOL;
        CICPDTL.MLY_AMT = CIB3070_AWA_3070.MLY_AMT;
        CICPDTL.MLY_VOL = CIB3070_AWA_3070.MLY_VOL;
        CICPDTL.SYY_AMT = CIB3070_AWA_3070.SYY_AMT;
        CICPDTL.YLY_AMT = CIB3070_AWA_3070.YLY_AMT;
        CICPDTL.TM_AMT = CIB3070_AWA_3070.TM_AMT;
        CICPDTL.SE_AMT = CIB3070_AWA_3070.SE_AMT;
        CICPDTL.LMT_AMT1 = CIB3070_AWA_3070.LMT_AMT1;
        CICPDTL.LMT_AMT2 = CIB3070_AWA_3070.LMT_AMT2;
        CICPDTL.LMT_AMT3 = CIB3070_AWA_3070.LMT_AMT3;
        CICPDTL.LMT_AMT4 = CIB3070_AWA_3070.LMT_AMT4;
        CICPDTL.REL_TYP = CIB3070_AWA_3070.REL_TYP;
        CICPDTL.BAL_AMT = CIB3070_AWA_3070.BAL_AMT;
        CICPDTL.BAL_TYP = CIB3070_AWA_3070.BAL_TYP;
        CICPDTL.LMT_STSW = CIB3070_AWA_3070.LMT_STSW;
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CIB3070_AWA_3070.TIMES50[WS_I-1].CON_TYP1);
            CICPDTL.TIMES50[WS_I-1].CON_TYP1 = CIB3070_AWA_3070.TIMES50[WS_I-1].CON_TYP1;
            CICPDTL.TIMES50[WS_I-1].VAL = CIB3070_AWA_3070.TIMES50[WS_I-1].VAL;
            CICPDTL.TIMES50[WS_I-1].VAL_COND = CIB3070_AWA_3070.TIMES50[WS_I-1].VAL_COND;
        }
        S000_CALL_CIZPDTL();
    }
    public void S000_CALL_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDTL", CICPDTL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
