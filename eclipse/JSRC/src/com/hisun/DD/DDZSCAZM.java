package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCAZM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD150";
    String WS_MSGID = " ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    DDZSCAZM_WS_FMT WS_FMT = new DDZSCAZM_WS_FMT();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICQACR CICQACR = new CICQACR();
    DCCICKCD DCCICKCD = new DCCICKCD();
    DCCUHDQU DCCUHDQU = new DCCUHDQU();
    SCCGWA SCCGWA;
    DDCSCAZM DDCSCAZM;
    public void MP(SCCGWA SCCGWA, DDCSCAZM DDCSCAZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCAZM = DDCSCAZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCAZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_CAZM();
        if (pgmRtn) return;
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_GET_CAZM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-GET-CAZM");
        IBS.init(SCCGWA, DCCUHDQU);
        DCCUHDQU.INPUT.I_QUERY_TYP = '2';
        DCCUHDQU.INPUT.I_CARD_HLDR_CINO = DDCSCAZM.INPUT_DATA.CI_NO;
        DCCUHDQU.INPUT.I_CARD_LNK_TYP = '0';
        DCCUHDQU.INPUT.I_CARD_ADSC_TYP = '0';
        DCCUHDQU.INPUT.I_CARD_PHY_TYP = '1';
        S000_CALL_DCZUHDQU();
        if (pgmRtn) return;
        for (WS_I = 1; DCCUHDQU.OUTPUT[WS_I-1].O_CARD_NO.trim().length() != 0 
            && WS_I <= 100; WS_I += 1) {
            CEP.TRC(SCCGWA, DCCUHDQU.OUTPUT[WS_I-1].O_CARD_NO);
            CEP.TRC(SCCGWA, DCCUHDQU.OUTPUT[WS_I-1].O_ISSU_BR);
            CEP.TRC(SCCGWA, DCCUHDQU.OUTPUT[WS_I-1].O_CARD_HLDR_IDTYP);
            CEP.TRC(SCCGWA, DCCUHDQU.OUTPUT[WS_I-1].O_CARD_HLDR_IDNO);
            IBS.init(SCCGWA, DCCICKCD);
            DCCICKCD.INP_DATA.CARD_NO = DCCUHDQU.OUTPUT[WS_I-1].O_CARD_NO;
            S000_CALL_DCZICKCD();
            if (pgmRtn) return;
            WS_FMT.WS_CARD_LIST[WS_I-1].WS_CARD_NO = DCCUHDQU.OUTPUT[WS_I-1].O_CARD_NO;
            WS_FMT.WS_CARD_LIST[WS_I-1].WS_BR = DCCUHDQU.OUTPUT[WS_I-1].O_ISSU_BR;
            WS_FMT.WS_CARD_LIST[WS_I-1].WS_ID_TYPE = DCCUHDQU.OUTPUT[WS_I-1].O_CARD_HLDR_IDTYP;
            WS_FMT.WS_CARD_LIST[WS_I-1].WS_ID_NO = DCCUHDQU.OUTPUT[WS_I-1].O_CARD_HLDR_IDNO;
            WS_FMT.WS_CARD_LIST[WS_I-1].WS_BAL = DCCICKCD.OUTP_DATA.DD_AC_BAL;
            CEP.TRC(SCCGWA, DCCICKCD.OUTP_DATA.DD_AC_BAL);
            WS_FMT.WS_CAZM.WS_TOTAL_NUM = WS_FMT.WS_CAZM.WS_TOTAL_NUM + 1;
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_CAZM.WS_TOTAL_NUM);
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 11806;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR", CICQACR);
    }
    public void S000_CALL_DCZUHDQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUHDQU", DCCUHDQU);
    }
    public void S000_CALL_DCZICKCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHK-OPN-CRD-INF", DCCICKCD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
