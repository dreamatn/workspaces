package com.hisun.CI;

import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3020 {
    int JIBS_tmp_int;
    String WS_MSGID = " ";
    short WS_I = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICMDTL CICMDTL = new CICMDTL();
    SCCGWA SCCGWA;
    CIB3020_AWA_3020 CIB3020_AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3020_AWA_3020>");
        CIB3020_AWA_3020 = (CIB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMDTL);
        CICMDTL.FUNC = CIB3020_AWA_3020.FUNC;
        CICMDTL.TYPE = CIB3020_AWA_3020.TYPE;
        CICMDTL.OBJ_TYP = CIB3020_AWA_3020.OBJ_TYP;
        CICMDTL.TX_TYPE = CIB3020_AWA_3020.TX_TYPE;
        CICMDTL.LC_NO = CIB3020_AWA_3020.LC_NO;
        CICMDTL.SEQ = CIB3020_AWA_3020.SEQ;
        CICMDTL.CON_TYP = CIB3020_AWA_3020.CON_TYP;
        CICMDTL.LVL_CON_TYP = CIB3020_AWA_3020.LVLCNTYP;
        CICMDTL.LVL = CIB3020_AWA_3020.LVL;
        CICMDTL.LC_CCY = CIB3020_AWA_3020.LC_CCY;
        CICMDTL.DAY_STA = CIB3020_AWA_3020.DAY_STA;
        CICMDTL.DAY_END = CIB3020_AWA_3020.DAY_END;
        CICMDTL.TXN_AMT = CIB3020_AWA_3020.TXN_AMT;
        CICMDTL.DLY_AMT = CIB3020_AWA_3020.DLY_AMT;
        CICMDTL.DLY_VOL = CIB3020_AWA_3020.DLY_VOL;
        CICMDTL.MLY_AMT = CIB3020_AWA_3020.MLY_AMT;
        CICMDTL.MLY_VOL = CIB3020_AWA_3020.MLY_VOL;
        CICMDTL.SYY_AMT = CIB3020_AWA_3020.SYY_AMT;
        CICMDTL.YLY_AMT = CIB3020_AWA_3020.YLY_AMT;
        CICMDTL.TM_AMT = CIB3020_AWA_3020.TM_AMT;
        CICMDTL.SE_AMT = CIB3020_AWA_3020.SE_AMT;
        CICMDTL.LMT_AMT1 = CIB3020_AWA_3020.LMT_AMT1;
        CICMDTL.LMT_AMT2 = CIB3020_AWA_3020.LMT_AMT2;
        CICMDTL.LMT_AMT3 = CIB3020_AWA_3020.LMT_AMT3;
        CICMDTL.LMT_AMT4 = CIB3020_AWA_3020.LMT_AMT4;
        if (CIB3020_AWA_3020.REL_TYP == null) CIB3020_AWA_3020.REL_TYP = "";
        JIBS_tmp_int = CIB3020_AWA_3020.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) CIB3020_AWA_3020.REL_TYP += " ";
        CICMDTL.REL_TYP = CIB3020_AWA_3020.REL_TYP.substring(2 - 1, 2 + 1 - 1).charAt(0);
        CICMDTL.BAL_AMT = CIB3020_AWA_3020.BAL_AMT;
        CICMDTL.BAL_TYP = CIB3020_AWA_3020.BAL_TYP;
        CICMDTL.LMT_STSW = CIB3020_AWA_3020.LMT_STSW;
        CICMDTL.ACT_SCENE = CIB3020_AWA_3020.ACT_SCEN;
        CICMDTL.TY_FLG = CIB3020_AWA_3020.TY_FLG;
        CICMDTL.STA_TM = CIB3020_AWA_3020.STA_TM;
        CICMDTL.END_TM = CIB3020_AWA_3020.END_TM;
        S000_CALL_CIZSDTL();
    }
    public void S000_CALL_CIZSDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-DTL", CICMDTL);
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
