package com.hisun.PY;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PYOT8100 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT = "PYZ05";
    String U_PYZRFPCI = "PY-R-ADW-FPCI";
    PYOT8100_WS_VARIABLES WS_VARIABLES = new PYOT8100_WS_VARIABLES();
    PYOT8100_WS_FMT_PYZ05 WS_FMT_PYZ05 = new PYOT8100_WS_FMT_PYZ05();
    PYCMSG_ERROR_MSG ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PYCRFPCI PYCRFPCI = new PYCRFPCI();
    PYRFPCI PYRFPCI = new PYRFPCI();
    SCCGWA SCCGWA;
    PYCI8100 PYCI8100;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PYOT8100 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PYCI8100 = new PYCI8100();
        IBS.init(SCCGWA, PYCI8100);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PYCI8100);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_FMT_PYZ05);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READ_FPCI_PROC();
        if (pgmRtn) return;
    }
    public void B100_READ_FPCI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PYRFPCI);
        IBS.init(SCCGWA, PYCRFPCI);
        PYCRFPCI.FUNC = 'R';
        PYRFPCI.KEY.TX_DT = PYCI8100.TX_DT;
        PYRFPCI.KEY.TX_JRNNO = PYCI8100.TX_JRN;
        CEP.TRC(SCCGWA, PYRFPCI.KEY.TX_DT);
        CEP.TRC(SCCGWA, PYRFPCI.KEY.TX_JRNNO);
        PYCRFPCI.REC_PTR = PYRFPCI;
        PYCRFPCI.REC_LEN = 1977;
        S000_CALL_PYZRFPCI();
        if (pgmRtn) return;
        if (PYCRFPCI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, PYCRFPCI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_FMT_PYZ05.TX_DT = PYRFPCI.KEY.TX_DT;
        WS_FMT_PYZ05.TX_JRNNO = PYRFPCI.KEY.TX_JRNNO;
        WS_FMT_PYZ05.TX_TYP = PYRFPCI.TX_TYP;
        WS_FMT_PYZ05.PAY_FLG = PYRFPCI.PAY_FLG;
        WS_FMT_PYZ05.PAY_AC = PYRFPCI.PAY_AC;
        WS_FMT_PYZ05.PAY_CNM = PYRFPCI.PAY_CNM;
        WS_FMT_PYZ05.PAY_MAC = PYRFPCI.PAY_MAC;
        WS_FMT_PYZ05.PAY_ACNM = PYRFPCI.PAY_ACNM;
        WS_FMT_PYZ05.PAY_IDTYP = PYRFPCI.PAY_IDTYP;
        WS_FMT_PYZ05.PAY_IDNO = PYRFPCI.PAY_IDNO;
        WS_FMT_PYZ05.REV_NO = PYRFPCI.REV_NO;
        WS_FMT_PYZ05.CLT_AC = PYRFPCI.CLT_AC;
        WS_FMT_PYZ05.CLT_CNM = PYRFPCI.CLT_CNM;
        WS_FMT_PYZ05.IN_AC = PYRFPCI.IN_AC;
        WS_FMT_PYZ05.IN_CNM = PYRFPCI.IN_CNM;
        WS_FMT_PYZ05.CCY = PYRFPCI.CCY;
        WS_FMT_PYZ05.TX_AMT = PYRFPCI.TX_AMT;
        WS_FMT_PYZ05.BV_DT = PYRFPCI.BV_DT;
        WS_FMT_PYZ05.BV_CODE = PYRFPCI.BV_CODE;
        WS_FMT_PYZ05.BV_NO = PYRFPCI.BV_NO;
        WS_FMT_PYZ05.BILL_NO = PYRFPCI.BILL_NO;
        WS_FMT_PYZ05.F_TYP = PYRFPCI.F_TYP;
        WS_FMT_PYZ05.VCHRF_NO = PYRFPCI.VCHRF_NO;
        WS_FMT_PYZ05.F_TXCD = PYRFPCI.F_TXCD;
        WS_FMT_PYZ05.CLT_CNTY = PYRFPCI.CLT_CNTY;
        WS_FMT_PYZ05.PAY_REMK = PYRFPCI.PAY_REMK;
        WS_FMT_PYZ05.CLT_REMK = PYRFPCI.CLT_REMK;
        WS_FMT_PYZ05.IN_REMK = PYRFPCI.IN_REMK;
        WS_FMT_PYZ05.STS = PYRFPCI.STS;
        WS_FMT_PYZ05.PAY_BR = PYRFPCI.PAY_BR;
        WS_FMT_PYZ05.CLT_BR = PYRFPCI.CLT_BR;
        WS_FMT_PYZ05.AUH_TLR = PYRFPCI.AUH_TLR;
        WS_FMT_PYZ05.TX_TLR = PYRFPCI.TX_TLR;
        WS_FMT_PYZ05.IN_DT = PYRFPCI.CR_DT;
        WS_FMT_PYZ05.IN_JRNNO = PYRFPCI.CR_JRNNO;
        WS_FMT_PYZ05.IN_TLR = PYRFPCI.CR_TLR;
        WS_FMT_PYZ05.OD_DT = PYRFPCI.BTX_DT;
        WS_FMT_PYZ05.OD_JRNNO = PYRFPCI.BTX_JRNNO;
        WS_FMT_PYZ05.TS = PYRFPCI.TS;
        B110_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void S000_CALL_PYZRFPCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_PYZRFPCI, PYCRFPCI);
    }
    public void B110_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FMT_PYZ05);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT_PYZ05;
        SCCFMT.DATA_LEN = 1869;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
