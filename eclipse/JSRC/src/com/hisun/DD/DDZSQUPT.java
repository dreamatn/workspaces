package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQUPT {
    DBParm DDTMST_RD;
    brParm DDTUPT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 24;
    String WS_ERR_MSG = " ";
    char WS_UPT_END = ' ';
    DDZSQUPT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DDZSQUPT_WS_OUTPUT_DATA();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DDRUPT DDRUPT = new DDRUPT();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSQUPT DDCSQUPT;
    public void MP(SCCGWA SCCGWA, DDCSQUPT DDCSQUPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQUPT = DDCSQUPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQUPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHK_AC_STS_PROC();
        if (pgmRtn) return;
        B030_INQ_UPT_INF();
        if (pgmRtn) return;
    }
    public void B001_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DDCSQUPT.AC;
        DCCPACTY.INPUT.FUNC = '3';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        DDCSQUPT.AC = DCCPACTY.OUTPUT.STD_AC;
        CEP.TRC(SCCGWA, DDCSQUPT.AC);
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_CHK_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSQUPT.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSQUPT.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.REL_AC_NO = DDCSQUPT.AC;
            CICQACRL.DATA.AC_REL = "12";
            CICQACRL.FUNC = '4';
            CICQACRL.FUNC = 'I';
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
            if (CICQACRL.RC.RC_CODE == 0) {
                DDCSQUPT.AC = CICQACRL.O_DATA.O_AC_NO;
            } else {
            }
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_UPT_INF() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        T000_STARTBR_DDTUPT();
        if (pgmRtn) return;
        T000_READNEXT_DDTUPT();
        if (pgmRtn) return;
        while (WS_UPT_END != 'E' 
            && SCCMPAG.FUNC != 'E') {
            B031_OUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_DDTUPT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTUPT();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 93;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B031_OUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_UPT_AC = DDRUPT.KEY.CUS_AC;
        WS_OUTPUT_DATA.WS_UPT_UPT_NO = DDRUPT.KEY.UPT_NO;
        WS_OUTPUT_DATA.WS_UPT_TXN_DATE = DDRUPT.TXN_DATE;
        WS_OUTPUT_DATA.WS_UPT_TXN_MMO = DDRUPT.TXN_MMO;
        WS_OUTPUT_DATA.WS_UPT_CCY = DDRUPT.CCY;
        WS_OUTPUT_DATA.WS_UPT_CCY_TYPE = DDRUPT.CCY_TYPE;
        WS_OUTPUT_DATA.WS_UPT_TXN_AMT = DDRUPT.TXN_AMT;
        WS_OUTPUT_DATA.WS_UPT_TXN_TYPE = DDRUPT.TXN_TYPE;
        WS_OUTPUT_DATA.WS_UPT_PRT_FLAG = DDRUPT.PRT_FLAG;
        WS_OUTPUT_DATA.WS_UPT_PRT_LINE = (short) DDRUPT.PRT_LINE;
        WS_OUTPUT_DATA.WS_UPT_TELLER = DDRUPT.TELLER;
        WS_OUTPUT_DATA.WS_BR = DDRUPT.BR;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 93;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DDTUPT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRUPT);
        DDRUPT.KEY.CUS_AC = DDCSQUPT.AC;
        DDRUPT.PRT_FLAG = '0';
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PRT_FLAG = :DDRUPT.PRT_FLAG";
        DDTUPT_BR.rp.order = "UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_READNEXT_DDTUPT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRUPT, this, DDTUPT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_UPT_END = 'E';
        }
    }
    public void T000_ENDBR_DDTUPT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTUPT_BR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
