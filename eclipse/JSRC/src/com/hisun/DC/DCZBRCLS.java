package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZBRCLS {
    DBParm DCTBRCLC_RD;
    brParm DCTBRCLC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int MAX_ROW = 10;
    int MAX_COL = 0;
    int COL_CNT = 0;
    String HIS_COPYBOOK = "DCRBRCLC";
    DCZBRCLS_WS_VARIABLES WS_VARIABLES = new DCZBRCLS_WS_VARIABLES();
    DCZBRCLS_WS_OUTPUT WS_OUTPUT = new DCZBRCLS_WS_OUTPUT();
    DCZBRCLS_WS_COND_FLG WS_COND_FLG = new DCZBRCLS_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DCRBRCLC DCRBRCLC = new DCRBRCLC();
    DCRBRCLC DCRBRCLC = new DCRBRCLC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS0201 DCCS0201;
    public void MP(SCCGWA SCCGWA, DCCS0201 DCCS0201) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS0201 = DCCS0201;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZBRCLS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, DCRBRCLC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (DCCS0201.FUNC == 'C') {
            B100_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (DCCS0201.FUNC == 'D') {
            B200_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (DCCS0201.FUNC == 'B') {
            B300_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS0201.BR_NO);
        CEP.TRC(SCCGWA, DCCS0201.CARD_CLS);
        if (DCCS0201.BR_NO == ' ') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_BR_NO_M_INPUT);
        }
        if (DCCS0201.CARD_CLS.trim().length() == 0 
            && DCCS0201.FUNC != 'B') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CLS);
        }
    }
    public void B100_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRCLC);
        DCRBRCLC.KEY.BR_NO = DCCS0201.BR_NO;
        DCRBRCLC.KEY.CARD_CLS_CD = DCCS0201.CARD_CLS;
        T000_WRITE_DCTBRCLC();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CDDAT_EXIST);
        }
        R000_OUTPUT_FMT();
        if (pgmRtn) return;
        B700_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRCLC);
        DCRBRCLC.KEY.BR_NO = DCCS0201.BR_NO;
        DCRBRCLC.KEY.CARD_CLS_CD = DCCS0201.CARD_CLS;
        T000_READUPD_DCTBRCLC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRBRCLC, DCRBRCLC);
        T000_DELETE_DCTBRCLC();
        if (pgmRtn) return;
        R000_OUTPUT_FMT();
        if (pgmRtn) return;
        B700_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B300_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        R000_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRBRCLC);
        DCRBRCLC.KEY.BR_NO = DCCS0201.BR_NO;
        T000_STARTBR_DCTBRCLC();
        if (pgmRtn) return;
        T000_READNEXT_DCTBRCLC();
        if (pgmRtn) return;
        while (WS_COND_FLG.TBL_FLAG != 'N') {
            R000_OUTPUT_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_DCTBRCLC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTBRCLC();
        if (pgmRtn) return;
    }
    public void B700_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 74;
        if (DCCS0201.FUNC == 'C') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRBRCLC;
        }
        if (DCCS0201.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRBRCLC;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_MPAG() throws IOException,SQLException,Exception {
        WS_OUTPUT.BR_NO = DCRBRCLC.KEY.BR_NO;
        WS_OUTPUT.CARD_CLS = DCRBRCLC.KEY.CARD_CLS_CD;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 16;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.BR_NO = DCRBRCLC.KEY.BR_NO;
        WS_OUTPUT.CARD_CLS = DCRBRCLC.KEY.CARD_CLS_CD;
        SCCFMT.FMTID = "DC201";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READUPD_DCTBRCLC() throws IOException,SQLException,Exception {
        DCTBRCLC_RD = new DBParm();
        DCTBRCLC_RD.TableName = "DCTBRCLC";
        DCTBRCLC_RD.upd = true;
        IBS.READ(SCCGWA, DCRBRCLC, DCTBRCLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_NOTFND_REC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTBRCLC() throws IOException,SQLException,Exception {
        DCTBRCLC_RD = new DBParm();
        DCTBRCLC_RD.TableName = "DCTBRCLC";
        DCTBRCLC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRBRCLC, DCTBRCLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_COND_FLG.TBL_FLAG = 'D';
        }
    }
    public void T000_DELETE_DCTBRCLC() throws IOException,SQLException,Exception {
        DCTBRCLC_RD = new DBParm();
        DCTBRCLC_RD.TableName = "DCTBRCLC";
        IBS.DELETE(SCCGWA, DCRBRCLC, DCTBRCLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
    }
    public void T000_STARTBR_DCTBRCLC() throws IOException,SQLException,Exception {
        DCTBRCLC_BR.rp = new DBParm();
        DCTBRCLC_BR.rp.TableName = "DCTBRCLC";
        DCTBRCLC_BR.rp.where = "BR_NO = BRCLC_BR_NO";
        IBS.STARTBR(SCCGWA, DCRBRCLC, this, DCTBRCLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
    }
    public void T000_READNEXT_DCTBRCLC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRBRCLC, this, DCTBRCLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
    }
    public void T000_ENDBR_DCTBRCLC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTBRCLC_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS    ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
