package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTHIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 30;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSTHIB_WS_THIS_HEAD WS_THIS_HEAD = new BPZSTHIB_WS_THIS_HEAD();
    BPZSTHIB_WS_THIS_DETAIL WS_THIS_DETAIL = new BPZSTHIB_WS_THIS_DETAIL();
    char WS_TBL_THIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCTTHIB BPCTTHIB = new BPCTTHIB();
    SCCGWA SCCGWA;
    BPCSTHIB BPCSTHIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTHIB BPCSTHIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTHIB = BPCSTHIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTHIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCTTHIB);
        IBS.init(SCCGWA, BPCSTHIB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSTHIB.FUNC == 'B') {
            B010_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        BPCTTHIB.DATE = BPCSTHIB.DATE;
        BPCTTHIB.END_DATE = BPCSTHIB.END_DATE;
        BPCTTHIB.AC = BPCSTHIB.AC;
        BPCTTHIB.VCH_NO = BPCSTHIB.VCH_NO;
        BPCTTHIB.AP_CODE = BPCSTHIB.AP_CODE;
        BPCTTHIB.TLR = BPCSTHIB.TLR;
        BPCTTHIB.BR = BPCSTHIB.BR;
        CEP.TRC(SCCGWA, BPCTTHIB.DATE);
        CEP.TRC(SCCGWA, BPCTTHIB.VCH_NO);
        CEP.TRC(SCCGWA, BPCTTHIB.AP_CODE);
        CEP.TRC(SCCGWA, BPCTTHIB.TLR);
        CEP.TRC(SCCGWA, BPCTTHIB.BR);
        BPCTTHIB.FUNC = 'S';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        BPCTTHIB.FUNC = 'R';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        CEP.TRC(SCCGWA, BPCTTHIB.DATE);
        CEP.TRC(SCCGWA, BPCTTHIB.VCH_NO);
        CEP.TRC(SCCGWA, BPCTTHIB.AP_CODE);
        CEP.TRC(SCCGWA, BPCTTHIB.TLR);
        CEP.TRC(SCCGWA, BPCTTHIB.BR);
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTTHIB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTTHIB.FUNC = 'R';
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
        }
        BPCTTHIB.FUNC = 'E';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 906;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_THIS_DETAIL.WS_THIS_DATE = BPCTTHIB.DATE;
        WS_THIS_DETAIL.WS_THIS_VCH_NO = BPCTTHIB.VCH_NO;
        WS_THIS_DETAIL.WS_THIS_SEQ = BPCTTHIB.SEQ;
        WS_THIS_DETAIL.WS_DC_FLG = BPCTTHIB.DC_FLG;
        WS_THIS_DETAIL.WS_RCE_PBNO = BPCTTHIB.RCE_PBNO;
        WS_THIS_DETAIL.WS_PAY_PBNO = BPCTTHIB.PAY_PBNO;
        WS_THIS_DETAIL.WS_AC_NAME = BPCTTHIB.AC_NAME;
        WS_THIS_DETAIL.WS_CCY = BPCTTHIB.CCY;
        WS_THIS_DETAIL.WS_CCY_TYP = BPCTTHIB.CCY_TYP;
        WS_THIS_DETAIL.WS_AC = BPCTTHIB.AC;
        WS_THIS_DETAIL.WS_THIS_AMT = BPCTTHIB.AMT;
        WS_THIS_DETAIL.WS_THIS_ID_TYP = BPCTTHIB.ID_TYP;
        WS_THIS_DETAIL.WS_THIS_IDNO = BPCTTHIB.IDNO;
        WS_THIS_DETAIL.WS_THIS_AGT_NAME = BPCTTHIB.AGT_NAME;
        WS_THIS_DETAIL.WS_THIS_AGT_IDTYP = BPCTTHIB.AGT_IDTYP;
        WS_THIS_DETAIL.WS_THIS_AGT_IDNO = BPCTTHIB.AGT_IDNO;
        WS_THIS_DETAIL.WS_THIS_CASH_NO = BPCTTHIB.CASH_NO;
        WS_THIS_DETAIL.WS_THIS_RMK = BPCTTHIB.RMK;
        WS_THIS_DETAIL.WS_THIS_BR = BPCTTHIB.BR;
        WS_THIS_DETAIL.WS_THIS_TLR = BPCTTHIB.TLR;
        WS_THIS_DETAIL.WS_THIS_SUP = BPCTTHIB.SUP;
        WS_THIS_DETAIL.WS_THIS_STS = BPCTTHIB.STS;
        WS_THIS_DETAIL.WS_TM = BPCTTHIB.TM;
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_TM);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_THIS_DETAIL);
        SCCMPAG.DATA_LEN = 906;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_BR);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_DATE);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_VCH_NO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_SEQ);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_DC_FLG);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_RCE_PBNO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_PAY_PBNO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_AC_NAME);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_CCY);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_CCY_TYP);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_AC);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_AMT);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_IDNO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_AGT_NAME);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_AGT_IDTYP);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_AGT_IDNO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_CASH_NO);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_RMK);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_BR);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_TLR);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_SUP);
        CEP.TRC(SCCGWA, WS_THIS_DETAIL.WS_THIS_STS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTTHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_THIS, BPCTTHIB);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCTTHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTTHIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
