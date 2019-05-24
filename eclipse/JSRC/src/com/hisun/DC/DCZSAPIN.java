package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSAPIN {
    brParm DCTCITAP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    String K_TBL_CITAP = "DCTCITAP";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSAPIN_WS_BRW_OUTPUT WS_BRW_OUTPUT = new DCZSAPIN_WS_BRW_OUTPUT();
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITAP DCRCITAP = new DCRCITAP();
    int WS_S_DT = 0;
    int WS_E_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSAPIN DCCSAPIN;
    public void MP(SCCGWA SCCGWA, DCCSAPIN DCCSAPIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSAPIN = DCCSAPIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSAPIN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSER_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSAPIN.S_DT);
        CEP.TRC(SCCGWA, DCCSAPIN.E_DT);
        if (DCCSAPIN.S_DT == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_DT_MUST_INPUT);
        }
        if (DCCSAPIN.E_DT == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_E_DT_MUST_INPUT);
        }
        if (DCCSAPIN.E_DT < DCCSAPIN.S_DT) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_S_MUST_LESS_THAN_E);
        }
    }
    public void B020_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCITAP);
        CEP.TRC(SCCGWA, DCCSAPIN.S_DT);
        CEP.TRC(SCCGWA, DCCSAPIN.E_DT);
        T000_STARTBR_DCTCITAP();
        if (pgmRtn) return;
        T000_READNEXT_DCTCITAP();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (WS_RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B022_OUTPUT_DETAIL();
            if (pgmRtn) return;
            T000_READNEXT_DCTCITAP();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCITAP();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 177;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        CEP.TRC(SCCGWA, DCRCITAP.KEY.APP_BAT_NO);
        CEP.TRC(SCCGWA, DCRCITAP.APP_NUM);
        CEP.TRC(SCCGWA, DCRCITAP.RECV_FILE_NM);
        CEP.TRC(SCCGWA, DCRCITAP.SENT_FILE_NM);
        CEP.TRC(SCCGWA, DCRCITAP.CRT_NUM);
        CEP.TRC(SCCGWA, DCRCITAP.RCD_STS);
        CEP.TRC(SCCGWA, DCRCITAP.TXN_DT);
        WS_BRW_OUTPUT.WS_BRW_APP_BAT_NO = DCRCITAP.KEY.APP_BAT_NO;
        WS_BRW_OUTPUT.WS_BRW_APP_NUM = DCRCITAP.APP_NUM;
        WS_BRW_OUTPUT.WS_BRW_RECV_FILE_NM = DCRCITAP.RECV_FILE_NM;
        WS_BRW_OUTPUT.WS_BRW_SENT_FILE_NM = DCRCITAP.SENT_FILE_NM;
        WS_BRW_OUTPUT.WS_BRW_CRT_NUM = DCRCITAP.CRT_NUM;
        WS_BRW_OUTPUT.WS_BRW_RCD_STS = DCRCITAP.RCD_STS;
        WS_BRW_OUTPUT.WS_BRW_APP_DT = DCRCITAP.TXN_DT;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 177;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCITAP() throws IOException,SQLException,Exception {
        WS_S_DT = DCCSAPIN.S_DT;
        WS_E_DT = DCCSAPIN.E_DT;
        DCTCITAP_BR.rp = new DBParm();
        DCTCITAP_BR.rp.TableName = "DCTCITAP";
        DCTCITAP_BR.rp.col = "APP_BAT_NO, APP_NUM, RECV_FILE_NM, SENT_FILE_NM, RCD_STS, CRT_NUM, TXN_DT";
        DCTCITAP_BR.rp.where = "TXN_DT >= :WS_S_DT "
            + "AND TXN_DT <= :WS_E_DT";
        DCTCITAP_BR.rp.order = "TXN_DT DESC";
        IBS.STARTBR(SCCGWA, DCRCITAP, this, DCTCITAP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CITAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTCITAP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCITAP, this, DCTCITAP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CITAP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTCITAP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCITAP_BR);
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
